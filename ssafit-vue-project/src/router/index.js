import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '@/views/HomeView.vue'
import BoardView from '@/views/BoardView.vue'
import YoutubeView from '@/views/YoutubeView.vue'
import LoginView from '@/views/LoginView.vue'
import RegistUserView from '@/views/RegistUserView.vue'
import KakaoView from '@/views/KakaoView.vue'
import MyPageView from '@/views/MyPageView.vue'

import BoardList from '@/components/board/BoardList.vue'
import BoardCreate from '@/components/board/BoardCreate.vue'
import BoardDetail from '@/components/board/BoardDetail.vue'
import BoardUpdate from '@/components/board/BoardUpdate.vue'
// 댓글 내용 컴포넌트
import CommentList from '@/components/Comment/CommentList.vue'

import SelectUserType from '@/components/User/SelectUserType.vue'
import RegistUser from '@/components/User/RegistUser.vue'
import RegistTrainer from '@/components/User/RegistTrainer.vue'
import RegistGymowner from '@/components/User/RegistGymowner.vue'

// 마이페이지 항목 컴포넌트
import ClassList from '@/components/myPage/Class.vue' 
import Chatroom from '@/components/myPage/Chatroom.vue'
import Main from '@/components/myPage/Main.vue'
import Charge from '@/components/myPage/Charge.vue'
import Update from '@/components/myPage/Update.vue'
import Delete from '@/components/myPage/Delete.vue'

import KakaoMap from '@/components/kakao/KakaoMap.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { // 홈 화면
      path: '/',
      name: 'home',
      component: HomeView
    },
    { // 마이페이지
      path: '/myPage',
      name: 'myPage',
      component: MyPageView,
      children: [
        {
          path: "",
          name: "main",
          component: Main
        },
        {
          path: "class",
          name: "classList",
          component: ClassList
        },
        {
          path: "charge",
          name: "charge",
          component: Charge
        },
        {
          path: "chatroom",
          name: "chatroom",
          component: Chatroom
        },
        {
          path: "update",
          name: "update",
          component: Update
        },
        {
          path: "delete",
          name: "delete",
          component: Delete
        },

      ]
    },
    { // 게시판
      path: '/board',
      name: 'board',
      component: BoardView,
      children: [
        {
          path: "",
          name: "boardList",
          component: BoardList
        },
        {
          path: "create",
          name: "boardCreate",
          component: BoardCreate
        },
        {
          path: "update/:id",
          name: "boardUpdate",
          component: BoardUpdate
        },
        {
          path: "detail/:id", 
          name: "boardDetail",
          component: BoardDetail,
          // 댓글 목록 표시
          children: [
            {
              path: "comment",
              name: "commentList",
              component: CommentList
            }
          ]
        },
      ]
    },
    {
      path: '/youtube',
      name: 'youtube',
      component: YoutubeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/registUser',
      name: 'registUser',
      component: RegistUserView,
      children: [
        {
          path: "",
          name: "SelectUserType",
          component: SelectUserType
        },
        {
          path: "normal",
          name: "registUser",
          component: RegistUser
        },
        {
          path: "trainer",
          name: "registTrainer",
          component: RegistTrainer
        },
        {
          path: "gymowner",
          name: "registGymowner",
          component: RegistGymowner
        },
      ]
    },
    {
      path: "/kakao",
      name: "kakao",
      component: KakaoView,
    },
  ]
})

export default router