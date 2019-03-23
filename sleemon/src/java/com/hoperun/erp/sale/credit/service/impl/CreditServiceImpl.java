/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：CreditServiceImpl.java
 */
package com.hoperun.erp.sale.credit.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.credit.model.CreditModel;
import com.hoperun.erp.sale.credit.service.CreditService;

import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandServiceImpl.
 * 
 * @module 销售管理
 * @func 信用额度设定
 * @version 1.1
 * @author 郭利伟
 */
public class CreditServiceImpl extends BaseService implements CreditService {

	/**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
	@Override
	@SuppressWarnings("unchecked")
	public Map load(String param) {
		Map params = new HashMap();
        params.put("CHANN_ID", param);
        return (Map) load("CREDIT.loadById", params);
	}
	
	
	/**
     * 查询并分页
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@Override
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		
		return this.pageQuery("CREDIT.pageQuery", "CREDIT.pageCount", params, pageNo);
	}
	
	/**
     * 编辑
     * 
     * @param terminalId the terminal id
     * @param terminalModel the terminal model
     * @param userBean the user bean
     * 
     * @return the string
     */
	@Override
	public void txEdit(String channId, CreditModel model, UserBean userBean) {
		
		Map <String, Object> params = new HashMap <String, Object>();
		//上次设置的信用额度
		String INI_CREDIT_LAST_ = StringUtil.nullToSring(model.getINI_CREDIT_LAST());
		Double INI_CREDIT_LAST = StringUtil.getDouble(INI_CREDIT_LAST_);
		
		//初始总额
		String INI_CREDIT_ = StringUtil.nullToSring(model.getINI_CREDIT());			
		Double INI_CREDIT = StringUtil.getDouble(INI_CREDIT_);
		
		Double DIFF_CREDIT = INI_CREDIT-INI_CREDIT_LAST;
		 
		
		//渠道ID
		params.put("CHANN_ID", channId);	
		params.put("INI_CREDIT", INI_CREDIT);
		params.put("DIFF_CREDIT", DIFF_CREDIT);//信用差额    当前信用=当前信用+信用差额
		//信用修改人
		params.put("CREDIT_CRE_NAME", userBean.getXM());		
		update("CREDIT.updateById", params);
		String DIRECTION = "0";
		if(DIFF_CREDIT<0){
			DIRECTION = "1";
		}
		try{
			LogicUtil.insJOURNALInitCREDIT(channId,DIFF_CREDIT.toString(),DIRECTION);
		}catch(Exception ex){
			throw new ServiceException("记信用流水账出错 !");
		}
		
        
	}

 
	
	/**
     * 更新记录
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
	@Override
	public boolean txUpdateById(Map<String, String> params) {
		return update("CREDIT.updateById", params);
	}
}
