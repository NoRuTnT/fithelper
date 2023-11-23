<template>
    <main>
    <!-- 메인 이미지 및 텍스트 영역 -->
    <section class="hero-section">
      <img src="/11.png" alt="Main Hero Image" class="hero-image">
      <img src="/22.png" alt="Main Hero Image" class="hero-image">
      <img src="/33.png" alt="Main Hero Image" class="hero-image">      
    </section>
  </main>
  </template>
  
  <script setup>
  // 세부적인 내용은 카카오뱅크 UI/UX토대로 만들기 + 스타벅스
  // 로고 | 항목들 |
  // -------------
  //       |
  // -------------
  // PT 트레이너 홍보
  </script>
  
  <style>
  /* 폰트, 컬러, 기타 변수 */
:root {
  --color-black: #000;
  --color-white: #fff;
  --color-grey: #f4f4f4;
}
 .hero-section {
  display: flex; /* 이미지들을 가로로 나란히 배치 */
  flex-direction: column; /* 아이템들을 세로로 나열 */
  position: relative;
  text-align: center;
}

.hero-image {
  
  flex: 0 0 auto; /* 이미지가 가로로 넘칠 경우 스크롤되도록 설정 */
  width: 100%; /* 화면 너비에 맞게 이미지 너비 설정 */
  max-width: none; /* 기본 이미지의 최대 너비 제한을 해제 */
  height: auto; /* 이미지의 높이를 비율에 맞게 자동 조정 */
}

/* 마지막 이미지에는 마진을 주지 않음 */
.hero-image:last-child {
  margin-bottom: 0;
}





  </style>