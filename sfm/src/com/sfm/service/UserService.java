package com.sfm.service;

import java.util.List;

import com.sfm.model.Data;
import com.sfm.model.User;
import com.sfm.util.JQueryDataTableParamModel;



public interface UserService {
	
	public void addUser(User user);
	public void updateUser(User user);
	public User getUserById(Integer userId);
	public List<User> listUsers();
	public Data listUsers(JQueryDataTableParamModel param);
	public void removeUser(User user);
	public List<User> listUsersByName(String userName);
	public User validateUser(String username, String password);
	public int listCount();

}
