package com.hoperun.drp.base.drppromoteprice.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.base.drppromoteprice.model.DrppromotepriceModel;
import com.hoperun.drp.base.drppromoteprice.service.DrppromotepriceService;
import com.hoperun.sys.model.UserBean;

public class DrppromotepriceServiceImpl  extends BaseService implements DrppromotepriceService {
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
		return this.pageQuery("Drppromoteprice.pageQuery", "Drppromoteprice.pageCount", params, pageNo);
	}
    /**
	 * @编辑
	 * @Description :
	 * @param ADVC_ORDER_ID
	 * @param AdvctoorderModel
	 * @param userBean
	 *            .
	 * 
	 * 
	 * @return true, if tx txEdit
	 * @throws ParseException 
	 */
	public void txEdit(List<DrppromotepriceModel> modelList, UserBean userBean,String PROMOTE_ID){
		List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
		List<Map<String,String>> upList=new ArrayList<Map<String,String>>();
		for (DrppromotepriceModel model : modelList) {
			Map<String, String> params = new HashMap<String, String>();
			String PROMOTE_PRICE_ID=model.getPROMOTE_PRICE_ID();
			if(StringUtil.isEmpty(PROMOTE_PRICE_ID)){
				params.put("PROMOTE_PRICE_ID", StringUtil.uuid32len());
				params.put("PROMOTE_ID", PROMOTE_ID);
				params.put("PRD_ID", model.getPRD_ID());
				params.put("DECT_PRICE", model.getDECT_PRICE());
				params.put("DECT_RATE", model.getDECT_RATE());
				params.put("PRICE", model.getPRICE());
				params.put("UPDATOR", userBean.getRYXXID());
				params.put("UPD_NAME", userBean.getXM());
				params.put("UPD_TIME", "sysdate");
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				addList.add(params);
			}else{
				params.put("PROMOTE_PRICE_ID", PROMOTE_PRICE_ID);
				params.put("DECT_PRICE", model.getDECT_PRICE());
				params.put("DECT_RATE", model.getDECT_RATE());
				params.put("PRICE", model.getPRICE());
				params.put("UPDATOR", userBean.getRYXXID());
				params.put("UPD_NAME", userBean.getXM());
				params.put("UPD_TIME", "sysdate");
				upList.add(params);
			}
		}
		if (!upList.isEmpty()) {
			this.batch4Update("Drppromoteprice.updateById", upList);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Drppromoteprice.insert", addList);
		}
	}
    
}
