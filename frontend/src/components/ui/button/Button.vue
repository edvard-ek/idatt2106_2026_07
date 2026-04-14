<script setup lang="ts">
import { computed, useAttrs } from 'vue'

import { cn } from '@/lib/utils'

import { buttonVariants, type ButtonVariants } from './button'

defineOptions({
  inheritAttrs: false,
})

interface Props {
  variant?: ButtonVariants['variant']
  size?: ButtonVariants['size']
  type?: 'button' | 'submit' | 'reset'
}

withDefaults(defineProps<Props>(), {
  variant: 'default',
  size: 'default',
  type: 'button',
})

const attrs = useAttrs()

const delegatedAttrs = computed(() => {
  const { class: _class, ...rest } = attrs
  return rest
})
</script>

<template>
  <button
    :class="cn(buttonVariants({ variant, size }), attrs.class)"
    :type="type"
    v-bind="delegatedAttrs"
  >
    <slot />
  </button>
</template>
