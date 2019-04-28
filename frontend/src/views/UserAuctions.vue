<template>
  <v-container>
    <h1>Mina auktioner</h1>
    <AuctionCard
      class="col-12 col-md-4"
      v-for="(auction, index) in userAuctions"
      :key="index + 0"
      :name="index"
      :auction="auction"
    />
  </v-container>
</template>

<script>
import AuctionCard from "@/components/AuctionCard.vue";

export default {
  name: "UserAuctions",
  components: {
    AuctionCard
  },
  data() {
    return {
      userAuctions: []
    };
  },
  async created() {
    let response = await fetch(
      "/api/auctions/user=" + this.$store.state.currentUser.username
    );
    response = await response.json();
    this.userAuctions = response;
  }
};
</script>

<style scoped></style>
