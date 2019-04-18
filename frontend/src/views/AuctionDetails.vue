<template>
  <v-container>
    <v-carousel height="50vh">
      <v-carousel-item
              v-for="(item, i) in items"
              :key="i"
              :src="item.src"
      ></v-carousel-item>
    </v-carousel>
    <h1>{{ auction.title }}</h1>
    <v-layout row wrap>
      <v-flex column xs6>
        <h5>Högsta Bud</h5>
        <h4>4005kr</h4>
      </v-flex>
      <v-flex column xs6>
        <h5>Avslutas</h5>
        <h4>{{ endDateString }}</h4>
      </v-flex>
      <v-flex column xs12 align-start="true">
        <h5>Beskrivning</h5>
        <p>{{ auction.description }}</p>
        <p>{{ auction.description }}</p>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
	export default {
		data() {
			let endDate = new Date(this.auction.endTime);
			return {
				endDateString:
					endDate.toLocaleDateString() +
					" " +
					endDate.getHours() +
					":" +
					endDate.getMinutes(),
				items: [
					{
						src: "https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg"
					},
					{
						src: "https://cdn.vuetifyjs.com/images/carousel/sky.jpg"
					},
					{
						src: "https://cdn.vuetifyjs.com/images/carousel/bird.jpg"
					},
					{
						src: "https://cdn.vuetifyjs.com/images/carousel/planet.jpg"
					}
				],
				id: this.auction.id
			};
		},
		name: "AuctionDetails",
		props: ["auction"],
		methods: {
			updateAuction() {
				this.$router.push({
					name: "upload",
					params: { auction: this.auction }
				});
			}
		},
		mounted() {
			if (this.id == null) {
				this.id = this.$route.params.routeId;
				console.log(this.id);
			} else console.log("existing props");
		}
	};
</script>

<style scoped>
  #v-carousel {
    background-color: #192337;
  }
</style>
