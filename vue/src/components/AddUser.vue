<template>
<div>
    <select name="selectedUsers" id="selectedUsers" v-model="selectedUser">
         <option v-for="user in localStorage.getItem('others')" v-bind:key="user.id" v-bind:value="user.id">
    {{user.username}}
    </option>
    </select>
   
            
    
</div>

  
</template>

<script>

import DatabaseService from '../services/DatabaseService'

export default {
    name:"AddUser",
    data(){
        return{
            
            selectedUser:{},
        }
    },
    methods:{
        OtherUsers(){
            let tempUsers;
            DatabaseService.getOtherUsers(this.$store.state.user.id).then((response)=>{
                this.tempUsers = response.data;
                let users = {};
            for (let key in tempUsers) {
                let split = tempUsers[key].split(" ");
                let username = ""
                let id = split[split.length-1];
                for (let i = 0; i < split.length -1; i++) {
                    username += split[i]
                }
                users.add({"username":username, "id":id})
                
            }
            localStorage.setItem("others", users);
            });
            return localStorage.getItem("others")
        }
    },
    created(){
        this.users = this.OtherUsers
    }


}
</script>

<style>

</style>