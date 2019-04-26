package web.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import web.controllers.SocketController;
import web.services.SocketService;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  @Autowired
  SocketService socketService;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    SocketController sc = new SocketController();
    sc.setSocketService(socketService);
    registry.addHandler(sc, "/ultraSecretSocketAddress").setAllowedOrigins("*")
            .setHandshakeHandler(new DefaultHandshakeHandler(new TomcatRequestUpgradeStrategy()))
            .withSockJS();
  }
}
