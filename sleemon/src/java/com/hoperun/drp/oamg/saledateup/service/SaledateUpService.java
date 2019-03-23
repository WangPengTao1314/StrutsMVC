package com.hoperun.drp.oamg.saledateup.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.saledateup.model.SaledateUpChildModel;
import com.hoperun.drp.oamg.saledateup.model.SaledateUpModel;
import com.hoperun.sys.model.UserBean;

public interface SaledateUpService extends IBaseService {
	
	
    /**
     * @param SALE_DATE_UP_ID
     * @param obj
     * @param userBean
     */
	public void txEdit(String SALE_DATE_UP_ID, SaledateUpModel obj, List<SaledateUpChildModel>childList,UserBean userBean);
	
	/**
	 * @param SALE_DATE_UP_ID
	 * @param userBean
	 */
	public void upSaledataup(String SALE_DATE_UP_ID,UserBean userBean);
	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    /**
     * @param SALE_DATE_UP_ID
     * @return
     */
    public Map <String, Object> loadById(String SALE_DATE_UP_ID);
    
    /**
 
    /**
     * 删除数据
     * @param  
     * @return true, if tx delete
     */
    public boolean txDelete(String SALE_DATE_UP_ID, UserBean userBean);
    
    /**
     * * 根据主表Id查询子表记录
     * @param SALE_DATE_UP_ID the SALE_DATE_UP_ID
     * @return the list
     */
    public List <SaledateUpChildModel> childQuery(String SALE_DATE_UP_ID);
    
    /**
     * * 根据子表Id批量加载子表信息
     * @return the list
     */
    public List <SaledateUpChildModel> loadChilds(Map <String, String> params);
    /**
     * 子表编辑
     * @param SALE_DATE_UP_ID
     * @param modelList
     * @return
     */
    public boolean txChildEdit(String SALE_DATE_UP_ID, List <SaledateUpChildModel> modelList);
	 /**
     * * 子表的批量删除
     * @param SALE_DATE_UP_DTL_IDS the SALE_DATE_UP_DTL_IDS
     */
    public void txBatch4DeleteChild(String SALE_DATE_UP_DTL_IDS);
    
    
    public Map<String,String>loadChannById(String CHANN_ID);
    /**
     * 导出用户终端分管的终端信息
     * @param params
     * @return
     */
    public List getChargTerminal(Map<String,String> params);
    
    /**
     * 导入
     * @param list
     * @param userBean
     */
    public void txImportExcel(List list,UserBean userBean);
    
}
