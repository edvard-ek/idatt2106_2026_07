import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'
import HomeView from '../views/LandingView.vue'
import LoginView from '../views/LoginView.vue'
import DashboardView from '@/views/teacher/DashboardView.vue'
import GameView from '@/views/game/GameView.vue'
import { getLandingPath } from '@/lib/auth'
import { useAuthStore } from '@/stores/auth'
import { pinia } from '@/stores/pinia'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView,
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    component: DashboardView,
    meta: { requiresAuth: true, role: 'TEACHER' },
  },
  {
    path: '/game',
    name: 'game',
    component: GameView,
    meta: { requiresAuth: true, role: 'PUPIL' },
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

router.beforeEach((to) => {
  const authStore = useAuthStore(pinia)
  const requiresAuth = to.meta.requiresAuth === true
  const requiredRole = typeof to.meta.role === 'string' ? to.meta.role : null

  if (!authStore.isAuthenticated) {
    return requiresAuth ? { name: 'login' } : true
  }

  const landingPath = getLandingPath(authStore.role!)

  if (to.name === 'home' || to.name === 'login') {
    return landingPath
  }

  if (requiredRole && authStore.role !== requiredRole) {
    return landingPath
  }

  return true
})

export default router
