<template>
    <div>
        <UserLogin v-if="selectUserType==1"/>
        <TrainerLogin v-if="selectUserType==2"/>
        <GymOwnerLogin v-if="selectUserType==3"/>
        <button @click="normal">일반회원</button>
        <button @click="trainer">trainer회원</button>
        <button @click="gym">gym회원</button>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

import UserLogin from "@/components/login/UserLogin.vue";
import TrainerLogin from "@/components/login/TrainerLogin.vue";
import GymOwnerLogin from "@/components/login/GymOwnerLogin.vue";
// 회원 종류에 따른 로그인 화면 렌더링
// 1: 일반 | 2: trainer | 3: gym
const selectUserType = ref(1); // default = 1
function normal(){
    selectUserType.value = 1;
}
function trainer(){
    selectUserType.value = 2;
}
function gym(){
    selectUserType.value = 3;
}
</script>

<style scoped>
*{
    margin: 0;
    padding: 0;
    border: none;
}

</style>