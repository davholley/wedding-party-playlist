<template>
   <div class="formfield" id="playlistName"> 
    <div class="selectDiv">
      <label for="playList"></label>
    <select id="playlistName" name="PlaylistName" class="formfield selectbox"
        @change="updateSelectionChoice" v-model="playlist">
      <option value="">--- Please Select a Playlist ---</option>
      <option v-for="playlist in Playlists" v-bind:key="playlist.playlistId" v-bind:value="playlist.playlistId">
    {{playlist.playlistName}}
    </option>
    </select>
    </div>
    <br>
   
    
    <div>
      <playlist-songs v-bind:Playlist="currentPlaylist" v-if="currentPlaylist" ></playlist-songs>
    </div>
    
      
    
  </div>
</template>
<script>
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
    

   
  }
  }
,
methods:{
  updateSelectionChoice(){
    if(this.playlist != ""){
        SpotifyService.getPlaylist(this.playlist).then((response)=>{
            this.currentPlaylist =response.data;
            this.$store.state.currentPlaylist = this.currentPlaylist.id;
        })
      
    }
    else{
    
      this.currentPlaylist = null;
      this.$store.state.currentPlaylist = "";
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
  align-items: center;
  height: 100px;
  

}

.formfield {
    display: flex;
    flex-wrap: wrap;
    margin-bottom: 1rem;
    height: 40px;
    width: 80%;
    font-family: Georgia, 'Times New Roman', Times, serif;
    font-style: italic;
    justify-content: center;
    align-items: left;
    
}
.break {
  flex-basis: 100%;
  height: 0;
}
.selectDiv{
  display: flex;
  flex-basis: 100%;
  
  justify-content: center;
  width: 280px;
}

</style>
