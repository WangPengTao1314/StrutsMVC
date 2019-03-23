<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%> 
<%@ include file="/commons/taglibs.jsp"%> 
<%@ include file="/commons/jslibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
		<script language="JavaScript" src="${ctx}/pages/common/select/selCommJs.js"></script>
		<title>数据迁移</title>
	</head>
	<body>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel"> 
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0" class="wz">
						<tr>
							<td width="28" align="center"><label class="wz_img"></label></td>
							<td>当前位置：系统管理 &gt;&gt; 权限管理 &gt;&gt; 数据迁移</td> 
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr>
			<tr>
				<td height="20">
					<form name="qryForm" id="qryForm">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="70" align="right">
									日期：
								</td>
								<td width="150">
									<input name="RQ" id="RQ" type="text" autocheck="true" style="width:110px;"
										inputtype="string" maxlength="6" onclick="SelectDate();" readonly>
										<img align="absmiddle" style="cursor: hand"
									src="${ctx}/styles/${theme}/images/plus/date_16x16.gif"
									onclick="SelectDate(RQ);">
								</td>	
								
								<td align="left"> 
									<input type="button" id="migration" class="btn" value="迁 移">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr>
			<tr>
				<td valign="top">
					<div class="lst_area">
						<table width="100%" border="0" id="jsontb" cellpadding="1" cellspacing="1" class="lst">
							<tr class="fixedRow">
								<th width="10%" height="25"> <input type="checkbox" id="checkAll" name="checkAll"></th>
								<th width="30%" height="25">
									源表表名
								</th>
								<th width="30%" align="center">
									目标表名
								</th>
								<th width="30%" align="center">
									模块名称
								</th>
							</tr>
							<c:forEach items="${listData}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)">
									<td align="center" height="25" width="10%">
										<input type="checkbox" id="check${row.index}" value="${rst.DATAEXPCONFID }">
									</td>
									<td width="30%">
										${rst.FROMTAB}
									</td>
									<td width="30%">
										${rst.TOTAB }
									</td>
									<td width="30%">
										&nbsp;${rst.BUSINESS}
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty listData}">
								<tr>
									<td height="25" colspan="7" align="center" class="lst_empty">
										&nbsp;无相关信息&nbsp;
									</td>
								</tr>
							</c:if> 
						</table>
					</div>
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">
		$("#checkAll").click(function(){
	        var flag = $(this).attr("checked");
	        if(flag){
	            $("#jsontb :checkbox").attr("checked","true");
	        }else{
	            $("#jsontb :checkbox").removeAttr("checked");
	        }
		});
		$("#migration").click(function(){
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			var _rq = $("#RQ").val();
			if(_rq==null||_rq==""){
				showErrorMsg("选择要迁移的日期!");
				return;
			}
			var _qydzbid = "";
			if(checkBox==null||checkBox.length==0){
				showErrorMsg("做数据迁移至少得选择一条记录!");
				return;
			}
			checkBox.each(function(){
		        _qydzbid = _qydzbid+$(this).val()+",";
		    });
		    _qydzbid = _qydzbid.substr(0,_qydzbid.length-1);
			$.ajax({
				url: "${ctx}/dataMigration.shtml?action=toDataMigration",
				type:"POST",
				dataType:"text",
				data:{qydzbid:_qydzbid,rq:_rq},
				complete: function(xhr){
				    eval("json = "+xhr.responseText);
				    if(json.success==true){
				        showErrorMsg(utf8Decode(json.messages));
				    }else{
				        showErrorMsg(utf8Decode(json.messages));
				    }
				}
			});
		});
	</script>
</html>
