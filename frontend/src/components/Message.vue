<template>
  <v-card id="msg-card">
    <v-card-title class="p-2">
      <div> {{ message.text }}</div>
    </v-card-title>
    <v-content>
      <div id="text"
           :class="message.sender === $store.state.currentUser.username ? 'textRight':'textLeft'">{{ message.time }}
      </div>
    </v-content>
  </v-card>
</template>

<script>
  export default {
    name: "Message",
    props: ['message']
  }
</script>

<style scoped>
  #text {
    padding: 0 5px;
    color: gray;
    font-size: 0.9em;
  }

  .textRight {
    text-align: end;
  }

  .textLeft {
    text-align: start;
  }
</style>