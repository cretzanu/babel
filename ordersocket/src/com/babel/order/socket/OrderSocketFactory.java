package com.babel.order.socket;

import javax.persistence.Persistence;

import com.babel.order.CreateOrderLocalJPA;

public class OrderSocketFactory {

	public static CreateCSVOrder createCSVOrderFactory() {
		CreateOrderLocalJPA x = new CreateOrderLocalJPA();
		x.setEm(Persistence.createEntityManagerFactory("BabelConnOrderSocket")
				.createEntityManager());
		
		
		return new CreateCSVOrderImpl(x);
	}
}
