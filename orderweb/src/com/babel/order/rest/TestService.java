package com.babel.order.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.babel.order.Order;
import com.babel.order.OrderLine;

@Path("/test")
public class TestService {
//	@GET
//	@Produces({ MediaType.APPLICATION_JSON })
	public Prod newProd() {
		Prod p= new Prod();
		p.getLines().add(new Linie());
		return p;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Order newOrder() {
		Order p= new Order();
		p.getOrderLines().add(new OrderLine());
		p.getOrderLines().add(new OrderLine());
		return p;
	}

}
