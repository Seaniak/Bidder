<template>
  <v-container>
    <v-carousel v-if="images[0]">
      <v-carousel-item
              v-for="(image,i) in images"
              :key="i"
              :src="image"
      ></v-carousel-item>
    </v-carousel>

    <h1>{{ auction.title }}</h1>
    <p>{{ auction.description }}</p>
  </v-container>
</template>

<script>
  export default {
    name: "AuctionDetails",
    data() {
      return {
        auction: {
          title: 'title',
          description: 'description lorem blabla'
        },
        images: []
      }
    },
    methods: {},
    async created() {
      let auction = await fetch('/api/auctions/' + this.$route.params.id)
      this.auction = await auction.json()

      let images = await fetch('/api/get_image/' + this.$route.params.id)
      this.images = await images.json();

      console.log('AuctionDetails: ', this.auction)
      console.log('AuctionDetails images: ', this.images)
    }
  };
</script>

<style scoped>
</style>
