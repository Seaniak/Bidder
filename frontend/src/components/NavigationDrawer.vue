<template>
  <v-navigation-drawer
          v-model="$store.state.openNavDrawer"
          fixed
          right
          clipped
          temporary
          id="drawer"
  >
    <div class="nav-title my-3">
      <v-layout row align-center justify-center>
        <v-btn id="closeDrawer" absolute icon @click.stop="toggleDrawer">
          <v-icon large>keyboard_arrow_right</v-icon>
        </v-btn>
        <h2>Navigering</h2>
        <v-btn
                v-if="$store.state.currentUser"
                absolute
                fab
                :color="iconColor"
                id="user-icon">
          {{$store.state.currentUser.name.charAt(0).toUpperCase()}}
          {{$store.state.currentUser.surname.charAt(0).toUpperCase()}}
        </v-btn>
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
      <v-btn
              v-if="!$store.state.currentUser"
              id="registerBtn"
              to="/register"
              flat
      >
        <span>Registera konto</span>
        <v-icon medium>account_box</v-icon>
      </v-btn>
      <v-btn v-if="$store.state.currentUser" to="/upload" flat>
        <span>Skapa auktion</span>
        <v-icon dark medium>note_add</v-icon>
      </v-btn>
      <v-btn v-if="$store.state.currentUser" to="/user-auctions" flat>
        <span>Mina auktioner</span>
        <v-icon dark medium>bookmarks</v-icon>
      </v-btn>
      <v-btn v-if="$store.state.currentUser" to="/openChats" flat>
        <span>Meddelanden</span>
        <v-icon dark medium>chat</v-icon>
      </v-btn>
      <!--<v-btn id="aboutBtn" to="/about" flat>-->
      <!--<span>Om oss</span>-->
      <!--<v-icon medium>account_box</v-icon>-->
      <!--</v-btn>-->
      <Notifications/>
    </v-layout>
  </v-navigation-drawer>
</template>

<script>
  import Notifications from './Notifications'

  export default {
    name: "NavigationDrawer",
    components: {
      Notifications
    },
    computed: {
      iconColor() {
        let colors = [
          '#F2D600',
          '#61BD4F',
          '#C377E0',
          '#FF9F1A',
          '#FF78CB',
          '#00C2E0'
        ]
        let randomColor = Math.round(Math.random() * colors.length)
        return colors[randomColor]
      }
    },
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
        this.$router.push({name: "home"});
      },
      created() {

      }
    }
  };
</script>

<style scoped>
  #drawer {
    z-index: 1000;
  }

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

  #user-icon {
    right: 5px;
    font-weight: bold;
    font-size: 1.7em;
    letter-spacing: -3px;
  }
</style>
