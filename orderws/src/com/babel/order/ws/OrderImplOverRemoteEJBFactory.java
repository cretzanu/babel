package com.babel.order.ws;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.babel.order.CreateOrder;
import com.babel.order.ReadOrder;
import com.babel.order.SaveOrder;

public class OrderImplOverRemoteEJBFactory extends OrderImplFactory {

	private String ejbInterface = "remote";

	/**
	 * 
	 * @param ejbInterface
	 *            = remote / local depending on deployment context
	 *            
	 * Excelent post about JNDI names generated for JBoss AS 4.2
	 * https://developer.jboss.org/thread/121194 
	 * (see Answer 6 - jmx-console)
	 * http://localhost:8080/jmx-console/ -->service=JNDIView -->list() invoke
	 */
	protected OrderImplOverRemoteEJBFactory(String ejbInterface) {
		this.ejbInterface = ejbInterface;
	}

	protected OrderImplOverRemoteEJBFactory() {
	}

	@Override
	public CreateOrder createOrderFactory() {
		try {
			System.out.println("CreateOrder Factory provides " + ejbInterface
					+ " EJB interface");
			return (CreateOrder) new InitialContext()
					.lookup("orderEAR(order-ear)/CreateOrderEJB/" + ejbInterface);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	public ReadOrder readOrderFactory() {
		try {
			System.out.println("ReadOrder Factory provides " + ejbInterface
					+ " EJB interface");
			return (ReadOrder) new InitialContext()
					.lookup("orderEAR(order-ear)/ReadOrderEJB/" + ejbInterface);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	public SaveOrder saveOrderFactory() {
		try {
			System.out.println("SaveOrder Factory provides " + ejbInterface
					+ " EJB interface");
			return (SaveOrder) new InitialContext()
					.lookup("orderEAR(order-ear)/SaveOrderEJB/" + ejbInterface);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
}
