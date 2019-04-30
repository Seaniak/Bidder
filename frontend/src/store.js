import Vue from "vue";
import Vuex from "vuex";
import {updateConnection, logoutConnection} from "./webSocket";
import {notifyMessage} from "./utilities/SocketMessage";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    activeAuction: null,
    currentAuctionId: null,
    currentUser: null,
    openNavDrawer: null,
    filteredItems: [],
    auctions: [],
    notificationBadge: false,
    notifications: [],
    chatRecipient: '',
    chatMessages: []
  },
  actions: {
    async getAuctions(context) {
      let response = await fetch("/api/auctions");
      response = await response.json();
      context.commit("getAuctions", response);
      context.commit("filterItems", "-default-");
    }
  },
  mutations: {
    filterItems(state, searchResult) {
      state.filteredItems = searchResult;
      searchResult.forEach((res) => {
        let exists = false;
        state.auctions.forEach((auction) => {
          if (res.id === auction.id) exists = true;
        });
        if (!exists) state.auctions.push(res);
      })

    },
    addAuction(state, value) {
      let exists = false;
      state.auctions.forEach((auction) => {
        if (value.id === auction.id) exists = true;
      });
      if (!exists) state.auctions.push(value);
    },
    getAuctions(state, value) {
      state.auctions = value;
      console.log('Auctions: ', state.auctions);
    },
    setActiveAuction(state, activeAuction) {
      state.activeAuction = activeAuction;
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
    setCurrentBid(state, bid) {
      state.currentBid = bid;
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
    removeNotification(state, item) {
      let index = state.notifications.indexOf(item);
      state.notifications.splice(index, 1);
      state.notificationBadge = false;
    },
    webSocket(state, data) {
      let notify = {
        icon: '',
        title: '',
        subtitle: '',
        route: '',
        params: ''
      }

      // update state depending on incoming action
      switch (data.action) {
        case "bid":
          let bid = data.payload;

          let subscribedAuction = false

          let auctionTitle;
          state.auctions.forEach(a => {
            if (a.id === bid.auctionId) {

              // check if user has placed a bid on that auction
              for (let b of a.bids) {
                if (b.username === state.currentUser.username)
                  console.log(b)
                subscribedAuction = true
                break;
              }
              a.bids.push(bid)
              auctionTitle = a.title
            }
          })

          //  don't notify yourself
          if (bid.username === state.currentUser.username || !subscribedAuction) return;

          state.notificationBadge = true
          notify.icon = 'monetization_on'
          notify.title = `Utbudad på ${auctionTitle}`
          notify.subtitle = `Nytt bud: ${bid.sum}kr, av ${bid.username}`
          notify.route = `/auction/${bid.auctionId}`

          state.notifications.unshift(notify)
          break;
        case "message":
          let message = data.payload

          if (message.sender !== state.chatRecipient) {
            notifyMessage(state, message)
            return;
          }
          state.chatMessages.push(message)

          if (message.sender !== state.currentUser.username)
            notifyMessage(state, message)
          break;
      }
    }
  },
})

