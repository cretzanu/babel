package com.babel.esb.transformer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.babel.accounting.Invoice;
import com.babel.esb.api.ApproveOrderResponse;
import com.babel.order.Order;
import com.babel.order.process.OrderProcessData;
import com.babel.order.process.ProcessStateType;
import com.babel.production.ProductionPlan;

public class ApproveOrderResponseTransformer {
	/**
	 * Aggregates the results of the process chain
	 * 
	 * @param processResults
	 *            - content: one list coming from processDataAggregator-channel
	 *            and one OrderProcessData object coming from
	 *            createOrderProcessData-request channel
	 * @return
	 */
	public ApproveOrderResponse aggregateResponse(List<Object> result) {
		 System.out.println("aggregateResponse list size---->"+result.size());
		// System.out.println(result);
		// create one list with data objects
		List<Object> processResults = new LinkedList<Object>();
		for (Object r : result) {
			if (r instanceof Collection)
				processResults.addAll((Collection) r);
			else
				processResults.add(r);
		}
		//  aggreagate the response
		ApproveOrderResponse response = buildResponse(processResults);
		// update order data to link the Order with the newly created Order Process 
		response.getOrder().setProcessId(
				"ORDER-PROCESS-" + response.getProcessData().getId());
		return response;
	}

	private ApproveOrderResponse buildResponse(List<Object> processResults) {
		ApproveOrderResponse response = new ApproveOrderResponse();
		for (Object r : processResults) {
			if (r instanceof Order)
				response.setOrder((Order) r);
			else if (r instanceof Invoice)
				response.setInvoice((Invoice) r);
			else if (r instanceof ProductionPlan)
				response.setProductionPlan((ProductionPlan) r);
			else if (r instanceof OrderProcessData)
				response.setProcessData((OrderProcessData) r);
		}
		return response;
	}

	/**
	 * Aggregates partial results of the process chain according to the
	 * definition of processDataAggregator-channel
	 * 
	 * @param processResults
	 * @return
	 */
	public OrderProcessData aggregateOrderProcessData(
			List<Object> processResults) {
		System.out.println("aggregateOrderProcessData list size---->"+processResults.size());
		ApproveOrderResponse interim = this.buildResponse(processResults);
		System.out.println(processResults.toArray());
		// link everything in a new OrderProcessData entity
		OrderProcessData opd = new OrderProcessData();
		opd.setOrderId(interim.getOrder().getId());
		opd.setProductionPlanId(interim.getProductionPlan().getId());
		opd.setProformaInvoiceId(interim.getInvoice().getId());
		opd.setProcessState(ProcessStateType.APPROVED);
		return opd;
	}

	/**
	 * 
	 * @param result
	 *            - the list is expected to contain two objects: 
	 *            1) the previous   {@link #ApproveOrderResponse}
	 *            2) the updated Order object
	 * @return
	 */
	public ApproveOrderResponse aggregateFinalResponse(List<Object> result) {
		System.out.println("aggregateFinalResponse list size---->"+result.size());
		Order updatedOrder=null;
		ApproveOrderResponse response=null;
		for (Object r : result) {
			if (r instanceof Order)
				updatedOrder=((Order) r);
			else if (r instanceof ApproveOrderResponse)
				response=(ApproveOrderResponse)r;
		}
		response.setOrder(updatedOrder);
		return response;
	}

}
