/**
 * prjName:喜临门营销平台
 * ucName:退货单维护
 * fileName:PrdreturnServiceImpl
*/
package com.hoperun.erp.sale.prdreturnfin.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.erp.sale.prdreturnfin.model.PrdreturnfinModelChld;
import com.hoperun.erp.sale.prdreturnfin.service.PrdreturnfinService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-19 15:33:31
 */
public class PrdreturnfinServiceImpl extends BaseService implements PrdreturnfinService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Prdreturnfin.pageQuery", "Prdreturnfin.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Prdreturnfin.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param PRD_RETURN_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Prdreturnfin.delete", params);
		 //删除子表 
		 return update("Prdreturnfin.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Prdreturnfin.updateById", params);
	}
	
	/**
	 * @详细
	 * @param param PRD_RETURN_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Prdreturnfin.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param PRD_RETURN_ID the PRD_RETURN_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(List<PrdreturnfinModelChld> chldList) {
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (PrdreturnfinModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("PRD_RETURN_DTL_ID",model.getPRD_RETURN_DTL_ID());
		    params.put("RETURN_AMOUNT",model.getRETURN_AMOUNT());		    
            updateList.add(params);
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Prdreturnfin.updateChldById", updateList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param PRD_RETURN_ID the PRD_RETURN_ID
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
    public List <PrdreturnfinModelChld> childQuery(String PRD_RETURN_ID) {
        Map params = new HashMap();
        params.put("PRD_RETURN_ID", PRD_RETURN_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Prdreturnfin.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
    public List <PrdreturnfinModelChld> loadChilds(Map <String, String> params) {
       return findList("Prdreturnfin.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs
     */
    @SuppressWarnings("unchecked")
    public void txBatch4DeleteChild(String PRD_RETURN_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("PRD_RETURN_DTL_IDS", PRD_RETURN_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Prdreturnfin.deleteChldByIds", params);
    }
    
    

    /* (non-Javadoc)
     * @see com.hoperun.erp.sale.prdreturnfin.service.PrdreturnfinService#txCommit(java.util.Map)
     */
    public String txCommit(Map<String,String> map)throws Exception{
    	String strMessage = "操作成功";
    	String pdtReturnId = (String)map.get("PRD_RETURN_ID");
    	String strJsonData = (String)map.get("JSON_DATA");
    	map.put("STATE", "已提交工厂");
    	update("Prdreturnfin.updateReturnById", map);
    	LogicUtil.updCanUseCredit(BusinessConsts.ACCT_PDTRETURN_CONF_ID, pdtReturnId,null,false);
    	LogicUtil.inertCreditJournal(BusinessConsts.ACCT_PDTRETURN_CONF_ID, pdtReturnId);
    	String strResult = "";
    	if("true".equals(Consts.INVOKE_SYS_FLG)){
    		try{
    			strResult = LogicUtil.createRMA(strJsonData);  
    		}catch(Exception ex){
    			throw new ServiceException("调用ESB接口失败!");
    		}
    		Map<String, String> resMap = new Gson().fromJson(strResult, new TypeToken<Map<String, String>>() {}.getType());
     		if(resMap!=null){
     			String stats = resMap.get("IsOK");
     			if("false".equals(stats)){
     				StringBuffer buf = new StringBuffer();
     				buf.append("操作失败!");
     				String Msg = resMap.get("Msg");
     				buf.append("错误信息:"+Msg);
     				throw new ServiceException(buf.toString());
     			}
     		}else{
     			strMessage = "调用接口失败,查看LOG日志";
     			throw new ServiceException(strMessage);
     		}
    	}

    	//更新返利待完成
//    	Map pdtReturnMap = load(pdtReturnId);
//    	String fromBillId = (String)pdtReturnMap.get("FROM_BILL_ID"); 
//    	if(fromBillId!=null && fromBillId.trim().length()>0){
//    		
//    	}
    	return strMessage;
    }
    
    /**
     * 退回
     * @param PRD_RETURN_ID
     * @param userBean
     */
    public void txReback(String PRD_RETURN_ID,UserBean userBean,String REMARK){
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("PRD_RETURN_ID", PRD_RETURN_ID);
    	params.put("STATE", "退回");
    	params.put("REMARK", REMARK);
    	params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
    	params.put("UPD_NAME", userBean.getXM());
    	params.put("UPDATOR", userBean.getXTYHID());
    	update("Prdreturnfin.updateById",params);
    	
    }
}