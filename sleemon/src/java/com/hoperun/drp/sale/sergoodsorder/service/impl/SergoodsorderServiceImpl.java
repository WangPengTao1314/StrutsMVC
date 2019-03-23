/**
 * prjName:喜临门营销平台
 * ucName:订货订单处理
 * fileName:GoodsorderServiceImpl
*/
package com.hoperun.drp.sale.sergoodsorder.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.base.factory.model.FactoryModel;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelTrace;
import com.hoperun.drp.sale.sergoodsorder.service.SergoodsorderService;
import com.hoperun.erp.sale.goodsorder.model.GoodsorderModel;
import com.hoperun.erp.sale.goodsorder.model.GoodsorderModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl *@func 分销 -区域服务中心- 订货订单处理 
 * *@version 1.1 *@author zzb 
 * *@createdate
 * 2014-05-22 15:55:09
 */
public class SergoodsorderServiceImpl extends BaseService implements SergoodsorderService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Sergoodsorder.pageQuery", "Sergoodsorder.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,Object> params) {
		insert("Sergoodsorder.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param GOODS_ORDER_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Sergoodsorder.delete", params);
		 //删除子表 
		 return update("Sergoodsorder.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,Object> params) {
		return update("Sergoodsorder.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param GOODS_ORDER_ID
	 * @param GoodsorderModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String GOODS_ORDER_ID, GoodsorderModel model,List<GoodsorderModelChld> chldList, UserBean userBean) {
		Map<String,Object> params = new HashMap<String,Object>();
		
		//params.put("", this.countAmount(chldList));
		
		if(StringUtil.isEmpty(GOODS_ORDER_ID)){
			GOODS_ORDER_ID= StringUtil.uuid32len();
			params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
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
			params.put("STATE",BusinessConsts.JC_STATE_DEFAULT);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		    txInsert(params);
		} else{
			params.put("SHIP_POINT_ID",model.getSHIP_POINT_ID());
			params.put("SHIP_POINT_NAME",model.getSHIP_POINT_NAME());
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
			txUpdateById(params);
			clearCaches(GOODS_ORDER_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(GOODS_ORDER_ID, chldList);
		}
	}
	
	public Double countAmount(List<GoodsorderModelChld> chldList){
		
		Double d = 0.00d;
		for(GoodsorderModelChld model : chldList){
			if(StringUtil.isEmpty(model.getORDER_AMOUNT())){
            	d =  d + StringUtil.getDouble(model.getORDER_AMOUNT());
            }
		}
		return d;
	}
	
	/**
	 * @详细
	 * @param param GOODS_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Sergoodsorder.loadById", param);
	}
	
	
	/**
	 * 加载主表数据 
	 * @param param GOODS_ORDER_ID
	 * @return GoodsorderModel
	 */
	public GoodsorderModel loadById(String GOODS_ORDER_ID){
		return  (GoodsorderModel) load("Sergoodsorder.loadByIdModel", GOODS_ORDER_ID);
	}
	
	/**
     * * 明细数据编辑
     * 
     * @param GOODS_ORDER_ID the GOODS_ORDER_ID
     * @param modelList the model list
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String GOODS_ORDER_ID, List<GoodsorderModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        
        for (GoodsorderModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("GOODS_ORDER_ID",GOODS_ORDER_ID); 
		    params.put("PRICE",model.getPRICE());
		    params.put("DECT_RATE",model.getDECT_RATE());
		    params.put("DECT_PRICE",model.getDECT_PRICE());
		    params.put("ORDER_NUM",model.getORDER_NUM());
		    params.put("ORDER_AMOUNT",model.getORDER_AMOUNT());
            params.put("OLD_PRICE",model.getOLD_PRICE());
            params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
            params.put("IS_NO_STAND_FLAG", model.getIS_NO_STAND_FLAG());//是否非标  1->非标订单
            
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getGOODS_ORDER_DTL_ID())) {
                params.put("GOODS_ORDER_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("GOODS_ORDER_DTL_ID", model.getGOODS_ORDER_DTL_ID());
                updateList.add(params);
            }
            
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Sergoodsorder.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Sergoodsorder.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param GOODS_ORDER_ID the GOODS_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <GoodsorderModelChld> childQuery(String GOODS_ORDER_ID) {
        Map params = new HashMap();
        params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Sergoodsorder.queryChldByFkId", params);
    }
    
    /**
     * 查询默认的工厂
     * @param SHIP_POINT_ID
     * @return
     */
    @SuppressWarnings("unchecked")
	public Map<String,String> queryDefaultFactory(String GOODS_ORDER_ID) {
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String> result = new HashMap<String,String>();
        params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
        params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		List<FactoryModel> list = this.findList("Sergoodsorder.queryDefaultFactory", params);
		if(null != list && !list.isEmpty()){
			result.put("FACTORY_ID", list.get(0).getFACTORY_ID());
			result.put("FACTORY_NAME", list.get(0).getFACTORY_NAME());
		}
		return result;
        
    }
    
    

    /**
     * * 根据子表Id批量加载子表信息
     * @param GOODS_ORDER_DTL_IDs the GOODS_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <GoodsorderModelChld> loadChilds(Map <String, String> params) {
       return findList("Sergoodsorder.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param GOODS_ORDER_DTL_IDs the GOODS_ORDER_DTL_IDs
     */
    @SuppressWarnings("unchecked")
	@Override
    public void txBatch4DeleteChild(String GOODS_ORDER_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("GOODS_ORDER_DTL_IDS", GOODS_ORDER_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Sergoodsorder.deleteChldByIds", params);
    }
    /**
     * 插入 销售订单、销售订单明细 
     * @param model 订货订单
     * @param chilList 订货订单明细
     */
    public void txAddSalOrder(GoodsorderModel model,List <GoodsorderModelChld> chilList,UserBean userBean){
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("FROM_BILL_ID",model.getGOODS_ORDER_ID()); 
    	param.put("FROM_BILL_NO",model.getGOODS_ORDER_NO()); 
    	param.put("ORDER_CHANN_ID",model.getORDER_CHANN_ID()); 
    	param.put("ORDER_CHANN_NO",model.getORDER_CHANN_NO()); 
    	param.put("ORDER_CHANN_NAME",model.getORDER_CHANN_NAME());
    	param.put("SEND_CHANN_ID",model.getSEND_CHANN_ID());
   	    param.put("SEND_CHANN_NO",model.getSEND_CHANN_NO());
   	    param.put("SEND_CHANN_NAME",model.getSEND_CHANN_NAME());
   	    Integer IS_USE_REBATE = StringUtil.getInteger( model.getIS_USE_REBATE());
   	    param.put("IS_USE_REBATE",IS_USE_REBATE);
	   	param.put("RECV_CHANN_ID",model.getRECV_CHANN_ID());
	   	param.put("RECV_CHANN_NO",model.getRECV_CHANN_NO());
	   	param.put("RECV_CHANN_NAME",model.getRECV_CHANN_NAME());
	   	param.put("PERSON_CON",model.getPERSON_CON());
	   	param.put("TEL",model.getTEL());
		param.put("RECV_ADDR",model.getRECV_ADDR());
		param.put("AREA_ID",model.getAREA_ID());
		param.put("AREA_NO",model.getAREA_NO());
		param.put("AREA_NAME",model.getAREA_NAME());
		param.put("RECV_ADDR_NO",model.getRECV_ADDR_NO());//收货地址编号
		param.put("SHIP_POINT_ID",model.getSHIP_POINT_ID());
		param.put("SHIP_POINT_NAME",model.getSHIP_POINT_NAME());
		param.put("REMARK",model.getREMARK());
		param.put("CREATOR",userBean.getXTYHID());
		param.put("CRE_NAME",userBean.getXM());
		param.put("CRE_TIME","sysdate");
		param.put("DEL_FLAG",Integer.valueOf(BusinessConsts.DEL_FLAG_COMMON));
		param.put("ORDER_DATE",model.getORDER_DATE());//订货日期
		param.put("DEPT_ID",userBean.getBMXXID());
		param.put("DEPT_NAME",userBean.getBMMC());
		param.put("ORG_ID",userBean.getJGXXID());
		param.put("ORG_NAME",userBean.getJGMC());
		param.put("LEDGER_ID",userBean.getLoginZTXXID());
		param.put("LEDGER_NAME",userBean.getLoginZTMC());
		param.put("SALE_ORDER_ID",StringUtil.uuid32len());
		param.put("SALE_ORDER_NO",LogicUtil.getBmgz("DRP_SALE_ORDER_NO"));
		param.put("BILL_TYPE","订货销售"); //销售类型
		param.put("STATE",BusinessConsts.UNCOMMIT);//状态为   未提交
    	//插入销售订单
    	insertSaleOrder(param);
		//插入销售订单明细
    	insertSaleChilds(param,chilList);
		
		//更新订货订单的状态
		param.clear();
		param.put("GOODS_ORDER_ID", model.getGOODS_ORDER_ID());
		param.put("UPDATOR", userBean.getXTYHID());
		param.put("UPD_NAME", userBean.getXM());
		param.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
		param.put("STATE",BusinessConsts.DONE);
		param.put("AUDIT_ID", userBean.getXTYHID());
		param.put("AUDIT_NAME", userBean.getXM());
		param.put("AUDIT_TIME", BusinessConsts.UPDATE_TIME);
		update("Sergoodsorder.updateById", param);
		
		
    }
    
    /**
     * 插入销售订单明细
     * @param parent
     * @param chilList
     */
    public void insertSaleChilds(Map<String,Object> parent,List <GoodsorderModelChld> chilList){
     
    	//原始单据新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        for(GoodsorderModelChld model:chilList){
        	Map<String,Object> param = new HashMap<String,Object>();
        	param.put("GOODS_ORDER_ID", parent.get("FROM_BILL_ID"));
        	param.put("GOODS_ORDER_NO", parent.get("FROM_BILL_NO"));  //订货订单NO
        	param.put("SALE_ORDER_DTL_ID", StringUtil.uuid32len());
        	param.put("FROM_BILL_DTL_ID", model.getGOODS_ORDER_DTL_ID()); 
        	param.put("SALE_ORDER_ID",parent.get("SALE_ORDER_ID"));
        	param.put("PRD_ID", model.getPRD_ID());   
        	param.put("PRD_NO", model.getPRD_NO());
        	param.put("PRD_NAME", model.getPRD_NAME());   
        	param.put("PRD_SPEC", model.getPRD_SPEC()); 
        	param.put("PRD_COLOR", model.getPRD_COLOR()); 
        	param.put("BRAND", model.getBRAND()); 
        	param.put("STD_UNIT", model.getSTD_UNIT()); 
        	param.put("VOLUME", model.getVOLUME());
        	param.put("SPCL_TECH_FLAG", model.getSPCL_TECH_FLAG()); 
        	//是否非标
        	Integer IS_NO_STAND_FLAG = StringUtil.getInteger(model.getIS_NO_STAND_FLAG());
        	param.put("IS_NO_STAND_FLAG", IS_NO_STAND_FLAG); 
        	Double PRICE = StringUtil.getDouble(model.getPRICE());
        	Double DECT_RATE = StringUtil.getDouble(model.getDECT_RATE());
        	Double DECT_PRICE = StringUtil.getDouble(model.getDECT_PRICE());
        	Double ORDER_AMOUNT = StringUtil.getDouble(model.getORDER_AMOUNT());
        	Double ORDER_NUM = StringUtil.getDouble(model.getORDER_NUM());
        	param.put("PRICE", PRICE); 
        	param.put("DECT_RATE", DECT_RATE); 
        	param.put("DECT_PRICE", DECT_PRICE); 
        	param.put("ORDER_NUM", ORDER_NUM);
        	param.put("ORDER_AMOUNT", ORDER_AMOUNT); 
        	param.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());  //特殊工艺ID
        	param.put("ADVC_SEND_DATE", model.getADVC_SEND_DATE()); 
        	param.put("DEL_FLAG", StringUtil.getInteger(BusinessConsts.DEL_FLAG_COMMON)); 
        	Double TECH_PRICE_MULT = 0d;
        	param.put("TECH_PRICE_MULT", TECH_PRICE_MULT); 
        	param.put("CANCEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        	param.put("CREDIT_FREEZE_PRICE", model.getCREDIT_FREEZE_PRICE());//信用冻结单价
        	addList.add(param);	
        }
        
    	if(!addList.isEmpty()){
    		this.batch4Update("Sergoodsorder.insertSaleOrderDtl", addList);
    	}
    	    
    }
    
    
    
    
    /**
     * 订单订单生命周期
     * @param GOODS_ORDER_ID
     * @param GOODS_ORDER_NO
     * @param userBean
     */
    private void insertGoodorderTack(String GOODS_ORDER_ID,String GOODS_ORDER_NO,UserBean userBean ){
    	Map<String,String> params = new HashMap<String,String>();
    	   //插入订货订单跟踪表
	    params.clear();
		String GOODS_ORDER_TRACE_ID = StringUtil.uuid32len();
		params.put("GOODS_ORDER_TRACE_ID", GOODS_ORDER_TRACE_ID);
		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		params.put("BILL_NO", GOODS_ORDER_NO);//订货订单的NO
		params.put("BILL_TYPE", BusinessConsts.INSTORE_ORDER_TYPE);//订货订单的type
		params.put("ACTION_NAME", BusinessConsts.ORDER_BILL_ACTION_COMMIT);//订货订单提交
		params.put("TRACE_URL", BusinessConsts.ORDER_BILL_ACTION_TRACE_URL + GOODS_ORDER_ID);
		params.put("DEAL_PSON_NAME", userBean.getXM());
		params.put("STATE", BusinessConsts.DONE);//已处理
		insert("Goodsorderhd.insertOrderTrack", params);
    }
    /**
     * 插入销售订单
     * @param param
     */
    public void insertSaleOrder(Map<String,Object> param){
		//插入销售订单
		insert("Sergoodsorder.insertSaleOrder", param);
		
    }
    
    /**
     * 插入 工艺单  和工艺单明细
     * @param param
     */
    public void insertTechOrder(Map<String,Object> param,List <Map <String, Object>> childList){
	 
		//工艺单
		insert("Sergoodsorder.insertTechOrder", param);
		
		this.batch4Update("Sergoodsorder.insertTechOrderDtl", childList);
		
    }
    
    
   
    
    /**
     * 更新预订单的状态
     * @param GOODS_ORDER_ID
     * @param reason 退货原因
     */
    public void txUpdateAdvcOrder(String GOODS_ORDER_ID,String reason,UserBean userBean)throws Exception{
    	Map<String,String> param = new HashMap<String,String>();
		param.put("UPDATOR", userBean.getXTYHID());
		param.put("UPD_NAME", userBean.getXM());
		param.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
		param.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
//		param.put("AREA_SER_ID", "");//将区域服务中心设置空
//		param.put("AREA_SER_NO", "");
//		param.put("AREA_SER_NAME", "");
		param.put("REMARK", reason);//将退货原因 写入 备注里面
 
		//更新当前订货订单
		param.put("STATE","区域服务中心退回"); //"退回"
		update("Sergoodsorder.updateById", param);
	 
    }
    
    
    /**
     * 取消 订货
     * @param GOODS_ORDER_ID 订单ID
     * @param mxIds 明细IDS
     * @param remark 取消原因
     * @param isAll 是否全部 取消 true 全部取消
     */
    public void updateOrderClose(String GOODS_ORDER_ID,String mxIds,String remark,boolean isAll,UserBean userBean){
    	Map<String,String> param = new HashMap<String,String>();
		param.put("UPDATOR", userBean.getXTYHID());
		param.put("UPD_NAME", userBean.getXM());
		param.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
		param.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		param.put("REMARK", remark);
		param.put("GOODS_ORDER_DTL_IDS", mxIds);
		
		if(isAll){
		    //跟新 订货订单的 状态 为 “制作”
			param.put("STATE", BusinessConsts.STATE_MAKE);//状态“制作”
	        update("Sergoodsorder.updateById", param);
		}
		
		param.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		//删除子表 
		update("Sergoodsorder.deleteChldByIds", param);
    }
    
    
    /**
     * * 根据主表Id查询生命周期子表记录
     * @param GOODS_ORDER_ID the GOODS_ORDER_ID
     * @return the list
     */
    @SuppressWarnings("unchecked")
	public List <GoodsorderhdModelTrace> traceQuery(String GOODS_ORDER_ID){
    	 Map<String,String> params = new HashMap<String,String>();
         params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
         return this.findList("Sergoodsorder.queryTraceByFkId", params);
    }
}