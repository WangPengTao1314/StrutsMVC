package com.hoperun.commons.util;

import java.util.HashMap;
import java.util.Map;

public class ParamsPool {
	public static ParamsPool paramsPool;

	private Map<String,String> billIDMap = new HashMap<String,String>();
	
	public static ParamsPool getInstance(){
		if(null == paramsPool){
			paramsPool = new ParamsPool();
		}
		
		return paramsPool;
	}

	public Map<String, String> getBillIDMap() {
		return billIDMap;
	}

	public void setBillIDMap(Map<String, String> billIDMap) {
		this.billIDMap = billIDMap;
	}
	
	

	 
	
	
	
	

 
	
	

}
