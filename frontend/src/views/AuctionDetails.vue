<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container>
    <v-carousel height="50vh" class="border">
      <v-carousel-item
        v-for="(image, i) in (updateAuction !== undefined) ? updateAuction.images : []"
        :key="i"
        :src="image"
      ></v-carousel-item>
    </v-carousel>
    <h1 v-if="updateAuction !== undefined">{{ updateAuction.title }}</h1>
    <v-layout row wrap class="pt-2 justify-content-around">
      <v-card class="border col-5">
        <h4>Högsta Bud</h4>
        <h3 v-if="updateAuction !== undefined">{{ updateAuction.maxBid }}kr</h3>
      </v-card>
      <v-card class="border col-5">
        <h4>Avslutas</h4>
        <AuctionTimeCountDown v-if="updateAuction !== undefined" :auctionEndTime="updateAuction.endTime" />
      </v-card>
    </v-layout>
    <v-layout row wrap class="pt-2 justify-content-around">
      <v-card class="border col-5">
        <h4>Säljare</h4>
        <h3 v-if="updateAuction !== undefined"> {{ updateAuction.username }}</h3>
      </v-card>
      <v-card class="border col-5">
        <h4>Kategori</h4>
        <h3 v-if="updateAuction !== undefined">{{ updateAuction.category }}</h3>
      </v-card>
    </v-layout>
    <div class="description py-3">
      <h3>Beskrivning</h3>
      <p v-if="updateAuction !== undefined">{{ updateAuction.description }}</p>
    </div>
    <v-data-table
      :headers="headers"
      :items="(updateAuction !== undefined) ? updateAuction.bids : []"
      :hide-actions="pageControl"
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
      pageController: [10, 20, 30, { text: "Alla", value: -1 }],
      endDateString: 0,
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
  },
  computed: {
  	pageControl() {
		return !(this.$store.getters.getAuction(this.$route.params.id) !== undefined
        && this.$store.getters.getAuction(this.$route.params.id).bids.length > 10);

	},
  	updateAuction() {
		return this.$store.getters.getAuction(this.$route.params.id);
	},
  },
  async created() {
  	console.log(this.$store.getters.getAuction(this.$route.params.id));
	  if(this.$store.getters.getAuction(this.$route.params.id) === undefined){
		  let auction = await fetch("/api/auctions/" + this.$route.params.id);
		  auction = await auction.json();
		  this.$store.commit("addAuction", auction);
	  }
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