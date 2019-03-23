package com.hoperun.drp.sale.advccheckprice.service.impl;
/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvcorderServiceImpl
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.SpringContextUtil;
import com.hoperun.commons.util.impl.LogicUtilServiceImpl;
import com.hoperun.drp.sale.advccheckprice.model.AdvccheckpriceModelChld;
import com.hoperun.drp.sale.advccheckprice.model.AdvccheckpriceModelGchld;
import com.hoperun.drp.sale.advccheckprice.service.AdvccheckpriceService;
import com.hoperun.drp.sale.advcorder.service.impl.AdvcorderServiceImpl;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:17:29
 */
public class AdvccheckpriceServiceImpl extends BaseService implements AdvccheckpriceService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo) {
		return this.pageQuery("Advccheckprice.pageQuery", "Advccheckprice.pageCount",params, pageNo);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public MsgInfo txUpdateById(Map<String,String> params,UserBean userBean)throws Exception {
		String advcOrderId = params.get("ADVC_ORDER_ID");
		String STATE = params.get("STATE");
		MsgInfo msgObj = null;
		if(!BusinessConsts.RETURN_BACK.equals(STATE)){
			msgObj = LogicUtil.checkStoreAcct(advcOrderId,BusinessConsts.STORE_DESC,BusinessConsts.ADVC_ORDER_CONF_ID);
			if(!msgObj.isFLAG()){
				if(!"0".equals(msgObj.getMESS())){ //要做库存CHECK的，返回信息
	    			return msgObj;
	    		}else{   //不用做库存CHECK ，直接返回true
	    			msgObj.setFLAG(true);
	    		}
			}
		}else{
			msgObj = new MsgInfo();
			msgObj.setFLAG(true);
		}
		
		
		update("Advccheckprice.updateById", params);
		
		
		//add by zhuxw 生成收款单
		if(BusinessConsts.COMMIT.equals(STATE))
		{
			AdvcorderServiceImpl advcorderService = (AdvcorderServiceImpl) SpringContextUtil.getBean("advcorderService");
			advcorderService.insStateMents(advcOrderId, userBean);	
		}
		
		return msgObj;
	}
	
	
	/**
	 * @详细
	 * @param param ADVC_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Advccheckprice.loadById", param);
	}
	
	
	/**
     * * 根据主表Id查询子表记录
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
    public List <AdvccheckpriceModelChld> childQuery(String ADVC_ORDER_ID) {
        Map params = new HashMap();
        params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
        return this.findList("Advccheckprice.queryChldByFkId", params);
    }
    /**
     * * 根据主表Id查询子表记录
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
    public List <AdvccheckpriceModelGchld> gchildQuery(String ADVC_ORDER_ID) {
        Map params = new HashMap();
        params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Advccheckprice.queryGchldByFkId", params);
    }
    public boolean upStateById(Map<String,String> map){
		return this.update("Advcorder.commitById", map);
	}
}