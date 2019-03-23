/**
 * prjName:喜临门营销平台
 * ucName:客户退货结算
 * fileName:RestatementsServiceImpl
*/
package com.hoperun.drp.finance.restatements.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.finance.restatements.model.RestatementsModel;
import com.hoperun.drp.finance.restatements.service.RestatementsService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-12 15:21:43
 */
public class RestatementsServiceImpl extends BaseService implements RestatementsService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Restatements.pageQuery", "Restatements.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Restatements.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param RETURN_STATEMENTS_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
        return update("Restatements.delete", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Restatements.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param RETURN_STATEMENTS_ID
	 * @param RestatementsModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String STATEMENTS_ID, RestatementsModel model, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
			params.put("ADVC_ORDER_ID",model.getADVC_ORDER_ID());
		    params.put("ADVC_ORDER_NO",model.getADVC_ORDER_NO());
		    params.put("TERM_ID", model.getTERM_ID());
		    params.put("TERM_NO",model.getTERM_NO());
		    params.put("TERM_NAME",model.getTERM_NAME());
		    params.put("SALE_DATE",model.getSALE_DATE());
		    params.put("SALE_PSON_NAME",model.getSALE_PSON_NAME());
		    params.put("CUST_NAME",model.getCUST_NAME());
		    params.put("TEL",model.getTEL());
		    params.put("ADVC_AMOUNT",model.getADVC_AMOUNT());
		    params.put("ADD_ADVC_AMOUNT",model.getADD_ADVC_AMOUNT());
		    params.put("RECV_LAST_AMOUNT",model.getRECV_LAST_AMOUNT());
		    params.put("STATEMENTS_AMOUNT",model.getSTATEMENTS_AMOUNT());
		    params.put("REMARK",model.getREMARK());
		if(StringUtil.isEmpty(STATEMENTS_ID)){
			STATEMENTS_ID= StringUtil.uuid32len();
			params.put("STATEMENTS_ID", STATEMENTS_ID);
			params.put("STATEMENTS_NO",LogicUtil.getBmgz("DRP_STATEMENTS_NO"));
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CREATOR",userBean.getXTYHID());
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("DEPT_ID",userBean.getBMXXID());
		    params.put("DEPT_NAME",userBean.getBMMC());
		    params.put("ORG_ID",userBean.getJGXXID());
		    params.put("ORG_NAME",userBean.getJGMC());
		    params.put("LEDGER_ID",userBean.getLoginZTXXID());
		    params.put("LEDGER_NAME",userBean.getLoginZTMC());
		    params.put("STATE",BusinessConsts.UNCOMMIT);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		    params.put("BILL_TYPE",BusinessConsts.BILL_TYPE_RETURN);
		    txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("STATEMENTS_ID", STATEMENTS_ID);
			txUpdateById(params);
			clearCaches(STATEMENTS_ID);
		}
		
		
		//删除原有的明细表
		this.delete("Restatements.deleteDtl", params);
		//获取出货单明细表
		
		params.put("BILL_TYPE", BusinessConsts.STORE_IN_TYPE);
		params.put("ADVC_ORDER_ID", model.getADVC_ORDER_ID());
		
		List<Map<String,Object>>  outList =  this.findList("Restatements.queryStoreinDtl", params);
		
		if(outList!=null){
			for(Map<String,Object> outParamsMap : outList){
				Map<String,Object> mentdtlMap = new HashMap<String,Object>();
				mentdtlMap.put("STATEMENTS_DTL_ID", StringUtil.uuid32len());
				mentdtlMap.put("STATEMENTS_ID", STATEMENTS_ID);
				mentdtlMap.put("PRD_ID", outParamsMap.get("PRD_ID"));
				mentdtlMap.put("PRD_NO", outParamsMap.get("PRD_NO"));
				mentdtlMap.put("PRD_NAME", outParamsMap.get("PRD_NAME"));
				mentdtlMap.put("PRD_SPEC", outParamsMap.get("PRD_SPEC"));
				mentdtlMap.put("PRD_COLOR", outParamsMap.get("PRD_COLOR"));
				mentdtlMap.put("BRAND", outParamsMap.get("BRAND"));
				mentdtlMap.put("STD_UNIT", outParamsMap.get("STD_UNIT"));
				mentdtlMap.put("PRICE", outParamsMap.get("PRICE"));
				mentdtlMap.put("DECT_RATE", outParamsMap.get("DECT_RATE"));
				mentdtlMap.put("DECT_PRICE", outParamsMap.get("DECT_PRICE"));
				mentdtlMap.put("SEND_NUM", outParamsMap.get("REAL_NUM"));
				mentdtlMap.put("PAYABLE_AMOUNT", outParamsMap.get("DECT_AMOUNT"));
				mentdtlMap.put("REMARK", outParamsMap.get("REMARK"));
				mentdtlMap.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
				
				mentdtlMap.put("STOREOUTIN_ID", outParamsMap.get("STOREOUT_ID"));
				mentdtlMap.put("STOREOUTIN_NO", outParamsMap.get("STOREOUT_NO"));
				mentdtlMap.put("STATEMENTS_NUM", outParamsMap.get("REAL_NUM"));
				mentdtlMap.put("STATEMENTS_AMOUNT", outParamsMap.get("DECT_AMOUNT"));
				mentdtlMap.put("STOREOUTIN_DATE", outParamsMap.get("DEAL_TIME"));
				
				this.insert("Restatements.insertDtl", mentdtlMap);
			}
		}
	}
	/**
	 * @详细
	 * @param param RETURN_STATEMENTS_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Restatements.loadById", param);
	}
	
	@Override
	public List<Map<String, String>> queryChild(Map<String, String> params) {
		return this.findList("Restatements.queryStateDtl", params);
	}
	
	@Override
	public boolean txSub(Map<String, String> params) {
		String STATEMENTS_ID = params.get("STATEMENTS_ID");
		double allPayAmount = 0;
		
		Map<String,String> payMap = this.load(STATEMENTS_ID);
		params.put("STATEMENTS_ID", STATEMENTS_ID);
		params.put("STATE", BusinessConsts.STATE_IS_PAY);
		this.txUpdateById(params);
		
		//更新订单表信息
		Map<String,Object> opParam = new HashMap<String,Object>();
		opParam.put("ADVC_AMOUNT_FLAG",BusinessConsts.DEL_FLAG_DROP);
		opParam.put("ADVC_ORDER_ID", payMap.get("ADVC_ORDER_ID"));
		return update("Advcorder.updateById", opParam);
	}
}