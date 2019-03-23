<%--
/**
 * @author zhuxw
 * @function 
 * @version 2011年11月16日 11时23分15秒
 */
--%>
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
	<script type="text/javascript" src="${ctx}/pages/sys/notice/Notice_Edit.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>信息编辑</title>
</head>
<body class="bodycss1">	
 <div class="buttonlayer" id="floatDiv">
      <table cellSpacing=0 cellPadding=0 border=0 width="100%">
		<tr>
		   <td align="left" nowrap>
		   	<input id="save" type="button" class="btn" value="保存(S)" title="Alt+S" accesskey="S">
			<input type="button" class="btn" value="返回(B)" title="Alt+B" accesskey="B" onclick='parent.$("#label_1").click();'>
			</td>
		</tr>
	</table>
</div>
<!--浮动按钮层结束-->
<!--占位用table，以免显示数据被浮动层挡住-->
<table width="100%" height="25px">
    <tr>
        <td>&nbsp;</td>
    </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td>
		<form name="form" id="mainForm">
		<table id="jsontb" width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
		<input type="hidden" name="selectPYXXParams" value=" YHZT = '启用' and  ZTXXID = '${ZTXXID}' " />
		<tr>
			<td class="detail2">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="15%" align="right" class="detail_label">公告标题：</td>
					<td width="35%" class="detail_content" colspan="4">
                        <input type="text" json="NOTICE_TITLE" style="width:690px;"  id="NOTICE_TITLE" name="NOTICE_TITLE" maxlength="100"    autocheck="true" inputtype="string" label="公告标题" value="${rst.NOTICE_TITLE}" mustinput="true"  />&nbsp;
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="detail_label">公告类型：</td>
					<td width="35%" class="detail_content">
                      <select id="NOTICE_TYPE" name="NOTICE_TYPE" label="公告类型"  json="NOTICE_TYPE" autocheck="true" inputtype="string" mustinput="true" style="width:155"></select>
					</td>
					<td width="15%" align="right" class="detail_label">公告对象：</td>
					<td width="35%" class="detail_content">
                    <input type="text"  id="NOTICE_OBJ" name="NOTICE_OBJ" json="NOTICE_OBJ" maxlength="30"    autocheck="true" inputtype="string" label="公告对象" value="${rst.NOTICE_OBJ}" mustinput="true"  />&nbsp;
                     <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('BS_128', true, 'NOTICE_OBJ', 'SJXMC', 'forms[0]','NOTICE_OBJ', 'SJXMC', '')">
					</td>
				</tr>
				<tr>
				    <td width="15%" align="right" class="detail_label">发布人名称：</td>
					<td width="35%" class="detail_content">
					<input type="hidden" id="ISSUER_ID" name="ISSUER_ID" json="ISSUER_ID" value="${rst.ISSUER_ID}"  />
                    <input type="text"  id="ISSUER_NAME" name="ISSUER_NAME" json="ISSUER_NAME" maxlength="30"    autocheck="true" inputtype="string" label="发布人名称" value="${rst.ISSUER_NAME}" mustinput="true"  />&nbsp;
                     <img align="absmiddle" name="selJGXX" style="cursor: hand"
						src="${ctx}/styles/${theme}/images/plus/select.gif"       
						onClick="selCommon('System_7', false, 'ISSUER_ID', 'XTYHID', 'forms[0]','ISSUER_NAME', 'XM', 'selectPYXXParams')">
					</td>
					<td width="15%" align="right" class="detail_label"></td>
					<td width="35%" class="detail_content"></td>
				</tr>
				
				<tr>
					<td width="15%" align="right" class="detail_label">开始时间：</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="STATIME"id="STATIME" name="STATIME" autocheck="true" inputtype="string" label="开始时间" value="${rst.STATIME}" mustinput="true" onclick="SelectTime();" readonly="readonly" />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(STATIME);">
					</td>
					<td width="15%" align="right" class="detail_label">结束时间：</td>
					<td width="35%" class="detail_content">
                        <input type="text" json="ENDTIME" id="ENDTIME"name="ENDTIME" autocheck="true" inputtype="string" label="结束时间"  value="${rst.ENDTIME}" mustinput="true" onclick="SelectTime();" readonly="readonly" />&nbsp;
						<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectTime(ENDTIME);">
					</td>
				</tr>
				<%--<tr>
					<td width="15%" align="right" class="detail_label">公告内容：</td>
					<td width="35%" class="detail_content" colspan="4">
       					<textarea  json="NOTICE" name="NOTICE" id="NOTICE" autocheck="true" inputtype="string" maxlength="1000" label="公告内容" mustinput="true" rows="6" cols="80%" ><c:out value="${rst.NOTICE}"></c:out></textarea>
       				</td>
      			</tr>
      			--%><tr>
					<td width="15%" align="right" class="detail_label">公告内容：</td>
					<td width="85%" class="detail_content" colspan="3">
       					<!--<textarea  json="NOTICE" name="NOTICE" autocheck="true"pattern="string" maxlength="4000"label="公告信息" required="true" rows="7" cols="80%" >${rst.NOTICE}</textarea>  -->
						<IFRAME ID="eWebEditor1" src="${ctx}/pages/common/webedit/eWebEditor.jsp?id=content1&style=standard" frameborder="0" scrolling="no" width="1000" height="400"></IFRAME>
						<INPUT type="hidden" json="" id="NOTICE"  name="content1" autocheck="true" pattern="string" maxlength="6000"label="公告信息" required="true" value="${rst.NOTICE}">
       				</td>
      			</tr>
      			
      			<tr>
					<td width="15%" align="right" class="detail_label">公告附件：</td>
					<td width="35%" class="detail_content" colspan="4">
       					<input type="hidden"  json="ATT_PATH" name="ATT_PATH" id="ATT_PATH" label="公告附件"  autocheck="true" inputtype="string" maxlength="300" value="${rst.ATT_PATH}" />
       					<span id="ATT_PATH_TXT"></span>
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
</body>
</html>
<%@ include file="/pages/common/uploadfile/uploadfile.jsp"%>
<script language="JavaScript">
	 
	SelDictShow("NOTICE_TYPE","192","${rst.NOTICE_TYPE}","");
	//uploadFile("ATT_PATH", "SAMPLE_DIR", true);
	uploadFile('ATT_PATH',"SAMPLE_DIR",true,true,true,"");
</script>
