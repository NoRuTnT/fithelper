<template>
    <div>
        <h3>회원정보를 수정할 수 있습니다.</h3>
        <ul>
            
                <li>메인</li>
                <li>
                    <label for="email">이메일</label>
                    <input type="text" id="email" v-model="store.user.email">
                </li>
                <li>
                    <label for="nickname">닉네임</label>
                    <input type="text" id="nickname" v-model="store.user.nickname">
                </li>
                <li>
                    <label for="birth">생일</label>
                    <input type="text" id="birth" v-model="store.user.birth">
                </li>
                <li>핸드폰 번호</li>
            </ul>
            <button @click="updateUser">수정</button>
    </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
const store = useUserStore();

const updateUser = function () {
    store.updateUser()
}



</script>

<style scoped>
nav ul {
    font-family: Arial, sans-serif;
  font-weight: bold; /* 모든 텍스트를 굵게 만듦 */
  align-items: center; /* 항목들을 세로 중앙에 정렬 */
    list-style: none; /* 기본 목록 스타일 제거 */
    padding: 0; /* 기본 패딩 제거 */
    margin: 0; /* 기본 마진 제거 */
}
nav li {
    display: block; /* 링크가 영역 전체를 차지하도록 설정 */
    text-decoration: none; /* 링크 밑줄 제거 */
    padding: 10px 15px; /* 패딩 조정 */
    color: #333; /* 링크 색상 */
    transition: background-color 0.3s; /* 호버 효과를 위한 트랜지션 */
    margin: 0; /* li 마진 조정 */
    line-height: 50px; /* 높이 조정 */
    border-bottom: 1px solid #e1e4e8; /* 하단 테두리 */
}
</style>