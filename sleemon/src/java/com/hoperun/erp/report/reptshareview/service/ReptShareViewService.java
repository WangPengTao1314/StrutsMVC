package com.hoperun.erp.report.reptshareview.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;

public interface ReptShareViewService extends IBaseService {
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
	 * 修改状态
	 * @param map
	 */
	public void txUpState(Map<String,String> map);
	
	/**
	 * 删除
	 * @param RPT_SCHE_SHAR_ID
	 */
	public void txDelete(String RPT_SCHE_SHAR_ID);
}
