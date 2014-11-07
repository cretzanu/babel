package com.babel.esb.api;


public interface BabelGateway {

	public ApproveOrderResponse handleMessage(ApproveOrderMessage msg);
	public void  handleMessage(OrderReceivedEvent msg);
}
