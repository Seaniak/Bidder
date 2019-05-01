<template>
  <div id="notifications-area">
    <h2>Notiser</h2>
    <v-list id="notifications-items">
      <NotificationItem
              v-for="(item, key) in notifications"
              :key="item[0]"
              :item="item[1]"
      />
    </v-list>
  </div>
</template>

<script>
  import NotificationItem from "./NotificationItem";

  export default {
    name: "Notifications",
    components: {
      NotificationItem
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
    }
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

  ::-webkit-scrollbar {
    display: none;
  }
</style>
