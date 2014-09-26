package com.babel.ioemail;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:57:11 AM
 */
@javax.ejb.Stateless(name = "SendEmailEJB", mappedName = "SendEmailEJB") @javax.ejb.Remote(value=SendEmail.class) @javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
public class SendEmailEJB implements SendEmail {

	public SendEmail delegate;
	@javax.persistence.PersistenceContext(unitName="myJPAUnit")
	public javax.persistence.EntityManager em;

	public SendEmailEJB(){

	}

	public void finalize() throws Throwable {

	}
	@javax.annotation.PostConstruct
	public void init(){
		this.delegate=new SendEmailImpl(); 
	
	}

	/**
	 * 
	 * @param p
	 */
	public void sendEmail(EmailMessage p){
		 this.delegate.sendEmail(p);
	}
}//end SendEmailEJB