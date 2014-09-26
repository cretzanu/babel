package com.babel.order.socket;

import com.babel.order.CreateOrder;
import com.babel.order.Order;
import com.babel.order.OrderLine;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 11:27:31 AM
 */
public class CreateCSVOrderImpl implements CreateCSVOrder {

	private CreateOrder createOrderDelegate;

	public CreateCSVOrderImpl(CreateOrder delegate) {
		createOrderDelegate=delegate;

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param p
	 *            - expecting CSV String implementing the CreateOrder from CSV
	 *            protocol
	 */
	public void createCSVOrder(String p) {
		Order order=new Order();
		try{
		String[] lines = p.split("\n");
		//first line should contain customer's name
		order.setCustomerName(lines[0]);
		//second line should contain the email address
		order.setCustomerEmail(lines[1]);
		//third line should contain the delivery address
		order.setDeliveryAddress(lines[2]);
		//next lines should contain the items
		OrderLine l=null;
		String[] line=null;
		for (int i=3; i<lines.length; i++){
			line=lines[i].split(", ");
			l=new OrderLine();
			l.setItem(line[0]);
			l.setQuantity(Double.valueOf(line[1]));
			l.setPrice(Double.valueOf(line[2]));
			order.addOrderLine(l);
		}
		}catch(Throwable t){
			t.printStackTrace();
			throw new RuntimeException ("Invalid format! "+t.getMessage()); 
		}
		
		try{
			this.getCreateOrderDelegate().createOrder(order);
		}catch(Throwable t){
			t.printStackTrace();
			throw new RuntimeException ("Internal Server Error ");
		}
		
	}

	public CreateOrder getCreateOrderDelegate() {
		return createOrderDelegate;
	}

	public void setCreateOrderDelegate(CreateOrder createOrderDelegate) {
		this.createOrderDelegate = createOrderDelegate;
	}
	
	
}// end CreateCSVOrderImpl