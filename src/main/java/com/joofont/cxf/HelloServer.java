package com.joofont.cxf;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class HelloServer {

	public static void main(String[] args) {
        JaxWsDynamicClientFactory factory=JaxWsDynamicClientFactory.newInstance();
        //根据服务地址创建客户端
        Client client=factory.createClient("http://192.168.30.21:8080/webservice/HelloJava?wsdl");
        Object [] result;
        try {
            result=client.invoke("getBook", 9);
            System.out.println(result[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
