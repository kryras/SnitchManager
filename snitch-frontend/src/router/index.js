import { createRouter, createWebHistory } from 'vue-router'
import { Role } from '../helpers/role.js';
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import LoginRegister from '../views/LoginRegister.vue'
import DenunciationList from '../views/DenunciationList.vue'
import TypesList from '../views/TypesList.vue'
import BonusesList from '../views/BonusesList.vue'
import Ranking from '../views/Ranking.vue'
import NotFound from '../views/NotFound.vue'
import AdminManageRoles from '../views/AdminManageRoles.vue'

const DEFAULT_TITLE = process.env.VUE_APP_TITLE;

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login-register',
    name: 'LoginRegister',
    component: LoginRegister,
    beforeEnter: (to, from, next) => {
      const currentUser =  JSON.parse(localStorage.getItem("user")) || null;
      if (currentUser) {
        return next({ name: 'Home' });
      }
      next();
    },
    meta: {
      title: ' - Login/Register',
    }
  },
  {
    path: '/about',
    name: 'About',
    component: About,
    meta: {
      title: ' - About',
    }
  },
  {
    path: '/denunciation-list',
    name: 'DenunciationList',
    component: DenunciationList,
    meta: {
      title: " - Denunciation list",
      authorize: [Role.Admin, Role.Moderator, Role.User]
    }
  },
  {
    path: '/types-list',
    name: 'TypesList',
    component: TypesList,
    meta: {
      title: ' - Types',
    }
  },
  {
    path: '/bonuses-list',
    name: 'BonusesList',
    component: BonusesList,
    meta: {
      title: ' - Bonuses',
    }
  },
  {
    path: '/ranking',
    name: 'Ranking',
    component: Ranking,
    meta: {
      title: ' - Ranking',
      authorize: [Role.Admin, Role.Moderator, Role.User]
    }
  },
  {
    path: '/admin-manager-roles',
    name: 'AdminManageRoles',
    component: AdminManageRoles,
    meta: {
      title: ' - Admin - Manage roles',
      authorize: [Role.Admin]
    }
  },
  {
    path: '/not-found',
    name: 'NotFound',
    component: NotFound,
    meta: {
      title: ' - Page not found',
    }
  },
  { path: '/:pathMatch(.*)*', redirect: { name: "NotFound" } }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  const { authorize } = to.meta;
  const currentUser = JSON.parse(localStorage.getItem("user"));

  if (authorize) {
    if (!currentUser) {
      return next({ name: 'LoginRegister' });
    }
    if (authorize.length && !authorize.includes(currentUser.userRoles.toString())) {
      return next({ name: 'Home' });
    }
  }
  next();
});

router.afterEach((to, from) => {
  document.title = `${DEFAULT_TITLE}${to.meta.title || ""}`;
});

export default router
