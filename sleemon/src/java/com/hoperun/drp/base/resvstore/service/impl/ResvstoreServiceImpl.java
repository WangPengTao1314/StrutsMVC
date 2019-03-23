/**
 * prjName:喜临门营销平台
 * ucName:库存储备信息
 * fileName:ResvstoreServiceImpl
*/
package com.hoperun.drp.base.resvstore.service.impl;
import java.util.HashMap;
import java.util.Map;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.base.resvstore.model.ResvstoreModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.base.resvstore.service.ResvstoreService;
/**
 * *@serviceImpl
 * *@func 库存储备
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-07 14:13:12
 */
public class ResvstoreServiceImpl extends BaseService implements ResvstoreService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Resvstore.pageQuery", "Resvstore.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Resvstore.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param RESV_STOCK_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
        return update("Resvstore.delete", params);
	}
	
	
	  /**
     * 判断是否存在重复.
     * 
     * @param params the params
     * 
     * @return true, 无重复
     */
    public boolean queryForInt(Map <String, Object> params) {

        if (0 == queryForInt("Resvstore.queryBhForInt", params)) {
            return true;
        } else{
            return false;
        }
    }
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Resvstore.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param RESV_STOCK_ID
	 * @param ResvstoreModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String RESV_STOCK_ID, ResvstoreModel model, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("PRD_ID",model.getPRD_ID()); 
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("SAFE_STORE_NUM",model.getSAFE_STORE_NUM());
		    params.put("MIN_STORE_NUM",model.getMIN_STORE_NUM());
		    params.put("REMARK",model.getREMARK());
		if(StringUtil.isEmpty(RESV_STOCK_ID)){
			RESV_STOCK_ID= StringUtil.uuid32len();
			params.put("RESV_STOCK_ID", RESV_STOCK_ID);
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CREATOR",userBean.getXTYHID());
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
			params.put("RESV_STOCK_ID", RESV_STOCK_ID);
			txUpdateById(params);
			clearCaches(RESV_STOCK_ID);
		}
	}
	/**
	 * @详细
	 * @param param RESV_STOCK_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Resvstore.loadById", param);
	}
	 /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params) {

        return update("Resvstore.updStusById", params);
    }
	
    /**
     * 修改状态为启用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params) {
        return update("Resvstore.updStusById", params);
    }
}