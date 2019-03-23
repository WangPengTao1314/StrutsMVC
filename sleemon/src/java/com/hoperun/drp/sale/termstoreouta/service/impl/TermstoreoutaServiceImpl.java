/**
 * prjName:喜临门营销平台
 * ucName:门店销售出库单
 * fileName:TermstoreoutaServiceImpl
*/
package com.hoperun.drp.sale.termstoreouta.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.termstoreouta.model.TermstoreoutaModel;
import com.hoperun.drp.sale.termstoreouta.model.TermstoreoutaModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.sale.termstoreouta.service.TermstoreoutaService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-26 14:47:09
 */
public class TermstoreoutaServiceImpl extends BaseService implements TermstoreoutaService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Termstoreouta.pageQuery", "Termstoreouta.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Termstoreouta.insert", params);
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
         update("Termstoreouta.delete", params);
		 //删除子表 
		 return update("Termstoreouta.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Termstoreouta.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STOREOUT_ID
	 * @param TermstoreoutaModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String STOREOUT_ID, TermstoreoutaModel model,List<TermstoreoutaModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtil.isEmpty(STOREOUT_ID)){
			STOREOUT_ID= StringUtil.uuid32len();
			params.put("STOREOUT_ID", STOREOUT_ID);
		    txInsert(params);
		} else{
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
		return (Map<String,String>) load("Termstoreouta.loadById", param);
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
    public boolean txChildEdit(String STOREOUT_ID, List<TermstoreoutaModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (TermstoreoutaModelChld model : chldList) {
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
            this.batch4Update("Termstoreouta.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Termstoreouta.insertChld", addList);
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
    public List <TermstoreoutaModelChld> childQuery(String STOREOUT_ID) {
        Map params = new HashMap();
        params.put("STOREOUT_ID", STOREOUT_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Termstoreouta.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TermstoreoutaModelChld> loadChilds(Map <String, String> params) {
       return findList("Termstoreouta.loadChldByIds",params);
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
       update("Termstoreouta.deleteChldByIds", params);
    }
    
    /**
     * @param map
     * @return
     */
	public List downQuery(Map<String,String> map){
		return this.findList("Termstoreouta.downGetById", map);
	}
}