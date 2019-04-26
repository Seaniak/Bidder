<template>
   <div>
      <p v-if="this.timeLeft < 86400">
         {{new Intl.DateTimeFormat("sv-SE", {hour: 'numeric', minute: 'numeric', second: 'numeric'}).format(this.endDate)}}
      </p>
      <p v-else>
         {{new Intl.DateTimeFormat("sv-SE", {weekday: 'long', year: 'numeric', month: 'numeric', day: 'numeric',
         hour: 'numeric', minute: 'numeric', second: 'numeric'}).format(this.endDate)}}
      </p>
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
            timeLeft: null,
         }
      },
      components: {  },
      props: ['auctionEndTime'],
      methods: {
         endTimeCountDown() {
            let timeDifference = this.endDate - Date.now();
            timeDifference = Math.round(timeDifference/1000);
            console.log(timeDifference)
            this.timeLeft = timeDifference;
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