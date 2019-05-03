<template>
  <div>
    <transition-group
      class="container row"
      name="animate-route"
      mode="out-in"
      enter-active-class="animated fadeIn"
      leave-active-class="animated fadeOut"
    >
      <h1 id="logo" class="col-12 mb-3" key="keyNeededToPreventError">Bidder</h1>
      <AuctionCard
        class="col-12 col-md-4"
        v-for="(auction, index) in auctions"
        :key="auction.id"
        :compact="true"
        :auctionId="auction.id"
      />
    </transition-group>
  </div>
</template>

<script>
// @ is an alias to /src
import AuctionCard from "@/components/AuctionCard.vue";

export default {
  name: "home",
  components: {
    AuctionCard
  },
  computed: {
    auctions() {
      // sorts auctions to list descending time left
      let auctions = this.$store.state.filteredItems;
      let timeSorted = [];
      for (let key in auctions) {
        timeSorted.push(auctions[key]);
      }
      timeSorted.sort((a, b) => new Date(a.endTime) - new Date(b.endTime));

      return timeSorted;
    }
  }
};
</script>

<style scoped>
  @import url('https://fonts.googleapis.com/css?family=Pacifico');

  h3 {
  animation-duration: 100ms;
}
  #logo {
    font-family: 'Pacifico', cursive;
    font-size: 10vh;
    color: gold;
    text-shadow: 1px 1px 1px teal, 0 0 0.05em teal, 0 0 0.05em teal;
    margin-bottom: 0vh;
  }
</style>
