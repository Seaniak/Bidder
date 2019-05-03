<template>
  <v-card id="auction-ended" color="#B6F6F0" class="border py-3">
    <div v-if="condition === 'ended'">
      <h3>Auktionen avslutad</h3>
      <p>Tyvärr, någon annan hann lägga sista budet.</p>
      <p>Bättre lycka nästa gång!</p>
    </div>
    <div v-else-if="condition === 'sold'">
      <h3>Auktionen såld</h3>
      <p>Auktionen blev såld till {{ soldTo }}, för {{ soldPrice }}kr!</p>
      <p>Kontakta vinnaren så snart som möjligt.</p>
    </div>
    <div v-else-if="condition === 'winner'">
      <h3>Grattis, du vann!</h3>
      <p>Du vann auktionen! Säljaren kommer ta kontakt så snart som möjligt.</p>
    </div>
    <div v-else-if="condition === 'price not reached'">
      <h3>Auktionen avslutad</h3>
      <p>Resarvationspriset ej uppnått.</p>
    </div>
  </v-card>
</template>

<script>
  export default {
    name: "AuctionEnded",
    props: ['auction'],
    data(){
      return{
        soldTo: '',
        soldPrice: 0
      }
    },
    computed: {
      condition() {
        let lastIndex = this.auction.bids.length - 1;
        let lastBid = this.auction.bids[lastIndex];
        if(lastBid !== undefined) {
          this.soldTo = lastBid.username;
          this.soldPrice = lastBid.sum
        } else lastBid = {sum: -1};

        if (lastBid.sum < this.auction.reservedSum)
          return 'price not reached'

        if(this.auction.username === window.socketUsername)
          return 'sold'

        if (lastBid.username === window.socketUsername)
          return 'winner'

        if (lastBid.username !== window.socketUsername)
          return 'ended'
      },
    }
  }
</script>

<style scoped>
  #auction-ended {
    background-color: #b0efe9;
  }
  p{
    margin: 0;
  }
</style>