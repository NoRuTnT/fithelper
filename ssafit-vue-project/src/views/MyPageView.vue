<template>

    <div class="container">
        <MyPageNav class="sidebar"/>        
        <RouterView class="content"/>
        <p>예약 목록</p>
        <table>
            <tr>
                <th>인덱스번호</th>
                <th>name</th>
                <th>status</th>
                <th>price</th>
                <th>description</th>
                <th>reserveTime</th>
                <th>meetTime</th>
                <th>delete</th>
            </tr>
            <tr v-for="(gym, index) in store.gymList" :key="gym.gymId">

                <td>{{ index + 1 }}</td>
                <td>
                    <!-- <RouterLink :to="`/gym/detail/${gym.gymId}`">{{ gym.name }}</RouterLink> -->
                    {{ gym.name }}
                </td>
                <td>{{ gym.status }}</td>
                <td>{{ gym.price }}</td>
                <td>{{ gym.description }}</td>
                <td>{{ gym.reserveTime }}</td>
                <td>{{ convert(gym.meetTime) }}</td>
                <td><button @click="deleteGymReserve(gym.gymId)">지우기</button></td>
            </tr>
        </table>


    </div>
</template>

<script setup>
import{ref} from 'vue'
import MyPageNav from '@/components/myPage/myPageNav.vue';
import Chatroom from '@/components/myPage/Chatroom.vue';
import Class from '@/components/myPage/Class.vue';
import Main from '@/components/myPage/Main.vue';
import { onMounted } from "vue";

import { useAuthStore } from '@/stores/auth';
import { useGymStore } from '@/stores/gym';
// 컴포넌트 내부에서
const authStore = useAuthStore();

const store = useGymStore()


onMounted(() => {
    authStore.updateUserIdFromToken();
    store.getGymList(authStore.userId);
    
})

// 타임스탬프를 날짜 형태로 변환해주는
function convert(timeStamp){
  let arg = timeStamp;
  return new Date(arg);
}

import { useRouter } from 'vue-router';
const router = useRouter();
// 예약 없애기
function deleteGymReserve(gymId){
  let q = confirm('예약을 취소하시겠습니까?');
  if(q){
    store.deleteGym(gymId);
    router.go();
  }
}


// 마이페이지에서는 가독성을 위해 nav 컴포넌트를 보여주지 않습니다.
const emit = defineEmits(['hideNav']);
emit('hideNav');
</script>

<style scoped>
.layout-container {
  display: flex;
  height: 100vh;
}
.sidebar {
    height: 100vh; /* 전체 뷰포트 높이를 사용 */
    width: 250px; /* 사이드바의 너비를 고정 */
    background-color: #f0f2f5; /* 사이드바의 배경색 */
    position: fixed; /* 사이드바를 화면에 고정 */
    top: 0;
    left: 0;
    overflow-y: auto; /* 내용이 넘칠 경우 스크롤 가능 */
}
.main-content {
    
    margin-left: 250px;
  flex-grow: 1; /* 남은 공간을 모두 채우도록 설정 */
  /* 메인 컨텐츠에 필요한 추가 스타일 */
}
</style>