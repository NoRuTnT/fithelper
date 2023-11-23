<!--회원가입 1단계-->
<!--일반 사용자 가입인지 트레이너 사용자 가입인지를 선택하고-->
<!--다음 화면으로 넘어감-->
<template>
    <div>
        <h3>사용자 유형 선택</h3>
        <RouterLink to="/registUser/normal">일반회원</RouterLink>
        <RouterLink to="/registUser/trainer">트레이너회원</RouterLink>
        <RouterLink to="/registUser/gymowner">Gym회원</RouterLink>
    </div>
</template>

<script setup>
const selectNormalUser = function(){

}
const selectTrainerUser = function(){
    
}
</script>

<style scoped>
*{
    margin: 0;
    padding: 0;
    border: none;
}
a{
   font-size: 30px;
    margin: 20px;
    background-color: pink;
    border-radius: 10px;
    text-decoration: none;
    color: white;
}
</style>