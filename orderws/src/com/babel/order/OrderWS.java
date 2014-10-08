package com.babel.order;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * The class implements Facade pattern to expose all Order interfaces as Web Service operations
 *
 * @author liviu.cretu
 * @version 1.0F
 * @created 06-Oct-2014 7:34:55 PM
 */
@WebService
@SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.RPC)//ok, this will be deprecated, eventually
public class OrderWS implements CreateOrder, ReadOrder, SaveOrder {

	private CreateOrder createOrderDelegate;
	private ReadOrder readOrderDelegate;
	private SaveOrder saveOrderDelegate;
	@javax.persistence.PersistenceContext(unitName="myJPAUnit")
	public javax.persistence.EntityManager em;

	public OrderWS(){	}

	@javax.annotation.PostConstruct
	public void init(){
		this.createOrderDelegate=new CreateOrderImpl(); 
		 (( CreateOrderImpl)this.createOrderDelegate).setEm(em);
		 this.readOrderDelegate=new ReadOrderImpl(); 
		 (( ReadOrderImpl)this.readOrderDelegate).setEm(em);
		 this.saveOrderDelegate=new SaveOrderImpl(); 
		 (( SaveOrderImpl)this.saveOrderDelegate).setEm(em);
	}

	@Override
	@WebMethod
	public Order createOrder(Order p){
		return this.createOrderDelegate.createOrder(p);
	}
	@Override
	@WebMethod
	public Order saveOrder(Order p) {
		return this.saveOrderDelegate.saveOrder(p);
	}

	@Override
	@WebMethod
	public Order readOrder(Long p) {
		return new Order();//this.readOrderDelegate.readOrder(p);
	}
}