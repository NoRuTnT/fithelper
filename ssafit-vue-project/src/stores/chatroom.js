import { ref } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import http from '@/api/http' // http 인스턴스를 임포트합니다.

export const useChatroomStore = defineStore('chatroom', () => {
    
    // 채팅글 작성
    const createChatroom = function (chatroom) {
        http.post('/api/chatroom', chatroom)
          .then(() => {
            // router.push({ name: 'boardList' })
        })
          .catch((err) => {
            console.log(err)
        })
    }

    // 채팅글 삭제
    const commentList = ref([])
    const getCommentList = function (boardId) {
    http.get(`/api/comment/${boardId}`)
      .then((response) => {
        commentList.value = response.data
        console.log('응답받은 내용은');
        console.log(commentList);
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

  return { commentList, getCommentList, createChatroom, deleteComment }
})

