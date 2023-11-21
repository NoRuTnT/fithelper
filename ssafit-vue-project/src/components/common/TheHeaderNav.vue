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
import{useRouter} from 'vue-router';



const loginUsernickname = ref(null);
const router = useRouter()   

// Base64 문자열을 디코딩하는 함수
function base64Decode(str) {
  // Base64 URL에서 '+'와 '/' 문자를 각각 '-'와 '_'로 대체합니다.
  const base64String = str.replace(/-/g, '+').replace(/_/g, '/');
  // Base64로 디코딩
  const decodedData = atob(base64String);
  // 디코드된 문자열을 JSON 객체로 파싱
  return JSON.parse(decodedData);
}


watchEffect(() => {
  console.log('watchEffect 실행됨');
  const token = sessionStorage.getItem('access-token');
  console.log('세션 스토리지에서 가져온 토큰:', token);
  if (token) {
    // JWT는 세 부분으로 나뉘며, 각 부분은 '.'으로 구분됩니다.
    const parts = token.split('.');
    if (parts.length === 3) {
      const header = parts[0];
      const payload = parts[1];

      // 헤더와 페이로드를 디코딩합니다.
      const decodedHeader = base64Decode(header);
      const decodedPayload = base64Decode(payload);

      console.log('헤더:', decodedHeader);
      console.log('페이로드:', decodedPayload);

      // 사용자 정보 추출 (예: 닉네임)
      loginUsernickname.value = decodedPayload.nickname || null;
    }
  } else {
    loginUsernickname.value = null;
  }
});

const logout = () => {  
  sessionStorage.removeItem('access-token'); 
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