package com.hoperun.drp.oamg.terminalchange.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

import com.hoperun.base.terminal.model.TerminalModel;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.terminalchange.model.TerminalChangeModel;
import com.hoperun.drp.oamg.terminalchange.service.TerminalChangeService;
import com.hoperun.sys.model.UserBean;

public class TerminalChangeServiceImpl extends BaseService implements TerminalChangeService{

	 /**
     * @param TERMINAL_CHANGE_ID
     * @return
     */
    public Map <String, String> load(String TERMINAL_CHANGE_ID){
    	return (Map<String, String>) load("terminalChange.loadById",TERMINAL_CHANGE_ID);
    }
    
    /**
     * @param TERMINAL_CHANGE_ID
     * @return
     */
    public Map <String, String> loadT(String TERMINAL_CHANGE_ID){
    	return (Map<String, String>) load("terminalChange.loadByIdT",TERMINAL_CHANGE_ID);
    }
    
    /**
     * @param TERMINAL_CHANGE_ID
     * @return
     */
    public Map <String, String> loadByHistory(String TERM_NO){
    	return (Map<String, String>) load("TERMINAL.loadByTerm", TERM_NO);
    }
    
    /**
     * @param TERM_NO
     * @return
     */
    public List<TerminalChangeModel> loadByTerm(String TERM_NO){
    	//return (Map<String, String>) load("terminalChange.loadByTerm", TERM_NO);
    	Map params = new HashMap();
		params.put("TERM_NO", TERM_NO);
		return this.findList("terminalChange.loadByTerm",params);
    }
    
    /**
	 * @param obj
	 * @param userBean
	 */
    public void txEditTerm(TerminalModel obj, UserBean userBean){
    	
    	Map<String, String> params = new HashMap<String, String>();

		params.put("TERM_ID",    obj.getTERM_ID());        //终端ID
		params.put("TERM_NO",    obj.getTERM_NO());        //终端编号
		params.put("TERM_NAME",  obj.getTERM_NAME());      //终端名称
		params.put("TERM_ABBR",  obj.getTERM_ABBR());      //终端简称
		params.put("TERM_TYPE",  obj.getTERM_TYPE());      //终端类型
		params.put("TERM_LVL",   obj.getTERM_LVL());       //终端级别
		params.put("CHANN_ID_P", obj.getCHANN_ID_P());     //上级渠道ID
		params.put("CHANN_NO_P", obj.getCHANN_NO_P());     //上级渠道编号
		params.put("CHANN_NAME_P", obj.getCHANN_NAME_P()); //上级渠道名称
		params.put("BUSS_NATRUE",  obj.getBUSS_NATRUE());  //营业性质
		params.put("LOGIC_TYPE",   obj.getLOGIC_TYPE());   //物流方式
		params.put("AREA_ID",      obj.getAREA_ID());      //区域ID
		params.put("AREA_NO",      obj.getAREA_NO());      //区域编号
		params.put("AREA_NAME",    obj.getAREA_NAME());    //区域名称
		params.put("ZONE_ID",      obj.getZONE_ID());      //行政区划ID
		params.put("NATION",       obj.getNATION());       //国家
		params.put("PROV",         obj.getPROV());         //省份
		params.put("CITY",         obj.getCITY());         //城市
		params.put("COUNTY",       obj.getCOUNTY());       //区县
		params.put("CITY_TYPE",    obj.getCITY_TYPE());    //城市类型
		params.put("PERSON_CON",   obj.getPERSON_CON());   //联系人
		params.put("TEL",          obj.getTEL());          //电话
		params.put("MOBILE",       obj.getMOBILE_PHONE()); //手机
		params.put("TAX",          obj.getTAX());          //传真
        params.put("POST",         obj.getPOST());         //邮政编号
        params.put("ADDRESS",      obj.getADDRESS());      //详细地址
        params.put("EMAIL",        obj.getEMAIL());        //电子邮件
        params.put("WEB", obj.getWEB());                   //网站
		params.put("LEGAL_REPRE", obj.getLEGAL_REPRE());   //法人代表
		params.put("BUSS_LIC",    obj.getBUSS_LIC());      //营业执照号
		params.put("AX_BURDEN",   obj.getAX_BURDEN());     //税务登记号
		params.put("ORG_CODE_CERT",obj.getORG_CODE_CERT());//组织机构代码证
		params.put("BUSS_SCOPE",   obj.getBUSS_SCOPE());   //经营范围
        params.put("FI_CMP_NO",    obj.getFI_CMP_NO());    //财务对照码
        params.put("BUSS_AREA",          obj.getBUSS_AREA());          //营业面积
		params.put("STOR_NO",            obj.getSTOR_NO());            //楼层数
		params.put("LAST_DECOR_TIME",    obj.getLAST_DECOR_TIME());    //最后装潢时间
		params.put("SALE_STORE_ID",      obj.getSALE_STORE_ID());      //卖场ID
		params.put("SALE_STORE_NAME",    obj.getSALE_STORE_NAME());    //卖场名称
		params.put("LOCAL_TYPE",         obj.getLOCAL_TYPE());         //商场位置类别
		params.put("BEG_SBUSS_DATE",     obj.getBEG_SBUSS_DATE());     //开业时间
		params.put("PLAY_BANK_NAME",     obj.getPLAY_BANK_NAME());     //打款开户银行
		params.put("PLAY_BANK_ACCT",     obj.getPLAY_BANK_ACCT());     //打款银行账号
		params.put("BUSS_STATE",         obj.getBUSS_STATE());         //营业状态
		params.put("BEG_BUSS_TYPE",      obj.getBEG_BUSS_TYPE());      //开店类型
		params.put("REMARK",             obj.getREMARK());             //备注
		params.put("STATE",              "");              //状态
		
		params.put("DEL_FLAG", "0");
		params.put("TERMINAL_CHANGE_ID", StringUtil.uuid32len());
		this.insert("terminalChange.insert", params);
    }
    
    /**
     * @param TERMINAL_CHANGE_ID
     * @param obj
     * @param userBean
     */    
	public void txEdit(String TERMINAL_CHANGE_ID, TerminalChangeModel obj, UserBean userBean){
		
		Map<String, String> params = new HashMap<String, String>();

		params.put("TERM_ID",    obj.getTERM_ID());        //终端ID
		params.put("TERM_NO",    obj.getTERM_NO());        //终端编号
		params.put("TERM_NAME",  obj.getTERM_NAME());      //终端名称
		params.put("TERM_ABBR",  obj.getTERM_ABBR());      //终端简称
		params.put("TERM_TYPE",  obj.getTERM_TYPE());      //终端类型
		params.put("TERM_LVL",   obj.getTERM_LVL());       //终端级别
		params.put("CHANN_ID_P", obj.getCHANN_ID_P());     //上级渠道ID
		params.put("CHANN_NO_P", obj.getCHANN_NO_P());     //上级渠道编号
		params.put("CHANN_NAME_P", obj.getCHANN_NAME_P()); //上级渠道名称
		params.put("BUSS_NATRUE",  obj.getBUSS_NATRUE());  //营业性质
		params.put("LOGIC_TYPE",   obj.getLOGIC_TYPE());   //物流方式
		params.put("AREA_ID",      obj.getAREA_ID());      //区域ID
		params.put("AREA_NO",      obj.getAREA_NO());      //区域编号
		params.put("AREA_NAME",    obj.getAREA_NAME());    //区域名称
		params.put("ZONE_ID",      obj.getZONE_ID());      //行政区划ID
		params.put("NATION",       obj.getNATION());       //国家
		params.put("PROV",         obj.getPROV());         //省份
		params.put("CITY",         obj.getCITY());         //城市
		params.put("COUNTY",       obj.getCOUNTY());       //区县
		params.put("CITY_TYPE",    obj.getCITY_TYPE());    //城市类型
		params.put("PERSON_CON",   obj.getPERSON_CON());   //联系人
		params.put("TEL",          obj.getTEL());          //电话
		params.put("MOBILE",       obj.getMOBILE());       //手机
		params.put("TAX",          obj.getTAX());          //传真
        params.put("POST",         obj.getPOST());         //邮政编号
        params.put("ADDRESS",      obj.getADDRESS());      //详细地址
        params.put("EMAIL",        obj.getEMAIL());        //电子邮件
        params.put("WEB", obj.getWEB());                   //网站
		params.put("LEGAL_REPRE", obj.getLEGAL_REPRE());   //法人代表
		params.put("BUSS_LIC",    obj.getBUSS_LIC());      //营业执照号
		params.put("AX_BURDEN",   obj.getAX_BURDEN());     //税务登记号
		params.put("ORG_CODE_CERT",obj.getORG_CODE_CERT());//组织机构代码证
		params.put("BUSS_SCOPE",   obj.getBUSS_SCOPE());   //经营范围
        params.put("FI_CMP_NO",    obj.getFI_CMP_NO());    //财务对照码
        params.put("BUSS_AREA",          obj.getBUSS_AREA());          //营业面积
		params.put("STOR_NO",            obj.getSTOR_NO());            //楼层数
		params.put("LAST_DECOR_TIME",    obj.getLAST_DECOR_TIME());    //最后装潢时间
		params.put("SALE_STORE_ID",      obj.getSALE_STORE_ID());      //卖场ID
		params.put("SALE_STORE_NAME",    obj.getSALE_STORE_NAME());    //卖场名称
		params.put("LOCAL_TYPE",         obj.getLOCAL_TYPE());         //商场位置类别
		params.put("BEG_SBUSS_DATE",     obj.getBEG_SBUSS_DATE());     //开业时间
		params.put("PLAY_BANK_NAME",     obj.getPLAY_BANK_NAME());     //打款开户银行
		params.put("PLAY_BANK_ACCT",     obj.getPLAY_BANK_ACCT());     //打款银行账号
		params.put("BUSS_STATE",         obj.getBUSS_STATE());         //营业状态
		params.put("BEG_BUSS_TYPE",      obj.getBEG_BUSS_TYPE());      //开店类型
		params.put("REMARK",             obj.getREMARK());             //备注
		params.put("STATE",              obj.getSTATE());              //状态
		
		if (StringUtils.isEmpty(TERMINAL_CHANGE_ID)) {
			params.put("DEL_FLAG", "0");
			params.put("CREATOR", userBean.getXTYHID());//制单人ID
			params.put("CRE_NAME", userBean.getXM());   //制单人名称
			params.put("CRE_TIME", DateUtil.now());     //制单时间
			params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
			params.put("ORG_ID", userBean.getJGXXID()); //制单机构id
			params.put("ORG_NAME", userBean.getJGMC()); //制单机构名称
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); //帐套ID
			params.put("LEDGER_NAME", userBean.getLoginZTMC()); //帐套名称
			params.put("TERMINAL_CHANGE_ID", StringUtil.uuid32len());
			params.put("STATE", "未提交");
			this.insert("terminalChange.insert", params);
			
		} else {
			params.put("TERMINAL_CHANGE_ID", TERMINAL_CHANGE_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称           
            params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间      
			this.update("terminalChange.updateById", params);
		}
	}
	
	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo){
    	return this.pageQuery("terminalChange.pageQuery",
				"terminalChange.pageCount", params, pageNo);
    }
    
    /**
     * @param TERMINAL_CHANGE_ID
     * @param userBean
     * @return
     */
    public boolean txDelete(String TERMINAL_CHANGE_ID, UserBean userBean){
    	
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("TERMINAL_CHANGE_ID", TERMINAL_CHANGE_ID);
        params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称           
        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间      
		return update("terminalChange.delete", params);
    }
    
    /**
     * @param TERMINAL_CHANGE_ID
     * @return
     */
    public  int queryTerminalState(String TERMINAL_CHANGE_ID){
    	return queryForInt("terminalChange.queryTerminalState",TERMINAL_CHANGE_ID);
    }
    
	/**
	 * @param TERMINAL_CHANGE_ID
	 * @return
	 */
	public List<TerminalChangeModel> queryTerminal(String TERMINAL_CHANGE_ID){
		Map params = new HashMap();
		params.put("TERMINAL_CHANGE_ID", TERMINAL_CHANGE_ID);
		return this.findList("terminalChange.queryTerminal",params);
	}
    
    /**
     * @param TERMINAL_CHANGE_ID
     * @param userBean
     */
    public  void updateTerminal(String TERMINAL_CHANGE_ID,UserBean userBean){
    	
    	List<TerminalChangeModel>  li = this.queryTerminal(TERMINAL_CHANGE_ID);
    	Map<String, String> params = new HashMap<String, String>(16);
    	TerminalChangeModel  modelt = null;
    	
		for(int i=0;i<li.size();i++){
			modelt = (TerminalChangeModel)li.get(i);
			params.put("TERM_ID", modelt.getTERM_ID());          //终端ID
			params.put("TERM_NO", modelt.getTERM_NO());          // 终端编号
			params.put("TERM_NAME", modelt.getTERM_NAME());      // 终端名称
			params.put("TERM_ABBR", modelt.getTERM_ABBR());      // 终端简称
			params.put("TERM_TYPE", modelt.getTERM_TYPE());      // 终端类型
			params.put("TERM_LVL", modelt.getTERM_LVL());        // 终端级别
			params.put("CHANN_ID_P", modelt.getCHANN_ID_P());    // 上级渠道ID
			params.put("CHANN_NO_P", modelt.getCHANN_NO_P());    // 上级渠道编号
			params.put("CHANN_NAME_P", modelt.getCHANN_NAME_P()); // 上级渠道名称
			params.put("BUSS_NATRUE", modelt.getBUSS_NATRUE());   // 营业性质
			params.put("LOGIC_TYPE", modelt.getLOGIC_TYPE());     // 物流方式
			params.put("AREA_ID", modelt.getAREA_ID());           // 区域ID
			params.put("AREA_NO", modelt.getAREA_NO());           // 区域编号
			params.put("AREA_NAME", modelt.getAREA_NAME());       // 区域名称
			params.put("ZONE_ID", modelt.getZONE_ID());           // 行政区划ID
			params.put("NATION", modelt.getNATION());             // 国家
			params.put("PROV", modelt.getPROV());                 // 省份
			params.put("CITY", modelt.getCITY());                 // 城市
			params.put("COUNTY", modelt.getCOUNTY());             // 区县
			params.put("CITY_TYPE", modelt.getCITY_TYPE());       // 城市类型
			params.put("PERSON_CON", modelt.getPERSON_CON());     // 联系人
			params.put("TEL", modelt.getTEL());                   // 电话
			params.put("MOBILE_PHONE", modelt.getMOBILE());       // 手机
			params.put("TAX", modelt.getTAX());                   // 传真
			params.put("POST", modelt.getPOST());                 // 邮政编码
			params.put("EMAIL", modelt.getEMAIL());               // 电子邮件
			params.put("WEB", modelt.getWEB());                   // 网站
			params.put("LEGAL_REPRE", modelt.getLEGAL_REPRE());   // 法人代表
			params.put("BUSS_LIC", modelt.getBUSS_LIC());         // 营业执照号
			params.put("AX_BURDEN", modelt.getAX_BURDEN());       // 税务登记号
			params.put("ORG_CODE_CERT", modelt.getORG_CODE_CERT()); // 组织结构代码证
			params.put("BUSS_SCOPE", modelt.getBUSS_SCOPE());       // 经营范围
			params.put("FI_CMP_NO", modelt.getFI_CMP_NO());         // 财务对照码
			params.put("BUSS_AREA", modelt.getBUSS_AREA());         // 营业面积
			params.put("STOR_NO", modelt.getSTOR_NO());             // 楼层数
			
			params.put("LAST_DECOR_TIME", modelt.getLAST_DECOR_TIME()); // 最后装潢时间
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); // 帐套ID
			params.put("LEDGER_NAME", userBean.getLoginZTMC()); // 帐套名称
			params.put("UPDATOR", userBean.getXTYHID());        // 更新人ID
			params.put("UPD_NAME", userBean.getXM());           // 更新人名称
			params.put("ADDRESS", modelt.getADDRESS());          // 详细地址
			params.put("REMARK", modelt.getREMARK());            // 备注
			params.put("SALE_STORE_ID", modelt.getSALE_STORE_ID());  
			params.put("SALE_STORE_NAME", modelt.getSALE_STORE_NAME());  
			params.put("LOCAL_TYPE",queryName(modelt.getLOCAL_TYPE())); 
			
			params.put("BEG_SBUSS_DATE", modelt.getBEG_SBUSS_DATE());  
			params.put("BUSS_STATE", modelt.getBUSS_STATE());//营业状态
			params.put("BEG_BUSS_TYPE", modelt.getBEG_BUSS_TYPE());//开店类型
			params.put("PLAY_BANK_NAME", modelt.getPLAY_BANK_NAME());//打款开户银行
			params.put("PLAY_BANK_ACCT", modelt.getPLAY_BANK_ACCT());//打款银行账号
			String termId = modelt.getTERM_NO();
			params.put("TERM_ID",termId); // 终端信息ID
			//params.put("STATE", BusinessConsts.JC_STATE_DEFAULT); // 状态
			params.put("CREATOR", userBean.getXTYHID()); // 制单人ID
			params.put("CRE_NAME", userBean.getXM()); // 制单人名称
			params.put("DEPT_ID", userBean.getBMXXID()); // 制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC()); // 制单部门名称
			params.put("ORG_ID", userBean.getJGXXID()); // 制单机构ID
			params.put("ORG_NAME", userBean.getJGMC()); // 制单机构名称
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON); // 制单机构名称
			txUpdateById(params);
         }
      }
    
    /**
     * @param params
     * @return
     */
	public boolean txUpdateById(Map<String, String> params) {
		return update("TERMINAL.updateById", params);
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
     * @param TERM_NO
     * @return
     */
	public TerminalModel queryTermHistory(String TERM_NO){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("TERM_NO", TERM_NO);
     	List<TerminalModel> list = this.findList("TERMINAL.loadByTERMNO", paramMap);
     	if(null != list && !list.isEmpty()){
     		return list.get(0);
     	}
     	return null;
    }
}
