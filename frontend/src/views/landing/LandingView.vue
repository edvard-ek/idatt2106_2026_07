<script setup lang="ts">
import { RouterLink } from 'vue-router'

import BiggurReveal from './components/BiggurReveal.vue'

const highlights = [
  {
    label: 'Play First',
    title: 'Et læringsspill som faktisk føles som et oppdrag',
    description:
      'Elevene møter saker, spor og valg som trener kildekritikk, nettvett og refleksjon uten at det blir en tung teoritime.',
  },
  {
    label: 'Fast Start',
    title: 'Kort vei fra innlogging til aktivitet',
    description:
      'Læreren og elevene trenger bare å logge inn med Feide, så kan klassen gå rett inn i oppgaven.',
  },
  {
    label: 'Now',
    title: 'Bygget for nettet barna faktisk bruker',
    description:
      'Opplegget tar høyde for dagens digitale hverdag, ikke bare gamle eksempler som føles fjerne.',
  },
] as const

const audiences = [
  {
    title: 'For elever',
    text: 'Utforsk oppdrag, samarbeid om spor og lær hvordan man vurderer informasjon, kilder og valg på nett.',
    accent: 'Et format som oppleves som spill, ikke bare oppgave.',
    palette: 'bg-[#fff5eb] text-[#222228]',
    chip: 'text-[#ff8343]',
  },
  {
    title: 'For lærere',
    text: 'Få en enkel inngang til undervisning om nettvett og digitale spor, med et opplegg som inviterer til samtale i etterkant.',
    accent: 'Tydelig oppstart, lett å bruke i klasserommet.',
    palette: 'bg-[#222228] text-white',
    chip: 'text-[#f1dec6]',
  },
] as const

const steps = [
  {
    number: '01',
    title: 'Logg inn med Feide',
    description:
      'Start raskt med kjent innlogging og gå rett videre til riktig flate for lærer eller elev.',
  },
  {
    number: '02',
    title: 'Bli med i klasserommet',
    description: 'Skriv inn koden læreren viser, og gå direkte inn i aktiviteten.',
  },
  {
    number: '03',
    title: 'Løs sakene',
    description:
      'Løs scenarier og svar på spørsmål som gjør digital dømmekraft konkret, relevant og engasjerende.',
  },
  {
    number: '04',
    title: 'Snakk om valgene',
    description:
      'Bruk opplevelsen i etterkant til refleksjon, oppsummering og felles læring i klassen.',
  },
] as const
</script>

<template>
  <main
    class="min-h-screen bg-[linear-gradient(180deg,#f7ede0_0%,#fff8f1_40%,#fffdf8_100%)] px-4 py-4 font-['Lato',sans-serif] text-[#222228] sm:px-6 lg:px-10"
  >
    <section class="mx-auto w-full max-w-7xl py-4 sm:py-6">
      <div class="flex flex-wrap items-center justify-between gap-4 border-b border-black/8 pb-4">
        <div>
          <p class="text-[0.7rem] font-black tracking-[0.18em] text-[#4158a6] uppercase">
            Nettdetektivene
          </p>
          <p class="mt-1 text-sm text-[#5f606b]">Læringsspill for nettvett og digital dømmekraft</p>
        </div>
        <div class="flex items-center gap-3">
          <a
            href="#slik-fungerer"
            class="rounded-full border border-black/10 px-4 py-2 text-sm font-black uppercase tracking-[0.08em] transition hover:bg-white"
          >
            Slik fungerer det
          </a>
          <RouterLink
            to="/login"
            class="rounded-full bg-[#222228] px-5 py-2 text-sm font-black uppercase tracking-[0.08em] text-white transition hover:bg-black"
          >
            Logg inn
          </RouterLink>
        </div>
      </div>
    </section>

    <section class="mx-auto grid w-full max-w-7xl gap-10 pb-18 pt-6 xl:grid-cols-[minmax(0,1fr)_minmax(420px,0.9fr)]">
      <div class="relative">
        <div class="relative z-10">
          <h1
            class="mt-5 font-['Montserrat_Alternates',sans-serif] text-[clamp(1rem,4.5vw,8rem)] leading-[0.86] font-extrabold tracking-[-0.07em]"
          >
            Nettdetektivene
          </h1>
          <p class="mt-8 max-w-2xl text-[1.08rem] leading-[1.8] text-[#4f525d] sm:text-[1.25rem]">
            Et læringsspill som gjør nettvett, kildekritikk og refleksjon til noe elevene
            faktisk vil være med på. Tanken er enkel: mindre brosjyre, mer oppdrag.
          </p>

          <div class="mt-10 flex flex-col gap-4 sm:flex-row sm:flex-wrap sm:items-center">
            <RouterLink
              to="/login"
              class="inline-flex min-h-16 items-center justify-center rounded-full bg-[#ff8343] px-8 py-4 text-base font-black uppercase tracking-[0.08em] text-white shadow-[0_18px_36px_rgba(255,131,67,0.28)] transition hover:-translate-y-0.5 hover:bg-[#f47a39]"
            >
              Start!
            </RouterLink>
            <a
              href="#slik-fungerer"
              class="inline-flex min-h-16 items-center justify-center rounded-full border border-black/10 bg-white px-8 py-4 text-base font-black uppercase tracking-[0.08em] text-[#222228] transition hover:-translate-y-0.5"
            >
              Hvordan funker det?
            </a>
          </div>


        </div>
      </div>

      <BiggurReveal />
    </section>

    <section class="mx-auto grid w-full max-w-7xl gap-6 py-8 lg:grid-cols-[minmax(280px,0.7fr)_minmax(0,1.3fr)]">
      <div class="rounded-[2rem] bg-[#222228] px-6 py-8 text-white sm:px-8 sm:py-10">
        <p class="text-[0.78rem] font-black tracking-[0.16em] text-[#f1dec6] uppercase">
          Why It Hits
        </p>
        <h2
          class="mt-5 font-['Montserrat_Alternates',sans-serif] text-[clamp(2.4rem,6vw,5rem)] leading-[0.92] font-extrabold tracking-[-0.06em]"
        >
          Spill først.
          <span class="block text-[#ff8343]">Læring rett etter.</span>
        </h2>
        <p class="mt-6 max-w-md text-base leading-[1.8] text-white/72">
          Inspirert av den mer editoriale følelsen fra `landonorris.com`: stor typografi,
          tydelige blokker og én sterk visuell helt i stedet for mange små elementer.
        </p>
      </div>

      <div class="grid gap-5 md:grid-cols-3">
        <article
          v-for="item in highlights"
          :key="item.title"
          class="flex h-full flex-col rounded-[1.8rem] border border-black/8 bg-white px-6 py-6 shadow-[0_14px_36px_rgba(34,34,40,0.05)]"
        >
          <p class="text-[0.72rem] font-black tracking-[0.16em] text-[#4158a6] uppercase">
            {{ item.label }}
          </p>
          <h3
            class="mt-4 font-['Montserrat_Alternates',sans-serif] text-[1.55rem] leading-[1.06] font-extrabold"
          >
            {{ item.title }}
          </h3>
          <p class="mt-4 text-base leading-[1.75] text-[#5f606b]">
            {{ item.description }}
          </p>
        </article>
      </div>
    </section>

    <section class="mx-auto w-full max-w-7xl py-8">
      <div class="mb-6 flex flex-col gap-3 lg:flex-row lg:items-end lg:justify-between">
        <div class="max-w-3xl">
          <p class="text-[0.78rem] font-black tracking-[0.16em] text-[#179bae] uppercase">
            One Product, Two Modes
          </p>
          <h2
            class="mt-3 font-['Montserrat_Alternates',sans-serif] text-[clamp(2rem,4vw,4rem)] leading-[0.96] font-extrabold tracking-[-0.05em]"
          >
            Samme opplegg.
            <span class="block text-[#4158a6]">Ulike innganger.</span>
          </h2>
        </div>
        <p class="max-w-xl text-base leading-[1.8] text-[#5f606b]">
          Elever får oppdrag og driv. Lærere får struktur, enkel start og et godt grunnlag
          for å snakke om valg, kilder og digitale spor.
        </p>
      </div>

      <div class="grid gap-5 lg:grid-cols-2">
        <article
          v-for="audience in audiences"
          :key="audience.title"
          :class="[
            'rounded-[2rem] px-7 py-8 shadow-[0_14px_36px_rgba(34,34,40,0.05)]',
            audience.palette,
          ]"
        >
          <p class="text-[0.72rem] font-black tracking-[0.16em] uppercase" :class="audience.chip">
            {{ audience.title }}
          </p>
          <p class="mt-4 text-[1.16rem] leading-[1.8]">
            {{ audience.text }}
          </p>
          <p class="mt-6 text-sm font-bold leading-6 opacity-80">
            {{ audience.accent }}
          </p>
        </article>
      </div>
    </section>

    <section id="slik-fungerer" class="mx-auto grid w-full max-w-7xl gap-8 py-8 lg:grid-cols-[minmax(260px,0.75fr)_minmax(0,1.25fr)]">
      <div>
        <p class="text-[0.78rem] font-black tracking-[0.16em] text-[#ff8343] uppercase">
          Slik fungerer det
        </p>
        <h2
          class="mt-4 font-['Montserrat_Alternates',sans-serif] text-[clamp(2.2rem,5vw,4.8rem)] leading-[0.92] font-extrabold tracking-[-0.06em]"
        >
          Fire steg.
          <span class="block text-[#222228]/45">Ingen friksjon.</span>
        </h2>
      </div>

      <div class="divide-y divide-black/8 rounded-[2rem] border border-black/8 bg-white shadow-[0_14px_36px_rgba(34,34,40,0.05)]">
        <article
          v-for="step in steps"
          :key="step.number"
          class="grid gap-3 px-6 py-6 sm:grid-cols-[88px_minmax(0,1fr)] sm:px-8"
        >
          <p class="text-[1.55rem] font-black tracking-[-0.04em] text-[#4158a6] sm:text-[1.8rem]">
            {{ step.number }}
          </p>
          <div>
            <h3
              class="font-['Montserrat_Alternates',sans-serif] text-[1.35rem] leading-[1.1] font-extrabold"
            >
              {{ step.title }}
            </h3>
            <p class="mt-3 max-w-2xl text-base leading-[1.75] text-[#5f606b]">
              {{ step.description }}
            </p>
          </div>
        </article>
      </div>
    </section>

    <section
      class="mx-auto mt-8 flex w-full max-w-7xl flex-col gap-8 rounded-[2.2rem] bg-[linear-gradient(135deg,#ff8343_0%,#ff9c54_48%,#4158a6_100%)] px-6 py-8 text-white shadow-[0_26px_56px_rgba(65,88,166,0.18)] sm:px-8 sm:py-10 lg:flex-row lg:items-end lg:justify-between"
    >
      <div class="max-w-3xl">
        <p class="text-[0.78rem] font-black tracking-[0.16em] text-white/75 uppercase">
          Start The Case
        </p>
        <h2
          class="mt-4 font-['Montserrat_Alternates',sans-serif] text-[clamp(2.2rem,5vw,4.8rem)] leading-[0.92] font-extrabold tracking-[-0.06em]"
        >
          Gjør forsiden like leken
          <span class="block">som spillet den leder til.</span>
        </h2>
      </div>

      <div class="flex flex-col gap-3 sm:flex-row">
        <RouterLink
          to="/login"
          class="inline-flex min-h-16 items-center justify-center rounded-full bg-white px-8 py-4 text-base font-black uppercase tracking-[0.08em] text-[#222228] transition hover:-translate-y-0.5"
        >
          Logg inn med Feide
        </RouterLink>
      </div>
    </section>
  </main>
</template>
