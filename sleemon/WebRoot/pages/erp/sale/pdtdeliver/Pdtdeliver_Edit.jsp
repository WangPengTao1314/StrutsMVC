<!--
 * @prjName:喜临门营销平台
 * @fileName:发运管理 - 货品发运
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/pdtdeliver/Pdtdeliver_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td>当前位置：销售管理>>发运管理>>货品发运</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <input type="hidden" id="selSendChannParam" name="selSendChannParam" value=" STATE ='启用' and DEL_FLAG=0 "/>
       <input type="hidden" id="selParam" name="selParam" value=" STATE ='启用' and DEL_FLAG=0 and PRVD_TYPE='物流供应商'  "/>
       <input type="hidden" id="selectParam" name="selectParam" value=" STATE ='启用' and DEL_FLAG='0' and CHANN_TYPE='区域服务中心'">
       <input type="hidden" id="selDeliverParam" name="selDeliverParam" value=" STATE in('已完成交付','已提交库房','已提交生产') and DEL_FLAG=0 and DELIVER_ORDER_ID != '${rst.DELIVER_ORDER_ID}' and  RECV_CHANN_ID in ${CHANNS_CHARG} and JOIN_DELIVER_ORDER_NO  is not null ">
       
       
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
		     <table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			   <tr>
                   <td width="18%" align="right" class="detail_label">发运单号：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" name="DELIVER_ORDER_ID" id="DELIVER_ORDER_ID" json="DELIVER_ORDER_ID" value="${rst.DELIVER_ORDER_ID}"/>
                    <input json="DELIVER_ORDER_NO" name="DELIVER_ORDER_NO"   label="发运单号"  readonly="readonly" autocheck="true"  maxlength="32" inputtype="string" mustinput="true" type="text" value="${rst.DELIVER_ORDER_NO}"/> 
		          </td>
		          <td width="18%" align="right" class="detail_label">出货计划号：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" name="JOIN_DELIVER_ORDER_ID" id="JOIN_DELIVER_ORDER_ID" json="JOIN_DELIVER_ORDER_ID" value=""/>
                    <input json="JOIN_DELIVER_ORDER_NO" name="JOIN_DELIVER_ORDER_NO"   label="合并发运单号"  readonly="readonly" inputtype="string"  type="text" value="${rst.JOIN_DELIVER_ORDER_NO}"/> 
                     <img align="absmiddle" name="selJGXX" style="cursor: hand"
				       src="${ctx}/styles/${theme}/images/plus/select.gif"
				       onClick="selCommon('BS_117', false, 'JOIN_DELIVER_ORDER_ID', 'DELIVER_ORDER_ID', 'forms[0]','JOIN_DELIVER_ORDER_NO', 'JOIN_DELIVER_ORDER_NO', 'selDeliverParam')">
                    <%--<span style="color: red;">(合并发运单号)</span> 
		          --%></td>
		          
               </tr>
               <tr>
               <td width="15%" align="right" class="detail_label">预计发货日期：</td>
			      <td width="35%" class="detail_content">
                    <input json="ADVC_SEND_DATE" name="ADVC_SEND_DATE"  label="预计发货日期"  type="text" autocheck="true"  maxlength="32" inputtype="string" mustinput="true"   onclick="SelectDate();"  value="${rst.ADVC_SEND_DATE}"/> 
			       <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(ADVC_SEND_DATE);"/>
			     </td>
                 <td width="18%" align="right" class="detail_label">单据类型：</td>
				   <td width="35%" class="detail_content"  >
                    <input json="" name="BILL_TYPE"   label="单据类型"  readonly="readonly"  autocheck="true"  maxlength="20" inputtype="string" mustinput="true"  type="text" value="${rst.BILL_TYPE}"/> 
		          </td>
                  
               </tr>
               <tr>
                   <td width="18%" align="right" class="detail_label">物流公司编号：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" id="PRVD_ID" name="PRVD_ID" json="PRVD_ID" value="${rst.PRVD_ID}"/>
                    <input json="PRVD_NO" name="PRVD_NO" id="PRVD_NO"  label="物流公司编号"  readonly="readonly"  autocheck="true"  maxlength="30" inputtype="string" mustinput="true" type="text" value="${rst.PRVD_NO}"/> 
		            <img align="absmiddle" name="selJGXX" style="cursor: hand"
				       src="${ctx}/styles/${theme}/images/plus/select.gif"
				       onClick="selCommon('BS_25', false, 'PRVD_ID', 'PRVD_ID', 'forms[0]','PRVD_NO,PRVD_NAME', 'PRVD_NO,PRVD_NAME', 'selParam')">
		          </td>
                  <td width="15%" align="right" class="detail_label">物流公司名称：</td>
			      <td width="35%" class="detail_content">
                    <input json="PRVD_NAME" name="PRVD_NAME" id="PRVD_NAME"  label="物流公司名称" readonly="readonly"  type="text"  autocheck="true"  maxlength="100" inputtype="string" mustinput="true"    value="${rst.PRVD_NAME}"/> 
			     </td>
               </tr>
               <tr>
                  <td width="15%" align="right" class="detail_label">车型：</td>
			      <td width="35%" class="detail_content">
                    <input json="TRUCK_TYPE" name="TRUCK_TYPE"  label="车型" readonly="readonly"  type="text"   value="${rst.TRUCK_TYPE}"/> 
			      </td>
                  <td width="15%" align="right" class="detail_label">进场时间：</td>
			      <td width="35%" class="detail_content">
                     <input json="APPCH_TIME" name="APPCH_TIME" id="APPCH_TIME"  label="进场时间"   type="text"   autocheck="true"  maxlength="32" inputtype="string" mustinput="true" value="${rst.APPCH_TIME}" onclick="SelectTime();"/> 
			         <img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(APPCH_TIME);"/>
			     </td>
               </tr><%--
               <tr>
                  <td width="18%" align="right" class="detail_label">发货方编号：</td>
				   <td width="35%" class="detail_content">
				    <input type="hidden" id="SEND_CHANN_ID" name="SEND_CHANN_ID" json="SEND_CHANN_ID" value="${rst.SEND_CHANN_ID}"/>
                    <input json="SEND_CHANN_NO" id="SEND_CHANN_NO"  name="SEND_CHANN_NO"   label="发货方编号"  readonly="readonly"  autocheck="true"  maxlength="32" inputtype="string" readonly  mustinput="true" type="text" value="${rst.SEND_CHANN_NO}"/> 
		           <img align="absmiddle" name="selJGXX" style="cursor: hand"
				    src="${ctx}/styles/${theme}/images/plus/select.gif"
				    onClick="selCommon('BS_19', false, 'SEND_CHANN_ID', 'CHANN_ID', 'forms[0]','SEND_CHANN_NO,SEND_CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selSendChannParam')">
		           </td>
                  <td width="15%" align="right" class="detail_label">发货方名称：</td>
			      <td width="35%" class="detail_content">
                    <input json="SEND_CHANN_NAME" id="SEND_CHANN_NAME" name="SEND_CHANN_NAME"  label="发货方名称" readonly="readonly"  type="text"  autocheck="true"  maxlength="100" inputtype="string" mustinput="true" value="${rst.SEND_CHANN_NAME}"/> 
			     </td>
               </tr>
               --%><tr>
                   <%--<td width="18%" align="right" class="detail_label">发货类型：</td>
				   <td width="35%" class="detail_content">
                     <select id="CHANN_TYPE" style="width: 155px;"></select>
		          </td>
		          --%>
		          <td width="18%" align="right" class="detail_label">发货方式：</td>
				   <td width="35%" class="detail_content"  >
                    <input json="DELIVER_TYPE" name="DELIVER_TYPE"   label="发货方式"  readonly="readonly"  autocheck="true"  maxlength="20" inputtype="string" mustinput="true"  type="text" value="${rst.DELIVER_TYPE}"/> 
		          </td>
		          <td width="15%" align="right" class="detail_label">生产基地：</td>
			      <td width="35%" class="detail_content">
                    <input json="SHIP_POINT_NAME" name="SHIP_POINT_NAME"  label="生产基地" readonly="readonly"  autocheck="true"  maxlength="100" inputtype="string" mustinput="true" type="text"     value="${rst.SHIP_POINT_NAME}"/> 
			      </td>
			       
               </tr><%--
               <tr>
                  <td width="15%" align="right" class="detail_label">代发的区域服务中心编号：</td>
			      <td width="35%" class="detail_content">
			        <input type="hidden" id="AREA_SER_ID" name="AREA_SER_ID" json="AREA_SER_ID" value="${rst.AREA_SER_ID}"/>
                    <input json="AREA_SER_NO" name="AREA_SER_NO"  label="代发的区域服务中心编号" readonly="readonly"   type="text" autocheck="true"  maxlength="30" inputtype="string"   value="${rst.AREA_SER_NO}"/> 
			        <img align="absmiddle" name="selJGXX" style="cursor: hand"
				      src="${ctx}/styles/${theme}/images/plus/select.gif"
				      onClick="selCommon('BS_19', false, 'AREA_SER_ID', 'CHANN_ID', 'forms[0]','AREA_SER_NO,AREA_SER_NAME', 'CHANN_NO,CHANN_NAME', 'selectParam')">
					
			      </td>
			      <td width="15%" align="right" class="detail_label">代发的区域服务中心名称：</td>
			      <td width="35%" class="detail_content">
                    <input json="AREA_SER_NAME" name="AREA_SER_NAME"  label="代发的区域服务中心名称" readonly="readonly"   type="text"  autocheck="true"  maxlength="100" inputtype="string"   value="${rst.AREA_SER_NAME}"/> 
			      </td>
                  
               </tr>
			  --%><tr>
                   <td width="18%" align="right" class="detail_label">备注说明：</td>
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
                  <input id="back" type="button" class="btn" style="margin-left: 20px;" value="返回(B)" title="Alt+B" accesskey="B" onclick="gobacknew();">
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
SelDictShow("CHANN_TYPE","BS_58","${rst.CHANN_TYPE}","");
</script>
</html>

 