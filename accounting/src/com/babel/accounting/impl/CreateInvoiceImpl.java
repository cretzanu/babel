package com.babel.accounting.impl;

import com.babel.accounting.CreateInvoice;
import com.babel.accounting.Invoice;
import com.babel.core.function.crud.CreateImpl;

public class CreateInvoiceImpl extends CreateImpl implements CreateInvoice{

	@Override
	public Invoice createInvoice(Invoice p) {
		return super.create(p);
	}

}
