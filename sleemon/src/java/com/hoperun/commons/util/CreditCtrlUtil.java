package com.hoperun.commons.util;

import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.service.impl.CreditCtrlServiceImpl;

public class CreditCtrlUtil {
	
	public static String getU9CurrCredit(String CHANN_NO)throws Exception{
		CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");
		String AcctAmount =  creditCtrlImpl.getU9CurrCredit(CHANN_NO);
		return AcctAmount;
	}

	/**销售订单关闭时回冻结信用
	 * @param SALE_ORDER_ID
	 * @param SALE_ORDER_DTL_IDS
	 * @return
	 */
	public static boolean updateFreezeCreditForSaleClose(String SALE_ORDER_ID,
			String SALE_ORDER_DTL_IDS) {
		CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
				.getBean("CreditCtrlService");
		creditCtrlImpl.updateFreezeCreditForSaleClose(SALE_ORDER_ID,
				SALE_ORDER_DTL_IDS);
		creditCtrlImpl.inertCreditJournalSaleClose(SALE_ORDER_DTL_IDS, "1");
		return true;
	}

	public static boolean updateFreezeCreditForPdtDeliver(
			String SALE_ORDER_DTL_ID, String realStoreNum) {
		CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
				.getBean("CreditCtrlService");
		if(!creditCtrlImpl.checkIsFreeOrder(SALE_ORDER_DTL_ID)){
			creditCtrlImpl.updateFreezeCredit(SALE_ORDER_DTL_ID, realStoreNum);
			creditCtrlImpl.inertCreditJournal(SALE_ORDER_DTL_ID, realStoreNum, "1");
		}
		return true;
	}

	/**
	 * 判断信用是否符合要求
	 * 
	 * @param bussID业务类型
	 *            ，是用来获得配置信息用的。
	 * @param keyValue
	 *            记账业务的主键值,是用来获得记账所需要的参数值。
	 * @param chkType
	 * @param busStats
	 *            业务状态
	 * @return
	 */
	public static boolean chkCanUseCreditForCommit(String bussID,
			String keyValue) throws Exception {
		CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
				.getBean("CreditCtrlService");
		return creditCtrlImpl.txChkCanUseCredit(bussID, keyValue);
	}
	
	public static String getChannNoByGoodOrderId(String GOODS_ORDER_ID) throws Exception{
		CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");
		Map goodOrderlMap = creditCtrlImpl.getGoodsOrderMap(GOODS_ORDER_ID);
		String  ORDER_CHANN_NO = (String)goodOrderlMap.get("ORDER_CHANN_NO");
		return ORDER_CHANN_NO;
		
	}
	
	/**查询渠道订货可用信用
	 * @param channId
	 * @return
	 * @throws Exception
	 */
	public static String queryUserCredit(String channId) throws Exception{	
		CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");
		return creditCtrlImpl.queryUserCredit(channId);
	}
	
	
	/**出货计划提交库房时,更新人工冻结信用，检查信用是否够提交库房
	 * @param DELIVER_ORDER_ID
	 * @param CHANN_ID
	 * @param direction
	 * @return
	 * @throws Exception
	 */
	public static String txDoDeliverOrderFreeCredit(String DELIVER_ORDER_ID,String CHANN_ID,String CHANN_NO)throws Exception{
		CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");	
		String checkResult = creditCtrlImpl.chkUseCreditForDeliverOrder(DELIVER_ORDER_ID,CHANN_ID,CHANN_NO);
		if("true".equals(checkResult)){
			boolean flag = creditCtrlImpl.updateFreeCreditForDeliverOrder(DELIVER_ORDER_ID,CHANN_ID,"0");
			if(flag){
				creditCtrlImpl.inertDeliverOrderCreditJournal(DELIVER_ORDER_ID,CHANN_ID,"0");
			}
		}
		return checkResult;

	}
	
	/**出货计划整单撤销
	 * @param DELIVER_ORDER_ID
	 * @param CHANN_ID
	 * @param direction
	 */
	public static void txUpdateFreeCreditForCancel(String DELIVER_ORDER_ID,String CHANN_ID)throws Exception{
		CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");	
		creditCtrlImpl.updateFreeCreditForDeliverOrder(DELIVER_ORDER_ID,CHANN_ID,"1");
		creditCtrlImpl.inertDeliverOrderCreditJournal(DELIVER_ORDER_ID,CHANN_ID,"1");
	}
	
	/**关闭出货计划
	 * @param DELIVER_ORDER_ID
	 * @param CHANN_ID
	 * @throws Exception
	 */
	public static void txUpdateFreeCreditForClose(String DELIVER_ORDER_ID,String CHANN_ID)throws Exception{
		CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");	
		if(creditCtrlImpl.checkIsUpdateFreeCredit(DELIVER_ORDER_ID)){
			creditCtrlImpl.updateFreeCreditForDeliverOrder(DELIVER_ORDER_ID,CHANN_ID,"1");
			creditCtrlImpl.inertDeliverOrderCreditJournal(DELIVER_ORDER_ID,CHANN_ID,"1");
		}
		creditCtrlImpl.updateDeliverFreezeAmount(DELIVER_ORDER_ID,CHANN_ID);
	}
	
	public static void txUpdateDeliverFreezeAmount(String DELIVER_ORDER_ID,String CHANN_ID){
		CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");	
		creditCtrlImpl.updateDeliverFreezeAmount(DELIVER_ORDER_ID,CHANN_ID);
	}
	
	public static void txUpdateFreeAmountByChannId(String CHANN_ID){
		CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");	
		creditCtrlImpl.updateFreeAmountByChannId(CHANN_ID);		
	}
	
	
	/*****************************************预算科目冻结金额**************************/
    /**冻结预批金额
     * @param PRMT_COST_REQ_ID
     * @throws Exception
     */
    public static void txUpdateFreeBudget(String EXPENSE_ORDER_ID)throws Exception{
    	CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");	
    	creditCtrlImpl.txUpdateFreeBudget(EXPENSE_ORDER_ID);
    }
    
    public static void txUpdateBudgetFreeAndAmount(String EXPENSE_ORDER_ID)throws Exception{
    	CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");	
    	creditCtrlImpl.txUpdateBudgetFreeAndAmount(EXPENSE_ORDER_ID);   	
    }
    
    
    /*****************************************成本调整金额*********************************************/
    public static void trUpdateCostAdJust(String COST_ADJUST_ID)throws Exception{
    	CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");	
    	creditCtrlImpl.trUpdateCostAdJust(COST_ADJUST_ID);
    }
    
    /*****************************************装修申请记账**************************/
    /**装修申请记账          add by zhuxw
     * @param CHANN_RNVTN_ID
     * @throws Exception
     */
    public static void txRnvtnReqAcct(String CHANN_RNVTN_ID,String DIRECTION)throws ServiceException{
    	CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");	
    	creditCtrlImpl.txRnvtnReqAcct(CHANN_RNVTN_ID,DIRECTION);
    }
    /*****************************************装修费报销记账**************************/
    /**装修费报销记账          add by zhuxw
     * @param CHANN_RNVTN_ID
     * @throws Exception
     */
    public static void txUpdRnvtnReitAcct(String RNVTN_REIT_REQ_ID)throws Exception{
    	CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");	
    	creditCtrlImpl.txUpdRnvtnReitAcct(RNVTN_REIT_REQ_ID);
    }
    
    /*****************************************装修费报销记账**************************/
    /**装修费报销冻结          add by jack
     * @param CHANN_RNVTN_ID
     * @throws Exception
     */
    public static void txUpdRnvtnReitFreeezeAcct(String RNVTN_REIT_REQ_ID,String DIRECTION)throws Exception{
    	CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");	
    	creditCtrlImpl.txUpdRnvtnReitFreeezeAcct(RNVTN_REIT_REQ_ID,DIRECTION);
    }

}
