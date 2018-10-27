package com.epam.lab.service.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User {
	private String userName;
	private String password;
	@XmlElement(name = "role")
	private List<Role> roleList;

	public User() {
		roleList = new ArrayList<>();
	}

	public User(String userName, String password, String role) {
		roleList = new ArrayList<Role>();
		this.userName = userName;
		this.password = password;
		roleList.add(Role.valueOf(role.toUpperCase()));
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void addRole(String role) {
		roleList.add(Role.valueOf(role.toUpperCase()));
	}
}
