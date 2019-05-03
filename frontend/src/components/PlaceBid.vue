<template class="template">
  <v-layout row wrap class="bidBar justify-center">
    <div class="text-xs-center">
      <v-dialog
          v-model="dialog"
          width="500"
      >
        <v-card class="bidDialog">
          <div v-if="successBid">
            <v-card-title
                dark
                class="headline dark borderTop"
                primary-title
            >
              Bud registrerat
            </v-card-title>
            <v-card-text class="text-xs-left">
              <h4>Tid: {{getBidDateString(successBid.time)}}</h4>
              <h4>Summa: {{successBid.sum}} kr</h4>
              <h4>Användare: {{successBid.username}}</h4>
            </v-card-text>
          </div>
          <v-card-title v-else
                        dark
              class="headlin"
              primary-title
          >
            Nånting gick fel!
          </v-card-title>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="primary"
                flat
                @click="dialog = false"
            >
              Stäng
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
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
          <v-layout v-if="$store.state.currentUser && $store.getters.getAuction(this.bidId).username ===
                                $store.state.currentUser.username" column align-center justify-center fill-height>
            <h2>Kan inte lägga bud på egna auktioner!</h2>
          </v-layout>
          <v-layout v-else-if=" $store.state.currentUser" column align-center justify-center fill-height>
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
      sheet: false,
      dialog: false,
      successBid: null,
    };
  },
  methods: {
    getBidDateString(bidTimeStamp) {
      let bidDate = new Date(bidTimeStamp);
      return bidDate.toLocaleDateString() + " " + bidDate.toLocaleTimeString();
    },
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
          this.$store.state.currentUser.username ||
        new Date(this.$store.getters.getAuction(this.bidId).endTime)-Date.now() < 0
      ) {
        this.sheet = false;
        return;
      }
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
		if(await bid.json != null) {
		  bid = await bid.json();
		  console.log(bid);
      this.successBid = bid;
      this.dialog=true;
      }
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
  .borderTop {
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
  }
  .bidDialog {
    border-radius: 10px;
  }
  .headline {
    background: teal;
    color: white;
  }
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
