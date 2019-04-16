<template>
  <v-navigation-drawer
          v-model="drawer"
          fixed
          right
          temporary
  >
    <div class="nav-title my-3">
      <h2>Navigering</h2>
    </div>
    <v-btn
            id="aboutBtn"
            to="/about"
    >
      <span>About</span>
      <v-icon medium>account_box</v-icon>
    </v-btn>
  </v-navigation-drawer>
</template>

<script>
  export default {
    name: "NavigationDrawer",
    props: ['toggleDrawer'],
    computed: {
      drawer: {
        get() {
          return this.toggleDrawer
        },
        set() {
        }
      }
    }
  }
</script>

<style scoped>
  .nav-title {
    border-bottom: solid 1px #333;
  }

  #aboutBtn {
    color: var(--main-font-color);
  }
</style>