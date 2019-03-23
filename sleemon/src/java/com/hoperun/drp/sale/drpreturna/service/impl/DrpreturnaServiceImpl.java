/**
 * prjName:喜临门营销平台
 * ucName:门店下级退货单
 * fileName:DrpreturnaServiceImpl
*/
package com.hoperun.drp.sale.drpreturna.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.drp.sale.drpreturna.model.DrpreturnaModel;
import com.hoperun.drp.sale.drpreturna.model.DrpreturnaModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.sale.drpreturna.service.DrpreturnaService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-26 15:41:36
 */
public class DrpreturnaServiceImpl extends BaseService implements DrpreturnaService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Drpreturna.pageQuery", "Drpreturna.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Drpreturna.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param PRD_RETURN_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Drpreturna.delete", params);
		 //删除子表 
		 return update("Drpreturna.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Drpreturna.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param PRD_RETURN_ID
	 * @param DrpreturnaModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String PRD_RETURN_ID, DrpreturnaModel model,List<DrpreturnaModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtil.isEmpty(PRD_RETURN_ID)){
			PRD_RETURN_ID= StringUtil.uuid32len();
			params.put("PRD_RETURN_ID", PRD_RETURN_ID);
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
			params.put("PRD_RETURN_ID", PRD_RETURN_ID);
			txUpdateById(params);
			clearCaches(PRD_RETURN_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(PRD_RETURN_ID, chldList);
		}
	}
	
	/**
	 * @详细
	 * @param param PRD_RETURN_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Drpreturna.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param PRD_RETURN_ID the PRD_RETURN_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String PRD_RETURN_ID, List<DrpreturnaModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (DrpreturnaModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("PRD_RETURN_ID",PRD_RETURN_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getPRD_RETURN_DTL_ID())) {
                params.put("PRD_RETURN_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("PRD_RETURN_DTL_ID", model.getPRD_RETURN_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Drpreturna.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Drpreturna.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param PRD_RETURN_ID the PRD_RETURN_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <DrpreturnaModelChld> childQuery(String PRD_RETURN_ID) {
        Map params = new HashMap();
        params.put("PRD_RETURN_ID", PRD_RETURN_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Drpreturna.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <DrpreturnaModelChld> loadChilds(Map <String, String> params) {
       return findList("Drpreturna.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String PRD_RETURN_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("PRD_RETURN_DTL_IDS", PRD_RETURN_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Drpreturna.deleteChldByIds", params);
    }
    
    
    /**
     * @param map
     * @return
     */
	public List downQuery(Map<String,String> map){
		return this.findList("Drpreturna.downGetById", map);
	}
}