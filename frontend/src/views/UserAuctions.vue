<template>
  <v-container>
    <h1>Mina auktioner</h1>
    <h2 class="py-3">Aktiva</h2>
    <AuctionCard
            class="col-12 col-md-4"
            v-for="(auction, index) in activeAuctions"
            :key="index + auction.title"
            :name="index"
            :auction="auction"
            :auctionId="auction.id"
    />
    <h2 v-if="closedAuctions.length > 0" class="pt-5 pb-3">Avslutade</h2>
    <AuctionCard
            class="col-12 col-md-4"
            v-for="(auction, index) in closedAuctions"
            :key="auction.title + index"
            :name="index"
            :auction="auction"
            :auctionId="auction.id"
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
    computed: {
      activeAuctions() {
        // sorts auctions to list descending time left
        let auctions = this.userAuctions
        auctions = auctions.filter(a => new Date(a.endTime) > new Date())

        auctions.sort((a, b) => new Date(a.endTime) - new Date(b.endTime))
        return auctions;
      },
      closedAuctions() {
        // sorts auctions to list descending time left
        let auctions = this.userAuctions
        auctions = auctions.filter(a => new Date(a.endTime) < new Date())

        auctions.sort((a, b) => new Date(b.endTime) - new Date(a.endTime))
        return auctions;
      }
    },
    async created() {
      let response = await fetch(
          "/api/auctions/user=" + window.socketUsername
      );
      response = await response.json();
      this.userAuctions = response;
    }
  };
</script>

<style scoped></style>
