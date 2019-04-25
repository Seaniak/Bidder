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
            @click.stop="$store.state.openNavDrawer = !$store.state.openNavDrawer"
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
		computed: {
			currentNavigation() {
				let indexOne = this.$route.path.indexOf("/");
				let indexTwo = this.$route.path.lastIndexOf("/");
				let path = this.$route.path;
				switch (((path.length > 1) && indexTwo > indexOne) ? path.substring(0, indexTwo) : path) {
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

<style scoped>
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
</style>
