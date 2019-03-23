package com.hoperun.drp.oamg.mkmdayreport.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.mkmdayreport.model.ChannVisitDayModel;
import com.hoperun.drp.oamg.mkmdayreport.model.CooperativeVisitModel;
import com.hoperun.drp.oamg.mkmdayreport.model.DistributorVisitModel;
import com.hoperun.drp.oamg.mkmdayreport.model.MkmDayReportModel;
import com.hoperun.drp.oamg.mkmdayreport.model.PromotionActvModel;
import com.hoperun.drp.oamg.mkmdayreport.model.ShoppGuideTranModel;
import com.hoperun.drp.oamg.mkmdayreport.model.TerminalVisitModel;
import com.hoperun.drp.oamg.mkmdayreport.service.MkmDayReportService;
import com.hoperun.sys.model.UserBean;

public class MkmDayReportServiceImpl extends BaseService implements
		MkmDayReportService {

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
			ShoppGuideTranModel shopModel,TerminalVisitModel termModel,UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> cvParams = new HashMap<String, String>();
		Map<String, String> cooParams = new HashMap<String, String>();
		Map<String, String> disParams = new HashMap<String, String>();
		
		String actNum =  getACVModel(actModel);    //推广活动推进
		String disNum =  getDISModel(disModel);    //分销商开发与拜访
		String cooNum =  getCOOModel(cooModel);    //合作商开发与拜访
		String shopNum=  getShopModel(shopModel);  //导购员培训s
 
		params.put("MKM_NAME", obj.getMKM_NAME()); // 营销经理名称
		params.put("WAREA_ID", obj.getWAREA_ID()); // 战区ID
		params.put("WAREA_NO", obj.getWAREA_NO()); // 战区编号
		params.put("WAREA_NAME", obj.getWAREA_NAME()); // 战区名称
		params.put("REPORT_DATE", obj.getREPORT_DATE()); // 上报日期
		params.put("VST_DATE", obj.getVST_DATE()); // 拜访日期
		if(obj.getON_TRIP().equals("从XXX省份XXX市XXX区县 至 XXX省份XXX市XXX区县")){
			params.put("ON_TRIP", ""); // 出差途中
		}else{
			params.put("ON_TRIP", obj.getON_TRIP()); // 出差途中
		}
		if(obj.getCORP_BUSS_DEAL().equals("具体处理内容:1、...... 2、...... 3、......")){
			params.put("CORP_BUSS_DEAL", "");
		}else{
			params.put("CORP_BUSS_DEAL", obj.getCORP_BUSS_DEAL()); // 公司总部公务处理
		}
		if(obj.getWAKE_OFF().equals("从XXXX年XX月XX日 至 XXXX年XX月XX日")){
			params.put("WAKE_OFF", ""); // 调休
		}else{
		    params.put("WAKE_OFF", obj.getWAKE_OFF()); // 调休
		}
		params.put("REMARK", obj.getREMARK()); // 备注

		// 加盟商拜访
		cvParams.put("CHANN_ID", cvModel.getCHANN_ID());
		cvParams.put("CHANN_NO", cvModel.getCHANN_NO());
		cvParams.put("CHANN_NAME", cvModel.getCHANN_NAME());
		cvParams.put("MATT_AMOUNT", cvModel.getMATT_AMOUNT());
		cvParams.put("SOFT_BED_AMOUNT", cvModel.getSOFT_BED_AMOUNT());
		cvParams.put("OTHER_AMOUNT", cvModel.getOTHER_AMOUNT());
		cvParams.put("CHANN_PROBLEM", cvModel.getCHANN_PROBLEM());
		cvParams.put("IMPRV_PLAN", cvModel.getIMPRV_PLAN());
		cvParams.put("COMPET_PRODUCT", cvModel.getCOMPET_PRODUCT());
		cvParams.put("SUPPORT_REQ", cvModel.getSUPPORT_REQ());

		// 推广活动推进
		params.put("ACTV_STYLE", actModel.getACTV_STYLE());
		params.put("ACTV_TITLE", actModel.getACTV_TITLE());
		params.put("BEG_DATE", actModel.getBEG_DATE());
		params.put("AGREE_DATE", actModel.getAGREE_DATE());
		params.put("AGREE_ADDR", actModel.getAGREE_ADDR());
		params.put("ACTV_PRMT_COST", actModel.getACTV_PRMT_COST());
		params.put("ADVC_DEAL_BILL_NUM", actModel.getADVC_DEAL_BILL_NUM());
		params
				.put("ADVC_DEAL_BILL_AMOUNT", actModel
						.getADVC_DEAL_BILL_AMOUNT());
		params.put("FORWORD_DATE", actModel.getFORWORD_DATE());
		params.put("SALE_CARD_NUM", actModel.getSALE_CARD_NUM());
		params.put("OTHER_REMARK", actModel.getOTHER_REMARK());
		params.put("SALE_CARD_NUM_END", actModel.getSALE_CARD_NUM_END());
		params.put("CHECK_CARD_NUM", actModel.getCHECK_CARD_NUM());
		params.put("ADVC_DEAL_AMOUNT", actModel.getADVC_DEAL_AMOUNT());
		params.put("DEAL_BILL_NUM", actModel.getDEAL_BILL_NUM());
		params.put("COST_AMOUNT", actModel.getCOST_AMOUNT());

		// 分销商
		disParams.put("DISTRIBUTOR_ID", disModel.getDISTRIBUTOR_ID_T());
		disParams.put("DISTRIBUTOR_NO", disModel.getDISTRIBUTOR_NO_T());
		disParams.put("DISTRIBUTOR_NAME", disModel.getDISTRIBUTOR_NAME_T());
		disParams.put("MAIN_PROBLEM", disModel.getMAIN_PROBLEM_T());
		disParams.put("SOLUTION", disModel.getSOLUTION_T());
		disParams
				.put("WILL_DISTRIBUTOR_TYPE", disModel
						.getWILL_DISTRIBUTOR_TYPE());
		disParams.put("WILL_SALE_STORE_NAME", disModel.getWILL_SALE_STORE_NAME_T());
		disParams
				.put("WILL_DISTRIBUTOR_NAME", disModel
						.getWILL_DISTRIBUTOR_NAME());
		disParams.put("WILL_DISTRIBUTOR_ADDRESS", disModel
				.getWILL_DISTRIBUTOR_ADDRESS());
		disParams.put("WILL_DISTRIBUTOR_TEL", disModel.getWILL_DISTRIBUTOR_TEL());
		disParams.put("WILL_DISTRIBUTOR_BUSS_BRAND", disModel
				.getWILL_DISTRIBUTOR_BUSS_BRAND());
		disParams.put("WILL_DISTRIBUTOR_BUSS_CLASS", disModel
				.getWILL_DISTRIBUTOR_BUSS_CLASS());
		disParams.put("WILL_DEGREE", disModel.getWILL_DEGREE_T());
		disParams.put("NO_WILL_RESON", disModel.getNO_WILL_RESON());
		
		//合作商
		cooParams.put("DISTRIBUTOR_ID",   cooModel.getDISTRIBUTOR_ID());
		cooParams.put("DISTRIBUTOR_NO",   cooModel.getDISTRIBUTOR_NO());
		cooParams.put("DISTRIBUTOR_NAME", cooModel.getDISTRIBUTOR_NAME());
		cooParams.put("MAIN_PROBLEM", cooModel.getMAIN_PROBLEM());
		cooParams.put("SOLUTION",     cooModel.getSOLUTION());
		cooParams.put("MARKET_TYPE",  cooModel.getMARKET_TYPE());
		cooParams.put("WILL_SALE_STORE_NAME", cooModel.getWILL_SALE_STORE_NAME());
		cooParams.put("CUST_NAME",      cooModel.getCUST_NAME());
		cooParams.put("CUST_TEL",       cooModel.getCUST_TEL());
		cooParams.put("MARKET_ADDRESS", cooModel.getMARKET_ADDRESS());
		cooParams.put("MARKET_BUSS_BRAND", cooModel.getMARKET_BUSS_BRAND());
		cooParams.put("MARKET_BUSS_CLASS", cooModel.getMARKET_BUSS_CLASS());
		cooParams.put("WILL_DEGREE",  cooModel.getWILL_DEGREE());
		cooParams.put("NO_WILL_RESON",cooModel.getNO_WILL_RESON());
		//导购员
		params.put("TRAN_NUM", shopModel.getTRAN_NUM());
		params.put("TRAN_TITLE", shopModel.getTRAN_TITLE());
		params.put("TRAN_TYPE",  shopModel.getTRAN_TYPE());
		params.put("TRAN_DATE",  shopModel.getTRAN_DATE());
		//门店拜访
		params.put("TERM_ID", termModel.getTERM_ID());
		params.put("TERM_NO", termModel.getTERM_NO());
		params.put("TERM_NAME", termModel.getTERM_NAME());
		params.put("CHANN_ID", termModel.getCHANN_ID_T());
		params.put("CHANN_NO", termModel.getCHANN_NO_T());
		params.put("CHANN_NAME", termModel.getCHANN_NAME_T());
		params.put("HARD_DECORATE", termModel.getHARD_DECORATE());
		params.put("SOFT_DECORATE", termModel.getSOFT_DECORATE());
		params.put("SHOPP_GUIDE", termModel.getSHOPP_GUIDE());
		params.put("MAIN_PROBLEM", termModel.getMAIN_PROBLEM_TERM());
		params.put("SOLUTION", termModel.getSOLUTION_TERM());
		String UID = StringUtil.uuid32len();
		if (StringUtils.isEmpty(MKM_DAY_RPT_ID)) {
			
			int mkmdayNum =  paramsCount(String.valueOf(obj.getMKM_NAME()),String.valueOf(obj.getVST_DATE()));
			if(mkmdayNum !=0){
	           return false;			
			}else{
			params.put("DEL_FLAG", "0");
			params.put("CREATOR", userBean.getXTYHID());// 制单人ID
			params.put("CRE_NAME", userBean.getXM()); // 制单人名称
			params.put("CRE_TIME", DateUtil.now()); // 制单时间
			params.put("DEPT_ID", userBean.getBMXXID());// 制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());// 制单部门名称
			params.put("ORG_ID", userBean.getJGXXID()); // 制单机构id
			params.put("ORG_NAME", userBean.getJGMC()); // 制单机构名称
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); // 帐套ID
			params.put("LEDGER_NAME", userBean.getLoginZTMC()); // 帐套名称
			params.put("MKM_DAY_RPT_ID", UID);
			String MKM_DAY_RPT_NO = LogicUtil.getBmgz("DRP_MKM_DAY_REPORT_NO");
			params.put("MKM_DAY_RPT_NO", MKM_DAY_RPT_NO);
			params.put("STATE", "未提交");
			this.insert("mkmdayreport.insert", params);
            
			if(!"".equals(cvModel.getCHANN_NO())){
				cvParams.put("CHANN_VISIT_DAY_ID", UID);
				cvParams.put("MKM_DAY_RPT_ID", UID);
				cvParams.put("DEL_FLAG", "0");
				this.insert("channvisitday.insert", cvParams);
				Map<String, String> attParams = new HashMap<String, String>();
				attParams.put("FROM_BILL_ID", UID);
				attParams.put("ATT_PATH", cvModel.getSTORE_ATT());// 图片路径
				attParams.put("ATT_ID", StringUtil.uuid32len());
				attParams.put("CREATOR", params.get("CREATOR"));
				attParams.put("CRE_NAME", params.get("CRE_NAME"));
				attParams.put("CRE_TIME", DateUtil.now());// 制单时间
				attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
				insert("Att.insertAtt", attParams);
			}

			// 推广活动推进
			if(!"".equals(actNum)){
				String UIDT = StringUtil.uuid32len();
				params.put("PROMOTION_ACTV_ID", UIDT);
				this.insert("Promotionactv.insert", params);
				Map<String, String> attParamsT = new HashMap<String, String>();
				attParamsT.put("FROM_BILL_ID", UIDT);
				attParamsT.put("ATT_PATH", actModel.getACTV_PRMT_ATT());// 图片路径
				attParamsT.put("ATT_ID", StringUtil.uuid32len());
				attParamsT.put("CREATOR", params.get("CREATOR"));
				attParamsT.put("CRE_NAME", params.get("CRE_NAME"));
				attParamsT.put("CRE_TIME", DateUtil.now());// 制单时间
				attParamsT.put("DEL_FLAG", params.get("DEL_FLAG"));
				insert("Att.insertAtt", attParamsT);
			}
		     // 分销商
			if(!"".equals(disNum)){
				disParams.put("DISTRIBUTOR_VISIT_ID", UID);
				disParams.put("MKM_DAY_RPT_ID", UID);
				disParams.put("DEL_FLAG", "0");
				this.insert("distributorvisit.insert", disParams);
			}
			
			//合作商
			if(!"".equals(cooNum)){
				cooParams.put("COOPERATIVE_VISIT_ID", UID);
				cooParams.put("MKM_DAY_RPT_ID", UID);
				cooParams.put("DEL_FLAG", "0");
				this.insert("cooperativevisit.insert", cooParams);
			}
			//导购员
			if(!"".equals(shopNum)){
				String SHOPUID = StringUtil.uuid32len();
				params.put("SHOPP_GUIDE_TRAN_ID", SHOPUID);
				params.put("MKM_DAY_RPT_ID", UID);
				this.insert("shoppGuideTran.insert", params);
				
				Map<String, String> attParamst = new HashMap<String, String>();
				attParamst.put("FROM_BILL_ID", SHOPUID);
				attParamst.put("ATT_PATH", shopModel.getTRAN_PIC());// 图片路径
				attParamst.put("ATT_ID", StringUtil.uuid32len());
				attParamst.put("CREATOR", params.get("CREATOR"));
				attParamst.put("CRE_NAME", params.get("CRE_NAME"));
				attParamst.put("CRE_TIME", DateUtil.now());// 制单时间
				attParamst.put("DEL_FLAG", params.get("DEL_FLAG"));
				insert("Att.insertAtt", attParamst);
			}
			//门店拜访表
			if(!"".equals(termModel.getTERM_NO())){
				params.put("DEL_FLAG", "0");
				params.put("TERMINAL_VISIT_ID", UID);
				params.put("MKM_DAY_RPT_ID", UID);
				this.insert("terminalVisit.insert", params);
			}
		 }

		} else {
					params.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
					params.put("UPDATOR", userBean.getXTYHID());// 更新人id
					params.put("UPD_NAME", userBean.getXM());// 更新人名称
					params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);// 更新时间
					this.update("mkmdayreport.updateById", params);
				 if(!"".equals(cvModel.getCHANN_NO())){
					String CHANN_VISIT_DAY_ID = queryChannId(MKM_DAY_RPT_ID);
					if(CHANN_VISIT_DAY_ID !=null){
						cvParams.put("CHANN_VISIT_DAY_ID", MKM_DAY_RPT_ID);
						this.update("channvisitday.updateById", cvParams);
						Map<String, String> attParams = new HashMap<String, String>();
						attParams.put("FROM_BILL_ID", MKM_DAY_RPT_ID);
						attParams.put("ATT_PATH", cvModel.getSTORE_ATT());// 图片路径
						update("Att.updateAtt", attParams);
					}else{
						
						cvParams.put("CHANN_VISIT_DAY_ID", UID);
						cvParams.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
						cvParams.put("DEL_FLAG", "0");
						this.insert("channvisitday.insert", cvParams);
						Map<String, String> attParams = new HashMap<String, String>();
						attParams.put("FROM_BILL_ID", UID);
						attParams.put("ATT_PATH", cvModel.getSTORE_ATT());// 图片路径
						attParams.put("ATT_ID", StringUtil.uuid32len());
						attParams.put("CREATOR", params.get("CREATOR"));
						attParams.put("CRE_NAME", params.get("CRE_NAME"));
						attParams.put("CRE_TIME", DateUtil.now());// 制单时间
						attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
						insert("Att.insertAtt", attParams);
					}
		     	}
			//推广活动推进
			if(!"".equals(actNum)){
				String PROMOTION_ACTV_ID = queryActvId(MKM_DAY_RPT_ID);
				if(PROMOTION_ACTV_ID !=null){
					params.put("PROMOTION_ACTV_ID", PROMOTION_ACTV_ID);
					this.update("Promotionactv.updateById", params);
					Map<String, String> attParamsT = new HashMap<String, String>();
					attParamsT.put("FROM_BILL_ID", PROMOTION_ACTV_ID);
					attParamsT.put("ATT_PATH", actModel.getACTV_PRMT_ATT());// 图片路径
					update("Att.updateAtt", attParamsT);
				}else{
					String UIDT = StringUtil.uuid32len();
					params.put("PROMOTION_ACTV_ID", UIDT);
					params.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
					this.insert("Promotionactv.insert", params);
					Map<String, String> attParamsT = new HashMap<String, String>();
					attParamsT.put("FROM_BILL_ID", UIDT);
					attParamsT.put("ATT_PATH", actModel.getACTV_PRMT_ATT());// 图片路径
					attParamsT.put("ATT_ID", StringUtil.uuid32len());
					attParamsT.put("CREATOR", params.get("CREATOR"));
					attParamsT.put("CRE_NAME", params.get("CRE_NAME"));
					attParamsT.put("CRE_TIME", DateUtil.now());// 制单时间
					attParamsT.put("DEL_FLAG", params.get("DEL_FLAG"));
					insert("Att.insertAtt", attParamsT);
				}
			}

			//分销商
			if(!"".equals(disNum)){
				String DISTRIBUTOR_VISIT_ID = queryDisId(MKM_DAY_RPT_ID);
				if(DISTRIBUTOR_VISIT_ID !=null){
					disParams.put("DISTRIBUTOR_VISIT_ID", DISTRIBUTOR_VISIT_ID);
					this.update("distributorvisit.updateById", disParams);
				}else{
					disParams.put("DISTRIBUTOR_VISIT_ID", UID);
					disParams.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
					disParams.put("DEL_FLAG", "0");
					this.insert("distributorvisit.insert", disParams);
				}
			}
			
			//合作商
			if(!"".equals(cooNum)){
				String COOPERATIVE_VISIT_ID = queryCoopId(MKM_DAY_RPT_ID);
				if(COOPERATIVE_VISIT_ID !=null){
					cooParams.put("COOPERATIVE_VISIT_ID", COOPERATIVE_VISIT_ID);
					this.update("cooperativevisit.updateById",cooParams);
				}else{
					cooParams.put("COOPERATIVE_VISIT_ID", UID);
					cooParams.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
					cooParams.put("DEL_FLAG", "0");
					this.insert("cooperativevisit.insert", cooParams);
				}
			}
			
			//导购员
			if(!"".equals(shopNum)){
				String SHOPP_GUIDE_TRAN_ID = queryTranId(MKM_DAY_RPT_ID);
				if(SHOPP_GUIDE_TRAN_ID!=null){
				    params.put("SHOPP_GUIDE_TRAN_ID", SHOPP_GUIDE_TRAN_ID);
				    this.update("shoppGuideTran.updateById", params);
				    Map<String, String> attParamst = new HashMap<String, String>();
					attParamst.put("FROM_BILL_ID", SHOPP_GUIDE_TRAN_ID);
					attParamst.put("ATT_PATH", shopModel.getTRAN_PIC());// 图片路径
					update("Att.updateAtt", attParamst);
				}else{
					String SHOPUID = StringUtil.uuid32len();
					params.put("SHOPP_GUIDE_TRAN_ID", SHOPUID);
					params.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
					this.insert("shoppGuideTran.insert", params);
					
					Map<String, String> attParamst = new HashMap<String, String>();
					attParamst.put("FROM_BILL_ID", SHOPUID);
					attParamst.put("ATT_PATH", shopModel.getTRAN_PIC());// 图片路径
					attParamst.put("ATT_ID", StringUtil.uuid32len());
					attParamst.put("CREATOR", params.get("CREATOR"));
					attParamst.put("CRE_NAME", params.get("CRE_NAME"));
					attParamst.put("CRE_TIME", DateUtil.now());// 制单时间
					attParamst.put("DEL_FLAG", params.get("DEL_FLAG"));
					insert("Att.insertAtt", attParamst);
				}
			}
			
			//门店拜访
			if(!"".equals(termModel.getTERM_NO())){
				String TERMINAL_VISIT_ID = queryTermId(MKM_DAY_RPT_ID);
				if(TERMINAL_VISIT_ID !=null ){
					params.put("TERMINAL_VISIT_ID", MKM_DAY_RPT_ID);
					update("terminalVisit.updateById", params);
				}else{
					params.put("DEL_FLAG", "0");
					params.put("TERMINAL_VISIT_ID", UID);
					params.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
					this.insert("terminalVisit.insert", params);
				}
			}
		// }
		}
		return true;
	}

	/**
	 * 分页查询 Description :.
	 * 
	 * @param params
	 *            the params
	 * @param pageNo
	 *            the page no
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("mkmdayreport.pageQuery",
				"mkmdayreport.pageCount", params, pageNo);
	}
	
	/**
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQueryWH(Map<String, String> params, int pageNo) {
		return this.pageQuery("mkmdayreport.pageQueryT",
				"mkmdayreport.pageCountT", params, pageNo);
	}

	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> load(String MKM_DAY_RPT_ID) {
		return (Map<String, String>) load("mkmdayreport.loadById",
				MKM_DAY_RPT_ID);
	}

	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByChannVist(String MKM_DAY_RPT_ID) {
		return (Map<String, String>) load("channvisitday.loadById",
				MKM_DAY_RPT_ID);
	}

	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByProActv(String MKM_DAY_RPT_ID) {
		return (Map<String, String>) load("Promotionactv.loadById",
				MKM_DAY_RPT_ID);
	}

	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByDisVisit(String MKM_DAY_RPT_ID) {
		return (Map<String, String>) load("distributorvisit.loadById",
				MKM_DAY_RPT_ID);
	}
	
	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByShopTran(String MKM_DAY_RPT_ID){
		return (Map<String, String>) load("shoppGuideTran.loadById",
				MKM_DAY_RPT_ID);
	}
	
	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByByTerm(String MKM_DAY_RPT_ID){
		return (Map<String, String>) load("terminalVisit.loadById",
				MKM_DAY_RPT_ID);
	}

	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByCooVisit(String MKM_DAY_RPT_ID){
		return (Map<String, String>) load("cooperativevisit.loadById",
				MKM_DAY_RPT_ID);
	}
	/**
	 * @param MKM_DAY_RPT_ID
	 * @param userBean
	 * @return
	 */
	public boolean txDelete(String MKM_DAY_RPT_ID, UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
		params.put("UPDATOR", userBean.getXTYHID());// 更新人id
		params.put("UPD_NAME", userBean.getXM());// 更新人名称
		params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);// 更新时间
		return update("mkmdayreport.delete", params);
	}

	public Map<String, String> loadByAMId(String RYYXXID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("RYYXXID", RYYXXID);
		return (Map<String, String>) this.load("mkmdayreport.loadByAMId",
				params);
	}

	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public String queryActvId(String MKM_DAY_RPT_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
		String str = (String) load("Promotionactv.queryActvId", params);
		return str;
	}

	public String queryDisId(String MKM_DAY_RPT_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
		String str = (String) load("distributorvisit.queryDisId", params);
		return str;
	}
	
	public String queryTranId(String MKM_DAY_RPT_ID){
		Map<String, String> params = new HashMap<String, String>();
		params.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
		String str = (String) load("shoppGuideTran.queryTranId", params);
		return str;
	}
	
	public String queryTermId(String MKM_DAY_RPT_ID){
		Map<String, String> params = new HashMap<String, String>();
		params.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
		String str = (String) load("terminalVisit.queryTermId", params);
		return str;
	}
	
	public String queryCoopId(String MKM_DAY_RPT_ID){
		Map<String, String> params = new HashMap<String, String>();
		params.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
		String str = (String) load("cooperativevisit.queryCoopId", params);
		return str;
	}
	
	public String queryChannId(String MKM_DAY_RPT_ID){
		Map<String, String> params = new HashMap<String, String>();
		params.put("MKM_DAY_RPT_ID", MKM_DAY_RPT_ID);
		String str = (String) load("channvisitday.queryChannId", params);
		return str;
	}
	
	 public  int  paramsCount(String MKM_NAME,String VST_DATE){
	    	Map<String, String> params = new HashMap<String, String>();
	    	params.put("MKM_NAME", MKM_NAME);
	    	params.put("VST_DATE", VST_DATE);
	    	return queryForInt("mkmdayreport.paramsCount",params);
	}
	
	/**
	 * 推广活动推进
	 * @param actModel
	 * @return
	 */
	public String getACVModel(PromotionActvModel actModel){
	   if(!"".equals(actModel.getBEG_DATE())){
		   return "1";
	   }
	   if(!"".equals(actModel.getACTV_STYLE())){
		   return "1";
	   }
	   if(!"".equals(actModel.getACTV_TITLE())){
		   return "1";
	   }
	   if(!"".equals(actModel.getAGREE_DATE())){
		   return "1";
	   }
	   if(!"".equals(actModel.getAGREE_ADDR())){
		   return "1";
	   }
	   if(!"".equals(actModel.getACTV_PRMT_COST())){
		   return "1";
	   }
	   if(!"".equals(actModel.getADVC_DEAL_BILL_AMOUNT())){
		   return "1";
	   }
	   if(!"".equals(actModel.getADVC_DEAL_BILL_NUM())){
		   return "1";
	   }
	   if(!"".equals(actModel.getACTV_PRMT_ATT())){
		   return "1";
	   }
	   if(!"".equals(actModel.getFORWORD_DATE())){
		   return "1";
	   }
	   if(!"".equals(actModel.getSALE_CARD_NUM())){
		   return "1";
	   }
	   if(!"".equals(actModel.getOTHER_REMARK())){
		   return "1";
	   }
	   if(!"".equals(actModel.getSALE_CARD_NUM_END())){
		   return "1";
	   }
	   if(!"".equals(actModel.getCHECK_CARD_NUM())){
		   return "1";
	   }
	   if(!"".equals(actModel.getDEAL_BILL_NUM())){
		   return "1";
	   }
	   if(!"".equals(actModel.getADVC_DEAL_AMOUNT())){
		   return "1";
	   }
	   if(!"".equals(actModel.getCOST_AMOUNT())){
		   return "1";
	   }
	   return "";
	}
	
	/**
	 * 分销商开发与拜访
	 * @param disModel
	 * @return
	 */
	public String getDISModel(DistributorVisitModel disModel){
		   if(!"".equals(disModel.getDISTRIBUTOR_NO_T())){
			   return "1";
		   }
		   if(!"".equals(disModel.getDISTRIBUTOR_NAME_T())){
			   return "1";
		   }
		   if(!"".equals(disModel.getMAIN_PROBLEM_T())){
			   return "1";
		   }
		   if(!"".equals(disModel.getSOLUTION_T())){
			   return "1";
		   }
		   if(!"".equals(disModel.getWILL_DISTRIBUTOR_TYPE())){
			   return "1";
		   }
		   if(!"".equals(disModel.getWILL_SALE_STORE_NAME_T())){
			   return "1";
		   }
		   if(!"".equals(disModel.getWILL_DISTRIBUTOR_NAME())){
			   return "1";
		   }
		   if(!"".equals(disModel.getWILL_DISTRIBUTOR_TEL())){
			   return "1";
		   }
		   if(!"".equals(disModel.getWILL_DISTRIBUTOR_ADDRESS())){
			   return "1";
		   }
		   if(!"".equals(disModel.getWILL_DISTRIBUTOR_BUSS_BRAND())){
			   return "1";
		   }
		   if(!"".equals(disModel.getWILL_DISTRIBUTOR_BUSS_CLASS())){
			   return "1";
		   }
		   if(!"".equals(disModel.getWILL_DEGREE_T())){
			   return "1";
		   }
		   return "";
		}
	  
	/**
	 * 合作商开发与拜访
	 * @param cooModel
	 * @return
	 */
	  public String getCOOModel(CooperativeVisitModel cooModel){
	   if(!"".equals(cooModel.getDISTRIBUTOR_NO())){
		   return "1";
	   }
	   if(!"".equals(cooModel.getDISTRIBUTOR_NAME())){
		   return "1";
	   }
	   if(!"".equals(cooModel.getMAIN_PROBLEM())){
		   return "1";
	   }
	   if(!"".equals(cooModel.getSOLUTION())){
		   return "1";
	   }
	   if(!"".equals(cooModel.getMARKET_TYPE())){
		   return "1";
	   }
	   if(!"".equals(cooModel.getWILL_SALE_STORE_NAME())){
		   return "1";
	   }
	   if(!"".equals(cooModel.getCUST_NAME())){
		   return "1";
	   }
	   if(!"".equals(cooModel.getCUST_TEL())){
		   return "1";
	   }
	   if(!"".equals(cooModel.getMARKET_ADDRESS())){
		   return "1";
	   }
	   if(!"".equals(cooModel.getMARKET_BUSS_BRAND())){
		   return "1";
	   }
	   if(!"".equals(cooModel.getMARKET_BUSS_CLASS())){
		   return "1";
	   }
	   if(!"".equals(cooModel.getWILL_DEGREE())){
		   return "1";
	   }
	   if(!"".equals(cooModel.getNO_WILL_RESON())){
		   return "1";
	   }
	   return "";
	  }
	  
	   /**
	    * 导购员培训
	    * @param cooModel
	    * @return
	    */
	   public String getShopModel(ShoppGuideTranModel shopModel){
		   if(!"".equals(shopModel.getTRAN_NUM())){
			   return "1";
		   }
		   if(!"".equals(shopModel.getTRAN_TITLE())){
			   return "1";
		   }
		   if(!"".equals(shopModel.getTRAN_TYPE())){
			   return "1";
		   }
		   if(!"".equals(shopModel.getTRAN_DATE())){
			   return "1";
		   }
		   if(!"".equals(shopModel.getTRAN_PIC())){
			   return "1";
		   }
		   return "";
		}
}
