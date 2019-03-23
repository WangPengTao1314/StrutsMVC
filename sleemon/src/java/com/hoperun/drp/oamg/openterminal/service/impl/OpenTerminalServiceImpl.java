package com.hoperun.drp.oamg.openterminal.service.impl;

import java.util.ArrayList;
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
import com.hoperun.drp.oamg.openterminal.model.OpenTerminalChildModel;
import com.hoperun.drp.oamg.openterminal.model.OpenTerminalModel;
import com.hoperun.drp.oamg.openterminal.model.TerminalCommModel;
import com.hoperun.drp.oamg.openterminal.service.OpenTerminalService;
import com.hoperun.sys.model.UserBean;

public class OpenTerminalServiceImpl extends BaseService implements OpenTerminalService {

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
	public IListPage pageQuery(Map <String, String> params, int pageNo){
		return this.pageQuery("openTerminal.pageQuery",
				"openTerminal.pageCount", params, pageNo);
	}
	
	
	 /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public Map <String, String> load(String OPEN_TERMINAL_REQ_ID){
    	return (Map<String, String>) load("openTerminal.loadById",OPEN_TERMINAL_REQ_ID);
    }
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public Map <String, String> loadT(String OPEN_TERMINAL_REQ_ID){
    	return (Map<String, String>) load("openTerminal.loadByIdT",OPEN_TERMINAL_REQ_ID);
    }
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public Map <String, String> loadTt(String OPEN_TERMINAL_REQ_ID){
    	return (Map<String, String>) load("openTerminal.loadByIdTt",OPEN_TERMINAL_REQ_ID);
    }
    
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public Map <String, String> loadTtT(String OPEN_TERMINAL_REQ_ID){
    	return (Map<String, String>) load("openTerminal.loadTtT",OPEN_TERMINAL_REQ_ID);
    }
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @param obj
     * @param userBean
     */
	public void txEdit(String OPEN_TERMINAL_REQ_ID, OpenTerminalModel obj, UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		params.put("TERM_NAME",  obj.getTERM_NAME());      //终端名称
		params.put("TERM_ABBR",  obj.getTERM_ABBR());      //终端简称
		params.put("TERM_TYPE",  obj.getTERM_TYPE());      //终端类型
		params.put("TERM_LVL",   "一级");       //终端级别
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
		params.put("TERM_VERSION",       obj.getTERM_VERSION());       //终端版本
		params.put("BEG_BUSS_TYPE",      obj.getBEG_BUSS_TYPE());      //开店类型
		params.put("REMARK",             obj.getREMARK());             //备注
		params.put("STATE",              obj.getSTATE());              //状态
		params.put("RNVTN_PROP",         obj.getRNVTN_PROP());         //装修性质
		String RNVTN_PROP  =   queryName(obj.getRNVTN_PROP());
		if(RNVTN_PROP.equals("翻新")){
			params.put("TERM_NO", obj.getTERM_NO());
		}
		
		if (StringUtils.isEmpty(OPEN_TERMINAL_REQ_ID)) {
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
			String UID = StringUtil.uuid32len();
			params.put("CHANN_RNVTN_ID", UID);
			String OPEN_TERMINAL_REQ_NO =LogicUtil.getBmgz("ERP_OPEN_TERMINAL_REQ_NO");	   
			params.put("OPEN_TERMINAL_REQ_ID", StringUtil.uuid32len());
			params.put("OPEN_TERMINAL_REQ_NO", OPEN_TERMINAL_REQ_NO);
			params.put("STATE", "未提交");
			this.insert("openTerminal.insert", params);
		} else {
			params.put("OPEN_TERMINAL_REQ_ID", OPEN_TERMINAL_REQ_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称           
            params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间      
			this.update("openTerminal.updateById", params);
		}
	}
	
   /**
     * @param OPEN_TERMINAL_REQ_ID
     * @param userBean
     * @return
     */
    public boolean txDelete(String OPEN_TERMINAL_REQ_ID, UserBean userBean){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("OPEN_TERMINAL_REQ_ID", OPEN_TERMINAL_REQ_ID);
        params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称           
        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间      
        this.update("openTerminal.delChldByFkId", params);
		return update("openTerminal.delete", params);
    }
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @param userBean
     */
    public  void txTerminal(String OPEN_TERMINAL_REQ_ID,UserBean userBean){
    	List<OpenTerminalModel>  list = this.queryTerminal(OPEN_TERMINAL_REQ_ID);
    	Map<String, String> params  = new HashMap<String, String>(16);
    	Map<String, String> paramsT = new HashMap<String, String>(16);
    	
        String TERM_NO = queryMaxNo();//LogicUtil.getBmgz("BASE_TERMINAL_NO");
		String[] TERM_NOs = TERM_NO.split("NO");
		int  Num = Integer.parseInt(TERM_NOs[1].toString());
	    String OPEN_TERM_NO = "NO"+String.format("%05d",(Num+1));
    	OpenTerminalModel  model = null;
		for(int i=0;i<list.size();i++){
			model = (OpenTerminalModel)list.get(i);
			String TERM_ID = StringUtil.uuid32len();
			params.put("TERM_ID", TERM_ID); // 终端信息ID
			params.put("TERM_NO", OPEN_TERM_NO); // 终端编号
			//System.out.print("TERM_NO====="+OPEN_TERM_NO);
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
			params.put("putZONE_ID", model.getZONE_ID()); // 行政区划ID
			params.put("NATION", model.getNATION()); // 国家
			params.put("PROV", model.getPROV()); // 省份
			params.put("CITY", model.getCITY()); // 城市
			params.put("COUNTY", model.getCOUNTY()); // 区县
			params.put("CITY_TYPE", model.getCITY_TYPE()); // 城市类型
			params.put("PERSON_CON", model.getPERSON_CON()); // 联系人
			params.put("TEL", model.getTEL()); // 电话
			params.put("MOBILE_PHONE", model.getMOBILE()); // 手机
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
			params.put("LOCAL_TYPE",queryName(model.getLOCAL_TYPE())); 
			
			params.put("BEG_SBUSS_DATE", model.getBEG_SBUSS_DATE());  
			params.put("BUSS_STATE", model.getBUSS_STATE());//营业状态
			params.put("BEG_BUSS_TYPE", model.getBEG_BUSS_TYPE());//开店类型
			params.put("PLAY_BANK_NAME", model.getPLAY_BANK_NAME());//打款开户银行
			params.put("PLAY_BANK_ACCT", model.getPLAY_BANK_ACCT());//打款银行账号
			params.put("STATE", BusinessConsts.JC_STATE_ENABLE); // 状态
			params.put("CREATOR", userBean.getXTYHID()); // 制单人ID
			params.put("CRE_NAME", userBean.getXM()); // 制单人名称
			params.put("DEPT_ID",  model.getDEPT_ID());  // 制单部门ID
			params.put("DEPT_NAME",model.getDEPT_NAME()); // 制单部门名称
			params.put("ORG_ID", model.getORG_ID());      // 制单机构ID
			params.put("ORG_NAME", model.getORG_NAME());  // 制单机构名称
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);  
			insert("TERMINAL.insert", params);
			paramsT.put("OPEN_TERMINAL_REQ_ID", OPEN_TERMINAL_REQ_ID);
			//paramsT.put("TERM_ID", OPEN_TERM_NO);
			paramsT.put("TERM_NO", OPEN_TERM_NO);
			this.update("openTerminal.updateById", paramsT);
			txEditBMXX(TERM_ID, model, userBean,OPEN_TERM_NO);
		}
    }
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @param userBean
     */
    public  void upTerminal(String OPEN_TERMINAL_REQ_ID,UserBean userBean){
    	List<OpenTerminalModel>  list = this.queryTerminal(OPEN_TERMINAL_REQ_ID);
    	Map<String, String> params  = new HashMap<String, String>(16);
    	Map<String, String> paramsT = new HashMap<String, String>(16);
    	OpenTerminalModel  model = null;
    	
//      String TERM_NO = queryMaxNo();    //LogicUtil.getBmgz("BASE_TERMINAL_NO");
//		String[] TERM_NOs = TERM_NO.split("NO");
//		int  Num = Integer.parseInt(TERM_NOs[1].toString());
//	    String OPEN_TERM_NO = "NO"+String.format("%05d",(Num+1));
//    	OpenTerminalModel  model = null;
		for(int i=0;i<list.size();i++){
			model = (OpenTerminalModel)list.get(i);
			String TERM_ID = queryTermIdByNo(model.getTERM_NO());
			params.put("TERM_ID", TERM_ID);                // 终端信息ID
			params.put("TERM_NO", model.getTERM_NO());     // 终端编号
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
			params.put("putZONE_ID", model.getZONE_ID()); // 行政区划ID
			params.put("NATION", model.getNATION()); // 国家
			params.put("PROV", model.getPROV()); // 省份
			params.put("CITY", model.getCITY()); // 城市
			params.put("COUNTY", model.getCOUNTY()); // 区县
			params.put("CITY_TYPE", model.getCITY_TYPE()); // 城市类型
			params.put("PERSON_CON", model.getPERSON_CON()); // 联系人
			params.put("TEL", model.getTEL()); // 电话
			params.put("MOBILE_PHONE", model.getMOBILE()); // 手机
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
			params.put("LOCAL_TYPE",queryName(model.getLOCAL_TYPE())); 
			
			params.put("BEG_SBUSS_DATE", model.getBEG_SBUSS_DATE());  
			params.put("BUSS_STATE", model.getBUSS_STATE());//营业状态
			params.put("BEG_BUSS_TYPE", model.getBEG_BUSS_TYPE());//开店类型
			params.put("PLAY_BANK_NAME", model.getPLAY_BANK_NAME());//打款开户银行
			params.put("PLAY_BANK_ACCT", model.getPLAY_BANK_ACCT());//打款银行账号
			params.put("STATE", BusinessConsts.JC_STATE_ENABLE); // 状态
			params.put("DEPT_ID",  model.getDEPT_ID());  // 制单部门ID
			params.put("DEPT_NAME",model.getDEPT_NAME()); // 制单部门名称
			params.put("ORG_ID", model.getORG_ID());      // 制单机构ID
			params.put("ORG_NAME", model.getORG_NAME());  // 制单机构名称
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);  
			
			txUpdateById(params);
//			insert("TERMINAL.insert", params);
//			paramsT.put("OPEN_TERMINAL_REQ_ID", OPEN_TERMINAL_REQ_ID);
//			paramsT.put("TERM_ID", OPEN_TERM_NO);
//			paramsT.put("TERM_NO", OPEN_TERM_NO);
//			this.update("openTerminal.updateById", paramsT);
//			txEditBMXX(OPEN_TERM_NO, model, userBean,OPEN_TERM_NO);
		}
    }
    
    public boolean txUpdateById(Map<String, String> params) {
		return update("TERMINAL.updateById", params);
	}
    
	/**
	 * 编辑部门信息
	 * @param termId
	 * @param model
	 * @param userBean
	 */
	public void txEditBMXX(String termId, OpenTerminalModel  model, UserBean userBean,String OPEN_TERM_NO) {
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
		//params.put("BMZT", BusinessConsts.JC_STATE_DEFAULT); // 状态
		params.put("BMZT", BusinessConsts.JC_STATE_ENABLE);
		params.put("ZTXXID", model.getCHANN_ID_P()); // 帐套信息id
		params.put("IS_DRP_LEDGER", BusinessConsts.YJLBJ_FLAG_TRUE);//是否分销
		insert("TERMINAL.insertBMXX", params);
	}
    
    /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
	public  int queryTerminalState(String OPEN_TERMINAL_REQ_ID){
		return queryForInt("openTerminal.queryTerminalState",OPEN_TERMINAL_REQ_ID);
	}
	
	/**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public  int queryIsCommit(String OPEN_TERMINAL_REQ_ID){
    	return queryForInt("openTerminal.queryIsCommit",OPEN_TERMINAL_REQ_ID);
    }
	/**
	 * @param OPEN_TERMINAL_REQ_ID
	 * @return
	 */
	public List<OpenTerminalModel> queryTerminal(String OPEN_TERMINAL_REQ_ID){
		Map<String,String> params = new HashMap<String,String>();
		params.put("OPEN_TERMINAL_REQ_ID", OPEN_TERMINAL_REQ_ID);
		
		return this.findList("openTerminal.queryTerminal",params);
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
	
	public  String  queryMaxNo(){
		 Map<String, String> params = new HashMap<String, String>();
		 String str = (String)load("openTerminal.queryMaxNo", params);
		 return str;
	}
	
	public  String  queryTermIdByNo(String TERM_NO){
		Map<String, String> params = new HashMap<String, String>();
		params.put("TERM_NO", TERM_NO);
		String str = (String)load("openTerminal.queryTermId", params);
		return str;
	}
	
	/**
     * @return
     */
    public  int  queryOpenTermNo(String TERM_NO){
    	return queryForInt("openTerminal.queryOpenTermNo",TERM_NO);
    }
	
	 /**
     * @param OPEN_TERMINAL_REQ_ID
     * @return
     */
    public  String  queryRrop(String OPEN_TERMINAL_REQ_ID){
    	 Map<String, String> params = new HashMap<String, String>();
		 params.put("OPEN_TERMINAL_REQ_ID", OPEN_TERMINAL_REQ_ID);
		 String str = (String)load("openTerminal.queryRrop", params);
		 return str;
    }

	public List<OpenTerminalChildModel> childQuery(String OPEN_TERMINAL_REQ_ID ){
		 Map<String,String> params = new HashMap<String,String>();
		 params.put("OPEN_TERMINAL_REQ_ID", OPEN_TERMINAL_REQ_ID);
		 params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
         return this.findList("openTerminal.queryChldByFkId", params);
	}    
	
    public void txBatch4DeleteChild(Map<String,String> params){
    	//删除区域扁平表的相关信息
    	this.delete("openTerminal.delChldByFkId", params);
    	
    }
    
    public List<OpenTerminalChildModel> loadChilds(String OPEN_TERMINAL_REQ_DTL_IDS){
    	return findList("openTerminal.loadByIds", OPEN_TERMINAL_REQ_DTL_IDS);
    }
    
    /**
     * 明细编辑
     */
    public void txChildEdit(String OPEN_TERMINAL_REQ_ID,OpenTerminalChildModel child,
    		List<TerminalCommModel> commList){
		Map<String,Object>params = new HashMap<String,Object>();
		params.put("OPEN_TERMINAL_REQ_ID", OPEN_TERMINAL_REQ_ID);
		params.put("CITY_POPULATION", child.getCITY_POPULATION());
		params.put("CITY_GDP", child.getCITY_GDP());
		params.put("CITY_MARKET_NUM", child.getCITY_MARKET_NUM());
		params.put("MALL_RANK", child.getMALL_RANK());
		params.put("MALL_NAME", child.getMALL_NAME());
		params.put("MALL_ALL_AREA", child.getMALL_ALL_AREA());
		params.put("TERMINAL_NUM", child.getTERMINAL_NUM());
		params.put("TERMINAL_SALE_AMOUNT", child.getTERMINAL_SALE_AMOUNT());
		params.put("PLAN_TERMINAL_NUM", child.getPLAN_TERMINAL_NUM());
		params.put("PLAN_TERMINAL_BRAND", child.getPLAN_TERMINAL_BRAND());
		params.put("TERMINAL_ADDR", child.getTERMINAL_ADDR());
		params.put("TERMINAL_AREA", child.getTERMINAL_AREA());
		params.put("PLAN_YEAR_ORDER_AMOUNT", child.getPLAN_YEAR_ORDER_AMOUNT());
		params.put("PLAN_RET_AMOUNT", child.getPLAN_RET_AMOUNT());
		params.put("GUIDE_STAFF_NUM", child.getGUIDE_STAFF_NUM());
		params.put("GUIDE_STAFF_NUM", child.getGUIDE_STAFF_NUM());
		String OPEN_TERMINAL_REQ_DTL_ID = child.getOPEN_TERMINAL_REQ_DTL_ID();
	    if(StringUtil.isEmpty(OPEN_TERMINAL_REQ_DTL_ID)){
	    	OPEN_TERMINAL_REQ_DTL_ID = StringUtil.uuid32len();
	    	params.put("OPEN_TERMINAL_REQ_DTL_ID", OPEN_TERMINAL_REQ_DTL_ID);
	    	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
	    	this.insert("openTerminal.insertChld", params);
	    }else{
	    	params.put("OPEN_TERMINAL_REQ_DTL_ID", OPEN_TERMINAL_REQ_DTL_ID);
	    	this.update("openTerminal.updateChldById", params); 
    	}
	    //竞品
	    if(null != commList && !commList.isEmpty()){
	    	txCommEdit(OPEN_TERMINAL_REQ_DTL_ID, commList);
	    }
    }
    
    /**
     * 编辑竞品
     * @param OPEN_TERMINAL_REQ_DTL_ID
     * @param commList
     */
    public void txCommEdit(String OPEN_TERMINAL_REQ_DTL_ID,List<TerminalCommModel> commList){
    	List<Map<String,Object>> addList = new ArrayList<Map<String,Object>>();
    	List<Map<String,Object>> updateList = new ArrayList<Map<String,Object>>();
    	
    	for(TerminalCommModel comm : commList){
    		Map<String,Object> params = new HashMap<String,Object>();
            String COMMODITIES_ID = comm.getCOMMODITIES_ID();
    		params.put("OPEN_TERMINAL_REQ_DTL_ID", OPEN_TERMINAL_REQ_DTL_ID);
    		params.put("COMMODITIES_NAME", comm.getCOMMODITIES_NAME());
    		params.put("SEQ_NO", "");
    		if(StringUtil.isEmpty(COMMODITIES_ID)){
    			COMMODITIES_ID = StringUtil.uuid32len();
    			params.put("COMMODITIES_ID", COMMODITIES_ID);
    			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    			addList.add(params);
    		}else{
    			params.put("COMMODITIES_ID", COMMODITIES_ID);
    			updateList.add(params);
    		}
    	}
    	if(!addList.isEmpty()){
			this.batch4Update("openTerminal.insertComm", addList);
		}
		if(!updateList.isEmpty()){
			this.batch4Update("openTerminal.updateCommById", updateList);
		}
    	
    }
    
    /**
     * 删除竞品
     * @param COMMODITIES_ID
     */
    public void txDeleteComm(String COMMODITIES_ID){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("COMMODITIES_ID", COMMODITIES_ID);
    	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
    	this.update("openTerminal.updateCommById", paramMap);
    }
    
    @SuppressWarnings("unchecked")
	public  Map<String,Object> loadChild(String OPEN_TERMINAL_REQ_ID ){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("OPEN_TERMINAL_REQ_ID", OPEN_TERMINAL_REQ_ID);
    	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	return (Map<String, Object>) load("openTerminal.queryChldByFkId",paramMap);
    }
    
    /**
     * 加载竞品
     * @param OPEN_TERMINAL_REQ_DTL_ID
     * @return
     */
    public List<Map<String,Object>> loadComms(String OPEN_TERMINAL_REQ_DTL_ID){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("OPEN_TERMINAL_REQ_DTL_ID", OPEN_TERMINAL_REQ_DTL_ID);
    	return this.findList("openTerminal.loadComms", paramMap);
    }
    /**
     *查询竞品的名称
     * @param OPEN_TERMINAL_REQ_DTL_ID
     * @return
     */
    public  Map<String,Object> loadCommNames(String OPEN_TERMINAL_REQ_DTL_ID ){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("OPEN_TERMINAL_REQ_DTL_ID", OPEN_TERMINAL_REQ_DTL_ID);
    	return (Map<String, Object>) load("openTerminal.loadCommsCommoditiesnames",paramMap);
    }
    
    /**
     * @param RNVTN_PROP
     * @return
     */
    public Map<String,Object> queryJudgeModel(String RNVTN_PROP){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("RNVTN_PROP", RNVTN_PROP);
    	List<Map<String,Object>> list = this.findList("openTerminal.selectBrothers", paramMap);
     	if(null != list && !list.isEmpty()){
     		return list.get(0);
     	}
     	return null;
    }
}
