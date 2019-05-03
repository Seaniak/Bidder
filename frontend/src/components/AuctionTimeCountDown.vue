<template>
  <div>
    <h4 v-if="this.timeLeft.getTime() < 0">Avslutad</h4>
    <h4 v-else-if="this.timeLeft.getTime() < 86400000" class="red--text">
      {{ formatTime(this.timeLeft) }}
    </h4>
    <h4 v-else>
      {{
        new Intl.DateTimeFormat("sv-SE", {
          year: "numeric",
          month: "numeric",
          day: "numeric",
          hour: "numeric",
          minute: "numeric",
          second: "numeric"
        }).format(this.endDate)
      }}
    </h4>
  </div>
</template>

<script>
  import {DateTime} from "luxon";

  export default {
  name: "AuctionTimeCountDown.vue",
  data() {
    return {
      endDate: new Date(this.auctionEndTime),
      interval: null,
      timer: 0,
      timeLeft: new Date()
    };
  },
  props: ["auctionEndTime"],
  methods: {
    endTimeCountDown() {
      let timeDifference = this.endDate - Date.now();
      this.timeLeft = new Date(timeDifference);
    },
    formatTime(time) {
      return DateTime.fromJSDate(time).toFormat("HH:mm:ss");
    },
  },
  watch:{
    timeLeft(time){
      if(time < 0)
        this.$emit('auctionEnded', true)
    }
  },
  created() {
    this.interval = setInterval(() => {
      this.endTimeCountDown();
    }, 1000);
  },
  beforeDestroy() {
    clearInterval(this.interval);
  }
};
</script>

<style scoped>
  p{
    margin: 0;
  }
</style>
