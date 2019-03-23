/**
 * prjName:喜临门营销平台
 * ucName:我的退货单
 * fileName:MyretrunServiceImpl
*/
package com.hoperun.drp.sale.myretrun.service.impl;
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
import com.hoperun.drp.sale.myretrun.model.MyretrunModel;
import com.hoperun.drp.sale.myretrun.model.MyretrunModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.sale.myretrun.service.MyretrunService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-25 09:38:48
 */
public class MyretrunServiceImpl extends BaseService implements MyretrunService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Myretrun.pageQuery", "Myretrun.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Myretrun.insert", params);
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
         update("Myretrun.delete", params);
		 //删除子表 
		 return update("Myretrun.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Myretrun.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param PRD_RETURN_ID
	 * @param MyretrunModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String PRD_RETURN_ID, MyretrunModel model,List<MyretrunModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtil.isEmpty(PRD_RETURN_ID)){
			PRD_RETURN_ID= StringUtil.uuid32len();
			params.put("PRD_RETURN_ID", PRD_RETURN_ID);
		txInsert(params);
		} else{
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
		return (Map<String,String>) load("Myretrun.loadById", param);
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
    public boolean txChildEdit(String PRD_RETURN_ID, List<MyretrunModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (MyretrunModelChld model : chldList) {
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
            this.batch4Update("Myretrun.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Myretrun.insertChld", addList);
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
    public List <MyretrunModelChld> childQuery(String PRD_RETURN_ID) {
        Map params = new HashMap();
        params.put("PRD_RETURN_ID", PRD_RETURN_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Myretrun.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <MyretrunModelChld> loadChilds(Map <String, String> params) {
       return findList("Myretrun.loadChldByIds",params);
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
       update("Myretrun.deleteChldByIds", params);
    }
}