package com.babel.order.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A Thread that delegates to a CreateCSVOrder concrete implementation the
 * business logic for creating a new Order from the CSV string received.
 * 
 * @see CreateCSVOrderImpl#createCSVOrder(String) for the application-specific
 *      protocol.
 * @author liviu.cretu
 * 
 */
public class OrderServerThread extends Thread {

	private Socket socket = null;
	private CreateCSVOrder worker = null;

	public OrderServerThread(Socket socket) {
		super("OrderServerThread");
		//keep the socket initiated by the client call
		this.socket = socket;
		//inject the worker that will actually execute the business logic
		this.worker = OrderSocketFactory.createCSVOrderFactory();
	}

	public void run() {
		PrintWriter out=null;
		BufferedReader in =null;
		try {

			 out = new PrintWriter(socket.getOutputStream(), true);
			//socket.getInputStream().
			 in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			String inputLine, outputLine;
			//expecting a multi-line text according to CreateCSVOrder protocol
			//read the string message line by line and reconstruct the client's message
			StringBuffer buf=new StringBuffer();
			
			while ((inputLine = in.readLine()) != null && inputLine.length()>0) {

				buf.append(inputLine).append("\n");			
					
			}
			System.out.println("client message"+ buf.toString());
			//delegate the business logic to the associated worker
			String orderExternalId=this.worker.createCSVOrder(buf.toString());
			System.out.println("new order created:"+orderExternalId);
			//respond with the new order id
			out.println(orderExternalId);

		} catch (Throwable e) {
			e.printStackTrace();
			out.print(e.getMessage());
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
