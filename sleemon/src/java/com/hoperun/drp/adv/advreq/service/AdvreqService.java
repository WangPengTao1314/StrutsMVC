/**
 * prjName:喜临门营销平台
 * ucName:广告投放申请单维护
 * fileName:AdvreqService
*/
package com.hoperun.drp.adv.advreq.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.adv.advreq.model.AdvreqModel;
import com.hoperun.drp.adv.advreq.model.AdvreqModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author ghx
 * *@createdate 2014-07-15
 */
public interface AdvreqService extends IBaseService {
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
	 * @param ERP_ADV_REQ_ID
	 * @param AdvreqModel
	 * @param userBean.
	 * @return 
	 */
	public void txEdit(String ERP_ADV_REQ_ID, AdvreqModel obj,List<AdvreqModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param ERP_ADV_REQ_ID
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String ERP_ADV_REQ_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param ERP_ADV_REQ_ID the ERP_ADV_REQ_ID
     * @return the list
     */
    public List <AdvreqModelChld> childQuery(String ERP_ADV_REQ_ID);
	
    /**
     * *根据子表Id批量加载子表信息
     * @param ERP_ADV_REQ_DTL_IDs the ERP_ADV_REQ_DTL_IDs
     * @return the list
     */
    public List <AdvreqModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param ERP_ADV_REQ_ID the ERP_ADV_REQ_ID
     * @param modelList the model list
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String ERP_ADV_REQ_ID, List <AdvreqModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param ERP_ADV_REQ_DTL_IDs the ERP_ADV_REQ_DTL_IDs
     */
    public void txBatch4DeleteChild(String ERP_ADV_REQ_DTL_IDs);
    
    /**
	 * 加载图片.
	 * @param param
	 * @return
	 */
	public String loadAtt(String param);
}