package com.hoperun.commons.webservice;

import java.util.ArrayList;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPBinding;

import com.hoperun.commons.model.Consts;
public class CommWebServiceClient {
	// 名字空间
	public static final String targetNamespace = Consts.WS_NAME_SPACE;
	// 服务名
	public static final String serName = Consts.WS_SER_NAME;
	// 端口名
	public static final String pName = Consts.WS_PORT_NO;
	// 服务地址
	public static final String endpointAddress = Consts.WS_WSDL;
	// ESB 验证用户名
	public static final String WS_USERNAME = Consts.WS_USERNAME;
	// ESB 验证密码
	public static final String WS_PASSWORD = Consts.WS_PASSWORD;
	

	public String callWebService(String method,ArrayList bussParam)throws Exception{
		QName serviceName = new QName(targetNamespace, serName);
		QName portName = new QName(targetNamespace, pName);
		//创建服务service
		javax.xml.ws.Service service = Service.create(serviceName);
		service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING,endpointAddress);
		//创建dispacth
		Dispatch<SOAPMessage> dispatch = service.createDispatch(portName,
				SOAPMessage.class, Service.Mode.MESSAGE);
		
		BindingProvider bp = (BindingProvider) dispatch;
		Map<String, Object> rc = bp.getRequestContext();
		rc.put(BindingProvider.SOAPACTION_USE_PROPERTY, Boolean.TRUE);
		rc.put(BindingProvider.SOAPACTION_URI_PROPERTY, method);
		//创建soapmessage
		MessageFactory factory = ((SOAPBinding) bp.getBinding()).getMessageFactory();
		SOAPMessage request = factory.createMessage();
		SOAPBody body = request.getSOAPBody();
		//传递参数
		QName payloadName = new QName(targetNamespace, method, "ns1");
		SOAPBodyElement payload = body.addBodyElement(payloadName);
		ArrayList paramList = getParamList(bussParam);
		for(int i=0;i<paramList.size();i++){
			String strParam = (String)paramList.get(i);
			SOAPElement message = payload.addChildElement("arg"+i);
			message.addTextNode(strParam);
		}
		
		
//		SOAPElement name = payload.addChildElement("userName");
//		name.addTextNode("admin");
//		
//		SOAPElement passw = payload.addChildElement("passWord");
//		passw.addTextNode("");
//		
//		String strParam = (String)paramList.get(3);
//		SOAPElement message = payload.addChildElement("messageData");
//		message.addTextNode(strParam);
		
		SOAPMessage reply = null;
        //通过dispatch传递消息并返回相应的消息
		try {
			reply = dispatch.invoke(request);
		} catch (WebServiceException wse) { 
			wse.printStackTrace();
		}
        //解析消息
		SOAPBody soapBody = reply.getSOAPBody();
		SOAPBodyElement nextSoapBodyElement = (SOAPBodyElement) soapBody
				.getChildElements().next();
		SOAPElement soapElement = (SOAPElement) nextSoapBodyElement
				.getChildElements().next();
		return soapElement.getValue();
	}
	
	/**
	 * 直接掉U9的接口
	 * @param method
	 * @param strJsonData
	 * @return
	 * @throws Exception
	 */
	public String callU9WebService(String method,String strJsonData)throws Exception{
		String u9_targetNamespace = "http://newua.chinabed.com/";
		String u9_serName = "UAEsbServices";
		String u9_pName = "UAEsbServicesSoap";
		String u9_endpointAddress = "http://192.168.10.97/UAXLMService/UAEsbServices.asmx?WSDL";
		String u9_WS_USERNAME = "uf";
		String u9_WS_PASSWORD = "asdzxc";
		QName serviceName = new QName(u9_targetNamespace, u9_serName);
		QName portName = new QName(u9_targetNamespace, u9_pName);
		javax.xml.ws.Service service = Service.create(serviceName);
		service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING,
				u9_endpointAddress);
		Dispatch<SOAPMessage> dispatch = service.createDispatch(portName,
				SOAPMessage.class, Service.Mode.MESSAGE);
		BindingProvider bp = (BindingProvider) dispatch;
		Map<String, Object> rc = bp.getRequestContext();
		rc.put(BindingProvider.SOAPACTION_USE_PROPERTY, Boolean.TRUE);
		rc.put(BindingProvider.SOAPACTION_URI_PROPERTY, method);
		MessageFactory factory = ((SOAPBinding) bp.getBinding())
				.getMessageFactory();
		SOAPMessage request = factory.createMessage();
		SOAPBody body = request.getSOAPBody();
		QName payloadName = new QName(u9_targetNamespace, method, "ns1");
		SOAPBodyElement payload = body.addBodyElement(payloadName);
		
		SOAPElement msg1 = payload.addChildElement("userName");
	    msg1.addTextNode(u9_WS_USERNAME);
	    
	    SOAPElement msg2 = payload.addChildElement("passWord");
	    msg2.addTextNode(u9_WS_PASSWORD);
	    
	    SOAPElement msg3 = payload.addChildElement("messageData");
	    msg3.addTextNode(strJsonData);
	    
		SOAPMessage reply = null;

		try {
			reply = dispatch.invoke(request);
		} catch (WebServiceException wse) {
			wse.printStackTrace();
		}

		SOAPBody soapBody = reply.getSOAPBody();
		SOAPBodyElement nextSoapBodyElement = (SOAPBodyElement) soapBody
				.getChildElements().next();
		SOAPElement soapElement = (SOAPElement) nextSoapBodyElement
				.getChildElements().next();
		return soapElement.getValue();
	}

	
	/**
	 * 自己做测试的 
	 * @param method
	 * @param strJsonData
	 * @return
	 * @throws Exception b 
	 */
	public String callSelfTestWebService(String method,String strJsonData)throws Exception{
		String u9_targetNamespace = "http://webservice.commons.hoperun.com/";
		String u9_serName = "CommWebServiceService";
		String u9_pName = "CommWebServicePort";
		String u9_endpointAddress = "http://60.190.212.158:8075/DMCommWebService?wsdl";//"http://192.168.10.184:8075/DMCommWebService?wsdl";//http://60.190.212.158:8075/DMCommWebService?wsdl
		String u9_WS_USERNAME = "userName";
		String u9_WS_PASSWORD = "abc123";
		QName serviceName = new QName(u9_targetNamespace, u9_serName);
		QName portName = new QName(u9_targetNamespace, u9_pName);
		javax.xml.ws.Service service = Service.create(serviceName);
		service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING,
				u9_endpointAddress);
		Dispatch<SOAPMessage> dispatch = service.createDispatch(portName,
				SOAPMessage.class, Service.Mode.MESSAGE);
		BindingProvider bp = (BindingProvider) dispatch;
		Map<String, Object> rc = bp.getRequestContext();
		rc.put(BindingProvider.SOAPACTION_USE_PROPERTY, Boolean.TRUE);
		rc.put(BindingProvider.SOAPACTION_URI_PROPERTY, method);
		MessageFactory factory = ((SOAPBinding) bp.getBinding())
				.getMessageFactory();
		SOAPMessage request = factory.createMessage();
		SOAPBody body = request.getSOAPBody();
		QName payloadName = new QName(u9_targetNamespace, method, "ns1");
	 
		SOAPBodyElement payload = body.addBodyElement(payloadName);
//		SOAPElement msg1 = payload.addChildElement("userName");
//	    msg1.addTextNode(u9_WS_USERNAME);
//	    
//	    SOAPElement msg2 = payload.addChildElement("passWord");
//	    msg2.addTextNode(u9_WS_PASSWORD);
	    
	    SOAPElement msg3 = payload.addChildElement("messageData");
	    msg3.addTextNode(strJsonData);
	    
		SOAPMessage reply = null;

		try {
			reply = dispatch.invoke(request);
		} catch (WebServiceException wse) {
			wse.printStackTrace();
		}

		SOAPBody soapBody = reply.getSOAPBody();
		SOAPBodyElement nextSoapBodyElement = (SOAPBodyElement) soapBody
				.getChildElements().next();
		SOAPElement soapElement = (SOAPElement) nextSoapBodyElement
				.getChildElements().next();
		return soapElement.getValue();
	}
	private ArrayList getParamList(ArrayList bussParam){
		ArrayList paramList = new ArrayList();
		paramList.add(WS_USERNAME);
		paramList.add(WS_PASSWORD);
		paramList.addAll(bussParam);
		return paramList;
	}
	
	private ArrayList getParamList(ArrayList bussParam,String WS_USERNAME,String WS_PASSWORD){
		ArrayList paramList = new ArrayList();
		paramList.add(WS_USERNAME);
		paramList.add(WS_PASSWORD);
		paramList.addAll(bussParam);
		return paramList;
	}
	
	public void getOa(){
		try{
			CommWebServiceClient client = new CommWebServiceClient();
			ArrayList list = new ArrayList();
			list.add("usernAME");
			list.add("WSS");
			list.add("sdfdsfsdf");
			client.callWebService("doSyncTask",list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
	}
}
