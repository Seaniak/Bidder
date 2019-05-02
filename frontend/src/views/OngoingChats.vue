<template>
  <div id="ongoing-chat-list">
    <h1 class="p-3">Meddelanden</h1>
    <transition-group
            class=""
            name="animate-route"
            mode="out-in"
            enter-active-class="animated fadeIn"
            leave-active-class="animated fadeOut"
    >
      <ChatUser
              v-for="(recipient, i) in ongoingChats"
              :key="recipient + i"
              :recipient="recipient"/>
    </transition-group>
  </div>
</template>

<script>
  import ChatUser from '@/components/ChatUser'
  import {eventBus} from "@/main";

  export default {
    name: "OngoingChats",
    data() {
      return {
        searchUser: ''
      }
    },
    components: {
      ChatUser
    },
    computed: {
      ongoingChats() {
        let filter = new RegExp(this.searchUser, "i")
        return this.$store.state.currentUser.ongoingChats.filter(u => u.match(filter))
      }
    },
    created() {
      eventBus.$on('search-chat-recipient', input => {
        this.searchUser = input
      })
    },
    beforeDestroy() {
      eventBus.$off('search-chat-recipient')
    }
  }
</script>

<style scoped>
#ongoing-chat-list > span > div {
  animation-duration: 100ms;
}
</style>