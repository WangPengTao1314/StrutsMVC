<!--
/**
 * @prjName:喜临门营销平台
 * @fileName:Store_Edit
 * @author wdb
 * @time   2013-08-13 14:01:22 
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
	<script type="text/javascript" src="${ctx}/pages/drp/base/store/Store_Edit.js"></script>
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">
<div style="height: 100">
<div class="buttonlayer" id="floatDiv">
	<table cellSpacing=0 cellPadding=0 border=0 width="100%">
		<tr>
			<td align="left" nowrap>
				<input id="save" type="button" class="btn" value="保存(S)"
					title="Alt+S" accesskey="S">
				<input type="button" class="btn" value="返回(B)" title="Alt+B"
					accesskey="B" onclick='parent.$("#label_1").click();'>
			</td>
		</tr>
	</table>
</div>
<!--<table width="100%" cellspacing="0" cellpadding="0" >-->
<!--	<tr>-->
<!--		<td height="20px">&nbsp;&nbsp;当前位置：系统管理>>基础信息>>库房库位信息</td>-->
<!--			<td width="50" align="right"></td>-->
<!--	</tr>-->
<!--</table>-->
<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
	<tr>
		<!--占位用行，以免显示数据被浮动层挡住-->
		<td height="20px">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td>
   <form method="POST" action="#" id="mainForm" >
       <table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<tr>
			<td class="detail2">
				<table id="jsontb" width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
					<input type="hidden" id="CHANN_ID" value="${channInfo.CHANN_ID}"/>
					<input type="hidden" id="CHANN_NO" value="${channInfo.CHANN_NO}"/>
					<input type="hidden" id="CHANN_NAME" value="${channInfo.CHANN_NAME}"/>
               <tr>
                    <td width="15%" align="right" class="detail_label">库房编号：</td>
					<td width="35%" class="detail_content">
						<c:if test="${empty rst.STORE_NO}">
							<input json="STORE_NO" name="STORE_NO" autocheck="true" label="库房编号"  type="text" mustinput="true" inputtype="string" maxlength="30"  value="${rst.STORE_NO}"/> 
						</c:if>
                        <c:if test="${not empty rst.STORE_NO}">
                        	<input json="STORE_NO" name="STORE_NO" autocheck="true" label="库房编号"  type="text"  mustinput="true" readonly inputtype="string" maxlength="30"  value="${rst.STORE_NO}"/> 
                        </c:if>
					</td>
					<td width="15%" align="right" class="detail_label">库房名称：</td>
				    <td width="35%" class="detail_content">
                       <input json="STORE_NAME" name="STORE_NAME" autocheck="true" label="库房名称"  type="text"   inputtype="string"     mustinput="true"     maxlength="50"  value="${rst.STORE_NAME}"/> 
				    </td>
                   
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">上级库房编号：</td>
               		<td width="35%" class="detail_content">
               			<input json="PAR_STORE_ID" id="PAR_STORE_ID" name="PAR_STORE_ID" autocheck="true" label="上级库房ID"  type="hidden"  inputtype="string"   maxlength="32"  value="${rst.PAR_STORE_ID}"/> 
               			<input name="PAR_STORE_NO" autocheck="true" label="上级库房编号"  type="text"  inputtype="string"   readonly    maxlength="32"  value="${rst.PAR_STORE_NO}"/> 
               			<img align="absmiddle" name="selAREA" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="selCommon('BS_35', false, 'PAR_STORE_ID', 'STORE_ID', 'forms[0]','PAR_STORE_NO,PAR_STORE_NAME','STORE_NO,STORE_NAME', 'selectParams')">
						<input type="hidden" name="selectParams" value=" STATE ='启用' AND LEDGER_ID = '${LEDGER_ID}' " />
               		</td>
               		<td width="15%" align="right" class="detail_label">上级库房名称：</td>
               		<td width="35%" class="detail_content">
               			<input name="PAR_STORE_NAME" autocheck="true" label="上级库房名称"  type="text"  inputtype="string"   readonly    maxlength="32"  value="${rst.PAR_STORE_NAME}">
               		</td>
               </tr>
               <tr>
                    <td width="15%" align="right" class="detail_label">库房类别：</td>
					<td width="35%" class="detail_content">
                         <select json="STORE_TYPE" id="STORE_TYPE"  name="STORE_TYPE" style="width:153px" inputtype="string" mustinput="true" autocheck="true" label="库房类型"></select>
					</td>
					<td width="15%" align="right" class="detail_label">库房所属：</td>
				    <td width="35%" class="detail_content">
				    	<input id="TERM_STROE_FLAG" json="TERM_STROE_FLAG"  name="TERM_STROE_FLAG" type="hidden"
				    	<c:if test="${rst.TERM_STROE_FLAG==null}">
				    		value="0"
				    	</c:if> 
				    	<c:if test="${rst.TERM_STROE_FLAG!=null}">
				    		value="${rst.TERM_STROE_FLAG}"
				    	</c:if>
				    	 >
	                    <input id="TERM_STROE_FLAG_YES" name="TERM_STROE_FLAG_TAB"  type="radio" onclick="upStoreFlag('1')"  <c:if test="${rst.TERM_STROE_FLAG==1 }">checked="checked"</c:if> />终端
	                    <input id="TERM_STROE_FLAG_NO" name="TERM_STROE_FLAG_TAB"  type="radio" onclick="upStoreFlag('0')" <c:if test="${rst.TERM_STROE_FLAG==0||rst.TERM_STROE_FLAG==null }">checked="checked"</c:if> />本部
						<!-- 0宽度input标签，用来显示单选按钮后的必选星号 -->
						<input mustinput="true" inputtype="string" name="tab"  style="width: 0px;"  type="text" >
				    </td>
               </tr>
                 <tr>
               		<td width="15%" align="right" class="detail_label">库房总容积：</td>
               		<td width="35%" class="detail_content" colspan="3">
               			<input json="TOTAL_VOLUME" name="TOTAL_VOLUME" autocheck="true" label="库房总容积"  type="text" inputtype="float"   valueType="8,2"      mustinput="true"  autocheck="true"     maxlength="32"  value="${rst.TOTAL_VOLUME}">
               		</td>
               </tr>
               
               <tr>
               	   <td width="15%" align="right" class="detail_label">所属单位编号：</td>
				   <td width="35%" class="detail_content">
				     <input  type="text"  id="BEL_CHANN_NO" name="BEL_CHANN_NO"  json="BEL_CHANN_NO" autocheck="true" label="所属单位编号"  inputtype="string" readonly value="${rst.BEL_CHANN_NO}">
                     <input json="BEL_UNIT_ID" id="BEL_UNIT_ID" name="BEL_UNIT_ID" autocheck="true" type="hidden" label="所属单位ID"    inputtype="string"       mustinput="true"     maxlength="32"  value="${rst.BEL_CHANN_ID}"/> 
                     <img align="absmiddle" name="selAREA" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/select.gif"
									onClick="getBelUnit()">
					<input type="hidden" id="CHANN_CON" name="CHANN_CON" value="STATE = '启用' and DEL_FLAG='0' and CHANN_ID='${LEDGER_ID}'">
					<input type="hidden" id="TERM_CON" name="TERM_CON" value="STATE = '启用' and DEL_FLAG='0' and CHANN_ID_P='${LEDGER_ID}'">
				   </td>
                   <td width="15%" align="right" class="detail_label">所属单位名称：</td>
				   <td width="35%" class="detail_content">
                     <input json="BEL_UNIT_NAME" name="BEL_UNIT_NAME" id="BEL_UNIT_NAME" autocheck="true" label="所属单位名称"  type="text"   inputtype="string"   readonly       maxlength="100"  value="${rst.BEL_CHANN_NAME}"/> 
				   </td>
               </tr>
               <tr>
               <td width="15%" align="right" class="detail_label">详细地址：</td>
			   <td width="35%" class="detail_content" colspan="3">
                        <textarea  json="DTL_ADDR" name="DTL_ADDR" id="DTL_ADDR" autocheck="true" inputtype="string"   
                        maxlength="250"   label="详细地址"    rows="3" cols="80%" >${rst.DTL_ADDR}</textarea>
			   </td>
               </tr>
               <tr>
                   <td width="15%" align="right" class="detail_label">备注：</td>
				   <td width="35%" class="detail_content" colspan="3">
                         <textarea  json="REMARK" name="REMARK" id="REMARK" autocheck="true" inputtype="string"   
                         maxlength="250"   label="备注"    rows="3" cols="80%" >${rst.REMARK}</textarea>
				   </td>
               </tr>
               
			</table>
		</td>
	</tr>
</table>
</form>
</td>
				</tr>
			</table>
		</div>
</body>
</html>
<script type="text/javascript">
	   SelDictShow("STORE_TYPE", "BS_39", "${rst.STORE_TYPE}", "");
</script>
 