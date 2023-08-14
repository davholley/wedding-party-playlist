import axios from "axios";

export default {

    createPlaylist(playlist) {
      return axios.post('/playlist/add', playlist)
    },
    
    getPlaylists(id){
      return axios.get(`/${id}/playlists`)
    },
   
    getPlaylistSongs(id){
      return axios.get(`/playlist/${id}/songs`);
    },
    addSongToPlaylist(Song){
      return axios.post('/song/add', Song);
    },
    getOtherUsers(id){
      return axios.get(`/${id}/others`);
    }
  
  }