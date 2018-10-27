package com.epam.lab.service.dao;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.service.model.Role;
import com.epam.lab.service.model.User;
import com.epam.lab.service.utils.CSVParser;

public class UserDAO {
	private final URL USERS_PATH = this.getClass().getResource("/users.csv");

	public List<User> getUsers() {
		return CSVParser.getUsers(USERS_PATH);
	}

	public boolean removeUser(String name) {
		return CSVParser.removeUser(USERS_PATH, name);
	}

	public List<Role> getUserRoles(String name) {
		List<User> userList = CSVParser.getUsers(USERS_PATH);
		List<Role> roleList = new ArrayList<>();
		for (User user : userList) {
			if (user.getUserName().equals(name)) {
				roleList = user.getRoleList();
			}
		}
		return roleList;
	}

	public List<User> getUsersByRole(String role) {
		List<User> userList = CSVParser.getUsers(USERS_PATH);
		List<User> resultUsers = new ArrayList<>();
		for (User user : userList) {
			if (user.getRoleList().contains(Role.valueOf(role.toUpperCase()))) {
				resultUsers.add(user);
			}
		}
		return resultUsers;
	}
}
