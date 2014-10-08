package com.babel.interceptor;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class UserInfoLogger {
	@Resource
	private SessionContext sessionContext;

	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception {
		String userName = null;
		boolean isAdmin = false;
		try {// no exception if there is no security context associated
			userName = sessionContext.getCallerPrincipal().getName();
			isAdmin = sessionContext.isCallerInRole("admin");
		} catch (Throwable t) {
			System.err.println("cannot get security context" + t.getMessage());
			return ctx.proceed();
		}
		System.out.println("user:" + userName + " / isAdmin:" + isAdmin);
		return ctx.proceed();

	}
}
