import { ref } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import http from '@/api/http' // http 인스턴스를 임포트합니다.

export const useCommentStore = defineStore('comment', () => {
    
    // 특정 글 번호에 해당하는 목록만 찾아서 가지고 오면 됨!
    const commentList = ref([])
    const getCommentList = function (boardId) {
        // console.log('받은 parameter');
        // console.log(boardId);
    http.get(`/api/comment/${boardId}`)
      .then((response) => {
        commentList.value = response.data
        console.log('응답받은 내용은');
        console.log(commentList);
      })
    } 
    // 댓글 작성
    const createComment = function (comment) {
    http.post('/api/comment', comment)
      .then(() => {
        // router.push({ name: 'boardList' })
      })
      .catch((err) => {
        console.log(err)
      })
  }


  // 댓글 삭제
  const deleteComment = function (id) {
    http.delete(`/api/comment/${id}`)
    .then(() => {
    //   router.push({ name: 'boardList' });
    }).catch((error) => {
      console.error('Failed to delete the comment', error);
    });
  }

  return { commentList, getCommentList, createComment, deleteComment }
})