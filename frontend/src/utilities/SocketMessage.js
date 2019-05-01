import Vue from "vue";

export function notifyMessage(state, message) {
  let notify = {
    key: message.sender,
    icon: 'chat',
    title: 'Nytt meddelande',
    subtitle: `Från ${message.sender}: ${message.text}`,
    time: new Date(),
    route: 'chat',
    params: {
      recipient: message.sender
    }
  }
  // same as state.notifications[notify.key] = notify
  //  but with Vue.set the object triggers reactivity
  //  on update
  Vue.set(state.notifications, notify.key, notify)
  state.notificationBadge = true
}