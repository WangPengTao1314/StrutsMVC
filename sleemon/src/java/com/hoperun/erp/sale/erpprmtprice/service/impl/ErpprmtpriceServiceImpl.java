/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ChannServiceImpl.java
 */
package com.hoperun.erp.sale.erpprmtprice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.erpprmtprice.model.ErpprmtpriceModel;
import com.hoperun.erp.sale.erpprmtprice.service.ErpprmtpriceService;
import com.hoperun.sys.model.UserBean;

/**
 * The Class ChannServiceImpl.
 * 
 * @module 系统管理
 * @func 渠道信息
 * @version 1.0
 * @author 刘曰刚
 */
public class ErpprmtpriceServiceImpl extends BaseService implements ErpprmtpriceService {

	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@Override
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Erpprmtprice.pageQuery", "Erpprmtprice.pageCount", params, pageNo);
	}
	public void txInsertPrice(List<ErpprmtpriceModel> ModelList,String PRMT_PLAN_ID,UserBean userBean){
		List<Map<String,Object>> addList=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> upList=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < ModelList.size(); i++) {
			ErpprmtpriceModel model=ModelList.get(i);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("PRMT_PRVD_PRICE", model.getPRMT_PRVD_PRICE());
			if(StringUtil.isEmpty(model.getPRMT_PRICE_ID())){
				map.put("PRMT_PRICE_ID", StringUtil.uuid32len());
				map.put("PRMT_PLAN_ID", PRMT_PLAN_ID);
				map.put("PRD_ID", model.getPRD_ID());
				map.put("STATE", "启用");
				map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				map.putAll(LogicUtil.sysFild(userBean));
				addList.add(map);
			}else{
				map.put("PRMT_PRICE_ID", model.getPRMT_PRICE_ID());
				map.put("UPDATOR", userBean.getRYXXID());
				map.put("UPD_NAME", userBean.getXM());
				upList.add(map);
			}
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Erpprmtprice.insert", addList);
		}
		if (!upList.isEmpty()) {
			this.batch4Update("Erpprmtprice.update", upList);
		}
	}
	public long getCount(Map<String,String> map){
		Object obj=this.load("Erpprmtprice.pageCount", map);
		return Long.parseLong(obj.toString());
	}
	public void txUpdateState(String PRMT_PRICE_IDS,String STATE,UserBean userBean){
		Map<String,String> map=new HashMap<String, String>();
		map.put("PRMT_PRICE_IDS", PRMT_PRICE_IDS);
		map.put("STATE", STATE);
		map.put("UPDATOR", userBean.getRYXXID());
		map.put("UPD_NAME", userBean.getXM());
		this.update("Erpprmtprice.updateState", map);
	}
}
