import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import Tabs from '../views/Home/Tabs.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    component: () => import('@/views/Welcome/Welcome.vue')
  },
  {
    name : 'home',
    path: '/home',
    component: () => import('@/views/Home/Home.vue')
  },
  {
    name : 'game',
    path: '/game',
    component: () => import('@/views/Game/Game.vue')
  },
  {
    name : 'cardsManager',
    path: '/cardsManager/:serieId',
    component: () => import('@/views/Manager/CardsManager.vue')
  },
  {
    path: '/app/',
    component: Tabs,
    children: [
      {
        path: '',
        redirect: '/app/home'
      },
      {
        path: 'home',
        component: () => import('@/views/Home/Home.vue')
      },
      {
        path: 'seriesManager',
        component: () => import('@/views/Manager/SeriesManager.vue')
      },
      {
        path: 'historyManager',
        component: () => import('@/views/Manager/HistoryManager.vue')
      }
    ]
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
