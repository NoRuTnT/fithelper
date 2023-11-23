import { ref } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import http from '@/api/http' // http 인스턴스를 임포트합니다.

export const useBoardStore = defineStore('board', () => {
  const boardList = ref([])

  const getBoardList = function () {
    http.get('/api/board')
      .then((response) => {
        boardList.value = response.data
      })
  }

  const board = ref({})
  const getBoard = function (boardId) {
    http.get(`/api/board/${boardId}`)
      .then((response) => {
        board.value = response.data
      })
  }

  const createBoard = function (board) {
    http.post('/api/board', board)
      .then(() => {
        router.push({ name: 'boardList' })
      })
      .catch((err) => {
        console.log(err)
      })
  }
  // board 번호가 input 파라미터로 들어옴
  const deleteBoard = function (id) {
    http.delete(`/api/board/${id}`)
    .then(() => {
      router.push({ name: 'boardList' });
    }).catch((error) => {
      console.error('Failed to delete the board', error);
    });
  }
  const updateBoard = function () {
    http.put('/api/board', board.value)
      .then(() => {
        router.push({ name: 'boardList' })
      })
  }

  const searchBoardList = function (searchCondition) {
    http.get('/api/board', {
      params: searchCondition
    })
      .then((res) => {
        boardList.value = res.data
      })
  }
  
  const likeCountUp = function(id){
    console.log(id);
    http.put(`/api/board/likeCntUp/${id}`)
      .then(() => {
        router.push({ path: `/board/detail/${id}`})
      })
  }

  return { boardList, getBoardList, board, getBoard, createBoard, updateBoard, searchBoardList, deleteBoard, likeCountUp }
})