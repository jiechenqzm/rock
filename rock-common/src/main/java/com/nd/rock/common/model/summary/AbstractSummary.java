package com.nd.rock.common.model.summary;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nd.rock.common.util.QStringUtil;

public abstract class AbstractSummary implements SummaryBehavior {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractSummary.class);
	
	private static final String NULL_SUMMARY = "NULL";

	@Override
	public String calculate(String content) {
		
		if(QStringUtil.isNull(content))
			return NULL_SUMMARY;

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(this.getAlgorithmName());
		} catch (NoSuchAlgorithmException e) {
			logger.error("Unsupported Summary Algorithm.", e);
			return null;
		}

		messageDigest.update(content.getBytes());

		byte[] tmp = messageDigest.digest();
		StringBuilder resultBuilder = new StringBuilder();
		for (byte b : tmp) {
			resultBuilder.append(Integer.toHexString(b & 0xff));
		}
		return resultBuilder.toString();
	}

	protected abstract String getAlgorithmName();

}
