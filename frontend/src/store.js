import Vue from "vue";
import Vuex from "vuex";
import {updateConnection, logoutConnection} from "./webSocket";

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
      context.commit('filterItems', '-default-')
    }
  },
  mutations: {
    filterItems(state, searchResult) {
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
      if (user) {
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
      let notify = {
        id: noticeID,
        icon: '',
        title: '',
        subtitle: '',
        route: ''
      }

      // update state depending on incoming action
      switch (data.action) {
        case "bid":
          state.notificationBadge = true
          let bid = data.payload;
          console.log('Socket bid: ', bid)

          notify.icon = 'monetization_on'
          notify.title = `Utbudad på ${bid.auctionId}`
          notify.subtitle = `Nytt bud: ${bid.sum}kr, av ${bid.username}`
          notify.route = `/auctions/${bid.auctionId}`

          state.notifications.unshift(notify)
          break;
        case "message":
          state.notificationBadge = true
          let message = data.payload

          notify.icon = 'chat'
          notify.title = 'Nytt meddelande'
          notify.subtitle = message
          notify.route = '/'

          state.notifications.unshift(notify)

          console.log('Socket message: ', message)
          break;
      }

      noticeID++
    }
  },
})
