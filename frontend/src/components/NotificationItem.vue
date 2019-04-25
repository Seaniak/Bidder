<template>
  <v-list-tile
          two-line
          ripple
          @click=""
  >
    <v-list-tile-action>
      <v-icon medium>message</v-icon>
    </v-list-tile-action>

    <v-list-tile-content>
      <v-list-tile-title v-text="item.title"></v-list-tile-title>
      <v-list-tile-sub-title v-text="item.subtitle"></v-list-tile-sub-title>
    </v-list-tile-content>

    <!--<v-list-tile-avatar>-->
    <!--<img :src="item.avatar">-->
    <!--</v-list-tile-avatar>-->
  </v-list-tile>
</template>

<script>
  export default {
    name: "NotificationItem",
    props: ['item']
  }
</script>

<style scoped>

</style>