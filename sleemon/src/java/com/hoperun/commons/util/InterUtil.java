package com.hoperun.commons.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.InterReturnMsg;
import com.hoperun.commons.service.BaseService;

public class InterUtil {
	/**
	 * 将null转化为""防止数据库插入 null
	 * 
	 * @param str
	 * @return
	 */
	public static String paseNullString(String str) {
		if (null == str || "null".equals(str)) {
			return "";
		}
		return str;
	}
	
	/**
	 * 替换sql的最后一个 逗号
	 * 
	 * @param sqlBuf
	 * @return
	 */
	public static StringBuffer replaceUpSql(StringBuffer sqlBuf) {
		String sql = sqlBuf.toString().trim();
		int length = sql.length() - 1;
		int index = sql.lastIndexOf(",");
		if (index == length) {
			sql = sql.substring(0, length);
		}
		return new StringBuffer(sql);
	}
	/**
	 * 检查必填字段是否有空
	 * @param strFld
	 * @param bodyMap
	 * @param msg
	 * @return
	 */
	public static boolean checkMustFld(String[] strFld, Map bodyMap,
			InterReturnMsg msg) {
		StringBuffer warin = new StringBuffer();
		for (int i = 0; i < strFld.length; i++) {
			String fldName = strFld[i];
			String fldValue = StringUtil.nullToSring(bodyMap.get(fldName));
			if (StringUtil.isEmpty(fldValue)) {
				warin.append("[" + fldName + "]");
			}
		}
		if (warin.toString().length() > 0) {
			warin.append("必填字段为空!");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(warin.toString());
			return false;
		}
		return true;
	}
	
	/**
	 * 检查必填字段是否有空(list)
	 * @param strFld
	 * @param bodyMap
	 * @param msg
	 * @return
	 */
	public static boolean checkMustFld(String[] strFld, Object[] object) throws Exception{
		try {
			for (int i = 0; i < object.length; i++) {
				Object obj=object[i];
				for (int j = 0; j < strFld.length; j++) {
					Method method=obj.getClass().getMethod("get"+strFld[j]);
					String value=String.valueOf(method.invoke(obj));
					if(StringUtil.isEmpty(value)){
						return false;
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 检查必填字段是否有空(model)
	 * @param strFld
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static boolean checkMustFMd(String[] strFld, Object object) throws Exception{
		try {
			for (int j = 0; j < strFld.length; j++) {
				Method method=object.getClass().getMethod("get"+strFld[j]);
				String value=String.valueOf(method.invoke(object));
				if(StringUtil.isEmpty(value)){
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * 单条数据查询
	 */
	public static Map selcom(Map params) {
		BaseService baseService = (BaseService) SpringContextUtil.getBean("baseService");
		return (Map) baseService.load("sqlcom.query", params);
	}

	/**
	 * 多条数据查询
	 * 
	 * @param params
	 * @return
	 */
	public static List selcomList(Map params) {
		BaseService baseService = (BaseService) SpringContextUtil.getBean("baseService");
		return baseService.findList("sqlcom.query", params);
	}
	
	/**
	 * 修改
	 * @param params
	 * @return
	 */
	public static boolean txUpdcom(Map params) {
		BaseService baseService = (BaseService) SpringContextUtil.getBean("baseService");
		return baseService.update("sqlcom.update", params);
	}
	
	/**
	 * 新增
	 * @param params
	 * @return
	 */
	public static boolean txInscom(Map params) {
		BaseService baseService = (BaseService) SpringContextUtil.getBean("baseService");
		baseService.insert("sqlcom.insert", params);
		return true;
	}
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public static boolean txDelcom(Map params) {
		BaseService baseService = (BaseService) SpringContextUtil.getBean("baseService");
		return baseService.update("sqlcom.delete", params);
	}
	
	/**
	 * U9状态信息转换
	 * @param strState
	 * @return
	 */
	public static String coveState(String strState) throws Exception {
		if (null == strState || "".equals(strState)) {
			strState = "-1";
		}
		int state = Integer.parseInt(strState);
		switch (state) {
		case 0:
			return "制定";
		case 1:
			return "启用";
		case 2:
			return "停用";
		default:
			return "启用";
		}
	}
	
	/**
	 * 检查是否有重复记录
	 * 
	 * @param mainKey
	 * @return
	 * @throws Exception
	 */
	public static boolean checkPrimarykey(String keyName, String keyValue,String tableName) throws Exception {
		StringBuffer payMentBuf = new StringBuffer();
		payMentBuf.append("SELECT ").append(keyName).append(" FROM ").append(
				tableName).append(" WHERE DEL_FLAG = 0 and ").append(keyName).append(" = '")
				.append(keyValue).append("'");
		Map params = new HashMap();
		params.put("SelSQL", payMentBuf.toString());
		System.out.println("checkPrimarykey==============="+ payMentBuf.toString());
		Map map = selcom(params);
		if (map != null && map.size() > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 检查是否有重复记录(老数据 删除标记为DELFLAG)
	 * 
	 * @param mainKey
	 * @return
	 * @throws Exception
	 */
	public static boolean checkPrimarykeyOld(String keyName, String keyValue,String tableName) throws Exception {
		StringBuffer payMentBuf = new StringBuffer();
		payMentBuf.append("SELECT ").append(keyName).append(" FROM ").append(
				tableName).append(" WHERE DELFLAG=0 and ").append(keyName).append(" = '").append(keyValue).append("'");
		Map params = new HashMap();
		params.put("SelSQL", payMentBuf.toString());
		System.out.println("checkPrimarykeyOld============="+payMentBuf.toString());
		Map map = selcom(params);
		if (map != null && map.size() > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 密码错误提示信息
	 * @return
	 */
	public static InterReturnMsg returnCheckUserFail(){
		InterReturnMsg msg = new InterReturnMsg();
		msg.setFLAG(BusinessConsts.FLASE);
		msg.setMESSAGE("用户名密码不正确!");
		return msg;
	}
	
	/**
	 * 提取错误信息
	 * @param ex
	 * @return
	 */
	public static String getErrorInfo(Exception ex){ 
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String errorInfo = ex.toString();
		if(errorInfo.length()>2900){
			errorInfo = errorInfo.substring(0, 2900);
		}
		return sw.toString();
	}
	
	/**
	 * 传参查询所需要的值
	 * @param param 条件  键为条件，值为条件查询值
	 * @param result 所需要查询的字段
	 * @param tableName  表名
	 * @return
	 */
	public static List<Map<String,String>> getSqlResult(Map<String,String> params,List<String> result,String tableName){
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		if(!result.isEmpty()){
			for (int i = 0; i < result.size(); i++) {
				sql.append(result.get(i)).append(",");
			}
			sql=replaceUpSql(sql);
		}
		sql.append(" from ").append(tableName).append(" where 1=1 ");
		if(!params.isEmpty()){
			 for (Map.Entry<String, String> param : params.entrySet()) {
				 sql.append(" and ").append(param.getKey()).append(" = '").append(param.getValue()).append("'");
			  }
		}
		Map<String,String> selSql=new HashMap<String, String>();
		selSql.put("SelSQL", sql.toString());
		System.err.println("checkSql=============================="+sql.toString());
		return selcomList(selSql);
	}
	/**
	 * 传参查询所需要的值
	 * @param param 条件  键为条件，值为条件查询值
	 * @param result 所需要查询的字段
	 * @param tableName  表名
	 * @param sqlStr 追加sql
	 * @return
	 */
	public static List<Map<String,String>> getSqlResult(Map<String,String> params,List<String> result,String tableName,String sqlStr){
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		if(!result.isEmpty()){
			for (int i = 0; i < result.size(); i++) {
				sql.append(result.get(i)).append(",");
			}
			sql=replaceUpSql(sql);
		}
		sql.append(" from ").append(tableName).append(" where 1=1 ");
		if(!params.isEmpty()){
			 for (Map.Entry<String, String> param : params.entrySet()) {
				 sql.append(" and ").append(param.getKey()).append(" = '").append(param.getValue()).append("'");
			  }
		}
		if(!StringUtil.isEmpty(sqlStr)){
			sql.append(sqlStr);
		}
		Map<String,String> selSql=new HashMap<String, String>();
		selSql.put("SelSQL", sql.toString());
		System.err.println("checkSql=============================="+sql.toString());
		return selcomList(selSql);
	}
	/**
	 * 接口传输，所有数据来自MDM
	 * @param type  add 新增，upd 修改
	 * @return
	 */
	public static Map<String, String> getCreInfo(String type){
		Map<String, String> map=new HashMap<String, String>();
		if("add".equals(type)){
			map.put("CREATOR", "MDM");//制单人
			map.put("CRE_NAME", "MDM");//制单人名称
			map.put("CRE_TIME", "sysdate");//制单时间
			map.put("DEPT_ID", "MDM");//制单部门
			map.put("DEPT_NAME", "MDM");//制单部门名称
			map.put("ORG_ID", "MDM");//制单机构
			map.put("ORG_NAME", "MDM");//制单机构名称
			map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//删除标记
		}else if ("upd".equals(type)) {
			map.put("UPDATOR", "MDM");//修改人
			map.put("UPD_NAME", "MDM");//修改人名称
			map.put("UPD_TIME", "sysdate");//修改时间
		}
		return map;
	}
	
	/**
	 * 清空查询需要的对象
	 * @param params
	 * @param result
	 * @param resultList
	 */
	public static void clearObj(Map<String,String> params,List<String> result,List<Map<String,String>> resultList){
		params.clear();
		result.clear();
		resultList.clear();
	}
	/**
	 * 验证数据字典是否存在,如果不存在，则新增一条
	 * @param value
	 * @return
	 */
	public static void checkSJZDInfo(String SJXZ,String BASE_CODE,String SJZDBH){
		BaseService baseService = (BaseService) SpringContextUtil.getBean("baseService");
		Map<String,String> params=new HashMap<String, String>();
		params.put("SJZDBH", SJZDBH);
		params.put("SJXZ", SJXZ);
		List<Map<String,String>> list=baseService.findList("SJZD.checkSJZDMXBySJXZ", params);
		//根据数据字典编号，值，查询是否有值，如果有值，则不操作
		if(list.isEmpty()){
			//如果没有值，则根据编码ID查询是否有值
			if(!StringUtil.isEmpty(BASE_CODE)){
				params.put("BASE_CODE", BASE_CODE);
			}
			list=baseService.findList("SJZD.checkSJZDMXById", params);
			//如果有值，说明修改，如果没有值，则是新增（值存在，code不存在，则是原数据，值不存在，code存在，则是修改)
			if(!list.isEmpty()){
				updateSJZDMXByService(SJZDBH, SJXZ, BASE_CODE);
			}else{
				insertSJZDMXByService(SJZDBH, SJXZ, BASE_CODE);
			}
		}
	}
	/**
	 * 新增数据字典明细
	 * @param SJZDBH 数据字典编号
	 * @param SJXMC 数据字典值
	 * @return
	 */
	public static boolean insertSJZDMXByService(String SJZDBH,String SJXZ,String BASE_CODE){
		Map<String,String> map=new HashMap<String, String>();
		map.put("SJZDBH", SJZDBH);
		map.put("SJXZ", SJXZ);
		map.put("BASE_CODE", BASE_CODE);
		map.put("SJZDMXID", StringUtil.uuid32len());
		map.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
		map.put("STATE", BusinessConsts.JC_STATE_ENABLE);
		map.put("CREATER", "MDM");
		BaseService baseService = (BaseService) SpringContextUtil.getBean("baseService");
		baseService.insert("SJZD.insertSJZDMXByService", map);
		
		return true;
	}
	/**
	 * 修改数据字典明细
	 * @param SJZDBH 数据字典编号
	 * @param SJXMC 数据字典值
	 * @return
	 */
	public static boolean updateSJZDMXByService(String SJZDBH,String SJXZ,String BASE_CODE){
		Map<String,String> map=new HashMap<String, String>();
		map.put("SJZDBH", SJZDBH);
		map.put("SJXZ", SJXZ);
		map.put("BASE_CODE", BASE_CODE);
		map.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
		map.put("STATE", BusinessConsts.JC_STATE_ENABLE);
		BaseService baseService = (BaseService) SpringContextUtil.getBean("baseService");
		baseService.insert("SJZD.updateSJZDMXByService", map);
		return true;
	}
}
