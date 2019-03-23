<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
 
<html>
<head>
<title>通用选择</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
<link rel="stylesheet" href="${ctx}/styles/${theme}/css/newSelComm.css" type="text/css">
 
</head>
<body style="border:0;margin:0px 0px 0px 0px;overflow:hidden">
<%--<iframe src="" name="MainList" id="MainList" style="width:100%;height:5px;overflow:hidden" frameborder="0"></iframe>
--%><form target="MainList" method="post" action="" style="display:none">
<input type="hidden" id="SELECT_DELIVER_ORDER_ID" name="SELECT_DELIVER_ORDER_ID" value="">
<input type="hidden" id="SELECT_DELIVER_ORDER_NO" name="SELECT_DELIVER_ORDER_NO" value="">
<input type="hidden" id="SELECT_RECV_CHANN_ID" name="SELECT_RECV_CHANN_ID" value="">

<input type="hidden" id="selRowId" name="selRowId" value="">
<input type="hidden" id="module" name="module" value="">
</form>

<iframe name="SubFrame" id="SubFrame" 
src="${ctx}/pdtdeliver.shtml?action=toList&module=&forWard=select"
 name="subFrame" id="subFrame"  style="width:100%;height:100%" frameborder="0"></iframe>

</body>
</html>

<script type="text/javascript">
   function setFatherValue(){
	    window.dialogArguments.$("#SELECT_DELIVER_ORDER_ID").val($("#SELECT_DELIVER_ORDER_ID").val());
   		window.dialogArguments.$("#SELECT_DELIVER_ORDER_NO").val($("#SELECT_DELIVER_ORDER_NO").val());
   		window.dialogArguments.$("#SELECT_RECV_CHANN_ID").val($("#SELECT_RECV_CHANN_ID").val());
   		window.close();
   }
   
   
    
    
</script>




