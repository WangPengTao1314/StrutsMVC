/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：GrgzjhService.java
 */
package com.hoperun.base.grgzjh.service;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.base.grgzjh.model.GrgzjhModel;
import com.hoperun.base.grgzjh.model.GrgzjhModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * The Class GrgzjhService.
 * 
 * @module 系统管理
 * @func 个人工作计划
 * @version 1.0
 * @author 吴军
 */
public interface GrgzjhService extends IBaseService {
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
	 * @param JHID
	 * @param GrgzjhModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public String txEdit(String JHID, GrgzjhModel obj,List<GrgzjhModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param XFJHID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String XFJHID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param XFJHID the XFJHID
     * @return the list
     */
    public List <GrgzjhModelChld> childQuery(String XFJHID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param XFJHMXIDs the XFJHMXIDs
     * 
     * @return the list
     */
    public List <GrgzjhModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param XFJHID the XFJHID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String XFJHID, List <GrgzjhModelChld> modelList, UserBean userBean);
	 /**
     * * 子表的批量删除
     * @param XFJHMXIDs the XFJHMXIDs
     */
    public void txBatch4DeleteChild(String XFJHMXIDs);
    
    /**
     * 个人工作计划明细查询
     * @param ryxxid
     * @param currentDate
     * @return
     */
    public List<HashMap<String, String>> loadEventsForCurrentUser(String ryxxid, String currentDate);
    
    
    
    /**
     * 根据年、月、人员ID查询个人工作计划
     * @param ryxxid 人员ID
     * @param JHNF	 计划年份
     * @param JHYF	 计划月份
     * @return
     */
    public List<HashMap<String, String>> loadUserPlanId(String ryxxid, String JHNF, String JHYF);
    
    /**
	 * 删除子表明细信息
	 * @param parameter
	 */
    public void deleteChild(HashMap<String, String> parameter);
    
    public void update(Map<String,String> params);
}