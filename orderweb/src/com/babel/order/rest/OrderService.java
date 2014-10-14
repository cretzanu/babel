package com.babel.order.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.babel.order.Order;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 14-Oct-2014 10:41:03 AM
 */
@Path("/order")
public class OrderService {

	public OrderService() {

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param p
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Order createOrder(Order p) {
		return OrderFactory.CreateOrderFactory().createOrder(p);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Order newOrder() {
		return new Order();
	}

	/**
	 * 
	 * @param p
	 */
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Order readOrder(@PathParam("id") Long p) {
		Order e = OrderFactory.ReadOrderFactory().readOrder(p);
		e.purify();
		return e;
	}

	/**
	 * 
	 * @param p
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Order saveOrder(Order p) {
		return OrderFactory.SaveOrderFactory().saveOrder(p);
	}
}// end OrderService