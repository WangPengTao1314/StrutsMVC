/*
 * 项目名称：平台
 * 系统名：业务共通公用方法
 * 文件名：LogicUtil.java
 */
package com.hoperun.commons.util;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.base.chann.service.impl.ChannServiceImpl;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.InterReturnMsg;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.model.inter.AdvcOrderForWeChatModel;
import com.hoperun.commons.model.inter.InterBranchModel;
import com.hoperun.commons.model.inter.InterBrandModel;
import com.hoperun.commons.model.inter.InterChannModel;
import com.hoperun.commons.model.inter.InterEmployeeModel;
import com.hoperun.commons.model.inter.InterProductModel;
import com.hoperun.commons.model.inter.InterUserModel;
import com.hoperun.commons.model.inter.ReturnInterInfo;
import com.hoperun.commons.techorder.service.impl.TechorderManagerServiceImpl;
import com.hoperun.commons.util.impl.LogicUtilServiceImpl;
import com.hoperun.commons.webservice.CommWebServiceClient;
import com.hoperun.drp.base.store.service.impl.StoreServiceImpl;
import com.hoperun.drp.finance.account.service.impl.ActServiceImpl;
import com.hoperun.drp.sale.advcgoodsapp.model.AdvcgoodsappModelChld;
import com.hoperun.drp.sale.advcgoodsapp.service.AdvcgoodsappService;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.service.AdvcorderService;
import com.hoperun.drp.sale.advcorder.service.impl.AdvcorderServiceImpl;
import com.hoperun.drp.sale.advctoout.model.AdvctooutModelChld;
import com.hoperun.drp.sale.advctoout.service.AdvctooutService;
import com.hoperun.drp.sale.dstrorder.model.DstrorderModelChld;
import com.hoperun.drp.sale.dstrorder.service.DstrorderService;
import com.hoperun.drp.sale.repairapp.model.RepairappModelChld;
import com.hoperun.drp.sale.repairapp.service.RepairappService;
import com.hoperun.drp.store.allocate.model.AllocateModelChld;
import com.hoperun.drp.store.allocate.service.AllocateService;
import com.hoperun.drp.store.storein.service.StoreinService;
import com.hoperun.drp.store.storeinnotice.service.StoreinnoticeService;
import com.hoperun.drp.store.storeout.service.StoreoutService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.service.TurnoverplanService;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.BmgzService;
import com.hoperun.sys.service.SysLoginService;
import com.hoperun.sys.service.impl.FlowServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class LogicUtil.
 * 
 * @module 共通设计
 * @func 业务共通公用方法
 * @version 1.1
 * @author zhuxw
 */
public class LogicUtil {
	
	private static String callESBAsyMethod = "doAsynTask"; //调用ESB异步方法

	private static String callESBSynMethod = "doSyncTask"; //调用ESB同步方法

	/** The logger. */
	private static Logger logger = Logger.getLogger(LogicUtil.class);
	

	/**
	 * Description :获取编码规则.
	 * @param bmdx 编码对象
	 * @return 编码
	 * @throws Exception
	 *             * @throws ServiceException the service exception
	 * @throws ServiceException
	 *             the service exception
	 */
	public static String getBmgz(String bmdx) throws ServiceException {
		logger.info("Enter LogicUtil.getBmgz().bmdx is " + bmdx);
		BmgzHelper bh = BmgzHelper.getInstance();
		if (null == bh) {
			logger.error("Init BmgzHelper.class failed.");
			throw new ServiceException();
		}
		return bh.createCode(bmdx);
	}
	
	/**
	 * 
	 * @param cellname 列名 字段编码
	 * @param tablename 表名
	 * @param Prefix 编码前缀
	 * @param length 段长度
	 * @param userBean
	 * @return
	 */
	public static String getBillNo(String cellname,String tablename,String Prefix,int length,UserBean userBean){
		BmgzService bmgzService = (BmgzService) SpringContextUtil
		.getBean("bmgzService");
		String billNo = "";
		//String Prefix = BusinessConsts.ADVC_ORDER_NO_PREFIX;//预订单 单头
		int lng = Prefix.length();
		String date = DateUtil.format(new Date(), "yyyyMMdd");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cellname", cellname);//列名 字段编码
		params.put("tablename", tablename);//表名
		params.put("date", date);
		params.put("index", lng+1);
		params.put("endIndex", date.length());
		if(null != userBean){
			params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套
		}
		List<Map<String,String>> result = bmgzService.findList("BMGZ.queryMaxBillNo", params);
		if(null == result || result.isEmpty()){
			billNo = Prefix+date+getRoundStr(length,0);
		}else{
			String ORDER_NO = result.get(0).get(cellname);
			String suffix = ORDER_NO.substring(lng+8,ORDER_NO.length());
			int num = StringUtil.getInteger(suffix);
			num = num+1;
			billNo =  Prefix+date+getRoundStr(length,num);
		}
		
	    return billNo;
	}
	
	public static String getRoundStr(int leng,int num){
		String str  = Integer.toString(num);
		while(str.length()<leng){
			str = "0"+str;
		}
		return str;
	}
	
	/**
	 * 
	 * @param cellname 列名 字段编码
	 * @param tablename 表名
	 * @param Prefix 编码前缀
	 * @param length 段长度
	 * @param LAST_BILL_NO 上一个编号
	 * @param userBean
	 * @return
	 */
	public static String getBillSequenceNo(String cellname,String tablename,String Prefix,
			int length,String LAST_BILL_NO,UserBean userBean){
		BmgzService bmgzService = (BmgzService) SpringContextUtil
		.getBean("bmgzService");
		String billNo = "";
		List<Map<String,String>> result = null;
		int lng = Prefix.length();
		String date = DateUtil.format(new Date(), "yyyyMMdd");
		String ORDER_NO = "";
		if(StringUtil.isEmpty(LAST_BILL_NO)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("cellname", cellname);//列名 字段编码
			params.put("tablename", tablename);//表名
			params.put("date", date);
			params.put("index", lng+1);
			params.put("endIndex", date.length());
			if(null != userBean){
				params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套
			}
			result = bmgzService.findList("BMGZ.queryMaxBillNo", params);
			if(null == result || result.isEmpty()){
				billNo = Prefix+date+getRoundStr(length,1);
				return  billNo;
			}else{
			    ORDER_NO = result.get(0).get(cellname);
			}
			 
		}
		
		if(StringUtil.isEmpty(ORDER_NO)){
			ORDER_NO = LAST_BILL_NO;
		}
		String suffix = ORDER_NO.substring(lng+8,ORDER_NO.length());
		int num = StringUtil.getInteger(suffix);
		num = num+1;
		billNo =  Prefix+date+getRoundStr(length,num);
	    return billNo;
	}
	
	
	public static String getRYXXBillNo(String cellname,String tablename,String Prefix,int length,String subString,UserBean userBean){
		BmgzService bmgzService = (BmgzService) SpringContextUtil
		.getBean("bmgzService");
		String billNo = "";
		int lng = Prefix.length();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cellname", cellname);//列名 字段编码
		params.put("tablename", tablename);//表名
		params.put("date", subString);
		params.put("index", 0);
		params.put("endIndex", subString.length());
		List<Map<String,String>> result =  bmgzService.findList("BMGZ.queryMaxBillNo", params);
		if(null == result || result.isEmpty()){
			billNo = Prefix+getRoundStr(length,0);
		}else{
			String ORDER_NO = result.get(0).get(cellname);
			String suffix = ORDER_NO.substring(lng,ORDER_NO.length());
			int num = StringUtil.getInteger(suffix);
			num = num+1;
			billNo =  Prefix+getRoundStr(length,num);
		}
		
	    return billNo;
	}
	

	/**
	 * 根据单据ID 获得审批相关信息 Page customized.
	 * @param request the request
	 * 
	 * @param confNo the conf no
	 */
	public static List getFlowInfos(String billId) {

		FlowServiceImpl flowServiceImpl = (FlowServiceImpl) SpringContextUtil
				.getBean("flowService");
		if (null == flowServiceImpl) {
			logger.error("Init flowServiceImpl.class failed.");
			throw new ServiceException();
		}
		return flowServiceImpl.getFlowInfos(billId);
	}

	/*
	 * 记操作日志
	 * 
	 * @param UC_NAME the 模块名称
	 * 
	 * @param ACT_NAME the 操作名称
	 * 
	 * @param OPRATOR the 操作人
	 * 
	 * @param STATE the 成功/失败
	 * 
	 * @param REMARK the 备注
	 * 
	 * @return void
	 */
	public static void actLog(String UC_NAME, String ACT_NAME, String OPRATOR,
			String STATE, String REMARK,String APPCODE,String APPID,String OPRCODE,String KEY) {
		System.err.println("Consts.ACT_LOG===" + Consts.ACT_LOG);
		System.err.println("Consts.REMARK===" +REMARK);
		if (Consts.ACT_LOG) {
			if (!"成功".equals(STATE))
				STATE = "失败";
			REMARK = StringUtil.splitStringAtBeginByte(REMARK,3000);
			SysLoginService sysLoginService = (SysLoginService) SpringContextUtil
					.getBean("sysLoginService");
			if (null == sysLoginService) {
				logger.error("Init sysLoginService.class failed.");
				throw new ServiceException();
			}
			sysLoginService.doActLog(UC_NAME, ACT_NAME, OPRATOR, STATE, REMARK, APPCODE, APPID, OPRCODE,KEY);
		}
	}
	
	/*
	 * 记操作日志
	 * 
	 * @param UC_NAME the 模块名称
	 * 
	 * @param ACT_NAME the 操作名称
	 * 
	 * @param OPRATOR the 操作人
	 * 
	 * @param STATE the 成功/失败
	 * 
	 * @param REMARK the 备注
	 * 
	 * @return void
	 */
	public static void actLog(String UC_NAME, String ACT_NAME, String OPRATOR,
			String STATE, String REMARK,String APPCODE,String APPID,String OPRCODE) {
		System.err.println("Consts.ACT_LOG===" + Consts.ACT_LOG);
		System.err.println("Consts.REMARK===" +REMARK);
		if (Consts.ACT_LOG) {
			if (!"成功".equals(STATE))
				STATE = "失败";
			REMARK = StringUtil.splitStringAtBeginByte(REMARK,3000);
			SysLoginService sysLoginService = (SysLoginService) SpringContextUtil
					.getBean("sysLoginService");
			if (null == sysLoginService) {
				logger.error("Init sysLoginService.class failed.");
				throw new ServiceException();
			}
			sysLoginService.doActLog(UC_NAME, ACT_NAME, OPRATOR, STATE, REMARK, APPCODE, APPID, OPRCODE,"");
		}
	}

	/**
	 * Store notice to in.
	 * 
	 * @param BussId
	 *            the buss id
	 */
	public static void storeNoticeToIn(String BussId, HttpServletRequest request) throws Exception{
		ArrayList storeList = new ArrayList();
		ArrayList storeDtlList = new ArrayList();
		if (!StringUtil.isEmpty(BussId)) {
			List<HashMap> storeNoticeList = getStoreInfo(BussId);
			UserBean userBean = ParamUtil.getUserBean(request);
			for (int i = 0; i < storeNoticeList.size(); i++) {
				HashMap<String, String> storeNoticeMap = storeNoticeList.get(i);
				String storinNoticeId = storeNoticeMap.get("STOREIN_NOTICE_ID");
				String storinNoticeNo = storeNoticeMap.get("STOREIN_NOTICE_NO");
				String storeinStoreId = storeNoticeMap.get("STOREIN_STORE_ID");
				String storeinId = StringUtil.uuid32len();
				setStoreinData(storeinId,storinNoticeId,storinNoticeNo,
						 storeNoticeMap, userBean);
				storeList.add(storeNoticeMap);
				List<HashMap> noticeDtllist = getStoreDtlInfo(storinNoticeId,
						storeinStoreId);
				for (int j = 0; j < noticeDtllist.size(); j++) {
					HashMap<String, String> noticeDtlData = noticeDtllist
							.get(j);
					setStoreinDtlData(storeinId,noticeDtlData);
					storeDtlList.add(noticeDtlData);
				}
			}
			insertSotre(storeList, storeDtlList);
		} else {
			logger.error("BussId is null.");
			throw new Exception("入库通知单ID为空!");
		}
		storeList.clear();
		storeDtlList.clear();
	}

	/**
	 * 组织入库单表数据
	 * 
	 * @param storeNoticeMap
	 * @param userBean
	 * @return
	 */
	private static void setStoreinData(String storeinId,String storinNoticeId,String storinNoticeNo,
			HashMap<String, String> storeNoticeMap, UserBean userBean)throws Exception {
		storeNoticeMap.put("STOREIN_ID", storeinId);
		storeNoticeMap.put("STOREIN_NO", getBmgz("DRP_STOREIN_NO"));
		storeNoticeMap.put("FROM_BILL_ID", storinNoticeId);
		storeNoticeMap.put("FROM_BILL_NO", storinNoticeNo);
//		storeNoticeMap.put("STORAGE_FLAG",
//				BusinessConsts.DEL_FLAG_COMMON);
		storeNoticeMap.put("STATE", BusinessConsts.UNDONE);
		storeNoticeMap.put("STORAGE_FLG",
				BusinessConsts.DEL_FLAG_COMMON);
		storeNoticeMap.put("CREATOR", userBean.getXTYHID());
		storeNoticeMap.put("CRE_NAME", userBean.getXM());
		storeNoticeMap.put("DEPT_ID", userBean.getBMXXID());
		storeNoticeMap.put("DEPT_NAME", userBean.getBMMC());
		storeNoticeMap.put("ORG_ID", userBean.getJGXXID());
		storeNoticeMap.put("ORG_NAME", userBean.getJGMC());
		storeNoticeMap.put("LEDGER_ID", userBean.getLoginZTXXID());
		storeNoticeMap.put("LEDGER_NAME", userBean.getLoginZTMC());
		storeNoticeMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		storeNoticeMap.put("DEAL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
	}

	/**组织入库单明细数据
	 * @param storeinId
	 * @param noticeDtlData
	 */
	private static void setStoreinDtlData(String storeinId,
			HashMap<String, String> noticeDtlData)throws Exception {
		String storeinNoticeDtlId = noticeDtlData.get("STOREIN_NOTICE_DTL_ID");
		noticeDtlData.put("STOREIN_DTL_ID", StringUtil.uuid32len());
		noticeDtlData.put("STOREIN_ID", storeinId);
		noticeDtlData.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		noticeDtlData.put("FROM_BILL_DTL_ID", storeinNoticeDtlId);
		noticeDtlData.put("INS_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
	}

	/**
	 * 取主表DRP_STOREIN信息 从表(DRP_STOREIN_NOTICE) and (DRP_STOREIN_NOTICE_DTL)
	 * 
	 * @param BussId
	 * @return
	 */
	private static List getStoreInfo(String BussId)throws Exception {
		StoreinnoticeService storeNoticeService = (StoreinnoticeService) SpringContextUtil
				.getBean("storeinnoticeService");
		return storeNoticeService.loadFullData(BussId);
	}

	/**
	 * 取子表DRP_STOREIN_DTL信息 从表(DRP_STOREIN_NOTICE) and (DRP_STOREIN_NOTICE_DTL)
	 * 
	 * @param BussId
	 * @return
	 */
	private static List getStoreDtlInfo(String storinNoticeId,
			String storeinStoreId)throws Exception {
		StoreinnoticeService storeNoticeService = (StoreinnoticeService) SpringContextUtil
				.getBean("storeinnoticeService");
		return storeNoticeService.loadFullDtlData(storinNoticeId,
				storeinStoreId);
	}
	
	/**
	 * 
	 * @param BussId 来源数据表的id
	 * @param type   来源数据表的数据库名
	 * @param userBean   登录用户信息
	 * @param locaInfo   发货方信息，收货方信息，库房信息。键为入库单表里各值对应的列明
	 * @throws Exception
	 * 该方法返回新增入库单id
	 */
	public static String genStoreInOrder(String BussId,String type,UserBean userBean,Map<String,String> locaInfo)throws Exception {
		if("DRP_ADVC_ORDER".equals(type)){
			return inAdvcOrderEdit(BussId,userBean,locaInfo);
		}else{
			return null;
		}
	}
	/**
	 * 
	 * @param BussId 来源数据表的id
	 * @param type   来源数据表的数据库名
	 * @param userBean   登录用户信息
	 * @param locaInfo   发货方信息，收货方信息，库房信息。键为入库单表里各值对应的列明
	 * @param detIds  所选子表明细id
	 * @param detIds  所选子表明细信息
	 * @throws Exception
	 * 该方法返回新增出库单id
	 */
	public static String genStoreOutOrder(String BussId,String type,UserBean userBean,Map<String,String> locaInfo,List chldModelList)throws Exception{
		if("DRP_ADVC_ORDER".equals(type)){
			return instAdvcStoreout(BussId,userBean,locaInfo,chldModelList);
		}else if("DRP_ALLOCATE".equals(type)){
			return instAllocateStoreout(BussId,userBean);
		}else if("ERP_DSTR_ORDER".equals(type)){//分发出库
			return instDstrStoreout(BussId,userBean,locaInfo);
		}else if("DRP_ADVC_SEND_REQ".equals(type)){
			return instSaleSotreout(BussId,userBean);//预订单发货申请
		}else if("FXCK".equals(type)){//FXCK返修出库
			return instRepairSotreout(BussId,userBean);
		}
		return null;
	}
			
	

	/**
	 * 插入入库单（storeList）和入库单明细（storeDtlList）
	 * 
	 * @param storeList
	 * @param storeDtlList
	 */
	private static void insertSotre(List storeList, List storeDtlList)throws Exception {
		StoreinnoticeService storeNoticeService = (StoreinnoticeService) SpringContextUtil
				.getBean("storeinnoticeService");
		storeNoticeService.txInsertStoreinData(storeList, storeDtlList);
	}
	
	/**
	 * 获得图片服务器路径
	 * add by zhuxw 2013-1-23
	 * @param storeList
	 * @param storeDtlList
	 */
	public static String getPicPath(String fileName)  {
		String picServer = Properties.getString("PIC_SERVER_URL");
		String secondPath = Properties.getString("PICTURE_DIR");
		String picPath=picServer+secondPath+"/"+fileName;
		return picPath;
	}
	
	
	/**
	 * 获得图片服务器路径
	 * add by zzb 2014-8-30
	 * @param picServerURL 服务器地址
	 * @param fileName 文件名
	 */
	public static String getPicServerURL(String picServerURL,String fileName)  {
		String secondPath = Properties.getString("PICTURE_DIR");
		String picPath = picServerURL+secondPath+"/"+fileName;
		return picPath;
	}
	
	
	/**
	 * 根据预订单ID获得预订单的追加预付款金额
	 * 
	 * @param storeList
	 * @param storeDtlList
	 */
	private static double qryAdd_ADVC_AMOUNT(String billId)throws Exception {
		return 0;
	}
	//预订单转入库单
	private static String inAdvcOrderEdit(String BussId,UserBean userBean,Map<String,String> locaInfo){
		//创建预订单Service
		AdvcorderService advcorderService = (AdvcorderService) SpringContextUtil.getBean("advcorderService");
		StoreinService storeinService = (StoreinService) SpringContextUtil.getBean("storeinService");
		//按预订单主表id获取子表明细信息
		List <AdvcorderModelChld> chldList=advcorderService.childQuery(BussId);
		//按预订单id获取预订单信息，主要用于获取预订单编号、id、备注反填到来源单据编号、id、备注里
		Map<String,String> map=advcorderService.load(BussId);
		//创建入库单map
		Map<String,String> model=new HashMap<String,String>();
		String STOREIN_ID=StringUtil.uuid32len();
		model.put("STOREIN_ID",STOREIN_ID);//入库单id
		model.put("STOREIN_NO",LogicUtil.getBmgz("DRP_STOREIN_NO"));//入库单编号
		model.put("BILL_TYPE","终端退货");//单据类型
		model.put("FROM_BILL_ID",map.get("ADVC_ORDER_ID"));//来源单据id
		model.put("FROM_BILL_NO",map.get("ADVC_ORDER_NO"));//来源单据编号
		model.put("RECV_CHANN_ID",userBean.getCHANN_ID());//收货方id
		model.put("RECV_CHANN_NO",userBean.getCHANN_NO());//收货方编号
		model.put("RECV_CHANN_NAME",userBean.getCHANN_NAME());//收货方名称
		
		model.put("SEND_CHANN_ID",map.get("TERM_ID"));//退货终端id
		model.put("SEND_CHANN_NO",map.get("TERM_NO"));//退货终端编号
		model.put("SEND_CHANN_NAME",map.get("TERM_NAME"));//退货终端名称
		
		model.put("STOREIN_STORE_ID",locaInfo.get("STOREIN_STORE_ID"));//入库库房id
		model.put("STOREIN_STORE_NO",locaInfo.get("STOREIN_STORE_NO"));//入库库房编号
		model.put("STOREIN_STORE_NAME",locaInfo.get("STOREIN_STORE_NAME"));//入库库房名称
		model.put("STORAGE_FLAG",locaInfo.get("STORAGE_FLAG"));//库位管理标记
		model.put("DEAL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		model.put("STATE",BusinessConsts.UNDONE);//状态
		model.put("REMARK",map.get("REMARK"));//备注
		model.put("STATEMENUT_DATE", map.get("RETURN_STATEMENT_DATE"));
		model.putAll(sysFild(userBean));
		model.put("YEAR_MONTH","sysdate");//年份月份
		List<Map<String,String>> chld=new ArrayList<Map<String,String>>();
		for(int i=0;i<chldList.size();i++){
			Map<String,String> param=(Map<String, String>) chldList.get(i);
			Map<String,String> params=new HashMap<String,String>();
			params.put("STOREIN_DTL_ID",StringUtil.uuid32len());//入库单明细ID
			params.put("STOREIN_ID",STOREIN_ID);//入库单ID
			params.put("PRD_ID",param.get("PRD_ID"));//货品ID
			params.put("PRD_NO",param.get("PRD_NO"));//货品NO
			params.put("PRD_NAME",param.get("PRD_NAME"));//货品名称
			params.put("PRD_SPEC",param.get("PRD_SPEC"));//规格型号
			params.put("PRD_COLOR",param.get("PRD_COLOR"));//颜色
			params.put("BRAND",param.get("BRAND"));//品牌
			params.put("STD_UNIT",param.get("STD_UNIT"));//标准单位
			Object obj=param.get("ORDER_NUM");
			params.put("NOTICE_NUM",obj.toString());//通知入库数量
			//params.put("REMARK",param.get("REMARK"));//备注
			obj=param.get("PRICE");
			params.put("PRICE",StringUtil.nullToSring(obj));//单价
			obj=param.get("DECT_RATE");
			params.put("DECT_RATE",StringUtil.nullToSring(obj));//折扣率
			obj=param.get("DECT_PRICE");
			params.put("DECT_PRICE",StringUtil.nullToSring(obj));//折扣价
			obj=param.get("PAYABLE_AMOUNT");
			params.put("DECT_AMOUNT",StringUtil.nullToSring(obj));//折后金额
			params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//删除标记
			params.put("FROM_BILL_DTL_ID",param.get("ADVC_ORDER_DTL_ID"));//来源单据明细ID
			params.put("YEAR_MONTH","sysdate");//年份月份
			params.put("SPCL_TECH_ID", param.get("SPCL_TECH_ID"));
			chld.add(params);
		}
		storeinService.txAddStorein(model,chld);
		//把设置来源数据的状态
		Map<String,String> upMap=new HashMap<String,String>();
		upMap.put("UPDATOR", userBean.getRYXXID());//更新人id
		upMap.put("UPD_NAME", userBean.getXM());//更新人姓名
		upMap.put("UPD_TIME", "sysdate");//更新人时间
		upMap.put("ADVC_ORDER_ID", BussId);
		upMap.put("STATE", "待退货");
		advcorderService.txUpdateById(upMap);
		return STOREIN_ID;
	}
	/**
	 * 预订单发货转出库单
	 * @param BussId 预订单id
	 * @param userBean 登录信息
	 * @param locaInfo 库房库位信息
	 * @return
	 */
	private static String instAdvcStoreout(String BussId,UserBean userBean,Map<String,String> locaInfo,List chldModelList){
		//创建预订单发货Service
		AdvctooutService advctooutService = (AdvctooutService) SpringContextUtil.getBean("advctooutService");
		StoreoutService storeoutService = (StoreoutService) SpringContextUtil.getBean("storeoutService");
		//按预订单明细id获取子表明细信息
		List <AdvctooutModelChld> chldList=chldModelList;
		//按预订单id获取预订单信息
		Map<String,String> map=advctooutService.load(BussId);
		//创建出库单map
		Map<String,Object> model=new HashMap<String,Object>();
		String STOREOUT_ID=StringUtil.uuid32len();
		model.put("STOREOUT_ID", STOREOUT_ID);//出库单ID
		model.put("STOREOUT_NO", LogicUtil.getBmgz("DRP_STOREOUT_NO"));//出库单编号
		model.put("BILL_TYPE", "销售出库");//单据类型
		model.put("FROM_BILL_ID", map.get("ADVC_ORDER_ID"));//来源单据id
		model.put("FROM_BILL_NO", map.get("ADVC_ORDER_NO"));//来源单据编号
		if(locaInfo!=null){
			model.put("STOREOUT_STORE_ID", locaInfo.get("STORE_ID"));//出库库房id
			model.put("STOREOUT_STORE_NO", locaInfo.get("STORE_NO"));//出库库房编号
			model.put("STOREOUT_STORE_NAME", locaInfo.get("STORE_NAME"));//出库库房名称
			Object STORAGE_FLAG=locaInfo.get("STORAGE_FLAG");
			model.put("STORAGE_FLAG", STORAGE_FLAG.toString());//库位管理标记
			model.put("REMARK", locaInfo.get("REMARK"));
		}
		model.put("TERM_ID", map.get("TERM_ID"));//终端信息id
		model.put("TERM_NO", map.get("TERM_NO"));//终端编号
		model.put("TERM_NAME", map.get("TERM_NAME"));//终端名称
		model.put("SALE_DATE", map.get("SALE_DATE"));//销售日期
		model.put("SALE_PSON_ID", map.get("SALE_PSON_ID"));//业务员id
		model.put("SALE_PSON_NAME", map.get("SALE_PSON_NAME"));//业务员名称
		model.put("CUST_NAME", map.get("CUST_NAME"));//客户名称
		model.put("TEL", map.get("TEL"));//电话
		model.put("RECV_ADDR", map.get("RECV_ADDR"));//收货地址
		model.put("DEAL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		model.put("ORDER_RECV_DATE", map.get("ORDER_RECV_DATE"));//要求到货日期
		Object ADVC_AMOUNT=map.get("ADVC_AMOUNT");
		model.put("ADVC_AMOUNT", ADVC_AMOUNT.toString());//订金金额
		Object PAYABLE_AMOUNT=map.get("PAYABLE_AMOUNT");
		model.put("STOREOUT_AMOUNT", PAYABLE_AMOUNT.toString());//订货总金额
		model.put("STATE", BusinessConsts.UNDONE);//状态
		model.put("RECV_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		model.putAll(sysFild(userBean));//系统字段
		List<Map<String,String>> chld=new ArrayList<Map<String,String>>();
		String SPCL_TECH_IDS="";
		for (AdvctooutModelChld chldMap : chldList) {
			Map<String,String> maps=new HashMap<String,String>();
			String PRD_ID=chldMap.getPRD_ID();
			maps.put("STORE_ID", locaInfo.get("STORE_ID"));
			maps.put("PRD_ID", PRD_ID);
			maps.put("LEDGER_ID", userBean.getLoginZTXXID());
        	Map<String,String> prdMap=(Map<String, String>) advctooutService.load("Advctoout.loadProductNum",maps);
        	float STORE_NUM=0;
        	if(prdMap!=null){
        		Object STORE_NUM_OBJ=prdMap.get("NUM");
        		if(STORE_NUM_OBJ!=null){
        			STORE_NUM=Float.parseFloat(STORE_NUM_OBJ.toString());
        		}
        	}
        	Object ORDER_NUM=chldMap.getORDER_NUM();
        	float NOTICE_NUM=Float.parseFloat(ORDER_NUM.toString());
        	if(STORE_NUM<NOTICE_NUM){
        		throw new ServiceException("对不起，所选货品库存不足,请重新选择!");
        	}
        	String SPCL_TECH_ID=chldMap.getSPCL_TECH_ID();
        	if(!StringUtil.isEmpty(SPCL_TECH_ID)){
        		SPCL_TECH_IDS+="'"+SPCL_TECH_ID+"',";
        	}
        	for (int i = 0; i < Integer.parseInt(ORDER_NUM.toString()); i++) {
        		Map<String,String> params=new HashMap<String,String>();
        		String STOREOUT_DTL_ID=StringUtil.uuid32len();
                params.put("STOREOUT_DTL_ID", STOREOUT_DTL_ID);
                params.put("STOREOUT_ID", STOREOUT_ID);
                params.put("PRD_ID", chldMap.getPRD_ID());
                params.put("PRD_NO", chldMap.getPRD_NO());
                params.put("PRD_NAME", chldMap.getPRD_NAME());
                params.put("PRD_SPEC", chldMap.getPRD_SPEC());
                params.put("PRD_COLOR", chldMap.getPRD_COLOR());
                params.put("BRAND", chldMap.getBRAND());
                params.put("STD_UNIT", chldMap.getSTD_UNIT());
                params.put("NOTICE_NUM", "1");
                params.put("PRICE", chldMap.getPRICE()+"");
                params.put("DECT_RATE", chldMap.getDECT_RATE()+"");
                params.put("DECT_PRICE", chldMap.getDECT_PRICE()+"");
                params.put("DECT_AMOUNT", chldMap.getDECT_PRICE()+"");
                params.put("REMARK", chldMap.getREMARK());
    		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    		    params.put("SPCL_TECH_ID", SPCL_TECH_ID);
    		    params.put("FROM_BILL_DTL_ID", chldMap.getADVC_ORDER_DTL_ID());
    		    chld.add(params);
			}
		}
		if(!StringUtil.isEmpty(SPCL_TECH_IDS)){
        	SPCL_TECH_IDS=SPCL_TECH_IDS.substring(0,SPCL_TECH_IDS.length()-1);
        	advctooutService.upUSE_FLAG(SPCL_TECH_IDS);
        }
		storeoutService.txAddStoreOut(model,chld);
		//去掉预订单多余状态修改
//		Map<String,String> upMap=new HashMap<String,String>();
//		upMap.put("UPDATOR", userBean.getRYXXID());
//		upMap.put("UPD_NAME", userBean.getXM());
//		upMap.put("UPD_TIME", "sysdate");
//		upMap.put("STATE", "待发货");
//		upMap.put("ADVC_ORDER_ID", BussId);
//		advctooutService.txUpdateById(upMap);
		return STOREOUT_ID;
	}
	/**
	 * 调拨单转出库单
	 * @param BussId 调拨单id
	 * @param userBean 登陆信息
	 * @return
	 */
	private static String instAllocateStoreout(String BussId,UserBean userBean){
		//创建调拨单Service
		AllocateService allocateService = (AllocateService) SpringContextUtil.getBean("allocateService");
		StoreoutService storeoutService = (StoreoutService) SpringContextUtil.getBean("storeoutService");
		//按调拨单主表id获取子表明细信息
		List <AllocateModelChld> chldList=allocateService.childQuery(BussId);
		//按调拨单id获取调拨单信息
		Map<String,String> map=allocateService.load(BussId);
		//创建出库单map
		Map<String,Object> model=new HashMap<String,Object>();
		String STOREOUT_ID=StringUtil.uuid32len();
		model.put("STOREOUT_ID", STOREOUT_ID);//出库单ID
		model.put("STOREOUT_NO", LogicUtil.getBmgz("DRP_STOREOUT_NO"));//出库单编号
		model.put("BILL_TYPE","调拨出库");//单据类型
		model.put("FROM_BILL_ID", map.get("ALLOCATE_ID"));//来源单据
		model.put("FROM_BILL_NO", map.get("ALLOCATE_NO"));
		model.put("SEND_CHANN_ID", map.get("ALLOC_OUT_CHANN_ID"));
		model.put("SEND_CHANN_NO", map.get("ALLOC_OUT_CHANN_NO"));
		model.put("SEND_CHANN_NAME", map.get("ALLOC_OUT_CHANN_NAME"));
		model.put("STOREOUT_STORE_ID", map.get("ALLOC_OUT_STORE_ID"));
		model.put("STOREOUT_STORE_NO", map.get("ALLOC_OUT_STORE_NO"));
		model.put("STOREOUT_STORE_NAME", map.get("ALLOC_OUT_STORE_NAME"));
		Object STORAGE_FLAG=map.get("STORAGE_FLAG");
		model.put("STORAGE_FLAG", STORAGE_FLAG.toString());
		model.put("DEAL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		model.put("RECV_CHANN_ID", map.get("ALLOC_IN_CHANN_ID"));
		model.put("RECV_CHANN_NO", map.get("ALLOC_IN_CHANN_NO"));
		model.put("RECV_CHANN_NAME", map.get("ALLOC_IN_CHANN_NAME"));
		model.put("STATE", BusinessConsts.UNDONE);
		model.put("REMARK", map.get("REMARK"));
		model.put("RECV_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		model.putAll(sysFild(userBean));//系统字段
		List<Map<String,String>> chld=new ArrayList<Map<String,String>>();
		for(int i=0;i<chldList.size();i++){
			Map<String,String> param=(Map<String, String>) chldList.get(i);
			Map<String,String> maps=new HashMap<String,String>();
			maps.putAll(param);
			String STOREOUT_DTL_ID=StringUtil.uuid32len();
			maps.put("STOREOUT_DTL_ID", STOREOUT_DTL_ID);
			maps.put("STOREOUT_ID", STOREOUT_ID);
			Object NOTICE_NUM= param.get("ALLOC_NUM");
			maps.put("NOTICE_NUM",NOTICE_NUM.toString());
			maps.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			maps.put("FROM_BILL_DTL_ID",param.get("ALLOCATE_DTL_ID"));
			chld.add(maps);
		}
		storeoutService.txAddStoreOut(model,chld);
		return STOREOUT_ID;
	}
	/**
	 * 分发指令接收生成出库单
	 * @param BussId 分发指令接收id
	 * @param userBean 登录信息
	 * @return
	 */
	private static String instDstrStoreout(String BussId,UserBean userBean,Map<String,String> locaInfo){
		//分发指令接收service
		DstrorderService dstrorderService = (DstrorderService) SpringContextUtil.getBean("dstrorderService");
		StoreoutService storeoutService = (StoreoutService) SpringContextUtil.getBean("storeoutService");
		//按分发指令接收主表id获取子表明细信息
		List <DstrorderModelChld> chldList=dstrorderService.childQuery(BussId);
		//按分发指令接收id获取分发指令接收信息
		Map<String,String> map=dstrorderService.load(BussId);
		//创建出库单map
		Map<String,Object> model=new HashMap<String,Object>();
		String STOREOUT_ID=StringUtil.uuid32len();
		model.put("STOREOUT_ID", STOREOUT_ID);//出库单ID
		model.put("STOREOUT_NO", LogicUtil.getBmgz("DRP_STOREOUT_NO"));//出库单编号
		model.put("BILL_TYPE","分发出库");//单据类型
		model.putAll(map);
		model.put("FROM_BILL_ID", map.get("DSTR_ORDER_ID"));//来源单据Id
		model.put("FROM_BILL_NO", map.get("DSTR_ORDER_NO"));//来源单据编号
		model.put("CUST_NAME",map.get("PERSON_CON"));
		model.put("DEAL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		model.put("RECV_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		model.put("STATE",BusinessConsts.UNDONE);
		model.putAll(locaInfo);
		model.putAll(sysFild(userBean));//系统字段
		//modify by zzb 2014-4-28  0151656
		model.put("LEDGER_ID", map.get("LEDGER_ID"));
		model.put("LEDGER_NAME", map.get("LEDGER_NAME"));
		//end
		List<Map<String,String>> chld=new ArrayList<Map<String,String>>();
		for(int i=0;i<chldList.size();i++){
			Map<String,String> param=(Map<String, String>) chldList.get(i);
			Map<String,String> maps=new HashMap<String,String>();
			maps.putAll(param);
			String STOREOUT_DTL_ID=StringUtil.uuid32len();
			maps.put("STOREOUT_DTL_ID", STOREOUT_DTL_ID);
			maps.put("STOREOUT_ID", STOREOUT_ID);
			maps.put("FROM_BILL_DTL_ID", param.get("DSTR_ORDER_DTL_ID"));
			Object DSTR_NUM=param.get("DSTR_NUM");
			if(DSTR_NUM==null){
				DSTR_NUM="0";
			}
			maps.put("NOTICE_NUM", DSTR_NUM.toString());
			chld.add(maps);
		}
		storeoutService.txAddStoreOut(model,chld);
		Map<String,String> params=new HashMap<String,String>();
		params.put("DSTR_ORDER_ID", BussId);
        params.put("STATE",BusinessConsts.RECEIVED);
		params.put("RECV_PSON_ID",userBean.getRYXXID());
		params.put("RECV_PSON_NAME",userBean.getXM());
		dstrorderService.txReceivedById(params);
		return STOREOUT_ID;
	}
	/**
	 * 该方法传入UserBean后把制单信息和帐套信息和删除标记封装成map返回
	 * @param userBean
	 * @return
	 */
	public static Map<String,String> sysFild(UserBean userBean){
		Map<String,String> map=new HashMap<String,String>();
		map.put("CRE_NAME",userBean.getXM());//制单人名称
		map.put("CREATOR",userBean.getXTYHID());//制单人ID
		map.put("DEPT_ID",userBean.getBMXXID());//制单部门ID
		map.put("DEPT_NAME",userBean.getBMMC());//制单部门名称
		map.put("ORG_ID",userBean.getJGXXID());//制单机构ID
		map.put("ORG_NAME",userBean.getJGMC());//制单机构名称
		map.put("CRE_TIME","sysdate");//制单时间
		map.put("LEDGER_ID",userBean.getLoginZTXXID());//帐套ID
		map.put("LEDGER_NAME",userBean.getLoginZTMC());//帐套名称
		map.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//删除标记
		return map;
	}
	/**
	 * 预订单发货申请转出库单
	 * @param BussId 预订单发货申请id
	 * @param userBean 登录信息
	 * @param locaInfo 
	 * @return
	 */
	private static String instSaleSotreout(String BussId,UserBean userBean){
		//创建预订单发货申请Service
		AdvcgoodsappService advcgoodsappService = (AdvcgoodsappService) SpringContextUtil.getBean("advcgoodsappService");
		StoreoutService storeoutService = (StoreoutService) SpringContextUtil.getBean("storeoutService");
		//按预订单发货申请明细id获取子表明细信息
		List <AdvcgoodsappModelChld> chldList=advcgoodsappService.childQuery(BussId);
		//按预订单发货申请id获取预订单发货申请信息
		Map<String,String> map=advcgoodsappService.load(BussId);
		//创建出库单map
		Map<String,Object> model=new HashMap<String,Object>();
		String STOREOUT_ID=StringUtil.uuid32len();
		String STOREOUT_NO=LogicUtil.getBmgz("DRP_STOREOUT_NO");
		model.put("STOREOUT_ID", STOREOUT_ID);//出库单ID
		model.put("STOREOUT_NO", STOREOUT_NO);//出库单编号
		model.put("BILL_TYPE", "销售出库");//单据类型
		model.put("CONTRACT_NO", map.get("CONTRACT_NO"));//合同编号
		model.put("FROM_BILL_ID", map.get("ADVC_SEND_REQ_ID"));//来源单据id
		model.put("FROM_BILL_NO", map.get("ADVC_SEND_REQ_NO"));//来源单据编号
		model.put("SEND_CHANN_ID", map.get("SEND_CHANN_ID"));//发货方ID
		model.put("SEND_CHANN_NO", map.get("SEND_CHANN_NO"));//发货方编号
		model.put("SEND_CHANN_NAME", map.get("SEND_CHANN_NAME"));//发货方名称
		model.put("STOREOUT_STORE_ID", map.get("STOREOUT_STORE_ID"));//出库库房id
		model.put("STOREOUT_STORE_NO", map.get("STOREOUT_STORE_NO"));//出库库房编号
		model.put("DEAL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		model.put("STOREOUT_STORE_NAME", map.get("STOREOUT_STORE_NAME"));//出库库房名称
//		Object STORAGE_FLAG=map.get("STORAGE_FLAG");
//		model.put("STORAGE_FLAG", STORAGE_FLAG.toString());//库位管理标记
		model.put("REMARK", map.get("REMARK"));
		model.put("TERM_ID", map.get("TERM_ID"));//终端信息id
		model.put("TERM_NO", map.get("TERM_NO"));//终端编号
		model.put("TERM_NAME", map.get("TERM_NAME"));//终端名称
		model.put("SALE_DATE", map.get("SALE_DATE"));//销售日期
		model.put("SALE_PSON_ID", map.get("SALE_PSON_ID"));//业务员id
		model.put("SALE_PSON_NAME", map.get("SALE_PSON_NAME"));//业务员名称
		model.put("CUST_NAME", map.get("CUST_NAME"));//客户名称
		model.put("TEL", map.get("TEL"));//电话
		model.put("RECV_ADDR", map.get("RECV_ADDR"));//收货地址
		model.put("ORDER_RECV_DATE", map.get("ORDER_RECV_DATE"));//要求到货日期
		model.put("ADVC_AMOUNT", map.get("ADVC_AMOUNT"));//订金金额
		model.put("RECV_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		model.put("STOREOUT_AMOUNT", map.get("STOREOUT_AMOUNT"));//订货总金额
		model.put("PAYABLE_AMOUNT", map.get("PAYABLE_AMOUNT"));//应收总额
		model.put("PAYED_TOTAL_AMOUNT", map.get("PAYED_TOTAL_AMOUNT"));//已付款金额
		model.put("STATE", BusinessConsts.UNDONE);//状态
		model.put("CRE_NAME",userBean.getXM());//制单人名称
		model.put("CREATOR",userBean.getXTYHID());//制单人ID
		model.put("DEPT_ID",userBean.getBMXXID());//制单部门ID
		model.put("DEPT_NAME",userBean.getBMMC());//制单部门名称
		model.put("ORG_ID",userBean.getJGXXID());//制单机构ID
		model.put("ORG_NAME",userBean.getJGMC());//制单机构名称
		model.put("CRE_TIME","sysdate");//制单时间
		model.put("LEDGER_ID", map.get("SEND_CHANN_ID"));//帐套ID
		model.put("LEDGER_NAME",map.get("SEND_CHANN_NAME"));//帐套名称
		model.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//删除标记
		List<Map<String,String>> chld=new ArrayList<Map<String,String>>();
		for (int j=0;j<chldList.size();j++) {
			Map<String,String> chldMap=(Map<String, String>) chldList.get(j);
			
			//modify by zzb 2015-01-26 是否扫码标记 1->否->不拆。 0->是->拆
			int IS_SCAN = StringUtil.getInteger(chldMap.get("IS_SCAN"));
			Integer NOTICE_NUM_INT = StringUtil.getInteger(chldMap.get("NOTICE_NUM"))*100;
			Double NOTICE_NUM_DOU = StringUtil.getDouble(chldMap.get("NOTICE_NUM"))*100;
			
			if(1 != IS_SCAN && NOTICE_NUM_INT<NOTICE_NUM_DOU){
				throw new ServiceException("货品["+chldMap.get("PRD_NO")+"]["+chldMap.get("PRD_NAME")+"] 的货品</br>只能整订整出</br>" +
						"通知出库数量有小数，请联系管理员修改货品属性");
			} 
			String NOTICE_NUM = StringUtil.nullToSring(chldMap.get("NOTICE_NUM"));
			String DECT_AMOUNT = StringUtil.nullToSring(chldMap.get("DECT_AMOUNT"));
			int loopNum = 1;
			//不拆的话 一个货品明细就是整订整出
			if(1 == IS_SCAN){
				loopNum = 1;
			}else{
				loopNum = StringUtil.getInteger(chldMap.get("NOTICE_NUM"));
				NOTICE_NUM = "1";//拆的话 一个货品明细就是整订一条一条的出库
				DECT_AMOUNT  = StringUtil.nullToSring(chldMap.get("DECT_PRICE"));
			}
			Object obj;
			 for (int i = 0; i < loopNum; i++) {
	    		Map<String,String> params=new HashMap<String,String>();
	    		String STOREOUT_DTL_ID=StringUtil.uuid32len();
	            params.put("STOREOUT_DTL_ID", STOREOUT_DTL_ID);
	            params.put("STOREOUT_ID", STOREOUT_ID);
	            params.put("PRD_ID", chldMap.get("PRD_ID"));
	            params.put("PRD_NO", chldMap.get("PRD_NO"));
	            params.put("PRD_NAME", chldMap.get("PRD_NAME"));
	            params.put("PRD_SPEC", chldMap.get("PRD_SPEC"));
	            params.put("PRD_COLOR", chldMap.get("PRD_COLOR"));
	            params.put("BRAND", chldMap.get("BRAND"));
	            params.put("STD_UNIT", chldMap.get("STD_UNIT"));
	            params.put("REMARK", chldMap.get("REMARK"));
			    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			    params.put("SPCL_TECH_ID", chldMap.get("SPCL_TECH_ID"));
			    params.put("PRD_TYPE", chldMap.get("PRD_TYPE"));
			    params.put("FROM_BILL_DTL_ID", chldMap.get("ADVC_SEND_REQ_DTL_ID"));
			    
			    params.put("NOTICE_NUM",NOTICE_NUM);
			     // modify by jack 2014-6-18 添加冻结数量
			    params.put("FREEZE_NUM",NOTICE_NUM);
			    // modify by jack 2014-6-18 添加冻结数量
			    obj=chldMap.get("PRICE");
			    params.put("PRICE", obj.toString());
			    obj=chldMap.get("DECT_RATE");
			    params.put("DECT_RATE", obj.toString());
			    obj = chldMap.get("DECT_PRICE");
			    params.put("DECT_PRICE", obj.toString());
			    params.put("DECT_AMOUNT", DECT_AMOUNT);
	            chld.add(params);
			}
		}
		storeoutService.txAddStoreOut(model,chld);
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
		maps.put("ADVC_ORDER_ID", map.get("FROM_BILL_ID"));
		maps.put("ACTION", "销售出库处理");
		maps.put("REMARK", "已生成");
		maps.put("ACTOR", userBean.getXM());
		maps.put("BILL_NO", STOREOUT_NO);
        advcgoodsappService.insertTrace(maps);
		return STOREOUT_ID;
	}
	

	/**
	 * 返修单产生返修出库单
	 * @param BussId
	 * @param userBean
	 * @return
	 */
	private static String instRepairSotreout(String BussId,UserBean userBean){
		RepairappService repairappService = (RepairappService) SpringContextUtil.getBean("repairappService");
		StoreoutService storeoutService = (StoreoutService) SpringContextUtil.getBean("storeoutService");		
		Map<String,Object> repairMap=repairappService.loadMap(BussId);
		Map<String,Object> storeOutMap =new HashMap<String,Object>();
		String STOREOUT_ID=StringUtil.uuid32len();
		storeOutMap.put("STOREOUT_ID", STOREOUT_ID);
		storeOutMap.put("STOREOUT_NO", LogicUtil.getBmgz("DRP_STOREOUT_NO"));//出库单编号
		storeOutMap.put("BILL_TYPE", "返修出库");//单据类型
		storeOutMap.put("FROM_BILL_ID", repairMap.get("ERP_REPAIR_ORDER_ID"));//来源单据id
		storeOutMap.put("FROM_BILL_NO", repairMap.get("ERP_REPAIR_ORDER_NO"));//来源单据编号
		storeOutMap.put("SEND_CHANN_ID", repairMap.get("REPAIR_CHANN_ID"));//发货方ID
		storeOutMap.put("SEND_CHANN_NO", repairMap.get("REPAIR_CHANN_NO"));//发货方编号
		storeOutMap.put("SEND_CHANN_NAME", repairMap.get("REPAIR_CHANN_NAME"));//发货方名称
		storeOutMap.put("STOREOUT_STORE_ID", repairMap.get("STOREOUT_STORE_ID"));//出库库房id
		storeOutMap.put("STOREOUT_STORE_NO", repairMap.get("STOREOUT_STORE_NO"));//出库库房编号
		storeOutMap.put("STOREOUT_STORE_NAME", repairMap.get("STOREOUT_STORE_NAME"));//出库库房名称
		storeOutMap.put("STORAGE_FLAG", repairMap.get("STORAGE_FLAG"));//出库库房名称
		storeOutMap.put("DEAL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		storeOutMap.put("RECV_CHANN_ID",userBean.getBASE_CHANN_ID() );
		storeOutMap.put("RECV_CHANN_NO",userBean.getBASE_CHANN_NO() );
		storeOutMap.put("RECV_CHANN_NAME",userBean.getBASE_CHANN_NAME());
		storeOutMap.put("STATE","未处理");//状态
		storeOutMap.put("STORAGE_FLAG","0");//状态
		storeOutMap.put("REMARK", repairMap.get("REMARK"));//备注
		storeOutMap.putAll(sysFild(userBean));//系统字段
		List<Map<String,String>> chld = new ArrayList<Map<String,String>>();
		List <RepairappModelChld> repairchldList = repairappService.childQuery(BussId);
		for (int i=0;i<repairchldList.size();i++) {
			RepairappModelChld chldMap = repairchldList.get(i);
    		Map<String,String> params = new HashMap<String,String>();
    		String STOREOUT_DTL_ID = StringUtil.uuid32len();
            params.put("STOREOUT_DTL_ID", STOREOUT_DTL_ID);
            params.put("STOREOUT_ID", STOREOUT_ID);
            params.put("PRD_ID", chldMap.getPRD_ID());
            params.put("PRD_NO", chldMap.getPRD_NO());
            params.put("PRD_NAME", chldMap.getPRD_NAME());
            params.put("PRD_SPEC", chldMap.getPRD_SPEC());
            params.put("PRD_COLOR", chldMap.getPRD_COLOR());
            params.put("BRAND", chldMap.getBRAND());
            params.put("STD_UNIT", chldMap.getSTD_UNIT());
            params.put("REMARK", chldMap.getREMARK());
            params.put("SPCL_TECH_ID", chldMap.getSPCL_TECH_ID());
            params.put("NOTICE_NUM", chldMap.getREPAIR_NUM());
		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		    params.put("FROM_BILL_DTL_ID", chldMap.getREPAIR_ORDER_DTL_ID());
		    chld.add(params);
		}
		storeoutService.txAddStoreOut(storeOutMap,chld);
		return STOREOUT_ID;
	}
	
	/**
	 * 返修单产生发运单  
	 * by huangru
	 * @param BussId
	 * @param userBean
	 * @return
	 */
	public static String instRepairSend(Map<String, String> param,UserBean userBean){
		RepairappService repairappService = (RepairappService) SpringContextUtil.getBean("repairappService");
		TurnoverplanService turnoverplanService = (TurnoverplanService) SpringContextUtil.getBean("turnoverplanService");		
		//查询 返修单据
		Map<String,String> repairMap = repairappService.load(param.get("ERP_REPAIR_ORDER_ID"));
		Map<String,Object> model = new HashMap<String,Object>();
		
		String DELIVER_ORDER_ID = StringUtil.uuid32len();
    	String DELIVER_ORDER_NO = LogicUtil.getBmgz("ERP_DELIVER_ORDER_NO");
    	String DELIVER_TYPE = param.get("DELIVER_TYPE");
    	model.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
    	model.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
    	model.put("BILL_TYPE", "返修发运");//来源于返修单
		model.put("DELIVER_TYPE",DELIVER_TYPE);//发运方式
		model.put("CHANN_TYPE",param.get("CHANN_TYPE"));//发货类型
		model.put("SHIP_POINT_ID",repairMap.get("SHIP_POINT_ID"));
		model.put("SHIP_POINT_NAME",repairMap.get("SHIP_POINT_NAME"));
		
		//整车发运
	   	if(BusinessConsts.DELIVER_TYPE.equals(DELIVER_TYPE)){
    		//选择了 ‘区域代发’ 就是 区域服务中心 发货 
	   		if("区域代发".equals(param.get("CHANN_TYPE"))){
	   			model.put("SEND_CHANN_ID",repairMap.get("AREA_SER_ID"));//返修方所属区域服务中心ID  (如果CHANN_TYPE是区域代发，关联从渠道表查区域服务中心)
				model.put("SEND_CHANN_NO",repairMap.get("AREA_SER_NO"));//返修方所属区域服务中心NO
				model.put("SEND_CHANN_NAME",repairMap.get("AREA_SER_NAME"));//返修方所属区域服务中心NAME
        		model.put("CHANN_TYPE","区域代发");
        	}else{
        		model.put("SEND_CHANN_ID",userBean.getBASE_CHANN_ID());
        		model.put("SEND_CHANN_NO",userBean.getBASE_CHANN_NO());
        		model.put("SEND_CHANN_NAME",userBean.getBASE_CHANN_NAME());
        		model.put("CHANN_TYPE","基地发货");
        	}
    	}else{
    		model.put("SEND_CHANN_ID",userBean.getBASE_CHANN_ID());
    		model.put("SEND_CHANN_NO",userBean.getBASE_CHANN_NO());
    		model.put("SEND_CHANN_NAME",userBean.getBASE_CHANN_NAME());
    		model.put("CHANN_TYPE","基地发货");
    	}
 
	   	model.put("AREA_SER_ID",repairMap.get("AREA_SER_ID"));//返修方所属区域服务中心ID  (如果CHANN_TYPE是区域代发，关联从渠道表查区域服务中心)
		model.put("AREA_SER_NO",repairMap.get("AREA_SER_NO"));//返修方所属区域服务中心NO
		model.put("AREA_SER_NAME",repairMap.get("AREA_SER_NAME"));//返修方所属区域服务中心NAME
		model.put("STATE",BusinessConsts.UNCOMMIT);
		model.put("REMARK",repairMap.get("REMARK"));//返修单备注
		model.put("CRE_NAME",userBean.getXM());
		model.put("CREATOR",userBean.getXTYHID());
		model.put("DEPT_ID",userBean.getBMXXID());
		model.put("DEPT_NAME",userBean.getBMMC());
		model.put("ORG_ID",userBean.getJGXXID());
		model.put("ORG_NAME",userBean.getJGMC());
		model.put("LEDGER_ID",userBean.getLoginZTXXID());
		model.put("LEDGER_NAME",userBean.getLoginZTMC());
		model.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		
		model.put("RECV_CHANN_ID", repairMap.get("REPAIR_CHANN_ID"));//收货方ID
		model.put("RECV_CHANN_NO", repairMap.get("REPAIR_CHANN_NO"));//收货方编号
		model.put("RECV_CHANN_NAME", repairMap.get("REPAIR_CHANN_NAME"));//收货方名称
		model.put("RECV_ADDR_NO", repairMap.get("DELIVER_ADDR_NO"));//收货地址编号
		//add by zzb 2014-7-29
		model.put("ORDER_CHANN_ID",repairMap.get("REPAIR_CHANN_ID"));//返修货方ID
		model.put("ORDER_CHANN_NO",repairMap.get("REPAIR_CHANN_NO"));
		model.put("ORDER_CHANN_NAME",repairMap.get("REPAIR_CHANN_NAME"));
        
		List <RepairappModelChld> repairchldList = repairappService.childQuery(param.get("ERP_REPAIR_ORDER_ID"));
		List<Map<String,String>> childList = new ArrayList<Map<String,String>>();
		Double TOTAL_VOLUME_P = 0.0;
		for (int i=0;i<repairchldList.size();i++) {
			 
			RepairappModelChld chldMap = repairchldList.get(i);
    		Map<String,String> child = new HashMap<String,String>();
    	 
    		child.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
    		child.put("DELIVER_ORDER_DTL_ID", StringUtil.uuid32len());
    		child.put("SALE_ORDER_ID", repairMap.get("ERP_REPAIR_ORDER_ID"));
    		child.put("SALE_ORDER_NO", repairMap.get("ERP_REPAIR_ORDER_NO"));
    		child.put("SALE_ORDER_DTL_ID", chldMap.getREPAIR_ORDER_DTL_ID());
    		child.put("U_CODE",DELIVER_ORDER_NO);
    		child.put("ORDER_CHANN_ID",repairMap.get("REPAIR_CHANN_ID"));//返修货方ID
            child.put("ORDER_CHANN_NO",repairMap.get("REPAIR_CHANN_NO"));
            child.put("ORDER_CHANN_NAME",repairMap.get("REPAIR_CHANN_NAME"));
            child.put("RECV_CHANN_ID",repairMap.get("REPAIR_CHANN_ID"));//返修货方ID
            child.put("RECV_CHANN_NO",repairMap.get("REPAIR_CHANN_NO"));
            child.put("RECV_CHANN_NAME",repairMap.get("REPAIR_CHANN_NAME"));
            // modify by zzb 2014-6-18 添加收货地址
            child.put("RECV_ADDR_NO", repairMap.get("DELIVER_ADDR_NO"));//收货地址编号
            child.put("RECV_ADDR",repairMap.get("DELIVER_DTL_ADDR"));// 收货地址 
            
            child.put("PRD_ID",chldMap.getPRD_ID());
            child.put("PRD_NO",chldMap.getPRD_NO());
            child.put("PRD_NAME",chldMap.getPRD_NAME());
            child.put("PRD_SPEC",chldMap.getPRD_SPEC());
            child.put("PRD_COLOR",chldMap.getPRD_COLOR());
            child.put("STD_UNIT",chldMap.getSTD_UNIT());
            //add by zzb 2014-8-6 添加体积
            String VOLUME_ = StringUtil.nullToSring(chldMap.getVOLUME());
            String TOTAL_VOLUME_ = StringUtil.nullToSring(chldMap.getTOTAL_VOLUME());
            Double VOLUME = StringUtil.getDouble(VOLUME_);
            Double TOTAL_VOLUME = StringUtil.getDouble(TOTAL_VOLUME_);
            TOTAL_VOLUME_P = TOTAL_VOLUME_P + TOTAL_VOLUME;
            child.put("VOLUME",VOLUME.toString());
            
            //modify  zzb 2014-6-18  用返修金额除以数量插入 发运单明细的折后单价
            Double REPAIR_AMOUNT = StringUtil.getDouble(chldMap.getREPAIR_AMOUNT());
            Integer REPAIR_NUM = StringUtil.getInteger(chldMap.getREPAIR_NUM());
            double DECT_PRICE = REPAIR_AMOUNT/REPAIR_NUM;
            DECT_PRICE =  (double)Math.round(DECT_PRICE*100)/100;
            child.put("DECT_PRICE",String.valueOf(DECT_PRICE));//返修单价
            
            child.put("CREDIT_FREEZE_PRICE","0");//信用冻结单价
            child.put("BRAND",chldMap.getBRAND());
            child.put("ADVC_PLAN_NUM",chldMap.getREPAIR_NUM()); //预排车=返修数量 
            child.put("PLAN_NUM",chldMap.getREPAIR_NUM()); //排车默认=返修数量 
            child.put("SPCL_TECH_ID",chldMap.getSPCL_TECH_ID());
            child.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
            
		    childList.add(child);
		}
		model.put("TOTAL_VOLUME", TOTAL_VOLUME_P);
		turnoverplanService.txEditFY(model, childList, userBean);
		return "";
	}
	
	

	

	
    /**判断库存，冻结库存
     * @param advcOrderId
     * @param freezeNum
     * @return
     * @throws Exception
     */
    public static MsgInfo handStoreAcct(String advcOrderId,String freezeNum,String advcConfigId) throws Exception{
    	String checkStoreAcctJson = doChkCanUseStoreNum(advcConfigId, advcOrderId, freezeNum);
    	MsgInfo model = new Gson().fromJson(checkStoreAcctJson, new TypeToken <MsgInfo>() {
        }.getType());
    	if(model.isFLAG()){
    		doStoreFreeze(advcConfigId, advcOrderId);
    	}
    	return model;
    }
    
    /**判断库存够不够
     * @param advcOrderId
     * @param freezeNum
     * @return
     * @throws Exception
     */
    public static MsgInfo checkStoreAcct(String advcOrderId,String freezeNum,String advcConfigId) throws Exception{
    	String checkStoreAcctJson = doChkCanUseStoreNum(advcConfigId, advcOrderId, freezeNum);
    	MsgInfo model = new Gson().fromJson(checkStoreAcctJson, new TypeToken <MsgInfo>() {
        }.getType());
    	return model;
    }
	
	/**
	 * 记库存实时账
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public static boolean doStoreAcct (String bussID,String keyValue)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txDoStoreAcct(bussID, keyValue);
	}
	
	/**
	 * 记形态转换库存实时账（不需要转换冻结数量）
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public static boolean doStateChangeStoreAcct (String bussID,String keyValue)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txStateChangeStoreAcct(bussID, keyValue);
	}
	
	/**
	 * 记库存冻结
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public static boolean doStoreFreeze (String bussID,String keyValue)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txDoStoreFreeze(bussID, keyValue);
	}

	/**********************************库存流水账***************************/
	/**
	 * 记库存流水账
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public static boolean doJournalAcct (String bussID,String keyValue)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txDoJournalAcct(bussID, keyValue);
	}
	/**
	 * 记库存流水账（带特殊工艺管控）
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public static boolean doJournalAcct (String bussID,String keyValue,String CHANN_ID)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txDoJournalAcct(bussID, keyValue,CHANN_ID);
	}
	
	/**
	 *删流水账
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public static boolean delJournalAcct (String keyValue,String BillType)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txDelJournalAcct(keyValue,BillType);
	}
	
	/**
	 *返入库时删库存流水帐
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public static boolean delStoreInJournalAcct (String keyValue,String BillType)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txDelStoreInJournalAcct(keyValue,BillType);
	}
	
	
	/**检查是否存在流水
	 * @param keyValue
	 * @param BillType
	 * @return
	 * @throws Exception
	 */
	public static boolean checkJournalAcct(String keyValue,String BillType)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.checkJournalAcct(keyValue,BillType);
	}
	
	/**
	 * 记库存流水账
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public static boolean doInitInventoryJournalAcct (String PRD_INV_ID,String STORE_NO,String CHANN_ID)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txInitInventoryJournalAcct(PRD_INV_ID,STORE_NO,CHANN_ID);
	}
	
	/**********************************库存流水账***************************/
	/**
	 * 判断可用库存是否够用的判断
	 * @param bussID
	 * @param keyValue
	 * @param chkType(库存数量,冻结数量)
	 * @return
	 */
	public static String doChkCanUseStoreNum(String bussID,String keyValue,String chkType){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txDoChkCanUseStoreNum(bussID, keyValue, chkType);
	}
	
	public static String doChkStoreNumByStoreOut(String STOREOUT_ID){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txDoChkStoreNumByStoreOut(STOREOUT_ID);
	}
	
	
	/**判断信用是否符合要求
	 * @param bussID业务类型，是用来获得配置信息用的。
	 * @param keyValue 记账业务的主键值,是用来获得记账所需要的参数值。
	 * @param chkType
	 * @param busStats   业务状态
	 * @return
	 */
	public static boolean chkCanUseCreditForCommit(String bussID,String keyValue)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txChkCanUseCredit(bussID, keyValue);
	}
	
	/**判断信用是否符合要求，跟据明细来做信用CHECK ，（目前主要是发运单的CHECK）
	 * @param bussID业务类型，是用来获得配置信息用的。
	 * @param deliverOrderId 记账业务的主键值,是用来获得记账所需要的参数值。
	 * @param chkType
	 * @param busStats   业务状态
	 * @return
	 */
	public static String chkCanUseCreditForSend(String bussID,String deliverOrderId)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.chkCanUseCreditForSend(bussID, deliverOrderId);
	}
	
	/**更新付款凭证信用
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public static boolean updPaymentCredit(String bussID,String keyValue)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txUpdPaymentCredit(bussID, keyValue);
	}
	
	
	/**更新冻结信用
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public static boolean updFreezeCredit(String bussID,String keyValue)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txUpdFreezeCredit(bussID, keyValue);
	}
	
	/**更新冻结信用-跟据明细冻结
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public static boolean updFreezeCredit(String bussID,String mainKey,String dtlKeys)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txUpdFreezeCredit(bussID, mainKey,dtlKeys);
	}
	
	/**更新冻结信用-跟据明细冻结
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public static boolean updFreezeCreditByCanleOrder(String bussID,String mainKey,String dtlKeys,String newOrderNum)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txUpdFreezeCreditByCanleOrder(bussID, mainKey,dtlKeys,newOrderNum);
	}
	
	/**更新冻结信用,跟据明细冻结，交付计划维护时撤销
	 * @param bussID
	 * @param mainKey
	 * @param dtlKeys
	 * @return
	 * @throws Exception
	 */
	public static boolean updFreezeCreditByDeliverOrder(String mainKey,String dtlKeys)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txUpdFreezeCreditByDeliverOrder(mainKey,dtlKeys);
		
	}
	
	/**发运单 还冻结信用,扣减余额
	 * @param DELIVER_ORDER_DTL_ID
	 * @return
	 * @throws Exception
	 */
	public static boolean updatePdtDeliverFree(String DELIVER_ORDER_DTL_ID,String DELIVER_ORDER_ID)throws Exception{
//		还冻结信用,扣减余额：
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		boolean flg = logicUtilServiceImpl.updatePdtDeliverFree(DELIVER_ORDER_DTL_ID,DELIVER_ORDER_ID);
		if(flg){
			logicUtilServiceImpl.inertPdtDeliverCreditJournal("货品发运",DELIVER_ORDER_DTL_ID,"1"); //记流水账
//			 扣减过的冻结单价清0
			return logicUtilServiceImpl.clearPdtDeliverFreePrice(DELIVER_ORDER_DTL_ID);
		}
		return false;
		 
		

		
	}
	
	/**发运确认 按新行ID，差异数量更新当前信用和冻结结信用
	 * @param DELIVER_ORDER_DTL_ID
	 * @return
	 * @throws Exception
	 */
	public static boolean updateDeliverCofimCredit(String billType,String NewsDELIVER_ORDER_DTL_ID,String deliverOrderId)throws Exception{
//		还冻结信用,扣减余额：
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
//		按新产生的发运单冻结的信用 (请注意 是差异数量)
		boolean flg = logicUtilServiceImpl.updateDeliverConfigFree(NewsDELIVER_ORDER_DTL_ID,deliverOrderId);
//		更新产生生的货品的冻结单价
		logicUtilServiceImpl.updateDeliverConfigFreePrice(NewsDELIVER_ORDER_DTL_ID);
//		按新产生的发运单回当前信用 (请注意 是差异数量)
		flg = logicUtilServiceImpl.updateDeliverConfigCurrtCredit(NewsDELIVER_ORDER_DTL_ID,deliverOrderId);
//		 记发运单流水账
		return logicUtilServiceImpl.inertPdtDeliverCreditJournal(billType,NewsDELIVER_ORDER_DTL_ID,"0");
		
	}
	
	public static boolean inertSaleCancelCreditJournal(String erpSaleOrderDtlIds,String DIRECTION,String ORDER_NUM){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		 return logicUtilServiceImpl.inertSaleCancelCreditJournal(erpSaleOrderDtlIds,DIRECTION,ORDER_NUM);
	}
	
	
	/**更新信用释放冻结信用
	 * @param bussID
	 * @param keyValue
	 * @param isDtl 是否跟据明细更新
	 * @return
	 * @throws Exception
	 */
	public static boolean updCanUseCredit(String bussID,String keyValue,String dtlKey,boolean isDtl)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		if(isDtl){
			return logicUtilServiceImpl.txUpdCanUseCredit(bussID,keyValue,dtlKey);
		}else{
			return logicUtilServiceImpl.txUpdCanUseCredit(bussID, keyValue);
		}
		
	}
	
	
	/**记流水账
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public static boolean inertCreditJournal(String bussID,String keyValue) throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.inertCreditJournal(bussID,keyValue);
	}
	
	public static boolean inertOrderCreditJournal(String bussID,String keyValue) throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.inertOrderCreditJournal(bussID,keyValue);
	}
	
	
	
	/**判断返利是否符合要求
	 * @param bussID
	 * @param keyValue
	 * @param chkType
	 * @return
	 * @throws Exception
	 */
	public static boolean chkCanUseRebate(String bussID,String keyValue)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txChkCanUseRebate(bussID,keyValue);		
	}
	
	/**判断总的信用够不够
	 * @param bussID
	 * @param keyValue
	 * @param chkType
	 * @return
	 * @throws Exception
	 */
	public static boolean chkAllCanUseRebate(String bussID,String keyValue)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txChkAllCanUseCredit(bussID,keyValue);		
	}
	
	/**返利变更
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public static boolean rebateChange(String keyValue,int isCancle)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txRebateChange(keyValue,isCancle);
	}
	
	public static boolean cancelRebateChange(String keyValue,int isCancle)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.txRebateChange(keyValue,isCancle);
	}
	
	
	/**更新返利
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public static boolean updateRebate(String bussID,String keyValue)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		logicUtilServiceImpl.txUpdateRebate(bussID,keyValue);
		return false;
	}
	
	/**更新返利,跟据明细更新
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public static boolean updateRebate(String bussID,String keyValue,String dtlKeys)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		boolean flg =  logicUtilServiceImpl.txUpdateRebate(bussID,keyValue,dtlKeys);
		return flg;
	}
	
	/**更新返利,跟据明细更新按数量
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public static boolean updateRebateByNum(String bussID,String keyValue,String dtlKeys,String CANCEL_NUM)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		boolean flg =  logicUtilServiceImpl.txUpdateRebateByNum(bussID,keyValue,dtlKeys,CANCEL_NUM);
		return flg;
	}
	

	
	public static boolean insRebateJournal(String bussID,String keyValue)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.insRebateJournal(bussID,keyValue);
	}
	
	public static boolean insRebateJournal(String bussID,String keyValue,String dtlKeys)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.insRebateJournal(bussID,keyValue, dtlKeys);
	}

	public static boolean insRebateJournal(String bussID,String keyValue,String dtlKeys,String ORDER_NUM)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.insRebateJournal(bussID,keyValue, dtlKeys,ORDER_NUM);
	}
	
	public static boolean insRebateRegistJournal(String BUSS_TYPE,String LEDGER_ID,String PRD_AMOUNT,String CHANN_IDS,String DIRECTION)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.insRebateRegistJournal( BUSS_TYPE, LEDGER_ID, PRD_AMOUNT, CHANN_IDS, DIRECTION);
	}

//	/**U9接口,导入收款单
//	 * @return
//	 */
//	public static boolean doRecivePayment(String channId,String paymentPrice,String date,String remark)throws Exception{
//		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
////		if(!checkEsbUser(userName,password)){
////			return logicUtilServiceImpl.returnCheckUserFail();
////		}
//		return logicUtilServiceImpl.txDoRecivePayment(channId,paymentPrice,date,remark);
//	}

	/**新增账套信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String createLedger(String userName,String passWord,String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result = logicUtilServiceImpl.txCreateLedger(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0100070","createLedger","E0000001",APPID,result);
	}
	
	
	/**修改账套信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String updateLedger(String userName,String passWord,String jsonData){
		InterReturnMsg result = null;
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		if(!checkEsbUser(userName,passWord)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result = logicUtilServiceImpl.txUpdateLedger(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0100090","updateLedger","E0000002",APPID,result);
	}
	
	/**删除账套信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String deleteLedger(String userName,String passWord,String jsonData){
		InterReturnMsg result = null;
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		if(!checkEsbUser(userName,passWord)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txDeleteLedger(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TD0100070","deleteLedger","E0000003",APPID,result); 
	}	

	

	/**新增机构信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String createOrganization(String userName,String passWord,String jsonData){
		InterReturnMsg result = null;
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		if(!checkEsbUser(userName,passWord)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result = logicUtilServiceImpl.txCreateOrganization(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0100080","createOrganization","E0000004",APPID,result);  
	}
	
	
	/**修改机构信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String updateOrganization(String userName,String passWord,String jsonData){
		InterReturnMsg result = null;
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		if(!checkEsbUser(userName,passWord)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result = logicUtilServiceImpl.txUpdateOrganization(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0100100","updateOrganization","E0000005",APPID,result);  
	}
	
	/**删除机构信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String deleteOrganization(String userName,String passWord,String jsonData){
		InterReturnMsg result = null;
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		if(!checkEsbUser(userName,passWord)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result = logicUtilServiceImpl.txDeleteOrganization(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TD0100080","deleteOrganization","E0000006",APPID,result);  
	}
	
	/**新增部门信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String createDepartMent(String userName,String passWord,String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txCreateDepartMent(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0100050","createDepartMent","E0000007",APPID,result);  
	}
	
	
	/**修改部门信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String updateDepartMent(String userName,String passWord,String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txUpdateDepartMent(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0100070","updateDepartMent","E0000008",APPID,result);  
	}
	
	/**删除部门信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String deleteDepartMent(String userName,String passWord,String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txDeleteDepartMent(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TD0100050","deleteDepartMent","E0000009",APPID,result); 
	}
	
	
	/**新增人员信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String createEmployee(String userName,String passWord,String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txCreateEmployee(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0100060","createEmployee","E0000010",APPID,result); 
	}
	
	
	/**修改人员信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String updateEmployee(String userName,String passWord,String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txUpdateEmployee(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0100080","updateEmployee","E0000011",APPID,result); 
	}
	
	/**删除人员信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String deleteEmployee(String userName,String passWord,String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txDeleteEmployee(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TD0100060","deleteEmployee","E0000012",APPID,result); 
	}
	
	/**新增用户信息
	 * @param messageDate
	 * @return
	 */
	public static String createUserInfo(String userName,String passWord,String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txCreateUserInfo(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0100180","createUserInfo","E0000013",APPID,result); 
	}
	
	/**修改用户信息
	 * @param messageDate
	 * @return
	 */
	public static String updateUserInfo(String userName,String passWord,String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txUpdateUserInfo(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0100160","updateUserInfo","E0000014",APPID,result); 
	}
	
	/**删除用户信息
	 * @param messageDate
	 * @return
	 */
	public static String deleteUserInfo(String userName,String passWord,String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txDeleteUserInfo(jsonData);
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TD0100140","deleteUserInfo","E0000015",APPID,result); 
	}
	
	/**更新实发数量
	 * @return
	 */
	public static boolean updateRealNum(String DELIVER_ORDER_NO,List result,String userId,String userName){
		try {
			HashMap inputMap = new HashMap();
			HashMap headMap = new HashMap();
			headMap.put("ServiceCode", "OrderManage");
			headMap.put("Operation", "CreateOrder");
			headMap.put("AppCode", userId);
			headMap.put("UId", StringUtil.uuid32len());
			headMap.put("AuthId", userName);
			ArrayList headList = new ArrayList();
			headList.add(headMap);
			inputMap.put("MbfHeader", headList);
			HashMap deliverMap = new HashMap();

			ArrayList dtlList = new ArrayList();

			for (int i = 0; i < result.size(); i++) {
				com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel model = (TurnoverplanChildModel) result.get(i);
				Map<String, Object> map = new HashMap<String, Object>();
				String saleOrderNo = model.getSALE_ORDER_NO();
				String pdNo =model.getPRD_NO();
				Map<String, String> params = new HashMap<String, String>();
				params.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
				params.put("SALE_ORDER_NO", saleOrderNo);
				params.put("PRD_NO", pdNo);

				map.put("DELIVER_ORDER_DTL_ID",model.getDELIVER_ORDER_DTL_ID());
				String REAL_STOREOUT_NUM = model.getREAL_SEND_NUM();
				map.put("REAL_STOREOUT_NUM", REAL_STOREOUT_NUM);
				dtlList.add(map);
			}
			deliverMap.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
			deliverMap.put("DETAIL", dtlList);
			ArrayList bodyList = new ArrayList();
			bodyList.add(deliverMap);
			inputMap.put("MbfBody", bodyList);

			HashMap serMap = new HashMap();
			ArrayList iputList = new ArrayList();
			iputList.add(inputMap);
			serMap.put("input1", iputList);

			HashMap jsonMap = new HashMap();
			ArrayList serList = new ArrayList();
			serList.add(serMap);
			jsonMap.put("MbfService", serList);
			String jsonDeliver = new Gson().toJson(jsonMap);
			String msg = updateDeliverOrder(Consts.DM_USERNAME,
					Consts.DM_PASSWORD, jsonDeliver);
			String jsonStr = LogicUtil.reutrnEsbInfo(msg);
			if (jsonStr.contains("false")) {
				throw new ServiceException(jsonStr);
			}
		} catch (Exception e) {
			throw new ServiceException("修改实发数量失败!");
		}
		return true;
	}
	
	/**U9回填实际发货数量，做相关处理 发运确认
	 * @param messageDate  出库主表信息
	 * @param jsonPdt   出库货品明细信息
	 * @param jsonPdt   出库序列号明细
	 * @throws Exception
	 */
	public static String updateDeliverOrder(String userName,String passWord,String messageDate){
		//回填实际数量到 发运单
		//生成收货方的入库通知单
		//如果发运单的提交方式为'区域代发',还要生成分发指令单，生成公告信息,告之 XXX货品以送至XX区域服务中心
		actLog("发运管理", "开始调入发运单确认接口", "U9或UA系统", "成功", messageDate, "", "", "","");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txUpdateDeliverOrder(messageDate);
		}
		if(BusinessConsts.SUCCESS.equals(result.getFLAG())){
			actLog("发运管理", "发运单确认", result.getAPPCODE(), "成功", messageDate, "", "", "",result.getKEY());
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("发运管理", "发运单确认", result.getAPPCODE(), "失败", "["+result.getMESSAGE()+"]"+messageDate, "", "", "",result.getKEY());
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(messageDate);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0300010","updateDeliverOrder","E0000016",APPID,result); 
	}
	
	
	/**
	 * 新增品牌
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String createBrand(String userName,String passWord,String messageDate){
		actLog("品牌管理", "新增品牌", "U9系统", "成功",messageDate, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txCreateBrand(messageDate);
		}
		actLog("品牌管理", "新增品牌", "U9系统", "成功", BusinessConsts.IMPL_GO_OUT_INFO, "", "", "");
		JesonImplUtil jsonUtil = new JesonImplUtil(messageDate);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0100030","createBrand","E0000017",APPID,result); 
	}
	/**
	 * 修改品牌
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String updateBrand(String userName,String passWord,String messageDate){
		actLog("品牌管理", "修改品牌", "U9系统", "成功",messageDate, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txUpdateBrand(messageDate);
		}
		actLog("品牌管理", "修改品牌", "U9系统", "成功", BusinessConsts.IMPL_GO_OUT_INFO, "", "", "");
		JesonImplUtil jsonUtil = new JesonImplUtil(messageDate);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0100050","updateBrand","E0000018",APPID,result); 
	}
	/**
	 * 删除品牌
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String deleteBrand(String userName,String passWord,String messageDate){
		actLog("品牌管理", "删除品牌", "U9系统", "成功",messageDate, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txDeleteBrand(messageDate);
		}
		actLog("品牌管理", "删除品牌", "U9系统", "成功", BusinessConsts.IMPL_GO_OUT_INFO, "", "", "");
		JesonImplUtil jsonUtil = new JesonImplUtil(messageDate);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TD0100030","deleteBrand","E0000019",APPID,result); 
	}
	
	/**
	 * 新增货品分类
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static String createPdtMenu(String userName,String passWord,String messageDate){
		actLog("货品分类管理", "新增货品分类", "U9系统", "成功", messageDate, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,passWord)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txCreatePdtMenu(messageDate);
		}
		actLog("货品分类管理", "新增货品分类", "U9系统", "成功", messageDate, "", "", "");
		JesonImplUtil jsonUtil = new JesonImplUtil(messageDate);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0100130","createPdtMenu","E0000020",APPID,result); 
	}
	
	/**
	 * 修改货品分类
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static String updatePdtMenu(String userName,String password,String jsonData){
		actLog("货品分类管理", "修改货品分类", "U9系统", "成功", jsonData, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txUpdatePdtMenu(jsonData);
		}
		actLog("货品分类管理", "修改货品分类", "U9系统", "成功", BusinessConsts.IMPL_GO_OUT_INFO, "", "", "");
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0100110","updatePdtMenu","E0000021",APPID,result); 
	}
	
	/**
	 * 删除货品分类
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static String deletePdtMenu(String userName,String password,String jsonData){
		actLog("货品分类管理", "删除货品分类", "U9系统", "成功", BusinessConsts.IMPL_GO_IN_INFO, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txDeletePdtMenu(jsonData);
		}
		actLog("货品分类管理", "删除货品分类", "U9系统", "成功", BusinessConsts.IMPL_GO_OUT_INFO, "", "", "");
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TD0100090","deletePdtMenu","E0000022",APPID,result); 
	}
	
	
	
	/**
	 * 新增货品
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static String createProduct(String userName,String password,String jsonData){
		actLog("货品管理", "新增货品", "U9系统", "成功",jsonData, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txCreateProduct(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("货品管理", "新增货品", "U9系统", "失败",result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0100140","createProduct","E0000023",APPID,result); 
	}
	
	/**
	 * 修改货品
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static String updateProduct(String userName,String password,String jsonData){
		actLog("货品管理", "修改货品", "U9系统", "成功",jsonData, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txUpdateProduct(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("货品管理", "修改货品", "U9系统", "失败",result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0100120","updateProduct","E0000024",APPID,result); 
	}
	
	/**
	 * 删除货品
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static String deleteProduct(String userName,String password,String jsonData){
		actLog("货品管理", "成功调入删除货品接口", "U9系统", "成功",jsonData, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txDeleteProduct(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("货品管理", "删除货品失败", "U9系统", "失败",result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TD0100100","deleteProduct","E0000025",APPID,result); 
	}
	
	/**新增货品单位
	 * @param jsonData
	 * @return
	 */
	public static String createUnitMenu(String userName,String password,String jsonData){
		actLog("货品单位管理", "新增货品单位", "U9系统", "成功", BusinessConsts.IMPL_GO_IN_INFO, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txCreateUnitMenu(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("货品单位管理", "新增货品单位", "U9系统", "失败", result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0100150","createUnitMenu","E0000026",APPID,result); 
		
	}
	
	/**修改货品单位
	 * @param jsonData
	 * @return
	 */
	public static String updateUnitMenu(String userName,String password,String jsonData){
		actLog("货品单位管理", "修改货品单位", "U9系统", "成功", BusinessConsts.IMPL_GO_IN_INFO, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txUpdateUnitMenu(jsonData);
		}
		actLog("货品单位管理", "修改货品单位", "U9系统", "成功", BusinessConsts.IMPL_GO_OUT_INFO, "", "", "");
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0100130","updateUnitMenu","E0000027",APPID,result); 
		
	}
	
	/**删除货品单位
	 * @param jsonData
	 * @return
	 */
	public static String deleteUnitMenu(String userName,String password,String jsonData){
		actLog("货品单位管理", "删除货品单位", "U9系统", "成功调入接口", BusinessConsts.IMPL_GO_IN_INFO, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txDeleteUnitMenu(jsonData);
		}
		actLog("货品单位管理", "删除货品单位", "U9系统", "成功", BusinessConsts.IMPL_GO_OUT_INFO, "", "", "");
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TD0100120","deleteUnitMenu","E0000028",APPID,result); 
		
	}
	
	
	
	/**新增销售区域
	 * @param jsonData
	 * @return
	 */
	public static String createSaleArea(String userName,String password,String jsonData){
		actLog("销售区域管理", "新增销售区域", "U9系统", "成功调入接口", BusinessConsts.IMPL_GO_IN_INFO, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result =   InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txCreateSaleArea(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("销售区域管理", "新增销售区域", "U9系统", "失败", result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0100160","createSaleArea","E0000029",APPID,result); 
	}
	
	/**修改销售区域
	 * @param jsonData
	 * @return
	 */
	public static String updateSaleArea(String userName,String password,String jsonData){
		actLog("销售区域管理", "修改销售区域", "U9系统", "成功调入接口", BusinessConsts.IMPL_GO_IN_INFO, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.trxUpdateSaleArea(jsonData);
		}
		actLog("销售区域管理", "修改销售区域", "U9系统", "成功", BusinessConsts.IMPL_GO_OUT_INFO, "", "", "");
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0100140","updateSaleArea","E0000030",APPID,result); 
	}
	
	/**修改销售区域
	 * @param jsonData
	 * @return
	 */
	public static String deleteSaleArea(String userName,String password,String jsonData){
		actLog("销售区域管理", "删除销售区域", "U9系统", "成功调入接口", BusinessConsts.IMPL_GO_IN_INFO, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txdeleteSaleArea(jsonData);
		}
		actLog("销售区域管理", "删除销售区域", "U9系统", "成功", BusinessConsts.IMPL_GO_OUT_INFO, "", "", "");
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TD0100120","deleteSaleArea","E0000031",APPID,result); 
	}
	
	/**新增渠道信息
	 * @param jsonData
	 * @return
	 */
	public static String createBaseChann(String userName,String password,String jsonData){
		actLog("渠道管理", "成功调入新增渠道信息接口", "U9系统", "成功", jsonData, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txCreateBaseChann(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("渠道管理", "新增渠道信息失败", "U9系统", "失败",result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0100170","createBaseChann","E0000032",APPID,result); 
	}
	
	/**修改渠道信息
	 * @param jsonData
	 * @return
	 */
	public static String updateBaseChann(String userName,String password,String jsonData){
		actLog("渠道管理", "成功调入修改渠道信息接口", "U9系统", "成功", jsonData, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txUpdateBaseChann(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("渠道管理", "修改渠道信息接口失败", "U9系统", "失败",result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0100150","updateBaseChann","E0000033",APPID,result); 
	}
	
	/**删除渠道信息
	 * @param jsonData
	 * @return
	 */
	public static String deleteBaseChann(String userName,String password,String jsonData){
		actLog("渠道管理", "成功调入删除渠道信息接口", "U9系统", "成功", jsonData, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result =  InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txDeleteBaseChann(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("渠道管理", "删除渠道信息出错", "U9系统", "失败",result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TD0100130","deleteBaseChann","E0000034",APPID,result); 
	}

	/**新增客户付款单
	 * @param jsonData
	 * @return
	 */
	public static String createPayMent(String userName,String password,String jsonData){
		actLog("信用管理", "成功调入新增客户付款单", "U9系统", "成功", jsonData, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txCreatePayMent(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("信用管理", "新增客户付款单出错", "U9系统", "失败",result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0200010","createPayMent","E0000035",APPID,result); 
	}
		
	/**弃审付款单
	 * @param jsonData
	 * @return
	 */
	public static String unAuditPayMent(String userName,String password,String jsonData){
		actLog("信用管理", "弃审付款单", "U9系统", "成功", jsonData, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txUnAuditPayment(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("信用管理", "弃审付款单出错", "U9系统", "失败",result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0200010","unAuditPayMent","E0000036",APPID,result); 
	}
	
	/**新增送货地址
	 * @param jsonData
	 * @return
	 */
	public static String createDeliverAddr(String userName,String password,String jsonData){
		actLog("送货地址管理", "成功调入新增送货地址接口", "U9系统", "成功", jsonData, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txCreateDeliverAddr(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("送货地址管理", "新增送货地址出错", "U9系统", "失败",result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TC0100040","createDeliverAddr","E0000037",APPID,result); 
	}
	
	/**修改送货地址
	 * @param jsonData
	 * @return
	 */
	public static String updateDeliverAddr(String userName,String password,String jsonData){
		actLog("送货地址管理", "成功调入修改送货地址接口", "U9系统", "成功", jsonData, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txUpdateDeliverAddr(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("送货地址管理", "修改送货地址出错", "U9系统", "失败",result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0100060","updateDeliverAddr","E0000038",APPID,result); 
	}
	
	/**删除送货地址
	 * @param jsonData
	 * @return
	 */
	public static String deleteDeliverAddr(String userName,String password,String jsonData){
		actLog("送货地址管理", "成功调入删除送货地址接口", "U9系统", "成功", jsonData, "", "", "");
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		if(!checkEsbUser(userName,password)){
			result = InterUtil.returnCheckUserFail();
		}else{
			result =  logicUtilServiceImpl.txDeleteDeliverAddr(jsonData);
		}
		if(BusinessConsts.FLASE.equals(result.getFLAG())){
			actLog("送货地址管理", "删除送货地址出错", "U9系统", "失败",result.getMESSAGE(), "", "", "");
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TD0100040","deleteDeliverAddr","E0000039",APPID,result); 
	}
	
	public static String strCreateShipPlan(String BusinessId,String InterfaceCode,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String OPRCODE)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		String strJsonData =  logicUtilServiceImpl.createShipPlan(BusinessId,InterfaceCode,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE);
		return strJsonData;
	}
	
	/**按行取消
	 * @param deliverId
	 * @param InterfaceCode
	 * @param ServiceCode
	 * @param Operation
	 * @param AppCode
	 * @param DestCode
	 * @param UId
	 * @param OPRCODE
	 * @return
	 * @throws Exception
	 */
	public static String strCreateShipPlanByClose(String deliverId,String DELIVER_ORDER_DTL_IDS,String InterfaceCode,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String OPRCODE)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		String strJsonData =  logicUtilServiceImpl.createShipPlanByClose(deliverId,DELIVER_ORDER_DTL_IDS,InterfaceCode,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE);
		return strJsonData;
	}
	
	/**创建出货单
	 * @param BusinessId
	 * @return
	 */
	public static String createShipPlan(String strJsonData)throws Exception{
		String InterfaceCode = "TC0300020";
		ArrayList bussParam = new ArrayList();
		bussParam.add(InterfaceCode);
		bussParam.add(strJsonData);
		String result = new CommWebServiceClient().callWebService(callESBSynMethod, bussParam);
		return returnRspInfo(result);
	}
	
	public static String getStrCreateSO(String BusinessId,String Is_Abort_Flag)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		String ServiceCode = "TC0300010";
		String Operation = "createSO";
		String AppCode = "DM";
		String DestCode = "U9";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode + ":" + "createSO";
		String strJsonData =  logicUtilServiceImpl.createSO(BusinessId,Is_Abort_Flag,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE);
		return strJsonData;
	}

	/**交付计划生成销售订单
	 * @param BusinessId 
	 * @return 
	 */
	public static String createSO(String strJsonData)throws Exception{
		String ServiceCode = "TC0300010";
		ArrayList bussParam = new ArrayList();
		bussParam.add(ServiceCode);
		bussParam.add(strJsonData);
		String result = new CommWebServiceClient().callWebService(callESBSynMethod, bussParam);
		return result;
	}
	
	public static String strModifyRequireDate(String BusinessId,String TEMP_DATE,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String OPRCODE )throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		String strJsonData = logicUtilServiceImpl.modifyRequireDate(BusinessId,TEMP_DATE,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE);
		return strJsonData;
	}
	
	/**修改交期传给u9
	 * @param BusinessId
	 * @return
	 */
	public static String modifyRequireDate(String strJsonData)throws Exception{
		String ServiceCode = "TU0300020";
		ArrayList bussParam = new ArrayList();
		bussParam.add(ServiceCode);
		bussParam.add(strJsonData);
		String result = new CommWebServiceClient().callWebService(callESBSynMethod, bussParam);
		return returnRspInfo(result);
	}
	
	public static String getStrCreateRMA(String BusinessId)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		String ServiceCode = "TC0200120";
		String Operation = "createRMA";
		String AppCode = "DM";
		String DestCode = "U9";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode+ ":" + "createRMA";
		String strJsonData = logicUtilServiceImpl.createRMA(BusinessId,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE);
		return strJsonData;
	}
	
	public static String getStrCreateRePairRMA(String BusinessId)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		String ServiceCode = "TC0200120";
		String Operation = "createRMA";
		String AppCode = "DM";
		String DestCode = "U9";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode+ ":" + "createRMA";
		String strJsonData = logicUtilServiceImpl.createRepair_RMA(BusinessId,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE);
		return strJsonData;
	}
	
	/** 创建退货单/返修单
	 * @param BusinessId
	 * @return
	 */
	public static String createRMA(String strJsonData)throws Exception{
		String ServiceCode = "TC0200120";
		ArrayList bussParam = new ArrayList();
		bussParam.add(ServiceCode);
		bussParam.add(strJsonData);
		String result = new CommWebServiceClient().callWebService(callESBSynMethod, bussParam);
		return returnRspInfo(result);
	}
	
	/**应收、余额查询
	 * @param CHANN_NO
	 * @param LEDGER_ID
	 * @return
	 */
	public static String queryPayMent(String CHANN_NO,String LEDGER_ID)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		String ServiceCode = "TR0200010";
		String Operation = "queryPayMent";
		String AppCode = "DM";
		String DestCode = "U9";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode+ ":" + Operation;
		String strJsonData = logicUtilServiceImpl.txQueryPayMent(CHANN_NO,LEDGER_ID,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE);
		ArrayList bussParam = new ArrayList();
		bussParam.add(ServiceCode);
		bussParam.add(strJsonData);
		LogicUtil.actLog("报表查询管理", "余额查询", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE);
		String result = new CommWebServiceClient().callWebService(callESBSynMethod, bussParam);
		LogicUtil.actLog("报表查询管理", "余额查询", "U9系统", "成功",result,AppCode,UId,OPRCODE);
		return reutrnEsbInfo(result);
	}
	
	
	/**库存查询
	 * @param CHANN_NO
	 * @param PDT_ID
	 * @param LEDGER_ID
	 * @return
	 */
	public static String queryStoreAcct(String CHANN_NO,String PDT_ID,String LEDGER_ID)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		String InterfaceCode = "TR0300040";
		String ServiceCode = "ReportQuery";
		String Operation = "queryStoreAcct";
		String AppCode = "DM";
		String DestCode = "U9";
		String UId = StringUtil.uuid32len();
		String strJsonData = logicUtilServiceImpl.txQueryStoreAcct(InterfaceCode,ServiceCode,Operation,AppCode,DestCode,UId,CHANN_NO,PDT_ID,LEDGER_ID);
		ArrayList bussParam = new ArrayList();
		bussParam.add("TR0300040");
		bussParam.add(strJsonData);
		String result = new CommWebServiceClient().callWebService(callESBSynMethod, bussParam);
		return reutrnEsbInfo(result);
	}
	
	
	/**交付计划-生产情况查询
	 * @param CHANN_NO
	 * @param PDT_ID
	 * @param LEDGER_ID
	 * @return
	 */
	public static String queryProStatus(String DeliverPlanNo)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		String InterfaceCode = "TR0300010";
		String ServiceCode = "ReportQuery";
		String Operation = "queryProStatus";
		String AppCode = "DM";
		String DestCode = "U9";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode+ ":" + Operation;
		String strJsonData = logicUtilServiceImpl.txQueryProStatus(InterfaceCode,ServiceCode,Operation,AppCode,DestCode,UId,DeliverPlanNo);
		ArrayList bussParam = new ArrayList();
		bussParam.add(InterfaceCode);
		bussParam.add(strJsonData);
		LogicUtil.actLog("报表查询管理", "生产情况查询", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE);
		String result = new CommWebServiceClient().callWebService(callESBSynMethod, bussParam);
		LogicUtil.actLog("报表查询管理", "生产情况查询", "U9系统", "成功",result,AppCode,UId,OPRCODE);
		return reutrnEsbInfo(result);
	}
	
	
	/**退货单跟踪查询
	 * @param RETURN_NO
	 * @return
	 */
	public static String queryReturnTrace(String RETURN_NO)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		String InterfaceCode = "TR0300030";
		String ServiceCode = "ReportQuery";
		String Operation = "queryReturnTrace";
		String AppCode = "DM";
		String DestCode = "U9";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode+ ":" + Operation;
		String strJsonData = logicUtilServiceImpl.txQueryReturnTrace(InterfaceCode,ServiceCode,Operation,AppCode,DestCode,UId,RETURN_NO);
		ArrayList bussParam = new ArrayList();
		bussParam.add(InterfaceCode);
		bussParam.add(strJsonData);
		LogicUtil.actLog("报表查询管理", "退货单跟踪查询", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE);
		String result = new CommWebServiceClient().callWebService(callESBSynMethod, bussParam);
		LogicUtil.actLog("报表查询管理", "退货单跟踪查询", "U9系统", "成功",result,AppCode,UId,OPRCODE);
		return reutrnEsbInfo(result);
	}

	public static String reutrnEsbInfo(String strJsonData){
		JesonImplUtil jsUtil = new JesonImplUtil(true,strJsonData);
		HashMap headMap =  jsUtil.getMbHead();
		HashMap resMap = jsUtil.getResponse();
		String Status = (String)resMap.get("Status");
		String Code = (String)resMap.get("Code");
		if(BusinessConsts.WS_SUCESS.equals(Code)){
			ArrayList bodyList = jsUtil.getMbBody();
        	String impJsonData = new Gson().toJson(bodyList);
        	return impJsonData;
		}else{
			ArrayList list  = new ArrayList();
			list.add(resMap);
			String resuData = new Gson().toJson(list);
        	return resuData;
			
		}
	}
	public static String returnRspInfo(String strJsonData){
		JesonImplUtil jsUtil = new JesonImplUtil(true,strJsonData);
		HashMap headMap =  jsUtil.getMbHead();
		Map rspMap = jsUtil.getResponse();
		ArrayList bodyList = jsUtil.getMbBody();
		HashMap bodyMap = (HashMap)bodyList.get(0);
		String resuData = new Gson().toJson(bodyMap);
    	return resuData;
	}
	
	public static boolean checkEsbUser(String userName,String password){
		if(Consts.DM_USERNAME.equals(userName)&&Consts.DM_PASSWORD.equals(password)){
			return true;
		}
		return false;
	}
	
	private static String returnDMInfo(String code,String operation,String errorCode,String UId,InterReturnMsg msg){
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", code);
		headMap.put("ServiceCode", code);
		headMap.put("Operation", operation);
		headMap.put("AppCode", "U9");
		headMap.put("DestCode", "DM");
		headMap.put("TransType", "1");
		headMap.put("UId", UId);
		ArrayList respList = new ArrayList();
		HashMap rspMap = new HashMap();
		rspMap.put("Status", msg.getFLAG());
		
		if(BusinessConsts.SUCCESS.equals(msg.getFLAG())){
			rspMap.put("Code", "00000000");
		}else{
			rspMap.put("Code",errorCode);
		}
		String messge ="";
		if(msg.getMESSAGE()!=null){
			messge = msg.getMESSAGE();
		}
		rspMap.put("Desc", messge);
		respList.add(rspMap);
		headMap.put("ServiceResponse", respList);
		return JesonImplUtil.getImplRsponseJson(headMap, new LinkedHashMap());
	}
	
	
	/**
	 * 渠道启用时（从制定状态启用）插入系统用户表，人员信息表等
	 * @param CHANN_ID
	 * @return
	 */
	public static boolean insertChannConf(String CHANN_ID){
		ChannServiceImpl channService = (ChannServiceImpl) SpringContextUtil.getBean("channService");
		Map<String,String> map=new HashMap<String,String>();
    	map.put("CHANN_ID", CHANN_ID);
    	map.put("YJZBJ", BusinessConsts.YJLBJ_FLAG_TRUE);
    	map.put("STATE", BusinessConsts.JC_STATE_ENABLE);
    	map.put("ZTLB", "独立帐套");
    	map.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
    	map.put("IS_DRP_LEDGER", BusinessConsts.YJLBJ_FLAG_TRUE);
    	map.put("CRENAME", "admin");
    	map.put("CRETIME", "sysdate");
    	map.put("XM", "加盟商初始用户");
    	map.put("XB", "男");
    	map.put("RYJB", "加盟商");
    	map.put("RYLB", "加盟商管理员");
    	map.put("YHKL", "e99a18c428cb38d5f260853678922e03");
    	map.put("YHLB", "普通用户");
    	map.put("XTJSID", Consts.INI_CHANN_ROLE_NO);
    	return channService.txInsertSundry(map);
	}
	
	private static boolean checkResPonseState(String strJsonData){
		JesonImplUtil jsUtil = new JesonImplUtil(true,strJsonData);
		HashMap headMap =  jsUtil.getMbHead();
		Map rspMap = jsUtil.getResponse();
		String stats = (String)rspMap.get("Status");
		if("false".equals(stats)){
				return false;
		}else{
			return true;
		}
	}
	/**
	 * 根据所传渠道id查询渠道折扣率，如果该渠道是惩罚渠道则取惩罚折扣，不然则取正常折扣
	 * @param CHANN_ID 当前登录人所属渠道
	 * @return 如果有折扣率，则返回折扣率字符串，如果没有，则返回null
	 */
	public static String getChannDsct(String CHANN_ID){
		ChannServiceImpl channService = (ChannServiceImpl) SpringContextUtil.getBean("channService");
		Map<String,String> map=new HashMap<String, String>();
		map.put("CHANN_ID", CHANN_ID);
		map.put("nrlDsct", BusinessConsts.DECT_TYPE);
		map.put("punDsct", "惩罚折扣");
		Map<String,Object> result = (Map<String, Object>) channService.load("chann.getChannDsct", map);
		if(null == result){
			return null;
		}else{
			return StringUtil.nullToSring(result.get("DECT_RATE"));
		}
	}
	/**
	 * 根据所传渠道id查询返利折扣
	 * @param CHANN_ID  渠道ID
	 * @return 
	 */
	public static Map<String,Object> getChannRebateDsct(String CHANN_ID){
		ChannServiceImpl channService = (ChannServiceImpl) SpringContextUtil.getBean("channService");
		Map<String,String> map = new HashMap<String, String>();
		map.put("CHANN_ID", CHANN_ID);
		map.put("DSCTTYPE", "返利折扣");
		Map<String,Object> result = (Map<String, Object>) channService.load("chann.getChannRebateDsct", map);
		return result;
	}
	
	
	/**
	 * 根据特殊工艺id获取特殊工艺信息，拼成字符串返回
	 * @return
	 */
	public static String getSpclInfoById(String SPCL_TECH_ID){
		TechorderManagerServiceImpl techorderManagerService = (TechorderManagerServiceImpl) SpringContextUtil.getBean("techorderManagerService");
		List<Map<String,String>> list=techorderManagerService.getSpclInfoById(SPCL_TECH_ID);
		//尺寸调整
		StringBuffer adjustSize=new StringBuffer();
		//部件替换
		StringBuffer unitReplace=new StringBuffer();
		//部件新增
		StringBuffer addUnit=new StringBuffer();
		//备注
		StringBuffer REMARK=new StringBuffer();
		
		StringBuffer spctchDesc=new StringBuffer();
		boolean flag=false;
		for(int i=0;i<list.size();i++){
			Map<String,String> map=list.get(i);
			if("非标工艺说明".equals(map.get("SPCL_TECH_TYPE"))){
				String strRemArk = map.get("REMARK");
				if(strRemArk!=null){
					REMARK.append(map.get("REMARK"));
					continue;
				}
			}
			if("尺寸调整".equals(map.get("SPCL_TECH_TYPE"))){
				String TechName = map.get("SPCL_TECH_NAME");
				if(TechName!=null && "宽度调整".equals(TechName.trim())){
					adjustSize.append("宽:").append(map.get("CUST_TURN_VAL")).append(",");
				}else if(TechName!=null && "长度调整".equals(TechName.trim())){
					adjustSize.append("长:").append(map.get("CUST_TURN_VAL")).append(",");
				}else{
					adjustSize.append(map.get("SPCL_TECH_NAME")).append(":").append(map.get("CUST_TURN_VAL")).append(",");
				}
			}
			if("部件替换".equals(map.get("SPCL_TECH_TYPE"))){
				unitReplace.append(map.get("CURRT_VAL")).append(" 替换为  ").append(map.get("CUST_TURN_VAL"));
			}
			if("部件新增".equals(map.get("SPCL_TECH_TYPE"))){
				flag=true;
				addUnit.append(map.get("SPCL_TECH_NAME")).append(",");
			}
			if("一般特殊工艺描述".equals(map.get("SPCL_TECH_TYPE"))){
				String remark = map.get("REMARK");
				if(remark!=null){
					spctchDesc.append(map.get("REMARK")).append(",");
				}
			}
		}
		StringBuffer explain=new StringBuffer();
		String unitReplaceString=unitReplace.toString();
		if(flag&&unitReplaceString.length()>0){
			unitReplaceString = unitReplaceString.substring(0,unitReplaceString.length() - 1);
		}
		if(adjustSize.toString().length()>0){
			explain.append(adjustSize.toString()).append("\n");
		}
		if(unitReplace.toString().length()>0){
			explain.append(unitReplace.toString()).append("\n");
		}
		if(addUnit.toString().length()>0){
			explain.append(addUnit.toString()).append("\n");
		}
		if(REMARK.toString().length()>0){
			explain.append(REMARK.toString());
		}
		if(spctchDesc.toString().length()>0){
			explain.append(spctchDesc.toString());
		}
		return explain.toString();
	}



	public static String getSpecTechNO(String specTechId,String pdtNo)throws Exception{
		TechorderManagerServiceImpl techorderManagerService = (TechorderManagerServiceImpl) SpringContextUtil.getBean("techorderManagerService");
		Map<String,String> specTechMap = techorderManagerService.getSpclDataById(specTechId);
		if(specTechMap==null){
			throw new Exception("数据出错,货品:"+pdtNo+"特殊工艺ID为不存在!");
		}
		
		String newSpecTechNo = techorderManagerService.txSpclDataByPdtNo(specTechId,pdtNo);
		return newSpecTechNo;
		
//		String oldSpecTechNo =  specTechMap.get("SPCL_TECH_NO");
//		if(oldSpecTechNo!=null){ //如果有工艺单编号
//			return oldSpecTechNo;
//		}else{
//			// 新的特殊工艺编号
//			String newSpecTechNo = techorderManagerService.txSpclDataByPdtNo(specTechId,pdtNo);
//			return newSpecTechNo;
//		}
	}
	
	public static boolean insJOURNALInitCREDIT(String bussID,String keyValue ,String DIRECTION)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.insertJOURNAL_InitCREDIT(bussID,keyValue , DIRECTION);
	}
	
    public static Map getOrderNumBydeliverId(String DELIVER_ORDER_DTL_ID){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		return logicUtilServiceImpl.getOrderNumByDeliverId(DELIVER_ORDER_DTL_ID);
    }
    
    public static boolean checkDeliverDtlStat(String deliverOrderNo){
    	LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
    	return logicUtilServiceImpl.checkDeliverDtlStat(deliverOrderNo);
    }
    
    public static boolean checkDeliverDtlStatById(String deliverOrderId){
    	LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
    	return logicUtilServiceImpl.checkDeliverDtlStatById(deliverOrderId);
    }
    
    /**发运单整单冻结
     * @param deliverOrderId
     * @return
     */
    public static boolean txDeliverOrderFreeze(String deliverOrderId){
    	LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
    	return logicUtilServiceImpl.txAllDeliverOrderFreeze(deliverOrderId);
    }
    
    /**发运单整单解冻
     * @param deliverOrderId
     * @return
     */
    public static boolean txDeliverOrderunFreeze(String deliverOrderId){
    	LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
    	return logicUtilServiceImpl.txAllDeliverOrderunFreeze(deliverOrderId);
    }
    /**检查是否已经月结 true 代表已经月结 false代表没有
     * @param deliverOrderId
     * @return
     */
    public  static boolean isMonthAcc(String ledgerId,String bussDate){
    	if(StringUtil.isEmpty(ledgerId)||StringUtil.isEmpty(bussDate))
    	{
    		throw new ServiceException("传递的参数有空值!ledger_Id==="+ledgerId+"===date=="+bussDate);
    	}	
    	LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
    	int num=0;
    	Map acc= logicUtilServiceImpl.getMonthAcc(ledgerId, bussDate);
    	if(acc!=null)
    	{
    		num=Integer.parseInt(acc.get("NUM").toString());
    	}
    	if(num==0)
    	{
    		return false;	
    	}
    	else
    	{
    	   return true;		
    	}
    	
    }
    public static Map<String,String> getPrvdInfo(){
    	LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
    	return (Map<String, String>) logicUtilServiceImpl.load("PROVIDER.getPrvdInfo","");
    }
    
    
	/**
	 * 查询渠道可用订货信用
	 * @param CHANN_ID
	 * @return
	 */
	public static String getU9CurrCredit(String CHANN_NO){
		try {
			return CreditCtrlUtil.getU9CurrCredit(CHANN_NO);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "0";
	}
	/**
	 * mapi里存 
	 * TABLE_NAME:需要修改的表名
	 * BUSS_ID:需要修改的id
	 * @param map
	 * @return
	 */
	public static boolean txUpdatePrintNum(Map<String,String> map){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		if(map.isEmpty()){
			return false;
		}
		String TABLE_NAME=map.get("TABLE_NAME");
		String BUSS_ID=map.get("BUSS_ID");
		if(StringUtil.isEmpty(TABLE_NAME)||StringUtil.isEmpty(BUSS_ID)){
			return false;
		}
		//根据表名获取主键名称
		String COLUMN_NAME=logicUtilServiceImpl.load("TechorderManager.getColumnName", map.get("TABLE_NAME")).toString();
		StringBuffer str=new StringBuffer();
		str.append(" update ").append(TABLE_NAME).append(" set PRINT_NUM=NVL(PRINT_NUM,0)+1 where ");
		if(BUSS_ID.length()>32){
			str.append(COLUMN_NAME).append(" in (").append(BUSS_ID).append(")");
		}else{
			str.append(COLUMN_NAME).append("='").append(BUSS_ID).append("'");
		}
		System.out.println(str.toString());
		return logicUtilServiceImpl.update("TechorderManager.backFill", str.toString());
	}
	
	public static boolean instSpechNoRemark(String sale_order_no,String sale_order_dtl_id,String prd_no,String spcl_tech_id,String spcl_tech_no,String spetchDesc){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		logicUtilServiceImpl.instSpechNoRemark( sale_order_no, sale_order_dtl_id, prd_no, spcl_tech_id, spcl_tech_no,spetchDesc);
		return true;
	}
	
	
	public static void genProfitLossOrder(String invenToryId)throws Exception{
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		logicUtilServiceImpl.genProfitLossOrder(invenToryId);
	}
	
	/**
	 * 记录预订单已付款金额变动
	 * @param userBean 登录用户信息
	 * @param ADVC_ORDER_ID 预订单id
	 * @param DEAL_AMOUNT 操作金额
	 * @param DOCUMENTS_NO 操作编号
	 * @param DEAL_STATE 操作状态(0为加，1为减)
	 * @param REMARK 操作说明
	 * @return
	 */
	public static boolean instAdvcAmountNotes(UserBean userBean,String ADVC_ORDER_ID,String DEAL_AMOUNT,String DOCUMENTS_NO,String DEAL_STATE,String REMARK){
		AdvcorderServiceImpl advcorderServiceImpl = (AdvcorderServiceImpl) SpringContextUtil.getBean("advcorderService");
		Map<String,String> map=new HashMap<String, String>();
		map.put("BUSS_ID", StringUtil.uuid32len());
		map.put("DOCUMENTS_NO", DOCUMENTS_NO);
		map.put("CRE_TIME", "sysdate");
		map.put("CRE_NAME", userBean.getXM());
		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		map.put("DEAL_STATE", DEAL_STATE);
		map.put("CREATOR", userBean.getRYXXID());
		map.put("DEAL_AMOUNT", DEAL_AMOUNT);
		map.put("REMARK", REMARK);
		try {
			boolean flag=advcorderServiceImpl.insertAdvcNotes(map);
			if(flag){
				Map<String,String> params=new HashMap<String, String>();
				map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
				map.put("BILL_TYPE", "'订金','正常收款','预付款','销售退款'");
				map.put("STATE", BusinessConsts.STATE_IS_PAY);
				map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				return advcorderServiceImpl.update("Advpayment.updateAdvcAmount", map);
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 验证库房是否冻结，冻结返回false
	 * @param STORE_ID
	 * @return
	 */
	public static boolean checkFreezeStore(String STORE_ID){
		if(StringUtil.isEmpty(STORE_ID)){
			return false;
		}
		StoreServiceImpl storeServiceImpl = (StoreServiceImpl) SpringContextUtil.getBean("storeService");
		Integer freezeFlag=storeServiceImpl.getStoreFreezeFlag(STORE_ID);
		if(1==freezeFlag){
			return false;
		}else{
			return true;
		}
	}
	
	public static StringBuffer getAudInfo(String xtyhid) {
	    StringBuffer aReSQL=new StringBuffer();
	    aReSQL.append(" select  a.MENU_NAME from T_SYS_MENUINFO a")
        .append("  LEFT JOIN T_SYS_YHQX b  on a.menu_qxcode = b.xtmkid ")
        .append("  WHERE b.Xtyhid = '"+xtyhid+"' and a.MENU_NAME LIKE '%审核%'");
	    return aReSQL;
	} 
	
	public static String  getFlowMode(String menuName,UserBean userBean){
		
		String xtyhid = userBean.getXTYHID();
	    String bmxxid = userBean.getBMXXID();
	    String xtjsids = userBean.getXTJSIDS();
	    String gzzxxids = userBean.getGZZXXIDS();
	    
	    StringBuffer sQL=new StringBuffer();
	    sQL.append("select c.MENU_ID,c.MENU_URL,c.MENU_NAME");
	    sQL.append(" from "+Consts.SYSSCHEMA+".T_SYS_FLOWINSTANCE a, "+Consts.SYSSCHEMA+".T_SYS_FLOWMODEL b  left join "+Consts.SYSSCHEMA+".T_SYS_MENUINFO c on  b.BUSINESSTYPE=c.BUSINESSTYPE where  a.flowmodelid=b.flowmodelid  and instanceState='进行' " );
	    sQL.append("and flowInstanceId in ");
	    sQL.append("(select FLOWINSTANCEID from "+Consts.SYSSCHEMA+".V_PROCESSNODE_INSTANCENODE where ");
	    sQL.append("(allFlag=0 and ((operationer='0' and operationerId='"+xtyhid);
	    sQL.append("') or (operationer='1' and operationerId ='"+bmxxid);
	    sQL.append("') or (operationer='2' and operationerId in ("+xtjsids);
	    sQL.append(")) or (operationer='3' and operationerId in ("+gzzxxids+"))))");
	    sQL.append(" or (allFlag=1 and instanceNodeId in (select INSTANCENODEID from ");
	    sQL.append(Consts.SYSSCHEMA+".T_SYS_INSTANCENODEOperation where operationUser='"+xtyhid+"' and operationFlag=0))) and c.MENU_NAME like '%"+menuName+"%'");
	    sQL.append(" order by c.MENU_ID ASC ,a.CORRELATION  ");
	    return sQL.toString();
	}
	
//	public static List getOperate(String MENU_NAME) {
//	    StringBuffer aReSQL=new StringBuffer();
//	    aReSQL.append(" select distinct a.CORRELATIONID,a.CORRELATION,a.PRIMARYKEY, b.BUSINESSTYPE,c.MENU_URL, c.MENU_NAME,c.MENU_ID,c.MENU_QXCODE ");
//	    aReSQL.append(" from xlm.T_SYS_FLOWINSTANCE a, xlm.T_SYS_FLOWMODEL b left join xlm.T_SYS_MENUINFO c on b.BUSINESSTYPE = c.BUSINESSTYPE" );
//	    aReSQL.append(" where a.flowmodelid = b.flowmodelid and instanceState = '进行'and c.MENU_NAME IS NOT NULL AND c.MENU_NAME='"+MENU_NAME+"'");
//	    Map params = new HashMap();
//		params.put("SelSQL", aReSQL.toString());
//		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
//        List list = logicUtilServiceImpl.selcomList(params);
//        return list;
//	} 
	
	/**
	 * 验证明细是否存在非标订单
	 * @param tableName 明细表名
	 * @param bussIdName 明细主表id名
	 * @param bussId 主表id
	 * @return
	 */
	public static boolean checkSpclOrder(String tableName,String bussIdName,String bussId){
		StringBuffer sql=new StringBuffer();
		if(StringUtil.isEmpty(tableName)){
			return false;
		}else if(StringUtil.isEmpty(bussIdName)){
			return false;
		}else if(StringUtil.isEmpty(bussId)){
			return false;
		}
		if(Consts.IS_NO_SPCL_FLAG.equals("1")){
			return true;
		}
		sql.append(" select count(1) CNT from ")
			.append(tableName).append(" a left join DRP_SPCL_TECH b on a.SPCL_TECH_ID=b.SPCL_TECH_ID and USE_FLAG=1 where ")
			.append(bussIdName)
			.append("='")
			.append(bussId)
			.append("' and a.DEL_FLAG=0 and b.SPCL_TECH_FLAG=2 ");
		TechorderManagerServiceImpl techorderManagerService = (TechorderManagerServiceImpl) SpringContextUtil.getBean("techorderManagerService");
		Map<String,String> param=new HashMap<String, String>();
		param.put("SELECTSQL", sql.toString());
		Map<String,Object> map=(Map<String, Object>) techorderManagerService.load("TechorderManager.selectSql", param);
		if(StringUtil.getInteger(map.get("CNT"))>0){
			return false;
		}
		return true;
	}
	
	public static List getAgencyWorkSql(UserBean userBean) {
		
		List<Map<String, String>> resList = new ArrayList();
		String[] list = {"装修申请单审核","装修报销申请单审核","专卖店转分销店申请单审核","专卖店撤店及终止申请单审核","门店精致化检查结果审核",
		        "拓展拜访表审核","门店拜访表审核","加盟商拜访表审核","月度拜访计划审核","新开/翻新门店评估单审核","门店变更申请单审核 ",
		        "工作计划审核","个人工作计划审核","推广费用申请单审核","推广费用报销单审核","渠道销售指标设定审核","区域销售指标设定审核",
		        "销售数据上报审核","退货申请单审核","加盟商终止合作申请审核","分销商购货月报审核","分销渠道信息登记审核","营销经理日报表审核"," 加盟商营销经理评价表审核"};
		for(int i=0;i<list.length;i++){
			if("装修申请单审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select 'BS2A01' MENU_ID, '装修申请单审核' MENU_NAME,'decorationapp.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from  DRP_CHANN_RNVTN u where 1=1 and u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0031701","BS0030101","CHANN_RNVTN_ID", "DRP_CHANN_RNVTN","DRP_CHANN_RNVTN_AUDIT","STATE",userBean));
                sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
                sql.append(" and "+qx.toString());
				p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("装修报销申请单审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select 'BS2A07' MENU_ID, '装修报销申请单审核' MENU_NAME,'/decorationreit.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from  DRP_RNVTN_REIT_REQ u where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0031801","BS0030301","RNVTN_REIT_REQ_ID", "DRP_RNVTN_REIT_REQ","DRP_RNVTN_REIT_REQ_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("专卖店转分销店申请单审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select 'BS2B02' MENU_ID, '专卖店转分销店申请单审核' MENU_NAME,'storetoretail.shtml?action=toFrame&module=sh' MENU_URL, count(0) NUM from  DRP_SPCL_STORE_TO_RETAIL_REQ u where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0031101","BS0030201","SPCL_STORE_TO_RETAIL_REQ_ID", "DRP_SPCL_STORE_TO_RETAIL_REQ","DRP_SPCL_STORE_TO_RETAIL_REQ_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			if("专卖店撤店及终止申请单审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select 'BS2B04' MENU_ID, '专卖店撤店及终止申请单审核' MENU_NAME,'storeclose.shtml?action=toFrame&module=sh' MENU_URL, count(0) NUM from  DRP_SPCL_STORE_CC_REQ u  where 1=1 AND u.DEL_FLAG=0 ");
                StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0030401","BS0030501","SPCL_STORE_CC_REQ_ID", "DRP_SPCL_STORE_CC_REQ","DRP_SPCL_STORE_CC_REQ_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
				p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("门店精致化检查结果审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select  'BS2D01' MENU_ID,'门店精致化检查结果审核' MENU_NAME,'termrefinecheck.shtml?action=toFrame&module=sh' MENU_URL, count(0) NUM from  TERM_REFINE_CHECK u where 1=1 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0032001","BS0031201","TERM_REFINE_CHECK_ID", "TERM_REFINE_CHECK","DRP_TERM_REFINE_CHECK_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
				p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("拓展拜访表审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select  'BS2F02' MENU_ID,'拓展拜访表审核' MENU_NAME,'expandvisit.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from  ERP_EXPAND_VISIT u where 1=1 AND u.DEL_FLAG=0");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0032801","BS0033101","EXPAND_VISIT_ID", "ERP_EXPAND_VISIT","ERP_EXPAND_VISIT_AUDIT","STATE",userBean));
				//sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("门店拜访表审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select  'BS2F04' MENU_ID,'门店拜访表审核' MENU_NAME,'storevisit.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from  ERP_STORE_VISIT u where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0033001","BS0033301","STORE_VISIT_ID", "ERP_STORE_VISIT","ERP_STORE_VISIT_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("加盟商拜访表审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select 'BS2F06' MENU_ID, '加盟商拜访表审核' MENU_NAME,'channvisit.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from  ERP_CHANN_VISIT u where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0032901","BS0033201","CHANN_VISIT_ID", "ERP_CHANN_VISIT","ERP_CHANN_VISIT_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                //sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("月度拜访计划审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select  'BS2F08' MENU_ID,'月度拜访计划审核' MENU_NAME,'monthVisit.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from  ERP_MONTH_VISIT_PLAN u where 1=1 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0033401","BS0033501","MONTH_VISIT_PLAN_ID", "ERP_MONTH_VISIT_PLAN","ERP_MONTH_VISIT_PLAN_AUDIT","STATE",userBean));
				//sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
                Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("新开/翻新门店评估单审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select  'BS2G02' MENU_ID,'新开/翻新门店评估单审核' MENU_NAME,'openterminal.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from ERP_OPEN_TERMINAL_REQ u where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0032101","BS0032201","OPEN_TERMINAL_REQ_ID", "ERP_OPEN_TERMINAL_REQ","ERP_OPEN_TERMINAL_REQ_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID_P IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("门店变更申请单审核 ".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select  'BS2G04' MENU_ID,'门店变更申请单审核' MENU_NAME,'terminalchange.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from BASE_TERMINAL_CHANGE u where 1=1 AND u.DEL_FLAG =0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0032301","BS0032401","TERMINAL_CHANGE_ID", "BASE_TERMINAL_CHANGE","BASE_TERMINAL_CHANGE_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID_P IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("工作计划审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select 'BS2H02' MENU_ID, '工作计划审核' MENU_NAME,'workplanmage.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from ERP_WORK_PLAN u where 1=1 AND u.DEL_FLAG=0");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0032501","BS0032601","WORK_PLAN_ID", "ERP_WORK_PLAN","ERP_WORK_PLAN_AUDIT","STATE",userBean));
				//sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("个人工作计划审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select  'BS2H04' MENU_ID,'个人计划审核' MENU_NAME,'grgzjh.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from ERP_PER_WORK_PLAN  u  where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0033601","BS0033701","PER_WORK_PLAN_ID", "ERP_PER_WORK_PLAN","ERP_PER_WORK_PLAN_AUDIT","STATE",userBean));
				//sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("推广费用申请单审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select  'BS3A01' MENU_ID,'推广费用申请单审核' MENU_NAME,'promoexpen.shtml?action=toFrame&module=sh' MENU_URL, count(0) NUM from ERP_PRMT_COST_REQ u where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0020901","BS0020601","PRMT_COST_REQ_ID", "ERP_PRMT_COST_REQ","ERP_PRMT_COST_REQ_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			if("推广费用报销单审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select  'BS1I04' MENU_ID,'推广费用报销单审核' MENU_NAME,'expenseorder.shtml?action=toFrame&module=sh' MENU_URL, count(0) NUM from ERP_EXPENSE_ORDER u where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0022801","BS0023001","EXPENSE_ORDER_ID", "ERP_EXPENSE_ORDER","ERP_EXPENSE_ORDER_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("渠道销售指标设定审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select  'BS1F02' MENU_ID,'渠道销售指标设定审核' MENU_NAME,'saleplan.shtml?action=toFrame&module=sh' MENU_URL, count(0) NUM from ERP_SALE_PLAN u where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0021301","BS0021401","SALE_PLAN_ID", "ERP_SALE_PLAN","ERP_SALE_PLAN_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("区域销售指标设定审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select  'BS1F04' MENU_ID,'区域销售指标设定审核' MENU_NAME,'areasaleplan.shtml?action=toFrame&module=sh' MENU_URL, count(0) NUM from ERP_AREA_SALE_PLAN  u where 1=1 AND u.DEL_FLAG=0");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0021801","BS0021901","AREA_SALE_PLAN_ID", "ERP_AREA_SALE_PLAN","ERP_AREA_SALE_PLAN_AUDIT","STATE",userBean));
				//sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());  
				sql.append(" and "+qx.toString());
				p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("销售数据上报审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select  'BS1G02' MENU_ID,'销售数据上报审核' MENU_NAME,'saledateup.shtml?action=toFrame&module=sh' MENU_URL, count(0) NUM from ERP_SALE_DATE_UP u where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0022701","BS0021701","SALE_DATE_UP_ID", "ERP_SALE_DATE_UP","DRP_SALE_DATE_UP_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
                p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			if("退货申请单审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select 'BS0H02' MENU_ID,'退货申请单审核' MENU_NAME, 'prdreturnreq.shtml?action=toFrame&module=sh' MENU_URL, count(0) NUM from DRP_PRD_RETURN_REQ u where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","FX0020701","BS0012101","PRD_RETURN_REQ_ID", "DRP_PRD_RETURN_REQ","DRP_PRD_RETURN_REQ_AUDIT","STATE",userBean));
				sql.append(" and RETURN_CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
				p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("加盟商终止合作申请审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select 'BS1M02' MENU_ID,'加盟商终止合作申请审核' MENU_NAME, 'distributerEndReq.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from ERP_DISTRIBUTOR_END_REQ u where 1=1 and u.DEL_FLAG=0");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0034801","BS0034901","CHANN_END_REQ_ID", "ERP_DISTRIBUTOR_END_REQ","ERP_CHANN_END_REQ_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
				p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("分销商购货月报审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select 'BS1K04' MENU_ID,'分销商购货月报审核' MENU_NAME, 'distributerSalerpt.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from ERP_DISTRIBUTOR_SALE_RPT u where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0034401","BS0035101","DISTRIBUTOR_SALE_RPT_ID", "ERP_DISTRIBUTOR_SALE_RPT","ERP_DISTRIBUTOR_SALE_RPT_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
				p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("分销渠道信息登记审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select 'BS1K02' MENU_ID,'分销渠道信息登记审核' MENU_NAME, 'distributerReq.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from ERP_DISTRIBUTOR_REQ u where 1=1 AND u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0034201","BS0034301","DISTRIBUTOR_REQ_ID", "ERP_DISTRIBUTOR_REQ","ERP_DISTRIBUTOR_REQ_AUDIT","STATE",userBean));
				sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
				p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("营销经理日报表审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select 'BS1J02' MENU_ID,'营销经理日报表审核' MENU_NAME, 'mkmdayreport.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from DRP_MKM_DAY_REPORT u where 1=1 and u.DEL_FLAG=0 ");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","BS0034601","BS0034701","MKM_DAY_RPT_ID", "DRP_MKM_DAY_REPORT","DRP_MKM_DAY_REPORT_AUDIT","STATE",userBean));
				//sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
				p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
			
			if("加盟商营销经理评价表审核".equals(list[i].toString())){
				Map<String,String> p = new HashMap<String,String>();
				StringBuffer sql = new StringBuffer();
				sql.append("select 'BS1J04' MENU_ID,'加盟商营销经理评价表审核' MENU_NAME, 'channscoremkm.shtml?action=toFrames&module=sh' MENU_URL, count(0) NUM from DRP_CHANN_SCORE_MKM u where 1=1 AND u.DEL_FLAG=0");
				StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(null,"sh","FX0060301","BS0034501","CHANN_SCORE_MKM_ID", "DRP_CHANN_SCORE_MKM","DRP_CHANN_SCORE_MKM_AUDIT","STATE",userBean));
				//sql.append(" and CHANN_ID IN "+userBean.getCHANNS_CHARG());
				sql.append(" and "+qx.toString());
				p.put("SelSQL", sql.toString());
				Map rstMap=InterUtil.selcom(p);
			    if(!"0".equals(StringUtil.nullToSring(rstMap.get("NUM"))))
				{
					 resList.add(rstMap); 
				}
			}
		}
		return resList;
	}
	
	/**
	 * 更新对账单
	 * @param CHANN_NO
	 * @param jsonData
	 * @return
	 */
	public static String txUpdatePayMentDetail(String CHANN_NO,String MonthStartDate,String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		logicUtilServiceImpl.txUpdatePayMentDetail(CHANN_NO, MonthStartDate,jsonData);
		return ""; 
	}
	
	
	/**
	 * 
	 * 查询门店导购员信息
	 * @param messageDate
	 * @return
	 */
	public static String queryTermSales(String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		try{
			result =  logicUtilServiceImpl.queryTermSales(jsonData);
		}catch(Exception e){
			result = new InterReturnMsg();
			result.setFLAG("FAIL");
			result.setMESSAGE(e.getMessage());
		}
		String returnJson = "";
		Map<String,String> rspMap = new HashMap<String,String>();
		if(BusinessConsts.SUCCESS.equals(result.getFLAG())){
			returnJson = new Gson().toJson(result.getList());
		}else{
			rspMap.put("SUCESS","FAIL");
			String messge = result.getMESSAGE();
			rspMap.put("MSG", messge);
			returnJson = new Gson().toJson(rspMap);
		}
		return returnJson ; 
	}
	
	
	/**
	 * 
	 * 订单接入
	 * @param messageDate
	 * @return
	 */
	public static String txInsAdvcOrder(String jsonData){
		LogicUtilServiceImpl logicUtilServiceImpl = (LogicUtilServiceImpl) SpringContextUtil.getBean("logicUtilService");
		InterReturnMsg result = null;
		try{
			 result = logicUtilServiceImpl.txInsAdvcOrder(jsonData);
		}catch(Exception e){
			result = new InterReturnMsg();
			result.setFLAG("FAIL");
			result.setMESSAGE(e.getMessage());
		}
	  
		String returnJson = "";
		Map<String,String> rspMap = new HashMap<String,String>();
		if(BusinessConsts.SUCCESS.equals(result.getFLAG())){
			rspMap.put("SUCESS","OK");
			rspMap.put("MSG", "保存成功");
			rspMap.put("ADVC_ORDER_NO", result.getMESSAGE());
			returnJson = new Gson().toJson(rspMap);
		}else{
			rspMap.put("SUCESS","FAIL");
			String messge = result.getMESSAGE();
			rspMap.put("MSG", messge);
			returnJson = new Gson().toJson(rspMap);
		}
		return returnJson ; 
	}
	
	public  void autoDoMonthAcct(String vCHANN_ID, String  vYEAR ,String vMONTH ){
		try {
			Map params=new HashMap();
			params.put("LEDGER_ID", vCHANN_ID);
			params.put("YEAR", vYEAR);
			params.put("MONTH", vMONTH);
		    String preYearMonth[]=preYearMonth(vYEAR,vMONTH);	
		    params.put("PRE_YEAR", preYearMonth[0]);
			params.put("PRE_MONTH", preYearMonth[1]);
			params.put("STATE", "已月结");
			ActServiceImpl actServiceImpl  = (ActServiceImpl) SpringContextUtil.getBean("actService"); 
			Map channMap=actServiceImpl.getChannInfo(params);
			if(channMap!=null)
			{
				String init_year=StringUtil.nullToSring(channMap.get("INIT_YEAR"));
				String init_month=StringUtil.nullToSring(channMap.get("INIT_MONTH"));
				if(StringUtil.isEmpty(init_year)||StringUtil.isEmpty(init_month))
				{
					actLog( "直营办自动月结:"+vCHANN_ID+"年份："+vYEAR+"月份："+vMONTH,  "自动月结",  "system","失败",  "渠道没有维护初始化年份、月份信息,请在渠道参数设置页面!", "DM", vCHANN_ID, vYEAR,vMONTH);
					
				}else
				{
					Map chkMap=actServiceImpl.chkAccount(params);
                    if(Integer.parseInt(init_year+init_month)>=Integer.parseInt(vYEAR+vMONTH))
					{
						actLog( "直营办自动月结:"+vCHANN_ID+"年份："+vYEAR+"月份："+vMONTH,  "自动月结",  "system","失败",  "您选择的年份、月份必须在:"+init_year+init_month+"之后！", "DM", vCHANN_ID, vYEAR,vMONTH);
					}
					else if(init_year.equals(preYearMonth[0])&&init_month.equals(preYearMonth[1]))
					{
					    if("1".equals(chkMap.get("THE_NUM").toString()))
						{
							actLog( "直营办自动月结:"+vCHANN_ID+"年份："+vYEAR+"月份："+vMONTH,  "自动月结",  "system","失败",  "当前月份已经月结!", "DM", vCHANN_ID, vYEAR,vMONTH);
						}else
						{
							actLog( "直营办自动月结:"+vCHANN_ID+"年份："+vYEAR+"月份："+vMONTH,  "自动月结",  "system","成功",  "月份成功!", "DM", vCHANN_ID, vYEAR,vMONTH);
							
						}	
						
					}else
					{
						if(!"1".equals(chkMap.get("PRE_NUM").toString()))
						{
							actLog( "直营办自动月结:"+vCHANN_ID+"年份："+vYEAR+"月份："+vMONTH,  "自动月结",  "system","失败",  "请先月结上月份!", "DM", vCHANN_ID, vYEAR,vMONTH);
						}else if("1".equals(chkMap.get("THE_NUM").toString()))
						{
							actLog( "直营办自动月结:"+vCHANN_ID+"年份："+vYEAR+"月份："+vMONTH,  "自动月结",  "system","失败",  "当前月份已经月结!", "DM", vCHANN_ID, vYEAR,vMONTH);
						}else
						{
							autoDoMonthAcctAction( vCHANN_ID,   vYEAR , vMONTH , actServiceImpl);
							actLog( "直营办自动月结:"+vCHANN_ID+"年份："+vYEAR+"月份："+vMONTH,  "自动月结",  "system","成功",  "月份成功!", "DM", vCHANN_ID, vYEAR,vMONTH);
						}	
						
					}
					
				}
					
			}else
			{
				actLog( "直营办自动月结:"+vCHANN_ID+"年份："+vYEAR+"月份："+vMONTH,  "自动月结",  "system","失败",  "找不到对应的渠道信息!", "DM", vCHANN_ID, vYEAR,vMONTH);
				
			}
			} catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	public  void autoDoMonthAcctAction(String vCHANN_ID, String  vYEAR ,String vMONTH ,ActServiceImpl actServiceImpl){
		Map params=new HashMap();
		params.put("LEDGER_ID", vCHANN_ID);
		params.put("YEAR", vYEAR);
		params.put("MONTH", vMONTH);
		params.put("YEAR_MONTH", vYEAR+"/"+vMONTH);
		String preYearMonth[]=preYearMonth(vYEAR,vMONTH);	
		params.put("PRE_YEAR", preYearMonth[0]);
		params.put("PRE_MONTH", preYearMonth[1]);
		params.put("UPD_NAME","system");
		Map chkMap=actServiceImpl.chkNotice(params);
		params.put("IS_NOTICE_FLAG",chkMap.get("IS_NOTICE_FLAG"));
		
		//获得成本的计算方式
		Map channMap=actServiceImpl.getChannInfo(params);
		if(channMap!=null)
		{
			params.put("COST_TYPE",channMap.get("COST_TYPE"));
		}
		  actServiceImpl.txDoAccount(params);
	
	}
	
	
	/**
	 * 获得后一个月份
	 * 
	 * @return ActionForward
	 */
	public String[] preYearMonth (String year,String month) {
		String [] year_Month=new String [2];
		if(month.equals("12"))
		{
			year_Month[0]=year;
			year_Month[1]="11";
		}else if(month.equals("11"))
		{
			year_Month[0]=year;
			year_Month[1]="10";
			
		}else if(month.equals("10"))
		{
			year_Month[0]=year;
			year_Month[1]="09";
			
		}else if(month.equals("09"))
		{
			year_Month[0]=year;
			year_Month[1]="08";
			
		}else if(month.equals("08"))
		{
			year_Month[0]=year;
			year_Month[1]="07";
			
		}else if(month.equals("08"))
		{
			year_Month[0]=year;
			year_Month[1]="07";
			
		}else if(month.equals("07"))
		{
			year_Month[0]=year;
			year_Month[1]="06";
			
		}else if(month.equals("06"))
		{
			year_Month[0]=year;
			year_Month[1]="05";
			
		}else if(month.equals("05"))
		{
			year_Month[0]=year;
			year_Month[1]="04";
			
		}else if(month.equals("04"))
		{
			year_Month[0]=year;
			year_Month[1]="03";
			
		}else if(month.equals("03"))
		{
			year_Month[0]=year;
			year_Month[1]="02";
			
		}else if(month.equals("02"))
		{
			year_Month[0]=year;
			year_Month[1]="01";
			
		}else if(month.equals("01"))
		{
			year_Month[0]=String.valueOf(Integer.parseInt(year)-1);
			year_Month[1]="12";
			
		}
		return year_Month;
	}
	
	
	/**
	 * 
	 * MDM接口
	 * 
	 */
	
	
	/**新增品牌信息
	 * @param BRAND_LIST
	 * @return ReturnInterInfo
	 * @throws Exception
	 */
	public static ReturnInterInfo insNewBrand(InterBrandModel[] BRAND_LIST){
		logger.info("MDM参数调用接口:新增品牌信息");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo result = newInterImpl.txInsNewBrand(BRAND_LIST);
		return result;
	}
	
	/**修改品牌信息
	 * @param BRAND_LIST
	 * @return ReturnInterInfo
	 * @throws Exception
	 */
	public static ReturnInterInfo upNewBrand(InterBrandModel[] BRAND_LIST){
		logger.info("MDM参数调用接口:修改品牌信息");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo result = newInterImpl.txUpNewBrand(BRAND_LIST);
		return result;
	}
	
	/**新增渠道信息
	 * @param CHANN_LIST
	 * @return ReturnInterInfo
	 * @throws Exception
	 */
	public static ReturnInterInfo insNewChann(InterChannModel[] CHANN_LIST){
		logger.info("MDM参数调用接口:新增渠道信息");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo result = newInterImpl.txInsNewChann(CHANN_LIST);
		return result;
	}
	
	

	/**修改渠道信息
	  * @param CHANN_LIST
	 * @return ReturnInterInfo
	 * @throws Exception
	 */
	public static ReturnInterInfo upNewChann(InterChannModel[] CHANN_LIST){
		logger.info("MDM参数调用接口:修改渠道信息");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo	result = newInterImpl.txUpNewChann(CHANN_LIST);
		return result;
	}
	
	
	/**新增用户信息
	 * @param USER_LIST
	 * @return ReturnInterInfo
	 * @throws Exception
	 */
	public static ReturnInterInfo insNewUserInfo(InterUserModel[] USER_LIST){
		logger.info("MDM参数调用接口:新增用户信息");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo	result = newInterImpl.txInsNewUserInfo(USER_LIST);
		return result;
	}
	
	
	/**修改用户信息
	  * @param USER_LIST
	 * @return ReturnInterInfo
	 * @throws Exception
	 */
	public static ReturnInterInfo upNewUserInfo(InterUserModel[] USER_LIST){
		logger.info("MDM参数调用接口:修改用户信息");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo	result = newInterImpl.txUpNewUserInfo(USER_LIST);
		return result;
	}
	
	/**新增物料信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static ReturnInterInfo insNewProduct(InterProductModel[] PRODUCT_LIST){
		logger.info("MDM参数调用接口:新增物料信息");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo	result = newInterImpl.txInsNewProduct(PRODUCT_LIST);
		return result;
	}
	
	/**修改物料信息
	 * @param messageDate
	 * @return
	 * @throws Exception
	 */
	public static ReturnInterInfo upNewProduct(InterProductModel[] PRODUCT_LIST){
		logger.info("MDM参数调用接口:修改物料信息");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo	result = newInterImpl.txUpNewProduct(PRODUCT_LIST);
		return result;
	}
	
	/**新增组织信息
	 * @param BRANCH_LIST
	 * @return
	 * @throws Exception
	 */
	public static ReturnInterInfo insNewBranch(InterBranchModel[] BRANCH_LIST){
		logger.info("MDM参数调用接口:新增组织信息");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo	result = newInterImpl.txInsNewBranch(BRANCH_LIST);
		return result;
	}
	
	/**修改组织信息
	 * @param CHANN_LIST
	 * @return
	 * @throws Exception
	 */
	public static ReturnInterInfo upNewBranch(InterBranchModel[] BRANCH_LIST){
		logger.info("MDM参数调用接口:修改组织信息");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo	result = newInterImpl.txUpNewBranch(BRANCH_LIST);
		return result;
	}
	
	/**新增员工信息
	 * @param EMPLOYEE_LIST
	 * @return
	 * @throws Exception
	 */
	public static ReturnInterInfo insNewEmployee(InterEmployeeModel[] EMPLOYEE_LIST){
		logger.info("MDM参数调用接口:新增员工信息");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo	result = newInterImpl.txInsNewEmployee(EMPLOYEE_LIST);
		return result;
	}
	
	/**修改员工信息
	 * @param EMPLOYEE_LIST
	 * @return
	 * @throws Exception
	 */
	public static ReturnInterInfo upNewEmployee(InterEmployeeModel[] EMPLOYEE_LIST){
		logger.info("MDM参数调用接口:修改员工信息");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo	result = newInterImpl.txUpNewEmployee(EMPLOYEE_LIST);
		return result;
	}
	
	/**新增微信订单
	 * @param EMPLOYEE_LIST
	 * @return
	 * @throws Exception
	 */
	public static ReturnInterInfo addAdvcOrderForWeChat(AdvcOrderForWeChatModel AdvcOrderModel){
		logger.info("MDM参数调用接口:新增微信预订单");
		NewInterImpl newInterImpl = (NewInterImpl) SpringContextUtil.getBean("newInterImpl");
		ReturnInterInfo	result=new ReturnInterInfo();
		try {
			result = newInterImpl.txAddAdvcOrderForWeChat(AdvcOrderModel);
			return result;
		} catch (ServiceException s) {
			LogicUtil.actLog("微信订单", "新增微信预订单", "MDM", "失败", s.getMessage(),
					"", "", "");
			result.setMessageType(BusinessConsts.N_INTER_FALSE);
			result.setMessageText(s.getMessage());
			return result;
		}
	}
}
