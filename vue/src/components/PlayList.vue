<template>
   <div class="formfield" id="playlistName"> 
    <label for="playList">{{ labelText }}</label>
    <select id="playlistName" name="PlaylistName" class="formfield selectbox"
        @change="updateSelectionChoice" v-model="playlist">
      <option value="">--- Please Select a Playlist ---</option>
      <option v-for="playlist in SpotifyPlaylists" v-bind:key="playlist.id" v-bind:value="playlist.id">
    {{playlist.name}}
    </option>
    </select>

  
  
  </div>
</template>
<script>
import SpotifyService from '../services/SpotifyService'

export default {
  name: "home",
  props: ["SpotifyPlaylists"],
  data(){
  return{
    playlist: "",
   
  }
  }
,
methods:{
  updateSelectionChoice(){
    if(this.playlist != ""){
    SpotifyService.getPlaylist(this.playlist).then((response)=>{
      if (response.status == 200){this.$store.state.playlistSongs = response.data.tracks.items;}
    }).catch(()=>{
      console.log("Something Went Wrong");
    })
    }
    else{this.$store.state.playlistSongs = {}}
  }
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
    background-color: #FEFAE0;
    margin-bottom: 1rem;
    height: 40px;
    width: 280px;
    font-family: Georgia, 'Times New Roman', Times, serif;
    font-style: italic;
    justify-content: left;
    align-items: left;
}

</style>
