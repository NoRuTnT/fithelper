<template>
    <div>
        <MyPageNav/>
        마이페이지 화면을 보여줍니다.
    </div>
</template>

<script setup>
import{ref} from 'vue'
import MyPageNav from '@/components/myPage/myPageNav.vue';

// 마이페이지에서는 가독성을 위해 nav 컴포넌트를 보여주지 않습니다.
const emit = defineEmits(['hideNav']);
emit('hideNav');
// <마이페이지 구성>
// 메인|클래스|1대1채팅|미구현
// 사용자 티어 표시하기 (이미지로 직관적이게)
// 내가 등록한 클래스 | 자주 갔던 헬스장
</script>

<style scoped>

</style>