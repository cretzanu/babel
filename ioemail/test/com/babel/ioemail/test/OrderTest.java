package com.babel.ioemail.test;

import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import com.babel.ioemail.EmailMessageHandler;
import com.babel.ioemail.ReadEmails;
import com.babel.ioemail.ReadEmailsImpl;
import com.babel.ioemail.handlers.CreateOrderHandler;


public class OrderTest {

	ReadEmails readEmailsWorker;

	@Before
	public void init() throws NamingException {
		List<EmailMessageHandler> handlers=new LinkedList<EmailMessageHandler>();
		handlers.add(new CreateOrderHandler());
		readEmailsWorker=new ReadEmailsImpl(handlers);
	}
	
	@Test
	public void testCreateOrderFromEmail(){
		readEmailsWorker.readEmails();
	}

}
