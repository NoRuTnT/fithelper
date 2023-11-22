<template>
    <div>
        <h4>게시글 목록</h4>
        <hr>
        <table>
            <tr>
                <th>번호</th><!--boardId-->
                <th>제목</th><!--title-->
                <th>조회수</th>
                <th>등록일</th>
            </tr>
            <tr v-for="board in store.boardList" :key="board.boardId">
                <td>{{ board.boardId }}</td>
                <td>
                    <RouterLink :to="`/board/detail/${board.boardId}`">{{ board.title }}</RouterLink>
                </td>
                <td>{{ board.viewCnt }}</td>
                <td>{{ board.regDate }}</td>
            </tr>
        </table>

        <BoardSearchInput />
        <div class="buttons">
            <button @click="writeBoard">글 등록</button>
        </div>
    </div>
</template>

<script setup>
import { useBoardStore } from "@/stores/board";
import { onMounted } from "vue";
import BoardSearchInput from "./BoardSearchInput.vue";
const store = useBoardStore()

onMounted(() => {
    store.getBoardList()
})

// import { useRoute, useRouter } from 'vue-router';
import router from '@/router';
const writeBoard = function(){
    router.push({name: 'boardCreate'})
}

</script>

<style scoped>
.buttons{
    display: flex;
    justify-content: center;
}
button{
    margin: 10px;
}
</style>