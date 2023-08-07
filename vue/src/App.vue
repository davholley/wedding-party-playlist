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
 .fa-house{
   color: #FEFAE0;
 }
  @import url('https://www.dafont.com/josephsophia.font');

   </style>