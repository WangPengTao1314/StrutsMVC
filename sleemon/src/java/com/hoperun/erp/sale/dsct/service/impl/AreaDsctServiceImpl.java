/**
 * 项目名称：喜临门
 * 系统名：区域折扣信息
 * 文件名：AreaDsctAction.java
 */
package com.hoperun.erp.sale.dsct.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.dsct.model.AreaDsctModel;
import com.hoperun.erp.sale.dsct.service.AreaDsctService;
import com.hoperun.sys.model.UserBean;

/**
 * The Class AreaDsctModel.
 * 
 * @module 总部->销售管理->价格管理
 * @func 区域折扣信息
 * @version 1.0
 * @author 王志格
 */
public class AreaDsctServiceImpl extends BaseService implements AreaDsctService {

	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@SuppressWarnings("unchecked")
	@Override
	public IListPage pageQuery(Map params, int pageNo) {
		
		return this.pageQuery("AREA_DSCT.pageQuery", "AREA_DSCT.pageCount", params, pageNo);
	}
	
	/**
     * 编辑
     * 
     */
	public void txEdit(String AREA_DSCT_ID, AreaDsctModel model, UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
		
		params.put("AREA_ID", model.getAREA_ID());
		params.put("AREA_NO", model.getAREA_NO());
		params.put("AREA_NAME", model.getAREA_NAME());
		params.put("PRD_ID", model.getPRD_ID());
		params.put("PRD_NO", model.getPRD_NO());
		params.put("PRD_NAME", model.getPRD_NAME());
		params.put("PRD_SPEC", model.getPRD_SPEC());
		params.put("DECT_TYPE", model.getDECT_TYPE());
		params.put("DECT_RATE", model.getDECT_RATE());
		params.put("BASE_PRICE", model.getBASE_PRICE());
		params.put("DECT_PRICE",model.getDECT_PRICE());
		
		if(StringUtil.isEmpty(AREA_DSCT_ID)){
			AREA_DSCT_ID = StringUtil.uuid32len();
			params.put("AREA_DSCT_ID", AREA_DSCT_ID);
			insert("AREA_DSCT.insert",params);
		}else{
			params.put("AREA_DSCT_ID", AREA_DSCT_ID);
			update("AREA_DSCT.updateById",params);
		}
		
		params.put("STATE", BusinessConsts.JC_STATE_ENABLE);//启用
		//先根据 区域ID 删除当前区域以及下级区域的 折扣信息
		delete("AREA_DSCT.delFlatByAreaId",params);
		//再 根据区域ID 将该区域下的所有货品的折扣信息 插入表（包括其下级区域）
		insert("AREA_DSCT.insertFlatByAreaId",params);
	}
	
	/**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    public Map load(String param){
    	 
        return (Map) load("AREA_DSCT.loadById", param);
    }
	
    
    /**
     * 删除 
     * @param AREA_DSCT_ID  区域折扣ID
     * @param AREA_ID 区域ID
     */
    public void txDeleteById(String AREA_DSCT_ID,String AREA_ID){
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("AREA_DSCT_ID", AREA_DSCT_ID);
    	
    	params.put("AREA_ID", AREA_ID);//启用
    	params.put("STATE", BusinessConsts.JC_STATE_ENABLE);//启用
		//先根据 区域ID 删除当前区域以及下级区域的 折扣信息
		delete("AREA_DSCT.delFlatByAreaId",params);
		//第二步 才能删除主表的数据
		delete("AREA_DSCT.deleteById",params);
		
		//再 根据区域ID 将该区域下的所有货品的折扣信息 插入表（包括其下级区域）
		insert("AREA_DSCT.insertFlatByAreaId",params);
		
    }
}
