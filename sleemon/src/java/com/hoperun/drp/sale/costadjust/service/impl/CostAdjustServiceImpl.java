/**
 * prjName:喜临门营销平台
 * ucName:终端退货录入
 * fileName:TermreturnServiceImpl
*/
package com.hoperun.drp.sale.costadjust.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.costadjust.model.CostAdjustModel;
import com.hoperun.drp.sale.costadjust.model.CostAdjustModelChld;
import com.hoperun.drp.sale.costadjust.service.CostAdjustService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public class CostAdjustServiceImpl extends BaseService implements CostAdjustService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo) {
		return this.pageQuery("CostAdjust.pageQuery", "CostAdjust.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("CostAdjust.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param ADVC_ORDER_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("CostAdjust.delete", params);
		 //删除子表 
		 return update("CostAdjust.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("CostAdjust.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param ADVC_ORDER_ID
	 * @param EmployeeModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String COST_ADJUST_ID, CostAdjustModel model,List<CostAdjustModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("MONTH",model.getMONTH());
		    params.put("YEAR",model.getYEAR());
		    params.put("REMARK",model.getREMARK());
		    params.put("STORE_ID", model.getSTORE_ID());
		    params.put("STORE_NO", model.getSTORE_NO());
		    params.put("STORE_NAME", model.getSTORE_NAME());
		    String data=model.getYEAR()+"-"+model.getMONTH();
		    boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),data);
			if(!isMonthAcc){
				throw new ServiceException(model.getYEAR()+"年"+model.getMONTH()+"月没有月结，不能保存");
			}
		if(StringUtil.isEmpty(COST_ADJUST_ID)){
			COST_ADJUST_ID= StringUtil.uuid32len();
			params.put("COST_ADJUST_ID", COST_ADJUST_ID);
			params.put("COST_ADJUST_NO",LogicUtil.getBmgz("DRP_COST_ADJUST_NO"));
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CREATOR",userBean.getXTYHID());
		    params.put("DEPT_ID",userBean.getBMXXID());
		    params.put("DEPT_NAME",userBean.getBMMC());
		    params.put("ORG_ID",userBean.getJGXXID());
		    params.put("ORG_NAME",userBean.getJGMC());
		    params.put("LEDGER_ID",userBean.getLoginZTXXID());
		    params.put("LEDGER_NAME",userBean.getLoginZTMC());
			params.put("STATE",BusinessConsts.UNCOMMIT);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("COST_ADJUST_ID", COST_ADJUST_ID);
			txUpdateById(params);
			clearCaches(COST_ADJUST_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(COST_ADJUST_ID, chldList,"edit");
		}else{
			this.delete("CostAdjust.delChld", COST_ADJUST_ID);
		}
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
		return (Map<String,String>) load("CostAdjust.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public void txChildEdit(String COST_ADJUST_ID, List<CostAdjustModelChld> chldList,String actionType) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (CostAdjustModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            String COST_ADJUST_DTL_ID=model.getCOST_ADJUST_DTL_ID();
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
		    double ADJUST_AMOUNT=Double.parseDouble(model.getNEW_COST_PRICE())-Double.parseDouble(model.getCUR_COST_PRICE());
		    params.put("ADJUST_AMOUNT",ADJUST_AMOUNT+"");
            params.put("COST_ADJUST_ID",COST_ADJUST_ID);
            params.put("SPCL_TECH_ID",model.getSPCL_TECH_ID());
            params.put("REMARK", model.getREMARK());
            params.put("CUR_COST_PRICE", model.getCUR_COST_PRICE());
            params.put("NEW_COST_PRICE", model.getNEW_COST_PRICE());
          //action类型，如果是在住子表编辑页面过来 则把明细全部删掉，重新新增，如果是明细编辑页面，则新增或修改明细
        	if("list".equals(actionType)){
            //如果没有明细ID的则是新增，有的是修改
        		 //如果没有明细ID的则是新增，有的是修改
                if (StringUtil.isEmpty(COST_ADJUST_DTL_ID)) {
                    params.put("COST_ADJUST_DTL_ID", StringUtil.uuid32len());
    		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                    addList.add(params);
                } else {
                    params.put("COST_ADJUST_DTL_ID", COST_ADJUST_DTL_ID);
                    updateList.add(params);
                }
        	}else{
        		 params.put("COST_ADJUST_DTL_ID", StringUtil.uuid32len());
 		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                 addList.add(params);
        	}
        }
        if(!"list".equals(actionType)){
        	this.delete("CostAdjust.delChld", COST_ADJUST_ID);
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("CostAdjust.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("CostAdjust.insertChld", addList);
        }
        boolean dtlFlag=checkRepeatPrdDtl(COST_ADJUST_ID);
        if(!dtlFlag){
        	throw new ServiceException("单据中包含重复货品,不能保存");
        }
        boolean flag=checkRepeatPrd(COST_ADJUST_ID);
        if(!flag){
        	throw new ServiceException("单据中包含成本单已添加未审核的货品,不能保存");
        }
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <CostAdjustModelChld> childQuery(String COST_ADJUST_ID) {
        Map params = new HashMap();
        params.put("COST_ADJUST_ID", COST_ADJUST_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("CostAdjust.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <CostAdjustModelChld> loadChilds(Map <String, String> params) {
       return findList("CostAdjust.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String COST_ADJUST_DTL_IDS,String COST_ADJUST_ID) {
	   Map<String,String> params = new HashMap<String,String>();
	   params.put("COST_ADJUST_DTL_IDS", COST_ADJUST_DTL_IDS);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("CostAdjust.deleteChldByIds", params);
    }
    //是不是已经有未审核通过的调整单，调整一样的货品
    public boolean checkRepeatPrd(String COST_ADJUST_ID){
    	Integer count =StringUtil.getInteger(this.load("CostAdjust.checkRepeatPrd", COST_ADJUST_ID));
    	if(count>0){
    		return false;
    	}
    	return true;
    }
    //验证同一个调整单是否存在重复货品
    public boolean checkRepeatPrdDtl(String COST_ADJUST_ID){
    	Integer count =StringUtil.getInteger(this.load("CostAdjust.checkRepeatPrdDtl", COST_ADJUST_ID));
    	if(count>0){
    		return false;
    	}
    	return true;
    }
}