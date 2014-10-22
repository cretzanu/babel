package com.babel.accounting.impl;

import com.babel.accounting.OperateBankAccount;
import com.babel.core.function.crud.CreateImpl;

public class OperateBankAccountImpl extends CreateImpl implements OperateBankAccount {

	@Override
	public void debit(String accountIdentification, double amount) {
		super.create(new BankAccountOperation(accountIdentification, amount, "D"));
		
	}

	@Override
	public void credit(String accountIdentification, double amount) {
		super.create(new BankAccountOperation(accountIdentification, amount, "C"));
		
	}

}
