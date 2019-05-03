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
        let timeDifference = new Date(this.auctionEndTime).getTime() - Date.now();
        this.timeLeft = new Date(timeDifference);
      },
      formatTime(time) {
        // return time.toLocaleTimeString()
        // return DateTime.fromJSDate(time).toFormat("HH:mm:ss");
        return new Date(time - 3600000).toLocaleTimeString();
      },
    },
    watch: {
      timeLeft(time) {
        if (time < 0) {
          this.$emit('auctionEnded', true)
          clearInterval(this.interval);
        }
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
  p {
    margin: 0;
  }
</style>
