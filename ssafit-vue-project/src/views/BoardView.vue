<template>
    <div>
        <h3>SSAFIT 자유게시판</h3>
        <RouterView/>
        
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import router from '@/router';
const writeBoard = function(){
    router.push({name: 'boardCreate'})
}
</script>

<style scoped>
.buttons{
    display: flex;
    justify-content: center;
}
button{
    margin: 10px;
}
h3{
    color: mediumorchid;
    font-size: 30px;
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}
</style>