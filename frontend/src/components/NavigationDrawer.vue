<template>
  <v-navigation-drawer
          v-model="$store.state.openNavDrawer"
          fixed
          right
          clipped
          temporary
  >
    <div class="nav-title my-3">
      <v-layout row align-center justify-center>
        <v-btn
                id="closeDrawer"
                absolute
                icon
                @click.stop="$store.state.openNavDrawer = !$store.state.openNavDrawer"
        >
          <v-icon large>keyboard_arrow_right</v-icon>
        </v-btn>
        <h2>Navigering</h2>
      </v-layout>
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
    name: "NavigationDrawer"
  }
</script>

<style scoped>
  .nav-title {
    padding-bottom: 10px;
    border-bottom: solid 1px #bbb;
  }

  #closeDrawer {
    left: 0px;
  }

  #aboutBtn {
    color: var(--main-font-color);
  }
</style>