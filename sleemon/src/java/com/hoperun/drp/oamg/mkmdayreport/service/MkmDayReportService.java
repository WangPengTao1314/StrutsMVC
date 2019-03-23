package com.hoperun.drp.oamg.mkmdayreport.service;

import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.mkmdayreport.model.ChannVisitDayModel;
import com.hoperun.drp.oamg.mkmdayreport.model.CooperativeVisitModel;
import com.hoperun.drp.oamg.mkmdayreport.model.DistributorVisitModel;
import com.hoperun.drp.oamg.mkmdayreport.model.MkmDayReportModel;
import com.hoperun.drp.oamg.mkmdayreport.model.PromotionActvModel;
import com.hoperun.drp.oamg.mkmdayreport.model.ShoppGuideTranModel;
import com.hoperun.drp.oamg.mkmdayreport.model.TerminalVisitModel;
import com.hoperun.sys.model.UserBean;

public interface MkmDayReportService extends IBaseService {

	/**
	 * 编辑
	 * 
	 * @param OPEN_TERMINAL_REQ_ID
	 * @param obj
	 * @param userBean
	 */
	public boolean txEdit(String MKM_DAY_RPT_ID, MkmDayReportModel obj,
			ChannVisitDayModel cvModel, PromotionActvModel actModel,
			DistributorVisitModel disModel, CooperativeVisitModel cooModel,
			ShoppGuideTranModel shopModel,TerminalVisitModel termModel,UserBean userBean);

	/**
	 * 分页查询 Description :.
	 * 
	 * @param params
	 *            the params
	 * @param pageNo
	 *            the page no
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo);
	
	/**
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQueryWH(Map<String, String> params, int pageNo);

	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> load(String MKM_DAY_RPT_ID);

	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByChannVist(String MKM_DAY_RPT_ID);

	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByProActv(String MKM_DAY_RPT_ID);

	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByDisVisit(String MKM_DAY_RPT_ID);
    
	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByCooVisit(String MKM_DAY_RPT_ID);
	
	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByShopTran(String MKM_DAY_RPT_ID);
	
	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByByTerm(String MKM_DAY_RPT_ID);
	
	/**
	 * @param MKM_DAY_RPT_ID
	 * @param userBean
	 * @return
	 */
	public boolean txDelete(String MKM_DAY_RPT_ID, UserBean userBean);

	/**
	 * @param CHANN_ID
	 * @return
	 */
	public Map<String, String> loadByAMId(String RYYXXID);

	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public String queryActvId(String MKM_DAY_RPT_ID);

}
