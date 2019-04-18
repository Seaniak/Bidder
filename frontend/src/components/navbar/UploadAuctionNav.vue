<template>
   <v-container>
   <v-btn @click="uploadAuctionBtnClicked" dark fab medium color="teal" id="submitBtn" type="submit">
      <v-icon dark large>add</v-icon>
   </v-btn>
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