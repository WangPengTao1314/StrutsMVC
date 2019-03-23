<!--
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 交付计划维护
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
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<script type="text/javascript" src="${ctx}/pages/erp/sale/deliveryhd/Deliveryhd_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td>当前位置：销售管理>>发运管理>>出货计划维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <input name="shipParam" type="hidden" value=" STATE = '启用' and DEL_FLAG = 0  "/>
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
		     <table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <tr>
                   <td width="18%" align="right" class="detail_label">出货计划号：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" name="DELIVER_ORDER_ID" id="DELIVER_ORDER_ID" json="DELIVER_ORDER_ID" value="${rst.DELIVER_ORDER_ID}"/>
                    <input json="DELIVER_ORDER_NO" name="DELIVER_ORDER_NO"   label="出货计划号"  readonly="readonly"  type="text" value="${rst.DELIVER_ORDER_NO}"/> 
		          </td>
		          <td width="18%" align="right" class="detail_label">单据类型：</td>
				   <td width="35%" class="detail_content">
                    <input json="BILL_TYPE" name="BILL_TYPE"   label="单据类型"  readonly="readonly"  type="text" value="${rst.BILL_TYPE}"/> 
		          </td>
               </tr>
               <tr>
                   <td width="18%" align="right" class="detail_label">发货方式：</td>
				   <td width="35%" class="detail_content">
                    <input json="DELIVER_TYPE" name="DELIVER_TYPE"   label="发货方式"  readonly="readonly"   type="text" value="${rst.DELIVER_TYPE}"/> 
		          </td>
                  <td width="15%" align="right" class="detail_label">车型：</td>
			      <td width="35%" class="detail_content">
                    <select  name="TRUCK_TYPE" json="TRUCK_TYPE" id="TRUCK_TYPE" style="width: 150px;" label="车型" autocheck="true" mustinput="true" inputtype="string" ></select> 
			     </td>
               </tr>
               <tr>
               	 <td width="15%" align="right" class="detail_label">车辆进场时间：</td>
			      <td width="35%" class="detail_content">
                    <input json="APPCH_TIME" name="APPCH_TIME" id="APPCH_TIME"  label="进场时间" style="width: 150px;" readonly="readonly"  type="text"   autocheck="true"  maxlength="32" inputtype="date" mustinput="true" value="${rst.APPCH_TIME}" onclick="SelectTime();"/> 
			    	<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(APPCH_TIME);"/> 
			     </td>
                  <td width="15%" align="right" class="detail_label">生产基地：</td>
			      <td width="35%" class="detail_content">
                    <input json="SHIP_POINT_NAME" name="SHIP_POINT_NAME"  label="生产基地" readonly="readonly"   type="text"     value="${rst.SHIP_POINT_NAME}"/> 
                    <input json="SHIP_POINT_ID" name="SHIP_POINT_ID"  label="生产基地id"   type="hidden"     value="${rst.SHIP_POINT_ID}"/>
			     	<c:if test="${pvg.PVG_SHIP_EDIT eq 1}">
                       <img align="absmiddle" name="" style="cursor: hand"
							src="${ctx}/styles/${theme}/images/plus/select.gif"
			      			onClick="selCommon('BS_106', false, 'SHIP_POINT_ID', 'SHIP_POINT_ID', 'forms[0]','SHIP_POINT_ID,SHIP_POINT_NAME','SHIP_POINT_ID,SHIP_POINT_NAME', 'shipParam');"  >
			      </c:if>
			     </td>
               </tr>
			  <tr>
                   <td width="18%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="3">
				    <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"  
				       maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
		          </td>
               </tr>
               <tr>
                <td></td>
               <td>
               <span style="margin-left: 200px;">
                  <input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
                  <input id="back" type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();" style="margin-left: 20px;" >
               </span>
               </td>
              
               </tr>
			 </table>
		</td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
	SelDictShow("TRUCK_TYPE","BS_109","${rst.TRUCK_TYPE}","");
</script>
</html>

 