package com.hoperun.drp.distributer.distributerendreq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.distributer.distributerendreq.model.DistributerEndReqModel;
import com.hoperun.drp.distributer.distributerendreq.service.DistributerEndReqService;
import com.hoperun.sys.model.UserBean;

/**
 *@module 渠道管理-分销商管理
 *@function   加盟商终止合作申请
 *@version 1.1
 *@author gu_hx
 */
public class DistributerEndReqServiceImpl extends BaseService implements DistributerEndReqService {

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
	public IListPage pageQuery(Map <String, String> params, int pageNo){
		return this.pageQuery("distributerEndReq.pageQuery",
				"distributerEndReq.pageCount", params, pageNo);
	}
	
	
	 /**
     * @param CHANN_END_REQ_ID
     * @return
     */
    public Map <String, String> load(String CHANN_END_REQ_ID){
    	return (Map<String, String>) load("distributerEndReq.loadById",CHANN_END_REQ_ID);
    }
    
    /**
     * @param model
     * @param userBean
     */
	public void txEdit(DistributerEndReqModel model, UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		
		String CHANN_END_REQ_ID = model.getCHANN_END_REQ_ID();
		
		params.put("CHANN_ID", model.getCHANN_ID());//渠道ID
        params.put("CHANN_NO", model.getCHANN_NO());//渠道编号
        params.put("CHANN_NAME", model.getCHANN_NAME());//渠道名称 
        params.put("CHANN_TYPE", model.getCHANN_TYPE());//渠道类型
        params.put("JOIN_DATE", model.getJOIN_DATE());//加盟日期        
        params.put("CHANN_PERSON_CON", model.getCHANN_PERSON_CON());//渠道联系人
        params.put("CHANN_MOBILE", model.getCHANN_MOBILE());//渠道联系人电话        
        params.put("PERSON_CON", model.getPERSON_CON());//联系人
        params.put("TEL", model.getTEL());//公司电话(座机)
        params.put("MOBILE", model.getMOBILE());//联系人电话(手机)
        params.put("WAREA_ID", model.getWAREA_ID());//战区ID
        params.put("WAREA_NO", model.getWAREA_NO());//战区编号
        params.put("WAREA_NAME", model.getWAREA_NAME());//战区名称
        params.put("AREA_MANAGE_ID", model.getAREA_MANAGE_ID());//区域经理ID
        params.put("AREA_MANAGE_NAME", model.getAREA_MANAGE_NAME());//区域经理名称
        params.put("AREA_MANAGE_TEL", model.getAREA_MANAGE_TEL());//区域经理电话
        params.put("TERM_NAMES", model.getTERM_NAMES());//终端信息
        params.put("DEPOSIT", model.getDEPOSIT());//保证金
        params.put("DEPOSIT_CONFIRM", model.getDEPOSIT_CONFIRM());//保证金_财务
        params.put("LEFT_ACCT_AMOUNT", model.getLEFT_ACCT_AMOUNT());//账面余额
        params.put("LEFT_ACCT_AMOUNT_CONFIRM", model.getLEFT_ACCT_AMOUNT_CONFIRM());//账面余额_财务
        params.put("RETURN_AMOUNT", model.getRETURN_AMOUNT());//退货金额
        params.put("RETURN_AMOUNT_CONFIRM", model.getRETURN_AMOUNT_CONFIRM());//退货金额_财务
        params.put("RETURN_ATT", model.getRETURN_ATT());//退货附件
        params.put("END_RESON", model.getEND_RESON());//终止合作原因
        params.put("REMARK", model.getREMARK());//备注
        params.put("TAX", model.getTAX());//传真
        
						
		if (StringUtils.isEmpty(CHANN_END_REQ_ID)) {
			CHANN_END_REQ_ID = StringUtil.uuid32len();
        	params.put("CHANN_END_REQ_ID", CHANN_END_REQ_ID);
        	params.put("CHANN_END_REQ_NO",LogicUtil.getBmgz("ERP_CHANN_END_REQ_NO"));
        	        	
            params.put("REQ_DATE",DateUtil.now());  //申请时间
        	
        	params.put("CREATOR", userBean.getXTYHID());//制单人ID
            params.put("CRE_NAME", userBean.getXM());//制单人名称
            params.put("CRE_TIME", DateUtil.now());//制单时间
            params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());//制单机构id
            params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
            params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套ID
            params.put("LEDGER_NAME", userBean.getLoginZTMC());//帐套名称
        	params.put("STATE", BusinessConsts.UNCOMMIT);//状态
        	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//删除标记        	
            txInsert(params);
		} else {
			params.put("CHANN_END_REQ_ID", CHANN_END_REQ_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称           
            params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间      
            txUpdateById(params);
		}
	}
	
	/**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, String> params) {
        insert("distributerEndReq.insert", params);
        return true;
    }
    /**
     * 更新记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, String> params) {    	
        return update("distributerEndReq.updateById", params);
    }
	
   /**
     * @param CHANN_END_REQ_ID
     * @param userBean
     * @return
     */
    public boolean txDelete(String CHANN_END_REQ_ID, UserBean userBean){
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("CHANN_END_REQ_ID", CHANN_END_REQ_ID);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);		
        params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称           
                
		return update("distributerEndReq.delete", params);
    }
    
    /**
     * @param RYXXID
     * @return
     */
    public  int  queryZW(String RYXXID,String GZZXXID){
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("GZZXXID", GZZXXID);
    	params.put("RYXXID", RYXXID);
    	return queryForInt("distributerEndReq.queryZW",params);
    }
    
    
    /**
     * @return
     */
    public List<String> queryGZZXXID(){
    	 Map<String, String> params = new HashMap<String, String>();
        //return (String) load("distributerEndReq.queryGZZXXID", params);	
    	 return this.findList("distributerEndReq.queryGZZXXID",params);
    }
}
