package com.hoperun.drp.main.firpage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.drp.main.firpage.service.DrpFirpageService;
import com.hoperun.erp.sale.prmtplan.model.PrmtplanModel;
import com.hoperun.sys.model.NoticeModel;
import com.hoperun.sys.model.UserBean;

public class DrpFirpageServiceImpl extends BaseService implements
		DrpFirpageService {

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
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageAllQueryNotice(Map<String, String> params, int pageNo) {
		return this.pageQuery("DrpFirpage.pageQuery_Allnotice",
				"DrpFirpage.pageCount_Allnotice", params, pageNo);
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
		paramMap.put("LEDGER_ID",userBean.getLoginZTXXID());//根据帐套过滤
	    return this.findList("DrpFirpage.queryBar", paramMap);
	}
	
	
	/**
	    * 查找附件
	    * @param NOTICEID
	    * @return
	    */
	   public Map<String,String> queryFilePath(String NOTICEID){
		   Map<String,String> paramMap = new HashMap<String,String>();
		   paramMap.put("FROM_BILL_ID", NOTICEID);
		   List<Map<String,String>> fileList = this.findList("Techorder.queryUploadFile", paramMap);
		   if(null != fileList && !fileList.isEmpty()){
			   return fileList.get(0);
		   }
		   paramMap.clear();
		   return paramMap;
	   }
	   
	   /**
	     * 查询发运单
	     * @param userBean
	     * @return
	     */
	    public List<Map<String,Object>> queryDeliver(UserBean userBean){
	    	 Map<String,String> paramMap = new HashMap<String,String>();
			 paramMap.put("ZTXXID", userBean.getLoginZTXXID());
			 paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			 return this.findList("DrpFirpage.queryDeliver", paramMap);
	    }
	    
	    /**
	     * @param userBean
	     * @param size
	     * @return
	     */
	    public List queryAllNotices(UserBean userBean,int size){
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("XTYHID", userBean.getXTYHID());
			paramMap.put("CHANN_ID", userBean.getCHANN_ID());
			paramMap.put("STATE", BusinessConsts.PASS);
			paramMap.put("size", size);
			return this.findList("DrpFirpage.queryAllNotices", paramMap);
		}
	    
	    /**
		 * 查看是否能看到所有公告的人员信息ID
		 */
		public List<NoticeModel> queryNoticers(String RYXXID){
			return this.findList("DrpFirpage.queryNoticers", RYXXID);
		}
		
	  
}
