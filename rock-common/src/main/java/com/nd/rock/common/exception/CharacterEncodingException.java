package com.nd.rock.common.exception;

public class CharacterEncodingException extends RuntimeException {

	private static final long serialVersionUID = -6806814365793702698L;

	public CharacterEncodingException(String message, Throwable cause){
        super(message, cause);
    }

    public CharacterEncodingException(String message){
        super(message);
    }

    public CharacterEncodingException(Throwable cause){
        super(cause);
    }
}
