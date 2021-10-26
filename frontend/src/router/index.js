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
      {path: 'alert', component: () => import('../views/main/Alert.vue')}
    ]
  },
  {
    path: '/diary',
    component: () => import('../layouts/DiaryLayout.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
