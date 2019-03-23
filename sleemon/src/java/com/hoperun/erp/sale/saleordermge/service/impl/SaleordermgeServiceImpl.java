/**
 * prjName:喜临门营销平台
 * ucName:标准订单
 * fileName:SaleorderServiceImpl
*/
package com.hoperun.erp.sale.saleordermge.service.impl;
import java.math.BigDecimal;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.CreditCtrlUtil;
import com.hoperun.commons.util.InterfaceInvokeUtil;
import com.hoperun.commons.util.JesonImplUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.report.queryProStatus.model.ProStatus;
import com.hoperun.erp.sale.saleordermge.service.SaleordermgeService;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModel;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-10-12 13:52:19
 */
public class SaleordermgeServiceImpl extends BaseService implements SaleordermgeService {
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
		return this.pageQuery("Saleordermge.pageQuery", "Saleordermge.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Saleordermge.insert", params);
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
         update("Saleordermge.delete", params);
		 //删除子表 
		 return update("Saleordermge.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Saleordermge.updateById", params);
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
	public void txEdit(String SALE_ORDER_ID, SaleorderspModel model,List<SaleorderspModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("REMARK",model.getREMARK());
		params.put("SHIP_POINT_ID",model.getSHIP_POINT_ID());
		params.put("SHIP_POINT_NAME",model.getSHIP_POINT_NAME());
		
		if(StringUtil.isEmpty(SALE_ORDER_ID)){
			SALE_ORDER_ID= StringUtil.uuid32len();
			params.put("SALE_ORDER_ID", SALE_ORDER_ID);
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
			    txChildEdit(SALE_ORDER_ID, chldList);
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
		return (Map<String,String>) load("Saleordermge.loadById", param);
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
    public boolean txChildEdit(String SALE_ORDER_ID, List<SaleorderspModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (SaleorderspModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("SALE_ORDER_ID",SALE_ORDER_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSALE_ORDER_DTL_ID())) {
                params.put("SALE_ORDER_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("SALE_ORDER_DTL_ID", model.getSALE_ORDER_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Saleordermge.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Saleordermge.insertChld", addList);
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
    public List <SaleorderspModelChld> childQuery(Map<String,String> params) {
		//查询 未取消预定的 0 正常
//		params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
        return this.findList("Saleordermge.queryChldByFkId", params);
    }
    
    /**
     * * 根据主表Id查询子表记录 
     *   返回map list
     */
    public List <Map<String,Object>> childMapQuery(String SALE_ORDER_ID){
    	 Map<String,String> params = new HashMap<String,String>();
         params.put("SALE_ORDER_ID", SALE_ORDER_ID);
         //只查询0的记录。1为删除。0为正常
 		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
 		//查询 未取消预定的 0 正常
 		params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
        return this.findList("Saleordermge.queryChldByFkId", params);
    }
    
    /**
     * 字表查询 封装model
     * @param SALE_ORDER_ID
     * @return
     */
    public List <SaleorderspModelChld> childModelQuery(String SALE_ORDER_ID) {
        Map<String,String> params = new HashMap<String,String>();
        params.put("SALE_ORDER_ID", SALE_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//查询 未取消预定的 0 正常
//		params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
		params.put("STATE","正常");
        return this.findList("Saleordermge.queryChldModelByFkId", params);
    }
    
    

    /**
     * * 根据子表Id批量加载子表信息
     * @param SALE_ORDER_DTL_IDs the SALE_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <SaleorderspModelChld> loadChilds(Map <String, String> params) {
       return findList("Saleordermge.loadChldByIds",params);
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
       update("Saleordermge.deleteChldByIds", params);
    }
    
    /**
     * 转标准订单
     * @param SALE_ORDER_ID 销售订单ID
     * @param SALE_ORDER_DTL_IDS 选择明细
     * @param isAll 是否全部明细 true->是
     */
    public void txConvertTechOrder(String SALE_ORDER_ID,String SALE_ORDER_DTL_IDS,boolean isAll,UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
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
		params.put("SALE_ORDER_ID", StringUtil.uuid32len());//新增订单
		params.put("SALE_ORDER_ID_OLD",SALE_ORDER_ID);
		params.put("BILL_TYPE", BusinessConsts.STANDARD);//标准
		params.put("IS_NO_STAND_FLAG",BusinessConsts.INTEGER_1);//1 是否非标
		//转标准订单的时候 新增 订单
		insert("Saleordermge.insertBZOrder", params);
		
		//新增 明细
		params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDS);
		params.put("IS_NO_STAND_FLAG",BusinessConsts.INTEGER_0);// 0 是否非标
		insert("Saleordermge.insertBZOrderDtl", params);
		
		//删除 非标订单的明细
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);//1
		delete("Saleordermge.deleteChldByIds",params);
		
		
		//如果是 全部的 明细 都转成 标准订单 那么则删除 相应的工艺单和相应的非标订单
		if(isAll){
			//删除工艺单
			params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
			params.put("SALE_ORDER_ID",SALE_ORDER_ID);//原始订单
		 
			this.delete("Saleordermge.deleteTechChilById", params);
			this.delete("Saleordermge.deleteTechById", params);
			
			//删除 非标订单明细
			delete("Saleordermge.deleteChldByIds",params);
			//删除 非标订单
			params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			delete("Saleordermge.delete",params);
			
		}
		
    }
    
    /**
     * 取消预定
     * @param SALE_ORDER_DTL_IDS
     */
    public void calcelSaleOrder(String SALE_ORDER_DTL_IDS){
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_1);//1
    	params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDS);//1
    	
    	this.update("Saleordermge.updateChldByIds", params);
    }
    
    /**
     * 恢复预定
     * @param SALE_ORDER_ID 销售订单ID
     * @param SALE_ORDER_DTL_ID  明细ID
     */
    public void recoverOrder(String SALE_ORDER_ID,String SALE_ORDER_DTL_ID){
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);//0
    	params.put("SALE_ORDER_DTL_IDS","'"+SALE_ORDER_DTL_ID+"'");
    	this.update("Saleordermge.updateChldByIds", params);
    }
    
    /**
     * 查询 工艺单 状态
     * @param SALE_ORDER_ID 销售订单ID
     * @return
     */
    public String queryTechState(String SALE_ORDER_ID){
    	
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//0
    	params.put("SALE_ORDER_ID",SALE_ORDER_ID);
    	String result =  (String) this.load("Saleordermge.queryTechState", params);
    	return result;
    }
    
    /**
     * 取消预定
     * @param SALE_ORDER_DTL_ID 订单ID
     * @param BILL_TYPE 单据类型
     * @param SALE_ORDER_DTL_IDS 明细IDs
     * @param FROM_BILL_DTL_IDS 来源单据IDS
     */
    public void txCalcelSaleOrder(String SALE_ORDER_ID,String BILL_TYPE, String SALE_ORDER_DTL_IDS,String FROM_BILL_DTL_IDS , List <SaleorderspModelChld> chldModelList,UserBean userBean)throws Exception{
    	for(int i=0;i<chldModelList.size();i++){
    		SaleorderspModelChld childMode = chldModelList.get(i);
    		String SALE_ORDER_DTL_ID = childMode.getSALE_ORDER_DTL_ID();
    		String FROM_BILL_DTL_ID = childMode.getFROM_BILL_DTL_ID();
    		String ORDER_NUM = childMode.getORDER_NUM();
    		String OLD_ORDER_NUM = childMode.getOLD_ORDER_NUM();
    		if(OLD_ORDER_NUM==null||"".equals(OLD_ORDER_NUM)){
    			OLD_ORDER_NUM =ORDER_NUM;
    		}
    		String CANCEL_NUM = childMode.getCANCEL_NUM();
    		if(CANCEL_NUM==null ||"".equals(CANCEL_NUM)){
    			CANCEL_NUM = "0";
    		}
    		String REBATE_PRICE = childMode.getREBATE_PRICE(); 
    		if(REBATE_PRICE==null||"".equals(REBATE_PRICE)){
    			REBATE_PRICE = "0";
    		}
    		float iREBATE_PRICE = Float.parseFloat(REBATE_PRICE);
    		String DECT_PRICE = childMode.getDECT_PRICE();//取折后单价
    		float iPrice = Float.parseFloat(DECT_PRICE);
    		int iNewsOrderNum = Integer.parseInt(ORDER_NUM)-Integer.parseInt(CANCEL_NUM);
    		float orderAmount = iPrice*iNewsOrderNum;
    		float REBATE_AMOUNT = iREBATE_PRICE*iNewsOrderNum;
    		String NewsORDER_NUM = String.valueOf(iNewsOrderNum);
    		Map<String,String> params = new HashMap<String,String>();
    		//该条明细全部取消的时候 取消标记置为1
    		if(0 == iNewsOrderNum){
    			params.put("CANCEL_FLAG",BusinessConsts.INTEGER_1);
    		}
    		params.put("SALE_ORDER_DTL_ID",SALE_ORDER_DTL_ID);
        	params.put("ORDER_NUM",NewsORDER_NUM);
        	params.put("IS_CANCELED_FLAG",BusinessConsts.INTEGER_1);//是否取消过标记
        	
        	params.put("ORDER_AMOUNT",String.valueOf(orderAmount));
        	params.put("REBATE_AMOUNT",String.valueOf(REBATE_AMOUNT));
        	this.update("Saleorder.updateChldById", params);
        	params.put("GOODS_ORDER_DTL_ID",FROM_BILL_DTL_ID);
        	params.put("OLD_ORDER_NUM",String.valueOf(OLD_ORDER_NUM));
        	this.update("Saleorder.updateGoodsOrderChldById", params);
            if("非标".equals(BILL_TYPE) && 0 == iNewsOrderNum){
            	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
    			params.put("SALE_ORDER_DTL_IDS","'"+SALE_ORDER_DTL_ID+"'");//来源明细ID
    		    //删除工艺单明细
    		    delete("Saleordersp.deleteTechChilById", params);
    		}
            String strSALE_ORDER_DTL_ID = "'"+SALE_ORDER_DTL_ID+"'";
        	LogicUtil.updFreezeCreditByCanleOrder(BusinessConsts.ACCT_SALE_ORDER3_CONF_ID,SALE_ORDER_ID, strSALE_ORDER_DTL_ID,ORDER_NUM);//按原订货订单数回信用  减
        	LogicUtil.inertSaleCancelCreditJournal(strSALE_ORDER_DTL_ID, "1",ORDER_NUM);
        	LogicUtil.updFreezeCredit(BusinessConsts.ACCT_SALE_ORDER_CONF_ID,SALE_ORDER_ID, strSALE_ORDER_DTL_ID); //按更改后的订货数量冻结信用 加
        	LogicUtil.inertSaleCancelCreditJournal(strSALE_ORDER_DTL_ID, "0",null);
        	LogicUtil.insRebateJournal(BusinessConsts.FL_SALE_ORDER_CONF_ID, SALE_ORDER_ID,SALE_ORDER_DTL_IDS);
        	
    	}
    	

    	
    	//如果该订单下 没有正常状态的明细 那么该订单状态更新为‘取消预订’
    	Map<String,String> params = new HashMap<String,String>();
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
    		
    		if("非标".equals(BILL_TYPE)){
            	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
    		    //删除工艺单
    		    delete("Saleordersp.deleteTechById", params);
    		}
    		
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
     * 明细保存，保存是否备货和备注
     * @param modelList
     */
    public void txChildSave(List <SaleorderspModelChld> modelList){
    	//修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (SaleorderspModelChld model : modelList) {
        	if("关闭".equals(model.getSTATE())){
        		continue;
        	}
            Map <String, String> params = new HashMap <String, String>();
            params.put("ADVC_SEND_DATE", model.getADVC_SEND_DATE());
            params.put("SALE_ORDER_DTL_ID", model.getSALE_ORDER_DTL_ID());
            params.put("IS_BACKUP_FLAG", model.getIS_BACKUP_FLAG());
            params.put("REMARK", model.getREMARK());
            params.put("NEW_COLOR", model.getNEW_COLOR());
            params.put("NEW_SPEC", model.getNEW_SPEC());
            params.put("PRODUCT_REMARK", model.getPRODUCT_REMARK());
            params.put("PRDC_POINT_ID", model.getPRDC_POINT_ID());
            params.put("PRDC_POINT_NAME", model.getPRDC_POINT_NAME());
            updateList.add(params);
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Saleordermge.updateChldById", updateList);
            this.batch4Update("Saleordermge.updateGoodsChldById", updateList);
        }
    }
    
    
    public List <Map> qrySaleOrderExp(Map params){
    	return findList("Saleordermge.qrySaleOrderExp",params);
    }
    
    
    /**
     * 反审核
     * @param SALE_ORDER_ID 销售订单ID
     * @param BILL_TYPE 单据类型
     * @param FROM_BILL_ID 来源单据ID
     * @param userBean
     */
    public void txOpposeAudit(String SALE_ORDER_ID,String BILL_TYPE,String FROM_BILL_ID,UserBean userBean){
    	//销售订单为弃审，订货订单未未处理
    	 Map <String, String> params = new HashMap <String, String>();
    	 params.put("FROM_BILL_ID", FROM_BILL_ID);
    	 params.put("STATE_PARAMS", "'审核通过','未提交','提交','待核价','已核价'");
    	 List<Map<String,String>> opposeList =  this.findList("Saleordermge.queryIsOpposeBill", params);
    	 if(null != opposeList && !opposeList.isEmpty()){
    		 String BILL_NO = "";
    		 String STATE_TEXT = "";
    		 for(Map<String,String> map : opposeList){
    			 BILL_NO += "["+map.get("SALE_ORDER_NO")+"]";
    			 STATE_TEXT += "["+map.get("STATE")+"]";
    		 }
    		 throw new ServiceException("相关的 "+BILL_NO+"订单处于"+STATE_TEXT+"状态不能反审核");
    	 }
    	 
    	//更新销售订单
    	params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("UPD_TIME","sysdate");
		params.put("SALE_ORDER_ID", SALE_ORDER_ID);
		params.put("DEL_FLAG",BusinessConsts.INTEGER_1);
		//根据主表的来源单据ID更新
		this.update("Saleordermge.updateSaleOrderByFromId", params);
		//根据主表的来源单据ID更新
		this.delete("Saleordermge.updateChldByFormFkId", params);
		inertSaleordertrace(params,userBean);
		if("非标".equals(BILL_TYPE)){
			//删除对应的工艺单和工艺单明细
			this.delete("Saleordermge.deleteTechById", params);
			this.delete("Saleordermge.deleteTechChilById", params);
		}
		//更新订货订单未处理
		params.put("STATE","未处理");
		params.put("GOODS_ORDER_ID", FROM_BILL_ID);
		this.update("Saleordermge.updateGoodsOrderById", params);
		params.put("STATE","弃审");
		insertGoodorderTack(params,userBean);
    }
    
    
    /**
     * 提交生产
     * @param SALE_ORDER_ID
     * @param userBean
     */
    public String txCommitProduction(String SALE_ORDER_ID,String SALE_ORDER_NO,UserBean userBean,String strJsonData,String AppCode,String UId,String OPRCODE,List<SaleorderspModelChld> modelList){
    	 String strMessage = "操作成功";
    	 Map <String, String> params = new HashMap <String, String>();
    	 params.put("UPD_NAME",userBean.getXM());
 	     params.put("UPDATOR",userBean.getXTYHID());
 	     params.put("STATE","待发货");
 	     params.put("UPD_TIME","sysdate");
 		 params.put("SALE_ORDER_ID", SALE_ORDER_ID);
 		 txUpdateById(params);
 		 /********************处理接口*********************/
     	if("true".equals(Consts.INVOKE_SYS_FLG)){
    		ArrayList bodyList = new ArrayList();
    		String strResult = null;
    		try{
    			String returnJsonData = InterfaceInvokeUtil.createSO(strJsonData); 
    			LogicUtil.actLog("销售订单管理", "销售订单接口返回值", "U9", "成功",returnJsonData,AppCode,UId,OPRCODE,SALE_ORDER_NO);
    			JesonImplUtil jsUtil = new JesonImplUtil(true,returnJsonData);
    			HashMap headMap =  jsUtil.getMbHead();
    			Map rspMap = jsUtil.getResponse();
    			bodyList = jsUtil.getMbBody();
    			HashMap bodyMap = (HashMap)bodyList.get(0);
    			strResult = new Gson().toJson(bodyMap);
    		}catch(Exception ex){
    			throw new ServiceException("调用ESB接口失败!");
    		}
    		Map<String, String> resMap = new Gson().fromJson(strResult, new TypeToken<Map<String, String>>() {}.getType());
    		if(resMap!=null){
     			String stats = resMap.get("IsOK");
     			if("false".equals(stats)){
     				StringBuffer buf = new StringBuffer();
     				buf.append("操作失败!");
     				String Msg = resMap.get("Msg");
     				buf.append("错误信息:"+Msg);
     				throw new ServiceException(buf.toString());
     			}
     		}else{
     			strMessage = "调用接口失败,查看LOG日志";
     			throw new ServiceException(strMessage);
     		}
    		updateSaleOrderDtlU9Info(bodyList);
    	}
     	return strMessage;
    }
    
    /**把U9返回的销售订单ID和明细ID返填到DM销售订单
     * @param bodyList
     */
    private void updateSaleOrderDtlU9Info(ArrayList bodyList){
    	for(int i=0;bodyList!=null && i<bodyList.size();i++){
    		HashMap bodyMap = (HashMap)bodyList.get(i);
    		if(bodyMap!=null){
    			String SODocNo = (String)bodyMap.get("SODocNo");
        		String SOLineID = (String)bodyMap.get("SOLineID");
        		String PlanDocLineID = (String)bodyMap.get("PlanDocLineID");
        		HashMap param = new HashMap();
        		param.put("U9_SALE_ORDER_NO", SODocNo);
        		param.put("U9_SALE_ORDER_DTL_NO", SOLineID);
        		param.put("SALE_ORDER_DTL_ID", PlanDocLineID);
        		update("Saleordermge.updateU9Id",param);
    		}
    	}
    }
    
    /**
     * 撤销生产
     * @param SALE_ORDER_ID
     * @param userBean
     */
    public void txToRevoke(String SALE_ORDER_ID,String SALE_ORDER_NO,UserBean userBean){
    	Map <String, String> params = new HashMap <String, String>();
   	    params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("STATE","审核通过");
	    params.put("UPD_TIME","sysdate");
		params.put("SALE_ORDER_ID", SALE_ORDER_ID);
		txUpdateById(params);
		//将明细的U9订单编号 清空
		params.put("U9_SALE_ORDER_NO", "");
		params.put("U9_SALE_ORDER_DTL_NO", "");
		update("Saleordermge.updateChldByFKId",params);
		
    	if("true".equals(Consts.INVOKE_SYS_FLG)){
    		ArrayList bodyList = new ArrayList();
    		String strResult = null;
    		String returnJsonData = null;
    		String UId = StringUtil.uuid32len();
    		try{
    			String strJsonData = InterfaceInvokeUtil.getStrCreateSO(SALE_ORDER_ID,"1",UId);
    			LogicUtil.actLog("销售订单管理", "调入销售订单整单撤销", "U9系统", "成功",strJsonData,"DM",UId,"TC0300010:createSO");
    			returnJsonData = InterfaceInvokeUtil.createSO(strJsonData); 
    			JesonImplUtil jsUtil = new JesonImplUtil(true,returnJsonData);
    			HashMap headMap =  jsUtil.getMbHead();
    			Map rspMap = jsUtil.getResponse();
    			bodyList = jsUtil.getMbBody();
    			HashMap bodyMap = (HashMap)bodyList.get(0);
    			strResult = new Gson().toJson(bodyMap);
    		}catch(Exception ex){
    			throw new ServiceException(ex.getMessage());
    		}
    		Map<String, String> resMap = new Gson().fromJson(strResult, new TypeToken<Map<String, String>>() {}.getType());
    		if(resMap!=null){
     			String stats = resMap.get("IsOK");
     			if("false".equals(stats)){
     				StringBuffer buf = new StringBuffer();
     				buf.append("操作失败!");
     				String Msg = resMap.get("Msg");
     				buf.append("错误信息:"+Msg);
     				throw new ServiceException(buf.toString());
     			}
     			LogicUtil.actLog("销售订单管理", "销售订单整单撤销成功", "U9系统", "成功","["+strResult+"]"+returnJsonData,"DM",UId,"TC0300010:createSO");
     		}else{
     			String strMessage = "调用接口失败,查看LOG日志";
     			throw new ServiceException(strMessage);
     		}
    	}
    }
    
    
    
    /**
	 * 订单订单生命周期
	 * 
	 * @param GOODS_ORDER_ID
	 * @param GOODS_ORDER_NO
	 * @param userBean
	 */
	private void insertGoodorderTack(Map<String,String>model, UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		String GOODS_ORDER_TRACE_ID = StringUtil.uuid32len();
		params.put("GOODS_ORDER_TRACE_ID", GOODS_ORDER_TRACE_ID);
		params.put("GOODS_ORDER_ID", model.get("GOODS_ORDER_ID"));
 		params.put("ACTION_NAME", "订货订单处理");// 订货订单处理
		params.put("TRACE_URL", BusinessConsts.ORDER_BILL_ACTION_TRACE_URL+ model.get("GOODS_ORDER_ID"));
		params.put("DEAL_PSON_NAME", userBean.getXM());
		params.put("STATE",model.get("STATE"));
//		if (StringUtil.isEmpty(model.get("GOODS_ORDER_NO"))) {
////			insert("Goodsorder.mergeInsertOrderTrack", params);
//		} else {
			insert("Saleordermge.insertGoodOrderTrack", params);
//		}

	}
	
	/**
	 * 插入销售订单生命周期
	 * @param parent 主表字段信息
	 * @param userBean
	 * 
	 */
	public void inertSaleordertrace(Map<String, String> parent, UserBean userBean) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("GOODS_ORDER_TRACE_ID", StringUtil.uuid32len());
		paramMap.put("SALE_ORDER_ID", parent.get("SALE_ORDER_ID"));
		paramMap.put("DEAL_PSON_NAME", userBean.getXM());
		paramMap.put("DEAL_TIME", BusinessConsts.UPDATE_TIME);
 		paramMap.put("STATE",parent.get("STATE"));
		
		paramMap.put("BILL_TYPE", "销售订单处理");
		paramMap.put("ACTION_NAME", "销售订单弃审 ");
	 
		this.insert("Saleordermge.insertSaleOrderTrack", paramMap);
	}
	
	
	   /**
     * 行关闭
     * @param SALE_ORDER_ID 主表id
     * @param SALE_ORDER_DTL_IDS mxids
     * @param userBean
     */
    public void txCloseOrder(String SALE_ORDER_ID,String BILL_TYPE,String SALE_ORDER_DTL_IDS,UserBean userBean)throws Exception{
    	if(!checkIsFreeOrder(SALE_ORDER_ID)){
        	try{
    	    	CreditCtrlUtil.updateFreezeCreditForSaleClose(SALE_ORDER_ID,SALE_ORDER_DTL_IDS);
    	    	LogicUtil.updateRebate(BusinessConsts.FL_SALE_ORDER_CONF_ID, SALE_ORDER_ID,SALE_ORDER_DTL_IDS);  //按订货订单数回反利额  减
    	    	LogicUtil.insRebateJournal(BusinessConsts.FL_SALE_ORDER_CONF_ID, SALE_ORDER_ID,SALE_ORDER_DTL_IDS);
        	}catch(Exception ex){
        		throw new ServerException("释放冻结信用出错!");
        	}   		
    	}

    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("SALE_ORDER_ID", SALE_ORDER_ID);
    	paramMap.put("STATE", "关闭");
    	Map<String,String> model = this.load(SALE_ORDER_ID);
		paramMap.put("SALE_ORDER_DTL_IDS", SALE_ORDER_DTL_IDS);
		//行关闭更新取消数量
		this.update("Saleordermge.updateChldCancelNumById", paramMap);
		//行关闭更新订货数量
		this.update("Saleordermge.updateChldOrderNumById", paramMap);
		//更新订货订单明细的行状态CANCEL_FLAG=1
		paramMap.put("CANCEL_FLAG", BusinessConsts.INTEGER_1);
		this.update("Saleordermge.updateGoodsOrderDtlById", paramMap);
		if("非标".equals(BILL_TYPE)){
			//更新工艺单明细
			paramMap.put("TECH_DEL_FLAG", BusinessConsts.INTEGER_1);
			this.update("Saleordermge.deleteTechChilBySaleDtlId", paramMap);
		}
		
		
		//查询明细正常状态的条数，如果为0则更新主表为关闭
		paramMap.put("STATE", "正常");
		int closeCount = 100;
		List<Map<String,Object>> stateList = this.findList("Saleordermge.queryChildNomerCount", paramMap);
		if(null != stateList){
			int size = stateList.size();
			if(size == 1 && "关闭".equals(StringUtil.nullToSring(stateList.get(0).get("STATE")))){
				closeCount = 0;
			}
			  
		}
		if(closeCount == 0){
			paramMap.put("UPD_NAME",userBean.getXM());
			paramMap.put("UPDATOR",userBean.getXTYHID());
			paramMap.put("STATE","关闭");
			paramMap.put("UPD_TIME","sysdate");
			paramMap.put("SALE_ORDER_ID", SALE_ORDER_ID);
			txUpdateById(paramMap);
			if("非标".equals(BILL_TYPE)){
				//更新工艺单主表
				this.update("Saleordermge.deleteTechById", paramMap);
			}
			
			
		}
    	if("true".equals(Consts.INVOKE_SYS_FLG)&&(BusinessConsts.STATE_WAIT_SEND.equals(model.get("STATE"))||BusinessConsts.HAVE_SEND_GOODS.equals(model.get("STATE")))){
        		ArrayList bodyList = new ArrayList();
        		String strResult = null;
        		String returnJsonData = null;
        		String UId = StringUtil.uuid32len();
        		try{
        			String strJsonData = InterfaceInvokeUtil.getStrCloseCreateSO(SALE_ORDER_ID,"0",SALE_ORDER_DTL_IDS,UId);
        			LogicUtil.actLog("销售订单管理", "调入销售订单行关闭", "U9系统", "成功","["+strResult+"]"+strJsonData,"DM",UId,"TC0300010:createSO");
        			returnJsonData = InterfaceInvokeUtil.createSO(strJsonData); 
        			JesonImplUtil jsUtil = new JesonImplUtil(true,returnJsonData);
        			HashMap headMap =  jsUtil.getMbHead();
        			Map rspMap = jsUtil.getResponse();
        			bodyList = jsUtil.getMbBody();
        			HashMap bodyMap = (HashMap)bodyList.get(0);
        			strResult = new Gson().toJson(bodyMap);
        		}catch(Exception ex){
        			throw new ServiceException("调用ESB接口失败!");
        		}
        		Map<String, String> resMap = new Gson().fromJson(strResult, new TypeToken<Map<String, String>>() {}.getType());
        		if(resMap!=null){
         			String stats = resMap.get("IsOK");
         			if("false".equals(stats)){
         				StringBuffer buf = new StringBuffer();
         				buf.append("操作失败!");
         				String Msg = resMap.get("Msg");
         				buf.append("错误信息:"+Msg);
         				throw new ServiceException(buf.toString());
         			}
         			LogicUtil.actLog("销售订单管理", "销售订单行关闭成功", "U9系统", "成功","["+strResult+"]"+returnJsonData,"DM",UId,"TC0300010:createSO");
         		}else{
         			String strMessage = "调用接口失败,查看LOG日志";
         			throw new ServiceException(strMessage);
         		}
    	}
    	//行关闭清除明细的冻结单价
    	this.update("Saleordermge.updateChldOrderFreePriceById", paramMap);
    	updateSaleOrder(SALE_ORDER_ID);
		
    }
    
    /**检查是否是赠品订单
     * @param saleOrderId
     * @return
     */
    private boolean checkIsFreeOrder(String SALE_ORDER_ID){
    	Map<String,String> model = this.load(SALE_ORDER_ID);
    	String BILL_TYPE = (String)model.get("BILL_TYPE");
    	if("赠品订货".equals(BILL_TYPE)){
    		return true;
    	}
    	return false;
    }
    
    
    
    /**
     * 强制关闭
     * @param SALE_ORDER_ID 主表id
     * @param SALE_ORDER_DTL_IDS mxids
     * @param userBean
     */
    public void txforceCloseOrder(String SALE_ORDER_ID,String BILL_TYPE,String SALE_ORDER_DTL_IDS,UserBean userBean)throws Exception{
    	if(!checkIsFreeOrder(SALE_ORDER_ID)){
	    	try{
				CreditCtrlUtil.updateFreezeCreditForSaleClose(SALE_ORDER_ID,SALE_ORDER_DTL_IDS);
		    	LogicUtil.updateRebate(BusinessConsts.FL_SALE_ORDER_CONF_ID, SALE_ORDER_ID,SALE_ORDER_DTL_IDS);  //按订货订单数回反利额  减
		    	LogicUtil.insRebateJournal(BusinessConsts.FL_SALE_ORDER_CONF_ID, SALE_ORDER_ID,SALE_ORDER_DTL_IDS);
			}catch(Exception ex){
				throw new ServerException("释放冻结信用出错!");
			}
    	}
		Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("SALE_ORDER_ID", SALE_ORDER_ID);
    	paramMap.put("STATE", "关闭");
    	Map<String,String> model = this.load(SALE_ORDER_ID);
		paramMap.put("SALE_ORDER_DTL_IDS", SALE_ORDER_DTL_IDS);
		//行关闭更新取消数量
		this.update("Saleordermge.updateChldCancelNumById", paramMap);
		//行关闭更新订货数量
		this.update("Saleordermge.updateChldOrderNumById", paramMap);
		//更新订货订单明细的行状态CANCEL_FLAG=1
		paramMap.put("CANCEL_FLAG", BusinessConsts.INTEGER_1);
		this.update("Saleordermge.updateGoodsOrderDtlById", paramMap);
		
		if("非标".equals(BILL_TYPE)){
			//更新工艺单明细
			paramMap.put("TECH_DEL_FLAG", BusinessConsts.INTEGER_1);
			this.update("Saleordermge.deleteTechChilBySaleDtlId", paramMap);
		}
		
		//查询明细正常状态的条数，如果为0则更新主表为关闭
		paramMap.put("STATE", "正常");
		int closeCount = 100;
		List<Map<String,Object>> stateList = this.findList("Saleordermge.queryChildNomerCount", paramMap);
		if(null != stateList){
			int size = stateList.size();
			if(size == 1 && "关闭".equals(StringUtil.nullToSring(stateList.get(0).get("STATE")))){
				closeCount = 0;
			}
			  
		}
		if(closeCount == 0){
			paramMap.put("UPD_NAME",userBean.getXM());
			paramMap.put("UPDATOR",userBean.getXTYHID());
			paramMap.put("STATE","关闭");
			paramMap.put("UPD_TIME","sysdate");
			paramMap.put("SALE_ORDER_ID", SALE_ORDER_ID);
			txUpdateById(paramMap);
			if("非标".equals(BILL_TYPE)){
				//更新工艺单主表
				this.update("Saleordermge.deleteTechById", paramMap);
			}
		}
		//调用接口
    	if("true".equals(Consts.INVOKE_SYS_FLG)&&(BusinessConsts.STATE_WAIT_SEND.equals(model.get("STATE"))||BusinessConsts.HAVE_SEND_GOODS.equals(model.get("STATE")))){
        		ArrayList bodyList = new ArrayList();
        		String strResult = null;
        		String UId = StringUtil.uuid32len();
        		String returnJsonData = null;
        		try{
        			String strJsonData = InterfaceInvokeUtil.getStrCloseCreateSO(SALE_ORDER_ID,"2",SALE_ORDER_DTL_IDS,UId);
        			LogicUtil.actLog("销售订单管理", "调入销售订单行强制关闭", "U9系统", "成功","["+strResult+"]"+strJsonData,"DM",UId,"TC0300010:createSO");
        			returnJsonData = InterfaceInvokeUtil.createSO(strJsonData); 
        			JesonImplUtil jsUtil = new JesonImplUtil(true,returnJsonData);
        			HashMap headMap =  jsUtil.getMbHead();
        			Map rspMap = jsUtil.getResponse();
        			bodyList = jsUtil.getMbBody();
        			HashMap bodyMap = (HashMap)bodyList.get(0);
        			strResult = new Gson().toJson(bodyMap);
        		}catch(Exception ex){
        			throw new ServiceException("调用ESB接口失败!");
        		}
        		Map<String, String> resMap = new Gson().fromJson(strResult, new TypeToken<Map<String, String>>() {}.getType());
        		if(resMap!=null){
         			String stats = resMap.get("IsOK");
         			if("false".equals(stats)){
         				StringBuffer buf = new StringBuffer();
         				buf.append("操作失败!");
         				String Msg = resMap.get("Msg");
         				buf.append("错误信息:"+Msg);
         				throw new ServiceException(buf.toString());
         			}
         			LogicUtil.actLog("销售订单管理", "销售订单行强制关闭成功", "U9系统", "成功","["+strResult+"]"+returnJsonData,"DM",UId,"TC0300010:createSO");
         		}else{
         			String strMessage = "调用接口失败,查看LOG日志";
         			throw new ServiceException(strMessage);
         		}
    	}
    	
    	//行关闭清除明细的冻结单价
    	this.update("Saleordermge.updateChldOrderFreePriceById", paramMap);
    	updateSaleOrder(SALE_ORDER_ID);
    }
	
    
    public List<Map<String,Object>> queryStorePrdDtl(Map<String, String> params){
//    	Map<String, String> paramMap = new HashMap<String, String>();
//    	paramMap.put("SALE_ORDER_ID", SALE_ORDER_ID);
//    	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	return this.findList("Saleordermge.queryStorePrdDtl", params);
    }
    
    
	 /**
     * 生产状态查询
     * @param params
     * @return
     */
    public List queryProStatus(Map<String,String> params){
		List rst = null;
		//交付计划单单号
		String deliverPlanNo = (String) params.get("DeliverPlanNo");
		if(StringUtil.isEmpty(deliverPlanNo)){
			return null;
		}
		if ("true".equals(Consts.INVOKE_SYS_FLG)) {			
			try {
				String jsonStr = LogicUtil.queryProStatus(deliverPlanNo);
				rst = new Gson().fromJson(jsonStr,
						new TypeToken<ArrayList<ProStatus>>() {
						}.getType());
			} catch (Exception e) {
				rst = getErrorData();
			}
		} else {
			rst = getTestDataModel(deliverPlanNo);
		}
		
		if(rst == null){
			rst = new ArrayList();
		}
				
		return formatList(deliverPlanNo, rst);
	}
    
    
    private List getErrorData(){
		List rst = new  ArrayList();
		ProStatus temp = new ProStatus();
		temp.setStatus("false");
		temp.setDesc("查询失败！");
		rst.add(temp);
		return rst;
	}
	private List getTestDataModel(String deliverPlanNo){
		List rst = new  ArrayList();
		
		for(int i = 0; i < 50; i++){				
			String temp = "test"+deliverPlanNo + Math.round(Math.random()* 100);		
			rst.add(getModel(temp));
		}
		
		return rst;
	}
	
	private ProStatus getModel(String temp){
		ProStatus model = new ProStatus();
		model.setSaleOrderNo(temp);
		model.setCustNo(temp);
		model.setCustName(temp);
		model.setPrdNo(temp);
		model.setPrdName(temp);
		model.setPrdSpec(temp);
		model.setPLAN_NUM(null);
		model.setStroeInNum(null);
		model.setStoreNum(null);
		
		return model;
	}
	

	private List formatList(String deliverPlanNo, List<ProStatus> source){
		
		List rst = null;
		if(source != null && source.size() > 0){
			rst = new ArrayList();
			Map<String,String> data = null;
			for(ProStatus model : source){
				data = new HashMap();
				String DELIVER_ORDER_DTL_ID = model.getDELIVER_ORDER_DTL_ID();
				Float f_orderNum = 0.00f;
				int I_IS_BACKUP_FLAG;
				Float backNum = 0.00f ;
				Map map =  LogicUtil.getOrderNumBydeliverId(DELIVER_ORDER_DTL_ID);
				if (map != null && map.size() > 0) {
					BigDecimal ADVC_PLAN_NUM = (BigDecimal)map.get("ADVC_PLAN_NUM");
					f_orderNum = ADVC_PLAN_NUM.floatValue();
					BigDecimal IS_BACKUP_FLAG = (BigDecimal)map.get("IS_BACKUP_FLAG");
					I_IS_BACKUP_FLAG = IS_BACKUP_FLAG.intValue();
					if(1 == I_IS_BACKUP_FLAG){
						backNum = f_orderNum;
					}
				}
				data.put("DeliverOrderNo", deliverPlanNo);
				data.put("SaleOrderNo", model.getSaleOrderNo());
				data.put("CustNo", model.getCustNo());
				data.put("CustName", model.getCustName());
				data.put("PrdNo", model.getPrdNo());
				data.put("PrdName", model.getPrdName());
				data.put("PrdSpec", model.getPrdSpec());
				data.put("PlanNum", String.valueOf(model.getPLAN_NUM()));
				data.put("StroeInNum", String.valueOf(model.getStroeInNum()));
				data.put("StoreNum", String.valueOf(backNum));
				data.put("WorkNum", String.valueOf(f_orderNum - backNum - model.getStroeInNum()));
				data.put("Status", model.getStatus());
				data.put("Desc", model.getDesc());
				data.put("LEDGER_NAME", model.getLEDGER_NAME());
				rst.add(data);
			}
			
		}
		
		return rst;
	}
	
	/**
	 * 加载渠道
	 * @param param CHANN_ID
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> loadChann(String CHANN_ID){
		Map<String,String> params = new HashMap<String,String>();
		params.put("CHANN_ID",CHANN_ID);
		return (Map<String, Object>) load("Shopcar.getChannCreDit", params);
	}
	//查询未出货订单
	public List noSendExport(Map<String,String> params){
		return this.findList("Saleordermge.noSendExport", params);
	}
	/**
	 * 根据销售订单ID获取明细并且默认交期
	 * @param DELIVER_ORDER_ID
	 * @return
	 */
	public List getChldAdvcDtat(String SALE_ORDER_ID){
		return this.findList("Saleordermge.getChldAdvcDtat", SALE_ORDER_ID);
	}
	public void upChldAdvcData(List<SaleorderspModelChld> modelList){
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		for (SaleorderspModelChld model : modelList) {
			Map<String,String> map=new HashMap<String, String>();
			map.put("SALE_ORDER_DTL_ID", model.getSALE_ORDER_DTL_ID());
			map.put("ADVC_SEND_DATE", model.getADVC_SEND_DATE());
			updateList.add(map);
		}
		if (!updateList.isEmpty()) {
			this.batch4Update("Saleordermge.upChldAdvcData", updateList);
		}
	}
	/**
	 * 更新销单主表状态
	 * 
	 * @param deliverOderNo
	 * @param TRUCK_NO
	 * @param STOREOUT_TIME
	 * @param IS_SEND_FIN
	 * @return
	 * @throws Exception
	 */
	public void updateSaleOrder(String SALE_ORDER_ID)  {
		boolean flag=false;
		Map<String,String> map=new HashMap<String, String>();
		map.put("SALE_ORDER_ID", SALE_ORDER_ID);
		map.put("DTLSTATE", BusinessConsts.COMMON_COLSE);
		Map<String,String> params=(Map<String, String>) this.load("Saleordermge.getSaleOrderInfo",map);
		if(null==params){
			map.put("STATE", BusinessConsts.COMMON_COLSE);
			flag=true;
		}else{
			String ORDER_NUM=String.valueOf(params.get("ORDER_NUM"));
			String SENDED_NUM=String.valueOf(params.get("SENDED_NUM"));
			if("0".equals(ORDER_NUM)&&"0".equals(SENDED_NUM)){
				map.put("STATE", BusinessConsts.COMMON_COLSE);
				flag=true;
			}else if(ORDER_NUM.equals(SENDED_NUM)){
				map.put("STATE", BusinessConsts.HAVE_SEND_GOODS);
				flag=true;
			}
		}
		if(flag){
			this.update("Saleordermge.updateSaleOrderState", map);
		}
		
	}
}