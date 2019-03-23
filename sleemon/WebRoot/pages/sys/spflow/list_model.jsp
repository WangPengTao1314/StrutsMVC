<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
    <title>列表</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
</head>
<body >
<table width="99.8%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
    <tr>
        <td height="20px" valign="top">
            <table cellSpacing=0 cellPadding=0 border=0 width="100%" >
            <tr>
			<td><span id="pageTitle">当前位置：系统管理&gt;&gt;审批管理&gt;&gt;审批流程设置</span></td>
			<td width="50" align="right"></td>
		    </tr>   
            </table>
        </td>
    </tr>
    <tr>
        <td height="20px" valign="top">
            <div class="tablayer" style="margin-left:3px; border-bottom:1px solid #d3d3d3; padding-bottom:3px; margin-bottom:3px;">
                <table cellSpacing=0 cellPadding=0 border=0 width="100%">
                    <tr height="2"><td colspan="4"></td></tr>
                    <tr>
                        <td align="left" nowrap id="qxBtnTb">
                        
                        <c:if test="${qxcom.XT0011002 eq 1 }">
							<input type="button" id= "add" value="新增(N)" title="Alt+N" accesskey="N" class="btn" onclick="eventHandle(1)">
							<input type="button" id="modify" value="修改(U)" title="Alt+U" accesskey="U" class="btn" onclick="eventHandle(2)" >
						</c:if>					
						
						<c:if test="${qxcom.XT0011003 eq 1 }">
							<input type="button" id="delete" value="删除(R)" title="Alt+R" accesskey="R" class="btn" onclick="eventHandle(3)" >
						</c:if>
						<c:if test="${qxcom.XT0011001 eq 1 }">
                           <input id="query" id="query" type="button" class="btn" value="查询(F)" title="Alt+F" accesskey="F">
                        </c:if>	
						<c:if test="${qxcom.XT0011004 eq 1 }">
							<input type="button" id="life" value='生效(I)' class="btn" title="Alt+I" accesskey="I" onclick="eventHandle(4)" >
							<input type="button" id="lost" value='失效(P)' class="btn" title="Alt+P" accesskey="P" onclick="eventHandle(5)">
						</c:if>							
                        </td>
                        <td width="10">&nbsp;</td>
                    </tr>
                    <tr height="2"><td colspan="4"></td></tr>
                </table>
            </div>
        </td>
    </tr>
<tr>
	<td valign="top">
		<div class="lst_area">
		<table id="ordertb" width="100%" border="0" cellpadding="1" cellspacing="1" class="lst" >
			<tr  class="fixedRow">
				<th nowrap align="center" width="1%" ></th>
				<th nowrap align="center" dbname="MODELNUMBER">模板编号</th>
				<th nowrap align="center" dbname="MODELNAME">模板名称</th>
				<th nowrap align="center" dbname="BUSINESSTYPE">业务类型</th>
				<th nowrap align="center" dbname="MODELSTATE">状态</th>
				
			</tr>
			<c:forEach items="${page.result}" var="rst" varStatus="row">
			<c:set var="r" value="${row.count % 2}"></c:set>
			<c:set var="rr" value="${row.count}"></c:set>
			<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)" onClick="selectThis(this);setSelEid(document.getElementById('eid${rr}'));aaa();">
			<td ><input type="radio" name="eid" style="height:12px;valign:middle" id="eid${rr}" value="${rst.FLOWMODELID }" ></td>
			<td align="left">${rst.MODELNUMBER}</td>
			<td>${rst.MODELNAME}</td>
			<td align="left">${rst.BUSINESSTYPE}</td>
			<td align="left"><input type="hidden" id="state${rst.FLOWMODELID }" value="${rst.MODELSTATE}">${rst.MODELSTATE}</td>
			</tr>
			</c:forEach>
			<c:if test="${empty page.result}">
			<tr>
				<td height="25" colspan="13" align="center" class="lst_empty">&nbsp;无相关信息&nbsp;</td>
			</tr>
			</c:if>
		</table>
		</div>
	</td>
</tr>
<tr>
	<td height="8px">
		<table width="100%" border="0" cellpadding="0"cellspacing="0" class="lst_toolbar">
		<tr>
			<td>
				<form id="pageForm" action="#" method="post" name="listForm">
				<input type="hidden" id="pageSize" name="pageSize" value='${page.pageSize}'/>
					<input type="hidden" id="pageNo" name="pageNo" value='${page.currentPageNo}'/>
					<input type="hidden" id="orderType" name="orderType" value='${orderType}'/>
					<input type="hidden" id="orderId" name="orderId" value='${orderId}'/>
					<input type="hidden" id="selRowId" name="selRowId" value="${selRowId}">&nbsp;
					<input type="hidden" id="paramUrl" name="paramUrl" value="${paramCover.coveredUrl}">
					<span id="hidinpDiv" name="hidinpDiv"></span>
					${paramCover.unCoveredForbidInputs } 
				</form>
			</td>
			<td align="right">${page.footerHtml}${page.toolbarHtml}</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<div id="querydiv" class="query_div">
<form id="queryForm" method="post" action="#">
<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
	<tr>
		<td class="detail2">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
				<tr>
					<td width="10%" align="right" class="detail_label">模板编号：</td>
					<td width="15%" class="detail_content">
						<input id="MBBH" name="MBBH" type="text" >
					</td>
					<td width="10%" align="right" class="detail_label">模板名称：</td>
					<td width="15%" class="detail_content">
						<input id="MBMC" name="MBMC" type="text">
					</td>
					<td width="10%" align="right"class="detail_label">状态：</td>
					<td width="15%" class="detail_content">
						
						<select id="ZT" name="ZT" style="width:155px">
						   <option value="">--请选择--</option>
						   <option value="已生效">已生效</option>
						   <option value="未生效">未生效</option>
						</select>
					</td>
					<td width="10%" align="right" class="detail_label" >业务类型：</td>
					<td width="15%" class="detail_content">
						<input id="YWLX" name="YWLX" type="text" >
					</td>
				</tr>
				<tr>
					<td colspan="10" align="center" valign="middle" >
						<input id="q_search" type="button" class="btn" value="确定(O)" title="Alt+O" accesskey="O">&nbsp;&nbsp;
						<input id="q_close" type="button" class="btn" value="关闭(X)" title="Alt+X" accesskey="X">&nbsp;&nbsp;
						<input id="q_reset" type="reset" class="btn" value="重置(R)" title="Alt+R" accesskey="R">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</form>
</div>
<script language="JavaScript">
 	$(function(){
 	   listPageInit("flow.shtml?action=toTOPList");
 	});
 	
 	function aaa(){
 	    var id=$("#selRowId").val();
 	   var state=$("#state"+id).val();
 	   btnReset();
 	   if(state=='未生效'){
 	      btnDisable(["lost"]);
 	   }
       if(state=="已生效"){
 	      btnDisable(["modify","delete","life"]);
 	   }
 	}
 	
    function checkOne()
     {
        var cheradio =document.all("eid");
        var mainid=parent.document.main_cs.flowModelId.value;
        
        if(cheradio!=null&&cheradio.length>1)
        {
         
		  if(mainid==null||mainid=='')
          {    var wz = cheradio[0].value.indexOf("|");
		       var temp=cheradio[0].value.substr(0,wz);
               cheradio[0].checked=true;
               parent.document.main_cs.flowModelId.value=temp;
               parent.bottomcontent.window.location="${ctx}/system/flow.shtml?action=toView&flowModelId="+temp;
               
          }else
          {    
               for( i=0;i<cheradio.length;i++)
               {
                 var wz = cheradio[i].value.indexOf("|");
		         var temp=cheradio[i].value.substr(0,wz);
		         if(temp==mainid)
		         {
		           cheradio[i].checked=true;
		           parent.bottomcontent.window.location="${ctx}/system/flow.shtml?action=toView&flowModelId="+mainid;
		         }
		         
		       }
               
           }
        }else if(cheradio!=null&&cheradio!='')
        {
          
           parent.document.main_cs.flowModelId.value=temp;
           parent.bottomcontent.window.location="${ctx}/system/flow.shtml?action=toView&flowModelId="+temp;
        }
        
        
      
        
     }

    
</script>
<script type="text/javascript">
//add by zhuxw
 function delModel()
  {
     $.ajax({
		          url: "${ctx}/system/flow.shtml?action=delFlow&flowModelId="+$("#selRowId").val(),
		          type:"POST",
		          dataType:"text",
		          complete: function(xhr){
			          eval("jsonResult = "+xhr.responseText);
				      if(jsonResult.success===true){
					       parent.showMsgPanel(utf8Decode(jsonResult.messages));
					       $("#pageForm").attr("action","${ctx}/system/flow.shtml?action=toTOPList");
					       $("#pageForm").submit();
				       }else{
					       parent.showErrorMsg(utf8Decode(jsonResult.messages));
				       }
		           }
	          });
    
  }
    //查询设置相关 
	 function eventHandle(i)
    {
        var myObject = "";
        if (i == 1)
        {
           parent.bottomcontent.window.location="${ctx}/system/flow.shtml?action=toAdd";
          
        }
        else if (i == 2)
        { 
            var flowModelId = $("#selRowId").val();
            //$("#bottomcontent").attr("src","${ctx}/system/flow.shtml?action=toUpdate&flowModelId=" +flowModelId);
            parent.bottomcontent.window.location="${ctx}/system/flow.shtml?action=toUpdate&flowModelId=" +flowModelId;
        }
        else if (i == 3)
        {
        
            parent.showConfirm("确定要删除本条记录?","topcontent.delModel();");
            
        }
        else if (i == 4)
        {
             $.ajax({
		          url: "${ctx}/system/flow.shtml?action=validation&flowModelId="+$("#selRowId").val(),
		          type:"POST",
		          dataType:"text",
		          complete: function(xhr){
			          eval("jsonResult = "+xhr.responseText);
				      if(jsonResult.success===true){
					       parent.showMsgPanel("状态修改成功");
						   //$("#q_search").click(); 
					      // parent.window.location.href="${ctx}/system/flow.shtml?action=toList";
					      $("#pageForm").attr("action","${ctx}/system/flow.shtml?action=toTOPList");
					      $("#pageForm").submit();
				       }else{
					       parent.showErrorMsg(utf8Decode(jsonResult.messages));
				       }
		           }
	          });
	          return true;
            
        }
        else if (i == 5)
        {
            $.ajax({
		          url: "${ctx}/system/flow.shtml?action=invalidation&flowModelId="+$("#selRowId").val(),
		          type:"POST",
		          dataType:"text",
		          complete: function(xhr){
			          eval("jsonResult = "+xhr.responseText);
				      if(jsonResult.success===true){
					       parent.showMsgPanel("状态修改成功");
					      //$("#q_search").click(); 
					       //parent.window.location.href="${ctx}/system/flow.shtml?action=toList";
					       $("#pageForm").attr("action","${ctx}/system/flow.shtml?action=toTOPList");
					       $("#pageForm").submit();
				       }else{
					       parent.showErrorMsg(utf8Decode(jsonResult.messages));
				       }
		           }
	          });
	          return true;
           
        }
        else if (i == 6)
        {
            //定制使用条件
            myObject = document.detailform.businessType.value;
            var resultArray = window.showModalDialog("condition.jsp?businessType=" + myObject, myObject, "dialogHeight:420px;dialogWidth:600px; dialogLeft:138px; dialogTop:72px; status=no; resizable=yes");
            if (resultArray == null) return false;
            document.detailform.modelType.value = resultArray[0];
            document.detailform.event.value = "updFlow";
            document.detailform.submit();
            return true;
        }

    }
</script>
</body>
</html>
