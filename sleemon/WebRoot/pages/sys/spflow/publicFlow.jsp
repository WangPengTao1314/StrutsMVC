<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<c:set var="ctx"  value="${pageContext.request.contextPath}"/>
<form name="affirm" method="post"  action="${ctx}/system/flow.shtml">
<!--自己模块定义的参数-->
<input type="hidden" name="flowInterfaceName" value="">
<!--自己实现的flow接口类名称-->
<input type="hidden" name="ztbgFieldName" value="">
<!--状态变更时间字段名称-->
 <input type="hidden" name="businessType" value="">
<!---模板对应业务类型，请在数据字典里添加你需要的值，最好是简单明了与模块相对应--->
 <input type="hidden" name="tableName" value="">
<!----你的模块操作的业务数据物理表名-------->
 <input type="hidden" name="fieldName" value="">
<!----关键字的字段名称------>
 <input type="hidden" name="id">
<!----关键字的字段值--------->
 <input type="hidden" name="operateFlag" value="affirm">
 <input type="hidden" name="sourceURI" value="">
 
 
<div id="showLayer" style="LEFT: 250px;TOP: 100pt; display:none; WIDTH: 300px;HEIGHT: 200px; POSITION:absolute;  background-color:lightgrey"  style="display:false" >
<table  align="center">
      <tr align="center"> 
                <td align="center">
                    　
                    <p align=center> <font color="#0066ff" size="2">正在处理,请稍等......</font><font color="#0066ff" size="2" face="Arial">...</font>　 
                      <input type=text name=chart size=46 style="font-family:Arial; font-weight:bolder; color:#0066ff; background-color:#fef4d9; padding:0px; border-style:none;">
                      　　 　　
                      <input type=text name=percent size=4  style="color:#0066ff; text-align:center; border-width:medium; border-style:none;">
                      　　
</td>
</tr>
</table>
</div>
</form>
<script>　
var bar=0; 
var line="|";
var amount="|";　 
 
function count(){

bar=bar+1;　
if (bar*1>100) {
	bar = 1;
	amount = "";

}
amount =amount + line;　 
document.affirm.chart.value=amount; 
document.affirm.percent.value=bar+"%";　 
setTimeout("count()",200);  
}
</script>
<script language="javascript">
 	function affirmEvent(oper) {
 	      var currentID = document.affirm.id.value;
   		if (oper == 0) {
	    	//确认
	    	document.affirm.action="${ctx}/system/flow.shtml?action=toAffirm";
	    	count();
	    	showWaitPanel('提交!');
		    document.affirm.submit();
	    	return true;
  		} else if (oper == 1) {
	   	//审签  
		   	var tableName = document.affirm.tableName.value;
		   	var fieldName = document.affirm.fieldName.value;
		   	var flowInterfaceName = document.affirm.flowInterfaceName.value;
		   	var ztbgFieldName = document.affirm.ztbgFieldName.value;
		   	var id = document.affirm.id.value;
		   	//
		   	var businessType = document.affirm.businessType.value;
		   	var para = new Array();
		   	para[0] = tableName;
		   	para[1] = fieldName;
		   	para[2] = id;
	 	    var refresh = window.showModalDialog('${ctx}/system/flow.shtml?action=toInStance&tableName='+tableName+'&fieldName='+fieldName+'&id='+id+'&flowInterfaceName='+flowInterfaceName+'&ztbgFieldName='+ztbgFieldName+'&businessType='+businessType,para,'dialogwidth=600px; dialogheight=400px; status=no; edge=sunken;');
	   	        if (refresh == "T") {
	    	            window.location = utf8(document.affirm.sourceURI.value);
	   	        }
	   	        return true;
	  	}else if (oper == 2) {
	    	//撤签
	    	    document.affirm.action="${ctx}/system/flow.shtml?action=toRepeal";
	    	    document.affirm.submit();
	    	    return true;
	  	} else if (oper == 3) {
	   	//察看审签过程
	   		window.showModalDialog('${ctx}/jsp/admin/flow/instance.jsp?tableName='+tableName+'&fieldName='+fieldName+'&id='+id,para,'dialogwidth=600px; dialogheight=400px; status=no; edge=sunken;');
	   		return true;
	  	}
 	}
    //审核
    function beforeAffirm() {
        document.affirm.action="${ctx}/system/flow.shtml?action=toAffirm";
        document.affirm.submit();
  		return false;
 	}
    //显示历史信息
    function showHistory(id){
            var tableName = document.affirm.tableName.value;
		   	var fieldName = document.affirm.fieldName.value;
	window.showModalDialog("${ctx}/system/flow.shtml?action=toHistory&tableName="+tableName+"&fieldName="+fieldName+"&id="+id, "", "dialogHeight:400px;dialogWidth:640px; status=no;resizable=no;help=no");
    }
</script>


