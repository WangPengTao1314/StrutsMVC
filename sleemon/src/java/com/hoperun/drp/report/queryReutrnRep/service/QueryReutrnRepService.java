package com.hoperun.drp.report.queryReutrnRep.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.service.IBaseService;

public interface QueryReutrnRepService extends IBaseService{
		
	/**
	 * 查询U9数据
	 * @return
	 */
	public List pageQueryU9(Map params);
	

}
