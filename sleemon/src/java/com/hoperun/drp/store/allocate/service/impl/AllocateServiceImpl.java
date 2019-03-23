/**
 * prjName:喜临门营销平台
 * ucName:调拨申请维护
 * fileName:AllocateServiceImpl
*/
package com.hoperun.drp.store.allocate.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.allocate.model.AllocateModel;
import com.hoperun.drp.store.allocate.model.AllocateModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.store.allocate.service.AllocateService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-09-05 13:29:12
 */
public class AllocateServiceImpl extends BaseService implements AllocateService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Allocate.pageQuery", "Allocate.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Allocate.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param ALLOCATE_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Allocate.delete", params);
		 //删除子表 
		 return update("Allocate.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Allocate.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param ALLOCATE_ID
	 * @param AllocateModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String ALLOCATE_ID, AllocateModel model,List<AllocateModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("ALLOC_OUT_STORE_ID",model.getALLOC_OUT_STORE_ID());
		    params.put("ALLOC_OUT_CHANN_ID",model.getALLOC_OUT_CHANN_ID());
		    params.put("ALLOC_IN_CHANN_ID",model.getALLOC_IN_CHANN_ID());
		    params.put("BILL_TYPE",model.getBILL_TYPE());
		    params.put("ALLOC_OUT_CHANN_NO",model.getALLOC_OUT_CHANN_NO());
		    params.put("ALLOC_OUT_CHANN_NAME",model.getALLOC_OUT_CHANN_NAME());
		    params.put("ALLOC_IN_CHANN_NO",model.getALLOC_IN_CHANN_NO());
		    params.put("ALLOC_IN_CHANN_NAME",model.getALLOC_IN_CHANN_NAME());
		    params.put("ALLOC_OUT_STORE_NO",model.getALLOC_OUT_STORE_NO());
		    params.put("ALLOC_OUT_STORE_NAME",model.getALLOC_OUT_STORE_NAME());
		    params.put("STORAGE_FLAG",model.getSTORAGE_FLAG());
		    params.put("REMARK",model.getREMARK());
		if(StringUtil.isEmpty(ALLOCATE_ID)){
			ALLOCATE_ID= StringUtil.uuid32len();
			params.put("ALLOCATE_ID", ALLOCATE_ID);
			params.put("ALLOCATE_NO",LogicUtil.getBmgz("DRP_ALLOCATE_NO"));
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CREATOR",userBean.getXTYHID());
		    params.put("CRE_TIME", "数据库时间");
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
		    params.put("UPD_TIME","数据库时间");
			params.put("ALLOCATE_ID", ALLOCATE_ID);
			txUpdateById(params);
			clearCaches(ALLOCATE_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(ALLOCATE_ID, chldList,model.getALLOC_OUT_STORE_ID(),"edit");
		}else{
			this.delete("Allocate.delChld", ALLOCATE_ID);
		}
	}
	
	/**
	 * @详细
	 * @param param ALLOCATE_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Allocate.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param ALLOCATE_ID the ALLOCATE_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String ALLOCATE_ID, List<AllocateModelChld> chldList,String ALLOC_OUT_STORE_ID,String actionType) {
        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (AllocateModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            Map<String,String> checkMap=new HashMap<String,String>();
            Integer ALLOC_NUM=Integer.parseInt(model.getALLOC_NUM());
            checkMap.put("PRD_ID", model.getPRD_ID());
            checkMap.put("STORE_ID", ALLOC_OUT_STORE_ID);
            Integer count=Integer.parseInt(this.load("Allocate.checkAllocNum", checkMap).toString());
            if(count<ALLOC_NUM){
            	throw new ServiceException("对不起，有货品库存数量不足，请检查调拨数量后重新保存 !");
            }
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("REMARK",model.getREMARK());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("PRICE",model.getPRICE());
		    params.put("DECT_RATE",model.getDECT_RATE());
		    params.put("DECT_PRICE",model.getDECT_PRICE());
		    params.put("ALLOC_NUM",ALLOC_NUM+"");
		    params.put("DECT_AMOUNT",model.getDECT_AMOUNT());
            params.put("ALLOCATE_ID",ALLOCATE_ID); 
            params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
          //action类型，如果是在住子表编辑页面过来 则把明细全部删掉，重新新增，如果是明细编辑页面，则新增或修改明细
        	if("list".equals(actionType)){
        		 //如果没有明细ID的则是新增，有的是修改
                if (StringUtil.isEmpty(model.getALLOCATE_DTL_ID())) {
                    params.put("ALLOCATE_DTL_ID", StringUtil.uuid32len());
    		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                    addList.add(params);
                } else {
                    params.put("ALLOCATE_DTL_ID", model.getALLOCATE_DTL_ID());
                    updateList.add(params);
                }
        	}else{
        		params.put("ALLOCATE_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
        	}
        }
        if(!"list".equals(actionType)){
        	this.delete("Allocate.delChld", ALLOCATE_ID);
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Allocate.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Allocate.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param ALLOCATE_ID the ALLOCATE_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <AllocateModelChld> childQuery(String ALLOCATE_ID) {
        Map params = new HashMap();
        params.put("ALLOCATE_ID", ALLOCATE_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Allocate.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param ALLOCATE_DTL_IDs the ALLOCATE_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <AllocateModelChld> loadChilds(Map <String, String> params) {
       return findList("Allocate.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param ALLOCATE_DTL_IDs the ALLOCATE_DTL_IDs
     */
    @SuppressWarnings("unchecked")
	@Override
    public void txBatch4DeleteChild(String ALLOCATE_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("ALLOCATE_DTL_IDS", ALLOCATE_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Allocate.deleteChldByIds", params);
    }
}