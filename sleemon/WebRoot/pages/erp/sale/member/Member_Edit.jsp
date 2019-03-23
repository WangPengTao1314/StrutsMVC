<!--
/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2014-12-03 10:35:10 
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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/member/Member_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td height="20px">&nbsp;&nbsp;当前位置：总部营销活动>>基础数据>>会员信息维护</td>
		<td width="50" align="right"></td>
	</tr>
</table>
   <form method="POST" action="#" id="mainForm" >
       <input type="hidden" id="selChannParam" name="selChannParam" value=" STATE ='启用' "/>
       <table width="100%"  border="0" cellpadding="4" cellspacing="4" class="detail">
        <tr>
      
        </tr>
		<tr>
			<td class="detail2">
			<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
			    <tr>
                    <td width="18%" align="right" class="detail_label">会员编号：</td>
				   	<td width="35%" class="detail_content">
						<input json="MEMBER_NO" name="MEMBER_NO" type="text"
							autocheck="true" label="会员编号" inputtype="string" mustinput="true"
							maxlength="32"
							<c:if test="${not empty rst.MEMBER_NO}">value="${rst.MEMBER_NO}"</c:if>
							<c:if test="${empty rst.MEMBER_NO}">value="自动生成"</c:if>
							READONLY
							>
					</td>
                   <td width="15%" align="right" class="detail_label">会员名称：</td>
				   <td width="35%" class="detail_content" >
                     <input json="MEMBER_NAME" name="MEMBER_NAME" json="MEMBER_NAME"   label="会员名称"  type="text"  autocheck="true" inputtype="string"   mustinput="true"     maxlength="30"  value="${rst.MEMBER_NAME}"/> 
				   </td>
               </tr>
                <tr>
                   <td width="18%" align="right" class="detail_label">性别：</td>
				   <td width="35%" class="detail_content">
						<input id="XB" name="SEX" json="SEX" type="radio" value="男" <c:if test="${'女' ne rst.SEX}">checked="checked"</c:if> />男
						<input id="XB" name="SEX" json="SEX" type="radio" value="女" <c:if test="${'女' eq rst.SEX}">checked="checked"</c:if>/>女
					</td>
                   <td width="15%" align="right" class="detail_label">手机：</td>
				   <td width="35%" class="detail_content">
                     <input json="MOBILE_PHONE" name="MOBILE_PHONE" id="MOBILE_PHONE"  label="手机"  type="text" mustinput="true" inputtype="string"    autocheck="true"   maxlength="30"  value="${rst.MOBILE_PHONE}"/> 
				   </td>
                </tr>
                 <tr>
                   <td width="18%" align="right" class="detail_label">所属加盟商编号：</td>
				   <td width="35%" class="detail_content">
				     <input type="hidden" json="CHANN_ID" name="CHANN_ID" id="CHANN_ID" value="${rst.CHANN_ID}"/>
                     <input json="CHANN_NO" name="CHANN_NO" id="CHANN_NO" label="所属加盟商编号" mustinput="true" inputtype="string" readonly  autocheck="true"  type="text" value="${rst.CHANN_NO}"/> 
                     <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_NO,CHANN_NAME', 'CHANN_NO,CHANN_NAME', 'selChannParam');"> 
				   </td>
				   <td width="18%" align="right" class="detail_label">所属加盟商名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="CHANN_NAME" name="CHANN_NAME" id="CHANN_NAME" label="所属加盟商名称" mustinput="true" inputtype="string"  readonly autocheck="true"  type="text" value="${rst.CHANN_NAME}"/> 
				   </td>
                </tr>
                <tr>
				   
				   <td width="15%" align="right" class="detail_label">积分：</td>
				   <td width="35%" class="detail_content">
                     <input json="POINT_VALUE" name="POINT_VALUE" id="POINT_VALUE"  label="积分"  type="text"  inputtype="int"  valuetype="8"   autocheck="true"   maxlength="11"  value="${rst.POINT_VALUE}"/> 
				   </td>
				   <td width="15%" align="right" class="detail_label">生日：</td>
				   <td width="35%" class="detail_content">
                     <input type="text" id="BIRTHDAY" name="BIRTHDAY"  json="BIRTHDAY" 
                     onclick="SelectDate();" label="生日"  readonly   size="20" value="${rst.BIRTHDAY}" >
					 <img align="absmiddle" style="cursor:hand" 
					 src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(BIRTHDAY);">
				   </td>
				   
                </tr>
               
                <tr>
				   <td width="18%" align="right" class="detail_label">地址：</td>
				   <td width="35%" class="detail_content" colspan="4">
				     <textarea  json="ADDRESS" name="ADDRESS" id="ADDRESS"  inputtype="string" autocheck="true" 
				      maxlength="250"   label="地址"    rows="4" cols="80%" >${rst.ADDRESS}</textarea>
				   </td>
                </tr>
                
                <tr>
				   <td width="18%" align="right" class="detail_label">客户需求（意向购买）：</td>
				   <td width="35%" class="detail_content" colspan="4">
				     <textarea  json="REMARK" name="REMARK" id="REMARK"   inputtype="string" autocheck="true" mustinput="true"
				      maxlength="250"   label="客户需求（意向购买）"    rows="4" cols="80%" >${rst.REMARK}</textarea>
				   </td>
                </tr>
                <tr>
                   <td width="15%" align="right" class="detail_label">爱好：</td>
                     <td width="35%" class="detail_content" colspan="4">
				     <textarea  json="HOBBY" name="HOBBY" id="HOBBY"   inputtype="string" autocheck="true" 
				      maxlength="250"   label="爱好"    rows="4" cols="80%" >${rst.HOBBY}</textarea>
				   </td>
                </tr>
                
			</table>
		</td>
	</tr>
	<tr>  </tr>
</table>
</form>
</body>
</html>

 