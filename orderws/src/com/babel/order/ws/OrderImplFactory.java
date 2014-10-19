package com.babel.order.ws;

import javax.persistence.Persistence;

import com.babel.order.CreateOrder;
import com.babel.order.CreateOrderImpl;
import com.babel.order.ReadOrder;
import com.babel.order.ReadOrderImpl;
import com.babel.order.SaveOrder;
import com.babel.order.SaveOrderImpl;

public class OrderImplFactory {
	
	/**This implementation is working with a RESOURCE-LOCAL EM configuration
	but there is no container-managed transactions.
	As a consequence, you have to manage transactions manually (e.g. implement Chain-of-Responsibilities design pattern to begin/commit transaction)
	**/
	private javax.persistence.EntityManager em = null;
//			Persistence
//			.createEntityManagerFactory("myJPAUnitOrderWS")
//			.createEntityManager();
	//static private OrderWSFactory f = new OrderWSFactory();
	/**
	 * Use the EJB implementation for the requred delegates.
	 * This one is for remote EJBs (when .war is deployed standalone
	 * 
	 */
	static private OrderImplFactory f = new OrderImplOverRemoteEJBFactory();
	
	/**
	 * Use the EJB implementation for the requred delegates.
	 * This one is for local EJBs (when .war is deployed wityhin the same .ear as the ejb)
	 * This should be the most optimal approach (@see remote vs local EJBs)
	 */
	//static private OrderImplFactory f = new OrderImplOverLocalEJBFactory();
	
	protected OrderImplFactory() {

	}

	public static OrderImplFactory getInstance() {
		return f;
	}

	public CreateOrder createOrderFactory() {
		CreateOrderImpl delegate = new CreateOrderImpl();
		delegate.setEm(em);
		return delegate;
	}

	public ReadOrder readOrderFactory() {
		ReadOrderImpl delegate = new ReadOrderImpl();
		delegate.setEm(em);
		return delegate;
	}
	
	public SaveOrder saveOrderFactory() {
		SaveOrderImpl delegate = new SaveOrderImpl();
		delegate.setEm(em);
		return delegate;
	}
}
