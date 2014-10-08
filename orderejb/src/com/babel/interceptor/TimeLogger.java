package com.babel.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class TimeLogger {

	private static final String END3 = "ms";
	private static final String END2 = " took ";
	private static final String END1 = "*** ";
	private static final String START = "*** Start ";

	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception {
		String m = ctx.getClass().getName() + "::" + ctx.getMethod().getName();
		System.out.println(START + m);
		long start = System.currentTimeMillis();
		try {
			return ctx.proceed();
		} catch (Exception e) {
			throw e;
		} finally {
			long time = System.currentTimeMillis() - start;
			System.out.println(END1 + m + END2 + time + END3);
		}
	}
}
