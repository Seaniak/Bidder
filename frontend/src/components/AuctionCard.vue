<template>
  <div id="auction" @click="seeAuctionDetails">
    <div id="auction-text">
      <h2>{{ auction.title }}</h2>
      <p>{{ auction.body }}</p>
    </div>
    <figure>
      <img :src="auction.imageData" alt="thumbnail" />
    </figure>
  </div>
</template>

<script>
export default {
  name: "AuctionCard",
  props: ["auction"],
  methods: {
    seeAuctionDetails() {
      this.$router.push({
        name: "auctionDetails",
        params: { auction: this.auction }
      });
    }
  }
};
</script>

<style scoped>
#auction {
  display: flex;
  text-decoration: none;
  color: var(--main-font-color);
  width: 100%;
  animation-duration: 200ms;
  border-bottom: solid 1px #7c7e70;
}

#auction-text {
  padding-left: 3%;
  text-align: start;
  flex: 4;
}

figure {
  flex: 2;
}

img {
  width: 100%;
}
</style>
