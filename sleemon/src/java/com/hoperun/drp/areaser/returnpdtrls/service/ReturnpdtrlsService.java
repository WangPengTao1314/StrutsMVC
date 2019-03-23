/**
 * prjName:喜临门营销平台
 * ucName:退货申请单维护
 * fileName:PrdreturnService
*/
package com.hoperun.drp.areaser.returnpdtrls.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.prdreturnreq.model.PrdreturnreqModel;
import com.hoperun.drp.sale.prdreturnreq.model.PrdreturnreqModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-15 10:17:13
 */
public interface ReturnpdtrlsService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
    /**
	 * @主从保存编辑
	 * @Description :
	 * @param PRD_RETURN_REQ_ID
	 * @param PrdreturnreqModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String PRD_RETURN_REQ_ID, PrdreturnreqModel obj,List<PrdreturnreqModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param PRD_RETURN_REQ_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String PRD_RETURN_REQ_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param PRD_RETURN_REQ_ID the PRD_RETURN_REQ_ID
     * @return the list
     */
    public List <PrdreturnreqModelChld> childQuery(String PRD_RETURN_REQ_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param PRD_RETURN_DTL_REQ_IDs the PRD_RETURN_DTL_REQ_IDs
     * 
     * @return the list
     */
    public List <PrdreturnreqModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param PRD_RETURN_REQ_ID the PRD_RETURN_REQ_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String PRD_RETURN_REQ_ID, List <PrdreturnreqModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param PRD_RETURN_DTL_REQ_IDs the PRD_RETURN_DTL_REQ_IDs
     */
    public void txBatch4DeleteChild(String PRD_RETURN_DTL_REQ_IDs);
    
    /**
     * 审核
     * @param PRD_RETURN_REQ_ID
     * @param userBean
     */
    public void txAudit(String PRD_RETURN_REQ_ID,UserBean userBean);
}