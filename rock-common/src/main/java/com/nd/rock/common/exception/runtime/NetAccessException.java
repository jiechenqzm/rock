package com.nd.rock.common.exception.runtime;

public class NetAccessException extends RuntimeException {

	private static final long serialVersionUID = -6452271173265689532L;

	public NetAccessException(String message, Throwable cause){
        super(message, cause);
    }

    public NetAccessException(String message){
        super(message);
    }

    public NetAccessException(Throwable cause){
        super(cause);
    }
}
