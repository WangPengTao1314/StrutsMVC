<!-- 
 *@module 系统管理
 *@func 终端信息
 *@version 1.1
 *@author  郭利伟
 *  -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>终端信息列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type="text/javascript" src="${ctx}/pages/base/terminal/Terminal_List.js"></script>
	<script type="text/javascript" src="${ctx}/pages/base/terminal/Calculator.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
	   	#edit_remark{
			margin: 0px auto; 
			width:350px;
			border: 1px;
			z-index:1;
			position:absolute;
			background-color: white;
			border:1px solid black;
			height:20px;
			left:230px;
			top:50px;
			text-align:center;
			display: block;
		}
	  
	
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td height="20">
		<table width="100%" cellspacing="0" cellpadding="0" >
		<tr>
			<td height="20px">&nbsp;&nbsp;当前位置：系统管理&gt;&gt;基础信息&gt;&gt;终端信息</td>
			<td width="50" align="right"></td>
		</tr>
	  </table>  
	</td>
</tr>
<tr>
	<td height="20">
		<div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
			<table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="100%" style="border:0px">
				<tr>
					<td nowrap>
					<c:if test="${params.IS_DRP_LEDGER eq 0}">
				   		<c:if test="${pvg.PVG_EDIT eq 1 }">
					   		<input id="add" type="button" class="btn" value="新增(N)" title="Alt+N" accesskey="N">
					   		<input id="modify" type="button" class="btn" value="修改(U)" title="Alt+U" accesskey="U">
				   		</c:if>
				   		<c:if test="${pvg.PVG_DELETE eq 1 }">
				   			<input id="delete" type="button" class="btn" value="删除(R)" title="Alt+R" accesskey="R">
				   		</c:if>
				   		<c:if test="${pvg.PVG_START_STOP eq 1 }">
				   			<input id="start" type="button" class="btn" value="启用(Q)" title="Alt+Q" accesskey="Q">	
				   			<input id="stop" type="button" class="btn" value="停用(T)" title="Alt+T" accesskey="T">
				  		 </c:if>	
					     <c:if test="${pvg.PVG_BWS eq 1 }">
					        <input id="expertExcel" type="button" class="btn" value="导出(U)" title="Alt+U" accesskey="U">
				   			<input id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">	
				  		 </c:if>
				  	</c:if>
				  		 <c:if test="${pvg.PVG_PRICE_EXPRESS eq 1 }">
				  		 <input id="calculator" type="button" class="btn" value="门店价格公式设置" title="Alt+F" accesskey="F">	
				  		 </c:if>
				  		 
				    <div id="edit_remark" style="display: none;" >
				    	<h4 align="center" style="margin-top: 5px" ondblclick="closeDiv();">门店价格公式设置</h4>
				    	<table style="margin-top: -10px;">
				    		<tr>
				    			<td >
			    			    <input type="button" style="width:30" style="height:25" value="+" id="Add" onclick="btShowValue(this);" />
			    			    <input type="button" style="width:30;height:25;" value="-" id="Sub" onclick="btShowValue(this);" />
			    			    <input type="button" style="width:30" style="height:25" value="*" id="Mud" onclick="btShowValue(this);" />
			    			    <%--
			    			    <input type="button" style="width:30" style="height:25" value="/" id="Div" onclick="BtDiv()" />
								--%>
								<input type="button" style="width:30" style="height:25" value="(" id="Mud" onclick="btShowValue(this);" />
								<input type="button" style="width:30" style="height:25" value=")" id="Mud" onclick="btShowValue(this);" />
								<input type="button" style="width:30" style="height:25" value="." id="Point" onclick="BtPoint()" />
								<input type="button" style="width:66" style="height:25" value="清除" id="Clean" onclick="BtClean()" />
								</td>
				    		</tr>
				    		<tr> 	
				    		 <td class="" colspan=" ">
								<input type="button" style="width:30;height:25" value="0" id="Zero" onclick="btShowValue(this);" />
								<input type="button" style="width:30;height:25" value="1" id="One" onclick="btShowValue(this);" />
								<input type="button" style="width:30;height:25" value="2" id="Two" onclick="btShowValue(this);" />
								<input type="button" style="width:30;height:25" value="3" id="Three" onclick="btShowValue(this);" />
								<input type="button" style="width:30;height:25" value="4" id="Four" onclick="btShowValue(this);" />
								<input type="button" style="width:30;height:25" value="5" id="Five" onclick="btShowValue(this);" />
								<input type="button" style="width:30;height:25" value="6" id="Six" onclick="btShowValue(this);" />
								<input type="button" style="width:33;height:25" value="7" id="Seven" onclick="btShowValue(this);" />
							  </td>
				    		</tr>
							<tr> 	
				    		<td class="" colspan=" ">	 
								<input type="button" style="width:30;height:25" value="8" id="Eight" onclick="btShowValue(this);" />
								<input type="button" style="width:30;height:25" value="9" id="Nine" onclick="btShowValue(this);" />
								<input type="button" style="width:75;height:25;margin-left: 10px;"  value="供货价" realvalue="NVL(c.PRVD_PRICE,0)" id="p" onclick="btShowValue(this);" />
								<input type="button" style="width:100;height:25;margin-left: 10px;" value="实际价格" realvalue="NVL(b.DECT_PRICE,0)" id="pr" onclick="btShowValue(this);" />
								<%--
								<input type="button" style="width:30" style="height:25" value="=" id="Equal" onclick="BtEqual()" />
								--%>
				    			</td>
				    		</tr>
				    	    <tr>
				    	     <td>
				    	      <textarea id="result" name="result" inputtype="string" oVal=""  style="color: #555;background-color: #eef;"
				    			  autocheck="true" maxlength="250" rows="4" readonly="readonly" cols="30">
				    		  </textarea>
				    		  <input type="hidden" id="PRICE_EXPRESS" name="PRICE_EXPRESS" value="" >
				    	     </td>
				    	    </tr>	 
				    	</table>
				    	<input style="margin-top: 3px;width:75;margin-bottom: 3px; " id="close" class="btn" type="button" value="测试" onclick="modifyExper(1);"/>
				    	<input style="margin-top: 3px;margin-left: 40px;width:75;margin-bottom: 3px;" id="close" class="btn" type="button" value="保存" onclick="modifyExper();"/>
				     </div>
				   
					</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td valign="top">
		<div class="lst_area" style="margin-left:3px;">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				<tr class="fixedRow">
					<th width="1%"></th>
					<th nowrap align="center" dbname="TERM_NO" >终端编号</th>
					<th nowrap align="center" dbname="TERM_NAME" >终端名称</th>
					<th nowrap align="center" dbname="TERM_ABBR" >终端简称</th>
					<th nowrap align="center" dbname="TERM_TYPE" >终端类型</th>
					<th nowrap align="center" dbname="TERM_LVL" >终端级别</th>
					<th nowrap align="center" dbname="CHANN_NAME_P" >上级渠道名称</th>
					<th nowrap align="center" dbname="AREA_NO" >区域编号</th>
					<th nowrap align="center" dbname="AREA_NAME" >区域名称</th>
					<th nowrap align="center" dbname="CRE_NAME" >制单人</th>
					<th nowrap align="center" dbname="CRE_TIME" >制单时间</th>
					<th nowrap align="center" dbname="DEPT_NAME" >制单部门</th>
					<th nowrap align="center" dbname="STATE" >状态</th>		
				</tr>
				<c:forEach items="${page.result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));$('#edit_remark').hide();">
						<td width="1%" align='center' >
							<input type="radio" style="height:12px;valign:middle" name="eid" id="eid${rr}" value="${rst.TERM_ID}"/>
						</td>
						<td align="center">${rst.TERM_NO }&nbsp;</td>
						<td align="center">${rst.TERM_NAME }&nbsp;</td>
						<td align="center">${rst.TERM_ABBR}&nbsp;</td>
						<td align="center">${rst.TERM_TYPE}&nbsp;</td>
						<td align="center">${rst.TERM_LVL}&nbsp;</td>
						<td align="center">${rst.CHANN_NAME_P}&nbsp;</td>
						<td align="center">${rst.AREA_NO}&nbsp;</td>
						<td align="center">${rst.AREA_NAME}&nbsp;</td>
						<td align="center">${rst.CRE_NAME}&nbsp;</td>
						<td align="center">${rst.CRE_TIME}&nbsp;</td>
						<td align="center">${rst.DEPT_NAME}&nbsp;</td>
						<td json='STATE' align="center">${rst.STATE}&nbsp;</td>
						<input type="hidden" name="PRICE_EXPRESS_CHS" id="PRICE_EXPRESS_CHS${rst.TERM_ID}" value="${rst.PRICE_EXPRESS_CHS}"/>
						<input type="hidden" name="PRICE_EXPRESS" id="PRICE_EXPRESS${rst.TERM_ID}" value="${rst.PRICE_EXPRESS}"/>
						
					</tr>
				</c:forEach>
				<c:if test="${empty page.result}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td height="12px" align="center">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" class="lst_toolbar">
			<tr>
				<td>
					<form id="pageForm" action="#" method="post" name="listForm">
					<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
						<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
						<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
						<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
						<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">
						<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
						<span id="hidinpDiv" name="hidinpDiv"></span>
						${paramCover.unCoveredForbidInputs } 
					</form>
				</td>
				<td align="right">
					 ${page.footerHtml}${page.toolbarHtml}
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#" name="queryForm">
<input type="hidden" name="selectParams" value=" STATE in( '启用')">
<input type="hidden" id="selectAreaManager" name="selectAreaManager" value=""/>
<input type="hidden" name="selectParamsArea" value="STATE in( '启用','停用') and DEL_FLAG='0' and AREA_NAME_P is null ">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td nowrap align="right" class="detail_label">终端编号：</td>
					<td class="detail_content">
						<input json="TERM_NO" name="TERM_NO" id="TERM_NO" type="text" label="终端编号" inputtype="string"  autocheck="true"  value="${params.TERM_NO}" >
						<input json="TERM_ID" name="TERM_ID" id="TERM_ID" type="hidden" label="终端ID">
						<img align="absmiddle" name="selTERM_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_27', false, 'TERM_ID', 'TERM_ID', 'queryForm','TERM_ID,TERM_NO,TERM_NAME,TERM_TYPE,TERM_LVL', 'TERM_ID,TERM_NO,TERM_NAME,TERM_TYPE,TERM_LVL')">
					</td>
					<td nowrap align="right" class="detail_label">终端名称：</td>
					<td class="detail_content">
						<input name="TERM_NAME" id="TERM_NAME" json="TERM_NAME" type="text"  value="${params.TERM_NAME }">
					</td>
					<td nowrap align="right" class="detail_label">终端类型：</td>
		      		<td nowrap class="detail_content">
						<select json="TERM_TYPE" id="TERM_TYPE" name="TERM_TYPE" style="width:145" inputtype="string" autocheck="true" ></select>
		      		</td>
		      		<td nowrap align="right" class="detail_label">终端级别：</td>
		      		<td nowrap class="detail_content">
						<select json="TERM_LVL" id="TERM_LVL" name="TERM_LVL" style="width:145" ></select>
		      		</td>
				</tr>
				<tr>
					<td nowrap align="right" class="detail_label">渠道编号：</td>
					<td class="detail_content">
						<input json="CHANN_NO_P" name="CHANN_NO_P" id="CHANN_NO_P" type="text" label="渠道编号" inputtype="string"  autocheck="true" value="${params.CHANN_NO_P}">
						<input json="CHANN_ID_P" name="CHANN_ID_P" id="CHANN_ID_P" type="hidden" label="渠道ID">
						<img align="absmiddle" name="selCHANN_NO_P" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'CHANN_ID_P', 'CHANN_ID', 'queryForm','CHANN_ID_P,CHANN_NO_P,CHANN_NAME_P', 'CHANN_ID,CHANN_NO,CHANN_NAME', 'selectParams')">
					</td>
					<td nowrap align="right" class="detail_label">渠道名称：</td>
					<td class="detail_content">
						<input json="CHANN_NAME_P" name="CHANN_NAME_P" id="CHANN_NAME_P" type="text"  value="${params.CHANN_NAME_P }">
					</td>
						<td nowrap align="right" class="detail_label">区域编号：</td>
					<td class="detail_content">
						<input json="AREA_ID" name="AREA_ID" id="AREA_ID" type="hidden" label="区域ID">
						<input json="AREA_NO" name="AREA_NO" id="AREA_NO" type="text"  inputtype="string"  autocheck="true" value="${params.AREA_NO}">
						<img align="absmiddle" name="selAREA_NO" style="cursor: hand"src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'queryForm','AREA_ID,AREA_NO,AREA_NAME', 'AREA_ID,AREA_NO,AREA_NAME', 'selectParams')">
					</td>
					<td nowrap align="right" class="detail_label">区域名称：</td>
		      		<td nowrap class="detail_content">
						<input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME" type="text" inputtype="string"  autocheck="true" value="${params.AREA_NAME}">
		      		</td>
				</tr>
				<tr>
					<td nowrap align="right" class="detail_label">制单时间从：</td>
		      		<td nowrap class="detail_content">
		            	<input type="text" json="CRE_TIME_FROM" id="CRE_TIME_FROM"name="CRE_TIME_FROM" autocheck="true" inputtype="string" value="${params.CRE_TIME_FROM}"
							label="日期" onclick="SelectTime();"  READONLY/>&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(CRE_TIME_FROM);"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">制单时间到：</td>
		      		<td nowrap class="detail_content">
		            	<input type="text" json="CRE_TIME_TO" id="CRE_TIME_TO"name="CRE_TIME_TO" autocheck="true" inputtype="string" value="${params.CRE_TIME_TO}" 
							label="日期" onclick="SelectTime();" READONLY />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectTime(CRE_TIME_TO);"/>
		      		</td>
		      		<td nowrap align="right" class="detail_label">状态：</td>
		      		<td nowrap class="detail_content">
						<select id="STATE" name="STATE" json="STATE" style="width:145" ></select>
		      		</td>	
		      		<td nowrap align="right" class="detail_label">营业状态：</td>
		      		<td nowrap class="detail_content">
		      			<select id="BUSS_STATE" name="BUSS_STATE" json="BUSS_STATE" style="width:145" ></select>
		      		</td>
				</tr>
				<tr>
					<td nowrap align="right" class="detail_label">战区：</td>
		      		<td nowrap class="detail_content">
		      			<input name="AREA_NAME_P" id="AREA_NAME_P" json="AREA_NAME_P" type="text"  value="${params.AREA_NAME_P }">
		      			<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_175', true, 'AREA_NAME_P', 'AREA_NAME', 'forms[1]',
												'AREA_NAME_P', 
												'AREA_NAME', 
												'selectParamsArea');">
		      		</td>
		      		<td nowrap align="right" class="detail_label">区域经理：</td>
		      		<td nowrap class="detail_content">
		      		    <input name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME" json="AREA_MANAGE_NAME" type="text"  value="${params.AREA_MANAGE_NAME}">
		      		    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
									onClick="selCommon('BS_167', true, 'AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'forms[1]','AREA_MANAGE_NAME', 'AREA_MANAGE_NAME', 'selectAreaManager')">						   
		      		</td>
		      		<td nowrap align="right" class="detail_label">开业日期从</td>
		      		<td nowrap class="detail_content">
		      		    <input type="text" json="BEG_SBUSS_DATE_BEG" id="BEG_SBUSS_DATE_BEG"name="BEG_SBUSS_DATE_BEG" autocheck="true" inputtype="string" value="${params.BEG_SBUSS_DATE_BEG}" 
							label="开店日期从" onclick="SelectDate();" READONLY />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectDate(BEG_SBUSS_DATE_BEG);"/>
		      		</td>	
		      		<td nowrap align="right" class="detail_label">开业日期到</td>
		      		<td nowrap class="detail_content">
		      		<input type="text" json="BEG_SBUSS_DATE_END" id="BEG_SBUSS_DATE_END"name="BEG_SBUSS_DATE_END" autocheck="true" inputtype="string" value="${params.BEG_SBUSS_DATE_END}" 
							label="开店日期到" onclick="SelectDate();" READONLY />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" 
							onclick="SelectDate(BEG_SBUSS_DATE_END);"/>
		      		</td>
				</tr>
				<tr>
				   <td width="10%" nowrap align="right" class="detail_label">省份：</td>
					 <td width="15%" class="detail_content">
	   					<input type="text" id="PROV" name="PROV" json="PROV"   value="${params.PROV}"/>
					    <img align="absmiddle" name="selSHIP" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"       
							onClick="selCommon('BS_113', true, 'PROV', 'PROV', 'forms[1]','PROV,CITY,COUNTY', 'PROV,CITY,COUNTY', '')">
					 </td>
					<td width="10%" nowrap align="right" class="detail_label">城市：</td>
					<td width="15%" class="detail_content">
						<input name="CITY" id="CITY" json="CITY" type="text" value="${params.CITY}">
					</td>
					
					<td width="10%" nowrap align="right" class="detail_label">区县：</td>
					<td width="15%" class="detail_content" colspan="3">
						<input name="COUNTY" id="COUNTY" json="COUNTY" type="text" value="${params.COUNTY}">
					</td>
				</tr>
				<tr>
					<td colspan="10" align="center" valign="middle">
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input  type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
<script type="text/javascript">
	SelDictShow("STATE","32","${params.STATE }","");
	SelDictShow("TERM_TYPE","175","${params.TERM_TYPE }","");
	SelDictShow("TERM_LVL","176","${params.TERM_LVL }","");	
	SelDictShow("BUSS_STATE","BS_181","${params.BUSS_STATE }","");
</script>
</body>
</html>

