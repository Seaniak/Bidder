<template>
  <v-container>
    <template>
      <!-- :class="{ red: !valid, green: valid }" -->
      <v-btn @click="goToLoginPage" dark flat>gå till inloggning</v-btn>
    </template>
  </v-container>
</template>

<script>
export default {
  name: "GoToLoginPageNav.vue",
  methods: {
    goToLoginPage() {
      this.$router.push({ name: "login" });
    }
  }
};
</script>

<style scoped></style>
