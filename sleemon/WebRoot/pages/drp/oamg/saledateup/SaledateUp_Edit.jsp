<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:分销-销售数据上报
 * @author zzb
 * @time   2014-11-12 10:35:10 
 * @version 1.1
 */
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
	<script type="text/javascript" src="${ctx}/pages/drp/oamg/saledateup/SaledateUp_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：销售管理>>销售数据上报>>销售数据上报维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
       <input type="hidden" id="selectAddrParams" name=selectAddrParams value=" DEL_FLAG=0 and STATE='启用' and CHANN_ID='${rst.RECV_CHANN_ID}' ">
       <input type="hidden" id="selChannParam" name="selChannParam" value=" STATE ='启用' and DEL_FLAG=0 and CHANN_ID IN ${CHANN_ID}"/>
       <input type="hidden" id="selTermParam" name="selTermParam" />
       <input type="hidden" id="CHANNS_CHARG" name="CHANNS_CHARG" value="${CHANN_ID}">
        
       <table width="100%"  border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <tr>
                   <td width="18%" align="right" class="detail_label">销售数据上报编号：</td>
                   <td width="35%" class="detail_content">
                     <input json="SALE_DATE_UP_NO" name="SALE_DATE_UP_NO" autocheck="true" label="销售数据上报编号"  type="text"   inputtype="string"   readonly    mustinput="true"  
                     	<c:if test="${empty rst.SALE_DATE_UP_NO }"> value="自动生成"</c:if>
                     	<c:if test="${not empty rst.SALE_DATE_UP_NO}">value="${rst.SALE_DATE_UP_NO}"</c:if>
                     	/> 
				   </td>
				   <td width="18%" align="right" class="detail_label"></td>
                   <td width="35%" class="detail_content"></td>
			   </tr>
			   <tr>
                   <td width="18%" align="right" class="detail_label">渠道编号：</td>
				    <td width="35%" class="detail_content">
				     <input type="hidden" json="CHANN_ID_OLD"  id="CHANN_ID_OLD" name="CHANN_ID_OLD" value="${rst.CHANN_ID}" />
				     <input type="hidden" json="CHANN_ID"  id="CHANN_ID" name="CHANN_ID" value="${rst.CHANN_ID}" />
	                 <input json="CHANN_NO" name="CHANN_NO"   label="渠道编号"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.CHANN_NO}"/> 
		             <img align="absmiddle" name="selJGXX" style="cursor: hand"
					   src="${ctx}/styles/${theme}/images/plus/select.gif"       
					   onClick="selCommon('BS_136', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_NO,CHANN_NAME,WAREA_ID,WAREA_NO,WAREA_NAME,AREA_ID,AREA_NO,AREA_NAME', 'CHANN_NO,CHANN_NAME,AREA_ID_P,AREA_NO_P,AREA_NAME_P,AREA_ID,AREA_NO,AREA_NAME', 'selChannParam');changeChann();"> 
				   </td>
	               <td width="15%" align="right" class="detail_label">渠道名称：</td>
				   <td width="35%" class="detail_content">
	                    <input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" label="渠道名称"  type="text"  autocheck="true" inputtype="string"  readonly="readonly"   mustinput="true"     maxlength="100"  value="${rst.CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
                <td width="18%" align="right" class="detail_label">终端编号：</td>
			    <td width="35%" class="detail_content">
			        <input type="hidden" json="TERM_ID"  id="TERM_ID" name="TERM_ID" value="${rst.TERM_ID}" />
                    <input json="TERM_NO" name="TERM_NO" id="TERM_NO"   label="终端编号"  autocheck="true"  inputtype="string"  readonly="readonly"   mustinput="true"  maxlength="30"  type="text" value="${rst.TERM_NO}"/> 
	               <img align="absmiddle" name="selJGXX" style="cursor: hand"
				   src="${ctx}/styles/${theme}/images/plus/select.gif"       
				   onClick="selectTerm();"/>
			   </td>
               <td width="15%" align="right" class="detail_label">终端名称：</td>
			   <td width="35%" class="detail_content">
                    <input json="TERM_NAME" name="TERM_NAME" id="TERM_NAME"  label="终端名称"  type="text"  autocheck="true" inputtype="string"  readonly="readonly"   mustinput="true"     maxlength="100"  value="${rst.TERM_NAME}"/> 
			   </td>
               </tr>
               
                <tr>
                   <td width="18%" align="right" class="detail_label">战区编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" id="WAREA_ID" name="WAREA_ID" json="WAREA_ID" value="${rst.WAREA_ID}"/>
                     <input json="WAREA_NO" name="WAREA_NO" id="WAREA_NO" label="战区编号" inputtype="string"   type="text" readonly value="${rst.WAREA_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">战区名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="WAREA_NAME" name="WAREA_NAME" id="WAREA_NAME"  label="战区名称"  type="text"  inputtype="string"  readonly   value="${rst.WAREA_NAME}"/> 
				   </td>
                </tr>
                <tr>
                   <td width="18%" align="right" class="detail_label">区域编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" id="AREA_ID" name="AREA_ID" json="AREA_ID" value="${rst.AREA_ID}"/>
                     <input json="AREA_NO" name="AREA_NO" id="AREA_NO" label="区域编号" inputtype="string"   type="text"  readonly value="${rst.AREA_NO}"/> 
				   </td>
                   <td width="15%" align="right" class="detail_label">区域名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="AREA_NAME" name="AREA_NAME" id="AREA_NAME"  label="区域名称"  type="text"  inputtype="string"  readonly    value="${rst.AREA_NAME}"/> 
				   </td>
                </tr>
                  
                <tr>
                   <td width="18%" align="right" class="detail_label">年份：</td>
				   <td width="35%" class="detail_content">
                     <select json="YEAR" name="YEAR" id="YEAR" label="年"  style="width: 155px;"  autocheck="true" inputtype="string"    mustinput="true"  ></select>
				   </td>
                   <td width="15%" align="right" class="detail_label">月份：</td>
				   <td width="35%" class="detail_content">
                     <select json="MONTH" name="MONTH" id="MONTH" label="月份"  style="width: 155px;"  autocheck="true" inputtype="string"    mustinput="true"  ></select>
				   </td>
                </tr>
                
                <tr>
                  <td width="18%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="4">
				     <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   maxlength="250"   label="备注"    rows="4" cols="80%" >${rst.REMARK}</textarea>
				   </td>
              </tr>
			</table>
		</td>
	</tr>
	<tr>  </tr>
</table>
</form>
<input type="hidden" id="addFlag" value="${rst.SALE_DATE_UP_ID}"/>
</body>
</html>
<script type="text/javascript">
    SelDictShow("YEAR","89","${rst.YEAR}","");
    SelDictShow("MONTH","168","${rst.MONTH}","");
    
</script>


 