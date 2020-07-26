package com.veasymall.api.controller;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veasymall.api.pojo.User;

@RestController
@RequestMapping("mq")
public class MqController {

	final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	/**
	 * 同步发送
	 */
	@RequestMapping("/syncSend")
	public void syncSend() {

		SendResult sendResult = rocketMQTemplate.syncSend("spring_topic", "hello rocketmq");

		logger.info("syncSend1 to topic {}, sendResult = {} ", "spring_topic", sendResult);

		String userTopic = "user_topic";

		User user = new User();
		user.setAccount("admin1");
		user.setPassword("123456");
		user.setName("admin");

		for (int i = 0; i < 100; i++) {

			user.setAccount("admin1" + i);

			sendResult = rocketMQTemplate.syncSend(userTopic, user);

			logger.info("syncSend2 to topic {}, sendResult = {} ", userTopic, sendResult);
		}

		for (int i = 0; i < 100; i++) {
			user.setAccount("admin2" + i);

			sendResult = rocketMQTemplate.syncSend(userTopic, MessageBuilder.withPayload(user)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE).build());

			logger.info("syncSend3 to topic {}, sendResult = {} ", userTopic, sendResult);
		}

	}

	@RequestMapping("/asyncSend")
	public void asyncSend() {

		String orderPaidTopic = "order_paid_topic";

		rocketMQTemplate.asyncSend(orderPaidTopic, "order_paid", new SendCallback() {

			@Override
			public void onSuccess(SendResult sendResult) {

				logger.info("async onSuccess SendResult = {}", sendResult);

			}

			@Override
			public void onException(Throwable e) {

				logger.error(e.getMessage());

			}

		});

	}

	@RequestMapping("/sendOneWay")
	public void sendOneWay() {

		String orderPaidTopic = "order_paid_topic";

		rocketMQTemplate.sendOneWay(orderPaidTopic, "order paid one way");
	}

	@RequestMapping("/sendMessageInTransaction")
	public void transactionalRocketMQTemplate() {
		String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD", "TagE" };
		for (int i = 0; i < 10; i++) {
			try {

				Message msg = MessageBuilder.withPayload("rocketMQTemplate transactional message " + i)
						.setHeader(RocketMQHeaders.TRANSACTION_ID, "KEY_" + i).build();
				SendResult sendResult = rocketMQTemplate
						.sendMessageInTransaction("springTransTopic" + ":" + tags[i % tags.length], msg, null);
				System.out.printf("------rocketMQTemplate send Transactional msg body = %s , sendResult=%s %n",
						msg.getPayload(), sendResult.getSendStatus());

				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
