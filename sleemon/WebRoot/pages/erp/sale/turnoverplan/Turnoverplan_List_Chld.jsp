<!--
 * @prjName:喜临门营销平台
 * @fileName:  发运管理 - 制定交付计划
 * @author zzb
 * @time   2013-10-12 13:52:19 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/turnoverplan/Turnoverplan_List_Chld.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<style type="text/css">
	  .text_underline{
			border-bottom-width:0px;
			border-bottom-style:double;
			border-top-width:0px;
			border-left-width:0px;
			border-right-width:0px;
			vertical-align:top;
		}
	</style>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area">
		 <form id="selForm">
		   <table id="qxBtnTb" cellSpacing=0 cellPadding=0 border=0 width="90%">
				<tr>
                 <td nowrap>
                  <div style="border:solid 1px #000; height: 70px; margin-left:10px; margin-top:10px;margin-bottom: 10px;" > 
                  <div style="height: 40px; width: 1200px;">
                   <table id="topTab"  width="100%" >
                    <tr >
                     <td width="5%"  >
                       <input type="hidden" id="AREA_SER_ID" name="AREA_SER_ID" json="AREA_SER_ID" />
		               <input type="hidden" id="AREA_SER_NO" name="AREA_SER_NO" json="AREA_SER_NO" />
		               <input type="hidden" id="AREA_SER_NAME" name="AREA_SER_NAME" json="AREA_SER_NAME" />
		               
		               <input type="hidden" id="RECV_CHANN_ID" name="RECV_CHANN_ID" json="RECV_CHANN_ID" value="" />
                       <input type="hidden" id="RECV_CHANN_NO" name="RECV_CHANN_NO" json="RECV_CHANN_NO" value="" />
                       <input type="hidden" id="RECV_CHANN_NAME" name="RECV_CHANN_NAME" json="RECV_CHANN_NAME" value="" />
                     
		               <input type="hidden" id="SHIP_POINT_ID" name="SHIP_POINT_ID" json="SHIP_POINT_ID" />
		               <input type="hidden" id="SHIP_POINT_NAME" name="SHIP_POINT_NAME" json="SHIP_POINT_NAME" />
		               <input type="hidden" id="ADVC_SEND_DATE" name="ADVC_SEND_DATE" json="ADVC_SEND_DATE" />
		               <input type="hidden" id="DELIVER_TYPE" name="DELIVER_TYPE" json="DELIVER_TYPE" value=""/>
		               <input type="hidden" id="TOTAL_VOLUME" name="TOTAL_VOLUME" json="TOTAL_VOLUME" value=""/>
		               <input type="hidden" id="REMARK" name="REMARK" json="REMARK" value=""/>
		               <input type="hidden" id="selectAddrParams" name=selectAddrParams value=" DEL_FLAG=0 and STATE='启用'">
                      <input type="radio" name="despatch" id="despatch_all" checked="checked"  value="整车发运"/>整车发运
                     </td>
                      
                     <td width="3.5%"  name="f_dis" >装载车型:</td>
                     <td  width="12%" name="f_dis" >
                         <select id="TRUCK_TYPE" json="TRUCK_TYPE" id="TRUCK_TYPE" style="width:100px;" name="TRUCK_TYPE" onchange="setVolum(this);"></select>
					     <span class=validate>&nbsp;*</span>
					     <span style="text-align: center;vertical-align:45%" id="car_VOLUMN"></span>
                         <input type="hidden" id="MIN_VOLUME" name="MIN_VOLUME">
                         <input type="hidden" id="MAX_VOLUME" name="MAX_VOLUME">
                     </td>
                     <%--
                     <td width="4%"  name="f_dis" > 可用容积: </td>
                     --%>
                     <td width="4%"  name="f_dis" > 货品总体积: </td>
                     <td width="6%"  name="f_dis" >
                        <span style="color: red;text-decoration:underline;margin-right:3px;" id="cuur_volum">0</span>&nbsp;(立方)
                     </td>
                     
                     <td width="5%"  name="f_dis" > 预计发货日期:</td>
                     <td width="8%" name="f_dis" > 
                         <input type="text" id="SEND_DATE_INT"   autocheck="true" readonly="readonly" name="SEND_DATE_INT"
								mustinput="true" label="预计发货日期" inputtype="string"   onclick="SelectDate();" size="10" />
								<%--<span class=validate>&nbsp;*</span>
                         --%><img align="absmiddle" style="cursor:hand;margin-left: 10px;" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SEND_DATE_INT);">
					     
					 </td>
					 <td width="4%"  name="f_dis" > </td>
					 <td width="1%" nowrap align="right">区域代发<input type="checkbox" name="CHANN_TYPE" id="CHANN_TYPE" json="CHANN_TYPE" onclick="showAddr()"  /></td>
					 <td width="4%" nowrap align="right" name="f_dis">收货地址：</td>
					 <td width="13%"  name="f_dis" >
					   <input type="hidden" id="USER_SELECT_ADDR" name="USER_SELECT_ADDR"  value=""/>
						   <input type="hidden" id="RECV_ADDR_NO" json="RECV_ADDR_NO" name="RECV_ADDR_NO"  value=""/>
	                       <input type="text"  id="RECV_ADDR" json="RECV_ADDR" name="RECV_ADDR"  autocheck="true" inputtype="string"  size="250"  style="width: 200px;" maxlength="250" readonly="readonly"  label="详细地址"  value="" />
	                       <img align="absmiddle" id="selAddr" name="selAddr" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onclick="selRevcAddr();">
					</td>
						   
                    </tr>
                   </table>
	                <hr style="margin-left: 20px;margin-right: 20px;">   
                  </div>
                  <div style="height: 30px;" >
                   <input type="radio" name="despatch"  id="despatch_consign"  value="托运"/>托运
                   <span style="margin-left: 10px;" name="consign"  >   
                                                               预计发货日期:
                   </span>
                   <span style="margin-left: 5px;width: 100px;" name="consign" >
                      <input type="text"   id="SEND_DATE_INP"   autocheck="true" name="SEND_DATE_INP" readonly="readonly" 
								mustinput="true" label="预计发货日期" inputtype="string" onclick="SelectDate();"/>
								<%--<span class=validate>&nbsp;*</span>
                      --%><img align="absmiddle" id="SEND_DATE_IMG"  style="cursor:hand;margin-left: 10px;" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(SEND_DATE_INP);">
                   </span>
                   <span style="margin-left: 60px;" > 
                     <input id="editRemark" type="button" class="btn" value="填写备注信息(S)" title="Alt+S" accesskey="S" onclick="showPage();">
                   </span> 
                   <span style="margin-left: 160px;" > 
                     <input id="create" type="button" class="btn" value="生成交付计划单(C)" title="Alt+D" accesskey="C">
                   </span> 
                  </div>
                  
                  <div style="height: 30px;" >
                   		&nbsp;&nbsp;总数量：<input id="allNum" name="allNum" size="8"readonly value=""  class="text_underline"/>
						总金额：<input id="allAmount" name="allAmount" size="15"  readonly class="text_underline" />
						总体积：<input id="allVolume" name="allVolume" size="15" readonly class="text_underline"/>
                  </div> 
                  
				  </div>
				  </td>
				</tr>
			</table>
			</form>
			  
			<form id="ordertbForm">
		    <input type="hidden" name="resultSize" id="resultSize" value="${resultSize}">
			<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
				<tr class="">
					<th nowrap align="center">
					<input type="checkbox" style="valign:middle" id="allChecked" >
					选择
					</th>
					<th  nowrap="nowrap" align="center" dbname="IS_BACKUP_FLAG" >是否抵库</th>
					<th  nowrap="nowrap" align="center" dbname="IS_FREE_FLAG" >赠品</th>
					<th  nowrap="nowrap" align="center" dbname="SALE_ORDER_NO" >订单编号</th>
					<th  nowrap="nowrap" align="center" dbname="ORDER_DATE" >订货日期</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NO" >货品编号</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_NAME" >货品名称</th>
                    <th  nowrap="nowrap" align="center" dbname="PAR_PRD_NAME" >货品分类</th>
                    <th  nowrap="nowrap" align="center" dbname="PRD_SPEC" >规格型号</th>
                    <th  nowrap="nowrap" align="center" dbname="SPCL_TECH_ID" >特殊工艺</th>
                    <th  nowrap="nowrap" align="center" dbname="IS_NO_STAND_FLAG" >是否非标</th>
                    <th  nowrap="nowrap" align="center" dbname="" >新规格</th>
                    <th  nowrap="nowrap" align="center" dbname="" >新花号</th>
                    <th  nowrap="nowrap" align="center" dbname="DECT_PRICE" >折后单价</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货数量</th>
                    <th  nowrap="nowrap" align="center" dbname="PLANED_NUM" >已排车</th>
                    <th  nowrap="nowrap" align="center" dbname="" >未排车</th>
                    <th  nowrap="nowrap" align="center" dbname="VOLUME" >未发体积(立方)</th>
                    <th  nowrap="nowrap" align="center" dbname="" >发运数量</th>
                    <th  nowrap="nowrap" align="center"  >发运金额</th>
                    <th  nowrap="nowrap" align="center" dbname="" >备注</th>
                    <th  nowrap="nowrap" align="center" dbname="ORDER_NUM" >订货金额</th>
				</tr>
				<c:forEach items="${rst}" var="rst" varStatus="row">
					<c:set var="r" value="${row.count % 2}"></c:set>
					<c:set var="rr" value="${row.count}"></c:set>
					<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)"  onclick="selectThis(this);setEidChecked(document.getElementById('eid${rr}')); countVolum();">
					 <td width="1%"align='center' >
						<input type="checkbox"  style="height:12px;valign:middle" name="mx_checkbox" id="eid${rr}" value="${rst.SALE_ORDER_DTL_ID}" onclick="setEidChecked(this);countVolum();"/>
					 </td>
					 <td nowrap="nowrap" align="center" >
					    <c:if test="${'1' eq rst.IS_BACKUP_FLAG}"> 是  </c:if>
					    <c:if test="${'1' ne rst.IS_BACKUP_FLAG}"> 否 </c:if>
					 </td>
					 <td nowrap="nowrap" align="center" >
					    <c:if test="${'1' eq rst.IS_FREE_FLAG}"> 是  </c:if>
					    <c:if test="${'1' ne rst.IS_FREE_FLAG}"> 否 </c:if>
					 </td>
					 <td nowrap="nowrap" align="center" >${rst.SALE_ORDER_NO}</td>
					 <td nowrap="nowrap" align="center" >${rst.ORDER_DATE}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_NO}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="left" >${rst.PAR_PRD_NAME}&nbsp;</td>
                     <td nowrap="nowrap" align="center" >${rst.PRD_SPEC}&nbsp;</td>
                     <td nowrap="nowrap" align="center" ><%--
	                     <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}">无</c:if>
	                     <c:if test="${rst.SPCL_TECH_FLAG ge 1}">
	                        <font color="red">有</font>
	                        <input type="button" class="btn" value="查看" onclick="selectTechPage('${rst.SPCL_TECH_ID}')" />
	                      </c:if>
                      	--%><span id="SPECIAL_FLAG${rr}" >
	                      <c:if test="${empty rst.SPCL_TECH_FLAG || rst.SPCL_TECH_FLAG eq 0}"> 无</c:if>
	                      <c:if test="${rst.SPCL_TECH_FLAG ge 1}"> 
	                       <c:if test="${!empty rst.SPCL_SPEC_REMARK }">
	                     		<label class="hideNoticeText" title="${rst.SPCL_SPEC_REMARK}">${rst.SPCL_SPEC_REMARK}</label>
	                     	</c:if>
	                       <input type="button" class="btn"  value="查看" onclick="selectTechPage('${rst.SPCL_TECH_ID}','${rst.PRICE}');"/>
	                      </c:if>
	                     </span>
                     </td>
                     <td  nowrap="nowrap" align="center">
                       <c:if test="${rst.IS_NO_STAND_FLAG eq 1}">是</c:if>
                        <c:if test="${rst.IS_NO_STAND_FLAG ne 1}">否</c:if>
                     </td>
                     <td nowrap="nowrap" align="right" >
                       <input type="hidden" id="" name="SPCL_TECH_FLAG"  value="${rst.SPCL_TECH_FLAG}" />
                       <input type="text" name="NEW_SPEC" lable="新规格" json="NEW_SPEC" inputtype="string" autocheck="true" maxlength="50"/><%--
                       <c:if test="${1 eq rst.SPCL_TECH_FLAG}"><span class=validate>&nbsp;*</span></c:if>
                     --%></td>
                     <td nowrap="nowrap" align="right" >
                     <input type="text" name="NEW_COLOR" lable="新花号" json="NEW_COLOR" inputtype="string" autocheck="true" maxlength="50"/><%--
                     <c:if test="${1 eq rst.SPCL_TECH_FLAG}"><span class=validate>&nbsp;*</span></c:if>
                     --%></td>
                     <td nowrap="nowrap" align="right" name="DECT_PRICE_TD">${rst.DECT_PRICE}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right"  name="PLANED_NUM_TD">${rst.PLANED_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_NUM - rst.PLANED_NUM}&nbsp;</td>
                     <td nowrap="nowrap" align="right" >
                       ${(rst.ORDER_NUM - rst.PLANED_NUM) * rst.VOLUME}
                       <input type="hidden" name="VOLUME" json="VOLUME" value="${rst.VOLUME}"/>
                       <input type="hidden" name="NO_SEND_NUM" json="NO_SEND_NUM" value="${rst.ORDER_NUM - rst.PLANED_NUM}"/>
                     </td>
                     <td nowrap="nowrap" align="center" >
                     <input type="hidden"  name="SALE_ORDER_DTL_ID" json="SALE_ORDER_DTL_ID"  value="${rst.SALE_ORDER_DTL_ID}"/>
                     <input type="hidden"  name="ORDER_NUM"   value="${rst.ORDER_NUM}"/>
                     
                     <input type="text" name="ADVC_PLAN_NUM" id="ADVC_PLAN_NUM${rr}" json="ADVC_PLAN_NUM" size="5" autocheck="true"
								mustinput="true" label="发运数量" textType="float" value="${rst.ORDER_NUM - rst.PLANED_NUM}" 
								onkeyup="countVolum(this);countNumPriceVolulmn();" onchange="countVolum();" onblur="countVolum();"/>
								
                     <input type="hidden" name="LAST_PLANED_NUM" json="LAST_PLANED_NUM" value="${rst.PLANED_NUM}"/>
                     <input type="hidden"  name="IS_FREE_FLAG" json="IS_FREE_FLAG"  label="是否赠品标记" value="${rst.IS_FREE_FLAG}" />
                     <!-- 明细的收货信息 -->
                      <input type="hidden" id="RECV_CHANN_ID_C" name="RECV_CHANN_ID_C" json="RECV_CHANN_ID" value="${rst.RECV_CHANN_ID}" />
                      <input type="hidden" id="RECV_CHANN_NO_C" name="RECV_CHANN_NO_C" json="RECV_CHANN_NO" value="${rst.RECV_CHANN_NO}" />
                      <input type="hidden" id="RECV_CHANN_NAME_C" name="RECV_CHANN_NAME_C" json="RECV_CHANN_NAME" value="${rst.RECV_CHANN_NAME}" />
                      <input type="hidden" id="RECV_ADDR_NO_C" name="RECV_ADDR_NO_C" json="RECV_ADDR_NO" value="${rst.RECV_ADDR_NO}" />
                      <input type="hidden" id="RECV_ADDR_C" name="RECV_ADDR_C" json="RECV_ADDR" value="${rst.RECV_ADDR}" />
                      
                     
                     </td>
                     <td nowrap="nowrap" align="right" name="ADVC_PLAN_NUM_AMOUNT_TD">
                      </td>
                     <td nowrap="nowrap" align="left" >
                      <input type="text" id="REMARK" size="31" name="REMARK" json="REMARK" value="${rst.REMARK}" oVal="" maxlength="250" onkeyup="changeTextAreaLength(this);"  onclick="checkedCheckBox(this);"/>
                     </td>
                     <td nowrap="nowrap" align="right" >${rst.ORDER_AMOUNT}&nbsp;</td>
				    </tr>
				</c:forEach>
				<c:if test="${empty rst}">
					<tr>
						<td height="25" colspan="22" align="center" class="lst_empty">
			                &nbsp;无相关记录&nbsp;
						</td>
					</tr>
				</c:if>
			</table>
			</form>
		</div>
	</td>
</tr>
</table>
 
</body>
<script type="text/javascript">
  SelDictShow("TRUCK_TYPE","BS_109","","");
</script>
</html>