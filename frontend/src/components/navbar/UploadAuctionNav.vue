<template>
  <v-btn @click="uploadAuctionBtnClicked" dark flat>skapa auktion</v-btn>
</template>

<script>
import { eventBus } from "@/main";

export default {
  name: "UploadAuctionNav.vue",
  methods: {
    uploadAuctionBtnClicked() {
      if (this.$route.path === "/user-auctions") {
        this.$router.push({
          name: "upload"
        });
      } else {
        eventBus.$emit("uploadAuctionClicked");
      }
    }
  }
};
</script>

<style scoped>
  button{
    width: 65%;
  }
</style>
