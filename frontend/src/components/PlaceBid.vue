<template class="template">
  <v-layout row wrap class="bidBar justify-center">
      <scroll-picker class="scroller col-6"
                     :touch-sensitivity="0.9"
                     :class="getPath ? 'scrollTextWhite':'scrollTextBlack'"
                     :options="possibleBids"
                     v-model="chosenBid">
      </scroll-picker>
    <div class="text-xs-center">
      <v-bottom-sheet v-model="sheet">
        <template v-slot:activator>
          <v-btn flat dark class="firstBidBtn btn" :class="getPath ? 'auctionDetails':'auctionCard'" >
            Lägg Bud
          </v-btn>
        </template>
        <v-card tile id="bottomSheet">
          <v-layout v-if="$store.state.currentUser" column align-center justify-center fill-height>
            <h2>Nytt bud på '{{$store.getters.getAuction(this.bidId).title}}'</h2>
            <h3>Nuvarande bud: {{ $store.getters.getAuction(this.bidId).maxBid }} kr</h3>
            <h3>Ditt bud: {{ chosenBid }} kr</h3>
            <v-btn @click="placeBidClicked" class="teal btn" dark>Lägg bud</v-btn>
          </v-layout>
          <v-layout v-else column align-center justify-center fill-height>
            <h2>Du måste vara inloggad för att lägga bud!</h2>
            <v-btn @click="$router.push('/login')" color="teal" dark class="pb-10">Gå till inloggning</v-btn>
          </v-layout>
        </v-card>
      </v-bottom-sheet>
    </div>
  </v-layout>
</template>

<script>
import "vue-scroll-picker/dist/style.css"
import { ScrollPicker } from "vue-scroll-picker"
export default {
  name: "PlaceBid",
  props: ["auctionId"],
  components: {
	  ScrollPicker,
  },
  data() {
    return {
    	bidId: this.auctionId,
      chosenBid: 0,
      sheet: false
    };
  },
  methods: {
    getAddSum(currentBid) {
      let addSum = 0;
      if (currentBid < 200) addSum = 10;
      else if (currentBid < 500) addSum = 20;
      else if (currentBid < 1000) addSum = 50;
      else if (currentBid < 10000) addSum = 100;
      else if (currentBid < 20000) addSum = 250;
      else if (currentBid < 40000) addSum = 500;
      else if (currentBid < 200000) addSum = 1000;
      else if (currentBid < 500000) addSum = 2500;
      else if (currentBid < 1000000) addSum = 5000;
      else if (currentBid < 2000000) addSum = 10000;
      else if (currentBid < 4000000) addSum = 25000;
      else if (currentBid < 10000000) addSum = 50000;
      else if (currentBid >= 20000000) addSum = 250000;
      return addSum;
    },
    newBids(currentBid) {
      let bids = [];
      let addSum = this.getAddSum(currentBid);
      let possibleBid = currentBid + addSum;
      while (bids.length < 20) {
        bids.push({value: possibleBid, name: possibleBid + " kr"});
        possibleBid += addSum;
      }
      this.chosenBid = bids[2].value;
      return bids;
    },
    async placeBidClicked() {
      console.log("TEST", new Date(this.$store.getters.getAuction(this.bidId).endTime)-Date.now());
    if(        new Date(this.$store.getters.getAuction(this.bidId).endTime)-Date.now() < 0) console.log("TIMEOUT");
      if (
        this.$store.state.currentUser === undefined ||
		    this.$store.getters.getAuction(this.bidId).username ===
          this.$store.state.currentUser.username ||
        this.$store.getters.getAuction(this.bidId).endDate-Date.now() < 0
      ) return;
      // let timeDifference = this.endDate - Date.now();
      // timeDifference = Math.round(timeDifference);
      let data = {
        auctionId: this.bidId,
        username: this.$store.state.currentUser.username,
        sum: this.chosenBid
      };

      let bid = await fetch("/api/bids", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
      });
		this.sheet = false;
    }
  },
  computed: {
    possibleBids() {
      return this.newBids(this.$store.getters.getAuction(this.bidId).maxBid);
    },
    getPath(){
      return this.$route.path.match(/\/[a-z]+/)
    }
  },
  created() {
	  if (this.auctionId == null) this.bidId = this.$route.params.id;
  }
};
</script>

<style scoped>
  .auctionCard{
    background-color: teal;
    }

  auctioDetails{
  }
  .scrollTextWhite{
    color: white;
  }
  .scrollTextBlack{
    color: black;
  }
  #bottomSheet {
    height: 20vh;
    background: var(--main-background);
  }
  .firstBidBtn {
    margin-top: 2.3vh;
    /*font-weight: bolder;*/
  }
  .btn {
    font-weight: bolder;
  }
  .bidBar {
    height: 10vh;
  }
  .bidBar >>> .vue-scroll-picker {
    height: 100%;
  }
  .bidBar >>> .vue-scroll-picker-list-rotator .-selected {
    font-weight: bold;
    font-size: larger;
  }
  .vue-scroll-picker >>> .vue-scroll-picker-layer .top {
    background: linear-gradient(180deg, var(--scrollColor) 10%, rgba(0,0,0, 0.01));
    height: 3.3vh;
  }
  .vue-scroll-picker >>> .vue-scroll-picker-layer .bottom {
    background: linear-gradient(0deg, var(--scrollColor) 10%, rgba(0,0,0, 0.01));
    height: 3.4vh;
  }
</style>
