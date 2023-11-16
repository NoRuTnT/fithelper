<template>
    <div>
        <header>
            <nav>
                <span>SSAFIT</span>
                <!-- board 관련 link를 추후에 동적 바인딩으로 (:to) 바꿔줘야 됨-->
                <RouterLink to="/">Home</RouterLink> |
                <RouterLink to="/kakao">헬스장찾기</RouterLink> |
                <RouterLink to="/board">자유게시판</RouterLink> |
                <RouterLink to="/youtube">영상게시판</RouterLink> | 
                <!-- <RouterLink to="{name: 'boardCreate'}">BoardCreate</RouterLink> -->
                <span v-if="!loginUsernickname">
                    <RouterLink to="/login">SSAFIT 로그인</RouterLink> 
                </span>
                <span v-else>                    
                    <span >{{ loginUsernickname }}님 환영합니다. </span>
                    <a @click="logout" href="#">로그아웃</a>
                </span>                
            </nav>
        </header>
    </div>
</template>

<script setup>

import {  ref,watch } from 'vue';
import{useRouter} from 'vue-router'
const router = useRouter()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
const logout = () => {  
  sessionStorage.removeItem('access-token'); 
  alert("로그아웃"); 
  router.push({name:'home'});
};
const loginUsernickname = ref(JSON.parse(sessionStorage.getItem('access-token')));

watch(
  () => sessionStorage.getItem('access-token'),
  (newValue) => {
    console.log('New value in watch:', newValue);
    loginUsernickname.value = JSON.parse(newValue);
  }
);
</script>

<style scoped>
span{
    margin: 20px;
    color: #F4EBE7;
    font-size: 30px;
}
nav{
    padding: 30px;
    background-color: pink;
}
nav a{
    font-weight: bold;
    text-decoration: none;
    color: white;
}
nav a.router-link-exact-active{
    color: magenta;
}
</style>