/**
 * prjName:喜临门营销平台
 * ucName:付款凭证上传
 * fileName:PaymentupServiceImpl
*/
package com.hoperun.drp.sale.paymentup.service.impl;
import java.util.HashMap;
import java.util.Map;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.paymentup.model.PaymentupModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.sale.paymentup.service.PaymentupService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-23 10:25:58
 */
public class PaymentupServiceImpl extends BaseService implements PaymentupService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo) {
		return this.pageQuery("Paymentup.pageQuery", "Paymentup.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Paymentup.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param PAYMENT_UPLOAD_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
        return update("Paymentup.delete", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Paymentup.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param PAYMENT_UPLOAD_ID
	 * @param PaymentupModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String PAYMENT_UPLOAD_ID, PaymentupModel model, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("REQ_PSON_ID",model.getREQ_PSON_ID());
		    params.put("CHANN_ID",model.getCHANN_ID());
		    params.put("AREA_ID",model.getAREA_ID());
		    params.put("PAYMENT_UPLOAD_ID",model.getPAYMENT_UPLOAD_ID());
		    params.put("PAYMENT_UPLOAD_NO",model.getPAYMENT_UPLOAD_NO());
		    params.put("PAYMENT_NO",model.getPAYMENT_NO());
		    params.put("PAYMENT_AMOUNT",model.getPAYMENT_AMOUNT());
		    params.put("REQ_PSON_NAME",model.getREQ_PSON_NAME());
		    params.put("TEL",model.getTEL());
		    params.put("CHANN_NO",model.getCHANN_NO());
		    params.put("CHANN_NAME",model.getCHANN_NAME());
		    params.put("AREA_NO",model.getAREA_NO());
		    params.put("AREA_NAME",model.getAREA_NAME());
		    params.put("REMARK", model.getREMARK());
		    params.put("PAYMENT_PATH", model.getPAYMENT_PATH());
		    params.put("STATE",BusinessConsts.UNCOMMIT);
		if(StringUtil.isEmpty(PAYMENT_UPLOAD_ID)){
			PAYMENT_UPLOAD_ID= StringUtil.uuid32len();
			params.put("PAYMENT_UPLOAD_ID", PAYMENT_UPLOAD_ID);
			params.put("PAYMENT_UPLOAD_NO", LogicUtil.getBmgz("DRP_PAYMENT_UPLOAD_NO"));
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CREATOR",userBean.getXTYHID());
		    params.put("DEPT_ID",userBean.getBMXXID());
		    params.put("DEPT_NAME",userBean.getBMMC());
		    params.put("ORG_ID",userBean.getJGXXID());
		    params.put("ORG_NAME",userBean.getJGMC());
		    params.put("LEDGER_ID",userBean.getLoginZTXXID());
		    params.put("LEDGER_NAME",userBean.getLoginZTMC());
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
		return (Map<String,String>) load("Paymentup.loadById", param);
	}
	/**
	 * 
	 * 查询是否有相同的凭证号
	 * @param PAYMENT_NO
	 * @return
	 */
	public int checkPAYMENT_NO(String PAYMENT_NO){
		return queryForInt("Paymentup.checkPAYMENT_NO",PAYMENT_NO);
	}
	/**
	 * 提交
	 */
	public boolean commit(Map<String,String> map){
		return update("Paymentup.commit", map);
	}
	 public Map<String,String> loadChann(String CHANNID){
			Map<String, String> params = new HashMap<String, String>();
	        params.put("CHANN_ID", CHANNID);
	        return (Map<String, String>) load("chann.loadById", params);
		}
}