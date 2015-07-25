package com.sfm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfm.dao.Dao;
import com.sfm.model.User;



@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private Dao dao;
	
	@Transactional
	public void addUser(User user) {
		dao.add(user);
	}

	@Transactional
	public void removeUser(Integer id) {
		dao.remove(id);
	}

	@Transactional
	public List<User> listUsers() {
		return dao.list(User.class);
	}

	@Transactional
	public void updateUser(User user) {
		dao.update(user);
	}

	@Transactional
	public User getUserById(Integer userId) {
		return (User) dao.getById(userId,User.class);
	}

	@Override
	public List<User> listUsers(Integer deptId, Integer year, Integer semester) {
		return dao.list(deptId, year, semester,User.class);
	}

	@Override
	public List<User> listUsersByName(String userName) {
		return dao.listUsersByName(userName) ;
	}

	@Override
	public User validateUser(String username, String password) {
		// TODO Auto-generated method stub
		return dao.validateUser(username,password);
	}
}