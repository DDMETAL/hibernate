package com.dmetal.ssh.web;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

import com.dmetal.ssh.util.JsonResult;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@Component
public class ExceptionInterceptor implements Interceptor {

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Object action=invocation.getAction();
		try {
			String value=invocation.invoke();//调用控制器
			return value;
		} catch (Exception e) {
			//action.result=new JsonResult(e);
			Class cls=action.getClass();
			//利用反射找到result熟悉
			try {
				Field fld=cls.getDeclaredField("result");
				//修改属性的值
				fld.setAccessible(true);
				fld.set(action, new JsonResult(e));
				return "json";
			} catch (Exception e2) {
				e2.printStackTrace();
				throw e;
			}
			
		}

	}

}
