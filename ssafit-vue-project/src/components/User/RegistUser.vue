<template>
    <div class="regist-box">
        <h2>SSAFIT</h2>
        <div>
            <ul>
                <li><input type="text" placeholder="이메일" v-model="user.email"></li>
                <li><input type="text" placeholder="이름" v-model="user.name"></li>
                <li><input type="password" placeholder="비밀번호" v-model="user.password"></li>
                <li><input type="text" placeholder="닉네임" v-model="user.nickname"></li>
                <li><input type="text" placeholder="생일" v-model="user.birth"></li>                
                <li><input type="text" placeholder="핸드폰번호(선택)" v-model="user.phonenum"></li>
                <li><input type="text" placeholder="주소(선택)" v-model="user.address"></li>
                <p>성별(선택)</p>
                <li>
                    <label for="male">남성</label>
                    <input type="radio" id="male" value="1" v-model="user.sex">
                </li>
                <li>
                    <label for="female">여성</label>
                    <input type="radio" id="female" value="2" v-model="user.sex">
                </li>
                <li><button @click="createUser">등록</button></li>
            </ul>
                
        </div>
    </div>
</template>

<script setup>


import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const store = useUserStore();
const user = ref({
    userId: '',
    email: '',
    password: '',
    name: '',
    nickname: '',
    birth: '',    
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
li{
    list-style: none;
}
</style>