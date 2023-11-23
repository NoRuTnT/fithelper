<template>
    <div>
        <UserLogin v-if="selectUserType==1"/>
        <TrainerLogin v-if="selectUserType==2"/>
        <GymOwnerLogin v-if="selectUserType==3"/>
        <div class="buttons-container">
            <button @click="normal">일반회원</button>
            <button @click="trainer">trainer회원</button>
            <button @click="gym">gym회원</button>
        </div>
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
.buttons-container {
  display: flex;
  justify-content: center; /* 가운데 정렬 */
  margin-top: 1rem; /* 상단 여백 추가 */
}

button {
  padding: 10px 20px;
  margin-right: 10px;
  background-color: #ffffff;
  color: #333333;
  text-transform: uppercase;
  font-weight: bold;
  border: 2px solid #cccccc;
  border-radius: 5px;
  cursor: pointer;
  outline: none;
  transition: all 0.3s ease;
}

button:last-child {
  margin-right: 0;
}

button:hover {
  background-color: #f2f2f2;
  border-color: #999999;
}
</style>