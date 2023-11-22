<template>
    <div>
        <ManageNav/>        
        <RouterView/>
    </div>
</template>

<script setup>
import{ref} from 'vue'
import ManageNav from '@/components/Manage/ManageNav.vue';
import Chatroom from '@/components/Manage/Chatroom.vue';
import Class from '@/components/myPage/Class.vue';
import Main from '@/components/Manage/Main.vue';

// 마이페이지에서는 가독성을 위해 nav 컴포넌트를 보여주지 않습니다.
const emit = defineEmits(['hideNav']);
emit('hideNav');
// <마이페이지 구성>
// 메인|클래스|1대1채팅|미구현
// 내가 등록한 클래스 | 자주 갔던 헬스장
</script>

<style scoped>

</style>