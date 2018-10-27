package com.epam.lab.service.bo;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.service.dao.UserDAO;
import com.epam.lab.service.model.Role;
import com.epam.lab.service.model.User;

public class UserBO {
	private UserDAO userDAO;

	public UserBO() {
		userDAO = new UserDAO();
	}

	public User getUser(String name) {
		List<User> users = userDAO.getUsers();
		User searchedUser = new User();
		for (User user : users) {
			if (user.getUserName().equals(name))
				searchedUser = user;
		}
		return searchedUser;
	}

	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	public boolean removeUser(String name) {
		return userDAO.removeUser(name);
	}

	public List<Role> getUserRoles(String name) {
		return userDAO.getUserRoles(name);
	}

	public List<User> getUsersByRole(String role) {
		return userDAO.getUsersByRole(role);
	}

	public List<Role> authenticate(String userName, String password) {
		List<User> userList = userDAO.getUsers();
		List<Role> roleList = new ArrayList<>();
		roleList.add(Role.GUEST);
		for (User user : userList) {
			if (user.getUserName().equals(userName)) {
				if (user.getPassword().equals(password)) {
					roleList.remove(Role.GUEST);
					roleList.addAll(user.getRoleList());
				}
			}
		}
		return roleList;
	}

}
