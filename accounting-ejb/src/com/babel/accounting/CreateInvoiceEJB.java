package com.babel.accounting;


/**
 * @author liviu.cretu
 * @version 1.0
 * @created 06-Oct-2014 7:34:55 PM
 */
@javax.ejb.Stateless 
@javax.ejb.Remote(value=CreateInvoice.class) 
//@javax.ejb.Local(value=CreateInvoice.class) //JBoss 4.2 still supports same interface as Remote. Otherwise, extend remote interfce with anither interface
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
public class CreateInvoiceEJB implements CreateInvoice {

	private CreateInvoice delegate;
	@javax.persistence.PersistenceContext(unitName="accounting-ejb")
	private javax.persistence.EntityManager em;

	public CreateInvoiceEJB(){}

	
	/**
	 * 
	 * @param p
	 */
	public Invoice createInvoice(Invoice p){
		return this.delegate.createInvoice(p);
	}

	@javax.annotation.PostConstruct
	public void init(){
		this.delegate=new CreateInvoiceImpl(); 
		 (( CreateInvoiceImpl)this.delegate).setEm(em);
	}
}//end CreateInvoiceEJB