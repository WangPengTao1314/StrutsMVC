/**
 * prjName:喜临门营销平台
 * ucName:渠道培训结果反馈
 * fileName:TrainresultService
*/
package com.hoperun.erp.oamg.trainresult.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.trainreq.model.TrainreqModelChld;
import com.hoperun.erp.oamg.trainresult.model.TrainresultModelChld;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author ghx
 * *@createdate 2014-07-10 
 */
public interface TrainresultService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	    	
	/**
	 * @主表详细页面
	 * @param param TRAIN_REQ_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String TRAIN_REQ_ID);
	
	/**
     * * 根据主表Id查询子表记录
     * @param TRAIN_REQ_ID the TRAIN_REQ_ID
     * @return the list
     */
    public List <TrainreqModelChld> childQuery(String TRAIN_REQ_ID);
	
	 /**
     * * 根据主表Id查询子表记录(培训结果明细)
     * @param TRAIN_REQ_ID the TRAIN_REQ_ID
     * @return the list
     */
    public List <TrainresultModelChld> childRstQuery(String TRAIN_REQ_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param TRAIN_REQ_DTL_IDs the TRAIN_REQ_DTL_IDs
     * 
     * @return the list
     */
    public List <TrainresultModelChld> loadChildRsts(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param TRAIN_REQ_ID the TRAIN_REQ_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String TRAIN_REQ_ID, List <TrainresultModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param TRAIN_RESULT_DTL_IDs the TRAIN_RESULT_DTL_IDs
     */
    public void txBatch4DeleteChildRst(String TRAIN_RESULT_DTL_IDs);
}