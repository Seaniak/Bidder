<template>
  <!--  <v-btn @click="placeBidClicked" dark flat>Placera bud</v-btn>-->
  <v-layout row wrap class="bidBar">
    <smooth-picker class="scroller col-6" ref="smoothPicker" :data="possibleBids" :change="dataChange"/>
    <v-btn col-2 class="bidBtn" @click="placeBidClicked">Lägg Bud</v-btn>
  </v-layout>
</template>

<script>
	//import { eventBus } from "@/main";
	import 'vue-smooth-picker/dist/css/style.css';
	import {SmoothPicker} from 'vue-smooth-picker';

	export default {
		name: "PlaceBid",
		components: {
			SmoothPicker
		},
		data() {
			return {
				chosenBid: 0,
				bids: [],
			}
		},
		methods: {
			bidAdder(currentBid) {
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
				let addSum = this.bidAdder(currentBid);
				let possibleBid = currentBid + addSum;
				while (bidStrings.length < 20) {
					bids.push(possibleBid);
					bidStrings.push((possibleBid) + ' kr');
					possibleBid += addSum;
				}
				this.bids = bids;
				return bidStrings;
			}
			,
			dataChange(gIndex, iIndex) {
				this.chosenBid = this.bids[iIndex - 1];
			}
			,
			placeBidClicked() {
				console.log(this.chosenBid);

			}
		}
		,
		computed: {
			possibleBids() {
				return [{
					currentIndex: 2,
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
