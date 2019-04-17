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
    created() {
      this.$store.dispatch('getPosts');
    }
  }
</script>

<style src="./style.css"></style>
