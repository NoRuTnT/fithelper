import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '@/views/HomeView.vue'
import BoardView from '@/views/BoardView.vue'
import YoutubeView from '@/views/YoutubeView.vue'
import LoginView from '@/views/LoginView.vue'
import KakaoView from "@/views/KakaoView.vue";
import RegistUserView from '@/views/RegistUserView.vue'

import BoardList from '@/components/board/BoardList.vue'
import BoardCreate from '@/components/board/BoardCreate.vue'
import BoardDetail from '@/components/board/BoardDetail.vue'
import BoardUpdate from '@/components/board/BoardUpdate.vue'

import SelectUserType from '@/components/User/SelectUserType.vue'
import RegistUser from '@/components/User/RegistUser.vue'
import RegistTrainer from '@/components/User/RegistTrainer.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
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
          component: BoardDetail
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
      path: "/kakao",
      name: "kakao",
      component: KakaoView,
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
      ]
    },
  ]
})

export default router