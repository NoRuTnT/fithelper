import { ref } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import http from '@/api/http' // http 인스턴스를 임포트합니다.

export const useGymStore = defineStore('gym', () => {
  const gymList = ref([])

  // 목록조회
  const getGymList = function (userId) {
    http.get(`/api/gym/list/${userId}`)
      .then((response) => {
        gymList.value = response.data
      })
  }

  // 하나만 뽑아서 조회
  const gym = ref({})
  const getGym = function (gymId) {
    http.get(`/api/gym/${gymId}`)
      .then((response) => {
        gym.value = response.data
      })
  }
  // 예약한 gym 등록
  const createGym = function (gym) {
    console.log(gym);
    http.post('/api/gym', gym)
      .then(() => {
        router.push({ name: 'kakao' })
      })
      .catch((err) => {
        console.log(err)
      })
  }
  // 삭제  
  const deleteGym = function (id) {
    http.delete(`/api/gym/${id}`)
    .then(() => {
      // router.push({ name: 'gymList' });
    }).catch((error) => {
      console.error('Failed to delete the gym', error);
    });
  }
  // 수정
  const updateGym = function () {
    http.put('/api/gym', gym.value)
      .then(() => {
        // router.push({ name: 'gymList' })
      })
  }

  

  return { gymList, getGymList, gym, getGym, createGym, updateGym, deleteGym }
})