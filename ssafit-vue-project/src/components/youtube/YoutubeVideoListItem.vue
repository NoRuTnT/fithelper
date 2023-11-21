<template>
    <li @click="openVideo">
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
  
  <style scoped></style>
  