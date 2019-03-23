/**
 * prjName:喜临门营销平台
 * ucName:形态转换
 * fileName:StateChangeServiceImpl
*/
package com.hoperun.drp.store.statechange.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.statechange.model.StateChangeModel;
import com.hoperun.drp.store.statechange.model.StateChangeModelChild;
import com.hoperun.drp.store.statechange.service.StateChangeService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-05 14:00:35
 */
public class StateChangeServiceImpl extends BaseService implements StateChangeService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("StateChange.pageQuery", "StateChange.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("StateChange.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param StateChange.ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("StateChange.delete", params);
		 //删除子表 
		 return update("StateChange.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("StateChange.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STATE_CHANGE_ID
	 * @param StateChangeModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String STATE_CHANGE_ID, StateChangeModel model,List<StateChangeModelChild> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("REMARK",model.getREMARK());
		if(StringUtil.isEmpty(STATE_CHANGE_ID)){
			String STATE_CHANGE_NO = LogicUtil.getBmgz("STATE_CHANGE_NO");
			STATE_CHANGE_ID = StringUtil.uuid32len();
			params.put("STATE_CHANGE_ID",STATE_CHANGE_ID);
			params.put("STATE_CHANGE_NO",STATE_CHANGE_NO);
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
			params.put("STATE_CHANGE_NO",model.getSTATE_CHANGE_NO());
			params.put("STATE_CHANGE_ID", STATE_CHANGE_ID);
			params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
			txUpdateById(params);
			clearCaches(STATE_CHANGE_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(STATE_CHANGE_ID, chldList);
		}else{
			delChldById(STATE_CHANGE_ID);
		}
	}
	
	/**
	 * @详细
	 * @param param STATE_CHANGE_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("StateChange.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param STATE_CHANGE_ID the STATE_CHANGE_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String STATE_CHANGE_ID, List<StateChangeModelChild> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (StateChangeModelChild model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("STATE_CHANGE_ID",STATE_CHANGE_ID);
		    params.put("STORE_ID",model.getSTORE_ID());
		    params.put("STORE_NO",model.getSTORE_NO());
		    params.put("STORE_NAME",model.getSTORE_NAME());
		    params.put("PAR_CHANGE_PRD_ID",model.getPAR_CHANGE_PRD_ID());
		    params.put("PAR_CHANGE_PRD_NO",model.getPAR_CHANGE_PRD_NO());
		    params.put("PAR_CHANGE_PRD_NAME",model.getPAR_CHANGE_PRD_NAME());
		    params.put("PAR_CHANGE_SPCL_TECH_ID",model.getPAR_CHANGE_SPCL_TECH_ID());
		    params.put("CHANGED_PRD_ID",model.getCHANGED_PRD_ID());
		    params.put("CHANGED_PRD_NO",model.getCHANGED_PRD_NO());
		    params.put("CHANGED_PRD_NAME",model.getCHANGED_PRD_NAME());
		    String CHANGED_SPCL_TECH_ID = model.getCHANGED_SPCL_TECH_ID();
//		    if(StringUtil.isEmpty(CHANGED_SPCL_TECH_ID)){
//		    	throw new ServiceException("转换后货品的特殊工艺没有选择");
//		    }
		    params.put("CHANGED_SPCL_TECH_ID",CHANGED_SPCL_TECH_ID);
		    params.put("CHANGE_NUM",model.getCHANGE_NUM());
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSTATE_CHANGE_DTL_ID())) {
                params.put("STATE_CHANGE_DTL_ID", StringUtil.uuid32len());
                params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("STATE_CHANGE_DTL_ID", model.getSTATE_CHANGE_DTL_ID());
                updateList.add(params);
            }
             
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("StateChange.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("StateChange.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param STATE_CHANGE_ID the STATE_CHANGE_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> childQuery(String STATE_CHANGE_ID) {
        Map<String,String> params = new HashMap<String,String> ();
        params.put("STATE_CHANGE_ID", STATE_CHANGE_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("StateChange.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param STATE_CHANGE_DTL_IDS the STATE_CHANGE_DTL_IDS 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StateChangeModelChild> loadChilds(Map <String, String> params) {
       return findList("StateChange.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param STATE_CHANGE_DTL_IDS the STATE_CHANGE_DTL_IDS
     */
    @Override
    public void txBatch4DeleteChild(String STATE_CHANGE_DTL_IDS) {
	   Map<String,String> params = new HashMap<String,String>();
	   params.put("STATE_CHANGE_DTL_IDS", STATE_CHANGE_DTL_IDS);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("StateChange.deleteChldByIds", params);
    }
    /**
     * 按主表Id清空明细
     */
    public void delChldById(String STATE_CHANGE_ID){
    	Map<String,String> map=new HashMap<String, String>();
    	map.put("STATE_CHANGE_ID", STATE_CHANGE_ID);
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	update("StateChange.delChldByFkId", map);
    }
    
    /**
     * 提交
     * @param STATE_CHANGE_ID
     */
    public void txCommit(String STATE_CHANGE_ID){
    	try{
	    	Map<String,String> map=new HashMap<String, String>();
	    	map.put("STATE_CHANGE_ID", STATE_CHANGE_ID);
	    	map.put("STATE", BusinessConsts.PASS);
	    	update("StateChange.updateById", map);
//	    	注释 下面已经记账，否则重复记账
//	    	update("StateChange.updateParAcctById",map);
//	    	update("StateChange.updateChangeAcctById",map);
	    	LogicUtil.doStateChangeStoreAcct(BusinessConsts.CONV_PDT_CONF_1ID,STATE_CHANGE_ID); //形态转换（按原货品减）
	    	LogicUtil.doJournalAcct(BusinessConsts.CONV_PDT_CONF_1ID,STATE_CHANGE_ID);
	    	LogicUtil.doStateChangeStoreAcct(BusinessConsts.CONV_PDT_CONF_2ID,STATE_CHANGE_ID); //形态转换(按转换后货品加)
	    	LogicUtil.doJournalAcct(BusinessConsts.CONV_PDT_CONF_2ID,STATE_CHANGE_ID);	
    	}catch(Exception ex){
    		throw new ServiceException("提交失败!");
    	}
    }
}