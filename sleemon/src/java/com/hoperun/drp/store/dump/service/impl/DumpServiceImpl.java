/**
 * prjName:喜临门营销平台
 * ucName:转储单
 * fileName:DumpServiceImpl
*/
package com.hoperun.drp.store.dump.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.dump.model.DumpModel;
import com.hoperun.drp.store.dump.model.DumpModelChld;
import com.hoperun.drp.store.dump.service.DumpService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-05 14:00:35
 */
public class DumpServiceImpl extends BaseService implements DumpService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Dump.pageQuery", "Dump.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Dump.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param DUMP_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Dump.delete", params);
		 //删除子表 
		 return update("Dump.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Dump.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param DUMP_ID
	 * @param DumpModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String DUMP_ID, DumpModel model,List<DumpModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("STORAGE_FLAG2",model.getSTORAGE_FLAG2());
		    params.put("DUMP_OUT_STORE_NAME",model.getDUMP_OUT_STORE_NAME());
		    params.put("DUMP_OUT_STORE_NO",model.getDUMP_OUT_STORE_NO());
		    params.put("REMARK",model.getREMARK());
		    params.put("DUMP_IN_STORE_ID",model.getDUMP_IN_STORE_ID());
		    params.put("BILL_TYPE",model.getBILL_TYPE());
		    params.put("DUMP_OUT_STORE_ID",model.getDUMP_OUT_STORE_ID());
		    params.put("DUMP_OUT_STORAGE_FLAG",model.getDUMP_OUT_STORAGE_FLAG());
		    params.put("DUMP_IN_STORE_NO",model.getDUMP_IN_STORE_NO());
		    params.put("DUMP_IN_STORE_NAME",model.getDUMP_IN_STORE_NAME());
		    params.put("DUMP_DATE", model.getDUMP_DATE());
		    params.put("RELT_BILL_NO", model.getRELT_BILL_NO());
		if(StringUtil.isEmpty(DUMP_ID)){
			String DUMP_NO = LogicUtil.getBmgz("DRP_DUMP_NO");
			DUMP_ID = StringUtil.uuid32len();
			params.put("DUMP_ID",DUMP_ID);
			params.put("DUMP_NO",DUMP_NO);
        	params.put("CREATOR", userBean.getXTYHID());
            params.put("CRE_NAME", userBean.getXM());
            params.put("DEPT_ID", userBean.getBMXXID());
            params.put("DEPT_NAME", userBean.getBMMC());
            params.put("ORG_ID", userBean.getJGXXID());
            params.put("ORG_NAME", userBean.getJGMC());
            
            params.put("LEDGER_NAME", userBean.getLoginZTMC());
            params.put("LEDGER_ID", userBean.getLoginZTXXID());
            
            params.put("STATE", BusinessConsts.UNCOMMIT);// 带审批的 新增的时候状态是未提交
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
            
		    txInsert(params);
		} else{
			params.put("DUMP_NO",model.getDUMP_NO());
			params.put("DUMP_ID", DUMP_ID);
			params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
			txUpdateById(params);
			clearCaches(DUMP_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(DUMP_ID, chldList,"edit");
		}else{
			delChldById(DUMP_ID);
		}
	}
	
	/**
	 * @详细
	 * @param param DUMP_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Dump.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param DUMP_ID the DUMP_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String DUMP_ID, List<DumpModelChld> chldList,String actionType) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (DumpModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("RELT_BILL_DTL_ID", model.getRELT_BILL_DTL_ID());
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("DUMP_OUT_STORG_ID",model.getDUMP_OUT_STORG_ID());
		    params.put("DUMP_OUT_STORG_NO",model.getDUMP_OUT_STORG_NO());
		    params.put("DUMP_OUT_STORG_NAME",model.getDUMP_OUT_STORG_NAME());
		    params.put("DUMP_IN_STORG_ID",model.getDUMP_IN_STORG_ID());
		    params.put("DUMP_IN_STORG_NO",model.getDUMP_IN_STORG_NO());
		    params.put("DUMP_IN_STORG_NAME",model.getDUMP_IN_STORG_NAME());
		    params.put("DUMP_NUM",model.getDUMP_NUM());
		    params.put("REMARK",model.getREMARK());
            params.put("DUMP_ID",DUMP_ID); 
            params.put("SPCL_TECH_ID",model.getSPCL_TECH_ID());
            if("list".equals(actionType)){
	            //如果没有明细ID的则是新增，有的是修改
	            if (StringUtil.isEmpty(model.getDUMP_DTL_ID())) {
	                params.put("DUMP_DTL_ID", StringUtil.uuid32len());
	                params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
	                addList.add(params);
	            } else {
	                params.put("DUMP_DTL_ID", model.getDUMP_DTL_ID());
	                updateList.add(params);
	            }
            }else{
            	params.put("DUMP_DTL_ID", StringUtil.uuid32len());
                params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            }
        }
        if(!"list".equals(actionType)){
        	delChldById(DUMP_ID);
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Dump.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Dump.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param DUMP_ID the DUMP_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <DumpModelChld> childQuery(String DUMP_ID) {
        Map params = new HashMap();
        params.put("DUMP_ID", DUMP_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Dump.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param DUMP_DTL_IDs the DUMP_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <DumpModelChld> loadChilds(Map <String, String> params) {
       return findList("Dump.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param DUMP_DTL_IDs the DUMP_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String DUMP_DTL_IDs) {
	   Map<String,String> params = new HashMap<String,String>();
	   params.put("DUMP_DTL_IDS", DUMP_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Dump.deleteChldByIds", params);
    }
    /**
     * 按主表Id清空明细
     */
    public void delChldById(String DUMP_ID){
    	Map<String,String> map=new HashMap<String, String>();
    	map.put("DUMP_ID", DUMP_ID);
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
    	update("Dump.delChldByFkId", map);
    }
    
    
    public List expertExcelQuery(Map<String,String>params){
    	return this.findList("Dump.expertExcel", params);
    }
    public int getDtlCount(String DUMP_ID){
    	return StringUtil.getInteger(this.load("Dump.getDtlCount", DUMP_ID));
    }
    
    
    /**
     * * 查询关联的明细
     * @param DUMP_ID the DUMP_ID
     * @return the list
     */
    public List <Map<String,String>> childReltQuery(String RELT_BILL_NO){
    	  Map<String,String> params = new HashMap<String,String>();
          params.put("RELT_BILL_NO", RELT_BILL_NO);
          //只查询0的记录。1为删除。0为正常
  		  params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
          return this.findList("Dump.queryChldByReltBillNo", params);
    	
    }
    /**
     * 查找关联的明细IDs
     * @param DUMP_ID
     * @return
     */
    public String queryReltDtls(String DUMP_ID){
    	Map<String,String> params = new HashMap<String,String>();
        params.put("DUMP_ID", DUMP_ID);
    	return (String) this.load("Dump.queryReltDtls",params);
    }
}