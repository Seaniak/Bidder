<template>
  <v-btn @click="loginClicked" dark flat>Login</v-btn>
</template>

<script>
  import {eventBus} from "@/main";

  export default {
    name: "LoginNav",
    methods: {
      loginClicked() {
        eventBus.$emit('nav-login-clicked')
      }
    }
  }
</script>

<style scoped></style>
