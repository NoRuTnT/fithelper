<template>
    <div>
        <h3>버튼을 누르면 회원 계정이 탈퇴됩니다.</h3>
        <button class="like" @click="deleteUser">회원탈퇴</button>
    </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useAuthStore } from '@/stores/auth';

const store = useUserStore();
const authStore = useAuthStore();

onMounted(() => {
    authStore.updateUserIdFromToken();
})


const deleteUser = function(){
    alert('회원탈퇴완료. 서비스를 이용해주셔서 감사합니다. 메인페이지에서 로그아웃하면 탈퇴절차가 마무리됩니다.');
    store.deleteUser(authStore.userId);
}
</script>

<style scoped>
button.like {
    background-color: white;
    border: 2px solid red;
    color: red;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
    display: inline-flex;
    align-items: center;
}
button.like:hover {
    background-color: #ffcccc;
}
</style>