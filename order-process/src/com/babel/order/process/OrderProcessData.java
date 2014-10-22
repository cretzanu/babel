package com.babel.order.process;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.babel.core.data.PersistentEntity;

@Entity
public class OrderProcessData extends PersistentEntity{

	
	private Long orderId;
	@Enumerated(EnumType.STRING)
	private ProcessStateType processState;
	private Long productionPlanId;
	private Long proformaInvoiceId;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public ProcessStateType getProcessState() {
		return processState;
	}
	public void setProcessState(ProcessStateType processState) {
		this.processState = processState;
	}
	public Long getProductionPlanId() {
		return productionPlanId;
	}
	public void setProductionPlanId(Long productionPlanId) {
		this.productionPlanId = productionPlanId;
	}
	public Long getProformaInvoiceId() {
		return proformaInvoiceId;
	}
	public void setProformaInvoiceId(Long proformaInvoiceId) {
		this.proformaInvoiceId = proformaInvoiceId;
	}
	
}
