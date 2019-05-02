<template>
  <v-card v-if="updateAuction !== undefined" id="auction-card" class="mb-3 p-1"
          :class="{closedAuction}">
    <div id="cardInfo" @click="$router.push('/auction/' + auctionId)">
      <v-img
          :src="updateAuction.thumbnail ? updateAuction.thumbnail : defaultThumbnail"
          aspect-ratio="2.75"
      ></v-img>
      <v-card-title class="align-items-start pt-2 p-0" primary-title>
        <div class="col-12">
          <h2 class="col-12 mb-0">{{ updateAuction.title }}</h2>
        </div>
        <div class="col-6">
          <h3 class="col-12 mt-0">Bud</h3>
          <h4>{{ updateAuction.maxBid }} kr</h4>
        </div>
        <div v-if="updateAuction !== undefined" class="col-6 mt-0">
          <h3 class="col-12">Avslutas</h3>
          <AuctionTimeCountDown :auctionEndTime="updateAuction.endTime" />
        </div>
      </v-card-title>
    </div>
    <v-content v-if="!getRoute" id="bidder">
      <place-bid :auctionId="auctionId"></place-bid>
    </v-content>
  </v-card>
</template>

<script>
import AuctionTimeCountDown from "../components/AuctionTimeCountDown";
import PlaceBid from "../components/PlaceBid";

export default {
  name: "AuctionCard",
  components: {
    AuctionTimeCountDown,
    PlaceBid
  },
	props: ["auctionId"],
	data() {
    return {
    };
  },
  methods: {
  },
  computed: {
    closedAuction() {
      return new Date(this.$store.getters.getAuction(this.auctionId).endTime) < new Date();
    },
    defaultThumbnail() {
		  return "https://cdn.starwebserver.se/shops/coolcard/files/cache/trainermix_grande.jpg?_=1475359673";
	  },
	  updateAuction() {
		  return this.$store.getters.getAuction(this.auctionId);
	  },
    getRoute(){
      return this.$route.path === '/user-auctions'
    }
  },
};
</script>
<style>
  #auction-card {
    --scrollColor: rgb(255,255,255);
  }
</style>
<style scoped>
* {
  text-decoration: none;
  color: var(--main-font-color);
}
.closedAuction{
  opacity: 0.6;
}
#bidder {
  margin-bottom: 2vh;
}

#auction-card {
  border-radius: 4px;
  animation-duration: 200ms;

}

#auction-card >>> bidBar {
}
#auction {
  display: flex;
  text-decoration: none;
  color: var(--main-font-color);
  width: 100%;
  border-bottom: solid 1px #7c7e70;
}

#auction-text {
  display: flex;
  flex-direction: row;
  padding-left: 3%;
  text-align: start;
  flex: 4;
}

figure {
  flex: 2;
}

img {
  width: 100%;
}
</style>
