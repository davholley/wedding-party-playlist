<template>
   <div class="formfield" id="playlistselect"> 
    <label for="playList">{{ labelText }}</label>
    <select id="playList" name="playList" class="formfield selectbox"
      @change="updateSelectionChoice" v-model="selectedId">
      <option value="">--- Please Select a Playlist ---</option>
      
    </select>
  
  <div class="playlists">
    <section v-for="playlist in SpotifyPlaylists" v-bind:key="playlist.id">
    <h1 >{{playlist.name}}</h1>
    <h2>{{playlist.description == ""? "No Description": playlist.description}}</h2>
    </section>
    
  </div>
  </div>
</template>
<script>

export default {
  name: "home",
  props:["SpotifyPlaylists"],
  data(){
  return{

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
.playlists{
  display: flex;
}

.formfield {
    display: flex;
    margin-bottom: 1rem;
    font-family: Georgia, 'Times New Roman', Times, serif;
    font-style: italic;
    justify-content: left;
    align-items: left;
}
</style>
