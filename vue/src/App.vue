<template>
  <div id="app">
    <div id="nav">   
      <router-link v-bind:to="{ name: 'home' }"><i   class="fa-solid fa-house fa-2xl"   ></i></router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>

</template>


   <style>
   @font-face {
    font-family: 'JosephSophia';
    src: url('./assets/font/josephsophia.otf') format('opentype');
    font-weight: normal;
    font-style: normal;
}
 .fa-house{
   color: #FEFAE0;
 }
  
   </style>