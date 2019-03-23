<!-- 
 *@module 渠道管理-上报管理
 *@func   营销经理日报维护
 *@version 1.1
 *@author zcx
 *  -->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	    <script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<title>营销经理日报维护</title>
	</head>
	<body class="bodycss1">
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="" style="margin-top: -20px;">
			<tr>   
				<td class="detail2">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail_lst">
						 <tr>
								<td width="18%" align="center" class="detail_content" colspan="3">
									<span style="border:0px solid;font-weight:bold;">评价人</span>
								</td>
								<td width="18%" class="detail_content">
								     ${rst.SCORE_NAME}
								</td>
								<td width="18%" class="detail_content" align="center">
									<span style="border:0px solid;font-weight:bold;">被评价人</span>
								</td>
								<td width="18%" class="detail_content">
								   ${rst.MKM_NAME}
								</td>
								<td width="18%" class="detail_content" align="center">
									<span style="border:0px solid;font-weight:bold;">评价日期</span>
								</td>
								<td width="10%" class="detail_content">
									 ${rst.SCORE_DATE}
								</td>
							</tr>
							<tr>
							   <td width="7%" align="center" class="detail_content"> 
								   <span style="border:0px solid;font-weight:bold;">工作内容</span>
							   </td>
							   <td width="4%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">分值</span>
							   </td>
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评价(分)</span>
								</td>
								<td width="18%" class="detail_content" align="center">
									 <span style="border:0px solid;font-weight:bold;">8-10/16-20</span>
								</td>
								<td width="18%" class="detail_content" align="center">
									<span style="border:0px solid;font-weight:bold;">5.5-7.5/11-15</span>
								</td>
								<td width="18%" class="detail_content" align="center">
									<span style="border:0px solid;font-weight:bold;">3-5/6-10</span>
								</td>
								<td width="18%" class="detail_content" align="center">
									<span style="border:0px solid;font-weight:bold;">0-2/0-5</span>
								</td>
								<td width="10%" class="detail_content" align="center">
									 <span style="border:0px solid;font-weight:bold;">得分</span>
									 ${rst.SCORE_TOTAL}
								</td>
							</tr>
							<tr> 
							   <td width="7%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">工作拜访频率</span>
								   <input type="hidden" name="SCORE_OPTION1" id="SCORE_OPTION1"  json="SCORE_OPTION1" value="工作拜访频率" />
							   </td>
							   <td width="4%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">10</span>
								   <input type="hidden" name="SCORE_VAL1" id="SCORE_VAL1" json="SCORE_VAL1" value="10" />
							   </td>
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评分</span>
								</td>
								<td width="18%" class="detail_content" align="center">
									 每月拜访1次及以上，且过程中多次主动电话沟通
									 <input type="hidden" id="OPTION_DESC1_1" name="OPTION_DESC1_1" json="OPTION_DESC1_1" value="每月拜访1次及以上，且过程中多次主动电话沟通"/>
								</td>
								<td width="18%" class="detail_content" align="center">
									 每2个月拜访1次，且过程中多次主动电话沟通
									 <input type="hidden" id="OPTION_DESC2_1" name="OPTION_DESC2_1" json="OPTION_DESC2_1" value="每2个月拜访1次，且过程中多次主动电话沟通" />
								</td>
								<td width="18%" class="detail_content" align="center">
									 每3个月拜访1次，且过程中偶有主动电话沟通
									 <input type="hidden" id="OPTION_DESC3_1" name="OPTION_DESC3_1" json="OPTION_DESC3_1" value="每3个月拜访1次，且过程中偶有主动电话沟通" /> 
								</td>
								<td width="18%" class="detail_content" align="center">
									 半年内拜访1次，且过程中基本无主动电话沟通
									 <input type="hidden" id="OPTION_DESC4_1" name="OPTION_DESC4_1" json="OPTION_DESC4_1" value="半年内拜访1次，且过程中基本无主动电话沟通" />
								</td>
								<td width="10%" class="detail_content" align="center" rowspan="2">
								   ${rst.OPTION_SCORE1}
 								</td>
							</tr>
							<tr> 
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评价描述</span>
								</td>
								<td align="center" class="detail_content" colspan="4">
								 ${rst.SCORE_DESC1}
								</td>
							</tr>
							<tr> 
							   <td width="7%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">公司政策传达</span>
								   <input type="hidden" name="SCORE_OPTION2" id="SCORE_OPTION2"  json="SCORE_OPTION2" value="公司政策传达" />
							   </td>
							   <td width="4%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">10</span>								   
								   <input type="hidden" name="SCORE_VAL2" id="SCORE_VAL2" json="SCORE_VAL2" value="10" />
							   </td>
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评分</span>
								</td>
								<td width="18%" class="detail_content" align="center">
									 1、积极将相关政策资料传达
									 2、主动讲解公司政策,使其
									    明了清晰
									 <input type="hidden" name="OPTION_DESC1_2" name="OPTION_DESC1_2" json="OPTION_DESC1_2" value=" 1、积极将相关政策资料传达 2、主动讲解公司政策,使其明了清晰" />
								</td>
								<td width="18%" class="detail_content" align="center">
									 1、能够及时送达多数公司
									    政策资料
									 2、主动讲解多数政策,不够
									    清晰明确
									 <input type="hidden" name="OPTION_DESC2_2" name="OPTION_DESC2_2" json="OPTION_DESC2_2" value="1、能够及时送达多数公司政策资料 2、主动讲解多数政策,不够清晰明确" />  
								</td>
								<td width="18%" class="detail_content" align="center">
									 1、能够送达公司部分政策资料,
									    但偶有拖延
									 2、被动讲解资料相关政策,且
									    不够清晰
									  <input type="hidden" name="OPTION_DESC3_2" name="OPTION_DESC3_2" json="OPTION_DESC3_2"  value="1、能够送达公司部分政策资料,但偶有拖延 2、被动讲解资料相关政策,且不够清晰"/>
								</td>
								<td width="18%" class="detail_content" align="center">
									 1、多数公司政策送达较滞后
									 2、基本未传达解释公司政策
									 <input type="hidden" name="OPTION_DES34_2" name="OPTION_DESC4_2" json="OPTION_DESC4_2"  value="1、多数公司政策送达较滞后 2、基本未传达解释公司政策" />
								</td>
								<td width="10%" class="detail_content" align="center" rowspan="2">
								   ${rst.OPTION_SCORE2}
 								</td>
							</tr>
							<tr> 
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评价描述</span>
								</td>
								<td align="center" class="detail_content" colspan="4">
								      ${rst.SCORE_DESC2}
								</td>
							</tr>
							
							<tr> 
							   <td width="7%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">门店精致化</span>
								   <input type="hidden" name="SCORE_OPTION3" id="SCORE_OPTION3"  json="SCORE_OPTION3" value="门店精致化" />
							   </td>
							   <td width="4%" align="center" class="detail_content" rowspan="2">
								  <span style="border:0px solid;font-weight:bold;"> 10</span>
								  <input type="hidden" name="SCORE_VAL3" id="SCORE_VAL3" json="SCORE_VAL3" value="10" />
							   </td>
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评分</span>
								</td>
								<td width="18%" class="detail_content" align="center">
									 1、确保公司精致化标准及时到客户
									 2、现场直到/培训导购落实精致化
									    要求,并亲身示范
									 3、所属门店精致化检查全部合格
									 <input type="hidden" name="OPTION_DESC1_3" name="OPTION_DESC1_3" json="OPTION_DESC1_3" value="1、确保公司精致化标准及时到客户 2、现场直到/培训导购落实精致化要求,并亲身示范 3、所属门店精致化检查全部合格"  />
									 
								</td>
								<td width="18%" class="detail_content" align="center">
									 1、公司精致化标准基本传达到位
									 2、多数情况下能够现场指导/培训
									    导购落实精致化要求,并亲身示范
									 3、所属门店精致化检查多数合格
									 <input type="hidden" name="OPTION_DESC2_3" name="OPTION_DESC2_3" json="OPTION_DESC2_3"  value="1、公司精致化标准基本传达到位 2、多数情况下能够现场指导/培训导购落实精致化要求,并亲身示范 3、所属门店精致化检查多数合格"  />
								</td>
								<td width="18%" class="detail_content" align="center">
									 1、公司精致化标准基本传达到位
									 2、基本通过电话给予指导,现场
									    示范较少
									 3、所属门店精致化检查半数合格
									  <input type="hidden" name="OPTION_DESC3_3" name="OPTION_DESC3_3" json="OPTION_DESC3_3" value="1、公司精致化标准基本传达到位  2、基本通过电话给予指导,现场示范较少 3、所属门店精致化检查半数合格" />
								</td>
								<td width="18%" class="detail_content" align="center">
									 1、公司精致化标准未能传达到位
									 2、精致化标准无指导和示范
									 3、所属门店精致化检查仅少数合格
									 <input type="hidden" name="OPTION_DESC4_3" name="OPTION_DESC4_3" json="OPTION_DESC4_3"  value="1、公司精致化标准未能传达到位 2、精致化标准无指导和示范 3、所属门店精致化检查仅少数合格">
								</td>
								<td width="10%" class="detail_content" align="center" rowspan="2">
							       	${rst.OPTION_SCORE3}
 								</td>
							</tr>
							<tr> 
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评价描述</span>
								</td>
								<td align="center" class="detail_content" colspan="4">
								   ${rst.SCORE_DESC3}
								</td>
							</tr>
							
								<tr> 
							   <td width="7%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">导购员培训</span>
								   <input type="hidden" name="SCORE_OPTION4" id="SCORE_OPTION4"  json="SCORE_OPTION4" value="导购员培训" />
							   </td>
							   <td width="4%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">10</span>
								   <input type="hidden" name="SCORE_VAL4" id="SCORE_VAL4" json="SCORE_VAL4" value="10" />
							   </td>
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评分</span>
								</td>
								<td width="18%" class="detail_content" align="center">
									 每月对导购员培训1次及以上,且培训效果良好
									 <input type="hidden" name="OPTION_DESC1_4" name="OPTION_DESC1_4" json="OPTION_DESC1_4" value="每月对导购员培训1次及以上,且培训效果良好" /> 
								</td>
								<td width="18%" class="detail_content" align="center">
									 每2个月对导购员培训1次,且培训效果良好
									 <input type="hidden" name="OPTION_DESC2_4" name="OPTION_DESC2_4" json="OPTION_DESC2_4" value="每2个月对导购员培训1次,且培训效果良好" />
								</td>
								<td width="18%" class="detail_content" align="center">
									 每3个月对导购员培训1次,且培训效果一般
									 <input type="hidden" name="OPTION_DESC3_4" name="OPTION_DESC3_4" json="OPTION_DESC3_4" value="每3个月对导购员培训1次,且培训效果一般" />
								</td>
								<td width="18%" class="detail_content" align="center">
									 半年内对导购员进行培训1次,且培训效果差
									 <input type="hidden" name="OPTION_DESC4_4" name="OPTION_DESC4_4" json="OPTION_DESC4_4" value="半年内对导购员进行培训1次,且培训效果差" />
								</td>
								<td width="10%" class="detail_content" align="center" rowspan="2">
								   ${rst.OPTION_SCORE4}
 								</td>
							</tr>
							<tr> 
							   <td width="7%" align="center" class="detail_content">
								  <span style="border:0px solid;font-weight:bold;"> 评价描述</span>
								</td>
								<td align="center" class="detail_content" colspan="4">
								    ${rst.SCORE_DESC4}
								</td>
							</tr>
							
							
							<tr> 
							   <td width="7%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">店外推广执行</span>
								   <input type="hidden" name="SCORE_OPTION5" id="SCORE_OPTION5"  json="SCORE_OPTION5" value="店外推广执行" />
							   </td>
							   <td width="4%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">20</span>
								   <input type="hidden" name="SCORE_VAL5" id="SCORE_VAL5" json="SCORE_VAL5" value="20" />
							   </td>
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评分</span>
								</td>
								<td width="18%" class="detail_content" align="center">
								   1、主导客户制定推广方案
								   2、整合资源,积极参与推广活动
								   3、活动能够达成预期目标
								   4、活动总结,优秀案例分享
								   <input type="hidden" name="OPTION_DESC1_5" name="OPTION_DESC1_5" json="OPTION_DESC1_5" value="1、主导客户制定推广方案 2、整合资源,积极参与推广活动 3、活动能够达成预期目标 4、活动总结,优秀案例分享" />
								</td>
								<td width="18%" class="detail_content" align="center">
								   1、指导客户制定推广方案
								   2、整合资源,积极参与多数推广活动
								   3、多数活动能够达成预期目标
								   4、针对多数活动总结和优秀案例分享
								   <input type="hidden" name="OPTION_DESC2_5" name="OPTION_DESC2_5" json="OPTION_DESC2_5" value="1、指导客户制定推广方案 2、整合资源,积极参与多数推广活动 3、多数活动能够达成预期目标 4、针对多数活动总结和优秀案例分享" />
								</td>
								<td width="18%" class="detail_content" align="center">
								   1、指导客户制定半数推广方案
								   2、整合资源,积极参与半数推广活动
								   3、半数活动能够达成预期目标
								   4、针对半数活动总结和优秀案例分享
								   <input type="hidden" name="OPTION_DESC3_5" name="OPTION_DESC3_5" json="OPTION_DESC3_5"  value="1、指导客户制定半数推广方案 2、整合资源,积极参与半数推广活动 3、半数活动能够达成预期目标 4、针对半数活动总结和优秀案例分享" />
								</td>
								<td width="18%" class="detail_content" align="center">
								   1、指导客户制定少数推广方案
								   2、未积极或仅少数活动能够参与
								   3、仅少数活动能够达成预期目标
								   4、基本上无活动总结和优秀案例分享
								   <input type="hidden" name="OPTION_DESC4_5" name="OPTION_DESC4_5" json="OPTION_DESC4_5"  value="1、指导客户制定少数推广方案 2、未积极或仅少数活动能够参与 3、仅少数活动能够达成预期目标 4、基本上无活动总结和优秀案例分享" />
								</td>
								<td width="10%" class="detail_content" align="center" rowspan="2">
								   ${rst.OPTION_SCORE5}
 								</td>
							</tr>
							<tr> 
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评价描述</span>
								</td>
								<td align="center" class="detail_content" colspan="4">
								    ${rst.SCORE_DESC5}
								</td>
							</tr>
							
							<tr> 
							   <td width="7%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">分销商开发与维护</span>
								   <input type="hidden" name="SCORE_OPTION6" id="SCORE_OPTION6"  json="SCORE_OPTION6" value="分销商开发与维护" />
							   </td>
							   <td width="4%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">20</span>
								   <input type="hidden" name="SCORE_VAL6" id="SCORE_VAL6" json="SCORE_VAL6" value="20" />
							   </td>
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评分</span>
								</td>
								<td width="18%" class="detail_content" align="center">
								   1、主导并主动协助客户分销商
								   2、分销商质量优异,对销量帮助很大
								   3、能够主动维护分销商,并及时反馈分销商问题,
								      协调沟通解决
								    <input type="hidden" name="OPTION_DESC1_6" name="OPTION_DESC1_6" json="OPTION_DESC1_6" value="1、主导并主动协助客户分销商 2、分销商质量优异,对销量帮助很大 3、能够主动维护分销商,并及时反馈分销商问题,协调沟通解决" />
								</td>
								<td width="18%" class="detail_content" align="center">
								   1、指导客户并主动开发分销商
								   2、分销商质量良好,对销量帮助良好
								   3、多数能够主动维护分销商,并及时
								      反馈分销商问题,协调沟通解决
								    <input type="hidden" name="OPTION_DESC2_6" name="OPTION_DESC2_6" json="OPTION_DESC2_6"  value="1、指导客户并主动开发分销商 2、分销商质量良好,对销量帮助良好 3、多数能够主动维护分销商,并及时反馈分销商问题,协调沟通解决" />
								</td>
								<td width="18%" class="detail_content" align="center">
								   1、指导客户并主动开发半数分销商
								   2、分销商质量一般,对销量帮助一般
								   3、半数能够主动维护分销商,并及时
								      反馈分销商问题,协调沟通解决
								    <input type="hidden" name="OPTION_DESC3_6" name="OPTION_DESC3_6" json="OPTION_DESC3_6"  value="1、指导客户并主动开发半数分销商 2、分销商质量一般,对销量帮助一般 3、半数能够主动维护分销商,并及时反馈分销商问题,协调沟通解决" />
								</td>
								<td width="18%" class="detail_content" align="center">
								   1、指导客户并主动开发少数分销商
								   2、分销商质量差,对销量帮助差
								   3、少数能够主动维护分销商,并
								      及时反馈分销商问题,协调沟通
								      解决
								    <input type="hidden" name="OPTION_DESC4_6" name="OPTION_DESC4_6" json="OPTION_DESC4_6"  value="1、指导客户并主动开发少数分销商 2、分销商质量差,对销量帮助差 3、少数能够主动维护分销商,并及时反馈分销商问题,协调沟通解决" />
								</td>
								<td width="10%" class="detail_content" align="center" rowspan="2">
								   ${rst.OPTION_SCORE6}
 								</td>
							</tr>
							<tr> 
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评价描述</span>
								</td>
								<td align="center" class="detail_content" colspan="4">
								   ${rst.SCORE_DESC6}
								</td>
							</tr>
							
							<tr> 
							   <td width="7%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">问题分析解决</span>
								   <input type="hidden" name="SCORE_OPTION7" id="SCORE_OPTION7"  json="SCORE_OPTION7" value="问题分析解决" />
							   </td>
							   <td width="4%" align="center" class="detail_content" rowspan="2">
								   <span style="border:0px solid;font-weight:bold;">20</span>
								   <input type="hidden" name="SCORE_VAL7" id="SCORE_VAL7" json="SCORE_VAL7" value="20" />
							   </td>
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评分</span>
								</td>
								<td width="18%" class="detail_content" align="center">
								   1、对客户反馈的市场问题做出快速反应
								   2、积极分析问题,并提出解决方案
								   3、整合资源,落实方案,问题快速解决
								   <input type="hidden" name="OPTION_DESC1_7" name="OPTION_DESC1_7" json="OPTION_DESC1_7" value=" 1、对客户反馈的市场问题做出快速反应 2、积极分析问题,并提出解决方案 3、整合资源,落实方案,问题快速解决"/>
								</td>
								<td width="18%" class="detail_content" align="center">
								   1、对客户反馈的市场问题多数能够做出
								      快速反应
								   2、多数问题能够积极分析问题,并提出
								      解决方案
								   3、整合资源,落实方案,多数问题快速解决
								   <input type="hidden" name="OPTION_DESC2_7" name="OPTION_DESC2_7" json="OPTION_DESC2_7" value="1、对客户反馈的市场问题多数能够做出快速反应 2、多数问题能够积极分析问题,并提出解决方案 3、整合资源,落实方案,多数问题快速解决" />
								</td>
								<td width="18%" class="detail_content" align="center">
								   1、对客户反馈的市场问题半数能够做出
								      快速反应
								   2、半数问题能够积极分析问题,并提出解决
								      方案
								   3、问题获得基本解决,基本上满意
								   <input type="hidden" name="OPTION_DESC3_7" name="OPTION_DESC3_7" json="OPTION_DESC3_7"  value="1、对客户反馈的市场问题半数能够做出快速反应 2、半数问题能够积极分析问题,并提出解决方案 3、问题获得基本解决,基本上满意" />
								</td>
								<td width="18%" class="detail_content" align="center">
								   1、对客户反馈的市场问题仅少数
								      能够做出快速反应
								   2、仅有数问题能够提出解决方案
								   3、仅少数问题获得基本解决,客户
								      不满意
								      <input type="hidden" name="OPTION_DESC4_7" name="OPTION_DESC4_7" json="OPTION_DESC4_7"  value=" 1、对客户反馈的市场问题仅少数能够做出快速反应 2、仅有数问题能够提出解决方案  3、仅少数问题获得基本解决,客户不满意" />
								</td>
								<td width="10%" class="detail_content" align="center" rowspan="2">
							    	${rst.OPTION_SCORE7}
 								</td>
							</tr>
							<tr> 
							   <td width="7%" align="center" class="detail_content">
								   <span style="border:0px solid;font-weight:bold;">评价描述</span>
								</td>
								<td align="center" class="detail_content" colspan="4">
								    ${rst.SCORE_DESC7}
								</td>
							</tr>
					</table>
				</td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
		</table>
	</body>
</html>
