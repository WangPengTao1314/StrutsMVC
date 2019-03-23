/**
 * prjName:喜临门营销平台
 * ucName:广告投放申请单维护
 * fileName:AdvreqServiceImpl
*/
package com.hoperun.drp.adv.advreq.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.adv.advreq.model.AdvreqModel;
import com.hoperun.drp.adv.advreq.model.AdvreqModelChld;
import com.hoperun.drp.adv.advreq.service.AdvreqService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author ghx
 * *@createdate 2014-07-15
 */
public class AdvreqServiceImpl extends BaseService implements AdvreqService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Advreq.pageQuery", "Advreq.pageCount",params, pageNo);
	}
	
	/**
	 * 增加
	 * @param params 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Advreq.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param ERP_ADV_REQ_ID
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Advreq.delete", params);
		 //删除子表 
		 return update("Advreq.delChldByFkId", params);
	}
	
	/**
	 * update data
	 * @param params
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Advreq.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param ERP_ADV_REQ_ID
	 * @param AdvreqModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String ERP_ADV_REQ_ID, AdvreqModel model,List<AdvreqModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		
		params.put("ERP_ADV_REQ_NO",model.getERP_ADV_REQ_NO());
		params.put("ADV_TYPE",model.getADV_TYPE());
		params.put("CHANN_ID",model.getCHANN_ID());
		params.put("CHANN_NO",model.getCHANN_NO());
		params.put("CHANN_NAME",model.getCHANN_NAME());
		params.put("AREA_ID",model.getAREA_ID());
		params.put("AREA_NO",model.getAREA_NO());
		params.put("AREA_NAME",model.getAREA_NAME());
		params.put("AREA_MANAGE_ID",model.getAREA_MANAGE_ID());
		params.put("AREA_MANAGE_NAME",model.getAREA_MANAGE_NAME());		
		params.put("CHANN_RANK",model.getCHANN_RANK());
		params.put("RANK_SCALE", model.getRANK_SCALE());
		params.put("ADV_CO_NAME",model.getADV_CO_NAME());
		params.put("ADV_CO_CONTACT",model.getADV_CO_CONTACT());
		params.put("ADV_CO_TEL",model.getADV_CO_TEL());
		params.put("ADV_CONTENT",model.getADV_CONTENT());
		params.put("ADV_ADDR",model.getADV_ADDR());
		params.put("ADV_START_DATE",model.getADV_START_DATE());
		params.put("ADV_END_DATE",model.getADV_END_DATE());
		params.put("ADV_TOTAL_AMOUNT",model.getADV_TOTAL_AMOUNT());
		params.put("HEAD_SUP_AMOUNT",model.getHEAD_SUP_AMOUNT());
		params.put("PIC_PATH",model.getPIC_PATH());
		params.put("STATE",model.getSTATE());
		params.put("REMARK",model.getREMARK());  //备注 
		
		
		if(StringUtil.isEmpty(ERP_ADV_REQ_ID)){
			ERP_ADV_REQ_ID= StringUtil.uuid32len();
			params.put("ERP_ADV_REQ_ID", ERP_ADV_REQ_ID);
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CREATOR",userBean.getXTYHID());
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
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
			params.put("ERP_ADV_REQ_ID", ERP_ADV_REQ_ID);
			txUpdateById(params);			
		}
		
		txInsertAtt(params);
		clearCaches(ERP_ADV_REQ_ID);
		
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(ERP_ADV_REQ_ID, chldList);
		}
	}
	
	/**
	 * @详细
	 * @param param ERP_ADV_REQ_ID
	 * @param param the param
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Advreq.loadById", param);
	}
	
	 /**
     * * 明细数据编辑
     * @param ERP_ADV_REQ_ID the ERP_ADV_REQ_ID
     * @param modelList the model list
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String ERP_ADV_REQ_ID, List<AdvreqModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (AdvreqModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("PAY_BATCH",model.getPAY_BATCH());
		    params.put("PAY_PERCENT",model.getPAY_PERCENT());
		    params.put("PAY_AMOUNT",model.getPAY_AMOUNT());
		    params.put("PAY_COND",model.getPAY_COND());
		    params.put("PAY_TYPE",model.getPAY_TYPE());
		    params.put("STATE",model.getSTATE());
            params.put("ERP_ADV_REQ_ID",ERP_ADV_REQ_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getERP_ADV_REQ_DTL_ID())) {
                params.put("ERP_ADV_REQ_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("ERP_ADV_REQ_DTL_ID", model.getERP_ADV_REQ_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Advreq.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Advreq.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param ERP_ADV_REQ_ID the ERP_ADV_REQ_ID
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <AdvreqModelChld> childQuery(String ERP_ADV_REQ_ID) {
        Map params = new HashMap();
        params.put("ERP_ADV_REQ_ID", ERP_ADV_REQ_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Advreq.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param ERP_ADV_REQ_DTL_IDs the ERP_ADV_REQ_DTL_IDs 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <AdvreqModelChld> loadChilds(Map <String, String> params) {
       return findList("Advreq.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * @param ERP_ADV_REQ_DTL_IDs the ERP_ADV_REQ_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String ERP_ADV_REQ_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("ERP_ADV_REQ_DTL_IDS", ERP_ADV_REQ_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Advreq.deleteChldByIds", params);
    }
    
    /**
	 * 新增/修改附件表
	 * @param params
	 * @return
	 */
	public boolean txInsertAtt(Map<String,String> params){
		String fromBillId = params.get("ERP_ADV_REQ_ID");
		String currAtt = params.get("PIC_PATH");//图片路径
		
		//查询是否存在
		String att_path = loadAtt(fromBillId);
		
		Map <String, String> attParams = new HashMap <String, String>();
		attParams.put("FROM_BILL_ID", fromBillId);
		attParams.put("ATT_PATH", currAtt);
		if(StringUtils.isEmpty(att_path) && !StringUtils.isEmpty(currAtt)){	
			//insert Att 
			attParams.put("ATT_ID", StringUtil.uuid32len());						
			attParams.put("CREATOR", params.get("CREATOR") == null ? params.get("UPDATOR") : params.get("CREATOR"));
			attParams.put("CRE_NAME", params.get("CRE_NAME") == null ? params.get("UPD_NAME") : params.get("CRE_NAME"));
			attParams.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			insert("Att.insertAtt", attParams);
		}else{
			if(StringUtils.isEmpty(currAtt)){
				//delete Att
				attParams.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
				delete("Att.deleteAtt", attParams);
				
			}else if (!currAtt.equals(att_path)){
				//update Att
				update("Att.updateAtt", attParams);	
			}
		}
		
        return true;
	}
	
	/**
	 * 加载图片.
	 * @param param
	 * @return
	 */
	public String loadAtt(String param){		
		Map<String, String> params = new HashMap<String, String>();
        params.put("FROM_BILL_ID", param);
        return (String) load("Att.loadAtt", params);
	}
}