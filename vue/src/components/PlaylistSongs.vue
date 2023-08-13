<template>
<div><div v-for="song in this.$store.state.playlistSongs" v-bind:key="song.track.id">
      <span>{{song.track.name}} - {{song.track.artists[0].name}}</span>
    </div></div>
  
</template>

<script>
export default {

}
</script>

<style>

</style>