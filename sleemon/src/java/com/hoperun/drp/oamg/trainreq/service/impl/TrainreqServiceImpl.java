/**
 * prjName:喜临门营销平台
 * ucName:渠道培训申请单维护
 * fileName:TrainreqServiceImpl
*/
package com.hoperun.drp.oamg.trainreq.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.trainreq.model.TrainreqModel;
import com.hoperun.drp.oamg.trainreq.model.TrainreqModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.oamg.trainreq.service.TrainreqService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author ghx
 * *@createdate 2014-02-28 14:01:04
 */
public class TrainreqServiceImpl extends BaseService implements TrainreqService {
    
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Trainreq.pageQuery", "Trainreq.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Trainreq.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param TRAIN_REQ_ID
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Trainreq.delete", params);
		 //删除子表 
		 return update("Trainreq.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Trainreq.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param TRAIN_REQ_ID
	 * @param TrainreqModel
	 * @param userBean.
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String TRAIN_REQ_ID, TrainreqModel model,List<TrainreqModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
				
		params.put("TRAIN_REQ_NO",model.getTRAIN_REQ_NO());
		params.put("CHANN_ID", model.getCHANN_ID());
		params.put("CHANN_NO", model.getCHANN_NO());
		params.put("CHANN_NAME", model.getCHANN_NAME());
		params.put("TRAIN_COURSE_NO", model.getTRAIN_COURSE_NO());
		params.put("TRAIN_COURSE_NAME", model.getTRAIN_COURSE_NAME());
		params.put("TRAIN_TYPE", model.getTRAIN_TYPE());
		params.put("TRAIN_TIME_BEG", model.getTRAIN_TIME_BEG());
		params.put("TRAIN_TIME_END", model.getTRAIN_TIME_END());
		params.put("TRAIN_ADDR", model.getTRAIN_ADDR());
		params.put("REQ_JOIN_NUM", model.getREQ_JOIN_NUM());
		params.put("FIXED_PSON_NUM", model.getFIXED_PSON_NUM());
		params.put("REQ_REASON", model.getREQ_REASON());
		params.put("TRAIN_COURSE_ID", model.getTRAIN_COURSE_ID());
		
		if(StringUtil.isEmpty(TRAIN_REQ_ID)){
			TRAIN_REQ_ID= StringUtil.uuid32len();
			params.put("TRAIN_REQ_ID", TRAIN_REQ_ID);
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
		    params.put("STATE",BusinessConsts.UNCOMMIT);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		    txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("TRAIN_REQ_ID", TRAIN_REQ_ID);
			txUpdateById(params);
			clearCaches(TRAIN_REQ_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(TRAIN_REQ_ID, chldList);
		}
	}
	
	/**
	 * @详细
	 * @param param TRAIN_REQ_ID
	 * @param param the param
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Trainreq.loadById", param);
	}
	
   /**
     * * 明细数据编辑
     * @param TRAIN_REQ_ID the TRAIN_REQ_ID
     * @param modelList the model list
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String TRAIN_REQ_ID, List<TrainreqModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (TrainreqModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("TRAIN_TYPE",model.getTRAIN_TYPE());
		    params.put("TRAIN_OBJECT_ID",model.getTRAIN_OBJECT_ID());
		    params.put("TRAIN_OBJECT_NO",model.getTRAIN_OBJECT_NO());
		    params.put("TRAIN_OBJECT_NAME",model.getTRAIN_OBJECT_NAME());
		    params.put("REMARK",model.getREMARK());
		    params.put("STATE",model.getSTATE());
            params.put("TRAIN_REQ_ID",TRAIN_REQ_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getTRAIN_REQ_DTL_ID())) {
                params.put("TRAIN_REQ_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("TRAIN_REQ_DTL_ID", model.getTRAIN_REQ_DTL_ID());
                updateList.add(params);
            }
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Trainreq.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Trainreq.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param TRAIN_REQ_ID the TRAIN_REQ_ID
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TrainreqModelChld> childQuery(String TRAIN_REQ_ID) {
        Map params = new HashMap();
        params.put("TRAIN_REQ_ID", TRAIN_REQ_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Trainreq.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param TRAIN_REQ_DTL_IDs the TRAIN_REQ_DTL_IDs 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TrainreqModelChld> loadChilds(Map <String, String> params) {
       return findList("Trainreq.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * @param TRAIN_REQ_DTL_IDs the TRAIN_REQ_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String TRAIN_REQ_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("TRAIN_REQ_DTL_IDS", TRAIN_REQ_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Trainreq.deleteChldByIds", params);
    }
}