package com.nd.rock.common.thread;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class NamedRejectedHandler implements RejectedExecutionHandler {
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		throw new RejectedExecutionException("Task " + r.toString() +
                " rejected from " + executor.toString());
	}
	
}