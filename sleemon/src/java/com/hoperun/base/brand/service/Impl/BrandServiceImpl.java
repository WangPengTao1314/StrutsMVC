/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：RYXXServiceImpl.java
 */
package com.hoperun.base.brand.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.base.brand.model.BrandModel;
import com.hoperun.base.brand.service.BrandService;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;

import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandServiceImpl.
 * 
 * @module 系统管理
 * @func 品牌信息
 * @version 1.1
 * @author 郭利伟
 */
public class BrandServiceImpl extends BaseService implements BrandService {

	/**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
	@Override
	@SuppressWarnings("unchecked")
	public Map load(String param) {
		Map params = new HashMap();
        params.put("BRAND_ID", param);
        return (Map) load("BRAND.loadById", params);
	}
	
	
	/**
     * 查询并分页
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@Override
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		
		return this.pageQuery("BRAND.pageQuery", "BRAND.pageCount", params, pageNo);
	}

	
	/**
     * 删除数据
     * 
     * @param BRANDID the brandId
     * 
     * @return true, if tx delete
     */	
	@Override
	public boolean txDelete(String brandId, UserBean userBean) {
		Map <String, String> params = new HashMap <String, String>();
        params.put("BRAND_ID", brandId);
        params.put("UPDATOR", userBean.getXTYHID());
        params.put("UPD_NAME", userBean.getXM());
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
        params.put("BRAND_ID_UUID", StringUtil.uuid32len());
        return update("BRAND.delete", params);
	}

	
	/**
     * 编辑
     * 
     * @param brandId the brand id
     * @param brandModel the brand model
     * @param userBean the user bean
     * 
     * @return the string
     */
	@Override
	public void txEdit(String brandId, BrandModel model, UserBean userBean) {
		
		Map <String, String> params = new HashMap <String, String>(16);
        params.put("BRAND", model.getBRAND());				//品牌名称
        params.put("BRAND_EN", model.getBRAND_EN());		//英文名称
        params.put("UPDATOR", userBean.getXTYHID());		//更新人ID
        params.put("UPD_NAME", userBean.getXM());			//更新人名称
        params.put("REMARK", model.getREMARK());			//备注

        if (StringUtils.isEmpty(brandId)) {
        	params.put("BRAND_ID", model.getBRAND_ID());		    //品牌编号
            params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);	//状态
            params.put("CREATOR", userBean.getXTYHID());			//制单人ID
            params.put("CRE_NAME", userBean.getXM());				//制单人名称
            params.put("DEPT_ID", userBean.getBMXXID());			//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());			//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());				//制单机构ID
            params.put("ORG_NAME", userBean.getJGMC());				//制单机构名称
            insert("BRAND.insert", params);
        } else {
        	params.put("BRAND_ID", model.getBRAND_ID());		    //品牌编号
            txUpdateById(params);
        }
		
	}

	
	/**
     * 更新记录
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
	@Override
	public boolean txUpdateById(Map<String, String> params) {
		return update("BRAND.updateById", params);
	}
	
	
	/**
     * 修改状态为启用
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
	@Override
	public boolean txStartById(Map<String, String> params) {
		return update("BRAND.updateById", params);
	}
	
	
	/**
     * 修改状态为停用
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
	@Override
	public boolean txStopById(Map<String, String> params) {
		return update("BRAND.updateById", params);
	}
	
}
