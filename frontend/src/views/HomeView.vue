<template>
  <main class="relative flex min-h-screen items-center overflow-hidden px-auto py-12">
    <div class="absolute inset-0 bg-[linear-gradient(135deg,rgba(15,23,42,0.03),rgba(14,165,233,0.08))]" />
    <section class="relative mx-auto grid w-full max-w-6xl gap-10 lg:items-center">
        <div class="mx-auto">
          <RouterLink :class="buttonVariants({ size: 'lg' })" to="/login">
            Log inn med Feide!
          </RouterLink>
        </div>
    </section>
  </main>
</template>

<script setup lang="ts">
import { RouterLink } from 'vue-router'

import { buttonVariants } from '@/components/ui/button'
</script>
