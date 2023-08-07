<template>
  <div id="login">
    <form @submit.prevent="login">
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
      <button class="sgnbutton" type="submit">Sign in</button>
      <p class="sgnbutton">
      <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
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
  }
};
</script>
<style>
body{
  
background-image: url("../assets/Background1.jpg");
  background-size: 100vw 100vh;  


}
</style>
<style scoped>
.form-input-group {
  margin-bottom: 1rem;
  
}
label {
  width:75px;
  display: inline-block;
  margin-right: 0.5rem;
  
}
h1{
  display: flex;
  justify-content: center;
  
}
button{
  display: flex;
  justify-content: center;
}
.sgnbutton{
  display: flex;
  justify-content: center;
  
}
.userbox{
  
  height: 40px;
  width: 250px;
  border-radius: 12px;
}
input{
  height: 15px;
}
#login{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  padding-top: 22%;
  
  }

</style>