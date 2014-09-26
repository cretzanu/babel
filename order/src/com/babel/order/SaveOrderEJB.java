package com.babel.order;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:55:54 AM
 */
@javax.ejb.Stateless(name = "SaveOrderEJB", mappedName = "SaveOrderEJB")
@javax.ejb.Remote(value = SaveOrder.class)
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
public class SaveOrderEJB implements SaveOrder {

	public SaveOrder delegate;
	@javax.persistence.PersistenceContext(unitName = "myJPAUnit")
	public javax.persistence.EntityManager em;

	public SaveOrderEJB() {

	}

	public void finalize() throws Throwable {

	}

	@javax.annotation.PostConstruct
	public void init() {
		this.delegate = new SaveOrderImpl();
		((SaveOrderImpl) this.delegate).setEm(em);
	}

	/**
	 * 
	 * @param p
	 */
	public Order saveOrder(Order p) {
		return this.delegate.saveOrder(p);
	}
}// end SaveOrderEJB