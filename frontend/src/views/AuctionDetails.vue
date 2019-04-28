<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container>
    <v-carousel height="50vh" class="border">
      <v-carousel-item
        v-for="(image, i) in (updateAuction !== null) ? updateAuction.images : []"
        :key="i"
        :src="image"
      ></v-carousel-item>
    </v-carousel>
    <h1 v-if="updateAuction !== null">{{ updateAuction.title }}</h1>
    <v-layout row wrap class="pt-2 justify-content-around">
      <v-card class="border col-5">
        <h4>Högsta Bud</h4>
        <h3 v-if="updateAuction !== null">{{ updateAuction.maxBid }}kr</h3>
      </v-card>
      <v-card class="border col-5">
        <h4>Avslutas</h4>
        <AuctionTimeCountDown v-if="updateAuction !== null" :auctionEndTime="updateAuction.endTime" />
      </v-card>
    </v-layout>
    <v-layout row wrap class="pt-2 justify-content-around">
      <v-card class="border col-5">
        <h4>Säljare</h4>
        <h3 v-if="updateAuction !== null"> {{ updateAuction.username }}</h3>
      </v-card>
      <v-card class="border col-5">
        <h4>Kategori</h4>
        <h3 v-if="updateAuction !== null">{{ updateAuction.category }}</h3>
      </v-card>
    </v-layout>
    <div class="description py-3">
      <h3>Beskrivning</h3>
      <p v-if="updateAuction !== null">{{ updateAuction.description }}</p>
    </div>
    <v-data-table
      :headers="headers"
      :items="(updateAuction === null) ? [] : updateAuction.bids"
      :hide-actions="bidsController"
      :must-sort="true"
      :no-data-text="'Inga bud är lagda!'"
      :rows-per-page-text="'Antal bud'"
      :pagination.sync="pagination"
      :rows-per-page-items="pageController"
      class="border elevation-1"
    >
      <template v-slot:items="props">
        <td>{{ props.item.sum }}</td>
        <td class="text-xs-right">{{ props.item.username }}</td>
        <td class="text-xs-right">{{ getDateString(props.item.time) }}</td>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
    import AuctionTimeCountDown from "../components/AuctionTimeCountDown";

export default {
  components: {
  	AuctionTimeCountDown,
  },
  data() {
    return {
      bidsController: true,
      pageController: [10, 20, 30, { text: "Alla", value: -1 }],
      // auction: this.updateAuction,
      endDateString: 0,
      // images: [],
      pagination: {
        descending: true,
        page: 1,
        rowsPerPage: 10,
        sortBy: "sum"
      },
      headers: [
        {
          text: "Bud",
          align: "left",
          sortable: true,
          value: "sum"
        },
        { text: "Användare", value: "username", sortable: true },
        { text: "Klockslag", value: "time", sortable: true }
      ]
    };
  },
  methods: {
    getDateString(bidTimeStamp) {
      let bidDate = new Date(bidTimeStamp);
      return bidDate.toLocaleDateString() + " " + bidDate.toLocaleTimeString();
    },
	  compareAuctionId(id){
    	return (this.$route.params.id == id);
    }
  },
  computed: {
  	updateAuction() {
  		let auction = null;
  		if(this.$store.state.activeAuction != null && this.compareAuctionId(this.$store.state.activeAuction.id)) {
  			auction = this.$store.state.activeAuction;
		    console.log("AUCTION SET BY COMPUTED() FROM STORE", auction);
		    auction.maxBid = (auction.bids.length === 0) ?
            auction.startSum
				  : Math.max(...(auction.bids.map(bid => bid.sum)));
		    if (auction.bids.length > 10) this.bidsController = false;
	    }
  		return auction;
	},
  },
  async created() {
	  this.$store.state.auctions.forEach((a) => {
		if(this.compareAuctionId(a.id)) {
	  		this.$store.commit("setActiveAuction", a);
		    console.log("ACTIVEAUCTION SET BY CREATED() FROM STORE", a)
	    }
	  });

	  if(this.$store.state.activeAuction == null || !this.compareAuctionId(this.$store.state.activeAuction.id)) {
      let auction = await fetch("/api/auctions/" + this.$route.params.id);
      auction = await auction.json();
		  this.$store.commit("setActiveAuction", auction);
		  this.$store.commit("addAuction", auction);
		  console.log("ACTIVEAUCTION SET BY CREATED() FROM DATABASE", auction);
	  }
    //
	  // let images = await fetch("/api/get_image/" + this.$route.params.id);
    // this.images = await images.json();

  }
};
</script>

<style>
.border {
  border-radius: 4px;
}

.v-carousel__controls {
  border-radius: 0 0 4px 4px;
}

.description {
  text-align: start;
}
</style>