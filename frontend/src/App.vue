<template>
  <v-app>
    <div id="app">
      <transition
              name="animate-route"
              mode="out-in"
              enter-active-class="animated fadeIn"
              leave-active-class="animated fadeOut"
      >
        <router-view :key="$route.fullPath"/>
      </transition>
      <Navigation/>
      <NavigationDrawer/>
    </div>
  </v-app>
</template>

<script>
  import {eventBus} from "@/main";
  import Navigation from "@/components/Navigation";
  import NavigationDrawer from "@/components/NavigationDrawer";

  export default {
    components: {
      Navigation,
      NavigationDrawer
    },
    async created() {
      let user = await fetch("/api/remember-me");
      user = await user.json()
          .catch(e => console.log("[Server] Not authenticated"));

      this.$store.commit("loginUser", user);

      // forward incoming socket data to store
      eventBus.$on("socket-data", data => this.$store.commit("webSocket", data));
    }
  };
</script>

<style src="./style.css"></style>
