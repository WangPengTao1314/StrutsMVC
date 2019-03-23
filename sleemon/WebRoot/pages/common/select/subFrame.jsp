<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ page import="com.hoperun.commons.util.*,com.hoperun.commons.select.object.*" %>
<%
try{
	TableObject aTableObject = (TableObject)request.getAttribute("TableObject");
	TableObject[] subTableObjects = aTableObject.getSubTableObjects();
	String currentSubId = StringUtil.tranCodeP(request.getParameter("currentSubId"));
	String selCommId = StringUtil.tranCodeP(request.getParameter("selCommId"));
	String id = currentSubId.substring(currentSubId.lastIndexOf("_")+1);
	String fromPop = StringUtil.tranCodeP(request.getParameter("fromPop"));
	String selectType = StringUtil.tranCodeP(request.getParameter("selectType"));
	if(id.equals("")) id="0";
%>
<html>
<head>
<title>通用选取</title>
<meta http-equiv=content-type content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/styles/${theme}/css/newSelComm.css" type="text/css">
<style>
	.label_up{
		height:25px;
		text-align:center;
		font-size:13px;
		border-width:1 2 0 1;
		border-style:solid;
		border-top-color:buttonhighlight;
		border-left-color:buttonhighlight;
		border-bottom-color:buttonshadow;
		border-right-color:buttonshadow;
	}
	.label_down{
		height:22px;
		text-align:center;
		font-size:13px;
		border-style:solid;
		border-width:1 2 1 1;
		border-top-color:buttonhighlight;
		border-left-color:buttonhighlight;
		border-bottom-color:buttonhighlight;
		border-right-color:buttonshadow;
		cursor:hand;
	}
	.label_pre{
		height:22px;
		text-align:center;
		font-size:13px;
		border-style:solid;
		border-width:1 0 1 1;
		border-top-color:buttonhighlight;
		border-left-color:buttonhighlight;
		border-bottom-color:buttonhighlight;
		border-right-color:buttonshadow;
		cursor:hand;
	}
	.label_null{
		height:22px;
		text-align:center;
		font-size:13px;
		border-width:0 0 1 0;
		border-style:solid;
		border-bottom-color:buttonhighlight;
	}
	td{
		padding: 0px 0px 0px 0px;;
	}
</style>
<script language="javascript">
	<%if(fromPop.equals("T")){%>
	var keyValues = dialogArguments;
	<%}else{%>
	var keyValues = new Array();
	<%}%>
	var currObjectId = "label_<%=id%>";
	var preObjectId = "<%=Integer.parseInt(id)-1>=0?"label_"+(Integer.parseInt(id)-1):""%>";
	function funClick(id){
		if(id==currObjectId){
			return;
		}else{
			eval(currObjectId+".className='label_down'");
			eval(id+".className='label_up'");
			currObjectId = id;

			var tmp = id.substring(id.indexOf("_")+1);
			if(preObjectId!=""&&currObjectId!=preObjectId) {
				eval(preObjectId+".className='label_down'");
			}
			if(parseInt(tmp)>0){
				preObjectId = id.substring(0,id.indexOf("_")+1)+(parseInt(tmp)-1);
				eval(preObjectId+".className='label_pre'");
			}else{
				preObjectId = "";
			}
		}
		reloadCurSubPage();
	}
	function reloadCurSubPage(){
		var tmp = currObjectId.substring(currObjectId.indexOf("_")+1);
		var paras = "";
		for(var i=0;i<keyValues.length;i++){
			eval("bottomcontent.document.forms[0].PK"+i).value= keyValues[i];
		}
		bottomcontent.document.forms[0].selectType.value="<%=selectType%>";
		bottomcontent.document.forms[0].selCommId.value="<%=selCommId%>";
	  <%if(subTableObjects[0].getIsWLDWXX()){%>
		bottomcontent.document.forms[0].tableId.value="<%=aTableObject.getId()%>";
		<%}else{%>
		bottomcontent.document.forms[0].tableId.value="<%=aTableObject.getId()%>_"+tmp;
		<%}%>
		bottomcontent.document.forms[0].submit();
	}
</script>
</head>
<body style="overflow:hidden;margin:0px 0px 0px 0px" onLoad="reloadCurSubPage()">
<table cellspacing="0" cellpadding="0" width="100%" border="0" height="30">
	<tr>
		<td colspan="<%=subTableObjects.length+1%>" height="5"></td>
	</tr>
	<tr height="26">
		<%for(int i=0;i<subTableObjects.length;i++){%>
		<td	nowrap style="width:<%=subTableObjects[i].getTableLgName().length()*15+"px"%>">
			<%
			String className = "label_down";
			if(Integer.parseInt(id)-1>=0&&Integer.parseInt(id)-1==i){
				className = "label_pre";
			}else if(Integer.parseInt(id)==i){
				className = "label_up";
			}
			%>
			<div class="<%=className%>" id="label_<%=i%>" onClick="funClick(this.id)">
			<div style="margin-top:4px;"><%=subTableObjects[i].getTableLgName()%></div>
			</div>
		</td>
		<%}%>
		<td nowrap><div class="label_null">&nbsp;</div></td>
	</tr>
</table>
<iframe id="bottomcontent" name="bottomcontent" src="${ctx}/common/select.shtml?action=doPost&cmdFlag=SubList&selectType=<%=selectType%>&selCommId=<%=selCommId%>&tableId=<%=aTableObject.getId()+(subTableObjects[0].getIsWLDWXX()?"":"_0")%>" frameborder=0 width="100%" style="height:180px"></iframe>
</body>
</html>
<%}catch(Exception e){
		e.printStackTrace();
	}%>