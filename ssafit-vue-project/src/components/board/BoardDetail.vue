<template>
    <div>
        <div class="board-header">
            <div>{{ store.board.title }}</div>
            <div>{{ store.board.writer }}</div>
            <div>{{ store.board.regDate }}</div>
        </div>
        
        <div class="board-content">
            <h4>게시글 상세</h4>
            <hr>
            <p>{{ store.board.content }}</p>
            <p>조회수: {{ store.board.viewCnt }}</p>
            <hr>
        </div>

        <div class="like-section">
            <button class="like" @click="likeCntUp">
                <span>{{ store.board.likeCount }}</span> 좋아요
            </button>
        </div>

        <div class="action-buttons">
            <button @click="deleteBoard">삭제</button>
            <button @click="updateBoard">수정</button>
        </div>
        
        <CommentList class="comment-list"/>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useBoardStore } from "@/stores/board";
import { onMounted, ref } from "vue";
import axios from 'axios'

import CommentList from '@/components/comment/CommentList.vue';

// 글의 삭제, 수정 권한 체크를 위한 import
import { useAuthStore } from '@/stores/auth';

// 컴포넌트 내부에서
const authStore = useAuthStore();
const getUserId = function(){
  authStore.updateUserIdFromToken();
  return authStore.userId;
}


const store = useBoardStore()

const route = useRoute();
const router = useRouter();
onMounted(() => {
    store.getBoard(route.params.id)
})

const deleteBoard = function () {
    // 자신이 쓴 board만 삭제할 수 있게 만들어야 됨
    if(store.board.userId === getUserId()){
        store.deleteBoard(route.params.id)
    }else{
        alert('자신이 작성한 게시글만 삭제할 수 있습니다.')
    }
}

const updateBoard = function () {
    // 자신이 쓴 board만 수정할 수 있게 만들어야 됨
    if(store.board.userId === getUserId()){
        // store.updateBoard(route.params.id);
        router.push({ name: 'boardUpdate'});
    }else{
        alert('자신이 작성한 게시글만 수정할 수 있습니다.')
    }
    
    
}
const likeCntUp = function(){
    store.likeCountUp(store.board.boardId);
    router.go();
}

</script>

<style scoped>
div {
    padding: 20px;
    max-width: 800px;
    margin: auto;
    font-family: Arial, sans-serif;
}
.board-header {
    background-color: #f2f2f2;
    padding: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.board-header div {
    color: #333;
}
.board-content {
    padding: 20px;
}
button.like {
    background-color: white;
    border: 2px solid red;
    color: red;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
    display: inline-flex;
    align-items: center;
}

button.like:hover {
    background-color: #ffcccc;
}

button.like span {
    margin-right: 10px;
    font-weight: bold;
}
.action-buttons {
    display: flex;
    justify-content: flex-end;
    background-color: #f2f2f2;
    padding: 10px;
    border-top: 1px solid #ddd;
}

.action-buttons button {
    margin-left: 10px;
    background-color: slateblue;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}
.like-section {
    text-align: center;
    margin: 20px 0;
}
</style>