<template>
    <div class="regist-box">
        <h2>트레이너 회원</h2>
        <h2>SSAFIT</h2>
        <div>
            <ul>
                <li><input type="text" placeholder="이메일" v-model="trainer.email"></li>
                <li><input type="text" placeholder="이름" v-model="trainer.name"></li>
                <li><input type="password" placeholder="비밀번호" v-model="trainer.password"></li>
                <li><input type="text" placeholder="닉네임" v-model="trainer.nickname"></li>
                <li><input type="text" placeholder="생일" v-model="trainer.birth"></li>                
                <li><input type="text" placeholder="핸드폰번호" v-model="trainer.phonenum"></li>
                <li><input type="text" placeholder="주소" v-model="trainer.address"></li>
                <p>성별(선택)</p>
                <li>
                    <label for="male">남성</label>
                    <input type="radio" id="male" value="1" v-model="trainer.sex">
                </li>
                <li>
                    <label for="female">여성</label>
                    <input type="radio" id="female" value="2" v-model="trainer.sex">
                </li>
                <li><button @click="createtrainer">등록</button></li>
            </ul>
                
        </div>
    </div>
</template>

<script setup>


import { ref } from "vue";
import { useTrainerStore } from "@/stores/trainer";

const store = useTrainerStore();
const trainer = ref({
    trainerId: '',
    email: '',
    password: '',
    name: '',
    nickname: '',
    birth: '',    
    phonenum: '',
    address: '',
    cash: '',
    sex: ''
    // sex: gender.value,
})

const createtrainer = function () {
    console.log(trainer);
    store.createTrainer(trainer.value)
}
</script>

<style scoped>
li{
    list-style: none;
}
</style>