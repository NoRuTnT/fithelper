<template>
  <div>
    <img v-for="(img, index) in images" :key="index" :src="img.src" :class="{ visible: img.isVisible }" class="image">
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';

const initImages = () => {
  return [
    { src: '/11.png', isVisible: false },
    { src: '/22.png', isVisible: false },
    { src: '/33.png', isVisible: false }
  ];
};

const images = ref([
  { src: '/11.png', isVisible: false },
  { src: '/22.png', isVisible: false },
  { src: '/33.png', isVisible: false }
]);

const checkVisibility = () => {
  images.value.forEach((img, index) => {
    const element = document.querySelectorAll('.image')[index];
    const rect = element.getBoundingClientRect();
    const isVisible = rect.top < window.innerHeight / 2; // 이미지가 윈도우 높이의 절반 이하일 때만 보이도록 설정

    if (isVisible) {
      img.isVisible = true;
    }
  });
};

onMounted(() => {
  images.value = initImages();
  setTimeout(checkVisibility, 100); // 100ms 후에 함수 실행
  window.addEventListener('scroll', checkVisibility);
});

onBeforeUnmount(() => {
  window.removeEventListener('scroll', checkVisibility);
});
</script>

<style>
.image {
  width: 100%;
  opacity: 0;
  visibility: hidden;
  transform: translateX(-30px); /* 왼쪽에서 조금 이동하여 시작 */
  transition: opacity 1.5s ease, transform 1.5s ease, visibility 1.5s ease;
}

/* 홀수 번째 이미지는 왼쪽에서, 짝수 번째 이미지는 오른쪽에서 나타남 */
.image:nth-child(odd) {
  transform: translateX(-30px);
}

.image:nth-child(even) {
  transform: translateX(30px);
}

.image.visible {
  opacity: 1;
  visibility: visible;
  transform: translateX(0);
}
</style>