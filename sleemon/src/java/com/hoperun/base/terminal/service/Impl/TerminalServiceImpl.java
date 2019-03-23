/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：RYXXServiceImpl.java
 */
package com.hoperun.base.terminal.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.hoperun.base.terminal.model.TerminalModel;
import com.hoperun.base.terminal.service.TerminalService;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandServiceImpl.
 * 
 * @module 系统管理
 * @func 终端信息
 * @version 1.1
 * @author 郭利伟
 */
public class TerminalServiceImpl extends BaseService implements TerminalService {

	/**
	 * 详细信息.
	 * 
	 * @param param
	 *            the param
	 * 
	 * @return the map< string, string>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map load(String param) {
		Map params = new HashMap();
		params.put("TERM_ID", param);
		return (Map) load("TERMINAL.loadById", params);
	}
	
	  /**
     * @param param
     * @return
     */
    public Map loadT(String param){
    	Map params = new HashMap();
		params.put("TERM_ID", param);
		return (Map) load("TERMINAL.loadByIdT", params);
    }

	/**
	 * 查询并分页
	 * 
	 * @param params
	 *            the params
	 * @param pageNo
	 *            the page no
	 * 
	 * @return the i list page
	 */
	@Override
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {

		return this.pageQuery("TERMINAL.pageQuery", "TERMINAL.pageCount",
				params, pageNo);
	}

	/**
	 * 删除数据
	 * 
	 * @param TERM_ID
	 *            the rermId
	 * 
	 * @return true, if tx delete
	 */
	@Override
	public boolean txDelete(String rermId, UserBean userBean) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("TERM_ID", rermId); // 终端信息ID
		params.put("UPDATOR", userBean.getXTYHID()); // 更新人ID
		params.put("UPD_NAME", userBean.getXM()); // 更新人名称
		return delete("TERMINAL.deleteById", params);
	}

	/**
	 * 编辑
	 * 
	 * @param terminalId
	 *            the terminal id
	 * @param terminalModel
	 *            the terminal model
	 * @param userBean
	 *            the user bean
	 * 
	 * @return the string
	 */
	@Override
	public void txEdit(String termId, TerminalModel model, UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>(16);
		params.put("TERM_NAME", model.getTERM_NAME()); // 终端名称
		params.put("TERM_ABBR", model.getTERM_ABBR()); // 终端简称
		params.put("TERM_TYPE", model.getTERM_TYPE()); // 终端类型
		params.put("TERM_LVL", model.getTERM_LVL()); // 终端级别
		params.put("CHANN_ID_P", model.getCHANN_ID_P()); // 上级渠道ID
		params.put("CHANN_NO_P", model.getCHANN_NO_P()); // 上级渠道编号
		params.put("CHANN_NAME_P", model.getCHANN_NAME_P()); // 上级渠道名称
		params.put("BUSS_NATRUE", model.getBUSS_NATRUE()); // 营业性质
		params.put("LOGIC_TYPE", model.getLOGIC_TYPE()); // 物流方式
		params.put("AREA_ID", model.getAREA_ID()); // 区域ID
		params.put("AREA_NO", model.getAREA_NO()); // 区域编号
		params.put("AREA_NAME", model.getAREA_NAME()); // 区域名称
		params.put("ZONE_ID", model.getZONE_ID()); // 行政区划ID
		params.put("NATION", model.getNATION()); // 国家
		params.put("PROV", model.getPROV()); // 省份
		params.put("CITY", model.getCITY()); // 城市
		params.put("COUNTY", model.getCOUNTY()); // 区县
		params.put("CITY_TYPE", model.getCITY_TYPE()); // 城市类型
		params.put("PERSON_CON", model.getPERSON_CON()); // 联系人
		params.put("TEL", model.getTEL()); // 电话
		params.put("MOBILE_PHONE", model.getMOBILE_PHONE()); // 手机
		params.put("TAX", model.getTAX()); // 传真
		params.put("POST", model.getPOST()); // 邮政编码
		params.put("EMAIL", model.getEMAIL()); // 电子邮件
		params.put("WEB", model.getWEB()); // 网站
		params.put("LEGAL_REPRE", model.getLEGAL_REPRE()); // 法人代表
		params.put("BUSS_LIC", model.getBUSS_LIC()); // 营业执照号
		params.put("AX_BURDEN", model.getAX_BURDEN()); // 税务登记号
		params.put("ORG_CODE_CERT", model.getORG_CODE_CERT()); // 组织结构代码证
		params.put("BUSS_SCOPE", model.getBUSS_SCOPE()); // 经营范围
		params.put("FI_CMP_NO", model.getFI_CMP_NO()); // 财务对照码
		params.put("BUSS_AREA", model.getBUSS_AREA()); // 营业面积
		params.put("STOR_NO", model.getSTOR_NO()); // 楼层数
		params.put("LAST_DECOR_TIME", model.getLAST_DECOR_TIME()); // 最后装潢时间
		params.put("LEDGER_ID", userBean.getLoginZTXXID()); // 帐套ID
		params.put("LEDGER_NAME", userBean.getLoginZTMC()); // 帐套名称
		params.put("UPDATOR", userBean.getXTYHID()); // 更新人ID
		params.put("UPD_NAME", userBean.getXM()); // 更新人名称
		params.put("ADDRESS", model.getADDRESS()); // 详细地址
		params.put("REMARK", model.getREMARK()); // 备注
		params.put("TERM_VERSION", model.getTERM_VERSION());//终端版本
		params.put("SALE_STORE_ID", model.getSALE_STORE_ID());  
		params.put("SALE_STORE_NAME", model.getSALE_STORE_NAME());  
		params.put("LOCAL_TYPE", queryName(model.getLOCAL_TYPE())); 
		params.put("BEG_SBUSS_DATE", model.getBEG_SBUSS_DATE());  
		params.put("BUSS_STATE", model.getBUSS_STATE());//营业状态
		params.put("BEG_BUSS_TYPE", model.getBEG_BUSS_TYPE());//开店类型
		params.put("PLAY_BANK_NAME", model.getPLAY_BANK_NAME());//打款开户银行
		params.put("PLAY_BANK_ACCT", model.getPLAY_BANK_ACCT());//打款银行账号
		params.put("TERM_CLASS", model.getTERM_CLASS());//门店分类
		params.put("CUST_NAME", model.getCUST_NAME());//客户名称
		
		if (StringUtils.isEmpty(termId)) {
			String TERM_NO = queryMaxNo();//LogicUtil.getBmgz("BASE_TERMINAL_NO");
			String[] TERM_NOs = TERM_NO.split("NO");
			int  Num = Integer.parseInt(TERM_NOs[1].toString());
		    String OPEN_TERM_NO = "NO"+String.format("%05d",(Num+1));
		    
			termId = StringUtil.uuid32len();
			params.put("TERM_ID", termId);           // 终端信息ID
			params.put("TERM_NO", OPEN_TERM_NO);     // 终端编号
			params.put("STATE", BusinessConsts.JC_STATE_DEFAULT); // 状态
			params.put("CREATOR", userBean.getXTYHID()); // 制单人ID
			params.put("CRE_NAME", userBean.getXM()); // 制单人名称
			params.put("DEPT_ID", userBean.getBMXXID()); // 制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC()); // 制单部门名称
			params.put("ORG_ID", userBean.getJGXXID()); // 制单机构ID
			params.put("ORG_NAME", userBean.getJGMC()); // 制单机构名称
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON); // 制单机构名称
			insert("TERMINAL.insert", params);
			txEditBMXX(termId,OPEN_TERM_NO, model, userBean);
		} else {
			params.put("TERM_ID", model.getTERM_ID()); // 终端信息ID
			txUpdateById(params);
		}
	}
    
	/**
	 * 查询名称
	 * @param  LOCAL_TYPE
	 * @return
	 */
	public  String  queryName(String LOCAL_TYPE){
		 Map<String, String> params = new HashMap<String, String>();
		 params.put("DATA_DTL_CODE", LOCAL_TYPE);
		 String str = (String)load("Decorationapp.queryNum", params);
		 return str;
	}
	
	/**
     * @return
     */
    public  int  queryOpenTermNo(String TERM_NO){
    	return queryForInt("openTerminal.queryOpenTermNo",TERM_NO);
    }
    
	/**
	 * 更新记录
	 * 
	 * @param params
	 *            the params
	 * 
	 * @return true, if tx update by id
	 */
	@Override
	public boolean txUpdateById(Map<String, String> params) {
		update("TERMINAL.updateBMXX",params);
		return update("TERMINAL.updateById", params);
	}

	/**
	 * 查找编号是否存在
	 * 
	 * @param termNo
	 *            the termNo
	 * 
	 * @return true, if tx update by id
	 */
	@Override
	public int getExits(String termNo) {
		Map<String, String> params = new HashMap<String, String>(5);
		params.put("TERM_NO", termNo);
		return queryForInt("TERMINAL.getExits", params);
	}

	/**
	 * 查找部门编号是否存在
	 * 
	 * @param termNo
	 *            the termNo
	 * 
	 * @return true, if tx update by id
	 */
	public int checkNo(String termNo) {
		Map<String, String> params = new HashMap<String, String>(5);
		params.put("BMBH", termNo);
		params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
		return queryForInt("TERMINAL.getExits", params);
	}
	
	public  String  queryMaxNo(){
		 Map<String, String> params = new HashMap<String, String>();
		 String str = (String)load("openTerminal.queryMaxNo", params);
		 return str;
	}

	/**
	 * 修改状态为启用
	 * 
	 * @param params
	 *            the params
	 * 
	 * @return true, if tx start by id
	 */
	@Override
	public boolean txStartById(Map<String, String> params) {
		//启用部门信息
		Map<String,String> map=new HashMap<String, String>();
		map.put("BMXXID", params.get("TERM_ID"));
		map.put("BMZT", params.get("STATE"));
		update("BMXX.updateById", map);
		return update("TERMINAL.updateById", params);
	}

	/**
	 * 修改状态为停用
	 * 
	 * @param params
	 *            the params
	 * 
	 * @return true, if tx stop by id
	 */
	@Override
	public boolean txStopById(Map<String, String> params) {
		Map<String,String> map=new HashMap<String, String>();
		map.put("BMXXID", params.get("TERM_ID"));
		map.put("BMZT", params.get("STATE"));
		update("BMXX.updateById", map);
		return update("TERMINAL.updateById", params);
	}
	/**
	 * 编辑部门信息
	 * @param termId
	 * @param model
	 * @param userBean
	 */
	public void txEditBMXX(String termId,String OPEN_TERM_NO, TerminalModel model, UserBean userBean) {
		Map<String,String> params=new HashMap<String,String>();
		params.put("BMBH", OPEN_TERM_NO); // 部门编号
		params.put("BMMC", model.getTERM_NAME()); // 部门名称
		params.put("BMJC", model.getTERM_ABBR()); // 部门简称
		params.put("DH", model.getTEL()); // 电话
		params.put("CZ", model.getTAX()); // 传真
		params.put("DZYJ", model.getEMAIL()); // 电子邮件
		params.put("XXDZ", model.getADDRESS()); // 详细地址
		params.put("YZBM", model.getPOST()); // 邮政编码
		params.put("ZYDZ", model.getWEB()); // 网站
		params.put("BMXXID", termId); // 部门信息ID
		params.put("JGXXID", model.getCHANN_ID_P());//上级机构
		params.put("BMZT", BusinessConsts.JC_STATE_ENABLE); // 状态
		params.put("ZTXXID", model.getCHANN_ID_P()); // 帐套信息id
		params.put("IS_DRP_LEDGER", BusinessConsts.YJLBJ_FLAG_TRUE);//是否分销
		insert("TERMINAL.insertBMXX", params);
	}
	
	/**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params){
		return findList("TERMINAL.expertExcel",params);
	}
	
	/**
	 * 修改价格计算公式
	 */
	public void txModifyExpress(Map<String, String> params )throws ServiceException{
		try{
			  this.findList("TERMINAL.testPRICE_EXPRESS", params);
		}catch (Exception e) {
		  throw new ServiceException("保存失败！公式填写不正确");
		}
		
		this.update("TERMINAL.modifyExpress", params);
	}
	
	/**
	 * 测试公式
	 * @param params
	 * @return
	 */
	public String testExpress(Map<String, String> params )throws ServiceException{
		List list = null;
		try{
		 list =  this.findList("TERMINAL.testPRICE_EXPRESS", params);
		}catch (Exception e) {
		  throw new ServiceException("测试失败！公式填写不正确");
		}
	    if(null == list || list.isEmpty()){
	    	return "";
	    }else{
	    	return StringUtil.nullToSring(list.get(0));
	    }
	}
	
	
}
