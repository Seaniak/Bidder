<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container>
    <v-carousel height="50vh"
                class="border">
      <v-carousel-item
              v-for="(image, i) in images"
              :key="i"
              :src="image"
      ></v-carousel-item>
    </v-carousel>
    <h1>{{ auction.title }}</h1>
    <v-layout row wrap class="pt-2 justify-content-around">
      <v-card class="border col-5">
        <h4>Högsta Bud</h4>
        <h3>{{maxBid}}kr</h3>
      </v-card>
      <v-card class="border col-5">
        <h4>Avslutas</h4>
        <h3>{{ endDateString }}</h3>
      </v-card>
    </v-layout>
    <div class="description py-3">
      <h3>Beskrivning</h3>
      <p>{{ auction.description }}</p>
    </div>
    <v-data-table
            :headers="headers"
            :items="auction.bids"
            :hide-actions="bidsController"
            :must-sort=true
            :no-data-text="'Inga bud är lagda!'"
            :rows-per-page-text="'Antal bud'"
            :pagination.sync="pagination"
            :rows-per-page-items="numbercontroll"
            class="border elevation-1"
    >
      <template v-slot:items="props">
        <td>{{ props.item.sum }}</td>
        <td class="text-xs-right">{{ props.item.username }}</td>
        <td class="text-xs-right">{{getDateString(props.item.time)}}</td>
      </template>
    </v-data-table>
<!--    <smooth-picker ref="smoothPicker" :data="data" :change="dataChange" />-->

  </v-container>
</template>

<script>
	// import 'vue-smooth-picker/dist/css/style.css';
	// import {SmoothPicker} from 'vue-smooth-picker';

	export default {
		components: {
			// SmoothPicker
		},
		data() {
			return {
				// data: [
				// 	{
				// 		currentIndex: 0,
				// 		flex: 3,
				// 		list: [
				// 			'Plan A - free', 'Plan B - $50', 'Plan C - $100'
				// 		],
				// 		textAlign: 'center',
				// 		className: 'row-group'
				// 	}
				// ],
				maxBid: 0,
				bidsController: true,
				numbercontroll: [10, 20, 30, {text: 'Alla', value: -1}],
				auction: 0,
				endDateString: 0,
				images: [],
				pagination: {
					descending: true,
					page: 1,
					rowsPerPage: 10,
					sortBy: "sum"
				},
				headers: [
					{
						text: 'Bud',
						align: 'left',
						sortable: true,
						value: 'sum'
					},
					{text: 'Användare', value: 'username', sortable: true},
					{text: 'Klockslag', value: 'time', sortable: true},
				]
			}
		},
		methods: {
			// dataChange(gIndex, iIndex){
			// 	console.log(gIndex, iIndex);
      // },
			getDateString(bidTimeStamp) {
				let bidDate = new Date(bidTimeStamp);
				return (bidDate.toLocaleDateString() + " " + bidDate.toLocaleTimeString());
			}
		},
		computed: {},
		async created() {
			let auction = await fetch('/api/auctions/' + this.$route.params.id);
			this.auction = await auction.json();

			let images = await fetch('/api/get_image/' + this.$route.params.id);
			this.images = await images.json();

			this.maxBid = Math.max(...(await this.auction.bids.map((bid) => bid.sum)));

			////tillfälligt
  		this.$store.commit("setCurrentBid", this.maxBid);
      console.log([this.$store.state.currentBid*1.01, this.$store.state.currentBid*1.02, this.$store.state.currentBid*1.03,
		  this.$store.state.currentBid*1.04, this.$store.state.currentBid*1.05, this.$store.state.currentBid*1.06]);
		if (this.auction.bids.length > 10) this.bidsController = false;

			let endDate = new Date(this.auction.endTime);
			this.endDateString = endDate.toLocaleDateString() + " " + endDate.getHours() + ":" + endDate.getMinutes();
		}
	};
</script>

<style scoped>
  .border {
    border-radius: 4px;
  }

  .description {
    text-align: start;
  }
</style>
