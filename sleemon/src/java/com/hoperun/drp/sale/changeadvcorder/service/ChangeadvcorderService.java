package com.hoperun.drp.sale.changeadvcorder.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.changeadvcorder.model.ChangeadvcorderModel;
import com.hoperun.drp.sale.changeadvcorder.model.ChangeadvcorderModelChld;
import com.hoperun.drp.sale.changeadvcorder.model.ChangeadvcorderModelGchld;
import com.hoperun.sys.model.UserBean;


/**
 * 销售管理-终端销售-预订单变更
 * @author gu_hongxiu
 *
 */
public interface ChangeadvcorderService extends IBaseService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo);
	
	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_CHANGE_ID
	 *            the ADVC_ORDER_CHANGE_ID
	 * @return the list
	 */
	public List<ChangeadvcorderModelChld> childQuery(String ADVC_ORDER_CHANGE_ID);
	
	/**
	 * @主表详细页面
	 * @param param
	 *            ADVC_ORDER_CHANGE_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String ADVC_ORDER_CHANGE_ID);
	
	/**
	 * @主从保存编辑
	 * @Description :
	 * @param ADVC_ORDER_CHANGE_ID
	 * @param chldModelList
	 * @param userBean
	 * @return
	 */
	public void txEdit(String ADVC_ORDER_CHANGE_ID,ChangeadvcorderModel obj,
			List<ChangeadvcorderModelChld> chldModelList, UserBean userBean)throws ParseException;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param ADVC_ORDER_CHANGE_ID the ADVC_ORDER_CHANGE_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public void txChildEdit(String ADVC_ORDER_CHANGE_ID,String ADVC_ORDER_ID, List <ChangeadvcorderModelChld> modelList,String actionType)throws ParseException;
    
    /**
     * * 子表的批量删除
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs
     */
    public void txBatch4DeleteChild(String ADVC_ORDER_DTL_IDs,String ADVC_ORDER_ID);
    
    /**
     * * 根据子表Id批量加载子表信息
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <ChangeadvcorderModelChld> loadChilds(Map <String, String> params) ;
    
    /**
     * 修改提交状态
     */
    public void txCommitById(Map <String, String> params);
	
	
    /**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @预订单明细
	 * @param param
	 *            ADVC_ORDER_DTL_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> loadOldChild(String ADVC_ORDER_DTL_ID);
	
	/**
	 * @预订单明细列表
	 * @param param
	 *            ADVC_ORDER_DTL_IDS
	 * 
	 * @return the map< string, string>
	 */
	public List<Map<String, String>> loadOldChildList(String ADVC_ORDER_DTL_IDS);
	
	/**
	 * @预订单查询
	 * @param param
	 *            ADVC_ORDER_ID
	 * 
	 * @return the List<Map<String, String>>
	 */
	public List<Map<String, String>> loadOld(String ADVC_ORDER_ID);
	
	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	public List<ChangeadvcorderModelGchld> gchildQuery(String ADVC_ORDER_CHANGE_ID);
}