import Vue from "vue";
import Vuex from "vuex";
import { updateConnection, logoutConnection } from "./webSocket";

Vue.use(Vuex);

let noticeID = 0;

export default new Vuex.Store({
  state: {
    auctionMap: null,
    activeAuction: null,
    currentAuctionId: null,
    // currentBid: null,
    currentUser: null,
    openNavDrawer: null,
    filteredItems: [],
    auctions: [],
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
    // addAuctionToMap(state, auction) {
    //   if (!state.auctionMap.has(auction.id)) {
    //     auction.maxBid = (auction.bids.length > 0) ?
    //         Math.max(...(auction.map((bid) => bid.sum)))
    //         : auction.startSum;
    //     state.auctionMap.set(auction.id, auction);
    //   }
    // }
  },
  getters: {
    getAuction: (state) => (auctionId) => {
      // return state.auctionMap.get(auctionId);
      // let res = undefined;
      // res = state.auctions.find((auction) => auction.id === auctionId);
      return state.auctions.find((auction) => auction.id === auctionId);
    }
  },
  mutations: {
    filterItems(state, searchResult) {
      searchResult.forEach((newAuction) => {
        if(!state.auctions.find((auction) => auction.id == newAuction.id)) {
          newAuction.maxBid = (newAuction.bids.length > 0) ?
                Math.max(...(newAuction.bids.map((bid) => bid.sum)))
                : newAuction.startSum;
          state.auctions.push(newAuction);
        }


        // let exists = false;
        // state.auctions.forEach((auction) => {
        // 	if(searchAuction.id === auction.id) exists = true;
        // });
        // if(!exists) state.auctions.push(searchAuction);


        // if(!state.auctionMap.has(searchAuction.id)) {
        //   searchAuction.maxBid = (searchAuction.bids.length > 0) ?
        //       Math.max(...(searchAuction.bids.map((bid) => bid.sum)))
        //       : searchAuction.startSum;
        //   state.auctionMap.set(searchAuction.id, searchAuction);
        // }
      });
      state.filteredItems = searchResult;
    },
  addAuction(state, newAuction) {
    // let exists = false;
    // state.auctions.forEach((auction) => {
    //   if (value.id === auction.id) exists = true;
    // });
    // if (!exists) state.auctions.push(value);
    // if(!state.auctions.find((auction) => {return auction.id === newAuction.id})){
      if(!state.auctions.includes(newAuction)){

        newAuction.maxBid = (newAuction.bids.length > 0) ?
          Math.max(...(newAuction.bids.map((bid) => bid.sum)))
          : newAuction.startSum;
      state.auctions.push(newAuction);
      console.log("ADDAUCTION", newAuction);
    }
  },
  // getAuctions(state, value) {
  //   state.auctions = value;
  //   console.log('Auctions: ', state.auctions);
  // },
  // getSingleAuction(state, value) {
  //   state.auctions = value;
  //   // console.log('Auctions: ', state.auctions);
  // },
  setActiveAuction(state, activeAuction) {
    state.activeAuction = activeAuction;
  },
  setAuctionMap(state, auctionMap) {
    state.auctionMap = auctionMap;
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
