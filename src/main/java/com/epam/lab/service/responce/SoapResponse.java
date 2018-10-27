package com.epam.lab.service.responce;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;

import com.epam.lab.service.model.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class SoapResponse {
	private String message;
	@XmlElementRefs({ @XmlElementRef(type = User.class) })
	private Object[] users;

	public SoapResponse() {

	}

	public SoapResponse(String message) {
		this.message = message;
	}

	public SoapResponse(String message, Object[] users) {
		this.message = message;
		this.users = users;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getUsers() {
		return users;
	}

	public void setUsers(Object[] users) {
		this.users = users;
	}

}
