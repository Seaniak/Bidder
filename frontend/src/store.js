import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    currentBid: null,
    currentUser: null,
    openNavDrawer: null,
    filteredItems: [],
    auctions: [],
  },
  mutations: {
    filterItems(state, searchResult) {
      console.log('search input in store is: ' + searchResult);
      state.filteredItems = searchResult;
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
    },
    setCurrentBid(state, bid) {
      state.currentBid = bid;
    },
  },
  actions: {
    async getAuctions(context) {
      let response = await fetch('/api/auctions');
      response = await response.json();

      context.commit('getAuctions', response);
      context.commit('filterItems', response.filter(a => new Date(a.endTime) > Date.now()))
    }
  }
})
