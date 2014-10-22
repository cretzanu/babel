package com.babel.accounting;

public interface OperateBankAccount {
public static final String COMPANY_BANK_ACCOUNT="IBAN12345";//usually specified in a config. somewhere
	public void debit(String accountIdentification, double amount);
	public void credit(String accountIdentification, double amount);
}
