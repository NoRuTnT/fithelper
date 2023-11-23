<template>
  <div class="cards-container">
    <div class="card-body">
      <div>
        <span class="title">나의 등급</span>
        <div v-if="authStore.totalcash >= 1000000" class="description">
          <img src="/gold.png" alt="Gold" width="200" height="200">
          <span class="info-value">gold</span>
        </div>
        <div v-else-if="authStore.totalcash >= 100000" class="description">
          <img src="/silver.png" alt="Silver" width="200" height="200">
          <span class="info-value">silver</span>
        </div>
        <div v-else class="description">
          <img src="/bronze.png" alt="Bronze" width="200" height="200">
          <span class="info-value">bronze</span>
        </div> 
      </div>
      <div>
        <span class="title">보유 캐시</span>    
        <div class="description">
          <img src="/cash.png" alt="cash" width="50" height="50">
          <span class="info-value">{{authStore.cash}}원</span>
          <button class="btn-charge" @click="goCharge">충전</button>
        </div>
        <span class="title">등급업까지 남은 돈</span>    
        <div class="description">
          <img src="/cash.png" alt="cash" width="50" height="50">
          <span v-if="authStore.totalcash >= 1000000" class="info-value">최종등급입니다.</span>
          <span v-else-if="authStore.totalcash >= 100000" class="info-value">{{1000000-authStore.totalcash}}원</span>
          <span v-else class="info-value">{{100000-authStore.totalcash}}원</span>
        </div>
      </div> 
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';

const router = useRouter();

const goCharge = () => {
  router.push({ name: 'charge' });
};
const authStore = useAuthStore();

authStore.updatecashFromToken();
authStore.updateTotalcashFromToken();
// const totalcash = authStore.totalcash;
// const cash = authStore.cash;

// const goGold = computed(() => 1000000 - totalcash);
// const goSilver = computed(() => 100000 - totalcash);
// console.log(totalcash.value);
// console.log(Cash.value);

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
  width: 200;
}

.card-body {
  display: flex;
  flex-direction: row; /* 요소들을 가로로 배치 */
 
  gap: 1rem; /* 요소 사이의 간격 설정 */
  
}

</style>