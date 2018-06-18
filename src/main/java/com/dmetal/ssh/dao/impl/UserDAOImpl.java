package com.dmetal.ssh.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dmetal.ssh.dao.UserDAO;
import com.dmetal.ssh.entity.User;


@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public int addUser(User user) {
		Object id=hibernateTemplate.save(user);
		return id==null?0:1;
	}
	public int updateUser(User user) {
		hibernateTemplate.update(user);
		return 1;
	}
	public int deleteUser(Integer id) {
		User user=hibernateTemplate.get(User.class, id);
		hibernateTemplate.delete(user);
		return 1;
	}
	public User findById(Integer id) {
		User user=hibernateTemplate.get(User.class, id);
		return user;
	}
	public List<User> findAll() {
		String hql="from User ";
		List<User> list=(List<User>) hibernateTemplate.find(hql);
		return list;
	}
	public User findByName(String name) {
		String hql="from User where name=? ";
		List<User> list=(List<User>) hibernateTemplate.find(hql, name);
		if(list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
		
	}
	public List<Map<String, Object>> findAllUsers() {
		String hql="select new map(id as id,name as name,age as age) "
				  +"from User ";
		List<Map<String,Object>> list=(List<Map<String,Object>>)hibernateTemplate.find(hql);
		return list;
	}
	public int findMaxId() {
		//sql:select max(u_id) from t_user
		//hql:select max(id) from User
		String hql="select max(id) from User";
		List<Number> list=(List<Number>)hibernateTemplate.find(hql);
		return list.get(0).intValue();
	}

}
