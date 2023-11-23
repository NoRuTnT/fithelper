import { ref } from 'vue'
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

  // 캐시충전
  const charge = async (charge) => {
    try {
      const response = await http.put('/api-user/charge', charge);
      if (response.data) {
        alert("충전완료!");
        authStore.setToken(response.data["access-token"]); // 토큰을 스토어에 저장
        router.push({ name: 'myPage' });
        window.location.reload();
      } else {
        alert("충전 실패");
        console.error("충전 실패");
      }
    } catch (err) {
      console.error(err);
    }
  };

  // 캐시소비
  const use = async (useAmount) => {
    try {
      const response = await http.put('/api-user/use', useAmount);
      if (response.data) {
        alert("예약완료!");
        authStore.setToken(response.data["access-token"]); // 토큰을 스토어에 저장
        router.push({ name: 'home' });
      } else {
        alert("예약 실패");
        console.error("예약 실패");
      }
    } catch (err) {
      console.error(err);
    }
  };

  // email로 User 객체에 대한 정보를 받아온다.
  const user = ref({});
  const getUser = function(email){
    http.get(`/api-user/find/${email}`)
    .then((response)=>{
      user.value = response.value;
    })
  }
  
  return { createUser, loginUser, user, getUser,charge,use};
});
