import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

let noticeID = 0;

export default new Vuex.Store({
  state: {
    currentBid: null,
    currentUser: null,
    openNavDrawer: null,
    filteredItems: [],
    auctions: [],
    notificationBadge: false,
    notifications: []
  },
  actions: {
    async getAuctions(context) {
      let response = await fetch('/api/auctions')
      response = await response.json();

      context.commit('getAuctions', response)
      context.commit('filterItems')
    }
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
      logoutConnection()
      window.socketUsername = 'anon'
    },
    loginUser(state, user) {
      state.currentUser = user;
      if (user){
        window.socketUsername = user.username
        updateConnection()
      }
      console.log('User: ', state.currentUser)
    },
    setCurrentBid(state, bid) {
      state.currentBid = bid;
    },
    notificationToggle(state, value) {
      state.notificationBadge = value
    },
    removeNotification(state, item) {
      let index = state.notifications.indexOf(item)
      state.notifications.splice(index, 1);
    },
    webSocket(state, data) {
      // update state depending on incoming action
      switch (data.action) {
        case "bid":
          state.notificationBadge = true
          console.log('Socket bid: ', data.payload)
          break;
        case "message":
          state.notificationBadge = true

          let notify = {
            id: noticeID,
            title: 'Nytt meddelande',
            subtitle: data.payload
          }
          state.notifications.push(notify)

          noticeID++

          console.log('Socket message: ', data.payload)
          break;
      }
    }
  },
})
