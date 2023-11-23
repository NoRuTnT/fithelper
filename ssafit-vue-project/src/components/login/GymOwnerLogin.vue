<template>
    <div>
        <div class="login">
            <div class="login-box">
                <h2>SSAFIT (Gym 회원)</h2>
                <input type="text" placeholder="이메일" v-model="user.email"><br>
                <input type="password" placeholder="비밀번호" v-model="user.password">
                <button @click="loginUser">로그인</button>
                <div>
                    <a href="#">비밀번호 찾기</a> |
                    <a href="#">아이디 찾기</a> |
                    <RouterLink to="/registUser">회원가입</RouterLink>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const store = useUserStore();

// 로그인하는 사용자 타입을 결정하는 변수(user/trainer/gymowner)
const userType = ref(0);

const user = ref({
    id: '',
    password: ''

})

const loginUser = function () {
    console.log(user);
    store.loginUser(user.value)
}
</script>

<style scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #fff;
}

.login-box {
  width: 350px; /* 너비를 약간 늘려서 링크들이 한 줄에 나오도록 함 */
  padding: 2rem;
  background-color: #fff;
  border: 3px solid #333; /* 테두리를 더 선명하게 */
  border-radius: 10px;
  text-align: center;
}

.login-box h2 {
  color: #333;
  margin-bottom: 1rem;
}

.login-box input {
  width: 100%;
  padding: 0.5rem;
  margin-bottom: 1rem;
  border: 1px solid #ccc;
  border-radius: 5px;
  color: #333;
}

.login-box button {
  width: 100%;
  padding: 0.5rem;
  margin-bottom: 1rem;
  background-color: #333;
  color: #fff;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.login-box button:hover {
  background-color: #555;
}

.login-box div {
  white-space: nowrap; /* 링크들이 줄바꿈되지 않도록 설정 */
}

.login-box a {
  color: #333;
  margin: 0 10px; /* 링크 사이의 간격을 늘림 */
  text-decoration: none;
}

.login-box a:hover {
  text-decoration: underline;
}
</style>