<template>
  <!--  <v-btn @click="placeBidClicked" dark flat>Placera bud</v-btn>-->
  <v-layout row wrap class="bidBar">
    <smooth-picker
      class="scroller col-6"
      ref="smoothPicker"
      :data="possibleBids"
      :change="dataChange"
    />
    <div class="text-xs-center">
      <v-bottom-sheet v-model="sheet">
        <template v-slot:activator>
          <v-btn color="purple" dark>
            Lägg Bud
          </v-btn>
        </template>
        <v-card tile>
          <v-layout v-if="$store.state.currentUser" column>
            <h3>Bud: {{ this.chosenBid }}kr</h3>
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
//import { eventBus } from "@/main";
import "vue-smooth-picker/dist/css/style.css";
import { SmoothPicker } from "vue-smooth-picker";

export default {
  name: "PlaceBid",
  props: ["auctionId"],
  components: {
    SmoothPicker
  },
  data() {
    return {
    	bidId: this.auctionId,
      bidAuction: null,
      chosenBid: 0,
      bids: [],
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
      let bidStrings = [];
      let addSum = this.getAddSum(currentBid);
      let possibleBid = currentBid + addSum;
      while (bidStrings.length < 20) {
        bids.push(possibleBid);
        bidStrings.push(possibleBid + " kr");
        possibleBid += addSum;
      }
      this.bids = bids;
      this.chosenBid = this.bids[0];
      return bidStrings;
    },
    dataChange(gIndex, iIndex) {
      this.chosenBid = this.bids[iIndex - 1];
    },
    async placeBidClicked() {
      if (
        this.$store.state.currentUser === undefined ||
        this.$store.state.activeAuction.username ===
          this.$store.state.currentUser.username
      )
        return;
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
      console.log(await bid.json());
      this.sheet = false;
    }
  },
  computed: {
    possibleBids() {
      // if (this.bidAuction == null) this.bidAuction = this.$store.state.activeAuction;
      // this.bidAuction.maxBid = (this.bidAuction.bids.length === 0) ?
      //     this.bidAuction.startSum
      //     : Math.max(...(this.bidAuction.bids.map(bid => bid.sum)));
        let maxBid = (this.$store.state.auctionMap.get(this.bidId) === undefined) ?
            0
            : ((this.$store.state.auctionMap.get(this.bidId).bids.length === 0) ?
				      this.$store.state.auctionMap.get(this.bidId).startSum
                : Math.max(...(this.$store.state.auctionMap.get(this.bidId).bids.map(bid => bid.sum))));
      return [
        {
          currentIndex: 1,
          flex: 6,
          list: this.newBids(maxBid),
          textAlign: "center",
          className: "row-group"
        }
      ];
    }
  },
  created() {
    // if (this.auctionId != null) this.bidAuction = this.propAuction;
	  // this.$store.state.auctionMap.get(this.$route.params.id
      console.log(this.auctionId);
	  if (this.auctionId == null) this.bidId = this.$route.params.id;

  }
};
</script>

<style scoped>
bidBar {
}

vars {
  --main-bg-color: inherit;
}

.smooth-picker[data-v-43f1648a] {
  /*font-size: 1rem;*/
  font-size: 1.2rem;
  /*height: 10em;*/
  height: 100%;
  position: relative;
  /*background-color: #fff;*/
  background-color: var(--main-bg-color);
  overflow: hidden;
}

.smooth-picker .smooth-list[data-v-43f1648a] {
  height: 6.25em;
  position: relative;
  top: 4em;
}

.smooth-picker .smooth-item[data-v-43f1648a] {
  position: absolute;
  top: 0;
  left: 0;
  overflow: hidden;
  width: 100%;
  text-overflow: ellipsis;
  white-space: nowrap;
  display: block;
  text-align: center;
  will-change: transform;
  contain: strict;
  height: 2em;
  line-height: 2;
  font-size: 1em;
}

.smooth-picker .smooth-handle-layer[data-v-43f1648a] {
  position: absolute;
  width: 100%;
  height: calc(100% + 2px);
  left: 0;
  right: 0;
  top: -1px;
  bottom: -1px;
}

.smooth-picker .smooth-handle-layer .smooth-top[data-v-43f1648a] {
  border-bottom: 1px solid #c8c7cc;
  background: linear-gradient(180deg, #fff 10%, hsla(0, 0%, 100%, 0.7));
  transform: translateZ(5.625em);
}

.smooth-picker .smooth-handle-layer .smooth-middle[data-v-43f1648a] {
  height: 2em;
}

.smooth-picker .smooth-handle-layer .smooth-bottom[data-v-43f1648a] {
  border-top: 1px solid #c8c7cc;
  background: linear-gradient(0deg, #fff 10%, hsla(0, 0%, 100%, 0.7));
  transform: translateZ(5.625em);
}
</style>
