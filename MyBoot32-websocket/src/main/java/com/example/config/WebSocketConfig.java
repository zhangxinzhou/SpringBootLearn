package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		/*注册一个Stomp的节点（endpoint）,并指定使用SockJS协议。*/
		registry.addEndpoint("/endpointWisely").withSockJS();		
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		/*配置消息代理（MessageBroker）*/
		registry.enableSimpleBroker("/topic");
	}

	
}
