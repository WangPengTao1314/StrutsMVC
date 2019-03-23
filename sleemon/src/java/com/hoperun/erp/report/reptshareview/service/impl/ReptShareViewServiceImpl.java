package com.hoperun.erp.report.reptshareview.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.erp.report.reptshareview.service.ReptShareViewService;

public class ReptShareViewServiceImpl extends BaseService implements ReptShareViewService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("ReptShareView.pageQuery", "ReptShareView.pageCount",
				params, pageNo);
	}

	public void txUpState(Map<String, String> map) {
		this.update("ReptShareView.updateById", map);
	}

	public void txDelete(String RPT_SCHE_SHAR_ID) {
		Map<String,String> map=new HashMap<String, String>();
		map.put("RPT_SCHE_SHAR_ID", RPT_SCHE_SHAR_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		this.update("ReptShareView.updateById", map);
	}
}
