package com.hoperun.drp.store.storeoutconfirm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.storeoutconfirm.model.StoreoutconfirmModelChld;
import com.hoperun.drp.store.storeoutconfirm.model.StoreoutconfirmModelGchld;
import com.hoperun.drp.store.storeoutconfirm.service.StoreoutconfirmService;
import com.hoperun.sys.model.UserBean;

public class StoreoutconfirmServiceImpl extends BaseService implements StoreoutconfirmService  {
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Storeoutconfirm.pageQuery", "Storeoutconfirm.pageCount",params, pageNo);
	}
	/**
	 * @详细
	 * @param param STOREOUT_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Storeoutconfirm.loadById", param);
	}
	/**
     * * 根据主表Id查询子表记录
     * @param STOREOUT_ID the STOREOUT_ID
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
    public List <StoreoutconfirmModelChld> childQuery(String STOREOUT_ID) {
        Map params = new HashMap();
        params.put("STOREOUT_ID", STOREOUT_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storeoutconfirm.queryChldByFkId", params);
    }
    public List <StoreoutconfirmModelGchld> GchildQuery(Map <String, String> params) {
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storeout.queryStorgChldByFkId", params);
    }
    /**
	 * * 明细数据编辑
	 * 
	 * @param STORE_ID
	 *            the STORE_ID
	 * @param modelList
	 *            the model list
	 * 
	 * @return true, if tx child edit
	 */
	public boolean txChildEdit(List<StoreoutconfirmModelChld> chldList,Map<String,String> map,String STOREOUT_ID,UserBean userBean) {
		// 修改列表
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		for (StoreoutconfirmModelChld model : chldList) {
			Map<String, String> params = new HashMap<String, String>();
			String RECV_NUM=model.getRECV_NUM();
			String REAL_NUM=model.getREAL_NUM();
			if(StringUtil.isEmpty(RECV_NUM)){
				throw new ServiceException("请填写收货数量");
			}
			if(StringUtil.isEmpty(REAL_NUM)){
				REAL_NUM="0";
			}
			if(Double.parseDouble(RECV_NUM)!=Double.parseDouble(REAL_NUM)){
				throw new ServiceException("收货数量与实际出库数量不相等");
			}
			params.put("RECV_NUM", RECV_NUM);
			params.put("STOREOUT_DTL_ID", model.getSTOREOUT_DTL_ID());
			updateList.add(params);
			}

		if (!updateList.isEmpty()) {
			this.batch4Update("Storeoutconfirm.updateChldById", updateList);
		}
		txUpdateById(map);
		//出库单出库总金额
//		double STOREOUT_AMOUNT=Double.parseDouble(load("Storeoutconfirm.getSTOREOUT_AMOUNTById",STOREOUT_ID).toString());
		//预订单已付款金额
//		double PAYED_TOTAL_AMOUNT=Double.parseDouble(load("Storeoutconfirm.getPAYED_TOTAL_AMOUNTById",STOREOUT_ID).toString());
//		Map<String,String> advMap=new HashMap<String,String>();
//		advMap.put("STOREOUT_ID", STOREOUT_ID);
		//去掉预订单修改多余状态
//		if(STOREOUT_AMOUNT==PAYED_TOTAL_AMOUNT){
//			advMap.put("STATE", BusinessConsts.STATE_IS_PAY);
//		}else{
//			advMap.put("STATE", "待结算");
//		}
//		this.update("Storeoutconfirm.upAdvState",advMap);
		//插预订单生命周期
		Map<String,String> entry = this.load(STOREOUT_ID);
    	Map<String,String> params=new HashMap<String,String>();
    	params.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
    	params.put("ADVC_SEND_REQ_ID",entry.get("FROM_BILL_ID"));
    	params.put("ACTION", "销售收货确认");
    	params.put("ACTOR", userBean.getXM());
    	params.put("BILL_NO", entry.get("STOREOUT_NO"));
    	params.put("REMARK", "已收货确认");
    	insert("Storeoutconfirm.insertAdvcOrderTrace", params);
		return true;
	}
	public void txUpdateById(Map<String,String> params) {
		 update("Storeoutconfirm.updateById", params);
	}

}
