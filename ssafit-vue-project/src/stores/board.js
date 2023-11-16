import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'
// Back API URL로 고쳐주기
const REST_BOARD_API = `http://localhost:8080/api/board`

export const useBoardStore = defineStore('board', () => {
  const boardList = ref([])
  const getBoardList = function () {
    axios.get(REST_BOARD_API,{
      headers: {
        'access-token' : sessionStorage.getItem('access-token')
      }
    })
      .then((response) => {
      boardList.value = response.data
      })
  }

  //게시글 하나
  const board = ref({})
  const getBoard = function (boardId) {
    axios.get(`${REST_BOARD_API}/${boardId}`)
      .then((response) => {
      board.value = response.data
    })
  }

  //게시글 등록
  const createBoard = function (board) {
    axios({
      url: REST_BOARD_API,
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      data: board
    })
      .then(() => {
        router.push({ name: 'boardList'})
      })
      .catch((err) => {
      console.log(err)
    })
  }

  const updateBoard = function () {
    axios.put(REST_BOARD_API, board.value)
      .then(() => {
      router.push({name: 'boardList'})
    })
  }

  const searchBoardList = function (searchCondition) {
    axios.get(REST_BOARD_API, {
      params: searchCondition
    })
      .then((res) => {
        boardList.value = res.data
    })
  }
  return { boardList, getBoardList, board, getBoard, createBoard, updateBoard,searchBoardList }
})