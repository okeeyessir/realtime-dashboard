import SockJS from "sockjs-client";
import Stomp from "stompjs";

let stompClient = null;

export function connect(callback) {
  const socket = new SockJS("http://localhost:8080/ws");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, () => {
    stompClient.subscribe("/topic/updates", (msg) => {
      callback(msg.body);
    });
  });
}

export function sendMessage(message) {
  if (stompClient && stompClient.connected) {
    stompClient.send("/app/send", {}, message);
  }
}