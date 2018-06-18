package ssh;



import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.dmetal.ssh.entity.User;

public class TestCase {
		ClassPathXmlApplicationContext ac;
		HibernateTemplate template;
		
		@Before
		public void init() {
			ac=new ClassPathXmlApplicationContext("conf/spring-hibernate.xml");
			template=ac.getBean("hibernateTemplate",HibernateTemplate.class);
		}
		
		@Test
		public void testGet() {
			User user=template.get(User.class, 1);
			System.out.println(user);
		}
		
		@Test
		public void testAdd() {
			User user=new User(2,"HuaLan","a123",21,150000.0,new Date(0));
			template.save(user);
		}
		
		@Test
		public void testUpdate() {
			User user=template.get(User.class, 1);
			user.setSalary(20000.0);
			template.update(user);
		}
		
		@Test
		public void testDelete() {
			User user=template.get(User.class, 2);
			template.delete(user);
		}
		
		@Test
		public void testState() {
			SessionFactory factory=template.getSessionFactory();
			Session session=factory.openSession();
			Transaction t=session.beginTransaction();
			//新创建的user对象状态为游离状态
			User user=new User(3,"DDD","1234",21,12000.0,new Date(0));	
			session.save(user);
			//save以后对象状态变成持久状态
			//持久状态对象属性更改将影响到数据库
			user.setPassword("a123");
			t.commit();
			session.close();
		}
		@Test
		public void testState2() {
			SessionFactory factory=template.getSessionFactory();
			Session session=factory.openSession();
			Transaction t=session.beginTransaction();
			User user=(User)session.get(User.class, 1);
			//持久状态
			user.setPassword("a123");
			session.flush();//调用flush清空缓存
			session.evict(user);//evict(user)将一个对象从换从中清除使其成为游离状态
			session.clear();//将当前session中全部对象从缓存中清除，使其全部成为游离状态
			//游离状态
			user.setPassword("a1234");
			session.flush();
			t.commit();
			session.close();
		}
		
		@Test
		public void testState3() {
			SessionFactory factory=template.getSessionFactory();
			Session session=factory.openSession();
			Transaction t=session.beginTransaction();
			User user=(User)session.get(User.class, 1);
			session.evict(user);
			user.setPassword("a1234");
			session.update(user);
			
			t.commit();
			session.close();
			
		}
		@Test
		public void testState4(){
			SessionFactory factory =
					template.getSessionFactory();
			Session session=factory.openSession();
			Transaction tx = session.beginTransaction();
				
			User user=(User)session.get(User.class, 3);
			session.delete(user);
			user.setName("ABC");
			tx.commit();
			session.close();
		}
		
}

