package com.nd.rock.common.exception;

import java.rmi.RemoteException;

public class ServerUnAvailableException extends RemoteException {

	private static final long serialVersionUID = -3248812324766388218L;

	public ServerUnAvailableException(String message, Throwable cause){
        super(message, cause);
    }

    public ServerUnAvailableException(String message){
        super(message);
    }
}
