/**
 * prjName:喜临门营销平台
 * ucName:付款凭证确认
 * fileName:PaymentcmtServiceImpl
*/
package com.hoperun.erp.sale.paymentclose.service.impl;
import java.util.HashMap;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.paymentclose.service.PaymentcloseService;
import com.hoperun.erp.sale.paymentcmt.model.PaymentcmtModel;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-25 09:50:09
 */
public class PaymentcloseServiceImpl extends BaseService implements PaymentcloseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo) {
		return this.pageQuery("Paymentcmt.pageQuery", "Paymentcmt.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Paymentcmt.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param PAYMENT_UPLOAD_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
        return update("Paymentcmt.delete", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Paymentcmt.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param PAYMENT_UPLOAD_ID
	 * @param PaymentcmtModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String PAYMENT_UPLOAD_ID, PaymentcmtModel model, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("AREA_ID",model.getAREA_ID());
		    params.put("AREA_NO",model.getAREA_NO());
		    params.put("REQ_PSON_ID",model.getREQ_PSON_ID());
		    params.put("REQ_PSON_NAME",model.getREQ_PSON_NAME());
		    params.put("PAYMENT_AMOUNT",model.getPAYMENT_AMOUNT());
		    params.put("AREA_NAME",model.getAREA_NAME());
		    params.put("TEL",model.getTEL());
		    params.put("PAYMENT_UPLOAD_ID",model.getPAYMENT_UPLOAD_ID());
		    params.put("CHANN_ID",model.getCHANN_ID());
		    params.put("PAYMENT_UPLOAD_NO",model.getPAYMENT_UPLOAD_NO());
		    params.put("PAYMENT_NO",model.getPAYMENT_NO());
		    params.put("CHANN_NO",model.getCHANN_NO());
		    params.put("CHANN_NAME",model.getCHANN_NAME());
		if(StringUtil.isEmpty(PAYMENT_UPLOAD_ID)){
			PAYMENT_UPLOAD_ID= StringUtil.uuid32len();
			params.put("PAYMENT_UPLOAD_ID", PAYMENT_UPLOAD_ID);
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
			params.put("STATE",BusinessConsts.JC_STATE_DEFAULT);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("PAYMENT_UPLOAD_ID", PAYMENT_UPLOAD_ID);
			txUpdateById(params);
			clearCaches(PAYMENT_UPLOAD_ID);
		}
	}
	/**
	 * @详细
	 * @param param PAYMENT_UPLOAD_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Paymentcmt.loadById", param);
	}

 
	/**
	 * 确认/打回
	 * 状态修改
	 */
	public boolean EditState(Map<String,String> map){
		return update("Paymentcmt.alterState", map);
	}
	
	/**
	 * 关闭
	 * @param PAYMENT_UPLOAD_ID 主表ID
	 * @param CHANN_ID 渠道ID
	 * @param PAYMENT_AMOUNT 凭证金额
	 * @param userBean
	 */
	public void txClosePayment(String PAYMENT_UPLOAD_ID,String CHANN_ID,String PAYMENT_AMOUNT,UserBean userBean){
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("PAYMENT_UPLOAD_ID", PAYMENT_UPLOAD_ID);
		params.put("CHANN_ID", CHANN_ID);
		params.put("PAYMENT_AMOUNT", PAYMENT_AMOUNT);
		String state=(String) this.load("Paymentcmt.getState",PAYMENT_UPLOAD_ID);
		if(!"确认".equals(state)){
			throw new ServiceException("该单据已被处理，现在为"+state+"状态");
		}
		//更新渠道的 付款凭证信用 
		update("Paymentcmt.updateChann", params);
		params.put("STATE","关闭");
		params.put("UPD_NAME",userBean.getXM());
		params.put("UPDATOR",userBean.getXTYHID());
		params.put("UPD_TIME","sysdate");
		//更新 付款 凭证
		update("Paymentcmt.alterState", params);
	}
	
	
}