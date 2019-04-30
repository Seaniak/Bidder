<template>
  <v-app>
    <div id="app">
      <transition
        name="animate-route"
        mode="out-in"
        enter-active-class="animated fadeIn"
        leave-active-class="animated fadeOut"
      >
        <router-view :key="$route.fullPath + $route.params.recipient" />
      </transition>
      <Navigation />
      <NavigationDrawer />
    </div>
  </v-app>
</template>

<script>
import { eventBus } from "@/main";
import Navigation from "@/components/Navigation";
import NavigationDrawer from "@/components/NavigationDrawer";

export default {
  components: {
    Navigation,
    NavigationDrawer
  },
  async created() {
    // this.$store.dispatch('getAuctions');

    let user = await fetch("/api/remember-me");
    user = await user.json().catch(e => console.log("Not logged in"));

    this.$store.commit("loginUser", user);

    // forward incoming socket data to store
    eventBus.$on("socket-data", data => this.$store.commit("webSocket", data));
  }
};
</script>

<style src="./style.css"></style>
