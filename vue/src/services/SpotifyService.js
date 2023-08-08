import axios from "axios";

export default{
    getSpotifyUser(token){
        return axios.get("https://api.spotify.com/v1/me", {headers: {'Authorization': `Bearer ${token}`}});
    }
}