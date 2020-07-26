package com.veasymall.api.consumer;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.veasymall.api.controller.UserController;
import com.veasymall.api.utils.JsonUtils;

@Service
@RocketMQMessageListener(consumerGroup = "${rocketmq.producer.group}", topic = "user_topic", consumeMode = ConsumeMode.ORDERLY)
public class MyConsumer implements RocketMQListener<Object> {

	final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Override
	public void onMessage(Object data) {

		logger.info("consumer user = {}", JsonUtils.objectToJson(data));

	}

}
