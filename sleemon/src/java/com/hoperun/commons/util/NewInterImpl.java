package com.hoperun.commons.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.inter.AdvcOrderDtlForWeChatModel;
import com.hoperun.commons.model.inter.AdvcOrderForWeChatModel;
import com.hoperun.commons.model.inter.AdvcOrderPaymentForWeChatModel;
import com.hoperun.commons.model.inter.InterBranchModel;
import com.hoperun.commons.model.inter.InterBrandModel;
import com.hoperun.commons.model.inter.InterChannModel;
import com.hoperun.commons.model.inter.InterEmployeeModel;
import com.hoperun.commons.model.inter.InterProductModel;
import com.hoperun.commons.model.inter.InterUserModel;
import com.hoperun.commons.model.inter.ReturnInterInfo;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.security.MD5Encrypt;
import com.hoperun.drp.sale.advcorder.service.AdvcorderService;
import com.hoperun.sys.model.UserBean;

public class NewInterImpl extends BaseService {
	/** 日志 **/
	private Logger logger = Logger.getLogger(NewInterImpl.class);
	/**
	 * MDM接口
	 */
	
	/**
	 * 新增品牌信息
	 * @param BRAND_LIST
	 * @return ReturnInterInfo
	 * @throws Exception
	 */
	public ReturnInterInfo txInsNewBrand(InterBrandModel[] BRAND_LIST) {
		ReturnInterInfo msg = new ReturnInterInfo();
		if (null==BRAND_LIST||BRAND_LIST.length==0) {
			LogicUtil.actLog("品牌信息管理", "新增品牌信息(新)", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
		LogicUtil.actLog("品牌信息管理", "新增品牌信息(新)==调入成功==", "MDM", "成功", new Gson().toJson(BRAND_LIST),"","","");
//		String[] mustFld = { "BRAND_CODE", "USE_STATUS", "BRAND_NAME" };
		String[] mustFld = { "BRAND_CODE"};
		try {
			if (!InterUtil.checkMustFld(mustFld, BRAND_LIST)) {
				LogicUtil.actLog("品牌信息管理", "新增品牌信息(新)", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			List<Map<String, String>> addList=new ArrayList<Map<String, String>>();
			for (int i = 0; i < BRAND_LIST.length; i++) {
				//品牌编号
				InterBrandModel model=BRAND_LIST[i];
				String BRAND_CODE = InterUtil.paseNullString(model.getBRAND_CODE());
				String BRAND=model.getBRAND_NAME();
				if (!InterUtil.checkPrimarykey("BRAND_ID", BRAND_CODE, "BASE_BRAND")) {
					LogicUtil.actLog("品牌信息管理", "新增品牌信息(新)", "MDM", "失败",
							BusinessConsts.KEY_REPEAT, "", "", "");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("品牌"+BRAND_CODE+"已存在!");
					return msg;
				}
				Map<String, String> map=new HashMap<String, String>();
				map.put("BRAND_ID", BRAND_CODE);//品牌ID
				map.put("BRAND", model.getBRAND_NAME());//品牌名
				map.put("BRAND_EN", model.getENGLISH_NAME());//英文名
				map.put("STATE", InterUtil.coveState(model.getUSE_STATUS()));//状态
				map.putAll(InterUtil.getCreInfo("add"));
				addList.add(map);
			}
			boolean flag=this.batch4Update("BRAND.insert", addList);
			if(!flag){
				LogicUtil.actLog("品牌信息管理", "新增品牌信息(新)", "MDM", "SQL执行失败",
						BusinessConsts.IMPL_SQL_EXECUTE_FLASE, "", "",	"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText("新增品牌信息保存失败!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增品牌信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("品牌信息管理", "新增品牌信息(新)", "MDM", "失败", errorInfo,"","","");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("新增品牌信息出错!");
			return msg;
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("品牌信息管理", "新增品牌信息(新)", "MDM", "成功", new Gson().toJson(BRAND_LIST),"","","");
		return msg;
	}
	
	
	/**
	 * 修改品牌信息
	 * @param BRAND_LIST
	 * @return ReturnInterInfo
	 * @throws Exception
	 */
	public ReturnInterInfo txUpNewBrand(InterBrandModel[] BRAND_LIST) {
		ReturnInterInfo msg = new ReturnInterInfo();
		if (null==BRAND_LIST||BRAND_LIST.length==0) {
			LogicUtil.actLog("品牌信息管理", "修改品牌信息(新)", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
		LogicUtil.actLog("品牌信息管理", "修改品牌信息(新)==调入成功==", "MDM", "成功", new Gson().toJson(BRAND_LIST),"","","");
//		String[] mustFld = { "BRAND_CODE", "USE_STATUS", "BRAND_NAME" };
		String[] mustFld = { "BRAND_CODE"};
		try {
			if (!InterUtil.checkMustFld(mustFld, BRAND_LIST)) {
				LogicUtil.actLog("品牌信息管理", "修改品牌信息(新)", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			List<Map<String, String>> upList=new ArrayList<Map<String, String>>();
			for (int i = 0; i < BRAND_LIST.length; i++) {
				//品牌编号
				InterBrandModel model=BRAND_LIST[i];
				String BRAND_CODE = InterUtil.paseNullString(model.getBRAND_CODE());
				String BRAND=model.getBRAND_NAME();
				if (InterUtil.checkPrimarykey("BRAND_ID", BRAND_CODE, "BASE_BRAND")) {
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("品牌:"+BRAND_CODE+"不存在!");
					return msg;
				}
				Map<String, String> map=new HashMap<String, String>();
				map.put("BRAND_ID", BRAND_CODE);//品牌ID
				map.put("BRAND", model.getBRAND_NAME());//品牌名
				map.put("BRAND_EN", model.getENGLISH_NAME());//英文名
				map.put("STATE", InterUtil.coveState(model.getUSE_STATUS()));//状态
				map.putAll(InterUtil.getCreInfo("upd"));
				upList.add(map);
			}
			boolean flag=this.batch4Update("BRAND.updateById", upList);
			if(!flag){
				LogicUtil.actLog("品牌信息管理", "修改品牌信息(新)", "MDM", "SQL执行失败",
						BusinessConsts.IMPL_SQL_EXECUTE_FLASE, "", "",	"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText("修改品牌信息保存失败!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增品牌信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("品牌信息管理", "新增品牌信息(新)", "MDM", "失败", errorInfo,"","","");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("修改品牌信息出错!");
			return msg;
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("品牌信息管理", "修改品牌信息(新)", "MDM", "成功", new Gson().toJson(BRAND_LIST),"","","");
		return msg;
	}
	 
	
	/**
	 * 新增渠道信息
	 * 
	 * @param CHANN_LIST
	 * @return ReturnInterInfo
	 * @throws Exception
	 */
	public ReturnInterInfo txInsNewChann(InterChannModel[] CHANN_LIST) {
		ReturnInterInfo msg = new ReturnInterInfo();
		if (null==CHANN_LIST||CHANN_LIST.length==0) {
			LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
		LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)==调入成功==", "MDM", "成功", new Gson().toJson(CHANN_LIST),"","","");
//		String[] mustFld = { "CUST_NAME","CUST_CODE","CUST_NAME_SIMPLE","ENTER_TIME","ORG_CODE","CUST_TAX_ID","COUNTRY","PROVINCE","CITY","COUNTY",
//							"ADDRESS","POSTAL_CODE","CONTACT_PERSON","PHONE","FAX","EMAIL","BANK","BANK_ACCOUNT","CUST_TYPE","CUST_CLASS",
//							"USE_STATUS","SALE_REGION","DEFAULT_ORG","DEFAULT_ORG_NAME" };
		String[] mustFld = {"CUST_CODE","CUST_NAME","CUST_NAME_SIMPLE","ENTER_TIME","ORG_CODE","CUST_TAX_ID","CUST_BUSI_LICENSE_ID","LEGAL_PERSON","COUNTRY","PROVINCE_VAL","CITY_VAL","COUNTY_VAL","ADDRESS","POSTAL_CODE",
		"CONTACT_PERSON","PHONE","FAX","EMAIL","BANK","BANK_ACCOUNT","SALE_REGION_VAL","SALE_CHANNEL_VAL","CUST_CLASS_VAL"};
		try {
			if (!InterUtil.checkMustFld(mustFld, CHANN_LIST)) {
				LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			/** 验证对象 **/
			Map<String,String> params=new HashMap<String, String>();//参数
			List<String> result=new ArrayList<String>();//返回值
			List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();//查询结果
			
			List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
			for (int i = 0; i < CHANN_LIST.length; i++) {
				Map<String,String> map=new HashMap<String, String>();
				InterChannModel model=CHANN_LIST[i];
				map.put("CHANN_ID", StringUtil.uuid32len());
				String CHANN_NO=model.getCUST_CODE();
				if (!InterUtil.checkPrimarykey("CHANN_NO", CHANN_NO, "BASE_CHANN")) {
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("渠道编号："+CHANN_NO+"已存在!");
					return msg;
				}
				map.put("CHANN_NO", CHANN_NO);//渠道编号
				map.put("CHANN_NAME", model.getCUST_NAME());//渠道名称
				map.put("CHANN_ABBR", model.getCUST_NAME_SIMPLE());//简称
				
				InterUtil.checkSJZDInfo(model.getCUST_TYPE_VAL(),model.getCUST_TYPE(), "CHANN_BILL_TYPE");
//				if (!InterUtil.checkSJZDInfo(model.getCUST_TYPE_VAL(), "CHANN_BILL_TYPE") ) {
//					LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)", "MDM", "失败",
//							"未找到匹配客户类别:"+model.getCUST_TYPE_VAL(), "", "",	"");
//					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//					msg.setMessageText("未找到匹配客户类别:"+model.getCUST_TYPE_VAL());
//					return msg;
//				}
				
				InterUtil.checkSJZDInfo(model.getSALE_CHANNEL_VAL(),model.getSALE_CHANNEL(), "CHAA_SALE_LVL");
//				if (!InterUtil.checkSJZDInfo(model.getSALE_CHANNEL_VAL(), "CHAA_SALE_LVL")) {
//					LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)", "MDM", "失败",
//							"未找到匹配销售渠道："+model.getSALE_CHANNEL_VAL(), "", "",	"");
//					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//					msg.setMessageText("未找到匹配销售渠道："+model.getSALE_CHANNEL_VAL());
//					return msg;
//				}
				InterUtil.checkSJZDInfo(model.getCUST_CLASS_VAL(),model.getCUST_CLASS(), "CHAA_LVL");
//				if (!InterUtil.checkSJZDInfo(model.getCUST_CLASS_VAL(), "CHAA_LVL")) {
//					LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)", "MDM", "失败",
//							"未找到匹配客户等级", "", "","");
//					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//					msg.setMessageText("未找到匹配客户等级"+model.getCUST_CLASS_VAL());
//					return msg;
//				}
				
				InterUtil.clearObj(params, result, resultList);//清空查询对象
				/** 验证区域信息是否存在，如存在，则返回区域信息*/
				
				params.put("AREA_NAME", model.getSALE_REGION_VAL());//区域名称
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				params.put("rownum", "1");
				result.clear();
				result.add("AREA_ID");
				result.add("AREA_NO");
				result.add("AREA_NAME");
				resultList=InterUtil.getSqlResult(params, result, "BASE_AREA");
				if (resultList.isEmpty()) {
					LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)", "MDM", "失败",
							"未找到匹配销售区域", "", "",	"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("未找到匹配销售区域"+model.getSALE_REGION_VAL());
					return msg;
				}
				map.put("AREA_ID", resultList.get(0).get("AREA_ID"));
				map.put("AREA_NO", resultList.get(0).get("AREA_NO"));
				map.put("AREA_NAME", resultList.get(0).get("AREA_NAME"));
				
				InterUtil.clearObj(params, result, resultList);//清空查询对象

				/** 验证国家省份信息是否在区域信息里存在，如存在，则返回区域信息id*/
				params.put("NATION", model.getCOUNTRY_VAL());//国家
				params.put("PROV", model.getPROVINCE_VAL());//省份
				params.put("CITY", model.getCITY_VAL());//城市
				params.put("COUNTY", model.getCOUNTY_VAL());//区县
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				params.put("rownum", "1");
				result.add("ZONE_ID");
				resultList=InterUtil.getSqlResult(params, result, "BASE_ZONE");
				if(resultList.isEmpty()){
					LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)", "MDM", "失败",
							"未找到匹配国家:"+model.getCOUNTRY_VAL()+"，省份:"+model.getPROVINCE_VAL()+"，城市:"+model.getCITY_VAL()+"，区县:"+model.getCOUNTY_VAL()+"信息", "", "","");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("未找到匹配国家:"+model.getCOUNTRY_VAL()+"，省份:"+model.getPROVINCE_VAL()+"，城市:"+model.getCITY_VAL()+"，区县:"+model.getCOUNTY_VAL()+"信息");
					return msg;
				}
				map.put("ZONE_ID", resultList.get(0).get("ZONE_ID"));//行政区划ID
				map.put("NATION",model.getCOUNTRY_VAL());//国家
				map.put("PROV", model.getPROVINCE_VAL());//省份
				map.put("CITY", model.getCITY_VAL());//城市
				map.put("COUNTY", model.getCOUNTY_VAL());//区县
//				map.put("CITY_TYPE", model.get());//城市类型
				map.put("PERSON_CON", model.getCONTACT_PERSON());//联系人
				map.put("TEL", model.getPHONE());//电话
//				map.put("MOBILE", model.get());//手机
				map.put("TAX", model.getFAX());//传真
				map.put("POST", model.getPOSTAL_CODE());//邮编
				map.put("ADDRESS", model.getADDRESS());//地址
				map.put("EMAIL", model.getEMAIL());//电子邮件
//				map.put("WEB", model.get());//网站
				map.put("LEGAL_REPRE", model.getLEGAL_PERSON());//法人代表
				map.put("BUSS_LIC", model.getCUST_BUSI_LICENSE_ID());//营业执照号
				map.put("AX_BURDEN", model.getCUST_TAX_ID());//税务登记号
				map.put("ORG_CODE_CERT", model.getORG_CODE());//组织机构代码
//				map.put("BUSS_NATRUE", model.get());//经营性质 
//				map.put("BUSS_SCOPE", model.get());//经营范围
//				map.put("VAT_NO", model.get());//增值税号
//				map.put("INVOICE_TI", model.get());//发票抬头
//				map.put("INVOICE_ADDR", model.get());//发票地址
				map.put("BANK_NAME", model.getBANK());//开户银行
				map.put("BANK_ACCT", model.getBANK_ACCOUNT());//银行账号
				map.put("STATE", InterUtil.coveState(model.getUSE_STATUS()));//状态
				map.put("IS_BASE_FLAG", BusinessConsts.DEL_FLAG_COMMON);//是否总部
				map.put("CHANN_TYPE", model.getSALE_CHANNEL_VAL());//销售渠道
				map.put("CHAA_SALE_LVL", model.getCUST_TYPE_VAL());//客户类别 
				map.put("CHAA_LVL", model.getCUST_CLASS_VAL());//客户等级
				InterUtil.clearObj(params, result, resultList);//清空查询对象
				
				params.put("a.SHIP_POINT_NO", model.getDEFAULT_ORG());
				params.put("a.SHIP_POINT_NAME", model.getDEFAULT_ORG_NAME());
				params.put("a.STATE", BusinessConsts.JC_STATE_ENABLE);
				params.put("a.DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
				result.add("a.SHIP_POINT_ID");
				
				resultList=InterUtil.getSqlResult(params, result, " BASE_SHIP_POINT a ");
				if (resultList.isEmpty()) {
					LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)", "MDM", "失败",
							"未找到匹配默认组织信息", "", "",	"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("未找到匹配默认组织信息：编号"+ model.getDEFAULT_ORG()+"，名称"+ model.getDEFAULT_ORG_NAME());
					return msg;
				}
				map.put("SHIP_POINT_ID", resultList.get(0).get("SHIP_POINT_ID"));//生产基地ID
				map.put("SHIP_POINT_NO", model.getDEFAULT_ORG());//生产基地NO
				map.put("SHIP_POINT_NAME",  model.getDEFAULT_ORG_NAME());//生产基地NAME
				map.put("JOIN_DATE", model.getENTER_TIME());//加盟时间
				String checkDateStr="[0-9]{4}-[0-9]{2}-[0-9]{2}";
				if(!StringUtil.regexCheck(model.getENTER_TIME(), checkDateStr)){
					LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)", "MDM", "失败",
							"加盟时间日期格式不正确"+model.getENTER_TIME(), "", "",
							"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("加盟时间日期格式不正确"+model.getENTER_TIME());
					return msg;
				}
				
				map.putAll(InterUtil.getCreInfo("add"));
				addList.add(map);
			}
			boolean flag=this.batch4Update("chann.insert", addList);
			if(!flag){
				LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)", "MDM", "SQL执行失败",
						BusinessConsts.IMPL_SQL_EXECUTE_FLASE, "", "",	"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText("新增客户信息失败!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增渠道信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)", "MDM", "失败", errorInfo,
					"", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("新增客户信息出错!");
			return msg;
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)", "MDM", "成功", new Gson().toJson(CHANN_LIST),"","","");
		return msg;
	}
	
	
	/**
	 * 修改渠道信息
	 * 
	 * @param CHANN_LIST
	 * @return ReturnInterInfo
	 * @throws Exception
	 */
	public ReturnInterInfo txUpNewChann(InterChannModel[] CHANN_LIST) {
		ReturnInterInfo msg = new ReturnInterInfo();
		if (null==CHANN_LIST||CHANN_LIST.length==0) {
			LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
		LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)==调入成功===", "MDM", "成功", new Gson().toJson(CHANN_LIST),"","","");
//		String[] mustFld = { "CUST_NAME","CUST_CODE","CUST_NAME_SIMPLE","ENTER_TIME","ORG_CODE","CUST_TAX_ID","COUNTRY","PROVINCE","CITY","COUNTY",
//		"ADDRESS","POSTAL_CODE","CONTACT_PERSON","PHONE","FAX","EMAIL","BANK","BANK_ACCOUNT","CUST_TYPE","CUST_CLASS",
//		"USE_STATUS","SALE_REGION","DEFAULT_ORG","DEFAULT_ORG_NAME" };
		String[] mustFld = {"CUST_CODE","CUST_NAME","CUST_NAME_SIMPLE","ENTER_TIME","ORG_CODE","CUST_TAX_ID","CUST_BUSI_LICENSE_ID","LEGAL_PERSON","COUNTRY","PROVINCE_VAL","CITY_VAL","COUNTY_VAL","ADDRESS","POSTAL_CODE",
		"CONTACT_PERSON","PHONE","FAX","EMAIL","BANK","BANK_ACCOUNT","SALE_REGION_VAL","SALE_CHANNEL_VAL","CUST_CLASS_VAL"};
		try {
			if (!InterUtil.checkMustFld(mustFld, CHANN_LIST)) {
				LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			/** 验证对象 **/
			Map<String,String> params=new HashMap<String, String>();//参数
			List<String> result=new ArrayList<String>();//返回值
			List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();//查询结果
			
			List<Map<String,String>> updList=new ArrayList<Map<String,String>>();
			for (int i = 0; i < CHANN_LIST.length; i++) {
				Map<String,String> map=new HashMap<String, String>();
				InterChannModel model=CHANN_LIST[i];
				String CHANN_NO=model.getCUST_CODE();
				if (InterUtil.checkPrimarykey("CHANN_NO", CHANN_NO, "BASE_CHANN")) {
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("渠道"+CHANN_NO+"不存在!");
					return msg;
				}
				map.put("CHANN_ID", String.valueOf(load("chann.getChannId", CHANN_NO)));
				map.put("CHANN_NO", CHANN_NO);//渠道编号
				map.put("CHANN_NAME", model.getCUST_NAME());//渠道名称
				map.put("CHANN_ABBR", model.getCUST_NAME_SIMPLE());//简称
				
				if(!StringUtil.isEmpty( model.getCUST_TYPE_VAL())){
					InterUtil.checkSJZDInfo(model.getCUST_TYPE_VAL(),model.getCUST_TYPE(), "CHANN_BILL_TYPE");
//					if (!InterUtil.checkSJZDInfo(model.getCUST_TYPE_VAL(), "CHANN_BILL_TYPE")) {
//						LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
//								"未找到匹配客户类别:"+model.getCUST_TYPE_VAL(), "", "",	"");
//						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//						msg.setMessageText("未找到匹配客户类别:"+model.getCUST_TYPE_VAL());
//						return msg;
//					}
				}
				
				if(!StringUtil.isEmpty(model.getCUST_CLASS_VAL())){
					InterUtil.checkSJZDInfo(model.getCUST_CLASS_VAL(),model.getCUST_CLASS(), "CHAA_LVL");
//					if (!InterUtil.checkSJZDInfo(model.getCUST_CLASS_VAL(), "CHAA_LVL")) {
//						LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
//								"未找到匹配客户等级"+model.getCUST_CLASS_VAL(), "", "","");
//						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//						msg.setMessageText("未找到匹配客户等级"+model.getCUST_CLASS_VAL());
//						return msg;
//					}
				}
				if(!StringUtil.isEmpty(model.getSALE_CHANNEL_VAL())){
					InterUtil.checkSJZDInfo(model.getSALE_CHANNEL_VAL(),model.getSALE_CHANNEL(), "CHAA_SALE_LVL");
//					if (!InterUtil.checkSJZDInfo(model.getSALE_CHANNEL_VAL(), "CHAA_SALE_LVL")) {
//						LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
//								"未找到匹配销售渠道："+model.getSALE_CHANNEL_VAL(), "", "",	"");
//						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//						msg.setMessageText("未找到匹配销售渠道："+model.getSALE_CHANNEL_VAL());
//						return msg;
//					}
				}
				if(!StringUtil.isEmpty(model.getSALE_REGION_VAL())){
					InterUtil.clearObj(params, result, resultList);//清空查询对象
					/** 验证区域信息是否存在，如存在，则返回区域信息*/
					
					params.put("AREA_NAME", model.getSALE_REGION_VAL());//区域名称
					params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
					params.put("rownum", "1");
					result.add("AREA_ID");
					result.add("AREA_NO");
					result.add("AREA_NAME");
					resultList=InterUtil.getSqlResult(params, result, "BASE_AREA");
					if (resultList.isEmpty()) {
						LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
								"未找到匹配销售区域", "", "",	"");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("未找到匹配销售区域"+model.getSALE_REGION_VAL());
						return msg;
					}
				}
				map.put("AREA_ID", resultList.get(0).get("AREA_ID"));
				map.put("AREA_NO", resultList.get(0).get("AREA_NO"));
				map.put("AREA_NAME", resultList.get(0).get("AREA_NAME"));
				
				if(!StringUtil.isEmpty(model.getCOUNTRY_VAL())||!StringUtil.isEmpty(model.getPROVINCE_VAL())||
						!StringUtil.isEmpty(model.getCITY_VAL())||!StringUtil.isEmpty(model.getCOUNTY_VAL())){
					InterUtil.clearObj(params, result, resultList);//清空查询对象

					/** 验证国家省份信息是否在区域信息里存在，如存在，则返回区域信息id*/
					params.put("NATION", model.getCOUNTRY_VAL());//国家
					params.put("PROV", model.getPROVINCE_VAL());//省份
					params.put("CITY", model.getCITY_VAL());//城市
					params.put("COUNTY", model.getCOUNTY_VAL());//区县
					params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
					params.put("rownum", "1");
					result.add("ZONE_ID");
					resultList=InterUtil.getSqlResult(params, result, "BASE_ZONE");
					if(resultList.isEmpty()){
						LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
								"未找到匹配国家:"+model.getCOUNTRY_VAL()+"，省份:"+model.getPROVINCE_VAL()+"，城市:"+model.getCITY_VAL()+"，区县:"+model.getCOUNTY_VAL()+"信息", "", "","");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("未找到匹配国家:"+model.getCOUNTRY_VAL()+"，省份:"+model.getPROVINCE_VAL()+"，城市:"+model.getCITY_VAL()+"，区县:"+model.getCOUNTY_VAL()+"信息");
						return msg;
					}
				}
				map.put("ZONE_ID", resultList.get(0).get("ZONE_ID"));//行政区划ID
				map.put("NATION",model.getCOUNTRY_VAL());//国家
				map.put("PROV", model.getPROVINCE_VAL());//省份
				map.put("CITY", model.getCITY_VAL());//城市
				map.put("COUNTY", model.getCOUNTY_VAL());//区县
//				map.put("CITY_TYPE", model.get());//城市类型
				map.put("PERSON_CON", model.getCONTACT_PERSON());//联系人
				map.put("TEL", model.getPHONE());//电话
//				map.put("MOBILE", model.get());//手机
				map.put("TAX", model.getFAX());//传真
				map.put("POST", model.getPOSTAL_CODE());//邮编
				map.put("ADDRESS", model.getADDRESS());//地址
				map.put("EMAIL", model.getEMAIL());//电子邮件
//				map.put("WEB", model.get());//网站
				map.put("LEGAL_REPRE", model.getLEGAL_PERSON());//法人代表
				map.put("BUSS_LIC", model.getCUST_BUSI_LICENSE_ID());//营业执照号
				map.put("AX_BURDEN", model.getCUST_TAX_ID());//税务登记号
				map.put("ORG_CODE_CERT", model.getORG_CODE());//组织机构代码
//				map.put("BUSS_NATRUE", model.get());//经营性质 
//				map.put("BUSS_SCOPE", model.get());//经营范围
//				map.put("VAT_NO", model.get());//增值税号
//				map.put("INVOICE_TI", model.get());//发票抬头
//				map.put("INVOICE_ADDR", model.get());//发票地址
				map.put("BANK_NAME", model.getBANK());//开户银行
				map.put("BANK_ACCT", model.getBANK_ACCOUNT());//银行账号
				map.put("STATE", InterUtil.coveState(model.getUSE_STATUS()));//状态
				map.put("CHANN_TYPE", model.getSALE_CHANNEL_VAL());//销售渠道
				map.put("CHAA_SALE_LVL", model.getCUST_TYPE_VAL());//客户类别 
				map.put("CHAA_LVL", model.getCUST_CLASS_VAL());//客户等级
				if (!StringUtil.isEmpty(model.getDEFAULT_ORG())||!StringUtil.isEmpty(model.getDEFAULT_ORG_NAME())) {
					InterUtil.clearObj(params, result, resultList);//清空查询对象
					
					params.put("a.SHIP_POINT_NO", model.getDEFAULT_ORG());
					params.put("a.SHIP_POINT_NAME", model.getDEFAULT_ORG_NAME());
					params.put("a.STATE", BusinessConsts.JC_STATE_ENABLE);
					params.put("a.DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
					result.add("a.SHIP_POINT_ID");
					
					resultList=InterUtil.getSqlResult(params, result, " BASE_SHIP_POINT a ");
					if (resultList.isEmpty()) {
						LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
								"未找到匹配默认组织信息", "", "",	"");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("未找到匹配默认组织信息：编号"+ model.getDEFAULT_ORG()+"，名称"+ model.getDEFAULT_ORG_NAME());
						return msg;
					}
				}
				
				map.put("SHIP_POINT_ID", resultList.get(0).get("SHIP_POINT_ID"));//生产基地ID
				map.put("SHIP_POINT_NO", model.getDEFAULT_ORG());//生产基地NO
				map.put("SHIP_POINT_NAME",  model.getDEFAULT_ORG_NAME());//生产基地NAME
				map.put("JOIN_DATE", model.getENTER_TIME());//加盟时间
				String checkDateStr="[0-9]{4}-[0-9]{2}-[0-9]{2}";
				if(!StringUtil.regexCheck(model.getENTER_TIME(), checkDateStr)){
					LogicUtil.actLog("渠道信息管理", "新增渠道信息(新)", "MDM", "失败",
							"加盟时间日期格式不正确"+model.getENTER_TIME(), "", "",
							"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("加盟时间日期格式不正确"+model.getENTER_TIME());
					return msg;
				}
				map.putAll(InterUtil.getCreInfo("upd"));
				updList.add(map);
			}
			batch4Update("chann.upZTXX", updList);
			batch4Update("chann.upJGXX", updList);
			batch4Update("chann.upBMXX", updList);
			batch4Update("chann.upTerminal",updList);
			boolean flag=this.batch4Update("chann.updateById", updList);
			if(!flag){
				LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "SQL执行失败",
						BusinessConsts.IMPL_SQL_EXECUTE_FLASE, "", "",	"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText("修改客户信息失败!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改渠道信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败", errorInfo,
					"", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("修改客户信息出错!");
			return msg;
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "成功", new Gson().toJson(CHANN_LIST),"","","");
		return msg;
	}
	
	
	/**
	 * 新增用户信息
	 * @param USER_LIST
	 * @return ReturnInterInfo
	 * @throws Exception
	 */
	public ReturnInterInfo txInsNewUserInfo(InterUserModel[] USER_LIST) {
		ReturnInterInfo msg = new ReturnInterInfo();
		if (null==USER_LIST||USER_LIST.length==0) {
			LogicUtil.actLog("用户信息管理", "新增用户信息(新)", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
		LogicUtil.actLog("用户信息管理", "新增用户信息(新)==调入成功==", "MDM", "成功", new Gson().toJson(USER_LIST),"","","");
//		String[] mustFld = { "ACCOUNT_NO", "MDM_USER_NAME", "MDM_USER_PWD","MDM_USER_TYPE","MDM_USER_NO","ORG_ID","USE_STATE" };
		String[] mustFld = { "ACCOUNT_NO","MDM_USER_TYPE_VAL","MDM_USER_NO","ORG_ID_VAL"};
		try {
			if (!InterUtil.checkMustFld(mustFld, USER_LIST)) {
				LogicUtil.actLog("用户信息管理", "新增用户信息(新)", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			//用户信息
			List<Map<String, String>> addList=new ArrayList<Map<String, String>>();
			//系统用户账套地址
			List<Map<String, String>> addZTDZList=new ArrayList<Map<String, String>>();
			
			/** 验证对象 **/
			Map<String,String> params=new HashMap<String, String>();//参数
			List<String> result=new ArrayList<String>();//返回值
			List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();//查询结果
			
			for (int i = 0; i < USER_LIST.length; i++) {
				InterUserModel model=USER_LIST[i];
				Map<String,String> map=new HashMap<String, String>();
				String YHBH=model.getACCOUNT_NO();
				if (!InterUtil.checkPrimarykeyOld("YHBH", YHBH, "T_SYS_XTYH")) {
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("用户："+YHBH+"已存在!");
					return msg;
				}
				map.put("XTYHID", StringUtil.uuid32len());//系统用户ID
				//校验人员表里是否存在该人员信息
				InterUtil.clearObj(params, result, resultList);//清空查询对象
				String RYBH=model.getMDM_USER_NO();
				params.put("RYBH", RYBH);
				params.put("RYZT", BusinessConsts.JC_STATE_ENABLE);
				params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
				result.add("RYXXID");
				resultList=InterUtil.getSqlResult(params, result, " T_SYS_RYXX ");
				if (resultList.isEmpty()) {
					LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
							"员工编号："+RYBH+"不存在!", "", "",	"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("员工编号："+RYBH+"不存在!");
					return msg;
				}
				String RYXXID=resultList.get(0).get("RYXXID");
				//校验用户表里是否已存在该人员信息
				InterUtil.clearObj(params, result, resultList);//清空查询对象
				params.put("RYXXID", RYXXID);
				params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
				result.add("XTYHID");
				resultList=InterUtil.getSqlResult(params, result, " T_SYS_XTYH ");
				if (!resultList.isEmpty()) {
					LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
							"员工编号："+RYBH+"已存在用户，不能新增!", "", "",	"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("员工编号："+RYBH+"已存在用户，不能新增!");
					return msg;
				}
				
				
				InterUtil.clearObj(params, result, resultList);//清空查询对象
				String BMBH=model.getORG_ID();
				String BMMC=model.getORG_ID_VAL();
				params.put("BMBH", BMBH);
				params.put("BMMC", BMMC);
				params.put("BMZT", BusinessConsts.JC_STATE_ENABLE);
				params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
				result.add("BMXXID");
				
				resultList=InterUtil.getSqlResult(params, result, " T_SYS_BMXX ");
				if (resultList.isEmpty()) {
					LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
							"组织编号："+BMBH+",组织名称："+BMMC+"不存在！", "", "",	"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("组织编号："+BMBH+",组织名称："+BMMC+"不存在！");
					return msg;
				}
				String BMXXID=resultList.get(0).get("BMXXID");
				
				/**根据员工编号，获取员工信息，根据部门ID，获取部门信息，对比是否对应**/
				InterUtil.clearObj(params, result, resultList);
				params.put("RYBH", RYBH);//员工编号
				params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
				params.put("rownum", "1");
				params.put("BMXXID", BMXXID);//部门ID
				result.add("a.RYXXID");
				result.add("a.BMXXID");
				result.add("a.JGXXID");
				result.add("b.CHANN_ID ZTXXID");
				result.add("(case when NVL(b.IS_BASE_FLAG,0)=0 then 1 else 0 end)IS_DRP_LEDGER");
				resultList=InterUtil.getSqlResult(params, result, " T_SYS_RYXX a left join BASE_CHANN b on a.JGXXID=b.CHANN_ID ");
				if(resultList.isEmpty()){
					LogicUtil.actLog("用户信息管理", "新增用户信息(新)", "MDM", "失败",
							"所属部门:"+model.getORG_ID_VAL()+"与员工:"+model.getMDM_USER_NO()+"非同一组织", "", "",	"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("所属部门:"+model.getORG_ID_VAL()+"与员工:"+model.getMDM_USER_NO()+"非同一组织");
					return msg;
				}
				map.put("ZTXXID", resultList.get(0).get("ZTXXID"));//帐套信息ID
				map.put("JGXXID", resultList.get(0).get("JGXXID"));//机构信息ID
				map.put("BMXXID", resultList.get(0).get("BMXXID"));//部门信息ID
				map.put("RYXXID", resultList.get(0).get("RYXXID"));//人员信息ID
				map.put("YHBH", YHBH);
				map.put("YHM", model.getMDM_USER_NAME());//用户名
				map.put("YHKL", MD5Encrypt.MD5(model.getMDM_USER_PWD()));//用户密码
				map.put("YHZT", InterUtil.coveState(model.getUSE_STATUS()));//用户状态
				
				map.put("YHLB", model.getMDM_USER_TYPE_VAL());//用户类别
				InterUtil.checkSJZDInfo(model.getMDM_USER_TYPE_VAL(),model.getMDM_USER_TYPE(), "YHLB");
//				if (!InterUtil.checkSJZDInfo(model.getMDM_USER_TYPE_VAL(), "YHLB") ) {
//					LogicUtil.actLog("用户信息管理", "新增用户信息(新)", "MDM", "失败",
//							"未找到匹配用户类型:"+model.getMDM_USER_TYPE_VAL(), "", "",	"");
//					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//					msg.setMessageText("未找到匹配用户类型:"+model.getMDM_USER_TYPE_VAL());
//					return msg;
//				}
				map.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
				map.put("IS_DRP_LEDGER", String.valueOf(resultList.get(0).get("IS_DRP_LEDGER")));
				map.putAll(InterUtil.getCreInfo("add"));
				map.put("XTYHZTDZID", StringUtil.uuid32len());
				addList.add(map);
				addZTDZList.add(map);
			}
			this.batch4Update("XTYH.insert", addList);
			boolean flag=this.batch4Update("XTYH.insertZtfgMx", addZTDZList);
			if(!flag){
				LogicUtil.actLog("用户信息管理", "新增用户信息(新)", "MDM", "SQL执行失败",
						BusinessConsts.IMPL_SQL_EXECUTE_FLASE, "", "",	"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText("新增用户信息失败!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增用户信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("用户信息管理", "新增用户信息(新)", "MDM", "失败", errorInfo,
					"", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("新增用户信息出错!");
			return msg;
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("用户信息管理", "新增用户信息(新)", "MDM", "成功", new Gson().toJson(USER_LIST),"","","");
		return msg;
	}
	
	/**
	 * 修改用户信息
	 * @param jsonData
	 * @return
	 */
	public ReturnInterInfo txUpNewUserInfo(InterUserModel[] USER_LIST) {
		ReturnInterInfo msg = new ReturnInterInfo();
		if (null==USER_LIST||USER_LIST.length==0) {
			LogicUtil.actLog("用户信息管理", "修改用户信息(新)", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
		LogicUtil.actLog("用户信息管理", "修改用户信息(新)==调入成功==", "MDM", "成功", new Gson().toJson(USER_LIST),"","","");
//		String[] mustFld = { "ACCOUNT_NO", "MDM_USER_NAME", "MDM_USER_PWD","MDM_USER_TYPE","MDM_USER_NO","ORG_ID","USE_STATE" };
		String[] mustFld = { "ACCOUNT_NO","MDM_USER_TYPE_VAL","MDM_USER_NO","ORG_ID_VAL"};
		try {
			if (!InterUtil.checkMustFld(mustFld, USER_LIST)) {
				LogicUtil.actLog("用户信息管理", "修改用户信息(新)", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			List<Map<String, String>> updList=new ArrayList<Map<String, String>>();
			
			/** 验证对象 **/
			Map<String,String> params=new HashMap<String, String>();//参数
			List<String> result=new ArrayList<String>();//返回值
			List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();//查询结果
			
			for (int i = 0; i < USER_LIST.length; i++) {
				InterUserModel model=USER_LIST[i];
				Map<String,String> map=new HashMap<String, String>();
				String YHBH=model.getACCOUNT_NO();
				if (InterUtil.checkPrimarykeyOld("YHBH", YHBH, "T_SYS_XTYH")) {
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("用户"+YHBH+"不存在!");
					return msg;
				}
				
				//校验人员表里是否存在该人员信息
				InterUtil.clearObj(params, result, resultList);//清空查询对象
				String RYBH=model.getMDM_USER_NO();
				params.put("RYBH", RYBH);
				params.put("RYZT", BusinessConsts.JC_STATE_ENABLE);
				params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
				result.add("RYXXID");
				resultList=InterUtil.getSqlResult(params, result, " T_SYS_RYXX ");
				if (resultList.isEmpty()) {
					LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
							"员工编号："+RYBH+"不存在!", "", "",	"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("员工编号："+RYBH+"不存在!");
					return msg;
				}
				String RYXXID=resultList.get(0).get("RYXXID");
				//校验用户表里是否已存在该人员信息
				InterUtil.clearObj(params, result, resultList);//清空查询对象
				params.put("RYXXID", RYXXID);
				params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
				result.add("XTYHID");
				resultList=InterUtil.getSqlResult(params, result, " T_SYS_XTYH "," and YHBH!='"+YHBH+"' ");
				if (!resultList.isEmpty()) {
					LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
							"员工编号："+RYBH+"已存在用户，不能新增!", "", "",	"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("员工编号："+RYBH+"已存在用户，不能新增!");
					return msg;
				}
				
				
				
				InterUtil.clearObj(params, result, resultList);//清空查询对象
				String BMBH=model.getORG_ID();
				String BMMC=model.getORG_ID_VAL();
				params.put("BMBH", BMBH);
				params.put("BMMC", BMMC);
				params.put("BMZT", BusinessConsts.JC_STATE_ENABLE);
				params.put("DELFLAG",BusinessConsts.DEL_FLAG_COMMON);
				result.add("BMXXID");
				
				resultList=InterUtil.getSqlResult(params, result, " T_SYS_BMXX ");
				if (resultList.isEmpty()) {
					LogicUtil.actLog("渠道信息管理", "修改渠道信息(新)", "MDM", "失败",
							"组织编号："+BMBH+",组织名称："+BMMC+"不存在！", "", "",	"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("组织编号："+BMBH+",组织名称："+BMMC+"不存在！");
					return msg;
				}
				String BMXXID=resultList.get(0).get("BMXXID");
				/**根据员工编号，获取员工信息，根据部门ID，获取部门信息，对比是否对应**/
				if(!StringUtil.isEmpty(model.getMDM_USER_NO())||!StringUtil.isEmpty(model.getORG_ID_VAL())){
					InterUtil.clearObj(params, result, resultList);
					params.put("RYBH", model.getMDM_USER_NO());//员工编号
					params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
					params.put("rownum", "1");
					params.put("BMXXID", BMXXID);//部门ID
					result.add("a.RYXXID");
					result.add("a.BMXXID");
					result.add("a.JGXXID");
					result.add("b.CHANN_ID ZTXXID");
					result.add("(case when NVL(b.IS_BASE_FLAG,0)=0 then 1 else 0 end)IS_DRP_LEDGER");
					resultList=InterUtil.getSqlResult(params, result, " T_SYS_RYXX a left join BASE_CHANN b on a.JGXXID=b.CHANN_ID ");
					if(resultList.isEmpty()){
						LogicUtil.actLog("用户信息管理", "修改用户信息(新)", "MDM", "失败",
								"所属部门:"+model.getORG_ID_VAL()+"与员工:"+model.getMDM_USER_NO()+"非同一组织", "", "",	"");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("所属部门:"+model.getORG_ID_VAL()+"与员工:"+model.getMDM_USER_NO()+"非同一组织");
						return msg;
					}
					map.put("ZTXXID", resultList.get(0).get("ZTXXID"));//帐套信息ID
					map.put("JGXXID", resultList.get(0).get("JGXXID"));//机构信息ID
					map.put("BMXXID", resultList.get(0).get("BMXXID"));//部门信息ID
					map.put("RYXXID", resultList.get(0).get("RYXXID"));//人员信息ID
				}
				
				map.put("YHBH", YHBH);
				map.put("YHM", model.getMDM_USER_NAME());//用户名
				map.put("YHKL", MD5Encrypt.MD5(model.getMDM_USER_PWD()));//用户密码
				map.put("YHZT", InterUtil.coveState(model.getUSE_STATUS()));//用户状态
				map.put("YHLB", model.getMDM_USER_TYPE_VAL());//用户类别
				InterUtil.checkSJZDInfo(model.getMDM_USER_TYPE_VAL(),model.getMDM_USER_TYPE(), "YHLB");
//				if (!InterUtil.checkSJZDInfo(model.getMDM_USER_TYPE_VAL(), "YHLB") ) {
//					LogicUtil.actLog("用户信息管理", "新增用户信息(新)", "MDM", "失败",
//							"未找到匹配用户类型:"+model.getMDM_USER_TYPE_VAL(), "", "",	"");
//					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//					msg.setMessageText("未找到匹配用户类型:"+model.getMDM_USER_TYPE_VAL());
//					return msg;
//				}
				map.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
				map.put("IS_DRP_LEDGER", String.valueOf(resultList.get(0).get("IS_DRP_LEDGER")));
				map.putAll(InterUtil.getCreInfo("add"));
				updList.add(map);
			}
			boolean flag=this.batch4Update("XTYH.updateByNo", updList);
			if(!flag){
				LogicUtil.actLog("用户信息管理", "修改用户信息(新)", "MDM", "SQL执行失败",
						BusinessConsts.IMPL_SQL_EXECUTE_FLASE, "", "",	"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText("修改用户信息失败!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改用户信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("用户信息管理", "修改用户信息(新)", "MDM", "失败", errorInfo,
					"", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("修改用户信息出错!");
			return msg;
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("用户信息管理", "修改用户信息(新)", "MDM", "成功", new Gson().toJson(USER_LIST),"","","");
		return msg;
	}
	
	
	/**
	 * 新增物料信息
	 * @param jsonData
	 * @return
	 */
	public ReturnInterInfo txInsNewProduct(InterProductModel[] PRODUCT_LIST) {
		ReturnInterInfo msg = new ReturnInterInfo();
		if (null==PRODUCT_LIST||PRODUCT_LIST.length==0) {
			LogicUtil.actLog("物料信息管理", "新增物料信息(新)", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
		LogicUtil.actLog("物料信息管理", "新增物料信息(新)==调入成功==", "MDM", "成功", new Gson().toJson(PRODUCT_LIST),"","","");
//		String[] mustFld = { "MATERIAL_CODE","MATERIAL_NAME","UNIT_ID","STOCK_TYPE","PACK_VOLUME","LENGTH","WIDTH","HEIGHT","MATERIAL_MODEL",
//							"BRAND","WHOLESALE_PRICE","RETAIL_PRICE","LOWEST_PRICE","PLAN_LAUNCH_DATE"};
		String[] mustFld = {"MATERIAL_CODE","MATERIAL_NAME","UNIT_ID","STOCK_TYPE","SALE_TYPE","PACK_VOLUME","LENGTH"
				,"WIDTH","HEIGHT","RETAIL_PRICE","LOWEST_PRICE","PLAN_LAUNCH_DATE"};
		try {
			if (!InterUtil.checkMustFld(mustFld, PRODUCT_LIST)) {
				LogicUtil.actLog("物料信息管理", "新增物料信息(新)", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			
			/** 验证对象 **/
			Map<String,String> params=new HashMap<String, String>();//参数
			List<String> result=new ArrayList<String>();//返回值
			List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();//查询结果
			/** 校验体积，长宽高，供货价，吊牌价，最低零售价是否为数字*/
			StringBuffer message=new StringBuffer();
			String checkStr="^[0-9]{1,8}+(.[0-9]{2})?$";//验证8位整数2位小数
			String checkDateStr="[0-9]{4}-[0-9]{2}-[0-9]{2}";
			List<Map<String, String>> addList=new ArrayList<Map<String, String>>();
			for (int i = 0; i < PRODUCT_LIST.length; i++) {
				//品牌编号
				InterProductModel model=PRODUCT_LIST[i];
				Map<String, String> map=new HashMap<String, String>();
				map.put("PRD_ID", StringUtil.uuid32len());
				String PRD_NO=model.getMATERIAL_CODE();//货品编号
				if (!InterUtil.checkPrimarykey("PRD_NO", PRD_NO, "BASE_PRODUCT")) {
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("物料:"+PRD_NO+"已存在!");
					return msg;
				}
				map.put("PRD_NO", PRD_NO);
				map.put("PRD_NAME", model.getMATERIAL_NAME());//货品名称
				map.put("PRD_SPEC", model.getSPEC());//规格型号
				map.put("PRD_COLOR", model.getINNER_MATERIAL_MODEL());//颜色
//				params.put("BRAND_ID", model.getBRAND());//品牌编号
//				params.put("BRAND", model.getBRAND_VAL());
//				params.put("rownum", "1");
//				result.add("count(1) CNT");
//				resultList=InterUtil.getSqlResult(params, result, " BASE_BRAND ");
//				if (resultList.isEmpty()||StringUtil.getInteger(resultList.get(0).get("CNT"))==0) {
//					LogicUtil.actLog("物料信息管理", "新增物料信息(新)", "MDM", "失败",
//							"品牌:"+model.getBRAND_VAL()+"不存在", "", "","");
//					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//					msg.setMessageText("品牌:"+model.getBRAND_VAL()+"不存在");
//					return msg;
//				}
				map.put("BRAND", model.getBRAND_VAL());//品牌
				InterUtil.clearObj(params, result, resultList);
				/**查询单位是否存在**/
				params.put("MEAS_UNIT_NO", model.getUNIT_ID());
				params.put("MEAS_UNIT_NAME", model.getUNIT_ID_VAL());
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				params.put("rownum", "1");
				result.add("count(1) CNT");
				List<Map<String,String>> unitList=InterUtil.getSqlResult(params, result, " BASE_UNIT ");
				if(unitList.isEmpty()||StringUtil.getInteger(unitList.get(0).get("CNT"))==0){
					LogicUtil.actLog("物料信息管理", "新增物料信息(新)", "MDM", "失败",
							"未找到匹配单位"+model.getUNIT_ID()+","+model.getUNIT_ID_VAL(), "", "","");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("未找到匹配单位，编号："+model.getUNIT_ID()+",名称："+model.getUNIT_ID_VAL());
					return msg;
				}
				map.put("STD_UNIT", model.getUNIT_ID_VAL());//标准单位
//				map.put("MEAS_UNIT", value);//默认计量单位
//				map.put("RATIO", value);//换算关系
				
				InterUtil.clearObj(params, result, resultList);
				//货品分类
				/**查询货品分类是否存在**/
				params.put("PRD_NO", model.getSTOCK_TYPE());
				params.put("PRD_NAME", model.getSTOCK_TYPE_VAL());
				params.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				params.put("rownum", "1");
				result.add("PRD_ID");
				result.add("PRD_NO");
				result.add("PRD_NAME");
				 resultList=InterUtil.getSqlResult(params, result, " BASE_PRODUCT ");
				if(resultList.isEmpty()){
					LogicUtil.actLog("物料信息管理", "新增物料信息(新)", "MDM", "失败",
							"未找到匹配货品分类", "", "",	"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("未找到匹配储存分类信息，编号："+model.getSTOCK_TYPE()+",名称："+model.getSTOCK_TYPE_VAL());
					return msg;
				}
				map.put("PAR_PRD_ID", resultList.get(0).get("PRD_ID"));
				map.put("PAR_PRD_NO", resultList.get(0).get("PRD_NO"));
				map.put("PAR_PRD_NAME", resultList.get(0).get("PRD_NAME"));
				map.put("PRD_TYPE", model.getSALE_TYPE());//货品类别
				
				
				if(!StringUtil.regexCheck(model.getPACK_VOLUME(), checkStr)){
					message.append("包装体积:").append(model.getPACK_VOLUME()).append(",");
				}
				map.put("VOLUME", model.getPACK_VOLUME());//包装体积
				
				if(!StringUtil.regexCheck(model.getLENGTH(), checkStr)){
					message.append("长:").append(model.getLENGTH()).append(",");
				}
				map.put("LENGTH", model.getLENGTH());//长
				
				if(!StringUtil.regexCheck(model.getWIDTH(), checkStr)){
					message.append("宽:").append(model.getWIDTH()).append(",");
				}
				map.put("WIDTH", model.getWIDTH());//宽
				
				if(!StringUtil.regexCheck(model.getHEIGHT(), checkStr)){
					message.append("高:").append(model.getHEIGHT()).append(",");
				}
				map.put("HEIGHT", model.getHEIGHT());//高
				
				
				if(!StringUtil.regexCheck(model.getWHOLESALE_PRICE(), checkStr)){
					message.append("供货价:").append(model.getWHOLESALE_PRICE()).append(",");
				}
				map.put("PRVD_PRICE", model.getWHOLESALE_PRICE());//供货价
				
				if(!StringUtil.regexCheck(model.getRETAIL_PRICE(), checkStr)){
					message.append("统一零售价:").append(model.getRETAIL_PRICE()).append(",");
				}
				map.put("FACT_PRICE", model.getRETAIL_PRICE());//最低零售价//出厂价
				
				if(!StringUtil.regexCheck(model.getLOWEST_PRICE(), checkStr)){
					message.append("最低零售价:").append(model.getLOWEST_PRICE()).append(",");
				}
				map.put("RET_PRICE_MIN", model.getLOWEST_PRICE());//最低零售价
				
				if(!StringUtil.isEmpty(message.toString())){
					message=InterUtil.replaceUpSql(message);
					LogicUtil.actLog("物料信息管理", "新增物料信息(新)", "MDM", "失败",
							message.toString()+"。数字格式不正确", "", "","");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText(message.toString()+"。数字格式不正确");
					return msg;
				}
				if(!StringUtil.regexCheck(model.getPLAN_LAUNCH_DATE(), checkDateStr)){
					LogicUtil.actLog("物料信息管理", "新增物料信息(新)", "MDM", "失败",
							"计划上市日期格式不正确:"+model.getPLAN_LAUNCH_DATE(), "", "",
							"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("计划上市日期格式不正确:"+model.getPLAN_LAUNCH_DATE());
					return msg;
				}
				map.put("BEG_DATE", model.getPLAN_LAUNCH_DATE());//开始生产日期
				map.put("PRD_SUIT_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);//货品套标记    0
				map.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);//终结点标记  1
				map.put("COMM_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);//是否总部货品 1
				map.put("STATE", InterUtil.coveState(model.getUSE_STATUS()));//状态
				map.put("PRD_MATL", model.getTEXTURE());//材质
				map.putAll(InterUtil.getCreInfo("add"));
				addList.add(map);
			}
			boolean flag=this.batch4Update("product.insert", addList);
			if(!flag){
				LogicUtil.actLog("物料信息管理", "新增物料信息(新)", "MDM", "SQL执行失败",
						BusinessConsts.IMPL_SQL_EXECUTE_FLASE, "", "",	"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText("新增物料信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增物料信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("物料信息管理", "新增物料信息(新)", "MDM", "失败", errorInfo,
					"", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("新增物料信息出错!");
			return msg;
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("物料信息管理", "新增物料信息(新)", "MDM", "成功", new Gson().toJson(PRODUCT_LIST),"","","");
		return msg;
	}
	
	
	/**
	 * 修改物料信息
	 * @param jsonData
	 * @return
	 */
	public ReturnInterInfo txUpNewProduct(InterProductModel[] PRODUCT_LIST) {
		ReturnInterInfo msg = new ReturnInterInfo();
		if (null==PRODUCT_LIST||PRODUCT_LIST.length==0) {
			LogicUtil.actLog("物料信息管理", "修改物料信息(新)", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
		LogicUtil.actLog("物料信息管理", "修改物料信息(新)==调入成功==", "MDM", "成功", new Gson().toJson(PRODUCT_LIST),"","","");
//		String[] mustFld = { "MATERIAL_CODE","MATERIAL_NAME","UNIT_ID","STOCK_TYPE","PACK_VOLUME","LENGTH","WIDTH","HEIGHT","MATERIAL_MODEL",
//							"BRAND","WHOLESALE_PRICE","RETAIL_PRICE","LOWEST_PRICE","PLAN_LAUNCH_DATE"};
		String[] mustFld = {"MATERIAL_CODE","MATERIAL_NAME","UNIT_ID","STOCK_TYPE","SALE_TYPE","PACK_VOLUME","LENGTH"
				,"WIDTH","HEIGHT","RETAIL_PRICE","LOWEST_PRICE","PLAN_LAUNCH_DATE"};
		try {
			if (!InterUtil.checkMustFld(mustFld, PRODUCT_LIST)) {
				LogicUtil.actLog("物料信息管理", "修改物料信息(新)", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			
			/** 验证对象 **/
			Map<String,String> params=new HashMap<String, String>();//参数
			List<String> result=new ArrayList<String>();//返回值
			List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();//查询结果
			/** 校验体积，长宽高，供货价，吊牌价，最低零售价是否为数字*/
			StringBuffer message=new StringBuffer();
			String checkStr="^[0-9]{1,8}+(.[0-9]{2})?$";//验证8位整数2位小数
			String checkDateStr="[0-9]{4}-[0-9]{2}-[0-9]{2}";
			List<Map<String, String>> addList=new ArrayList<Map<String, String>>();
			for (int i = 0; i < PRODUCT_LIST.length; i++) {
				//品牌编号
				InterProductModel model=PRODUCT_LIST[i];
				Map<String, String> map=new HashMap<String, String>();
				map.put("PRD_ID", StringUtil.uuid32len());
				String PRD_NO=model.getMATERIAL_CODE();//货品编号
				if (InterUtil.checkPrimarykey("PRD_NO", PRD_NO, "BASE_PRODUCT")) {
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("物料"+PRD_NO+"不存在!");
					return msg;
				}
				map.put("PRD_NO", PRD_NO);
				map.put("PRD_NAME", model.getMATERIAL_NAME());//货品名称
				map.put("PRD_SPEC", model.getSPEC());//规格型号
				map.put("PRD_COLOR", model.getINNER_MATERIAL_MODEL());//颜色
//				if(!StringUtil.isEmpty(model.getBRAND())){
//					params.put("BRAND", model.getBRAND_VAL());
//					params.put("rownum", "1");
//					result.add("count(1) CNT");
//					resultList=InterUtil.getSqlResult(params, result, " BASE_BRAND ");
//					if (resultList.isEmpty()||StringUtil.getInteger(resultList.get(0).get("CNT"))==0) {
//						LogicUtil.actLog("物料信息管理", "修改物料信息(新)", "MDM", "失败",
//								"品牌:"+ model.getBRAND_VAL()+"不存在", "", "","");
//						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//						msg.setMessageText("品牌:"+model.getBRAND_VAL()+"不存在");
//						return msg;
//					}
//				}
				map.put("BRAND", model.getBRAND_VAL());//品牌
				if(!StringUtil.isEmpty(model.getUNIT_ID())){
					InterUtil.clearObj(params, result, resultList);
					/**查询单位是否存在**/
					params.put("MEAS_UNIT_NO", model.getUNIT_ID());
					params.put("MEAS_UNIT_NAME", model.getUNIT_ID_VAL());
					params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
					params.put("rownum", "1");
					result.add("count(1) CNT");
					List<Map<String,String>> unitList=InterUtil.getSqlResult(params, result, " BASE_UNIT ");
					if(unitList.isEmpty()||StringUtil.getInteger(unitList.get(0).get("CNT"))==0){
						LogicUtil.actLog("物料信息管理", "修改物料信息(新)", "MDM", "失败",
								"未找到匹配单位，编号："+model.getUNIT_ID()+",名称："+model.getUNIT_ID_VAL(), "", "","");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("未找到匹配单位，编号："+model.getUNIT_ID()+",名称："+model.getUNIT_ID_VAL());
						return msg;
					}
				}
				
				map.put("STD_UNIT", model.getUNIT_ID_VAL());//标准单位
//				map.put("MEAS_UNIT", value);//默认计量单位
//				map.put("RATIO", value);//换算关系
				
				if(!StringUtil.isEmpty(model.getSTOCK_TYPE())){
					InterUtil.clearObj(params, result, resultList);
					//货品分类
					/**查询货品分类是否存在**/
					params.put("PRD_NO", model.getSTOCK_TYPE());
					params.put("PRD_NAME", model.getSTOCK_TYPE_VAL());
					params.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
					params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
					params.put("rownum", "1");
					result.add("PRD_ID");
					result.add("PRD_NO");
					result.add("PRD_NAME");
					 resultList=InterUtil.getSqlResult(params, result, " BASE_PRODUCT ");
					if(resultList.isEmpty()){
						LogicUtil.actLog("物料信息管理", "修改物料信息(新)", "MDM", "失败",
								"未找到匹配货品分类信息，编号："+model.getSTOCK_TYPE()+",名称："+model.getSTOCK_TYPE_VAL(), "", "",	"");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("未找到匹配储存分类信息,编号："+model.getSTOCK_TYPE()+",名称："+model.getSTOCK_TYPE_VAL());
						return msg;
					}
					map.put("PAR_PRD_ID", resultList.get(0).get("PRD_ID"));
					map.put("PAR_PRD_NO", resultList.get(0).get("PRD_NO"));
					map.put("PAR_PRD_NAME", resultList.get(0).get("PRD_NAME"));
				}
				
				map.put("PRD_TYPE", model.getSALE_TYPE());//货品类别
				
				
				if(!StringUtil.isEmpty(model.getPACK_VOLUME())&&!StringUtil.regexCheck(model.getPACK_VOLUME(), checkStr)){
					message.append("包装体积:").append(model.getPACK_VOLUME()).append(",");
				}
				map.put("VOLUME", model.getPACK_VOLUME());//包装体积
				
				if(!StringUtil.isEmpty(model.getLENGTH())&&!StringUtil.regexCheck(model.getLENGTH(), checkStr)){
					message.append("长:").append(model.getLENGTH()).append(",");
				}
				map.put("LENGTH", model.getLENGTH());//长
				
				if(!StringUtil.isEmpty(model.getWIDTH())&&!StringUtil.regexCheck(model.getWIDTH(), checkStr)){
					message.append("宽:").append(model.getWIDTH()).append(",");
				}
				map.put("WIDTH", model.getWIDTH());//宽
				
				if(!StringUtil.isEmpty(model.getHEIGHT())&&!StringUtil.regexCheck(model.getHEIGHT(), checkStr)){
					message.append("高:").append(model.getHEIGHT()).append(",");
				}
				map.put("HEIGHT", model.getHEIGHT());//高
				
				
				if(!StringUtil.isEmpty(model.getWHOLESALE_PRICE())&&!StringUtil.regexCheck(model.getWHOLESALE_PRICE(), checkStr)){
					message.append("供货价:").append(model.getWHOLESALE_PRICE()).append(",");
				}
				map.put("PRVD_PRICE", model.getWHOLESALE_PRICE());//供货价
				
				if(!StringUtil.isEmpty(model.getRETAIL_PRICE())&&!StringUtil.regexCheck(model.getRETAIL_PRICE(), checkStr)){
					message.append("统一零售价:").append(model.getRETAIL_PRICE()).append(",");
				}
				map.put("FACT_PRICE", model.getRETAIL_PRICE());//最低零售价//出厂价
				
				if(!StringUtil.isEmpty(model.getLOWEST_PRICE())&&!StringUtil.regexCheck(model.getLOWEST_PRICE(), checkStr)){
					message.append("最低零售价:").append(model.getLOWEST_PRICE()).append(",");
				}
				map.put("RET_PRICE_MIN", model.getLOWEST_PRICE());//最低零售价
				
				if(!StringUtil.isEmpty(message.toString())){
					message=InterUtil.replaceUpSql(message);
					LogicUtil.actLog("物料信息管理", "修改物料信息(新)", "MDM", "失败",
							message.toString()+"。数字格式不正确", "", "","");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText(message.toString());
					return msg;
				}
				if(!StringUtil.isEmpty(model.getPLAN_LAUNCH_DATE())&&!StringUtil.regexCheck(model.getPLAN_LAUNCH_DATE(), checkDateStr)){
					LogicUtil.actLog("物料信息管理", "修改物料信息(新)", "MDM", "失败",
							"计划上市日期格式不正确:"+model.getPLAN_LAUNCH_DATE(), "", "",
							"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("计划上市日期格式不正确:"+model.getPLAN_LAUNCH_DATE());
					return msg;
				}
				map.put("BEG_DATE", model.getPLAN_LAUNCH_DATE());//开始生产日期
				map.put("PRD_SUIT_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);//货品套标记    0
				map.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);//终结点标记  1
				map.put("STATE", InterUtil.coveState(model.getUSE_STATUS()));//状态
				map.put("PRD_MATL", model.getTEXTURE());//材质
				map.putAll(InterUtil.getCreInfo("add"));
				addList.add(map);
			}
			boolean flag=this.batch4Update("product.updateByNo", addList);
			if(!flag){
				LogicUtil.actLog("物料信息管理", "修改物料信息(新)", "MDM", "SQL执行失败",
						BusinessConsts.IMPL_SQL_EXECUTE_FLASE, "", "",	"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText("修改物料信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改物料信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("物料信息管理", "修改物料信息(新)", "MDM", "失败", errorInfo,
					"", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("修改物料信息出错!");
			return msg;
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("物料信息管理", "修改物料信息(新)", "MDM", "成功", new Gson().toJson(PRODUCT_LIST),"","","");
		return msg;
	}
	
	
	
	/**
	 * 新增组织信息
	 * 
	 * @param CHANN_LIST
	 * @return
	 * @throws Exception
	 */
	public ReturnInterInfo txInsNewBranch(InterBranchModel[] BRANCH_LIST) {
		ReturnInterInfo msg = new ReturnInterInfo();
		if (null==BRANCH_LIST||BRANCH_LIST.length==0) {
			LogicUtil.actLog("组织信息管理", "新增组织信息(新)", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
		LogicUtil.actLog("组织信息管理", "新增组织信息(新)==调入成功==", "MDM", "成功", new Gson().toJson(BRANCH_LIST),"","","");
//		String[] mustFld = { "ORG_CODE", "ORG_NAME", "USE_STATUS" };
		String[] mustFld = { "ORG_CODE" };
		
		try {
			if (!InterUtil.checkMustFld(mustFld, BRANCH_LIST)) {
				LogicUtil.actLog("组织信息管理", "新增组织信息(新)", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			
			/** 验证对象 **/
			Map<String,String> params=new HashMap<String, String>();//参数
			List<String> result=new ArrayList<String>();//返回值
			List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();//查询结果
			
			List<Map<String, String>> addList=new ArrayList<Map<String, String>>();
			for (int i = 0; i < BRANCH_LIST.length; i++) {
				Map<String, String> map=new HashMap<String, String>();
				InterBranchModel model=BRANCH_LIST[i];
				if (!InterUtil.checkPrimarykeyOld("BMBH", model.getORG_CODE(), "T_SYS_BMXX")) {
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("组织:"+model.getORG_CODE()+"已存在!");
					return msg;
				}
				map.put("BMXXID", StringUtil.uuid32len());//部门信息ID
				map.put("BMBH", model.getORG_CODE());//部门编号
				map.put("BMMC", model.getORG_NAME());//组织名称
				map.put("BMJC", model.getABBREV_NAME());//简称
				map.put("DH", model.getPHONE());//电话
				map.put("CZ", model.getFAX());//传真
//				map.put("DZYJ", model.get());//电子邮件
				
				//上级部门编号
				if(!StringUtil.isEmpty(model.getPARENT_CODE())){
					InterUtil.clearObj(params, result, resultList);
					
					/**上级部门是否存在**/
					params.put("BMBH", model.getPARENT_CODE());
					params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
					params.put("rownum", "1");
					result.add("BMXXID");
					result.add("JGXXID");
					result.add("ZTXXID");
					resultList=InterUtil.getSqlResult(params, result, " T_SYS_BMXX ");
					if(resultList.isEmpty()){
						LogicUtil.actLog("组织信息管理", "新增组织信息(新)", "MDM", "失败",
								"未找到匹配上级部门编码:"+model.getPARENT_CODE(), "", "",	"");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("未找到匹配上级部门编码："+model.getPARENT_CODE());
						return msg;
					}
					if(!"00".equals(resultList.get(0).get("JGXXID"))||!"00".equals(resultList.get(0).get("ZTXXID"))){
						LogicUtil.actLog("组织信息管理", "新增组织信息(新)", "MDM", "失败",
								"上级部门非总部部门"+model.getPARENT_CODE(), "", "",	"");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("上级组织:"+model.getPARENT_CODE()+"非总部组织");
						return msg;
					}
					if(model.getPARENT_CODE().equals(model.getORG_CODE())){
						LogicUtil.actLog("组织信息管理", "新增组织信息(新)", "MDM", "失败",
								"上级部门不能为本部门:"+model.getPARENT_CODE(), "", "",	"");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("上级部门不能为本部门:"+model.getPARENT_CODE());
						return msg;
					}
					map.put("SJBM", resultList.get(0).get("BMXXID"));//上级部门
				}
				
				map.put("BMZT", InterUtil.coveState(model.getUSE_STATUS()));//
				map.put("XXDZ", model.getADDRESS());//详细地址
				map.put("JGXXID", "00");//机构信息ID
				map.put("ZTXXID", "00");//部门信息ID
				map.put("YZBM", model.getPOST_CODE());//邮政编码
				map.put("ZYDZ", model.getURL());//主页地址
				map.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
				addList.add(map);
			}
			boolean flag=this.batch4Update("BMXX.insert", addList);
			if(!flag){
				LogicUtil.actLog("组织信息管理", "新增组织信息(新)", "MDM", "SQL执行失败",
						BusinessConsts.IMPL_SQL_EXECUTE_FLASE, "", "",	"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText("新增组织信息失败!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增组织信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("组织信息管理", "新增组织信息(新)", "MDM", "失败", errorInfo,
					"", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("新增组织信息出错!");
			return msg;
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("组织信息管理", "新增组织信息(新)", "MDM", "成功", new Gson().toJson(BRANCH_LIST),"","","");
		return msg;
	}
	
	
	/**
	 * 修改组织信息
	 * @param BRANCH_LIST
	 * @return
	 */
	public ReturnInterInfo txUpNewBranch(InterBranchModel[] BRANCH_LIST) {
		ReturnInterInfo msg = new ReturnInterInfo();
		if (null==BRANCH_LIST||BRANCH_LIST.length==0) {
			LogicUtil.actLog("组织信息管理", "修改组织信息(新)", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
//		String[] mustFld = { "ORG_CODE", "ORG_NAME", "USE_STATUS" };
		LogicUtil.actLog("组织信息管理", "修改组织信息(新)==调入成功==", "MDM", "成功", new Gson().toJson(BRANCH_LIST),"","","");
		String[] mustFld = { "ORG_CODE" };
		try {
			if (!InterUtil.checkMustFld(mustFld, BRANCH_LIST)) {
				LogicUtil.actLog("组织信息管理", "修改组织信息(新)", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			
			/** 验证对象 **/
			Map<String,String> params=new HashMap<String, String>();//参数
			List<String> result=new ArrayList<String>();//返回值
			List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();//查询结果
			
			List<Map<String, String>> updList=new ArrayList<Map<String, String>>();
			for (int i = 0; i < BRANCH_LIST.length; i++) {
				Map<String, String> map=new HashMap<String, String>();
				InterBranchModel model=BRANCH_LIST[i];
				if (InterUtil.checkPrimarykeyOld("BMBH", model.getORG_CODE(), "T_SYS_BMXX")) {
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("组织:"+model.getORG_CODE()+"不存在!");
					return msg;
				}
				map.put("BMBH", model.getORG_CODE());//部门编号
				map.put("BMMC", model.getORG_NAME());//组织名称
				map.put("BMJC", model.getABBREV_NAME());//简称
				map.put("DH", model.getPHONE());//电话
				map.put("CZ", model.getFAX());//传真
//				map.put("DZYJ", model.get());//电子邮件
				
				//上级部门编号
				if(!StringUtil.isEmpty(model.getPARENT_CODE())){
					
					InterUtil.clearObj(params, result, resultList);
					
					/**上级部门是否存在**/
					params.put("BMBH", model.getPARENT_CODE());
					params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
					params.put("rownum", "1");
					result.add("BMXXID");
					result.add("JGXXID");
					result.add("ZTXXID");
					resultList=InterUtil.getSqlResult(params, result, " T_SYS_BMXX ");
					if(resultList.isEmpty()){
						LogicUtil.actLog("组织信息管理", "新增组织信息(新)", "MDM", "失败",
								"未找到匹配上级部门编码:"+model.getPARENT_CODE(), "", "",	"");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("未找到匹配上级组织编码"+model.getPARENT_CODE());
						return msg;
					}
					if(!"00".equals(resultList.get(0).get("JGXXID"))||!"00".equals(resultList.get(0).get("ZTXXID"))){
						LogicUtil.actLog("组织信息管理", "修改组织信息(新)", "MDM", "失败",
								"上级部门非总部部门", "", "",	"");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("上级组织:"+model.getPARENT_CODE()+"非总部组织");
						return msg;
					}
					if(model.getPARENT_CODE().equals(model.getORG_CODE())){
						LogicUtil.actLog("组织信息管理", "修改组织信息(新)", "MDM", "失败",
								"上级部门不能为本部门:"+model.getPARENT_CODE(), "", "",	"");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("上级部门不能为本部门:"+model.getPARENT_CODE());
						return msg;
					}
					map.put("SJBM", resultList.get(0).get("BMXXID"));//上级部门
				}
				
				map.put("BMZT", InterUtil.coveState(model.getUSE_STATUS()));//
				map.put("XXDZ", model.getADDRESS());//详细地址
				map.put("JGXXID", "00");//机构信息ID
				map.put("ZTXXID", "00");//部门信息ID
				map.put("YZBM", model.getPOST_CODE());//邮政编码
				map.put("ZYDZ", model.getURL());//主页地址
				map.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
				updList.add(map);
			}
			boolean flag=this.batch4Update("BMXX.updateByNo", updList);
			if(!flag){
				LogicUtil.actLog("组织信息管理", "修改组织信息(新)", "MDM", "SQL执行失败",
						BusinessConsts.IMPL_SQL_EXECUTE_FLASE, "", "",	"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText("修改组织信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改组织信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("组织信息管理", "修改组织信息(新)", "MDM", "失败", errorInfo,
					"", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("修改组织信息出错!");
			return msg;
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("组织信息管理", "修改组织信息(新)", "MDM", "成功", new Gson().toJson(BRANCH_LIST),"","","");
		return msg;
	}
	 
	
	/**
	 * 新增员工信息
	 * 
	 * @param EMPLOYEE_LIST
	 * @return
	 * @throws Exception
	 */
	public ReturnInterInfo txInsNewEmployee(InterEmployeeModel[] EMPLOYEE_LIST) {
		ReturnInterInfo msg = new ReturnInterInfo();
		if (null==EMPLOYEE_LIST||EMPLOYEE_LIST.length==0) {
			LogicUtil.actLog("用户信息管理", "新增用户信息(新)", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
		LogicUtil.actLog("员工信息管理", "新增员工信息(新)==调入成功==", "MDM", "成功", new Gson().toJson(EMPLOYEE_LIST),"","","");
//		String[] mustFld = { "EMPLOYEE_CODE", "EMPLOYEE_NAME", "SEX","PHONE","BELONG_CODE","WORK_STATUS","ID_NUMBER","OFFICE_PHONE",
//				"OFFICE_EMAIL","POST_NAME","POST_NAME","POSITION_NAME","POST_LEVEL" };
		String[] mustFld = {"EMPLOYEE_CODE"};
		try {
			if (!InterUtil.checkMustFld(mustFld, EMPLOYEE_LIST)) {
				LogicUtil.actLog("员工信息管理", "新增员工信息(新)", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
			for (int i = 0; i < EMPLOYEE_LIST.length; i++) {
				InterEmployeeModel model =EMPLOYEE_LIST[i];
				Map<String,String> map=new HashMap<String, String>();
				
				if (!InterUtil.checkPrimarykeyOld("RYBH", model.getEMPLOYEE_CODE(), "T_SYS_RYXX")) {
					LogicUtil.actLog("员工信息管理", "新增员工信息(新)", "MDM", "失败",
							"员工"+model.getEMPLOYEE_CODE()+"已存在!", "", "",
							"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("员工"+model.getEMPLOYEE_CODE()+"已存在!");
					return msg;
				}
				map.put("RYXXID", StringUtil.uuid32len());
				map.put("RYBH", model.getEMPLOYEE_CODE());//人员编号
				map.put("XM", model.getEMPLOYEE_NAME());//员工名称
				String XB=model.getSEX_VAL();
				if(!"男".equals(XB)&&!"女".equals(XB)){
					LogicUtil.actLog("员工信息管理", "新增员工信息(新)", "MDM", "失败",
							"性别："+XB+"错误！", "", "",
							"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("性别："+XB+"错误！");
					return msg;
				}
				map.put("XB", model.getSEX_VAL());//性别
				map.put("SFZH", model.getID_NUMBER());//身份证号
				map.put("GSDH", model.getOFFICE_PHONE());//公司电话
				map.put("SJ", model.getPHONE());//手机
				map.put("DZYJ", model.getOFFICE_EMAIL());//电子邮件
				
				map.put("ZW", model.getPOSITION_NAME_VAL());//职务
				map.put("RYJB", model.getPOST_LEVEL_VAL());//人员级别
				map.put("RYLB", model.getEMPLOYEE_TYPE_VAL());//人员类别
				
				if (!StringUtil.isEmpty(model.getEMPLOYEE_TYPE_VAL()) ) {
					InterUtil.checkSJZDInfo(model.getEMPLOYEE_TYPE_VAL(),model.getEMPLOYEE_TYPE(), "RYLB");
//					LogicUtil.actLog("员工信息管理", "新增员工信息(新)", "MDM", "失败",
//							"未找到匹配员工类别:"+model.getEMPLOYEE_TYPE_VAL(), "", "",	"");
//					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//					msg.setMessageText("未找到匹配员工类别:"+model.getEMPLOYEE_TYPE_VAL());
//					return msg;
				}
				
				if (!StringUtil.isEmpty(model.getPOST_LEVEL_VAL()) ) {
					InterUtil.checkSJZDInfo(model.getPOST_LEVEL_VAL(),model.getPOST_LEVEL(), "RYJB");
//					LogicUtil.actLog("员工信息管理", "新增员工信息(新)", "MDM", "失败",
//							"未找到匹配员工级别:"+model.getPOST_LEVEL_VAL(), "", "",	"");
//					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//					msg.setMessageText("未找到匹配员工级别:"+model.getPOST_LEVEL_VAL());
//					return msg;
				}
				
				if (!StringUtil.isEmpty(model.getPOSITION_NAME_VAL()) ) {
					InterUtil.checkSJZDInfo(model.getPOSITION_NAME_VAL(),model.getPOSITION_NAME(), "ZW");
//					LogicUtil.actLog("员工信息管理", "新增员工信息(新)", "MDM", "失败",
//							"未找到匹配员工职务:"+model.getPOSITION_NAME_VAL(), "", "",	"");
//					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//					msg.setMessageText("未找到匹配员工职务:"+model.getPOSITION_NAME_VAL());
//					return msg;
				}
				
				/** 验证对象 **/
				Map<String,String> params=new HashMap<String, String>();//参数
				List<String> result=new ArrayList<String>();//返回值
				List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();//查询结果
				InterUtil.clearObj(params, result, resultList);
				/**上级部门是否存在**/
				params.put("a.BMBH", model.getBELONG_CODE());
				params.put("a.BMMC", model.getBELONG_CODE_VAL());
				params.put("a.DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
				params.put("b.DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
				params.put("b.IS_DRP_LEDGER", BusinessConsts.DEL_FLAG_COMMON);
				params.put("rownum", "1");
				result.add("a.BMXXID");
				result.add("a.JGXXID");
				resultList=InterUtil.getSqlResult(params, result, " T_SYS_BMXX a left join T_SYS_JGXX b on a.JGXXID=b.JGXXID ");
				if(resultList.isEmpty()){
					LogicUtil.actLog("组织信息管理", "新增组织信息(新)", "MDM", "失败",
							"未找到匹配上级组织编码,编号："+model.getBELONG_CODE()+",名称："+model.getBELONG_CODE_VAL(), "", "",	"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("未找到匹配上级组织编码,编号："+model.getBELONG_CODE()+",名称："+model.getBELONG_CODE_VAL());
					return msg;
				}
				
				map.put("JGXXID", resultList.get(0).get("JGXXID"));//机构信息id
				map.put("BMXXID", resultList.get(0).get("BMXXID"));//部门信息id
				map.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);//删除标记
				map.put("RYZT", InterUtil.coveState(model.getUSE_STATUS()));//
				map.put("CREATOR", "MDM");//制单人
				map.put("UPDATER", "MDM");//制单人名称
				map.put("CRETIME", "sysdate");//制单时间
				addList.add(map);
			}
			boolean flag=this.batch4Update("employee.insert", addList);
			if(!flag){
				LogicUtil.actLog("员工信息管理", "新增员工信息(新)", "MDM", "SQL执行失败",
						BusinessConsts.IMPL_SQL_EXECUTE_FLASE, "", "",	"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText("新增员工信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增员工信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("员工信息管理", "新增员工信息(新)", "MDM", "失败", errorInfo,
					"", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("新增员工信息出错!");
			return msg;
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("员工信息管理", "新增员工信息(新)", "MDM", "成功", new Gson().toJson(EMPLOYEE_LIST),"","","");
		return msg;
	}
	
	
	/**
	 * 修改员工信息
	 * 
	 * @param EMPLOYEE_LIST
	 * @return
	 * @throws Exception
	 */
	public ReturnInterInfo txUpNewEmployee(InterEmployeeModel[] EMPLOYEE_LIST) {
		ReturnInterInfo msg = new ReturnInterInfo();
		if (null==EMPLOYEE_LIST||EMPLOYEE_LIST.length==0) {
			LogicUtil.actLog("用户信息管理", "修改用户信息(新)", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
		LogicUtil.actLog("员工信息管理", "修改员工信息(新)==调入成功==", "MDM", "成功", new Gson().toJson(EMPLOYEE_LIST),"","","");
//		String[] mustFld = { "EMPLOYEE_CODE", "EMPLOYEE_NAME", "SEX","PHONE","BELONG_CODE","WORK_STATUS","ID_NUMBER","OFFICE_PHONE",
//				"OFFICE_EMAIL","POST_NAME","POST_NAME","POSITION_NAME","POST_LEVEL" };
		String[] mustFld = {"EMPLOYEE_CODE"};
		try {
			if (!InterUtil.checkMustFld(mustFld, EMPLOYEE_LIST)) {
				LogicUtil.actLog("员工信息管理", "修改员工信息(新)", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
			for (int i = 0; i < EMPLOYEE_LIST.length; i++) {
				InterEmployeeModel model =EMPLOYEE_LIST[i];
				Map<String,String> map=new HashMap<String, String>();
				
				if (InterUtil.checkPrimarykeyOld("RYBH", model.getEMPLOYEE_CODE(), "T_SYS_RYXX")) {
					LogicUtil.actLog("员工信息管理", "修改员工信息(新)", "MDM", "失败",
							"员工"+model.getEMPLOYEE_CODE()+"不存在!", "", "",
							"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("员工"+model.getEMPLOYEE_CODE()+"不存在!");
					return msg;
				}
				map.put("RYBH", model.getEMPLOYEE_CODE());//人员编号
				map.put("RYBH", model.getEMPLOYEE_CODE());//人员编号
				map.put("XM", model.getEMPLOYEE_NAME());//员工名称
				String XB=model.getSEX_VAL();
				if(!"男".equals(XB)&&!"女".equals(XB)){
					LogicUtil.actLog("员工信息管理", "新增员工信息(新)", "MDM", "失败",
							"性别："+XB+"错误！", "", "",
							"");
					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
					msg.setMessageText("性别："+XB+"错误！");
					return msg;
				}
				map.put("XB", model.getSEX_VAL());//性别
				map.put("SFZH", model.getID_NUMBER());//身份证号
				map.put("ZW", model.getPOSITION_NAME_VAL());//职务
				map.put("GSDH", model.getOFFICE_PHONE());//公司电话
				map.put("SJ", model.getPHONE());//手机
				map.put("DZYJ", model.getOFFICE_EMAIL());//电子邮件
				map.put("RYZT", InterUtil.coveState(model.getUSE_STATUS()));//
				map.put("RYJB", model.getPOST_LEVEL_VAL());//人员级别
				map.put("RYLB", model.getEMPLOYEE_TYPE_VAL());//人员类别
				if (!StringUtil.isEmpty(model.getEMPLOYEE_TYPE_VAL()) ) {
					InterUtil.checkSJZDInfo(model.getEMPLOYEE_TYPE_VAL(),model.getEMPLOYEE_TYPE(), "RYLB");
//					LogicUtil.actLog("员工信息管理", "修改员工信息(新)", "MDM", "失败",
//							"未找到匹配员工类别:"+model.getEMPLOYEE_TYPE_VAL(), "", "",	"");
//					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//					msg.setMessageText("未找到匹配员工类别:"+model.getEMPLOYEE_TYPE_VAL());
//					return msg;
				}
				
				if (!StringUtil.isEmpty(model.getPOST_LEVEL_VAL()) ) {
					InterUtil.checkSJZDInfo(model.getPOST_LEVEL_VAL(),model.getPOST_LEVEL(), "RYJB");
//					LogicUtil.actLog("员工信息管理", "修改员工信息(新)", "MDM", "失败",
//							"未找到匹配员工级别:"+model.getPOST_LEVEL_VAL(), "", "",	"");
//					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//					msg.setMessageText("未找到匹配员工级别:"+model.getPOST_LEVEL_VAL());
//					return msg;
				}
				
				if (!StringUtil.isEmpty(model.getPOSITION_NAME_VAL()) ) {
					InterUtil.checkSJZDInfo(model.getPOSITION_NAME_VAL(),model.getPOSITION_NAME(), "ZW");
//					LogicUtil.actLog("员工信息管理", "修改员工信息(新)", "MDM", "失败",
//							"未找到匹配员工职务:"+model.getPOSITION_NAME_VAL(), "", "",	"");
//					msg.setMessageType(BusinessConsts.N_INTER_FALSE);
//					msg.setMessageText("未找到匹配员工职务:"+model.getPOSITION_NAME_VAL());
//					return msg;
				}
				
				
				/** 验证对象 **/
				Map<String,String> params=new HashMap<String, String>();//参数
				List<String> result=new ArrayList<String>();//返回值
				List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();//查询结果
				
				if(!StringUtil.isEmpty(model.getBELONG_CODE_VAL())){
					InterUtil.clearObj(params, result, resultList);
					/**上级部门是否存在**/
					params.put("BMBH", model.getBELONG_CODE());
					params.put("BMMC", model.getBELONG_CODE_VAL());
					params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
					params.put("rownum", "1");
					result.add("BMXXID");
					result.add("JGXXID");
					resultList=InterUtil.getSqlResult(params, result, " T_SYS_BMXX ");
					if(resultList.isEmpty()){
						LogicUtil.actLog("组织信息管理", "修改组织信息(新)", "MDM", "失败",
								"未找到匹配上级组织编码,编号："+model.getBELONG_CODE()+",名称："+model.getBELONG_CODE_VAL(), "", "",	"");
						msg.setMessageType(BusinessConsts.N_INTER_FALSE);
						msg.setMessageText("未找到匹配上级组织编码,编号："+model.getBELONG_CODE()+",名称："+model.getBELONG_CODE_VAL());
						return msg;
					}
					map.put("JGXXID", resultList.get(0).get("JGXXID"));//机构信息id
					map.put("BMXXID", resultList.get(0).get("BMXXID"));//部门信息id
				}
				map.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);//删除标记
				map.put("RYZT", InterUtil.coveState(model.getUSE_STATUS()));//
				map.put("CREATOR", "MDM");//制单人
				map.put("UPDATER", "MDM");//制单人名称
				map.put("CRETIME", "sysdate");//制单时间
				addList.add(map);
			}
			boolean flag=this.batch4Update("employee.updateByNo", addList);
			if(!flag){
				LogicUtil.actLog("员工信息管理", "修改员工信息(新)", "MDM", "SQL执行失败",
						BusinessConsts.IMPL_SQL_EXECUTE_FLASE, "", "",	"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText("修改员工信息失败!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改员工信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("员工信息管理", "修改员工信息(新)", "MDM", "失败", errorInfo,
					"", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("修改员工信息出错!");
			return msg;
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("员工信息管理", "修改员工信息(新)", "MDM", "成功", new Gson().toJson(EMPLOYEE_LIST),"","","");
		return msg;
	}
	
	/**
	 * 新增微信订单
	 * @param AdvcOrderModel
	 * @return
	 */
	public ReturnInterInfo txAddAdvcOrderForWeChat(AdvcOrderForWeChatModel model) throws ServiceException{
		ReturnInterInfo msg = new ReturnInterInfo();
		AdvcorderService service = (AdvcorderService) SpringContextUtil.getBean("advcorderService");
		if (null==model) {
			LogicUtil.actLog("微信订单", "新增微信预订单", "MDM", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setMessageType(BusinessConsts.N_INTER_FALSE);
			msg.setMessageText("参数为空!");
			return msg;
		}
		LogicUtil.actLog("微信订单", "新增微信预订单==调入成功==", "MDM", "成功", new Gson().toJson(model),"","","");
		String[] mustFld = { "TERM_NO", "SALE_DATE", "SALE_PSON_NO","CUST_NAME","TEL","RECV_ADDR","ADVC_AMOUNT","PAYABLE_AMOUNT",
				"CRE_NO","CRE_TIME","FROM_BILL_NO" };
		String errorFlag="0";
		boolean excepFlag=true;
		try{
			if (!InterUtil.checkMustFMd(mustFld, model)) {
				LogicUtil.actLog("微信订单", "新增微信预订单", "MDM", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "",
						"");
				msg.setMessageType(BusinessConsts.N_INTER_FALSE);
				msg.setMessageText(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			String ADVC_ORDER_ID=StringUtil.uuid32len();
			//查询信息
			Map<String,String> param=new HashMap<String, String>();
			//查询终端信息
			param.put("SelSQL", "select t.TERM_ID,t.TERM_NO, t.TERM_NAME,t.CHANN_ID_P,t.CHANN_NO_P from BASE_TERMINAL t where t.TERM_NO='"+model.getTERM_NO()+"'");
			Map<String,String> termMap=(Map<String, String>) this.load("sqlcom.query", param);
			if(null==termMap||termMap.isEmpty()){
				throw new ServiceException("没有查到门店信息！编号："+model.getTERM_NO());
			}
			
			param.clear();
			StringBuffer sql=new StringBuffer();
			sql.append("select ")
			   .append("t.RYXXID, t.RYBH, t.XM, t.BMXXID, b.BMMC,c.JGXXID,c.JGMC ")
			   .append("from T_SYS_RYXX t  left join t_sys_bmxx b on t.bmxxid = b.bmxxid left join t_sys_jgxx c  on c.jgxxid = t.jgxxid ")
			   .append("where  t.rybh='"+model.getCRE_NO()+"'");
			//查询制单人员信息
			param.put("SelSQL", sql.toString()); 
	 
				Map<String,String> employeeMap= (Map<String, String>) this.load("sqlcom.query", param);
			if(null==employeeMap||employeeMap.isEmpty()){
				throw new ServiceException("没有查到制单员信息！编号："+model.getCRE_NO());
			}
				
			param.clear();
			//查询销售人员信息
			param.put("SelSQL", "select t.RYXXID, t.RYBH, t.XM ,t.JGXXID from T_SYS_RYXX t where  t.rybh='"+model.getSALE_PSON_NO()+"'");
			Map<String,String> saleMap= (Map<String, String>) this.load("sqlcom.query", param);
			if(null==employeeMap||employeeMap.isEmpty()){
				throw new ServiceException("没有查到门店导购员信息！编号："+model.getSALE_PSON_NO());
			}
			//验证制单人员，门店，销售人员是否存在关系
			if(!employeeMap.get("JGXXID").equals(termMap.get("CHANN_ID_P"))){
				throw new ServiceException("制单人员与终端不在同一机构");
			}
			if(!employeeMap.get("JGXXID").equals(saleMap.get("JGXXID"))){
				throw new ServiceException("制单人员与销售人员不在同一机构");
			}
				
			//验证销售日期
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			Date d=fm.parse(model.getSALE_DATE());
			errorFlag="1";
			d=fm.parse(model.getCRE_TIME());
			errorFlag="2";
			boolean isMonthAcc = LogicUtil.isMonthAcc(employeeMap.get("JGXXID"),model.getSALE_DATE());
			if(isMonthAcc){
				throw new ServiceException("销售结算日期:"+model.getSALE_DATE()+"已月结不能保存");
			}
//				
//			//验证销售日期
			String currentDate=service.getDate();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = df.parse(model.getSALE_DATE());    
			Date d2 = df.parse(currentDate);    
			long diff = d1.getTime() - d2.getTime();    
			long days = diff / (1000 * 60 * 60 * 24);
			if(days>0){
				throw new ServiceException("销售日期不能大于当前日期!");
			}
				
			//生成预订单编号
			String prefix = termMap.get("CHANN_NO_P");//预订单 单头
			int length = BusinessConsts.ADVC_ORDER_NO_LENGTH;//预订单编号段长
			UserBean userBean = new UserBean(null,null,null);
			userBean.setLoginZTXXID(employeeMap.get("JGXXID"));
			String ADVC_ORDER_NO = LogicUtil.getBillNo("ADVC_ORDER_NO","DRP_ADVC_ORDER",prefix,length,userBean);
			Map<String,String> map=new HashMap<String, String>();
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("ADVC_ORDER_NO", ADVC_ORDER_NO);
			map.put("CONTRACT_NO", ADVC_ORDER_NO);
			map.put("TERM_ID", termMap.get("TERM_ID"));
			map.put("TERM_NO", termMap.get("TERM_NO"));
			map.put("TERM_NAME", termMap.get("TERM_NAME"));
			map.put("SALE_DATE", model.getSALE_DATE());
			map.put("SALE_PSON_ID", saleMap.get("RYXXID"));
			map.put("SALE_PSON_NAME", saleMap.get("XM"));
			map.put("CUST_NAME", model.getCUST_NAME());
			map.put("TEL", model.getTEL());
			map.put("RECV_ADDR", model.getRECV_ADDR());
			map.put("ADVC_AMOUNT", model.getADVC_AMOUNT());
			map.put("PAYABLE_AMOUNT", model.getPAYABLE_AMOUNT());
			map.put("TOTAL_AMOUNT", model.getTOTAL_AMOUNT());
			double DISCOUNT_AMOUNT=Double.parseDouble(model.getTOTAL_AMOUNT())-Double.parseDouble(model.getPAYABLE_AMOUNT());
			map.put("DISCOUNT_AMOUNT", DISCOUNT_AMOUNT+"");
			map.put("STATE", BusinessConsts.PASS);
			map.put("REMARK", model.getREMARK());
			map.put("CREATOR", employeeMap.get("RYXXID"));
			map.put("CRE_NAME", employeeMap.get("XM"));
			map.put("CRE_TIME", model.getCRE_TIME());
			map.put("DEPT_ID", employeeMap.get("BMXXID"));
			map.put("DEPT_NAME", employeeMap.get("BMMC"));
			map.put("ORG_ID", employeeMap.get("JGXXID"));
			map.put("ORG_NAME", employeeMap.get("JGMC"));
			map.put("LEDGER_ID", employeeMap.get("JGXXID"));
			map.put("LEDGER_NAME", employeeMap.get("JGMC"));
			map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			map.put("STTLE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);// 结算标记
			map.put("DEAL_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);// 处理标记
			map.put("FROM_BILL_NO", model.getFROM_BILL_NO());
			map.put("BILL_TYPE", "终端销售");
			map.put("PAYED_TOTAL_AMOUNT", model.getADVC_AMOUNT());
			
			// 订金金额(ADVC_AMOUNT)>=应收总额(PAYABLE_AMOUNT)*0.5 的情况下，业绩日期(PFMC_DATE)=
			// 销售日期(SALE_DATE) ，否则为空
			double ADVC_AMOUNT = Double.parseDouble(String.valueOf(model.getADVC_AMOUNT()));
			double PAYABLE_AMOUNT = Double.parseDouble(String.valueOf(model.getPAYABLE_AMOUNT()));
			if (ADVC_AMOUNT >= (PAYABLE_AMOUNT * 0.5)) {
				map.put("PFMC_DATE", model.getSALE_DATE());
			} 
			insert("Advcorder.insertForWeChat", map);
			//新增
			Map<String, String> traceMap = new HashMap<String, String>();
			traceMap.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
			traceMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			traceMap.put("ACTION", "预订单录入");
			traceMap.put("REMARK", "已生成");
			traceMap.put("ACTOR", employeeMap.get("XM"));
			traceMap.put("BILL_NO", ADVC_ORDER_NO);
			this.insert("Advcorder.insertTrace", traceMap);
			if(null!=model.getDtlList()&&!model.getDtlList().isEmpty()){
				//新增明细
				insertAdvcDtl(model, ADVC_ORDER_ID, employeeMap.get("JGXXID"));
				//把明细最近交货日期存入主表
				Map<String,String> dateMap=new HashMap<String, String>();
				dateMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
				dateMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				this.update("Advcorder.addORDER_RECV_DATE",dateMap);
			}
			if(null!=model.getPaymentList()&&!model.getPaymentList().isEmpty()){
				//新增付款明细
				insertPayment(model, ADVC_ORDER_ID);
			}
			
			//生成付款单
			insStateMents(ADVC_ORDER_ID,map);
			
			//验证
			checkAdvcOrderInfo(model, ADVC_ORDER_ID, employeeMap.get("JGXXID"));
			
		}catch(ServiceException s){
			throw new ServiceException(s.getMessage());
		} catch (Exception ex) {
			if("0".equals(errorFlag)){
				throw new ServiceException("销售日期格式错误，请重新编辑!");
			}else if("1".equals(errorFlag)){
				throw new ServiceException("制单时间格式错误，请重新编辑!");
			}
			throw new ServiceException("接口执行失败,请联系DM系统人员");
		}
		msg.setMessageType(BusinessConsts.N_INTER_SUCCESS);
		msg.setMessageText("");
		LogicUtil.actLog("微信订单", "新增微信预订单", "MDM", "成功", new Gson().toJson(model),"","","");
		return msg;
	}
	
	/**
	 * 新增预订单明细
	 * @param model
	 * @param ADVC_ORDER_ID
	 * @param LEDGER_ID
	 */
	public void insertAdvcDtl(AdvcOrderForWeChatModel model,String ADVC_ORDER_ID,String LEDGER_ID) throws ServiceException{
		//新增明细
		List<AdvcOrderDtlForWeChatModel> dtlList=model.getDtlList();
		List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
		for (int i = 0; i < dtlList.size(); i++) {
			AdvcOrderDtlForWeChatModel dtlModel =dtlList.get(i);
			Map<String,String> dtlMap=new HashMap<String, String>();
			dtlMap.put("ADVC_ORDER_DTL_ID", StringUtil.uuid32len());
			dtlMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			dtlMap.put("PRD_NO", dtlModel.getPRD_NO());
			
			double DECT_RATE = Double.parseDouble(String.valueOf(dtlModel.getDECT_RATE()));
			double PRICE=Double.parseDouble(String.valueOf(dtlModel.getPRICE()));
			double DECT_PRICE=Double.parseDouble(String.valueOf(dtlModel.getDECT_PRICE()));
			double ORDER_NUM=Double.parseDouble(String.valueOf(dtlModel.getORDER_NUM()));
			double PAYABLE_AMOUNT=Double.parseDouble(String.valueOf(dtlModel.getPAYABLE_AMOUNT()));
			
			BigDecimal convert = new  BigDecimal(DECT_PRICE/PRICE);
			double check_DECT_RATE=convert.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			if(check_DECT_RATE!=DECT_RATE){
				throw new ServiceException("金额计算错误：  单价:"+PRICE+",折扣率:"+DECT_RATE+",折扣单价:"+DECT_PRICE);
			}else if(ORDER_NUM*DECT_PRICE!=PAYABLE_AMOUNT){
				throw new ServiceException("金额计算错误：  订货数量:"+ORDER_NUM+",折扣单价:"+DECT_PRICE+",应收金额:"+PAYABLE_AMOUNT);
			}
			
			
			dtlMap.put("PRICE", dtlModel.getPRICE());
			dtlMap.put("DECT_RATE", dtlModel.getDECT_RATE());
			dtlMap.put("DECT_PRICE", dtlModel.getDECT_PRICE());
			dtlMap.put("ORDER_NUM", dtlModel.getORDER_NUM());
			dtlMap.put("PAYABLE_AMOUNT", dtlModel.getPAYABLE_AMOUNT());
			dtlMap.put("REMARK", dtlModel.getREMARK());
			dtlMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			dtlMap.put("SPCL_TECH_REMARK", dtlModel.getSPCL_TECH_REMARK());
			dtlMap.put("FROM_BILL_ID", dtlModel.getFROM_BILL_ID());
			dtlMap.put("LEDGER_ID", LEDGER_ID);
			dtlMap.put("STATE", "正常");
			//生成特殊工艺
			if(BusinessConsts.YJLBJ_FLAG_TRUE.equals(dtlModel.getSPCL_TECH_FLAG())&&!StringUtil.isEmpty(dtlModel.getSPCL_TECH_REMARK())){
				//查询是否已存在特殊工艺
				String SPCL_TECH_ID=(String) this.load("TechorderManager.getSpclIdByRemark", dtlMap);
				if(StringUtil.isEmpty(SPCL_TECH_ID)){
					SPCL_TECH_ID=insertSpcl(dtlModel,LEDGER_ID);
				}
				dtlMap.put("SPCL_TECH_ID", SPCL_TECH_ID);
			}
			dtlMap.put("PRD_TYPE", converPrdType(dtlModel.getPRD_TYPE()));
			dtlMap.put("IS_FREEZE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
			dtlMap.put("ORDER_RECV_DATE", dtlModel.getORDER_RECV_DATE());
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d=fm.parse(dtlModel.getORDER_RECV_DATE());
			} catch (Exception e) {
				throw new ServiceException("预计交货日期格式错误！");
			}
			addList.add(dtlMap);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Advcorder.insertChldForWeChat", addList);
		}
		
	}
	/**
	 * 新增预订单付款明细
	 * @param model
	 * @param ADVC_ORDER_ID
	 */
	public void insertPayment(AdvcOrderForWeChatModel model,String ADVC_ORDER_ID){
		List<AdvcOrderPaymentForWeChatModel> payList=model.getPaymentList();
		List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
		for (int i = 0; i < payList.size(); i++) {
			Map<String,String> map=new HashMap<String, String>();
			AdvcOrderPaymentForWeChatModel paymentModel=payList.get(i);
			map.put("PAYMENT_DTL_ID", StringUtil.uuid32len());
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("PAY_TYPE", paymentModel.getPAY_TYPE());
			map.put("PAY_INFO", paymentModel.getPAY_INFO());
			map.put("PAY_AMONT", paymentModel.getPAY_AMONT());
			map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			map.put("PAY_BILL_NO", paymentModel.getPAY_BILL_NO());
			addList.add(map);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Advcorder.insertGchld", addList);
			
		}
	}
	
	/**
	 * 新增特殊工艺
	 * @param model
	 * @param LEDGER_ID
	 * @return
	 */
	public String insertSpcl(AdvcOrderDtlForWeChatModel model,String LEDGER_ID){
		Map<String,String> spclMap=new HashMap<String, String>();
		String SPCL_TECH_ID=StringUtil.uuid32len();
		spclMap.put("SPCL_TECH_ID", SPCL_TECH_ID);
		spclMap.put("PRD_NO", model.getPRD_NO());
		spclMap.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		spclMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		spclMap.put("PRICE", model.getPRICE());
		spclMap.put("DECT_RATE", model.getDECT_RATE());
		spclMap.put("DECT_PRICE", model.getDECT_PRICE());
		spclMap.put("TECH_MULT", "0");
		spclMap.put("TECH_AMOUNT", "0");
		spclMap.put("TECH_CHECK_PRICE", model.getDECT_PRICE());
		spclMap.put("SPCL_TECH_FLAG", "1");
		spclMap.put("SPCL_SPEC_REMARK", "一般特殊");
		spclMap.put("SPCL_DTL_REMARK", "一般特殊:"+model.getSPCL_TECH_REMARK());
		spclMap.put("DM_SPCL_TECH_NO", LogicUtil.getBmgz("DRP_SPCL_TECH_NO"));
		spclMap.put("LEDGER_ID", LEDGER_ID);
		spclMap.put("TECH_NO_EDIT_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		spclMap.put("SPCL_DTL_REMARK_CHK", "一般特殊工艺描述:"+model.getSPCL_TECH_REMARK());
		//明细
		Map<String,String> dtlMap=new HashMap<String, String>();
		dtlMap.put("SPCL_TECH_DTL_ID", StringUtil.uuid32len());
		dtlMap.put("SPCL_TECH_ID", SPCL_TECH_ID);
		dtlMap.put("SPCL_TECH_TYPE", "一般特殊工艺描述");
		dtlMap.put("REMARK", model.getSPCL_TECH_REMARK());
		dtlMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		this.insert("TechorderManager.insertForWeChat", spclMap);
		this.insert("TechorderManager.insertChld", dtlMap);
		return SPCL_TECH_ID;
	}
	
	/**
	 * 校验预订单信息
	 * @param model
	 * @param ADVC_ORDER_ID
	 * @param LEDGER_ID
	 * @return
	 * @throws ServiceException
	 */
	public boolean checkAdvcOrderInfo(AdvcOrderForWeChatModel model,String ADVC_ORDER_ID,String LEDGER_ID) throws ServiceException{
		//验证预订单明细是否存在未找到货品
		List<String> prdList=this.findList("Advcorder.getPrdNoByAdvcId", ADVC_ORDER_ID);
		List<AdvcOrderDtlForWeChatModel> dtlList=model.getDtlList();
		StringBuffer check=new StringBuffer();
		if(dtlList.size()!=prdList.size()){
			check.append("存在未找到货品：");
			for (int i = 0; i < dtlList.size(); i++) {
				boolean flag=true;
				for (int j = 0; j < prdList.size(); j++) {
					if(dtlList.get(i).getPRD_NO().equals(prdList.get(j))){
						flag=false;
					}
				}
				if(flag){
					check.append(dtlList.get(i).getPRD_NO()).append(",");
				}
			}
			check=InterUtil.replaceUpSql(check);
			throw new ServiceException(check.toString());
		}
		
		//验证是否所有付款方式都存在
		Map<String,String> map=new HashMap<String, String>();
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("LEDGER_ID", LEDGER_ID);
		List<String> paymentCheck=this.findList("Advcorder.checkPaymentByAdvcId", map);
		if(!paymentCheck.isEmpty()){
			check.append("存在未找到付款方式：");
			for (int i = 0; i < paymentCheck.size(); i++) {
				check.append(paymentCheck.get(i)).append(",");
			}
			check=InterUtil.replaceUpSql(check);
			throw new ServiceException(check.toString());
		}
		
		//验证金额
		map=new HashMap<String, String>();
		map=(Map<String, String>) this.load("Advcorder.checkAmountByAdvcId", ADVC_ORDER_ID);
		if(!String.valueOf(map.get("PAY_AMONT")).equals(String.valueOf(map.get("ADVC_AMOUNT")))){
			throw new ServiceException("付款明细金额总和与订单订金不等");
		}
		if(!String.valueOf(map.get("PAYABLE_AMOUNT")).equals(String.valueOf(map.get("DTL_PAYABLE_AMOUNT")))){
			throw new ServiceException("明细应收金额总和与订单应收金额不等");
		}
		if(!String.valueOf(map.get("TOTAL_AMOUNT")).equals(String.valueOf(map.get("DTL_TOTAL_PAYABLE_AMOUNT")))){
			throw new ServiceException("明细总金额与订单总金额不等");
		}
		return true;
	}
	/**
	 * 生成付款单
	 * @param advcOrderId
	 * @param advcMap
	 */
	public void  insStateMents(String advcOrderId,Map<String,String> advcMap) {
		// 结算单主表map
		Map<String, String> params = new HashMap<String, String>();
		params.put("ADVC_ORDER_ID", advcOrderId);
		Map<String, String> param = new HashMap<String, String>();
		String STATEMENTS_ID = StringUtil.uuid32len();
		String STATEMENTS_NO=LogicUtil.getBmgz("DRP_STATEMENTS_NO");
		param.put("STATEMENTS_ID", STATEMENTS_ID);
		param.put("STATEMENTS_NO", STATEMENTS_NO);
		param.put("ADVC_ORDER_ID", advcOrderId);
		param.put("CREATOR", advcMap.get("CREATOR"));
		param.put("CRE_NAME", advcMap.get("CRE_NAME"));
		param.put("CRE_TIME", advcMap.get("CRE_TIME"));
		param.put("DEPT_ID", advcMap.get("DEPT_ID"));
		param.put("DEPT_NAME", advcMap.get("DEPT_NAME"));
		param.put("ORG_ID", advcMap.get("ORG_ID"));
		param.put("ORG_NAME", advcMap.get("ORG_NAME"));
		param.put("LEDGER_ID", advcMap.get("LEDGER_ID"));
		param.put("LEDGER_NAME", advcMap.get("LEDGER_NAME"));
		param.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		param.put("STATE", "已结算");
		param.put("BILL_TYPE", "订金");
		insert("Advcorder.insertSTATEMENTS", param);
		
		//获取预定单订金金额
//		String amount=StringUtil.nullToSring(this.load("Advcorder.getAmount",advcOrderId));
		//新增预订单付款记录
//		String remark="提交/待确认预订单，生成客户收款单";
//		boolean flag =LogicUtil.instAdvcAmountNotes(userBean, advcOrderId, amount, STATEMENTS_NO, BusinessConsts.YJLBJ_FLAG_FALSE,remark );
//		if(!flag){
//			throw new ServiceException("审核失败！生成预订单记录失败");
//		}
		// 结算单货品明细list
		List<Map<String, String>> chldList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> list = findList("Advcorder.queryChldByFkId",params);
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> model = list.get(i);
			Map<String, String> chldMap = new HashMap<String, String>();
			chldMap.putAll(model);
			chldMap.put("STATEMENTS_DTL_ID", StringUtil.uuid32len());
			chldMap.put("STATEMENTS_ID", STATEMENTS_ID);
			chldList.add(chldMap);
		}
		// 结算单订金付款明细list
		List<Map<String, String>> gchldList = new ArrayList<Map<String, String>>();
		list = findList("Advcorder.queryGchldByFkId", params);
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> model = list.get(i);
			Map<String, String> gchldMap = new HashMap<String, String>();
			gchldMap.putAll(model);
			gchldMap.put("STATEMENTS_PAYMENT_DTL_ID", StringUtil.uuid32len());
			gchldMap.put("STATEMENTS_ID", STATEMENTS_ID);
			gchldList.add(gchldMap);
		}
		if (!chldList.isEmpty()) {
			this.batch4Update("Advcorder.insertSTATEMENTS_DTL", chldList);
		}
		if (!gchldList.isEmpty()) {
			this.batch4Update("Advcorder.insertSTATEMENTS_PAYMENT_DTL",
					gchldList);
		}
		
		}
	/**
	 * 转换货品类型
	 * @param PRD_TYPE
	 * @return
	 * @throws Exception
	 */
	public static String converPrdType(String PRD_TYPE) throws ServiceException {
		if (null == PRD_TYPE || "".equals(PRD_TYPE)) {
			PRD_TYPE = "-1";
		}
		int prdType=0;
		try {
			prdType = Integer.parseInt(PRD_TYPE);
		} catch (Exception e) {
			throw new ServiceException("货品类型"+PRD_TYPE+"有误！");
		}
		
		switch (prdType) {
		case 0:
			return "销售货品";
		case 1:
			return "赠品";
		default:
			return "销售货品";
		}
	}
}
