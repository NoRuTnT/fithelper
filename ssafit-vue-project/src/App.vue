<template>
  <div>
    <TheHeaderNav id="nav" v-if="!displayNav"/>
    <RouterView @hide-nav="deleteNav"/>
    <TheFooterNav id="nav" v-if="!displayNav"/>
  </div> 
</template>

<script setup>
import {RouterLink, RouterView} from 'vue-router';
import {ref} from 'vue'
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
import TheFooterNav from '@/components/common/TheFooterNav.vue';

// 마이페이지에서는 nav 컴포넌트를 보여주지 않는다.
const displayNav = ref(false);
const deleteNav = function(){
  displayNav.value = true;
}

</script>
<style scoped>
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>