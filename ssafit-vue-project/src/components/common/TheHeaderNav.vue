<template>
    <div>
        <header>
            <nav>
                <img src="@/assets/logo.png" width="200" height="100">
                <!-- board 관련 link를 추후에 동적 바인딩으로 (:to) 바꿔줘야 됨-->
                <RouterLink to="/">Home</RouterLink> |
                <RouterLink to="/board">자유게시판</RouterLink> |
                <RouterLink to="/youtube">영상게시판</RouterLink> | 
                <RouterLink to="/kakao">지도확인</RouterLink> |
                <span v-if="role === 'user'">
                  <RouterLink to="/mypage">마이페이지</RouterLink> 
                </span>     
                
                <span v-if="!loginUsernickname">
                    <RouterLink to="/login">SSAFIT 로그인</RouterLink> 
                </span>
                <span v-else>                    
                    <span>{{ loginUsernickname }}님 환영합니다. </span>
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
const role = ref(null);
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
      role.value = JSON.parse(decodedPayload).role[0] || null;
    }
  } else {
    // 토큰이 없으면 사용자 닉네임 비움
    loginUsernickname.value = null;
    role.value = null;
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

nav{
  font-family: Arial, sans-serif;
  font-weight: bold; /* 모든 텍스트를 굵게 만듦 */
  display: flex; /* Flexbox 레이아웃을 적용 */
  justify-content: space-between; /* 항목들 사이에 균등한 간격을 줌 */
  flex-wrap: nowrap; /* 항목들이 한 줄에 나오도록 함 */
  align-items: center; /* 항목들을 세로 중앙에 정렬 */
  padding: 0.5rem 1rem; /* 상단 패딩을 줄이고 좌우 패딩을 조정 */
}
nav img {
  margin-right: 1rem; /* 로고와 메뉴 사이의 마진 조정 */
  margin-top: -0.5rem; /* 로고의 상단 마진을 줄여서 위로 올림 */
}
nav a{
  margin: 0 15px; /* 각 링크에 좌우 마진을 줌 */
  white-space: nowrap; /* 링크가 줄바꿈되지 않도록 함 */
  text-decoration: none; /* 링크 밑줄 제거 */
  color: var(--color-black); /* 링크 색상 설정 */
  font-size: 1rem; /* 링크 폰트 사이즈 설정 */
  
}
nav a.router-link-exact-active{
    color: rgb(147, 147, 147);
} 
/* 폰트, 컬러, 기타 변수 */
:root {
  --color-black: #000;
  --color-white: #fff;
  --color-grey: #f4f4f4;
}

/* 전체 페이지 스타일링 */
nav {
  color: var(--color-black);
  background-color: var(--color-grey);
  line-height: 1.6;
}

/* 네비게이션 스타일링 */
nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 5%;
  background: var(--color-white);
}
</style>