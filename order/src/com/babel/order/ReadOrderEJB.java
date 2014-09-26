package com.babel.order;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:55:54 AM
 */
@javax.ejb.Stateless(name = "ReadOrderEJB", mappedName = "ReadOrderEJB")
@javax.ejb.Remote(value = ReadOrder.class)
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
public class ReadOrderEJB implements ReadOrder {

	public ReadOrder delegate;
	@javax.persistence.PersistenceContext(unitName = "myJPAUnit")
	public javax.persistence.EntityManager em;

	public ReadOrderEJB() {

	}

	public void finalize() throws Throwable {

	}

	@javax.annotation.PostConstruct
	public void init() {
		this.delegate = new ReadOrderImpl();
		((ReadOrderImpl) this.delegate).setEm(em);
	}

	/**
	 * 
	 * @param p
	 */
	public Order readOrder(Long p) {
		return this.delegate.readOrder(p);
	}
}// end ReadOrderEJB