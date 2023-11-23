<template>
    <div class="layout-container">
        <MyPageNav class="sidebar"/>        
        <RouterView class="main-content"/>
    </div>
</template>

<script setup>
import{ref} from 'vue'
import MyPageNav from '@/components/myPage/myPageNav.vue';
import Chatroom from '@/components/myPage/Chatroom.vue';
import Class from '@/components/myPage/Class.vue';
import Main from '@/components/myPage/Main.vue';

// 마이페이지에서는 가독성을 위해 nav 컴포넌트를 보여주지 않습니다.
const emit = defineEmits(['hideNav']);
emit('hideNav');
// <마이페이지 구성>
// 메인|클래스|1대1채팅|미구현
// 사용자 티어 표시하기 (이미지로 직관적이게)
// 내가 등록한 클래스 | 자주 갔던 헬스장
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