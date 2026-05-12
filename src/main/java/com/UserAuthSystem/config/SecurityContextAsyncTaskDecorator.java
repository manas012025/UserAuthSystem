package com.UserAuthSystem.config;

import org.springframework.core.task.TaskDecorator;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextAsyncTaskDecorator implements TaskDecorator {
	@Override
	public Runnable decorate(Runnable runnable) {
		SecurityContext context = SecurityContextHolder.getContext(); // capture current user
		return () -> {
			try {
				SecurityContextHolder.setContext(context); // set in async thread
				runnable.run();
			} finally {
				SecurityContextHolder.clearContext();
			}
		};
	}
}
 

