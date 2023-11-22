import { ref } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import http from '@/api/http' // http 인스턴스를 임포트합니다.
import { useAuthStore } from './auth';


export const useTrainerStore = defineStore('trainer', () => {
  // 트레이너 회원 등록
  const authStore = useAuthStore(); // auth 스토어 사용
  const createTrainer = async (trainer) => {
    try {
      await http.post('/api-trainer/signup', trainer);
      alert("회원가입이 완료되었습니다.");
      router.push({ name: 'home' });
    } catch (err) {
      alert("회원가입 실패.");
      console.error(err);
    }
  };
  
  // 트레이너 회원 로그인
  const loginTrainer = async (trainer) => {
    try {
      const response = await http.post('/api-trainer/login', trainer);
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

  // email로 Trainer 객체에 대한 정보를 받아온다.
  const trainer = ref({});
  const getTrainer = function(email){
    http.get(`/api-trainer/find/${email}`)
    .then((response)=>{
      trainer.value = response.value;
    })
  }
  
  return { createTrainer, loginTrainer, trainer, getTrainer };
});
