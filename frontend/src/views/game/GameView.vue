<script setup lang="ts">
import { useRouter } from 'vue-router'

import { useAuthStore } from '@/stores/auth'

const authStore = useAuthStore()
const router = useRouter()

function logout() {
  authStore.clearSession()
  router.push({ name: 'login' })
}
</script>

<template>
  <main class="min-h-screen bg-[radial-gradient(circle_at_top,#e9f5ec,transparent_48%),linear-gradient(180deg,#f6fbf7,#edf5ef)] px-6 py-10 text-[#102118]">
    <section class="mx-auto flex max-w-4xl flex-col gap-6 rounded-[28px] border border-[#d7e9db] bg-white p-8 shadow-[0_24px_60px_rgba(24,57,35,0.08)]">
      <div class="flex items-start justify-between gap-4">
        <div>
          <p class="text-sm font-semibold tracking-[0.12em] text-[#53715b] uppercase">
            Elevflate
          </p>
          <h1 class="text-3xl font-black tracking-[-0.04em] text-[#15281b]">
            Game
          </h1>
        </div>
        <button
          class="rounded-full bg-[#1d5c36] px-5 py-3 text-sm font-semibold text-white"
          type="button"
          @click="logout"
        >
          Logg ut
        </button>
      </div>

      <div class="grid gap-4 md:grid-cols-2">
        <article class="rounded-[22px] bg-[#edf8ef] p-5">
          <p class="text-sm font-semibold text-[#4f7357]">Innlogget som</p>
          <p class="mt-2 text-2xl font-bold text-[#14251a]">
            {{ authStore.user?.firstName }} {{ authStore.user?.lastName }}
          </p>
          <p class="mt-1 text-base text-[#31503a]">@{{ authStore.user?.username }}</p>
        </article>

        <article class="rounded-[22px] bg-[#f2f7ff] p-5">
          <p class="text-sm font-semibold text-[#587197]">Klasse</p>
          <p class="mt-2 text-2xl font-bold text-[#18253b]">
            {{ authStore.user?.classroomName }}
          </p>
          <p class="mt-1 text-base text-[#39506e]">{{ authStore.user?.schoolName }}</p>
        </article>
      </div>
    </section>
  </main>
</template>
