/**
 * prjName:喜临门营销平台
 * ucName:终端退货录入
 * fileName:AdvcreturnServiceImpl
*/
package com.hoperun.drp.sale.advcreturn.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advcreturn.model.AdvcreturnModel;
import com.hoperun.drp.sale.advcreturn.model.AdvcreturnModelChld;
import com.hoperun.drp.sale.advcreturn.service.AdvcreturnService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public class AdvcreturnServiceImpl extends BaseService implements AdvcreturnService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo) {
		return this.pageQuery("Advcreturn.pageQuery", "Advcreturn.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Advcreturn.insert", params);
		return true;
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Advcreturn.updateById", params);
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
	public void txEdit(String ADVC_ORDER_ID, AdvcreturnModel model,List<AdvcreturnModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
			if(model.getADVC_ORDER_NO().equals(BusinessConsts.ZDSC)){
				String ADVC_ORDER_NO = LogicUtil.getBmgz("DRP_ADVC_ORDER_NO");// 预订单编号
				params.put("ADVC_ORDER_NO",ADVC_ORDER_NO);//终端退货单编号
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
		    params.put("TEL",model.getTEL());
		    params.put("SALE_PSON_NAME",model.getSALE_PSON_NAME());
		    params.put("PAYABLE_AMOUNT",model.getPAYABLE_AMOUNT());
		    params.put("RECV_ADDR",model.getRECV_ADDR());
		    params.put("REMARK",model.getREMARK());
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
			    txChildEdit(ADVC_ORDER_ID, chldList);
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
		return (Map<String,String>) load("Advcreturn.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public String txChildEdit(String ADVC_ORDER_ID, List<AdvcreturnModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (AdvcreturnModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            String ADVC_ORDER_DTL_ID=model.getADVC_ORDER_DTL_ID();
            String FROM_BILL_DTL_ID=model.getFROM_BILL_DTL_ID();
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("PRICE",model.getPRICE());
		    params.put("DECT_RATE",model.getDECT_RATE());
		    params.put("DECT_PRICE",model.getDECT_PRICE());
		    params.put("ORDER_NUM",model.getORDER_NUM());
		    params.put("PAYABLE_AMOUNT",model.getPAYABLE_AMOUNT());
            params.put("ADVC_ORDER_ID",ADVC_ORDER_ID);
            params.put("RETURN_ATT",model.getRETURN_ATT());
            params.put("REMARK", model.getREMARK());
            float ORDER_NUM=0;
            //如果没有明细ID的则是新增，有的是修改
            if (FROM_BILL_DTL_ID==null||FROM_BILL_DTL_ID==""||FROM_BILL_DTL_ID.equals("undefined")) {
            	ORDER_NUM=Float.parseFloat(load("Advcreturn.loadORDER_NUMById",ADVC_ORDER_DTL_ID).toString());
                params.put("ADVC_ORDER_DTL_ID", StringUtil.uuid32len());
                params.put("FROM_BILL_DTL_ID", ADVC_ORDER_DTL_ID);
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
            	ORDER_NUM=Float.parseFloat(load("Advcreturn.loadORDER_NUMById",FROM_BILL_DTL_ID).toString());
                params.put("ADVC_ORDER_DTL_ID", ADVC_ORDER_DTL_ID);
                updateList.add(params);
            }
            if(ORDER_NUM<Float.parseFloat(model.getORDER_NUM())){
            	return "1";
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Advcreturn.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Advcreturn.insertChld", addList);
        }
        this.update("Advcreturn.upPAYABLE_AMOUNT", ADVC_ORDER_ID);
        return "0";
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <AdvcreturnModelChld> childQuery(String ADVC_ORDER_ID) {
        Map params = new HashMap();
        params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
        return this.findList("Advcreturn.queryChldByFkId", params);
    }
    /**
     * 按库房编号或库房名称查找库房数据
     */
    @SuppressWarnings("unchecked")
	public Map<String,String> findSTOREOUT(Map<String,String> map){
    	return (Map<String,String>) load("Advcreturn.findSTOREOUT", map);
    }
    public void txReverse(Map<String, String> map) {
		// TODO Auto-generated method stub
		update("Advcreturn.updateById",map);
	}
    public Integer getDtlCount(String ADVC_ORDER_ID){
		return  StringUtil.getInteger(this.load("Advcorder.getDtlCount", ADVC_ORDER_ID));
	}
}