package edu.somewhere;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.handler.WSHandlerConstants;

public class testCXF {
	
	
	public static void main(String args[]) throws Exception{
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance(); 
	    org.apache.cxf.endpoint.Client client = dcf.createClient("http://isse.cqu.edu.cn:80/exam/remoting/soap/kim/v2_0/permissionService?wsdl"); 
	    Map<String, Object> outProps = new HashMap<String, Object>();
		outProps.put( WSHandlerConstants.ACTION, "Signature" );
		outProps.put( WSHandlerConstants.USER, "rice" );
		outProps.put( WSHandlerConstants.SIG_PROP_FILE, "client-sign.properties" );
		outProps.put( WSHandlerConstants.PW_CALLBACK_CLASS, testCallBackHandler.class.getName() ); 
	    WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor( outProps );
	    client.getEndpoint().getOutInterceptors().add( wssOut );
	    
	    Object[] objects=client.invoke("hasPermission","20152660","KR_EXM","ViewMessagePagePermission");    
	    System.out.println(objects[0].toString()); 
	}
}
