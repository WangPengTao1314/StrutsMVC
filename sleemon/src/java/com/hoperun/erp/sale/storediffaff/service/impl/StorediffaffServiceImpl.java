/**
 * prjName:喜临门营销平台
 * ucName:入库差异确认
 * fileName:StorediffServiceImpl
*/
package com.hoperun.erp.sale.storediffaff.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.storediff.model.StorediffModel;
import com.hoperun.drp.store.storediff.model.StorediffModelChld;
import com.hoperun.drp.store.storediff.model.StorediffdealModelChld;
import com.hoperun.drp.store.storediff.service.StorediffService;
import com.hoperun.erp.sale.storediffaff.service.StorediffaffService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-30 14:03:21
 */
public class StorediffaffServiceImpl extends BaseService implements StorediffaffService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo) {
		return this.pageQuery("Storediffaff.pageQuery", "Storediffaff.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Storediffaff.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param STOREIN_DIFF_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Storediffaff.delete", params);
		 //删除子表 
		 return update("Storediffaff.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Storediffaff.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STOREIN_DIFF_ID
	 * @param StorediffaffModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String STOREIN_DIFF_ID, StorediffModel model,List<StorediffModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("RECV_DEAL_TIME","sysdate");
		    params.put("RECV_DEAL_PSON_ID",userBean.getXTYHID());
		    params.put("REVC_DEAL_PSON_NAME",userBean.getXM());
		    params.put("STATE",BusinessConsts.WAIT_FOR_SEND_PERSON);
		    
		    
		if(StringUtil.isEmpty(STOREIN_DIFF_ID)){
			STOREIN_DIFF_ID= StringUtil.uuid32len();
			params.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
			params.put("STATE",BusinessConsts.JC_STATE_DEFAULT);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		txInsert(params);
		} else{
			params.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
			txUpdateById(params);
			clearCaches(STOREIN_DIFF_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(STOREIN_DIFF_ID, chldList,userBean);
		}
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STOREIN_DIFF_ID
	 * @param StorediffaffModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txOver(String STOREIN_DIFF_ID, UserBean userBean) {
		//a.	更新主表的状态：
	    Map<String,String> params = new HashMap<String,String>();
	    params.put("RECV_DEAL_TIME","sysdate");
	    params.put("RECV_DEAL_PSON_ID",userBean.getXTYHID());
	    params.put("REVC_DEAL_PSON_NAME",userBean.getXM());
	    params.put("STATE",BusinessConsts.DEAL_OVER);
		params.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
		txUpdateById(params);
		clearCaches(STOREIN_DIFF_ID);
		
		//b.	生成对应的业务单据：
		
		
		//c.	更新帐表：
		
	}
	
	
	
	/**
	 * @详细
	 * @param param STOREIN_DIFF_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Storediffaff.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param STOREIN_DIFF_ID the STOREIN_DIFF_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String STOREIN_DIFF_ID, List<StorediffModelChld> chldList,UserBean userBean) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (StorediffModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("STOREIN_DIFF_ID",STOREIN_DIFF_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSTOREIN_DIFF_DTL_ID())) {
                params.put("STOREIN_DIFF_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("STOREIN_DIFF_DTL_ID", model.getSTOREIN_DIFF_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Storediffaff.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Storediffaff.insertChld", addList);
        }
        Map<String,String> upMap=new HashMap<String,String>();
		upMap.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
        upMap.put("UPDATOR", userBean.getRYXXID());
        upMap.put("UPD_NAME", userBean.getXM());
        upMap.put("UPD_TIME", "数据库时间");
        update("Storediffaff.updateById",upMap);
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param STOREIN_DIFF_ID the STOREIN_DIFF_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StorediffModelChld> childQuery(String STOREIN_DIFF_ID) {
        Map params = new HashMap();
        params.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storediffaff.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREIN_DIFF_DTL_IDs the STOREIN_DIFF_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StorediffModelChld> loadChilds(Map <String, String> params) {
       return findList("Storediffaff.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param STOREIN_DIFF_DTL_IDs the STOREIN_DIFF_DTL_IDs
     */
    @SuppressWarnings("unchecked")
	@Override
    public void txBatch4DeleteChild(String STOREIN_DIFF_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("STOREIN_DIFF_DTL_IDS", STOREIN_DIFF_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Storediffaff.deleteChldByIds", params);
    }
    
    /**
     * 差异处理
     */
    
    /**
     * * 根据主表Id查询子表记录
     * @param STOREIN_DIFF_ID the STOREIN_DIFF_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StorediffdealModelChld> dealQuery(String STOREIN_DIFF_ID,String STOREIN_DIFF_DTL_ID) {
        Map params = new HashMap();
        params.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		params.put("STOREIN_DIFF_DTL_ID",STOREIN_DIFF_DTL_ID);
		
        return this.findList("Storediffaff.dealqueryChldByFkId", params);
    }
    
    /**
     * * 根据子表Id批量加载子表信息
     * @param DIFF_DEAL_DTL_IDs the DIFF_DEAL_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StorediffdealModelChld> loadDealChilds(Map <String, String> params) {
       return findList("Storediffaff.dealloadChldByIds",params);
    }
    
    /**
     * * 子表批量删除:软删除
     * 
     * @param DIFF_DEAL_DTL_IDs the DIFF_DEAL_DTL_IDs
     */
    @SuppressWarnings("unchecked")
	@Override
    public void txBatch4DeleteDealChild(String DIFF_DEAL_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("DIFF_DEAL_DTL_IDS", DIFF_DEAL_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Storediffaff.dealdeleteChldByIds", params);
    }
	 /**
     * * 明细数据编辑
     * 
     * @param STOREIN_DIFF_ID the STOREIN_DIFF_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public void txdealEdit(List<StorediffdealModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (StorediffdealModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("DIFF_DEAL_DTL_ID",model.getDIFF_DEAL_DTL_ID());
            params.put("DIFF_RSON",model.getDIFF_RSON());
            params.put("DEAL_WAY",model.getDEAL_WAY());
            params.put("REMARK", model.getREMARK());
            update("Storediffaff.updateDealByDiffId",params);
        }
    }

}