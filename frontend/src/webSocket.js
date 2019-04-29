import {eventBus} from '@/main'

let ws;
let isConnected = false;
connect();

export function connect() {
  ws = new WebSocket('ws://localhost:8070/ultraSecretSocketAddress/websocket');

  ws.onmessage = (e) => {
    let data = JSON.parse(e.data);
    // sends incoming data to the Vue instance
    // where it gets commited to store
    eventBus.$emit('socket-data', data)
  }

  ws.onopen = (e) => {
    console.log("[Web Socket] Connected");

    let connectData = {
      action: 'connect',
      payload: window.socketUsername
    }
    ws.send(JSON.stringify(connectData))
  };

  ws.onclose = (e) => {

    isConnected = false;
    console.log("[Web Socket] Disconnected");

    setTimeout(() => {
      console.log("[Web Socket] Trying to reconnect..")
      connect()
    }, 2000)
  };
}

export function disconnect() {
  if (ws != null) {
    ws.close();
  }
  console.log("[Web Socket] Disconnected");
}

export function sendMessage(message) {
  let data = {
    action: 'message',
    payload: message
  }
  ws.send(JSON.stringify(data));
}
export function sendData(data) {
  ws.send(JSON.stringify(data));
}

export function updateConnection() {
  let data = {
    action: 'connect',
    payload: window.socketUsername
  }
  ws.send(JSON.stringify(data));
}

export function logoutConnection() {
  let data = {
    action: 'logout',
    payload: window.socketUsername
  }
  ws.send(JSON.stringify(data));
}
