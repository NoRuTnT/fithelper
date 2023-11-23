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
</style>