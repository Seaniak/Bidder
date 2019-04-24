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
        <v-btn id="closeDrawer" absolute icon @click.stop="toggleDrawer">
          <v-icon large>keyboard_arrow_right</v-icon>
        </v-btn>
        <h2>Navigering</h2>
      </v-layout>
    </div>
    <v-layout column>
      <v-btn v-if="!$store.state.currentUser" to="/login" flat>
        <span>Logga in</span>
        <v-icon medium>account_box</v-icon>
      </v-btn>
      <v-btn v-else flat @click="logout">
        <span>Logga ut</span>
        <v-icon medium>account_box</v-icon>
      </v-btn>
      <v-btn id="aboutBtn" to="/about" flat>
        <span>Om oss</span>
        <v-icon medium>account_box</v-icon>
      </v-btn>
      <v-btn id="registerBtn" to="/register" flat>
        <span>Registera konto</span>
        <v-icon medium>account_box</v-icon>
      </v-btn>
      <v-btn v-if="$store.state.currentUser" to="/upload" flat>
        <span>Skapa auktion</span>
        <v-icon dark medium>note_add</v-icon>
      </v-btn>
    </v-layout>
  </v-navigation-drawer>
</template>

<script>
export default {
  name: "NavigationDrawer",
  methods: {
    toggleDrawer() {
      this.$store.state.openNavDrawer = !this.$store.state.openNavDrawer;
    },
    logout() {
      fetch("/logout").then(res => {
        if (res.url.includes("logout")) {
          this.$store.commit("logoutUser");
        }
      });
      this.toggleDrawer();
      this.$router.push({ name: "home" });
    }
  }
};
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
