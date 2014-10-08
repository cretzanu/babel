package com.babel.order;

import javax.interceptor.Interceptors;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 06-Oct-2014 7:34:55 PM
 */
@javax.ejb.Stateless(name = "SaveOrderEJB",mappedName = "SaveOrderEJB") 
@javax.ejb.Remote(value=SaveOrder.class) 
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
public class SaveOrderEJB implements SaveOrder {

	private SaveOrder delegate;
	@javax.persistence.PersistenceContext(unitName="myJPAUnit")
	private javax.persistence.EntityManager em;

	public SaveOrderEJB(){

	}

	public void finalize() throws Throwable {

	}
	@javax.annotation.PostConstruct
	public void init(){
		this.delegate=new SaveOrderImpl(); 
		 (( SaveOrderImpl)this.delegate).setEm(em);
	}

	/**
	 * 
	 * @param p
	 */
//	@Interceptors(value={com.babel.interceptor.NullParamValidator.class})
	public Order saveOrder(Order p){
		return this.delegate.saveOrder(p);
	}
}//end SaveOrderEJB