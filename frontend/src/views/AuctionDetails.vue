<template>
  <v-container>
    <figure>
      <img width="100%" src="" alt="thumbnail" />
    </figure>
    <h1>{{ auction.title }}</h1>

    <p>{{ auction.description }}</p>
  </v-container>
</template>

<script>
export default {
  name: "AuctionDetails",
  data(){
    return{
      auction: {
        title: 'title',
        description: 'description lorem blabla'
      }
    }
  },
  methods: {

  },
  async created(){
    let auction = await fetch('/api/auctions/' + this.$route.params.id)
    auction = await auction.json()

    this.auction = auction;

    console.log(this.auction)
  }
};
</script>

<style scoped>
</style>
