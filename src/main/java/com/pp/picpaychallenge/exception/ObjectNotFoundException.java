package com.pp.picpaychallenge.exception;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -8567505308668576619L;

	public ObjectNotFoundException (String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super (msg, cause);
	}

}
