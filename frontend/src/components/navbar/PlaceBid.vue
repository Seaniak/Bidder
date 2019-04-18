<template>
      <v-btn @click="placeBidClicked" dark flat>Placera bud</v-btn>
</template>

<script>
   import {eventBus} from "@/main";

   export default {
      name: "PlaceBid.vue",
      methods: {
         placeBidClicked() {
            // eventBus.$emit('nav-placebid-clicked')
         }
      }
   }
</script>

<style scoped>

</style>