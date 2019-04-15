<template>
  <v-bottom-nav
          :value="true"
          dark
          fixed
          color="#2c3e50"
  >
    <v-btn
            to="/"
            color="teal"
            flat
            value="home"
    >
      <span>Home</span>
      <v-icon medium>home</v-icon>
    </v-btn>
    <v-btn
            to="/about"
            color="teal"
            flat
            value="about"
    >
      <span>About</span>
      <v-icon medium>account_box</v-icon>
    </v-btn>

    <v-btn
            v-if="$store.state.loggedIn"
            to="/upload"
            color="teal"
            flat
            value="newPost"
    >
      <span>New post</span>
      <v-icon medium>note_add</v-icon>
    </v-btn>
  </v-bottom-nav>
</template>

<script>
  export default {
    name: "Navigation"
  }
</script>

<style scoped>
  #nav {
    display: flex;
    justify-content: space-around;
    width: 100vw;
    position: fixed;
    bottom: 0;
    padding: 20px 0;
    border-top: solid 1px #7c7e70;
    background-color: #2c3e50;
  }

  #nav a {
    flex: 1;
    font-weight: bold;
    font-size: 1.2em;
    text-decoration: none;
    color: #f5f5f5;
  }

  #nav a.router-link-exact-active {
    color: #42b983;
  }
</style>