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
li{
    list-style: none;
}
.regist-box {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    background-color: white;
}

.regist-box h2 {
    text-align: center;
    color: #333;
    margin-bottom: 20px;
}

.regist-box ul {
    list-style: none;
    padding: 0;
}

.regist-box li {
    margin-bottom: 10px;
}

input[type="text"],
input[type="password"],
input[type="radio"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    box-sizing: border-box; /* 입력 필드의 패딩을 너비에 포함 */
}

input[type="radio"] {
    width: auto; /* 라디오 버튼의 경우 너비를 자동으로 설정 */
}

label {
    margin-right: 10px;
}

button {
    width: 100%;
    padding: 10px 0;
    background-color: #5f5f5f;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

button:hover {
    background-color: #3b3b3b;
}

p {
    margin: 10px 0;
}
</style>