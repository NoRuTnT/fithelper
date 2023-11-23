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
a {
    font-size: 16px; /* 폰트 크기 조정 */
    padding: 10px 15px; /* 적절한 패딩 추가 */
    margin: 10px; /* 마진 조정 */
    background-color: #969696; /* 배경색 */
    border: none; /* 테두리 제거 */
    border-radius: 5px; /* 둥근 모서리 */
    text-decoration: none; /* 밑줄 제거 */
    color: black; /* 글자색 */
    display: inline-block; /* 올바른 패딩과 마진 적용을 위해 */
    transition: background-color 0.3s, color 0.3s; /* 부드러운 색상 변화 효과 */
}

a:hover {
    background-color: #bfbfbf; /* 마우스 호버 시 배경색 변경 */
    color: #333; /* 마우스 호버 시 글자색 변경 */
}
</style>