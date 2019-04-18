<template>
   <v-container>

   <h4>Lägg upp auktion</h4>
   <v-btn @click="uploadAuctionClicked" dark fab medium color="teal" id="submitBtn" type="submit">
      <v-icon dark large>add</v-icon>
   </v-btn>
   </v-container>
</template>

<script>
   import { eventBus } from "@/main";

   export default {
      name: "UploadAuction.vue",
      methods: {
         uploadAuctionClicked() {
            eventBus.$emit('submitRegisterClicked')
         }
      }
   }
</script>

<style scoped>

</style>