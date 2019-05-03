<template>
  <v-btn @click="registerClicked" dark flat>registrera</v-btn>
</template>

<script>
import { eventBus } from "@/main";

export default {
  name: "RegisterNav",
  data() {
    return {};
  },
  methods: {
    registerClicked() {
      eventBus.$emit("submitRegisterClicked");
    }
  }
};
</script>

<style scoped>
  button{
    width: 65%;
  }
</style>
