<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<html>
<head>
    <title>模板管理主框架</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <%@ include file="/commons/jslibs.jsp"%>
     <link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
</head>

<body >
<form name="main_cs" method="post" action="">
    <input type="hidden" name="flowModelId" value="${flowModelId}">
</form>
<table cellSpacing=0 cellPadding=0 border=0 width="100%" height="100%">
<tr>
	<td valign="top" height="98%">
    <div id="topdiv" style="height:50%;width:100%">
			<iframe id="topcontent" name="topcontent" style="margin-left: 0px"  frameBorder=0 width="100%" height="100%" vspace="0px" hspace="0px" scrolling="AUTO"></iframe>
	</div>
		
	<div class="tablayer" style="height:20px;width:100%;">
			<table cellSpacing=0 cellPadding=0 border=0 width="100%">
				<tr>
					<td class="label_line" width="8px">&nbsp;</td>
					<!--这里加入需要显示的标签页, 注意序号和标签的宽度-->
					<td id="label" nowrap="nowrap">
                          <span id="label_1" class="label_down" style="margin-top: 2px;">&nbsp;详细信息&nbsp;</span>
						  <span id="label_2" class="label_down" style="margin-top: 2px;">&nbsp;模板节点&nbsp;</span>
						  <input type="hidden" id="showLabel" value="label_1" />
					</td>
					<!--标签页添加完毕-->
					
					<td class="label_line" align="right" width="150px">
						<input type="button" id="butHidTop" class="button" value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" title="Alt+↑" >
						<input type="button" id="butHidBottom" class="button" value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" title="Alt+↓">
					</td>
				</tr>
			</table>
		</div>
	  <div id="bottomdiv" style="height:45%;width:100%">
			<!-- 下帧 -->
			<iframe id="bottomcontent" name="bottomcontent" src="#"  frameBorder=0 width="100%" height="100%" frameborder="0" scrolling="AUTO"></iframe>
	  </div>
   </td>
</tr>
</table>
<input type="hidden" id="selRowId" value="${selRowId}"/>
<input type="hidden" id="orderId" value="${orderId}" />
<input type="hidden" id="orderType" value="${orderType}" />
</body>
</html>
<script language="JavaScript">

	$(function(){
       framePageInit("flow.shtml?action=toTOPList");
	});
	
	 
    var MKMC = "SHENPIMUBAN";  //审批模板
    function refreshSelf(flowModelId)
    {
        window.location = "${ctx}/system/flow.shtml?action=toList&flowModelId=" + flowModelId;
    }

    var oldLableObject = null;
    function showit(myLable, file, lmax)
    {
        if(myLable==null)
        {
            myLable="label_0";
        }
        var obj = document.all[myLable];
        try
        {
            
            switchLabel(myLable.substring(myLable.length - 1, myLable.length));
        }
        catch(e)
        {
            //
        }
        if (file != document.all.bottomcontent.src)
        {
            document.all.bottomcontent.src = file;
        }
    }
</script>

<script language="javascript">
    

  function checkOne()
     {
        /*<c:if test="${refreshFlag eq 'T'}">
           parent.document.main_cs.flowModelId.value='${flowModelId}';
           parent.topcontent.listForm.submit();   
        </c:if>*/
        var cheradio =document.all("eid");
        var mainid=document.main_cs.flowModelId.value;
        
        if(cheradio!=null&&cheradio.length>1)
        {
         
		  if(mainid==null||mainid=='')
          {    var wz = cheradio[0].value.indexOf("|");
		       var temp=cheradio[0].value.substr(0,wz);
               cheradio[0].checked=true;
               document.main_cs.flowModelId.value=temp;
               bottomcontent.window.location="${ctx}/system/flow.shtml?action=toView&flowModelId="+temp;
               
          }else
          {    
               for( i=0;i<cheradio.length;i++)
               {
                 var wz = cheradio[i].value.indexOf("|");
		         var temp=cheradio[i].value.substr(0,wz);
		         if(temp==mainid)
		         {
		           cheradio[i].checked=true;
		           bottomcontent.window.location="${ctx}/system/flow.shtml?action=toView&flowModelId="+mainid;
		         }
		         
		       }
               
           }
        }else if(cheradio!=null&&cheradio!='')
        {
          
           document.main_cs.flowModelId.value=temp;
           bottomcontent.window.location="${ctx}/system/flow.shtml?action=toView&flowModelId="+temp;
        }
     }
     
    function gotoBottomPage(action){
       //获取当前选中的记录
	var selRowId = $("#selRowId").val();
    
	//初始化时下帧页面的action
	if(null == action || "label_1" == action){//"label_1"的判断是为了与主从界面通用
		action = "toView";
	}else{
	    if (selRowId == '') 
	    {
	       alert('请先选定模板！')
	       return false;
	    }
		action = "setFlow";
	}
	var url = "flow.shtml?action="+action+"&selRowId="+selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src",url);
    }

</script>

