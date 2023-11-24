<template>
    <div class="cards-container">
       
       <div class="card-body">
           <span class="title">캐시충전</span>
           <div class="description"> 
                <input type="number" v-model="charge.money" placeholder="충전할 금액을 입력하세요">               
                <button class="btn-finalcharge"  @click="finalcharge">충전하기</button>
           </div>
       </div>
   </div>
</template>

<script setup>
import { ref, onMounted  } from 'vue';
import { useAuthStore } from '@/stores/auth';
import {useUserStore} from '@/stores/user';

const authStore = useAuthStore();
const store = useUserStore();
authStore.updateEmailFromToken();
const email = authStore.email;
console.log("Auth store email:", authStore.email);
const charge = ref({    
    email: email,
    money: ''    
})

const finalcharge = function () {
    console.log("Current charge data:", charge.value);
    store.charge(charge.value)
}
</script>

<style scoped>
.cards-container {
 display: flex;
 flex-wrap: wrap;
 justify-content: space-around;
}
.description {
 background: #fff;
 border-radius: 8px;
 box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
 margin: 1rem;
 padding: 1rem;
}

.card-header {
 display: flex;
 justify-content: space-between;
 margin-bottom: 1rem;
}


.card-body {
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    padding: 20px;
    max-width: 400px;
    margin: auto;
    text-align: center;
}

.title {
    display: block;
    font-size: 1.4em;
    color: #333;
    margin-bottom: 20px;
}

.description {
    margin-bottom: 20px;
}

input[type="number"] {
    width: 80%;
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ddd;
    border-radius: 5px;
    box-sizing: border-box;
}

button.btn-finalcharge {
    width: 80%;
    padding: 10px 0;
    background-color: rgb(230, 249, 137);
    color: rgb(0, 0, 0);
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

button.btn-finalcharge:hover {
    background-color: rgb(252, 252, 226);
}

</style>