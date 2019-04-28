<template>
  <v-card id="auction-card" :to="'/auction/' + auction.id" class="mb-3 pt-1">
    <v-img
      :src="auction.thumbnail ? auction.thumbnail : defaultThumbnail"
      aspect-ratio="2.75"
    ></v-img>
    <v-card-title class="align-items-start" primary-title>
      <div class="col-12">
        <h3 class="col-12 mb-0">{{ auction.title }}</h3>
      </div>
      <div class="col-6">
        <h5 class="col-12 mt-0">Bud</h5>
        <p>{{ maxBid }}</p>
      </div>
      <div class="col-6 mt-0">
        <h5 class="col-12">Tid kvar</h5>
        <AuctionTimeCountDown :auctionEndTime="auction.endTime" />
      </div>
    </v-card-title>
    <v-content>
      <place-bid :propAuction="auction"></place-bid>
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
  data() {
    return {
      maxBid: "Placeholder"
    };
  },
  methods: {
    // getDateString(auctionTimeStamp) {
    //   let bidDate = new Date(auctionTimeStamp);
    //   return bidDate.toLocaleDateString() + " " + bidDate.toLocaleTimeString();
    // }
  },
  computed: {
    defaultThumbnail() {
      return "https://cdn.starwebserver.se/shops/coolcard/files/cache/trainermix_grande.jpg?_=1475359673";
    }
  },
  props: ["auction", "auctionEndTime"]
  /*    async created() {
          this.maxBid = Math.max(...(await this.auction.bids.map((bid) => bid.sum)));
        }*/
};
</script>

<style scoped>
* {
  text-decoration: none;
  color: var(--main-font-color);
}

#auction-card {
  border-radius: 4px;
  animation-duration: 200ms;
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
