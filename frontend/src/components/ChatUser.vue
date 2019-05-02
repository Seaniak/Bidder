<template>
  <v-list-tile
          ripple
          @click="goToChat"
  >
    <v-list-tile-action>
      <v-btn
              fab
              small
              depressed
              :color="iconColor">{{recipient.charAt(0).toUpperCase()}}
      </v-btn>
    </v-list-tile-action>
    <v-list-tile-content
            class="border-bottom">
      <v-list-tile-title v-text="recipient"></v-list-tile-title>
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
            recipient: this.recipient
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