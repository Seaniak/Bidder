<template>
  <v-container>
    <figure>
      <img width="100%" :src="auction.imageData" alt="thumbnail">
    </figure>
    <h1>{{auction.title}}</h1>
    <p>{{auction.body}}</p>
    <p>{{auction}}</p>
  </v-container>
</template>

<script>
  export default {
    data(){
      return {
        id: this.auction.id
      }
    },
    name: "AuctionDetails",
    props: ['auction'],
    methods: {
      updateAuction() {
        this.$router.push({
          name: 'upload',
          params: {auction: this.auction}
        })
      }
    },
    mounted() {
      if(this.id == null) {
        this.id = this.$route.params.routeId;
        console.log(this.id);
      } else  console.log('existing props');
	}
  }
</script>

<style scoped>
  button {
    position: fixed;
    bottom: 70px;
    right: 20px;
  }
</style>