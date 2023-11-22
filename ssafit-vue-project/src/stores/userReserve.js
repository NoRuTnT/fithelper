import { ref } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import http from '@/api/http' // http 인스턴스를 임포트합니다.

export const useUserReserve = defineStore('userReserve', () => {
    
    // 특정 글 번호에 해당하는 목록만 찾아서 가지고 오면 됨!
    // const commentList = ref([])
    // const getCommentList = function (boardId) {
    // http.get(`/api/comment/${boardId}`)
    //   .then((response) => {
    //     commentList.value = response.data
    //     console.log('응답받은 내용은');
    //     console.log(commentList);
    //   })
    // } 
    // 댓글 작성
    const createUserReserve = function (userReserve) {
    http.post('/api/user-reserve', userReserve)
      .then(() => {
        router.push({ name: 'kakao' })
      })
      .catch((err) => {
        console.log(err)
      })
  }


  return { createUserReserve }
})