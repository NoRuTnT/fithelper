<template>

    <div class="container">
        <MyPageNav class="sidebar"/>        
        <RouterView class="content"/>
        <div v-if="isMainComponentActive" class="center">
            <p>예약 목록</p>
        </div>
        <div v-if="isMainComponentActive" class="table-container">
        <table class="content">
            <tr>
                <th>인덱스번호</th>
                <th>이름</th>
                <th>상태</th>
                <th>가격</th>
                <th>결제시간</th>
                <th>예약시간</th>
            </tr>
            <tr v-for="(gym, index) in store.gymList" :key="gym.gymId">

                <td>{{ index + 1 }}</td>
                <td>
                    <!-- <RouterLink :to="`/gym/detail/${gym.gymId}`">{{ gym.name }}</RouterLink> -->
                    {{ gym.name }}
                </td>
                <td>{{ gym.status }}</td>
                <td>{{ gym.price }}</td>
                <td>{{ new Date(gym.reserveTime).toLocaleString() }}</td>
                <td>{{ new Date(gym.meetTime).toLocaleString() }}</td>
            </tr>
        </table>

        </div>       
    </div>
</template>

<script setup>
import{ref, computed} from 'vue'
import MyPageNav from '@/components/myPage/myPageNav.vue';
import Chatroom from '@/components/myPage/Chatroom.vue';
import Class from '@/components/myPage/Class.vue';
import Main from '@/components/myPage/Main.vue';
import { onMounted } from "vue";
import { useRoute } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import { useGymStore } from '@/stores/gym';
// 컴포넌트 내부에서
const authStore = useAuthStore();

const store = useGymStore()

onMounted(() => {
    authStore.updateUserIdFromToken();
    store.getGymList(authStore.userId);
    
})

const route = useRoute();
const isMainComponentActive = computed(() => route.name === 'main');

// 마이페이지에서는 가독성을 위해 nav 컴포넌트를 보여주지 않습니다.
const emit = defineEmits(['hideNav']);
emit('hideNav');
</script>

<style scoped>
.center {
  text-align: center; /* 텍스트를 중앙 정렬 */
}
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
.content {
    
    margin-left: 250px;
  flex-grow: 1; /* 남은 공간을 모두 채우도록 설정 */
  /* 메인 컨텐츠에 필요한 추가 스타일 */
}
.content table {
  width: 100%; /* 테이블 너비를 부모 요소에 맞춤 */
  border-collapse: collapse; /* 테두리 간 간격 없앰 */
  text-align: left; /* 텍스트를 왼쪽 정렬 */
  color: #333; /* 텍스트 색상 설정 */
}

/* 테이블 헤더에 대한 스타일 */
.content th {
  background-color: #f0f2f5; /* 헤더 배경색 설정 */
  color: #333; /* 헤더 텍스트 색상 설정 */
  padding: 10px 15px; /* 헤더 내부 여백 설정 */
  font-weight: normal; /* 헤더 글꼴 굵기 */
  border-bottom: 1px solid #eaeaea; /* 헤더 하단에 연한 테두리 설정 */
}

/* 테이블 셀에 대한 스타일 */
.content td {
  padding: 10px 15px; /* 셀 내부 여백 설정 */
  border-bottom: 1px solid #eaeaea; /* 셀 하단에 연한 테두리 설정 */
}

/* 마지막 행의 셀 테두리 제거 */
.content tr:last-child td {
  border-bottom: none;
}
.table-container {
    width: 100%; /* 전체 폭을 사용 */
    display: flex;
    justify-content: center; /* 가운데 정렬 */
}

.table-container table {
    width: auto; /* 테이블의 실제 크기에 맞춤 */
}
a{
  text-decoration: none;
}
</style>