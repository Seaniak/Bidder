export function notifyMessage(state, message) {
  let notify = {
    icon: 'chat',
    title: 'Nytt meddelande',
    subtitle: `Från ${message.sender}: ${message.text}`,
    route: 'chat',
    params: {
      recipient: message.sender
    }
  }
  state.notificationBadge = true
  state.notifications.unshift(notify)
}