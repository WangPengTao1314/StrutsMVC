/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.inter.service.CommonBMGZService;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;

// TODO: Auto-generated Javadoc
/**
 * The Class BmgzHelper.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class BmgzHelper {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(BmgzHelper.class);
	
	/** The instance. */
	private static BmgzHelper instance;
	
	/** The common bmgz service. */
	private CommonBMGZService commonBMGZService;
	
	/**
	 * 获取BmgzHelper实例
	 * Description :.
	 * 
	 * @return the instance
	 */
	public static BmgzHelper getInstance(){
		if(null == instance){
			instance = (BmgzHelper) SpringContextUtil
			.getBean("bmgzHelper");
		}
		return instance;
	}
	
	/**
	 * 生成编码.
	 * 
	 * @param gzmc String
	 * 
	 * @return String
	 * 
	 * @throws ServiceException the service exception
	 */
	@SuppressWarnings("unchecked")
	public String createCode(String gzmc) throws ServiceException{
		List bmgzMxList = queryBmgzMx(gzmc);
		
		if(!validate(bmgzMxList)) {
			throw new ServiceException();
		}
		
		return buildCode(bmgzMxList);
	}
	
	/**
	 * 验证编码规则明细数据的有效性.
	 * 
	 * @param bmgzMxList List
	 * 
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean validate(List bmgzMxList) {
		if(bmgzMxList == null || bmgzMxList.size() != 2) {
			logger.error("bmgzMxList is null or bmgzMxList.size() != 2");
			return false;
		}
		
		if(bmgzMxList.get(0) == null || bmgzMxList.get(1) == null) {
			logger.error("bmgzMxList.get(0) == null || bmgzMxList.get(1) == null");
			return false;
		}
		
		//不同编码集会导致排序方式不一致
		Map resultMap0 = (HashMap)bmgzMxList.get(0);
		Map resultMap1 = (HashMap)bmgzMxList.get(1);
		
		if(!BusinessConsts.BMGZ_YYR_LSH.equals((String)resultMap0.get("DLX")+(String)resultMap1.get("DLX"))){
			if(!BusinessConsts.BMGZ_YYR_LSH.equals((String)resultMap1.get("DLX")+(String)resultMap0.get("DLX"))){
				logger.error("DLX is error.["+(String)resultMap0.get("DLX")+(String)resultMap1.get("DLX")+"]");
				return false;
			}
		}
		
        return true;
	}
	
	/**
	 * 构造段头.
	 * 
	 * @param nyrMap the nyr map
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	private String buildDT(HashMap nyrMap) {
		logger.info("Enter BmgzHelper.buildDT");
		return (String)nyrMap.get("DT");
	}
	
	/**
	 * 构造年月日.
	 * 
	 * @param nyrMap the nyr map
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	private String buildYYR(HashMap nyrMap) {
		logger.info("Enter BmgzHelper.buildYYR");
		String formatStr = "yyyy-MM-dd";
		String nys = (String)nyrMap.get("NYS");
		if(!StringUtil.isEmpty(nys)){
			int len = nys.trim().length();
			if(len == 2){
				formatStr = "yy-MM-dd"; 
			}
		}
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
        df.setLenient(false);
        String formatString = df.format(new Date());
        
        return formatString.replaceAll("-", "");
	}
	
	/**
	 * 构造Sequence.
	 * 
	 * @param lshMap the lsh map
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	private String buildSequence(HashMap lshMap) {
		logger.info("Enter BmgzHelper.buildSequence");
		String sequencemc = (String)lshMap.get("SEQUENCEMC");
		String sequence = commonBMGZService.selectSequence(sequencemc);
		sequence = sequence.trim();
		int len = sequence.length();
		BigDecimal bc = (BigDecimal)lshMap.get("BC");
		int bcint = Integer.valueOf(bc.toString());
		if(len >= bcint){
			return sequence.substring(len-bcint,len);
		}else{
			do{
				sequence = "0"+sequence;
			}while(sequence.length() < bcint);
			return sequence;
		}
	}
	
	/**
	 * 构造编码.
	 * 
	 * @param bmgzMxList List
	 * 
	 * @return String
	 */    
	@SuppressWarnings("unchecked")
	private String buildCode(List bmgzMxList) {
		StringBuffer sb = new StringBuffer();
		HashMap nyrMap = (HashMap)bmgzMxList.get(0);
		HashMap lshMap = (HashMap)bmgzMxList.get(1);
		//不同字符集会导致排序方式不一致
		if(!BusinessConsts.BMGZ_YYR.equals((String)nyrMap.get("DLX"))){
			 nyrMap = (HashMap)bmgzMxList.get(1);
			 lshMap = (HashMap)bmgzMxList.get(0);
		}
		sb.append(buildDT(nyrMap));
		sb.append(buildYYR(nyrMap));
		sb.append(buildSequence(lshMap));
		return sb.toString();
	}
	
	/**
	 * 查询编码明细数据.
	 * 
	 * @param bmdx the bmdx
	 * 
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	private List queryBmgzMx(String bmdx) {
		return commonBMGZService.selectBmgzMx(bmdx);
	}

	/**
	 * Gets the common bmgz service.
	 * 
	 * @return the common bmgz service
	 */
	public CommonBMGZService getCommonBMGZService() {
		return commonBMGZService;
	}

	/**
	 * Sets the common bmgz service.
	 * 
	 * @param commonBMGZService the new common bmgz service
	 */
	public void setCommonBMGZService(CommonBMGZService commonBMGZService) {
		this.commonBMGZService = commonBMGZService;
	}

}
