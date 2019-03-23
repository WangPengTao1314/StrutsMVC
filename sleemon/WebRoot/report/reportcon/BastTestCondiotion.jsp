<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/pages/common/select/selCommJs.jsp"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">
	<script type=text/javascript src="${ctx}/scripts/util/My97DatePicker/WdatePicker.js"></script>
	<title>查询条件</title>
</head>
<body style="overflow:auto">
<table width="100%" cellspacing="0" cellpadding="0" >
	<tr>
		<td width="28" align="center"><label class="wz_img"></label></td>
		<td>当前位置：报表管理&gt;&gt;系统报表&gt;&gt;测试报表</td>
	</tr>
</table>
<form id="queryForm" method="post" action="raq.shtml?action=toBastTestPageResult" target="bottomcontent${rptConFile}">
	<table width="100%" height="100" border="0" cellpadding="4" cellspacing="4" class="detail">	
		<tr>
			<td class="detail2" height="100">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="detail3" id="jsontb">
						<tr>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								用户名称：
							</td>
							<td width="15%" class="detail_content">
								<input json="YHMC" name="YHMC" id="YHMC"  type="text" autocheck="true" inputtype="string"  mustinput='true'  label="用户名称"   style="width:100px">
					       </td>	
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								用户IP：
							</td>
							<td width="15%" class="detail_content">
								<input json="YHIP" name="YHIP" id="YHIP" type="text"  style="width: 100px" autocheck="true" inputtype="string" value="" label="用户IP" >							
							</td>
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								开始日期：
							</td>
							<td width="15%" class="detail_content">
								<input json="KSRQ" name="KSRQ" id="KSRQ" type="text" style="width: 100px" autocheck="true" inputtype="date" value="" label="开始日期" onclick="SelectDate()">	
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(KSRQ);">
							</td>	
							<td width="10%" align="right" class="detail_label"nowrap="nowrap">
								截止日期：
							</td>	
							<td width="15%" class="detail_content">
								<input json="JZRQ" name="JZRQ" id="JZRQ" type="text" style="width: 100px" autocheck="true" inputtype="date" value="" label="截止日期"  onclick="SelectDate()">
								<img align="absmiddle" style="cursor:hand" src="${ctx}/styles/${theme}/images/plus/date_16x16.gif" onclick="SelectDate(JZRQ);">
							</td>				
						</tr>
						
						<tr>
							<td colspan="10" align="center" valign="middle"
								>
								<input id="qurey" type="button" class="btn" value="查 询(Q)" title="Alt+O" accesskey="O">
								&nbsp;&nbsp;
								<input id="reset" type="reset" class="btn" value="重 置(R)" title="Alt+R" accesskey="R">
							</td>	
							<td style="display:none;">
								<input id="conDition" name="conDition" type="hidden" value="" >
								<input id="rptModel" name="rptModel" type="hidden" value="" >
								<input id="cutSql" name="cutSql" type="hidden" value="" >
							</td>												
						</tr>
					</table>
			</td>
		</tr>
	</table>
</form>

</body>
<script language="JavaScript">
$(function(){
	//form校验设置
	InitFormValidator(0);
	//查询！！！！！！
	$("#qurey").click(function(){
	
		if(!formChecked('queryForm')){
			return;
		}
		var obj = parent.document.getElementById("butHidTop");
	 	$(obj).trigger("click");
	 	
	/** 前台写SQL 不安全	
	var paramData = siglePackParamData();
	   var sql = " select YHM YHMC,YHIP,DLSJ from T_SYS_XTYH a,T_SYS_DLRZ b where a.XTYHID=b.XTYHID  ";
	   var coutsql=" select count(1) NUM from T_SYS_XTYH a,T_SYS_DLRZ b where a.XTYHID=b.XTYHID ";
       var YHMC = $("#YHMC").val();
        var YHIP = $("#YHIP").val();
        var KSRQ = $("#KSRQ").val();
        var JZRQ = $("#JZRQ").val();
       var condition="";
       if(YHMC!=""&&YHMC!=null)
        {
         condition=condition+" and a.YHM='"+YHMC+"'";
        }
       if(YHIP!=""&&YHIP!=null)
       {
         condition=condition+" and b.YHIP='"+YHIP+"'";
       }
       if(KSRQ!=""&&KSRQ!=null)
       {
         condition=condition+" and to_char(b.DLSJ,'YYYY-MM-DD')>='"+KSRQ+"'";
       }
        if(JZRQ!=""&&JZRQ!=null)
       {
         condition=condition+" and to_char(b.DLSJ,'YYYY-MM-DD')>='"+JZRQ+"'";
       }
       sql=sql+condition;
       coutsql=coutsql+condition;
		conDition = "rptSql=" + sql;
	    var rptModel = "login.raq";
	    $("#queryForm").attr("action",url);
		$("#conDition").val(conDition);
		$("#rptModel").val(rptModel);
		$("#cutSql").val(coutsql);
	  **/
		$("#queryForm").submit();
	});	 
  });
  function siglePackParamData(tableid){
		if(null == tableid){
			tableid = "jsontb";
		}
		var paramData = "";
		var inputs = $("#"+tableid+" :input");
		inputs.each(function(){
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
				}else if("radio" == type){
					if($(this).attr("checked")){
						key = $(this).attr("json");
						value= $(this).attr("value");
					}
				}else{
					key = $(this).attr("json");
					value= $(this).attr("value");
				}
				var inputtype = $(this).attr("inputtype");
				paramData = paramData+ "" + key + "=" + inputtypeFilter(inputtype,value) +"!";
			}
		});
		return 	paramData = paramData.substr(0,paramData.length-1);
	}
</script>



