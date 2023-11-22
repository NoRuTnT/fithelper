<!-- 게시글 상세보기에서 하단에 나타나는-->
<!-- 댓글 목록 컴포넌트-->
<template>
    <div>
        <h4>댓글 목록</h4>
        <hr>
        <table>
            <tr>
                <th>댓글번호</th><!--boardId-->
                <th>사용자번호</th><!--title-->
                <th>내용</th>
                <th>등록일</th>
            </tr>
            <tr v-for="(comment, index) in store.commentList" :key="comment.commentId">
                <td>{{ index + 1 }}</td>
                <td>
                    {{ comment.userId }}
                </td>
                <td>{{ comment.content }}</td>
                <td>{{ comment.regDate }}</td>
                <td><button @click="deleteComment(comment.commentId)">글 삭제하기</button></td>
            </tr>
        </table>
        <p>댓글 내용</p>
        <input type="text" v-model="comment.content">
        <button @click="createComment">댓글등록</button>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useCommentStore } from "@/stores/comment";
import { useUserStore } from '@/stores/user';
import { onMounted, ref } from "vue";

const route = useRoute();
const store = useCommentStore();
const userStore = useUserStore(); // 사용자 id를 가져와서 내가 쓴 글만 삭제 가능하게 만들기 위함.
// onMounted(() => {
//     store.getCommentList(route.params.id)
// })
onMounted(
    () => {
    store.getCommentList(route.params.id);
    console.log('vue에서 전달받은 내용');
    console.log(store.commentList);
}
)

const comment = ref({
    commentId: '',
    userId: '1', // 로그인 사용자 정보를 입력받아서 넣어줍니다.
    boardId: route.params.id,
    content: '',
    regDate: '',
})

const createComment = function () {
    store.createComment(comment.value, route.params.id)
}
const deleteComment = function(UID){
    const userInfo = ref({});


    userInfo.value = userStore.getUser(); // 스토리지에서 받은 이메일을 넣어 주세요
    if(true){ // userInfo.value.userId == UID
        store.deleteComment(UID); // 게시글 삭제
    }else{
        alert('내가 쓴 댓글만 삭제할 수 있습니다!');
    }
}

// <!-- <RouterLink :to="`/board/detail/${board.boardId}`"></RouterLink> -->

</script>

<style scoped></style>