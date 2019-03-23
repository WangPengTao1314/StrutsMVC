package com.hoperun.erp.oamg.monthvisitreport.service.impl;

import java.util.Map;

import com.hoperun.commons.service.BaseService;
import com.hoperun.erp.oamg.monthvisitreport.service.MonthvisitReqService;

public class MonthvisitReqServiceImpl  extends BaseService implements MonthvisitReqService {

		/**
		 * @return
		 */
		public Map queryMonthVisitreq(){
			return (Map) load("monthvisitReq.queryMonthVisitreq","");
		}
}
