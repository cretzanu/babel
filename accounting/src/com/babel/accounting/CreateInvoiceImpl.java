package com.babel.accounting;

import com.babel.core.function.crud.CreateImpl;

public class CreateInvoiceImpl extends CreateImpl implements CreateInvoice{

	@Override
	public Invoice createInvoice(Invoice p) {
		return super.create(p);
	}

}
