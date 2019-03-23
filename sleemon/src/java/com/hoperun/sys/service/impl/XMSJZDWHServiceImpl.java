/**
 * prjName:喜临门营销平台
 * ucName:项目数据字典维护
 * fileName:SjzdwhServiceImpl
*/
package com.hoperun.sys.service.impl;
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
import com.hoperun.sys.model.XMSJZDWHModel;
import com.hoperun.sys.model.XMSJZDWHModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.XMSJZDWHService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2014-01-30 10:18:20
 */
public class XMSJZDWHServiceImpl extends BaseService implements XMSJZDWHService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Xmsjzdwh.pageQuery", "Xmsjzdwh.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Xmsjzdwh.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param DATA_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Xmsjzdwh.delete", params);
		 //删除子表 
		 return update("Xmsjzdwh.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Xmsjzdwh.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param DATA_ID
	 * @param SjzdwhModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String DATA_ID, XMSJZDWHModel model,List<XMSJZDWHModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("DATA_CODE",model.getDATA_CODE());
		    params.put("DATA_NAME",model.getDATA_NAME());
		    params.put("DATA_REMARK",model.getDATA_REMARK());
		if(StringUtil.isEmpty(DATA_ID)){
			DATA_ID= StringUtil.uuid32len();
			params.put("DATA_ID", DATA_ID);
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CREATOR",userBean.getXTYHID());
			params.put("STATE",BusinessConsts.JC_STATE_DEFAULT);
			txInsert(params);
		} else{
			params.put("DATA_ID", DATA_ID);
			txUpdateById(params);
			clearCaches(DATA_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(DATA_ID, chldList);
		}
	}
	
	/**
	 * @详细
	 * @param param DATA_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Xmsjzdwh.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param DATA_ID the DATA_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String DATA_ID, List<XMSJZDWHModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (XMSJZDWHModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("PAR_DATA_DTL_NAME",model.getPAR_DATA_DTL_NAME());
		    params.put("DATA_DTL_NAME",model.getDATA_DTL_NAME());
		    params.put("DATA_DTL_CODE",model.getDATA_DTL_CODE());
		    params.put("PAR_DATA_DTL_ID", model.getPAR_DATA_DTL_ID());
		    
		    System.out.println("当前字表code"+model.getDATA_DTL_CODE()+"    选择的父ID"+model.getPAR_DATA_DTL_ID());
		    
		    params.put("PAR_DATA_DTL_CODE",model.getPAR_DATA_DTL_CODE());
		    params.put("DATA_DTL_DES_1",model.getDATA_DTL_DES_1());
		    params.put("DATA_DTL_DES_2",model.getDATA_DTL_DES_2());
		    params.put("REMARK",model.getREMARK());
            params.put("DATA_ID",DATA_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getDATA_DTL_ID())) {
                params.put("DATA_DTL_ID", StringUtil.uuid32len());
                addList.add(params);
            } else {
                params.put("DATA_DTL_ID", model.getDATA_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Xmsjzdwh.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Xmsjzdwh.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param DATA_ID the DATA_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <XMSJZDWHModelChld> childQuery(String DATA_ID) {
        Map params = new HashMap();
        params.put("DATA_ID", DATA_ID);
        //只查询0的记录。1为删除。0为正常
        return this.findList("Xmsjzdwh.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param DATA_DTL_IDs the DATA_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <XMSJZDWHModelChld> loadChilds(Map <String, String> params) {
       return findList("Xmsjzdwh.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param DATA_DTL_IDs the DATA_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String DATA_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("DATA_DTL_IDS", DATA_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Xmsjzdwh.deleteChldByIds", params);
    }
}