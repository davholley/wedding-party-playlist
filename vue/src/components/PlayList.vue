<template>
   <div class="formfield" id="playlistName"> 
    <div class="selectDiv">
      <label for="playList"></label>
    <select id="playlistName" name="PlaylistName"  class="formfield-selectbox"
        @change="updateSelectionChoice" v-model="playlist">
      <option value="">--- Please Select a Playlist ---</option>
      <option v-for="playlist in Playlists" v-bind:key="playlist.playlistId" v-bind:value="playlist">
    {{playlist.playlistName}}
    </option>
    </select>
    </div>
    
   
    
    <div class="playlist-songs">
      <playlist-songs v-bind:Playlist="currentPlaylist" v-bind:PlaylistOwner="currentPlaylistOwner" v-if="currentPlaylist" ></playlist-songs>
    </div>
    
      
    
  </div>
</template>
<script>
import DatabaseService from '../services/DatabaseService';
import SpotifyService from '../services/SpotifyService';
import PlaylistSongs from './PlaylistSongs.vue';


export default {
  components: { PlaylistSongs },
  name: "Playlist",
  props: ["Playlists"],
  data(){
  return{
    playlist: "",
    currentPlaylist: null,
    currentPlaylistOwner: null,
    height: null,
    

   
  }
  }
,
methods:{
  updateSelectionChoice(){
    if(this.playlist != ""){
       this.$store.state.mustHaveSongs = []
      this.$store.state.doNotPlaySongs = []

        this.currentPlaylistOwner = this.playlist.dj_id
        SpotifyService.getPlaylist(this.playlist.playlistId).then((response)=>{
          
            this.currentPlaylist =response.data;
            this.$store.state.currentPlaylist = this.currentPlaylist.id;
        })
        DatabaseService.getPlaylistSongs(this.playlist.playlistId).then((response)=>{
          let songs = response.data;
          
          for (let song in songs) {
            if(songs[song].mustPlay){
              this.$store.state.mustHaveSongs.push(songs[song])
            }
            if(songs[song].doNotPlay){
              this.$store.state.doNotPlaySongs.push(songs[song])
            }
          }
           
        })
        let parent = document.getElementById("playlist-container")
        this.height = parent.offsetHeight

        
      
    }
    else{
      this.currentPlaylistOwner = null;
      this.currentPlaylist = null;
      this.$store.state.currentPlaylist = "";
      this.$store.state.mustHaveSongs = []
      this.$store.state.doNotPlaySongs = []

    }
  },
  
}
}
</script>
<style scoped>

section{
  background: rgba(210, 180, 140, 0.849) ;
  border-radius: 20px;
}
/* h1,h2{
 
 } */
option{
  display: flex;
  flex-direction: column;
  justify-content: center;
  
  height: 100px;
  

}

.formfield {
    display: grid;
    grid-template-rows: 40px 1fr;
    grid-template-columns: 1fr;
    gap: 10px;
    grid-template-areas: "select" "songs";
    flex-wrap: wrap;
    margin-bottom: 1rem;
    flex-grow: 0;
    height: 765px;
    width: 80%;
    font-family: Georgia, 'Times New Roman', Times, serif;
    font-style: italic;
    
    

}
.formfield-selectbox{
  height: 40px;
}
.break {
  flex-basis: 100%;
  height: 0;
}
.selectDiv{
  display: flex;
  width: 100%;
  height: 40px;
  justify-self: center;
  justify-content: center;
  width: 280px;
}
.playlist-songs{
  height: 100%;
  overflow-y: auto;
}

.playlist-songs::-webkit-scrollbar {
  display: none;
}


</style>
