package com.hoperun.drp.finance.advcInvoice.service.impl;

/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvcorderServiceImpl
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.finance.advcInvoice.service.AdvcInvoiceService;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModel;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelTrace;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl *@func *@version 1.1 *@author lyg *@createdate 2013-08-11
 * 17:17:29
 */
public class AdvcInvoiceServiceImpl extends BaseService implements AdvcInvoiceService {
	private Logger logger = Logger.getLogger(AdvcInvoiceServiceImpl.class);

	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("AdvcInvoice.pageQuery", "AdvcInvoice.pageCount",
				params, pageNo);
	}


	/**
	 * * update data * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String, String> params) {
		return update("AdvcInvoice.updateById", params);
	}


	/**
	 * @详细
	 * @param param
	 *            ADVC_ORDER_ID
	 * @param param
	 *            the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String param) {
		return (Map<String, String>) load("AdvcInvoice.loadById", param);
	}


	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * 
	 * @return the list< new master slavemx model>
	 */
	@SuppressWarnings("unchecked")
	public List<AdvcorderModelChld> childQuery(String ADVC_ORDER_ID) {
		Map params = new HashMap();
		params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		return this.findList("AdvcInvoice.queryChldByFkId", params);
	}

	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param PAYMENT_DTL_IDs
	 *            the PAYMENT_DTL_IDs
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdvcorderModelGchld> loadGchilds(Map<String, String> params) {
		return findList("AdvcInvoice.loadGchldByIds", params);
	}


	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param ADVC_ORDER_DTL_IDs
	 *            the ADVC_ORDER_DTL_IDs
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdvcorderModelChld> loadChilds(Map<String, String> params) {
		return findList("AdvcInvoice.loadChldByIds", params);
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * 
	 * @return the list< new master slavemx model>
	 */
	@SuppressWarnings("unchecked")
	public List<AdvcorderModelGchld> gchildQuery(String ADVC_ORDER_ID) {
		Map params = new HashMap();
		params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("AdvcInvoice.queryGchldByFkId", params);
	}

	/**
	 * 根据主表Id查询跟踪记录
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<AdvcorderModelTrace> traceQuery(String ADVC_ORDER_ID) {
		return this.findList("AdvcInvoice.queryTraceByFkId", ADVC_ORDER_ID);
	}
	/**
	 * 预订单开票
	 * @param ADVC_ORDER_ID
	 */
	public void commitInvoice(Map<String,String> params){
		this.update("AdvcInvoice.updateById", params);
	}

}