<template>
  <div class="message-nav row align-items-center">
    <div id="textarea-msg">
      <v-textarea
              rows="1"
              auto-grow
              dark
              id="nav-message"
              placeholder="Skriv meddelande"
              v-model="newMessage"
              @keyup="listenForEnter"
              @focus="scrollToBottom"
      ></v-textarea>
    </div>
    <v-btn id="send-btn" class="ml-auto" icon dark @click="sendMessage">
      <v-icon dark large>send</v-icon>
    </v-btn>
  </div>
</template>

<script>
  import {eventBus} from "@/main";

  export default {
    name: "ChatNav",
    data() {
      return {
        newMessage: "",
      };
    },
    methods: {
      sendMessage() {
        if (!this.newMessage.trim()) return;

        eventBus.$emit("nav-new-message-clicked", this.newMessage);
        this.newMessage = ""

        // reset multi row textarea
        document.getElementById("nav-message").blur()
        document.getElementById("nav-message").focus()
      },
      listenForEnter(e) {
        if (e.keyCode == 13 && !e.shiftKey)
          this.sendMessage()
      },
      scrollToBottom() {
        window.scrollTo(0, document.body.scrollHeight);
      }
    },
  };
</script>

<style>
  #textarea-msg {
    width: 80%;
    height: 100%;
    margin-bottom: -30px;
  }

  #nav-message {
    padding: 5px;
    background-color: rgba(0, 128, 128, 0.9);
    border-radius: 10px;
    position: absolute;
    bottom: 0;
    width: 55vw;
  }

  .message-nav {
    width: 70%;
  }

  .primary--text {
    color: lightgray !important;
    caret-color: lightgray !important;
  }
</style>
