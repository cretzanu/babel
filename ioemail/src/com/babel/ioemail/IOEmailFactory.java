package com.babel.ioemail;

public class IOEmailFactory {

	public static CreateOrder createOrderFactory(){
		return new CreateOrderImpl();
				
	}
	
}
