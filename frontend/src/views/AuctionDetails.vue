<template>
  <v-container>
    <figure>
      <img width="100%" :src="auction.imageData" alt="thumbnail" />
    </figure>
    <h1>{{ auction.title }}</h1>
    <h1>{{ auction.title }}</h1>

    <p>{{ auction.body }}</p>
    <!--<v-btn
            v-if="$store.state.loggedIn"
            fab
            dark
            color="teal"
            fixed
            medium
            @click="updateAuction">
      <v-icon dark>create</v-icon>
    </v-btn>-->
  </v-container>
</template>

<script>
export default {
  name: "AuctionDetails",
  props: ["auction"],
  methods: {
    updateAuction() {
      this.$router.push({
        name: "upload",
        params: { auction: this.auction }
      });
    }
  }
};
</script>

<style scoped>
button {
  position: fixed;
  bottom: 70px;
  right: 20px;
}
</style>
