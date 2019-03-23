package com.hoperun.drp.base.retalprice.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.base.retalprice.model.RetalpriceModel;
import com.hoperun.drp.base.retalprice.service.RetalpriceService;
import com.hoperun.sys.model.UserBean;

public class RetalpriceServiceImpl  extends BaseService implements RetalpriceService {
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
		return this.pageQuery("Retalprice.pageQuery", "Retalprice.pageCount", params, pageNo);
	}
	public Map<String, String> load(String param) {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
        params.put("RETAL_PRICE_ID", param);
        return (Map<String, String>) load("Retalprice.loadById", params);
	}
	public Map<String,String> loadPRO(String PRD_ID){
		return (Map<String, String>) this.load("Retalprice.loadPRO",PRD_ID);
	}
	/**
     * 按货品id和渠道id查询该货品是否已存在
     * @param PRD_ID
     * @return
     */
    public List<String> getCountPRD_ID(String LEDGER_ID){
    	return this.findList("Retalprice.getCountPRD_ID",LEDGER_ID);
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
	public void txEdit(List<RetalpriceModel> modelList, UserBean userBean){
		List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
		List<Map<String,String>> upList=new ArrayList<Map<String,String>>();
		for (RetalpriceModel model : modelList) {
			Map<String, String> params = new HashMap<String, String>();
			String RETAL_PRICE_ID=model.getRETAL_PRICE_ID();
			if(StringUtil.isEmpty(RETAL_PRICE_ID)){
				params.put("RETAL_PRICE_ID", StringUtil.uuid32len());
				params.put("PRD_ID", model.getPRD_ID());
				params.put("FACT_PRICE", model.getFACT_PRICE());
				params.put("CREATOR", userBean.getRYXXID());
				params.put("CRE_NAME", userBean.getXM());
				params.put("CRE_TIME", "sysdate");
				params.put("LEDGER_ID", userBean.getLoginZTXXID());
				params.put("LEDGER_NAME", userBean.getLoginZTMC());
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				addList.add(params);
			}else{
				params.put("UPDATOR", userBean.getRYXXID());
				params.put("RETAL_PRICE_ID", RETAL_PRICE_ID);
				params.put("UPD_NAME", userBean.getXM());
				params.put("UPD_TIME", "sysdate");
				params.put("FACT_PRICE", model.getFACT_PRICE());
				upList.add(params);
			}
		}
		if (!upList.isEmpty()) {
			this.batch4Update("Retalprice.updateById", upList);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Retalprice.insert", addList);
		}
//		List<String> list=this.getCountPRD_ID(userBean.getLoginZTXXID());
//		if(!list.isEmpty()){
//			StringBuffer sub=new StringBuffer();
//			sub.append("货品编号：");
//			for (String str : list) {
//				sub.append(str).append("  ");
//			}
//			sub.append("重复,请检查后重新保存");
//		}
	}
	public long getCount(Map<String,String> map){
		Object obj=this.load("Retalprice.pageCount", map);
		return Long.parseLong(obj.toString());
	}
    
}
