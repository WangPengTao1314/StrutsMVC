/**
 * prjName:喜临门营销平台
 * ucName:渠道培训申请单维护
 * fileName:TrainreqService
*/
package com.hoperun.drp.oamg.trainreq.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.trainreq.model.TrainreqModel;
import com.hoperun.drp.oamg.trainreq.model.TrainreqModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author ghx
 * *@createdate 2014-02-28 14:01:04
 */
public interface TrainreqService extends IBaseService {
    
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
    /**
	 * @主从保存编辑
	 * @Description :
	 * @param TRAIN_REQ_ID
	 * @param TrainreqModel
	 * @param userBean.
	 * @return 
	 */
	public void txEdit(String TRAIN_REQ_ID, TrainreqModel obj,List<TrainreqModelChld> chldList, UserBean userBean);
	
	/**
	 * @主表删除
	 * @param Map
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param TRAIN_REQ_ID
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
     * * 根据子表Id批量加载子表信息
     * @param TRAIN_REQ_DTL_IDs the TRAIN_REQ_DTL_IDs
     * @return the list
     */
    public List <TrainreqModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param TRAIN_REQ_ID the TRAIN_REQ_ID
     * @param modelList the model list
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String TRAIN_REQ_ID, List <TrainreqModelChld> modelList);
	
    /**
     * * 子表的批量删除
     * @param TRAIN_REQ_DTL_IDs the TRAIN_REQ_DTL_IDs
     */
    public void txBatch4DeleteChild(String TRAIN_REQ_DTL_IDs);
}