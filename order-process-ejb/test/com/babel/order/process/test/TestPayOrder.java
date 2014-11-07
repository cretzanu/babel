package com.babel.order.process.test;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.babel.accounting.OperateBankAccount;
import com.babel.order.ReadOrder;
import com.babel.order.process.PayOrder;

public class TestPayOrder {
	private static final long ORDER_ID = 7L;
	PayOrder payOrderService;
	ReadOrder readOrderService;
	OperateBankAccount operateBankAccountService;
	@Before
	public void init() throws NamingException {
		payOrderService = (PayOrder) new InitialContext()
				.lookup("order-process-ear/PayOrderEJB/remote");
		readOrderService = (ReadOrder) new InitialContext()
				.lookup("orderEAR/ReadOrderEJB/remote");
		operateBankAccountService = (OperateBankAccount) new InitialContext()
				.lookup("accounting-ear/OperateBankAccountEJB/remote");
	}
	@Test
	public void test() {
		payOrderService.payOrder(readOrderService.readOrder(ORDER_ID));
	}
	@Test
	public void testFailDirectAccess() {
		// this should fail on Mandatory transaction attribute (cannot be called
		// from a client outside a transaction
		operateBankAccountService.debit("test",0);
	}

}
