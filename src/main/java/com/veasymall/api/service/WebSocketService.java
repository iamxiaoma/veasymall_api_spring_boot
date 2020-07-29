/**
 * @Description: TODO(描述）
 * @author machaoyi
 * @date 2020-07-29 03:55:41
 */
package com.veasymall.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

/**
 * @author machaoyi
 *
 */
@Component
@ServerEndpoint("/ws/{merchantId}")
public class WebSocketService {

	static final ConcurrentHashMap<String, List<WebSocketClient>> webSocketClientMap = new ConcurrentHashMap<>();

	/**
	 * 连接建立成功时触发，绑定参数
	 * 
	 * @param session    与某个客户端的连接会话，需要通过它来给客户端发送数据
	 * @param merchantId 商户ID
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("merchantId") String merchantId) {

		WebSocketClient client = new WebSocketClient();
		client.setSession(session);
		client.setUri(session.getRequestURI().toString());

		System.out.println("onOpen：" + session);
		System.out.println("merchantId:" + merchantId);

		List<WebSocketClient> webSocketClientList = webSocketClientMap.get(merchantId);
		if (webSocketClientList == null) {
			webSocketClientList = new ArrayList<>();
		}
		webSocketClientList.add(client);
		webSocketClientMap.put(merchantId, webSocketClientList);
	}

	/**
	 * 收到客户端消息后调用的方法
	 */
	@OnMessage
	public void onMessage(String message, Session session, @PathParam("merchantId") String merchantId)
			throws IOException {
		System.out.println("来自客户端的消息:" + message + "   ID是：" + session.getId());

		sendMessage(merchantId, "服务器消息！");

	}

	/**
	 * 连接关闭时触发，注意不能向客户端发送消息了
	 * 
	 * @param merchantId
	 */
	@OnClose
	public void onClose(@PathParam("merchantId") String merchantId) {
		System.out.println("链接关闭");
		webSocketClientMap.remove(merchantId);
	}

	/**
	 * 通信发生错误时触发
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	/**
	 * 向客户端发送消息
	 * 
	 * @param merchantId
	 * @param message
	 */
	public static void sendMessage(String merchantId, String message) {
		try {
			List<WebSocketClient> webSocketClientList = webSocketClientMap.get(merchantId);
			if (webSocketClientList != null) {
				for (WebSocketClient webSocketServer : webSocketClientList) {
					webSocketServer.getSession().getBasicRemote().sendText(message);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

}
