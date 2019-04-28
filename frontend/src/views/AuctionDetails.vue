<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container>
    <v-carousel height="50vh" class="border">
      <v-carousel-item
        v-for="(image, i) in auction.images"
        :key="i"
        :src="image"
      ></v-carousel-item>
    </v-carousel>
    <h1>{{ auction.title }}</h1>
    <v-layout row wrap class="pt-2 justify-content-around">
      <v-card class="border col-5">
        <h4>Högsta Bud</h4>
        <h3>{{ auction.maxBid }}kr</h3>
      </v-card>
      <v-card class="border col-5">
        <h4>Avslutas</h4>
        <AuctionTimeCountDown :auctionEndTime="auction.endTime" />
      </v-card>
    </v-layout>
    <v-layout row wrap class="pt-2 justify-content-around">
      <v-card class="border col-5">
        <h4>Säljare</h4>
        <h3>{{auction.username }}</h3>
      </v-card>
      <v-card class="border col-5">
        <h4>Kategori</h4>
        <h3>{{auction.category }}</h3>
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
export default {
  // props: ["auctionProp"],
  components: {},
  data() {
    return {
      bidsController: true,
      pageController: [10, 20, 30, { text: "Alla", value: -1 }],
      auction: null,
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
    // dataChange(gIndex, iIndex){
    // 	console.log(gIndex, iIndex);
    // },
    getDateString(bidTimeStamp) {
      let bidDate = new Date(bidTimeStamp);
      return bidDate.toLocaleDateString() + " " + bidDate.toLocaleTimeString();
    },
    // setAuction(activeAuction) {
		//   if (this.$route.params.id === activeAuction.id) this.auction = activeAuction;
	  // },
	  compareAuctionId(id){
    	return (this.$route.params.id === id);
    }
  },
  computed: {
  	updateAuction() {
  		if(this.compareAuctionId(this.$store.state.activeAuction.id)) {
  			this.auction = this.$store.state.activeAuction;
		    console.log("AUCTION SET BY COMPUTED() FROM STORE", this.auction)
	  }
	  },
  },
  async created() {
	  console.log("STORED AUCTIONS", this.$store.state.auctions);
	  this.$store.state.auctions.forEach((a) => {
		console.log(a);
		if(this.compareAuctionId(a.id)) {
	  		this.$store.commit("setActiveAuction", a);
		    console.log("ACTIVEAUCTION SET BY CREATED() FROM STORE", a)
	    }
	  });

	  if(this.$store.state.activeAuction != null && this.compareAuctionId(this.$store.state.activeAuction.id)) {
	  	this.auction = this.$store.state.activeAuction;
  		console.log("AUCTION SET BY CREATED() FROM STORE", this.auction)
    } else {
      let auction = await fetch("/api/auctions/" + this.$route.params.id);
      this.auction = await auction.json();
		  console.log("AUCTION SET BY CREATED() FROM DATABASE", this.auction);
		  this.$store.commit("setActiveAuction", await auction.json());
		  console.log("ACTIVEAUCTION SET BY CREATED FROM DATABASE", this.auction);
	  }

	  let images = await fetch("/api/get_image/" + this.$route.params.id);
    this.images = await images.json();
    this.auction.maxBid =
      this.auction.bids.length === 0
        ? this.auction.startSum
        : Math.max(...(await this.auction.bids.map(bid => bid.sum)));

    if (this.auction.bids.length > 10) this.bidsController = false;
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