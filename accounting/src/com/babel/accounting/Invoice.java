package com.babel.accounting;

import javax.persistence.Entity;

import com.babel.core.data.PersistentEntity;

@Entity
public class Invoice extends PersistentEntity{
	
	private String customerName;
	private String customerBankAccount;
	private Double amountToPay;
	
	//....
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerBankAccount() {
		return customerBankAccount;
	}
	public void setCustomerBankAccount(String customerBankAccount) {
		this.customerBankAccount = customerBankAccount;
	}
	public Double getAmountToPay() {
		return amountToPay;
	}
	public void setAmountToPay(Double amountToPay) {
		this.amountToPay = amountToPay;
	}
	public void calculateInvoice(double calculateOrderValue) {
		//usually extract taxes such as VAT and so on
		this.amountToPay=calculateOrderValue;
			
	}
	

	
}
