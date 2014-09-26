package com.babel.order;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.enterprise.core.data.PersistentEntity;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:55:53 AM
 */
@Entity 
@Table(name="OrderTable")
public class Order extends PersistentEntity {

	private String customerEmail;
	private String customerName;
	private String deliveryAddress;
	private Date orderDate;
	
	private String specialRequirements;
	 @OneToMany(mappedBy="order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)  @org.hibernate.annotations.Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN) 
	@JsonManagedReference("orderLines")
	private Set<OrderLine> orderLines = new HashSet<OrderLine>();

	public Order() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	

	public String getCustomerEmail() {
		return this.customerEmail;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	

	public String getSpecialRequirements() {
		return this.specialRequirements;
	}

	/**
	 * 
	 * @param customerEmail
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 
	 * @param deliveryAddress
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * 
	 * @param orderDate
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 
	 * @param specialRequirements
	 */
	public void setSpecialRequirements(String specialRequirements) {
		this.specialRequirements = specialRequirements;
	}

	/**
	 * 
	 * @param p
	 */
	public void addOrderLine(OrderLine p){
		 this.orderLines.add(p);
		 p.setOrder(this);
	}

	public Set<OrderLine> getOrderLines(){
		return this.orderLines;
	}
}// end Order