/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import com.hoperun.commons.model.Properties;

/**
 * The Class DateUtil.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class DateUtil {

	/** The Constant DATE_FORMATTER. */
	public static final String DATE_FORMATTER = Properties
			.getString("DATE_FORMATTER");

	/**
	 * Parses the date.
	 * 
	 * @param dateStr the date str
	 * @param format the format
	 * 
	 * @return the java.util. date
	 */
	public static java.util.Date parseDate(String dateStr, String format) {
		if (StringUtil.isEmpty(dateStr))
			return null;
		java.util.Date date = null;
		try {
			java.text.DateFormat df = new java.text.SimpleDateFormat(format);
			String dt = dateStr;// .replaceAll("-", "/");
			if ((!dt.equals("")) && (dt.length() < format.length())) {
				dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
						"0");
			}
			date = (java.util.Date) df.parse(dt);
		} catch (Exception e) {
		}
		return date;
	}

	/**
	 * Parses the date.
	 * 
	 * @param dateStr the date str
	 * 
	 * @return the java.util. date
	 */
	public static java.util.Date parseDate(String dateStr) {
		return parseDate(dateStr, DATE_FORMATTER);
	}

	/**
	 * Today str.
	 * 
	 * @return the string
	 */
	public static String todayStr() {
		return formatDateToStr(new Date(), DATE_FORMATTER);
	}

	/**
	 * Today.
	 * 
	 * @return the date
	 */
	public static Date today() {
		return parseDate(todayStr(), DATE_FORMATTER);
	}

	/**
	 * Format date to str.
	 * 
	 * @param date 需要格式化的日期對像
	 * @param formatter 格式化的字符串形式
	 * 
	 * @return 按照formatter指定的格式的日期字符串
	 * 
	 * @throws ParseException 無法解析的字符串格式時拋出
	 */
	public static String formatDateToStr(Date date, String formatter) {
		if (date == null)
			return "";
		try {
			return new java.text.SimpleDateFormat(formatter).format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 生成默认格式的日期.
	 * 
	 * @param date the date
	 * 
	 * @return the string
	 */
	public static String formatDateToStr(Date date) {
		return formatDateToStr(date, DATE_FORMATTER);
	}

	/**
	 * 將日期按照指定的模式格式化.
	 * 
	 * @param date {@link Date}
	 * @param format 如：yyyy/MM/dd
	 * 
	 * @return 返回空表示格式化產生異常
	 */
	public static String format(java.util.Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 将一种字符日期转化成另外一种日期格式.
	 * 
	 * @param date the date
	 * @param fmtSrc the fmt src
	 * @param fmtTag the fmt tag
	 * 
	 * @return the string
	 */
	public static String format(String date, String fmtSrc, String fmtTag) {
		if (null == fmtSrc)
			return null;
		if (fmtSrc.equals(fmtTag)) {
			return date;
		}
		String year, month, daty;
		int Y, M, D;
		fmtSrc = fmtSrc.toUpperCase();
		Y = fmtSrc.indexOf("YYYY");
		M = fmtSrc.indexOf("MM");
		D = fmtSrc.indexOf("DD");
		if (Y < 0 || M < 0 || D < 0) {
			return date;
		}
		year = date.substring(Y, Y + 4);
		month = date.substring(M, M + 2);
		daty = date.substring(D, D + 2);
		fmtTag = fmtTag.toUpperCase();
		fmtTag = fmtTag.replaceAll("YYYY", year);
		fmtTag = fmtTag.replaceAll("MM", month);
		fmtTag = fmtTag.replaceAll("DD", daty);
		return fmtTag;
	}

	/**
	 * 計算指定年月的日期數.
	 * 
	 * @param year the year
	 * @param month the month
	 * 
	 * @return the int
	 */
	public static int maxDayOfMonth(int year, int month) {
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			boolean isRunYear = (year % 400 == 0)
					|| (year % 4 == 0 && year % 100 != 0);
			return isRunYear ? 29 : 28;
		default:
			return 31;
		}
	}

	/**
	 * 获取指定年月的日期數.
	 * 
	 * @param year the year
	 * @param month the month
	 * 
	 * @return the int
	 */
	public static int maxDayOfMonth(String year, String month) {
		return maxDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
	}

	/**
	 * 获取指定年月上月月末日期.
	 * 
	 * @param year the year
	 * @param month the month
	 * 
	 * @return the last month date
	 */
	public static String getLastMonthDate(String year, String month) {
		return getLastMonthDate(Integer.parseInt(year), Integer.parseInt(month));
	}

	/**
	 * 获取指定年月上月月末日期.
	 * 
	 * @param year the year
	 * @param month the month
	 * 
	 * @return the last month date
	 */
	public static String getLastMonthDate(int year, int month) {
		if (month <= 1) {
			year -= 1;
			month = 12;
		} else {
			month -= 1;
		}
		StringBuffer bfDate = new StringBuffer();
		bfDate.append(year);
		if (month < 10)
			bfDate.append("0");
		bfDate.append(month);
		bfDate.append(maxDayOfMonth(year, month));
		return bfDate.toString();
	}

	/**
	 * 提前N天的日期.
	 * 
	 * @param date the date
	 * @param days the days
	 * 
	 * @return the date
	 */
	public static Date beforeDate(Date date, int days) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.add(java.util.Calendar.DAY_OF_YEAR, -days);
		return c.getTime();

	}

	/**
	 * 一周前的日期.
	 * 
	 * @return the last week
	 */
	public static Date getLastWeek() {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.add(java.util.Calendar.DAY_OF_YEAR, -7);
		return c.getTime();

	}
	
    /**
     * Gets the date.
     * 
     * @return the date
     */
    public static Date getDate(){
    	Calendar now = Calendar.getInstance();   		
    	return now.getTime();    
    } 
    
    /**
     * Gets the date field for sql.
     * 
     * @param fieldName the field name
     * @param dbType the db type
     * 
     * @return the date field for sql
     */
    public static String getDateFieldForSql(String fieldName,String dbType) {
		String returnvalue = "";
		if (dbType.equalsIgnoreCase("DB2")) {
			returnvalue = "date(" + fieldName + ")";
		}
		if (dbType.equalsIgnoreCase("ORACLE")) {
			returnvalue =
				"to_date(to_char("
				+ fieldName
				+ ", 'yyyy-mm-dd'),'yyyy-mm-dd')";
		}
		return returnvalue;
	}

	/**
	 * Gets the date value for sql.
	 * 
	 * @param fieldValue the field value
	 * @param dbType the db type
	 * 
	 * @return the date value for sql
	 */
	public static String getDateValueForSql(String fieldValue,String dbType) {
		String returnvalue = "";
		if (dbType.equalsIgnoreCase("DB2")) {
			returnvalue = "date('" + fieldValue + "')";
		}
		if (dbType.equalsIgnoreCase("ORACLE")) {
			returnvalue = "to_date('" + fieldValue + "','yyyy-mm-dd')";
		}
		return returnvalue;
	}
	
	/**
	 * Now.
	 * 
	 * @return the string
	 */
	public static String newDataTime(){
		return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS" );
	}
	
	/**
	 * Now.
	 * 
	 * @return the string
	 */
	public static String now(){
		return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss" );
	}
	
	
	/**
	 * Nfyf.
	 * 
	 * @return the string
	 */
	public static String nfyf(){
		return DateFormatUtils.format(new Date(), "yyyyMM");
	}
	/**
	 * 获取日期的上一个月(月份减1)
	 * @param d
	 * @return
	 */
	public static String getLastDate(Date d){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
	    c.add(Calendar.MONTH, -1);
		return format( c.getTime(),"yyyy-MM-dd");
	}
	
	/**
	 * 获取日期的上一个月初1号(月份减1)
	 * @param d
	 * @return
	 */
	public static String getLastDateBegin(Date d){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
	    c.add(Calendar.MONTH, -1);
	    c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		return format( c.getTime(),"yyyy-MM-dd");
	}
	
	
	/**
	 * 获取日期的年初初 1月1号(月份减1)
	 * @param d
	 * @return
	 */
	public static String getDateYearBegin(Date d){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
	    c.set(c.get(Calendar.YEAR), 0, 1);
		return format( c.getTime(),"yyyy-MM-dd");
	}
	

}
