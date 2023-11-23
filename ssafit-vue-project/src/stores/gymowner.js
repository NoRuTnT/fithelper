import { ref } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import http from '@/api/http' // http 인스턴스를 임포트합니다.
import { useAuthStore } from './auth';


export const useGymownerStore = defineStore('gymowner', () => {
  // Gym 회원 등록
  const authStore = useAuthStore(); // auth 스토어 사용
  const createGymowner = async (gymowner) => {
    try {
      await http.post('/api-gymowner/signup', gymowner);
      alert("회원가입이 완료되었습니다.");
      router.push({ name: 'home' });
    } catch (err) {
      alert("회원가입 실패.");
      console.error(err);
    }
  };
  
  // Gym 회원 로그인
  const loginGymowner = async (gymowner) => {
    try {
      const response = await http.post('/api-gymowner/login', gymowner);
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

  // email로 Gymowner 객체에 대한 정보를 받아온다.
  const gymonwer = ref({});
  const getGymowner = function(email){
    http.get(`/api-gymowner/find/${email}`)
    .then((response)=>{
      gymowner.value = response.value;
    })
  }
  
  return { createGymowner, loginGymowner, gymowner, getGymowner };
});
