<%@ page language="java" pageEncoding="UTF-8"%> 
<%@page import="com.hoperun.sys.model.UserBean,java.util.Map"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
 UserBean userBean = (UserBean) session.getAttribute("UserBean");
 String userName=userBean.getXM();
 String ztInfo=userBean.getLoginZTMC();
 %>
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/top.css" />
<script type="text/javascript" src="${ctx}/scripts/core/jquery-1.6.3.min.js"></script>
<style type="text/css">
 
.bs_right_low {
    background-repeat: no-repeat;
    position: absolute;
    right: 26px;
    top: 28px;
}
img {
	border: 0px currentColor;
}
</style>
</head>
<body>
<table width="100%" height="85" border="0" cellpadding="0" cellspacing="0" >
<tr>
   <td align="right" >      
	    <table height="100%" width="100%" border="0" cellpadding="0" cellspacing="0" class="white" >
	      <tr>
	        <td id="pic2"  height="23" align="right" style="background:#f5f5f5" colspan="2">
		        <table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr valign="middle">
		            <td align="right" nowrap="nowrap">
		          		<div  style="font-size:12px;"><a href="#" id="message"><img id="pic3"></img></a> <span id="messageSize" style="color: red;"></span>&nbsp;&nbsp;  欢迎&nbsp;<span id="user"><%=userName %></span>&nbsp;<%=ztInfo %>&nbsp;&nbsp;
		          		<!--  <label  style="cursor: pointer"  class='fontcss' onclick="parent.mainFrame.getSystemTime()" ></label>-->
						&nbsp;&nbsp;<span style=""><img id="pic7" align="top" style="vertical-align:middle;"></img><label id="setPwd" style="cursor: pointer;" >修改密码</label>&nbsp;
						<img align="top" src="${ctx}/styles/${theme}/images/top/split-top.gif"></img>
			          	<a href="${ctx}/pages/index/logout.jsp" target="_top" style ="text-decoration:none;" ><img id="pic4" align="top" style="vertical-align:middle;"></img>&nbsp;<label  onclick='$("#pic4").click()'  style="cursor: pointer;" >注销</label>&nbsp;
			          	<img align="top" src="${ctx}/styles/${theme}/images/top/split-top.gif"></img>
			            <a href="#" id="close" style ="text-decoration:none;"><img id="pic5" align="top" style="vertical-align:middle;"></img>&nbsp;<label style="cursor: pointer;" >关闭</label>&nbsp;
			            </a></a></span></div>
		            </td>
		          
		          </tr>
		        </table>
	        </td>
	      </tr>
	      <tr>
	      	  <td><img src="${ctx}/styles/${theme}/images/top/login_all.png"></img></td>
		      <td height="62" align="right" valign="bottom">
		          <div id="bs_right_low">
		          <!-- 
						<a href="javascript:addTab('qdmhzcjs001','整车计算','carcalculate.shtml?action=listGoodsOrder&search=search');"><img src="../../styles/drp/images/carCalculate.png"> </a>
						-->
						<a href="javascript:addTab('qdmhgwc001','购物车页面','shopcar.shtml?action=toInfo','FX0020401');"><img src="../../styles/drp/images/gwc.gif" border=0></a>&nbsp;&nbsp;
						<a href="javascript:addTab('qdmhdh001','我要订货','myorder.shtml?action=toSelPrd','FX0020401');"><img src="../../styles/drp/images/wydh.gif" border=0></a>&nbsp;&nbsp;
						<a href="javascript:addTab('DR3A01','投诉与建议','adviseinit.shtml?action=toFrame','FX0040101');"><img src="../../styles/drp/images/tsjy.gif"></a>&nbsp;&nbsp;
					    <a href="javascript:addTab('DR1E01','订货订单维护','goodsorderhd.shtml?action=toFrame&module=wh','FX0020401');"><img src="../../styles/drp/images/ddck.gif"></a>&nbsp;&nbsp;
			      </div>
		      </td>
	      </tr>
	    </table>
	   </td>		    
    </tr>		  
</table>
<table width="100%" height="26" border="0" cellpadding="0" cellspacing="0" class ="navbar">
	   <tr> 
	   		<td align="left" >
	   			<span style="padding-left:5%;color:#fff;font-size:12px"><script language="javascript">
	   				var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
					var now = new Date();
				    var year=now.getFullYear();
					var month=now.getMonth()+1;
					var day=now.getDate();
				    var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()];
	   				document.write(currentime);
	   			</script></span>
	   		</td>
	   		<td align="right" valign="middle" height="26px">
	   			<table cellpadding="0" cellspacing="0"  border="0">
	   				<tr>
	   					<td align="left" height="26px"><script language="javascript">document.write(parent.writeTopMenus());</script></td>
	   				</tr>
	   			</table>         
		    </td>
	   </tr>
</table>
</body>
<script type="text/javascript">
 
	 //add by zhuxw 2012-11-5
   $(function(){
      //$("#pic1").attr("background","${ctx}/styles/${theme}/images/top/logo_all.gif");
      //$("#pic2").attr("background","${ctx}/styles/${theme}/images/top/bs_bannercenter_new.jpg");
      $("#pic3").attr("src","${ctx}/styles/${theme}/images/top/nonews.gif");
      $("#pic4").attr("src","${ctx}/styles/${theme}/images/top/zx.gif");
      $("#pic5").attr("src","${ctx}/styles/${theme}/images/top/gb.gif");
      $("#pic6").attr("background","${ctx}/styles/${theme}/images/top/top5.png");
      $("#pic7").attr("src","${ctx}/styles/${theme}/images/top/modpwd.gif");
      
      //获取分销门户标记 add by zzb 2014-2-10
      var indexTop = parent.indexTop();
      if("drp" == indexTop){
    	   $("#bs_right_low").show();
      }else{
    	   $("#bs_right_low").hide();
      }
      var messageSize = parent.messageSize();
      if(messageSize>=1){
    	 // $("#messageSize").text("("+messageSize+")");
      }
      
    });
    
    var preMenuObj = null;
    var old = "";
    function changeSelectMenu(menuid,menuname){
    
    	/*if(preMenuObj != null){
    		preMenuObj.css("color","#fff");
    	}
    	
		$("#"+menuid).css("color","#000");
		preMenuObj = $("#"+menuid);*/
		
		
		if(preMenuObj != null){
    		preMenuObj.html(old);
    	}
    	
		preMenuObj = $("#td"+menuid);		
		old = "&nbsp;&nbsp;<b><a style ='color:#fff;font-size:12px;font-weight:bold;text-decoration:none;' href='#' title='" + menuname + "' onclick='changeSelectMenu(\"" + menuid + "\",\"" + menuname+ "\");' >"+menuname+"</a><b>&nbsp;&nbsp;";
		
		
		
		$("#td"+menuid).html("<div style='float:left' style='text-align:center' ><img src='${ctx}/styles/${theme}/images/top/left.gif' alt='' style='vertical-align:middle;'/></div>");
		$("#td"+menuid).html($("#td"+menuid).html()+"<div style='text-align:center' class='menubackground'>&nbsp;&nbsp;<b><a style ='color:#835d2c;font-size:12px;font-weight:bold;text-decoration:none;' href='#' title='" + menuname + "' onclick='changeSelectMenu(\"" + menuid + "\",\"" + menuname+ "\");' >"+menuname+"</a><b>&nbsp;&nbsp;</div>");
		$("#td"+menuid).html($("#td"+menuid).html()+"<div style='float:left;text-align:center'><img src='${ctx}/styles/${theme}/images/top/right.gif' alt='' style='vertical-align:middle;'/></div>");
		
		
		parent.treeCtl.showMenu(menuid);
	};
 
	document.getElementById("close").onclick=function closeWindow(){
		window.parent.window.close();
	}
	
	document.getElementById("setPwd").onclick=function closeWindow(){
		window.showModalDialog('${ctx}/login.shtml?action=setPwdLogin&sid='+new Date().valueOf(),'','dialogwidth=620px; dialogheight=280px; status=no;scroll=yes');
	}
	document.getElementById("message").onclick=function closeWindow(){
		window.showModalDialog('${ctx}/firstPage.shtml?action=getNewShortInfo&sid='+new Date().valueOf(),'','dialogwidth=950px; dialogheight=600px; status=no');
		fresh();
	}
	fresh();
    var int=self.setInterval("fresh();",120000);
	function fresh()
	{
		$.ajax({
			url: "${ctx}/firstPage.shtml?action=queryNotLookMess",
			type:"POST",
			dataType:"text",
			complete: function(xhr){
			    eval("jsonResult="+xhr.responseText);
			    if(jsonResult.success==true){
			    	var haveSize = jsonResult.messages;
			    	if(haveSize>0){
			    		$("#messageSize").text("("+haveSize+")");
			    	}
			    	document.getElementById("message").innerHTML="<img align='top' src='${ctx}/styles/${theme}/images/top/havenews.gif'>";
			    }else{
			    	$("#messageSize").text("");
			    	document.getElementById("message").innerHTML="<img align='top' src='${ctx}/styles/${theme}/images/top/nonews.gif'>";
			   	}
			}
		});
	}
	
	var mainFrame = window.top.mainFrame;  
	function addTab(id,title,url,QX){
		if(null == QX){
			QX = "";
		}
		if(null != url && "" != url){
			 $.ajax({
				url: "../../drpFirpage.shtml?action=checkHaveQx&QX="+QX,
				type:"POST",
				dataType:"json",
				data:{},
				complete: function(xhr){
					eval("jsonResult = "+xhr.responseText);
					if(jsonResult.success===true){
						alert(jsonResult.messages);
					}else{
					    mainFrame.addTab(id,title,"../../"+url);
					}
				}
			});
			
		}
		
	}
</script>

</html>
