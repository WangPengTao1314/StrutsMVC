package com.hoperun.erp.sale.storeout.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.erp.sale.storeout.model.ErpStoreoutChildPrdModel;
import com.hoperun.erp.sale.storeout.model.ErpStoreoutModel;
import com.hoperun.erp.sale.storeout.service.ErpStoreoutService;
import com.hoperun.sys.model.UserBean;

public class ErpStoreoutServiceImpl extends BaseService implements
		ErpStoreoutService {
	/**
	 * 查询并分页.
	 * 
	 * @param params
	 *            the params
	 * @param pageNo
	 *            the page no
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("ErpStoreout.pageQuery", "ErpStoreout.pageCount",
				params, pageNo);
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param DELIVER_ORDER_ID
	 *            主表ID
	 * 
	 * @return the list
	 */
	public List<ErpStoreoutChildPrdModel> childQuery(String DELIVER_ORDER_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("ErpStoreout.queryChldByFkId", params);
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param params
	 * @return the list
	 */
	public List<ErpStoreoutChildPrdModel> childQuery(Map<String, String> params) {
		return this.findList("ErpStoreout.queryChldByFkId", params);
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param params
	 * @return the list
	 */
	public List<ErpStoreoutChildPrdModel> childQueryByParentIDS(
			Map<String, String> params) {
		return this.findList("ErpStoreout.queryChldByFkIds", params);
	}

	/**
	 * @主表详细页面
	 * @param param
	 *            DELIVER_ORDER_ID
	 */
	public Map<String, String> load(String STOREOUT_ID) {
		return (Map<String, String>) this.load("ErpStoreout.loadById",
				STOREOUT_ID);
	}

	/**
	 * 编辑：新增
	 * 
	 * @param model
	 *            发运单
	 * @return the string
	 */
	public void txEdit(ErpStoreoutModel model, UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		// params.put("DELIVER_ORDER_ID", model.getDELIVER_ORDER_ID());
		// params.put("CHANN_TYPE", model.getCHANN_TYPE());
		// params.put("PRVD_ID", model.getPRVD_ID());
		// params.put("PRVD_NO", model.getPRVD_NO());
		// params.put("PRVD_NAME", model.getPRVD_NAME());
		// params.put("AREA_SER_ID", model.getAREA_SER_ID());
		// params.put("AREA_SER_NO", model.getAREA_SER_NO());
		// params.put("AREA_SER_NAME", model.getAREA_SER_NAME());
		// params.put("SEND_CHANN_ID", model.getSEND_CHANN_ID());
		// params.put("SEND_CHANN_NO", model.getSEND_CHANN_NO());
		// params.put("SEND_CHANN_NAME", model.getSEND_CHANN_NAME());
		// params.put("ADVC_SEND_DATE", model.getADVC_SEND_DATE());
		// params.put("REMARK", model.getREMARK());
		// params.put("APPCH_TIME", model.getAPPCH_TIME());
		// params.put("UPDATOR", userBean.getXTYHID());// 更新人id
		// params.put("UPD_NAME", userBean.getXM());// 更新人名称
		// params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);// 更新时间

		this.update("ErpStoreout.updateById", params);

	}

	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param GOODS_ORDER_DTL_IDs
	 *            the GOODS_ORDER_DTL_IDs
	 * 
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<ErpStoreoutChildPrdModel> loadChilds(Map<String, String> params) {
		return this.findList("ErpStoreout.queryChldByFkId", params);
	}

	@Override
	public void txUpdatChild(List<ErpStoreoutChildPrdModel> childList) {

	}

	public List downQuery(Map<String, String> map) {
		return this.findList("ErpStoreout.downQuery", map);
	}

	/**
	 * 关闭
	 * 
	 * @param STOREOUT_ID 出库单ID
	 * @param DELIVER_ORDER_ID 发运单ID
	 * @param U9_SM_NO U9单据编号
	 * @param userBean
	 */
	public void txClose(String STOREOUT_ID,String DELIVER_ORDER_ID,String U9_SM_NO, UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("STOREOUT_ID", STOREOUT_ID);
		//只有销售订单主表状态为‘待发货’或‘已发货’状态 才能删除
		List<Map<String,String>> list = this.findList("ErpStoreout.querySaleOrderState", params);
		if(null != list && !list.isEmpty()){
			for(Map<String,String> saleOrderMap : list){
				String STATE = saleOrderMap.get("STATE");
				if("待发货".equals(STATE) || "已发货".equals(STATE)){
					
				}else{
					throw new ServiceException("明细上销售订单["+saleOrderMap.get("SALE_ORDER_NO")+"]<br/>在当前状态下不能关闭");
				}
			}
		}
		String STOREIN_NOTICE_NO = U9_SM_NO+"-0";
		params.put("STOREOUT_ID", STOREOUT_ID);
		params.put("STOREIN_NOTICE_NO", STOREIN_NOTICE_NO);
		
		//如果入库单已经是‘已处理’状态，不允许关闭出库单
		int num = this.queryForInt("ErpStoreout.queryStoreinSate", params);
		if(num>0){
			throw new ServiceException("出库单相关的渠道入库单已处理，不允许关闭此单据");
		}
		
		params.put("UPD_NAME", userBean.getXM());
		params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_TIME", DateUtil.now());
		params.put("STATE", "关闭");
		// 1、把出库单状态改为关闭
		this.update("ErpStoreout.updateById", params);
	 
		//把销售订单的实出数量减掉
		this.update("ErpStoreout.updateSaleOrderDtlSendNum", params);
		//销售订单主表为‘已发货’的改为‘待发货’   行状态‘已发货’改为‘正常’
		params.put("STATE", "待发货");
		this.update("ErpStoreout.updateSaleOrderSate", params);
		this.update("ErpStoreout.updateSaleOrderDtlSate", params);
		
		params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
		//把发运单的实出数量减掉
		this.update("ErpStoreout.updateDeliverDtlSendNum", params);
		//删除入库通知单和明细  先删除明细
		this.delete("ErpStoreout.deleteStoreinNoticeDtl", params);
		this.delete("ErpStoreout.deleteStoreinNotice", params);
		
		//删除入库单和明细   先删除明细
		this.delete("ErpStoreout.deleteStoreinDtl", params);
		this.delete("ErpStoreout.deleteStorein", params);
		
	}

}
