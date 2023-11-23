<template>
<<<<<<< HEAD
    <div class="container">
        <MyPageNav class="nav"/>        
        <RouterView class="content"/>
        <p>내가 예약한 목록을 아래에 보여줌(주의! 발표 직전에는 지워야 되는 구문들)</p>
        
        <table>
            <tr>
                <th>인덱스번호</th>
                <th>name</th>
                <th>category</th>
                <th>status</th>
                <th>price</th>
                <th>description</th>
                <th>reserveTime</th>
                <th>meetTime</th>
            </tr>
            <tr v-for="(gym, index) in store.gymList" :key="gym.gymId">

                <td>{{ index + 1 }}</td>
                <td>
                    <RouterLink :to="`/gym/detail/${gym.gymId}`">{{ gym.name }}</RouterLink>
                </td>
                <td>{{ gym.category }}</td>
                <td>{{ gym.status }}</td>
                <td>{{ gym.price }}</td>
                <td>{{ gym.description }}</td>
                <td>{{ gym.reserveTime }}</td>
                <td>{{ gym.meetTime }}</td>
            </tr>
        </table>

=======
    <div class="layout-container">
        <MyPageNav class="sidebar"/>        
        <RouterView class="main-content"/>
>>>>>>> 4f4b88282caf4511a777d13908771d18c579a322
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