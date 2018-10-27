package com.epam.lab.service.rest;

import javax.ws.rs.core.Response;

import com.epam.lab.service.model.Role;
import com.epam.lab.service.responce.ResponseMessage;
import com.epam.lab.service.responce.RestResponseBuilder;
import com.epam.lab.service.rest.RestUserService;
import com.epam.lab.service.bo.UserBO;

import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestUserServiceImpl implements RestUserService {
	private UserBO userBO;
	private static final Logger LOGGER = LogManager.getLogger(RestUserServiceImpl.class);
	private final String RESPONDING_MESSAGE = "Responding REST %s request with credentials: %s, %s";
	private final String USER_TO_DELETE = " (searched user: %s)";
	private final String ROLE_TO_FIND = " (role: %s)";

	public RestUserServiceImpl() {
		userBO = new UserBO();
	}

	@Override
	public Response authorize(String userName, String password) {
		LOGGER.info(String.format(RESPONDING_MESSAGE, "authorize", userName, password));
		if (userBO.authenticate(userName, password).contains(Role.GUEST)) {
			return RestResponseBuilder.getResponse(Status.UNAUTHORIZED, ResponseMessage.INVALID_CREDENTIALS);
		}
		return RestResponseBuilder.getResponse(Status.OK, userBO.getUserRoles(userName));
	}

	@Override
	public Response getUsers(String userName, String password) {
		LOGGER.info(String.format(RESPONDING_MESSAGE, "getUsers", userName, password));
		if (userBO.authenticate(userName, password).contains(Role.GUEST)) {
			return RestResponseBuilder.getResponse(Status.UNAUTHORIZED, ResponseMessage.INVALID_CREDENTIALS);
		}
		if (userBO.authenticate(userName, password).contains(Role.ADMIN)) {
			return RestResponseBuilder.getResponse(Status.OK, userBO.getUsers());
		}
		return RestResponseBuilder.getResponse(Status.FORBIDDEN, ResponseMessage.FORBIDDEN_OPERATION);
	}

	@Override
	public Response getUsersByRole(String userName, String password, String role) {
		LOGGER.info(String.format(RESPONDING_MESSAGE + ROLE_TO_FIND, "getUsersByRole", userName, password, role));
		if (userBO.authenticate(userName, password).contains(Role.GUEST)) {
			return RestResponseBuilder.getResponse(Status.UNAUTHORIZED, ResponseMessage.INVALID_CREDENTIALS);
		}
		if (userBO.authenticate(userName, password).contains(Role.ADMIN)) {
			return RestResponseBuilder.getResponse(Status.OK, userBO.getUsersByRole(role));
		}
		return RestResponseBuilder.getResponse(Status.FORBIDDEN, ResponseMessage.FORBIDDEN_OPERATION);
	}

	@Override
	public Response deleteUser(String userName, String password, String name) {
		LOGGER.info(String.format(RESPONDING_MESSAGE + USER_TO_DELETE, "deleteUser", userName, password, name));
		if (userBO.authenticate(userName, password).contains(Role.GUEST)) {
			return RestResponseBuilder.getResponse(Status.UNAUTHORIZED, ResponseMessage.INVALID_CREDENTIALS);
		}
		if (userBO.authenticate(userName, password).contains(Role.ADMIN)) {
			if (userBO.removeUser(name)) {
				return RestResponseBuilder.getResponse(Status.OK, ResponseMessage.USER_REMOVED);
			} else
				return RestResponseBuilder.getResponse(Status.NOT_FOUND, ResponseMessage.USER_NOT_FOUND);
		}
		return RestResponseBuilder.getResponse(Status.FORBIDDEN, ResponseMessage.FORBIDDEN_OPERATION);
	}

	@Override
	public Response authenticate(String userName, String password) {
		LOGGER.info(String.format(RESPONDING_MESSAGE, "authenticate", userName, password));
		if (userBO.authenticate(userName, password).contains(Role.GUEST)) {
			return RestResponseBuilder.getResponse(Status.UNAUTHORIZED, ResponseMessage.INVALID_CREDENTIALS);
		}
		return RestResponseBuilder.getResponse(Status.OK, ResponseMessage.AUTHENTICATION_SUCCESS);
	}

}
