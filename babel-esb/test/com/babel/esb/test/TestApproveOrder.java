package com.babel.esb.test;

import org.junit.Before;
import org.junit.Test;

import com.babel.esb.api.ApproveOrderMessage;
import com.babel.esb.api.ApproveOrderResponse;
import com.babel.esb.api.BabelGateway;
import com.babel.esb.impl.BabelGatewayImpl;

public class TestApproveOrder {
	BabelGateway esb;
	@Before
	public void init(){
		this.esb=new BabelGatewayImpl();
		((BabelGatewayImpl)this.esb).init();
	}
	@Test
	public void test(){
		
//            URLClassLoader classLoader = (URLClassLoader)TestApproveOrder.class.getClassLoader();
//            System.out.println(Arrays.toString(classLoader.getURLs()));
//    
		ApproveOrderResponse r=esb.handleMessage(new ApproveOrderMessage(4L));
		System.out.println("invoice id:"+r.getInvoice().getId());
		System.out.println("production plan id:"+r.getProductionPlan().getId());
		System.out.println("order id:"+r.getOrder().getId());
		System.out.println("order process id:"+r.getProcessData().getId());
		
		System.out.println("order associated order process id:"+r.getOrder().getProcessId());
		System.out.println("order process state:"+r.getProcessData().getProcessState());
		
	}
}
