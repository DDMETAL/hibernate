package ssh;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dmetal.ssh.service.UserService;


public class ServiceTestCase {
	ClassPathXmlApplicationContext ac;
	UserService service;
	@Before
	public void init() {
		ac=new ClassPathXmlApplicationContext("conf/spring-hibernate.xml","conf/spring-service.xml");
		service=ac.getBean("userService",UserService.class);
	}
	@After
	public void destroy() {
		ac.close();
	}
	@Test
	public void testFindAllList() {
		List<Map<String,Object>> list=service.userList();
		for(Map<String,Object> map:list) {
			System.out.println(map);
		}
	}
}
