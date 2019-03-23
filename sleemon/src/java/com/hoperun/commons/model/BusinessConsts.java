/**
 *@module 系统模块   
 *@func 业务系统常量   
 *@version 1.1
 *@author zhuxw      
 */
package com.hoperun.commons.model;

// TODO: Auto-generated Javadoc
/**
 * The Interface BusinessConsts.
 * 
 * @module 业务系统常量
 * @func 业务系统常量
 * @version 1.1
 * @author zhuxw
 */
public interface BusinessConsts {

	/* system Business Consts 不能删除，其它业务常量加在下面 */
	/** 数据状态：正常. */
	public static final String DEL_FLAG_COMMON = "0";

	/** 业务单据状态. */
	public static final String DEL_FLAG_DROP = "1";

	/** 原记录标记：是. */
	public static final String YJLBJ_FLAG_TRUE = "1";

	/** 原记录标记：否. */
	public static final String YJLBJ_FLAG_FALSE = "0";

	// 编号自动生成时页面显示用信息
	/** The Constant ZDSC. */
	public static final String ZDSC = "自动生成";

	/** The Constant USER_SESSION_OUT_OF_DATE. */
	public static final String USER_SESSION_OUT_OF_DATE = "用户已过期，请重新登录";

	/** The Constant 未提交 UNCOMMIT. */
	public static final String UNCOMMIT = "未提交";

	/** The Constant 提交 COMMIT. */
	public static final String COMMIT = "提交";

	/** The Constant REVOKE. */
	public static final String REVOKE = "撤销";

	/** The Constant REJECT. */
	public static final String REJECT = "否决";

	/** The Constant PASS. */
	public static final String PASS = "审核通过";

	/** The Constant DONE. */
	public static final String DONE = "已处理";

	/** 基础信息状态. */
	public static final String JC_STATE_DEFAULT = "制定";

	/** The Constant JC_STATE_ENABLE. */
	public static final String JC_STATE_ENABLE = "启用";

	/** The Constant JC_STATE_DISENABLE. */
	public static final String JC_STATE_DISENABLE = "停用";

	/** 小数位定义. */
	// 零售单价
	public static final String XSW_LSDJ = "LSDJ";

	// 零售金额
	/** The Constant XSW_LSJE. */
	public static final String XSW_LSJE = "LSJE";

	// 超交比率
	/** The Constant XSW_CJBL. */
	public static final String XSW_CJBL = "CJBL";

	// 单耗
	/** The Constant XSW_DH. */
	public static final String XSW_DH = "DH";

	// 成品数量
	/** The Constant XSW_CPSL. */
	public static final String XSW_CPSL = "CPSL";

	// 成本金额
	/** The Constant XSW_CBJE. */
	public static final String XSW_CBJE = "CBJE";

	// 税额
	/** The Constant XSW_SE. */
	public static final String XSW_SE = "SE";

	// 税率
	/** The Constant XSW_SL. */
	public static final String XSW_SL = "SL";

	// 成本单价
	/** The Constant XSW_CBDJ. */
	public static final String XSW_CBDJ = "CBDJ";

	/** The Constant BMGZ_LSH. */
	public static final String BMGZ_LSH = "流水号";

	/** The Constant BMGZ_YYR. */
	public static final String BMGZ_YYR = "年月日";

	/** The Constant BMGZ_YYR_LSH. */
	public static final String BMGZ_YYR_LSH = "年月日流水号";

	// SEQUENCEMC重复
	/** The Constant ERROR_0. */
	public static final String ERROR_0 = "0";

	// 段类型重复
	/** The Constant ERROR_1. */
	public static final String ERROR_1 = "1";

	// 子表记录大于2
	/** The Constant ERROR_2. */
	public static final String ERROR_2 = "2";

	// 编码对象重复
	/** The Constant ERROR_3. */
	public static final String ERROR_3 = "3";

	// 编码编号重复
	/** The Constant ERROR_4. */
	public static final String ERROR_4 = "4";

	// 已接收
	public static final String RECEIVED = "已接收";

	// 未接收
	public static final String UNRECEIVE = "未接收";
	/* end system Business Consts */

	/**
	 * 已登记
	 */
	public static final String CHECK_IN = "已登记";
	/**
	 * 未登记
	 */
	public static final String UN_CHECK_IN = "未登记";

	/**
	 * 手工新增
	 */
	public static final String HAND_AND = "手工新增";

	/**
	 * 产品投诉
	 */
	public static final String PRO_CMPL = "产品投诉";

	/**
	 * 服务投诉
	 */
	public static final String SER_CMPL = "服务投诉";

	/**
	 * 建议
	 */
	public static final String ADVS = "建议";

	/**
	 * 产品建议
	 */
	public static final String PRO_ADVS = "产品建议";

	/**
	 * 服务建议
	 */
	public static final String SER_ADVS = "服务建议";

	/**
	 * 未反馈
	 */
	public static final String STATE_WFK = "未反馈";

	/**
	 * 已反馈
	 */
	public static final String STATE_YFK = "已反馈";

	/**
	 * 未处理
	 */
	public static final String UNDONE = "未处理";

	/**
	 * 已提交厂家
	 */
	public static final String COMMITTOFACTORY = "已提交厂家";

	/**
	 * 已发布
	 */
	public static final String ISSUANCE = "已发布";
	/**
	 * 已终止
	 */
	public static final String OVER = "已终止";

	/**
	 * 推广促销 发布菜单传递的 参数
	 */
	public static final String FLAG_PRMT = "PRMT";

	/**
	 * 折扣类型
	 */
	public static final String DECT_TYPE = "正常折扣";

	/**
	 * 未审核
	 */
	public static final String UN_AUDIT = "未审核";

	/**
	 * 标准
	 * 
	 */
	public static final String STANDARD = "标准";
	/**
	 * 非标
	 * 
	 */
	public static final String UN_STANDARD = "非标";

	/**
	 * 待发货方确认
	 */
	public static final String WAIT_FOR_SEND_PERSON = "待发货方确认";
	
	/**
	 * 总部收货确认
	 */
	public static final String BS_Recv = "总部收货确认";
	
	/**
	 * 总部收货确认
	 */
	public static final String BS_Send = "总部已发货";
	/**
	 * 完成
	 */
	public static final String DEAL_OVER = "完成";

	/**
	 * 更新时间 数据库 系统时间
	 */
	public static final String UPDATE_TIME = "sysdate";

	/**
	 * 库间转储
	 */
	public static final String DUMP_DEFAULT_TYPE = "库间转储";

	/**
	 * 制作
	 */
	public static final String STATE_MAKE = "制作";

	/**
	 * 备货订货
	 */
	public static final String INSTORE_ORDER_TYPE = "备货订货";

	/**
	 * 已发货
	 */
	public static final String HAVE_SEND_GOODS = "已发货";

	/**
	 * 动作 --订货订单制作
	 */
	public static final String ORDER_BILL_ACTION_MAKE = "订货订单制作";
	/**
	 * 动作 --订货订单提交
	 */
	public static final String ORDER_BILL_ACTION_COMMIT = "订货订单提交";
	/**
	 * 动作 --订单员核单
	 */
	public static final String SALE_BILL_ACTION_AUDIT = "订单员核单";
	
	
	/**
	 * 状态 —— 客服预付款状态
	 */
	public static final String ADVPAYMENT_NOSUBMIT_STATE = "未提交";
	
	public static final String ADVPAYMENT_EFFECT_STATE = "已生效"; 
	
 
	/**
	 * 动作 订货订单制作 跳转URL
	 */
	public static final String ORDER_BILL_ACTION_TRACE_URL = "goodsorderhd.shtml?action=toFrame&selRowId=_";

	
	/**
	 * 动作 销售单制作 跳转URL
	 */
	public static final String SALE_BILL_ACTION_TRACE_URL = "saleorderrls.shtml?action=toFrame&selRowId=_";

	
	public static final String TABLE = "table";

	/**
	 * 货品信息标准规格
	 */
	public static final String[] PRODUCT_SPEC = new String[] { "900*2000",
			"1000*2000","1200*1900", "1200*2000", "1350*2000", "1500*1900", "1500*2000",
			"1800*2000", "2000*2000", "2000*2200" };

	/**
	 * 计划订货
	 */
	public static final String PLAN_ORDER = "计划订货";
	/**
	 * 总部未发
	 */
	public static final String NOT_SEND = "总部未发";
	/**
	 * 5吨
	 */
	public static final String CAR_TYPE = "5吨";
	
	/**
	 * 交易关闭
	 */
	public static final String STATE_COLSE = "交易关闭";
	/**
	 * 单据关闭
	 */
	public static final String COMMON_COLSE = "关闭";
	/**
	 * 已结算
	 */
	public static final String STATE_IS_PAY = "已结算";
	
	/**
	 * 待发货
	 */
	public static final String STATE_WAIT_SEND = "待发货";
	
	/**
	 * 终端销售
	 */
	public static final String TYPE_END_SALE = "终端销售";
	
	/**
	 * module -> sh
	 */
	public static final String MODULE_TYPE = "sh";
	
	/**
	 * 待审核
	 */
	public static final String WAIT_AUDIT = "待审核";
	
	/**
	 * 总部退回
	 */
	public static final String ORDER_BACK = "总部退回";
	
	/**
	 * 退回
	 */
	public static final String _BACK = "退回";
	
	public static final String BILL_TYPE_DEPOSIT = "订金";
	
	public static final String BILL_TYPE_ADVPAY = "正常收款";
	
	public static final String BILL_TYPE_PREPAY = "预订单结算";
	
	public static final String BILL_TYPE_RETURN = "客户退货结算";
	
	public static final String BILL_TYPE_RETURN_OTHER = "其它退款";
	
	public static final String BILL_TYPE_RETURN_TERM = "终端退货退款";
	
	public static final String STORE_OUT_TYPE = "销售出库";
	
	public static final String STORE_IN_TYPE = "终端退货";
	
	/**
	 * 已核销
	 */
	public static final String STATE_CHECK_PAY = "已核销";
	
	/** 常量 -> 0 */
	public static final String INTEGER_0 = "0";

	/** 常量 ->1 */
	public static final String INTEGER_1 = "1";
	
	/** 常量 ->2 */
	public static final String INTEGER_2 = "2";
	/** 常量 ->3 */
	public static final String INTEGER_3 = "3";
	
	/** 整车发运 */
	public static final String DELIVER_TYPE = "整车发运";
	
	/** 已核价 */
	public static final String HAVE_CHECK_PRICE = "已核价";
	
	/** 未核价 */
	public static final String UN_HAVE_CHECK_PRICE = "未核价";
 
	/** 已提交生产*/
	public static final String COMMIT_FACTORY = "已提交生产";
 
	/** 待出库方确认 */
	public static final String WAIT_FOR_SENDER = "待出库方确认";
	
	/** 待入库方确认 */
	public static final String WAIT_FOR_ACCEPT = "待入库方确认";
  
	/** 退回*/
	public static final String RETURN_BACK = "退回";
	
	/** 特殊工艺标记 是*/
	public static final String SPCL_YES = "有";
	
	/** 特殊工艺标记 否*/
	public static final String SPCL_NO = "无";
	
	
	/** 特殊工艺使用标记 */
	public static final String SPCL_USER_YES = "1";
	
	/** 正常 */
	public static final String NORMAL = "正常";
	
	public static final String ADVC_ORDER_CONF_ID = "YDD001";    //预订单 加
	
	public static final String ADVC_ORDER1_CONF_ID = "YDD002";    //预订单 减
	
	public static final String ADVC_ORDERCHANGE_CONF_ID = "YBG001";    //预订单 变更   加
	
	public static final String ADVC_ORDER_SEQ_CONF_ID = "YFH001";  //发货申请单
	
	public static final String ADVC_ORDER_SEQ_CONF1_ID = "YFH002";  //发货申请单  减库存
	
	public static final String ADVC_ORDER_SEQ_CONF2_ID = "YFH003";  //发货申请单  按冻结转发货数量 减冻结库存
	
	public static final String STOREOUT_ORDER_CONF_ID = "CKD001";   //终端销售出库  
	
	public static final String STOREOUT_ORDER_CONF11_ID = "CKD011";   //返出库  
	
	public static final String STOREOUT_ORDER_CONF2_ID = "CKD002";   //退货出库  

	public static final String STOREOUT_ORDER_CONF3_ID = "CKD003";   //返修出库  
	
	public static final String STOREOUT_ORDER_CONF4_ID = "CKD004";   //下级销售出库  
	
	public static final String STOREOUT_ORDER_CONF5_ID = "CKD005";   //区服退货出库  
	
	public static final String STOREOUT_ORDER_CONF6_ID = "CKD006";   //零星出库  
	
	public static final String CONV_PDT_CONF_1ID = "CONV001";   //形态转换  减
	
	public static final String CONV_PDT_CONF_2ID = "CONV002";   //形态转换  加
	
	public static final String STOREIN_ORDER_CONF_ID = "RKD001";  //订货入库
	
	public static final String STOREIN_ORDER_CONF2_ID = "RKD002";  //手工新增入库
	
	public static final String STOREIN_ORDER_CONF3_ID = "RKD003";  //返修入库
	
	public static final String STOREIN_ORDER_CONF4_ID = "RKD004";  //调拨入库
	
	public static final String STOREIN_ORDER_CONF5_ID = "RKD005";  //终端退货入库
	
	public static final String STOREIN_ORDER_CONF6_ID = "RKD006";  //下级退货入库
	
	public static final String STOREOUT_ADVC_ORDER_CONF_ID = "YDK001";   //预订单出库  
	
	/****************返入库*********************/
	public static final String STOREIN_FORDER_CONF_ID = "FRK001";  //订货入库
	
	public static final String STOREIN_FORDER_CONF2_ID = "FRK002";  //手工新增入库
	
	public static final String STOREIN_FORDER_CONF3_ID = "FRKD003";  //返修入库
	
	public static final String STOREIN_FORDER_CONF4_ID = "FRK004";  //调拨入库
	
	public static final String STOREIN_FORDER_CONF5_ID = "FRK005";  //终端退货入库
	
	public static final String STOREIN_FORDER_CONF6_ID = "FRK006";  //下级退货入库
	
	/****************返入库*********************/
	
	public static final String DUMP_OUT_CONF_ID = "ZC001";   //转储出库存
	
	public static final String DUMP_IN_CONF_ID = "ZC002";  //转储入库存
	
	public static final String INVEN_CONF_ID = "PD001";  //发货申请单
	
	public static final String COSTAD_CONF_ID = "CB001";  //成本调整
	
	public static final String ADVC_ORDER_CANCEL_CONF_ID = "YQX001";  //预订发货取消申请
	
	public static final String ACCT_GOODS_ORDER_CONF_ID = "ACCT_DHDD001";  //订货订单    加
	
	public static final String ACCT_GOODS_ORDER1_CONF_ID = "ACCT_DHDD002";  //订货订单 减
	
	public static final String ACCT_PAYMENT_CONF_ID = "ACCT_FKPZ001";  //付款凭证
	
	public static final String ACCT_SALE_ORDER_CONF_ID = "ACCT_XSDD001";       //销售订单 加

	public static final String ACCT_SALE_ORDER1_CONF_ID = "ACCT_XSDD002";      //销售订单 减
	
	public static final String ACCT_SALE_ORDER3_CONF_ID = "ACCT_XSDD003";      //销售订单按原订货数量 减
	 
	public static final String ACCT_PDTRETURN_CONF_ID = "ACCT_THHJ001";        //退货核价
	
	public static final String ACCT_DELIVER_CONF_ID = "ACCT_FYD001";           //发运单 加  计划发货
	
	public static final String ACCT_DELIVER_CONF4_ID = "ACCT_FYD004";           //发运单  减 计划发货
	
	public static final String ACCT_DELIVER_CONF1_ID = "ACCT_FYD002";           //发运单  减 实际发货
	
	public static final String ACCT_PAYMENT_CONF1_ID = "ACCT_PAM001";           //付款单  加
	
	public static final String ACCT_PAYMENT_CONF2_ID = "ACCT_PAM002";           //付款单 减
	
	public static final String ACCT_DELIVER_CONF3_ID = "ACCT_FYD003";           //发运单  加 实际发货
	
//	public static final String ACCT_STOREIN_CONF_ID = "ACCT_RKD001";  //入库单
//	
//	public static final String ACCT_STOREOUT_CONF_ID = "ACCT_CKD001";   //出库单  
	
	public static final String ACCT_RECIVE_PAYMENT_CONF_ID = "ACCT_SKD001";     //客户收款单
	
	public static final String ACCT_RECIVE_PAYMENT1_CONF_ID = "ACCT_SKD002";     //客户收款单 减
	
	public static final String FL_GOODS_ORDER_CONF_ID = "FL_DHDD001";           //订货订单 加
	
	public static final String FL_GOODS_ORDER1_CONF_ID = "FL_DHDD002";           //订货订单 减
	
	public static final String FL_SALE_ORDER_CONF_ID = "FL_XSDD001";           //销售订单  加
	
	public static final String FL_SALE_ORDER1_CONF_ID = "FL_XSDD002";           //销售订单  减
	
	public static String STORE_DESC = "库存数量";
    
	public static String FREEZE_DESC = "冻结数量";
	
	public static String SUCCESS = "true";
	
	public static String FLASE = "false";
	
	/*新接口返回成功值*/
	public static String N_INTER_SUCCESS="S";
	/*新接口返回失败值*/
	public static String N_INTER_FALSE="E";
	
	//接口信息
	public static String IMPL_GO_IN_INFO="成功调入接口";
	public static String IMPL_GO_OUT_INFO="成功调出接口";
	public static String IMPL_PARAM_NULL_ERR="接口传入参数为空";
	public static String IMPL_PARAM_FORMAT_ERR="接口传入参数(JSON)格式不正确";
	public static String IMPL_PARAM_MUST_FLD = "必填字段为空";
	public static String APP_KEY_ERROR = "APP_KEY不匹配";
	public static String IMPL_SQL_EXECUTE_FLASE = "Sql执行异常";
	public static String KEY_REPEAT = "主要字段重复";
	
	public static String WS_SUCESS="00000000";  //调ESB系统成功状态
	
	/**预订单单据 单头**/
	public static String ADVC_ORDER_NO_PREFIX="YDD";  
	/**预订单单据 段长**/
	public static int ADVC_ORDER_NO_LENGTH = 4;  
	
	
	/**
	 * 区域服务中心
	 */
	public static String CHANN_TYPE_AREA_SER = "区域服务中心";
	
	/**
	 * 付款方式
	 */
	public static String PAY_TYPE = "付款方式";
	
	/**
	 * http 请求方法 GET
	 */
	public static String REQUEST_GET = "GET";
	/**
	 * http 请求方法 POST
	 */
	public static String REQUEST_POST = "POST";
	/**
	 * 订单上2014-08-31以后单据变色
	 */
	public static String BILL_DIFF_DATE_FLAG = "2014-08-31";
}
