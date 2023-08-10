<template>
  <div class="home">
    <h1>Welcome Home</h1>
    
    <form  action="" >
      <input class="userbox" v-model="playlist.name" type="text" name="playlistName" id="playlistName">
      <input class="userbox" v-model="playlist.description" type="text" name="playlistDesc" id="playlistDesc">
    </form>
    <button @click.prevent="createPlaylist">Create Playlist</button>
    <play-list v-bind:SpotifyPlaylists="SpotifyPlaylists"></play-list>
    
    
  </div>
</template>

<script>
import SpotifyService from '../services/SpotifyService';
import DatabaseService from '../services/DatabaseService';

import PlayList from '../components/PlayList.vue';


export default {
  components: { PlayList },


  name: "home",
  
  data(){
  return {  playlist: {"name": "New Playlist","description": "New playlist description","public": true},
  DatabasePlaylists: {},
  SpotifyPlaylists : [],
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
<style>
body{
  display: block;
background-image: url("../assets/Background1.jpg");
  background-size: 100vw 100vh;  



}
</style>
<style scoped>
.form-input-group {
  margin-bottom: 1rem;
  padding-right: 3%;
  
}
label {
  width:75px;
  display: inline-block;
  margin-right: 0.5rem;
  
}
h1{
  display: flex;
  justify-content: center;
  letter-spacing: 3px;
  font-family: 'JosephSophia';
  font-size: 90px;
  color: #D4a373;
  text-shadow: 4px 4px black ;
  
}
button{
  display: flex;
  justify-content: center;
  border-radius: 6px;
  line-height: 22px;
  background-color: #fcf7d9;
  font-family: Georgia, 'Times New Roman', Times, serif;
   font-style: italic;
}
.sgnbutton{
  display: flex;
  justify-content: center;
  align-content: center;
  margin: 3px;
  
  
}
.userbox{
  background-color: #FEFAE0;
  height: 40px;
  width: 280px;
  border-radius: 12px;
  font-family: Georgia, 'Times New Roman', Times, serif;
   font-style: italic;
}

</style>
