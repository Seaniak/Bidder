<template>
  <div id="notifications-area">
    <h2>Notiser</h2>
    <v-list id="notifications-items">
      <NotificationItem
              v-for="(item, i) in notifications"
              :key="i"
              :item="item"
      />
    </v-list>
  </div>
</template>

<script>
  import NotificationItem from './NotificationItem'

  export default {
    name: "Notifications",
    components: {
      NotificationItem
    },
    computed: {
      notifications() {
        return this.$store.state.notifications
      }
    }
  }
</script>

<style scoped>
  #notifications-area {
    bottom: 0;
    position: absolute;
  }
  #notifications-items{
    overflow-y: scroll;
    height: 40vh;
  }

  ::-webkit-scrollbar {
    display: none;
  }
</style>