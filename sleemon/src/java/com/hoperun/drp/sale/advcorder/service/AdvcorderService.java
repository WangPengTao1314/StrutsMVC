/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvcorderService
 */
package com.hoperun.drp.sale.advcorder.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModel;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelTrace;
import com.hoperun.sys.model.UserBean;

/**
 * *@service *@func *@version 1.1 *@author lyg *@createdate 2013-08-11 17:17:29
 */
public interface AdvcorderService extends IBaseService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo);

	public boolean txUpdateById(Map<String, String> params);

	/**
	 * @主从保存编辑
	 * @Description :
	 * @param ADVC_ORDER_ID
	 * @param AdvctoorderModel
	 * @param userBean
	 *            .
	 * 
	 * @return
	 */
	public void txEdit(String ADVC_ORDER_ID, AdvcorderModel obj,
			List<AdvcorderModelChld> gchldList, UserBean userBean,String oldSaleDate,String updateFlag) throws ParseException;

	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map<String, String> params);

	/**
	 * @主表详细页面
	 * @param param
	 *            ADVC_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String ADVC_ORDER_ID);

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	public List<AdvcorderModelChld> childQuery(String ADVC_ORDER_ID);

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	public List<AdvcorderModelGchld> gchildQuery(String ADVC_ORDER_ID);

	/**
	 * 根据主表Id查询跟踪记录
	 * 
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public List<AdvcorderModelTrace> traceQuery(String ADVC_ORDER_ID);

	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param PAYMENT_DTL_IDs
	 *            the PAYMENT_DTL_IDs
	 * 
	 * @return the list
	 */
	public List<AdvcorderModelChld> loadChilds(Map<String, String> params);

	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param ADVC_ORDER_DTL_IDs
	 *            the ADVC_ORDER_DTL_IDs
	 * 
	 * @return the list
	 */
	public List<AdvcorderModelGchld> loadGchilds(Map<String, String> params);

	/**
	 * * 子表记录编辑：新增/修改
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @param modelList
	 *            the model list
	 * 
	 * @return true, if tx child edit
	 */
	public boolean txChildEdit(String ADVC_ORDER_ID,
			List<AdvcorderModelChld> modelList, UserBean userBean,String updateFlag) throws ParseException;

	/**
	 * * 子表记录编辑：新增/修改
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @param modelList
	 *            the model list
	 * 
	 * @return true, if tx gchild edit
	 */
	public boolean txGchildEdit(String ADVC_ORDER_ID,
			List<AdvcorderModelGchld> modelList);

	/**
	 * * 子表的批量删除
	 * 
	 * @param PAYMENT_DTL_IDs
	 *            the PAYMENT_DTL_IDs
	 */
	public void txBatch4DeleteChild(String PAYMENT_DTL_IDs, String ADVC_ORDER_ID);

	/**
	 * * 子表的批量删除
	 * 
	 * @param ADVC_ORDER_DTL_IDs
	 *            the ADVC_ORDER_DTL_IDs
	 */
	public void txBatch4DeleteGchild(String ADVC_ORDER_DTL_IDs);

//	/**
//	 * 修改提交状态
//	 */
//	public MsgInfo txCommitById(Map<String, String> params, UserBean userBean)
//			throws Exception;

	/**
	 * 按id查找定金
	 */
	public double amountGetById(String ADVC_ORDER_ID);

	/**
	 * 按id查询付款总金额
	 */
	public double payAmountGetById(String ADVC_ORDER_ID);

	/**
	 * 查询子表条数
	 * 
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public int getByIdADVC(String ADVC_ORDER_ID);

	/**
	 * 查询子表条数
	 * 
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public int getByIdPAYMENT(String ADVC_ORDER_ID);


	// 导入
	public void txParseExeclToDbNew(List list,UserBean userBean);

	// 导入数据后 更新主表应收总额，判断订金金额后修改销售日期
	public void txUpPAYABLE_AMOUNT(String ADVC_ORDER_ID);

	// 获取登录人员的最低折扣率
	public float getTERM_DECT_FROM(String RYXXID);

	// 导出数据时根据id查询数据
	public Map<String, String> getAdv(String ADVC_ORDER_ID);

	// 导出数据时根据id查询子表数据
	public List<AdvcorderModelChld> getDtl(String ADVC_ORDER_ID);

	// 获取数据库当前时间
	public String getDate();

	public void upFreeze(String ADVC_ORDER_DTL_IDS);

	public void txUpdateFreeze(String ADVC_ORDER_ID, String ADVC_ORDER_DTL_IDS)
			throws Exception;
	//按预订单id获取明细最小折扣率
	public float getRate(Map<String,Object> map);
	/**
	 * 根据当前登陆人所属部门ID查询终端信息
	 * 
	 * @param BMXXID
	 * @return
	 */
	public Map<String, String> getTerminalInfoById(String BMXXID);
	public String getPAYABLE_AMOUNT(Map<String,String> map);
	
	//获得渠道的冻结天数
	public int getMaxFrezzDays(String ledger_Id);
	public void txUpStartDoUncomm(Map<String,String> map,UserBean userBean,double amount);
	
	/**
	 * 查询客户付款单
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public Map<String,Object> queryStatements(String ADVC_ORDER_ID);
	
	/**
	 * 查询预订单对应的客户收款单是否核销
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public boolean chackHavaWRITE(String ADVC_ORDER_ID);
	/**
	 * 删除对应的客户付款单
	 * @param ADVC_ORDER_ID
	 */
	public void deletePayments(String ADVC_ORDER_ID);
	/**
	 * 关闭预订单
	 * @param map
	 */
	public void txClose(Map<String,String> map);
	/**
	 * 预订单审核通过/退回
	 * @param map  预订单id，状态
	 * @return
	 */
	public MsgInfo txUpStateById(Map<String,String> map,UserBean userBean,double amount)throws Exception;
	//根据预订单id查询明细是否有未处理或者已发货的
	public String checkReturnAudit(Map<String,String> map);
	//反审核
	public void txRetuAudit(Map<String,String> map,UserBean userBean);
	//验证是否有反核销过的单子
	public int checkWriteFlag(String ADVC_ORDER_ID);
	/**
	 * 查询核销的明细的数量
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public int queryWriteoffCount(String ADVC_ORDER_ID);
	/**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params);
	public List<AdvcorderModelChld> priceChldQuery(String ADVC_ORDER_ID,String CHANN_TYPE);
	public void txUpdateChldPrice(List<AdvcorderModelChld> chldModelList);
	/*
	 * 新增预订单已付款记录
	 */
	public boolean insertAdvcNotes(Map<String,String> map);
	public Integer getDtlCount(String ADVC_ORDER_ID);
	public List<Map<String,String>> joinAdvcQuery(String ADVC_ORDER_ID,String CHANN_TYPE);
	//查询明细中关闭的行数
	public int checkCloseFlag(String ADVC_ORDER_ID);
	//获取发货申请单数量
	public int getSendAdvcNum(String ADVC_ORDER_ID);
	//校验预订单的所有金额值
	public String checkAdvcAllPrice(String ADVC_ORDER_ID);
	//搜索框模糊查询
	public List<Map<String,String>> querySeachBox(Map<String,String> map);
	public Map<String,String> getPrdInfoById(String PRD_ID);
	/**
	 * 根据预订单Id查询预订单跟踪是否存在待确认记录
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public boolean getTraceById(String ADVC_ORDER_ID);
}