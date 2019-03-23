/**
 * prjName:喜临门营销平台
 * ucName:盘点单维护
 * fileName:InventoryService
*/
package com.hoperun.drp.store.inventory.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.store.inventory.model.InventoryModel;
import com.hoperun.drp.store.inventory.model.InventoryModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-09-07 09:54:59
 */
public interface InventoryService extends IBaseService {
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
	 * @param PRD_INV_ID
	 * @param InventoryModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String PRD_INV_ID, InventoryModel obj,List<InventoryModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param PRD_INV_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String PRD_INV_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param PRD_INV_ID the PRD_INV_ID
     * @return the list
     */
    public List <InventoryModelChld> childQuery(String PRD_INV_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param PRD_INV_DTL_IDs the PRD_INV_DTL_IDs
     * 
     * @return the list
     */
    public List <InventoryModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param PRD_INV_ID the PRD_INV_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String PRD_INV_ID, List <InventoryModelChld> modelList,String INV_RANGE,UserBean userBean,String actionType);
	 /**
     * * 子表的批量删除
     * @param PRD_INV_DTL_IDs the PRD_INV_DTL_IDs
     */
    public void txBatch4DeleteChild(String PRD_INV_DTL_IDs);
    /**
     * 主表编辑页面根据传递id获取货品信息
     */
    public List<InventoryModelChld> accTypeChildQuery(Map<String,String> map);
    //按主表id查找盘点范围
    public String getINV_RANGE(String PRD_INV_ID);
    //按主表id修改主表状态信息
    public void alterState(Map<String,String> map);
    //导入excel
	@SuppressWarnings("unchecked")
	public void txParseExeclToDbNew(List list, UserBean userBean,String PRD_INV_ID,String type,String initData)throws Exception;
	 //--0157490--Start--刘曰刚--2014-06-25--//
    //验证盘点货品是否重复，如果重复给予提示，按货品id或特殊工艺判断
	public String checkRepeat(String PRD_INV_ID);
	//--0157490--End--刘曰刚--2014-06-25--//
	public List storeAcctQuery(String LEDGER_ID);
	public void upChld(List<InventoryModelChld> chldList);
	/**
	 * 查询
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQueryChld(Map<String, String> params, int pageNo);
	public long getCount(Map<String,String> map);
	public int chldCountQuery(String PRD_INV_ID);
	public String getChannMonth(String CHANN_ID);
	public Map getAllNum(Map<String,String> map);
}