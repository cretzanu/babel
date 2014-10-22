package com.babel.accounting.impl;

import javax.persistence.Entity;

import com.babel.core.data.PersistentEntity;

@Entity
public class BankAccountOperation extends PersistentEntity{
	private String accountIdentification;
	private Double ammount;
	private String operationType;
	
	public BankAccountOperation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankAccountOperation(String accountIdentification, Double ammount,
			String operationType) {
		super();
		this.accountIdentification = accountIdentification;
		this.ammount = ammount;
		this.operationType = operationType;
	}
	public String getAccountIdentification() {
		return accountIdentification;
	}
	public void setAccountIdentification(String accountIdentification) {
		this.accountIdentification = accountIdentification;
	}
	public double getAmmount() {
		return ammount;
	}
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	
}
