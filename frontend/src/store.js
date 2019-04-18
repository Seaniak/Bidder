import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loggedIn: false,
    loggedInUserId: null,
    openNavDrawer: null,
    filteredItems: [],
    auctions: [],
    addUserResponseFromDb: ''
  },
  mutations: {
    filterItems(state, searchInput = '') {
      let filter = new RegExp(searchInput, "i")
      state.filteredItems = state.auctions.filter(p => p.title.match(filter) || p.body.match(filter))
    },
    addAuction(state, value) {
      console.log(value)
      state.auctions.push(value)
    },
    addUserToDb(state, value) {
      state.addUserResponseFromDb = value
    },
    clearResponseFromDb(state, value = '') {
      state.addUserResponseFromDb = value;
    },
    getAuctions(state, value) {
      state.auctions = value;
      console.log(state.auctions)
    },
    logoutUser(state, value) {
      state.loggedIn = value;
      state.loggedInUserId = null;
    },
    loginUser(state, successfulLogin) {
      state.loggedIn = successfulLogin;
    },
    setLoggedInUserId(state, loggedInUserId) {
      state.loggedInUserId = loggedInUserId;
    }
  },
  actions: {
    getAuctions(context) {
      console.log('Fetching auctions')
      fetch('/api/auctions')
          .then((res) => {
            return res.json();
          })
          .then((res) => {
            context.commit('getAuctions', res)
            context.commit('filterItems')
          })
          .catch(e => console.log(e))
    },
  }
})
