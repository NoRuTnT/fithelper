<template>
    <div>
        <h4>게시글 목록</h4>
        <hr>
        <table>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>조회수</th>
                <th>등록일</th>
            </tr>
            <tr v-for="(board, index) in store.boardList" :key="board.boardId">


                <td>{{ index + 1 }}</td>
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
import router from '@/router';
const store = useBoardStore()

onMounted(() => {
    store.getBoardList()
})

// import { useRoute, useRouter } from 'vue-router';


const writeBoard = function(){
    router.push({name: 'boardCreate'})
}

</script>

<style scoped>
div {
    padding: 20px;
    max-width: 800px;
    margin: auto;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
}
th {
    background-color: rgb(255, 255, 255);
    color: rgb(0, 0, 0);
    padding: 10px;
    text-align: left;
}

td {
    padding: 10px;
    border-bottom: 1px solid #ddd;
}

tr:hover {
    background-color: #f5f5f5;
}
a {
    color: rgb(0, 0, 0);
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}
.buttons {
    display: flex;
    justify-content: center;
}

button {
    background-color: slateblue;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

button:hover {
    background-color: darkslateblue;
}
h4 {
    color: slateblue;
    font-size: 28px;
    text-align: center;
    margin-bottom: 20px;
}

</style>