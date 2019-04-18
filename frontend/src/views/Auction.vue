<template>
  <div>
    <h1>TEST</h1>
    <h1>{{ auctionId }}</h1>
  </div>
</template>

<script>
export default {
  name: "Auction",
  data() {
    return {
      auctionId: this.$route.params.id
    };
  }
};
</script>

<style scoped></style>
