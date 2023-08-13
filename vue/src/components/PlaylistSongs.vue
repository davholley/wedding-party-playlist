<template>
<div >
  <div v-for="song in this.$store.state.playlistSongs" v-bind:key="song.track.id" class="song-card">
    
    <img :src="song.track.album.images[0].url" alt="Album Cover" />
    <span>{{song.track.name}} - {{song.track.artists[0].name}}
      
    </span>
    <button @click="removeSong(song.track.uri,song)"><i class="fa-regular fa-trash-can fa-2xl"></i></button>
    
    </div>
    
    
    </div>
  
</template>

<script>
import SpotifyService from '../services/SpotifyService'


export default {
  data(){
   return {
    
  }  
  },
  
  methods:{
    removeSong(id,song){
      
      SpotifyService.removeSongFromPlaylist(localStorage.getItem("bearer"), this.$store.state.currentPlaylist,id,this.$store.state.currentPlaylistSnapshot )
      this.$store.state.playlistSongs.delete(song)

    }
  }

}

</script>

<style scoped>
.song-card{
  display: flex;
  flex-wrap: wrap;
  font-size: 26px;
  align-items: center;
  padding-bottom: 5px ;
}
.song-card img {
  height: 60px;
  width: 60px;
  padding-right: 10px;
}
.song-card span{
  
  align-items: center;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: italic;
    
  padding-right: 20px;
  

}
button{
  border: none;
  
}


</style>