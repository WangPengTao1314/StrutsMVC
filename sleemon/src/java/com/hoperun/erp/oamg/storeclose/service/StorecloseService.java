
package com.hoperun.erp.oamg.storeclose.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.oamg.storeclose.model.StorecloseModel;
import com.hoperun.sys.model.UserBean;

/**
 * 专卖店撤店及终止申请单维护
 * @author gu_hongxiu
 *
 */
public interface StorecloseService extends IBaseService {
	
	/**
     * 获取所有品牌名称
     * @return
     */
    public List<String> getBrand();
    
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
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map
     */
    public Map<String,String> load(String param);
    
    
    public Map<String,String> loadT(String param);
  
	/**
	 * 加载图片.
	 * @param param
	 * @return
	 */
	public String loadAtt(String param);
    
	/**
     * 编辑：新增/修改
     * @param SPCL_STORE_CC_REQ_ID
     * @param model
     * @param userBean
     * @return
     */
	public String txEdit(String SPCL_STORE_CC_REQ_ID, StorecloseModel model, UserBean userBean);
    
   
    /**
     * 删除
     * @param map
     * @return
     */
    public boolean txDelete(Map<String,String> map);
    
    public  String  queryName(String BUSS_SCOPE);
	
    /**
     * @param SPCL_STORE_CC_REQ_ID
     * @return
     */
    public  int queryTerminalState(String SPCL_STORE_CC_REQ_ID);
    
    /**
     * @param SPCL_STORE_CC_REQ_ID
     * @param userBean
     */
    public  void upTerminal(String SPCL_STORE_CC_REQ_ID,UserBean userBean);
    
    /**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params);
}