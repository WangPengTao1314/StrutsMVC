/**
 * prjName:喜临门营销平台
 * ucName:门店退货入库单
 * fileName:TermreturnstoreinServiceImpl
*/
package com.hoperun.drp.sale.termreturnstorein.service.impl;
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
import com.hoperun.drp.sale.termreturnstorein.model.TermreturnstoreinModel;
import com.hoperun.drp.sale.termreturnstorein.model.TermreturnstoreinModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.sale.termreturnstorein.service.TermreturnstoreinService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-27 15:46:33
 */
public class TermreturnstoreinServiceImpl extends BaseService implements TermreturnstoreinService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Termreturnstorein.pageQuery", "Termreturnstorein.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Termreturnstorein.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param STOREIN_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Termreturnstorein.delete", params);
		 //删除子表 
		 return update("Termreturnstorein.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Termreturnstorein.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STOREIN_ID
	 * @param TermreturnstoreinModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String STOREIN_ID, TermreturnstoreinModel model,List<TermreturnstoreinModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtil.isEmpty(STOREIN_ID)){
			STOREIN_ID= StringUtil.uuid32len();
			params.put("STOREIN_ID", STOREIN_ID);
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
			params.put("STOREIN_ID", STOREIN_ID);
			txUpdateById(params);
			clearCaches(STOREIN_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(STOREIN_ID, chldList);
		}
	}
	
	/**
	 * @详细
	 * @param param STOREIN_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Termreturnstorein.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param STOREIN_ID the STOREIN_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String STOREIN_ID, List<TermreturnstoreinModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (TermreturnstoreinModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("STOREIN_ID",STOREIN_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSTOREIN_DTL_ID())) {
                params.put("STOREIN_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("STOREIN_DTL_ID", model.getSTOREIN_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Termreturnstorein.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Termreturnstorein.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param STOREIN_ID the STOREIN_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TermreturnstoreinModelChld> childQuery(String STOREIN_ID) {
        Map params = new HashMap();
        params.put("STOREIN_ID", STOREIN_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Termreturnstorein.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREIN_DTL_IDs the STOREIN_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TermreturnstoreinModelChld> loadChilds(Map <String, String> params) {
       return findList("Termreturnstorein.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param STOREIN_DTL_IDs the STOREIN_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String STOREIN_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("STOREIN_DTL_IDS", STOREIN_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Termreturnstorein.deleteChldByIds", params);
    }
}