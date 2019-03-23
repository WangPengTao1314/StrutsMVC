<!--  
/**
 *@module 营销管理-广告投放管理
 *@func   广告投放报销申请单维护
 *@version 1.1
 *@author zcx
*/
-->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" 	href="${ctx}/styles/${theme}/css/style.css" />
	<script language="JavaScript"  src="${ctx}/pages/common/select/selCommJs.js"></script>
	<script type=text/javascript src="${ctx}/pages/drp/oamg/decorationapp/DecorationSQ_Edit.js"></script>
	<script type=text/javascript src="${ctx}/pages/drp/oamg/decorationapp/DecorationSQ_Edit_T.js"></script>	
	<script type="text/javascript" src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>装修申请单审核</title>
	<style type="text/css">
	    .query_divT{
			display:none;
			width:50%;
			filter:alpha(opacity=90);
			position:absolute;
			left:550;
			top:220;
			right:30%;
			bottom:45%;
		}
	</style>
	<script> 
	    function  closeT(){
          $("#showDiv").hide();
          $("#showDivT").hide();
        }
        
        function  funT(){
          // alert($("#rowcount").val());
        }
	</script>
</head>
<body class="bodycss1" onload="funT()">
<div id="showDiv" class="query_divT" style="display:none;">
           <table id="ordertb" width="30%" border="0" class="lst">
				<tr>
					<th nowrap align="center" dbname="CHANN_RNVTN_ID" >报销批次</th>
					<th nowrap align="center" dbname="RNVTN_SCALE" >报销比例</th>
					<th nowrap align="center" dbname="REIT_REMARK" >达成条件</th>
				</tr>
				<c:forEach items="${result}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
					    <td align="center">${row.count}&nbsp;</td>
					    <td align="center">${rst.DATA_DTL_DES_1}&nbsp;</td>
					    <td align="center">${rst.REMARK}&nbsp;</td>
					</tr>
			    </c:forEach>
				<tr>
					<td colspan="10" align="center" valign="middle">
						<input type="button" class="btn" id="btn1" value="确定(O)" title="Alt+O" accesskey="O" onclick="closeT()" />
                        <input type="button" class="btn" id="btn2" value="关闭(B)" title="Alt+B" accesskey="B" onclick="closeT()" />	
                    </td>
				</tr>
			</table>
</div>


<div id="showDivT" class="query_divT" style="display:none;">
           <input type="hidden" name="rowcount" id="rowcount" value="${rowcount}"/>
           <table id="ordertb" width="30%" border="0" class="lst">
				<tr>
					<th nowrap align="center" dbname="CHANN_RNVTN_ID" >报销批次</th>
					<th nowrap align="center" dbname="RNVTN_SCALE" >报销比例</th>
					<th nowrap align="center" dbname="REIT_REMARK" >达成条件</th>
				</tr>
				<c:choose>
				    <c:when test="${empty page.result}">
				         <tr>
					        <td align="center">
					             1
					        </td>
							<td align="center"><input type="text" name="RNVTN_SCALE" id="RNVTN_SCALE1" json="RNVTN_SCALE" size="10" onblur="check(this)"/>&nbsp;</td>
							<td align="center"><input type="text" name="REIT_REMARK" id="REIT_REMARK" json="REIT_REMARK" size"10" onblur="checkT('1')"/>&nbsp;</td>
					      </tr>
					      <tr>
					        <td align="center">
					             2
					        </td>
							<td align="center"><input type="text" name="RNVTN_SCALE" id="RNVTN_SCALE2" json="RNVTN_SCALE" size="10" onblur="check(this)"/>&nbsp;</td>
							<td align="center"><input type="text" name="REIT_REMARK" id="REIT_REMARK" json="REIT_REMARK" size"10" onblur="checkT('2')"/>&nbsp;</td>
					      </tr>
					      <tr>
					        <td align="center">
					             3
					        </td>
							<td align="center"><input type="text" name="RNVTN_SCALE" id="RNVTN_SCALE3" json="RNVTN_SCALE" size="10" onblur="check(this)"/>&nbsp;</td>
							<td align="center"><input type="text" name="REIT_REMARK" id="REIT_REMARK" json="REIT_REMARK" size"10" onblur="checkT('3')"/>&nbsp;</td>
					      </tr>
					      <tr>
					        <td align="center">
					             4
					        </td>
							<td align="center"><input type="text" name="RNVTN_SCALE" id="RNVTN_SCALE4" json="RNVTN_SCALE" size="10" onblur="check(this)"/>&nbsp;</td>
							<td align="center"><input type="text" name="REIT_REMARK" id="REIT_REMARK" json="REIT_REMARK" size"10" onblur="checkT('4')"/>&nbsp;</td>
					      </tr>
				    </c:when>
				    <c:otherwise>
				        <c:forEach items="${page.result}" var="rst" varStatus="row">
							<c:set var="r" value="${row.count % 2}"></c:set>
							<c:set var="rr" value="${row.count}"></c:set>
							<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" id="tr${rr}">
								<td align="center">${rst.RNVTN_REIT_NO}&nbsp;
								</td>
								<td align="center">${rst.RNVTN_SCALE}&nbsp;</td>
								<td align="center">${rst.REIT_REMARK}&nbsp;</td>
							</tr>
						</c:forEach>
						 <tr>
					        <td align="center">
					            ${rowcount+1}
					        </td>
							<td align="center"><input type="text" name="RNVTN_SCALE" id="RNVTN_SCALET1" json="RNVTN_SCALE" size="10" onblur="check(this)"/>&nbsp;</td>
							<td align="center"><input type="text" name="REIT_REMARK" id="REIT_REMARK" json="REIT_REMARK" size"10" onblur="checkT('1')"/>&nbsp;</td>
					      </tr>
					      <tr>
					        <td align="center">
					            ${rowcount+2}
					        </td>
							<td align="center"><input type="text" name="RNVTN_SCALE" id="RNVTN_SCALET2" json="RNVTN_SCALE" size="10" onblur="check(this)"/>&nbsp;</td>
							<td align="center"><input type="text" name="REIT_REMARK" id="REIT_REMARK" json="REIT_REMARK" size"10" onblur="checkT('2')"/>&nbsp;</td>
					      </tr>
					       <tr>
					        <td align="center">
					            ${rowcount+3}
					        </td>
							<td align="center"><input type="text" name="RNVTN_SCALE" id="RNVTN_SCALET3" json="RNVTN_SCALE" size="10" onblur="check(this)"/>&nbsp;</td>
							<td align="center"><input type="text" name="REIT_REMARK" id="REIT_REMARK" json="REIT_REMARK" size"10" onblur="checkT('3')"/>&nbsp;</td>
					      </tr>
					       <tr>
					        <td align="center">
					            ${rowcount+4}
					        </td>
							<td align="center"><input type="text" name="RNVTN_SCALE" id="RNVTN_SCALET4" json="RNVTN_SCALE" size="10" onblur="check(this)"/>&nbsp;</td>
							<td align="center"><input type="text" name="REIT_REMARK" id="REIT_REMARK" json="REIT_REMARK" size"10" onblur="checkT('4')"/>&nbsp;</td>
					      </tr>
					  </c:otherwise>
				    </c:choose>
					<tr>
						<td colspan="10" align="center" valign="middle">
							<input type="button" class="btn" id="commitT"  value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
	                        <input type="button" class="btn" id="btn3"     value="关闭(B)" title="Alt+B" accesskey="B" onclick="closeT()">				
	                    </td>
					</tr>
			</table> 
        </div>


<div style="height: 100">
	<div class="buttonlayer" id="floatDiv">
		<table cellSpacing=0 cellPadding=0 border=0 width="100%">
			<tr>
				<td align="left" nowrap>
					<input id="save" type="button" class="btn" value="保存(S)"
						title="Alt+S" accesskey="S">
					<input type="button" class="btn" value="返回(B)" title="Alt+B"
						accesskey="B" onclick="history.back();">
				</td>
			</tr>
		</table>
	</div>
	<!--浮动按钮层结束-->
	<!--占位用table，以免显示数据被浮动层挡住-->
	<table width="100%" height="25px">
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>
	</table>
	<form method="POST" action="#" id="mainForm">
	<input type="hidden" id="selectParams" name="selectParams" value="CHANN_NO='${CHANN_ID}'" />
		<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
			<tr>
				<td class="detail2">
					<table id="jsontb" width="100%" border="0" cellpadding="1"
						cellspacing="1" class="detail3">
						<tr>
							<td width="17%" align="right" class="detail_label">
								装修申请单号 ：
							</td>
							<td width="33%" class="detail_content">
								  <input json="CHANN_RNVTN_NO" name="CHANN_RNVTN_NO" id="CHANN_RNVTN_NO" type="text"
									 label="装修申请单号"
									 size="35" value="${rst.CHANN_RNVTN_NO}"  readonly inputtype="string"  mustinput="true"  disabled="true" />
							      <input json="CHANN_RNVTN_ID" id="CHANN_RNVTN_ID" json="CHANN_RNVTN_ID" type="hidden" value="${rst.CHANN_RNVTN_ID}"/>
							</td>
							<td width="17%" align="right" class="detail_label">
								申请人：
							</td>
							<td width="33%" class="detail_content">
								<input json="RNVTN_REQ_NAME" name="RNVTN_REQ_NAME" id="RNVTN_REQ_NAME" type="text"
									seltarget="selLL"   label="申请人"
									  size="35" value="${rst.RNVTN_REQ_NAME}" inputtype="string"  mustinput="true"  disabled="true">
							</td>
							</tr>
							<tr>
							<td width="17%" align="right" class="detail_label">
								申请日期：
							</td>
							<td width="33%" class="detail_content">
							     <input json="RNVTN_REQ_DATE" name="RNVTN_REQ_DATE" id="RNVTN_REQ_DATE" type="text"
									autocheck="true" valueType="chinese:false" label="申请日期"
									inputtype="string" size="35" value="${rst.RNVTN_REQ_DATE}"  inputtype="string"  mustinput="true" disabled="true"> 
							</td>
								<td width="17%" align="right" class="detail_label">
									状态：
								</td>
								<td width="33%" class="detail_content">
									<input json="STATE" name="STATE" id="STATE"
										type="text"  
										label="状态" size="35"
										value="${rst.STATE}" inputtype="string" disabled="true">
								</td>
							</tr>
							
							<tr>
							<td width="17%" align="right" class="detail_label">
								终端编号：
							</td>
							<td width="33%" class="detail_content">
			   					   <input json="TERM_ID" name="TERM_ID" id="TERM_ID"  label="终端信息ID" type="hidden" inputtype="string"   value="${rst.TERM_ID}"/>
			                       <input json="TERM_NO" name="TERM_NO" id="TERM_NO"  label="终端编号"   type="text"   inputtype="string"   size="35"  value="${rst.TERM_NO}" disabled="true" inputtype="string"     mustinput="true" />
			                       
								<img align="absmiddle" name="" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_82', false, 'TERM_ID', 'TERM_ID', 'forms[0]','TERM_ID,TERM_NO,TERM_NAME,CHANN_PERSON_CON,CHANN_TEL,AREA_ID,AREA_NO,AREA_NAME,CHANN_ID,CHANN_NO,CHANN_NAME,AREA_MANAGE_NAME,AREA_MANAGE_TEL,SALE_STORE_NAME,ZONE_ADDR,ADDRESS', 'TERM_ID,TERM_NO,TERM_NAME,CHANN_PERSON_CON,CHANN_TEL,AREA_ID,AREA_NO,AREA_NAME,CHANN_ID,CHANN_NO,CHANN_NAME,AREA_MANAGE_NAME,AREA_MANAGE_TEL,SALE_STORE_NAME,ZONE_ADDR,ADDRESS', 'selectParams')">
 							</td>
								<td width="17%" align="right" class="detail_label">
									终端名称：
								</td>
								<td width="33%" class="detail_content">
								   <input id="TERM_NAME" name="TERM_NAME" json="TERM_NAME" type="text" inputtype="string" size="35" value="${rst.TERM_NAME}" disabled="true"/>
								</td>
							</tr>
							
							<tr>
							<td width="17%" align="right" class="detail_label">
								渠道编号：
							</td>
							<td width="33%" class="detail_content">
								<input json="CHANN_ID" name="CHANN_ID" id="CHANN_ID"
									type="hidden" size="40" value="${rst.CHANN_ID}" />
								<input json="CHANN_NO" name="CHANN_NO" inputtype="string"
									id="CHANN_NO" type="text" size="35" value="${rst.CHANN_NO}"
									disabled="true" />
							</td>
							<td width="17%" align="right" class="detail_label">
							    渠道名称：
								</td>
								<td width="33%" class="detail_content">
									<input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME"
										type="text" label="渠道名称"  size="35"
										value="${rst.CHANN_NAME}" inputtype="string" disabled="true"/>
								</td>
							</tr>
							
							<tr>
							<td width="17%" align="right" class="detail_label">
								渠道联系人：
							</td>
							<td width="33%" class="detail_content">
								<input json="CHANN_PERSON_CON" name="CHANN_PERSON_CON" id="CHANN_PERSON_CON" type="text"
								 label="渠道联系人" size="35" value="${rst.CHANN_PERSON_CON}" inputtype="string" disabled="true"/>
							</td>
								<td width="17%" align="right" class="detail_label">
							     渠道联系电话：
								</td>
								<td width="33%" class="detail_content">
									<input json="CHANN_TEL" name="CHANN_TEL" id="CHANN_TEL"
										type="text" label="渠道联系电话" size="35" inputtype="string" value="${rst.CHANN_TEL}" disabled="true"/>
								</td>
							</tr>
							
							<tr>
							<td width="17%" align="right" class="detail_label">
								所属战区编号：
							</td>
							<td width="33%" class="detail_content">
							    <input json="AREA_ID" name="AREA_ID" id="AREA_ID" value="${rst.AREA_ID}" type="hidden"/>
								<input json="AREA_NO" name="AREA_NO" id="AREA_NO" inputtype="string" type="text"label="所属战区编号" size="35" value="${rst.AREA_NO}" disabled="true"/>
							</td>
								<td width="17%" align="right" class="detail_label">
							     所属战区名称：
								</td>
								<td width="33%" class="detail_content">
									<input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME"
										type="text"  
										label="所属战区名称"   size="35"
										value="${rst.AREA_NAME}" inputtype="string" disabled="true"/>
								</td>
							</tr>
							
							
							<tr>
							<td width="17%" align="right" class="detail_label">
								区域经理：
							</td>
							<td width="33%" class="detail_content">
								<input json="AREA_MANAGE_NAME" name="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME" type="text"
									 label="区域经理"
									 size="35" value="${rst.AREA_MANAGE_NAME}" inputtype="string" disabled="true"/>
							</td>
								<td width="17%" align="right" class="detail_label">
							     区域经理电话：
								</td>
								<td width="33%" class="detail_content">
									<input json="AREA_MANAGE_TEL" name="AREA_MANAGE_TEL" id="AREA_MANAGE_TEL"
										type="text"  
										label="区域经理电话"   size="35"
										value="${rst.AREA_MANAGE_TEL}" inputtype="string" disabled="true"/>
								</td>
							</tr>
							
							<tr>
							<td width="17%" align="right" class="detail_label">
								卖场名称：
							</td>
							<td width="33%" class="detail_content">
								<input json="SALE_STORE_NAME" name="SALE_STORE_NAME" id="SALE_STORE_NAME" type="text"
									 label=卖场名称"
									 size="35" value="${rst.SALE_STORE_NAME}" inputtype="string" disabled="true"/>
							</td>
								<td width="17%" align="right" class="detail_label">
							     行政区域：
								</td>
								<td width="33%" class="detail_content">
									<input json="ZONE_ADDR" name="ZONE_ADDR" id="ZONE_ADDR"
										type="text" 
										label="行政区域" size="35"
										value="${rst.ZONE_ADDR}" inputtype="string" disabled="true"/>
								</td>
							</tr>
							
								<tr>
							<td width="17%" align="right" class="detail_label">
								详细地址：
							</td>
							<td width="33%" class="detail_content" colspan="3">
								<input json="ADDRESS" name="ADDRESS" id="ADDRESS" type="text"
									label="详细地址" size="35" value="${rst.ADDRESS}" disabled="true" />
							</td>
						</tr>

						<tr>
						
							<td width="17%" align="right" class="detail_label">
								经营品牌：
							</td>
							<td width="33%" class="detail_content">
								<select id="BUSS_SCOPE" name="BUSS_SCOPE" json="BUSS_SCOPE"
									     style="width: 245px;" inputtype="string" autocheck="true" label="经营品牌" onchange="getMsg()" disabled="true" value="${rst.BUSS_SCOPE}">
								 </select>
							</td> 
							<td width="17%" align="right" class="detail_label">
								装修性质：
							</td>
							<td width="33%" class="detail_content">
								<select id="RNVTN_PROP" name="RNVTN_PROP" json="RNVTN_PROP" style="width: 245px;"
								 	label="装修性质" inputtype="string" autocheck="true" mustinput="true" value="${rst.RNVTN_PROP}" disabled="disabled">
							    </select>	
							</td>
						</tr>


						<tr>
							<td width="17%" align="right" class="detail_label">
								计划开业时间：
							</td>
							<td width="33%" class="detail_content">
								<input type="text" id="PLAN_SBUSS_DATE" name="PLAN_SBUSS_DATE"
									json="PLAN_SBUSS_DATE" value="${rst.PLAN_SBUSS_DATE}" inputtype="string" autocheck="true"
									json="PLAN_SBUSS_DATE" onclick="SelectDate();" size="35" inputtype="string"  onblur="CompareDate()" disabled="true"/>
								<img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
									onclick="SelectDate(PLAN_SBUSS_DATE);">

							</td>
							<td width="17%" align="right" class="detail_label">
								要求出图时间：
							</td>
							<td width="33%" class="detail_content">
								<input type="text" id="DMD_DRAW_DATE" value="${rst.DMD_DRAW_DATE}" name="DMD_DRAW_DATE"
									json="DMD_DRAW_DATE" onclick="SelectDate();"  onblur="CompareDateT()"
									size="35" value="${rst.DMD_DRAW_DATE}"  inputtype="string" autocheck="true" disabled="true"/>
								<img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
									onclick="SelectDate(DMD_DRAW_DATE);">
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								上报使用面积(不含公摊)(平米)：
							</td>
							<td width="33%" class="detail_content">
								<input json="USE_AREA" name="USE_AREA" id="USE_AREA" type="text"
									autocheck="true" inputtype="float" maxlength="11" valueType="8,2" label="上报使用面积" size="35" value="${rst.USE_AREA}" onblur="getMsg()" disabled="true"/>
							</td>
							<td width="17%" align="right" class="detail_label">
								一场多店信息：
							</td>
							<td width="33%" class="detail_content">
							     <select id="TERM_WHICH_NUM" name="TERM_WHICH_NUM" json="TERM_WHICH_NUM"  style="width: 245px;"  value="${rst.TERM_WHICH_NUM}" onchange="getMsg()" disabled="disabled">
								 </select>
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								商场租金(元/平米)：
							</td>
							<td width="33%" class="detail_content">
								<input json="MARKET_RENT" name="MARKET_RENT" id="MARKET_RENT"
									type="text" label="商场租金" value="${rst.MARKET_RENT}" autocheck="true" inputtype="float" maxlength="11" valueType="8,2"  size="35"  disabled="true"/>
							</td>
							
							<td width="17%" align="right" class="detail_label">
								预计年销量(万元)：
							</td>
							<td width="33%" class="detail_content">
							    <input json="EXP_YEAR_SALE" name="EXP_YEAR_SALE" id="EXP_YEAR_SALE" 
							        type="text" label="预计年销量" size="35" value="${rst.EXP_YEAR_SALE}" disabled="true"/>
							</td>
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								合同签订金额(万元)：
							</td>
							<td width="33%" class="detail_content">
								<c:choose>  
								   <c:when test="${pvg.PVG_COMPACT_AMOUNT eq 1 }">
								      <input json="COMPACT_AMOUNT" name="COMPACT_AMOUNT" id="COMPACT_AMOUNT"
									      type="text" label="合同签订金额" size="35" value="${rst.COMPACT_AMOUNT}" />
								   </c:when>  
								    <c:otherwise>  
								       <input json="COMPACT_AMOUNT" name="COMPACT_AMOUNT" id="COMPACT_AMOUNT"
									      type="text" label="合同签订金额" size="35" value="${rst.COMPACT_AMOUNT}" disabled="true"/>
								    </c:otherwise>  
							   </c:choose>
							</td>
							<td width="17%" align="right" class="detail_label">
								报销频次： 
							</td>
							<td width="33%" class="detail_content">
							<c:choose>  
								   <c:when test="${pvg.PVG_REIT_POLICY eq 1 }">
								         <select id="REIT_POLICY" name="REIT_POLICY"
											json="REIT_POLICY" style="width: 165px;" value="${rst.REIT_POLICY}">
											</select>&nbsp;<input type="button" name="btn" id="btn" value="政策明细" onclick="fun()"/>
								   </c:when>  
								    <c:otherwise>  
								         <select id="REIT_POLICY" name="REIT_POLICY"
									json="REIT_POLICY" style="width: 165px;"
									value="${rst.REIT_POLICY}" disabled="disabled"></select>&nbsp;<input type="button" name="btn" id="btn" value="政策明细" disabled="disabled"/>
								    </c:otherwise>  
							   </c:choose>
							</td>
						</tr>

						<tr>
						<td width="17%" align="right" class="detail_label">
								商场位置类别：
							</td>
							<td width="33%" class="detail_content">
							  <c:choose>  
								   <c:when test="${pvg.PVG_LOCAL_TYPE eq 1 }">
								       <select id="LOCAL_TYPE" name="LOCAL_TYPE" json="LOCAL_TYPE"
									      style="width: 245px;"  onchange="total(this)"  value="${rst.LOCAL_TYPE}">
									   </select>
								   </c:when>  
								    <c:otherwise>  
								       <select id="LOCAL_TYPE" name="LOCAL_TYPE" json="LOCAL_TYPE"
									      style="width: 245px;" value="${rst.LOCAL_TYPE}"  value="${rst.LOCAL_TYPE}" disabled="disabled">
									      </select>
								    </c:otherwise>  
							   </c:choose>
							</td> 
							<td width="17%" align="right" class="detail_label">是否标准内：</td>
							<td width="33%" class="detail_content">
			                    <c:choose>  
								   <c:when test="${pvg.PVG_IS_STAD_FLAG eq 1 }">
								        <c:if test="${rst.IS_STAD_FLAG eq 1}">
								           <input type="radio" name="radio" json="IS_STAD_FLAG"  id="IS_STAD_FLAG" value="1" checked="true"/>是&nbsp;&nbsp;&nbsp;&nbsp;
								           <input type="radio" json="IS_STAD_FLAG" id="IS_STAD_FLAG" name="radio" value="0"/>否
								        </c:if>
								        <c:if test="${rst.IS_STAD_FLAG eq 0}">
								           <input type="radio" name="radio" json="IS_STAD_FLAG"  id="IS_STAD_FLAG" value="1"/>是&nbsp;&nbsp;&nbsp;&nbsp;
								           <input type="radio" json="IS_STAD_FLAG" id="IS_STAD_FLAG" name="radio" value="0" checked="true"/>否
								        </c:if>
								   </c:when>  
								    <c:otherwise>  
								         <input type="radio" name="radio" json="IS_STAD_FLAG" id="IS_STAD_FLAG" value="1"  disabled="true"/><font color="gray">是</font>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" json="IS_STAD_FLAG" id="IS_STAD_FLAG" name="radio" value="0" disabled/><font color="gray">否</font>
								    </c:otherwise>  
							   </c:choose>
							</td> 
						</tr>
						<tr>
						  
							<td width="17%" align="right" class="detail_label">
								商场综合情况分析：
							</td>
							<td width="33%" class="detail_content">
								<textarea json="SALE_STORE_ANALYSE" name="SALE_STORE_ANALYSE"
									id="SALE_STORE_ANALYSE" label="商场综合情况分析" rows="5" cols="28%" autocheck="true" inputtype="string"   maxlength="250" disabled="true">${rst.SALE_STORE_ANALYSE}</textarea>
							</td> 
							<td width="17%" align="right" class="detail_label">专卖店相关资料：</td>
							<td width="33%" class="detail_content">
			                     <input id="PIC_PATH" json="PIC_PATH" name="PIC_PATH"  type="hidden" value="${rst.ATT_PATH}" disabled="true"/> 
							</td> 
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								<!-- 报销金额(元/平米)： -->
								每平米标准参考报销金额(元)
							</td>
							<td width="33%" class="detail_content">
								<c:choose>  
								   <c:when test="${pvg.REIT_AMOUNT_PS eq 1 }">
								       <input json="REIT_AMOUNT_PS" name="REIT_AMOUNT_PS"
											id="REIT_AMOUNT_PS" type="text" label="每平米参考报销金额" size="35"
											value="${rst.REIT_AMOUNT_PS}" />
								   </c:when>  
								    <c:otherwise>  
								       <input json="REIT_AMOUNT_PS" name="REIT_AMOUNT_PS"
											id="REIT_AMOUNT_PS" type="text" label="每平米参考报销金额" size="35"
											value="${rst.REIT_AMOUNT_PS}"  disabled="disabled" inputtype="float" valueType="10,2" />
								    </c:otherwise>  
							   </c:choose>
							</td>
							<td width="17%" align="right" class="detail_label">总标准参考报销金额(元)：</td>
							<td width="33%" class="detail_content">
			                     <c:choose>  
								   <c:when test="${pvg.REIT_AMOUNT eq 1 }">
								       <input id="REIT_AMOUNT" json="REIT_AMOUNT" name="REIT_AMOUNT"  type="text"   value="${rst.REIT_AMOUNT}"
			                             size="35" label="总标准参考报销金额"/> 
								   </c:when>  
								    <c:otherwise>  
								       <input id="REIT_AMOUNT" json="REIT_AMOUNT" name="REIT_AMOUNT"  type="text"   value="${rst.REIT_AMOUNT}"
			                             size="35" label="总标准参考报销金额" disabled="disabled" inputtype="float" valueType="10,2" />
								    </c:otherwise>  
							   </c:choose>
							</td> 
						</tr>

						<tr>
							<td width="17%" align="right" class="detail_label">
								特殊说明：
							</td>
							<td width="33%" class="detail_content" colspan="3">
								<textarea json="REMARK" name="REMARK" id="REMARK" label="特殊说明"
									rows="5" cols="28%" autocheck="true" inputtype="string"   maxlength="250" disabled="true">${rst.REMARK}</textarea>
							</td>
							<td>
						       <input type="hidden" id="minArea" name="minArea"/>
	                           <input type="hidden" id="stdArea" name="stdArea"/>
	                           <input type="hidden" id="amount"  name="amount" />
	                           <input type="hidden" id="dtldes"  name="dtldes" />
	                        </td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
	<script type="text/javascript">
       SelDictShow("LOCAL_TYPE","BS_86","${rst.LOCAL_TYPE}","");
       SelDictShow("RNVTN_PROP","BS_87","${rst.RNVTN_PROP}","");
       SelDictShow("BUSS_SCOPE","BS_88","${rst.BUSS_SCOPE}","");
       SelDictShow("TERM_WHICH_NUM","BS_89","${rst.TERM_WHICH_NUM}","");
       SelDictShow("REIT_POLICY","BS_90","${rst.REIT_POLICY}","");
       //uploadFile('PIC_PATH', 'SAMPLE_DIR', true,true,true);
   </script>
  </div>
</body>



