<template>
  <div class="home">
    <h1>Home</h1>
    <p>If you are seeing this, you are authenticated.</p>
    <form action="" >
      <input  v-model="playlist.name" type="text" name="playlistName" id="playlistName">
      <input  v-model="playlist.description" type="text" name="playlistDesc" id="playlistDesc">
    </form>
    <button @click.prevent="createPlaylist">Create Playlist</button>
    
  </div>
</template>

<script>
import SpotifyService from '../services/SpotifyService';
import DatabaseService from '../services/DatabaseService'

export default {


  name: "home",
  data(){
  return {  playlist: {"name": "New Playlist","description": "New playlist description","public": true},
}
  },
  
  
  methods : {
    createPlaylist(){
      
     
      SpotifyService.createPlaylist(localStorage.getItem('bearer'), this.playlist).then(
        (response)=> {
          if (response.status === 201){console.log(response.data);
          let dataPlay = {"playlistId": response.data.external_urls.spotify, "playlistName" : response.data.name, "dj_id": parseInt(this.$store.state.user.id)  };
          DatabaseService.createPlaylist(dataPlay);
          window.location = "http://localhost:8080/";
          }
          

        }
      );
    }
  }
};
</script>

