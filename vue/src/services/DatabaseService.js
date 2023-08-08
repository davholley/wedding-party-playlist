import axios from "axios";

export default {

    createPlaylist(playlist) {
      return axios.post('/playlist/add', playlist)
    },
  
   
  
  }