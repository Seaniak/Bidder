import Vue from "vue";
import Vuex from "vuex";
import { updateConnection, logoutConnection } from "./webSocket";

Vue.use(Vuex);

let noticeID = 0;

export default new Vuex.Store({
  state: {
    currentUser: null,
    openNavDrawer: null,
    filteredItems: [],
    auctions: {},
    notificationBadge: false,
    notifications: []
  },
  actions: {
    async getAuctions(context) {
      let response = await fetch("/api/auctions");
      response = await response.json();
      console.log("ASYNC GETAUC", response);
      context.commit("getAuctions", response);
      context.commit("filterItems", "-default-");
    },
  },
  getters: {
    getAuction: (state) => (auctionId) => {
      return state.auctions.auctionId;
    }
  },
  mutations: {
    filterItems(state, searchResult) {
      searchResult.forEach((newAuction) => {
        let exists = false;
        for(let key in state.auctions){
          if(key == newAuction.id) exists = true;
        }
        if(!exists) {
          newAuction.maxBid = (newAuction.bids.length > 0) ?
              Math.max(...(newAuction.bids.map((bid) => bid.sum)))
              : newAuction.startSum;
          state.auctions[newAuction.id] = newAuction;
          // console.log("FILTERITEMS", state.auctions[newAuction.id])
        }
        state.filteredItems = searchResult;
        });
    },
  addAuction(state, newAuction) {
    let exists = false;
    for(let key in state.auctions){
      if(key == newAuction.id) exists = true;
    }
    if(!exists) {
      newAuction.maxBid = (newAuction.bids.length > 0) ?
          Math.max(...(newAuction.bids.map((bid) => bid.sum)))
          : newAuction.startSum;
      state.auctions[newAuction.id] = newAuction;
      console.log("ADDAUCTION", state.auctions[newAuction.id]);
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
    // console.log('User: ', state.currentUser);
  },
  setCurrentBid(state, bid) {
    state.currentBid = bid;
  },
  notificationToggle(state, value) {
    state.notificationBadge = value;
  },
  removeNotification(state, item) {
    let index = state.notifications.indexOf(item);
    state.notifications.splice(index, 1);
  },
  webSocket(state, data) {
    // update state depending on incoming action
    switch (data.action) {
      case "bid":
        state.notificationBadge = true;
        // console.log('Socket bid: ', data.payload);
        break;
      case "message":
        state.notificationBadge = true;
        state.notifications.push({
          id: noticeID,
          title: "Nytt meddelande",
          subtitle: data.payload
        });

        noticeID++;

        // console.log('Socket message: ', data.payload);
        break;
    }
  }}
});
