package com.hoperun.commons.inter.dyncolumn.service;

import java.util.List;

import com.hoperun.commons.inter.dyncolumn.model.DynColumnModel;
import com.hoperun.commons.service.IBaseService;
// TODO: Auto-generated Javadoc

/**
 * The Interface DynColumnService.
 * 
 * @module 共通模块
 * @func 动态列
 * @version 1.1
 * @author zhuxw
 */
public interface DynColumnService extends IBaseService {

	/**
	 * Gets the dyn column.
	 * 
	 * @param xtyhId the xtyh id
	 * @param confNo the conf no
	 * @param isEdit the is edit
	 * 
	 * @return the dyn column
	 */
	public List<DynColumnModel> getDynColumn(String xtyhId, String confNo,String isEdit);

	/**
	 * Tx save customized page.
	 * 
	 * @param custList the cust list
	 * @param xtyhId the xtyh id
	 * @param confNo the conf no
	 */
	public void txSaveCustomizedPage(List<DynColumnModel> custList, String xtyhId,
			String confNo);

}
