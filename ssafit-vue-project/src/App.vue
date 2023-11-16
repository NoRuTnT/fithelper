<template>
  <div>
    <TheHeaderNav/>
    <RouterView/>
    <TheFooterNav/>
  </div> 
</template>

<script setup>
import {RouterLink, RouterView} from 'vue-router';
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
import TheFooterNav from '@/components/common/TheFooterNav.vue';

</script>

<style scoped>

</style>