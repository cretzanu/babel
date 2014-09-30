package com.babel.order.socket.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * Client-server test. Create a client-socket and send the text according to
 * application-specific CReateCSVOrder protocol
 * 
 * @author liviu.cretu
 * 
 */
public class CreateCSVOrderClientServerTest {

	@Test
	public void test1() throws UnknownHostException, IOException {
		Socket clientSocket = null;
		try {
			clientSocket = new Socket("localhost", 4444);
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			//Send the text message with the new Order 
			//Remember the text format has to implement the app-specific protocol
			out.println(CreateCSVOrderTest.buildNewOrderCSVText());
			out.print((char)13);//signal the end of the message
			
			System.out.println("server response (the new order's external ID): " + in.readLine());
			clientSocket.close();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			clientSocket.close();
		}


	}
}
