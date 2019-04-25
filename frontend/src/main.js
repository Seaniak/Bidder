import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "./webSocket";
import store from "./store";
import Vuetify from "vuetify";
import "vuetify/dist/vuetify.min.css";

Vue.use(Vuetify);

// Vue.$socket = socket;
// Vue.prototype.$socket = Vue.$socket;

export const eventBus = new Vue();

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
