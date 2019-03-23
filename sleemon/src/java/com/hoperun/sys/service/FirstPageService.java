package com.hoperun.sys.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.prmtplan.model.PrmtplanModel;
import com.hoperun.sys.model.MessageModel;
import com.hoperun.sys.model.NoticeModel;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface FirstPageService.
 */
public interface FirstPageService extends IBaseService {

	/**
	 * 查询所有短消息.
	 * 
	 * @param map the map
	 * 
	 * @return the all message
	 */
	public List<MessageModel>getAllMessage(Map<String,String> map);
	
	/**
	 * 根据短消息ID修改查看状态.
	 * 
	 * @param map the map
	 * 
	 * @return the string
	 */
	public String txInsertCkztByDxxid(Map<String,String> map);
	
	/**
	 * 将短消息插入表中.
	 * 
	 * @param map the map
	 * 
	 * @return true, if tx insert message
	 */
	public boolean txInsertMessage(Map<String,String> map);
	
	/**
	 * 将审批时生成的短消息插入表中.
	 * 
	 * @param map the map
	 * 
	 * @return true, if tx insert message by audit
	 */
	public boolean txInsertMessageByAudit(Map<String,String> map);
	
	/**
	 * 代办事宜.
	 * 
	 * @param sql the sql
	 * 
	 * @return the list< map< string, string>>
	 */
	public List<Map<String,String>> queryDbsyByUser(String sql);
	
	/**
	 * Gets the filter data.
	 * 
	 * @param sql the sql
	 * 
	 * @return the filter data
	 */
	public Map<String,String>getFilterData(String sql);
	
	
	/**
	 * 查询推广促销
	 * @param size 记录数
	 * @return
	 */
	public List<PrmtplanModel> queryPrmtpList(UserBean userBean,int size);
	
	/**
	 * 查询公告
	 * @param userBean 用户
	 * @param size 记录数
	 * @return
	 */
	public List<NoticeModel> queryNoticeList(UserBean userBean,int size);
	
	
	/**
	 * 查询库存预警
	 * @return
	 */
	public List queryStore(UserBean userBean);
	
	public int queryBmForInt(UserBean userBean);
	/**
	 * 总部出库金额按月份
	 * 
	 * @param sql the sql
	 * 
	 * @return the list< map< string, string>>
	 */
	public List<Map> qrySaleAmountByMonth(Map<String,String> paramMap);
	
	
	
	public List<Map> directsaleStoreDevelopmentTheSalesAmountOfSummary(Map<String,String> paramMap);
	public List<Map> columnChartDefAccordingToCategoryHeadquatersShippingAmount(Map<String,String> paramMap);
	
	
		
	
	/**
	 * 总部出库金额按品类
	 * 
	 * @param sql the sql
	 * 
	 * @return the list< map< string, string>>
	 */
	public List<Map> qrySaleAmountByWarZoneQuarter(Map<String,String> paramMap);
	
	
	
	/**
	 * 总部出库金额按品类
	 * 
	 * @param sql the sql
	 * 
	 * @return the list< map< string, string>>
	 */
	public List<Map> qrySaleAmountByWarZoneMonth(Map<String,String> paramMap);
	
	
	
	/**
	 * 渠道部（当前有效门店数） 有效门店发展汇总 按总部
	 * 
	 * @param sql the sql
	 * 
	 * @return the list< map< string, string>>
	 */
	public List<Map> effectiveStoreChannelDeptsSummarization(Map<String,String> paramMap);
	
	
	
	/**
	 * 渠道部（当前有效门店数） 有效门店发展汇总 按战区
	 * 
	 * @param sql the sql
	 * 
	 * @return the list< map< string, string>>
	 */
	public List<Map> effectiveStoreChannelDeptsSummarizationByWarZone(Map<String,String> paramMap);
	
	
	
	
	/**
	 * 总部订货金额（货品分类）
	 * 
	 * @param sql the sql
	 * 
	 * @return the list< map< string, string>>
	 */
	public List<Map> headquartersOrderAmountClassificationOfGoods(Map<String,String> paramMap);
	
	public List<Map> headquartersOrderAmountByWarDimensionByQuarter(Map<String,String> paramMap);
	
	
	public List<Map> headquartersOrderAmountByWarDimension(Map<String,String> paramMap);
	
	
	public List<Map> headquartersOrderAmountByHeaderQuaterDimension(Map<String,String> paramMap);
	
	public List<Map> directsalestoreTheSalesAmountOfSummary(Map<String,String> paramMap);
	
	
	public List<Map> channelAndDirectSaleStoreSalesAmountOfOutbound(Map<String,String> paramMap);
	
	
	public List<Map> channelAndDirectSaleStoreSalesOutboundTop5ByStore(Map<String,String> paramMap);
	
	public List<Map> channelAndDirectSaleStoreSalesOutboundTop5BySalesman(Map<String,String> paramMap);
	
	
	public List<Map> channelAndDirectSaleStoreSalesOutboundTop5ByGoods(Map<String,String> paramMap);
	
	/**
	 * 区域前30销售额
	 * @param paramMap
	 * @return
	 */
	public List<Map<String,String>> queryAreaTopByQuarter(Map<String,String> paramMap);
	
	/**
	 * @param RYXXID
	 * @return
	 */
	public List<NoticeModel> queryNoticers(String RYXXID);
	
	/**
	 * @param userBean
	 * @param size
	 * @return
	 */
	public List<NoticeModel> queryAllNotices(UserBean userBean,int size);
}
