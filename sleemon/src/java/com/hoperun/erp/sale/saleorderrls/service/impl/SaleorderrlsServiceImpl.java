/**
 * prjName:喜临门营销平台
 * ucName:标准订单
 * fileName:SaleorderServiceImpl
*/
package com.hoperun.erp.sale.saleorderrls.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.saleorderrls.service.SaleorderrlsService;
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
public class SaleorderrlsServiceImpl extends BaseService implements SaleorderrlsService {
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
		return this.pageQuery("Saleorderrls.pageQuery", "Saleorderrls.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Saleorderrls.insert", params);
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
         update("Saleorderrls.delete", params);
		 //删除子表 
		 return update("Saleorderrls.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Saleorderrls.updateById", params);
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
		return (Map<String,String>) load("Saleorderrls.loadById", param);
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
            this.batch4Update("Saleorderrls.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Saleorderrls.insertChld", addList);
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
    public List <SaleorderspModelChld> childQuery(String SALE_ORDER_ID) {
        Map params = new HashMap();
        params.put("SALE_ORDER_ID", SALE_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//查询 未取消预定的 0 正常
//		params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
		
        return this.findList("Saleorderrls.queryChldByFkId", params);
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
       return findList("Saleorderrls.loadChldByIds",params);
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
       update("Saleorderrls.deleteChldByIds", params);
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
		insert("Saleorderrls.insertBZOrder", params);
		
		//新增 明细
		params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDS);
		params.put("IS_NO_STAND_FLAG",BusinessConsts.INTEGER_0);// 0 是否非标
		insert("Saleorderrls.insertBZOrderDtl", params);
		
		//删除 非标订单的明细
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);//1
		delete("Saleorderrls.deleteChldByIds",params);
		
		
		//如果是 全部的 明细 都转成 标准订单 那么则删除 相应的工艺单和相应的非标订单
		if(isAll){
			//删除工艺单
			params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
			params.put("SALE_ORDER_ID",SALE_ORDER_ID);//原始订单
		 
			this.delete("Saleorderrls.deleteTechChilById", params);
			this.delete("Saleorderrls.deleteTechById", params);
			
			//删除 非标订单明细
			delete("Saleorderrls.deleteChldByIds",params);
			//删除 非标订单
			params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			delete("Saleorderrls.delete",params);
			
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
    	
    	this.update("Saleorderrls.updateChldByIds", params);
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
    	this.update("Saleorderrls.updateChldByIds", params);
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
    	String result =  (String) this.load("Saleorderrls.queryTechState", params);
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
    		//取消数量是0的不更新相关的数据
    		if("0".equals(CANCEL_NUM)){
    			continue;
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
        	
        	LogicUtil.updateRebateByNum(BusinessConsts.FL_SALE_ORDER_CONF_ID, SALE_ORDER_ID,strSALE_ORDER_DTL_ID,CANCEL_NUM);  //按取消数量加返利
        	LogicUtil.insRebateJournal(BusinessConsts.FL_SALE_ORDER_CONF_ID, SALE_ORDER_ID,strSALE_ORDER_DTL_ID,CANCEL_NUM); //按取消数据加返利
        	
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
            Map <String, String> params = new HashMap <String, String>();
            params.put("SALE_ORDER_DTL_ID", model.getSALE_ORDER_DTL_ID());
            params.put("IS_BACKUP_FLAG", model.getIS_BACKUP_FLAG());
            params.put("REMARK", model.getREMARK());
            updateList.add(params);
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Saleorderrls.updateChldById", updateList);
        }
    }
    public List <Map> qrySaleOrderExp(Map params)
    {
    	return findList("Saleorderrls.qrySaleOrderExp",params);
    }
}