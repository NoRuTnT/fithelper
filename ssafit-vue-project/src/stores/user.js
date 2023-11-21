import { defineStore } from 'pinia'
import router from '@/router'
import http from '@/api/http' // http 인스턴스를 임포트합니다.
import { useAuthStore } from './auth';


export const useUserStore = defineStore('user', () => {
  //회원 등록
  const authStore = useAuthStore(); // auth 스토어 사용
  const createUser = async (user) => {
    try {
      await http.post('/api-user/signup', user);
      alert("회원가입이 완료되었습니다.");
      router.push({ name: 'home' });
    } catch (err) {
      alert("회원가입 실패.");
      console.error(err);
    }
  };
  
  // 회원 로그인
  const loginUser = async (user) => {
    try {
      const response = await http.post('/api-user/login', user);
      if (response.data) {
        alert("로그인 성공!");
        authStore.setToken(response.data["access-token"]); // 토큰을 스토어에 저장
        router.push({ name: 'home' });
      } else {
        alert("로그인 실패");
        console.error("로그인 실패");
      }
    } catch (err) {
      console.error(err);
    }
  };
  
  return { createUser, loginUser };
});
