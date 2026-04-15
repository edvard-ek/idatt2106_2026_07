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
  <main class="min-h-screen bg-[#f5f8fc] px-6 py-10 text-[#142033]">
    <section class="mx-auto flex max-w-4xl flex-col gap-6 rounded-[28px] border border-[#d9e3ef] bg-white p-8 shadow-[0_24px_60px_rgba(34,53,91,0.08)]">
      <div class="flex items-start justify-between gap-4">
        <div>
          <p class="text-sm font-semibold tracking-[0.12em] text-[#5d6f8d] uppercase">
            Lærerflate
          </p>
          <h1 class="text-3xl font-black tracking-[-0.04em] text-[#15233b]">
            Dashboard
          </h1>
        </div>
        <button
          class="rounded-full bg-[#153463] px-5 py-3 text-sm font-semibold text-white"
          type="button"
          @click="logout"
        >
          Logg ut
        </button>
      </div>

      <div class="grid gap-4 md:grid-cols-2">
        <article class="rounded-[22px] bg-[#edf4ff] p-5">
          <p class="text-sm font-semibold text-[#56709a]">Innlogget som</p>
          <p class="mt-2 text-2xl font-bold text-[#142033]">
            {{ authStore.user?.firstName }} {{ authStore.user?.lastName }}
          </p>
          <p class="mt-1 text-base text-[#334765]">@{{ authStore.user?.username }}</p>
        </article>

        <article class="rounded-[22px] bg-[#fff4e8] p-5">
          <p class="text-sm font-semibold text-[#89623e]">Skole</p>
          <p class="mt-2 text-2xl font-bold text-[#2f2215]">
            {{ authStore.user?.schoolName }}
          </p>
          <p class="mt-1 text-base text-[#6e4f31]">Rolle: {{ authStore.role }}</p>
        </article>
      </div>
    </section>
  </main>
</template>
