package com.sfm.model;

import java.util.ArrayList;
import java.util.List;

public class User {

	public static List<User> createUsers() {
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new User());
		return userList;
	}

}
