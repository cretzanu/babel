package com.babel.ioemail;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:57:11 AM
 */
@javax.ejb.Stateless(name = "ReadEmailsEJB", mappedName = "ReadEmailsEJB") @javax.ejb.Remote(value=ReadEmails.class) @javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
public class ReadEmailsEJB implements ReadEmails {

	public ReadEmails delegate;


	public ReadEmailsEJB(){

	}

	public void finalize() throws Throwable {

	}
	@javax.annotation.PostConstruct
	public void init(){
		this.delegate=new ReadEmailsImpl();
	}

	public void readEmails(){
		this.delegate.readEmails();
	}
}//end ReadEmailsEJB