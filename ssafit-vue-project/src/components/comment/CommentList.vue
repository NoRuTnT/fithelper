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

import { useAuthStore } from '@/stores/auth';

// 컴포넌트 내부에서
const authStore = useAuthStore();



const route = useRoute();
const store = useCommentStore();
const userStore = useUserStore(); // 사용자 id를 가져와서 내가 쓴 글만 삭제 가능하게 만들기 위함.

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

const deleteComment = function(CID){
    
    authStore.updateUserIdFromToken(); // 필수
    console.log("auth에서 받은 정보는");
    console.log(authStore.userId);
    console.log("댓글에서 받은 user번호는");
    console.log(comment.value.userId);
    if(comment.value.userId == authStore.userId){ 
        store.deleteComment(CID); // 게시글 삭제
    }else{
        alert('내가 쓴 댓글만 삭제할 수 있습니다!');
    }
}

</script>

<style scoped></style>