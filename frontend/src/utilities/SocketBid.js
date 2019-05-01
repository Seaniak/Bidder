import Vue from "vue";

export function notifyBid(state, bid){
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

  let notify = {
    icon: 'monetization_on',
    title: `Utbudad på ${auctionTitle}`,
    subtitle: `Nytt bud: ${bid.sum}kr, av ${bid.username}`,
    time: new Date(),
    route: `/auction/${bid.auctionId}`,
    key: bid.auctionId
  }
  // same as state.notifications[notify.key] = notify
  //  but with Vue.set the object triggers reactivity
  //  on update
  Vue.set(state.notifications, notify.key, notify)
  state.notificationBadge = true
}