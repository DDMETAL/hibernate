package ssh;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dmetal.ssh.dao.UserDAO;
import com.dmetal.ssh.entity.User;

public class DAOTestCase {
	ClassPathXmlApplicationContext ac;
	UserDAO dao;
	@Before
	public void init() {
		ac=new ClassPathXmlApplicationContext("conf/spring-hibernate.xml");
		dao=ac.getBean("userDAO",UserDAO.class);
	}
	
	@Test
	public void testAddUser() {
		User user=new User(2,"HuaLan","a123",21,150000.0,new Date(0));
		int n=dao.addUser(user);
		System.out.println(n);
	}
	@Test
	public void testUpdateUser() {
		User user=dao.findById(2);
		user.setName("»ªÀ¼");
		dao.updateUser(user);
	}
	@Test
	public void testFindAll() {
		List<User> users=dao.findAll();
		for(User user:users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testFindAllUsers() {
		List<Map<String,Object>> list=dao.findAllUsers();
		for(Map<String,Object> map:list) {
			System.out.println(map);
		}
	}
	
	@Test
	public void testFindMaxId() {
		int id=dao.findMaxId();
		System.out.println(id);
	}
}
