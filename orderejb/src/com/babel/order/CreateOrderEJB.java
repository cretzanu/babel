package com.babel.order;


/**
 * @author liviu.cretu
 * @version 1.0
 * @created 06-Oct-2014 7:34:55 PM
 */
@javax.ejb.Stateless 
@javax.ejb.Remote(value=CreateOrder.class) 
//@javax.ejb.Local(value=CreateOrder.class) //JBoss 4.2 still supports same interface as Remote. Otherwise, extend remote interfce with anither interface
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
public class CreateOrderEJB implements CreateOrder {

	private CreateOrder delegate;
	@javax.persistence.PersistenceContext(unitName="myJPAUnit")
	private javax.persistence.EntityManager em;

	public CreateOrderEJB(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param p
	 */
	public Order createOrder(Order p){
		return this.delegate.createOrder(p);
	}

	@javax.annotation.PostConstruct
	public void init(){
		this.delegate=new CreateOrderImpl(); 
		 (( CreateOrderImpl)this.delegate).setEm(em);
	}
}//end CreateOrderEJB