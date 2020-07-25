package com.veasymall.api.tasks;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {
	
	// 异步执行使用场景
	// 发送短信、发送邮件、App消息推送、节省运维凌晨发布任务时间

	@Async
	public Future<Boolean> doTask1() throws Exception {

		long start = System.currentTimeMillis();
		Thread.sleep(1000);
		long end = System.currentTimeMillis();
		System.out.println("任务1耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>(true);
	}

	@Async
	public Future<Boolean> doTask2() throws Exception {

		long start = System.currentTimeMillis();
		Thread.sleep(700);
		long end = System.currentTimeMillis();
		System.out.println("任务2耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>(true);
	}

	@Async
	public Future<Boolean> doTask3() throws Exception {

		long start = System.currentTimeMillis();
		Thread.sleep(800);
		long end = System.currentTimeMillis();
		System.out.println("任务3耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>(true);
	}
}
