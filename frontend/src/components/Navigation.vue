<template>
  <nav id="bottom-nav">
    <v-btn
            to="/"
            icon
            :active-class="null"
    >
      <v-icon
              color="white"
              large>keyboard_arrow_left
      </v-icon>
    </v-btn>

    <div>
      <component :is="currentNavigation"></component>
    </div>

    <v-btn
            icon
            id="toggleDrawer"
            @click.stop="drawer = !drawer"
    >
      <v-icon
              color="white"
              medium>menu
      </v-icon>
    </v-btn>
    <v-btn
            v-if="$store.state.loggedIn"
            to="/upload"
            color="teal"
            flat
            value="newPost"
    >
      <span>New post</span>
      <v-icon dark medium>note_add</v-icon>
    </v-btn>
    <NavigationDrawer
            @closeDrawer="drawer = null"
            :toggleDrawer="drawer"/>
  </nav>
</template>

<script>
  import NavigationDrawer from './NavigationDrawer'
  import HomeNav from './navbar/HomeNav'
  import LoginNav from './navbar/LoginNav'

  export default {
    name: "Navigation",
    components: {
      NavigationDrawer
    },
    data() {
      return {
        drawer: null,
      }
    },
    computed: {
      currentNavigation() {
        switch (this.$route.path) {
          case '/':
            return HomeNav
            break
          case '/login':
            return LoginNav
            break
          default:
            return HomeNav
        }
      }

    }
  }
</script>

<style scoped>
  #bottom-nav {
    display: flex;
    justify-content: space-around;
    width: 100vw;
    position: fixed;
    bottom: 0;
    height: 10vh;
    border-top: solid 1px #7c7e70;
    background-color: teal;
  }

</style>