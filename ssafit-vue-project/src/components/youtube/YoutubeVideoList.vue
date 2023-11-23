<template>
    <div>
        <h4>검색 결과</h4>
        <ul id="video-list">
            <YoutubeVideoListItem 
                v-for="video in store.videos"
                :key="video.id.videoId"
                :video="video"
            />
        </ul>
    </div>
</template>

<script setup>
import {useYoutubeStore} from '@/stores/youtube'
import YoutubeVideoListItem from './YoutubeVideoListItem.vue';

const store = useYoutubeStore()
</script>

<style scoped>
h4 {
  text-align: center;
  margin: 2rem 0;
  font-size: 2rem;
}

ul {
  list-style: none;
  padding: 0;
  margin: 0;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 1rem;
}
#video-list {
  list-style: none;
  padding: 0;
  margin: 0;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 1rem;
}

</style>