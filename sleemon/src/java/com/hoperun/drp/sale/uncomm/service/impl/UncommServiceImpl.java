package com.hoperun.drp.sale.uncomm.service.impl;

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
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModel;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.uncomm.service.UncommService;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl *@func *@version 1.1 *@author lyg *@createdate 2013-08-11
 * 17:17:29
 */
public class UncommServiceImpl extends BaseService implements UncommService {
	private Logger logger = Logger.getLogger(UncommServiceImpl.class);
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Uncomm.pageQuery", "Uncomm.pageCount",
				params, pageNo);
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
		return this.findList("Uncomm.queryChldByFkId", params);
	}
	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param ADVC_ORDER_DTL_IDs
	 *            the ADVC_ORDER_DTL_IDs
	 * 
	 * @return the list< new master slavemx model>
	 */
	public List<AdvcorderModelChld> loadChilds(Map<String, String> params) {
		return findList("Uncomm.loadChldByIds", params);
	}
	
 
	/**
	 * * 明细数据编辑
	 * @param ADVC_ORDER_ID  the ADVC_ORDER_ID
	 * @param modelList  the model list
	 * @return true, if tx gchild edit
	 * @throws ParseException 
	 */
	public boolean txChildEdit(String ADVC_ORDER_ID,
			List<AdvcorderModelChld> chldList, UserBean userBean) throws ParseException {
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
			params.put("PRD_ID", model.getPRD_ID());
			params.put("PRD_NAME", model.getPRD_NAME());
			params.put("PRD_SPEC", model.getPRD_SPEC());
			params.put("PRD_COLOR", model.getPRD_COLOR());
			params.put("BRAND", model.getBRAND());
			params.put("STD_UNIT", model.getSTD_UNIT());
			params.put("PRICE", model.getPRICE() + "");
			float DECT_RATE = (float) model.getDECT_RATE();
			params.put("DECT_RATE", DECT_RATE + "");
			params.put("DECT_PRICE", model.getDECT_PRICE() + "");
			params.put("ORDER_NUM", model.getORDER_NUM() + "");
			params.put("PAYABLE_AMOUNT", model.getPAYABLE_AMOUNT() + "");
			params.put("REMARK", model.getREMARK());
			String ORDER_RECV_DATE=model.getORDER_RECV_DATE();
			String IS_FREEZE_FLAG = model.getIS_FREEZE_FLAG();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = df.parse(ORDER_RECV_DATE);    
			Date d2 = df.parse(currentDate);    
			long diff = d1.getTime() - d2.getTime();    
			long days = diff / (1000 * 60 * 60 * 24);
			if(days<0){
				throw new ServiceException("交货日期不能在今天之前!");
			}
			if(days>7&&"1".equals(IS_FREEZE_FLAG)){
				throw new ServiceException("交期超过允许最大冻结天数(7天)，不许允冻结!");
			}
			params.put("ORDER_RECV_DATE", ORDER_RECV_DATE);
			params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			params.put("PRD_TYPE", model.getPRD_TYPE());
			
			params.put("IS_FREEZE_FLAG", IS_FREEZE_FLAG);
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
		addORDER_RECV_DATE(ADVC_ORDER_ID);
		upDISCOUNT_AMOUNT(ADVC_ORDER_ID);
		return true;
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
	// 反填特殊工艺使用标识
	public void upUSE_FLAG(String SPCL_TECH_IDS) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("SPCL_TECH_IDS", SPCL_TECH_IDS);
		map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		this.update("Advcorder.upUSE_FLAG", map);
	}
	
	// 获取数据库当前时间
	public String getDate() {
		return load("Advcorder.getDate", "").toString();
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
	/**
	 * 求和更新应收总金额
	 */
	public void txgetPAYABLE_AMOUNT(UserBean userBean, String ADVC_ORDER_ID) {
		Map<String, Object> map = new HashMap<String, Object>();
		String amount = load("Uncomm.getPAYABLE_AMOUNT", ADVC_ORDER_ID)
				.toString();
		double PAYABLE_AMOUNT;
		if (StringUtil.isEmpty(amount)) {
			PAYABLE_AMOUNT = 0;
		} else {
			PAYABLE_AMOUNT = Double.parseDouble(amount);
		}
		map.put("UPDATOR", userBean.getRYXXID());
		map.put("UPD_NAME", userBean.getXM());
		map.put("UPD_TIME", "数据库时间");
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		map.put("PAYABLE_AMOUNT", PAYABLE_AMOUNT);
		update("Advcorder.updateById", map);
	}
	/**
	 * @删除
	 * @param ADVC_ORDER_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map<String, String> params) {
		// 删除父
		update("Uncomm.delete", params);
		// 删除子表
		return update("Uncomm.delChldByFkId", params);
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
		update("Uncomm.deleteChldByIds", params);
		this.update("Termreturn.upPAYABLE_AMOUNT", ADVC_ORDER_ID);
		addORDER_RECV_DATE(ADVC_ORDER_ID);
		upDISCOUNT_AMOUNT(ADVC_ORDER_ID);
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
		return (Map<String, String>) load("Uncomm.loadById", param);
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
	 * 修改提交状态
	 */
	@SuppressWarnings("unchecked")
	public MsgInfo txCommitById(Map<String, String> params, UserBean userBean)
			throws Exception {
		String advcOrderId = params.get("ADVC_ORDER_ID");
		MsgInfo msgObj = LogicUtil.checkStoreAcct(advcOrderId,
				BusinessConsts.STORE_DESC, BusinessConsts.ADVC_ORDER_CONF_ID);
		if (!msgObj.isFLAG()) {
			if (!"0".equals(msgObj.getMESS())) { // 要做库存CHECK的，返回信息
				return msgObj;
			} else { // 不用做库存CHECK ，直接返回true
				msgObj.setFLAG(true);
			}
		}
		update("Advcorder.commitById", params);
		// 结算单主表map
		Map<String, String> param = new HashMap<String, String>();
		String STATEMENTS_ID = StringUtil.uuid32len();
		param.put("STATEMENTS_ID", STATEMENTS_ID);
		param.put("STATEMENTS_NO", LogicUtil.getBmgz("DRP_STATEMENTS_NO"));
		param.put("ADVC_ORDER_ID", advcOrderId);
		param.putAll(LogicUtil.sysFild(userBean));
		param.put("BILL_TYPE", "订金");
		param.put("STATE", BusinessConsts.STATE_IS_PAY);
		insert("Advcorder.insertSTATEMENTS", param);
		// 结算单货品明细list
		List<Map<String, String>> chldList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> list = findList("Advcorder.queryChldByFkId",
				params);
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

		return msgObj;
	}
	public boolean upStateById(Map<String,String> map){
		return this.update("Advcorder.commitById", map);
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
			List<AdvcorderModelChld> chldList, UserBean userBean) throws ParseException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("TERM_ID", model.getTERM_ID());// 终端ID
		params.put("TERM_NO", model.getTERM_NO());// 终端编号
		params.put("TERM_NAME", model.getTERM_NAME());// 终端名称
		String SALE_DATE = model.getSALE_DATE();
		params.put("SALE_DATE", SALE_DATE);// 销售日期
		boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),SALE_DATE);
		if(isMonthAcc){
			throw new ServiceException("销售结算日期:"+SALE_DATE+"已月结不能保存");
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
		params.put("PAYED_TOTAL_AMOUNT", ADVC_AMOUNT + "");//已付款金额
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
			txUpdateById(params);
			clearCaches(ADVC_ORDER_ID);
		}
		// 子表信息保存
		if (null != chldList && !chldList.isEmpty()) {
			txChildEdit(ADVC_ORDER_ID, chldList, userBean);
		}else{
			addORDER_RECV_DATE(ADVC_ORDER_ID);
		}
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
	 * * update data * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String, String> params) {
		return update("Advcorder.updateById", params);
	}
	public String checkMonthAcc(String ADVC_ORDER_ID,UserBean userBean){
		Map<String,String> map=this.load(ADVC_ORDER_ID);
		String SALE_DATE=map.get("SALE_DATE");
		boolean isMonthAcc= LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),SALE_DATE);
		StringBuffer str=new StringBuffer();
		if(isMonthAcc){
			str.append("销售结算日期:").append(SALE_DATE).append("已月结不能保存");
		}
		return str.toString();
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
	 * 关闭
	 */
	public void txClose(Map<String,String> map){
		this.update("Uncomm.upChldNumById", map);
		this.upDISCOUNT_AMOUNT(map.get("ADVC_ORDER_ID"));
		this.txUpdateById(map);
	}
}