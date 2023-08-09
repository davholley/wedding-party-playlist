<template>
  <div id="login">
    
    <form @submit.prevent="login" v-show="!isHash">
      <h1 >Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div  class="form-input-group">
        <label for="username">Username</label>
        <input class="userbox" type="text"  v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input class="userbox" type="password" id="password" v-model="user.password" required />
      </div>
      <div class="sgnbutton">
      <button class="sgnbutton" type="submit">Sign in</button>
      </div>
      <div class="sgnbutton">
        <button  class="sgnbutton" @click.prevent="spotify" >DJ Login</button>
      </div>
      
      <p class="sgnbutton">
      <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
      
    </form>
  </div>
</template>

<script>

import authService from "../services/AuthService";

import spotifyService from "../services/SpotifyService"
export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false,
      client_id : '6d7a9526161e48ba94c6d297e82b6075',
      redirect_uri : 'http://localhost:8080/login',

     scope : 'playlist-modify-public user-read-email',

    url : 'https://accounts.spotify.com/authorize',
    isHash : false
    };
  },
  methods: {
  
    spotify(){
    let state = this.generateRandomString(16);
    
    this.url += '?response_type=token';
    this.url += '&client_id=' + encodeURIComponent(this.client_id);
    this.url += '&scope=' + encodeURIComponent(this.scope);
    this.url += '&redirect_uri=' + encodeURIComponent(this.redirect_uri);
    this.url += '&state=' + encodeURIComponent(state);
    window.location.href = this.url;
    
    }
    ,
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
    ,
    generateRandomString(length)  {
      let result = '';
      const characters ='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
      const charactersLength = characters.length;
      for (let i = 0; i < length; i++) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
       }
      return result;
    }

  }
  ,
  created(){
   if (window.location.hash){
     this.isHash = true;
    let hash  = window.location.hash;
    let after = hash.substring(1);
      let urlParams = after.split("&");
      let splitParams = urlParams.reduce((accum, val)=> {
      let [key,value] = val.split("=");
      accum[key] = value;
      
      return accum;
      },{});

      let token = splitParams.access_token;
      localStorage.setItem("bearer" ,token);
      
      spotifyService.getSpotifyUser(token).then((spotResponse) => {
        let dat = spotResponse.data;
        localStorage.setItem( "spotifyId", dat.id);
        localStorage.setItem("tempPassword", dat.email + dat.id );
        localStorage.setItem("tempUserName",dat.display_name); 
        
        this.user.username = localStorage.getItem("tempUserName");
        this.user.password = localStorage.getItem("tempPassword");

        let registerUser = {
        "username": localStorage.getItem("tempUserName") ,
        "password": localStorage.getItem("tempPassword"),
        "confirmPassword": localStorage.getItem("tempPassword"),
        "role": "user",
         }
         
          
        authService.register(registerUser).then((responser) =>{
          if (responser.status === 201 ){this.user.username = localStorage.getItem("tempUserName");
        this.user.password = localStorage.getItem("tempPassword"); this.login()}
          }
          ).catch((error) =>{
            if(error.response.status === 400){
         this.login()
         }
          })

       })
      
      

  }
}
};
</script>
<style>
  body{
    display: flex;
  background-image: url("../assets/Background1.jpg");
    background-size: 100vw 100vh;  
  justify-content: center;
    align-self: center;


  }
  </style>
  <style scoped>
  .form-input-group {
    margin-bottom: 1rem;
    padding-right: 3%;
    font-family: Georgia, 'Times New Roman', Times, serif;
   font-style: italic;
    
  }
  label {
  width:75px;
  display: inline-block;
  margin-right: 0.5rem;
  
}
h1{
  display: flex;
  justify-content: center;
  letter-spacing: 3px;
  font-family: 'JosephSophia';
  font-size: 90px;
  color: #d1985e;
  text-shadow: 4px 4px rgb(10, 9, 9) ;
  
}
button{
  display: flex;
  justify-content: center;
  border-radius: 6px;
  line-height: 22px;
  background-color: #fcf7d9;
}
.sgnbutton{
  display: flex;
  justify-content: center;
  align-content: center;
  margin: 3px;
  font-family: Georgia, 'Times New Roman', Times, serif;
   font-style: italic;
  
}             
.userbox{
  background-color: #FEFAE0;
  height: 40px;
  width: 280px;
  border-radius: 12px;
}
input{
  height: 15px;
}
#login{
  display: flex;
  justify-content: center;
  padding-top: 15% ;
  }

</style>