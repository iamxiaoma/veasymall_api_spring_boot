package com.veasymall.api.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	// 定义每3秒执行任务
	@Scheduled(fixedRate = 3000)
	public void reportCurrentTime() {
		System.out.println("现在时间：" + dateFormat.format(new Date()));
	}

	/**
	 * 不支持指定到每年，只能填写6位 https://cron.qqe2.com/
	 */
	@Scheduled(cron = "4-40 * * * * ? ")
	public void cronSchedule() {
		System.out.println("Cron 现在时间：" + dateFormat.format(new Date()));
	}

}
