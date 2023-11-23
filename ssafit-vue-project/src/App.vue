<template>
  <div>
    <TheHeaderNav  v-if="!displayNav" id="header"/>
    <RouterView @hide-nav="deleteNav" id="body"/>
    <TheFooterNav id="footer" v-if="!displayNav"/>
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
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 500; /* 다른 요소들 위에 헤더가 위치하도록 z-index 설정 */
  background-color: white; /* 배경색 추가 */
}


</style>