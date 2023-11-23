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
<<<<<<< HEAD

                <td>{{ index + 1 }}</td>
=======
                <td>{{ index+1 }}</td>
>>>>>>> 4f4b88282caf4511a777d13908771d18c579a322
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
<<<<<<< HEAD
import router from '@/router';
const writeBoard = function () {
    router.push({ name: 'boardCreate' })
=======

const writeBoard = function(){
    router.push({name: 'boardCreate'})
>>>>>>> 4f4b88282caf4511a777d13908771d18c579a322
}

</script>

<style scoped>
<<<<<<< HEAD
.buttons {
    display: flex;
    justify-content: center;
}

button {
    margin: 10px;
}

h4 {
    color: slateblue;
    font-size: 25px;
}
td{
    border-bottom: 1px solid gray;
}
td:nth-child(2){
    width: 50%;
}
.list-content {
    border: 1px solid black;
    border-radius: 2px;
}
a{
    text-decoration: none;
    color: black;
}
=======
.buttons{
    display: flex;
    justify-content: center;
}
button{
    margin: 10px;
}
>>>>>>> 4f4b88282caf4511a777d13908771d18c579a322
</style>