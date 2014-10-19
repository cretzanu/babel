package com.babel.order;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;

import com.babel.core.data.PersistentEntity;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 3:38:48 PM
 */
@Entity
public class OrderLine extends PersistentEntity {

	private String item;
	 @ManyToOne 
	 @JsonBackReference//("orderLines")
	private Order order;
	private double price;
	private double quantity;

	public OrderLine(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	public String getItem(){
		return this.item;
	}

	public Order getOrder(){
		return this.order;
	}

	public double getPrice(){
		return this.price;
	}

	public double getQuantity(){
		return this.quantity;
	}

	/**
	 * 
	 * @param item
	 */
	public void setItem(String item){
		this.item=item;
	}

	/**
	 * 
	 * @param p
	 */
	public void setOrder(Order p){
		 this.order=p;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(double price){
		this.price=price;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(double quantity){
		this.quantity=quantity;
	}
}//end OrderLine