<template>
  <div id="notifications-area">
    <h2>Notiser</h2>
    <v-list id="notifications-items">
      <transition-group
              class="container row"
              name="animate-route"
              mode="out-in"
              enter-active-class="animated pulse"
              :leave-active-class="'animated ' + leaveAnimation"
      >
        <NotificationItem
                v-for="(item, key) in notifications"
                :key="item[0]"
                :item="item[1]"
        />
      </transition-group>
    </v-list>
  </div>
</template>

<script>
  import NotificationItem from "./NotificationItem";
  import {eventBus} from "@/main";

  export default {
    name: "Notifications",
    components: {
      NotificationItem
    },
    data() {
      return {
        leaveAnimation: 'pulse'
      }
    },
    computed: {
      notifications() {
        let noties = this.$store.state.notifications
        let timeSorted = []
        for (let key in noties) {
          timeSorted.push([key, noties[key]])
        }
        timeSorted.sort((a, b) => b[1].time - a[1].time)

        return timeSorted;
      }
    },
    created() {
      eventBus.$on('notify-leave-animation', animation => this.leaveAnimation = animation)
    },
    beforeDestroy() {
      eventBus.$off('notify-leave-animation')
    },
  };
</script>

<style scoped>
  #notifications-area {
    bottom: 0;
    position: absolute;
    width: 100%;
  }

  #notifications-items {
    overflow-y: scroll;
    height: 40vh;
  }

  #notifications-items > span > div {
    animation-duration: 200ms;
  }

  ::-webkit-scrollbar {
    display: none;
  }
</style>
