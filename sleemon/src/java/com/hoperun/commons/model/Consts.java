/**
 *@module 系统模块   
 *@func 系统模块   
 *@version 1.1
 *@author zhuxw      
 */
package com.hoperun.commons.model;

// TODO: Auto-generated Javadoc
/**
 * The Interface Consts.
 * 
 * @module 系统参数常量
 * @func 系统参数常量
 * @version 1.1
 * @author zhuxw
 */
public interface Consts {
	
	/** 调试信息开关. */
	public static final boolean DEBUG = Properties.getBoolean("DEBUG");
	
	/** 缺省密码. */
	public static final String DEF_PWD = Properties.getString("DEF_PWD");

	/** 缺省分頁記錄數. */
	public static final int PAGE_SIZE = Properties.getInt("PAGE_SIZE");
	
	/** 是否显示帐套. */
	public static final String ACCOUNT_DISPLAY = Properties.getString("ACCOUNT_DISPLAY");
	
	/** 默认帐套编号. */
	public static final String ACCOUNT_CODE = Properties.getString("ACCOUNT_CODE");
	
	/** 默认帐套名称. */
	public static final String ACCOUNT_DISPLAY_NAME = Properties.getString("ACCOUNT_DISPLAY_NAME");
	
	/** 默认登录CSS. */
	public static final String DEFAULT_CSS = Properties.getString("DEFAULT_CSS");
	
	/** 是否单点登录. */
	public static final String SINGN_LOGIN = Properties.getString("SINGN_LOGIN");
	
	/** 是否采用大窗口. */
	public static final String MAX_WINDOWS = Properties.getString("MAX_WINDOWS");
	/** 是否记录操作日志 */
	public static final boolean ACT_LOG = Properties.getBoolean("ACT_LOG");
	
	/** 渠道初始化编号 */
	public static final String INI_CHANN_ROLE_NO = Properties.getString("INI_CHANN_ROLE_NO");
	
	/** 用户Session标识. */
	public static final String USR_SESS = "UserBean";

	/** The Constant PARAM_COVER. */
	public static final String PARAM_COVER = "paramCover";

	/** 公共页面链接：空白页面. */
	public static String BLANK = "blank";

	/** 公共页面链接：预置页面，提示：正在开发中……. */
	public static String INPROGRESS = "inprogress";

	/** 公共页面链接：操作失败警示页面,返回时不刷新原页面. */
	public static String FAILURE = "failure";

	/** 公共页面链接: 操作成功提示页面,参数以_下划线标识. */
	public static String SUCCESS = "success";

	/** 公共页面链接：操作失败警示页面,返回时刷新原页面. */
	public static String FAILEXT = "failext";

	/** 公共页面链接: 操作成功提示页面,普通参数. */
	public static String SUCCEXT = "succext";

	/** 公共页面链接: 翻页工具栏. */
	public static String PAGE_FOOTER = "pfooter";

	/** 数据库类型. */
	public static final String DBTYPE = Properties.getString("DBTYPE");
	
	/** 系统表的模式名. */
    public static final String SYSSCHEMA = Properties.getString("SYSSCHEMA");
    
    /** 邮件服务器地址. */
    public static final String MAIL_SERVER_HOST = Properties.getString("MAIL_SERVER_HOST");
    
    /** 邮件服务器端口. */
    public static final String MAIL_SERVER_PORT = Properties.getString("MAIL_SERVER_PORT");
    
    /** 邮件是否需要身份验证. */
    public static final boolean MAIL_VALIDATE = Properties.getBoolean("MAIL_VALIDATE");
    
    /** 默认发送邮件地址. */
    public static final String DEF_FROM_MAIL_AD = Properties.getString("DEF_FROM_MAIL_AD");
    
    /** 默认邮件用户名. */
    public static final String DEF_MAIL_USERNAME = Properties.getString("DEF_MAIL_USERNAME");
    
    /** 默认邮件用户名. */
    public static final String DEF_MAIL_PASSWORD = Properties.getString("DEF_MAIL_PASSWORD");
    
    /** JobserverIp. */
    public static final String JOB_SERVER_IP = Properties.getString("JOB_SERVER_IP");
    
    /** PRE_SEVJOBS 一次锁定JOBS. */
    public static final String PRE_SEVJOBS = Properties.getString("PRE_SEVJOBS");
    
	/** 调试信息开关. */
	public static final boolean FUN_CHEK_PVG = Properties.getBoolean("FUN_CHEK_PVG");
	
	/** 最大冻结数量. */
	public static final String MAX_FREEZE_DATE = Properties.getString("MAX_FREEZE_DATE");

	/** 控制是否调用外系统. */
	public static final String INVOKE_SYS_FLG = Properties.getString("INVOKE_SYS_FLG");

	/** web service wsdl. */
	public static final String WS_WSDL = Properties.getString("WS_WSDL");

	/** web service 命名空间. */
	public static final String WS_NAME_SPACE = Properties.getString("WS_NAME_SPACE");

	/** web service 服务名. */
	public static final String WS_SER_NAME = Properties.getString("WS_SER_NAME");

	/** web service 端口号. */
	public static final String WS_PORT_NO = Properties.getString("WS_PORT_NO");

	/** web service 验证用户名. */
	public static final String WS_USERNAME = Properties.getString("WS_USERNAME");

	/** web service 验证密码. */
	public static final String WS_PASSWORD = Properties.getString("WS_PASSWORD");

	/** web service 验证用户名. */
	public static final String DM_USERNAME = Properties.getString("DM_USERNAME");

	/** web service 验证密码. */
	public static final String DM_PASSWORD = Properties.getString("DM_PASSWORD");
	
	/** DM web service . */
	public static final String DM_WSDL = Properties.getString("DM_WSDL");

	/** 不走区域服务中心流程. */
	public static final String IS_OLD_FLOW = Properties.getString("IS_OLD_FLOW");
	
	/** 控制订货订单合并成一个销售订单功能. */
	public static final String INVOKE_GOODS_MERGE = Properties.getString("INVOKE_GOODS_MERGE");
	 
	public static final String SLEEMON_REPORT_URL = Properties.getString("SLEEMON_REPORT_URL");
	/** 图片服务器地址 内网 **/
	public static final String APP_SERVER_URL_NET = Properties.getString("APP_SERVER_URL_NET");	
	/** 图片服务器地址 内网 **/
	public static final String PIC_SERVER_URL = Properties.getString("PIC_SERVER_URL");
	
	/** 图片服务器地址 外网 **/
	public static final String PIC_SERVER_URL_NET = Properties.getString("PIC_SERVER_URL_NET");
	
	/** 报表服务器地址 内网 **/
	public static final String RUNQIAN_REPORT_URL = Properties.getString("RUNQIAN_REPORT_URL");
	
	/** 报表服务器地址 外网 **/
	public static final String RUNQIAN_REPORT_URL_NET = Properties.getString("RUNQIAN_REPORT_URL_NET");
	
	public static final  String REBATE_PRD_NUMBERS="07-12,07-11,07-10,06-82,06-20,06-08,06-06,06-05,06-02,06-01,05,03,02,01,22,06-14,06-91,04";
	
	public static final boolean CTR_LARGESS = false;
	public static final  String LARGESS_PRD_NUMBERS="07-12,06-31,06-20,06-82,07-11,06-08,01,06-06,07-10,06-05,06-83,09-30,02,05,06-01,06-02,03,09-28,09-31,09-27,09-26";
	
	public static final String IS_NO_DELIVER_PLAN_FLAG = Properties.getString("IS_NO_DELIVER_PLAN_FLAG");
	
	public static final String USER_ONLY_ORDER_NO = Properties.getString("USER_ONLY_ORDER_NO");
	
	public static final String IS_NO_ADVC_DATE_FLAG = Properties.getString("IS_NO_ADVC_DATE_FLAG");
	
	public static final String IS_NO_SPCL_FLAG = Properties.getString("IS_NO_SPCL_FLAG");
	//flash报表打印地址
	public static final String FLASH_PRINT_URL = Properties.getString("FLASH_PRINT_URL");
}
