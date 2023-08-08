import axios from "axios";
import store from "../store";

export default{
    getSpotifyUser(token){
        return axios.get("https://api.spotify.com/v1/me", {headers: {'Authorization': `Bearer ${token}`}});
    },
    createPlaylist(bearer, playlist){
        axios.defaults.headers.common['Authorization'] = `Bearer ${bearer}`
        let response = axios.post("https://api.spotify.com/v1/users/" + store.state.spotifyId + "/playlists",
          playlist,);
         axios.defaults.headers.common['Authorization'] = `Bearer ${store.state.token}`
         return response;
    }
}