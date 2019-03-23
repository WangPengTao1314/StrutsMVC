/**
 * prjName:喜临门营销平台
 * ucName:返修单
 * fileName:RepairappServiceImpl
*/
package com.hoperun.drp.sale.repairapp.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.repairapp.model.RepairappModel;
import com.hoperun.drp.sale.repairapp.model.RepairappModelChld;
import com.hoperun.drp.sale.repairapp.service.RepairappService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-11-03 16:25:12
 */
public class RepairappServiceImpl extends BaseService implements RepairappService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Repairapp.pageQuery", "Repairapp.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Repairapp.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param ERP_REPAIR_ORDER_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Repairapp.delete", params);
		 //删除子表 
		 return update("Repairapp.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Repairapp.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param ERP_REPAIR_ORDER_ID
	 * @param RepairappModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String ERP_REPAIR_ORDER_ID, RepairappModel model,List<RepairappModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
			String no = LogicUtil.getBmgz("ERP_REPAIR_ORDER_NO");
		    params.put("ERP_REPAIR_ORDER_NO",no);
	    
		    params.put("REMARK",model.getREMARK());
		    params.put("REPAIR_CHANN_ID",model.getREPAIR_CHANN_ID());
		    params.put("REPAIR_CHANN_NO",model.getREPAIR_CHANN_NO());
		    params.put("REPAIR_CHANN_NAME",model.getREPAIR_CHANN_NAME());
		    params.put("AREA_ID",model.getAREA_ID());
		    params.put("AREA_NO",model.getAREA_NO());
		    params.put("AREA_NAME",model.getAREA_NAME());
		    params.put("STOREOUT_STORE_ID",model.getSTOREOUT_STORE_ID());
		    params.put("STOREOUT_STORE_NO",model.getSTOREOUT_STORE_NO());
		    params.put("STOREOUT_STORE_NAME",model.getSTOREOUT_STORE_NAME());
		    params.put("STORAGE_FLAG",model.getSTORAGE_FLAG());
		    params.put("REQ_FINISH_DATE",model.getREQ_FINISH_DATE());
		    // add by zzb 2014-6-18 返修方收货地址信息
		    params.put("DELIVER_ADDR_ID",model.getDELIVER_ADDR_ID());
		    params.put("DELIVER_ADDR_NO",model.getDELIVER_ADDR_NO());
		    params.put("DELIVER_DTL_ADDR",model.getDELIVER_DTL_ADDR());
		    
		if(StringUtil.isEmpty(ERP_REPAIR_ORDER_ID)){
			ERP_REPAIR_ORDER_ID= StringUtil.uuid32len();
			params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
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
		    txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
			txUpdateById(params);
			clearCaches(ERP_REPAIR_ORDER_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(ERP_REPAIR_ORDER_ID, chldList);
		}
	}
	
	/**
	 * @详细
	 * @param param ERP_REPAIR_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Repairapp.loadById", param);
	}
	
	/**
	 * @主表详细页面
	 * @param param ERP_REPAIR_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,Object> loadMap(String ERP_REPAIR_ORDER_ID){
		return (Map<String,Object>) load("Repairapp.loadById", ERP_REPAIR_ORDER_ID);
	}
		 /**
     * * 明细数据编辑
     * 
     * @param ERP_REPAIR_ORDER_ID the ERP_REPAIR_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String ERP_REPAIR_ORDER_ID, List<RepairappModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (RepairappModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("REPAIR_ATT",model.getREPAIR_ATT());
		    params.put("REPAIR_RESON",model.getREPAIR_RESON());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("VOLUME",model.getVOLUME());
		    params.put("TOTAL_VOLUME",model.getTOTAL_VOLUME());
		    params.put("SPCL_TECH_ID",model.getSPCL_TECH_ID());
		    
		    params.put("BRAND",model.getBRAND());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("REPAIR_PRICE",model.getREPAIR_PRICE());
		    params.put("REPAIR_NUM",model.getREPAIR_NUM());
		    params.put("REPAIR_AMOUNT",model.getREPAIR_AMOUNT());
		    
            params.put("ERP_REPAIR_ORDER_ID",ERP_REPAIR_ORDER_ID); 
            params.put("ERP_REPAIR_ORDER_ID",ERP_REPAIR_ORDER_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getREPAIR_ORDER_DTL_ID())) {
            	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                params.put("REPAIR_ORDER_DTL_ID", StringUtil.uuid32len());
                addList.add(params);
            } else {
                params.put("REPAIR_ORDER_DTL_ID", model.getREPAIR_ORDER_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Repairapp.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Repairapp.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录 返回map
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <Map<String,Object>> childQueryByParentId(String ERP_REPAIR_ORDER_ID) {
        Map<String,String> params = new HashMap<String,String> ();
        params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Repairapp.queryChldByParentId", params);
    }
    
    
    
    public List <RepairappModelChld> childQuery(String ERP_REPAIR_ORDER_ID) {
        Map<String,String> params = new HashMap<String,String> ();
        params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Repairapp.queryChldByFkId", params);
    }
    
    
    
    

    /**
     * * 根据子表Id批量加载子表信息
     * @param REPAIR_ORDER_DTL_IDs the REPAIR_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <RepairappModelChld> loadChilds(Map <String, String> params) {
       return findList("Repairapp.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param REPAIR_ORDER_DTL_IDs the REPAIR_ORDER_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String REPAIR_ORDER_DTL_IDs) {
	   Map<String,String> params = new HashMap<String,String>();
	   params.put("REPAIR_ORDER_DTL_IDS", REPAIR_ORDER_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Repairapp.deleteChldByIds", params);
    }
    
    
    public Map<String,String> getAreaByuserChann(String channId) {
        //只查询0的记录。1为删除。0为正常
        return (Map<String,String>)load("Repairapp.queryAreaByuserChann", channId);
    }
    
    public Map<String,String> getAreaByChannType(String type) {
        Map<String,String> params = new HashMap<String,String>();
        params.put("CHANN_TYPE", "区域服务中心");
        params.put("STATE", "启用");
        //只查询0的记录。1为删除。0为正常
        return (Map<String,String>)load("Repairapp.queryAreaByChannType", params);
    }
    
    
    public void txSubmitById(Map<String, String> params){
    	update("Repairapp.updateStateById", params);
    }
    
    /**
     * 查询总体积 总数量 总金额
     * @param ERP_REPAIR_ORDER_ID
     * @return
     */
    public Map<String,Object> queryTotal(String ERP_REPAIR_ORDER_ID){
    	 Map<String,String> params = new HashMap<String,String>();
    	 params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
    	return (Map<String, Object>) this.load("Repairapp.queryTotal", params);
    }
    
    
    
    
}