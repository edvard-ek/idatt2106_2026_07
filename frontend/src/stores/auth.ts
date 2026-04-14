import { defineStore } from 'pinia'
import type { AuthSession, AuthRole } from '@/lib/auth'

const STORAGE_KEY = 'feide-auth-session'

function loadSession(): AuthSession | null {
  if (typeof window === 'undefined') {
    return null
  }

  const stored = window.localStorage.getItem(STORAGE_KEY)
  if (!stored) {
    return null
  }

  try {
    return JSON.parse(stored) as AuthSession
  } catch {
    window.localStorage.removeItem(STORAGE_KEY)
    return null
  }
}

export const useAuthStore = defineStore('auth', {
  state: () => ({
    session: loadSession() as AuthSession | null,
  }),
  getters: {
    isAuthenticated: (state) => state.session !== null,
    role: (state): AuthRole | null => state.session?.role ?? null,
    user: (state) => state.session?.user ?? null,
    accessToken: (state) => state.session?.accessToken ?? null,
  },
  actions: {
    setSession(session: AuthSession) {
      this.session = session
      window.localStorage.setItem(STORAGE_KEY, JSON.stringify(session))
    },
    clearSession() {
      this.session = null
      window.localStorage.removeItem(STORAGE_KEY)
    },
  },
})
