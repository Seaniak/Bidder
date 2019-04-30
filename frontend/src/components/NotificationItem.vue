<template>
  <v-list-tile
          two-line
          ripple
          @click="goToRoute"
  >
    <v-list-tile-action>
      <v-icon medium>{{item.icon}}</v-icon>
    </v-list-tile-action>

    <v-list-tile-content>
      <v-list-tile-title v-text="item.title"></v-list-tile-title>
      <v-list-tile-sub-title v-text="item.subtitle"></v-list-tile-sub-title>
    </v-list-tile-content>
  </v-list-tile>
</template>

<script>
  export default {
    name: "NotificationItem",
    props: ['item'],
    data() {
      return {}
    },
    methods: {
      goToRoute() {
        if (this.item.icon === 'monetization_on')
          this.$router.push({
            path: this.item.route,
          })
        else {
          this.$router.push({
            name: 'chatReRoute',
            params: {
              params: this.item.params
            }
          })
        }
        this.deleteItem()
      },
      deleteItem() {
        this.$store.commit('removeNotification', this.item)
      }
    }
  }
</script>

<style scoped>
</style>