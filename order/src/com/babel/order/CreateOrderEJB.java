package com.babel.order;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:55:53 AM
 */
@javax.ejb.Stateless(name = "CreateOrderEJB", mappedName = "CreateOrderEJB")
@javax.ejb.Remote(value = CreateOrder.class)
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
public class CreateOrderEJB implements CreateOrder {

	public CreateOrder delegate;
	@javax.persistence.PersistenceContext(unitName = "myJPAUnit")
	public javax.persistence.EntityManager em;

	public CreateOrderEJB() {

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param p
	 */
	public Order createOrder(Order p) {
		return this.delegate.createOrder(p);
	}

	@javax.annotation.PostConstruct
	public void init() {
		this.delegate = new CreateOrderImpl();
		((CreateOrderImpl) this.delegate).setEm(em);
	}
}// end CreateOrderEJB