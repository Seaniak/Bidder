import Vue from "vue";

let ws;

let isConnected = false;
connect();

function connect() {
  ws = new WebSocket('ws://localhost:8070/ultraSecretSocketAddress/websocket');
  ws.onmessage = (e) => {
    let data = JSON.parse(e.data);

    switch (data.action) {
      case "bid":
        console.log(data.payload)
        break;
      case "message":
        console.log(data.payload)
        break;
      default:
    }
  }
  ws.onopen = (e) => {
    sendSomething();
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

function sendSomething() {
  let data = {
    action: 'Hello',
    payload: 'Hello World!'
  }
  ws.send(JSON.stringify(data));
}
