package com.nd.rock.server.exception;

public class ServiceBehaviorFailException extends RuntimeException {

	private static final long serialVersionUID = 7418746880756858826L;

	public ServiceBehaviorFailException(String message, Throwable cause){
        super(message, cause);
    }

    public ServiceBehaviorFailException(String message){
        super(message);
    }

    public ServiceBehaviorFailException(Throwable cause){
        super(cause);
    }
}
