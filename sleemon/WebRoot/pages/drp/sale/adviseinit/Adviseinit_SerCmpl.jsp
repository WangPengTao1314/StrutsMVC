<!--
 * @prjName:喜临门营销平台
 * @fileName:Adviseinit_SerCmpl
 * @author wdb
 * @time   2013-08-13 14:01:22 
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
	<script type="text/javascript" src="${ctx}/pages/drp/sale/adviseinit/Adviseinit_SerCmpl.js"></script>
	<title>服务投诉</title>
</head>
<body>
<form id="mainform" method="post">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	
	<tr>
		<td style="height: 15px">
			<input type="button" id="check" class="btn" name="check" value="查看我的投诉">
		</td>
	</tr>
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3" id="maintable">
				
               <tr>
               		<td width="15%" align="right" class="detail_label">投诉编号:</td>
					<td width="35%" class="detail_content">
                        <input type="text" value="系统自动生成" autocheck="true" inputtype="string"" label="投诉编号" id="CMPL_ADVS_ID" name="CMPL_ADVS_ID" readonly/>
					</td>
					<td width="15%" align="right" class="detail_label">标题:</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="CMPL_ADVS_TITLE" id="CMPL_ADVS_TITLE" name="CMPL_ADVS_TITLE" mustinput="true" inputtype="string" autocheck="true" label="标题" maxlength="100"/>
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">渠道编号:</td>
					<td width="35%" class="detail_content">
						<input id="selectParams" name="selectParams" type="hidden"  value="STATE = '启用' and DEL_FLAG=0 ">
						<input type="hidden" json="CHANN_ID" id="CHANN_ID" name="CHANN_ID" value="${channInfo.CHANN_ID}"/>
                        <input type="text" json="CHANN_NO" id="CHANN_NO" value="${channInfo.CHANN_NO}" name="CHANN_NO" autocheck="true" label="渠道编号" inputtype="string" mustinput="true" readonly/>
                        <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"
							onClick="selCommon('BS_19', false, 'CHANN_ID', 'CHANN_ID', 'forms[0]','CHANN_NO,CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME','CHANN_NO,CHANN_NAME,AREA_ID,AREA_NO,AREA_NAME', 'selectParams')"> 
					</td>
					<td width="15%" align="right" class="detail_label">渠道名称:</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="CHANN_NAME" id="CHANN_NAME" name="CHANN_NAME" value="${channInfo.CHANN_NAME}" readonly/>
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">区域编号:</td>
					<td width="35%" class="detail_content">
						<input type="hidden" id="AREA_ID" name="AREA_ID" json="AREA_ID" value="${channInfo.AREA_ID}" />
                        <input type="text" json="AREA_NO" id="AREA_NO" name="AREA_NO" value="${channInfo.AREA_NO}" readonly/>
<!--                        <img align="absmiddle" name="selAREA" style="cursor: hand" src="${ctx}/styles/${theme}/images/plus/select.gif"-->
<!--							onClick="selCommon('BS_18', false, 'AREA_ID', 'AREA_ID', 'forms[0]','AREA_NO,AREA_NAME','AREA_NO,AREA_NAME', 'selectParams')"> -->
					</td>
					<td width="15%" align="right" class="detail_label">区域名称:</td>
					<td width="35%" class="detail_content">
						<input type="text" json="AREA_NAME" id="AREA_NAME" name="AREA_NAME" value="${channInfo.AREA_NAME}" readonly/>
					</td>
               </tr>
               <tr>
			        <td width="15%" align="right" class="detail_label">投诉人:</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="RAISE_PSON_NAME" id="RAISE_PSON_NAME" name="RAISE_PSON_NAME" value="${rst}" readonly/>
					</td>
			        <td width="15%" align="right" class="detail_label">联系电话:</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="TEL" name="TEL" autocheck="true" maxlength="30" inputtype="string" label="联系电话" id="TEL"/>
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">投诉对象:</td>
               		<td width="35%" class="detail_content">
               			<select id="CMPL_OBJ" name="CMPL_OBJ" json="CMPL_OBJ" style="width:155px"></select>
               		</td>
               		<td width="15%" align="right" class="detail_label">投诉的人员:</td>
               		<td width="35%" class="detail_content">
                        <input type="text" json="CMPL_TO_PSON" name="CMPL_TO_PSON" autocheck="true" mustinput="true" inputtype="string" label="投诉的人员" maxlength="30" id="CMPL_TO_PSON" maxlength="30"/>
					</td>
               </tr>
                <tr>
			        <td width="15%" align="right" class="detail_label">紧急程度:</td>
					<td width="35%" class="detail_content">
                      <select id="EMG_LVL" name="EMG_LVL" json="EMG_LVL" style="width:155px"></select>
					</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content">
					</td>
               </tr>
               <tr>
               		<td width="15%" align="right" class="detail_label">投诉内容:</td>
               		<td width="35%" class="detail_content" colspan="3">
               			<textarea rows="5" cols="90" id="CMPL_ADVS_CONTENT" name="CMPL_ADVS_CONTENT" maxlength="1000" autocheck="true" mustinput="true" inputtype="string" label="投诉内容"></textarea>
               		</td>
               </tr>
              
             </table>
		</td>
	</tr>
 	<tr>
	<td nowrap align="right">
	 	<input id="save" type="button" class="btn" value="提交(S)" title="Alt+S" accesskey="S" >
	</td>
  </tr>
</table>
</form>
</body>
<script type="text/javascript">
	SelDictShow("CMPL_OBJ", "BS_15", "${params.CMPL_OBJ}", "");
	SelDictShow("EMG_LVL", "BS_76", "${params.EMG_LVL}", "");
</script>
</html>
