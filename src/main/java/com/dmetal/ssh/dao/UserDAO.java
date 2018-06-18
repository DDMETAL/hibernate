package com.dmetal.ssh.dao;

import java.util.List;
import java.util.Map;

import com.dmetal.ssh.entity.User;

public interface UserDAO {
	int addUser(User user);
	int updateUser(User user);
	int deleteUser(Integer id);
	User findById(Integer id);
	List<User> findAll();
	List<Map<String,Object>> findAllUsers();
	User findByName(String name);
	int findMaxId();
	
}
