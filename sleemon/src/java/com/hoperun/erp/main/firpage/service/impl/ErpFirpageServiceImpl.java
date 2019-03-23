package com.hoperun.erp.main.firpage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.main.firpage.service.DrpFirpageService;
import com.hoperun.erp.main.firpage.service.ErpFirpageService;
import com.hoperun.erp.sale.prmtplan.model.PrmtplanModel;
import com.hoperun.sys.model.UserBean;

public class ErpFirpageServiceImpl extends BaseService implements
		ErpFirpageService {

	/**
	 * 查询推广促销
	 * 
	 * @param userBean
	 *            userBean
	 * @param size
	 *            记录数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PrmtplanModel> queryPrmtpList(UserBean userBean, int size) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("XTYHID", userBean.getXTYHID());
		paramMap.put("CHANN_ID", userBean.getCHANN_ID());
		paramMap.put("STATE", BusinessConsts.ISSUANCE);
		paramMap.put("size", size);
		return this.findList("DrpFirpage.queryPrmt", paramMap);
	}

	/**
	 * 加载公告
	 * 
	 * @param NOTICE_ID
	 * @return
	 */
	public Map<String, String> loadNoticeModel(String NOTICE_ID) {
		return (Map<String, String>) this.load("Notice.loadById", NOTICE_ID);
	}

	/**
	 * 公告加载更多
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQueryNotice(Map<String, String> params, int pageNo) {
		return this.pageQuery("DrpFirpage.pageQuery_notice",
				"DrpFirpage.pageCount_notice", params, pageNo);
	}

	/**
	 * 加载促销
	 * 
	 * @param PRMT_PLAN_ID
	 * @return
	 */
	public Map<String, String> loadPrmtModel(String PRMT_PLAN_ID) {
		return (Map<String, String>) this.load("Prmtplan.loadById",
				PRMT_PLAN_ID);
	}

	/**
	 * 公告促销更多
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQueryPrmt(Map<String, String> params, int pageNo) {
		return this.pageQuery("DrpFirpage.pageQuery_prmt",
				"DrpFirpage.pageCount_prmt", params, pageNo);
	}

	/**
	 * 查询 待发货预订单 待入库 待退货
	 */
	public List queryCount() {
		return this.findList("DrpFirpage.queryCount", null);
	}

	
	/**
	 *查询  待退货  状态 已提交
	 */
    public int queryPrdRetuenReqCount(Map<String,String> params){
    	return this.queryForInt("DrpFirpage.queryPrdRetuenReqCount", params);
    }
    
    /**
	 *查询  待入库  状态 已提交
	 */
    public int queryStoreIn(Map<String,String> params){
    	return this.queryForInt("DrpFirpage.queryStoreIn", params);
    }
    
    /**
	 *查询  待发货  状态 待发货
	 */
    public int queryOrderCount(Map<String,String> params){
    	return this.queryForInt("DrpFirpage.queryOrderCount", params);
    }
    
	/**
	 * 首页柱状图
	 * 
	 * @return
	 */
	public List queryBar(UserBean userBean){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("STATE",BusinessConsts.HAVE_SEND_GOODS);
	    return this.findList("DrpFirpage.queryBar", paramMap);
	}

	 
	public Map<String, String> queryFilePath(String NOTICEID) {
		return null;
	}
	
	/**
	 * 查询订货订单处理未处理状态条数
	 * @param CHANNS_CHARG
	 * @return
	 */
    public int queryGoodsCount(String CHANNS_CHARG){
    	Map<String,String> map=new HashMap<String, String>();
    	map.put("CHANNS_CHARG", CHANNS_CHARG);
    	map.put("STATE", BusinessConsts.UNDONE);
    	return StringUtil.getInteger(this.load("DrpFirpage.queryGoodsCount", map));
    }
    /**
     * 查询制定交付计划条数
     * @param userBean
     * @return
     */
    public int queryTurnoverplanCount(UserBean userBean){
    	Map<String,String> map=new HashMap<String, String>();
    	StringBuffer sql=new StringBuffer();
    	sql.append("'").append(BusinessConsts.PASS).append("',");//'审核通过'
//    	sql.append("'").append(BusinessConsts.HAVE_CHECK_PRICE).append("',"); //'已核价'
    	//sql.append("'").append(BusinessConsts.UN_HAVE_CHECK_PRICE).append("',"); //'未核价'
    	sql.append("'").append(BusinessConsts._BACK).append("'"); //'退回'
    	map.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
    	map.put("STATE", sql.toString());
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
//    	map.put("CREATOR", userBean.getXTYHID());
    	return StringUtil.getInteger(this.load("DrpFirpage.queryTurnoverplanCount", map));
    }
    
    /**
     * 查询交付计划维护条数
     * @param userBean
     * @return
     */
    public int queryTurnoverhdCount(UserBean userBean){
    	Map<String,String> map=new HashMap<String, String>();
    	StringBuffer sql=new StringBuffer();
    	sql.append("'").append(BusinessConsts.UNCOMMIT).append("'");//'审核通过'
    	map.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
    	map.put("STATE", sql.toString());
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	return StringUtil.getInteger(this.load("DrpFirpage.queryTurnoverhdCount", map));
    }
    
    /**
     * 查询货品发运维护条数
     * @param userBean
     * @return
     */
    public int queryPdtdeliverCount(UserBean userBean){
    	Map<String,String> map=new HashMap<String, String>();
    	StringBuffer sql=new StringBuffer();
    	sql.append("'").append("已提交生产").append("'");//'审核通过'
    	map.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
    	map.put("STATE", sql.toString());
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	return StringUtil.getInteger(this.load("DrpFirpage.queryTurnoverhdCount", map));
    }
    
    /**
     * 查询发运确认条数
     * @param userBean
     * @return
     */
    public int querydeliverconfmCount(UserBean userBean){
    	Map<String,String> map=new HashMap<String, String>();
    	StringBuffer sql=new StringBuffer();
    	sql.append("'").append("已发货").append("'");//'审核通过'
    	map.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
    	map.put("STATE", sql.toString());
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	return StringUtil.getInteger(this.load("DrpFirpage.queryTurnoverhdCount", map));
    }


	@Override
	public List queryBar() {
		return null;
	}
	
	
	/**
     * 查询待审核的工艺单
     * @param userBean
     * @return
     */
    public int queryTechorderAudit(UserBean userBean){
    	Map<String,String> map = new HashMap<String, String>();
    	StringBuffer sql = new StringBuffer();
    	sql.append("'").append("提交").append("'"); 
    	map.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
    	map.put("STATE", sql.toString());
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	return StringUtil.getInteger(this.load("DrpFirpage.queryTechorderAuditCount", map));
    }
    /**
     * 查询核价的工艺单
     * @param userBean
     * @return
     */
    public int queryTechorderPrice(UserBean userBean){
    	Map<String,String> map = new HashMap<String, String>();
    	StringBuffer sql = new StringBuffer();
    	sql.append("'").append("待核价").append("'"); 
    	map.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
    	map.put("STATE", sql.toString());
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	return StringUtil.getInteger(this.load("DrpFirpage.queryTechorderCount", map));
    }
    
}
