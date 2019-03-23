<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<%@ include file="/commons/jslibs.jsp"%>
		<script type=text/javascript src="${ctx}/scripts/core/md5.js"></script>
    	<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<title>消息提醒</title>
	</head>
	<body>
	<form action="">
		<input type="hidden" id="yhFilter" name="yhFilter" value=" YHZT='启用' AND DELFLAG=0">
		<input type="hidden" id="bmFilter" name="bmFilter" value=" BMZT='启用' AND DELFLAG=0">
		<input type="hidden" id="jgFilter" name="jgFilter" value=" JGZT='启用' AND DELFLAG=0">
		<input type="hidden" id="ZTXXID" name="ZTXXID" value="${XTYHZTXXID}">
		<input type="hidden" id="IS_DRP_LEDGER" name="IS_DRP_LEDGER" value="${IS_DRP_LEDGER}">
		
	<table height="100%" width="100%">
		<tr>
			<td valign="top" height="100%" width="40%">
			<div style="height:100%;width:100%;overflow-x:hidden;overflow-y:auto;">
				<table id="xxjs" width="350px" style="table-layout:fixed;" class="lst">
					<tr height="25px" class="fixedRow">
						<th nowrap align="center" width="8%"></th>
						<th nowrap align="center" width="15%">发送人</th>
						<th nowrap align="center" width="30%">发送时间</th>
						<th nowrap align="center" width="47%">信息内容</th>
					</tr>
					<c:forEach items="${modelList}" var="rst" varStatus="row">
						<c:set var="r" value="${row.count % 2}"></c:set>
						<c:set var="rr" value="${row.count}"></c:set>
                       <a href="javascript:void(0)" onclick="divDisplayContent('MSGINFO${rr}','${rst.MESSAGEID }','${rst.CKZT }','span_ckzt${row.index}');" >
                       <input type="hidden"  name="MSGINFO" id="MSGINFO${rr}" value="${rst.MSGINFO}"/>
						<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" >
							<input type="hidden" name="dxx" id="dxx" value="${rst.MESSAGEID}"/>
							<td width="8%" align="center">
								<c:if test="${rst.CKZT=='1'}"><span id="span_ckzt${row.index}"><img src="${ctx}/styles/${theme}/images/main/readed-msg.gif"></img></span></c:if>
								<c:if test="${rst.CKZT=='0'}"><span id="span_ckzt${row.index}"><img src="${ctx}/styles/${theme}/images/main/unread-msg.gif"></img></span></c:if>
							</td>
					 		<td nowrap align="left" width="15%">${rst.SENDER }</td>
							<td nowrap align="left" width="30%">${rst.SENDTIME}</td>
							<td nowrap style="width=47%;text-overflow:ellipsis;overflow:hidden;white-space: nowrap;"> 
							<font color="#000000">${rst.MSGINFO}</font>
							</td>
						</tr>
						</a>
						
					</c:forEach>
					<c:if test="${empty modelList}">
					<tr>
						<td height="25" colspan="13" align="center" class="lst_empty">
							&nbsp;无相关信息&nbsp;
						</td>
					</tr>
					</c:if>
				</table>
			</div>
			</td>
			<td valign="top" height="100%" width="60%">
				<table height="100%">
					<tr><td>消息内容</td></tr>
					<tr><td height="40%" valign="top"><textarea id="showJsnr" readonly style="width:500px;height:150px;overflow:hidden;"></textarea>
					</td></tr>
					<tr><td><HR align=center width="100%" color=#000000 SIZE=1></td></tr>
					<tr><td height="60%" valign="top">
					<div style="height:100%;overflow-x:hidden;overflow-y:auto;">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td>发送内容</td>
						</tr>
						<tr>
							<td><textarea id="FSNR" name="FSNR" inputtype="string" autocheck="true" maxlength="500" style="width:500px;height:150px;overflow:hidden;"></textarea></td>
						</tr>
						<tr>
							<td height="30px">接&nbsp;&nbsp;收&nbsp;&nbsp;人<input id="YHM" name="YHM" type="text" readonly size="60"><input id="XTYHID" name="XTYHID" type="hidden" >&nbsp;&nbsp;
								<img align="absmiddle" name="selLL" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selCommon('System_7', false, 'XTYHID', 'XTYHID', 'forms[0]','YHM', 'YHM', 'yhFilter')">
							</td>
						</tr>
						<tr>
							<td height="30px">接收部门<input id="BMMC" name="BMMC" type="text" readonly size="60"><input id="BMXXID" name="BMXXID" type="hidden" >&nbsp;&nbsp;
								<img align="absmiddle" name="selLL" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selCommon('1', true, 'BMXXID', 'BMXXID', 'forms[0]','BMMC', 'BMMC', 'bmFilter')">
							</td>
						</tr>
						<tr>
						<td height="30px">接收机构<input id="JGMC" name="JGMC" type="text" readonly size="60"><input id="JGXXID" name="JGXXID" type="hidden" >&nbsp;&nbsp;
								<img align="absmiddle" name="selLL" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/select.gif" onClick="selCommon('2', true, 'JGXXID', 'JGXXID', 'forms[0]','JGMC', 'JGMC', 'jgFilter')">
								<input id="send" name="send" value="发送" type="button" onclick="sendMessage();">
							</td>
						</tr>
					</table>
					</div>
					</td></tr>
				</table>
			</td>
		</tr>
	</table>
	</form>
	</body>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript">
	    $(function(){
	    	var IS_DRP_LEDGER = $("#IS_DRP_LEDGER").val();//是否分销1->分销
	    	if("1" == IS_DRP_LEDGER){
	    		var ZTXXID = $("#ZTXXID").val();
	    		var param = " and ZTXXID in (select CHANN_ID from BASE_CHANN where CHANN_ID='"+ZTXXID+"' or AREA_SER_ID='"+ZTXXID+"')";
	    		$("#yhFilter").val(" YHZT='启用' and DELFLAG=0 " + param);
	    		$("#bmFilter").val(" BMZT='启用' AND DELFLAG=0 " + param);
	    		$("#jgFilter").val(" JGZT='启用' AND DELFLAG=0 " + param);
	    		
	    	}
	    	
	    });
	    
	    
		function divDisplayContent(infoInout,_dxxid,ckzt,spanid){
			var divObj=document.getElementById("showJsnr");
			var cont = $("#"+infoInout).val();
			divObj.value=cont;
			if(ckzt=='0'){
				$.ajax({
					url: "firstPage.shtml?action=txInsertCkztByDxxid",
					type:"POST",
					dataType:"text",
					data:{dxxid:_dxxid},
					complete: function(xhr){
						document.getElementById(spanid).innerHTML="<img src='${ctx}/styles/${theme}/images/main/readed-msg.gif'></img>";
					    eval("json = "+xhr.responseText);
					    if(json.success==true){
					        
					    }else{
					        parent.showErrorMsg(utf8Decode(json.messages));
					    }
					}
				});
			}
		}
		function sendMessage(){
			var _xtyhids = document.getElementById("XTYHID").value;
			var _jgxxids = document.getElementById("JGXXID").value;
			var _bmxxids = document.getElementById("BMXXID").value;
			var _jsrxms  = document.getElementById("YHM").value;
			var _bmmc  = document.getElementById("BMMC").value;
			var _jgmc  = document.getElementById("JGMC").value;
			if(_jsrxms==""&&_bmmc==""&&_jgmc==""){
				alert("没有选择发送人，不能发送！");
				return;
			}
			var _fsnr = document.getElementById("FSNR").value;
			if(_fsnr==""){
				alert("发送内容为空，不能发送！");
				return;
			}
			$.ajax({
				url: "firstPage.shtml?action=txInsertMessage",
				type:"POST",
				dataType:"text",
				data:{xtyhids:_xtyhids,bmxxids:_bmxxids,jgxxids:_jgxxids,fsnr:_fsnr},
				complete: function(xhr){
				    eval("json = "+xhr.responseText);
				    if(json.success==true){
				        parent.showErrorMsg(utf8Decode(json.messages));
				        document.getElementById("YHM").value="";
						document.getElementById("XTYHID").value="";
						document.getElementById("JGXXID").value="";
						document.getElementById("BMXXID").value="";
						document.getElementById("BMMC").value="";
						document.getElementById("JGMC").value="";
						
				    }else{
				        parent.showErrorMsg(utf8Decode(json.messages));
				    }
				}
			});
		}
	</script>
</html>