import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    currentUser: null,
    openNavDrawer: null,
    filteredItems: [],
    auctions: [],
    addUserResponseFromDb: ''
  },
  mutations: {
    filterItems(state, searchInput = '') {
      let filter = new RegExp(searchInput, "i")
      state.filteredItems = state.auctions.filter(p => p.title.match(filter) || p.description.match(filter))
    },
    addAuction(state, value) {
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
      state.currentUser = null;
    },
    loginUser(state, user) {
      state.currentUser = user;
    }
  },
  actions: {
    async getAuctions(context) {
      console.log('Fetching auctions')
      let response = await fetch('/api/auctions')
      response = await response.json();

      context.commit('getAuctions', response)
      context.commit('filterItems')
    }
  },
})
