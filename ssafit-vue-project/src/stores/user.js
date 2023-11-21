import { defineStore } from 'pinia'
import router from '@/router'
import http from '@/api/http' // http 인스턴스를 임포트합니다.

export const useUserStore = defineStore('user', () => {
  //회원 등록
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
        sessionStorage.setItem('access-token', response.data["access-token"]);
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
