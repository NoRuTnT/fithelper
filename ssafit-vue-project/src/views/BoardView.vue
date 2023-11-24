<template>
    <div>
        
        <RouterView/>
        
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import router from '@/router';
const writeBoard = function(){
    router.push({name: 'boardCreate'})
}
</script>

<style scoped>
.buttons{
    display: flex;
    justify-content: center;
}
button{
    margin: 10px;
}

</style>