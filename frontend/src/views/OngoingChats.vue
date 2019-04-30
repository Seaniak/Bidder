<template>
  <div>
    <h1>Meddelanden</h1>
    <v-list-tile
            v-for="(chat, i) in ongoingChats"
            :key="chat + i"
            ripple
            @click="goToChat(chat)"
    >
      <v-list-tile-action>
        <v-btn
                fab
                small
                depressed
                :color="iconColor">{{chat.charAt(0).toUpperCase()}}
        </v-btn>
      </v-list-tile-action>
      <v-list-tile-content
              class="border-bottom">
        <v-list-tile-title v-text="chat"></v-list-tile-title>
      </v-list-tile-content>
    </v-list-tile>
  </div>
</template>

<script>
  export default {
    name: "OngoingChats",
    methods: {
      goToChat(recipient) {
        this.$router.push({
          name: 'chat',
          params: {
            recipient: recipient
          }
        })
      }
    },
    computed: {
      ongoingChats() {
        return this.$store.state.currentUser.ongoingChats
      },
      iconColor() {
        let colors = [
          '#F2D600',
          '#61BD4F',
          '#C377E0',
          '#FF9F1A',
          '#FF78CB',
          '#00C2E0'
        ]
        let randomColor = Math.round(Math.random() * colors.length)
        return colors[randomColor]
      }
    },
  }
</script>

<style scoped>
.border-bottom{
  border-bottom: 1px solid lightgray;
}
</style>