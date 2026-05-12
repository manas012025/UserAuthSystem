package com.UserAuthSystem.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class AsyncConfig {
	@Bean("CommonExecutor")
	public Executor commonExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(50);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("OCR-SAVE-");
		executor.setTaskDecorator(new SecurityContextAsyncTaskDecorator());
		executor.initialize();
		return executor;
	}
}


