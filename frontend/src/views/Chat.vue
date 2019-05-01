<template>
  <div id="chat-window">
    <h1 id="chat-title">{{recipient}}</h1>
    <transition-group
            tag="div"
            id="message-area"
            name="animate-route"
            mode="out-in"
            enter-active-class="animated pulse"
    >
      <Message
              v-for="(message, i) in messages"
              :message="message"
              :key="i + message.text"
              :class="message.sender === recipient ? 'left' : 'right'"
              id="message-card"/>
    </transition-group>
  </div>
</template>

<script>
  import Message from '@/components/Message'
  import {eventBus} from "@/main";
  import {sendMessage, sendData} from "@/webSocket";

  export default {
    name: "Chat",
    props: ['recipient'],
    components: {
      Message
    },
    computed: {
      messages() {
        setTimeout(() => {
          window.scrollTo(0, document.body.scrollHeight);
        }, 10)
        return this.$store.state.chatMessages
      }
    },
    methods: {
      sendMessage(newMessage) {
        let message = {
          action: 'message',
          sender: this.$store.state.currentUser.username,
          recipient: this.recipient,
          text: newMessage
        }
        sendData(message)
      }
    },
    beforeDestroy() {
      eventBus.$off("nav-new-message-clicked");
      this.$store.commit('currentRecipient', '')
    },
    async created() {
      eventBus.$on("nav-new-message-clicked", newMessage => {
        this.sendMessage(newMessage)
      });

      this.$store.commit('currentRecipient', this.recipient)

      // load messages from database
      let response = await fetch(
          `/api/messages/user=${this.$store.state.currentUser.username}&recipient=${this.recipient}`)
      response = await response.json()
      this.$store.commit('fetchMessages', response)

    },
    mounted() {
    }
  }
</script>

<style scoped>
  #chat-title {
    position: fixed;
    top: 0;
    right: 0;
    padding: 10px 0;
    width: 100%;
    z-index: 11;
    background-color: var(--main-background);
    box-shadow: 0 0 3px 0;
  }

  .left {
    align-self: flex-start;
    background-color: #eee;
  }

  .right {
    align-self: flex-end;
    background-color: #9eeee3;
  }

  #message-card {
    position: relative;
    animation-duration: 200ms;
    width: 70vw;
    margin: 5px 15px;
    border-radius: 10px;
  }

  #message-area {
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    width: 100%;
    min-height: 95vh;
  }
</style>