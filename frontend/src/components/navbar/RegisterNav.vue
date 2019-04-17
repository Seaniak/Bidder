<template>
   <v-btn @click="registerClicked" dark flat>Register</v-btn>
</template>

<script>
   import { eventBus } from "@/main";

   export default {
      name: "RegisterNav",
      methods: {
         registerClicked() {
            eventBus.$emit('submitRegisterClicked')
         }
      }
   }
</script>

<style scoped>

</style>