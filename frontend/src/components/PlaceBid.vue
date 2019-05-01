<template class="template">
  <v-layout row wrap class="bidBar justify-center">
      <scroll-picker class="scroller col-6"
                     :options="possibleBids"
                     v-model="chosenBid">
      </scroll-picker>
    <div class="text-xs-center">
      <v-bottom-sheet v-model="sheet">
        <template v-slot:activator>
          <v-btn color="purple" dark class="firstBidBtn" >
            Lägg Bud
          </v-btn>
        </template>
        <v-card tile>
          <v-layout v-if="$store.state.currentUser" column>
            <h3>Bud: {{ chosenBid }}kr</h3>
            <v-btn @click="placeBidClicked">Lägg bud</v-btn>
          </v-layout>
          <v-layout v-else column>
            <h2>Logga in för att lägga bud!</h2>
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
      if (
        this.$store.state.currentUser === undefined ||
		    this.$store.getters.getAuction(this.bidId).username ===
          this.$store.state.currentUser.username
      ) return;

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
		alert(await bid.json() ? "Bud registrerat!" : "Bud inte registrerat, försök igen!")
    }
  },
  computed: {
    possibleBids() {
      return this.newBids(this.$store.getters.getAuction(this.bidId).maxBid);
    }
  },
  created() {
	  if (this.auctionId == null) this.bidId = this.$route.params.id;
  }
};
</script>

<style scoped>
  .firstBidBtn {
    margin-top: 2.2vh;
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
    height: 3.4vh;
  }
  .vue-scroll-picker >>> .vue-scroll-picker-layer .bottom {
    background: linear-gradient(0deg, var(--scrollColor) 20%, rgba(0,0,0, 0.01));
    height: 3.7vh;
  }
</style>
