<script setup lang="ts">
import { onBeforeUnmount, onMounted, ref } from 'vue'

import feideLogoUrl from '@/assets/Feide_idY2p4BuwR_1.svg'
import norwayFlagUrl from '@/assets/norway.svg'

const schools = ['NTNU', 'Universitetet i Oslo', 'OsloMet', 'BI'] as const

const username = ref('eksempelbruker')
const password = ref('passordpassord')
const selectedSchool = ref<(typeof schools)[number]>('NTNU')
const isSchoolModalOpen = ref(false)

function handleSubmit() {}

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
  <main class="login-page">
    <section class="login-layout">
      <article class="login-card">
        <header class="card-header">
          <button class="language-toggle" type="button">
            <img :src="norwayFlagUrl" alt="" class="language-flag" />
            <span>Bokmål</span>
            <svg class="language-chevron" viewBox="0 0 24 24" aria-hidden="true">
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

        <div class="card-body">
          <h1>Logg inn med Feide</h1>

          <section class="intro-section">
            <img :src="feideLogoUrl" alt="Feide" class="feide-logo" />
            <p>
              Du må logge deg på via Feide for å få tilgang til
              Administrasjonsgrensesnitt for www.feide.no.
            </p>
          </section>

          <div class="divider"></div>

          <section class="organization-section">
            <h2>Din tilhørighet</h2>
            <div class="organization-card">
              <div class="organization-badge" aria-hidden="true">
                <span>{{ selectedSchool.slice(0, 2).toUpperCase() }}</span>
              </div>
              <div class="organization-copy">
                <p class="organization-name">{{ selectedSchool }}</p>
                <button class="organization-link" type="button" @click="openSchoolModal">
                  Endre tilhørighet
                </button>
              </div>
            </div>
          </section>

          <div class="divider"></div>

          <form class="login-form" @submit.prevent="handleSubmit">
            <label class="field">
              <span>Brukernavn</span>
              <input v-model="username" autocomplete="username" type="text" />
            </label>

            <label class="field">
              <span>Passord</span>
              <input v-model="password" autocomplete="current-password" type="password" />
            </label>

            <a class="recovery-link" href="/" @click.prevent>Glemt brukernavn eller passord?</a>

            <button class="submit-button" type="submit">Logg inn</button>
          </form>

          <div class="divider divider-tight"></div>

          <button class="help-row" type="button">
            <span>Trenger du hjelp?</span>
            <span class="plus" aria-hidden="true"></span>
          </button>

          <div class="divider divider-subtle"></div>

          <a class="privacy-link" href="/" @click.prevent>Personvern og informasjonskapsler</a>
        </div>
      </article>

      <footer class="login-footer">
        <p>
          Feide leveres av <a href="/" @click.prevent>Uninett</a>
        </p>
      </footer>
    </section>

    <div
      v-if="isSchoolModalOpen"
      class="modal-backdrop"
      aria-hidden="true"
      @click.self="closeSchoolModal"
    >
      <section
        class="school-modal"
        aria-labelledby="school-modal-title"
        aria-modal="true"
        role="dialog"
      >
        <div class="school-modal-header">
          <div>
            <p class="modal-kicker">Velg skole</p>
            <h3 id="school-modal-title">Tilhørighet</h3>
          </div>
          <button class="close-button" type="button" aria-label="Lukk" @click="closeSchoolModal">
            <svg viewBox="0 0 24 24" aria-hidden="true">
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

        <div class="school-list" role="list">
          <button
            v-for="school in schools"
            :key="school"
            class="school-option"
            :class="{ 'school-option-active': school === selectedSchool }"
            type="button"
            @click="selectSchool(school)"
          >
            <span>{{ school }}</span>
            <span v-if="school === selectedSchool" class="school-check">Valgt</span>
          </button>
        </div>
      </section>
    </div>
  </main>
</template>

<style scoped>
.login-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 32px 16px;
  background:
    linear-gradient(180deg, #e5edf6 0%, #dce7f3 100%);
  color: #12131a;
}

.login-layout {
  width: 100%;
  max-width: 560px;
}

.login-card {
  overflow: hidden;
  border: 1px solid #d4dfec;
  border-radius: 24px;
  background: #ffffff;
  box-shadow: 0 24px 60px rgb(34 53 91 / 0.12);
}

.card-header {
  display: flex;
  justify-content: flex-end;
  padding: 18px 20px 0;
}

.language-toggle {
  display: inline-flex;
  align-items: center;
  gap: 10px;
  border: 0;
  background: transparent;
  color: #39404f;
  font-size: 0.98rem;
  font-weight: 500;
  cursor: default;
}

.language-flag {
  width: 22px;
  height: 16px;
  border-radius: 2px;
  box-shadow: 0 0 0 1px rgb(0 0 0 / 0.05);
}

.language-chevron {
  width: 18px;
  height: 18px;
  color: #31449f;
}

.card-body {
  padding: 18px 28px 28px;
}

h1 {
  margin: 0 0 20px;
  color: #2f3138;
  font-size: clamp(2.15rem, 5vw, 3.2rem);
  font-weight: 800;
  line-height: 1;
  letter-spacing: -0.05em;
}

.intro-section {
  display: grid;
  gap: 14px;
}

.feide-logo {
  width: 112px;
  height: auto;
}

.intro-section p {
  margin: 0;
  color: #15171e;
  font-size: 1.05rem;
  line-height: 1.35;
}

.divider {
  margin: 22px 0 18px;
  border-top: 3px solid #d9e5f1;
}

.divider-tight {
  margin-top: 18px;
  margin-bottom: 0;
}

.divider-subtle {
  margin-top: 0;
  margin-bottom: 18px;
}

.organization-section h2 {
  margin: 0 0 14px;
  font-size: 1.1rem;
  font-weight: 700;
}

.organization-card {
  display: flex;
  align-items: center;
  gap: 14px;
}

.organization-badge {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 52px;
  height: 52px;
  border-radius: 16px;
  background: linear-gradient(135deg, #dce9fb 0%, #9ec2f3 100%);
  color: #214f9f;
  font-size: 0.95rem;
  font-weight: 800;
  letter-spacing: 0.04em;
}

.organization-copy {
  display: grid;
  gap: 4px;
}

.organization-name {
  margin: 0;
  font-size: 1.28rem;
  font-weight: 600;
  line-height: 1.2;
}

.organization-link,
.recovery-link,
.privacy-link,
.login-footer a {
  color: #15171e;
  text-decoration: underline;
  text-decoration-thickness: 1.5px;
  text-underline-offset: 3px;
}

.organization-link {
  width: fit-content;
  padding: 0;
  border: 0;
  background: transparent;
  font-size: 0.98rem;
  cursor: pointer;
}

.login-form {
  display: grid;
  gap: 18px;
}

.field {
  display: grid;
  gap: 8px;
}

.field span {
  font-size: 1rem;
  font-weight: 700;
}

.field input {
  width: 100%;
  height: 72px;
  border: 2px solid #7b88bd;
  border-radius: 0;
  background: #ffffff;
  padding: 0 18px;
  color: #111111;
  font-size: 1.15rem;
  font-weight: 600;
  outline: none;
  transition: border-color 0.15s ease;
}

.field input:focus {
  border-color: #4f5ea8;
}

.recovery-link {
  width: fit-content;
  font-size: 0.98rem;
}

.submit-button {
  height: 74px;
  border: 0;
  background: #99bff1;
  color: #111111;
  font-size: 1.2rem;
  font-weight: 500;
  letter-spacing: -0.02em;
}

.help-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  padding: 16px 0;
  border: 0;
  background: transparent;
  color: #111111;
  font-size: 1.08rem;
  font-weight: 500;
  text-align: left;
}

.plus {
  position: relative;
  display: inline-block;
  width: 24px;
  height: 24px;
}

.plus::before,
.plus::after {
  content: '';
  position: absolute;
  top: 50%;
  left: 50%;
  background: #31449f;
  transform: translate(-50%, -50%);
  border-radius: 999px;
}

.plus::before {
  width: 3px;
  height: 24px;
}

.plus::after {
  width: 24px;
  height: 3px;
}

.privacy-link {
  display: inline-block;
  font-size: 0.98rem;
}

.login-footer {
  padding: 18px 8px 0;
  text-align: center;
  color: #44506b;
}

.login-footer p {
  margin: 0;
  font-size: 1rem;
}

.modal-backdrop {
  position: fixed;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
  background: rgb(16 24 40 / 0.42);
}

.school-modal {
  width: min(100%, 420px);
  border-radius: 22px;
  background: #ffffff;
  box-shadow: 0 24px 60px rgb(15 23 42 / 0.28);
  padding: 22px;
}

.school-modal-header {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 16px;
  margin-bottom: 18px;
}

.modal-kicker {
  margin: 0 0 4px;
  color: #51607b;
  font-size: 0.82rem;
  font-weight: 700;
  letter-spacing: 0.08em;
  text-transform: uppercase;
}

.school-modal h3 {
  margin: 0;
  color: #1b2130;
  font-size: 1.45rem;
  font-weight: 700;
}

.close-button {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 40px;
  height: 40px;
  border: 0;
  border-radius: 999px;
  background: #eef4fb;
  color: #31449f;
}

.close-button svg {
  width: 20px;
  height: 20px;
}

.school-list {
  display: grid;
  gap: 10px;
}

.school-option {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  width: 100%;
  padding: 14px 16px;
  border: 1px solid #d7e0ec;
  border-radius: 16px;
  background: #ffffff;
  color: #1a2233;
  font-size: 1rem;
  font-weight: 600;
  text-align: left;
}

.school-option-active {
  border-color: #84a6df;
  background: #eef4fe;
}

.school-check {
  color: #2a59b1;
  font-size: 0.86rem;
  font-weight: 700;
}

@media (max-width: 640px) {
  .login-page {
    padding: 18px 12px;
    align-items: flex-start;
  }

  .login-card {
    border-radius: 20px;
  }

  .card-header {
    padding: 16px 16px 0;
  }

  .card-body {
    padding: 16px 16px 22px;
  }

  .field input,
  .submit-button {
    height: 64px;
  }
}
</style>
