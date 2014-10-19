package com.babel.order.process;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.babel.accounting.CreateInvoice;
import com.babel.accounting.CreateInvoiceImpl;
import com.babel.order.CreateOrder;
import com.babel.order.ReadOrder;
import com.babel.order.SaveOrder;
import com.babel.order.process.internal.CreateOrderProcessData;
import com.babel.order.process.internal.CreateOrderProcessDataImpl;
import com.babel.order.process.internal.ReadOrderProcessData;
import com.babel.order.process.internal.ReadOrderProcessDataImpl;
import com.babel.production.CreateProductionPlan;
import com.babel.production.CreateProductionPlanImpl;

public class OrderProcessOverRemoteEJBFactory extends OrderProcessFactory {

	private String ejbInterface = "remote";

	/**
	 * 
	 * @param ejbInterface
	 *            = remote / local depending on deployment context
	 *            https://developer.jboss.org/thread/121194 (see Answer 6 -
	 *            jmx-console) http://localhost:8080/jmx-console/
	 *            -->service=JNDIView -->list() invoke
	 */
	protected OrderProcessOverRemoteEJBFactory(String ejbInterface) {
		this.ejbInterface = ejbInterface;
	}

	protected OrderProcessOverRemoteEJBFactory() {
	}
	@Override
	public ReadOrder readOrderFactory() {
		try {
			System.out.println("ReadOrder Factory provides " + ejbInterface
					+ " EJB interface");
			return (ReadOrder) new InitialContext()
					.lookup("orderEAR/ReadOrderEJB/" + ejbInterface);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public SaveOrder saveOrderFactory() {
		try {
			System.out.println("SaveOrder Factory provides " + ejbInterface
					+ " EJB interface");
			return (SaveOrder) new InitialContext()
					.lookup("orderEAR/SaveOrderEJB/" + ejbInterface);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public CreateInvoice createInvoiceFactory() {
		try {
			System.out.println("CreateInvoice Factory provides " + ejbInterface
					+ " EJB interface");
			return (CreateInvoice) new InitialContext()
					.lookup("accounting-ear/CreateInvoiceEJB/" + ejbInterface);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public CreateProductionPlan createProductionPlanFactory() {
		try {
			System.out.println("CreateProductionPlan Factory provides " + ejbInterface
					+ " EJB interface");
			return (CreateProductionPlan) new InitialContext()
					.lookup("production-ear/CreateProductionPlanEJB/" + ejbInterface);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
//remains as it is in the super-class (order-process operation)
//	public CreateOrderProcessData createOrderProcessDataFactory() {
		

	
	//remains as it is in the super-class (order-process operation)
	//public ReadOrderProcessData readOrderProcessDataFactory() {
		

}
