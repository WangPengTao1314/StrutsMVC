/**
 * prjName:喜临门营销平台
 * ucName:订货订单处理
 * fileName:GoodsorderServiceImpl
 */
package com.hoperun.erp.sale.goodsorder.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.base.factory.model.FactoryModel;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.InterUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.report.querystock.model.StoreModel;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelTrace;
import com.hoperun.erp.sale.goodsorder.model.GoodsorderModel;
import com.hoperun.erp.sale.goodsorder.model.GoodsorderModelChld;
import com.hoperun.erp.sale.goodsorder.service.GoodsorderService;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl *@func *@version 1.1 *@author zzb *@createdate 2013-08-30
 * 15:55:09
 */
public class GoodsorderServiceImpl extends BaseService implements
		GoodsorderService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		//变颜色的日期
		params.put("BILL_DIFF_DATE_FLAG", BusinessConsts.BILL_DIFF_DATE_FLAG);
		return this.pageQuery("Goodsorder.pageQuery", "Goodsorder.pageCount",
				params, pageNo);
	}

	/**
	 * * 增加 * @param params
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String, Object> params) {
		insert("Goodsorder.insert", params);
		return true;
	}

	/**
	 * @删除
	 * @param GOODS_ORDER_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map<String, String> params) {
		// 删除父
		update("Goodsorder.delete", params);
		// 删除子表
		return update("Goodsorder.delChldByFkId", params);
	}

	/**
	 * * update data * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String, Object> params) {
		return update("Goodsorder.updateById", params);
	}

	/**
	 * @编辑
	 * @Description :
	 * @param GOODS_ORDER_ID
	 * @param GoodsorderModel
	 * @param userBean
	 *            .
	 * 
	 * @return true, if tx txEdit
	 */
	public void txEdit(String GOODS_ORDER_ID, GoodsorderModel model,
			List<GoodsorderModelChld> chldList, UserBean userBean) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtil.isEmpty(GOODS_ORDER_ID)) {
			GOODS_ORDER_ID = StringUtil.uuid32len();
			params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
			params.put("CRE_NAME", userBean.getXM());
			params.put("CREATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getXM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("DEPT_ID", userBean.getBMXXID());
			params.put("DEPT_NAME", userBean.getBMMC());
			params.put("ORG_ID", userBean.getJGXXID());
			params.put("ORG_NAME", userBean.getJGMC());
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
			params.put("LEDGER_NAME", userBean.getLoginZTMC());
			params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			txInsert(params);
		} else {
			params.put("SHIP_POINT_ID", model.getSHIP_POINT_ID());
			params.put("SHIP_POINT_NAME", model.getSHIP_POINT_NAME());
			params.put("UPD_NAME", userBean.getXM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_TIME", "sysdate");
			params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
			txUpdateById(params);
			clearCaches(GOODS_ORDER_ID);
		}
		// 子表信息保存
		if (null != chldList && !chldList.isEmpty()) {
//			txChildEdit(GOODS_ORDER_ID, chldList);
		}
	}

	public Double countAmount(List<GoodsorderModelChld> chldList) {

		Double d = 0.00d;
		for (GoodsorderModelChld model : chldList) {
			if (StringUtil.isEmpty(model.getORDER_AMOUNT())) {
				d = d + StringUtil.getDouble(model.getORDER_AMOUNT());
			}
		}
		return d;
	}

	/**
	 * @详细
	 * @param param
	 *            GOODS_ORDER_ID
	 * @param param
	 *            the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String param) {
		return (Map<String, String>) load("Goodsorder.loadById", param);
	}

	/**
	 * 加载主表数据
	 * 
	 * @param param
	 *            GOODS_ORDER_ID
	 * @return GoodsorderModel
	 */
	public GoodsorderModel loadById(String GOODS_ORDER_ID) {
		return (GoodsorderModel) load("Goodsorder.loadByIdModel",
				GOODS_ORDER_ID);
	}

	/**
	 * * 明细数据编辑
	 * 
	 * @param GOODS_ORDER_ID
	 *            the GOODS_ORDER_ID
	 * @param modelList
	 *            the model list
	 * @return true, if tx child edit
	 */
	@Override
	public boolean txChildEdit(String GOODS_ORDER_ID, String BILL_TYPE,
			List<GoodsorderModelChld> chldList,UserBean userBean) {

		// 新增列表
		List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
		// 修改列表
		List<Map<String, Object>> updateList = new ArrayList<Map<String, Object>>();

		for (GoodsorderModelChld model : chldList) {
			Map<String, Object> params = new HashMap<String, Object>();
			
			String PRICE_ = model.getPRICE();
			String DECT_PRICE_ = model.getDECT_PRICE();
			String ORDER_NUM_ = model.getORDER_NUM();
			String OLD_DECT_PRICE_ = model.getOLD_DECT_PRICE();
			Double PRICE = StringUtil.getDouble(PRICE_);
			Double DECT_PRICE = StringUtil.getDouble(DECT_PRICE_);
			Integer ORDER_NUM = StringUtil.getInteger(ORDER_NUM_);
			Double OLD_DECT_PRICE = StringUtil.getDouble(OLD_DECT_PRICE_);
			
	        Double REBATE_PRICE = 0.0;
	        Double REBATE_AMOUNT = 0.0;
//			if("返利订货".equals(BILL_TYPE)){
//				REBATE_PRICE = (PRICE - DECT_PRICE)<0?0:(PRICE - DECT_PRICE);
//				REBATE_AMOUNT = ORDER_NUM*REBATE_PRICE;
//			}
			if(!"返利订货".equals(BILL_TYPE)&&!"赠品订货".equals(BILL_TYPE)){
				params.put("PRICE", PRICE);
				params.put("DECT_PRICE", DECT_PRICE);
				params.put("ORDER_NUM", ORDER_NUM);
				params.put("REBATE_PRICE", REBATE_PRICE);
				params.put("REBATE_AMOUNT", REBATE_AMOUNT);
				if(DECT_PRICE.doubleValue() == DECT_PRICE.doubleValue()){
					System.out.println("============");
				}
				if(!OLD_DECT_PRICE.equals(DECT_PRICE)){
					params.put("EDIT_PRICE", DECT_PRICE);//修改后的价格
					params.put("PRICE_EDIT_NAME", userBean.getXM());//价格修改人名称
					params.put("PRICE_EDIT_TIME", BusinessConsts.UPDATE_TIME);//价格修改时间
				}
				params.put("OLD_PRICE", OLD_DECT_PRICE);
				params.put("DECT_RATE", model.getDECT_RATE());
				params.put("ORDER_AMOUNT", model.getORDER_AMOUNT());
				params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
				params.put("IS_NO_STAND_FLAG", model.getIS_NO_STAND_FLAG());// 是否非标 // 1->非标订单
				params.put("IS_BACKUP_FLAG", model.getIS_BACKUP_FLAG());// 是否抵库 1->抵库
				params.put("ADVC_SEND_DATE", model.getADVC_SEND_DATE()); //预计交货日期
			}
			params.put("PRODUCT_REMARK", model.getPRODUCT_REMARK());
			params.put("REMARK", StringUtil.nullToSring(model.getREMARK()).trim());
			params.put("NEW_SPEC", model.getNEW_SPEC());
			params.put("NEW_COLOR", model.getNEW_COLOR());
			// 如果没有明细ID的则是新增，有的是修改
			if (StringUtil.isEmpty(model.getGOODS_ORDER_DTL_ID())) {
				params.put("GOODS_ORDER_DTL_ID", StringUtil.uuid32len());
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				addList.add(params);
			} else {
				params.put("GOODS_ORDER_DTL_ID", model.getGOODS_ORDER_DTL_ID());
				updateList.add(params);
			}

		}

		if (!updateList.isEmpty()) {
			this.batch4Update("Goodsorder.updateChldById", updateList);
		}
		if (!addList.isEmpty()) {
			//this.batch4Update("Goodsorder.insertChld", addList);
		}
		return true;
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param GOODS_ORDER_ID
	 *            the GOODS_ORDER_ID
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<GoodsorderModelChld> childQuery(String GOODS_ORDER_ID) {
		Map params = new HashMap();
		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Goodsorder.queryChldByFkId", params);
	}
	
	
	/**
	 * * 根据主表Id查询子表记录
	 * @param  params
	 * @return the list
	 */
	public  List <GoodsorderModelChld> childQuery(Map<String, String> params) {
		return this.findList("Goodsorder.queryChldByFkId", params);
	}
	

	/**
	 * 查询默认的工厂
	 * 
	 * @param SHIP_POINT_ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> queryDefaultFactory(String GOODS_ORDER_ID) {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> result = new HashMap<String, String>();
		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		List<FactoryModel> list = this.findList(
				"Goodsorder.queryDefaultFactory", params);
		if (null != list && !list.isEmpty()) {
			result.put("FACTORY_ID", list.get(0).getFACTORY_ID());
			result.put("FACTORY_NAME", list.get(0).getFACTORY_NAME());
		}
		return result;

	}

	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param GOODS_ORDER_DTL_IDs
	 *            the GOODS_ORDER_DTL_IDs
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<GoodsorderModelChld> loadChilds(Map<String, String> params) {
		return findList("Goodsorder.loadChldByIds", params);
	}

	/**
	 * * 子表批量删除:软删除
	 * 
	 * @param GOODS_ORDER_DTL_IDs
	 *            the GOODS_ORDER_DTL_IDs
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void txBatch4DeleteChild(String GOODS_ORDER_DTL_IDs) {
		Map params = new HashMap();
		params.put("GOODS_ORDER_DTL_IDS", GOODS_ORDER_DTL_IDs);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		update("Goodsorder.deleteChldByIds", params);
	}
	
	 /**
     * * 根据主表Id查询子表记录
     * @param params 参数
     * @return the list<GoodsorderModelChld>
     */
    public List <GoodsorderModelChld> childModelQuery(String GOODS_ORDER_ID){
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("GOODS_ORDER_ID",GOODS_ORDER_ID);
    	params.put("CANCEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Goodsorder.queryChldModelByFkId", params);
    }

    /**
     * 根据主表ID查询明细
     * @param GOODS_ORDER_ID
     * @return
     */
    @SuppressWarnings("unchecked")
	public List <Map<String,Object>> childMapQuery(String GOODS_ORDER_ID){
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("GOODS_ORDER_ID",GOODS_ORDER_ID);
    	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	params.put("CANCEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Goodsorder.queryChldByFkId", params);
    }
    
	/**
	 * 插入 销售订单、销售订单明细 、销售订单特殊工艺 三张表
	 * 
	 * @param model
	 *            订货订单
	 * @param chilList
	 *            订货订单明细
	 */
	public Map<String,Object> txAddSalOrder(GoodsorderModel model,
			List<GoodsorderModelChld> chilList, UserBean userBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		String GOODS_ORDER_NO = model.getGOODS_ORDER_NO();
		
		List<Map<String, Object>> updateDtlList = new ArrayList<Map<String, Object>>();
		for(GoodsorderModelChld c : chilList){
			// 将抵库标记放入map
			Map<String, Object> tempMap = new HashMap<String, Object>();
			String IS_BACKUP_FLAG = c.getIS_BACKUP_FLAG();
			if(null == IS_BACKUP_FLAG){
				continue;
			}
			tempMap.put("GOODS_ORDER_DTL_ID", c.getGOODS_ORDER_DTL_ID());
			tempMap.put("IS_BACKUP_FLAG", c.getIS_BACKUP_FLAG());
			updateDtlList.add(tempMap);
		}
		if(!updateDtlList.isEmpty()){
			// 先更新 订货订单的是否抵库
			this.batch4Update("Goodsorder.updateChldById", updateDtlList);
		}
		//反填特殊工艺的不可编辑标记
		this.txUpSpclEditFlag(model.getGOODS_ORDER_ID());
		
		
		
		// 插入销售订单明细
//		insertSaleChilds(param, chilList, userBean);
		Map<String,Object> returnMap = null;
		String PRD_SUIT_FLAG_IDS = "";
		String GOODS_ORDER_DTL_ID_BZ = "";
		String GOODS_ORDER_DTL_ID_FB = "";
	    //根据主表ID查询明细
		List<Map<String, Object>> childList = this.childMapQuery(model.getGOODS_ORDER_ID());
		List<Map<String, Object>> nomalList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> spelList = new ArrayList<Map<String, Object>>();
		int index_BZ = 0;
		int index_FB = 0;
		int size = childList.size();
		for(Map<String, Object> child : childList){
			String PRD_SUIT_FLAG = StringUtil.nullToSring(child.get("PRD_SUIT_FLAG"));//产品套件标记1->套件
			String IS_NO_STAND_FLAG = StringUtil.nullToSring(child.get("IS_NO_STAND_FLAG"));
			String GOODS_ORDER_DTL_ID = StringUtil.nullToSring(child.get("GOODS_ORDER_DTL_ID"));
			if (StringUtil.isEmpty(IS_NO_STAND_FLAG) || "0".equals(IS_NO_STAND_FLAG)) {
				index_BZ++;
				//产品套件
				if("1".equals(PRD_SUIT_FLAG)){
					Map<String,Object> params = new HashMap<String,Object>();
					//计算冻结单价的百分比
					Double DECT_PRICE = StringUtil.getDouble(child.get("DECT_PRICE"));
					Double CREDIT_FREEZE_PRICE = StringUtil.getDouble(child.get("CREDIT_FREEZE_PRICE"));
					Double PAY_RATE = CREDIT_FREEZE_PRICE/DECT_PRICE;
					PAY_RATE = Math.round(PAY_RATE * 100)/100.00; 
					params.put("PRD_SUIT_FLAG_ID", GOODS_ORDER_DTL_ID);
					params.put("CHANN_ID", model.getORDER_CHANN_ID());
					params.put("PAY_RATE", PAY_RATE);//冻结比例
					params.put("DECT_RATE", child.get("DECT_RATE"));//折扣率
					List<Map<String,Object>> SUITList = this.findList("Goodsorder.queryChildBySuitFlag", params);
					//把两个list合并
					if(!SUITList.isEmpty()){
						nomalList.addAll(SUITList);
					}
				}else{
					nomalList.add(child);
				}
				GOODS_ORDER_DTL_ID_BZ += "'" + GOODS_ORDER_DTL_ID + "',";
				
			}
			if ("1".equals(IS_NO_STAND_FLAG)) { 
				index_FB++;
				GOODS_ORDER_DTL_ID_FB += "'" + GOODS_ORDER_DTL_ID+ "',";
				spelList.add(child);
			}
			
		}
		//有非标的订单，非标标记设置为1
		if(index_BZ <size && index_FB<size){
			model.setIS_HAVE_SPECL("1");
		}
	    //标准订单包含产品套件
		if(!nomalList.isEmpty()){
			GOODS_ORDER_DTL_ID_BZ = GOODS_ORDER_DTL_ID_BZ.substring(0,
					GOODS_ORDER_DTL_ID_BZ.length() - 1);
			inserSaleOrder(model, "0", nomalList,GOODS_ORDER_DTL_ID_BZ,
					GOODS_ORDER_NO, userBean);
		}
		//非标订单
		if(!spelList.isEmpty()){
			GOODS_ORDER_DTL_ID_FB = GOODS_ORDER_DTL_ID_FB.substring(0,
					GOODS_ORDER_DTL_ID_FB.length() - 1);
			returnMap = inserSaleOrder(model, "1", spelList,GOODS_ORDER_DTL_ID_FB,
					GOODS_ORDER_NO, userBean);
		}
		

		// 暂时未知此表的用处
		// param.clear();
		// param.put("SALE_ORDER_DTL_ID", "SALE_ORDER_DTL_ID");
		// param.put("SALE_ORDER_ID", "SALE_ORDER_ID");
		// insert("Goodsorder.insertSaleSpacTech", param);

		// 更新订货订单的状态
		param.clear();
		param.put("GOODS_ORDER_ID", model.getGOODS_ORDER_ID());
		param.put("UPDATOR", userBean.getXTYHID());
		param.put("UPD_NAME", userBean.getXM());
		param.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
		param.put("STATE", BusinessConsts.DONE);
		param.put("AUDIT_ID", userBean.getXTYHID());
		param.put("AUDIT_NAME", userBean.getXM());
		param.put("AUDIT_TIME", BusinessConsts.UPDATE_TIME);
		update("Goodsorder.updateStateById", param);
		// 插入订货订单的生命周期
		insertGoodorderTack(model.getGOODS_ORDER_ID(), model
				.getGOODS_ORDER_NO(), model.getBILL_TYPE(), userBean);
		
		
		return returnMap;

	}

	/**
	 * 订单订单生命周期
	 * 
	 * @param GOODS_ORDER_ID
	 * @param GOODS_ORDER_NO
	 * @param userBean
	 */
	private void insertGoodorderTack(String GOODS_ORDER_ID,
			String GOODS_ORDER_NO, String BILL_TYPE, UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		String GOODS_ORDER_TRACE_ID = StringUtil.uuid32len();
		params.put("GOODS_ORDER_TRACE_ID", GOODS_ORDER_TRACE_ID);
		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		params.put("BILL_NO", GOODS_ORDER_NO);// 订货订单的NO
		params.put("BILL_TYPE", BILL_TYPE);// 订货订单的type
		params.put("ACTION_NAME", "订货订单处理");// 订货订单处理
		params.put("TRACE_URL", BusinessConsts.ORDER_BILL_ACTION_TRACE_URL
				+ GOODS_ORDER_ID);
		params.put("DEAL_PSON_NAME", userBean.getXM());
		params.put("STATE", BusinessConsts.DONE);// 已处理

		if (StringUtil.isEmpty(GOODS_ORDER_NO)) {
			insert("Goodsorder.mergeInsertOrderTrack", params);
		} else {
			insert("Goodsorder.insertOrderTrack", params);
		}

	}

	/**
	 * 插入销售订单
	 * 
	 * @param param
	 * @param userBean
	 * @param IS_NO_STAND_FLAG
	 *            是否非标 1-> 非标
	 */
	public void insertSaleOrder(Map<String, Object> param, UserBean userBean,
			String IS_NO_STAND_FLAG) {
		// 插入销售订单
		insert("Goodsorder.insertSaleOrder", param);

		inertSaleordertrace(param, userBean, IS_NO_STAND_FLAG);

	}

	/**
	 * 插入 工艺单 和工艺单明细
	 * 
	 * @param param
	 */
	public void insertTechOrder(Map<String, Object> param,
			List<Map<String, Object>> childList) {

		// 工艺单
		insert("Goodsorder.insertTechOrder", param);
		// 子表
		this.batch4Update("Goodsorder.insertTechOrderDtl", childList);

	}

	/**
	 * 插入销售订单明细
	 * 
	 * @param parent
	 * @param chilList
	 */
	public void insertSaleChilds(Map<String, Object> parent,
			List<GoodsorderModelChld> chilList, UserBean userBean) {
		// 新增列表
		List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
		Map<String, String> IdMap = new HashMap<String, String>();
		// 原始单据新增列表
		List<Map<String, Object>> addOldList = new ArrayList<Map<String, Object>>();
		// 原始单据修改修改列表
		List<Map<String, Object>> updateOldList = new ArrayList<Map<String, Object>>();
		// 工艺单 明细
		List<Map<String, Object>> techList = new ArrayList<Map<String, Object>>();

		String pId = "";
		String pIdOther = "";
		String techId = "";
		String techNo = "";
		for (GoodsorderModelChld model : chilList) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("GOODS_ORDER_ID", parent.get("FROM_BILL_ID"));
			param.put("SALE_ORDER_DTL_ID", StringUtil.uuid32len());
			param.put("PRD_ID", model.getPRD_ID());
			param.put("PRD_NO", model.getPRD_NO());
			param.put("PRD_NAME", model.getPRD_NAME());
			param.put("PRD_SPEC", model.getPRD_SPEC());
			param.put("PRD_COLOR", model.getPRD_COLOR());
			param.put("BRAND", model.getBRAND());
			param.put("STD_UNIT", model.getSTD_UNIT());
			param.put("VOLUME", model.getVOLUME());
			param.put("SPCL_TECH_FLAG", model.getSPCL_TECH_FLAG());
			// 是否非标
			Integer IS_NO_STAND_FLAG = StringUtil.getInteger(model
					.getIS_NO_STAND_FLAG());
			param.put("IS_NO_STAND_FLAG", IS_NO_STAND_FLAG);
			Double PRICE = StringUtil.getDouble(model.getPRICE());
			Double DECT_RATE = StringUtil.getDouble(model.getDECT_RATE());
			Double DECT_PRICE = StringUtil.getDouble(model.getDECT_PRICE());
			Double ORDER_AMOUNT = StringUtil.getDouble(model.getORDER_AMOUNT());
			Double ORDER_NUM = StringUtil.getDouble(model.getORDER_NUM());
			param.put("PRICE", PRICE);
			param.put("DECT_RATE", DECT_RATE);
			param.put("DECT_PRICE", DECT_PRICE);
			param.put("ORDER_NUM", ORDER_NUM);
			param.put("ORDER_AMOUNT", ORDER_AMOUNT);
			param.put("REBATE_PRICE", model.getREBATE_PRICE()); // 返利单价
			param.put("SPCL_TECH_ID", model.getSPCL_TECH_ID()); // 特殊工艺ID
			param.put("GOODS_ORDER_NO", parent.get("FROM_BILL_NO")); // 订货订单NO
			param.put("ADVC_SEND_DATE", model.getADVC_SEND_DATE());
			// param.put("FACTORY_ID", model.getFACTORY_ID());
			// param.put("FACTORY_NAME", model.getFACTORY_NAME());
			// param.put("SHIP_POINT_ID", parent.get("SHIP_POINT_ID"));
			// param.put("SHIP_POINT_NAME",parent.get("SHIP_POINT_NAME"));
			// param.put("REMARK", "");
			param.put("DEL_FLAG", StringUtil
					.getInteger(BusinessConsts.DEL_FLAG_COMMON));
			// param.put("SESSION_ID", "");
			Double TECH_PRICE_MULT = 0d;
			String GOODS_ORDER_DTL_ID = model.getGOODS_ORDER_DTL_ID();
			param.put("TECH_PRICE_MULT", TECH_PRICE_MULT);
			param.put("FROM_BILL_DTL_ID", GOODS_ORDER_DTL_ID);
			param.put("CANCEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			param.put("CREDIT_FREEZE_PRICE", model.getCREDIT_FREEZE_PRICE());// 信用冻结单价
			param.put("IS_BACKUP_FLAG", model.getIS_BACKUP_FLAG());// 是否抵库

			// 如果是非标 订单要拆分为两条销售订单
			if (1 == IS_NO_STAND_FLAG) {
				pId = IdMap.get("pId");
				if (StringUtil.isEmpty(pId)) {
					pId = StringUtil.uuid32len();
					IdMap.put("pId", pId);
				}
				param.put("SALE_ORDER_ID", pId);

				// 如果是 非标订单 还要生成 工艺单
				techId = IdMap.get("techId");
				if (StringUtil.isEmpty(techId)) {
					techId = StringUtil.uuid32len();
					IdMap.put("techId", techId);
				}

				techNo = IdMap.get("techNo");
				if (StringUtil.isEmpty(techNo)) {
					techNo = LogicUtil.getBmgz("ERP_TECH_ORDER_NO");
					IdMap.put("techNo", techNo);
				}

				// 如果是 非标订单 还要生成 工艺单
				parent.put("TECH_ORDER_ID", techId);
				parent.put("TECH_ORDER_NO", techNo);

				param.put("TECH_ORDER_ID", parent.get("TECH_ORDER_ID"));
				param.put("TECH_ORDER_DTL_ID", StringUtil.uuid32len());// 工艺单明细ID
				param.put("IS_CAN_PRD_FLAG", BusinessConsts.INTEGER_1);// 默认为1可生产
				// 来源单据编号 SALE_ORDER_DTL_ID
				techList.add(param);

			} else {
				pIdOther = IdMap.get("pIdOther");
				if (StringUtil.isEmpty(pIdOther)) {
					pIdOther = StringUtil.uuid32len();
					IdMap.put("pIdOther", pIdOther);
				}
				param.put("SALE_ORDER_ID", pIdOther);
			}
			addList.add(param);
			// 更新货新增 订货订单明细
			if (StringUtil.isEmpty(GOODS_ORDER_DTL_ID)) {
				param.put("GOODS_ORDER_DTL_ID", StringUtil.uuid32len());
				addOldList.add(param);
			} else {
				param.put("GOODS_ORDER_DTL_ID", model.getGOODS_ORDER_DTL_ID());
				updateOldList.add(param);
			}
		}

		// 非标订单 同时生产 工艺单
		if (!StringUtil.isEmpty(pId)) {
			parent.put("SALE_ORDER_ID", pId);
			parent.put("SALE_ORDER_NO", LogicUtil.getBmgz("ERP_SALE_ORDER_NO"));
			parent.put("BILL_TYPE", BusinessConsts.UN_STANDARD); // 非标
			parent.put("STATE", BusinessConsts.UNCOMMIT);// 状态为 未提交
			insertSaleOrder(parent, userBean, "1");

			// 插入 工艺单
			parent.put("STATE", BusinessConsts.COMMIT);// 状态是“提交” 2014-3-4 改 之前为
														// 状态为 "待审核"
			insertTechOrder(parent, techList);
		}

		// 生成标准订单
		if (!StringUtil.isEmpty(pIdOther)) {
			parent.put("SALE_ORDER_ID", pIdOther);
			parent.put("SALE_ORDER_NO", LogicUtil.getBmgz("ERP_SALE_ORDER_NO"));
			parent.put("BILL_TYPE", BusinessConsts.STANDARD); // 标准
			parent.put("STATE", BusinessConsts.PASS);// modify by zzb 2014-07-14
														// 改为‘审核通过’ 之前的状态为-状态为
														// 未提交
			insertSaleOrder(parent, userBean, "0");
		}

		if (!addList.isEmpty()) {
			// 插入销售订单明细
			this.batch4Update("Goodsorder.insertSaleOrderDtl", addList);
			// 反填销售订单ID到订货订单明细
			this.batch4Update("Goodsorder.updateGoodorderDtlByone_", addList);
		}

		if (!addOldList.isEmpty()) {
			this.batch4Update("Goodsorder.insertChld", addOldList);
		}
		if (!updateOldList.isEmpty()) {
			this.batch4Update("Goodsorder.updateChldById", updateOldList);
		}

	}

	/**
	 * 更新预订单的状态
	 * 
	 * @param GOODS_ORDER_ID
	 * @param reason
	 *            退货原因
	 */
	public void txUpdateAdvcOrder(String GOODS_ORDER_ID, String reason,
			UserBean userBean) throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("UPDATOR", userBean.getXTYHID());
		param.put("UPD_NAME", userBean.getXM());
		param.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
		param.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		param.put("RETURN_RSON", reason);// 将退货原因 写入 备注里面

		// 更新 预订单
		// update("Goodsorder.updateAdvcById", param);
		// 更新当前订货订单
		param.put("STATE", BusinessConsts.ORDER_BACK); // "退回"
		update("Goodsorder.updateById", param);
		Map orderMap = (Map) load("Goodsorderhd.loadById", GOODS_ORDER_ID);
		String BILL_TYPE = (String)orderMap.get("BILL_TYPE");
		if(!"赠品订货".equals(BILL_TYPE)){
			// 更新信用冻结单价
			LogicUtil.updFreezeCredit(BusinessConsts.ACCT_GOODS_ORDER1_CONF_ID,
					GOODS_ORDER_ID);
			LogicUtil.inertOrderCreditJournal(BusinessConsts.ACCT_GOODS_ORDER1_CONF_ID,
					GOODS_ORDER_ID);
			BigDecimal isUseRebate = (BigDecimal) orderMap.get("IS_USE_REBATE");
			if ("1".equals(isUseRebate.toString())) {
				LogicUtil.updateRebate(BusinessConsts.FL_GOODS_ORDER_CONF_ID,
						GOODS_ORDER_ID);
				LogicUtil.insRebateJournal(BusinessConsts.FL_GOODS_ORDER_CONF_ID,
						GOODS_ORDER_ID);
			}
		}
		
//		txUpSpclEditFlag(GOODS_ORDER_ID,BusinessConsts.YJLBJ_FLAG_FALSE);
		this.update("Goodsorder.updateCREDIT_FREEZE_PRICE", GOODS_ORDER_ID);
		txUpSpclFlag(GOODS_ORDER_ID);
		//插入生命周期
		Map<String, String> params = new HashMap<String, String>();
		String GOODS_ORDER_TRACE_ID = StringUtil.uuid32len();
		String GOODS_ORDER_NO = StringUtil.nullToSring(orderMap.get("GOODS_ORDER_NO"));
		params.put("GOODS_ORDER_TRACE_ID", GOODS_ORDER_TRACE_ID);
		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		params.put("BILL_NO", GOODS_ORDER_NO);// 订货订单的NO
		params.put("BILL_TYPE", BILL_TYPE);// 订货订单的type
		params.put("ACTION_NAME", "总部退回");// 订货订单处理
//		params.put("TRACE_URL", BusinessConsts.ORDER_BILL_ACTION_TRACE_URL
//				+ GOODS_ORDER_ID);
		params.put("DEAL_PSON_NAME", userBean.getXM());
		params.put("STATE", BusinessConsts.ORDER_BACK);// 总部退回
		insert("Goodsorder.insertOrderTrack", params);
		 
		
	}

	/**
	 * 取消 订货
	 * 
	 * @param GOODS_ORDER_ID
	 *            订单ID
	 * @param mxIds
	 *            明细IDS
	 * @param remark
	 *            取消原因
	 * @param isAll
	 *            是否全部 取消 true 全部取消
	 */
	public void updateOrderClose(String GOODS_ORDER_ID, String mxIds,
			String remark, boolean isAll, UserBean userBean) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("UPDATOR", userBean.getXTYHID());
		param.put("UPD_NAME", userBean.getXM());
		param.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
		param.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		param.put("REMARK", remark);
		param.put("GOODS_ORDER_DTL_IDS", mxIds);

		if (isAll) {
			// 跟新 订货订单的 状态 为 “制作”
			param.put("STATE", BusinessConsts.STATE_MAKE);// 状态“制作”
			update("Goodsorder.updateById", param);
		}

		param.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		// 删除子表
		update("Goodsorder.deleteChldByIds", param);
	}

	/**
	 * * 根据主表Id查询生命周期子表记录
	 * 
	 * @param GOODS_ORDER_ID
	 *            the GOODS_ORDER_ID
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsorderhdModelTrace> traceQuery(String GOODS_ORDER_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		return this.findList("Goodsorder.queryTraceByFkId", params);
	}

	/**
	 * 合并提交查询
	 * 
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the i list page
	 */
	public IListPage pageMergeQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Goodsorder.pageMergeQuery",
				"Goodsorder.pageMergeCount", params, pageNo);
	}

	/**
	 * 合并提交
	 * 
	 * @param MERGER_GOODS_ORDER_IDS 要合并单据的ID
	 * @param MERGER_REMARKS 合并的主表备注
	 * @param model 主表信息
	 * @param childModelList 页面穿来的明细
	 * @param userBean
	 */

	@SuppressWarnings("unchecked")
	public Map<String, Object> txMergerBill(String MERGER_GOODS_ORDER_IDS,String MERGER_REMARKS,
			GoodsorderModel model, List<GoodsorderModelChld> childModelList,
			UserBean userBean) {
		Map<String, String> paramMap = new HashMap<String, String>();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// paramMap.put("GOODS_ORDER_IDS", MERGER_GOODS_ORDER_IDS);
		// List<GoodsorderModelChld> childList =
		// this.findList("Goodso5rder.queryMergeChild", paramMap);
		if(StringUtil.isEmpty(MERGER_REMARKS)){
			MERGER_REMARKS = model.getREMARK();
		}
		model.setREMARK(MERGER_REMARKS);//备注
		
		String GOODS_ORDER_DTL_ID_BZ = "";
		String GOODS_ORDER_DTL_ID_FB = "";
		String PRD_SUIT_FLAG_IDS = "";
		List<Map<String, String>> updateDtlList = new ArrayList<Map<String, String>>();
		// 先遍历 把非标和标准的货品 分类
		for (GoodsorderModelChld child : childModelList) {
			String IS_NO_STAND_FLAG = child.getIS_NO_STAND_FLAG();
			String PRD_SUIT_FLAG = child.getPRD_SUIT_FLAG();//产品套件标记1->套件
		
			if (StringUtil.isEmpty(IS_NO_STAND_FLAG) || "0".equals(IS_NO_STAND_FLAG)) {
				if("1".equals(PRD_SUIT_FLAG)){
					PRD_SUIT_FLAG_IDS += "'" + child.getGOODS_ORDER_DTL_ID() + "',";
				}else{
					GOODS_ORDER_DTL_ID_BZ += "'" + child.getGOODS_ORDER_DTL_ID() + "',";
				}
			}
			if ("1".equals(IS_NO_STAND_FLAG)) { 
				 GOODS_ORDER_DTL_ID_FB += "'" + child.getGOODS_ORDER_DTL_ID()+ "',";
			}
			// 将抵库标记放入map
			Map<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("GOODS_ORDER_DTL_ID", child.getGOODS_ORDER_DTL_ID());
			tempMap.put("IS_BACKUP_FLAG", child.getIS_BACKUP_FLAG());
			updateDtlList.add(tempMap);
		}
		// 先更新 订货订单的是否抵库
		this.batch4Update("Goodsorder.updateChldById", updateDtlList);
		System.out.println(GOODS_ORDER_DTL_ID_BZ);
		System.out.println(GOODS_ORDER_DTL_ID_FB);

		String FROM_BILL_NO = model.getGOODS_ORDER_NO();
		if (MERGER_GOODS_ORDER_IDS.indexOf(",") != -1) {
			FROM_BILL_NO = "合并订单";
		}

		Map<String, Object> params = new HashMap<String, Object>();
		
		List<Map<String, Object>> megerBZList = new ArrayList<Map<String, Object>>();
		//标准货品
		if (!StringUtil.isEmpty(GOODS_ORDER_DTL_ID_BZ)) {
			GOODS_ORDER_DTL_ID_BZ = GOODS_ORDER_DTL_ID_BZ.substring(0, GOODS_ORDER_DTL_ID_BZ.length() - 1);
			params.put("GOODS_ORDER_DTL_IDS", GOODS_ORDER_DTL_ID_BZ);
			// 合并货品只包含货品信息
			megerBZList = this.findList("Goodsorder.mergeChild", params);
		}
		
	   //合并 套件的货品
	   if(!StringUtil.isEmpty(PRD_SUIT_FLAG_IDS)){
		    PRD_SUIT_FLAG_IDS = PRD_SUIT_FLAG_IDS.substring(0,PRD_SUIT_FLAG_IDS.length()-1);
		    //计算冻结单价的百分比
		    GoodsorderModelChld child = childModelList.get(0);
		    Double DECT_PRICE = StringUtil.getDouble(child.getDECT_PRICE());
			Double CREDIT_FREEZE_PRICE = StringUtil.getDouble(child.getCREDIT_FREEZE_PRICE());
			Double PAY_RATE = CREDIT_FREEZE_PRICE/DECT_PRICE;
			PAY_RATE = Math.round(PAY_RATE * 100)/100.00; 
			
		    params.put("PRD_SUIT_FLAG_IDS", PRD_SUIT_FLAG_IDS);
		    params.put("CHANN_ID", model.getORDER_CHANN_ID());
		    params.put("PAY_RATE", PAY_RATE);
		    
			List<Map<String,Object>> megerSUITList = this.findList("Goodsorder.mergeChildBySuitFlag", params);
			//把两个list合并
			if(!megerSUITList.isEmpty()){
				megerBZList.addAll(megerSUITList);
			}
			
			if(StringUtil.isEmpty(GOODS_ORDER_DTL_ID_BZ)){
				GOODS_ORDER_DTL_ID_BZ = PRD_SUIT_FLAG_IDS;
			}else{
				GOODS_ORDER_DTL_ID_BZ = GOODS_ORDER_DTL_ID_BZ+","+PRD_SUIT_FLAG_IDS;
			}
			
		}
	   
	   //生成标准订单
	   if(!megerBZList.isEmpty()){
		   inserSaleOrder(model, "0", megerBZList,GOODS_ORDER_DTL_ID_BZ,
					FROM_BILL_NO, userBean);
			params.clear();
	   }
		
		
		
		// 非标订单
		if (!StringUtil.isEmpty(GOODS_ORDER_DTL_ID_FB)) {
			GOODS_ORDER_DTL_ID_FB = GOODS_ORDER_DTL_ID_FB.substring(0,
					GOODS_ORDER_DTL_ID_FB.length() - 1);
			params.put("GOODS_ORDER_DTL_IDS", GOODS_ORDER_DTL_ID_FB);
			// 合并货品只包含货品信息
			List<Map<String, Object>> megerFBList = this.findList(
					"Goodsorder.mergeChild", params);
			returnMap = inserSaleOrder(model, "1", megerFBList,
					GOODS_ORDER_DTL_ID_FB, FROM_BILL_NO, userBean);
		}

		// 更新 订货订单 状态
		paramMap.put("STATE", BusinessConsts.DONE);// 已处理
		paramMap.put("UPDATOR", userBean.getXTYHID());
		paramMap.put("UPD_NAME", userBean.getXM());
		paramMap.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
		paramMap.put("AUDIT_ID", userBean.getXTYHID());
		paramMap.put("AUDIT_NAME", userBean.getXM());
		paramMap.put("AUDIT_TIME", BusinessConsts.UPDATE_TIME);
		paramMap.put("GOODS_ORDER_IDS", MERGER_GOODS_ORDER_IDS);
		this.update("Goodsorder.updateByIdS", paramMap);

		// 插入订货订单的生命周期
		String[] ids = MERGER_GOODS_ORDER_IDS.replaceAll("'", "").split(",");
		for (int i = 0; i < ids.length; i++) {
			insertGoodorderTack(ids[i], null, null, userBean);
		}

		return returnMap;
	}

	/**
	 * 合并后生成 销售订单
	 * 
	 * @param model  订货订单
	 * @param IS_NO_STAND_FLAG 是否非标 1->非标
	 * @param mergerList 合并的货品list
	 * @param userBean
	 */
	public Map<String, Object> inserSaleOrder(GoodsorderModel model,
			String IS_NO_STAND_FLAG, List<Map<String, Object>> mergerList,
			String GOODS_ORDER_DTL_IDS, String FROM_BILL_NO, UserBean userBean) {

		Map<String, Object> parent = new HashMap<String, Object>();
		parent.put("SALE_ORDER_ID", StringUtil.uuid32len());
		
		//modify by zzb 2014-10-14 订单只使用一个编号
		String GOODS_ORDER_NO = model.getGOODS_ORDER_NO();
		//是否使用唯一编号
		if("1".equals(Consts.USER_ONLY_ORDER_NO)){
			String IS_HAVE_SPECL = model.getIS_HAVE_SPECL();
			if("1".equals(IS_HAVE_SPECL)){
				if("1".equals(IS_NO_STAND_FLAG)){
					parent.put("SALE_ORDER_NO", GOODS_ORDER_NO+"-02");
				}else{
					parent.put("SALE_ORDER_NO", GOODS_ORDER_NO+"-01");
				}
			}else{
				parent.put("SALE_ORDER_NO", GOODS_ORDER_NO);
			}
		}else{
			parent.put("SALE_ORDER_NO", LogicUtil.getBmgz("ERP_SALE_ORDER_NO"));
		}
		
		
		parent.put("FROM_BILL_NO", FROM_BILL_NO);
        if("合并订单".equals(FROM_BILL_NO)){
        	GOODS_ORDER_NO = "";
        }else{
        	parent.put("FROM_BILL_ID",model.getGOODS_ORDER_ID());
        }
        Map<String,String> wareaMap=(Map<String, String>) this.load("Shopcar.getWareaInfo", model.getORDER_CHANN_ID());
        parent.putAll(wareaMap);
		parent.put("ORDER_CHANN_ID", model.getORDER_CHANN_ID());
		parent.put("ORDER_CHANN_NO", model.getORDER_CHANN_NO());
		parent.put("ORDER_CHANN_NAME", model.getORDER_CHANN_NAME());

		parent.put("SEND_CHANN_ID", userBean.getBASE_CHANN_ID());// 发货方是总部
		parent.put("SEND_CHANN_NO", userBean.getBASE_CHANN_NO());
		parent.put("SEND_CHANN_NAME", userBean.getBASE_CHANN_NAME());

		Integer IS_USE_REBATE = StringUtil.getInteger(model.getIS_USE_REBATE());
		parent.put("IS_USE_REBATE", IS_USE_REBATE);
		parent.put("RECV_CHANN_ID", model.getRECV_CHANN_ID());
		parent.put("RECV_CHANN_NO", model.getRECV_CHANN_NO());
		parent.put("RECV_CHANN_NAME", model.getRECV_CHANN_NAME());
		parent.put("PERSON_CON", model.getPERSON_CON());
		parent.put("TEL", model.getTEL());
		parent.put("AREA_ID", model.getAREA_ID());
		parent.put("AREA_NO", model.getAREA_NO());
		parent.put("AREA_NAME", model.getAREA_NAME());
		parent.put("RECV_ADDR_NO", model.getRECV_ADDR_NO());// 收货地址编号
		parent.put("RECV_ADDR", model.getRECV_ADDR());
		parent.put("SHIP_POINT_ID", model.getSHIP_POINT_ID());
		parent.put("SHIP_POINT_NAME", model.getSHIP_POINT_NAME());
		parent.put("REMARK", model.getREMARK());
		parent.put("CREATOR", userBean.getXTYHID());
		parent.put("CRE_NAME", userBean.getXM());
		parent.put("CRE_TIME", "sysdate");
		parent.put("DEL_FLAG", Integer.valueOf(BusinessConsts.DEL_FLAG_COMMON));
		parent.put("ORDER_DATE", model.getORDER_DATE());// 订货日期
		parent.put("DEPT_ID", userBean.getBMXXID());
		parent.put("DEPT_NAME", userBean.getBMMC());
		parent.put("ORG_ID", userBean.getJGXXID());
		parent.put("ORG_NAME", userBean.getJGMC());
		parent.put("LEDGER_ID", userBean.getLoginZTXXID());
		parent.put("LEDGER_NAME", userBean.getLoginZTMC());

		// 非标订单对应的工艺单
		if ("1".equals(IS_NO_STAND_FLAG)) {
			String ERP_TECH_ORDER_ID = StringUtil.uuid32len();
			String ERP_TECH_ORDER_NO = LogicUtil.getBmgz("ERP_TECH_ORDER_NO");
			// 如果是 非标订单 还要生成 工艺单
			parent.put("TECH_ORDER_ID", ERP_TECH_ORDER_ID);
			parent.put("TECH_ORDER_NO", ERP_TECH_ORDER_NO);
		}
		
		String BILL_TYPE = model.getBILL_TYPE();
	        
		// 新增列表
		List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
        int index = 0;
		for (Map<String, Object> prdMap : mergerList) {
			Map<String, Object> param = new HashMap<String, Object>();
			String SALE_ORDER_DTL_ID = StringUtil.uuid32len();
			param.put("GOODS_ORDER_NO", GOODS_ORDER_NO);
			param.put("SALE_ORDER_DTL_ID", SALE_ORDER_DTL_ID);
			param.put("SALE_ORDER_ID", parent.get("SALE_ORDER_ID"));
			param.put("PRD_ID", prdMap.get("PRD_ID"));
			param.put("PRD_NO", prdMap.get("PRD_NO"));
			param.put("PRD_NAME", prdMap.get("PRD_NAME"));
			param.put("PRD_SPEC", prdMap.get("PRD_SPEC"));
			param.put("PRD_COLOR", prdMap.get("PRD_COLOR"));
			param.put("BRAND", prdMap.get("BRAND"));
			param.put("STD_UNIT", prdMap.get("STD_UNIT"));
			param.put("VOLUME", prdMap.get("VOLUME"));
			param.put("FROM_BILL_DTL_ID", prdMap.get("GOODS_ORDER_DTL_ID"));
			param.put("PRODUCT_REMARK", prdMap.get("PRODUCT_REMARK"));
			param.put("NEW_SPEC", prdMap.get("NEW_SPEC"));
			param.put("NEW_COLOR", prdMap.get("NEW_COLOR"));
			
			int IS_FREE_FLAG = 0;//是否赠品标记 1->是赠品
			if("赠品订货".equals(BILL_TYPE)){
				IS_FREE_FLAG = 1;
			}else{
				//套件货品此标记为0表示是赠品， 
				String MAIN_BOM_FLAG = StringUtil.nullToSring(prdMap.get("MAIN_BOM_FLAG"));
				//货品套件标记 1->是套件货品
				String PRD_SUIT_FLAG = StringUtil.nullToSring(prdMap.get("PRD_SUIT_FLAG"));
				//是套件货品而且MAIN_BOM_FLAG标记为0的表示赠品
				if("1".equals(PRD_SUIT_FLAG) && "0".equals(MAIN_BOM_FLAG)){
					IS_FREE_FLAG = 1;
				}else{
					IS_FREE_FLAG = 0;
				}
			}
			
			param.put("IS_FREE_FLAG", IS_FREE_FLAG);//是否赠品标记 1->是赠品
			param.put("PRICE", prdMap.get("PRICE"));
			param.put("DECT_RATE", prdMap.get("DECT_RATE"));
			param.put("DECT_PRICE", prdMap.get("DECT_PRICE"));
			param.put("ORDER_AMOUNT", prdMap.get("ORDER_AMOUNT"));
			param.put("CREDIT_FREEZE_PRICE", prdMap.get("CREDIT_FREEZE_PRICE"));// 信用冻结单价
			 
			param.put("ORDER_NUM", prdMap.get("ORDER_NUM"));
			param.put("REBATE_PRICE", prdMap.get("REBATE_PRICE")); // 返利单价
			param.put("SPCL_TECH_ID", prdMap.get("SPCL_TECH_ID")); // 特殊工艺ID
			param.put("IS_NO_STAND_FLAG", IS_NO_STAND_FLAG); // 是否非标
			param.put("REMARK",prdMap.get("REMARK"));//备注
			param.put("ADVC_SEND_DATE", StringUtil.nullToSring(prdMap.get("ADVC_SEND_DATE")));//预计交货日期
			param.put("DEL_FLAG", StringUtil
					.getInteger(BusinessConsts.DEL_FLAG_COMMON));
			// param.put("SESSION_ID", "");
			Double TECH_PRICE_MULT = 0d;
			param.put("TECH_PRICE_MULT", TECH_PRICE_MULT);
			param.put("CANCEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			
			//自动抵库标记 add by zzb 2015-5-4
			String AUTO_BACKUP_FLG = StringUtil.nullToSring(prdMap.get("AUTO_BACKUP_FLG"));
			if("1".equals(AUTO_BACKUP_FLG)){
				// 将抵库数据拿出来
				param.put("IS_BACKUP_FLAG",1);// 是否抵库
			}
			
			// 将抵库数据拿出来
			//param.put("IS_BACKUP_FLAG", prdMap.get("IS_BACKUP_FLAG"));// 是否抵库

			if ("1".equals(IS_NO_STAND_FLAG)) {
				param.put("TECH_ORDER_ID", parent.get("TECH_ORDER_ID"));
				param.put("TECH_ORDER_DTL_ID", StringUtil.uuid32len());// 工艺单明细ID
				param.put("IS_CAN_PRD_FLAG", BusinessConsts.INTEGER_1);// 默认为1可生产
			}
			
			param.put("SHIP_POINT_ID", model.getSHIP_POINT_ID());//生产基地
			param.put("SHIP_POINT_NAME", model.getSHIP_POINT_NAME());
			param.put("IS_TOSS_FLAG", BusinessConsts.INTEGER_0);//抛砖标记默认为  0
			index++;
			param.put("ROW_NO", index);//行号
			addList.add(param);
		}

		if ("1".equals(IS_NO_STAND_FLAG)) {
			// 生成非标订单
			parent.put("BILL_TYPE", BusinessConsts.UN_STANDARD); // 非标准
			parent.put("STATE", "待核价");// 状态为 待核价

			// 插入 销售订单 与 插入生命周期
			insertSaleOrder(parent, userBean, IS_NO_STAND_FLAG);

			// 插入 工艺单 和工艺单明细
			parent.put("STATE", BusinessConsts.COMMIT);// 状态是“提交” 2014-3-4 改 之前为
														// 状态为 "待审核"
			if (!addList.isEmpty()) {
				insertTechOrder(parent, addList);
			}
		} else {
			
			if("赠品订货".equals(BILL_TYPE)){
				parent.put("BILL_TYPE", BILL_TYPE);
			}else if("返利订货".equals(BILL_TYPE)){
	        	parent.put("BILL_TYPE", BILL_TYPE);
	        }else{
	        	parent.put("BILL_TYPE", BusinessConsts.STANDARD); // 标准
	        }
			parent.put("STATE", BusinessConsts.PASS);// modify by zzb 2014-07-14
														// 改为‘审核通过’ 之前的状态为-状态为
														// 未提交
			// 插入 销售订单 与 插入生命周期
			insertSaleOrder(parent, userBean, IS_NO_STAND_FLAG);
		}

		// 插入销售订单明细
		if (!addList.isEmpty()) {
			this.batch4Update("Goodsorder.insertSaleOrderDtl", addList);
		}

		// 更新 订货订单明细的销售订单ID
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("GOODS_ORDER_DTL_IDS", GOODS_ORDER_DTL_IDS);
		paramMap.put("SALE_ORDER_ID", parent.get("SALE_ORDER_ID"));
		this.update("Goodsorder.updateGoodorderDtl_", paramMap);

		return parent;
	}

	/**
	 * 插入销售订单生命周期
	 * 
	 * @param parent
	 *            主表字段信息
	 * @param userBean
	 * @param IS_NO_STAND_FLAG
	 *            是否非标 1->非标
	 */
	public void inertSaleordertrace(Map<String, Object> parent,
			UserBean userBean, String IS_NO_STAND_FLAG) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("GOODS_ORDER_TRACE_ID", StringUtil.uuid32len());
		paramMap.put("SALE_ORDER_ID", parent.get("SALE_ORDER_ID"));
		paramMap.put("BILL_NO", parent.get("SALE_ORDER_NO"));
		paramMap.put("DEAL_PSON_NAME", userBean.getXM());
		paramMap.put("DEAL_TIME", BusinessConsts.UPDATE_TIME);
		paramMap.put("STATE", parent.get("STATE"));//
		// paramMap.put("TRACE_URL","");

		if ("1".equals(IS_NO_STAND_FLAG)) {
			paramMap.put("BILL_TYPE", "非标销售订单");
			paramMap.put("ACTION_NAME", "非标订单制作 ");
		} else {
			paramMap.put("BILL_TYPE", "标准销售订单");
			paramMap.put("ACTION_NAME", "标准订单制作 ");
		}

		this.insert("Goodsorder.insertOrderTrack", paramMap);
	}

	public List<GoodsorderModelChld> childQueryMore(String GOODS_ORDER_IDS) {
		Map<String, String> params = new HashMap<String, String>();
		// GOODS_ORDER_IDS = "'" + GOODS_ORDER_IDS.replaceAll(",", "','")+"'";
		params.put("GOODS_ORDER_IDS", GOODS_ORDER_IDS);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Goodsorder.queryChldByFkMore", params);
	}

	/**
	 * 发消息
	 * 
	 * @param TECH_ORDER_NO
	 * @param userBean
	 */
	@SuppressWarnings("unchecked")
	public void txSendMessage(String TECH_ORDER_NO, UserBean userBean) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			String msg = "您有单号为：[" + TECH_ORDER_NO + "]的工艺单，请尽快处理";
			List<Map<String, String>> list = this.findList(
					"Goodsorder.queryReceiver", params);
			String XTYHIDS = "";
			if (null != list) {
				for (Map<String, String> map : list) {
					XTYHIDS += "'"+map.get("XTYHID") + "',";
				}
			}
			if (!"".equals(XTYHIDS)) {
				XTYHIDS = XTYHIDS.substring(0, XTYHIDS.length() - 1);
			}

			Map<String, String> sendMap = new HashMap<String, String>();
			sendMap.put("YHBH", userBean.getYHBH());
			sendMap.put("SENDID", userBean.getXTYHID());
			sendMap.put("SENDER", userBean.getYHM());
			sendMap.put("MSGINFO", msg);
			sendMap.put("XTYHID", XTYHIDS);
			sendMap.put("BMXXID", "''");
			sendMap.put("JGXXID", "''");

			this.insert("MESSAGE.insertMessage", sendMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("给工艺工程部发消息失败，单号：" + TECH_ORDER_NO);
		}

	}

	/**
	 * 查看库存
	 * 
	 * @param params ORDER_CHANN_NO 订货方编号  SHIP_POINT_ID 生产基地ID
	 * @return
	 */
	public Map<String, Object> queryStore(Map<String, String> params,
			UserBean userBean) {
		List<Map<String, Object>> list = this.findList("Goodsorder.queryStore",params);
		Float U9_STORE_NUM = 0f;   //U9反填库存
		Float TOTAL_STORE = 0f;    //可用库存
		Float DM_STORE = 0f;   //DM抵库库存

		try {
			if (null != list && !list.isEmpty()) {
				DM_STORE = StringUtil.getDouble( StringUtil.nullToSring(list.get(0).get("TOTAL_STORE")))
						.floatValue();
			}

			String jsonData = LogicUtil.queryStoreAcct(null,params.get("PRD_NO"),params.get("SHIP_POINT_ID"));
			List<StoreModel> result = new Gson().fromJson(jsonData,
					new TypeToken<ArrayList<StoreModel>>() {
					}.getType());
			
			for(int i=0;result!=null && i<result.size();i++){
				StoreModel model = result.get(i);
				String custNo =  model.getCustNo();
				if(StringUtil.isEmpty(custNo)){
					Float storeNum = model.getStoreNum();
					if (null == storeNum) {
						U9_STORE_NUM += 0f;
					} else {
						U9_STORE_NUM += storeNum;
					}	
				}
			
			}
			TOTAL_STORE = U9_STORE_NUM - DM_STORE;
			if(TOTAL_STORE<=0){
				TOTAL_STORE = 0f;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("TOTAL_STORE", TOTAL_STORE);
		map.put("U9_STORE_NUM", U9_STORE_NUM);
		map.put("DM_STORE", DM_STORE);
		return map;
	}
	
	
	/**
	 * 修改备注
	 * @param GOODS_ORDER_ID
	 * @param REMARK
	 * @param userBean
	 */
	public void modifyRemark(String GOODS_ORDER_ID,String REMARK,UserBean userBean){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		paramMap.put("REMARK", REMARK);// 
		paramMap.put("UPDATOR", userBean.getXTYHID());
		paramMap.put("UPD_NAME", userBean.getXM());
		paramMap.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
		txUpdateById(paramMap);
	}
	public List downQuery(Map<String,String> map){
		return this.findList("Goodsorder.downGetById", map);
	}
	//更新预订单转订货过来的特殊工艺为不可编辑
	public void txUpSpclEditFlag(String GOODS_ORDER_ID){
		Map<String,String> map=new HashMap<String, String>();
		//根据订单ID获取来自预订单的特殊工艺ID
		List<String> list=this.findList("Goodsorder.getAdvcSpclId", GOODS_ORDER_ID);
		StringBuffer str=new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			str.append("'").append(list.get(i)).append("',");
		}
		if(!StringUtil.isEmpty(str.toString())){
			str=InterUtil.replaceUpSql(str);
			map.put("SPCL_TECH_IDS", str.toString());
			map.put("TECH_NO_EDIT_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
			this.update("Goodsorder.txUpSpclEditFlag", map);
		}
//		map.put("TECH_NO_EDIT_FLAG", BusinessConsts.);
//		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
//		map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
//		if("1".equals(TECH_NO_EDIT_FLAG)){
//			this.update("Goodsorder.txUpSpclEditFlag", map);
//		}else{
//			
//		}
	}
	public void txUpSpclFlag(String GOODS_ORDER_ID)throws Exception{
		Map<String,String> map=new HashMap<String, String>();
		map.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		List<Map<String,String>> chldList=this.findList("Goodsorder.getChldIdAndSpclId", map);
		String SPCL_TECH_IDS="";
		for (Map<String, String> map2 : chldList) {
			int count=StringUtil.getInteger(this.load("Goodsorder.checkSpclId", map2));
			if(count<1){
				SPCL_TECH_IDS += "'" + map2.get("SPCL_TECH_ID") + "',";
			}
		}
		if(!StringUtil.isEmpty(SPCL_TECH_IDS)){
			SPCL_TECH_IDS = SPCL_TECH_IDS.substring(0,
					SPCL_TECH_IDS.length() - 1);
			Map<String,String> params=new HashMap<String, String>();
			params.put("SPCL_TECH_IDS", SPCL_TECH_IDS);
			params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
			params.put("TECH_NO_EDIT_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
			this.update("Goodsorder.updateSpclFlagById", params);
		}
	}
}