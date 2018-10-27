package com.epam.lab.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.epam.lab.service.responce.SoapResponse;

@WebService
public interface SoapUserService {

	@WebMethod(operationName = "authorize")
	SoapResponse authorize(@WebParam(name = "userName") String userName, @WebParam(name = "password") String password);

	@WebMethod(operationName = "authenticate")
	SoapResponse authenticate(@WebParam(name = "userName") String userName,
			@WebParam(name = "password") String password);

	@WebMethod(operationName = "getAllUsers")
	SoapResponse getUsers(@WebParam(name = "userName") String userName, @WebParam(name = "password") String password);

	@WebMethod(operationName = "getUsersByRole")
	SoapResponse getUsersByRole(@WebParam(name = "userName") String userName,
			@WebParam(name = "password") String password, @WebParam(name = "role") String role);

	@WebMethod(operationName = "deleteUser")
	SoapResponse deleteUserById(@WebParam(name = "userName") String userName,
			@WebParam(name = "password") String password, @WebParam(name = "name") String name);

}
