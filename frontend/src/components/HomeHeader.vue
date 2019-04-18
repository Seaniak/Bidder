<template>
  <div id="home-header">
    <h1>Logfolio</h1>
    <v-btn icon absolute @click="toggleMenu">
      <v-icon medium>more_vert</v-icon>
    </v-btn>
    <transition
      name="animate-route"
      mode="out-in"
      enter-active-class="animated pulse"
      leave-active-class="animated pulse"
    >
      <v-card v-show="showMenu" width="150">
        <v-card-title v-if="!$store.state.loggedIn" @click="logIn"
          >Log in</v-card-title
        >
        <v-card-title v-else @click="logOut">Log out</v-card-title>
      </v-card>
    </transition>
  </div>
</template>

<script>
export default {
  name: "HomeHeader",
  data() {
    return {
      searchInput: "",
      showMenu: false
    };
  },
  methods: {
    toggleMenu() {
      this.showMenu = !this.showMenu;
    },
    logIn() {
      this.showMenu = false;
      this.$router.push({ name: "login" });
    },
    logOut() {
      this.showMenu = false;
      this.$store.commit("logOut", false);
    }
  },
  watch: {
    searchInput: function(val) {
      this.$emit("searchInput", val);
    }
  }
};
</script>

<style scoped>
button {
  top: 15px;
  right: 10px;
  /*margin-top: 5px;*/
}

.v-card {
  right: 15px;
  position: absolute;
  z-index: 100;
  animation-duration: 100ms;
}
</style>
