package com.hoperun.drp.sale.progressadvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModel;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.progressadvc.service.ProgressAdvcOrderService;
import com.hoperun.sys.model.UserBean;

public class ProgressAdvcOrderServiceImpl extends BaseService implements ProgressAdvcOrderService{
	
	
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo){
		return this.pageQuery("Progressadvc.pageQuery", "Progressadvc.pageCount",
				params, pageNo);
	}
	
	
	

	/**
	 * * 增加 * @param params
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String, String> params) {
		insert("Progressadvc.insert", params);
		return true;
	}

	/**
	 * @删除
	 * @param ADVC_ORDER_ID
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map<String, String> params) {
		update("Progressadvc.delGchldByFkId",params);
		// 删除子表
		update("Progressadvc.delChldByFkId", params);
		return update("Progressadvc.delete", params);
	}

	/**
	 * * update data * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String, String> params) {
		return update("Progressadvc.updateById", params);
	}
	
	/**
	 * @主从保存编辑
	 * @Description :
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public void txEdit(String ADVC_ORDER_ID, AdvcorderModel model, 
			List<AdvcorderModelChld> childList, UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		params.put("TERM_ID", model.getTERM_ID());// 终端ID
		params.put("TERM_NO", model.getTERM_NO());// 终端编号
		params.put("TERM_NAME", model.getTERM_NAME());// 终端名称
		String SALE_DATE = model.getSALE_DATE();
		params.put("SALE_DATE", SALE_DATE);// 销售日期
	 
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
			this.insert("Progressadvc.insertTrace", map);
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
		if (null != childList && !childList.isEmpty()) {
			txChildEdit(ADVC_ORDER_ID, childList, userBean);
		}else{
			addORDER_RECV_DATE(ADVC_ORDER_ID);
		}
	}
	
	
	
	/**
	 * @主表详细页面
	 * @param param
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String ADVC_ORDER_ID){
		return (Map<String, String>) load("Progressadvc.loadById", ADVC_ORDER_ID);
	}
	
	
	/**
	 * 编辑付款明细
	 */
	public void txGchildEdit(String ADVC_ORDER_ID,
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
			this.batch4Update("Progressadvc.updateGchldById", updateList);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Progressadvc.insertGchld", addList);
		}
		this.update("Progressadvc.updateAdvcAmountById", ADVC_ORDER_ID);
		 
	}
	
	/**
	 * 货品明细
	 */
	public List<AdvcorderModelChld> childQuery(String ADVC_ORDER_ID){
		Map<String, String> params = new HashMap<String, String>();
		params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		return this.findList("Progressadvc.queryChldByFkId", params);
	}
	
	
	/**
	 * 付款明细
	 * @return the list
	 */
	public List<AdvcorderModelGchld> gchildQuery(String ADVC_ORDER_ID){
		Map<String, String> params = new HashMap<String, String>();
		params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return findList("Progressadvc.queryGchldByFkId", params);
	}
	
	/**
	 * 编辑货品明细
	 * @param ADVC_ORDER_ID
	 * @param modelList
	 * @param userBean
	 */
	public void txChildEdit(String ADVC_ORDER_ID,
			List<AdvcorderModelChld> modelList, UserBean userBean){
		String SPCL_TECH_IDS = "";
		// 新增列表
		List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
		// 修改列表
		List<Map<String, Object>> updateList = new ArrayList<Map<String, Object>>();
		String ADVC_ORDER_DTL_ID;
		for (AdvcorderModelChld model : modelList) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("PRD_NO", model.getPRD_NO());
			params.put("PRD_ID", model.getPRD_ID());
			params.put("PRD_NAME", model.getPRD_NAME());
			params.put("PRD_SPEC", model.getPRD_SPEC());
			params.put("PRD_COLOR", model.getPRD_COLOR());
			params.put("BRAND", model.getBRAND());
			params.put("STD_UNIT", model.getSTD_UNIT());
			params.put("PRICE", model.getPRICE());
			params.put("DECT_RATE",  model.getDECT_RATE());
			params.put("DECT_PRICE", model.getDECT_PRICE());
			params.put("ORDER_NUM", model.getORDER_NUM());
			params.put("PAYABLE_AMOUNT", model.getPAYABLE_AMOUNT());
			params.put("REMARK", model.getREMARK());
			params.put("ORDER_RECV_DATE", model.getORDER_RECV_DATE());
			params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			params.put("PRD_TYPE", model.getPRD_TYPE());
			params.put("IS_FREEZE_FLAG", model.getIS_FREEZE_FLAG());
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
	 
		if (!updateList.isEmpty()) {
			this.batch4Update("Progressadvc.updateChldById", updateList);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Progressadvc.insertChld", addList);
		}
		addORDER_RECV_DATE(ADVC_ORDER_ID);
		upDISCOUNT_AMOUNT(ADVC_ORDER_ID);
	}
	/**
	 * 把明细最近交货日期存入主表
	 * @param ADVC_ORDER_ID
	 */
	public void addORDER_RECV_DATE(String ADVC_ORDER_ID){
		Map<String,String> map=new HashMap<String, String>();
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		this.update("Progressadvc.addORDER_RECV_DATE",map);
	}
	//更新优惠金额，应收总额,总金额
	public void upDISCOUNT_AMOUNT(String ADVC_ORDER_ID){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		double PAYABLE_AMOUNT=StringUtil.getDouble(this.load("Progressadvc.getPAYABLE_AMOUNT", map));
		double DISCOUNT_AMOUNT=StringUtil.getDouble(this.load("Progressadvc.getDISCOUNT_AMOUNT", map));
		double TOTAL_AMOUNT=PAYABLE_AMOUNT+DISCOUNT_AMOUNT;
		map.clear();
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("PAYABLE_AMOUNT", PAYABLE_AMOUNT);
		map.put("DISCOUNT_AMOUNT", DISCOUNT_AMOUNT);
		map.put("TOTAL_AMOUNT", TOTAL_AMOUNT);
		this.update("Progressadvc.updateMoney", map);
	}
 
	
	/**
	 * 删除付款明细
	 * 
	 * @param PAYMENT_DTL_IDS
	 */
	public void txBatch4DeleteGchild(String PAYMENT_DTL_IDS){
		Map<String, String> params = new HashMap<String, String>();
		params.put("PAYMENT_DTL_IDS", PAYMENT_DTL_IDS);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		update("Progressadvc.deleteGchldByIds", params);
	}

	
	/**
	 * * 根据子表Id批量加载货品明细
	 */
	public List<AdvcorderModelChld> loadChilds(Map<String, String> params){
		return findList("Progressadvc.loadChldByIds", params);
	}

	/**
	 * * 根据子表Id批量加载付款明细
	 */
	public List<AdvcorderModelGchld> loadGchilds(Map<String, String> params){
		return findList("Progressadvc.loadGchldByIds", params);
	}

	
	
	/**
	 * 提交
	 * @param ADVC_ORDER_ID
	 * @param userBean
	 */
	public void txToCommit(String ADVC_ORDER_ID,UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		params.put("STATE", "未提交");
		params.put("UPD_NAME", userBean.getXM());// 更新人名称
		params.put("UPDATOR", userBean.getXTYHID());// 更新人ID
		params.put("UPD_TIME", "数据库时间");// 更新时间
		txUpdateById(params);
	}
	
	/**
	 * 撤销
	 * @param ADVC_ORDER_ID
	 * @param userBean
	 */
	public void txRevoke(String ADVC_ORDER_ID,UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		params.put("STATE", "待完善");
		params.put("UPD_NAME", userBean.getXM());// 更新人名称
		params.put("UPDATOR", userBean.getXTYHID());// 更新人ID
		params.put("UPD_TIME", "数据库时间");// 更新时间
		txUpdateById(params);
	}
	
}
