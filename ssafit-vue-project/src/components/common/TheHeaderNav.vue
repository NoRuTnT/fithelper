<template>
    <div>
        <header>
            <nav>
                <span>SSAFIT</span>
                <!-- board 관련 link를 추후에 동적 바인딩으로 (:to) 바꿔줘야 됨-->
                <RouterLink to="/">Home</RouterLink> |
                <RouterLink to="/board">자유게시판</RouterLink> |
                <RouterLink to="/youtube">영상게시판</RouterLink> | 
                <RouterLink to="/kakao">지도확인</RouterLink>
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

import {  ref,watchEffect } from 'vue';
import { decodeJWT } from '../../util/jwtDecoder'; 
import{useRouter} from 'vue-router'


const loginUsernickname = ref(null);

const router = useRouter()   

watchEffect(() => {
  console.log('watchEffect 실행됨');
  const token = sessionStorage.getItem('access-token');
  console.log('세션 스토리지에서 가져온 토큰:', token);
  if (token) {
    const decoded = decodeJWT(token);
    console.log('디코드된 값:', decoded);
    loginUsernickname.value = decoded ? decoded.nickname : null;
  } else {
    loginUsernickname.value = null;
  }
});

const logout = () => {  
  sessionStorage.removeItem('loginUser'); 
  alert("로그아웃"); 
  router.push({name:'home'});
};
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