package com.hoperun.drp.distributer.distributerreq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.base.chann.model.ChannModel;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.distributer.distributerreq.model.DistributerReqModel;
import com.hoperun.drp.distributer.distributerreq.service.DistributerReqService;
import com.hoperun.sys.model.UserBean;

/**
 *@module 渠道管理-分销商管理
 *@function   分销渠道信息登记
 *@version 1.1
 *@author gu_hx
 */
public class DistributerReqServiceImpl extends BaseService implements DistributerReqService {

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
	public IListPage pageQuery(Map <String, String> params, int pageNo){
		return this.pageQuery("distributerReq.pageQuery",
				"distributerReq.pageCount", params, pageNo);
	}
	
	
	 /**
     * @param DISTRIBUTOR_REQ_ID
     * @return
     */
    public Map <String, String> load(String DISTRIBUTOR_REQ_ID){
    	return (Map<String, String>) load("distributerReq.loadById",DISTRIBUTOR_REQ_ID);
    }
    
    /**
     * @param model
     * @param userBean
     */
	public void txEdit(DistributerReqModel model, UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		
		String DISTRIBUTOR_REQ_ID = model.getDISTRIBUTOR_REQ_ID();
		
		params.put("DISTRIBUTOR_NAME", model.getDISTRIBUTOR_NAME());//分销商名称
		params.put("DISTRIBUTOR_TYPE", model.getDISTRIBUTOR_TYPE());//分销商类型
		params.put("TEL", model.getTEL());//公司电话		
		params.put("CHANN_ID", model.getCHANN_ID());//渠道ID
        params.put("CHANN_NO", model.getCHANN_NO());//渠道编号
        params.put("CHANN_NAME", model.getCHANN_NAME());//渠道名称       
        params.put("AREA_MANAGE_ID", model.getAREA_MANAGE_ID());//区域经理ID
        params.put("AREA_MANAGE_NAME", model.getAREA_MANAGE_NAME());//区域经理名称
        params.put("AREA_MANAGE_TEL", model.getAREA_MANAGE_TEL());//区域经理电话      
        params.put("AREA_ID", model.getAREA_ID());//区域id
        params.put("AREA_NO", model.getAREA_NO());//区域编号
        params.put("AREA_NAME", model.getAREA_NAME());//区域名称
        params.put("ZONE_ID", model.getZONE_ID());//行政区划id
        params.put("NATION", model.getNATION());//国家
        params.put("PROV", model.getPROV());//省份
        params.put("CITY", model.getCITY());//城市
        params.put("COUNTY", model.getCOUNTY());//区县        
        params.put("ADDRESS", model.getADDRESS());//详细地址
        params.put("TAX", model.getTAX());//传真
        params.put("EMAIL", model.getEMAIL());//电子邮件
        params.put("PERSON_CON", model.getPERSON_CON());//联系人
        params.put("MOBILE", model.getMOBILE());//手机
        params.put("SALE_STORE_NAME", model.getSALE_STORE_NAME());//商场名称
        params.put("SALE_STORE_LOCAL", model.getSALE_STORE_LOCAL());//商场位置
        params.put("BUSS_BRAND", model.getBUSS_BRAND());//经营品牌
        params.put("BUSS_CLASS", model.getBUSS_CLASS());//主营分类        
        params.put("COOPER_DATE", model.getCOOPER_DATE());//合作日期
        
		params.put("COOPER_PLAN_ATT",model.getCOOPER_PLAN_ATT());  //合作方案附件
		params.put("ATT",  model.getATT());  //附件
		//params.put("REMARK", model.getREMARK());//备注
				
		if (StringUtils.isEmpty(DISTRIBUTOR_REQ_ID)) {
			DISTRIBUTOR_REQ_ID = StringUtil.uuid32len();
        	params.put("DISTRIBUTOR_REQ_ID", DISTRIBUTOR_REQ_ID);
        	params.put("DISTRIBUTOR_REQ_NO",LogicUtil.getBmgz("ERP_DISTRIBUTOR_REQ_NO"));
        	
        	params.put("REQ_ID",userBean.getXTYHID());  //申请人ID
            params.put("REQ_NAME",userBean.getXM());  //申请人姓名
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
			params.put("DISTRIBUTOR_REQ_ID", DISTRIBUTOR_REQ_ID);
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
        insert("distributerReq.insert", params);
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
        return update("distributerReq.updateById", params);
    }
	
   /**
     * @param DISTRIBUTOR_REQ_ID
     * @param userBean
     * @return
     */
    public boolean txDelete(String DISTRIBUTOR_REQ_ID, UserBean userBean){
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("DISTRIBUTOR_REQ_ID", DISTRIBUTOR_REQ_ID);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);		
        params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称           
                
		return update("distributerReq.delete", params);
    }
    /***
     * @param CHANN_ID
     * @return
     */
    public ChannModel getChannInfo(String CHANN_ID){
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("CHANN_ID", CHANN_ID);
    	List<ChannModel> list = this.findList("chann.getChannInfo", paramMap);
     	if(null != list && !list.isEmpty()){
     		return list.get(0);
     	}
     	return null;
    }
    
    /***
     * @param userId
     * @return
     */
    public int getMkmCount(String RYXXID){
    	return queryForInt("chann.getMkmCount",RYXXID);
    }
    
	public  String  queryMaxNo(){
		 Map<String, String> params = new HashMap<String, String>();
		 String str = (String)load("distributor.queryMaxNo", params);
		 return str;
	}
}
