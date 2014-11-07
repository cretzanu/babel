package com.babel.esb.api;

public class ApproveOrderMessage {

	private Long orderId;

	public ApproveOrderMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApproveOrderMessage(Long orderId) {
		super();
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
