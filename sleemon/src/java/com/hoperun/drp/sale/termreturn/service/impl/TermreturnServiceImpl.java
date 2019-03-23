/**
 * prjName:喜临门营销平台
 * ucName:终端退货录入
 * fileName:TermreturnServiceImpl
*/
package com.hoperun.drp.sale.termreturn.service.impl;
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
import com.hoperun.drp.sale.termreturn.model.TermreturnModel;
import com.hoperun.drp.sale.termreturn.model.TermreturnModelChld;
import com.hoperun.drp.sale.termreturn.service.TermreturnService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public class TermreturnServiceImpl extends BaseService implements TermreturnService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo) {
		return this.pageQuery("Termreturn.pageQuery", "Termreturn.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Termreturn.insert", params);
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
         update("Termreturn.delete", params);
		 //删除子表 
		 return update("Termreturn.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Termreturn.updateById", params);
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
	public void txEdit(String ADVC_ORDER_ID, TermreturnModel model,List<TermreturnModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
			if(model.getADVC_ORDER_NO().equals(BusinessConsts.ZDSC)){
				params.put("ADVC_ORDER_NO",LogicUtil.getBmgz("DRP_ADVC_ORDER_RETURN_NO"));//终端退货单编号
			}else{
				params.put("ADVC_ORDER_NO",model.getADVC_ORDER_NO());//终端退货单编号
			}
		    params.put("FROM_BILL_ID",model.getFROM_BILL_ID());
		    params.put("TERM_ID",model.getTERM_ID());
		    params.put("SALE_PSON_ID",model.getSALE_PSON_ID());
		    params.put("FROM_BILL_NO",model.getFROM_BILL_NO());
		    params.put("TERM_NO",model.getTERM_NO());
		    params.put("TERM_NAME",model.getTERM_NAME());
		    params.put("CUST_NAME",model.getCUST_NAME());
		    params.put("CONTRACT_NO",model.getCONTRACT_NO());
		    params.put("TEL",model.getTEL());
		    params.put("SALE_PSON_NAME",model.getSALE_PSON_NAME());
		    params.put("PAYABLE_AMOUNT",model.getPAYABLE_AMOUNT());
		    params.put("RECV_ADDR",model.getRECV_ADDR());
		    params.put("REMARK",model.getREMARK());
		    params.put("RETURN_STATEMENT_DATE", model.getRETURN_STATEMENT_DATE());
		    params.put("RETURN_DEDUCT_AMOUNT", model.getRETURN_DEDUCT_AMOUNT());
		if(StringUtil.isEmpty(ADVC_ORDER_ID)){
			ADVC_ORDER_ID= StringUtil.uuid32len();
			params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
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
		    params.put("BILL_TYPE", "终端退货");
		txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			txUpdateById(params);
			clearCaches(ADVC_ORDER_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(ADVC_ORDER_ID, chldList,"edit",userBean.getCHANN_TYPE());
		}else{
			this.delete("Termreturn.delChld", ADVC_ORDER_ID);
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
		return (Map<String,String>) load("Termreturn.loadById", param);
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
    public void txChildEdit(String ADVC_ORDER_ID, List<TermreturnModelChld> chldList,String actionType,String CHANN_TYPE) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (TermreturnModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            String ADVC_ORDER_DTL_ID=model.getADVC_ORDER_DTL_ID();
            String FROM_BILL_DTL_ID=model.getFROM_BILL_DTL_ID();
            Map<String,String> checkMap=new HashMap<String,String>();
            checkMap.put("PRD_ID", model.getPRD_ID());
            checkMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
            checkMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
            int count=Integer.parseInt(load("Termreturn.checkDtl",checkMap).toString());
            if(count>0&&StringUtil.isEmpty(ADVC_ORDER_DTL_ID)){
            	throw new ServiceException("所添加货品有重复项，请重新添加 !");
            }
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
		    params.put("PRD_TYPE", model.getPRD_TYPE());
		    params.put("PRICE",model.getPRICE());
		    params.put("DECT_RATE",model.getDECT_RATE());
		    params.put("DECT_PRICE",model.getDECT_PRICE());
		    params.put("ORDER_NUM",model.getORDER_NUM());
		    params.put("PAYABLE_AMOUNT",model.getPAYABLE_AMOUNT());
            params.put("ADVC_ORDER_ID",ADVC_ORDER_ID);
            params.put("RETURN_ATT",model.getRETURN_ATT());
            params.put("FROM_BILL_DTL_ID", FROM_BILL_DTL_ID);
            params.put("REMARK", model.getREMARK());
          //action类型，如果是在住子表编辑页面过来 则把明细全部删掉，重新新增，如果是明细编辑页面，则新增或修改明细
        	if("list".equals(actionType)){
            //如果没有明细ID的则是新增，有的是修改
	            if (StringUtil.isEmpty(ADVC_ORDER_DTL_ID)) {
	                params.put("ADVC_ORDER_DTL_ID", StringUtil.uuid32len());
			        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	                addList.add(params);
	            } else {
	                params.put("ADVC_ORDER_DTL_ID", ADVC_ORDER_DTL_ID);
	                updateList.add(params);
	            }
        	}else{
                params.put("ADVC_ORDER_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
        	}
        }
        if(!"list".equals(actionType)){
        	this.delete("Termreturn.delChld", ADVC_ORDER_ID);
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Termreturn.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Termreturn.insertChld", addList);
        }
        List<Map<String,String>> list=new ArrayList<Map<String,String>>();
        list.addAll(updateList);
        list.addAll(addList);
        for (int i = 0; i < list.size(); i++) {
        	Map<String,String> map=list.get(i);
        	String FROM_BILL_DTL_ID=map.get("FROM_BILL_DTL_ID");
        	Map<String,Object> checkNum;
        	if(!StringUtil.isEmpty(FROM_BILL_DTL_ID)){
        		if("直营办".equals(CHANN_TYPE)){
        			checkNum=(Map<String, Object>) load("Termreturn.checkSendNum",FROM_BILL_DTL_ID);
        		}else{
        			checkNum=(Map<String, Object>) load("Termreturn.checkStoreNum",FROM_BILL_DTL_ID);
        		}
        		double retuNum=Double.parseDouble(String.valueOf(checkNum.get("RETUNUM")));
        		double sendNum=Double.parseDouble(String.valueOf(checkNum.get("SENDNUM")));
        		if(retuNum-sendNum>0){
        			StringBuffer str=new StringBuffer();
        			str.append("货品编号").append(map.get("PRD_NO")).append("退货数量不得大于已发货数量 !已退数量："+retuNum+"已发数量："+sendNum);
        			throw new ServiceException(str.toString());
        		}
        	}
		}
        this.update("Termreturn.upPAYABLE_AMOUNT", ADVC_ORDER_ID);
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TermreturnModelChld> childQuery(String ADVC_ORDER_ID,String CHANN_TYPE) {
        Map params = new HashMap();
        params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		if("直营办".equals(CHANN_TYPE)){
			return this.findList("Termreturn.queryOldChldByFkId", params);
		}else{
			return this.findList("Termreturn.queryChldByFkId", params);
		}
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TermreturnModelChld> loadChilds(Map <String, String> params) {
    	if("直营办".equals(params.get("CHANN_TYPE"))){
    		return findList("Termreturn.loadOldChldByIds",params);
    	}else{
    		return findList("Termreturn.loadChldByIds",params);
    	}
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String ADVC_ORDER_DTL_IDs,String ADVC_ORDER_ID) {
	   Map<String,String> params = new HashMap<String,String>();
	   params.put("ADVC_ORDER_DTL_IDS", ADVC_ORDER_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Termreturn.deleteChldByIds", params);
       this.update("Termreturn.upPAYABLE_AMOUNT", ADVC_ORDER_ID);
    }
    /**
     * 修改提交状态
     */
    public void txCommitById(Map <String, String> params){
    	update("Termreturn.commitById", params);
    }
    /**
     * 根据主表id查询现有终端退货明细来源单据明细id
     * @param QUERYID
     * @return
     */
    public String qeuryId(String QUERYID) {
		Object id=load("Termreturn.queryDtlId",QUERYID);
		if(null==id){
			return null;
		}else{
			return  id.toString();
		}
	}
    public Map<String, String> getTerminalInfoById(String BMXXID) {
		return (Map<String, String>) this.load("Advcorder.getTerminalInfoById",BMXXID);
	}
    
    /**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params){
		return findList("Termreturn.expertExcel",params);
	}
	
}