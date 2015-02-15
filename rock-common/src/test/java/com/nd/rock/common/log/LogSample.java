package com.nd.rock.common.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogSample {
	
    private static final Logger logger = LoggerFactory.getLogger(LogSample.class);

	@Test
	public void test() {
		logger.info("LOG!!!");
	}

}
