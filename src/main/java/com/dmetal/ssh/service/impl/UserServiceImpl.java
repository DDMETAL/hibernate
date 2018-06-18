package com.dmetal.ssh.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmetal.ssh.dao.UserDAO;
import com.dmetal.ssh.entity.User;
import com.dmetal.ssh.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserDAO userDAO;
	public List<Map<String, Object>> userList() {
		
		return userDAO.findAllUsers();
	}
	public boolean saveUser(String name, String password, Integer age, Double salary) {
		long now=System.currentTimeMillis();
//		int max=userDAO.findMaxId();
//		int id=max+1;
		User user=new User(null,name,password,age,salary,new Date(now));
		int n=userDAO.addUser(user);
		System.out.println(user);//id会传回
		return n==1;
	}
	public User loadUser(Integer id) {
		if(id==null) {
			throw new RuntimeException("id不能为空");
		}
		
		return userDAO.findById(id);
	}
	public void updateUser(Integer id, String name, String password, Integer age, Double salary) {
		User user=userDAO.findById(id);
		user.setName(name);
		user.setPassword(password);
		user.setAge(age);
		user.setSalary(salary);
		long now=System.currentTimeMillis();
		user.setHiredate(new Date(now));
		userDAO.updateUser(user);
		
	}

}
