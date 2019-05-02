<template>
  <div>
    <transition-group
      class="container row"
      name="animate-route"
      mode="out-in"
      enter-active-class="animated fadeIn"
      leave-active-class="animated fadeOut"
    >
      <h1 class="col-12 mb-3" key="keyNeededToPreventError">Bidder</h1>
      <AuctionCard
        class="col-12 col-md-4"
        v-for="(auction, index) in auctions"
        :key="index"
        :auctionId="auction.id"
        :auction="auction"
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
      let auctions = this.$store.state.filteredItems
      let timeSorted = []
      for (let key in auctions) {
        timeSorted.push(auctions[key])
      }
      timeSorted.sort((a, b) => new Date(a.endTime) - new Date(b.endTime))

      return timeSorted;
    }
  }
};
</script>

<style scoped>
h3 {
  animation-duration: 100ms;
}
</style>
