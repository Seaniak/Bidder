<template>
   <div>
      <p v-if="this.timeLeft.getTime() < 86400000">
         {{formatNumber(this.timeLeft.getHours()) +
         ':' + formatNumber(this.timeLeft.getMinutes() +
         ':' + formatNumber(this.timeLeft.getSeconds()))}}
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
            timeLeft: new Date(),
         }
      },
      components: {  },
      props: ['auctionEndTime'],
      methods: {
         endTimeCountDown() {
            let timeDifference = this.endDate - Date.now();
            timeDifference = Math.round(timeDifference);
            this.timeLeft = new Date(timeDifference);
         },
         formatNumber(time) {
            return time.toString().padStart(2, '0');
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