<template>
    <div>
        <h4>게시글 상세</h4>
        <hr>
        <div>{{ store.board.title }}</div>
        <div>{{ store.board.writer }}</div>
        <div>{{ store.board.regDate }}</div>
        <div>{{ store.board.viewCnt }}</div>
        <div>{{ store.board.content }}</div>
        <hr>
        <button @click="likeCntUp">좋아요</button>
        <p>좋아요 개수는 {{ store.board.likeCount }}개 입니다!</p>

        <button @click="deleteBoard">삭제</button>
        <button @click="updateBoard">수정</button>
        
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

</style>