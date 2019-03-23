/**
 * prjName:喜临门营销平台
 * ucName:工艺单维护
 * fileName:TechorderServiceImpl
*/
package com.hoperun.erp.sale.techorder.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.techorder.model.TechorderModelChld;
import com.hoperun.erp.sale.techorder.service.TechorderService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-10-12 18:31:07
 */
public class TechorderServiceImpl extends BaseService implements TechorderService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Techorder.pageQuery", "Techorder.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Techorder.insert", params);
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
		return update("Techorder.updateById", params);
	}
	
	/**
	 * @详细
	 * @param param TECH_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Techorder.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param TECH_ORDER_ID the TECH_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String TECH_ORDER_ID, List<TechorderModelChld> chldList,UserBean userBean) {

        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (TechorderModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            String TECH_ORDER_DTL_ID = model.getTECH_ORDER_DTL_ID();
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("SPCL_TECH_FLAG",model.getSPCL_TECH_FLAG());
		    params.put("IS_CAN_PRD_FLAG",model.getIS_CAN_PRD_FLAG());
		    params.put("NEW_PRD_ID",model.getNEW_PRD_ID());
		    params.put("NEW_PRD_NO",model.getNEW_PRD_NO());
		    params.put("NEW_PRD_NAME",model.getNEW_PRD_NAME());
		    params.put("NEW_PRD_SPEC",model.getNEW_PRD_SPEC());
//            params.put("TECH_ORDER_ID",TECH_ORDER_ID);
            params.put("REMARK",model.getREMARK()); 
            params.put("TECH_ORDER_DTL_ID", TECH_ORDER_DTL_ID);
            
            
            //插入附件表
    		String TECH_ATT = model.getTECH_ATT();//附件
    		Map<String,String> paramMap = new HashMap<String,String>();
    		paramMap.put("FROM_BILL_ID", TECH_ORDER_DTL_ID);
    		List<Map<String,String>> fileList = this.findList("Techorder.queryUploadFile", paramMap);
    		if(null != fileList && !fileList.isEmpty()){
    			updateFileUpload(TECH_ORDER_DTL_ID, TECH_ATT, userBean);
    		}else{
    			insertFileUpload(TECH_ORDER_DTL_ID, TECH_ATT, userBean);
    		}
    		
            updateList.add(params);
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Techorder.updateChldById", updateList);
        }
        return true;
    }
	
    /**
     * 插入附件表 
     * @param FROM_BILL_ID 表ID
     * @param ATT_PATH 附件路径
     * @param userBean
     */
    public void insertFileUpload(String FROM_BILL_ID,String ATT_PATH,UserBean userBean){
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("ATT_ID", StringUtil.uuid32len());
    	paramMap.put("FROM_BILL_ID", FROM_BILL_ID);
    	paramMap.put("ATT_PATH", ATT_PATH);
    	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	paramMap.put("CREATOR", userBean.getXTYHID());//制单人ID
    	paramMap.put("CRE_NAME", userBean.getXM());//制单人名称
    	paramMap.put("CRE_TIME", DateUtil.now());//制单时间
    	this.insert("Techorder.insertFileUpload", paramMap);
    }
    
    /**
     * 更新附件信息
     * @param ATT_ID 附件ID
     * @param ATT_PATH 附件路径
     * @param userbean
     */
    public void updateFileUpload(String FROM_BILL_ID,String ATT_PATH,UserBean userBean){
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("FROM_BILL_ID", FROM_BILL_ID);
    	paramMap.put("ATT_PATH", ATT_PATH);
    	this.insert("Techorder.updateFileUpload", paramMap);
    }
    
    
	/**
     * * 根据主表Id查询子表记录
     * @param TECH_ORDER_ID the TECH_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TechorderModelChld> childQuery(String TECH_ORDER_ID) {
        Map params = new HashMap();
        params.put("TECH_ORDER_ID", TECH_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Techorder.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param TECH_ORDER_DTL_IDs the TECH_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <TechorderModelChld> loadChilds(Map <String, String> params) {
       return findList("Techorder.loadChldByIds",params);
    }
     
    /**
     * 审核
     * @param TECH_ORDER_ID 工艺单ID
     * @param SALE_ORDER_ID 非标订单ID
     * @param TECH_ORDER_NO 工艺单编号
     * @param userBean
     */
    @SuppressWarnings("unchecked")
	public void txAudit(String TECH_ORDER_ID,String SALE_ORDER_ID,String TECH_ORDER_NO, UserBean userBean){
    	Map<String,String> checkMap=new HashMap<String,String>();
//    	checkMap.put("TECH_ORDER_ID", TECH_ORDER_ID);
//    	checkMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
//    	checkMap.put("IS_CAN_PRD_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
//    	int count=Integer.parseInt(load("Techorder.countId",checkMap).toString());
    	Map<String,String> map=new HashMap<String,String>();
    	map.put("TECH_ORDER_ID", TECH_ORDER_ID);
    	map.put("STATE", "待核价");
        map.put("AUDIT_ID", userBean.getRYXXID());
        map.put("AUDIT_NAME", userBean.getXM());
        map.put("AUDIT_TIME", "sysdate");
        map.put("UPDATOR", userBean.getRYXXID());//更新人
        map.put("UPD_NAME", userBean.getXM());
        map.put("UPD_TIME", "sysdate");
        
//    	if(count==0){
//    		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
//    	}
    	txUpdateById(map);
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        map.put("IS_CAN_PRD_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
    	List<String> list=findList("Techorder.getTechDtl", map);
    	for (int i = 0; i < list.size(); i++) {
       		Map<String,String> param=new HashMap<String,String>();
       		param.put("SALE_ORDER_DTL_ID", list.get(i));
       		param.put("IS_CAN_PRD_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
   			update("Techorder.upSale",param);
   		}
    	
    	checkMap.clear();
    	checkMap.put("TECH_ORDER_ID", TECH_ORDER_ID);
    	checkMap.put("TECH_ORDER_NO", TECH_ORDER_NO);
    	checkMap.put("SALE_ORDER_ID", SALE_ORDER_ID);
    	//插入生命周期
    	inertSaleordertrace(checkMap,userBean);
    }
    
    
    
    /**
	 * 插入销售订单生命周期
	 * @param parent 主表字段信息
	 * @param userBean
	 */
	public void inertSaleordertrace(Map<String,String> parent,UserBean userBean){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("GOODS_ORDER_TRACE_ID", StringUtil.uuid32len());
		paramMap.put("SALE_ORDER_ID", parent.get("SALE_ORDER_ID"));
		paramMap.put("BILL_NO", parent.get("TECH_ORDER_NO"));
		paramMap.put("DEAL_PSON_NAME", userBean.getXM());
		paramMap.put("DEAL_TIME", BusinessConsts.UPDATE_TIME);
		paramMap.put("STATE", "工艺单已审核");//工艺单审核
//		paramMap.put("TRACE_URL","");
		 
		paramMap.put("BILL_TYPE", "工艺单");
		paramMap.put("ACTION_NAME", "工艺单已审核");
		
		this.insert("Saleorder.insertOrderTrack", paramMap);
	}
	
	
    /**
     * * 根据主表Id查询生命周期子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @return the list
     */
    @SuppressWarnings("unchecked")
	public List <Map<String,String>> queryTrace(String SALE_ORDER_ID){
    	 Map<String,String> params = new HashMap<String,String>();
         params.put("SALE_ORDER_ID", SALE_ORDER_ID);
         return this.findList("Saleorder.queryTraceByFkId", params);
    }
    
    
    /**
	 * @查询编辑明细
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage childQueryEdit(Map<String,String> params, int pageNo){
		return this.pageQuery("Techorder.pageChildQuery", "Techorder.pageChildCount",params, pageNo);
	}
	
	
	public int checkChild(String TECH_ORDER_DTL_IDS){
		Object count=this.load("Techorder.checkChild", TECH_ORDER_DTL_IDS);
		if(null==count){
			count="0";
		}
		return StringUtil.getInteger(count);
	}
	public void txChldAudit(String TECH_ORDER_DTL_IDS,UserBean userBean){
		Map<String,String> map=new HashMap<String, String>();
		map.put("STATE", "待核价");
		map.put("TECH_ORDER_DTL_IDS", TECH_ORDER_DTL_IDS);
		this.update("Techorder.upChldByIDS", map);//更新
//		map.put("IS_CAN_PRD_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
//		this.update("Techorder.upSale",map);
		map.put("AUDIT_ID", userBean.getRYXXID());
        map.put("AUDIT_NAME", userBean.getXM());
        map.put("AUDIT_TIME", "sysdate");
        map.put("UPDATOR", userBean.getRYXXID());//更新人
        map.put("UPD_NAME", userBean.getXM());
        map.put("UPD_TIME", "sysdate");
        this.update("Techorder.updateStateById", map);
//        this.update("Techorder.updateMainStateByIDS", map);
	}
	public void txChldCancel(String TECH_ORDER_DTL_IDS){
		Map<String,String> map=new HashMap<String, String>();
		map.put("STATE", "提交");
		map.put("TECH_ORDER_DTL_IDS", TECH_ORDER_DTL_IDS);
		this.update("Techorder.upChldByIDS", map);
		this.update("Techorder.upStateByChldIDS", map);
	}
}