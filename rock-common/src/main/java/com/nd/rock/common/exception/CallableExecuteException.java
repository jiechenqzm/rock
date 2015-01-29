package com.nd.rock.common.exception;

public class CallableExecuteException extends RuntimeException {

	private static final long serialVersionUID = -6402967545798844460L;

	public CallableExecuteException(String message, Throwable cause){
        super(message, cause);
    }

    public CallableExecuteException(String message){
        super(message);
    }

    public CallableExecuteException(Throwable cause){
        super(cause);
    }
}
