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
			//�´�����user����״̬Ϊ����״̬
			User user=new User(3,"DDD","1234",21,12000.0,new Date(0));	
			session.save(user);
			//save�Ժ����״̬��ɳ־�״̬
			//�־�״̬�������Ը��Ľ�Ӱ�쵽���ݿ�
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
			//�־�״̬
			user.setPassword("a123");
			session.flush();//����flush��ջ���
			session.evict(user);//evict(user)��һ������ӻ��������ʹ���Ϊ����״̬
			session.clear();//����ǰsession��ȫ������ӻ����������ʹ��ȫ����Ϊ����״̬
			//����״̬
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

