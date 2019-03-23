package com.hoperun.sys.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.NumformatModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.NumformatService;

// TODO: Auto-generated Javadoc
/**
 * The Class NumformatServiceImpl.
 */
public class NumformatServiceImpl extends BaseService  implements NumformatService {

	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.NumformatService#pageQuery(java.util.Map, int)
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo){
		return this.pageQuery("Numformat.pageQuery","Numformat.pageCount",params, pageNo);
	}
	
	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.NumformatService#load(java.lang.String)
	 */
	public Map<String,String> load(String numid){
		return (Map<String,String>)this.load("Numformat.getOneRecord",numid);
	}
	
	/**
	 * 编辑.
	 * 
	 * @param NUMFORMATID the nUMFORMATID
	 * @param model the model
	 * @param userBean the user bean
	 */
	public void txEdit(String NUMFORMATID,NumformatModel model, UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
		params.put("NUMFORMATMC", model.getNUMFORMATMC());
		params.put("NUMTYPE", model.getNUMTYPE());
		params.put("DECIMALS", model.getDECIMALS());
		params.put("ROUNDTYPE", model.getROUNDTYPE());
		params.put("CRETIME", model.getCRETIME());
		params.put("CREATER", userBean.getYHBH());
		params.put("CRENAME", userBean.getYHM());
		params.put("STATE", "制定");
		 
		if(StringUtils.isEmpty(NUMFORMATID)){
			params.put("NUMFORMATID", StringUtil.uuid32len());
			insert("Numformat.insertOneRecord",params);
		}else{
			params.put("NUMFORMATID", NUMFORMATID);
			update("Numformat.updateOneRecord",params);
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.NumformatService#chenagestate(java.util.Map)
	 */
	public void chenagestate(Map<String,String> params){
		update("Numformat.updatestate",params);
	}
	
	/* (non-Javadoc)
	 * @see com.hoperun.sys.service.NumformatService#deleteOneR(java.lang.String)
	 */
	public void deleteOneR(String NUMFORMATID){
		delete("Numformat.deleteOneRecord",NUMFORMATID);
	}
}
