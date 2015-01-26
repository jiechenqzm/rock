package com.nd.rock.server.model.summary;


public class MD5Summary extends AbstractSummary {
	
	private static final String MD5_ALGORITHM = "MD5";
	
	@Override
	protected String getAlgorithmName() {
		return MD5_ALGORITHM;
	}

}
