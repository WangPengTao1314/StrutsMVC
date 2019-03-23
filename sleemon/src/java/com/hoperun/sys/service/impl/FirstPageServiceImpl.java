package com.hoperun.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.prmtplan.model.PrmtplanModel;
import com.hoperun.sys.model.MessageModel;
import com.hoperun.sys.model.NoticeModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FirstPageService;

// TODO: Auto-generated Javadoc
/**
 * The Class FirstPageServiceImpl.
 */
public class FirstPageServiceImpl extends BaseService implements FirstPageService {
    
    /**
     * 查询所有短消息.
     * 
     * @param map the map
     * 
     * @return the all message
     */
	public List<MessageModel>getAllMessage(Map<String,String> map){
		return this.findList("MESSAGE.queryAllMessage", map);
	}
    
    /**
     * 根据短消息ID修改查看状态.
     * 
     * @param map the map
     * 
     * @return the string
     */
	public String txInsertCkztByDxxid(Map<String,String> map){
		List<Map<String,String>> list = this.findList("MESSAGE.queryOldMessById", map.get("MESSAGEID"));
		if(list==null||list.size()==0){
			this.insert("MESSAGE.insertCkztBydxxid", map);
		}
		return "1";
	}
	
	/**
	 * 将短消息插入表中.
	 * 
	 * @param map the map
	 * 
	 * @return true, if tx insert message
	 */
	public boolean txInsertMessage(Map<String,String> map){
		this.insert("MESSAGE.insertMessage", map);
		return true;
	}
	
	/**
	 * 将审批时生成的短消息插入表中.
	 * 
	 * @param map the map
	 * 
	 * @return true, if tx insert message by audit
	 */
	public boolean txInsertMessageByAudit(Map<String,String> map){
		this.insert("MESSAGE.insertMessageByAudit", map);
		return true;
	}
	
	/**
	 * 代办事宜.
	 * @param sql the sql
	 * @return the list< map< string, string>>
	 */
	public List<Map<String,String>> queryDbsyByUser(String sql){
		return this.findList("MESSAGE.queryDbsyByUser", sql);
	}
	 
 	/**
 	 * 数据过滤.
 	 * @param sql the sql
 	 * @return the filter data
 	 */
	public Map<String,String>getFilterData(String sql){
		return (Map)load("MESSAGE.queryFilterData",sql);
	}
	
	
	/**
	 * Tx inscom.
	 * @param params the params
	 * @return true, if successful
	 */
	public boolean txInscom(Map params) {

        insert("sqlcom.insert", params);
        return true;
    }
	 //通用增删改查
    /**
 	 * Selcom.
 	 * 
 	 * @param params the params
 	 * 
 	 * @return the map
 	 */
 	public Map selcom(Map params) {

        return (Map) load("sqlcom.query", params);
    }
    
    /**
     * Tx updcom.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    public boolean txUpdcom(Map params) {

        return update("sqlcom.update", params);
    }

    

	/**
	 * 查询推广促销
	 * @param userBean userBean
	 * @param size 记录数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PrmtplanModel> queryPrmtpList(UserBean userBean,int size){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("XTYHID", userBean.getXTYHID());
		paramMap.put("STATE", BusinessConsts.ISSUANCE);
		paramMap.put("size", size);
		return this.findList("DrpFirpage.queryPrmt", paramMap);
	}
	
	/**
	 * 查询公告
	 * @param userBean 用户
	 * @param size 记录数
	 * @return
	 */
	public List<NoticeModel> queryNoticeList(UserBean userBean,int size){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		paramMap.put("XTYHID", userBean.getXTYHID());
		paramMap.put("CHANN_ID", userBean.getCHANN_ID());
		paramMap.put("STATE", BusinessConsts.PASS);
		paramMap.put("size", size);
		
		String DEPTZ_NO = userBean.getBMXXID();
		String TERM_ID = userBean.getTERM_ID();
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		String NOTICE_OBJ = "总部公告";
		if(BusinessConsts.INTEGER_1.equals(IS_DRP_LEDGER)){
			TERM_ID = userBean.getTERM_ID();
			System.out.println("CHANN_ID:"+userBean.getCHANN_ID()); 
			System.out.println("DEPTZ_NO:"+userBean.getBMXXID()); 
			System.out.println("TERM_ID:"+TERM_ID); 
			if(StringUtil.isEmpty(TERM_ID)){
				NOTICE_OBJ = "渠道公告";
			}else{
				NOTICE_OBJ = "门店公告";
			}
		}
		paramMap.put("NOTICE_OBJ", NOTICE_OBJ);
		if("1".equals(userBean.getIS_DRP_LEDGER())){//如果是分销的首页
			return this.findList("DrpFirpage.queryNotice", paramMap);
		}else{//如果是总部的首页
			paramMap.put("BMXXIDS", "'"+userBean.getBMXXID()+"'");
			return this.findList("DrpFirpage.queryNoticeForErp", paramMap);
		}
	}
	
	/**
	 * 查看是否能看到所有公告的人员信息ID
	 */
	public List<NoticeModel> queryNoticers(String RYXXID){
		return this.findList("DrpFirpage.queryNoticers", RYXXID);
	}
	
	/**
	 * @param userBean
	 * @param size
	 * @return
	 */
	public List<NoticeModel> queryAllNotices(UserBean userBean,int size){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("XTYHID", userBean.getXTYHID());
		paramMap.put("CHANN_ID", userBean.getCHANN_ID());
		paramMap.put("STATE", BusinessConsts.PASS);
		paramMap.put("size", size);
		return this.findList("DrpFirpage.queryAllNotices", paramMap);
	}
	
	/**
	 * 加载公告
	 * @param NOTICE_ID
	 * @return
	 */
	public Map<String,String> loadNoticeModel(String NOTICE_ID){
		return (Map<String,String>) this.load("Notice.loadById", NOTICE_ID);
	}
	
	
	/**
	 * 查询库存预警
	 * @return
	 */
	public List queryStore(UserBean userBean){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("LEDGER_ID",userBean.getLoginZTXXID());
		return this.findList("DrpFirpage.queryStore", paramMap);
	}
	
	public int queryBmForInt(UserBean userBean){
		return 0;
	}
	/**
	 * 总部出库金额按月份
	 * 
	 * @param sql the sql
	 * 
	 * @return the list< map< string, string>>
	 */
	public List<Map> qrySaleAmountByMonth(Map<String,String> paramMap)
	{
		return this.findList("DrpFirpage.qrySaleAmountByMonth", paramMap);
	}

	/**
	 * 总部出库金额按品类
	 * 
	 * @param sql the sql
	 * 
	 * @return the list< map< string, string>>
	 */
 
	public List<Map> columnChartDefAccordingToCategoryHeadquatersShippingAmount(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.findList("DrpFirpage.columnChartDefAccordingToCategoryHeadquatersShippingAmount", paramMap);
	}
	
	public List<Map> directsaleStoreDevelopmentTheSalesAmountOfSummary(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.findList("DrpFirpage.directsaleStoreDevelopmentTheSalesAmountOfSummary", paramMap);
	}
	
	@Override
	public List<Map> qrySaleAmountByWarZoneMonth(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.findList("DrpFirpage.qrySaleAmountByWarZoneMonth", paramMap);
	}

	@Override
	public List<Map> qrySaleAmountByWarZoneQuarter(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.findList("DrpFirpage.qrySaleAmountByWarZoneQuarter", paramMap);
	}

	@Override
	public List<Map> effectiveStoreChannelDeptsSummarization(
			Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.findList("DrpFirpage.effectiveStoreChannelDeptsSummarization", paramMap);
	}
	
	@Override
	public List<Map> effectiveStoreChannelDeptsSummarizationByWarZone(
			Map<String, String> paramMap) {
		return this.findList("DrpFirpage.effectiveStoreChannelDeptsSummarizationByWarZone", paramMap);
	}

	@Override
	public List<Map> headquartersOrderAmountClassificationOfGoods(
			Map<String, String> paramMap) {
		return this.findList("DrpFirpage.headquartersOrderAmountClassificationOfGoods", paramMap);
	}

	@Override
	public List<Map> headquartersOrderAmountByWarDimensionByQuarter(
			Map<String, String> paramMap) {
		return this.findList("DrpFirpage.headquartersOrderAmountByWarDimensionByQuarter", paramMap);
	}

	@Override
	public List<Map> headquartersOrderAmountByWarDimension(
			Map<String, String> paramMap) {
		return this.findList("DrpFirpage.headquartersOrderAmountByWarDimension", paramMap);
	}

	@Override
	public List<Map> headquartersOrderAmountByHeaderQuaterDimension(
			Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.findList("DrpFirpage.headquartersOrderAmountByHeaderQuaterDimension", paramMap);
	}

	@Override
	public List<Map> directsalestoreTheSalesAmountOfSummary(
			Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.findList("DrpFirpage.directsalestoreTheSalesAmountOfSummary", paramMap);
	}

	@Override
	public List<Map> channelAndDirectSaleStoreSalesAmountOfOutbound(
			Map<String, String> paramMap) {
		return this.findList("DrpFirpage.channelAndDirectSaleStoreSalesAmountOfOutbound", paramMap);
	}

	@Override
	public List<Map> channelAndDirectSaleStoreSalesOutboundTop5ByStore(
			Map<String, String> paramMap) {
		return this.findList("DrpFirpage.channelAndDirectSaleStoreSalesOutboundTop5ByStore", paramMap);
	}

	@Override
	public List<Map> channelAndDirectSaleStoreSalesOutboundTop5ByGoods(
			Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.findList("DrpFirpage.channelAndDirectSaleStoreSalesOutboundTop5ByGoods", paramMap);
	}

	@Override
	public List<Map> channelAndDirectSaleStoreSalesOutboundTop5BySalesman(
			Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return this.findList("DrpFirpage.channelAndDirectSaleStoreSalesOutboundTop5BySalesman", paramMap);
	}
	
	/**
	 * 区域前30销售额
	 * @param paramMap
	 * @return
	 */
	public List<Map<String,String>> queryAreaTopByQuarter(Map<String,String> paramMap){
		return this.findList("DrpFirpage.queryAreaTopByQuarter", paramMap);
	}
	
}
