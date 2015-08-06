package com.sfm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfm.dao.Dao;
import com.sfm.model.Data;
import com.sfm.model.User;
import com.sfm.util.JQueryDataTableParamModel;


@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private Dao dao;
	
	@Transactional
	public void addUser(User user) {
		dao.save(user);
	}

	@Transactional
	public void removeUser(User user) {
		dao.remove(user);
	}

	@Transactional
	public List<User> listUsers() {
		return dao.list(User.class);
	}

	@Transactional
	public void updateUser(User user) {
		dao.save(user);
	}

	@Transactional
	public User getUserById(Integer userId) {
		return (User) dao.getById(userId,User.class);
	}

	@Transactional
	@Override
	public Data listUsers(JQueryDataTableParamModel param) {
		return dao.list(param, User.class);
	}

	@Transactional
	@Override
	public List<User> listUsersByName(String userName) {
		return dao.listUsersByName(userName) ;
	}

	@Transactional
	@Override
	public User validateUser(String username, String password) {
		return dao.validateUser(username,password);
	}

	@Transactional
	@Override
	public int listCount() {
		return dao.listCount(User.class);
	}
}