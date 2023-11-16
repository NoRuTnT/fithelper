<template>
    <div>
        <h3>여기는 게시판</h3>
        <RouterView/>
        <div class="buttons">
            <button @click="goHome">뒤로</button>
            <button @click="writeBoard">글 등록</button>
        </div>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import router from '@/router';
const goHome = function(){
    router.push({name: 'home'})
}
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
</style>