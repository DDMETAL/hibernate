package com.dmetal.ssh.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dmetal.ssh.service.UserService;

@Controller("usrAction")
@Scope("prototype")
public class UAction implements RequestAware{
	
	private Map<String,Object> request;
	public void setRequest(Map<String, Object> request) {
		this.request=request;
		
	}
	@Resource
	private UserService userService;
	
	private List<Map<String,Object>> users;
	public List<Map<String, Object>> getUsers() {
		return users;
	}
	public void setUsers(List<Map<String, Object>> users) {
		this.users = users;
	}
	
	private String myMessage;
	public void setMyMessage(String myMessage) {
		this.myMessage = myMessage;
	}
	public String getMyMessage() {
		return myMessage;
	}
	public String list() {
		List<Map<String,Object>> list=userService.userList();
		users=list;
		request.put("message", "Hello");
		myMessage="Hello Value Stack";
		return "success";
	}


}
