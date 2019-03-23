<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<link href="${ctx}/styles/${theme}/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/scripts/core/jquery-1.6.3.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/core/TabPanel.js"></script>
<script type="text/javascript" src="${ctx}/scripts/util/Fader.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
<script type="text/javascript">
		//tab
		var tabpanel;
		$(document).ready(function(){
			  tabpanel = new TabPanel({
				renderTo:'tab',
				autoResizable:true,
				border:'none',
				active : 0,
				maxLength : 10,
				items : [{
					id:'1',
					title:'门户页面', 
					closable:false,
					html:"<iframe src='${ctx}/firstPage.shtml?action=toFirst' id='fir_page' width='100%' height='100%' frameborder='0' ></iframe>"
				}]
			 });
				//saleMssage();
				//setInterval("saleMssage()",1000*60*10);
			queryPrmt();
			
			$("#more_prmt").click(function(){    
				 fir_page.doMore_Prmt();
				 return false;
				//document.getElementById("to_more").click();
				//$("#queryMoreForm").attr("action","${ctx}/drpFirpage.shtml?action=toMorePrmt");
				//$("#queryMoreForm").submit();
			});
		});
		
		function addTab(id,name,url){		
		  tabpanel.addTab({id:id,title:name, html:'<iframe name="'+id+'IFrame" src="'+url+'" id="'+id+'IFrame" width="100%" height="100%" frameborder="0" ></iframe>'});
		}
		
		//促销信息
		function queryPrmt(){
			$.ajax({
				url: "${ctx}/drpFirpage.shtml?action=queryPrmt",
				type:"POST",
				dataType:"json",
				data:{},
				complete: function(xhr){
					eval("jsonResult = "+xhr.responseText);
					if(jsonResult.success===true){
						var data = jsonResult.data;
						 if(null ==data || ""==data){
							 $("#marqueeId").append("<span>暂无促销信息</span>");
						 }else{
							$.each(data,function(i,item){
							   $("#marqueeId").append("<span>"+item.PRMT_PLAN_NAME+"</span>").append("<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>");
						    });
						 }
						 
					}
				}
			});
		}
		
		
		
</script>
<title></title> 
	</head>
	<body  style="margin:0px;border:0px;overflow:auto" >
	<div id="saleTap" style="width:100%;height:30px">
		<table height="100%"  width="100%" border="0" cellpadding="0" cellspacing="0" align="left"> 
	    <tr class ="tabpanel_sale_content">
            <td width = "80" align="left">
				 <span style="margin-left:2px">促销信息:</span>
            </td>
            <td width = "80%">
            	<marquee SCROLLAMOUNT=2 SCROLLDELAY=100 onmouseover= "javascript:this.stop(); "  onmouseout= "javascript:this.start(); " id="marqueeId">
            		  
            		 <!--<c:forEach items="${prmtList}" var="prmt">
            		    <span id="" >${prmt.PRMT_PLAN_NAME}</span>
            		    <span id="" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            		 </c:forEach>
            		 <c:if test="${empty prmtList}">
            		   <span id="saleMsg" >暂无促销信息</span>
            		 </c:if>--> 
            	 </marquee>
            </td>
            <td>
             <a id="more_prmt" style="font-size:16px;text-decoration:none" href="#">&nbsp;&nbsp;more...</a>
            </td>
        </tr>
        </table>
	</div>
	<div id="tab" style="width:100%;"></div>
	</body>
	<script type="text/javascript" language="javascript"> 

function getSystemTime()
{
	   $.ajax({
			url: "${ctx}/login.shtml?action=getSysTime",
			type:"POST",
			dataType:"text",
			complete: function(xhr){
				eval("jsonResult = " + xhr.responseText);
				if (jsonResult.success === true) {
					
var strTitle="";
var strContent=jsonResult.messages;
var msgw,msgh,bordercolor; 
    msgw=400;//提示窗口的宽度 
    msgh=100;//提示窗口的高度 
    titleheight=25 //提示窗口标题高度 
    bordercolor="#336699";//提示窗口的边框颜色 
    titlecolor="#99CCFF";//提示窗口的标题颜色
    var sWidth,sHeight; 
    sWidth=document.body.offsetWidth; 
    sHeight=screen.height; 
    var bgObj=document.createElement("div"); 
    bgObj.setAttribute('id','bgDiv'); 
    bgObj.style.position="absolute"; 
    bgObj.style.top="0"; 
    bgObj.style.background="#777"; 
    bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75"; 
    bgObj.style.opacity="0.6"; 
    bgObj.style.left="0"; 
    bgObj.style.width=sWidth + "px"; 
    bgObj.style.height=sHeight + "px"; 
    bgObj.style.zIndex = "10000"; 
    document.body.appendChild(bgObj);
    var msgObj=document.createElement("div") 
    msgObj.setAttribute("id","msgDiv"); 
    msgObj.setAttribute("align","center"); 
    msgObj.style.background="white"; 
    msgObj.style.border="1px solid " + bordercolor; 
    msgObj.style.position = "absolute"; 
    msgObj.style.left = "50%"; 
    msgObj.style.top = "50%"; 
    msgObj.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif"; 
    msgObj.style.marginLeft = "-225px" ; 
    msgObj.style.marginTop = -75+document.documentElement.scrollTop+"px"; 
    msgObj.style.width = msgw + "px"; 
    msgObj.style.height =msgh + "px"; 
    msgObj.style.textAlign = "center"; 
    msgObj.style.lineHeight ="25px"; 
    msgObj.style.zIndex = "10001";
    var title=document.createElement("h4"); 
    title.setAttribute("id","msgTitle"); 
    title.setAttribute("align","right"); 
    title.style.margin="0"; 
    title.style.padding="3px"; 
    title.style.background=bordercolor; 
    title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);"; 
    title.style.opacity="0.75"; 
    title.style.border="1px solid " + bordercolor; 
    title.style.height="18px"; 
    title.style.font="12px Verdana, Geneva, Arial, Helvetica, sans-serif"; 
    title.style.color="white"; 
    title.style.cursor="pointer"; 
    title.title = "点击关闭"; 
    title.innerHTML="<table border='0′ width='100%'><tr><td align='left'><b>"+ strTitle +"</b></td><td>关闭</td></tr></table></div>"; 
    title.onclick=function(){ 
    document.body.removeChild(bgObj); 
    document.getElementById("msgDiv").removeChild(title); 
    document.body.removeChild(msgObj); 
    } 
    document.body.appendChild(msgObj); 
    document.getElementById("msgDiv").appendChild(title); 
    var txt=document.createElement("p"); 
    txt.style.margin="1em 0" 
    txt.setAttribute("id","msgTxt"); 
    txt.innerHTML=strContent; 
    document.getElementById("msgDiv").appendChild(txt); 

} else {
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
}

    function saleMssage(){
   	$.ajax({
			url: "",
			type:"POST",
			dataType:"text",
			complete: function(xhr){
				$("#saleMsg").text(xhr.responseText);
			}
		});
    }
    
  
</script>
</html>
