<!--  
/**
 * @module  共通设计
 * @func 定制画面编辑
 * @version 1.1
 * @author zhuxw
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
	<title>浏览设置</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
</head>
<body >
<input id="tbNo" type="text" style="display:none" value="${tbNo}"/>
<input id="rowcount" type="text" style="display:none"  value="${fn:length(Customized)}"/>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
<tr>
	<td valign="top">
		<div class="lst_area">
			<table id="dyn" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
				 <tr class="fixedRow">
						<th nowrap width="80px">是否显示</th>
						<th nowrap align="center">列名称</th>
				 </tr>
				 
				 
				 <c:forEach items="${Customized}" var="c" varStatus="row">
				 <c:set var="r" value="${row.count % 2}"></c:set>
			     <c:set var="rr" value="${row.count}"></c:set>
					<tr name='cusrr' id="cusrr${rr}" index="${rr}" class="list_row" >
						<td nowrap align="center">
							<input json='ISDISPLAY' name='eid' type="checkbox" isdisplay="${c.ISDISPLAY}" />
						</td>
						<td nowrap align="left" name="logicName" onclick="rowSelected(this)">${c.COLLOGICNAME}</td>
						<td style="display:none" >
							<input type="text" json="COLPHYSICSNAME" value="${c.COLPHYSICSNAME}"> 
							<input type="text" json="COLLOGICNAME" value="${c.COLLOGICNAME}"> 
							<input type="text" json="TABNAME" value="${c.TABNAME}"> 
							<input type="text" json="COLORDER" value="${rr}"> 
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</td>
</tr>
<tr>
	<td align="center" height="50">
		<input id="up" type="button" class="btn" value="&nbsp;上&nbsp;移&nbsp;" title="上移">&nbsp;&nbsp;
		<input id="down" type="button" class="btn" value="&nbsp;下&nbsp;移&nbsp;" title="上移">&nbsp;&nbsp;
		<input id="save" type="button" class="btn" value="&nbsp;确&nbsp;定&nbsp;" title="保存">&nbsp;&nbsp;
		<input id="add" type="button" class="btn" value="&nbsp;关&nbsp;闭&nbsp;" title="关闭" onclick="window.close()">
	</td>
</tr>
</table>
</body>
<script type="text/javascript">
	$(function(){
	      //选中
		 $("#dyn input[name='eid']").each(function(){
		 	if(0!=$(this).attr("isdisplay")){
		 		$(this).attr("checked","checked");
		    }
		 });
	
	
	
		 $("#dyn input[name='eid']").each(function(){
		 	if(0!=$(this).attr("isdisplay")){
		 		$(this).attr("checked","checked");
		    }
		 });
		 
		 $("#up").click(function(){
		 	var objs = $(".list_row1");
		 	if(objs.length<1){
		 		parent.showErrorMsg("当前没有选中任何记录。");
		 		return;
		 	}
		 	var v = objs.first().attr("index");
		 	if(v==1){
		 		showErrorMsg("已经在第一条，无法上移");
		 		return;
		 	}
		 	for(var i=0;i<objs.length;i++){
		 		var idx = objs[i].index;
		 		var pidx = idx-1;
		 		var tmpContent = $("#cusrr"+pidx).html();
		 		$("#cusrr"+pidx).html($("#cusrr"+idx).html());
		 		$("#cusrr"+idx).html(tmpContent);
		 		$("#cusrr"+pidx).toggleClass("list_row1");
		 		$("#cusrr"+idx).toggleClass("list_row1");
		 		$("#cusrr"+pidx).find("json='COLORDER'").val(pidx);
		 		$("#cusrr"+pidx).find("input[json='COLORDER']").val(pidx);
		 		$("#cusrr"+idx).find("input[json='COLORDER']").val(idx);
		 	}
		 });
		 
		 $("#down").click(function(){
		 	var objs = $(".list_row1");
		 	if(objs.length<1){
		 		showErrorMsg("当前没有选中任何记录。");
		 		return;
		 	}
		 	var v = objs.last().attr("index");
		 	if(v==$("#rowcount").val()){
		 		showErrorMsg("已经在最后一条，无法下移");
		 		return;
		 	}
		 	for(var i=objs.length-1;i>=0;i--){
		 		var idx = objs[i].index;
		 		var nidx = Number(idx)+1;
		 		var tmpContent = $("#cusrr"+nidx).html();
		 		$("#cusrr"+nidx).html($("#cusrr"+idx).html());
		 		$("#cusrr"+idx).html(tmpContent);
		 		$("#cusrr"+nidx).toggleClass("list_row1");
		 		$("#cusrr"+idx).toggleClass("list_row1");
		 		$("#cusrr"+nidx).find("input[json='COLORDER']").val(nidx);
		 		$("#cusrr"+idx).find("input[json='COLORDER']").val(idx);
		 	}
		 });
		
		$("#save").click(function(){
			showWaitPanel();
			var len = $("#dyn input[name='eid']:checked").length;
			if(len<1){
				showErrorMsg("页面至少需要显示一个字段");
				return;
			}
			var jsonData = packJsonData();
			$.ajax({
				url: "customized.shtml?action=save",
				type:"POST",
				dataType:"text",
				data:{"jsonData":jsonData,"tbNo":$("#tbNo").val()},
				complete: function(xhr){
					closeWindow();
					eval("jsonResult = "+xhr.responseText);
					if(jsonResult.success===true){
						window.returnValue=1;
						window.close();
					}else{
						parent.showErrorMsg(utf8Decode(jsonResult.messages));
					}
				}
			});
		});
	});
	
	function packJsonData(){
		var jsonData = "";
		$("#dyn tr[name='cusrr']").each(function(){
			jsonData = jsonData+"{";
			$(this).find(":input").each(function(){
				if(null != $(this).attr("json")){
					var key;
					var value;
					var type = $(this).attr("type");
					if("checkbox" == type){
						key = $(this).attr("json");
						if($(this).attr("checked")){
							value= 1;
						}else{
							value= 0;
						}
					}else{
						key = $(this).attr("json");
						value = $(this).attr("value");
						isFirst = false;
					}
					jsonData = jsonData+ "'" + key + "':'" + value +"',";
			 	}
			});
			jsonData = jsonData.substr(0,jsonData.length-1)+"},"
		});
		if(jsonData.length>1){
			return "["+jsonData.substr(0,jsonData.length-1)+"]";
		}
		return "[]";
	};
	
	function rowSelected(obj){
		$(obj).parent().toggleClass("list_row1");
	}
	</script>
</html>
