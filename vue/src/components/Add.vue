<template>
    <div>
        <div class="title">
            <h1>Select users</h1>
        </div>
        
        <div class="list">
        <ul>
            <li v-for="user in otherUsers" v-bind:key="user.id">
                {{user.username}}
                <button @click="$emit('add',user)"><i class="fa-solid fa-user-plus fa-2xl"></i></button>
                
                
            </li>
        </ul>
        </div>
        
    </div>
</template>

<script>
import DatabaseService from '../services/DatabaseService'

export default {
    name: "Add",
    data(){
        return{
            otherUsers:[],
        }
    }
    ,
    methods: {
       
    },
    created(){
        DatabaseService.getOtherUsers(this.$store.state.user.id).then((response)=>{
            this.otherUsers = response.data
        })
    }
};
</script>

<style scoped>
.title{
    display: flex;
    justify-content: center;
}
.list{
    display: flex;
    justify-content: left;
    font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: italic;
  font-size: 20px;
}

</style>
