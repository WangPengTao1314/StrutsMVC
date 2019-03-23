
package com.hoperun.erp.oamg.traincourse.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.oamg.traincourse.model.TraincourseModel;
import com.hoperun.sys.model.UserBean;

/**
 * 培训课程维护
 * @author gu_hongxiu
 *
 */
public interface TraincourseService extends IBaseService {
	
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
	public String txEdit(String SPCL_STORE_CC_REQ_ID, TraincourseModel model, UserBean userBean);
    
   
    /**
     * 删除
     * @param map
     * @return
     */
    public boolean txDelete(Map<String,String> map);
	
	
}