/**
 * prjName:喜临门营销平台
 * ucName:推广费用报销单维护
 * fileName:PromoreimServiceImpl
*/
package com.hoperun.drp.oamg.promoreim.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.promoreim.model.PromoreimModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.oamg.promoreim.service.PromoreimService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2014-01-25 19:49:05
 */
public class PromoreimServiceImpl extends BaseService implements PromoreimService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Promoreim.pageQuery", "Promoreim.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Promoreim.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param PRMT_COST_REIT_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
        return update("Promoreim.delete", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Promoreim.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param PRMT_COST_REIT_ID
	 * @param PromoreimModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String PRMT_COST_REIT_ID, PromoreimModel model, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		
			params.put("PRMT_COST_REQ_ID",model.getPRMT_COST_REQ_ID());
			params.put("PRMT_COST_REQ_NO",model.getPRMT_COST_REQ_NO());
			params.put("CHANN_ID", model.getCHANN_ID());
		    params.put("CHANN_NO",model.getCHANN_NO());
		    params.put("CHANN_NAME",model.getCHANN_NAME());
		    params.put("CHANN_PERSON_CON",model.getCHANN_PERSON_CON());
		    params.put("CHANN_TEL",model.getCHANN_TEL());
		    params.put("ZONE_ID",model.getZONE_ID());
		    params.put("ZONE_ADDR",model.getZONE_ADDR());
		    params.put("AREA_MANAGE_ID",model.getAREA_MANAGE_ID());
		    params.put("AREA_MANAGE_NAME",model.getAREA_MANAGE_NAME());
		    params.put("AREA_MANAGE_TEL",model.getAREA_MANAGE_TEL());
		    params.put("REQ_ID",model.getREQ_ID());
		    params.put("REQ_NAME",model.getREQ_NAME());
		    params.put("REQ_DATE",model.getREQ_DATE());
		    params.put("EXCT_SMRZ",model.getEXCT_SMRZ());
		    params.put("TOTAL_PRMT_COST",model.getTOTAL_PRMT_COST());
		    params.put("REIT_REQ_AMOUNT",model.getREIT_REQ_AMOUNT());
		    params.put("COST_TYPE",model.getCOST_TYPE());
		if(StringUtil.isEmpty(PRMT_COST_REIT_ID)){
			PRMT_COST_REIT_ID= StringUtil.uuid32len();
			params.put("PRMT_COST_REIT_ID", PRMT_COST_REIT_ID);
			params.put("PRMT_COST_REIT_NO", LogicUtil.getBmgz("ERP_PRMT_COST_REIT_NO"));
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
			params.put("PRMT_COST_REIT_ID", PRMT_COST_REIT_ID);
			txUpdateById(params);
			clearCaches(PRMT_COST_REIT_ID);
		}
		
		//删除附件列表后重新添加附件信息
		Map<String,String> fileParamMap = new HashMap<String,String>();
		fileParamMap.put("FROM_BILL_ID", PRMT_COST_REIT_ID);
		this.delete("Promoexpen.deleteFile", fileParamMap);
		
		List<String> pathList = parsePath(model.getSTATENEBTS_ATT());
	    if(pathList!=null){
	    	for(String path : pathList){
		    	fileParamMap.put("ATT_ID", StringUtil.uuid32len());
		    	fileParamMap.put("ATT_PATH", path);
		    	fileParamMap.put("FROM_BILL_ID", PRMT_COST_REIT_ID);
		    	fileParamMap.put("CREATOR", userBean.getYHBH());
		    	fileParamMap.put("CRE_NAME", userBean.getXM());
		    	fileParamMap.put("DEL_FLAG", "0");
		    	this.insert("Promoexpen.saveFile", fileParamMap);//批量插入附件信息
		    }
	    }
	}
	
	//解析多文件路径
	private List<String> parsePath(String paths){
		List<String> pathList = new ArrayList<String>();
		if(StringUtil.isEmpty(paths)){
			return null;
		}else{
			String[] pathArr = paths.split(",");
			for(String path : pathArr){
				pathList.add(path);
			}
		}
		return pathList;
	}
	
	/**
	 * @详细
	 * @param param PRMT_COST_REIT_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Promoreim.loadById", param);
	}
	 /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params) {

        return update("Promoreim.updStusById", params);
    }
	
    /**
     * 修改状态为启用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params) {
        return update("Promoreim.updStusById", params);
    }
    
    @Override
	public String loadFiles(String param) {
		List<Map> fileList = this.findList("Promoexpen.queryFiles", param);
		String path = "";
			
		if(fileList!=null){
			for(Map fileMap : fileList){
				path += fileMap.get("ATT_PATH")+",";
			}
		}
		if(path.lastIndexOf(",")>0){
			path = path.substring(0,path.lastIndexOf(","));
		}
		
		return path;//去除最后逗号
	}
}