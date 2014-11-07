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
		
//		   URLClassLoader classLoader = (URLClassLoader)TestApproveOrder.class.getClassLoader();
//         System.out.println(Arrays.toString(classLoader.getURLs()));
// when using JBoss4.2, delete server/default/lib/spring2.5.jar 
// and replace it with  the Spring version used in this project 
//	  (e.g. Spring-core-3.1.3-RELEASE.jar (from .m2/org/springfraemwork) 
		ApproveOrderResponse r=esb.handleMessage(new ApproveOrderMessage(4L));
		System.out.println("invoice id:"+r.getInvoice().getId());
		System.out.println("production plan id:"+r.getProductionPlan().getId());
		System.out.println("order id:"+r.getOrder().getId());
		System.out.println("order process id:"+r.getProcessData().getId());
		
		System.out.println("order associated order process id:"+r.getOrder().getProcessId());
		System.out.println("order process state:"+r.getProcessData().getProcessState());
		
	}
}
