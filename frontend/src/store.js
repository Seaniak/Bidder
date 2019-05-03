import Vue from "vue";
import Vuex from "vuex";
import {updateConnection, logoutConnection} from "./webSocket";
import {notifyMessage} from "./utilities/SocketMessage";
import {notifyBid} from "./utilities/SocketBid";

Vue.use(Vuex);

let getMaxBid = (auction) => {
  return (auction.bids.length > 0) ?
      Math.max(...(auction.bids.map((bid) => bid.sum)))
      : auction.startSum;};

export default new Vuex.Store({
  state: {
    currentUser: null,
    openNavDrawer: null,
    filteredItems: [],
    auctions: {},
    notificationBadge: false,
    notifications: {},
    chatRecipient: '',
    chatMessages: []
  },
  actions: {
    async getAuctions(context) {
      let response = await fetch("/api/auctions");
      response = await response.json();
      context.commit("getAuctions", response);
      context.commit("filterItems", "-default-");
    },
  },
  getters: {
    getAuction: (state) => (auctionId) => {
      return state.auctions[auctionId];
    }
  },
  mutations: {
    filterItems(state, searchResult) {
      searchResult.forEach((newAuction) => {
        if(!state.auctions[newAuction.id]) {
          newAuction.maxBid = getMaxBid(newAuction);
          Vue.set(state.auctions, newAuction.id, newAuction);}
        });
      state.filteredItems = searchResult;
    },
  addAuction(state, newAuction) {
    if(!state.auctions[newAuction.id]) {
      newAuction.maxBid = getMaxBid(newAuction);
      Vue.set(state.auctions, newAuction.id, newAuction);
    }
  },
    logoutUser(state) {
      state.currentUser = null;
      logoutConnection();
      window.socketUsername = "anon";
    },
    loginUser(state, user) {
      state.currentUser = user;
      if (user) {
        window.socketUsername = user.username;
        updateConnection();
      }
    },
    fetchMessages(state, value) {
      state.chatMessages = value
    },
    addNewMessage(state, value) {
      state.chatMessages.push(value)
    },
    currentRecipient(state, value) {
      state.chatRecipient = value
    },
    notificationToggle(state, value) {
      state.notificationBadge = value;
    },
    removeNotification(state, key) {
      Vue.delete(state.notifications, key)
      state.notificationBadge = false;
    },
    webSocket(state, data) {
      let bid;
      let message;
      // update state depending on incoming action
      switch (data.action) {
        case "bid":
          bid = data.payload;
          notifyBid(state, bid)
          break;
        case "message":
          message = data.payload

          if (message.sender === state.chatRecipient || message.sender === state.currentUser.username) {
            state.chatMessages.push(message)
          } else {
            notifyMessage(state, message)
          }
          break;
      }
    }
  },
})

