package com.hoperun.report;

import com.hoperun.commons.service.IBaseService;

/**
 * 报表公用
 * @author gu_hongxiu
 *
 */
public interface IBaseRptService extends IBaseService {
	
	/**
	 * 格式化报表sql
	 * @param queryParam
	 * @param sumParam
	 * @param orderParam
	 * @param fromSql
	 * @param whereSql
	 * @return
	 */
	public String formatSql(String queryParam,String sumParam,String orderParam, String fromSql,String whereSql);
	
}
