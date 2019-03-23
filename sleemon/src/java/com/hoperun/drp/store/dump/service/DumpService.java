/**
 * prjName:喜临门营销平台
 * ucName:转储单
 * fileName:DumpService
*/
package com.hoperun.drp.store.dump.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.store.dump.model.DumpModel;
import com.hoperun.drp.store.dump.model.DumpModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-05 14:00:34
 */
public interface DumpService extends IBaseService {
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
	 * @param DUMP_ID
	 * @param DumpModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String DUMP_ID, DumpModel obj,List<DumpModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param DUMP_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String DUMP_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param DUMP_ID the DUMP_ID
     * @return the list
     */
    public List <DumpModelChld> childQuery(String DUMP_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param DUMP_DTL_IDs the DUMP_DTL_IDs
     * 
     * @return the list
     */
    public List <DumpModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param DUMP_ID the DUMP_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String DUMP_ID, List <DumpModelChld> modelList,String actionType);
	 /**
     * * 子表的批量删除
     * @param DUMP_DTL_IDs the DUMP_DTL_IDs
     */
    public void txBatch4DeleteChild(String DUMP_DTL_IDs);
    
    public List expertExcelQuery(Map<String,String>params);
    public int getDtlCount(String DUMP_ID);
    
    
    /**
     * * 查询关联的明细
     * @param DUMP_ID the DUMP_ID
     * @return the list
     */
    public List <Map<String,String>> childReltQuery(String RELT_BILL_NO);
    
    /**
     * 查找关联的明细IDs
     * @param DUMP_ID
     * @return
     */
    public String queryReltDtls(String DUMP_ID);
    
}