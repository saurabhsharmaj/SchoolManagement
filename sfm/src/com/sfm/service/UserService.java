package com.sfm.service;

import java.util.List;

import com.sfm.model.User;



public interface UserService {
	
	public void addUser(User user);
	public void updateUser(User user);
	public User getUserById(Integer userId);
	public List<User> listUsers();
	public List<User> listUsers(Integer deptId, Integer year, Integer semester);
	public void removeUser(Integer id);
	public List<User> listUsersByName(String userName);
	public User validateUser(String username, String password);

}
