package com.dmetal.ssh.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dmetal.ssh.entity.User;
import com.dmetal.ssh.service.UserService;
import com.dmetal.ssh.util.JsonResult;


@Controller
@Scope("prototype")
public class UserAction {
	@Resource
	private UserService userService;
	
	
	
	private JsonResult result;
	public JsonResult getResult() {
		return result;
	}

	public void setResult(JsonResult result) {
		this.result = result;
	}
	public String list() {
		List<Map<String,Object>> list=userService.userList();
		result=new JsonResult(list);
		return "json";
	}
	private String name;
	private String password;
	private Integer age;
	private Double salary;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String save() {
		boolean ok=userService.saveUser(name,password,age,salary);
		result=new JsonResult(ok);
		return "json";
	}
	
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String load() {
		User user=userService.loadUser(id);
		result=new JsonResult(user);
		return "json";
	}
	public String update() {
		userService.updateUser(id,name,password,age,salary);
		result=new JsonResult("ok");
		return "json";
	}
}
