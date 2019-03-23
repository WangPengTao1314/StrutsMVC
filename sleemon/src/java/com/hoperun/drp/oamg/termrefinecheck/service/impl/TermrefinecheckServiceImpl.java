/**
 * prjName:喜临门营销平台
 * ucName:门店精致化检查结果
 * fileName:TermrefinecheckServiceImpl
 */
package com.hoperun.drp.oamg.termrefinecheck.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.termrefinecheck.model.TermrefinecheckModel;
import com.hoperun.drp.oamg.termrefinecheck.model.TermrefinecheckModelChld;
import com.hoperun.drp.oamg.termrefinecheck.service.TermrefinecheckService;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl *@func *@version 1.1 *@author lyg *@createdate 2014-01-26
 * 14:46:31
 */
public class TermrefinecheckServiceImpl extends BaseService implements
		TermrefinecheckService {
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
	 * * 增加 * @param params
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String, String> params) {
		insert("Termrefinecheck.insert", params);
		return true;
	}

	/**
	 * @删除
	 * @param TERM_REFINE_CHECK_ID
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map<String, String> params) {
		// 删除父
		update("Termrefinecheck.delete", params);
		// 删除子表
		return update("Termrefinecheck.delChldByFkId", params);
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
	 * @编辑
	 * @Description :
	 * @param TERM_REFINE_CHECK_ID
	 * @param TermrefinecheckModel
	 * @param userBean
	 * @return true, if tx txEdit
	 */
	public String txEdit(String TERM_REFINE_CHECK_ID,
			TermrefinecheckModel model, List<TermrefinecheckModelChld> mxList,
			UserBean userBean, String chkLen) {

		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> paramsT = new HashMap<String, String>();

		params.put("TERM_ID", model.getTERM_ID());
		params.put("AREA_NO", model.getAREA_NO());
		params.put("AREA_ID", model.getAREA_ID());
		params.put("CHANN_ID", model.getCHANN_ID());
		params.put("CHANN_NO", model.getCHANN_NO());
		params.put("CHANN_NAME", model.getCHANN_NAME());
		params.put("CHECK_ORG_ID", model.getCHECK_ORG_ID());
		params.put("TERM_REFINE_TASK_NO", model.getTERM_REFINE_TASK_NO());
		params.put("AREA_MANAGE_ID", model.getAREA_MANAGE_ID());
		params.put("TERM_NO", model.getTERM_NO());
		params.put("TERM_NAME", model.getTERM_NAME());
		params.put("BUSS_SCOPE", model.getBUSS_SCOPE());
		params.put("AREA_NAME", model.getAREA_NAME());
		params.put("AREA_MANAGE_NAME", model.getAREA_MANAGE_NAME());
		params.put("CHECK_ORG_NAME", model.getCHECK_ORG_NAME());
		params.put("CHECK_DATE", model.getCHECK_DATE());
		params.put("REMARK", model.getREMARK());
		params.put("CHECK_TOTAL_SCORE", model.getCHECK_TOTAL_SCORE());
		params.put("CHECK_PROGRAM_NO", model.getCHECK_PROGRAM_NO());
		params.put("MYSTIC_CMNR", model.getMYSTIC_CMNR());
		params.put("MAIN_DEDUCT_SCORE_REMARK", model
				.getMAIN_DEDUCT_SCORE_REMARK());

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

		if (StringUtil.isEmpty(TERM_REFINE_CHECK_ID)) {
			TERM_REFINE_CHECK_ID = StringUtil.uuid32len();
			params.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
			params.put("TERM_REFINE_CHECK_NO", LogicUtil
					.getBmgz("DRP_TERM_REFINE_CHECK_NO"));
			params.put("CRE_NAME", userBean.getXM());
			params.put("CREATOR", userBean.getXTYHID());
			params.put("DEPT_ID", userBean.getBMXXID());
			params.put("DEPT_NAME", userBean.getBMMC());
			params.put("ORG_ID", userBean.getJGXXID());
			params.put("ORG_NAME", userBean.getJGMC());
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
			params.put("LEDGER_NAME", userBean.getLoginZTMC());
			params.put("STATE", BusinessConsts.UNCOMMIT);
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			txInsert(params);
			
			String att_path = loadAtt(TERM_REFINE_CHECK_ID);
			Map <String, String> attParams = new HashMap <String, String>();
			attParams.put("FROM_BILL_ID", TERM_REFINE_CHECK_ID);
			attParams.put("ATT_PATH", model.getPIC_PATH());//图片路径
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
					paramsT.put("TERM_REFINE_CHECK_DTL_ID", StringUtil
							.uuid32len());
					paramsT.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
					paramsT.put("PRJ_TYPE", mxList.get(i).getPRJ_TYPE()); // 验收项目类型
					paramsT.put("PRJ_CODE", mxList.get(i).getPRJ_CODE());
					paramsT.put("PRJ_NAME", mxList.get(i).getPRJ_NAME()); // 验收项目名称
					paramsT.put("PRJ_SCORE", mxList.get(i).getPRJ_SCORE()); // 项目分值
					paramsT.put("CHECK_SCORE", mxList.get(i).getCHECK_SCORE());
					paramsT
							.put("CHECK_REMARK", mxList.get(i)
									.getCHECK_REMARK());
					paramsT.put("DEL_FLAG", "0");
					List<TermrefinecheckModelChld> result = queryTypeAndName(
							mxList.get(i).getPRJ_CODE().toString(), mxList.get(
									i).getPRJ_NAME().toString(),
							TERM_REFINE_CHECK_ID);
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
			params.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
			txUpdateById(params);
			clearCaches(TERM_REFINE_CHECK_ID);
			if (mxList != null) {
				if (!chkLen.equals(mxList.size())) {
					List<TermrefinecheckModelChld> list = queryDtlByReformId(TERM_REFINE_CHECK_ID);
					for (int j = 0; j < list.size(); j++) {
						TermrefinecheckModelChld chkModel = (TermrefinecheckModelChld) list
								.get(j);
						String TERM_REFINE_CHECK_DTL_ID = chkModel
								.getTERM_REFINE_CHECK_DTL_ID().toString();
						this.txDeleteChild(TERM_REFINE_CHECK_DTL_ID);
					}
				}
				for (int i = 0; i < mxList.size(); i++) {
					paramsT.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
					paramsT.put("PRJ_TYPE", mxList.get(i).getPRJ_TYPE());
					paramsT.put("PRJ_CODE", mxList.get(i).getPRJ_CODE());
					paramsT.put("PRJ_NAME", mxList.get(i).getPRJ_NAME());
					paramsT.put("PRJ_SCORE", mxList.get(i).getPRJ_SCORE());
					paramsT.put("CHECK_SCORE", mxList.get(i).getCHECK_SCORE());
					paramsT
							.put("CHECK_REMARK", mxList.get(i)
									.getCHECK_REMARK());
					paramsT.put("DEL_FLAG", "0");

					// 子表信息保存
					List<TermrefinecheckModelChld> result = queryTypeAndName(
							mxList.get(i).getPRJ_CODE().toString(), mxList.get(
									i).getPRJ_NAME().toString(),
							TERM_REFINE_CHECK_ID);
					if (StringUtils.isEmpty(mxList.get(i)
							.getTERM_REFINE_CHECK_DTL_ID())) {
						if (result.size() == 0) {
							paramsT.put("TERM_REFINE_CHECK_DTL_ID", StringUtil
									.uuid32len());
							this.insert("Termrefinecheck.insertChld", paramsT);
						} else {
							return "1";
						}
					} else {
						if (!chkLen.equals(mxList.size())) {
							paramsT.put("TERM_REFINE_CHECK_DTL_ID", StringUtil
									.uuid32len());
							this.insert("Termrefinecheck.insertChld", paramsT);
						} else {
							paramsT.put("TERM_REFINE_CHECK_DTL_ID", mxList.get(
									i).getTERM_REFINE_CHECK_DTL_ID());
							this.insert("Termrefinecheck.updateChldById",
									paramsT);
						}
					}
				}
			}
			Map <String, String> attParamsT = new HashMap <String, String>();
			attParamsT.put("FROM_BILL_ID",TERM_REFINE_CHECK_ID);
			attParamsT.put("ATT_PATH", model.getPIC_PATH());//图片路径
			update("Att.updateAtt", attParamsT);
		}
		return "0";
	}

	/**
	 * @param RNVTN_REFORM_ID
	 * @return
	 */
	public List<TermrefinecheckModelChld> queryDtlByReformId(
			String TERM_REFINE_CHECK_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
		params.put("DEL_FLAG", "0");
		return this.findList("Termrefinecheck.loadByChkId", params);
	}

	/**
	 * 查询精致化明细中是否存在重复记录
	 * 
	 * @param type
	 * @param name
	 * @return
	 */
	public List<TermrefinecheckModelChld> queryTypeAndName(String code,
			String name, String TERM_REFINE_CHECK_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("PRJ_CODE", code);
		params.put("PRJ_NAME", name);
		params.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
		return this.findList("Termrefinecheck.queryTypeAndName", params);
	}

	public List<TermrefinecheckModelChld> queryDtlByCheck(
			String TERM_REFINE_CHECK_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
		params.put("DEL_FLAG", "0");
		return this.findList("Termrefinecheck.queryTermcheckDtl", params);
	}

	/**
	 * @param RNVTN_REFORM_ID
	 * @return
	 */
	public List<TermrefinecheckModelChld> queryDtlByCheckId(
			String RNVTN_REFORM_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
		params.put("DEL_FLAG", "0");
		return this.findList("rnvtnreformMX.loadById", params);
	}

	/**
	 * @详细
	 * @param param
	 *            TERM_REFINE_CHECK_ID
	 * @param param
	 *            the param
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String param) {
		return (Map<String, String>) load("Termrefinecheck.loadById", param);
	}

	public Map<String, String> loadByConf(String param) {
		return (Map<String, String>) load("Termrefinecheck.loadByConf", param);
	}

	/**
	 * * 明细数据编辑
	 * 
	 * @param TERM_REFINE_CHECK_ID
	 *            the TERM_REFINE_CHECK_ID
	 * @param modelList
	 *            the model list
	 * @return true, if tx child edit
	 */
	@Override
	public String txChildEdit(String TERM_REFINE_CHECK_ID,
			List<TermrefinecheckModelChld> chldList) {

		// 新增列表
		List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
		// 修改列表
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		String error = null;
		for (TermrefinecheckModelChld model : chldList) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRJ_TYPE", model.getPRJ_TYPE());
			params.put("PRJ_CODE", model.getPRJ_CODE());
			params.put("PRJ_NAME", model.getPRJ_NAME());
			params.put("PRJ_SCORE", model.getPRJ_SCORE());
			params.put("CHECK_REMARK", model.getCHECK_REMARK());
			params.put("CHECK_SCORE", model.getCHECK_SCORE());
			params.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
			// 如果没有明细ID的则是新增，有的是修改
			if (StringUtil.isEmpty(model.getTERM_REFINE_CHECK_DTL_ID())) {
				params.put("TERM_REFINE_CHECK_DTL_ID", StringUtil.uuid32len());
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);

				int cnt = this.queryForInt("Termrefinecheck.checkRepeatPrjNo",
						params);// 判断项目编号是否重复
				if (cnt > 0) {
					error = "项目编号'" + params.get("PRJ_CODE") + "'重复!";
					return error;
				} else {
					addList.add(params);
				}
			} else {
				params.put("TERM_REFINE_CHECK_DTL_ID", model
						.getTERM_REFINE_CHECK_DTL_ID());
				updateList.add(params);
			}
		}
		if (!updateList.isEmpty()) {
			this.batch4Update("Termrefinecheck.updateChldById", updateList);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Termrefinecheck.insertChld", addList);
		}

		// 反填主表的精致化得分字段
		this
				.update("Termrefinecheck.updateScoreByMainId",
						TERM_REFINE_CHECK_ID);

		return error;
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param TERM_REFINE_CHECK_ID
	 *            the TERM_REFINE_CHECK_ID
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<TermrefinecheckModelChld> childQuery(String TERM_REFINE_CHECK_ID) {
		Map params = new HashMap();
		params.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Termrefinecheck.queryChldByFkId", params);
	}

	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param TERM_REFINE_CHECK_DTL_IDs
	 *            the TERM_REFINE_CHECK_DTL_IDs
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<TermrefinecheckModelChld> loadChilds(Map<String, String> params) {
		return findList("Termrefinecheck.loadChldByIds", params);
	}

	/**
	 * * 子表批量删除:软删除
	 * 
	 * @param TERM_REFINE_CHECK_DTL_IDs
	 *            the TERM_REFINE_CHECK_DTL_IDs
	 */
	@Override
	public void txBatch4DeleteChild(String TERM_REFINE_CHECK_ID,
			String TERM_REFINE_CHECK_DTL_IDs) {
		Map params = new HashMap();
		params.put("TERM_REFINE_CHECK_DTL_IDS", TERM_REFINE_CHECK_DTL_IDs);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		update("Termrefinecheck.deleteChldByIds", params);

		// 反填主表的精致化得分字段
		this
				.update("Termrefinecheck.updateScoreByMainId",
						TERM_REFINE_CHECK_ID);
	}

	/**
	 * 获取所有品牌名称
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getBrand(String type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		if ("toList".equals(type)) {
			map.put("STATE", "'启用','停用'");
		} else {
			map.put("STATE", "'启用'");
		}
		return this.findList("product.getBrand", map);
	}

	@SuppressWarnings("unchecked")
	public void txParseExeclToDbNew(List list, UserBean userBean,
			String PRD_INV_ID) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			List listList = (List) list.get(i);
			Map<String, String> allMainMap = new HashMap<String, String>();// 记录最全的主表信息判断是否重复

			List<Map<String, String>> mainList = new ArrayList<Map<String, String>>();// 主表list
			List<Map<String, String>> dtlList = new ArrayList<Map<String, String>>();// 从表list
			for (int j = 1; j < listList.size(); j++) {
				Map<String, String> map = (Map<String, String>) listList.get(j);
				Map<String, String> mainTab = new HashMap<String, String>();

				if (allMainMap.get(map.get("TERM_REFINE_TASK_NO")
						+ map.get("TERM_NO")) == null) {// 如果第一次出现主表的信息
					String TERM_REFINE_CHECK_ID = StringUtil.uuid32len();
					String TERM_REFINE_CHECK_NO = LogicUtil
							.getBmgz("DRP_TERM_REFINE_CHECK_NO");
					String TERM_REFINE_TASK_NO = map.get("TERM_REFINE_TASK_NO");
					mainTab.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
					mainTab.put("TERM_REFINE_CHECK_NO", TERM_REFINE_CHECK_NO);
					mainTab.put("TERM_REFINE_TASK_NO", TERM_REFINE_TASK_NO);
					mainTab.put("TERM_NO", map.get("TERM_NO"));
					mainTab.put("STATE", BusinessConsts.UNCOMMIT);
					mainTab.putAll(LogicUtil.sysFild(userBean));

					mainList.add(mainTab);

					allMainMap.put(map.get("TERM_REFINE_TASK_NO")
							+ map.get("TERM_NO"), TERM_REFINE_CHECK_ID);// 主表的id作为value值
				}

				// excel的每一行来判断是否符合要求
				String error = checkEmpty(map);
				if (!StringUtil.isEmpty(error)) {
					throw new ServiceException(error);
				}
				error = checkRepeat(map.get("TERM_REFINE_TASK_NO"), map
						.get("TERM_NO"));
				if (!StringUtil.isEmpty(error)) {
					throw new ServiceException(error);
				}

				Map<String, String> dtlMap = new HashMap<String, String>();
				Object obj = map.get("PRJ_SCORE");
				float PRJ_SCORE = Float.parseFloat(obj.toString());
				obj = map.get("CHECK_SCORE");
				float CHECK_SCORE = Float.parseFloat(obj.toString());
				if (PRJ_SCORE < CHECK_SCORE) {
					throw new ServiceException("对不起，检查得分不能大于检查项目分值 !");
				}
				dtlMap.put("TERM_REFINE_CHECK_DTL_ID", StringUtil.uuid32len());
				dtlMap.put("TERM_REFINE_CHECK_ID", allMainMap.get(map
						.get("TERM_REFINE_TASK_NO")
						+ map.get("TERM_NO")));
				dtlMap.put("PRJ_TYPE", map.get("PRJ_TYPE"));
				dtlMap.put("PRJ_CODE", map.get("PRJ_CODE"));
				dtlMap.put("PRJ_NAME", map.get("PRJ_NAME"));
				dtlMap.put("PRJ_SCORE", PRJ_SCORE + "");
				dtlMap.put("CHECK_SCORE", CHECK_SCORE + "");
				dtlMap.put("CHECK_REMARK", map.get("CHECK_REMARK"));
				dtlMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				dtlList.add(dtlMap);
			}
			String error = txEditTemp(mainList, dtlList);
			if (!StringUtil.isEmpty(error)) {
				throw new ServiceException(error);
			}
		}

		// 检查临时表的记录是否重复
		String error = checkOtherChild();
		if (!StringUtil.isEmpty(error)) {
			throw new ServiceException(error);
		}

		// 把临时表的数据加入到正式表
		this.insert("Termrefinecheck.insertMain", null);
		this.insert("Termrefinecheck.insertDtl", null);

		// 回填精致化得分
		this.update("Termrefinecheck.updateScore", null);

		// 删除临时表
		this.delete("Termrefinecheck.delTempMain", null);
		this.delete("Termrefinecheck.delTempDtl", null);
	}

	public String txEditTemp(List<Map<String, String>> mList,
			List<Map<String, String>> dlist) {
		// batch4Update("Termrefinecheck.insertTemp", mList);
		String error = null;
		for (Map<String, String> map : mList) {
			int i = this.queryForInt("Termrefinecheck.queryHasTerm", map);
			if (i == 0) {
				error = "终端编号'" + map.get("TERM_NO") + "'不存在，请重新修改上传文件！";
				return error;
			}
			this.insert("Termrefinecheck.insertTemp", map);
		}

		if (dlist.size() != 0) {
			this.batch4Update("Termrefinecheck.insertChldTemp", dlist);
		}
		return error;
	}

	// 校验是否有重复的子表记录
	private String checkOtherChild() {
		List<Map> list = this.findList("Termrefinecheck.checkOtherChild", null);
		String error = null;
		for (Map map : list) {
			error = "导入文件中任务单号'" + map.get("TERM_REFINE_TASK_NO") + "'和终端编号'"
					+ map.get("TERM_NO") + "'有重复记录，请检查后上传";
			return error;
		}
		return error;
	}

	// 校验临时表中是否有重复记录
	private String checkTempRepeat() {
		int i = this.queryForInt("Termrefinecheck.checkTempRule", null);
		String error = null;
		if (i > 0) {
			error = "导入文件中任务单号和终端编号有重复记录，请检查后上传";
		}
		return error;
	}

	private String checkRepeat(String TERM_REFINE_TASK_NO, String TERM_NO) {
		String error = null;
		Map param = new HashMap();
		param.put("TERM_REFINE_TASK_NO", TERM_REFINE_TASK_NO);
		param.put("TERM_NO", TERM_NO);

		Map map = (Map) this.load("Termrefinecheck.checkRule", param);
		if (map != null) {
			error = "对不起，任务单号'" + map.get("TERM_REFINE_TASK_NO") + "',终端编号'"
					+ map.get("TERM_NO") + "'系统中已有记录，请检查后上传";
			return error;
		}
		return error;
	}

	public String checkEmpty(Map<String, String> map) {
		String error;
		if (StringUtil.isEmpty(map.get("PRJ_CODE"))) {
			error = "对不起，有记录检查项目编号为空，请检查后上传！";
			return error;
		}
		if (StringUtil.isEmpty(map.get("PRJ_NAME"))) {
			error = "对不起，有记录检查项目名称为空，请检查后上传！";
			return error;
		}
		if (StringUtil.isEmpty(map.get("TERM_NO"))) {
			error = "对不起，有记录终端编号为空，请检查后上传！";
			return error;
		}
		if (StringUtil.isEmpty(map.get("PRJ_SCORE"))) {
			error = "对不起，有记录检查项目分值为空，请检查后上传！";
			return error;
		}
		if (StringUtil.isEmpty(map.get("TERM_REFINE_TASK_NO"))) {
			error = "对不起，有记录精致化检查任务单号为空，请检查后上传！";
			return error;
		}
		if (StringUtil.isEmpty(map.get("CHECK_SCORE"))) {
			error = "对不起，有记录检查得分为空，请检查后上传！";
			return error;
		}
		if (StringUtil.isEmpty(map.get("PRJ_TYPE"))) {
			error = "对不起，有记录检查项目类别为空，请检查后上传！";
			return error;
		}
		String checkStr="[0-9]+\\.{0,1}[0-9]{0,2}";
		if (!StringUtil.regexCheck(map.get("PRJ_SCORE"),checkStr)) {
			error = "对不起，有记录检查项目分值不是数字，请检查后上传！";
			return error;
		}
		if (!StringUtil.regexCheck(map.get("CHECK_SCORE"),checkStr)) {
			error = "对不起，有记录检查得分不是数字，请检查后上传！";
			return error;
		}
		return null;
	}


	/**
	 * @param TERM_REFINE_CHECK_DTL_ID
	 */
	public void txDeleteChild(String TERM_REFINE_CHECK_DTL_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("TERM_REFINE_CHECK_DTL_ID", TERM_REFINE_CHECK_DTL_ID);
		params.put("DEL_FLAG", "1");
		delete("Termrefinecheck.deletebyCheck", params);
	}

	/**
	 * @param TERM_REFINE_CHECK_ID
	 * @param PRJ_TYPE
	 * @param PRJ_CODE
	 * @param PRJ_NAME
	 * @param PRJ_SCORE
	 * @param CHECK_SCORE
	 * @param CHECK_REMARK
	 * @param modelList
	 * @return
	 */
	public String insertChild(String TERM_REFINE_CHECK_ID, String PRJ_TYPE,
			String PRJ_CODE, String PRJ_NAME, String PRJ_SCORE,
			String CHECK_SCORE, String CHECK_REMARK,
			List<TermrefinecheckModelChld> modelList) {

		if (modelList != null) {
			for (int i = 0; i < modelList.size() - 1; i++) {
				for (int j = modelList.size() - 1; j > i; j--) {
					if (modelList.get(i).getPRJ_CODE().equals(
							modelList.get(j).getPRJ_CODE())
							&& modelList.get(i).getPRJ_NAME().equals(
									modelList.get(j).getPRJ_NAME())) {
						return "1";
					}
				}
			}
		}
		Map<String, String> paramsT = new HashMap<String, String>();
		paramsT.put("TERM_REFINE_CHECK_DTL_ID", StringUtil.uuid32len());
		paramsT.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
		paramsT.put("PRJ_TYPE", PRJ_TYPE);
		paramsT.put("PRJ_CODE", PRJ_CODE);
		paramsT.put("PRJ_NAME", PRJ_NAME);
		paramsT.put("PRJ_SCORE", PRJ_SCORE);
		paramsT.put("CHECK_SCORE", CHECK_SCORE);
		paramsT.put("CHECK_REMARK", CHECK_REMARK);
		paramsT.put("DEL_FLAG", "0");
		this.insert("Termrefinecheck.insertChld", paramsT);
		return "0";
	}

	/**
	 * @param TERM_REFINE_CHECK_DTL_ID
	 * @param TERM_REFINE_CHECK_ID
	 * @param PRJ_TYPE
	 * @param PRJ_CODE
	 * @param PRJ_NAME
	 * @param PRJ_SCORE
	 * @param CHECK_SCORE
	 * @param CHECK_REMARK
	 */
	public void updateChild(String TERM_REFINE_CHECK_DTL_ID,
			String TERM_REFINE_CHECK_ID, String PRJ_TYPE, String PRJ_CODE,
			String PRJ_NAME, String PRJ_SCORE, String CHECK_SCORE,
			String CHECK_REMARK) {

		Map<String, String> paramsT = new HashMap<String, String>();
		paramsT.put("TERM_REFINE_CHECK_DTL_ID", TERM_REFINE_CHECK_DTL_ID);
		paramsT.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
		paramsT.put("PRJ_TYPE", PRJ_TYPE);
		paramsT.put("PRJ_CODE", PRJ_CODE);
		paramsT.put("PRJ_NAME", PRJ_NAME);
		paramsT.put("PRJ_SCORE", PRJ_SCORE);
		paramsT.put("CHECK_SCORE", CHECK_SCORE);
		paramsT.put("CHECK_REMARK", CHECK_REMARK);
		paramsT.put("DEL_FLAG", "0");
		this.insert("Termrefinecheck.updateChldById", paramsT);
	}
	
	  /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public List <TermrefinecheckModelChld> queryJudgeModel(String CHANN_CHECK_PLAN_ID,String BUSS_SCOPE){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("CHANN_CHECK_PLAN_ID", CHANN_CHECK_PLAN_ID);
    	paramMap.put("BUSS_SCOPE", BUSS_SCOPE);
    	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
     	List<TermrefinecheckModelChld> list = this.findList("Termrefinecheck.selectBrothers", paramMap);
     	return list;
    }
	
	public String queryIdByNo(String CHANN_CHECK_PLAN_NO){
		 return (String) load("Termrefinecheck.queryPro", CHANN_CHECK_PLAN_NO);
	}
	
	public void txUpdateChild(String TERM_REFINE_CHECK_ID){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("TERM_REFINE_CHECK_ID", TERM_REFINE_CHECK_ID);
		this.update("Termrefinecheck.updateChldByFkId", map);
	}
	
	public void txChildSave(String TERM_REFINE_CHECK_ID, List <TermrefinecheckModelChld> modelList){
		List<Map<String,Object>>updateList = new ArrayList<Map<String,Object>>();
		for(TermrefinecheckModelChld child : modelList){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("TERM_REFINE_CHECK_DTL_ID", child.getTERM_REFINE_CHECK_DTL_ID());
			map.put("CHECK_FINISH_SCORE", child.getCHECK_FINISH_SCORE());
			updateList.add(map);
		}
		if (!updateList.isEmpty()) {
			this.batch4Update("Termrefinecheck.updateChldById", updateList);
		}
	}
	 
}
