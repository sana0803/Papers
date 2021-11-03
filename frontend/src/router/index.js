import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path:'*',
    component: () => import('../views/intro/Login.vue'),
    redirect: '/',
  },
  {
    path:'/',
    component: () => import('../views/intro/Login.vue')
  },
  {
    path: '/signUp',
    component: () => import('../layouts/IntroLayout.vue'),
    children:[
      {path: '', component: () => import('../views/intro/SignUp.vue')}
    ]
  },
  {
    path: '/main',
    component: () => import('../layouts/MainLayout.vue'),
    redirect: 'main/mainList',
    children:[
      {path: 'mainList', component: () => import('../views/main/MainList.vue')},
      {path: 'alert', component: () => import('../views/main/Alert.vue')},
      {path: 'myPage', component: () => import('../views/main/MyPage.vue')},
      {path: 'templete', component: () => import('../views/main/WriteTemplete.vue')},
      {path: 'store', component: () => import('../views/main/Store.vue')},
      {path: 'album', component: () => import('../views/main/Album.vue')}
    ]
  },
  {
    path: '/diary',
    component: () => import('../layouts/DiaryLayout.vue'),
    redirect: 'diary/diaryList1',
    children:[
      {path: 'diaryList1', component: () => import('../views/diary/DiaryList1.vue')},
      {path: 'diaryList2', component: () => import('../views/diary/DiaryList2.vue')},
      {path: 'manage', component: () => import('../views/diary/Manage.vue')}
    ]
  },
  {
    path: '/write',
    component: () => import('../views/write/Write.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
