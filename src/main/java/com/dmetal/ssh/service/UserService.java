package com.dmetal.ssh.service;

import java.util.List;
import java.util.Map;

import com.dmetal.ssh.entity.User;

public interface UserService {
	List<Map<String,Object>> userList();
	boolean saveUser(String name,String password,Integer age,Double salary);
	User loadUser(Integer id);
	void updateUser(Integer id, String name, String password, Integer age, Double salary);
}
