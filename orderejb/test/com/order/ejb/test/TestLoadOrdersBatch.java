package com.order.ejb.test;

import static org.junit.Assert.assertFalse;

import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.babel.order.LoadOrdersBatch;
import com.babel.order.Order;
import com.babel.order.OrdersBatchException;

public class TestLoadOrdersBatch {

	LoadOrdersBatch worker;

	@Before
	public void init() throws NamingException {

		worker = (LoadOrdersBatch) new InitialContext()
				.lookup("orderEAR/LoadOrdersBatchEJB/remote");

	}

	@Test
	public void test() {
		List<Order> ordersLst = new LinkedList<Order>();
		Order order = new Order();
		order.setCustomerName("Batch 1");
		order.setCustomerEmail("Batch1@yahoo.com");
		ordersLst.add(order);
		ordersLst.add(null); // this should fail ...IllegalArgumentException (is
								// a RuntimeException, so it will rollback) -
								// see NullParamValidator interceptor on
								// CreateOrderEJB
		order = new Order();
		order.setCustomerName("Batch 2");
		order.setCustomerEmail("Batch2@yahoo.com");
		ordersLst.add(order);

		try {
			this.worker.loadOrdersBatch(ordersLst);
		} catch (Throwable e) {
			e.printStackTrace();
			if ((e instanceof OrdersBatchException)
					|| (e.getCause() instanceof IllegalArgumentException)) {
				e.printStackTrace();
				return;
			}
		}
		assertFalse("should not arrive here...", 1 == 1);
	}

}
