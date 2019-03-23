package com.hoperun.drp.sale.advctoorder.service.impl;

/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvctoorderServiceImpl
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.advctoorder.model.AdvctoorderModelChld;
import com.hoperun.drp.sale.advctoorder.model.AdvctoorderModelGoodChlds;
import com.hoperun.drp.sale.advctoorder.service.AdvctoorderService;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl *@func *@version 1.1 *@author lyg *@createdate 2013-08-11
 * 17:17:29
 */
public class AdvctoorderServiceImpl extends BaseService implements
		AdvctoorderService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Advctoorder.pageQuery", "Advctoorder.pageCount",
				params, pageNo);
	}

	public IListPage getAdv(Map<String, String> params, int pageNo) {
		return this.pageQuery("Advctoorder.advPageQuery", "Advctoorder.pageCount",
				params, pageNo);
	}

	/**
	 * * 增加 * @param params
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String, Object> param) {
		insert("Advctoorder.insert", param);
		return true;
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
		return (Map<String, String>) load("Advctoorder.loadById", param);
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdvctoorderModelChld> childQuery(String ADVC_ORDER_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		return this.findList("Advctoorder.queryChldByFkId", params);
	}

	/**
	 * * 根据主表Id查询子表记录并合并货品
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<AdvctoorderModelChld> combineChildQuery(String ADVC_ORDER_ID) {
		Map params = new HashMap();
		params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Advctoorder.queryChldByFkId", params);
	}

	/**
	 * 按预订单id获取分组后的货品信息
	 */
	@SuppressWarnings("unchecked")
	public List findGroInfo(Map<String, String> map) {
		return this.findList("Advctoorder.findGroInfo", map);
	}

	/**
	 * 按订货方id查询区域折扣率
	 */
	public String getDECT_RATE(String ORDER_CHANN_ID) {
		return (String) load("Advctoorder.getDECT_RATE", ORDER_CHANN_ID);
	}

	/**
	 * @编辑
	 * @Description :
	 * @param ADVC_ORDER_ID
	 * @param AdvctoorderModel
	 * @param userBean
	 *            .
	 * 
	 * @return true, if tx txEdit
	 */
	public void txEdit(String ADVC_ORDER_IDS,
			List<AdvctoorderModelGoodChlds> chldModelList, UserBean userBean) {
		List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		for (AdvctoorderModelGoodChlds model : chldModelList) {
			Map<String, String> params = new HashMap<String, String>();
			String SHOP_CART_ID = StringUtil.uuid32len();
			params.put("SHOP_CART_ID", SHOP_CART_ID);// 购物车id
			params.put("PRD_ID", model.getPRD_ID());// 货品id
			params.put("PRD_NO", model.getPRD_NO());// 货品编号
			params.put("PRD_NAME", model.getPRD_NAME());// 货品名称
			params.put("PRD_SPEC", model.getPRD_SPEC());// 规格型号
			params.put("PRD_COLOR", model.getPRD_COLOR());// 颜色
			params.put("BRAND", model.getBRAND());// 品牌
			params.put("STD_UNIT", model.getSTD_UNIT());// 标准单位
			params.put("PRICE", model.getPRICE());
			params.put("DECT_RATE", model.getDECT_RATE());
			params.put("DECT_PRICE", model.getDECT_PRICE());
			String ORDER_NUM= model.getORDER_NUM();
			params.put("ORDER_NUM", ORDER_NUM);
			params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
			params.put("DEAL_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
			params.put("ADVC_ORDER_DTL_ID", model.getADVC_ORDER_DTL_ID());
			params.putAll(LogicUtil.sysFild(userBean));
			params.put("IS_TO_BASE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
			params.put("OFFSET_STORE_NUM", model.getPLE_REP());
			if("0".equals(ORDER_NUM)){
				updateList.add(params);
				continue;
			}
			params.put("SHOP_CART_TYPE", "预订单转订货");
			params.put("ORDER_AMOUNT", model.getDECT_AMOUNT());
			params.put("ORDER_RECV_DATE", model.getORDER_RECV_DATE());
			addList.add(params);
			updateList.add(params);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Advctoorder.insert", addList);
		}
		if(!updateList.isEmpty()){
			this.batch4Update("Advctoorder.upSpclId", updateList);
		}
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("UPDATOR", userBean.getRYXXID());
		maps.put("UPD_NAME", userBean.getXM());
		maps.put("UPD_TIME", "数据库时间");
		maps.put("ADVC_ORDER_IDS", ADVC_ORDER_IDS);
		maps.put("DEAL_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		this.update("Advctoorder.updateByIds", maps);
	}

	/** 根据收货方id查找订货方信息 */
	@SuppressWarnings("unchecked")
	public Map<String, String> loadChann(String RECV_CHANN_ID) {
		return (Map<String, String>) load("Advctoorder.getChann", RECV_CHANN_ID);
	}

	/**
	 * 根据货品id和当前登录帐套查找库存信息
	 */
	@SuppressWarnings("unchecked")
	public List loadProduct(Map<String, String> map) {
		return findList("Advctoorder.loadProduct", map);
	}

	public void txReverse(Map<String, String> map,UserBean userBean)throws Exception {
		update("Advctoorder.updateById", map);
		map.put("IS_TO_BASE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		update("Advctoorder.updateChldById",map);
		String ADVC_ORDER_ID = (String)map.get("ADVC_ORDER_ID");
		update("Advctoorder.upShopCartDel", ADVC_ORDER_ID);
		map.put("BILL_TYPE", "订金");
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		this.delete("Advcorder.delStatements", map);
//		this.txUpSpclEditFlag(ADVC_ORDER_ID, BusinessConsts.DEL_FLAG_COMMON);
		//获取预定单订金金额
//		String amount=StringUtil.nullToSring(this.load("Advcorder.getAmount",ADVC_ORDER_ID));
		//新增预订单付款记录
//		String remark="预订单转订货退回";
//		boolean flag =LogicUtil.instAdvcAmountNotes(userBean, ADVC_ORDER_ID, amount, "", BusinessConsts.YJLBJ_FLAG_TRUE,remark );
//		if(!flag){
//			throw new ServiceException("退回失败！生成预订单记录失败");
//		}
		//更新订单表信息
		boolean advcFlag=upPAYED_TOTAL_AMOUNTById(ADVC_ORDER_ID);
		if(!advcFlag){
			throw new ServiceException("操作失败！更新预订单尾款失败");
		}
	}

	// 按预订单id查询未删除明细条数
	public int getCount(Map<String, String> map) {
		return Integer.parseInt(load("Advctoorder.getCount", map).toString());
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
		return this.findList("Advctoorder.queryGchldByFkId", params);
	}
	//-- 0156143--Start--刘曰刚--2014-06-16//
	//修改渠道折扣率获取方式
//	/**
//	 * 根据查出来的数据里的货品id查询没有查出来的货品编号（区域扁平表里不存在的货品编号）
//	 * @param includedId
//	 * @return
//	 */
//	public String getNotIncludedPrd(String includedId,String ADVC_ORDER_IDS){
//		StringBuffer sql=new StringBuffer();
//		sql.append("select wmsys.wm_concat(DISTINCT PRD_NO) PRD_NO from DRP_ADVC_ORDER_DTL where ADVC_ORDER_ID in (")
//			.append(ADVC_ORDER_IDS).append(") and DEL_FLAG=0 ");
//		if(!StringUtil.isEmpty(includedId)){
//			sql.append("and PRD_ID not in (").append(includedId).append(")");
//		}
//		Object obj=this.load("Advctoorder.getNotIncludedPrd",sql.toString());
//		if(null==obj){
//			return null;
//		}else{
//			return obj.toString();
//		}
//	}
	/**
	 * 根据当前登录人所属渠道获取渠道折扣率
	 */
	public String getChannDiscount(String CHANN_ID){
		Object obj=this.load("Advctoorder.getChannDiscount", CHANN_ID);
		if(null!=obj){
			return obj.toString();
		}
		return null;
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
	
	//验证是否已经创建发货申请单
	public boolean checkShipmentFlag(String ADVC_ORDER_ID){
		Map<String,String> map=new HashMap<String, String>();
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		int count=StringUtil.getInteger(this.load("Advctoorder.checkShipmentFlag", map));
		if(count>0){
			return false;
		}else{
			return true;
		}
	}
	//验证是否生成订货订单
	public String checkOrderFlag(String ADVC_ORDER_ID){
		List list=this.findList("Advctoorder.checkOrderFlag", ADVC_ORDER_ID);
		String Message="";
		if(!list.isEmpty()){
			Message="该预订单已生成订货订单，订单编号：";
			for (int i = 0; i < list.size(); i++) {
				Message+=list.get(i)+"、";
			}
			Message=Message.substring(0,Message.length() - 1);
			Message+="，请删除订货订单后再进行退回 !";
		}
		return Message;
		
	}
	//根据预订单id查询是否存在已付款的单子
	public boolean checkamountFlag(String ADVC_ORDER_ID){
		Map<String,String> map=new HashMap<String, String>();
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		int count= StringUtil.getInteger(this.load("Advctoorder.checkAmountFlag", map));
		if(count>0){
			return false;
		}else{
			return true;
		}
	}
	//验证是否有反核销过的单子
	public boolean checkWriteFlag(String ADVC_ORDER_ID){
		int amount= StringUtil.getInteger(this.load("Advcorder.checkWriteFlag",ADVC_ORDER_ID));
		if(amount>0){
			return false;
		}else{
			return true;
		} 
	}
//	public void txUpSpclEditFlag(String ADVC_ORDER_ID,String TECH_NO_EDIT_FLAG){
//		Map<String,String> map=new HashMap<String, String>();
//		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
//		map.put("TECH_NO_EDIT_FLAG", TECH_NO_EDIT_FLAG);
//		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
//		map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
//		this.update("Advcorder.txUpSpclEditFlag", map);
//	}
	public List downQuery(Map<String,String> map){
		return this.findList("Advctoorder.downGetById", map);
	}
	//预订单退回时 更新已付款金额为订金金额
	public boolean upPAYED_TOTAL_AMOUNTById(String ADVC_ORDER_ID){
		return this.update("Advcorder.upPAYED_TOTAL_AMOUNTById", ADVC_ORDER_ID);
	}
	public String getStatementsNo(String ADVC_ORDER_ID){
		return (String) this.load("Advcorder.getAdvcStatemNO",getSql(ADVC_ORDER_ID));
	}
	
	public String getSql(String ADVC_ORDER_ID){
		StringBuffer sql=new StringBuffer();
		sql.append("select wmsys.wm_concat((case when a.BILL_TYPE='销售退款' then a.STATEMENTS_NO ||'(销售退款)' else a.STATEMENTS_NO || '(付款单)' end )) str")
		.append(" from DRP_STATEMENTS a where a.ADVC_ORDER_ID='").append(ADVC_ORDER_ID).append("' and a.DEL_FLAG=0 and a.BILL_TYPE!='订金' ");
		return sql.toString();
	}
	public Map<String,String> getChannInfo(String CHANN_ID){
		 return (Map<String, String>) this.load("Advctoorder.getChannInfo",CHANN_ID);
	 }
}