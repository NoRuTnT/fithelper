// 로그인, 로그아웃, 회원관련 API를 연결

import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_USER_API = `http://localhost:8080/api-user`

export const useUserStore = defineStore('user', () => {

  //회원 등록
  const createUser = function (user) {
    axios({
      url: REST_USER_API+'/signup',
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      data: user
    })
      .then(() => {
        alert("회원가입이 완료되었습니다.")
        router.push({name: 'home'})
      })
      .catch((err) => {
      alert("회원가입 실패.")
      console.log(err)
    })
  }
  // 회원 로그인
  const loginUser = function(user){
    axios({
      url: REST_USER_API+'/login',
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      data: user
    }).then((response) => {
      if(response.data){
        alert("로그인 성공!");
        sessionStorage.setItem('loginUser', JSON.stringify(response.data));
        router.push({ name: 'home' });        
      }else{
        alert("로그인 실패")
        console.log("로그인 실패");

      }
    })
    .catch((err)=>{
      console.log(err);
    })
  }
  
  return {  createUser, loginUser}
})