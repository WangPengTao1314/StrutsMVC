package com.hoperun.drp.report.saleProStatus.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.service.IBaseService;

public interface SaleProStatusService extends IBaseService{
		
	/**
	 * 查询U9数据
	 * @return
	 */
	public List pageQueryU9(Map params);
	
	/**
	 * 查询U9数据 出货计划改造后
	 * @return
	 */
	public List<Map<String,Object>> pageQueryU9New(Map<String,String> params);
	

}
