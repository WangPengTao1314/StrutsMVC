/**
 * prjName:喜临门营销平台
 * ucName:返修单
 * fileName:RepairstoreoutServiceImpl
*/
package com.hoperun.drp.sale.repairstoreout.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.drp.sale.repairapp.model.RepairappModel;
import com.hoperun.drp.sale.repairapp.model.RepairappModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.sale.repairstoreout.service.RepairStoreoutService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-11-03 16:25:12
 */
public class RepairStoreoutServiceImpl extends BaseService implements RepairStoreoutService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Repairstoreout.pageQuery", "Repairstoreout.pageCount",params, pageNo);
	}
	
	
	
	
	/**
	 * @详细
	 * @param param ERP_REPAIR_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Repairstoreout.loadById", param);
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
        return this.findList("Repairstoreout.queryChldByFkId", params);
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
       return findList("Repairstoreout.loadChldByIds",params);
    }
	
    
    public void txSubmitById(Map<String, String> params,UserBean userBean) throws Exception{
    	LogicUtil.genStoreOutOrder(params.get("ERP_REPAIR_ORDER_ID"),"FXCK", userBean,null,null);//调用接口生成出库单
    	update("Repairstoreout.updateStateById", params);
    }
    
    /**
	 * 关闭
	 * @param ERP_REPAIR_ORDER_ID
	 * @param userBean
	 */
	public void txAuditClose(String ERP_REPAIR_ORDER_ID,UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
		params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
		params.put("UPDATOR", userBean.getYHBH());
		params.put("UPD_NAME", userBean.getXM());
	    params.put("STATE", BusinessConsts.COMMON_COLSE);
	    update("Repairstoreout.updateStateById", params);
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