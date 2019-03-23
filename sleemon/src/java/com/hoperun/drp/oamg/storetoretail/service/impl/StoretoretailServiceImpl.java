
package com.hoperun.drp.oamg.storetoretail.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.storetoretail.model.StoretoretailModel;
import com.hoperun.drp.oamg.storetoretail.service.StoretoretailService;
import com.hoperun.sys.model.UserBean;

/**
 * 专卖店转分销店申请单维护
 * @author gu_hongxiu
 *
 */
public class StoretoretailServiceImpl extends BaseService implements StoretoretailService {
	
	/**
     * 获取所有品牌名称
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<String> getBrand(){
    	Map<String,String> map=new HashMap<String,String>();
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	map.put("STATE", "'启用'");
    	return this.findList("product.getBrand",map);
    }
    
	/**
	 * @查询
	 * @param params
	 *          map
	 * @param pageNo
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Storetoretail.pageQuery", "Storetoretail.pageCount",
				params, pageNo);
	}
	
	/**
     * 加载.
     * @param param the param
     * @return the map
     */
	public Map<String, String> load(String param) {		
		Map<String, String> params = new HashMap<String, String>();
        params.put("SPCL_STORE_TO_RETAIL_REQ_ID", param);
        return (Map<String, String>) load("Storetoretail.loadById", params);
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
	
	/**
     * 编辑：新增/修改
     * @param SPCL_STORE_TO_RETAIL_REQ_ID
     * @param model
     * @param userBean
     * @return
     */
	public String txEdit(String SPCL_STORE_TO_RETAIL_REQ_ID, StoretoretailModel model, UserBean userBean) {
		
		Map <String, String> params = new HashMap <String, String>();
		params.put("SPCL_STORE_TO_RETAIL_REQ_ID",model.getSPCL_STORE_TO_RETAIL_REQ_ID());  //专卖店转分销店申请单ID   
		 //专卖店转分销店申请单编号   
		if(model.getSPCL_STORE_TO_RETAIL_REQ_NO().equals(BusinessConsts.ZDSC)){
			params.put("SPCL_STORE_TO_RETAIL_REQ_NO",LogicUtil.getBmgz("DRP_TO_RETAIL_REQ_NO"));
		}else{
			params.put("SPCL_STORE_TO_RETAIL_REQ_NO",model.getSPCL_STORE_TO_RETAIL_REQ_NO());
		}
		params.put("CHANN_ID",model.getCHANN_ID());  //渠道ID 
		params.put("CHANN_NO",model.getCHANN_NO());  //渠道编号 
		params.put("CHANN_NAME",model.getCHANN_NAME());  //渠道名称 
		params.put("TERM_ID",model.getTERM_ID());  //终端信息ID 
		params.put("TERM_NO",model.getTERM_NO());  //终端编号 
		params.put("TERM_NAME",model.getTERM_NAME());  //终端名称 
		params.put("TERM_TYPE",model.getTERM_TYPE());  //终端类型 
		params.put("RNVTN_REQ_ID",model.getRNVTN_REQ_ID());  //装修申请人ID 
		params.put("RNVTN_REQ_NAME",model.getRNVTN_REQ_NAME());  //装修申请人姓名 
		params.put("CHANN_PERSON_CON",model.getCHANN_PERSON_CON());  //渠道联系人 
		params.put("CHANN_TEL",model.getCHANN_TEL());  //渠道电话 
		params.put("AREA_ID",model.getAREA_ID());  //区域ID 
		params.put("AREA_NO",model.getAREA_NO());  //区域编号 
		params.put("AREA_NAME",model.getAREA_NAME());  //区域名称 
		params.put("AREA_MANAGE_ID",model.getAREA_MANAGE_ID());  //区域经理ID 
		params.put("AREA_MANAGE_NAME",model.getAREA_MANAGE_NAME());  //区域经理名称 
		params.put("AREA_MANAGE_TEL",model.getAREA_MANAGE_TEL());  //区域经理电话  
		params.put("BEG_SBUSS_DATE",model.getBEG_SBUSS_DATE());  //开业时间 
		params.put("PERSON_CON",model.getPERSON_CON());  //联系人 
		params.put("TEL",model.getTEL());  //电话 
		params.put("MOBILE",model.getMOBILE());  //手机 
		params.put("SALE_STORE_ID",model.getSALE_STORE_ID());  //卖场ID 
		params.put("SALE_STORE_NAME",model.getSALE_STORE_NAME());  //卖场名称 
		params.put("ZONE_ID",model.getZONE_ID());  //行政区划ID 
		params.put("ZONE_ADDR",model.getZONE_ADDR());  //行政区划地址 
		params.put("BUSS_SCOPE",model.getBUSS_SCOPE());  //经营范围(经营品牌) 
		params.put("LOCAL_TYPE",model.getLOCAL_TYPE());  //商场位置类别 
		params.put("SPCL_STORE_TO_RETAIL_DATE",model.getSPCL_STORE_TO_RETAIL_DATE());  //专卖店转分销店申请单日期 
		params.put("NEW_CHANN_ID",model.getNEW_CHANN_ID());  //取货渠道ID 
		params.put("CHANN_NO2",model.getCHANN_NO2());  //取货渠道编号 
		params.put("CHANN_NAME2",model.getCHANN_NAME2());  //取货渠道名称 
		params.put("REIT_AMOUNT_PS",model.getREIT_AMOUNT_PS());  //报销金额(元/平米) 
		params.put("ORG_REIT_AMOUNT",model.getORG_REIT_AMOUNT());  //原报销金额 
		params.put("REITED_AMOUNT",model.getREITED_AMOUNT()); //已报销金额 
		params.put("NO_REIT_AMOUNT",model.getNO_REIT_AMOUNT()); //未报销金额(元/平米) 
		params.put("ADDRESS",model.getADDRESS());  //详细地址 
		params.put("STATE",model.getSTATE());  //状态 
		params.put("REQ_RSON",model.getREQ_RSON());  //申报理由 
		params.put("REMARK",model.getREMARK());  //备注 	
		params.put("BUSS_AREA",model.getBUSS_AREA());  //营业面积 
		
		if (StringUtils.isEmpty(SPCL_STORE_TO_RETAIL_REQ_ID)) {
			SPCL_STORE_TO_RETAIL_REQ_ID= StringUtil.uuid32len();
			params.put("SPCL_STORE_TO_RETAIL_REQ_ID", SPCL_STORE_TO_RETAIL_REQ_ID);
        	params.put("CREATOR", userBean.getXTYHID());//制单人ID
            params.put("CRE_NAME", userBean.getXM());//制单人名称            
            params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());//制单机构id
            params.put("ORG_NAME", userBean.getJGMC());//制单机构名称  
            params.put("LEDGER_ID",userBean.getLoginZTXXID());
		    params.put("LEDGER_NAME",userBean.getLoginZTMC());
        	params.put("STATE", BusinessConsts.UNCOMMIT);//状态
        	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//删除标记         	
            txInsert(params);            
        } else {
            params.put("SPCL_STORE_TO_RETAIL_REQ_ID", SPCL_STORE_TO_RETAIL_REQ_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称           
            txUpdateById(params);
            clearCaches(SPCL_STORE_TO_RETAIL_REQ_ID);
        }
		if(StringUtils.isNotEmpty(model.getPIC_PATH())){
			params.put("PIC_PATH", model.getPIC_PATH());//图片路径
			txInsertAtt(params);
		}		
        return SPCL_STORE_TO_RETAIL_REQ_ID;
	}
	
	/**
     * 增加记录 Description :.
     * @param params the params
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, String> params) {
        insert("Storetoretail.insert", params);
        return true;
    }
    
    /**
     * 更新记录 Description :.
     * @param params the params
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, String> params) {

        return update("Storetoretail.updateById", params);
    }
    
    /**
     * 删除
     * @param map
     * @return
     */
	public boolean txDelete(Map<String,String> map) {	
		delete("Storetoretail.delete", map);
		delete("Att.deleteAtt", map);
        return true;
	}
	
	/**
	 * 新增/修改附件表
	 * @param params
	 * @return
	 */
	public boolean txInsertAtt(Map<String,String> params){
		String fromBillId = params.get("SPCL_STORE_TO_RETAIL_REQ_ID");
		
		//查询是否存在
		String att_path = loadAtt(fromBillId);
		Map <String, String> attParams = new HashMap <String, String>();
		attParams.put("FROM_BILL_ID", fromBillId);
		attParams.put("ATT_PATH", params.get("PIC_PATH"));//图片路径
		if(StringUtils.isEmpty(att_path)){			
			attParams.put("ATT_ID", StringUtil.uuid32len());						
			attParams.put("CREATOR", params.get("CREATOR"));
			attParams.put("CRE_NAME", params.get("CRE_NAME"));
			attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
			insert("Att.insertAtt", attParams);
		}else{
			update("Att.updateAtt", attParams);
		}
        return true;
	}
	
	/**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params){
		return findList("Storetoretail.expertExcel",params);
	}
}