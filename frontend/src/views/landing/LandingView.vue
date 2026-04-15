<script setup lang="ts">
import { RouterLink } from 'vue-router'

import BiggurReveal from './components/BiggurReveal.vue'

const quickNotes = [
  {
    title: 'For elever',
    text: 'Et opplegg som føles som et oppdrag, med spor, valg og samarbeid.',
    accent: 'bg-[#ffe978] text-[#111111] border-[#907247]/35',
    tape: 'bg-[#fff4c4]/90',
    tilt: 'rotate-[-1.2deg]',
  },
  {
    title: 'For lærere',
    text: 'Kort vei fra Feide til aktivitet, og et godt grunnlag for samtale etterpå.',
    accent: 'bg-[#9ce7f0] text-[#111111] border-[#4c8c94]/35',
    tape: 'bg-[#d9f5f8]/90',
    tilt: 'rotate-[1deg]',
  },
] as const

const steps = [
  {
    number: '01',
    title: 'Logg inn med Feide',
    description: 'Start raskt med kjent innlogging og gå rett til riktig flate.',
    palette: 'bg-[#ffe978] text-[#111111] border-[#907247]/35',
    numberColor: 'text-[#4d63bb]',
    tape: 'bg-[#fff4c4]/90',
    tilt: 'rotate-[-1deg]',
  },
  {
    number: '02',
    title: 'Bli med i klassen',
    description: 'Skriv inn koden læreren viser og gå direkte inn i oppdraget.',
    palette: 'bg-[#ffb8aa] text-[#111111] border-[#a36a5d]/35',
    numberColor: 'text-[#d95f39]',
    tape: 'bg-[#ffd9d0]/90',
    tilt: 'rotate-[1deg]',
  },
  {
    number: '03',
    title: 'Løs sakene',
    description: 'Undersøk spor, vurder kilder og ta valg underveis i spillet.',
    palette: 'bg-[#9ce7f0] text-[#111111] border-[#4c8c94]/35',
    numberColor: 'text-[#2b4baa]',
    tape: 'bg-[#d9f5f8]/90',
    tilt: 'rotate-[-0.8deg]',
  },
  {
    number: '04',
    title: 'Snakk om valgene',
    description: 'Bruk opplevelsen som utgangspunkt for refleksjon i klassen.',
    palette: 'bg-[#c9d1ff] text-[#111111] border-[#6975b1]/35',
    numberColor: 'text-[#d95f39]',
    tape: 'bg-[#e1e6ff]/90',
    tilt: 'rotate-[1.2deg]',
  },
] as const
</script>

<template>
  <main
    class="min-h-screen overflow-hidden font-['Lato',sans-serif] text-[#111111]"
    style="
        background-color: burlywood;
    "
  >
    <div
      class="relative isolate before:pointer-events-none before:absolute before:inset-0 before:bg-[radial-gradient(circle,rgba(87,54,31,0.14)_0_1px,transparent_1px)] before:bg-[length:16px_16px] before:opacity-60"
    >



      <section
        class="mt-15 mx-auto grid w-full max-w-[92rem] gap-8 px-4 pb-6 pt-2 sm:px-6 lg:grid-cols-[minmax(0,1.12fr)_minmax(380px,0.88fr)] lg:px-10 lg:pb-8"
      >
        <div
          class="relative rotate-[-1.1deg] rounded-[0.9rem] border border-[#907247]/35 bg-[#ffe978]  sm:p-8 lg:p-15"
        >
          <div
            class="pointer-events-none absolute left-1/2 top-0 h-5 w-28 -translate-x-1/2 -translate-y-1/2 rotate-[1deg] rounded-sm bg-[#fff4c4]/90"
            aria-hidden="true"
          ></div>

          <h1
            class="mt-4 font-['Montserrat_Alternates',sans-serif] text-[clamp(2.8rem,3.5vw,6rem)] leading-[0.84] font-extrabold tracking-[-0.07em] text-[#111111]"
          >
            Nettdetektivene
          </h1>
          <p class="mt-6 max-w-2xl text-[1.02rem] leading-[1.8] text-[#383433] sm:text-[1.12rem]">
            Et læringsspill om kildekritikk, nettvett og digitale spor.
            Elevene går rett inn i oppdraget, og læreren får et opplegg som er enkelt å starte og
            lett å bruke videre i klasserommet.
          </p>

          <div class="mt-8 grid gap-3 sm:grid-cols-2">
            <RouterLink
              to="/login"
              class="inline-flex min-h-14 items-center justify-center rounded-[0.8rem] border border-[#95452d]/18 bg-[#ef7e56] px-7 py-3 text-sm font-black tracking-[0.08em] text-white uppercase transition hover:bg-[#e16f48]"
            >
              Start med Feide
            </RouterLink>
            <a
              href="#slik-fungerer"
              class="inline-flex min-h-14 items-center justify-center rounded-[0.8rem] border border-[#6c492f]/18 bg-[#fff7d1] px-7 py-3 text-sm font-black tracking-[0.08em] text-[#111111] uppercase transition hover:bg-[#fff2bc]"
            >
              Hvordan det virker
            </a>
          </div>

          <div class="mt-8 grid gap-4 sm:grid-cols-2">
            <article
              v-for="note in quickNotes"
              :key="note.title"
              :class="[
                'relative rounded-[0.8rem] border px-4 pb-5 pt-6 sm:px-5',
                note.accent,
                note.tilt,
              ]"
            >
              <div
                :class="[
                  'pointer-events-none absolute left-1/2 top-0 h-4 w-20 -translate-x-1/2 -translate-y-1/2 rounded-sm',
                  note.tape,
                ]"
                aria-hidden="true"
              ></div>
              <p class="text-[0.72rem] font-black tracking-[0.16em] uppercase">
                {{ note.title }}
              </p>
              <p class="mt-3 text-sm leading-[1.7] sm:text-[0.95rem]">
                {{ note.text }}
              </p>
            </article>
          </div>
        </div>

        <div class="grid gap-6">



          <BiggurReveal />
        </div>
      </section>

      <section id="slik-fungerer" class="mx-auto w-full max-w-[92rem] px-4 pb-10 sm:px-6 lg:px-10">
        <div class="grid gap-5 lg:grid-cols-[minmax(270px,0.72fr)_minmax(0,1.28fr)]">
          <div
            class="relative rotate-[-1deg] rounded-[0.85rem] border border-[#9f6b43]/35 bg-[#ffd49e] px-5 pb-6 pt-7 sm:px-6"
          >
            <div
              class="pointer-events-none absolute left-1/2 top-0 h-4 w-20 -translate-x-1/2 -translate-y-1/2 rotate-[2deg] rounded-sm bg-[#ffe9c9]/90"
              aria-hidden="true"
            ></div>
            <p class="text-[0.72rem] font-black tracking-[0.16em] text-[#d95f39] uppercase">
              Slik fungerer det
            </p>
            <h2
              class="mt-3 font-['Montserrat_Alternates',sans-serif] text-[clamp(2rem,5vw,4rem)] leading-[0.92] font-extrabold tracking-[-0.06em] text-[#111111]"
            >
              Fire steg.
              <span class="block text-[#111111]/50">Enkelt og greit</span>
            </h2>
          </div>

          <div class="grid gap-4 lg:grid-cols-4">
            <article
              v-for="step in steps"
              :key="step.number"
              :class="[
                'relative rounded-[0.8rem] border px-5 pb-5 pt-7 sm:px-6',
                step.palette,
                step.tilt,
              ]"
            >
              <div
                :class="[
                  'pointer-events-none absolute left-1/2 top-0 h-4 w-18 -translate-x-1/2 -translate-y-1/2 rounded-sm',
                  step.tape,
                ]"
                aria-hidden="true"
              ></div>
              <p :class="['text-[1.8rem] font-black tracking-[-0.05em]', step.numberColor]">
                {{ step.number }}
              </p>
              <h3
                class="mt-4 font-['Montserrat_Alternates',sans-serif] text-[1.3rem] leading-[1.08] font-extrabold"
              >
                {{ step.title }}
              </h3>
              <p class="mt-3 text-sm leading-[1.7] opacity-90">
                {{ step.description }}
              </p>
            </article>
          </div>
        </div>
      </section>
    </div>
    <footer
      class="mx-auto w-full max-w-[92rem] px-4 pb-8 pt-2 text-center text-sm text-[#2e1f13]/82 sm:px-6 lg:px-10"
    >
      Levert av Nettdetektivene.no
    </footer>
  </main>
</template>
