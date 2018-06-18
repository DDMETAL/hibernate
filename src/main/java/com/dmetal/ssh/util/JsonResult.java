package com.dmetal.ssh.util;

import java.io.Serializable;

public class JsonResult<T> implements Serializable{
	public static final int SUCCESS=1;
	public static final int ERROR=0;
	private int state;
	private String message;
	private T data;
	public JsonResult() {
	}
	public JsonResult(int state, String message) {
		this.state = state;
		this.message = message;
	}
	public JsonResult(Throwable e) {
		this.state=ERROR;
		this.message=e.getMessage();
	}
	public JsonResult(T data) {
		this.state=SUCCESS;
		this.data=data;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
