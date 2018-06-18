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
			String value=invocation.invoke();//���ÿ�����
			return value;
		} catch (Exception e) {
			//action.result=new JsonResult(e);
			Class cls=action.getClass();
			//���÷����ҵ�result��Ϥ
			try {
				Field fld=cls.getDeclaredField("result");
				//�޸����Ե�ֵ
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
