<!--
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time  2014-12-03 10:35:10 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>浏览</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/erp/sale/allotchanncard/AllotChanncard_Main.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
 	<style type="text/css">
	  .query_div_1{
		display:none;
		width:100%;
		background-color:#e0edf6;
		filter:alpha(opacity=90);
		position:absolute;
		left:0;
		top:20;
		right:100%;
		bottom:100%;
	}
	
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20px" valign="top">
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<c:if test="${module eq 'wh' || empty module}">
			  <td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>基础数据>>渠道卡券分配</td>
			</c:if>
			<td width="50" align="right"></td>
		</tr>
	  </table>
	</td>
</tr>
</table>
<div id="querydiv" class="query_div_1" >
<form id="mainForm" method="post" action="#">
 <input type="hidden" id="selChannParam" name="selChannParam" value=" STATE in('启用','停用')  "/>
 <input type="hidden" id="selActParam" name="selActParam" value=" STATE in('审核通过') and DEL_FLAG=0 "/>
 
<table id="querytb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">营销活动编号:</td>
					<td width="15%" class="detail_content">
	   					<input id="MARKETING_ACT_NO" name="MARKETING_ACT_NO" label="营销活动编号" readonly mustinput="true" inputtype="string" autocheck="true" value="${params.MARKETING_ACT_NO}"/>
	   					<input type="hidden" id="MARKETING_ACT_ID" name="MARKETING_ACT_ID" value="${params.MARKETING_ACT_ID}"/>
	   				   <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_149', false, 'MARKETING_ACT_ID', 'MARKETING_ACT_ID', 'forms[0]','MARKETING_ACT_NO,MARKETING_ACT_NAME', 'MARKETING_ACT_NO,MARKETING_ACT_NAME', 'selActParam');"> 
					
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">营销活动名称:</td>
					<td width="15%" class="detail_content">
	   					<input id="MARKETING_ACT_NAME" name="MARKETING_ACT_NAME" label="营销活动名称"  readonly mustinput="true" inputtype="string" autocheck="true" value="${params.MARKETING_ACT_NAME}"/>
					</td>				
                    <td width="8%" nowrap align="right" class="detail_label">加盟商编号:</td>
					<td width="15%" class="detail_content">
					   <input type="hidden" id="CHANN_ID" name="CHANN_ID" value="${params.CHANN_ID}"/>
	   				   <input type="text" id="CHANN_NO" name="CHANN_NO" label="加盟商编号" readonly  value="${params.CHANN_NO}" mustinput="true" inputtype="string" autocheck="true" />
	   				   <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"      onClick="selChann();"> 
					</td>
					<td width="8%" nowrap align="right" class="detail_label">加盟商名称:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="CHANN_NAME" name="CHANN_NAME" readonly value="${params.CHANN_NAME}"/>
					</td>
										
               </tr>
               <tr>
                    <td width="8%" nowrap align="right" class="detail_label">卡券序号从:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="CARD_SEQ_NO_BEGIN" name="CARD_SEQ_NO_BEGIN" value="${params.CARD_SEQ_NO_BEGIN}"/>
					</td>
					<td width="8%" nowrap align="right" class="detail_label">卡券序号从到:</td>
					<td width="15%" class="detail_content">
	   				   <input type="text" id="CARD_SEQ_NO_END" name="CARD_SEQ_NO_END" value="${params.CARD_SEQ_NO_END}"/>
					</td>
                    <td width="8%" nowrap align="right" class="detail_label">卡券编号:</td>
					 <td width="15%" class="detail_content">
					      <input type="text" id="MARKETING_CARD_NO" name="MARKETING_CARD_NO" value="${params.MARKETING_CARD_NO}"/>
					 </td>	
					 <td width="15%" class="detail_content" colspan="2">
					   <input type="radio" name="notallot" id="notallot_0"  value="-1" /> 显示全部
					   <input type="radio" name="notallot" id="notallot_1"  value="0"  /> 显示未分配
					   <input type="radio" name="notallot" id="notallot_2"  value="1" /> 显示已分配
					 </td>
               </tr>
               
               
               <c:if test="${pvg.PVG_TRANSFER eq 1}">
               <tr> 
                    <input type="hidden" id="transferFlag" label="转卡标记" value=""/>
                    <td width="8%"   align="center" style="text-align: center;" class="detail_label" colspan="2">
                      <input id="totransfer" type="button" class="btn" value="加盟商之间转卡" title="Alt+R" accesskey="R">
                    </td>
                   
                    <td width="8%" nowrap align="right" class="detail_label" name="c-td" >转卡后加盟商编号:</td>
					<td width="15%" class="detail_content" name="c-td">
					   <input type="hidden" id="TRAN_CHANN_ID" name="TRAN_CHANN_ID" value="${params.TRAN_CHANN_ID}"/>
	   				   <input type="text" id="TRAN_CHANN_NO" name="TRAN_CHANN_NO" value="${params.TRAN_CHANN_NO}"  inputtype="string" autocheck="true" /><span class="validate">*</span>
	   				   <img align="absmiddle" name="selJGXX" style="cursor: hand"  src="${ctx}/styles/${theme}/images/plus/select.gif"     
	   				    onClick="selTRANChann();"> 
					</td>
					<td width="8%" nowrap align="right" class="detail_label" name="c-td">转卡后加盟商名称:</td>
					<td width="15%" class="detail_content" name="c-td">
	   				   <input type="text" id="TRAN_CHANN_NAME" name="TRAN_CHANN_NAME" value="${params.TRAN_CHANN_NAME}"/>
					</td>
					<td width="15%" class="detail_content" name="c-td-l" id="c-td-l"  colspan="6"></td>
               </tr>
               </c:if>
               <tr>
					<td colspan="10" align="center" valign="middle">
						<input id="query" type="button" class="btn" value="查询(Q)" title="Alt+Q" accesskey="Q">&nbsp;&nbsp;
				    	<input id="allot" type="button" class="btn" value="分配(A)" title="Alt+A" accesskey="A">&nbsp;&nbsp;
				    	<input id="transfer" type="button" class="btn" value="确认转卡(T)" title="Alt+T" accesskey="T" style="display: none;">&nbsp;&nbsp;
				    	<input id="calcelTransfer" type="button" class="btn" value="取消转卡(T)" title="Alt+T" accesskey="T" style="display: none;">&nbsp;&nbsp;
					</td>
			   </tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>
