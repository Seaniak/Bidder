<template>
  <!--  <v-btn @click="placeBidClicked" dark flat>Placera bud</v-btn>-->
  <v-layout row wrap class="bidBar">
    <smooth-picker class="scroller col-6" ref="smoothPicker" :data="possibleBids" :change="dataChange"/>
    <div class="text-xs-center">
      <v-bottom-sheet
              v-model="sheet"
              >
        <template v-slot:activator>
          <v-btn
                  color="purple"
                  dark
                  >
            Lägg Bud
          </v-btn>
        </template>
        <v-card tile>
          <v-layout v-if="$store.state.currentUser" column>
            <h3>Bud: {{this.chosenBid}}kr</h3>
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
	import 'vue-smooth-picker/dist/css/style.css';
	import {SmoothPicker} from 'vue-smooth-picker';

	export default {
		name: "ChooseBid",
		props: ["auctionId"],
		components: {
			SmoothPicker
		},
		data() {
			return {
				chosenBid: 0,
				bids: [],
				sheet: false,
			}
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
			checkLoggedIn() {
				return this.$store.state.currentUser === undefined;
			},
			newBids(currentBid) {
				let bids = [];
				let bidStrings = [];
				let addSum = this.getAddSum(currentBid);
				let possibleBid = currentBid + addSum;
				while (bidStrings.length < 20) {
					bids.push(possibleBid);
					bidStrings.push((possibleBid) + ' kr');
					possibleBid += addSum;
				}
				this.bids = bids;
				this.chosenBid = this.bids[0];
				return bidStrings;
			}
			,
			dataChange(gIndex, iIndex) {
				this.chosenBid = this.bids[iIndex - 1];
			}
			,
			async placeBidClicked() {
				if (this.checkLoggedIn()) return;
				let data = {
					auctionId: this.auctionId,
					username: this.$store.state.currentUser.username,
					sum: this.chosenBid
				};
				console.log(data);
				let bid = await fetch('/api/bids', {
					method: 'POST',
					headers: {
						"Content-Type": "application/json"
					},
					body: JSON.stringify(data)
				});
				console.log(await bid.json());
				this.sheet = false;

			}
		}
		,
		computed: {
			possibleBids() {
				return [{
					currentIndex: 1,
					flex: 4,
					list: this.newBids(this.$store.state.currentBid),
					textAlign: 'center',
					className: 'row-group'
				}];
			}
		}
	};
</script>

<style scoped>
  .scroller {
    width: auto;
    height: auto;
    bottom: 10px;
  }

  .bidBtn {
    width: auto;
    height: auto;
  }

  .bidBar {
    width: 300px;
    height: auto;
  }
</style>
