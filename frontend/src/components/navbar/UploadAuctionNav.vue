<template>
   <v-container>
      <v-btn @click="uploadAuctionBtnClicked" dark flat>skapa auktion</v-btn>
   </v-container>
</template>

<script>
   import { eventBus } from "@/main";

   export default {
      name: "UploadAuctionNav.vue",
      methods: {
         uploadAuctionBtnClicked() {
            eventBus.$emit('uploadAuctionClicked')
         }
      }
   }
</script>

<style scoped>

</style>