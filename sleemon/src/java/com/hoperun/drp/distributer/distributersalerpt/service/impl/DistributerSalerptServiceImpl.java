package com.hoperun.drp.distributer.distributersalerpt.service.impl;

import java.util.ArrayList;
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
import com.hoperun.drp.distributer.distributersalerpt.model.DistributerSalerptChildModel;
import com.hoperun.drp.distributer.distributersalerpt.model.DistributerSalerptModel;
import com.hoperun.drp.distributer.distributersalerpt.service.DistributerSalerptService;
import com.hoperun.sys.model.UserBean;

/**
 *@module 渠道管理-分销商管理
 *@function   分销商购货月报
 *@version 1.1
 *@author gu_hx
 */
public class DistributerSalerptServiceImpl extends BaseService implements DistributerSalerptService {

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
	public IListPage pageQuery(Map <String, String> params, int pageNo){
		return this.pageQuery("distributerSalerpt.pageQuery",
				"distributerSalerpt.pageCount", params, pageNo);
	}
	
	
	 /**
     * @param DISTRIBUTOR_SALE_RPT_ID
     * @return
     */
    public Map <String, String> load(String DISTRIBUTOR_SALE_RPT_ID){
    	return (Map<String, String>) load("distributerSalerpt.loadById",DISTRIBUTOR_SALE_RPT_ID);
    }
    
    /**
	 * 编辑
	 * @param obj
	 * @param userBean
	 */
	public void txEdit(DistributerSalerptModel model,UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();		
		
		String DISTRIBUTOR_SALE_RPT_ID = model.getDISTRIBUTOR_SALE_RPT_ID();
					
		params.put("CHANN_ID", model.getCHANN_ID());//渠道ID
        params.put("CHANN_NO", model.getCHANN_NO());//渠道编号
        params.put("CHANN_NAME", model.getCHANN_NAME());//渠道名称   
        params.put("YEAR", model.getYEAR());//年份
        params.put("MONTH", model.getMONTH());//月份       
		//params.put("REMARK", model.getREMARK());//备注
				
		if (StringUtils.isEmpty(DISTRIBUTOR_SALE_RPT_ID)) {
			DISTRIBUTOR_SALE_RPT_ID = StringUtil.uuid32len();
        	params.put("DISTRIBUTOR_SALE_RPT_ID", DISTRIBUTOR_SALE_RPT_ID);
        	params.put("DISTRIBUTOR_SALE_RPT_NO",LogicUtil.getBmgz("ERP_DISTRIBUTOR_SALE_RPT_NO"));
        	    	
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
			params.put("DISTRIBUTOR_SALE_RPT_ID", DISTRIBUTOR_SALE_RPT_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称
            params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间      
            txUpdateById(params);
		}
		
		// 子表信息保存
		List<DistributerSalerptChildModel> chldList = model.getChildList();
		if (null != chldList && !chldList.isEmpty()) {
			txChildEdit(DISTRIBUTOR_SALE_RPT_ID, chldList);
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
        insert("distributerSalerpt.insert", params);
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
        return update("distributerSalerpt.updateById", params);
    }
	
   /**
     * @param DISTRIBUTOR_SALE_RPT_ID
     * @param userBean
     * @return
     */
    public boolean txDelete(String DISTRIBUTOR_SALE_RPT_ID, UserBean userBean){
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("DISTRIBUTOR_SALE_RPT_ID", DISTRIBUTOR_SALE_RPT_ID);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);		
        params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称           
                
		return update("distributerSalerpt.delete", params);
    }
    
    /**
     * 主子表信息详情
     * @param DISTRIBUTOR_SALE_RPT_ID
     * @return
     */
    public DistributerSalerptModel find(String DISTRIBUTOR_SALE_RPT_ID){
    	return (DistributerSalerptModel) load("distributerSalerpt.findById",DISTRIBUTOR_SALE_RPT_ID);
    }
    
    /**
     * * 子表记录编辑：新增/修改
     * @param DISTRIBUTOR_SALE_RPT_ID
     * @param modelList the model list
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String DISTRIBUTOR_SALE_RPT_ID, List <DistributerSalerptChildModel> chldList){
    	// 修改列表
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		// 新增列表
		List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
		for (DistributerSalerptChildModel model : chldList) {
			String DISTRIBUTOR_SALE_RPT_DTL_ID = model.getDISTRIBUTOR_SALE_RPT_DTL_ID();
			Map<String, String> params = new HashMap<String, String>();
			params.put("DISTRIBUTOR_SALE_RPT_ID", DISTRIBUTOR_SALE_RPT_ID);
			params.put("DISTRIBUTOR_ID", model.getDISTRIBUTOR_ID());
			params.put("DISTRIBUTOR_NO", model.getDISTRIBUTOR_NO());
			params.put("DISTRIBUTOR_NAME", model.getDISTRIBUTOR_NAME());
			params.put("DISTRIBUTOR_TYPE", model.getDISTRIBUTOR_TYPE());			
			params.put("PRD_ID", model.getPRD_ID());
			params.put("PRD_NO", model.getPRD_NO());
			params.put("PRD_NAME", model.getPRD_NAME());
			params.put("PRD_SPEC", model.getPRD_SPEC());
			params.put("STOREOUT_NUM", model.getSTOREOUT_NUM());
			params.put("STOREOUT_AMOUNT", model.getSTOREOUT_AMOUNT());
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		 
	        if(StringUtil.isEmpty(DISTRIBUTOR_SALE_RPT_DTL_ID)){
	        	params.put("DISTRIBUTOR_SALE_RPT_DTL_ID",StringUtil.uuid32len());
	        	addList.add(params);
	        }else{
	        	params.put("DISTRIBUTOR_SALE_RPT_DTL_ID",DISTRIBUTOR_SALE_RPT_DTL_ID);
	        	updateList.add(params);
	        }
		} 
		 
		if (!addList.isEmpty()) {
			this.batch4Update("distributerSalerpt.insertChld", addList);
		}
		if (!updateList.isEmpty()) {
			this.batch4Update("distributerSalerpt.updateChldById", updateList);
		}
		return true;
    }
    
    /**
	 * 子表的批量删除
	 * @param DISTRIBUTOR_SALE_RPT_DTL_IDS
	 */
	public void txBatch4DeleteChild(String DISTRIBUTOR_SALE_RPT_DTL_IDS){
		Map<String, String> params = new HashMap<String, String>();
		params.put("DISTRIBUTOR_SALE_RPT_DTL_IDS", DISTRIBUTOR_SALE_RPT_DTL_IDS);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		update("distributerSalerpt.deleteChldByIds", params);
	}
	
	/**
     * 主表状态更新（提交/撤销）
     * @param params
     * @return
     */
    public boolean txUpdateStateById(Map <String, String> params){
    	return update("distributerSalerpt.updateStateById", params);
    }
    
    /**
     * 查询导出数据
     */
    public  List <Map> qryExp(Map params){
        return findList("distributerSalerpt.qryExp",params);
    }
}
