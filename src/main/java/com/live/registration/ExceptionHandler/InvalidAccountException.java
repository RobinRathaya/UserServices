package com.live.registration.ExceptionHandler;

public class InvalidAccountException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAccountException(String message) {
		
		super(message);
		
	}

}
