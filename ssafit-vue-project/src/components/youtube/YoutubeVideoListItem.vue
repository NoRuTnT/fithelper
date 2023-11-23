<template>
    <li @click="openVideo" id="video-item">
      <img :src="video.snippet.thumbnails.default.url" >
      <span>{{video.snippet.title}}</span>
    </li>
  </template>
  
  <script setup>
  import { useYoutubeStore } from '@/stores/youtube';
  const store = useYoutubeStore();
  
  const props = defineProps({
    video: {
      type: Object,
      required: true,
    },
  });
  
  
  
  const clickVideo = function() {
      store.clickVideo(props.video)
  }

  
  const openVideo = function() {
  const videoId = props.video.id.videoId;
  const videoURL = `https://youtube.com/watch?v=${videoId}`;

  // 새 창을 열어서 영상을 보여줌
  window.open(videoURL, '_blank');
}
  
  </script>
  
  <style scoped>
#video-item { /* If you want the style applied to each item, better use class instead of id */
  border: 1px solid #ddd;
  border-radius: 0.5rem;
  overflow: hidden;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s ease-in-out;
}

#video-item:hover {
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}

#video-item img {
  width: 100%;
  height: auto;
  display: block;
}

#video-item h5 {
  font-size: 1rem;
  padding: 1rem;
  margin: 0;
  background-color: #f9f9f9;
  border-bottom: 1px solid #eee;
}

#video-item p {
  padding: 0.5rem 1rem;
  margin: 0;
  font-size: 0.9rem;
  color: #555;
}

.video-item span {
  display: block; /* span이 블록처럼 동작하게 만들어 공간 조정을 쉽게 함 */
  font-size: 1.2rem; /* 가독성을 위한 폰트 크기 조정 */
  font-weight: 600; /* 폰트를 약간 굵게 만들어 무겁지 않게 표현 */
  color: #333; /* 대비를 개선하기 위해 짙은 회색 사용, 순수 검은색보다 덜 가혹함 */
  margin-bottom: 0.5rem; /* 제목 아래 공간 추가 */
  line-height: 1.4; /* 텍스트 흐름을 위한 줄 간격 조정 */
  overflow: hidden; /* 텍스트가 컨테이너를 넘치지 않도록 함 */
  text-overflow: ellipsis; /* 텍스트가 너무 길면 말줄임표(...)를 추가 */
  white-space: nowrap; /* 텍스트가 다음 줄로 넘어가지 않도록 함 */
  padding: 0 1rem; /* 좌우 패딩 추가 */
  text-align: left; /* 텍스트를 왼쪽 정렬 */
}
</style>
  