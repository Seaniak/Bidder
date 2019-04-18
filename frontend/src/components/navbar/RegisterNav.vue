<template>
   <!-- :class="{ red: !valid, green: valid }" -->
   <v-btn @click="registerClicked" dark flat>bekräfta registrering</v-btn>
</template>

<script>
   import { eventBus } from "@/main";

   export default {
      name: "RegisterNav",
      data() {
         return {
         }
      },
      methods: {
         registerClicked() {
            eventBus.$emit('submitRegisterClicked')
         }
      }
   }
</script>

<style scoped>

</style>