package com.flipkart.exception;

//Exception arises when appropriate student details cant be found or are not present
public class StudentNotRegisteredException extends Exception {

	@Override
	public String getMessage() {
		return "No such registered student was found";
	}
}
