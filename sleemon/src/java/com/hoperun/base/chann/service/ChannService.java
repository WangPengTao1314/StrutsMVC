/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：QDXXService.java
 */
package com.hoperun.base.chann.service;

import java.util.List;
import java.util.Map;

import com.hoperun.base.chann.model.ChannModel;
import com.hoperun.base.chann.model.DeliveraddrModelChld;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;
/**
 * The Interface channService.
 * 
 * @module 系统管理
 * @func 渠道信息
 * @version 1.0
 * @author 刘曰刚
 */
public interface ChannService extends IBaseService {
	
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map<String, String> params, int pageNo);
    /**
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map
     */
    public Map<String,String> load(String param);
    /**
     * 判断编号是否重复
     * Gets the count CHANN_NO.
     * 
     * @param CHANN_NO the CHANN_NO
     * 
     * @return the count CHANN_NO
     */
    public int getCountCHANN_NO(String CHANN_NO);
    /**
     * 查询渠道状态
     * @param CHANN_ID
     * @return
     */
    public boolean loadSTATE(String CHANN_ID);
    /**
     * 编辑：新增/删除
     * Description :.
     * 
     * @param chann_id the chan id
     * @param ChannpunishModel the chann model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public String txEdit(String CHANN_ID, ChannModel model, UserBean userBean);
    /**
	 * 校验是否有未停用的下级渠道.
	 * 
	 * @param params
	 *            the params
	 * 
	 * @return the int
	 */
	public int checkSubChann(Map<String, String> params);
	/**
     * 删除记录
     * Description :.
     * 
     * @param CHANN_ID the chann id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(Map<String,String> map);
    /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params);
  //按渠道id查找渠道状态
    public String getSTATE(String CHANN_ID);
    //渠道状态为制定时点启用状态需要新增帐套信息，机构信息，部门信息，用户
    public boolean txInsertSundry(Map<String,String> map);
    
    
	 /**
     * * 子表记录编辑：新增/修改
     * @param CHANN_ID the CHANN_ID
     * @param modelList the model list
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String CHANN_ID, List <DeliveraddrModelChld> modelList);
    
    
    /**
     * * 根据主表Id查询子表记录
     * @param CHANN_ID the CHANN_ID
     * @return  
     */
    public List <DeliveraddrModelChld> childQuery(String CHANN_ID);
    
    /**
     * * 根据子表Id批量加载子表信息
     * 
     */
    public List <DeliveraddrModelChld> loadChilds(Map <String, String> params);
    
    /**
     * * 子表批量删除:软删除
     * 
     * @param DELIVER_ADDR_IDS the DELIVER_ADDR_IDS
     */
    public void txBatch4DeleteChild(String DELIVER_ADDR_IDS);
    
    public void txStarChld(String DELIVER_ADDR_IDS);
    
    public void txStopChld(String DELIVER_ADDR_IDS);
    
    /**
     * 查重
     * @param CHANN_ID 渠道ID
     * @param DELIVER_ADDR_IDS 地址信息IDs
     * @param DELIVER_ADDR_NOS 地址编号
     * @return
     */
    public int valiAddrNo(String CHANN_ID,String DELIVER_ADDR_IDS,String DELIVER_ADDR_NOS);
    //启用
    public boolean txStartById(Map <String, String> params);
    public List chargQuery(String CHANN_ID);
    /**
     * 查询导出数据
     */
    public  List <Map> qryChannExp(Map params);
    
    /**
     * 批量编辑区域经理
     */
	public void txbatchUpdate(String flag,String CHANN_IDS, ChannModel model,UserBean userBean);
	
	/**
	 * @param flag
	 * @param CHANN_IDS
	 * @param model
	 * @param userBean
	 */
	public void txbatchUpdateStoreIn(String flag,String CHANN_IDS, ChannModel model,UserBean userBean);
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageBatch(Map <String, String> params, int pageNo);
    
    /****
     * @param params
     * @return
     */
    public   List<Map<String,String>>  childQuery(Map<String,String> params);
}
