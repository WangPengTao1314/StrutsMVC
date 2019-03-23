/**
 * prjName:喜临门营销平台
 * ucName:促销品发放
 * fileName:PrmtexitServiceImpl
*/
package com.hoperun.erp.sale.prmtexit.service.impl;
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
import com.hoperun.erp.sale.prmtexit.model.PrmtexitModel;
import com.hoperun.erp.sale.prmtexit.model.PrmtexitModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.erp.sale.prmtexit.service.PrmtexitService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-19 16:54:28
 */
public class PrmtexitServiceImpl extends BaseService implements PrmtexitService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Prmtexit.pageQuery", "Prmtexit.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Prmtexit.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param PRMT_GOODS_EXTD_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Prmtexit.delete", params);
		 //删除子表 
		 return update("Prmtexit.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Prmtexit.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param PRMT_GOODS_EXTD_ID
	 * @param PrmtexitModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String PRMT_GOODS_EXTD_ID, PrmtexitModel model,List<PrmtexitModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		
			String no = LogicUtil.getBmgz("ERP_PRMT_GOODS_EXTD_NO");
		    params.put("PRMT_GOODS_EXTD_NO",no);
		    
		    params.put("PRMT_PLAN_ID",model.getPRMT_PLAN_ID());
		    params.put("PRMT_PLAN_NO",model.getPRMT_PLAN_NO());
		    params.put("PRMT_PLAN_NAME",model.getPRMT_PLAN_NAME());
		    params.put("COUNT_DATE_BEG",model.getCOUNT_DATE_BEG());
		    params.put("COUNT_DATE_END",model.getCOUNT_DATE_END());
		    params.put("CHANN_ID",model.getCHANN_ID());
		    params.put("CHANN_NO",model.getCHANN_NO());
		    params.put("CHANN_NAME",model.getCHANN_NAME());
		    params.put("SALE_AMOUNT",model.getSALE_AMOUNT());
		    params.put("EXTD_PSON_ID",model.getEXTD_PSON_ID());
		    params.put("EXTD_PSON_NAME",model.getEXTD_PSON_NAME());
		    
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
		if(StringUtil.isEmpty(PRMT_GOODS_EXTD_ID)){
			PRMT_GOODS_EXTD_ID= StringUtil.uuid32len();
			params.put("PRMT_GOODS_EXTD_ID", PRMT_GOODS_EXTD_ID);
		txInsert(params);
		} else{
			params.put("PRMT_GOODS_EXTD_ID", PRMT_GOODS_EXTD_ID);
			txUpdateById(params);
			clearCaches(PRMT_GOODS_EXTD_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(PRMT_GOODS_EXTD_ID, chldList);
		}
	}
	
	/**
	 * @详细
	 * @param param PRMT_GOODS_EXTD_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Prmtexit.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param PRMT_GOODS_EXTD_ID the PRMT_GOODS_EXTD_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String PRMT_GOODS_EXTD_ID, List<PrmtexitModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (PrmtexitModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("EXTD_NUM",model.getEXTD_NUM());
		    params.put("REMARK",model.getREMARK());
            params.put("PRMT_GOODS_EXTD_ID",PRMT_GOODS_EXTD_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getPRMT_GOODS_EXTD_DTL_ID())) {
                params.put("PRMT_GOODS_EXTD_DTL_ID", StringUtil.uuid32len());
                params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("PRMT_GOODS_EXTD_DTL_ID", model.getPRMT_GOODS_EXTD_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Prmtexit.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Prmtexit.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param PRMT_GOODS_EXTD_ID the PRMT_GOODS_EXTD_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <PrmtexitModelChld> childQuery(String PRMT_GOODS_EXTD_ID) {
        Map params = new HashMap();
        params.put("PRMT_GOODS_EXTD_ID", PRMT_GOODS_EXTD_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Prmtexit.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param PRMT_GOODS_EXTD_DTL_IDs the PRMT_GOODS_EXTD_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <PrmtexitModelChld> loadChilds(Map <String, String> params) {
       return findList("Prmtexit.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param PRMT_GOODS_EXTD_DTL_IDs the PRMT_GOODS_EXTD_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String PRMT_GOODS_EXTD_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("PRMT_GOODS_EXTD_DTL_IDS", PRMT_GOODS_EXTD_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Prmtexit.deleteChldByIds", params);
    }
    /**
     * 
     * 
     */
    public String countSum(String param) {
    	return  (String) this.load("Prmtexit.countSum", param);
     }
    /**
	 * @提交
	 * @param PRMT_GOODS_EXTD_ID
	 * 
	 * @return true, if tx delete
	 */
	public void commit(Map <String, String> params) {
	     //删除父
         update("Prmtexit.commit", params);
		 //删除子表 
	}
	
	public boolean loadPrmtExitByPrd(Map <String, String> param){
		String countNum =  (String)this.load("Prmtexit.countPrd", param); 
		if(null!=countNum && !"0".equals(countNum)){
			return true;
		}else{
			return false;
		}
	}
}