<template>
   <!-- <div class="formfield" id="userselect"> 
    <label for="categoryList">{{ labelText }}</label>
    <select id="categoryList" name="categoryList" class="formfield selectbox"
      @change="updateSelectionChoice" v-model="selectedId">
      <option value="">--- Please select a category ---</option>
      
    </select>
  </div> -->
  <div>
    <section v-for="playlist in SpotifyPlaylists" v-bind:key="playlist.id">
    <h1 >{{playlist.name}}</h1>
    <h2>{{playlist.description == ""? "No Description": playlist.description}}</h2>
    </section>
    
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
  padding-left: 5px;
  padding-right: 5px;
  border-radius: 20px;
}
h1,h2{
  margin: 5px;
}
</style>
