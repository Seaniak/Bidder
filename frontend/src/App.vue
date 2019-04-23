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
  import Navigation from '@/components/Navigation'
  import NavigationDrawer from '@/components/NavigationDrawer'

  export default {
    components: {
      Navigation,
      NavigationDrawer
    },
    async created() {
      this.$store.dispatch('getAuctions');

      // Get last logged in account from local storage,
      // and auto login on connect
      let rememberedUser = JSON.parse(localStorage.getItem('remember-me'))
      let response = await fetch("/login", {
        method: "POST",
        body: rememberedUser,
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        }
      })
      if (!await response.url.includes('error'))
        this.$store.commit("loginUser", true);
    }
  }
</script>

<style src="./style.css"></style>
