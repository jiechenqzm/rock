package com.nd.rock.server.controller.multithread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {

	private final ThreadGroup group;
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;

	public NamedThreadFactory(String threadType) {
		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread()
				.getThreadGroup();
		namePrefix = threadType + "-TASK-";
	}

	@Override
	public Thread newThread(Runnable runnable) {
		Thread thread = new Thread(group, runnable, namePrefix
				+ threadNumber.getAndIncrement(), 0);
		if (thread.isDaemon())
			thread.setDaemon(false);
		if (thread.getPriority() != Thread.NORM_PRIORITY)
			thread.setPriority(Thread.NORM_PRIORITY);
		return thread;
		
	}
}
