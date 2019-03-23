/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.util.security.DESPlus;

// TODO: Auto-generated Javadoc
/**
 * The Class StringUtil.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class StringUtil {

	/**
	 * <li>判断字符串是否为空值</li>
	 * <li>NULL、空格均认为空值</li>.
	 * 
	 * @param value the value
	 * 
	 * @return true, if checks if is empty
	 */
	public static boolean isEmpty(String value) {
		return null == value || "".equals(value.trim());
	}

	/**
	 * 重复字符串 如 repeatString("a",3) ==> "aaa".
	 * 
	 * @param src the src
	 * @param repeats the repeats
	 * 
	 * @return the string
	 * 
	 * @author uke
	 */
	public static String repeatString(String src, int repeats) {
		if (null == src || repeats <= 0) {
			return src;
		} else {
			StringBuffer bf = new StringBuffer();
			for (int i = 0; i < repeats; i++) {
				bf.append(src);
			}
			return bf.toString();
		}
	}

	/**
	 * 左对齐字符串 * lpadString("X",3); ==>" X" *.
	 * 
	 * @param src the src
	 * @param length the length
	 * 
	 * @return the string
	 */
	public static String lpadString(String src, int length) {
		return lpadString(src, length, " ");
	}

	/**
	 * 以指定字符串填补空位，左对齐字符串 * lpadString("X",3,"0"); ==>"00X".
	 * 
	 * @param src the src
	 * @param length the length
	 * @param single the single
	 * 
	 * @return the string
	 */
	public static String lpadString(String src, int length, String single) {
		if (src == null || length <= src.getBytes().length) {
			return src;
		} else {
			return repeatString(single, length - src.getBytes().length) + src;
		}
	}

	/**
	 * 右对齐字符串 * rpadString("9",3)==>"9 ".
	 * 
	 * @param src the src
	 * @param byteLength the byte length
	 * 
	 * @return the string
	 */
	public static String rpadString(String src, int byteLength) {
		return rpadString(src, byteLength, " ");
	}

	/**
	 * 以指定字符串填补空位，右对齐字符串 rpadString("9",3,"0")==>"900".
	 * 
	 * @param src the src
	 * @param single the single
	 * @param length the length
	 * 
	 * @return the string
	 */
	public static String rpadString(String src, int length, String single) {
		if (src == null || length <= src.getBytes().length) {
			return src;
		} else {
			return src + repeatString(single, length - src.getBytes().length);
		}
	}

	/**
	 * 去除,分隔符，用于金额数值去格式化.
	 * 
	 * @param value the value
	 * 
	 * @return the string
	 */
	public static String decimal(String value) {
		if (null == value || "".equals(value.trim())) {
			return "0";
		} else {
			return value.replaceAll(",", "");
		}
	}

	/**
	 * 在数组中查找字符串.
	 * 
	 * @param params the params
	 * @param name the name
	 * @param ignoreCase the ignore case
	 * 
	 * @return the int
	 */
	public static int indexOf(String[] params, String name, boolean ignoreCase) {
		if (params == null)
			return -1;
		for (int i = 0, j = params.length; i < j; i++) {
			if (ignoreCase && params[i].equalsIgnoreCase(name)) {
				return i;
			} else if (params[i].equals(name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 将字符转数组.
	 * 
	 * @param str the str
	 * 
	 * @return the string[]
	 */
	public static String[] toArr(String str) {
		String inStr = str;
		String a[] = null;
		try {
			if (null != inStr) {
				StringTokenizer st = new StringTokenizer(inStr, ",");
				if (st.countTokens() > 0) {
					a = new String[st.countTokens()];
					int i = 0;
					while (st.hasMoreTokens()) {
						a[i++] = st.nextToken();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	
	/**
	 * 将字符转数组.
	 * 
	 * @param str the str
	 * @param splitChar the split char
	 * 
	 * @return the string[]
	 */
	public static String[] toArr(String str,String splitChar) {
		String inStr = str;
		String a[] = null;
		try {
			if (null != inStr) {
				StringTokenizer st = new StringTokenizer(inStr, splitChar);
				if (st.countTokens() > 0) {
					a = new String[st.countTokens()];
					int i = 0;
					while (st.hasMoreTokens()) {
						a[i++] = st.nextToken();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	
	/**
	 * 字符串数组包装成字符串.
	 * 
	 * @param ary the ary
	 * @param s 包装符号如 ' 或 "
	 * 
	 * @return the string
	 */
	public static String toStr(String[] ary, String s) {
		if (ary == null || ary.length < 1)
			return "";
		StringBuffer bf = new StringBuffer();
		bf.append(s);
		bf.append(ary[0]);
		for (int i = 1; i < ary.length; i++) {
			bf.append(s).append(",").append(s);
			bf.append(ary[i]);
		}
		bf.append(s);
		return bf.toString();
	}

	/**
	 * 設置MESSAGE中的變量{0}...{N}
	 * 
	 * @param msg the msg
	 * @param vars the vars
	 * 
	 * @return the message
	 */
	public static String getMessage(String msg, String[] vars) {
		for (int i = 0; i < vars.length; i++) {
			msg = msg.replaceAll("\\{" + i + "\\}", vars[i]);
		}
		return msg;
	}

	/**
	 * Gets the message.
	 * 
	 * @param msg the msg
	 * @param var the var
	 * 
	 * @return the message
	 */
	public static String getMessage(String msg, String var) {
		return getMessage(msg, new String[] { var });
	}

	/**
	 * Gets the message.
	 * 
	 * @param msg the msg
	 * @param var the var
	 * @param var2 the var2
	 * 
	 * @return the message
	 */
	public static String getMessage(String msg, String var, String var2) {
		return getMessage(msg, new String[] { var, var2 });
	}

	/**
	 * 从Map中取String类型值.
	 * 
	 * @param map the map
	 * @param key the key
	 * 
	 * @return the map value
	 */
	public static Object getMapValue(Map map, Object key) {
		if (null == map || null == key)
			return "";
		Object value = map.get(key);
		return null == value ? "" : value;
	}

	/**
	 * 从Map中取Integer类型值.
	 * 
	 * @param map the map
	 * @param key the key
	 * 
	 * @return the map int value
	 */
	public static Object getMapIntValue(Map map, Object key) {
		if (null == map || null == key)
			return new Integer(0);
		Object value = map.get(key);
		return null == value ? new Integer(0) : value;
	}

	/**
	 * 將key=value;key2=value2……轉換成Map.
	 * 
	 * @param params the params
	 * 
	 * @return the map
	 */
	public static Map gerneryParams(String params) {
		Map args = new HashMap();
		if (!isEmpty(params)) {
			try {
				String map, key, value;
				StringTokenizer st = new StringTokenizer(params, ";");
				StringTokenizer stMap;
				while (st.hasMoreTokens()) {
					map = st.nextToken();
					if (isEmpty(map.trim()))
						break;
					stMap = new StringTokenizer(map, "=");
					key = stMap.hasMoreTokens() ? stMap.nextToken() : "";
					if (isEmpty(key.trim()))
						continue;
					value = stMap.hasMoreTokens() ? stMap.nextToken() : "";
					args.put(key, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return args;
	}

	/**
	 * 字符串加密.
	 * 
	 * @param src the src
	 * 
	 * @return the string
	 */
	public static String encrypt(String src) {
		try {
			return DESPlus.getInstance().encrypt(src);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return src;
	}

	/**
	 * 字符串解密.
	 * 
	 * @param src the src
	 * 
	 * @return the string
	 */
	public static String decrypt(String src) {
		try {
			return DESPlus.getInstance().decrypt(src);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return src;
	}

	/**
	 * 页面格式化日期:yyyyMMdd ---> yyyy-MM-dd.
	 * 
	 * @param date the date
	 * 
	 * @return the string
	 */
	public static String formatDate(String date) {
		return isEmpty(date) ? "" : DateUtil.format(date, "yyyyMMdd",
				"yyyy-MM-dd");
	}

	/**
	 * 获取主键.
	 * 
	 * @return the string
	 */
	public static String uuid32len() {
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 将字符数值取scale为小数.
	 * 
	 * @param v the v
	 * @param scale the scale
	 * 
	 * @return the string
	 */
	public static String round(String v, int scale) {
		if ((v == null) || (v.equals("")))
			return "";
		try {
			BigDecimal b = new BigDecimal(v);
			BigDecimal one = new BigDecimal("1");
			return b.divide(one, scale, 4).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 将字符数值取scale为小数.
	 * 
	 * @param value the value
	 * @param XT the xT
	 * @param SJLX the sJLX
	 * 
	 * @return the value
	 */
	public static String getValue(String value, String XT, String SJLX) {
		try {
			if (value == null) {
				return "";
			}
			return StringUtil.round(value, 2);
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * Tran code p.
	 * 
	 * @param value the value
	 * 
	 * @return the string
	 */
	public static String tranCodeP(String value) {
		String tempStr = "";
		if (value != null) {
			if (System.getProperties().toString().indexOf("tomcat") != -1) {
				try {
					// tempStr = new String(value.getBytes("iso-8859-1"),
					// "GBK");
					tempStr = (null == value ? "" : value);
				} catch (Exception ex)// UnsupportedEncodingException ex
				{
				}
			} else {
				tempStr = value;
			}
		}
		return tempStr.trim();
	}

	/**
	 * 字符串替换.
	 * 
	 * @param strSource the str source
	 * @param oldStr the old str
	 * @param newStr the new str
	 * 
	 * @return the string
	 */
	public static String replace(String strSource, String oldStr, String newStr) {
		//String strDest = "";
		int intFromLen = oldStr.length();
		int intPos;
		StringBuffer strDest=new StringBuffer();
		while ((intPos = strSource.indexOf(oldStr)) != -1) {
			strDest.append(strSource.substring(0, intPos));
			strDest.append(newStr);
			strSource = strSource.substring(intPos + intFromLen);
		}
		strDest.append(strSource) ;
		return strDest.toString();
	}
	
	/**
	 * utf8转码
	 * Description :.
	 * 
	 * @param str the str
	 * 
	 * @return the string
	 */
	public static String utf8Decoder(String str) {
		try {
			if(str!=null){
				return URLDecoder.decode(str, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * Description :通过实例和字段名称调用该字段的get方法.
	 * 
	 * @param owner the owner
	 * @param fieldName the field name
	 * 
	 * @return 该字段对应的值
	 * 
	 * @throws ServiceException the service exception
	 */
	public static String getProperty(Object owner, String fieldName) throws ServiceException{
		if (StringUtils.isBlank(fieldName)) {
			return "";
		}
		String getMethod4FieldName = "get"
				+ fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		Object result = null;
		try {
			Method method = owner.getClass().getDeclaredMethod(
					getMethod4FieldName);
			result = method.invoke(owner);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return null==result? "" : result.toString().trim();
	}

	/**
	 * 将空串转换成空字符串.
	 * 
	 * @param obj the obj
	 * 
	 * @return the string
	 */
	public static String nullToSring(Object obj){
		if(null == obj || "".equals(obj) || "null".equals(obj)){
			return "";
		}
		return String.valueOf(obj);
	}
	
	public static String filterString(String str)
	{
		//for example
	      String []fileStr={
	    		  "'",
	    		  "$",
	    		  "=",
	    		  "select"
	      };
	      String []replaceStr={
	    		  "",
	    		  "",
	    		  "",
	    		  ""
	      };
	      int len=fileStr.length;
	      for(int i=0;i<len;i++)
	      {
	    	  str=str.replaceAll(fileStr[i], replaceStr[i]);
	      }
		  return str;
	}
	
	/**
	 * 将string 转换为integer
	 * @param str
	 * @return
	 */
	public static Integer getInteger(Object obj){
		Integer i = 0;
		String str = nullToSring(obj);
		try{
			if(isEmpty(str)){
				i = 0;
			}
			i = Integer.valueOf(str);
		}catch(Exception e){
			i = 0;
		}
		return i;
		
	}
	
	/**
	 * 将string 转换为double
	 * @param str
	 * @return
	 */
	public static Double getDouble(Object obj){
		Double d = 0d;
		String str = nullToSring(obj);
		try{
			if(isEmpty(str)){
				d = 0d;
			}
			d = Double.valueOf(str);
		}catch(Exception e){
			d = 0d;
		}
		return d;
		
	}
	
	
	public static String getStrQX(String byname, String strQx){
		if(strQx.trim().indexOf("1<>1")==0||strQx.trim().indexOf("1=1")==0){
			return strQx;
		}else{
			StringBuffer sb = new StringBuffer();
			sb.append(byname).append(".").append(strQx);
			return sb.toString();
		}
	}
	/**
	 * 当前日期 格式 yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentDate(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(now);
	}
	
	/**按字节数截取字符串
	 * @param str
	 * @param count
	 * @return
	 */
	public static String splitStringAtBeginByte(String str, int count) {
		   int reInt = 0;
		   String reStr = "";
		   if (str == null)
		    return "";
		   char[] tempChar = str.toCharArray();
		   for (int kk = 0; (kk < tempChar.length && count > reInt); kk++) {
		   String s1 = String.valueOf(tempChar[kk]);
		    byte[] b = s1.getBytes();
		    reInt += b.length;
		    reStr += tempChar[kk];
		   }
		   return reStr;
	}

	public static String[] deleteByIndex(String[] array, int index) {
		String[] newArray = new String[array.length - 1];
		for (int i = 0; i < newArray.length; i++) {
			   if (i < index) {
				   newArray[i] = array[i];
			   } else {
				   newArray[i] = array[i + 1];
			   }
		}
		return newArray;
	}
	//获得摸个字符出现在字符串中第几次的位置
	/**
	public static int getCharacterPosition(String str,String name,int idx){
		String [] arr=toArr(str,name); 
		int returnInt=0;
		if(arr.length>=idx-1)
		{
			for(int i=0;i<idx-1;i++)
				returnInt=returnInt+arr[i].length();
		}
		return returnInt;
	 }
	 **/
	public static int getCharacterPosition(String string,String name,int idx){
	    //这里是获取"/"符号的位置
	    Matcher slashMatcher = Pattern.compile(name).matcher(string);
	    int mIdx = 0;
	    int reIdx=-1;
	    while(slashMatcher.find()) {
	       mIdx++;
	       //当"/"符号第三次出现的位置
	       if(mIdx == idx){
	    	  reIdx= slashMatcher.start();
	          break;
	       }
	    }

    	return reIdx;
	 }
	
	
	
	/**
	 * 拼接like  sql
	 * @param dbCol 字段名
	 * @param valus 
	 * @param sign
	 * @return
	 */
	public static String creCon(String dbCol , String valus, String sign){
    	StringBuffer sqlTemp = new StringBuffer();
    	String[] array = valus.split(sign);
    	String[] dbCols = dbCol.split(",");
    	for(int j=0;j<dbCols.length;j++){
    		for(int i=0;i<array.length;i++){
        		String param = array[i];
        		if(!StringUtil.isEmpty(StringUtil.nullToSring(param))){
        			sqlTemp.append(dbCols[j]);
            		sqlTemp.append(" like '%"+param+"%'");
            		sqlTemp.append(" or ");
        		}
        		 
        	}
    	}
    	
        String  sql = sqlTemp.toString();
        if(sql.length()>0){
        	 int index = sql.lastIndexOf("or");
        	 if(-1 != index){
        		 sql = " and ("+sql.substring(0,index)+") ";
        	 }
        }else{ 
        	return "";
        }
        
    	return sql;
    }
	
	/**
	 * 拼接=  sql
	 * @param dbCol 字段名
	 * @param valus 
	 * @param sign dbCol的分隔符
	 * @return
	 */
	public static String creEqualCon(String dbCol , String valus, String sign){
    	StringBuffer sqlTemp = new StringBuffer();
    	String[] array = valus.split(",");
    	String[] dbCols = dbCol.split(sign);
    	for(int j=0;j<dbCols.length;j++){
    		for(int i=0;i<array.length;i++){
        		String param = array[i];
        		if(!StringUtil.isEmpty(StringUtil.nullToSring(param))){
        			sqlTemp.append(dbCols[j]);
            		sqlTemp.append(" = '"+param+"'");
            		sqlTemp.append(" or ");
        		}
        		 
        	}
    	}
    	
        String  sql = sqlTemp.toString();
        if(sql.length()>0){
        	 int index = sql.lastIndexOf("or");
        	 if(-1 != index){
        		 sql = " and ("+sql.substring(0,index)+") ";
        	 }
        }else{ 
        	return "";
        }
        
    	return sql;
    }
	
	/**
	 * 转换大写
	 * @param obj
	 * @return
	 */
	public static String toUpperCase(Object obj){
		if(null == obj){
			return null;
		}
		return obj.toString().toUpperCase();
	}
	
	/**
	 * 转换小写
	 * @param obj
	 * @return
	 */
	public static String toLowerCase(Object obj){
		if(null == obj){
			return null;
		}
		return obj.toString().toLowerCase();
	}
	
	public static boolean isContain(String[] strs,String str){
		for(int i=0;i<strs.length;i++){
			if(strs[i].indexOf(str)!=-1){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 月份转季度
	 * @param month
	 * @return
	 */
	public static String getQuarterFromMonth(String month){
		String quarter = "";
		int m = getInteger(month);
		if(1 == m || 2 == m || 3 == m){
			quarter = "一季度";
		}

		if(5 == m || 6 == m || 4 == m){
			quarter = "二季度";
		}

		if(8 == m || 9 == m || 7 == m){
			quarter = "三季度";
		}
		if(11 == m || 12 == m || 10 == m){
			quarter = "四季度";
		}
		return quarter;
	}
	
	/**
	 * 季度转换
	 * 一季度->1   二季度->2
	 * @param month
	 * @return
	 */
	public static int getStrQuarterToInt(String quarter){
		int q = 1;
		if("一季度".equals(quarter)){
			 q = 1;
		}
		if("二季度".equals(quarter)){
			 q = 2;
		}
		if("三季度".equals(quarter)){
			 q = 3;
		}
		if("四季度".equals(quarter)){
			 q = 4;
		}
		return q;
	}
	
	
	//add by zhuxw 2015/02/10
	public static String joinConIn(String str, String splitChar) {
		String[] strs = toArr(nullToSring(str),splitChar);
		StringBuffer rst = new StringBuffer();
		int len=strs.length;
		for (int i = 0; i < len; i++) {
			rst.append("'");
			rst.append(nullToSring(strs[i]));
			rst.append("'");
			if(i!=len-1)
			rst.append(",");
		}
		if(len==0)
		{
			rst.append("'1<>1'");
		}
			
		return rst.toString();
	}
	
	/**
	 * 正则表达式验证
	 * @param str 字符串
	 * @param checkStr 正则表达式
	 * @return
	 */
	//验证8位正整数和2位小数    String checkStr="[0-9]+\\.{0,1}[0-9]{0,2}";
	//验证日期格式YYYY-MM-DD String checkDateStr="[0-9]{4}-[0-9]{2}-[0-9]{2}";
	public static boolean regexCheck(String str,String checkStr){ 
		   Pattern pattern = Pattern.compile(checkStr); 
		   Matcher flag = pattern.matcher(str);
		   if( !flag.matches() ){
		       return false; 
		   } 
		   return true; 
	}
}
