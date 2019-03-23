/**
 * prjName:喜临门营销平台
 * ucName:标准订单
 * fileName:SaleorderServiceImpl
*/
package com.hoperun.erp.sale.saleorder.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.saleorderview.model.SaleorderviewChldModel;
import com.hoperun.erp.sale.saleorder.model.SaleorderModel;
import com.hoperun.erp.sale.saleorder.model.SaleorderModelChld;
import com.hoperun.erp.sale.saleorder.service.SaleorderService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-10-12 13:52:19
 */
public class SaleorderServiceImpl extends BaseService implements SaleorderService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		//变颜色的日期
		params.put("BILL_DIFF_DATE_FLAG", BusinessConsts.BILL_DIFF_DATE_FLAG);
		return this.pageQuery("Saleorder.pageQuery", "Saleorder.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Saleorder.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param SALE_ORDER_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Saleorder.delete", params);
		 //删除子表 
		 return update("Saleorder.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Saleorder.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param SALE_ORDER_ID
	 * @param SaleorderModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String SALE_ORDER_ID, SaleorderModel model,List<SaleorderModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("RECV_CHANN_ID", model.getRECV_CHANN_ID());
		params.put("RECV_CHANN_NO", model.getRECV_CHANN_NO());
		params.put("RECV_CHANN_NAME", model.getRECV_CHANN_NAME());
		params.put("PERSON_CON", model.getPERSON_CON());
		params.put("TEL", model.getTEL());
		params.put("RECV_ADDR", model.getRECV_ADDR());
		params.put("RECV_ADDR_NO", model.getRECV_ADDR_NO());
		params.put("SHIP_POINT_ID", model.getSHIP_POINT_ID());
		params.put("SHIP_POINT_NAME", model.getSHIP_POINT_NAME());
		params.put("AREA_ID", model.getAREA_ID());
		params.put("AREA_NO", model.getAREA_NO());
		params.put("AREA_NAME", model.getAREA_NAME());
		
		Map warMap = new HashMap();
		warMap.put("AREA_ID", model.getAREA_ID());
		HashMap areaWarMap = (HashMap)load("Saleorder.queryChannWarId", warMap);
		if(areaWarMap!=null){
			params.put("WAREA_ID", (String)areaWarMap.get("AREA_ID_P"));
			params.put("WAREA_NO", (String)areaWarMap.get("AREA_NO_P"));
			params.put("WAREA_NAME", (String)areaWarMap.get("AREA_NAME_P"));		
		}

		params.put("REMARK", model.getREMARK());
		if(StringUtil.isEmpty(SALE_ORDER_ID)){
			SALE_ORDER_ID= StringUtil.uuid32len();
			params.put("SALE_ORDER_ID", SALE_ORDER_ID);
			//是否使用唯一编号 ，使用唯一单号以订货订单编号
			if("1".equals(Consts.USER_ONLY_ORDER_NO)){
				params.put("SALE_ORDER_NO", LogicUtil.getBmgz("DRP_GOODS_ORDER_NO"));
			}else{
				params.put("SALE_ORDER_NO", LogicUtil.getBmgz("ERP_SALE_ORDER_NO"));
			}
			params.put("BILL_TYPE", model.getBILL_TYPE()); 
			params.put("ORDER_CHANN_ID", model.getORDER_CHANN_ID());
			params.put("ORDER_CHANN_NO", model.getORDER_CHANN_NO());
			params.put("ORDER_CHANN_NAME", model.getORDER_CHANN_NAME());
			params.put("SEND_CHANN_ID", userBean.getCHANN_ID());
			params.put("SEND_CHANN_NO", userBean.getCHANN_NO());
			params.put("SEND_CHANN_NAME", userBean.getCHANN_NAME());
			params.put("FROM_BILL_NO", "手工新增");
			params.put("ORDER_DATE", "sysdate");
			params.put("IS_USE_REBATE", BusinessConsts.YJLBJ_FLAG_FALSE);
			params.put("BASE_ADD_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
//			Map<String,String> map=(Map<String, String>) this.load("Saleorder.loadAreaInfo",userBean.getCHANN_ID());
//			if(!map.isEmpty()){
//				params.putAll(map);
//			}
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
			params.put("SALE_ORDER_ID", SALE_ORDER_ID);
			txUpdateById(params);
			clearCaches(SALE_ORDER_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(SALE_ORDER_ID, chldList,"edit",model.getBILL_TYPE());
		}
	}
	
	/**
	 * @详细
	 * @param param SALE_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Saleorder.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String SALE_ORDER_ID, List<SaleorderModelChld> chldList,String actionType,String BILL_TYPE) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        Map<String,String>paramMap = new HashMap<String,String>();
    	paramMap.put("SALE_ORDER_ID", SALE_ORDER_ID);
        int ROW_NO = this.queryForInt("Goodsorderhd.queryMaxRowNo", paramMap);
        Map saleMap = load(SALE_ORDER_ID);
        for (SaleorderModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("SALE_ORDER_ID",SALE_ORDER_ID);
            params.put("PRD_ID", model.getPRD_ID());
            params.put("PRD_NO", model.getPRD_NO());
            params.put("PRD_NAME", model.getPRD_NAME());
			params.put("PRD_SPEC", model.getPRD_SPEC());
			params.put("PRD_COLOR", model.getPRD_COLOR());
			params.put("BRAND", model.getBRAND());
			params.put("STD_UNIT", model.getSTD_UNIT());
			params.put("VOLUME", model.getVOLUME());
			params.put("PRICE",model.getPRICE());
			params.put("DECT_RATE",model.getDECT_RATE());
			params.put("DECT_PRICE",model.getDECT_PRICE());
			params.put("ORDER_NUM",model.getORDER_NUM());
			params.put("ORDER_AMOUNT",model.getORDER_AMOUNT());
			params.put("ADVC_SEND_DATE",model.getADVC_SEND_DATE());
			params.put("IS_BACKUP_FLAG", model.getIS_BACKUP_FLAG());//是否是备货
			params.put("IS_NO_STAND_FLAG", model.getIS_NO_STAND_FLAG());//是否非标
			params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
			ROW_NO++;
			params.put("ROW_NO", StringUtil.nullToSring(ROW_NO));//行号
			
        	//如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSALE_ORDER_DTL_ID())) {
                params.put("SALE_ORDER_DTL_ID", StringUtil.uuid32len());
    			params.put("CANCEL_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);//取消标记
    			params.put("IS_CAN_PRD_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);//是否可生产
    			if("赠品订货".equals(BILL_TYPE)){
    				params.put("IS_FREE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);//是否赠品标记
    			}else{
    				params.put("IS_FREE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);//是否赠品标记
    			}
    			
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		        params.put("PRDC_POINT_ID",(String)saleMap.get("SHIP_POINT_ID"));
		        params.put("PRDC_POINT_NAME",(String)saleMap.get("SHIP_POINT_NAME"));
                addList.add(params);
            } else {
                params.put("SALE_ORDER_DTL_ID", model.getSALE_ORDER_DTL_ID());
                updateList.add(params);
            }
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Saleorder.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Saleorder.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <SaleorderModelChld> childQuery(String SALE_ORDER_ID) {
        Map params = new HashMap();
        params.put("SALE_ORDER_ID", SALE_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//查询 未取消预定的 0 正常
		params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
		
        return this.findList("Saleorder.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param SALE_ORDER_DTL_IDs the SALE_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <SaleorderModelChld> loadChilds(Map <String, String> params) {
       return findList("Saleorder.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param SALE_ORDER_DTL_IDs the SALE_ORDER_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String SALE_ORDER_DTL_IDs) {
	   Map<String,String> params = new HashMap<String,String>();
	   params.put("SALE_ORDER_DTL_IDS", SALE_ORDER_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Saleorder.deleteChldByIds", params);
    }
    
    /**
     * 转非标订单
     * @param SALE_ORDER_ID_OLD 销售订单ID
     * @param SALE_ORDER_DTL_IDS  明细IDS
     * @param isAll true->全部
     */
    public void txConvertTechOrder(String SALE_ORDER_ID_OLD,String SALE_ORDER_DTL_IDS,boolean isAll,UserBean userBean){
    	Map<String,String> params = new HashMap<String,String>();
    	String SALE_ORDER_ID = StringUtil.uuid32len();
    	params.put("CRE_NAME",userBean.getXM());
	    params.put("CREATOR",userBean.getXTYHID());
	    params.put("CRE_TIME","sysdate");
	    params.put("DEPT_ID",userBean.getBMXXID());
	    params.put("DEPT_NAME",userBean.getBMMC());
	    params.put("ORG_ID",userBean.getJGXXID());
	    params.put("ORG_NAME",userBean.getJGMC());
	    params.put("LEDGER_ID",userBean.getLoginZTXXID());
	    params.put("LEDGER_NAME",userBean.getLoginZTMC());
	    params.put("SALE_ORDER_NO",LogicUtil.getBmgz("ERP_SALE_ORDER_NO"));
		params.put("SALE_ORDER_ID",SALE_ORDER_ID);//新增 销售订单
		params.put("SALE_ORDER_ID_OLD",SALE_ORDER_ID_OLD);
		params.put("BILL_TYPE", BusinessConsts.UN_STANDARD);//非标
		params.put("STATE", BusinessConsts.UNCOMMIT);//未提交
		params.put("IS_NO_STAND_FLAG",BusinessConsts.INTEGER_1);//1 是否非标
		params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDS);
		//插入新的  销售订单
		insert("Saleorder.insertFBOrder", params);
		insert("Saleorder.insertFBOrderDtl", params);
		//删除 原始的 明细 单据
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
		delete("Saleorder.deleteChldByIds", params);
		
		//插入工艺单
		params.put("TECH_ORDER_ID",StringUtil.uuid32len());
		params.put("TECH_ORDER_NO",LogicUtil.getBmgz("ERP_TECH_ORDER_NO"));
		params.put("FROM_BILL_ID",params.get("SALE_ORDER_ID"));
		params.put("FROM_BILL_NO",params.get("FROM_BILL_NO"));
		params.put("STATE",BusinessConsts.COMMIT);//状态为 提交
		this.insert("Saleorder.insertTechOrder", params);
		//插入工艺单明细
		params.put("TECH_ORDER_DTL_ID",StringUtil.uuid32len());
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		params.put("IS_CAN_PRD_FLAG",BusinessConsts.INTEGER_1);//1
		
		this.insert("Saleorder.insertTechOrderDtl", params);
		
		if(isAll){
			//删除 原始的 标准订单
		  	params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
			params.put("SALE_ORDER_ID",SALE_ORDER_ID_OLD);//原始的销售订单ID
			update("Saleorder.delete", params);
			
			//将新增的销售订单ID反填到订货订单明细里面
			params.put("SALE_ORDER_ID_OLD", SALE_ORDER_ID_OLD);//原始的销售订单ID
			params.put("SALE_ORDER_ID", SALE_ORDER_ID);//新生产的销售订单ID
			update("Saleorder.updateGoodsOrderDtl_SaleId",params);
		}
		
		
    }
    
    
   

    /**
     * 取消预定
     * @param SALE_ORDER_DTL_ID 订单ID
     * @param SALE_ORDER_DTL_IDS 明细IDs
     * @param FROM_BILL_DTL_IDS 来源单据IDS
     */
    public void txCalcelSaleOrder(String SALE_ORDER_ID, String SALE_ORDER_DTL_IDS,String FROM_BILL_DTL_IDS,UserBean userBean)throws Exception{
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_1);//1
    	params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDS);
    	params.put("CREDIT_FREEZE_PRICE","0");
    	LogicUtil.updFreezeCredit(BusinessConsts.ACCT_SALE_ORDER1_CONF_ID,SALE_ORDER_ID, SALE_ORDER_DTL_IDS);
    	LogicUtil.insRebateJournal(BusinessConsts.FL_SALE_ORDER_CONF_ID, SALE_ORDER_ID,SALE_ORDER_DTL_IDS);
    	this.update("Saleorder.updateChldByIds", params);
    	
    	//如果该订单下 没有正常状态的明细 那么该订单状态更新为‘取消预订’
    	params.clear();
    	params.put("SALE_ORDER_ID",SALE_ORDER_ID);
    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
    	params.put("DEL_FLAG",BusinessConsts.INTEGER_0);
    	int rst = this.queryForInt("Saleorder.queryChildForInt", params);
    	if(rst == 0){
    		params.put("STATE", "已取消");
    		params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
    		update("Saleorder.updateById",params);
    	}
    	//更新 订货订单明细
    	params.clear();
    	if(!StringUtil.isEmpty(FROM_BILL_DTL_IDS)){
    		params.put("CANCEL_FLAG",BusinessConsts.INTEGER_1);//取消标记 1
        	params.put("GOODS_ORDER_DTL_IDS", FROM_BILL_DTL_IDS);
        	update("Goodsorder.updateGoodDtlByIdS",params);
    	}
    	
    }
    
    /**
     * 恢复预定
     * @param SALE_ORDER_ID 销售订单ID
     * @param SALE_ORDER_DTL_IDS  明细IDS
     * @param FROM_BILL_DTL_IDS 来源单据IDS
     */
    public void txRecoverOrder(String SALE_ORDER_ID,String SALE_ORDER_DTL_IDS,String FROM_BILL_DTL_IDS ,UserBean userBean)throws Exception{
//    	String strSaleOrderDtlIds = "'"+SALE_ORDER_DTL_IDS.replaceAll(",", "','")+"'";
//    	Map<String,String> dtlParam = new HashMap<String,String>();
//    	dtlParam.put("SALE_ORDER_DTL_IDS",strSaleOrderDtlIds);
//        List list = loadChilds(dtlParam);
//        Map dtlMap = (Map)list.get(0);
//        BigDecimal dectPrice = (BigDecimal)dtlMap.get("DECT_PRICE");
//        BigDecimal ratio = new BigDecimal("0.3");
//        double dfreeze =dectPrice.multiply(ratio).doubleValue();   	
//    	Map<String,String> params = new HashMap<String,String>();
//    	//params.put("SALE_ORDER_ID",SALE_ORDER_ID);
//    	//params.put("SALE_ORDER_DTL_ID",StringUtil.uuid32len());
//    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);//0
//    	params.put("CREDIT_FREEZE_PRICE",String.valueOf(dfreeze));
//    	//params.put("SALE_ORDER_DTL_ID_OLD",SALE_ORDER_DTL_ID);//原始单据明细ID
//    	params.put("SALE_ORDER_DTL_IDS",strSaleOrderDtlIds);
//    	this.update("Saleorder.updateChldByIds", params);
//    	LogicUtil.updFreezeCredit(BusinessConsts.ACCT_SALE_ORDER_CONF_ID,SALE_ORDER_ID, strSaleOrderDtlIds);
//    	LogicUtil.updateRebate(BusinessConsts.FL_SALE_ORDER1_CONF_ID, SALE_ORDER_ID,strSaleOrderDtlIds);
//    	LogicUtil.insRebateJournal(BusinessConsts.FL_SALE_ORDER1_CONF_ID, SALE_ORDER_ID,strSaleOrderDtlIds);
//    	//如果单据已经‘取消预订’，又恢复预订的话，更新状态
//    	params.clear();
//    	params.put("SALE_ORDER_ID",SALE_ORDER_ID);
//    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
//    	params.put("DEL_FLAG",BusinessConsts.INTEGER_0);
//    	int rst = this.queryForInt("Saleorder.queryChildForInt", params);
//    	if(0<rst){
//    		params.put("STATE", "未提交");
//    		params.put("UPD_NAME",userBean.getXM());
//		    params.put("UPDATOR",userBean.getXTYHID());
//		    params.put("UPD_TIME","sysdate");
//    		update("Saleorder.updateById",params);
//    	}
//    	
//    	//更新 订货订单明细
//    	String tempIDs = FROM_BILL_DTL_IDS.replaceAll(",", "");
//    	if(!StringUtil.isEmpty(tempIDs)){
//    		FROM_BILL_DTL_IDS = "'"+FROM_BILL_DTL_IDS.replaceAll(",", "','")+"'";
//    		params.clear();
//        	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);//0
//        	params.put("GOODS_ORDER_DTL_IDS", FROM_BILL_DTL_IDS);
//        	update("Goodsorder.updateGoodDtlByIdS",params);
//    	}
    	
    	
    }
    
    /**乖法运算
     * @param x
     * @param y
     * @return
     */
    public static double multiply(String x, String y) {
    	if(x!=null && y!=null){
     	   BigDecimal bigX = new BigDecimal(x);
    	   BigDecimal bigY = new BigDecimal(y);
    	   return bigX.multiply(bigY).doubleValue();   		
    	}
    	return 0;
    }
    
    /**
     * 提交   审核通过
     * @param SALE_ORDER_ID 销售订单ID
     */
    public void txToCommit(String SALE_ORDER_ID,String SALE_ORDER_NO,UserBean userBean){
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("SALE_ORDER_ID", SALE_ORDER_ID);
    	params.put("STATE", BusinessConsts.PASS);
    	params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("UPD_TIME","sysdate");
    	this.update("Saleorder.updateById", params);
    	params.put("SALE_ORDER_NO",SALE_ORDER_NO);
    	inertSaleordertrace(params,userBean);
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
		paramMap.put("BILL_NO", parent.get("SALE_ORDER_NO"));
		paramMap.put("DEAL_PSON_NAME", userBean.getXM());
		paramMap.put("DEAL_TIME", BusinessConsts.UPDATE_TIME);
		paramMap.put("STATE", BusinessConsts.COMMIT);//提交
//		paramMap.put("TRACE_URL","");
		 
		paramMap.put("BILL_TYPE", "标准销售订单");
		paramMap.put("ACTION_NAME", "标准订单生效");
		
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
	 * @订货订单查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage goodsPageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Goodsorderhd.pageQuery", "Goodsorderhd.pageCount",params, pageNo);
	}
	
	/**
	 * * 根据订货订单主表Id查询子表记录
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<SaleorderviewChldModel> goodsChildQuery(String GOODS_ORDER_ID) {
		Map params = new HashMap();
		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		return this.findList("Goodsorderhd.queryChldByFkId", params);
	}
	
	public String getDECT_RATE(String CHANN_ID){
		Map<String,String> map=new HashMap<String, String>();
		map.put("CHANN_ID", CHANN_ID);
		map.put("DECT_TYPE", "赠品折扣");
		Object DECT_RATE=this.load("Saleorder.getDECT_RATE", map);
		if(null==DECT_RATE){
			return null;
		}
		return DECT_RATE.toString();
	}
	public List qrySaleOrderExp(Map<String,String> map){
		return this.findList("Saleorder.qrySaleOrderExp", map);
	}
	
}