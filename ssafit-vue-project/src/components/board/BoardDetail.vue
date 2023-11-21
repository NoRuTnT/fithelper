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
        <button @click="">좋아요</button>
        <p>좋아요 개수는 {{ likeNum }}개 입니다!</p>

        <button @click="deleteBoard">삭제</button>
        <button @click="updateBoard">수정</button>
        
        <CommentList/>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useBoardStore } from "@/stores/board";
import { onMounted, ref } from "vue";
import axios from 'axios'

import CommentList from '@/components/comment/CommentList.vue';

// 좋아요 개수를 반응형 변수로 나타냅니다.
const likeNum = ref(0);

const store = useBoardStore()

const route = useRoute();
const router = useRouter();
onMounted(() => {
    store.getBoard(route.params.id)
})

const deleteBoard = function () {
    store.deleteBoard(route.params.id)
}

const updateBoard = function () {
    router.push({ name: 'boardUpdate'})
}


</script>

<style scoped></style>