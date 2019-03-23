package com.hoperun.drp.oamg.channTermianl.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.channTermianl.model.ChannTerminalQuotaModel;
import com.hoperun.drp.oamg.channTermianl.service.ChannTerminalQuotaService;
import com.hoperun.sys.model.UserBean;

public class ChannTerminalQuotaServiceImpl  extends BaseService implements ChannTerminalQuotaService{

	 /**
	  * @param TERMINAL_QUOTA_ID
	  * @return
	  */
	 public Map <String, String> loadByConfIdT(String TERMINAL_QUOTA_ID){
		 return (Map<String, String>) load("ChannTerminal.loadByConfIdT",TERMINAL_QUOTA_ID);
	 }
	 
	 /**
	  * @param TERMINAL_QUOTA_ID
	  * @param obj
	  * @param userBean
	  */
	 public void txEdit(String TERMINAL_QUOTA_ID, ChannTerminalQuotaModel obj, UserBean userBean){
		   
		    Map<String, String> params = new HashMap<String, String>();

			params.put("WAREA_ID",   obj.getWAREA_ID());   //战区ID
			params.put("WAREA_NO",   obj.getWAREA_NO());   //战区编号
			params.put("WAREA_NAME", obj.getWAREA_NAME()); //战区名称
			params.put("AREA_ID",    obj.getAREA_ID());    //区域ID
			params.put("AREA_NO",    obj.getAREA_NO());    //区域编号
			params.put("AREA_NAME",  obj.getAREA_NAME());  //区域名称
			params.put("YEAR",       obj.getYEAR());      //年份
			params.put("MONTH",      obj.getMONTH());     //月份
			params.put("QUOTA_NUM",  obj.getQUOTA_NUM()); //次数
			params.put("REMARK",     obj.getREMARK());    //备注

			if (StringUtils.isEmpty(TERMINAL_QUOTA_ID)) {
				params.put("DEL_FLAG", "0");
				params.put("LEDGER_ID", userBean.getLoginZTXXID()); //帐套ID
				params.put("LEDGER_NAME", userBean.getLoginZTMC()); //帐套名称
				params.put("CREATOR", userBean.getXTYHID());        //制单人ID
				params.put("CRE_NAME", userBean.getXM());           //制单人名称
				params.put("CRE_TIME", DateUtil.now());             //制单时间
				params.put("DEPT_ID", userBean.getBMXXID());        //制单部门ID
				params.put("DEPT_NAME", userBean.getBMMC());        //制单部门名称
				params.put("ORG_ID", userBean.getJGXXID());         //制单机构id
				params.put("ORG_NAME", userBean.getJGMC());         //制单机构名称
				params.put("STATE", "提交");
				String UID = StringUtil.uuid32len();
				params.put("TERMINAL_QUOTA_ID", UID);
				String TERMINAL_QUOTA_NO =LogicUtil.getBmgz("ERP_CHANN_TERMINAL_QUOTA_NO");	  //装修申请单号
				params.put("TERMINAL_QUOTA_NO", TERMINAL_QUOTA_NO);
				this.insert("ChannTerminal.insert", params);
				
			} else {
				params.put("UPDATOR", userBean.getXTYHID());
	    		params.put("UPD_TIME", DateUtil.now());
	    		params.put("TERMINAL_QUOTA_ID", TERMINAL_QUOTA_ID);
				this.update("ChannTerminal.updateById", params);
			}
	 }
	 
	 
	 
	 /**
	  * @param TERMINAL_QUOTA_ID
	  * @param userBean
	  * @return
	  */
	 public boolean txDelete(String TERMINAL_QUOTA_ID, UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("TERMINAL_QUOTA_ID", TERMINAL_QUOTA_ID);
		return update("ChannTerminal.delete", params);
	 }
	 
	 /**
	  * @param params
	  * @param pageNo
	  * @return
	  */
	 public IListPage pageQuery(Map <String, String> params, int pageNo){
		 return this.pageQuery("ChannTerminal.pageQuery",
					"ChannTerminal.pageCount", params, pageNo);
	 }
}
