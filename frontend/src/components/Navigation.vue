<template>
  <nav id="bottom-nav">
    <v-btn to="/" icon :active-class="null">
      <v-icon color="white" large>keyboard_arrow_left</v-icon>
    </v-btn>

      <transition
              name="animate-route"
              mode="out-in"
              enter-active-class="animated pulse"
              leave-active-class="animated pulse"
      >
      <component id="custom-nav"
                 :is="currentNavigation"
                 :key="$route.fullPath"></component>
      </transition>

    <v-badge
            overlap
            left
            v-model="$store.state.notificationBadge"
            color="orange"
    >
      <template v-slot:badge>
        <v-icon
                dark
                small
        >
          notifications
        </v-icon>
      </template>
    <v-btn
            icon
            id="toggleDrawer"
            @click.stop="toggleDrawer"
    >
      <v-icon color="white" medium>menu</v-icon>
    </v-btn>
    </v-badge>
  </nav>
</template>

<script>
	import HomeNav from './navbar/HomeNav';
	import LoginNav from './navbar/LoginNav';
	import RegisterNav from './navbar/RegisterNav';
	import UploadAuction from "./navbar/UploadAuctionNav";
	import GoToLoginPageNav from "./navbar/GoToLoginPageNav";
	import PlaceBid from "./navbar/PlaceBid";


	export default {
		name: "Navigation",
    methods: {
      toggleDrawer(){
        this.$store.state.openNavDrawer = !this.$store.state.openNavDrawer

        this.$store.commit('notificationToggle', false)
      }
    },
		computed: {
			currentNavigation() {
		    let path = this.$route.path;
				switch ((path.lastIndexOf("/") > 1) ? path.substring(0, path.lastIndexOf("/")) : path) {
					case '/':
						return HomeNav;
						break;
					case '/login':
						return LoginNav;
						break;
					case '/register':
						return RegisterNav;
						break;
					case '/register-success':
						return GoToLoginPageNav;
						break;
					case '/upload':
						return UploadAuction;
						break;
					case '/auction':
						return PlaceBid;
						break;
					default:
						return HomeNav
				}
			}
		}
	}
</script>

<style>
  #custom-nav{
    animation-duration: 200ms;
  }
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
  .v-badge__badge{
    width: 2em;
    height: 2em;
  }
</style>
