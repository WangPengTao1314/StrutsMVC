/**
 * prjName:喜临门营销平台
 * ucName:退货单维护
 * fileName:PrdreturnServiceImpl
*/
package com.hoperun.erp.sale.prdreturn.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.prdreturn.model.PrdreturnModel;
import com.hoperun.erp.sale.prdreturn.model.PrdreturnModelChld;
import com.hoperun.erp.sale.prdreturn.service.PrdreturnService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-19 15:33:31
 */
public class PrdreturnServiceImpl extends BaseService implements PrdreturnService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Prdreturn.pageQuery", "Prdreturn.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Prdreturn.insert", params);
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
         update("Prdreturn.delete", params);
		 //删除子表 
		 return update("Prdreturn.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Prdreturn.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param PRD_RETURN_ID
	 * @param PrdreturnfinModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String PRD_RETURN_ID, PrdreturnModel model,List<PrdreturnModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();

		    params.put("PRD_RETURN_ID",model.getPRD_RETURN_ID());
		    params.put("PRD_RETURN_NO",model.getPRD_RETURN_NO());
		    params.put("FROM_BILL_ID",model.getFROM_BILL_ID());
		    params.put("BILL_TYPE",model.getBILL_TYPE());
		    params.put("FROM_BILL_NO",model.getFROM_BILL_NO());
		    params.put("EXPECT_RETURNDATE",model.getEXPECT_RETURNDATE());
		    params.put("RETURN_CHANN_ID",model.getRETURN_CHANN_ID());
		    params.put("RETURN_CHANN_NO",model.getRETURN_CHANN_NO());
		    params.put("RETURN_CHANN_NAME",model.getRETURN_CHANN_NAME());
		    params.put("RECV_CHANN_ID",model.getRECV_CHANN_ID());
		    params.put("RECV_CHANN_NO",model.getRECV_CHANN_NO());
		    params.put("RECV_CHANN_NAME",model.getRECV_CHANN_NAME());
		    params.put("REMARK",model.getREMARK());
		    
		if(StringUtil.isEmpty(PRD_RETURN_ID)){
			PRD_RETURN_ID= StringUtil.uuid32len();
			params.put("PRD_RETURN_ID", PRD_RETURN_ID);
			params.put("PRD_RETURN_NO",LogicUtil.getBmgz("ERP_PRD_RETURN_NO"));
			
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
			params.put("PRD_RETURN_ID", PRD_RETURN_ID);
			txUpdateById(params);
			clearCaches(PRD_RETURN_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(PRD_RETURN_ID, chldList,userBean);
		}
	}
	
	/**
	 * @详细
	 * @param param PRD_RETURN_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Prdreturn.loadById", param);
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
    public boolean txChildEdit(String PRD_RETURN_ID, List<PrdreturnModelChld> chldList,UserBean userBean) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (PrdreturnModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRICE_DECIDE",model.getPRICE_DECIDE());		    
		    params.put("DEAL_FACTORY_ID",model.getDEAL_FACTORY_ID());		    
		    params.put("DUTY_DECIDE",model.getDUTY_DECIDE());
		    params.put("REMARK_DECIDE",model.getREMARK_DECIDE());
		    params.put("DEAL_FACTORY_NAME",model.getDEAL_FACTORY_NAME());
		    params.put("REAL_RETURN_AMOUNT",model.getREAL_RETURN_AMOUNT());
		    params.put("FROM_BILL_DTL_ID",model.getFROM_BILL_DTL_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("RETURN_PRICE",model.getRETURN_PRICE());
		    params.put("RETURN_NUM",model.getRETURN_NUM());
		    params.put("RETURN_AMOUNT",model.getRETURN_AMOUNT());
		    params.put("RETURN_RSON_TYPE",model.getRETURN_RSON_TYPE());
		    params.put("RETURN_RSON",model.getRETURN_RSON());
		    String ADVC_RETURN_PRICE=model.getADVC_RETURN_PRICE();
		    if(!"0".equals(ADVC_RETURN_PRICE)){
		    	params.put("ADVC_RETURN_PRICE", ADVC_RETURN_PRICE);
		    }
		    //附件上传 不能查看附件问题 是文件路径有问题
		    String RETURN_ATT = model.getRETURN_ATT();
		    String name = "";
		    int i = RETURN_ATT.indexOf("/");
		    int length = RETURN_ATT.length();
		    if(-1 == i && length > 10){
		    	name = RETURN_ATT.substring(0,4)+"/"+RETURN_ATT.substring(4,9)+"/"
		    	+RETURN_ATT.substring(9,length);
		    	
		    	 params.put("RETURN_ATT",name);
		    }else{
		    	  params.put("RETURN_ATT",RETURN_ATT);
		    }
		    
            params.put("PRD_RETURN_ID",PRD_RETURN_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getPRD_RETURN_DTL_ID())) {
                params.put("PRD_RETURN_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		        params.put("DECIDE_FLAG","0");
                addList.add(params);
            } else {
                params.put("PRD_RETURN_DTL_ID", model.getPRD_RETURN_DTL_ID());
                
                params.put("DECIDE_PSON_ID",userBean.getXTYHID());
                params.put("DECIDE_PSON_NAME",userBean.getXM());
                params.put("DECIDE_FLAG","1");
                params.put("DECIDE_TIME","sysdate");
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Prdreturn.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Prdreturn.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param PRD_RETURN_ID the PRD_RETURN_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <PrdreturnModelChld> childQuery(String PRD_RETURN_ID) {
        Map params = new HashMap();
        params.put("PRD_RETURN_ID", PRD_RETURN_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Prdreturn.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <PrdreturnModelChld> loadChilds(Map <String, String> params) {
       return findList("Prdreturn.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String PRD_RETURN_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("PRD_RETURN_DTL_IDS", PRD_RETURN_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Prdreturn.deleteChldByIds", params);
    }
    //按来源单据id查询是否来自入库差异单
    public Object getStoreInDiff(String Bussid){
    	return this.load("Prdreturn.getStoreInDiff",Bussid);
    }
}