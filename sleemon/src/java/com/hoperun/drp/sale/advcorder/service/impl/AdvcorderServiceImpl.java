package com.hoperun.drp.sale.advcorder.service.impl;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.InterUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.SpringContextUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModel;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelTrace;
import com.hoperun.drp.sale.advcorder.service.AdvcorderService;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.BmgzService;

/**
 * *@serviceImpl *@func *@version 1.1 *@author lyg *@createdate 2013-08-11
 * 17:17:29
 */
public class AdvcorderServiceImpl extends BaseService implements
		AdvcorderService {
	private Logger logger = Logger.getLogger(AdvcorderServiceImpl.class);
	private String NullFlag="tempNull";
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Advcorder.pageQuery", "Advcorder.pageCount",
				params, pageNo);
	}

	/**
	 * * 增加 * @param params
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String, String> params) {
		insert("Advcorder.insert", params);
		return true;
	}

	/**
	 * @删除
	 * @param ADVC_ORDER_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map<String, String> params) {
		//验证状态
		String newState= (String) this.load("Advcorder.getAdvcState",params.get("ADVC_ORDER_ID"));
		if(!"未提交".equals(newState)&&!"退回".equals(newState)){
			throw new ServiceException("对不起，该单据当前状态为："+newState+",不能删除！");
		}
		// 删除父
		update("Advcorder.delete", params);
		// 删除子表
		return update("Advcorder.delChldByFkId", params);
	}

	/**
	 * * update data * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String, String> params) {
		return update("Advcorder.updateById", params);
	}

	/**
	 * @编辑
	 * @Description :
	 * @param ADVC_ORDER_ID
	 * @param AdvctoorderModel
	 * @param userBean
	 *            .
	 * 
	 * 
	 * @return true, if tx txEdit
	 * @throws ParseException 
	 */
	public void txEdit(String ADVC_ORDER_ID, AdvcorderModel model,
			List<AdvcorderModelChld> chldList, UserBean userBean,String oldSaleDate,String updateFlag) throws ParseException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("TERM_ID", model.getTERM_ID());// 终端ID
		params.put("TERM_NO", model.getTERM_NO());// 终端编号
		params.put("TERM_NAME", model.getTERM_NAME());// 终端名称
		String SALE_DATE = model.getSALE_DATE();
		params.put("SALE_DATE", SALE_DATE);// 销售日期
		if(!SALE_DATE.equals(oldSaleDate)){
			boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),SALE_DATE);
			if(isMonthAcc){
				throw new ServiceException("销售结算日期:"+SALE_DATE+"已月结不能保存");
			}
		}
		String currentDate=this.getDate();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = df.parse(SALE_DATE);    
		Date d2 = df.parse(currentDate);    
		long diff = d1.getTime() - d2.getTime();    
		long days = diff / (1000 * 60 * 60 * 24);
		if(days>0){
			throw new ServiceException("销售日期不能大于当前日期!");
		}
		params.put("SALE_PSON_ID", model.getSALE_PSON_ID());// 业务员id
		params.put("SALE_PSON_NAME", model.getSALE_PSON_NAME());// 业务员名称
		params.put("CUST_NAME", model.getCUST_NAME());// 客户名称
		params.put("DISCOUNT_AMOUNT", model.getDISCOUNT_AMOUNT());//优惠金额
		params.put("TOTAL_AMOUNT", model.getTOTAL_AMOUNT());//总金额
		params.put("TEL", model.getTEL());// 电话
		params.put("PROMOTE_ID", model.getPROMOTE_ID());
		params.put("PROMOTE_NO", model.getPROMOTE_NO());
		params.put("PROMOTE_NAME", model.getPROMOTE_NAME());
		params.put("CUSTOMER_AGE",model.getCUSTOMER_AGE());// 消费者年龄
		params.put("CUSTOMER_SOURCE", model.getCUSTOMER_SOURCE());// 客户来源
		params.put("CUSTOMER_DEMAND", model.getCUSTOMER_DEMAND());// 客户需求
		// 订金金额(ADVC_AMOUNT)>=应收总额(PAYABLE_AMOUNT)*0.5 的情况下，业绩日期(PFMC_DATE)=
		// 销售日期(SALE_DATE) ，否则为空
		double ADVC_AMOUNT = model.getADVC_AMOUNT();
		double PAYABLE_AMOUNT = model.getPAYABLE_AMOUNT();
		if (ADVC_AMOUNT >= (PAYABLE_AMOUNT * 0.5)) {
			params.put("PFMC_DATE", model.getSALE_DATE());
		} else {
			params.put("PFMC_DATE1", "kong");
		}
		params.put("ADVC_AMOUNT", ADVC_AMOUNT + "");// 订金金额
		if(StringUtil.isEmpty(updateFlag)&&!"1".equals(updateFlag)){
			params.put("PAYED_TOTAL_AMOUNT", ADVC_AMOUNT + "");//已付款金额
		}
		params.put("PAYABLE_AMOUNT", PAYABLE_AMOUNT + "");// 应收总额
		params.put("RECV_ADDR", model.getRECV_ADDR());// 收货地址
		params.put("REMARK", model.getREMARK());// 备注
		params.put("BILL_TYPE", "终端销售");// 单据类型
		if (StringUtil.isEmpty(ADVC_ORDER_ID)) {
//			String ADVC_ORDER_NO = LogicUtil.getBmgz("DRP_ADVC_ORDER_NO");// 预订单编号
//			String prefix = BusinessConsts.ADVC_ORDER_NO_PREFIX;//预订单 单头
//			modify by zzb 2014-7-29 
			String prefix = userBean.getCHANN_NO();//预订单 单头
			int length = BusinessConsts.ADVC_ORDER_NO_LENGTH;//预订单编号段长
			String ADVC_ORDER_NO = LogicUtil.getBillNo("ADVC_ORDER_NO","DRP_ADVC_ORDER",prefix,length,userBean);
			String CONTRACT_NO=model.getCONTRACT_NO();
			if(StringUtil.isEmpty(CONTRACT_NO)){
				CONTRACT_NO=ADVC_ORDER_NO;
			}
			params.put("CONTRACT_NO", CONTRACT_NO);
			params.put("ADVC_ORDER_NO", ADVC_ORDER_NO);
			ADVC_ORDER_ID = StringUtil.uuid32len();
			params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);// 预订单id
			params.put("CRE_NAME", userBean.getXM());// 制单人名称
			params.put("CREATOR", userBean.getXTYHID());// 制单人ID
			params.put("DEPT_ID", userBean.getBMXXID());// 制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());// 制单部门名称
			params.put("ORG_ID", userBean.getJGXXID());// 制单机构ID
			params.put("ORG_NAME", userBean.getJGMC());// 制单机构名称
			params.put("CRE_TIME", "数据库时间");// 制单时间
			params.put("LEDGER_ID", userBean.getLoginZTXXID());// 帐套ID
			params.put("LEDGER_NAME", userBean.getLoginZTMC());// 帐套名称
			params.put("STATE", BusinessConsts.UNCOMMIT);// 状态
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);// 删除标记
			params.put("STTLE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);// 结算标记
			params.put("DEAL_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);// 处理标记
			txInsert(params);
			Map<String, String> map = new HashMap<String, String>();
			map.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("ACTION", "预订单录入");
			map.put("REMARK", "已生成");
			map.put("ACTOR", userBean.getXM());
			map.put("BILL_NO", ADVC_ORDER_NO);
			this.insert("Advcorder.insertTrace", map);
		} else {
			String CONTRACT_NO=model.getCONTRACT_NO();
			if(StringUtil.isEmpty(CONTRACT_NO)){
				CONTRACT_NO=model.getADVC_ORDER_NO();
			}
			params.put("CONTRACT_NO", CONTRACT_NO);
			params.put("UPD_NAME", userBean.getXM());// 更新人名称
			params.put("UPDATOR", userBean.getXTYHID());// 更新人ID
			params.put("UPD_TIME", "数据库时间");// 更新时间
			params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);// 预订单ID
			int count=getSendAdvcNum(ADVC_ORDER_ID);
			if(count>0){
//				throw new ServiceException("对不起，您所选的预订单已存在发货申请单，不能变更客户信息 !");
				throw new ServiceException("对不起，您所选的预订单已存在预订单出库单或发货申请单，不能变更客户信息 !");
			}
			txUpdateById(params);
			clearCaches(ADVC_ORDER_ID);
		}
		// 子表信息保存
		if (null != chldList && !chldList.isEmpty()) {
			txChildEdit(ADVC_ORDER_ID, chldList, userBean,updateFlag);
		}else{
			addORDER_RECV_DATE(ADVC_ORDER_ID);
		}
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
		return (Map<String, String>) load("Advcorder.loadById", param);
	}

	/**
	 * * 明细数据编辑
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @param modelList
	 *            the model list
	 * 
	 * @return true, if tx child edit
	 */
	@Override
	public boolean txGchildEdit(String ADVC_ORDER_ID,
			List<AdvcorderModelGchld> gchldList) {
		// 新增列表
		List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
		// 修改列表
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		for (AdvcorderModelGchld model : gchldList) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("PAY_TYPE", model.getPAY_TYPE());
			params.put("PAY_INFO", model.getPAY_INFO());
			params.put("PAY_AMONT", model.getPAY_AMONT());
			params.put("PAY_BILL_NO", model.getPAY_BILL_NO());
			params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			// 如果没有明细ID的则是新增，有的是修改
			if (StringUtil.isEmpty(model.getPAYMENT_DTL_ID())) {
				params.put("PAYMENT_DTL_ID", StringUtil.uuid32len());
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				addList.add(params);
			} else {
				params.put("PAYMENT_DTL_ID", model.getPAYMENT_DTL_ID());
				updateList.add(params);
			}
		}

		if (!updateList.isEmpty()) {
			this.batch4Update("Advcorder.updateGchldById", updateList);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Advcorder.insertGchld", addList);
		}
		return true;
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
		return this.findList("Advcorder.queryChldByFkId", params);
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
		return findList("Advcorder.loadGchldByIds", params);
	}

	/**
	 * * 子表批量删除:软删除
	 * 
	 * @param PAYMENT_DTL_IDs
	 *            the PAYMENT_DTL_IDs
	 */
	@Override
	public void txBatch4DeleteChild(String ADVC_ORDER_DTL_IDS,
			String ADVC_ORDER_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ADVC_ORDER_DTL_IDS", ADVC_ORDER_DTL_IDS);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		update("Advcorder.deleteChldByIds", params);
		addORDER_RECV_DATE(ADVC_ORDER_ID);
		upDISCOUNT_AMOUNT(ADVC_ORDER_ID);
	}

	/**
	 * * 明细数据编辑
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @param modelList
	 *            the model list
	 * 
	 * @return true, if tx gchild edit
	 * @throws ParseException 
	 */
	public boolean txChildEdit(String ADVC_ORDER_ID,
			List<AdvcorderModelChld> chldList, UserBean userBean,String updateFlag) throws ParseException {
		String SPCL_TECH_IDS = "";
		// 新增列表
		List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
		// 修改列表
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		String currentDate=this.getDate();
		String ADVC_ORDER_DTL_ID;
		for (AdvcorderModelChld model : chldList) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRD_NO", model.getPRD_NO());
			if(StringUtil.isEmpty(model.getPRD_ID())){
				throw new ServiceException("货品编号："+model.getPRD_NO()+"选择错误，请使用通用选取或自动搜索选择货品！");
			}
			params.put("PRD_ID", model.getPRD_ID());
			params.put("PRD_NAME", model.getPRD_NAME());
			params.put("PRD_SPEC", model.getPRD_SPEC());
			params.put("PRD_COLOR", model.getPRD_COLOR());
			params.put("BRAND", model.getBRAND());
			params.put("STD_UNIT", model.getSTD_UNIT());
			
			double DECT_RATE = model.getDECT_RATE();
			double PRICE=model.getPRICE();
			double DECT_PRICE=model.getDECT_PRICE();
			double ORDER_NUM=model.getORDER_NUM();
			double PAYABLE_AMOUNT=model.getPAYABLE_AMOUNT();
//			if(PRICE*DECT_RATE!=DECT_PRICE){
//				throw new ServiceException("金额计算错误，请联系管理员!");
//			}
			if(DECT_PRICE*ORDER_NUM!=PAYABLE_AMOUNT){
				throw new ServiceException("金额计算错误，请联系管理员!");
			}
			params.put("PRICE", PRICE + "");
			params.put("DECT_RATE", DECT_RATE + "");
			params.put("DECT_PRICE", DECT_PRICE + "");
			params.put("ORDER_NUM", ORDER_NUM + "");
			params.put("PAYABLE_AMOUNT", PAYABLE_AMOUNT + "");
			params.put("REMARK", model.getREMARK());
			String ORDER_RECV_DATE=model.getORDER_RECV_DATE();
			String IS_FREEZE_FLAG = model.getIS_FREEZE_FLAG();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = df.parse(ORDER_RECV_DATE);    
			Date d2 = df.parse(currentDate);    
			long diff = d1.getTime() - d2.getTime();    
			long days = diff / (1000 * 60 * 60 * 24);
//			if(days<0){
//				throw new ServiceException("交货日期不能在今天之前!");
//			}
			if(days>7&&"1".equals(IS_FREEZE_FLAG)){
				throw new ServiceException("交期超过允许最大冻结天数(7天)，不许允冻结!");
			}
			params.put("ORDER_RECV_DATE", ORDER_RECV_DATE);
			params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			params.put("PRD_TYPE", model.getPRD_TYPE());
			
			String FREEZE_NUM = model.getFREEZE_NUM() + "";
			String FREEZE_STORE_ID = model.getFREEZE_STORE_ID();
			if (IS_FREEZE_FLAG.equals("1")) {
				if ("0".equals(FREEZE_NUM)) {
					throw new ServiceException("有冻结货品未输入冻结数量，请检查后重新保存!");
				}
				if (StringUtil.isEmpty(FREEZE_STORE_ID)) {
					throw new ServiceException("有冻结货品未选择冻结库房，请检查后重新保存!");
				}
				params.put("FREEZE_STORE_ID", FREEZE_STORE_ID);
				params.put("FREEZE_STORE_NO", model.getFREEZE_STORE_NO());
				params.put("FREEZE_STORE_NAME", model.getFREEZE_STORE_NAME());
			} else {
				params.put("FREEZE_STORE_ID", "");
				params.put("FREEZE_STORE_NO", "");
				params.put("FREEZE_STORE_NAME", "");
			}
			params.put("IS_FREEZE_FLAG", IS_FREEZE_FLAG);
			params.put("FREEZE_NUM", FREEZE_NUM);
			String SPCL_TECH_ID = model.getSPCL_TECH_ID();
			if (!StringUtil.isEmpty(SPCL_TECH_ID)) {
				SPCL_TECH_IDS += "'" + SPCL_TECH_ID + "',";
			}
			params.put("SPCL_TECH_ID", SPCL_TECH_ID);
			params.put("SPCL_TECH_FLAG", model.getSPCL_TECH_FLAG());
			// 如果没有明细ID的则是新增，有的是修改
			if (StringUtil.isEmpty(model.getADVC_ORDER_DTL_ID())) {
				ADVC_ORDER_DTL_ID = StringUtil.uuid32len();
				params.put("ADVC_ORDER_DTL_ID", ADVC_ORDER_DTL_ID);
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				// 获取会话id值
				String SESSION_ID = model.getSESSION_ID();
				params.put("SESSION_ID", SESSION_ID);
				params.put("STATE", BusinessConsts.NORMAL);
				addList.add(params);
			} else {
				ADVC_ORDER_DTL_ID = model.getADVC_ORDER_DTL_ID();
				params.put("ADVC_ORDER_DTL_ID", ADVC_ORDER_DTL_ID);
				updateList.add(params);
			}
		}
		if (!StringUtil.isEmpty(SPCL_TECH_IDS)) {
			SPCL_TECH_IDS = SPCL_TECH_IDS.substring(0,
					SPCL_TECH_IDS.length() - 1);
			this.upUSE_FLAG(SPCL_TECH_IDS);
		}
		if (!updateList.isEmpty()) {
			this.batch4Update("Advcorder.updateChldById", updateList);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Advcorder.insertChld", addList);
		}
		Map<String,String> map=new HashMap<String, String>();
		map.put("LEDGER_ID", userBean.getLoginZTXXID());
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		if(!"1".equals(updateFlag)){
			String message=checkPrdInfo(map);
			if(!StringUtil.isEmpty(message)){
				throw new ServiceException(message);
			}
		}
		addORDER_RECV_DATE(ADVC_ORDER_ID);
		upDISCOUNT_AMOUNT(ADVC_ORDER_ID);
		return true;
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
		return findList("Advcorder.loadChldByIds", params);
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
		return this.findList("Advcorder.queryGchldByFkId", params);
	}

	/**
	 * 根据主表Id查询跟踪记录
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<AdvcorderModelTrace> traceQuery(String ADVC_ORDER_ID) {
		return this.findList("Advcorder.queryTraceByFkId", ADVC_ORDER_ID);
	}

	/**
	 * * 子表批量删除:软删除
	 * 
	 * @param ADVC_ORDER_DTL_IDs
	 *            the ADVC_ORDER_DTL_IDs
	 */
	public void txBatch4DeleteGchild(String PAYMENT_DTL_IDS) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("PAYMENT_DTL_IDS", PAYMENT_DTL_IDS);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		update("Advcorder.deleteGchldByIds", params);
	}

//	/**
//	 * 修改提交状态
//	 */
//	public MsgInfo txCommitById(Map<String, String> params, UserBean userBean)
//			throws Exception {
//		String advcOrderId = params.get("ADVC_ORDER_ID");
//		MsgInfo msgObj = LogicUtil.handStoreAcct(advcOrderId,
//				BusinessConsts.STORE_DESC, BusinessConsts.ADVC_ORDER_CONF_ID);
//		if (!msgObj.isFLAG()) {
//			if (!"0".equals(msgObj.getMESS())) { // 要做库存CHECK的，返回信息
//				return msgObj;
//			} else { // 不用做库存CHECK ，直接返回true
//				msgObj.setFLAG(true);
//			}
//		}
//		update("Advcorder.commitById", params);
//		insStateMents( advcOrderId, userBean);
//        return msgObj;
//	}
	
	public void  insStateMents(String advcOrderId,UserBean userBean) {
	//根据预订单id获取查看是否已存在付款信息
	int count=Integer.parseInt(String.valueOf(this.load("Advcorder.checkStateMentsById",advcOrderId)));
	if(count>1){
		throw new ServiceException("审核失败！该单据已生成付款信息！");
	}
	// 结算单主表map
	Map<String, String> params = new HashMap<String, String>();
	params.put("ADVC_ORDER_ID", advcOrderId);
	Map<String, String> param = new HashMap<String, String>();
	String STATEMENTS_ID = StringUtil.uuid32len();
	String STATEMENTS_NO=LogicUtil.getBmgz("DRP_STATEMENTS_NO");
	param.put("STATEMENTS_ID", STATEMENTS_ID);
	param.put("STATEMENTS_NO", STATEMENTS_NO);
	param.put("ADVC_ORDER_ID", advcOrderId);
	param.putAll(LogicUtil.sysFild(userBean));
	param.put("STATE", "已结算");
	param.put("BILL_TYPE", "订金");
	insert("Advcorder.insertSTATEMENTS", param);
	
	//获取预定单订金金额
	String amount=StringUtil.nullToSring(this.load("Advcorder.getAmount",advcOrderId));
	//新增预订单付款记录
	String remark="提交/待确认预订单，生成客户收款单";
	boolean flag =LogicUtil.instAdvcAmountNotes(userBean, advcOrderId, amount, STATEMENTS_NO, BusinessConsts.YJLBJ_FLAG_FALSE,remark );
	if(!flag){
		throw new ServiceException("审核失败！生成预订单记录失败");
	}
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
	 * 按id查找主表订金金额
	 */
	public double amountGetById(String ADVC_ORDER_ID) {
		Object ob = load("Advcorder.amountGetById", ADVC_ORDER_ID);
		if(ob==null){
			return Double.parseDouble("0.0");
		}
		return StringUtil.getDouble(ob);
	}
	/**
	 * 按id查询付款总金额
	 */
	public double payAmountGetById(String ADVC_ORDER_ID) {
		Object ob = load("Advcorder.loadGchldSum", ADVC_ORDER_ID);
		if(ob==null){
			return Double.parseDouble("0.0");
		}
		return (Double)load("Advcorder.loadGchldSum", ADVC_ORDER_ID);
	}

	/**
	 * 查询子表条数
	 * 
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public int getByIdADVC(String ADVC_ORDER_ID) {
		return Integer.parseInt(load("Advcorder.getByIdADVC", ADVC_ORDER_ID).toString());
	}

	/**
	 * 查询子表条数
	 * 
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public int getByIdPAYMENT(String ADVC_ORDER_ID) {
		return Integer.parseInt(load("Advcorder.getByIdPAYMENT", ADVC_ORDER_ID)
				.toString());
	}


	// 导入主表
	public void txParseExeclToDbNew(List lists,UserBean userBean) {
		List<Map<String,String>> list = (List<Map<String, String>>) lists.get(0);//只有一个页签
		String SESSIONID=StringUtil.uuid32len();
		Map<String,String> params =(Map<String,String>)list.get(0);
		if(!params.get("TERM_NO").equals("hoperun")){
			throw new ServiceException("对不起，请使用模版文件进行修改上传!");
		}
		list.remove(0);//第一行模版标记
		list.remove(0);//第二行列头
		String[] mustFld = {"TERM_NO","TERM_NAME","SALE_DATE","SALE_PSON_NAME","CUST_NAME","TEL","RECV_ADDR","PRD_NO",//"PRD_NAME","PRD_SPEC",
				"PRICE","DECT_PRICE","ORDER_NUM","PAYABLE_AMOUNT","PRD_TYPE","ORDER_RECV_DATE"};
		String[] mustFName = {"终端编号","终端名称","销售日期","业务员","客户名称","电话","收货地址","货品编号",//"货品名称","规格型号",
				"单价","折后单价","订货数量","应收金额","是否赠品","交货日期"};
		String message=checkMustFld(mustFld,mustFName, list);//验证必填字段
		if(!StringUtil.isEmpty(message)){
			throw new ServiceException(message);
		}
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("SESSIONID", SESSIONID);
			list.get(i).put("NullFlag", NullFlag);
			String checkStr="[0-9]+\\.{0,1}[0-9]{0,2}";//验证8位正整数和2位小数
			String PRICE=String.valueOf(list.get(i).get("PRICE"));
			if(!StringUtil.regexCheck(PRICE,checkStr)){
				throw new ServiceException("单价"+PRICE+"必须为数字，请重新编辑!");
			}
			String ORDER_NUM=String.valueOf(list.get(i).get("ORDER_NUM"));
			if(!StringUtil.regexCheck(PRICE,checkStr)){
				throw new ServiceException("订货数量"+ORDER_NUM+"必须为数字，请重新编辑!");
			}
			String PAYABLE_AMOUNT=String.valueOf(list.get(i).get("PAYABLE_AMOUNT"));
			if(!StringUtil.regexCheck(PRICE,checkStr)){
				throw new ServiceException("应收金额"+PAYABLE_AMOUNT+"必须为数字，请重新编辑!");
			}
			String PAY_AMONT=String.valueOf(list.get(i).get("PAY_AMONT"));
			if(!StringUtil.isEmpty(PAY_AMONT)&&!StringUtil.regexCheck(PRICE,checkStr)){
				throw new ServiceException("付款金额"+PAY_AMONT+"必须为数字，请重新编辑!");
			}
			String error="0";
			String SALE_DATE=String.valueOf(list.get(i).get("SALE_DATE"));
			String ORDER_RECV_DATE=String.valueOf(list.get(i).get("SALE_DATE"));
			try {
				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
				Date d=fm.parse(SALE_DATE);
				error="1";
				d=fm.parse(ORDER_RECV_DATE);
				error="2";
				boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),SALE_DATE);
				if(isMonthAcc){
					throw new Exception();
				}
			} catch (Exception e) {
				if("0".equals(error)){
					throw new ServiceException("销售日期格式错误，请重新编辑!");
				}else if("1".equals(error)){
					throw new ServiceException("交货日期格式错误，请重新编辑!");
				}else if("2".equals(error)){
					throw new ServiceException("销售结算日期:"+SALE_DATE+"已月结不能保存");
				}
			}
		}
		
		//新增到临时表
		if (!list.isEmpty()) {
			this.batch4Update("Advcorder.insertAdvcTemp", list);
		}
		
		//验证信息
		checkExpertAdvc(SESSIONID, userBean.getLoginZTXXID());
		
		
		List<Map<String,String>> addAdvcList=new ArrayList<Map<String,String>>();//预订单主表新增list
		List<Map<String,String>> addPrdList=new ArrayList<Map<String,String>>();//预订单货品明细新增list
		List<Map<String,String>> addPayList=new ArrayList<Map<String,String>>();//预订单付款明细新增list
		List<Map<String,String>> addSpclList=new ArrayList<Map<String,String>>();//特殊工艺主表新增list
		List<Map<String,String>> addSpclDtlList=new ArrayList<Map<String,String>>();//特殊工艺明细新增list
		List<Map<String,String>> addTraceList=new ArrayList<Map<String,String>>();//预订单跟踪List
		//获取预订单主表信息
		List<Map<String,String>> advcList=this.findList("Advcorder.queryTempAdvcOrder", SESSIONID);
		StringBuffer advcIdSb=new StringBuffer();
		for (int i = 0; i < advcList.size(); i++) {
			String ADVC_ORDER_ID=StringUtil.uuid32len();
			advcIdSb.append("'").append(ADVC_ORDER_ID).append("',");
			Map<String,String> advcMap=NullToEmpty(advcList.get(i));
			advcMap.put("SESSIONID", SESSIONID);
			//根据主表信息获取货品明细和付款明细信息
			List<Map<String,String>> prdList=this.findList("Advcorder.queryTempAdvcOrderDtl", advcMap);
			List<Map<String,String>> payList=this.findList("Advcorder.queryTempAdvcOrderPay", advcMap);
			
			
			//新增主表
			advcMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			String prefix = userBean.getCHANN_NO();//预订单 单头
			int length = BusinessConsts.ADVC_ORDER_NO_LENGTH;//预订单编号段长
			String ADVC_ORDER_NO = getBillNo("ADVC_ORDER_NO","DRP_ADVC_ORDER",prefix,length,userBean,i);
			String CONTRACT_NO=advcMap.get("CONTRACT_NO");
			if(StringUtil.isEmpty(CONTRACT_NO)||NullFlag.equals(CONTRACT_NO)){
				CONTRACT_NO=ADVC_ORDER_NO;
			}
			advcMap.put("CONTRACT_NO", CONTRACT_NO);
			advcMap.put("BILL_TYPE", "终端销售");
			advcMap.put("ADVC_ORDER_NO", ADVC_ORDER_NO);
			advcMap.put("STATE", BusinessConsts.UNCOMMIT);
			params.put("STTLE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);// 结算标记
			params.put("DEAL_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);// 处理标记
			advcMap.putAll(LogicUtil.sysFild(userBean));
			addAdvcList.add(advcMap);
			
			//获取货品特殊工艺描述，是否存在新增特殊工艺，如果有则新增特殊工艺
			Map<String,String> checkMap=new HashMap<String, String>();
			checkMap.put("SESSIONID", SESSIONID);
			checkMap.put("LEDGER_ID", userBean.getLoginZTXXID());
			List<Map<String,String>> spclRemList=this.findList("Advcorder.querySpclRem", checkMap);
			if(null!=spclRemList&&!spclRemList.isEmpty()){
				for(int j=0;i<spclRemList.size();j++){
					Map<String,String> spclMap=spclRemList.get(j);
					String SPCL_TECH_ID=StringUtil.uuid32len();
					spclMap.put("SPCL_TECH_ID", SPCL_TECH_ID);
					spclMap.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
					spclMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
					spclMap.put("TECH_MULT", "0");
					spclMap.put("TECH_AMOUNT", "0");
					spclMap.put("SPCL_TECH_FLAG", "1");
					spclMap.put("SPCL_SPEC_REMARK", "一般特殊");
					spclMap.put("SPCL_DTL_REMARK", "一般特殊:"+spclMap.get("SPCL_TECH_REMARK"));
					spclMap.put("DM_SPCL_TECH_NO", LogicUtil.getBmgz("DRP_SPCL_TECH_NO"));
					spclMap.put("LEDGER_ID", userBean.getLoginZTXXID());
					spclMap.put("TECH_NO_EDIT_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
					spclMap.put("SPCL_DTL_REMARK_CHK", "一般特殊工艺描述:"+spclMap.get("SPCL_TECH_REMARK"));
					addSpclList.add(spclMap);
					
					//明细
					Map<String,String> dtlMap=new HashMap<String, String>();
					dtlMap.put("SPCL_TECH_DTL_ID", StringUtil.uuid32len());
					dtlMap.put("SPCL_TECH_ID", SPCL_TECH_ID);
					dtlMap.put("SPCL_TECH_TYPE", "一般特殊工艺描述");
					dtlMap.put("REMARK", spclMap.get("SPCL_TECH_REMARK"));
					dtlMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
					addSpclDtlList.add(dtlMap);
				}
				if (!addSpclList.isEmpty()) {
					this.batch4Update("Advcorder.insertExeclSpcl", addSpclList);
				}
				if (!addSpclDtlList.isEmpty()) {
					this.batch4Update("Advcorder.insertExeclSpclDtl", addSpclDtlList);
				}
			}
			
			//新增货品明细
			for (int j = 0; j < prdList.size(); j++) {
				Map<String,String> prdMap=prdList.get(j);
				prdMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
				prdMap.put("ADVC_ORDER_DTL_ID", StringUtil.uuid32len());
				prdMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				prdMap.put("STATE", BusinessConsts.NORMAL);
				prdMap.put("LEDGER_ID", userBean.getLoginZTXXID());
				addPrdList.add(prdMap);
			}
			//新增付款明细
			for (int j = 0; j < payList.size(); j++) {
				Map<String,String> payMap=payList.get(j);
				payMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
				payMap.put("PAYMENT_DTL_ID", StringUtil.uuid32len());
				payMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				addPayList.add(payMap);
			}
			
			//新增订单跟踪
			Map<String, String> map = new HashMap<String, String>();
			map.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("ACTION", "预订单录入");
			map.put("REMARK", "已生成");
			map.put("ACTOR", userBean.getXM());
			map.put("BILL_NO", ADVC_ORDER_NO);
			addTraceList.add(map);
		}
		if (!addAdvcList.isEmpty()) {
			this.batch4Update("Advcorder.insertAdvcOrder", addAdvcList);
		}
		if (!addPrdList.isEmpty()) {
			this.batch4Update("Advcorder.insertAdvcOrderDtl", addPrdList);
		}
		if (!addPayList.isEmpty()) {
			this.batch4Update("Advcorder.insertAdvcOrderPay", addPayList);
		}
		if (!addTraceList.isEmpty()) {
			this.batch4Update("Advcorder.insertTrace", addTraceList);
		}
		System.out.println(advcIdSb);
		advcIdSb=InterUtil.replaceUpSql(advcIdSb);
		String ADVC_ORDER_IDS=advcIdSb.toString();
		//更新预订单要求到货时间
		this.update("Advcorder.updateAdvcORDER_RECV_DATEByIds", ADVC_ORDER_IDS);
		//更新预订单应收总额，总金额，优惠金额,已付款金额
		this.update("Advcorder.updateAdvcPriceByIds", ADVC_ORDER_IDS);
		//更新预订单订金，已付款金额
		this.update("Advcorder.updateAdvcAddAmountByIds", ADVC_ORDER_IDS);
		//更新业绩日期
		this.update("Advcorder.updateAdvcPFMC_DATEByIds", ADVC_ORDER_IDS);
		
		//删除临时表
//		this.delete("Advcorder.deleteAdvcTemp", SESSIONID);
	}


	// 导入数据后 更新主表应收总额，判断订金金额后修改销售日期
	public void txUpPAYABLE_AMOUNT(String ADVC_ORDER_ID) {
		double ADVC_AMOUNT = amountGetById(ADVC_ORDER_ID);// 预订单主表订货金额
		if (ADVC_AMOUNT != payAmountGetById(ADVC_ORDER_ID)) {
			// 如果主表订金金额不等于付款明细的付款金额总和，因为之前数据已经添加到数据库，所以需要强删之前添加的数据
			throw new ServiceException("Excel模版里预订单信息订金金额不等于付款金额之合，请重新输入上传");
		}
		Object PAYABLE_AMOUNTAdv = load("Advcorder.getPAYABLE_AMOUNTAdv",
				ADVC_ORDER_ID);
		if (PAYABLE_AMOUNTAdv == null) {
			PAYABLE_AMOUNTAdv = "0";
		}
		double PAYABLE_AMOUNT = Double
				.parseDouble(PAYABLE_AMOUNTAdv.toString());// 主表应收总额
		if (ADVC_AMOUNT >= PAYABLE_AMOUNT * 0.5) {
			update("Advcorder.upPFMC_DATE", ADVC_ORDER_ID);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String amount = load("Advcorder.getPAYABLE_AMOUNT", ADVC_ORDER_ID)
				.toString();
		double PAYABLE_AMOUNTS;
		if (StringUtil.isEmpty(amount)) {
			PAYABLE_AMOUNTS = 0;
		} else {
			PAYABLE_AMOUNTS = Double.parseDouble(amount);
		}
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("PAYABLE_AMOUNT", PAYABLE_AMOUNTS);
		update("Advcorder.updateById", map);
	}

	// 获取登录人员的最低折扣率
	public float getTERM_DECT_FROM(String RYXXID) {
		Object obj = load("Advcorder.getTERM_DECT_FROM", RYXXID);
		if (obj == null) {
			obj = "0";
		}
		return Float.parseFloat(obj.toString());
	}

	// 导出数据时根据id查询数据
	@SuppressWarnings("unchecked")
	public Map<String, String> getAdv(String ADVC_ORDER_ID) {
		return (Map<String, String>) load("Advcorder.getAdv", ADVC_ORDER_ID);
	}

	// 导出数据时根据id查询子表数据
	@SuppressWarnings("unchecked")
	public List<AdvcorderModelChld> getDtl(String ADVC_ORDER_ID) {
		Map params = new HashMap();
		params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Advcorder.getDtl", params);
	}

	// 获取数据库当前时间
	public String getDate() {
		return load("Advcorder.getDate", "").toString();
	}

	// 反填特殊工艺使用标识
	public void upUSE_FLAG(String SPCL_TECH_IDS) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("SPCL_TECH_IDS", SPCL_TECH_IDS);
		map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		this.update("Advcorder.upUSE_FLAG", map);
	}

	public void txUpdateFreeze(String advcOrderId, String ADVC_ORDER_DTL_IDS)
			throws Exception {
//		LogicUtil
//				.doStoreFreeze(BusinessConsts.ADVC_ORDER1_CONF_ID, advcOrderId);
		upFreeze(ADVC_ORDER_DTL_IDS);
	}

	public void upFreeze(String ADVC_ORDER_DTL_IDS) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ADVC_ORDER_DTL_IDS", ADVC_ORDER_DTL_IDS);
		map.put("IS_FREEZE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		map.put("FREEZE_NUM", "0");
		this.update("Advcorder.upFreeze", map);
	}

	@Override
	public Map<String, String> getTerminalInfoById(String BMXXID) {
		return (Map<String, String>) this.load("Advcorder.getTerminalInfoById",BMXXID);
	}
	/**
	 * 把明细最近交货日期存入主表
	 * @param ADVC_ORDER_ID
	 */
	public void addORDER_RECV_DATE(String ADVC_ORDER_ID){
		Map<String,String> map=new HashMap<String, String>();
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		this.update("Advcorder.addORDER_RECV_DATE",map);
	}
	//更新优惠金额，应收总额,总金额
	public void upDISCOUNT_AMOUNT(String ADVC_ORDER_ID){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		double PAYABLE_AMOUNT=StringUtil.getDouble(this.load("Advcorder.getPAYABLE_AMOUNT", map));
		double DISCOUNT_AMOUNT=StringUtil.getDouble(this.load("Advcorder.getDISCOUNT_AMOUNT", map));
		double TOTAL_AMOUNT=PAYABLE_AMOUNT+DISCOUNT_AMOUNT;
		map.clear();
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("PAYABLE_AMOUNT", PAYABLE_AMOUNT);
		map.put("DISCOUNT_AMOUNT", DISCOUNT_AMOUNT);
		map.put("TOTAL_AMOUNT", TOTAL_AMOUNT);
		this.update("Advcorder.updateMoney", map);
	}
	//按预订单id获取明细最小折扣率
	public float getRate(Map<String,Object> map){
		Object obj= load("Advcorder.getRate",map);
		if(obj==null){
			return -1;
		}else{
			return Float.parseFloat(obj.toString());
		}
	}

	public String getPAYABLE_AMOUNT(Map<String,String> map) {
		Object amount = load("Advcorder.getPAYABLE_AMOUNT", map);
		if(null==amount){
			amount="0";
		}
		return amount.toString();
	}
	
	
	public String getBillNo(UserBean userBean){
		String billNo = "";
		String Prefix = BusinessConsts.ADVC_ORDER_NO_PREFIX;//预订单 单头
		int lng = Prefix.length();
		String date = DateUtil.format(new Date(), "yyyyMMdd");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("date", date);
		params.put("index", lng+1);
		params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套
		List<Map<String,String>> result = this.findList("Advcorder.queryAdvcMaxBillNo", params);
		if(null == result || result.isEmpty()){
			billNo = Prefix+date+getRoundStr(6,0);
		}else{
			String ADVC_ORDER_NO = result.get(0).get("ADVC_ORDER_NO");
			String suffix = ADVC_ORDER_NO.substring(lng+8,ADVC_ORDER_NO.length());
			int num = StringUtil.getInteger(suffix);
			num = num+1;
			billNo =  Prefix+date+getRoundStr(6,num);
		}
		
	    return billNo;
	}
	
	public String getRoundStr(int leng,int num){
		String str  = Integer.toString(num);
		while(str.length()<leng){
			str = "0"+str;
		}
		return str;
	}
	
	public int getMaxFrezzDays(String ledger_Id)
	{
		return queryForInt("Advcorder.qryMaxFrezzDays", ledger_Id);
		
	}
	public void txUpStartDoUncomm(Map<String,String> map,UserBean userBean,double amount){
		Map<String, String> entry = this.load(map.get("ADVC_ORDER_ID"));
		Map<String, String> params = new HashMap<String, String>();
		params.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
		params.put("ADVC_ORDER_ID", map.get("ADVC_ORDER_ID"));
		params.put("ACTION", "待确认预订单");
		params.put("REMARK", "已生成");
		params.put("ACTOR", userBean.getXM());
		params.put("BILL_NO", entry.get("ADVC_ORDER_NO"));
		this.insert("Advcorder.insertTrace", params);
		if(amount>0){
			insStateMents(map.get("ADVC_ORDER_ID"), userBean);
		}
//		String remark="待确认预订单";
//		boolean flag =LogicUtil.instAdvcAmountNotes(userBean, map.get("ADVC_ORDER_ID"), amount+"", "", BusinessConsts.YJLBJ_FLAG_TRUE,remark );
//		if(!flag){
//			throw new ServiceException("待确认失败！生成预订单记录失败");
//		}
//		boolean flag=updateAdvcAmount(map.get("ADVC_ORDER_ID"));
//		if(!flag){
//			throw new ServiceException("操作失败！更新预订单尾款失败");
//		}
		this.update("Advcorder.upStartDoUncomm", map);
	}
	
	
	/**
	 * 查询客户付款单
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public Map<String,Object> queryStatements(String ADVC_ORDER_ID){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		paramMap.put("DEL_FLAG",  BusinessConsts.DEL_FLAG_COMMON);
		return (Map<String, Object>) this.load("Advcorder.loadStatementsByAdvcId", paramMap);
	}
	
	
	/**
	 * 查询预订单对应的客户收款单是否核销
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public boolean chackHavaWRITE(String ADVC_ORDER_ID){
	   boolean flag = false;
	   Map<String,String> paramMap = new HashMap<String,String>();
	   paramMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
	   int result = this.queryForInt("Advctoorder.queryWRITEFlag", paramMap);
	   if(result>0){
		   flag = true; 
	   }
	   return flag;
	}
	
	
	/**
	 * 删除对应的客户付款单
	 * @param ADVC_ORDER_ID
	 */
	public void deletePayments(String ADVC_ORDER_ID){
		//同时删除对应的客户付款单
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		update("Advctoorder.updateStatementsByAdvcId", paramMap);
	}
	
	/**
	 * 关闭预订单
	 * @param map
	 */
	public void txClose(Map<String,String> map)throws ServiceException{
		int count=StringUtil.getInteger(this.load("Advcorder.checkNum", map));
		if(count>0){
			throw new ServiceException("所选单据中有订货数量大于或等于已发数量，不能关闭！");
		}
		this.update("Advcorder.upChldNumById", map);//修改明细数量和状态
		//修改后订货数量应与已发数量相等，如不相等，则是问题，需排查
		int num=Integer.parseInt(this.load("Advcorder.getSumDtlNum",map.get("ADVC_ORDER_DTL_IDS")).toString());
		if(num>0){
			throw new ServiceException("所选单据关闭后订货数量与已发数量不等，请联系管理员！");
		}
		//如果明细存在未出货确认的出库单，不能关闭
		int checkNum=Integer.parseInt(this.load("Advcorder.getStoreNotDiffByIds",map).toString());
		if(checkNum>0){
			throw new ServiceException("所选明细存在未出货确认的货品，不能关闭！");
		}
		checkNum=Integer.parseInt(this.load("Advcorder.getAdvcStoreNotDiffByIds",map).toString());
		if(checkNum>0){
			throw new ServiceException("所选明细存在未出货确认的货品，不能关闭！");
		}
		
		double PAYABLE_AMOUNT=StringUtil.getDouble(this.load("Advcorder.getPAYABLE_AMOUNT", map));
		double DISCOUNT_AMOUNT=StringUtil.getDouble(this.load("Advcorder.getDISCOUNT_AMOUNT", map));
		map.put("PAYABLE_AMOUNT", PAYABLE_AMOUNT+"");//应收总额
		map.put("DISCOUNT_AMOUNT", DISCOUNT_AMOUNT+"");//优惠金额
		this.update("Advcorder.updateAdvcById", map);//修改主表信息
		this.update("Advcorder.updateStateMent", map);//修改结算单信息
	}
	/**
	 * 预订单审核通过/退回
	 * @param map  预订单id，状态
	 * @return
	 */
	public MsgInfo txUpStateById(Map<String,String> map,UserBean userBean,double amount)throws Exception{
		String ADVC_ORDER_ID = (String)map.get("ADVC_ORDER_ID");
		String STATE = map.get("STATE");
		MsgInfo msgObj = null;
		String message=checkState(STATE, ADVC_ORDER_ID);
		if(!StringUtil.isEmpty(message)){
			msgObj = new MsgInfo();
			msgObj.setFLAG(false);
			msgObj.setMESS(message);
			return msgObj;
		}
//		String checkState=(String) this.load("Advcorder.getAdvcState",ADVC_ORDER_ID);
//		if(!"提交".equals(checkState)){
//			throw new ServiceException("审核通过/退回状态修改失败！当前状态为:"+checkState+",不能操作");
//		}
		if("审核通过".equals(STATE)){
			msgObj = LogicUtil.checkStoreAcct(ADVC_ORDER_ID,
					BusinessConsts.STORE_DESC, BusinessConsts.ADVC_ORDER_CONF_ID);
			if (!msgObj.isFLAG()) {
				if (!"0".equals(msgObj.getMESS())) { // 要做库存CHECK的，返回信息
					return msgObj;
				} else { // 不用做库存CHECK ，直接返回true
					msgObj.setFLAG(true);
				}
			}
			if(amount>0){
				insStateMents(ADVC_ORDER_ID, userBean);
			}
			
//			this.txUpSpclEditFlag(ADVC_ORDER_ID, BusinessConsts.DEL_FLAG_DROP);
//			String remark="审核通过/退回";
//			boolean flag =LogicUtil.instAdvcAmountNotes(userBean, ADVC_ORDER_ID, amount+"", "", BusinessConsts.YJLBJ_FLAG_TRUE,remark );
//			if(!flag){
//				throw new ServiceException("审核通过/退回！生成预订单记录失败");
//			}
			//更新订单表信息
//			boolean advcFlag=updateAdvcAmount(ADVC_ORDER_ID);
//			if(!advcFlag){
//				throw new ServiceException("操作失败！更新预订单尾款失败");
//			}
		}
		if(msgObj==null){
			msgObj = new MsgInfo();
			 msgObj.setFLAG(true);
		}
	    this.update("Advcorder.commitById", map);
	    return msgObj;
	}
	//验证预订单提交，撤销
	public String checkState(String STATE,String ADVC_ORDER_ID){
		String newState= (String) this.load("Advcorder.getAdvcState",ADVC_ORDER_ID);
		if("提交".equals(STATE)){
			if(!"未提交".equals(newState)&&!"退回".equals(newState)){
				return "现预订单已处于"+newState+"状态不能提交";
			}
		}else if("未提交".equals(STATE)){
			if(!"提交".equals(newState)){
				return "现预订单已处于"+newState+"状态不能撤销";
			}
		}else if("审核通过".equals(STATE)){
			if(!"提交".equals(newState)){
				return "现预订单已处于"+newState+"状态不能审核通过";
			}
		}else if("退回".equals(STATE)){
			if(!"提交".equals(newState)){
				return "现预订单已处于"+newState+"状态不能退回";
			}
		}
		return "";
	}
	//反审核
	public void txRetuAudit(Map<String,String> map,UserBean userBean){
		String advcOrderId =  (String)map.get("ADVC_ORDER_ID");
		try{
			//当状态为提交时，删除付款单，为待确认时，不删除
			if(BusinessConsts.COMMIT.equals(map.get("STATE"))){
				map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
				map.put("BILL_TYPE", "订金");
				this.delete("Advcorder.delStatements", map);
				//反审核的时候 删除客户付款单与明细
				this.delete("Advcorder.delStatementsDtls", map);
			}
			this.update("Advcorder.commitById", map);
//			this.txUpSpclEditFlag(advcOrderId, BusinessConsts.DEL_FLAG_COMMON);
			//更新订单表信息
			boolean flag=upPAYED_TOTAL_AMOUNTById(advcOrderId);
			if(!flag){
				throw new ServiceException("操作失败！更新预订单尾款失败");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ServiceException("反审核失败 !");
		}
	}
	//根据预订单id查询明细是否有未处理或者已发货的
	public String checkReturnAudit(Map<String,String> map){
		map.put("DEAL_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		Map<String,Object> checkMap=(Map<String, Object>) this.load("Advcorder.checkReturnAudit",map);
		StringBuffer str=new StringBuffer();
		boolean pd=false;
		str.append("对不起，该单据");
		if(StringUtil.getInteger(checkMap.get("ORDERFLAG"))>0){
			str.append("已转订货订单");
			pd=true;
		}
		if(StringUtil.getInteger(checkMap.get("SENDFLAG"))>0){
			if(pd){
				str.append("和");
			}
			str.append("已生成预订单出库单");
			pd=true;
		}
		if(StringUtil.getInteger(checkMap.get("ADVCSENDFLAG"))>0){
			if(pd){
				str.append("和");
			}
			str.append("已生成预订单发货单");
			pd=true;
		}
		boolean flag=false;
		if(StringUtil.getInteger(checkMap.get("STATEMENTSFLAG"))>0){
			flag=true;
			if(pd){
				str.append("和");
			}
			str.append("已创建付款单");
			pd=true;
		}
		if(StringUtil.getInteger(checkMap.get("RETUADVCFLAG"))>0){
			flag=true;
			if(pd){
				str.append("和");
			}
			str.append("已创建销售退款单");
			pd=true;
		}
		if(flag){
			String no=(String) this.load("Advcorder.getAdvcStatemNO",getSql(map.get("ADVC_ORDER_ID")));
			str.append(",单号为").append(no);
		}
		if(pd){
			str.append("，不能反审核");
			return str.toString();
		}else{
			return null;
		}
		
	}
	//验证是否有反核销过的单子
	public int checkWriteFlag(String ADVC_ORDER_ID){
		return StringUtil.getInteger(this.load("Advcorder.checkWriteFlag",ADVC_ORDER_ID));
	}
//	public void txUpSpclEditFlag(String ADVC_ORDER_ID,String TECH_NO_EDIT_FLAG){
//		Map<String,String> map=new HashMap<String, String>();
//		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
//		map.put("TECH_NO_EDIT_FLAG", TECH_NO_EDIT_FLAG);
//		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
//		map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
//		this.update("Advcorder.txUpSpclEditFlag", map);
//	}
	
	/**
	 * 查询核销的明细的数量
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public int queryWriteoffCount(String ADVC_ORDER_ID){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("ADVC_ORDER_ID",ADVC_ORDER_ID);
		paramMap.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//0
		paramMap.put("BILL_TYPE", "订金");
		return this.queryForInt("Advcorder.queryWriteoffCount", paramMap);
	}
	/**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params){
		return findList("Advcorder.expertExcel",params);
	}
	public List<AdvcorderModelChld> priceChldQuery(String ADVC_ORDER_ID,String CHANN_TYPE){
		if("直营办".equals(CHANN_TYPE)){
			return findList("Advcorder.advcPriceChldQuery",ADVC_ORDER_ID);
		}else{
			return findList("Advcorder.priceChldQuery",ADVC_ORDER_ID);
		}
		
	}
	public void txUpdateChldPrice(List<AdvcorderModelChld> chldModelList){
		List<Map<String,Object>> updateList=new ArrayList<Map<String, Object>>();
		StringBuffer ADVC_ORDER_IDS=new StringBuffer();
		for (AdvcorderModelChld model : chldModelList) {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("ADVC_ORDER_DTL_ID", model.getADVC_ORDER_DTL_ID());
			map.put("DECT_RATE", model.getDECT_RATE());
			map.put("DECT_PRICE", model.getDECT_PRICE());
			map.put("PAYABLE_AMOUNT", model.getPAYABLE_AMOUNT());
			map.put("DECT_AMOUNT", model.getDECT_PRICE());
			ADVC_ORDER_IDS.append("'").append(model.getADVC_ORDER_DTL_ID()).append("',");
			updateList.add(map);
		}
		String ADVC_ORDER_IDSTR=ADVC_ORDER_IDS.toString();
		if (!StringUtil.isEmpty(ADVC_ORDER_IDSTR)) {
			ADVC_ORDER_IDSTR = ADVC_ORDER_IDSTR.substring(0,
					ADVC_ORDER_IDSTR.length() - 1);
		}
		this.batch4Update("Advcorder.updateChldById", updateList);//更新预订单明细价格
		this.batch4Update("Advcorder.updateSendChldById", updateList);//更新预订单发货申请明细价格
		this.batch4Update("Advcorder.updateStoreChldById", updateList);//更新预订单出库明细价格
		this.batch4Update("Advcorder.updateAdvcStoreChldById", updateList);//更新销售出库明细价格
		List<String> ADVC_ORDER_IDList=this.findList("Advcorder.geiAdvcId", ADVC_ORDER_IDSTR);//获取所选明细的主表ID
		for (int i = 0; i < ADVC_ORDER_IDList.size(); i++) {
			String ADVC_ORDER_ID=ADVC_ORDER_IDList.get(i);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			double PAYABLE_AMOUNT=StringUtil.getDouble(this.load("Advcorder.getPAYABLE_AMOUNT", map));
			double DISCOUNT_AMOUNT=StringUtil.getDouble(this.load("Advcorder.getDISCOUNT_AMOUNT", map));
			double TOTAL_AMOUNT=PAYABLE_AMOUNT+DISCOUNT_AMOUNT;
			map.clear();
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("PAYABLE_AMOUNT", PAYABLE_AMOUNT);
			map.put("DISCOUNT_AMOUNT", DISCOUNT_AMOUNT);
			map.put("TOTAL_AMOUNT", TOTAL_AMOUNT);
			this.update("Advcorder.updateMoney", map);//更新预订单主表价格
			this.update("Advcorder.updateSendMoney", map);//更新预订单发货主表价格
			this.update("Advcorder.updateAdvcStoreMoney", map);//更新销售出库主表价格
			this.update("Advcorder.updateStoreMoney", map);//更新预订单出库主表价格
		}
		//验证所选预订单明细是否存在已发货的存在，避免并发处理销售出库，如果存在则回滚
		boolean flag=checkRealNum(ADVC_ORDER_IDSTR);
		if(!flag){
			throw new ServiceException("对不起，您的选择存在已发货的货品，请检查后重新修改 !");
		}
	}
	public boolean checkRealNum(String ADVC_ORDER_IDS){
		Integer count=StringUtil.getInteger(this.load("Advcorder.checkRealNum",ADVC_ORDER_IDS));
		if(count>0){
			return false;
		}else{
			return true;
		}
	}
	//预订单反审核时 更新已付款金额为订金金额
	public boolean upPAYED_TOTAL_AMOUNTById(String ADVC_ORDER_ID){
		return this.update("Advcorder.upPAYED_TOTAL_AMOUNTById", ADVC_ORDER_ID);
	}
	/*
	 * 新增预订单已付款记录
	 */
	public boolean insertAdvcNotes(Map<String,String> map){
		return this.update("Advcorder.insertAdvcNotes", map);
	}
	public Integer getDtlCount(String ADVC_ORDER_ID){
		return  StringUtil.getInteger(this.load("Advcorder.getDtlCount", ADVC_ORDER_ID));
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> joinAdvcQuery(String ADVC_ORDER_ID,String CHANN_TYPE){
		if("直营办".equals(CHANN_TYPE)){
			return this.findList("Advcorder.joinOldAdvcQuery", ADVC_ORDER_ID);
		}else{
			return this.findList("Advcorder.joinAdvcQuery", ADVC_ORDER_ID);
		}
	}
	public String getSql(String ADVC_ORDER_ID){
		StringBuffer sql=new StringBuffer();
		sql.append("select wmsys.wm_concat((case when a.BILL_TYPE='销售退款' then a.STATEMENTS_NO ||'(销售退款)' else a.STATEMENTS_NO || '(付款单)' end )) str")
		.append(" from DRP_STATEMENTS a where a.ADVC_ORDER_ID='").append(ADVC_ORDER_ID).append("' and a.DEL_FLAG=0 and a.BILL_TYPE!='订金' ");
		return sql.toString();
	}
	//查询明细中关闭的行数
	public int checkCloseFlag(String ADVC_ORDER_ID){
		Map<String,String> map=new HashMap<String, String>();
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("STATE", "关闭");
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return StringUtil.getInteger(this.load("Advcorder.checkCloseFlag", map));
	}
	//获取发货申请单数量
	public int getSendAdvcNum(String ADVC_ORDER_ID){
		Map<String,String> map=new HashMap<String, String>();
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		int count=Integer.parseInt(String.valueOf(this.load("Advcorder.getStoreAdvcNum", map)));
		return count+Integer.parseInt(String.valueOf(this.load("Advcorder.getSendAdvcNum", map)));
	}
	public String checkAdvcAllPrice(String ADVC_ORDER_ID){
		int count=Integer.parseInt(String.valueOf(this.load("Advcorder.checkPayableAmount",ADVC_ORDER_ID)));
		if(count>0){
			return "当前单据明细存在金额计算错误，请联系管理员或检查后重新操作！";
		}
		Map<String,String> map=(Map<String, String>) this.load("Advcorder.checkMainPayableAmount",ADVC_ORDER_ID);
		if(!String.valueOf(map.get("PAYABLE_AMOUNT")).equals(String.valueOf(map.get("DTL_AMOUNT")))){
			return "当前单据应收金额计算错误，请联系管理员或检查后重新操作！";
		}
		return null;
	}
	//搜索框模糊查询
	public List<Map<String,String>> querySeachBox(Map<String,String> map){
		return this.findList("Advcorder.queryPrdInfo", map);
	}
	public Map<String,String> getPrdInfoById(String PRD_ID){
		return (Map<String, String>) this.load("Advcorder.getPrdInfoById",PRD_ID);
	}
	/**
	 * 验证货品信息是否正确
	 * @param map
	 * @return
	 */
	public String checkPrdInfo(Map<String,String> map){
		List<String> list=this.findList("Advcorder.checkPrdInfo", map);
		if(null!=list&&!list.isEmpty()){
			StringBuffer str=new StringBuffer();
			str.append("货品编号：");
			for (int i = 0; i < list.size(); i++) {
				str.append(list.get(i)).append(",");
			}
			str=InterUtil.replaceUpSql(str);
			str.append("信息存在错误，请使用通用选取或自动搜索选择货品！");
			return str.toString();
		}
		return null;
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
	/**
	 * 检查必填字段是否有空(list)
	 * @param strFld
	 * @param bodyMap
	 * @param msg
	 * @return
	 */
	public String checkMustFld(String[] strFld,String[]strFName, List<Map<String,String>> list){
		for (int i = 0; i < list.size(); i++) {
			Map<String,String> map=list.get(i);
			for (int j = 0; j < strFld.length; j++) {
				if(StringUtil.isEmpty(map.get(strFld[j]))){
					return strFName[j]+"字段为空，请检查后重新导入";
				}
			}
		}
		return null;
	}
	/**
	 * 验证导入预订单
	 * @param SESSIONID
	 * @param LEDGER_ID
	 * @throws ServiceException
	 */
	public void checkExpertAdvc(String SESSIONID,String LEDGER_ID) throws ServiceException{
		StringBuffer str=new StringBuffer();
		Map<String,String> checkMap=new HashMap<String, String>();
		checkMap.put("SESSIONID", SESSIONID);
		checkMap.put("LEDGER_ID", LEDGER_ID);
		//验证终端编号，名称是否存在
		List<Map<String,String>> termList=this.findList("Advcorder.checkTerminalInfo", checkMap);
		if(null!=termList&&!termList.isEmpty()){
			for (int i = 0; i < termList.size(); i++) {
				str.append("终端编号:"+termList.get(i).get("TERM_NO"))
				   .append("终端名称："+termList.get(i).get("TERM_NAME"))
				   .append(",");
			}
			str=InterUtil.replaceUpSql(str);
			str.append("未找到匹配信息,请检查后重新导入");
			throw new ServiceException(str.toString());
		}
		
		//验证业务员是否存在
		List<String> employeeList=this.findList("Advcorder.checkSalePsonInfo",checkMap);
		if(null!=employeeList&&!employeeList.isEmpty()){
			str.append("业务员：");
			for (int i = 0; i < employeeList.size(); i++) {
				str.append(employeeList.get(i)).append(",");
			}
			str=InterUtil.replaceUpSql(str);
			str.append("未找到匹配信息,请检查后重新导入");
			throw new ServiceException(str.toString());
		}
		//验证渠道活动信息
		checkMap.put("NullFlag", NullFlag);
		List<Map<String,String>> promoteList=this.findList("Advcorder.checkPromoteInfo", checkMap);
		if(null!=promoteList&&!promoteList.isEmpty()){
			for (int i = 0; i < promoteList.size(); i++) {
				str.append("渠道活动编号:"+promoteList.get(i).get("PROMOTE_NO"))
				   .append("渠道活动名称："+promoteList.get(i).get("PROMOTE_NAME"))
				   .append(",");
			}
			str=InterUtil.replaceUpSql(str);
			str.append("未找到匹配信息,请检查后重新导入");
			throw new ServiceException(str.toString());
		}
		//验证货品信息(有货品名称)
		List<Map<String,String>> prdList=this.findList("Advcorder.checkProductInfo", checkMap);
		if(null!=prdList&&!prdList.isEmpty()){
			for (int i = 0; i < prdList.size(); i++) {
				str.append("货品编号:"+prdList.get(i).get("PRD_NO"))
				   .append("货品名称："+prdList.get(i).get("PRD_NAME"))
				   .append(",");
			}
			str=InterUtil.replaceUpSql(str);
			str.append("未找到匹配信息,请检查后重新导入");
			throw new ServiceException(str.toString());
		}
		
		//验证货品信息
		List<String> prdNoList=this.findList("Advcorder.checkPrdNoInfo", checkMap);
		if(null!=prdNoList&&!prdNoList.isEmpty()){
			for (int i = 0; i < prdNoList.size(); i++) {
				str.append("货品编号:"+prdNoList.get(i))
				   .append(",");
			}
			str=InterUtil.replaceUpSql(str);
			str.append("未找到匹配信息,请检查后重新导入");
			throw new ServiceException(str.toString());
		}
		
		//校验是否存在订货数量*折后金额不等于应收金额的
		List<Map<String,String>> priceList=this.findList("Advcorder.checkPriceInfo", SESSIONID);
		if(null!=priceList&&!priceList.isEmpty()){
			for (int i = 0; i < priceList.size(); i++) {
				str.append("订货数量："+priceList.get(i).get("ORDER_NUM"))
				   .append("*折扣金额："+priceList.get(i).get("DECT_PRICE"))
				   .append("与应收金额："+priceList.get(i).get("PAYABLE_AMOUNT"))
				   .append(",");
			}
			str=InterUtil.replaceUpSql(str);
			str.append("不等,请检查后重新导入");
			throw new ServiceException(str.toString());
		}
		//验证货品类型是否只有赠品和销售货品
		List<String> typeList=this.findList("Advcorder.checkPrdTypeInfo", SESSIONID);
		if(null!=typeList&&!typeList.isEmpty()){
			str.append("是否赠品：");
			for (int i = 0; i < typeList.size(); i++) {
				str.append(typeList.get(i)).append(",");
			}
			str=InterUtil.replaceUpSql(str);
			str.append("有误，请输入是或否,请检查后重新导入");
			throw new ServiceException(str.toString());
		}
		
		//如果有特殊工艺编号的，验证是否有不存在特殊工艺编号
		List<String> spclList=this.findList("Advcorder.checkSpclNoInfo", checkMap);
		if(null!=spclList&&!spclList.isEmpty()){
			str.append("特殊工艺编号：");
			for (int i = 0; i < spclList.size(); i++) {
				str.append(spclList.get(i)).append(",");
			}
			str=InterUtil.replaceUpSql(str);
			str.append("不存在,请检查后重新导入");
			throw new ServiceException(str.toString());
		}
		//如果同时存在特殊工艺编号和描述的验证是否匹配
		List<String> spclRemarkList=this.findList("Advcorder.checkSpclNoAndRemarkInfo", checkMap);
		if(null!=spclRemarkList&&!spclRemarkList.isEmpty()){
			str.append("特殊工艺编号：");
			for (int i = 0; i < spclRemarkList.size(); i++) {
				str.append(spclRemarkList.get(i)).append(",");
			}
			str=InterUtil.replaceUpSql(str);
			str.append("与特殊工艺描述不匹配,请检查后重新导入");
			throw new ServiceException(str.toString());
		}
		
		
		//如果存在付款信息，查询是否存在信息不全的信息
		int count=Integer.parseInt(String.valueOf(this.load("Advcorder.countErrorPayInfo",SESSIONID)));
		if(count>0){
			throw new ServiceException("有付款信息时付款方式和付款金额为必填，请检查后重新导入！");
		}
		List<Map<String,String>> payList=this.findList("Advcorder.checkPayTypeInfo", checkMap);
		if(null!=payList&&!payList.isEmpty()){
			str.append("付款信息：");
			for (int i = 0; i < payList.size(); i++) {
				str.append(payList.get(i)).append(",");
			}
			str=InterUtil.replaceUpSql(str);
			str.append("不存在,请检查后重新导入");
			throw new ServiceException(str.toString());
		}
	}
	public Map<String,String> NullToEmpty(Map<String,String> map){
		for (String key:map.keySet()) {
			if("null".equals(String.valueOf(map.get(key)))||StringUtil.isEmpty(String.valueOf(map.get(key)))){
				map.put(key, NullFlag);
			}
		}
		map.put("NullFlag", NullFlag);
		return map;
	}
	public String getBillNo(String cellname,String tablename,String Prefix,int length,UserBean userBean,int index){
		BmgzService bmgzService = (BmgzService) SpringContextUtil
		.getBean("bmgzService");
		String billNo = "";
		//String Prefix = BusinessConsts.ADVC_ORDER_NO_PREFIX;//预订单 单头
		int lng = Prefix.length();
		String date = DateUtil.format(new Date(), "yyyyMMdd");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cellname", cellname);//列名 字段编码
		params.put("tablename", tablename);//表名
		params.put("date", date);
		params.put("index", lng+1);
		params.put("endIndex", date.length());
		if(null != userBean){
			params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套
		}
		List<Map<String,String>> result = bmgzService.findList("BMGZ.queryMaxBillNo", params);
		if(null == result || result.isEmpty()){
			billNo = Prefix+date+getRoundStr(length,0+index);
		}else{
			String ORDER_NO = result.get(0).get(cellname);
			String suffix = ORDER_NO.substring(lng+8,ORDER_NO.length());
			int num = StringUtil.getInteger(suffix);
			num = num+1;
			billNo =  Prefix+date+getRoundStr(length,num+index);
		}
		
	    return billNo;
	}
	/**
	 * 根据预订单Id查询预订单跟踪是否存在待确认记录
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public boolean getTraceById(String ADVC_ORDER_ID){
		int count=Integer.parseInt(String.valueOf(this.load("Advcorder.getTraceById",ADVC_ORDER_ID)));
		if(count>0){
			return true;
		}
		return false;
	}
}