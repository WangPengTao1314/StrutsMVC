/**
  *@module 系统模块   
  *@func 客户端JOBModel 
  *@version 1.1
  *@author 朱晓文     
*/
package com.hoperun.commons.job.model;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientJobModel.
 */
public class ClientJobModel  {
   
   /** The BIL l_ type. */
   private String BILL_TYPE;
   
   /** The CAL l_ back. */
   private String CALL_BACK;
   
   /** The PARAMS. */
   private HashMap<String,String> PARAMS;

/**
 * this is javaAutoDoc.
 * 
 * @return return value
 * 
 * @author administrator
 */
public String getBILL_TYPE() {
	return BILL_TYPE;
}

/**
 * this is javaAutoDoc.
 * 
 * @param bill_type the bill_type
 * 
 * @author administrator
 */
public void setBILL_TYPE(String bill_type) {
	BILL_TYPE = bill_type;
}

/**
 * this is javaAutoDoc.
 * 
 * @return return value
 * 
 * @author administrator
 */
public String getCALL_BACK() {
	return CALL_BACK;
}

/**
 * this is javaAutoDoc.
 * 
 * @param call_back the call_back
 * 
 * @author administrator
 */
public void setCALL_BACK(String call_back) {
	CALL_BACK = call_back;
}

/**
 * this is javaAutoDoc.
 * 
 * @return return value
 * 
 * @author administrator
 */
public HashMap<String,String> getPARAMS() {
	if(PARAMS==null)
	{
		PARAMS= new HashMap<String,String>();
	}
	return PARAMS;
}

/**
 * this is javaAutoDoc.
 * 
 * @param params the params
 * 
 * @author administrator
 */
public void setPARAMS(HashMap<String,String> params) {
	PARAMS = params;
}

}
