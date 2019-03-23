/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：TempCreditReqServiceImpl.java
 */
package com.hoperun.drp.oamg.channcontractup.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.channcontractup.model.ChanncontractupModel;
import com.hoperun.drp.oamg.channcontractup.service.ChanncontractupService;
import com.hoperun.sys.model.UserBean;
/**
 * The Class ChanncontractupServiceImpl.
 * 
 * @module 渠道合同管理
 * @func 渠道合同上传
 * @version 1.1
 * @author 刘曰刚
 */
public class ChanncontractupServiceImpl extends BaseService implements ChanncontractupService {
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		// TODO Auto-generated method stub
		return this.pageQuery("Channcontractup.pageQuery", "Channcontractup.pageCount", params, pageNo);
	}
	
	public Map<String, String> load(String CHANN_CONTRACT_ID) {
		// TODO Auto-generated method stub
        return (Map<String, String>) load("Channcontractup.loadById", CHANN_CONTRACT_ID);
	}
	/**
     * 编辑.
     * 
     * @param userBean the user bean
     * 
     * @return the string
     */
	@Override			
	public String txEdit(String CHANN_CONTRACT_ID, ChanncontractupModel model, UserBean userBean) {
		// TODO Auto-generated method stub
		Map <String, Object> params = new HashMap <String, Object>();
		params.put("CHANN_ID", model.getCHANN_ID());//渠道ID
		params.put("CHANN_NO", model.getCHANN_NO());//渠道NO
		params.put("CHANN_NAME", model.getCHANN_NAME());//渠道名称
		params.put("CONTRACT_ATT", model.getCONTRACT_ATT());//合同附件
		params.put("YEAR", model.getYEAR());//年份
		params.put("REMARK", model.getREMARK());//备注
		params.put("YEAR_PLAN_AMOUNT", model.getYEAR_PLAN_AMOUNT());//年度指标
		params.put("FIRST_QUARTER_AMOUNT", model.getFIRST_QUARTER_AMOUNT());//一季度指标
		params.put("SECOND_QUARTER_AMOUNT", model.getSECOND_QUARTER_AMOUNT());//二季度指标
		params.put("THIRD_QUARTER_AMOUNT", model.getTHIRD_QUARTER_AMOUNT());//三季度指标
		params.put("FOURTH_QUARTER_AMOUNT", model.getFOURTH_QUARTER_AMOUNT());//四季度指标
        if (StringUtils.isEmpty(CHANN_CONTRACT_ID)) {
        	CHANN_CONTRACT_ID=StringUtil.uuid32len();
        	params.put("CHANN_CONTRACT_ID", CHANN_CONTRACT_ID);//渠道合同ID
        	params.put("CHANN_CONTRACT_NO", LogicUtil.getBmgz("ERP_CHANN_CONTRACT_UP_NO"));//渠道合同编号
        	params.put("STATE", "未提交");
        	params.putAll(LogicUtil.sysFild(userBean));
            txInsert(params);
        } else {
        	params.put("CHANN_CONTRACT_ID", CHANN_CONTRACT_ID);//渠道合同ID
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称
            params.put("UPD_TIME", "数据库时间");//更新时间
            txUpdateById(params);
            clearCaches(CHANN_CONTRACT_ID);
        }
        if(!checkRepeatYear()){
        	throw new ServiceException("同一年份存在相同渠道合同附件，不能保存 !");
        }
        return CHANN_CONTRACT_ID;
	}
	
	  /**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, Object> params) {
        insert("Channcontractup.insert", params);
        return true;
    }
    /**
     * 更新记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, Object> params) {

        return update("Channcontractup.updateById", params);
    }
    
    /**
     * 软删除.
     * 
     * @param CHANN_ID the chann id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
	public boolean txDelete(String CHANN_CONTRACT_ID, UserBean userBean) {
		// TODO Auto-generated method stub
		 Map <String, String> params = new HashMap <String, String>();
        params.put("CHANN_CONTRACT_ID", CHANN_CONTRACT_ID);//渠道合同ID
        params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称
        params.put("UPD_TIME", "数据库时间");//更新时间
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);//删除标记
        return delete("Channcontractup.delete", params);
	}
	//提交
    public void toCommit(Map<String,String> map){
    	this.update("Channcontractup.updateById", map);
    }
    public boolean checkRepeatYear(){
    	int count=StringUtil.getInteger(this.load("Channcontractup.checkRepeatYear",""));
    	if(count>0){
    		return false;
    	}
    	return true;
    }
}
