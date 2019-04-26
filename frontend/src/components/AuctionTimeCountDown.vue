<template>
   <div>
      <p>{{endTimeCountDown(endDate)}}</p>
   </div>
   
</template>

<script>

   export default {
      name: "AuctionTimeCountDown.vue",
      data() {
         return {
            endDate: new Date(this.auctionEndTime)
         }
      },
      components: {  },
      props: ['auctionEndTime'],
      methods: {
         endTimeCountDown(date) {
            return date;
         }
      }
   }
</script>

<style scoped>

</style>