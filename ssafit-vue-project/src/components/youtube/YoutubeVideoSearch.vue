<template>
    <div class="search-container">
      <input type="text" v-model="keyword" placeholder="검색어 입력" class="search-input">
      <button @click="search" class="search-button">검색</button>
      <div class="preset-buttons">
        <button @click="presetSearch('전신')" class="preset-button">전신</button>
        <button @click="presetSearch('상체')" class="preset-button">상체</button>
        <button @click="presetSearch('하체')" class="preset-button">하체</button>
      </div>
    </div>
  </template>

<script setup>
import {ref} from 'vue'
import {useYoutubeStore} from '@/stores/youtube'
const keyword = ref('')

const store = useYoutubeStore()

const search = function() {
    store.youtubeSearch(`${keyword.value} + 운동`);
}

const presetSearch = function(presetKeyword) {
    // 미리 지정된 검색어를 검색창에 설정하고 검색 실행
    keyword.value = presetKeyword;
    search(); // 이미 "운동"을 추가하는 로직이 search 함수에 있으므로 그대로 호출
}
</script>

<style scoped>
.search-container {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  margin-bottom: 1rem;
}

.search-input {
  flex-grow: 1; /* 검색창이 가능한 공간을 모두 차지하도록 함 */
  max-width: 70%; /* 검색창의 최대 너비를 제한 */
  padding: 0.5rem;
  border: 2px solid #ccc;
  border-radius: 0.25rem;
  font-size: 1rem;
}

.search-button {
  padding: 0.5rem 1rem;
  border: none;
  background-color: #007bff;
  color: white;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: background-color 0.2s;
}

.search-button:hover {
  background-color: #0056b3;
}

.preset-buttons {
  display: flex;
  justify-content: center;
  gap: 0.5rem;
}

.preset-button {
  padding: 0.5rem 1rem;
  border: none;
  background-color: #afafaf;
  color: #212529;
  border-radius: 0.25rem;
  cursor: pointer;
  transition: background-color 0.2s;
}

.preset-button:hover {
  background-color: #e2e6ea;
}
</style>