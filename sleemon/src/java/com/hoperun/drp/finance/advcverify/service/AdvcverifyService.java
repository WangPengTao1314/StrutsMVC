package com.hoperun.drp.finance.advcverify.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.finance.advcverify.model.WriteOffDtlModel;
import com.hoperun.sys.model.UserBean;

public interface AdvcverifyService extends IBaseService{
	
	   /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	
	/**
	 * 核销
	 * @param STATEMENTS_IDS 结算单 IDS
	 * @param STATEMENTS_PAYMENT_DTL_IDS 结算付款明细IDS
	 */
	public void txCheckPayment(String STATEMENTS_IDS,String STATEMENTS_PAYMENT_DTL_IDS,List<WriteOffDtlModel> delModelList,UserBean userBean);
	
	/**
	 * 反核销
	 * @param STATEMENTS_ID 结算单ID
	 * @param STATEMENTS_PAYMENT_DTL_IDS 结算付款明细IDS
	 */
	public void txUnCheckPayment(String STATEMENTS_ID,String STATEMENTS_PAYMENT_DTL_IDS,UserBean userBean);
	/**
	 * 查询已核销的明细
	 * @param STATEMENTS_PAYMENT_DTL_ID
	 * @return
	 */
	public List<Map<String,Object>> queryChild(String STATEMENTS_PAYMENT_DTL_ID);
	
	/**
	 * 反核销
	 * @param STATEMENTS_ID  预订单_结算单
	 * @param STATEMENTS_PAYMENT_DTL_ID  预订单_结算付款明细
	 * @param WRITE_OFF_DTL_IDS 核销明细IDS
	 */
	public void txUnCheckPayment(String STATEMENTS_ID,String STATEMENTS_PAYMENT_DTL_ID,
			String WRITE_OFF_DTL_IDS,UserBean userBean);
	
	/**
	 * 检查主表的状态
	 * @param STATEMENTS_IDS
	 * @return
	 */
	public int checkState(String STATEMENTS_IDS,String STATES);

}
