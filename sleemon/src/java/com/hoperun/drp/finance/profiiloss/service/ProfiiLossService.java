package com.hoperun.drp.finance.profiiloss.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.finance.profiiloss.model.ProfiiLossModelChld;

public interface ProfiiLossService extends IBaseService{
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	/**
	 * 根据主表Id查询子表记录
	 * @param PROFIT_LOSS_ID
	 * @return
	 */
	public List <ProfiiLossModelChld> childQuery(String PROFIT_LOSS_ID);
	/**
	 * 主表详细页面
	 * @param PROFIT_LOSS_ID
	 * @return
	 */
	public Map<String,String> load(String PROFIT_LOSS_ID);
	public String getBillType(String PROFIT_LOSS_ID);
}
