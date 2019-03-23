<!--
 * @prjName:喜临门营销平台
 * @fileName:Goodsorder_Frame
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
-->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>通用选择</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
</head>
<BODY>
<table cellspacing="0" cellpadding="0" width="99.9%" height="98%">
		<tr>
			<td >
				<div id="topdiv" style="height: 60%; width: 100%">
				  <iframe id="topcontent" name="topcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
				</div>
				<div class="tablayer tabBackground" style="height: 20px; width: 100%;">
					<table cellSpacing=0 cellPadding=0 border=0 width="100%">
						<tr>
						   <!--这里加入需要显示的标签页, 注意序号和标签的宽度-->
							  <td  align="center">
							  <input id="confirm" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
							  <input id="rest" type="button" class="btn" value="取消(C)" title="Alt+C" accesskey="C">&nbsp;&nbsp;
							 </td>
						</tr>
					</table>
				</div>
				<div id="bottomdiv" style="height: 34.5%; width: 100%">
					<!-- 下帧 -->
					<iframe id="bottomcontent" name="bottomcontent" src="#" frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
				</div>
			</td>
		</tr>
	</table>
	
<form target="MainList" method="post" action="" style="display:none">
<input type="hidden" id="GOODS_ORDER_IDS" name="GOODS_ORDER_IDS" value="">
<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">
<input type="hidden" id="module" name="module" value="">

<table id="jsontb" >
<input type="hidden" id="ORDER_CHANN_ID"    json="ORDER_CHANN_ID"   name="ORDER_CHANN_ID" value="${rst.ORDER_CHANN_ID}">
<input type="hidden" id="ORDER_CHANN_NO"    json="ORDER_CHANN_NO"   name="ORDER_CHANN_NO" value="${rst.ORDER_CHANN_NO}">
<input type="hidden" id="ORDER_CHANN_NAME"  json="ORDER_CHANN_NAME"   name="ORDER_CHANN_NAME" value="${rst.ORDER_CHANN_NAME}">
<input type="hidden" id="BILL_TYPE"         json="BILL_TYPE"   name="BILL_TYPE" value="${rst.BILL_TYPE}">
<input type="hidden" id="RECV_CHANN_ID"     json="RECV_CHANN_ID"   name="RECV_CHANN_ID" value="${rst.RECV_CHANN_ID}">
<input type="hidden" id="RECV_CHANN_NO"     json="RECV_CHANN_NO"   name="RECV_CHANN_NO" value="${rst.RECV_CHANN_NO}">
<input type="hidden" id="RECV_CHANN_NAME"   json="RECV_CHANN_NAME"   name="RECV_CHANN_NAME" value="${rst.RECV_CHANN_NAME}">
<input type="hidden" id="SHIP_POINT_ID"     json="SHIP_POINT_ID" name="SHIP_POINT_ID" value="${rst.SHIP_POINT_ID}">
<input type="hidden" id="AREA_ID"           json="AREA_ID"  name="AREA_ID" value="${rst.AREA_ID}">

</table>


</form>
 
<script type="text/javascript">
    $(function(){
    	//${ctx}/goodsorder.shtml?action=toMergeList&selRowId=${selRowId}
    	var jsonData = siglePackJsonData();
    	var url = "goodsorder.shtml?action=toMergeList&jsonData="+utf8(jsonData);
    	$("#topcontent").attr("src",url);
    	 
    });
    
    function gotoBottomPage(GOODS_ORDER_IDS){
    	if("" == GOODS_ORDER_IDS || "label_1" == GOODS_ORDER_IDS){
    		GOODS_ORDER_IDS =  parent.topcontent.$("#selRowId").val();
    	}
    	var url = "goodsorder.shtml?action=childListMore&mergeFlage=true&GOODS_ORDER_IDS="+GOODS_ORDER_IDS;
    	$("#bottomcontent").attr("src",url);
    }
    
    
    $("#confirm").click(function(){
		setFatherValue();
		window.close();
    	 
    });
    
    $("#rest").click(function(){
    	rest();
    	window.close();
    });
    
    function rest(){
    	$("#GOODS_ORDER_IDS").val("");
    }
    
   function setFatherValue(){
	    var GOODS_ORDER_IDS = topcontent.$("#selRowId").val();
	    if(null == GOODS_ORDER_IDS || "" == GOODS_ORDER_IDS){
	    	return;
	    }
	    
	    var MERGER_REMARKS = topcontent.getMergeRemrk();
	    var childJson = topcontent.getChildJson();
	    window.dialogArguments.$("#MERGER_GOODS_ORDER_IDS").val(GOODS_ORDER_IDS);
	    window.dialogArguments.$("#MERGER_REMARKS").val(MERGER_REMARKS);
	    window.dialogArguments.$("#childJson").val(childJson);
	    
   		window.close();
   }
</script>
</body>