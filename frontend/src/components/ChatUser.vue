<template>
  <v-list-tile
          class="pb-1"
          ripple
          two-line
          @click="goToChat"
  >
    <v-list-tile-action>
      <v-btn
              fab
              small
              depressed
              :color="iconColor">{{recipient.username.charAt(0).toUpperCase()}}
      </v-btn>
    </v-list-tile-action>
    <v-list-tile-content
            class="border-bottom">
      <v-list-tile-title v-text="recipient.username"></v-list-tile-title>
      <v-list-tile-sub-title class="grey--text text--darken-1" v-text="recipient.text"></v-list-tile-sub-title>
    </v-list-tile-content>
  </v-list-tile>
</template>

<script>
  export default {
    name: "ChatUser",
    props: ['recipient'],
    methods: {
      goToChat() {
        this.$router.push({
          name: 'chat',
          params: {
            recipient: this.recipient.username
          }
        })
      }
    },
    computed:{
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
    }
  }
</script>

<style scoped>
  button{
    font-size: 1.2em;
    font-weight: bold;
  }
  .border-bottom{
    border-bottom: 1px solid lightgray;
  }
</style>