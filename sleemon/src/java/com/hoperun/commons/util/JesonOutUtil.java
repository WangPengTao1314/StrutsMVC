package com.hoperun.commons.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;


public class JesonOutUtil{
	private JsonObject jsonInputObj;
	ArrayList dtlList = new ArrayList();
	HashMap resMap = new HashMap();
	public JesonOutUtil(String jsonData){
		JsonParser parser = new JsonParser();
		JsonElement jsonEl = parser.parse(jsonData);
		JsonObject jsonObj = jsonEl.getAsJsonObject();
		JsonElement mbfSerEl=jsonObj.get("MbfService");
		JsonArray jsonserArr= mbfSerEl.getAsJsonArray();
		JsonElement jsonNd =  jsonserArr.get(0);
		JsonObject jsonserObj = jsonNd.getAsJsonObject();
		JsonElement inputEl=jsonserObj.get("output1");
		JsonArray jsonInputArr = inputEl.getAsJsonArray();
		JsonElement inputNd = jsonInputArr.get(0);
		jsonInputObj = inputNd.getAsJsonObject();
	}
	
	 

	public HashMap getMbHead(){
		HashMap headMap = new HashMap();
		JsonElement mbfHeadEl=jsonInputObj.get("MbfHeader");
		JsonArray jsonHeadArr= mbfHeadEl.getAsJsonArray();
		JsonElement jsonEl =  jsonHeadArr.get(0);
		JsonObject headObj= jsonEl.getAsJsonObject();
		Iterator it = headObj.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String fldName = (String)entry.getKey();
			if("ServiceResponse".equals(fldName)){
				String rsponseJson = entry.getValue().toString();
				setResponse(rsponseJson);
			}else{
				JsonPrimitive jsonPrim = (JsonPrimitive)entry.getValue();
				headMap.put(fldName, jsonPrim.getAsString());
			}
		}
		return headMap;
	}
	
	
	public HashMap getMbBody1(){
		HashMap bodyMap = new HashMap();
		JsonElement mbfHeadEl = jsonInputObj.get("MbfBody");
		JsonArray jsonHeadArr = mbfHeadEl.getAsJsonArray();
		JsonElement jsonEl =  jsonHeadArr.get(0);
		JsonObject headObj = jsonEl.getAsJsonObject();
		Iterator it = headObj.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String fldName = (String)entry.getKey();
			bodyMap.put(fldName, entry.getValue());
			 
		}
		return bodyMap;
	}
	
	public void setResponse(String rsponseJson){
		JsonParser parser = new JsonParser();
		JsonElement jsonEl = parser.parse(rsponseJson);
		if(jsonEl.isJsonArray()){
			JsonArray array= jsonEl.getAsJsonArray();
			for(int i=0;i<array.size();i++){
				JsonElement jsonNd =  array.get(i);
				JsonObject jsonObj = jsonNd.getAsJsonObject();
				Iterator it = jsonObj.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					String fldName = (String)entry.getKey();
					Object obj = entry.getValue();
					if(!(obj instanceof JsonNull)){
						JsonPrimitive jsonPrim = (JsonPrimitive)entry.getValue();
						resMap.put(fldName, jsonPrim.getAsString());
					}
				}
			}
		}
	}
	
	public ArrayList getMbBody(){
		ArrayList bodyList = new ArrayList();
		JsonElement mbfBodyEl=jsonInputObj.get("MbfBody");
		JsonArray jsonBodyArr= mbfBodyEl.getAsJsonArray();
		for(int i=0;i<jsonBodyArr.size();i++){
			HashMap bodyMap = new HashMap();
			JsonElement jsonEl =  jsonBodyArr.get(i);
			JsonObject bodyObj= jsonEl.getAsJsonObject();		
			Iterator it = bodyObj.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String fldName = (String)entry.getKey();
				if("DETAIL".equalsIgnoreCase(fldName)||"ItemInfo".equals(fldName)){
					String dtlJson = entry.getValue().toString();
					bodyMap.put(fldName,dtlJson);
//					setMbDtl(dtlJson);
				}else{
					Object obj = entry.getValue();
					if(!(obj instanceof JsonNull)){
						JsonPrimitive jsonPrim = (JsonPrimitive)entry.getValue();
						String fldValue = jsonPrim.getAsString();
						bodyMap.put(fldName,fldValue);
					}
				}
			}
			bodyList.add(bodyMap);
		}
		return bodyList;
	}
	
	public HashMap getResponse(){
		return resMap;
	}
	
	public void setMbDtl(String jsonDtlData){
		JsonParser parser = new JsonParser();
		JsonElement jsonEl = parser.parse(jsonDtlData);
		if(jsonEl.isJsonArray()){
			JsonArray array= jsonEl.getAsJsonArray();
			for(int i=0;i<array.size();i++){
				HashMap dtlMap = new HashMap();
				JsonElement jsonNd =  array.get(i);
				JsonObject jsonObj = jsonNd.getAsJsonObject();
				Iterator it = jsonObj.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					String fldName = (String)entry.getKey();
					JsonPrimitive jsonPrim = (JsonPrimitive)entry.getValue();
					dtlMap.put(fldName, jsonPrim.getAsString());
				}
				dtlList.add(dtlMap);
			}
		}
		
	}
	
	public ArrayList getMbDtlList(){
		return dtlList;
	}
	
	public static String getImplJson(LinkedHashMap headMap,LinkedHashMap dataMap){
		HashMap jsonMap = new HashMap();
		ArrayList iputList = new ArrayList();
		LinkedHashMap iputMap = new LinkedHashMap();
		ArrayList headList = new ArrayList();
		headList.add(headMap);
		iputMap.put("MbfHeader", headList);
		ArrayList bodyList = new ArrayList();
		bodyList.add(dataMap);
		iputMap.put("MbfBody", bodyList);
		iputList.add(iputMap);
		ArrayList serList = new ArrayList();
		HashMap serMap = new HashMap();
		serMap.put("input1", iputList);
		serList.add(serMap);
		jsonMap.put("MbfService", serList);
		String strJson = new Gson().toJson(jsonMap);
		return strJson;
	}
	
	public static String getImplRsponseJson(LinkedHashMap headMap,LinkedHashMap dataMap){
		HashMap jsonMap = new HashMap();
		ArrayList iputList = new ArrayList();
		LinkedHashMap iputMap = new LinkedHashMap();
		ArrayList headList = new ArrayList();
		headList.add(headMap);
		iputMap.put("MbfHeader", headList);
		ArrayList bodyList = new ArrayList();
		bodyList.add(dataMap);
		iputMap.put("MbfBody", bodyList);
		iputList.add(iputMap);
		ArrayList serList = new ArrayList();
		HashMap serMap = new HashMap();
		serMap.put("output1", iputList);
		serList.add(serMap);
		jsonMap.put("MbfService", serList);
		String strJson = new Gson().toJson(jsonMap);
		return strJson;
	}
}
