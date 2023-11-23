<template>
    <div >
        <nav>
            <ul>
                <a href="/">
                    <img src="@/assets/logo.png" height="100" width="200">
                </a>
                <li><RouterLink to="/myPage">메인</RouterLink></li>
                <li><RouterLink to="/myPage/class">클래스</RouterLink></li>
                <li><RouterLink to="/myPage/chatroom">1대1채팅</RouterLink></li>
                <li><RouterLink to="/">정보수정</RouterLink></li>
            </ul>
        </nav>
    </div>
</template>

<script setup>
// Vue Router의 RouterLink 컴포넌트를 사용합니다.
import { RouterLink } from 'vue-router';
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
    margin: 0; /* li 마진 조정 */
    line-height: 50px; /* 높이 조정 */
    border-bottom: 1px solid #e1e4e8; /* 하단 테두리 */
}

nav li a {
    display: block; /* 링크가 영역 전체를 차지하도록 설정 */
    text-decoration: none; /* 링크 밑줄 제거 */
    padding: 10px 15px; /* 패딩 조정 */
    color: #333; /* 링크 색상 */
    transition: background-color 0.3s; /* 호버 효과를 위한 트랜지션 */
}

nav li a:hover {
    background-color: #e2e6ea; /* 호버 시 배경색 변경 */
}
</style>