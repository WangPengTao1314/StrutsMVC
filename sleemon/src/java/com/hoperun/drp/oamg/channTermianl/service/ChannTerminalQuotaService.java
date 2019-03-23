package com.hoperun.drp.oamg.channTermianl.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.channTermianl.model.ChannTerminalQuotaModel;
import com.hoperun.sys.model.UserBean;

public interface ChannTerminalQuotaService  extends IBaseService {
    
	 /**
	  * @param TERMINAL_QUOTA_ID
	  * @return
	  */
	 public Map <String, String> loadByConfIdT(String TERMINAL_QUOTA_ID);
	 
	 /**
	  * @param TERMINAL_QUOTA_ID
	  * @param obj
	  * @param userBean
	  */
	 public void txEdit(String TERMINAL_QUOTA_ID, ChannTerminalQuotaModel obj, UserBean userBean);
	 
	 /**
	  * @param TERMINAL_QUOTA_ID
	  * @param userBean
	  * @return
	  */
	 public boolean txDelete(String TERMINAL_QUOTA_ID, UserBean userBean);
	 
	 /**
	  * @param params
	  * @param pageNo
	  * @return
	  */
	 public IListPage pageQuery(Map <String, String> params, int pageNo);
}
