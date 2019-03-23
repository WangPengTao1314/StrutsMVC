/**
 * prjName:供应链_贵人鸟
 * ucName:不良通知单
 * fileName:GrantService
*/
package com.hoperun.sample.grant.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sample.grant.model.GrantModel;
import com.hoperun.sample.grant.model.GrantModelChld;
import com.hoperun.sample.grant.model.GrantModelGchld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author zhuxw
 * *@createdate 2013-05-15 10:35:31
 */
public interface GrantService extends IBaseService {
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
	 * @param CPBLTZDID
	 * @param GrantModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String CPBLTZDID, GrantModel obj,List<GrantModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param CPBLTZDID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String CPBLTZDID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param CPBLTZDID the CPBLTZDID
     * @return the list
     */
    public List <GrantModelChld> childQuery(String CPBLTZDID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param CPBLTZDMXIDs the CPBLTZDMXIDs
     * 
     * @return the list
     */
    public List <GrantModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param CPBLTZDID the CPBLTZDID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String CPBLTZDID, List <GrantModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param CPBLTZDMXIDs the CPBLTZDMXIDs
     */
    public void txBatch4DeleteChild(String CPBLTZDMXIDs);
    /**
     * * 根据明细Id查询明细子表记录
     * @param CPBLTZDMXID the CPBLTZDMXID
     * @return the list
     */
    public List <GrantModelGchld> gchildQuery(String CPBLTZDMXID);
    /**
     * * 根据子表Id批量加载子表信息
     * @param CPBLTZDMXMXIDs the CPBLTZDMXMXIDs
     * 
     * @return the list
     */
    public List <GrantModelGchld> loadGchilds(Map <String, String> params) ;
	 /**
     * * 明细表子表记录编辑：新增/修改
     * @param CPBLTZDID the CPBLTZDID
     * @param CPBLTZDMXID the CPBLTZDMXID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txGchildEdit(String CPBLTZDID, String CPBLTZDMXID,List <GrantModelGchld> modelList);
    /**
     * * 子表的明细批量删除
     * @param CPBLTZDMXMXIDs the CPBLTZDMXMXIDs
     */
    public void txBatch4DeleteGchild(String CPBLTZDMXMXIDs);
}