<template>
    <div>
        <div class="login">
            <div class="login-box">
                <h2>SSAFIT (Trainer 회원)</h2>
                <input type="text" placeholder="아이디" v-model="trainer.email"><br>
                <input type="password" placeholder="비밀번호" v-model="trainer.password">
                <button @click="loginTrainer">로그인</button>
                <div>
                    <a href="#">비밀번호 찾기</a> |
                    <a href="#">아이디 찾기</a> |
                    <RouterLink to="/registTrainer">회원가입</RouterLink>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useTrainerStore } from "@/stores/trainer";

const store = useTrainerStore();

// 로그인하는 사용자 타입을 결정하는 변수(user/trainer/gymowner)
const trainerType = ref(0);

const trainer = ref({
    email: '',
    password: ''

})

const loginTrainer = function () {
    console.log(trainer);
    store.loginTrainer(trainer.value)
}
</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
    border: none;
}

.login {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

body {
    font-size: 14px;
    font-family: sans-serif;
}

.login-box {
    width: 400px;
    height: 400px;
    padding: 40px;
    box-sizing: border-box;
    border: 3px solid pink;
    border-radius: 20px;

}

.login-box>h2 {
    color: pink;
    font-size: 30px;
    margin-top: 20px;
    margin-bottom: 20px;
}

#login-form>input {
    width: 100%;
    height: 30px;
    border: 1px solid gainsboro;
    border-radius: 3px;
}

#login-form>button {
    background-color: pink;
    color: white;

}

a {
    text-decoration: none;
    color: plum;
    font-family: sans-serif;
}</style>