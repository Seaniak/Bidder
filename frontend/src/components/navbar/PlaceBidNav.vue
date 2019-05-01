<template>
  <PlaceBid class="placeBidNav" :auctionId="auctionId"></PlaceBid>
</template>

<script>
import PlaceBid from "../PlaceBid";

export default {
  name: "PlaceBidNav",
  components: {
    PlaceBid
  },
  data() {
    return {
      auctionId: this.$route.params.id
    };
  }
};
</script>

<style scoped>
</style>
