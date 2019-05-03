<template>
  <div>
    <h2 class="pt-5 pb-3">{{seller}}s andra auktioner</h2>
    <AuctionCard
            class="col-12 col-md-4"
            v-for="(auction, index) in activeAuctions"
            :key="index + auction.title"
            :name="index"
            :compact="false"
            :auction="auction"
            :auctionId="auction.id"
    />
  </div>
</template>

<script>
  import AuctionCard from "@/components/AuctionCard.vue";

  export default {
    name: "SellerActiveAuctions",
    components: {
      AuctionCard
    },
    props: ['seller', 'auctionId'],
    data() {
      return {
        userAuctions: []
      };
    },
    computed: {
      activeAuctions() {
        // sorts auctions to list descending time left
        let auctions = this.userAuctions
        auctions = auctions.filter(a => new Date(a.endTime) > new Date())

        auctions.sort((a, b) => new Date(a.endTime) - new Date(b.endTime))
        return auctions;
      }
    },
    async created() {
      let response = await fetch(
          "/api/auctions/user=" + this.seller
      );
      response = await response.json();
      this.userAuctions = [];
      for (let auction of response) {
        if (this.$store.getters.getAuction(auction.id) === undefined) {
          this.$store.commit("addAuction", auction);
        }
        if(auction.id !== this.auctionId)
        this.userAuctions.push(this.$store.getters.getAuction(auction.id));
      }
    }
  };
</script>

<style scoped></style>
