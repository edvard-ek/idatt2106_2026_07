<template>
  <div style="padding: 2rem; text-align: center;">
    <h1>Dummy View</h1>
    <div style="margin-top: 2rem; display: flex; gap: 1rem; justify-content: center;">
      <button @click="goToDashboard" style="padding: 0.5rem 1rem; font-size: 1rem;">
        Dashboard
      </button>
      <button @click="goToMain" style="padding: 0.5rem 1rem; font-size: 1rem;">
        Main Screen
      </button>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  methods: {
    goToDashboard() {
      this.$router.push('/dashboard');
    },
    goToMain() {
      this.$router.push('/game');
    }
  }
};
</script>
