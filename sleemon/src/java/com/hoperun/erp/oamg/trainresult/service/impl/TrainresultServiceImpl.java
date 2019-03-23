/**
 * prjName:喜临门营销平台
 * ucName:渠道培训结果反馈
 * fileName:TrainresultServiceImpl
*/
package com.hoperun.erp.oamg.trainresult.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.trainreq.model.TrainreqModelChld;
import com.hoperun.erp.oamg.trainresult.model.TrainresultModelChld;
import com.hoperun.erp.oamg.trainresult.service.TrainresultService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author ghx
 * *@createdate 2014-07-10
 */
public class TrainresultServiceImpl extends BaseService implements TrainresultService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Trainreq.pageQuery", "Trainreq.pageCount",params, pageNo);
	}
	
	/**
	 * @详细
	 * @param param TRAIN_REQ_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Trainreq.loadById", param);
	}
	
	/**
     * * 根据主表Id查询子表记录
     * @param TRAIN_REQ_ID the TRAIN_REQ_ID
     * 
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
     * 明细数据编辑
     * 
     * @param TRAIN_REQ_ID the TRAIN_REQ_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String TRAIN_REQ_ID, List<TrainresultModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (TrainresultModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
           
		    params.put("TRAIN_PER_NO",model.getTRAIN_PER_NO());
		    params.put("TRAIN_PER_NAME",model.getTRAIN_PER_NAME());
		    params.put("THE_ORGAN",model.getTHE_ORGAN());
		    params.put("THE_POST",model.getTHE_POST());
		    params.put("SIGN_STATE",model.getSIGN_STATE());
		    params.put("TRAIN_PER",model.getTRAIN_PER());
		    params.put("TRAIN_ASSES",model.getTRAIN_ASSES());
		    params.put("TRAIN_OVERALL",model.getTRAIN_OVERALL());
		    params.put("REMARK",model.getREMARK());
		    params.put("STATE",model.getSTATE());
            params.put("TRAIN_REQ_ID",TRAIN_REQ_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getTRAIN_RESULT_DTL_ID())) {
                params.put("TRAIN_RESULT_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("TRAIN_RESULT_DTL_ID", model.getTRAIN_RESULT_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Trainreq.updateChldRstById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Trainreq.insertChldRst", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录(培训结果明细)
     * @param TRAIN_REQ_ID the TRAIN_REQ_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TrainresultModelChld> childRstQuery(String TRAIN_REQ_ID) {
        Map params = new HashMap();
        params.put("TRAIN_REQ_ID", TRAIN_REQ_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Trainreq.queryChldRstByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param TRAIN_REQ_DTL_IDs the TRAIN_REQ_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TrainresultModelChld> loadChildRsts(Map <String, String> params) {
       return findList("Trainreq.loadChldRstByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param TRAIN_RESULT_DTL_IDs the TRAIN_RESULT_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChildRst(String TRAIN_RESULT_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("TRAIN_RESULT_DTL_IDS", TRAIN_RESULT_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Trainreq.deleteChldRstByIds", params);
    }
}