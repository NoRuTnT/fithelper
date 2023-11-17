<template>
    <div class="regist-box">
        <h2>SSAFIT</h2>
        <div>
                <input type="text" placeholder="아이디" v-model="user.id">
                <input type="text" placeholder="이름" v-model="user.name">
                <input type="password" placeholder="비밀번호" v-model="user.password">
                <input type="text" placeholder="닉네임" v-model="user.nickname">
                <input type="text" placeholder="생일" v-model="user.birth">
                <input type="text" placeholder="이메일(선택)" v-model="user.email">
                <input type="text" placeholder="핸드폰번호(선택)" v-model="user.phonenum">
                <input type="text" placeholder="주소(선택)" v-model="user.address">
                <p>성별(선택)</p>
                <label for="male">남성</label>
                <input type="radio" id="male" value="1" v-model="user.sex">
                <label for="female">여성</label>
                <input type="radio" id="female" value="2" v-model="user.sex">
                <button @click="createUser">등록</button>
        </div>
    </div>
</template>

<script setup>


import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const store = useUserStore();
const user = ref({
    userId: '',
    id: '',
    password: '',
    name: '',
    nickname: '',
    birth: '',
    email: '',
    phonenum: '',
    address: '',
    cash: '',
    sex: '',
    // sex: gender.value,
})

const createUser = function () {
    console.log(user);
    store.createUser(user.value)
}
</script>

<style scoped>
</style>