<template>
  <!--  <v-btn @click="placeBidClicked" dark flat>Placera bud</v-btn>-->
  <v-layout row wrap class="bidBar">
    <smooth-picker class="scroller col-6" ref="smoothPicker" :data="possibleBids" :change="dataChange"/>
    <v-btn col-2 class="bidBtn">Lägg Bud</v-btn>
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
			}
		},
		methods: {
			newBids(currentBid) {
				let addSum = 10;
				if (currentBid >= 100) addSum = 20;
				else if (currentBid >= 300) addSum = 50;
				else if (currentBid >= 800) addSum = 100;
				else if (currentBid >= 1800) addSum = 200;
				else if (currentBid >= 3800) addSum = 200;
				let bids = [];
				let latestSum = currentBid + addSum;
				while (bids.length < 20) bids.push((latestSum += addSum) + ' kr');
				return bids;
			},
			dataChange(gIndex, iIndex) {
				console.log(gIndex, iIndex);
			},
			placeBidClicked() {
				// eventBus.$emit('nav-placebid-clicked')
			}
		},
		computed: {
			possibleBids () {
        return {
			    currentIndex: 1,
				  flex: 4,
				  list: this.newBids(this.$store.state.currentBid),
				  textAlign: 'center',
				  className: 'row-group'
		  };
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
