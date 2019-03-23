package com.hoperun.report.reportcomm.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.mkmdayreport.model.MkmDayReportModel;
import com.hoperun.sys.model.UserBean;

/**
 * 报表管理接口
 * @author zhuxw
 *
 */
public interface ReportService  extends IBaseService{

	/**
	 * 更新
	 * @param params
	 * @return
	 */
	public boolean txUpdcom(Map params);
	
	public void batch4Update(List datas);
	/**
	 * 主表分页查询
	 * Description :
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);

	public Map selecTotalCount(Map<String,String> params);
	//查询渠道信息
	public Map queryChannInfo(String CHANN_ID);
	
	/**
	 * 信用流水账查询 分销sql
	 * @param userBean
	 * @return
	 */
	public String getCreditAcctFXSQL(UserBean userBean,String conSql);
	/**
	 * 信用流水账查询  总部sql
	 * @param userBean
	 * @return
	 */
	public String getCreditAcctSQL(UserBean userBean,String conSql);
	/**
	 * 统计流水账 DIRECTION=0加 DIRECTION=1减
	 * @param conSql
	 * @return
	 */
	public List queryCreditTolal(String conSql);
	/**
	 * 收款查询总条数sql
	 * @param userBean
	 * @param conSql
	 * @return
	 */
	public String getImportCountSql(String conSql);
	/**
	 * 门店销售出货统计sql
	 * @param userBean
	 * @param conSql
	 * @return
	 */
	public String getTermSaleSendSql(UserBean userBean,String conSql,String conSqloT);
	
	/**
	 * 门店销售出货统计sql
	 * 按货品分类查询
	 * @param conSql
	 * @param conSqloT
	 * @return
	 */
	public String getTermSaleSendByPrdParentSql(String conSql,String conSqloT);
	
	/**
	 * 门店销售出货统计sql
	 * 按导购员查询
	 * @param conSql
	 * @param conSqloT
	 * @return
	 */
	public String getTermSaleSendBySalePsonNameSql(String conSql,String conSqloT);
	
	/**
	 * 合同执行情况sql
	 * @return
	 */
	public String getContractSql(String SAEL_DATE_BENG,String SAEL_DATE_END);
	//收款查询sql
	public String getImportSql(String conSql);
	//根据货品id，特殊工艺id，渠道id查询预订单信息，用于库存预订单对照备货查询报表弹出页面
	public List qeuryAdvcStoreAcct(Map<String,String> map);
	/**
	 * 未处理的订货订单冻结信用
	 * @param conSql
	 * @return
	 */
	public String getCreditFreezeSql(String conSql);
	/**
	 * 返利订单扣除信用
	 * @param conSql
	 * @return
	 */
	public String getRebateFreezeSql(String conSql);
	
	/**
	 * 分类销售统计
	 * @param conSql
	 * @return
	 */
	public String getTop10ProductSql(String conSql,String RANKING);
	
	/**
	 * 销量统计比例
	 * @param conSql
	 * @return
	 */
	public String getSaleStatisticsSql(String conSql);
	/**
	 * 待确认预订单
	 * @param conSql
	 * @return
	 */
	public String getUncommAdvcSql(String conSql);
	
	/**
	 * 总部销售报表
	 * @param conSql
	 * @return
	 */
	public String getErpSaleReportSql(String conSql);
	
	/**
	 * 各战区季度有效门店达成数
	 * @param conSql
	 * @return
	 */
	public String getWareaQuarterOpenNumSql(String conSql);
	/**
	 * 战区推广费
	 * @param conSql
	 * @return
	 */
	public String getWareExtensionFeeSql(String conSql,String YEAR,String QUARTER);
	/**
	 * 区域推广费
	 * @param conSql
	 * @return
	 */
	public String getAreaExtensionFeeSql(String conSql,String YEAR,String QUARTER);
	
	/**
	 * 每月售后投诉报表
	 * @param YEAR_MONTH
	 * @return
	 */
	public String getAdviseMothStatisSql(String YEAR_MONTH,String lastDate);
	
	/**
	 * 软床季/年 售后投诉报表
	 * 
	 * @param YEAR_MONTH
	 * @return
	 */
	public String getCDAdviseMothStatisSql(String CUUR_YEAR_MONTH_BEG,String CUUR_YEAR_MONTH_END,
			String LAST_YEAR_MONTH_BEG,String LAST_YEAR_MONTH_END);
	
	
	/**
	 * 软床季/年 售后投诉报表
	 * @param CUUR_YEAR_MONTH_BEG 查询开始时间
	 * @param CUUR_YEAR_MONTH_END 查询结束时间
	 * @param LAST_YEAR_MONTH_BEG 上年开始时间
	 * @param LAST_YEAR_MONTH_END 上年结束时间
	 * @param PRD_TYPE  货品类型 软床、床垫、床头柜
	 * @return
	 */
	public String getAdviseMothStatisSql(String CUUR_YEAR_MONTH_BEG,String CUUR_YEAR_MONTH_END,
			String LAST_YEAR_MONTH_BEG,String LAST_YEAR_MONTH_END,String PRD_TYPE);
	
	/**
	 * @param params
	 * @return
	 */
	public int getCountMkm(Map<String,String> params);
	
	/**
	 * @param params
	 * @return
	 */
	public List<MkmDayReportModel> queryMkmdayreport(Map<String,String> params);
	
	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> load(String MKM_DAY_RPT_ID);

	
	public Map<String, String> loadByChannVist(String MKM_DAY_RPT_ID);

 
	public Map<String, String> loadByProActv(String MKM_DAY_RPT_ID);

	
	public Map<String, String> loadByDisVisit(String MKM_DAY_RPT_ID);
    
	 
	public Map<String, String> loadByCooVisit(String MKM_DAY_RPT_ID);
	
	 
	public Map<String, String> loadByShopTran(String MKM_DAY_RPT_ID);
	 
	public Map<String, String> loadByByTerm(String MKM_DAY_RPT_ID);
	
	/**
	 * @param REPORT_DATE
	 * @param MKM_NAME
	 * @return
	 */
	public  String  queryReportInfo(String VST_DATE,String MKM_NAME);
 
}
