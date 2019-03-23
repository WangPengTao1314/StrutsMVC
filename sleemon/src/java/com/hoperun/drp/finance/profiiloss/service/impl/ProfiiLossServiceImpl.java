package com.hoperun.drp.finance.profiiloss.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.drp.finance.profiiloss.model.ProfiiLossModelChld;
import com.hoperun.drp.finance.profiiloss.service.ProfiiLossService;

public class ProfiiLossServiceImpl extends BaseService implements ProfiiLossService {

	public List<ProfiiLossModelChld> childQuery(String PROFIT_LOSS_ID) {
		Map params = new HashMap();
        params.put("PROFIT_LOSS_ID", PROFIT_LOSS_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("ProfiiLoss.queryChldByFkId", params);
	}

	public Map<String, String> load(String PROFIT_LOSS_ID) {
		return (Map<String,String>) load("ProfiiLoss.loadById", PROFIT_LOSS_ID);
	}

	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("ProfiiLoss.pageQuery", "ProfiiLoss.pageCount",params, pageNo);
	}
	public String getBillType(String PROFIT_LOSS_ID){
		return (String) this.load("ProfiiLoss.getBillType",PROFIT_LOSS_ID);
	}
}
