package com.hoperun.sys.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.filter.MySessionContext;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.util.MessTimerTask;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.util.TimeComm;
import com.hoperun.commons.util.security.MD5Encrypt;
import com.hoperun.sys.model.MenuHelper;
import com.hoperun.sys.model.MessageModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.model.ZTWHModel;
import com.hoperun.sys.service.SysLoginService;

// TODO: Auto-generated Javadoc
/**
 * The Class SysLoginAction.
 * 
 * @module 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class SysLoginAction extends BaseAction {
	
	/** The logger. */
	
	private Logger logger = Logger.getLogger(SysLoginAction.class);
	
	/** The sys login service. */
	private SysLoginService sysLoginService;
    /**
	 * Auth login.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward authLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String YHM = StringUtil.nullToSring(request.getParameter("S_NAME")).trim();
		String KL = request.getParameter("S_PWD");
		String ZTXXID = request.getParameter("S_ZTID");
		String ZTMC = request.getParameter("S_ZTMC");
        String USERCSS = request.getParameter("userCss");
        
        if(StringUtil.isEmpty(YHM)){
        	YHM = (String)request.getAttribute("S_NAME");
        }
        if(StringUtil.isEmpty(KL)){
        	KL = (String)request.getAttribute("S_PWD");
        }
        if(StringUtil.isEmpty(ZTXXID)){
        	ZTXXID = (String)request.getAttribute("S_ZTID");
        }
        if(StringUtil.isEmpty(ZTMC)){
        	ZTMC = (String)request.getAttribute("S_ZTMC");
        }
        if(StringUtil.isEmpty(USERCSS)){
        	USERCSS = (String)request.getAttribute("userCss");
        }
        
        
        
        //LogicUtil.actLog("系统管理","登录","admin","成功","这是一条测试'数据，'阿基多'");
         if(StringUtil.isEmpty(YHM)||StringUtil.isEmpty(KL))
        {
        	request.setAttribute("msg", "登录失败,用户名或者密码为空！");
			return mapping.findForward(FAILURE);
        }
         
		/**
		 * 如果系统无帐套，则取默认帐套信息 ,如果信息配置不全提示错误
		 * 
		 * 范春锋 20120323 说明：如果无帐套系统需配置conf.properties文件 ACCOUNT_DISPLAY=false 无帐套
		 * ACCOUNT_CODE=1001 默认帐套编号 ACCOUNT_DISPLAY_NAME=BSD 默认帐套名称
		 */
		String isAccount = Consts.ACCOUNT_DISPLAY;
		if (!StringUtil.isEmpty(isAccount)
				&& "false".equals(isAccount)) {
			ZTXXID = Consts.ACCOUNT_CODE;
			ZTMC = Consts.ACCOUNT_DISPLAY_NAME;
			// 如果配置错误提示
			if (StringUtil.isEmpty(ZTXXID) || StringUtil.isEmpty(ZTMC)) {
				request.setAttribute("msg", "登录失败,系统初始化配置错误！");
				return mapping.findForward(FAILURE);
			}
		} else {
			if (StringUtil.isEmpty(ZTXXID)) {
				request.setAttribute("msg", "登录失败,当前用户没有分管任何帐套！");
				return mapping.findForward(FAILURE);

			}
		}
		try {
			String startTime = TimeComm.getTimeStamp("");
			UserBean aUserBean = null;
            System.err.println("开始登陆!!");
			try {
				// 这里需求修改
				// int switchInt = 0;
				aUserBean = initQXComm(YHM, KL, ZTXXID);

			} catch (Exception ex) {
				logger.error(ex);
				request.setAttribute("msg", "登陆失败，程序出错！");
				return mapping.findForward(FAILURE);

			}
			if (aUserBean == null) {
				request.setAttribute("msg", "登陆失败，用户名或密码不正确！");
				return mapping.findForward(FAILURE);
			}
			aUserBean.setLoginZTXXID(ZTXXID);
			aUserBean.setLoginZTMC(ZTMC);
			System.err.println("userBean\u521D\u6B65\u7EC4\u5EFA\u5B8C\u6BD5");
			// 记录登陆日志
			String fromIP = request.getRemoteAddr();
			sysLoginService.doLog(aUserBean, fromIP);
			Map is_Drp_LedgerMap = sysLoginService.getIS_DRP_LEDGER(aUserBean.getLoginZTXXID());
		    aUserBean.setIS_DRP_LEDGER(StringUtil.nullToSring(is_Drp_LedgerMap.get("IS_DRP_LEDGER")));
			
			
			
			
			//初始化总部和分销商信息
			Map baseChann = sysLoginService.getBaseChann();
			if(baseChann!=null)
			{
				aUserBean.setBASE_CHANN_ID(StringUtil.nullToSring(baseChann.get("CHANN_ID")));
				aUserBean.setBASE_CHANN_NAME(StringUtil.nullToSring(baseChann.get("CHANN_NAME")));
				aUserBean.setBASE_CHANN_NO(StringUtil.nullToSring(baseChann.get("CHANN_NO")));
			}
			Map<String,String> currChann = sysLoginService.getcurrChann(aUserBean.getLoginZTXXID());
			if(currChann!=null)
			{
				aUserBean.setCHANN_ID(StringUtil.nullToSring(currChann.get("CHANN_ID")));
				aUserBean.setCHANN_NAME(StringUtil.nullToSring(currChann.get("CHANN_NAME")));
				aUserBean.setCHANN_NO(StringUtil.nullToSring(currChann.get("CHANN_NO")));
				aUserBean.setCHANN_TYPE(StringUtil.nullToSring(currChann.get("CHANN_TYPE")));
				
				//add by zzb 区域服务中心 2014-6-6
				aUserBean.setAREA_SER_ID(StringUtil.nullToSring(currChann.get("AREA_SER_ID")));
				aUserBean.setAREA_SER_NO(StringUtil.nullToSring(currChann.get("AREA_SER_NO")));
				aUserBean.setAREA_SER_NAME(StringUtil.nullToSring(currChann.get("AREA_SER_NAME")));
			}
			String channCharg="(select CHANN_ID  from BASE_USER_CHANN_CHRG where  USER_ID='"+aUserBean.getXTYHID()+"')"; 
			String channChargAll="(select CHANN_ID  from BASE_CHANN )"; 
			String areaCharg="(select AREA_ID  from BASE_AREA_CHRG_FLAT where  CHRG_PSON_ID='"+aUserBean.getXTYHID()+"')";
			if("1".equals(aUserBean.getCHANNS_CHARG()))
			{
				aUserBean.setCHANNS_CHARG(channChargAll);	
			}else
			{
               aUserBean.setCHANNS_CHARG(channCharg);	
			}	
			aUserBean.setAREA_CHARG(areaCharg);
			Map<String,String> currTrem = sysLoginService.getCurrTrem(aUserBean.getBMBH());
			if(currTrem!=null)
			{
			aUserBean.setTERM_ID(StringUtil.nullToSring(currTrem.get("TERM_ID")));
			aUserBean.setTERM_NO(StringUtil.nullToSring(currTrem.get("TERM_NO")));
			aUserBean.setTERM_NAME(StringUtil.nullToSring(currTrem.get("TERM_NAME")));
			}
			if(!"1".equals(aUserBean.getIS_DRP_LEDGER()))
			{
				String tremCharg="(select TERM_ID  from BASE_TERMINAL where  1=1)"; 
				aUserBean.setTERM_CHARGE(tremCharg);	
			}else if("1".equals(aUserBean.getIS_DRP_LEDGER())&&!"1".equals(aUserBean.getYHLB()))
			{
				String tremCharg="(select TERM_ID  from BASE_TERMINAL where  TERM_ID='"+aUserBean.getLoginZTXXID()+"' or CHANN_ID_P='"+aUserBean.getLoginZTXXID()+"')"; 
				aUserBean.setTERM_CHARGE(tremCharg);
			}else
			{
				String tremCharg="(select TERM_ID  from BASE_TERMINAL where  TERM_ID='"+aUserBean.getBMXXID()+"' or CHANN_ID_P='"+aUserBean.getBMXXID()+"')"; 
				aUserBean.setTERM_CHARGE(tremCharg);
			}	
			
			
			//设置报表和图片服务器的当前地址
			String webUrl = request.getRequestURL().toString();
			if(-1 != webUrl.indexOf(Consts.APP_SERVER_URL_NET)){
				aUserBean.setCURRT_PIC_URL(Consts.PIC_SERVER_URL_NET);
			}else{
				aUserBean.setCURRT_PIC_URL(Consts.PIC_SERVER_URL);
			}
			//报表地址
			if(-1 != webUrl.indexOf(Consts.RUNQIAN_REPORT_URL_NET)){
				aUserBean.setCURRT_RPT_URL(Consts.RUNQIAN_REPORT_URL_NET);
			}else{
				aUserBean.setCURRT_RPT_URL(Consts.RUNQIAN_REPORT_URL);
			}
			
			
			// 组织个人权限
			Map qxMap = sysLoginService.getUserQXINFO(aUserBean);
			aUserBean.setQXMap(qxMap);
			// 组织模块权限级别对象
			Map<String, String> qxjbMap = sysLoginService.getUserQXJB(aUserBean);
			aUserBean.setQXJBMap(qxjbMap);
			// 设置session失效时间和session切换
			HttpSession session = request.getSession(true);
			if(null!=session){
              session.invalidate();
            }
            session = request.getSession(true);
            System.err.println("session\u5931\u6548\u65F6\u95F4:="+ (int) (0.5D * 3600D));
			session.setMaxInactiveInterval((int) (0.5D * 3600D));
			session.removeAttribute("UserCSS");
			session.removeAttribute("UserBean");
			aUserBean.setRealYHKL(KL);
			// 设置用户样式
			aUserBean.setUSERSTYLE(USERCSS);
			// 创建小数位参数对象
			aUserBean.setXswModelList(sysLoginService.getXSWModel());
			session.setAttribute("UserBean_MD5KL", KL);
			session.setAttribute("UserBean", aUserBean);
			session.setAttribute("UserCSS", USERCSS);
			String firstPage = request.getParameter("firstPage");
			session.setAttribute("firstPage", firstPage);
			session.setAttribute("query_user_name", System.currentTimeMillis()
					+ aUserBean.getYHM());
			System.err.println("\u7CFB\u7EDF\u5F00\u59CB\u65F6\u95F4="
					+ startTime + ", \u7CFB\u7EDF\u7ED3\u675F\u65F6\u95F4="
					+ TimeComm.getTimeStamp(""));

			// 加载用户菜单
			// List usrMenus = sysLoginService.findAllMenus();
			List usrMenus = new ArrayList();
			if ("administrator".equals(aUserBean.getYHM())) {
				usrMenus = sysLoginService.findAllMenus();
			} else {
				usrMenus = sysLoginService.findMenuByUserId(aUserBean.getXTYHID(),aUserBean.getIS_DRP_LEDGER());

			}
			if (usrMenus == null || usrMenus.isEmpty()) {
				request.setAttribute("msg", "您未被授权，请检查角色菜单配置！");
				return mapping.findForward(FAILURE);
			}
			MenuHelper menuHelper = new MenuHelper(usrMenus);
			String Str = menuHelper.getJsonData4MenuTree();
			request.setAttribute("treeData", Str);
			// 获取主角色
			// 设置系统首页
			setIndexUrl(request, YHM);
			// 处理同一帐号同一时刻只允许登录一个的问题 范春锋 20120323
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("YHM", YHM);
			paraMap.put("invalidateFlag", request.getParameter("invalidateFlag"));
			proSession(request, paraMap);
            //add by zzb  首页显示消息数目
			List<MessageModel> oldModelList = MessTimerTask.getReturnList();
			if(null != oldModelList && oldModelList.size() > 0){
				int size = oldModelList.size();
				String yhid = aUserBean.getXTYHID();
				int messageSize = 0;
				for(int i=0;i<size;i++){
					MessageModel oldModel = oldModelList.get(i);
					String RECEIVEID  = oldModel.getRECEIVEID();
					if(yhid.equals(RECEIVEID)){
						messageSize ++;
					}
					if(messageSize == 500){//每个人只能看前500条消息
						break;
					}
				} 
				request.setAttribute("messageSize", messageSize);
			}
			 
			return mapping.findForward("index");
		} catch (Exception ex) {
			logger.error(ex);
			System.err.println("LoginServlet in Error:" + ex.toString());
			request.setAttribute("msg", "登陆失败，程序出错！");
			return mapping.findForward(FAILURE);
		}
	}

	/**
	 * simpleLogin
	 *  add by zhuxw 2014-06-19
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward simpleLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String YHM = StringUtil.nullToSring(request.getParameter("user")).trim();
		String KL = StringUtil.nullToSring(request.getParameter("pwd"));
		String ZTXXID = "";
		String ZTMC = "";
		if(StringUtil.isEmpty(KL)||StringUtil.isEmpty(KL))
		{
			request.setAttribute("msg", "登录失败,用户名或者密码不正确！");
			return mapping.findForward(FAILURE);
		}else
		{
			KL=MD5Encrypt.MD5(KL);
			Map userInfo=sysLoginService.getUserInfoByUserIdAndPd(YHM,KL);
			if(userInfo!=null)
		    {
		    	 ZTXXID = StringUtil.nullToSring(userInfo.get("ZTXXID"));
				 ZTMC = StringUtil.nullToSring(userInfo.get("ZTMC"));
		    }
		}
		if(StringUtil.isEmpty(ZTXXID)||StringUtil.isEmpty(ZTMC))
		{
			request.setAttribute("msg", "登录失败,用户名或者密码不正确！");
			return mapping.findForward(FAILURE);
		}
		
		String USERCSS = Consts.DEFAULT_CSS;
        
     	/**
		 * 如果无帐套系统需配置conf.properties文件 ACCOUNT_DISPLAY=false 无帐套
		 * ACCOUNT_CODE=1001 默认帐套编号 ACCOUNT_DISPLAY_NAME=BSD 默认帐套名称
		 */
		String isAccount = Consts.ACCOUNT_DISPLAY;
		if (!StringUtil.isEmpty(isAccount)
				&& "false".equals(isAccount)) {
			ZTXXID = Consts.ACCOUNT_CODE;
			ZTMC = Consts.ACCOUNT_DISPLAY_NAME;
			// 如果配置错误提示
			if (StringUtil.isEmpty(ZTXXID) || StringUtil.isEmpty(ZTMC)) {
				request.setAttribute("msg", "登录失败,系统初始化配置错误！");
				return mapping.findForward(FAILURE);
			}
		} else {
			if (StringUtil.isEmpty(ZTXXID)) {
				request.setAttribute("msg", "登录失败,当前用户没有分管任何帐套！");
				return mapping.findForward(FAILURE);

			}
		}
		try {
			String startTime = TimeComm.getTimeStamp("");
			UserBean aUserBean = null;
            System.err.println("开始登陆!!");
			try {
				// 这里需求修改
				// int switchInt = 0;
				aUserBean = initQXComm(YHM, KL, ZTXXID);

			} catch (Exception ex) {
				logger.error(ex);
				request.setAttribute("msg", "登陆失败，程序出错！");
				return mapping.findForward(FAILURE);

			}
			if (aUserBean == null) {
				request.setAttribute("msg", "登陆失败，用户名或密码不正确！");
				return mapping.findForward(FAILURE);
			}
			aUserBean.setLoginZTXXID(ZTXXID);
			aUserBean.setLoginZTMC(ZTMC);
			Map is_Drp_LedgerMap = sysLoginService.getIS_DRP_LEDGER(aUserBean.getLoginZTXXID());
		    aUserBean.setIS_DRP_LEDGER(StringUtil.nullToSring(is_Drp_LedgerMap.get("IS_DRP_LEDGER")));
		    
		    
			System.err.println("userBean\u521D\u6B65\u7EC4\u5EFA\u5B8C\u6BD5");
			// 记录登陆日志
			String fromIP = request.getRemoteAddr();
			sysLoginService.doLog(aUserBean, fromIP);
			//初始化总部和分销商信息
			Map baseChann = sysLoginService.getBaseChann();
			if(baseChann!=null)
			{
				aUserBean.setBASE_CHANN_ID(StringUtil.nullToSring(baseChann.get("CHANN_ID")));
				aUserBean.setBASE_CHANN_NAME(StringUtil.nullToSring(baseChann.get("CHANN_NAME")));
				aUserBean.setBASE_CHANN_NO(StringUtil.nullToSring(baseChann.get("CHANN_NO")));
			}
			Map<String,String> currChann = sysLoginService.getcurrChann(aUserBean.getLoginZTXXID());
			if(currChann!=null)
			{
				aUserBean.setCHANN_ID(StringUtil.nullToSring(currChann.get("CHANN_ID")));
				aUserBean.setCHANN_NAME(StringUtil.nullToSring(currChann.get("CHANN_NAME")));
				aUserBean.setCHANN_NO(StringUtil.nullToSring(currChann.get("CHANN_NO")));
				//add by zzb 区域服务中心 2014-6-6
				aUserBean.setAREA_SER_ID(StringUtil.nullToSring(currChann.get("AREA_SER_ID")));
				aUserBean.setAREA_SER_NO(StringUtil.nullToSring(currChann.get("AREA_SER_NO")));
				aUserBean.setAREA_SER_NAME(StringUtil.nullToSring(currChann.get("AREA_SER_NAME")));
			}
			String channCharg="(select CHANN_ID  from BASE_USER_CHANN_CHRG where  USER_ID='"+aUserBean.getXTYHID()+"')"; 
			String channChargAll="(select CHANN_ID  from BASE_CHANN )"; 
			String areaCharg="(select AREA_ID  from BASE_AREA_CHRG_FLAT where  CHRG_PSON_ID='"+aUserBean.getXTYHID()+"')";
			if("1".equals(aUserBean.getCHANNS_CHARG()))
			{
				aUserBean.setCHANNS_CHARG(channChargAll);	
			}else
			{
               aUserBean.setCHANNS_CHARG(channCharg);	
			}	
			aUserBean.setAREA_CHARG(areaCharg);
			
			// 组织个人权限
			Map qxMap = sysLoginService.getUserQXINFO(aUserBean);
			aUserBean.setQXMap(qxMap);
			// 组织模块权限级别对象
			Map<String, String> qxjbMap = sysLoginService.getUserQXJB(aUserBean);
			aUserBean.setQXJBMap(qxjbMap);
			// 设置session失效时间和session切换
			HttpSession session = request.getSession(true);
			if(null!=session){
              session.invalidate();
            }
            session = request.getSession(true);
            System.err.println("session\u5931\u6548\u65F6\u95F4:="+ (int) (0.5D * 3600D));
			session.setMaxInactiveInterval((int) (0.5D * 3600D));
			session.removeAttribute("UserCSS");
			session.removeAttribute("UserBean");
			aUserBean.setRealYHKL(KL);
			// 设置用户样式
			aUserBean.setUSERSTYLE(USERCSS);
			// 创建小数位参数对象
			aUserBean.setXswModelList(sysLoginService.getXSWModel());
			session.setAttribute("UserBean_MD5KL", KL);
			session.setAttribute("UserBean", aUserBean);
			session.setAttribute("UserCSS", USERCSS);
			String firstPage = request.getParameter("firstPage");
			session.setAttribute("firstPage", firstPage);
			session.setAttribute("query_user_name", System.currentTimeMillis()
					+ aUserBean.getYHM());
			System.err.println("\u7CFB\u7EDF\u5F00\u59CB\u65F6\u95F4="
					+ startTime + ", \u7CFB\u7EDF\u7ED3\u675F\u65F6\u95F4="
					+ TimeComm.getTimeStamp(""));

			// 加载用户菜单
			// List usrMenus = sysLoginService.findAllMenus();
			List usrMenus = new ArrayList();
			if ("administrator".equals(aUserBean.getYHM())) {
				usrMenus = sysLoginService.findAllMenus();
			} else {
				usrMenus = sysLoginService.findMenuByUserId(aUserBean.getXTYHID(),aUserBean.getIS_DRP_LEDGER());

			}
			if (usrMenus == null || usrMenus.isEmpty()) {
				request.setAttribute("msg", "您未被授权，请检查角色菜单配置！");
				return mapping.findForward(FAILURE);
			}
			MenuHelper menuHelper = new MenuHelper(usrMenus);
			String Str = menuHelper.getJsonData4MenuTree();
			request.setAttribute("treeData", Str);
			// 获取主角色
			// 设置系统首页
			setIndexUrl(request, YHM);
			// 处理同一帐号同一时刻只允许登录一个的问题 范春锋 20120323
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("YHM", YHM);
			paraMap.put("invalidateFlag", request.getParameter("invalidateFlag"));
			proSession(request, paraMap);
            //add by zzb  首页显示消息数目
			List<MessageModel> oldModelList = MessTimerTask.getReturnList();
			if(null != oldModelList && oldModelList.size() > 0){
				int size = oldModelList.size();
				String yhid = aUserBean.getXTYHID();
				int messageSize = 0;
				for(int i=0;i<size;i++){
					MessageModel oldModel = oldModelList.get(i);
					String RECEIVEID  = oldModel.getRECEIVEID();
					if(yhid.equals(RECEIVEID)){
						messageSize ++;
					}
					if(messageSize == 500){//每个人只能看前500条消息
						break;
					}
				} 
				request.setAttribute("messageSize", messageSize);
			}
			return mapping.findForward("index");
		} catch (Exception ex) {
			logger.error(ex);
			System.err.println("LoginServlet in Error:" + ex.toString());
			request.setAttribute("msg", "登陆失败，程序出错！");
			return mapping.findForward(FAILURE);
		}	
			
	}
	/**
	 * simpleLogin
	 *  add by zhuxw 2014-06-19
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward wapLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    Map newMap=new HashMap();
		    List newList=new ArrayList();
		    UserBean aUserBean = new UserBean(newMap, newList, newList);
            HttpSession session = request.getSession(true);
			if(null!=session){
              session.invalidate();
            }
			// add by zzb 2015-4-20 
			String card_no = request.getParameter("card_no");
			request.setAttribute("card_no", card_no);
			
            session = request.getSession(true);
            System.err.println("session\u5931\u6548\u65F6\u95F4:="+ (int) (0.5D * 3600D));
			session.setMaxInactiveInterval((int) (0.5D * 3600D));
			session.setAttribute("UserBean", aUserBean);
			return mapping.findForward("towapindex");
			
	}
	/**
	 * 设置用户页面会话.
	 * 
	 * @param request the request
	 * @param userId the user id
	 */
	// private String generyUserSession(UserSession user, boolean portalLogin) {
	// String today = DateUtil.format(new Date(), "yyyy年MM月dd日");
	// StringBuffer bf = new StringBuffer(200);
	// bf.append("{");
	// bf.append("today:'").append(today).append("'");
	// bf.append(",userId:'").append(user.getUserId()).append("'");
	// bf.append(",userName:'").append(user.getUserName()).append("'");
	// bf.append(",orgName:'").append(user.getOrgName()).append("'");
	// bf.append(",orgId:'").append(user.getOrgId()).append("'");
	// bf.append(",userType:'").append(user.getUserType()).append("'");
	// bf.append(",typeName:'").append(user.getUserTypeName()).append("'");
	// bf.append(",portal:").append(portalLogin);
	// bf.append("}");
	// return bf.toString();
	// }
	/**
	 * 设置系统首页
	 * 
	 * @param request
	 * @param usr
	 */
	private void setIndexUrl(HttpServletRequest request, String userId) {
		UserBean userBean =(UserBean)request.getSession(false).getAttribute("UserBean");
		String indexUrl = null;
		String indexTop = null;
		if("1".equals(userBean.getIS_DRP_LEDGER())){
			indexUrl = "pages/index/drpmain.jsp";//"pages/index/drpmain.jsp";
			indexTop = "drp";
		}else{
			indexUrl = "pages/index/erpmain.jsp";
		}
		request.setAttribute("indexUrl", indexUrl);
		request.setAttribute("indexTop", indexTop);
	}

	/**
	 * 密码设置页面.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward setPwdLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");
		if (null == userBean) {
			request.setAttribute("msg", "用户已失效，请重新登录！");
			return mapping.findForward(FAILURE);
		}
		String YHBH = userBean.getYHBH();
		String YHM = userBean.getXM();
		request.setAttribute("YHBH", YHBH);
		request.setAttribute("YHM", YHM);
		return mapping.findForward("setPwd");
		// 验证用户及密码
		// String userId = ParamUtil.get(request, "userId");
		// String password = request.getParameter("userPwd");
		// if (ParamUtil.isEmpty(userId) || ParamUtil.isEmpty(password)) {
		// request.setAttribute("msg", "您未被授权，请检查登陆信息！");
		// return mapping.findForward(FAILURE);
		// }
		// Map rst = sysLoginService.loadUser(userId);
		// if (rst == null || "0".equals(rst.get("userStatus"))) {
		// request.setAttribute("msg", "用户不存在，请重新登陆！");
		// return mapping.findForward(FAILURE);
		// }
		// Map params = new HashMap();
		// params.put("userId", userId);
		// params.put("userPwd", MD5Encrypt.MD5(password));
		// sysLoginService.updatePwd(params);
		//
		// return this.authLogin(mapping, form, request, response);
	}

	/**
	 * 修改密码 Description :.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void setPwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			UserBean userBean = (UserBean) request.getSession(false)
					.getAttribute("UserBean");
			String realPwd = userBean.getRealYHKL();
			String userId = userBean.getXTYHID();
			String oldPwd = ParamUtil.get(request, "oldpwd");
			String newPwd = ParamUtil.get(request, "newpwd");
			if (!realPwd.equals(oldPwd)) {
				jsonResult = jsonResult(false, "原密码输入有误，请重新输入");
			} else {
				Map<String, String> params = new HashMap<String, String>();
				params.put("xtyhid", userId);
				params.put("password", newPwd);
				sysLoginService.updatePwd(params);

				// 更新session中的密码
				HttpSession session = request.getSession(true);
				userBean.setRealYHKL(newPwd);
				session.setAttribute("UserBean", userBean);
			}
		} catch (Exception e) {
			jsonResult = jsonResult(false, "更改密码失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 获取帐套信息 Description :.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void getZtxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String userId = ParamUtil.get(request, "userId");
			StringBuffer rst = new StringBuffer();
			if (!StringUtil.isEmpty(userId)) {
				List<ZTWHModel> ztxxList = sysLoginService.getZtxx(userId);
				if (null != ztxxList && !ztxxList.isEmpty()) {
					for (ZTWHModel ztxx : ztxxList) {
						rst.append("<option title='" + ztxx.getZTMC()
								+ "' value='" + ztxx.getZTXXID() + "'>"
								+ ztxx.getZTMC() + "</option>");
					}
				}
			}
			jsonResult = jsonResult(true, rst.toString());
		} catch (Exception e) {
			logger.error(e);
			jsonResult = jsonResult(false, "获取帐套信息失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 获取公告信息（跑马灯） Description :.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
	public void getNotices(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String splits = request.getParameter("splits");
		if (StringUtil.isEmpty(splits)) {
			splits = " ";
		}
		StringBuffer noticeBuffer = new StringBuffer();

		try {
			UserBean userBean = (UserBean) request.getSession(false)
					.getAttribute("UserBean");
			if (null == userBean) {
				noticeBuffer.append("用户已失效，请重新登录");
			} else {
				List<HashMap<String, String>> notices = sysLoginService
						.getNotices(userBean.getLoginZTXXID());
				if (null != notices && !notices.isEmpty()) {
					for (int i = 0; i < notices.size(); i++) {
						noticeBuffer.append(i + 1).append("：").append(
								notices.get(i).get("NOTICE")).append(splits);
					}
				} else {
					noticeBuffer.append("暂无公告信息");
				}
			}
		} catch (Exception e) {
			noticeBuffer.append("&nbsp;获取公告信息失败");
		}
		if (null != writer) {
			writer.write(noticeBuffer.toString());
			writer.close();
		}
	}

	/**
	 * Gets the sys time.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the sys time
	 */
	public void getSysTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("delete() begin");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String temp = "当前系统时间为：" + TimeComm.getNYRDate() + (":")
					+ TimeComm.getHour() + (":") + TimeComm.getMinute() + (":")
					+ TimeComm.getSecond();
			jsonResult = jsonResult(true, temp);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "获得系统时间失败！");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		logger.info("getSysTime end !");
	}

	// 初始化权限
	/**
	 * Inits the qx comm.
	 * 
	 * @param YHM the yHM
	 * @param KL the kL
	 * @param ZTXXID the zTXXID
	 * 
	 * @return the user bean
	 * 
	 * @throws Exception the exception
	 */
	public UserBean initQXComm(String YHM, String KL, String ZTXXID)
			throws Exception {
		return sysLoginService.initQXComm(YHM, KL, ZTXXID, true);
	}

	/**
	 * Gets the sys login service.
	 * 
	 * @return the sys login service
	 */
	public SysLoginService getSysLoginService() {
		return sysLoginService;
	}

	/**
	 * Sets the sys login service.
	 * 
	 * @param sysLoginService the new sys login service
	 */
	public void setSysLoginService(SysLoginService sysLoginService) {
		this.sysLoginService = sysLoginService;
	}

	/**
	 * * 记录登录信息相关信息到Application 作者 范春锋 时间 20120321 request paraMap
	 * 当前登录信息、注销当前登录号前Session 返回值.
	 * 
	 * @param request the request
	 * @param paraMap the para map
	 */
	@SuppressWarnings("unchecked")
	public void proSession(HttpServletRequest request,
			Map<String, String> paraMap) {
//		// 注销标志
//		String invalidateFlag = paraMap.get("invalidateFlag");
//		// 当前用户登录号
//		String YHM = (String) paraMap.get("YHM");
//
//		// 取application对象
//		ServletContext application = request.getSession().getServletContext();
//		// 获取application对象中在线用户Map
//		Map<String, String> loginMap = (Map<String, String>) application
//				.getAttribute("loginUser");
//		// 如果为空创建新map
//		if (null == loginMap) {
//			loginMap = new HashMap<String, String>();
//		}
//
//		// 注销当前用户前一个Session标志(true:注销)
//		if ("true".equals(invalidateFlag)) {
//			String sessionId = (String) loginMap.get(YHM);
//			// 根据SessionID 取 Session
//			HttpSession sess = MySessionContext.getSession(sessionId);
//
//			// 如果Session不为空
//			if (null != sess
//					&& null != (UserBean) sess.getAttribute("UserBean")) {
//				// 注销session
//				MySessionContext.delSession(sess);
//				loginMap.remove(YHM);
//			}
//		}
//		// Map<用户登录号,Session>
//		loginMap.put(YHM, request.getSession().getId());
//		// 赋值application
//		application.setAttribute("loginUser", loginMap);
	}

	/**
	 * 查询当前用户是否己经登录 作者 范春锋 时间 20120322.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	@SuppressWarnings("unchecked")
	public ActionForward checkUserOnline(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
//		try {
//			// 当前用户
//			String yhm = request.getParameter("YHM");
//			// 取application对象
//			ServletContext application = request.getSession().getServletContext();
//            // 获取application对象中在线用户Map
//			Map<String, String> loginMap = (Map<String, String>) application.getAttribute("loginUser");
//			if (null != loginMap) {
//				String sessionId = (String) loginMap.get(yhm);
//				HttpSession session = (HttpSession) request.getSession();
//				if (null != session) {
//					if (!StringUtil.isEmpty(sessionId)
//							&& !sessionId.equals(session.getId())) {
//						// 根据SessionID 取当前Session
//						HttpSession sessionObj = MySessionContext.getSession(sessionId);
//						if (null != sessionObj) {
//							jsonResult = jsonResult(false, "当前用户己经登录,是否强制退出?");
//						}
//					}
//
//					// 判断当前浏览器是否有用户己经登录过系统
//					 for (Map.Entry<String, String> entry : loginMap.entrySet()) {    
//						 String sessId = entry.getValue();
//						 if (session.getId().equals(sessId)) {
//								jsonResult = jsonResult(false, "onLine");
//							}
//				     }  
//					// 同一浏览器同一用户不允许重复登录
//					if (!StringUtil.isEmpty(sessionId)
//							&& sessionId.equals(session.getId())) {
//						jsonResult = jsonResult(false, "REPEAT");
//					}
//				}
//			} else {
//				jsonResult = jsonResult(true, null);
//			}
//			System.err.println("jsonResult==="+jsonResult);
//		} catch (Exception e) {
//			logger.info(e);
//			jsonResult = jsonResult(false, "程序异常");
//		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}

		return null;
	}

	/**
	 * 关闭IE时，注销Session事件 作者 范春锋 时间 20120322.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	@SuppressWarnings("unchecked")
	public ActionForward destroyedSession(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			HttpSession session = (HttpSession) request.getSession();
			if (session.getAttribute("UserBean") != null) {
				session.removeAttribute("UserBean");
			}
			// 复位会话
			session.invalidate();
			jsonResult = jsonResult(true, null);
		} catch (Exception e) {
			logger.info(e);
			jsonResult = jsonResult(false, "程序异常");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
       return null;
	}
}
