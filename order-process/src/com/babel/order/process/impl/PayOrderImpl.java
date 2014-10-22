package com.babel.order.process.impl;

import com.babel.accounting.OperateBankAccount;
import com.babel.order.Order;
import com.babel.order.process.PayOrder;

public class PayOrderImpl implements PayOrder {
	// Allow clients to inject services from different institutions
	// implementing the required interface
	private OperateBankAccount debitDelegate;
	private OperateBankAccount creditDelegate;

	
	@Override
	public void payOrder(Order order) {
		Double v = order.calculateOrderValue();
		debitDelegate.debit(order.getBankAccount(), v);
		creditDelegate.credit(OperateBankAccount.COMPANY_BANK_ACCOUNT, v);
	}

	public OperateBankAccount getDebitDelegate() {
		return debitDelegate;
	}

	public void setDebitDelegate(OperateBankAccount debitDelegate) {
		this.debitDelegate = debitDelegate;
	}

	public OperateBankAccount getCreditDelegate() {
		return creditDelegate;
	}

	public void setCreditDelegate(OperateBankAccount creditDelegate) {
		this.creditDelegate = creditDelegate;
	}

	
}
