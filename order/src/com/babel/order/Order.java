package com.babel.order;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.babel.core.data.PersistentEntity;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:55:53 AM
 */
@Entity 
@Table(name="OrderTable")
@XmlRootElement
public class Order extends PersistentEntity {

	
	private String customerEmail;
	private String customerName;
	private String deliveryAddress;
	private Date orderDate;
	private String specialRequirements;
	/**
	 * The Order may be involved in multiple external processes. 
  	 * This field should store a collection of all process identifiers.
  	 * For the sake of simplicity, we use only one here.
	 */
	private String processId;
	
	@XmlElement
	@XmlElementWrapper(name="orderLines")
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)  @org.hibernate.annotations.Cascade(value=org.hibernate.annotations.CascadeType.DELETE_ORPHAN) 
	@JsonManagedReference 	
	private Set<OrderLine> orderLines = new HashSet<OrderLine>();

	public Order() {

	}
	
	
	public Double calculateOrderValue(){
		double s=0;
		for (OrderLine l:this.orderLines)
			s=s+l.getPrice()*l.getQuantity();
		return s;
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



	public String getProcessId() {
		return processId;
	}



	public void setProcessId(String processId) {
		this.processId = processId;
	}



	public void setOrderLines(Set<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
}// end Order