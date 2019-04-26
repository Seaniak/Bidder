<template>
   <div>
      <p>{{timeLeft}}</p>
   </div>
   
</template>

<script>

   export default {
      name: "AuctionTimeCountDown.vue",
      data() {
         return {
            endDate: new Date(this.auctionEndTime),
            interval: null,
            timer: 0,
            timeLeft: null
         }
      },
      components: {  },
      props: ['auctionEndTime'],
      methods: {
         endTimeCountDown() {
            let timeDifference = new Date(Date.now() - this.endDate);
            this.timeLeft = new Date(this.endDate - timeDifference);
         }
      },
      mounted(){
         this.interval = setInterval(() => {
            this.endTimeCountDown();
         }, 1000)
      },
      beforeDestroy() {
         clearInterval(this.interval);
      }
   }
</script>

<style scoped>

</style>