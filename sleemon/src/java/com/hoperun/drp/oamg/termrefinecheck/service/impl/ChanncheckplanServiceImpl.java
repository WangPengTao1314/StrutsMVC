package com.hoperun.drp.oamg.termrefinecheck.service.impl;

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
import com.hoperun.drp.oamg.termrefinecheck.model.ChanncheckplanModel;
import com.hoperun.drp.oamg.termrefinecheck.model.ChanncheckplanModelChild;
import com.hoperun.drp.oamg.termrefinecheck.service.ChanncheckplanService;
import com.hoperun.drp.oamg.workplanmage.model.WorkplanmagedtlModel;
import com.hoperun.sys.model.UserBean;

public class ChanncheckplanServiceImpl extends BaseService implements ChanncheckplanService {

	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Termrefinecheck.pageQuery",
				"Termrefinecheck.pageCount", params, pageNo);
	}
	

	/**
	 * * 根据主表Id查询子表记录
	 * @param TERM_REFINE_CHECK_ID
	 *            the TERM_REFINE_CHECK_ID
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ChanncheckplanModelChild> childQuery(String CHANN_CHECK_PLAN_ID) {
		Map params = new HashMap();
		params.put("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Termrefinecheck.queryChldByFkId", params);
	}
	
	public Map<String, String> loadByConf(String param) {
		return (Map<String, String>) load("Termrefinecheck.loadByConf", param);
	}
	
	/**
	 * @param TERM_REFINE_CHECK_ID
	 * @return
	 */
	public Map<String,String> loadByConfT(String CHANN_CHECK_PLAN_ID){
		return (Map<String, String>) load("Termrefinecheck.loadByConfT", CHANN_CHECK_PLAN_ID);
	}
	
    /**
     * 查询主表单条记录详细信息
     * Description :.
     * @param bmgzid the bmgzid
     * @return the map< string, string>
     */
    public Map <String, String> load(String CHANN_CHECK_PLAN_ID){
    	return (Map <String, String>) load("Termrefinecheck.loadById", CHANN_CHECK_PLAN_ID);
    }
    
    /**
     * 状态修改.
     * @param params the params
     */
    public void updateState(Map <String, String> params){
    	updateById(params);
    }
    
    /**
     * 更新主表记录.
     * @param params the params
     * @return update的结果
     */
    public boolean updateById(Map <String, String> params) {
        return update("Termrefinecheck.updateById", params);
    }
    
    /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String CHANN_CHECK_PLAN_ID, UserBean userBean){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
		return update("Termrefinecheck.deleteBy", params);
    }
    
	 /**
	 * @主从保存编辑
	 * @Description :
	 * @param TERM_REFINE_CHECK_ID
	 * @param TermrefinecheckModel
	 * @param userBean.
	 * @return 
	 */
	public String txEdit(String CHANN_CHECK_PLAN_ID, ChanncheckplanModel obj,List<ChanncheckplanModelChild> mxList, UserBean userBean,String chkLen){
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> paramsT = new HashMap<String, String>();

		params.put("CHANN_CHECK_PLAN_NAME", obj.getCHANN_CHECK_PLAN_NAME());
		params.put("PLAN_TYPE", obj.getPLAN_TYPE());
		params.put("CHECK_DATE_BEG", obj.getCHECK_DATE_BEG());
		params.put("CHECK_DATE_END", obj.getCHECK_DATE_END());
		params.put("REMARK", obj.getREMARK());

		if (mxList != null) {
			for (int i = 0; i < mxList.size() - 1; i++) {
				for (int j = mxList.size() - 1; j > i; j--) {
					if (mxList.get(i).getPRJ_CODE().equals(
							mxList.get(j).getPRJ_CODE())
							&& mxList.get(i).getPRJ_NAME().equals(
									mxList.get(j).getPRJ_NAME())) {
						return "1";
					}
				}
			}
		}

		if (StringUtil.isEmpty(CHANN_CHECK_PLAN_ID)) {
			
			String  UID = StringUtil.uuid32len();
			CHANN_CHECK_PLAN_ID = UID;
			params.put("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
			params.put("CHANN_CHECK_PLAN_NO", LogicUtil
					.getBmgz("ERP_CHANN_CHECK_PLAN_NO"));
			params.put("CRE_NAME", userBean.getXM());
			params.put("CREATOR", userBean.getXTYHID());
			params.put("DEPT_ID", userBean.getBMXXID());
			params.put("DEPT_NAME", userBean.getBMMC());
			params.put("ORG_ID", userBean.getJGXXID());
			params.put("ORG_NAME", userBean.getJGMC());
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
			params.put("LEDGER_NAME", userBean.getLoginZTMC());
			params.put("STATE",   "制定");
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			txInsert(params);
			
			String att_path = loadAtt(UID);
			Map <String, String> attParams = new HashMap <String, String>();
			attParams.put("FROM_BILL_ID", UID);
			attParams.put("ATT_PATH",obj.getPIC_PATH());//图片路径
			if(StringUtils.isEmpty(att_path)){			
				attParams.put("ATT_ID", StringUtil.uuid32len());						
				attParams.put("CREATOR", params.get("CREATOR"));
				attParams.put("CRE_NAME", params.get("CRE_NAME"));
				attParams.put("CRE_TIME", DateUtil.now());//制单时间
				attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
				insert("Att.insertAtt", attParams);
			}

			if (mxList != null) {
				for (int i = 0; i < mxList.size(); i++) {
					paramsT.put("CHANN_CHECK_PLAN_DTL_ID", StringUtil
							.uuid32len());
					paramsT.put("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
					paramsT.put("PRJ_TYPE", mxList.get(i).getPRJ_TYPE()); // 验收项目类型
					paramsT.put("PRJ_CODE", mxList.get(i).getPRJ_CODE());
					paramsT.put("PRJ_NAME", mxList.get(i).getPRJ_NAME()); // 验收项目名称
					paramsT.put("PRJ_SCORE",mxList.get(i).getPRJ_SCORE()); // 项目分值
					paramsT.put("DEL_FLAG", "0");
					List<ChanncheckplanModelChild> result = queryTypeAndName(
							mxList.get(i).getPRJ_CODE().toString(), mxList.get(
									i).getPRJ_NAME().toString(),
									CHANN_CHECK_PLAN_ID);
					if (result.size() == 0) {
						this.insert("Termrefinecheck.insertChld", paramsT);
					} else {
						return "1";
					}
				}
			}

		} else {
			params.put("UPD_NAME", userBean.getXM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_TIME", "sysdate");
			params.put("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
			txUpdateById(params);
			clearCaches(CHANN_CHECK_PLAN_ID);
			if (mxList != null) {
				if (!chkLen.equals(mxList.size())) {
					List<ChanncheckplanModelChild> list = queryDtlByReformId(CHANN_CHECK_PLAN_ID);
					for (int j = 0; j < list.size(); j++) {
						ChanncheckplanModelChild chkModel = (ChanncheckplanModelChild) list
								.get(j);
						String CHANN_CHECK_PLAN_DTL_ID = chkModel.getCHANN_CHECK_PLAN_DTL_ID().toString();
						this.txDeleteChild(CHANN_CHECK_PLAN_DTL_ID);
					}
				}
				for (int i = 0; i < mxList.size(); i++) {
					paramsT.put("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
					paramsT.put("PRJ_TYPE", mxList.get(i).getPRJ_TYPE());
					paramsT.put("PRJ_CODE", mxList.get(i).getPRJ_CODE());
					paramsT.put("PRJ_NAME", mxList.get(i).getPRJ_NAME());
					paramsT.put("PRJ_SCORE", mxList.get(i).getPRJ_SCORE());
					paramsT.put("DEL_FLAG", "0");

					// 子表信息保存
					List<ChanncheckplanModelChild> result = queryTypeAndName(
							mxList.get(i).getPRJ_CODE().toString(), mxList.get(
									i).getPRJ_NAME().toString(),
									CHANN_CHECK_PLAN_ID);
					if (StringUtils.isEmpty(mxList.get(i)
							.getCHANN_CHECK_PLAN_DTL_ID())) {
						if (result.size() == 0) {
							paramsT.put("CHANN_CHECK_PLAN_DTL_ID", StringUtil
									.uuid32len());
							this.insert("Termrefinecheck.insertChld", paramsT);
						} else {
							return "1";
						}
					} else {
						if (!chkLen.equals(mxList.size())) {
							paramsT.put("CHANN_CHECK_PLAN_DTL_ID", StringUtil
									.uuid32len());
							this.insert("Termrefinecheck.insertChld", paramsT);
						} else {
							paramsT.put("CHANN_CHECK_PLAN_DTL_ID", mxList.get(
									i).getCHANN_CHECK_PLAN_DTL_ID());
							this.insert("Termrefinecheck.updateChldById",
									paramsT);
						}
					}
				}
			}
		}
		return "0";
	}
	
	/**
	 * 加载上传文件.
	 * @param param
	 * @return
	 */
	public String loadAtt(String param){		
		Map<String, String> params = new HashMap<String, String>();
        params.put("FROM_BILL_ID", param);
        return (String) load("Att.loadAtt", params);
	}
	
	/**
	 * 查询精致化明细中是否存在重复记录
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public List<ChanncheckplanModelChild> queryTypeAndName(String code,
			String name, String CHANN_CHECK_PLAN_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("PRJ_CODE", code);
		params.put("PRJ_NAME", name);
		params.put("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
		return this.findList("Termrefinecheck.queryTypeAndName", params);
	}

	
	/**
	 * * 增加 * @param params
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String, String> params) {
		insert("Termrefinecheck.insert", params);
		return true;
	}
	
	/**
	 * * update data * @param params
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String, String> params) {
		return update("Termrefinecheck.updateById", params);
	}
	
	/**
	 * @param RNVTN_REFORM_ID
	 * @return
	 */
	public List<ChanncheckplanModelChild> queryDtlByReformId(
			String CHANN_CHECK_PLAN_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
		params.put("DEL_FLAG", "0");
		return this.findList("Termrefinecheck.loadByChkId", params);
	}
	
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public List <ChanncheckplanModelChild> queryJudgeModel(String CHANN_CHECK_PLAN_ID){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
    	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
     	List<ChanncheckplanModelChild> list = this.findList("Termrefinecheck.selectBrothers", paramMap);
     	return list;
    }
	
	/**
	 * @param TERM_REFINE_CHECK_DTL_ID
	 */
	public void txDeleteChild(String CHANN_CHECK_PLAN_DTL_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("CHANN_CHECK_PLAN_DTL_ID", CHANN_CHECK_PLAN_DTL_ID);
		params.put("DEL_FLAG", "1");
		delete("Termrefinecheck.deletebyCheck", params);
	}
	
	public String queryIdByNo(String CHANN_CHECK_PLAN_NO){
		 return (String) load("Termrefinecheck.queryPro", CHANN_CHECK_PLAN_NO);
    }
}
