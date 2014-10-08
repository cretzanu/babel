package com.babel.order;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name="HelloWS")
@SOAPBinding
(
      style = SOAPBinding.Style.DOCUMENT,
      use = SOAPBinding.Use.LITERAL,
      parameterStyle = SOAPBinding.ParameterStyle.WRAPPED
 )
public class HelloWS {
	
	@WebMethod
	public String greet( @WebParam(name = "name")
	String name )
	{
	   return "Hello" + name;
	}


}
