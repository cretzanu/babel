package com.babel.order.process;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.babel.accounting.CreateInvoice;
import com.babel.accounting.CreateInvoiceImpl;
import com.babel.ioemail.SendEmail;
import com.babel.ioemail.SendEmailImpl;
import com.babel.order.ReadOrder;
import com.babel.order.ReadOrderImpl;
import com.babel.order.SaveOrder;
import com.babel.order.SaveOrderImpl;
import com.babel.order.process.internal.CreateOrderProcessData;
import com.babel.order.process.internal.CreateOrderProcessDataImpl;
import com.babel.order.process.internal.ReadOrderProcessData;
import com.babel.order.process.internal.ReadOrderProcessDataImpl;
import com.babel.production.CreateProductionPlan;
import com.babel.production.CreateProductionPlanImpl;

public class OrderProcessFactory {
	private javax.persistence.EntityManager em = null;
	
	/**This implementation is working with a RESOURCE-LOCAL EM configuration
	but there is no container-managed transactions.
	As a consequence, you have to manage transactions manually (e.g. implement Chain-of-Responsibilities design pattern to begin/commit transaction)
	However, this EM has to be provided by the EJB in order to enlist in JTA 
	Note: add all entity classes from dependent projects in persistence.xml (only if POJO injection instead of corresponding EJB) 
	**/
			
	//static private OrderProcessFactory f = new OrderProcessFactory();
	/**
	 * Use the EJB implementation for the requred delegates.
	 * This one is for remote EJBs (when .war is deployed standalone
	 * Note: take off entity classes that belong to injected EJBs from the local persistence.xml
	 */
	static private OrderProcessFactory f = new OrderProcessOverRemoteEJBFactory();
	
	/**
	 * Use the EJB implementation for the requred delegates.
	 * This one is for local EJBs (when .war is deployed wityhin the same .ear as the ejb)
	 * This should be the most optimal approach (@see remote vs local EJBs)
	 */
	//static private OrderImplFactory f = new OrderImplOverLocalEJBFactory();
	
	protected OrderProcessFactory() {

	}
	public void setEm(EntityManager em) {
		 this.em=em;
	}
	public static OrderProcessFactory getInstance() {
		return f;
	}

	public ReadOrder readOrderFactory() {
		ReadOrderImpl delegate = new ReadOrderImpl();
		delegate.setEm(em);
		return delegate;
	}
	
	public SaveOrder saveOrderFactory() {
		SaveOrderImpl delegate = new SaveOrderImpl();
		delegate.setEm(em);
		return delegate;
	}

	public CreateInvoice createInvoiceFactory() {
		CreateInvoiceImpl delegate = new CreateInvoiceImpl();
		delegate.setEm(em);
		return delegate;
	}

	public CreateOrderProcessData createOrderProcessDataFactory() {
		CreateOrderProcessDataImpl delegate = new CreateOrderProcessDataImpl();
		delegate.setEm(em);
		return delegate;
	}

	public CreateProductionPlan createProductionPlanFactory() {
		CreateProductionPlanImpl delegate = new CreateProductionPlanImpl();
		delegate.setEm(em);
		return delegate;
	}

	public ReadOrderProcessData readOrderProcessDataFactory() {
		ReadOrderProcessDataImpl delegate = new ReadOrderProcessDataImpl();
		delegate.setEm(em);
		return delegate;
	}

	public SendEmail sendEmailFactory() {
		SendEmailImpl delegate = new SendEmailImpl();
		
		return delegate;
	}
}
