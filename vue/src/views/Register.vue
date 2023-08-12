<template>
  <div id="register" class="text-center">
    <form @submit.prevent="register">
      <h1>Creat{{e}}accoun{{t}}</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input class="userbox" type="text" placeholder="username" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input  class="userbox" type="password" placeholder="password"  id="password" v-model="user.password" required />

      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input  class="userbox" type="password" placeholder="confirm password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <div class="sgnbutton">
      <button type="submit">Create Account</button>
      </div>
      <p class="sgnbutton"><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
      
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      e: '\ue00e',
      t: '\ue01d',
      user: {
        username: 'username',
        password: 'password',
        confirmPassword: 'confirm password',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
  body{
    display: flex;
  background-image: url("../assets/Background1.jpg");
    background-size: 100vw 100vh;  
  justify-content: center;
    align-self: center;
    


  }
  .form-input-group {
    margin-bottom: 1rem;
    padding-right: 3%;
    display: flex;
   justify-content: center;
   font-family: Georgia, 'Times New Roman', Times, serif;
   font-style: italic;
   color: #FEFAE0;
   text-shadow: black 2px 2px;
   
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
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: italic;
}
.userbox{
  background-color: #FEFAE0;
  height: 40px;
  width: 280px;
  border-radius: 12px;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: italic;  
}

.sgnbutton{
  display: flex;
  justify-content: center;
  align-content: center;
  margin: 3px;
  font-family: Georgia, 'Times New Roman', Times, serif;
   font-style: italic;
  
  
}             
 


</style>

