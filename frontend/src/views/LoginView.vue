<script setup lang="ts">
import { ref } from 'vue'
import { RouterLink } from 'vue-router'

import { Button, buttonVariants } from '@/components/ui/button'

const username = ref('')
const password = ref('')
const organization = ref('NTNU')
const submitted = ref(false)

function handleSubmit() {
  submitted.value = true
}
</script>

<template>
  <main class="flex min-h-screen items-center justify-center px-6 py-12">
    <section class="grid w-xl max-w-5xl gap-8 ">
      <div class="rounded-[2rem] border border-white/80 bg-white/85 p-8 shadow-2xl shadow-slate-900/10 backdrop-blur">
        <div class="flex items-center justify-between gap-4">
          <div>
            <h2 class="mt-2 text-2xl font-semibold text-slate-950">Sign in</h2>
          </div>
          <RouterLink :class="buttonVariants({ variant: 'ghost' })" to="/">
            Back
          </RouterLink>
        </div>

        <form class="mt-8 space-y-5" @submit.prevent="handleSubmit">
          <label class="block space-y-2">
            <span class="text-sm font-medium text-slate-700">Organization</span>
            <input
              v-model="organization"
              class="flex h-11 w-full rounded-xl border border-slate-200 bg-white px-4 text-sm text-slate-950 shadow-sm outline-none transition focus:border-sky-400 focus:ring-4 focus:ring-sky-100"
              placeholder="Institution"
              type="text"
            />
          </label>

          <label class="block space-y-2">
            <span class="text-sm font-medium text-slate-700">Username</span>
            <input
              v-model="username"
              class="flex h-11 w-full rounded-xl border border-slate-200 bg-white px-4 text-sm text-slate-950 shadow-sm outline-none transition focus:border-sky-400 focus:ring-4 focus:ring-sky-100"
              placeholder="username@example.no"
              type="text"
            />
          </label>

          <label class="block space-y-2">
            <span class="text-sm font-medium text-slate-700">Password</span>
            <input
              v-model="password"
              class="flex h-11 w-full rounded-xl border border-slate-200 bg-white px-4 text-sm text-slate-950 shadow-sm outline-none transition focus:border-sky-400 focus:ring-4 focus:ring-sky-100"
              placeholder="Password"
              type="password"
            />
          </label>


          <Button class="w-full" size="lg" type="submit">
            Sign in
          </Button>
        </form>

        <p
          v-if="submitted"
          class="mt-5 rounded-2xl border border-emerald-200 bg-emerald-50 px-4 py-3 text-sm text-emerald-900"
        >
          Dummy submit completed for <strong>{{ username || 'guest user' }}</strong>. No validation
          or backend call was performed.
        </p>
      </div>
    </section>
  </main>
</template>
