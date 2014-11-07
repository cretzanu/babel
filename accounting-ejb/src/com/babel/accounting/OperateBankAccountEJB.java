package com.babel.accounting;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.babel.accounting.impl.OperateBankAccountImpl;

/**
 * TransactionAttributeType.MANDATORY will restrict the calling of this bean
 * only from other beans and never directly from the client. This will keep the
 * ACID when one needs to call bean's methods repeatedly (debit one account
 * while credit one or multiple other accounts) depending on the specific
 * business requirement.
 * 
 * For example, the usual Pay operation (two accounts involved ) will be
 * implemented as another bean with REQUIRED transaction that will be then
 * executed by the client. But the client outside a transaction continer (e.g. a
 * web app) will not be able to implement it itself.
 * 
 * 
 * @author liviu.cretu
 * @version 1.0
 * @created 06-Oct-2014 7:34:55 PM
 */
@javax.ejb.Stateless
@javax.ejb.Remote(value = OperateBankAccount.class)
@javax.ejb.Local(value = OperateBankAccount.class)
@javax.ejb.TransactionManagement(javax.ejb.TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class OperateBankAccountEJB implements OperateBankAccount {

	private OperateBankAccount delegate;
	@javax.persistence.PersistenceContext(unitName = "accounting-ejb")
	private javax.persistence.EntityManager em;

	public OperateBankAccountEJB() {}

	@javax.annotation.PostConstruct
	public void init() {
		this.delegate = new OperateBankAccountImpl();
		((OperateBankAccountImpl) this.delegate).setEm(em);
	}
	@Override
	public void debit(String accountIdentification, double amount) {
		this.delegate.debit(accountIdentification, amount);
	}
	@Override
	public void credit(String accountIdentification, double amount) {
		this.delegate.credit(accountIdentification, amount);

	}

}// end CreateInvoiceEJB