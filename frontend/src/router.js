import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/upload",
      name: "upload",
      props: true,
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "upload" */ "./views/Upload.vue")
    },
    {
      path: "/auction/:id",
      name: "auctionDetails",
      component: () =>
        import(/* webpackChunkName: "auctionDetails" */ "./views/AuctionDetails.vue")
    },
    {
      path: "/about",
      name: "about",
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/About.vue")
    },
    {
      path: "/login",
      name: "login",
      component: () =>
        import(/* webpackChunkName: "login" */ "./views/Login.vue")
    },
    {
      path: "/register",
      name: "register",
      component: () =>
        import(/* webpackChunkName: "register" */ "./views/Register.vue")
    },
    {
      path: "/register-success",
      name: "registerSuccess",
      component: () =>
        import(/* webpackChunkName: "registerSuccess" */ "./views/RegistrationSuccess.vue")
    },
    {
      path: "/user-auctions",
      name: "userAuctions",
      component: () =>
        import(/* webpackChunkName: "userAuctions" */ "./views/UserAuctions.vue")
    }
  ]
});
