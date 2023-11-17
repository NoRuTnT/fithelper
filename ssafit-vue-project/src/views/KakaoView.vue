<template>
    <div>
        <h2>헬스장 위치 정보를 알려드립니다.</h2>
        <KakaoMap/>
    </div>
</template>

<script setup>
import KakaoMap from '@/components/kakao/KakaoMap.vue';
</script>

<style scoped>
h2{
    color: pink;
}
</style>