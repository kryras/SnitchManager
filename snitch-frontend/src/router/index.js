import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import LoginRegister from '../views/LoginRegister.vue'
import DenunciationList from '../views/DenunciationList.vue'
import TypesList from '../views/TypesList.vue'
import BonusesList from '../views/BonusesList.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login-register',
    name: 'LoginRegister',
    component: LoginRegister
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/denunciation-list',
    name: 'DenunciationList',
    component: DenunciationList
  },
  {
    path: '/types-list',
    name: 'TypesList',
    component: TypesList
  },
  {
    path: '/bonuses-list',
    name: 'BonusesList',
    component: BonusesList
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
