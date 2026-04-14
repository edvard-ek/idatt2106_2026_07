<script setup lang="ts">
import { onBeforeUnmount, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'

import feideLogoUrl from '@/assets/Feide_idY2p4BuwR_1.svg'
import norwayFlagUrl from '@/assets/norway.svg'

const schools = ['NTNU', 'Universitetet i Oslo', 'OsloMet', 'BI'] as const

const username = ref('eksempelbruker')
const password = ref('passordpassord')
const selectedSchool = ref<(typeof schools)[number]>('NTNU')
const isSchoolModalOpen = ref(false)
const router = useRouter()

function handleSubmit() {
  router.push({ name: 'dummy' })
}

function openSchoolModal() {
  isSchoolModalOpen.value = true
}

function closeSchoolModal() {
  isSchoolModalOpen.value = false
}

function selectSchool(school: (typeof schools)[number]) {
  selectedSchool.value = school
  closeSchoolModal()
}

function handleWindowKeydown(event: KeyboardEvent) {
  if (event.key === 'Escape' && isSchoolModalOpen.value) {
    closeSchoolModal()
  }
}

onMounted(() => {
  window.addEventListener('keydown', handleWindowKeydown)
})

onBeforeUnmount(() => {
  window.removeEventListener('keydown', handleWindowKeydown)
})
</script>

<template>
  <main
    class="flex min-h-screen items-center justify-center bg-linear-to-b from-[#e5edf6] to-[#dce7f3] px-4 py-8 text-[#12131a]"
  >
    <section class="w-full max-w-[560px]">
      <article class="overflow-hidden rounded-[24px] border border-[#d4dfec] bg-white shadow-[0_24px_60px_rgba(34,53,91,0.12)]">
        <header class="flex justify-end px-5 pt-[18px]">
          <button
            class="inline-flex cursor-default items-center gap-[10px] border-0 bg-transparent text-[0.98rem] font-medium text-[#39404f]"
            type="button"
          >
            <img
              :src="norwayFlagUrl"
              alt=""
              class="h-4 w-[22px] rounded-[2px] shadow-[0_0_0_1px_rgba(0,0,0,0.05)]"
            />
            <span>Bokmål</span>
            <svg class="h-[18px] w-[18px] text-[#31449f]" viewBox="0 0 24 24" aria-hidden="true">
              <path
                d="M5 8.5 12 15.5 19 8.5"
                fill="none"
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
              />
            </svg>
          </button>
        </header>

        <div class="px-7 pb-7 pt-[18px] max-sm:px-4 max-sm:pb-[22px] max-sm:pt-4">
          <h1 class="mb-5 text-[clamp(2.15rem,5vw,3.2rem)] leading-none font-extrabold tracking-[-0.05em] text-[#2f3138]">
            Logg inn med Feide
          </h1>

          <section class="grid gap-[14px]">
            <img :src="feideLogoUrl" alt="Feide" class="h-auto w-28" />
            <p class="m-0 text-[1.05rem] leading-[1.35] text-[#15171e]">
              Du må logge deg på via Feide for å få tilgang til
              Administrasjonsgrensesnitt for www.feide.no.
            </p>
          </section>

          <div class="my-[22px] mb-[18px] border-t-[3px] border-[#d9e5f1]"></div>

          <section>
            <h2 class="mb-[14px] text-[1.1rem] font-bold">Din tilhørighet</h2>
            <div class="flex items-center gap-[14px]">
              <div
                class="inline-flex h-[52px] w-[52px] items-center justify-center rounded-2xl bg-linear-to-br from-[#dce9fb] to-[#9ec2f3] text-[0.95rem] font-extrabold tracking-[0.04em] text-[#214f9f]"
                aria-hidden="true"
              >
                <span>{{ selectedSchool.slice(0, 2).toUpperCase() }}</span>
              </div>
              <div class="grid gap-1">
                <p class="m-0 text-[1.28rem] leading-[1.2] font-semibold">
                  {{ selectedSchool }}
                </p>
                <button
                  class="w-fit border-0 bg-transparent p-0 text-[0.98rem] underline decoration-[1.5px] underline-offset-[3px]"
                  type="button"
                  @click="openSchoolModal"
                >
                  Endre tilhørighet
                </button>
              </div>
            </div>
          </section>

          <div class="my-[22px] mb-[18px] border-t-[3px] border-[#d9e5f1]"></div>

          <form class="grid gap-[18px]" @submit.prevent="handleSubmit">
            <label class="grid gap-2">
              <span class="text-base font-bold">Brukernavn</span>
              <input
                v-model="username"
                autocomplete="username"
                class="h-[72px] w-full rounded-none border-2 border-[#7b88bd] bg-white px-[18px] text-[1.15rem] font-semibold text-[#111111] outline-none transition-colors focus:border-[#4f5ea8] max-sm:h-16"
                type="text"
              />
            </label>

            <label class="grid gap-2">
              <span class="text-base font-bold">Passord</span>
              <input
                v-model="password"
                autocomplete="current-password"
                class="h-[72px] w-full rounded-none border-2 border-[#7b88bd] bg-white px-[18px] text-[1.15rem] font-semibold text-[#111111] outline-none transition-colors focus:border-[#4f5ea8] max-sm:h-16"
                type="password"
              />
            </label>

            <a
              class="w-fit text-[0.98rem] underline decoration-[1.5px] underline-offset-[3px]"
              href="/"
              @click.prevent
            >
              Glemt brukernavn eller passord?
            </a>

            <button
              class="h-[74px] border-0 bg-[#99bff1] text-[1.2rem] font-medium tracking-[-0.02em] text-[#111111] max-sm:h-16"
              type="submit"
            >
              Logg inn
            </button>
          </form>

          <div class="mb-0 mt-[18px] border-t-[3px] border-[#d9e5f1]"></div>

          <button
            class="flex w-full items-center justify-between border-0 bg-transparent py-4 text-left text-[1.08rem] font-medium text-[#111111]"
            type="button"
          >
            <span>Trenger du hjelp?</span>
            <span class="relative inline-block h-6 w-6" aria-hidden="true">
              <span class="absolute left-1/2 top-1/2 h-6 w-[3px] -translate-x-1/2 -translate-y-1/2 rounded-full bg-[#31449f]"></span>
              <span class="absolute left-1/2 top-1/2 h-[3px] w-6 -translate-x-1/2 -translate-y-1/2 rounded-full bg-[#31449f]"></span>
            </span>
          </button>

          <div class="mb-[18px] mt-0 border-t-[3px] border-[#d9e5f1]"></div>

          <a
            class="inline-block text-[0.98rem] underline decoration-[1.5px] underline-offset-[3px]"
            href="/"
            @click.prevent
          >
            Personvern og informasjonskapsler
          </a>
        </div>
      </article>

      <footer class="px-2 pt-[18px] text-center text-[#44506b]">
        <p class="m-0 text-base">
          Feide leveres av <a href="/" @click.prevent>Uninett</a>
        </p>
      </footer>
    </section>

    <div
      v-if="isSchoolModalOpen"
      class="fixed inset-0 flex items-center justify-center bg-[rgba(16,24,40,0.42)] p-5"
      aria-hidden="true"
      @click.self="closeSchoolModal"
    >
      <section
        class="w-full max-w-[420px] rounded-[22px] bg-white p-[22px] shadow-[0_24px_60px_rgba(15,23,42,0.28)]"
        aria-labelledby="school-modal-title"
        aria-modal="true"
        role="dialog"
      >
        <div class="mb-[18px] flex items-start justify-between gap-4">
          <div>
            <p
              class="mb-1 text-[0.82rem] font-bold tracking-[0.08em] text-[#51607b] uppercase"
            >
              Velg skole
            </p>
            <h3 id="school-modal-title" class="m-0 text-[1.45rem] font-bold text-[#1b2130]">
              Tilhørighet
            </h3>
          </div>
          <button
            class="inline-flex h-10 w-10 items-center justify-center rounded-full border-0 bg-[#eef4fb] text-[#31449f]"
            type="button"
            aria-label="Lukk"
            @click="closeSchoolModal"
          >
            <svg class="h-5 w-5" viewBox="0 0 24 24" aria-hidden="true">
              <path
                d="M6 6 18 18M18 6 6 18"
                fill="none"
                stroke="currentColor"
                stroke-linecap="round"
                stroke-width="2"
              />
            </svg>
          </button>
        </div>

        <div class="grid gap-[10px]" role="list">
          <button
            v-for="school in schools"
            :key="school"
            class="flex w-full items-center justify-between gap-3 rounded-2xl border px-4 py-[14px] text-left text-base font-semibold text-[#1a2233]"
            :class="
              school === selectedSchool
                ? 'border-[#84a6df] bg-[#eef4fe]'
                : 'border-[#d7e0ec] bg-white'
            "
            type="button"
            @click="selectSchool(school)"
          >
            <span>{{ school }}</span>
            <span v-if="school === selectedSchool" class="text-[0.86rem] font-bold text-[#2a59b1]">
              Valgt
            </span>
          </button>
        </div>
      </section>
    </div>
  </main>
</template>
