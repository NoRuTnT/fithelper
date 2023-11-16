<template>
    <div class="login">
        <div class="login-box">
            <h2>SSAFIT</h2>
                <input type="text" placeholder="이메일" v-model.trim="user.email"><br>
                <input type="password" placeholder="비밀번호" v-model.trim="user.password">
                <button @click="loginUser">로그인</button>
        <div>
        <a href="#">비밀번호 찾기</a> | 
        <a href="#">아이디 찾기</a> | 
        <RouterLink to="/registUser">회원가입</RouterLink>
        </div>
        </div>
    </div>
</template>

<script setup>

import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const store = useUserStore();
const user = ref({    
    email: '',
    password: ''
    
})

const loginUser = function () {
    console.log(user);
    store.loginUser(user.value)
}






</script>

<style scoped>
*{
    margin: 0;
    padding: 0;
    border: none;
}
.login{
    display: flex;
    justify-content: center;
    margin-top: 20px;
}
body{
    font-size: 14px;
    font-family: sans-serif;
}
.login-box{
    width: 400px;
    height: 400px;
    padding: 40px;
    box-sizing: border-box;
    border: 3px solid pink;
    border-radius: 20px;
    
}
.login-box > h2{
    color: pink;
    font-size: 30px;
    margin-top: 20px;
    margin-bottom: 20px;
}
#login-form > input{
    width: 100%;
    height: 30px;
    border: 1px solid gainsboro;
    border-radius: 3px;
}
#login-form > button{
    background-color: pink;
    color: white;
    
}
a{
    text-decoration: none;
    color: plum;
    font-family: sans-serif;
}
</style>