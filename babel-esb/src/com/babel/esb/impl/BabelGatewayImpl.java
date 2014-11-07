package com.babel.esb.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.babel.esb.api.ApproveOrderMessage;
import com.babel.esb.api.ApproveOrderResponse;
import com.babel.esb.api.BabelGateway;
import com.babel.esb.api.OrderReceivedEvent;
@Stateless
@Remote (BabelGateway.class)
public class BabelGatewayImpl implements BabelGateway{
	 ApplicationContext context;
	 BabelGateway gateway;
	 
	 @PostConstruct
	 public void init(){
		 context = new ClassPathXmlApplicationContext("esb-context.xml");
		 gateway=context.getBean("babelGateway",BabelGateway.class );
	 }
	
	@Override
	public ApproveOrderResponse handleMessage(ApproveOrderMessage msg) {
		return this.gateway.handleMessage(msg);
		
	}
	@Override
	public void handleMessage(OrderReceivedEvent msg) {
		 this.gateway.handleMessage(msg);
		
	}
	

}
