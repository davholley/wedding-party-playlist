<template>
  <div class="home">
    <h1><span class="box-outer">Welcome Hom{{e}} </span></h1>

    <div class="main-content">
      <!-- Left Side: Playlist Creation and Container -->
      <div class="playlist-section">
        <form action="">
          <input class="label" v-model="playlist.name" type="text" name="playlistName" id="playlistName">
          <input class="label" v-model="playlist.description" type="text" name="playlistDesc" id="playlistDesc">
          <button @click.prevent="createPlaylist">Create Playlist</button>
        </form>
        <div class="playlist-container">
          <play-list v-bind:SpotifyPlaylists="SpotifyPlaylists"></play-list>
          <!-- ... other playlist related elements ... -->
        </div>
      </div>

      <!-- Right Side: Song Search, Must-Have, and Do Not Play Containers -->
      <div class="song-containers">
        <!-- Search Box -->
        <div class="search-container">
          <input class="search-box" v-model="searchQuery" type="text" placeholder="Search for a song...">
          <button @click.prevent="searchSong">Search</button>
        </div>
        <search-results :results="searchResults" @add-must-have="addMustHaveSong" @add-do-not-play="addDoNotPlaySong" @add-to-playlist="addSongToPlaylist"></search-results>

        <!-- Must-Have Songs Container -->
        <div class="must-have-container song-container" @drop="dropMustHave" @dragover.prevent>
          <h2>Must-Have Songs</h2>
          <ul>
            <li v-for="song in mustHaveSongs" :key="song.id">{{ song.name }}</li>
          </ul>
        </div>

        <!-- Do Not Play Songs Container -->
        <div class="do-not-play-container song-container" @drop="dropDoNotPlay" @dragover.prevent>
          <h2>Do Not Play Songs</h2>
          <ul>
            <li v-for="song in doNotPlaySongs" :key="song.id">{{ song.name }}</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SpotifyService from '../services/SpotifyService';
import DatabaseService from '../services/DatabaseService';

import PlayList from '../components/PlayList.vue';
import SearchResults from '../components/SearchResults.vue';


export default {
  components: { 
    PlayList,
    SearchResults, 
  },
  


  name: "home",
  
  data(){
  return {
  e: '\ue00e',
  playlist: {"name": "New Playlist","description": "Playlist Description","public": true},
  DatabasePlaylists: {},
  SpotifyPlaylists : [],
  hasSpotify: false,
  searchQuery: '', 
  mustHaveSongs: [], 
  doNotPlaySongs: [], 
  searchResults: [], 
  searchPerformed: false,
}
  },
  
  
  methods : {
    createPlaylist(){
      
     
      SpotifyService.createPlaylist(localStorage.getItem('bearer'), this.playlist).then(
        (response)=> {
          if (response.status === 201){console.log(response.data);
          let dataPlay = {"playlistId": response.data.id, "playlistName" : response.data.name, "dj_id": parseInt(this.$store.state.user.id)  };
          DatabaseService.createPlaylist(dataPlay);
          window.location = "http://localhost:8080/";
          }
          

        }
      );
    },
    searchSong() {
    this.searchPerformed = true;
    SpotifyService.searchSong(localStorage.getItem('bearer'), this.searchQuery).then((response) => {
      this.searchResults = response.data.tracks.items;
      console.log(this.searchResults);
    });
  },

  addMustHaveSong(song) {
    this.mustHaveSongs.push(song);
  },

  addDoNotPlaySong(song) {
    this.doNotPlaySongs.push(song);
  },
  dropMustHave(event) {
    // Get the track data from the dataTransfer object
    const track = JSON.parse(event.dataTransfer.getData('text/plain'));

    // Add the track to the mustHaveSongs array
    this.mustHaveSongs.push(track);
  },
  dropDoNotPlay(event) {
    // Get the track data from the dataTransfer object
    const track = JSON.parse(event.dataTransfer.getData('text/plain'));

    // Add the track to the mustHaveSongs array
    this.doNotPlaySongs.push(track);
  },
    
  },
  created(){
      SpotifyService.getBearer()
      DatabaseService.getPlaylists(this.$store.state.user.id).then((response)=>{
        
        this.DatabasePlaylists = response.data;
        this.DatabasePlaylists.forEach(playlist => {
          let id = playlist.playlistId;
          SpotifyService.getPlaylist(id).then((result)=>{this.SpotifyPlaylists.push(result.data)});
          
        }); 
      })
    },
    comments:{
      PlayList,
    }
};
</script>
<style scoped>
body {
  display: block;
  background-image: url("../assets/Background1.jpg");
  background-size: 100vw 100vh;
}

.form-input-group {
  margin-bottom: 1rem;
  padding-right: 3%;
}

.label {
  width: 120px;
  display: inline-block;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: italic;
}

h1 {
  display: flex;
  justify-content: center;
  letter-spacing: 3px;
  font-family: 'JosephSophia';
  font-size: 80px;
  color: #D4a373;
  text-shadow: 4px 4px black;
}

button {
  display: inline-block;
  width: 105px;
  height: 22px;
  margin-right: 0.5rem;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: italic;
}

.sgnbutton {
  display: flex;
  justify-content: center;
  align-content: center;
  margin: 3px;
}

.userbox {
  background-color: #FEFAE0;
  height: 40px;
  width: 280px;
  border-radius: 12px;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: italic;
}

.box-outer {
  background-color: rgb(254, 250, 224, .75);
  display: flex;
  justify-content: center;
  position: relative;
  align-self: center;
  border-radius: 12px;
  width: 500px;
  height: 100px;
}

.home {
  display: flex;
  flex-direction: column;
}

.main-content {
  display: flex;
}

.playlist-section {
  width: 50%;
}

.playlist-container {
  background-color: #f0f0f0;
  padding: 20px;
  border-radius: 10px; /* Rounded corners */
  margin: 20px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Add shadow for a card effect */
}

.song-containers {
  width: 75%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.search-container {
  display: flex;
  justify-content: center;
  margin: 20px;
  width: 100%;
}

.search-box {
  width: 300px;
  padding: 10px;
  border-radius: 5px;
  font-size: 16px;
}

.song-container {
  background-color: #f0f0f0;
  padding: 20px;
  border-radius: 10px; /* Rounded corners */
  margin: 20px;
  width: 50%;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Add shadow for a card effect */
}

.must-have-container ul,
.do-not-play-container ul {
  list-style-type: none; /* Remove bullets */
  padding: 0; /* Remove padding */
}

.must-have-container li,
.do-not-play-container li {
  padding: 10px;
  border-bottom: 1px solid #ccc; /* Add a line between songs */
}

.must-have-container li:last-child,
.do-not-play-container li:last-child {
  border-bottom: none; /* Remove the line for the last song */
}

h2 {
  font-family: 'Georgia', 'Times New Roman', Times, serif;
  font-style: italic;
  color: #333;
}
</style>
