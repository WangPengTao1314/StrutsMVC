/**
 * prjName:喜临门营销平台
 * ucName:门店-退货出库单
 * fileName:TermreturnstoreoutServiceImpl
*/
package com.hoperun.drp.sale.termreturnstoreout.service.impl;
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
import com.hoperun.drp.sale.termreturnstoreout.model.TermreturnstoreoutModel;
import com.hoperun.drp.sale.termreturnstoreout.model.TermreturnstoreoutModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.sale.termreturnstoreout.service.TermreturnstoreoutService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-31 10:42:20
 */
public class TermreturnstoreoutServiceImpl extends BaseService implements TermreturnstoreoutService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Termreturnstoreout.pageQuery", "Termreturnstoreout.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Termreturnstoreout.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param STOREOUT_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Termreturnstoreout.delete", params);
		 //删除子表 
		 return update("Termreturnstoreout.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Termreturnstoreout.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STOREOUT_ID
	 * @param TermreturnstoreoutModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String STOREOUT_ID, TermreturnstoreoutModel model,List<TermreturnstoreoutModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtil.isEmpty(STOREOUT_ID)){
			STOREOUT_ID= StringUtil.uuid32len();
			params.put("STOREOUT_ID", STOREOUT_ID);
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
			params.put("STOREOUT_ID", STOREOUT_ID);
			txUpdateById(params);
			clearCaches(STOREOUT_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(STOREOUT_ID, chldList);
		}
	}
	
	/**
	 * @详细
	 * @param param STOREOUT_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Termreturnstoreout.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param STOREOUT_ID the STOREOUT_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String STOREOUT_ID, List<TermreturnstoreoutModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (TermreturnstoreoutModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("STOREOUT_ID",STOREOUT_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSTOREOUT_DTL_ID())) {
                params.put("STOREOUT_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("STOREOUT_DTL_ID", model.getSTOREOUT_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Termreturnstoreout.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Termreturnstoreout.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param STOREOUT_ID the STOREOUT_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TermreturnstoreoutModelChld> childQuery(String STOREOUT_ID) {
        Map params = new HashMap();
        params.put("STOREOUT_ID", STOREOUT_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Termreturnstoreout.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TermreturnstoreoutModelChld> loadChilds(Map <String, String> params) {
       return findList("Termreturnstoreout.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String STOREOUT_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("STOREOUT_DTL_IDS", STOREOUT_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Termreturnstoreout.deleteChldByIds", params);
    }
    
    /**
     * @param map
     * @return
     */
	public List downQuery(Map<String,String> map){
		return this.findList("Termreturnstoreout.downGetById", map);
	}
}