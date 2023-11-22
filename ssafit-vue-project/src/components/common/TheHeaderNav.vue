<template>
    <div>
        <header>
            <nav>
                <span>SSAFIT</span>
                <!-- board 관련 link를 추후에 동적 바인딩으로 (:to) 바꿔줘야 됨-->
                <RouterLink to="/">Home</RouterLink> |
                <RouterLink to="/board">자유게시판</RouterLink> |
                <RouterLink to="/youtube">영상게시판</RouterLink> | 
                <RouterLink to="/kakao">지도확인</RouterLink> |
                <span v-if="authStore.role === 'user'">
                  <RouterLink to="/mypage">마이페이지</RouterLink> 
                </span>               
                
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
import { useAuthStore } from '@/stores/auth';
import { ref, watch } from 'vue'; 
import { useRouter } from 'vue-router';

const loginUsernickname = ref(null);
const router = useRouter();
const token = ref(sessionStorage.getItem('access-token')); // 토큰을 반응형 데이터로 관리 
const authStore = useAuthStore();


const b64_to_utf8 = function ( str ) {
  return decodeURIComponent(escape(window.atob( str )));
}
authStore.updateRoleFromToken();



watch(() => authStore.token, (newToken) => {
  console.log('watchEffect 실행');  
  if (newToken) {
    console.log('세션 스토리지에서 가져온 토큰:', newToken);
    // 토큰이 있으면 사용자 닉네임 업데이트
    const parts = newToken.split('.');
    if (parts.length === 3) {
      const payload = parts[1];
      const decodedPayload = b64_to_utf8(payload);
      console.log('페이로드:', decodedPayload);
      loginUsernickname.value = JSON.parse(decodedPayload).nickname || null;
    }
  } else {
    // 토큰이 없으면 사용자 닉네임 비움
    loginUsernickname.value = null;
  }
}, { immediate: true }); // 컴포넌트 마운트 시 즉시 실행

window.addEventListener('storage', () => {
  token.value = sessionStorage.getItem('access-token');
});


  const logout = () => {  
    authStore.clearToken(); 
    token.value = null; // 토큰 반응형 데이터 업데이트
    alert("로그아웃"); 
    router.push({ name: 'home' });
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