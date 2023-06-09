import { createRouter, createWebHistory } from 'vue-router'
import Main from '../views/Main.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'index',
      // component: Main,
      children: [
        {
          path: '/converter',
          name: 'Converter',
          component:  () =>
              import("../views/Converter.vue")
        },
        {
          path: '/history',
          name: 'History',
          component: () =>
              import("../views/History.vue")
        },
        {
          path: '/login',
          name: 'Login',
          component: () =>
              import("../views/Login.vue")
        },
      ]
    },

  ]
})

export default router
