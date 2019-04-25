import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    currentUser: null,
    openNavDrawer: null,
    filteredItems: [],
    auctions: [],
  },
  mutations: {
    filterItems(state, searchInput) {
      console.log('search input in store is: ' + searchInput);
      state.filteredItems = searchInput;
      if (searchInput.length === 0) state.filteredItems = state.auctions;
    },
    addAuction(state, value) {
      state.auctions.push(value)
    },
    getAuctions(state, value) {
      state.auctions = value;
      console.log('Auctions: ', state.auctions)
    },
    logoutUser(state, value) {
      state.currentUser = null;
    },
    loginUser(state, user) {
      state.currentUser = user;
      console.log('User: ', state.currentUser)
    }
  },
  actions: {
    async getAuctions(context) {
      let response = await fetch('/api/auctions')
      response = await response.json();

      context.commit('getAuctions', response)
      context.commit('filterItems', [])
    }
  }
})
