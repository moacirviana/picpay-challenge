package com.pp.picpaychallenge.exception;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 191682764517400741L;

	public DataIntegrityException (String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super (msg, cause);
	}

}