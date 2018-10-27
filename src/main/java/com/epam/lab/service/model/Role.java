package com.epam.lab.service.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "role")
public enum Role {

	GUEST(1), USER(2), ADMIN(3);

	private int id;

	private Role(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
