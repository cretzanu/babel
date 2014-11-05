package com.babel.order.rest;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.babel.order.CreateOrder;
import com.babel.order.CreateOrderImpl;
import com.babel.order.CreateOrderTA;
import com.babel.order.ReadOrder;
import com.babel.order.ReadOrderImpl;
import com.babel.order.SaveOrder;
import com.babel.order.SaveOrderImpl;
import com.babel.order.SaveOrderTA;

public class OrderImplFactory extends OrderFactory {
	
	/**This implementation is working with a RESOURCE-LOCAL EM configuration
	but there is no container-managed transactions.
	As a consequence, you have to manage transactions manually (e.g. implement Chain-of-Responsibilities design pattern to begin/commit transaction)
	**/
	private static javax.persistence.EntityManagerFactory emf = //null;
			Persistence
			.createEntityManagerFactory("myJPAUnitOrderWeb");
	
	/**
	 * Use the EJB implementation for the requred delegates.
	 * This one is for remote EJBs (when .war is deployed standalone
	 * 
	 */
//	static private OrderImplFactory f = new OrderImplOverRemoteEJBFactory();
	
	/**
	 * Use the EJB implementation for the requred delegates.
	 * This one is for local EJBs (when .war is deployed wityhin the same .ear as the ejb)
	 * This should be the most optimal approach (@see remote vs local EJBs)
	 */
	//static private OrderImplFactory f = new OrderImplOverLocalEJBFactory();
	
	
	protected OrderImplFactory() {}
	
	protected EntityManager getEm(){
		return emf.createEntityManager();
	}
	/* (non-Javadoc)
	 * @see com.babel.order.rest.OrderFactory#createOrderFactory()
	 */
	@Override
	public CreateOrder createOrderFactory() {
		//we need the transaction aware implementation here
		CreateOrderImpl delegate = new CreateOrderTA();
		delegate.setEm(this.getEm());
		return delegate;
	}

	/* (non-Javadoc)
	 * @see com.babel.order.rest.OrderFactory#readOrderFactory()
	 */
	@Override
	public ReadOrder readOrderFactory() {
		ReadOrderImpl delegate = new ReadOrderImpl();
		delegate.setEm(this.getEm());
		return delegate;
	}
	
	/* (non-Javadoc)
	 * @see com.babel.order.rest.OrderFactory#saveOrderFactory()
	 */
	@Override
	public SaveOrder saveOrderFactory() {
		//we need the transaction aware implementation here
		SaveOrderImpl delegate = new SaveOrderTA();
		delegate.setEm(this.getEm());
		return delegate;
	}
}
