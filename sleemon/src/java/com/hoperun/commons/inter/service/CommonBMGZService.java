/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.inter.service;

import java.util.HashMap;
import java.util.List;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.service.BaseService;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonBMGZService.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
public class CommonBMGZService  extends BaseService {

	/**
	 * 查询编码规则明细.
	 * 
	 * @param bmdx the bmdx
	 * 
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List selectBmgzMx(String bmdx) {
		
		HashMap queryMap = new HashMap();
		queryMap.put("bmdx", bmdx);
		queryMap.put("state", BusinessConsts.JC_STATE_ENABLE);
		List list = findList("Common.select001_T_SYS_BMGZ",queryMap);
		return list;
	}
	
	/**
	 * 查询Sequence.
	 * 
	 * @param sequencemc String
	 * 
	 * @return String
	 */
	public String selectSequence(String sequencemc) {
		
		Object object = load("Common.select002_T_SYS_BMGZ",sequencemc);
		
		return (String)object;
	}
}
