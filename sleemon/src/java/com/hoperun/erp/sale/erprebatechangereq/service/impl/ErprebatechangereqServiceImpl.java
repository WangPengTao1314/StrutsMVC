/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：TempCreditReqServiceImpl.java
 */
package com.hoperun.erp.sale.erprebatechangereq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.erprebatechangereq.model.ErprebatechangereqModel;
import com.hoperun.erp.sale.erprebatechangereq.service.ErprebatechangereqService;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;
/**
 * The Class TempCreditReqServiceImpl.
 * 
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author 刘曰刚
 */
public class ErprebatechangereqServiceImpl extends BaseService implements ErprebatechangereqService {
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@Override
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		// TODO Auto-generated method stub
		return this.pageQuery("Erprebatechangereq.pageQuery", "Erprebatechangereq.pageCount", params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String REBATE_CHANGE_REQ_ID) {
		// TODO Auto-generated method stub
        return (Map<String, String>) load("Erprebatechangereq.loadById", REBATE_CHANGE_REQ_ID);
	}
	/**
     * 编辑.
     * 
     * @param CHANN_ID the chann id
     * @param ChannModel the chann model
     * @param userBean the user bean
     * 
     * @return the string
     */
	@Override
	public String txEdit(String REBATE_CHANGE_REQ_ID, ErprebatechangereqModel model, UserBean userBean) {
		// TODO Auto-generated method stub
		Map <String, Object> params = new HashMap <String, Object>();
		params.put("BILL_TYPE", model.getBILL_TYPE());
		params.put("REQ_PSON_ID", model.getREQ_PSON_ID());
		params.put("REQ_PSON_NAME", model.getREQ_PSON_NAME());
		params.put("CHANGE_REBATE", model.getCHANGE_REBATE());
		params.put("REMARK", model.getREMARK());
		params.put("CHANN_ID", model.getCHANN_ID());
		params.put("CHANN_NO", model.getCHANN_NO());
		params.put("CHANN_NAME", model.getCHANN_NAME());
		params.put("CHANN_TYPE", model.getCHANN_TYPE());
		params.put("BILL_TYPE", model.getBILL_TYPE());
		params.put("REBATE_TYPE", model.getREBATE_TYPE());
        if (StringUtils.isEmpty(REBATE_CHANGE_REQ_ID)) {
        	REBATE_CHANGE_REQ_ID=StringUtil.uuid32len();
        	params.put("REBATE_CHANGE_REQ_ID", REBATE_CHANGE_REQ_ID);
        	params.put("REBATE_CHANGE_REQ_NO", LogicUtil.getBmgz("ERP_REBATE_CHANGE_REQ_NO"));
        	params.put("STATE", BusinessConsts.UNCOMMIT);
        	params.putAll(LogicUtil.sysFild(userBean));
            txInsert(params);
        } else {
        	params.put("REBATE_CHANGE_REQ_ID", REBATE_CHANGE_REQ_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称
            params.put("UPD_TIME", "数据库时间");//更新时间
            txUpdateById(params);
            clearCaches(REBATE_CHANGE_REQ_ID);
        }
        return REBATE_CHANGE_REQ_ID;
	}
	
	  /**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, Object> params) {
        insert("Erprebatechangereq.insert", params);
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

        return update("Erprebatechangereq.updateById", params);
    }
    
    /**
     * 软删除.
     * 
     * @param CHANN_ID the chann id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
	public boolean txDelete(String REBATE_CHANGE_REQ_ID, UserBean userBean) {
		// TODO Auto-generated method stub
		 Map <String, String> params = new HashMap <String, String>();
        params.put("REBATE_CHANGE_REQ_ID", REBATE_CHANGE_REQ_ID);//临时信用申请ID
        params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称
        params.put("UPD_TIME", "数据库时间");//更新时间
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);//删除标记
        return delete("Erprebatechangereq.delete", params);
	}

	public void upTEMP_CREDIT_VALDT(Map<String, String> map) {
		this.update("Erprebatechangereq.upTEMP_CREDIT_VALDT", map);
	}
	
	public Map<String,String> loadChann(String CHANNID){
		Map<String, String> params = new HashMap<String, String>();
        params.put("CHANN_ID", CHANNID);
        return (Map<String, String>) load("chann.loadById", params);
	}
	
    /**
     * 关闭
     * @param REBATE_CHANGE_REQ_ID
     * @param userBean
     */
    public void txClose(String REBATE_CHANGE_REQ_ID, UserBean userBean){
    	 Map <String, String> params = new HashMap <String, String>();
         params.put("REBATE_CHANGE_REQ_ID", REBATE_CHANGE_REQ_ID);//临时信用申请ID
         params.put("UPDATOR", userBean.getXTYHID());//更新人id
         params.put("UPD_NAME", userBean.getXM());//更新人名称
         params.put("UPD_TIME", "数据库时间");//更新时间
         params.put("STATE", "关闭");
         update("Erprebatechangereq.updateById", params);
    }
  //获取渠道返利
    public Map<String,String> getRebate(String CHANN_ID){
    	return (Map<String, String>) this.load("Erprebatechangereq.getRebate",CHANN_ID);
    }
    /**
     * 导出
     * @param map
     * @return
     */
    public List downQuery(Map<String,String> map){
    	return this.findList("Erprebatechangereq.query", map);
    }
    
    
	/* 批量提交返利订单
	 * (non-Javadoc)
	 * @see com.hoperun.erp.sale.erprebatechangereq.service.ErprebatechangereqService#txBatchCommit(java.lang.String)
	 */
	@Override
	public void txBatchCommit(HttpServletRequest request,String AUD_FLOW_INS,String AUD_BUSS_TYPE,String REBATE_CHANGE_REQ_IDS)throws Exception {
		isExistRls(REBATE_CHANGE_REQ_IDS);
		boolean isFlowFlg = isFlowModelStats(AUD_BUSS_TYPE);
		String [] REBATE_CHANGE_REQ_ID_ARR = REBATE_CHANGE_REQ_IDS.split(",");
		if(!isFlowFlg){  //没有审批流或审批流未生效
			for(int i=0;i<REBATE_CHANGE_REQ_ID_ARR.length;i++){
				String stats = "审核通过";
				String REBATE_CHANGE_REQ_ID = REBATE_CHANGE_REQ_ID_ARR[i];
				REBATE_CHANGE_REQ_ID = REBATE_CHANGE_REQ_ID.replaceAll("'", "");
		        updateRabateChangeStats(request,REBATE_CHANGE_REQ_ID,stats);
		         
				request.setAttribute("currentState",stats);
				request.setAttribute("id",REBATE_CHANGE_REQ_ID);
				FlowInterface flowInterface = (FlowInterface) Class.forName(AUD_FLOW_INS).newInstance();
				flowInterface.afterAuditing(request, null, null);	
			}
		}else{  //存在已生效的审批流
			for(int i=0;i<REBATE_CHANGE_REQ_ID_ARR.length;i++){
				String stats = "提交";
				String REBATE_CHANGE_REQ_ID = REBATE_CHANGE_REQ_ID_ARR[i];
				REBATE_CHANGE_REQ_ID = REBATE_CHANGE_REQ_ID.replaceAll("'", "");
		        updateRabateChangeStats(request,REBATE_CHANGE_REQ_ID,stats);
			}
		}

	}
	
	
	/* 复制返利订单
	 * (non-Javadoc)
	 * @see com.hoperun.erp.sale.erprebatechangereq.service.ErprebatechangereqService#txBatchCommit(java.lang.String)
	 */
	@Override
	public String txCopyRecord(HttpServletRequest request,String REBATE_CHANGE_REQ_ID)throws Exception {
		String REBATE_CHANGE_REQ_NO = LogicUtil.getBmgz("ERP_REBATE_CHANGE_REQ_NO");
		try{
			UserBean userBean =  ParamUtil.getUserBean(request);
			Map <String, String> params = new HashMap <String, String>();
			params.put("REBATE_CHANGE_REQ_ID", REBATE_CHANGE_REQ_ID);
			params.put("REBATE_CHANGE_REQ_NO",REBATE_CHANGE_REQ_NO );
	        params.put("STATE", "未提交");
			params.put("REQ_PSON_NAME", userBean.getXM());
			params.put("REQ_PSON_ID", userBean.getXTYHID());
			params.put("CRE_NAME", userBean.getXM());
			params.put("CREATOR", userBean.getXTYHID());
	        update("Erprebatechangereq.copyRebateRecord", params);
		}catch(Exception ex){
			throw new ServiceException("复制返利变更申请单出错,请联系管理员!");
		}
		return REBATE_CHANGE_REQ_NO;
	}
	

	
	private boolean isExistRls(String REBATE_CHANGE_REQ_IDS)throws Exception{
		Map <String, String> params = new HashMap <String, String>();
		params.put("REBATE_CHANGE_REQ_IDS", REBATE_CHANGE_REQ_IDS);
		String rebateNo = (String)this.load("Erprebatechangereq.isExsitRls",params);
		if(rebateNo!=null){
			throw new ServiceException(rebateNo+":已经审核通过,不允许重复提交!");
		}
		return false;
	}
	
	private boolean isExistNoRls(String REBATE_CHANGE_REQ_IDS)throws Exception{
		Map <String, String> params = new HashMap <String, String>();
		params.put("REBATE_CHANGE_REQ_IDS", REBATE_CHANGE_REQ_IDS);
		String rebateNo = (String)this.load("Erprebatechangereq.isExsitRls",params);
		if(rebateNo==null){
			throw new ServiceException(rebateNo+":不是审核通过状态,不允许弃审!");
		}
		return false;
	}
	
	/**判断审批流是否失效,失效支提审核通过,否则改为提交状态
	 * @return
	 */
	private boolean isFlowModelStats(String AUD_BUSS_TYPE)throws Exception{
		try{
			Map <String, String> params = new HashMap <String, String>();
			 params.put("BUSINESSTYPE", AUD_BUSS_TYPE);
			 String MODELSTATE = (String) this.load("Erprebatechangereq.getFlowState",params);
			 if("已生效".equals(MODELSTATE)){
				 return true;
			 }
			return false;
		}catch(Exception ex){
			throw new ServiceException("审核流异常,请联系管理员!");
		}
	}
	
	/**更新主表状态
	 * @param state
	 */
	private void updateRabateChangeStats(HttpServletRequest request,String REBATE_CHANGE_REQ_ID,String state)throws Exception{
		try{
			UserBean userBean =  ParamUtil.getUserBean(request);
			Map <String, String> params = new HashMap <String, String>();
			params.put("REBATE_CHANGE_REQ_ID", REBATE_CHANGE_REQ_ID);
	        params.put("STATE", state);
			params.put("UPD_NAME", userBean.getXM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_TIME", "sysdate");
	        update("Erprebatechangereq.updateById", params);
		}catch(Exception ex){
			throw new ServiceException("更新主表状态出错,请联系管理员!");
		}
	}
	
	/**更新主表状态(弃审时要清空变更前返利和变更后两返)
	 * @param state
	 */
	private void updateRabateChange(HttpServletRequest request,String REBATE_CHANGE_REQ_ID,String state)throws Exception{
		try{
			UserBean userBean =  ParamUtil.getUserBean(request);
			Map <String, String> params = new HashMap <String, String>();
			params.put("REBATE_CHANGE_REQ_ID", REBATE_CHANGE_REQ_ID);
	        params.put("STATE", state);
			params.put("UPD_NAME", userBean.getXM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_TIME", "sysdate");
	        update("Erprebatechangereq.updateRebateChangeByCancel", params);
		}catch(Exception ex){
			throw new ServiceException("更新主表状态出错,请联系管理员!");
		}
	}
	
	  
	
	/* 批量提交返利订单
	 * (non-Javadoc)
	 * @see com.hoperun.erp.sale.erprebatechangereq.service.ErprebatechangereqService#txBatchCommit(java.lang.String)
	 */
	@Override
	public void txRebateRevoke(HttpServletRequest request,String REBATE_CHANGE_REQ_ID)throws Exception {
		checkRevokeStats(REBATE_CHANGE_REQ_ID);
		REBATE_CHANGE_REQ_ID = REBATE_CHANGE_REQ_ID.replaceAll("'", "");
		updateRabateChangeStats(request,REBATE_CHANGE_REQ_ID,"撤销");
	}
	
	/**检查是否已经审
	 * 
	 */
	private void checkRevokeStats(String REBATE_CHANGE_REQ_ID){
		Map <String, String> params = new HashMap <String, String>();
		params.put("REBATE_CHANGE_REQ_IDS", REBATE_CHANGE_REQ_ID);
		String stats = (String)this.load("Erprebatechangereq.checkStats",params);
		if(stats==null){
			throw new ServiceException("单据异常,请联系管理员!");
		}
		if("撤销".equals(stats)){
			throw new ServiceException("单据已经是撤销状态,不能重复撤销!");
		}
		if("审核通过".equals(stats)){
			throw new ServiceException("单据已经审核通过,不允许撤销!");
		}	
	}
	
	/* 批量提交返利订单
	 * (non-Javadoc)
	 * @see com.hoperun.erp.sale.erprebatechangereq.service.ErprebatechangereqService#txBatchCommit(java.lang.String)
	 */
	@Override
	public void txRebateAudit(HttpServletRequest request,String AUD_FLOW_INS,String REBATE_CHANGE_REQ_IDS)throws Exception {
		isExistRls(REBATE_CHANGE_REQ_IDS);
		String [] REBATE_CHANGE_REQ_ID_ARR = REBATE_CHANGE_REQ_IDS.split(",");
		for(int i=0;i<REBATE_CHANGE_REQ_ID_ARR.length;i++){
			String stats = "审核通过";
			String REBATE_CHANGE_REQ_ID = REBATE_CHANGE_REQ_ID_ARR[i];
		    REBATE_CHANGE_REQ_ID = REBATE_CHANGE_REQ_ID.replaceAll("'", "");
		    updateRabateChangeStats(request,REBATE_CHANGE_REQ_ID,stats);
		         
			request.setAttribute("currentState",stats);
			request.setAttribute("id",REBATE_CHANGE_REQ_ID);
			FlowInterface flowInterface = (FlowInterface) Class.forName(AUD_FLOW_INS).newInstance();
			flowInterface.afterAuditing(request, null, null);	
		}
	}
	
	/* 弃审返利订单
	 * (non-Javadoc)
	 * @see com.hoperun.erp.sale.erprebatechangereq.service.ErprebatechangereqService#txBatchCommit(java.lang.String)
	 */
	@Override
	public void txCancelAudit(HttpServletRequest request,String REBATE_CHANGE_REQ_IDS)throws Exception {
		isExistNoRls(REBATE_CHANGE_REQ_IDS);
		String [] REBATE_CHANGE_REQ_ID_ARR = REBATE_CHANGE_REQ_IDS.split(",");
		for(int i=0;i<REBATE_CHANGE_REQ_ID_ARR.length;i++){
			String stats = "提交";
			String REBATE_CHANGE_REQ_ID = REBATE_CHANGE_REQ_ID_ARR[i];
		    REBATE_CHANGE_REQ_ID = REBATE_CHANGE_REQ_ID.replaceAll("'", "");
		    updateRabateChange(request,REBATE_CHANGE_REQ_ID,stats);
		    LogicUtil.cancelRebateChange(REBATE_CHANGE_REQ_ID,-1);     	
		}
	}

	
	/* 批量审核返利订单
	 * (non-Javadoc)
	 * @see com.hoperun.erp.sale.erprebatechangereq.service.ErprebatechangereqService#txBatchCommit(java.lang.String)
	 */
	@Override
	public void txBatchAudit(HttpServletRequest request,String AUD_FLOW_INS,String AUD_BUSS_TYPE,String REBATE_CHANGE_REQ_IDS)throws Exception {
		isExistRls(REBATE_CHANGE_REQ_IDS);
		String [] REBATE_CHANGE_REQ_ID_ARR = REBATE_CHANGE_REQ_IDS.split(",");
		for(int i=0;i<REBATE_CHANGE_REQ_ID_ARR.length;i++){
			String stats = "审核通过";
			String REBATE_CHANGE_REQ_ID = REBATE_CHANGE_REQ_ID_ARR[i];
		    REBATE_CHANGE_REQ_ID = REBATE_CHANGE_REQ_ID.replaceAll("'", "");
		    updateRabateChangeStats(request,REBATE_CHANGE_REQ_ID,stats);
		         
			request.setAttribute("currentState",stats);
			request.setAttribute("id",REBATE_CHANGE_REQ_ID);
			FlowInterface flowInterface = (FlowInterface) Class.forName(AUD_FLOW_INS).newInstance();
			flowInterface.afterAuditing(request, null, null);	
		}
	}
}
