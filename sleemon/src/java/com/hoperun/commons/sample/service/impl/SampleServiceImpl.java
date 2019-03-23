/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.sample.service.impl;

import java.util.List;

import com.hoperun.commons.sample.model.SampleTree;
import com.hoperun.commons.sample.service.SampleService;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.ResourceUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class SampleServiceImpl.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class SampleServiceImpl extends BaseService implements SampleService {
	
	/* (non-Javadoc)
	 * @see com.hoperun.commons.sample.service.SampleService#getTree()
	 */
	public List<SampleTree> getTree() throws Exception{
		List<SampleTree> menus = this.findList("Sample.queryTree", "");
			return ResourceUtil.getZTreeFromList(menus,SampleTree.class);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.commons.sample.service.SampleService#getTreeAsync(java.lang.String)
	 */
	@Override
	public List<SampleTree> getTreeAsync(String sjjg)  throws Exception{
		return this.findList("Sample.queryTreeAsync",sjjg);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.commons.sample.service.SampleService#getTreeAsyncInit()
	 */
	@Override
	public List<SampleTree> getTreeAsyncInit()  throws Exception{
		return this.findList("Sample.queryTreeAsyncInit", "");
	}
   //add by zhuxw  调用存储过程例子
	//public Map<String,Object> doProduce(List<Object> params,List<String> outParams,List<Integer> outParamType) {
	//	return (Map<String, Object>) this.callProcedure("{call P_LC_LCCX(?,?,?,?,?)}", params,outParams,outParamType);
	//}
	
}
