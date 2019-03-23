/**
 * prjName:喜临门营销平台
 * ucName:返修单
 * fileName:RepairstoreoutServiceImpl
*/
package com.hoperun.drp.sale.repairsend.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.repairapp.model.RepairappModel;
import com.hoperun.drp.sale.repairapp.model.RepairappModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.sale.repairsend.service.RepairsendService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-11-03 16:25:12
 */
public class RepairsendServiceImpl extends BaseService implements RepairsendService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Repairsend.pageQuery", "Repairsend.pageCount",params, pageNo);
	}
	
	/**
	 * @详细
	 * @param param ERP_REPAIR_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Repairsend.loadById", param);
	}
	
	/**
     * * 根据主表Id查询子表记录
     * @param ERP_REPAIR_ORDER_ID the ERP_REPAIR_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <RepairappModelChld> childQuery(String ERP_REPAIR_ORDER_ID) {
        Map params = new HashMap();
        params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Repairsend.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param REPAIR_ORDER_DTL_IDs the REPAIR_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <RepairappModelChld> loadChilds(Map <String, String> params) {
       return findList("Repairsend.loadChldByIds",params);
    }
	
    
    
    public void txSubmitById(Map<String, String> params,UserBean userBean) throws Exception{
    	LogicUtil.instRepairSend(params,userBean);//调用接口生成发运单
    	//add by zhuxw 2014-07-17 发货发运之后需要提示对应的订单员
    	params.put("SENDID", userBean.getXTYHID());
    	params.put("SENDER", userBean.getXM());
    	update("Repairsend.insSendMsgToDlvPan", params);
    	update("Repairsend.updateStateById", params);
    }
    
    
    /**
     * 查询总体积 总数量 总金额
     * @param ERP_REPAIR_ORDER_ID
     * @return
     */
    public Map<String,Object> queryTotal(String ERP_REPAIR_ORDER_ID){
    	 Map<String,String> params = new HashMap<String,String>();
    	 params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
    	return (Map<String, Object>) this.load("Repairapp.queryTotal", params);
    }
    
    
    
    
}