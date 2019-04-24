<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container>
    <v-carousel height="50vh">
      <v-carousel-item
              v-for="(image, i) in images"
              :key="i"
              :src="image"
      ></v-carousel-item>
    </v-carousel>
    <h1>{{ auction.title }}</h1>
    <v-layout row wrap>
      <v-flex column xs6>
        <h5>Högsta Bud</h5>
        <h4>{{maxBid}}kr</h4>
      </v-flex>
      <v-flex column xs6>
        <h5>Avslutas</h5>
        <h4>{{ endDateString }}</h4>
      </v-flex>
      <v-flex column xs12 align-start="true">
        <h5>Beskrivning</h5>
        <p>{{ auction.description }}</p>
      </v-flex>
    </v-layout>
    <v-data-table
            :headers="headers"
            :items="auction.bids"
            :hide-actions=true
            :must-sort=true
            :no-data-text="'Inga bud är lagda!'"
            :rows-per-page-text="'Antal bud'"
            :pagination.sync="pagination"
            class="elevation-1"
    >
      <template v-slot:items="props">
        <td>{{  props.item.sum }}</td>
        <td class="text-xs-right">{{  props.item.username }}</td>
        <td class="text-xs-right">{{getDateString(props.item.time)}}</td>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
	export default {
		data() {
			return {
				maxBid: 0,
				auction: 0,
				endDateString: 0,
				images: [],
          pagination: {
					  descending: true,
			      page: 1,
			      rowsPerPage: 20,
			      sortBy: "sum" },
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
			getDateString(bidTimeStamp)  {
		    let bidDate = new Date(bidTimeStamp);
		    return (bidDate.toLocaleDateString() + " " + bidDate.toLocaleTimeString());
	    }
    },
	  computed: {
	  },
		async created() {
			let auction = await fetch('/api/auctions/' + this.$route.params.id);
			this.auction = await auction.json();

			let images = await fetch('/api/get_image/' + this.$route.params.id);
			this.images = await images.json();

			this.maxBid = Math.max(...(await this.auction.bids.map((bid) => bid.sum)));


			console.log('AuctionDetails: ', this.auction);
			console.log('AuctionDetails images: ', this.images);

			let endDate = new Date(this.auction.endTime);
			this.endDateString = endDate.toLocaleDateString() + " " + endDate.getHours() + ":" + endDate.getMinutes();
		}
	};
</script>

<style scoped>
</style>
