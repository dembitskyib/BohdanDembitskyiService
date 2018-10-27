package com.epam.lab.service.responce;

public class ResponseMessage {
	public static final String AUTHENTICATION_SUCCESS = "Authentication done successfully";
	public static final String USER_REMOVED = "User has been successfully removed";
	public static final String AUTHORIZATION_SUCCESS = "Authorization done successfully";

	public static final String INVALID_CREDENTIALS = "User name or password are incorrect";
	public static final String FORBIDDEN_OPERATION = "You have not access to this operation";
	public static final String USER_NOT_FOUND = "No users with this user name found";

	private ResponseMessage() {

	}
}
