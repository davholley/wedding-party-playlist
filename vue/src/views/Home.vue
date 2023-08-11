<template>
  <div class="home">
    <h1><span class="box-outer">Welcome Hom{{e}} </span></h1>
    
    <form action="" >
      <input class="label" v-model="playlist.name" type="text" name="playlistName" id="playlistName">
      <input class="label" v-model="playlist.description" type="text" name="playlistDesc" id="playlistDesc">
       <button @click.prevent="createPlaylist">Create Playlist</button>
    </form>
   
    <play-list v-bind:SpotifyPlaylists="SpotifyPlaylists"></play-list>
    
     <div class="formfield" id="playlistName">
       <selection-box class="playlistName" v-bind="SpotifyPlaylists" mutation="SET_USER"/>
          </div>
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
  return {
  e: '\ue00e',
  playlist: {"name": "New Playlist","description": "Playlist Description","public": true},
  DatabasePlaylists: {},
  SpotifyPlaylists : [],
  hasSpotify: false
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
.label {
  width:120px;
  display: inline-block;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: italic;

  
}
h1{
  display: flex;
  justify-content: center;
  letter-spacing: 3px;
  font-family: 'JosephSophia';
  font-size: 80px;
  color: #D4a373;
  text-shadow: 4px 4px black ;
  
}
button{
  display: inline-block;
  width:105px;
  height: 22px;
  display: inline-block;
  margin-right: 0.5rem;
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

</style>
