package com.babel.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class NullParamValidator {

	@AroundInvoke
	public Object checkNulls(InvocationContext ctx) throws Exception {
		Object[] params=ctx.getParameters();
		for (Object p:params){
			if (p==null)
				throw new IllegalArgumentException("null params not allowed");
		}
		return ctx.proceed();
	}
}
