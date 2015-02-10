package com.nd.rock.common.exception;

public class FileAccessException extends RuntimeException {

	private static final long serialVersionUID = 8841581288845745024L;

	public FileAccessException(String message, Throwable cause){
        super(message, cause);
    }

    public FileAccessException(String message){
        super(message);
    }

    public FileAccessException(Throwable cause){
        super(cause);
    }
}
