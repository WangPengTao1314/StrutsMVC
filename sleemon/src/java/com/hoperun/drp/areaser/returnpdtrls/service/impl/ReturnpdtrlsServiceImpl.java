/**
 * prjName:喜临门营销平台
 * ucName:退货申请单维护
 * fileName:PrdreturnServiceImpl
*/
package com.hoperun.drp.areaser.returnpdtrls.service.impl;
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
import com.hoperun.drp.areaser.returnpdtrls.service.ReturnpdtrlsService;
import com.hoperun.drp.sale.prdreturnreq.model.PrdreturnreqModel;
import com.hoperun.drp.sale.prdreturnreq.model.PrdreturnreqModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-15 10:17:13
 */
public class ReturnpdtrlsServiceImpl extends BaseService implements ReturnpdtrlsService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Returnpdtrls.pageQuery", "Returnpdtrls.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Returnpdtrls.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param PRD_RETURN_REQ_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Returnpdtrls.delete", params);
		 //删除子表 
		 return update("Returnpdtrls.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Returnpdtrls.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param PRD_RETURN_REQ_ID
	 * @param PrdreturnreqreqModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String PRD_RETURN_REQ_ID, PrdreturnreqModel model,List<PrdreturnreqModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		
		    params.put("BILL_TYPE",model.getBILL_TYPE());
		    params.put("RETURN_CHANN_ID",model.getRETURN_CHANN_ID());
		    params.put("RETURN_CHANN_NO",model.getRETURN_CHANN_NO());
		    params.put("RETURN_CHANN_NAME",model.getRETURN_CHANN_NAME());
		    params.put("RECV_CHANN_ID",model.getRECV_CHANN_ID());
		    params.put("RECV_CHANN_NO",model.getRECV_CHANN_NO());
		    params.put("RECV_CHANN_NAME",model.getRECV_CHANN_NAME());
		    params.put("RETURN_STORE_ID",model.getRETURN_STORE_ID());
		    params.put("RETURN_STORE_NO",model.getRETURN_STORE_NO());
		    params.put("RETURN_STORE_NAME",model.getRETURN_STORE_NAME());
		    params.put("REMARK",model.getREMARK());
		     
		    params.put("UPDATOR", userBean.getXTYHID());//更新人ID
	        params.put("UPD_NAME", userBean.getXM());//更新人名称
  
		if(StringUtil.isEmpty(PRD_RETURN_REQ_ID)){
			PRD_RETURN_REQ_ID= StringUtil.uuid32len();
			String id = LogicUtil.getBmgz("DRP_PRD_RETURN_REQ_NO");
			
			params.put("PRD_RETURN_REQ_ID", PRD_RETURN_REQ_ID);
			params.put("PRD_RETURN_REQ_NO",id);
			
			params.put("STATE", BusinessConsts.UNCOMMIT);//状态
			
			params.put("CREATOR", userBean.getXTYHID());//制单人ID
            params.put("CRE_NAME", userBean.getXM());//制单人名称
            params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());//制单机构ID
            params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
            params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套ID
            params.put("LEDGER_NAME", userBean.getLoginZTMC());//帐套名称
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//删除状态
			      
			txInsert(params);
		} else{
			
			params.put("PRD_RETURN_REQ_ID", PRD_RETURN_REQ_ID);
		    params.put("PRD_RETURN_REQ_NO",model.getPRD_RETURN_REQ_NO());
		    
		    
			txUpdateById(params);
			clearCaches(PRD_RETURN_REQ_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(PRD_RETURN_REQ_ID, chldList);
		}
	}
	
	/**
	 * @详细
	 * @param param PRD_RETURN_REQ_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Returnpdtrls.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param PRD_RETURN_REQ_ID the PRD_RETURN_REQ_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String PRD_RETURN_REQ_ID, List<PrdreturnreqModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (PrdreturnreqModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("FROM_BILL_ID",model.getFROM_BILL_ID());
		    params.put("FROM_BILL_NO",model.getFROM_BILL_NO());
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("RETURN_PRICE",model.getRETURN_PRICE());
		    Object RETURN_NUM=model.getRETURN_NUM();
		    if(Integer.parseInt(RETURN_NUM.toString())==0){
		    	throw new ServiceException("退货数量不能为0 !");
		    }
		    params.put("RETURN_NUM",RETURN_NUM.toString());
		    params.put("RETURN_AMOUNT",model.getRETURN_AMOUNT());
		    params.put("RETURN_RSON_TYPE",model.getRETURN_RSON_TYPE());
		    params.put("RETURN_RSON",model.getRETURN_RSON());
		    String SPCL_TECH_ID=model.getSPCL_TECH_ID();
		    if("0".equals(SPCL_TECH_ID)){
		    	SPCL_TECH_ID="";
		    }
		    params.put("SPCL_TECH_ID", SPCL_TECH_ID);
		    //附件上传 不能查看附件问题 是文件路径有问题
		    String RETURN_ATT = model.getRETURN_ATT();
		    String name = "";
		    int i = RETURN_ATT.indexOf("/");
		    int length = RETURN_ATT.length();
		    if(-1 == i && length>10){
		    	name = RETURN_ATT.substring(0,4)+"/"+RETURN_ATT.substring(4,9)+"/"
		    	+RETURN_ATT.substring(9,length);
		    	
		    	 params.put("RETURN_ATT",name);
		    }else{
		    	  params.put("RETURN_ATT",RETURN_ATT);
		    }
		    
            params.put("PRD_RETURN_REQ_ID",PRD_RETURN_REQ_ID); 
            params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
            
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getPRD_RETURN_DTL_REQ_ID())) {
                params.put("PRD_RETURN_DTL_REQ_ID", StringUtil.uuid32len());
                addList.add(params);
            } else {
                params.put("PRD_RETURN_DTL_REQ_ID", model.getPRD_RETURN_DTL_REQ_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Returnpdtrls.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Returnpdtrls.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param PRD_RETURN_REQ_ID the PRD_RETURN_REQ_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <PrdreturnreqModelChld> childQuery(String PRD_RETURN_REQ_ID) {
        Map params = new HashMap();
        params.put("PRD_RETURN_REQ_ID", PRD_RETURN_REQ_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Returnpdtrls.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param PRD_RETURN_DTL_REQ_IDs the PRD_RETURN_DTL_REQ_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <PrdreturnreqModelChld> loadChilds(Map <String, String> params) {
       return findList("Returnpdtrls.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param PRD_RETURN_DTL_REQ_IDs the PRD_RETURN_DTL_REQ_IDs
     */
    @Override
    public void txBatch4DeleteChild(String PRD_RETURN_DTL_REQ_IDs) {
	   Map<String,String> params = new HashMap<String,String>();
	   params.put("PRD_RETURN_DTL_REQ_IDS", PRD_RETURN_DTL_REQ_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Returnpdtrls.deleteChldByIds", params);
    }
    
    /**
     * 审核
     * @param PRD_RETURN_REQ_ID
     * @param userBean
     */
    public void txAudit(String PRD_RETURN_REQ_ID,UserBean userBean){
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("PRD_RETURN_REQ_ID",PRD_RETURN_REQ_ID);
    	params.put("LEDGER_ID",userBean.getBASE_CHANN_ID());
    	params.put("LEDGER_NAME",userBean.getBASE_CHANN_NAME());
    	params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_NAME", userBean.getXM());
		params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
    	//更新为总部帐套
    	txUpdateById(params);
    	 
    }
}