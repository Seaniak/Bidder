<template>
  <nav id="bottom-nav">
    <v-btn to="/" icon :active-class="null">
      <v-icon class="home-button" color="white" large>keyboard_arrow_left</v-icon>
    </v-btn>

    <transition
            name="animate-route"
            mode="out-in"
            enter-active-class="animated pulse"
            leave-active-class="animated pulse"
    >
      <component
              id="custom-nav"
              :is="currentNavigation"
              :key="$route.fullPath"
      ></component>
    </transition>

    <v-badge
            overlap
            v-model="$store.state.notificationBadge"
            color="orange"
    >
      <template v-slot:badge>
        !
        <!--<v-icon  color="orange" medium>-->
          <!--notifications-->
        <!--</v-icon>-->
      </template>
      <v-btn icon id="toggleDrawer" @click.stop="toggleDrawer">
        <v-icon color="white" large>menu</v-icon>
      </v-btn>
    </v-badge>
  </nav>
</template>

<script>
  import HomeNav from "./navbar/HomeNav";
  import LoginNav from "./navbar/LoginNav";
  import RegisterNav from "./navbar/RegisterNav";
  import UploadAuction from "./navbar/UploadAuctionNav";
  import GoToLoginPageNav from "./navbar/GoToLoginPageNav";
  import PlaceBidNav from "./navbar/PlaceBidNav";
  import ChatNav from "./navbar/ChatNav";

  export default {
    name: "Navigation",
    methods: {
      toggleDrawer() {
        this.$store.state.openNavDrawer = !this.$store.state.openNavDrawer;

        this.$store.commit("notificationToggle", false);
      }
    },
    computed: {
      currentNavigation() {
        let path = this.$route.path;
        switch (
            path.lastIndexOf("/") > 1
                ? path.substring(0, path.lastIndexOf("/"))
                : path
            ) {
          case "/":
            return HomeNav;
          case "/login":
            return LoginNav;
          case "/register":
            return RegisterNav;
          case "/register-success":
            return GoToLoginPageNav;
          case "/upload":
            return UploadAuction;
          case "/auction":
            return PlaceBidNav;
          case "/chat":
            return ChatNav;
          default:
            return HomeNav;
        }
      }
    }
  };
</script>

<style>
  #custom-nav {
    animation-duration: 200ms;
  }

  .home-button{
    font-size: 46px !important;
  }

  #bottom-nav {
    display: flex;
    justify-content: space-around;
    align-items: center;
    width: 100vw;
    position: fixed;
    bottom: 0;
    height: 10vh;
    border-top: solid 1px #7c7e70;
    background-color: teal;
  }

  .v-badge__badge {
    top: -15px !important;
    right: -2px !important;
    line-height: 20px;
    font-weight: bold;
    font-size: 1.8em !important;
    width: 1.4em !important;
    height: 1.4em !important;
  }
</style>
