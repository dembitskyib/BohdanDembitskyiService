package com.epam.lab.service.soap;

import javax.jws.WebService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.service.bo.UserBO;
import com.epam.lab.service.model.Role;
import com.epam.lab.service.responce.ResponseMessage;
import com.epam.lab.service.responce.SoapResponse;

@WebService(endpointInterface = "com.epam.lab.service.soap.SoapUserService")
public class SoapUserServiceImpl implements SoapUserService {
	private UserBO userBO;
	private static final Logger LOGGER = LogManager.getLogger(SoapUserServiceImpl.class);
	private final String RESPONDING_MESSAGE = "Responding SOAP %s request with credentials: %s, %s";
	private final String USER_TO_DELETE = " (searched user: %s)";
	private final String ROLE_TO_FIND = " (role: %s)";

	public SoapUserServiceImpl() {
		userBO = new UserBO();
	}

	@Override
	public SoapResponse authorize(String userName, String password) {
		LOGGER.info(String.format(RESPONDING_MESSAGE, "authorize", userName, password));
		if (userBO.authenticate(userName, password).contains(Role.GUEST)) {
			return new SoapResponse(ResponseMessage.INVALID_CREDENTIALS);
		}
		return new SoapResponse(ResponseMessage.AUTHORIZATION_SUCCESS, new Object[] { userBO.getUser(userName) });
	}

	@Override
	public SoapResponse authenticate(String userName, String password) {
		LOGGER.info(String.format(RESPONDING_MESSAGE, "authenticate", userName, password));
		if (userBO.authenticate(userName, password).contains(Role.GUEST)) {
			return new SoapResponse(ResponseMessage.INVALID_CREDENTIALS);
		}
		return new SoapResponse(ResponseMessage.AUTHENTICATION_SUCCESS);
	}

	@Override
	public SoapResponse getUsers(String userName, String password) {
		LOGGER.info(String.format(RESPONDING_MESSAGE, "getUsers", userName, password));
		if (userBO.authenticate(userName, password).contains(Role.GUEST)) {
			return new SoapResponse(ResponseMessage.INVALID_CREDENTIALS);
		}
		if (userBO.authenticate(userName, password).contains(Role.ADMIN)) {
			return new SoapResponse(ResponseMessage.AUTHORIZATION_SUCCESS, (Object[]) userBO.getUsers().toArray());
		}
		return new SoapResponse(ResponseMessage.FORBIDDEN_OPERATION);
	}

	@Override
	public SoapResponse getUsersByRole(String userName, String password, String role) {
		LOGGER.info(String.format(RESPONDING_MESSAGE + ROLE_TO_FIND, "getUsersByRole", userName, password, role));
		if (userBO.authenticate(userName, password).contains(Role.GUEST)) {
			return new SoapResponse(ResponseMessage.INVALID_CREDENTIALS);
		}
		if (userBO.authenticate(userName, password).contains(Role.ADMIN)) {
			return new SoapResponse(ResponseMessage.AUTHORIZATION_SUCCESS,
					(Object[]) userBO.getUsersByRole(role).toArray());
		}
		return new SoapResponse(ResponseMessage.FORBIDDEN_OPERATION);
	}

	@Override
	public SoapResponse deleteUserById(String userName, String password, String name) {
		LOGGER.info(String.format(RESPONDING_MESSAGE + USER_TO_DELETE, "deleteUser", userName, password, name));
		if (userBO.authenticate(userName, password).contains(Role.GUEST)) {
			return new SoapResponse(ResponseMessage.INVALID_CREDENTIALS);
		}
		if (userBO.authenticate(userName, password).contains(Role.ADMIN)) {
			if (userBO.removeUser(name)) {
				return new SoapResponse(ResponseMessage.USER_REMOVED);
			} else
				return new SoapResponse(ResponseMessage.USER_NOT_FOUND);
		}
		return new SoapResponse(ResponseMessage.FORBIDDEN_OPERATION);
	}

}
