<template>
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
  </div>
</template>

<script>
  import Navigation from '@/components/Navigation'

  export default {
    components: {
      Navigation
    },
    created() {
      this.$store.dispatch('getPosts');
    }
  }
</script>

<style src="./style.css"></style>
