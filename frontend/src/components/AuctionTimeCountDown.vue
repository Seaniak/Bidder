<template>
  <div>
    <h4 v-if="this.timeLeft.getTime() < 0">Avslutad</h4>
    <h4 v-else-if="this.timeLeft.getTime() < 86400000" class="red--text">
      {{
        formatNumber(this.timeLeft.getHours()) +
          ":" +
          formatNumber(
            this.timeLeft.getMinutes() +
              ":" +
              formatNumber(this.timeLeft.getSeconds())
          )
      }}
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
  components: {},
  props: ["auctionEndTime"],
  methods: {
    endTimeCountDown() {
      let timeDifference = this.endDate - Date.now();
      timeDifference = Math.round(timeDifference);
      this.timeLeft = new Date(timeDifference);
    },
    formatNumber(time) {
      return time.toString().padStart(2, "0");
    }
  },
  mounted() {
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
