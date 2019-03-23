
package com.hoperun.drp.oamg.openbusspkg.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.openbusspkg.model.OpenbusspkgModel;
import com.hoperun.sys.model.UserBean;

/**
 * 开业大礼包申请单维护
 * @author gu_hongxiu
 */
public interface OpenbusspkgService extends IBaseService {
	
	/**
     * 获取所有品牌名称
     * @return
     */
    public List<String> getBrand();
    
	/**
	 * @查询
	 * @param params map
	 * @param pageNo
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo);
	
	/**
     * 加载.
     * @param param the param
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
     * @param OPEN_BUSS_PKG_ID
     * @param model
     * @param userBean
     * @return
     */
	public String txEdit(String OPEN_BUSS_PKG_ID, OpenbusspkgModel model, UserBean userBean);
    
    /**
     * 删除
     * @param map
     * @return
     */
    public boolean txDelete(Map<String,String> map);
	
	
}