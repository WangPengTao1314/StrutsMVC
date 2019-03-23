package com.hoperun.commons.inter.dyncolumn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.inter.dyncolumn.model.DynColumnModel;
import com.hoperun.commons.inter.dyncolumn.service.DynColumnService;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;

/**
 * The Class DynColumnServiceImpl.
 * 
 * @module 共通模块
 * @func 动态列
 * @version 1.1
 * @author zhuxw
 */
public class DynColumnServiceImpl extends BaseService implements DynColumnService{

	/* (non-Javadoc)
	 * @see com.hoperun.commons.inter.dyncolumn.service.DynColumnService#getDynColumn(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DynColumnModel> getDynColumn(String xtyhId, String confNo,String isEdit) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("xtyhId", xtyhId);
		paramMap.put("confNo", confNo);
		paramMap.put("isEdit", isEdit);
		return this.findList("Common.queryDynColumn", paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.commons.inter.dyncolumn.service.DynColumnService#txSaveCustomizedPage(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void txSaveCustomizedPage(List<DynColumnModel> custList,
			String xtyhId, String confNo) {
		List<DynColumnModel> sqlModelList = new ArrayList<DynColumnModel>();
		for(DynColumnModel dyn : custList){
			dyn.setXTYHID(xtyhId);
			dyn.setTABNO(confNo);
			String uuid = StringUtil.uuid32len();
			dyn.setUSERCOLSHOWID(uuid);
			sqlModelList.add(dyn);
		}
		
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("xtyhId", xtyhId);
		paramMap.put("confNo", confNo);
		//先删除，再插入
		this.delete("Common.delCustomizedColumn", paramMap);
		
		if (!sqlModelList.isEmpty()) {
            this.batchObj4Update("Common.insertCustomizedColumn", sqlModelList);
        }
	}

}
