/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.util;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.Properties;
import com.hoperun.sys.model.XswModel;

// TODO: Auto-generated Javadoc
/**
 * The Class NumberFormat.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class NumberFormat {

	/** The Constant thousandFlag. */
	public static final int thousandFlag = Properties.getInt("THOUSANDFLAG");
	
	/** The Constant qfw. */
	private static final int qfw = 3;
    
    /**
     * <li>格式化小数位</li>
     * <li>在插入数据库，小数位格式化用</li>.
     * 
     * @param value the value
     * @param aModle the a modle
     * 
     * @return the string
     */
	public static String doFormatforBO(String value,XswModel aModle) {
		String returnString = value;
		if ((value == null) || (value.equals(""))){
			returnString = "";
		}else {
			returnString = StringUtils.replace(returnString, ",", "");
			if (aModle != null) {
				int tempInt = 0;
				String roundTypeString = aModle.getROUNDTYPE();
				String decimalString = aModle.getDECIMALS();
				if ((roundTypeString == null) || ("".endsWith(roundTypeString))) {
					if((decimalString ==null) || ("".endsWith(decimalString))) {
						returnString = value;
					}else if ((decimalString !=null) && (!"".endsWith(decimalString))) {
	    				int intDecimal = Integer.parseInt(decimalString);
						if (value.indexOf(".")!= -1) {
							int positionOfPoint = value.indexOf(".");
							int lengthAfterPoint= value.length()-1 - positionOfPoint;
							String stringBeforPoint = value.substring(0, positionOfPoint);
							String newStringAfterPoint = "";
							if (lengthAfterPoint > intDecimal) {
								StringBuffer tempStringBuffer = new StringBuffer(stringBeforPoint);
								if (intDecimal > 0) {
									newStringAfterPoint = value.substring(positionOfPoint, positionOfPoint+intDecimal+1);
								}else {
									newStringAfterPoint = value.substring(positionOfPoint + 1, positionOfPoint+intDecimal+1);
								}
								
								tempStringBuffer.append(newStringAfterPoint);
								returnString = tempStringBuffer.toString();
							}else {
								StringBuffer tempStringBuffer = new StringBuffer(value);
								for(int i=0; i<intDecimal - lengthAfterPoint; i++){
									tempStringBuffer.append("0");
								}
								returnString = tempStringBuffer.toString();
							}
						}else {
							StringBuffer tempStringBuffer = new StringBuffer(value);
							if (intDecimal>0) {
								tempStringBuffer.append(".");
								for(int i=0; i<intDecimal; i++){
									tempStringBuffer.append("0");
								}
							}
							returnString = tempStringBuffer.toString();
						}
					}
				}else{
	    			try {
				    	switch(Integer.parseInt(roundTypeString)){
				    	case 1:
				    		BigDecimal temp = new BigDecimal(value);
					    	BigDecimal one = new BigDecimal("1");
				    		if ((decimalString !=null) && (!"".endsWith(decimalString))) {
						    	returnString = temp.divide(one, Integer.parseInt(decimalString), 4).toString();
							}else {
								if (value.indexOf(".") != 1) {
									int length = value.length() - 1 - value.indexOf(".")-1;
									returnString = temp.divide(one, Integer.parseInt(String.valueOf(length)), 4).toString();
								}else {
									returnString = temp.divide(one, Integer.parseInt(String.valueOf(0)), 4).toString();
								}
							}
					    	break;
				    	case 2:
				    		if(value.indexOf(".")!= -1 ){
				    			returnString = value.substring(0,value.indexOf("."));
				    			tempInt = Integer.parseInt(returnString);
					    		if (value.indexOf("-") != -1) {
					    		}else {
					    			tempInt++;
					    		}
					    		returnString = String.valueOf(tempInt);
				    		}
				    		break;
				    	case 3:
				    		if(value.indexOf(".")!= -1 ){
				    			returnString = value.substring(0,value.indexOf("."));
				    			tempInt = Integer.parseInt(returnString);
				    			if (value.indexOf("-") != -1) {
				    				tempInt--;
				    			}
					    		returnString = String.valueOf(tempInt);
				    		}
				    		break;
				    	}// switch
				    } catch (Exception e) {
				    	e.printStackTrace();
				    }
				}
			}
		}// first else
	    return returnString;
	}
	
	/**
	 * <li>格式化小数位</li>
	 * <li>在展现页面，小数位格式化</li>.
	 * 
	 * @param value the value
	 * @param aModle the a modle
	 * 
	 * @return the string
	 */
	public static String doFormatforVO(String value,XswModel aModle) {
		try {
			if(value==null||value.equals(""))
		    	return   "";
			value = doFormatforBO(value, aModle);
			String formateString = String.valueOf(value);
			String oneString = "";
			String twoString = "";
			if (formateString.indexOf(".")!=-1){
				oneString = formateString.substring(0,formateString.indexOf("."));
				twoString = formateString.substring(formateString.indexOf(".")+1,formateString.length());
				oneString = formatSL(oneString, false);
				twoString = formatSL(twoString, true);
				StringBuffer newString = new StringBuffer(oneString);
				newString.append(".");
				newString.append(twoString);
				formateString = newString.toString();
			}else {
				formateString = formatSL(formateString, false);
				}
			return formateString;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Format sl.
	 * 
	 * @param value the value
	 * @param pointAfter the point after
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public static String formatSL(String value, boolean pointAfter) throws Exception{
		if(0== thousandFlag){
			return value;
		}
		try {
			if (value.length()<=qfw)
				return value;
			StringBuffer sb = new StringBuffer(value);
			if(!pointAfter){
				for(int i =sb.length() ;i>qfw;i=i-qfw){
					sb.insert(i-qfw,',');
				}
			}else if (pointAfter) {
				for(int i =qfw ;i<sb.length();i=i+qfw){
					sb.insert(i,',');
				}
			}
			return sb.toString();
		} catch (Exception e) {
			throw e;
		}
	}
}
