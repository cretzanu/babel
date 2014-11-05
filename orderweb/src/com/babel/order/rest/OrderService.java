package com.babel.order.rest;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.babel.order.CreateOrder;
import com.babel.order.Order;
import com.babel.order.ReadOrder;
import com.babel.order.SaveOrder;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 14-Oct-2014 10:41:03 AM
 */
@Path("/orders")

public class OrderService {

	CreateOrder createOrderDelegate;
	ReadOrder readOrderDelegate;
	SaveOrder saveOrderDelegate;
	 
	public OrderService() {
		OrderFactory f=OrderFactory.factory();
		this.createOrderDelegate=f.createOrderFactory();
		this.readOrderDelegate=f.readOrderFactory();
		this.saveOrderDelegate=f.saveOrderFactory();
		
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Order createOrder(Order p) {
		return this.createOrderDelegate.createOrder(p);
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Order readOrder(@PathParam("id") Long id) {
		
		Order e = this.readOrderDelegate.readOrder(id);
		e.purify();
		return e;
	}

	
	@PUT
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Order saveOrder(Order p) {
		return this.saveOrderDelegate.saveOrder(p);
	}
	
	@GET
	@Path("/template")
	@Produces({ MediaType.APPLICATION_JSON })
	public Order newOrder() {
		return new Order();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Order> readAllOrders() {
		return new LinkedList<Order>();
		//TODO: implement the ReadOrdersAll interface
	}
}// end OrderService