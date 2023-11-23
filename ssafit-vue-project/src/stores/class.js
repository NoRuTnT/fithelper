import { ref } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import http from '@/api/http' // http 인스턴스를 임포트합니다.

export const useClassStore = defineStore('class', () => {
  const classList = ref([])

  const usergetClassList = function (userId) {
    http.get(`/api/class/userSelect/${userId}`)
      .then((response) => {
        classList.value = response.data
      })
  }

  const trainergetClassList = function (trainerId) {
    http.get(`/api/class/trainerSelect/${trainerId}`)
      .then((response) => {
        classList.value = response.data
      })
  }

  const Class = ref({})
  const getClass = function (classId) {
    http.get(`/api/class/${classId}`)
      .then((response) => {
        board.value = response.data
      })
  }

  const createClass = function (Class) {
    http.post('/api/class', Class)
      .then(() => {
        router.push({ name: 'trainerclassList' })
      })
      .catch((err) => {
        console.log(err)
      })
  }
  const deleteClass = function (classId) {
    http.delete(`/api/class/${classId}`)
    .then(() => {
      router.push({ name: 'trainerclassList' });
    }).catch((error) => {
      console.error('Failed to delete the board', error);
    });
  }
  const updateClass = function () {
    http.put('/api/class', Class.value)
      .then(() => {
        router.push({ name: 'trainerclassList' })
      })
  }

  

  return { usergetClassList,trainergetClassList, Class, getClass, createClass, updateClass, deleteClass,classList }
})