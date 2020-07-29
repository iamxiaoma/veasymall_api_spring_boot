/**
 * @Description: TODO(描述）
 * @author machaoyi
 * @date 2020-07-29 04:01:54
 */
package com.veasymall.api.service;

import javax.websocket.Session;

import lombok.Data;

/**
 * @author machaoyi
 *
 */
@Data
public class WebSocketClient {

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	// 连接的uri
	private String uri;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
