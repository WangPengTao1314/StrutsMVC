package com.hoperun.report;

import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;

/**
 * 报表公用
 * @author gu_hongxiu
 *
 */
public class BaseRptService extends BaseService{
	
	/**
	 * 格式化报表sql
	 * @param queryParam
	 * @param sumParam
	 * @param orderParam
	 * @param fromSql
	 * @param whereSql
	 * @return
	 */
	public String formatSql(String queryParam, String sumParam,String orderParam, String fromSql,String whereSql) {	

		if (StringUtil.isEmpty(queryParam) || StringUtil.isEmpty(fromSql)) {
			return "";
		}
		
		String endSql = fromSql + whereSql;
		String querySql = " select 1 as XH, " + queryParam + endSql;
		String sumSql = " select 2 as XH, " + sumParam + endSql+"group by a.STATE";
		
		StringBuffer rptSql = new StringBuffer(querySql);
				
		rptSql.append(" union  all select total.* from ( ");
		rptSql.append(sumSql);		
		rptSql.append(" ) total where exists ( ");
		rptSql.append(querySql);
		rptSql.append(" ) ");
		
		rptSql.append(" order by XH asc ");
		if(!StringUtil.isEmpty(orderParam)){
			rptSql.append(", ");
			rptSql.append(orderParam);
		}
		
		
		
		return "select rownum, temp.* from (" + rptSql.toString() + ") temp";
	}
}
