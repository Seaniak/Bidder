import {eventBus} from '@/main'

let ws;
let isConnected = false;
connect();

function connect() {
  ws = new WebSocket('ws://localhost:8070/ultraSecretSocketAddress/websocket');
  ws.onmessage = (e) => {
    let data = JSON.parse(e.data);

    // sends incoming data to the Vue instance
    // where it gets commited to store
    eventBus.$emit('socket-data', data)
  }
  ws.onopen = (e) => {
    console.log("[Web Socket] Connected");
    isConnected = true;
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

function disconnect() {
  if (ws != null) {
    ws.close();
  }
  isConnected = false;
  console.log("[Web Socket] Disconnected");
}

export function sendMessage(message) {
  let data = {
    action: 'message',
    payload: message
  }
  ws.send(JSON.stringify(data));
}
