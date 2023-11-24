<template>
    <div>
        <h4>게시글 작성</h4>
        <fieldset>
            <legend>등록</legend>
            <div>
                <label for="title">제목 : </label>
                <input type="text" id="title" v-model="board.title">
            </div>
            <!-- <div>
                <label for="writer">쓰니 : </label>
                <input type="text" id="writer" v-model="board.userId">
            </div> -->
            <div>
                <label for="content">내용 : </label>
                <textarea id="content" cols="30" rows="10" v-model="board.content"></textarea>
            </div>
            <div>
                <button @click="createBoard">등록</button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useBoardStore } from "@/stores/board";
import { useAuthStore } from '@/stores/auth';

const authStore = useAuthStore();
const store = useBoardStore();
const board = ref({
    userId: '',
    title: '',
    content: '',
})

const createBoard = function () {
    console.log(board.value);
    authStore.updateUserIdFromToken(); // 필수
    board.value.userId = authStore.userId;
    store.createBoard(board.value)
}
</script>

<style scoped>
div {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
}

h4 {
    text-align: center;
    color: #333;
    margin-bottom: 20px;
}

fieldset {
    border: 1px solid #ddd;
    border-radius: 10px;
    padding: 20px;
}

legend {
    font-size: 1.2em;
    font-weight: bold;
}

label {
    display: block;
    margin-bottom: 5px;
    color: #333;
}

input[type="text"],
textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    box-sizing: border-box; /* 입력 필드의 패딩을 너비에 포함 */
}

textarea {
    resize: vertical; /* 사용자가 세로 크기만 조절할 수 있도록 설정 */
}

button {
    width: 100%;
    padding: 10px 0;
    background-color: #767676;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
}

button:hover {
    background-color: #3e3e3e;
}

</style>