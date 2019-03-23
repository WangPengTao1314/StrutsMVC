<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<c:set var="ctx"  value="${pageContext.request.contextPath}"/>
<c:set var="theme"  value="${sessionScope.UserCSS }"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${ctx}/scripts/core/jquery-1.6.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/core/Jscore.js"></script>
	<title>卡券列表</title> 
<style type="text/css">


body { 
    margin:0; 
    padding:0; 
    background-color:#E4E8EC; 
} 
.wrap { 
    margin:15px auto; 
    width:90%; 
    overflow:hidden; 
} 
.loginForm { 
    box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 0 1px 1px rgba(0, 0, 0, 0.2), 0 3px 0 #fff, 0 4px 0 rgba(0, 0, 0, 0.2), 0 6px 0 #fff, 0 7px 0 rgba(0, 0, 0, 0.2); 
    position:absolute; 
    z-index:0; 
    background-color:#FFF; 
    border-radius:4px; 
    height:100%; 
    width:90%; 
    background: -webkit-gradient(linear, left top, left 24, from(#EEE), color-stop(4%, #FFF), to(#EEE)); 
    background: -moz-linear-gradient(top, #EEE, #FFF 1px, #EEE 24px); 
    background: -o-linear-gradient(top, #EEEEEE, #FFFFFF 1px, #EEEEEE 24px); 
} 
.loginForm:before { 
    content:''; 
    position:absolute; 
    z-index:-1; 
    border:1px dashed #CCC; 
    top:5px; 
    bottom:5px; 
    left:5px; 
    right:5px; 
    box-shadow: 0 0 0 1px #FFF; 
} 
.loginForm h1 { 
    text-shadow: 0 1px 0 rgba(255, 255, 255, .7), 0px 2px 0 rgba(0, 0, 0, .5); 
    text-transform:uppercase; 
    text-align:center; 
    color:#666; 
    line-height:3em; 
    margin:16px 0 20px 0; 
    letter-spacing: 4px; 
    font:normal 46px/1 Microsoft YaHei, sans-serif; 
} 
fieldset { 
    border:none; 
    padding:10px 10px 0; 
} 
fieldset input[type=text] { 
    background:url(style/default/images/user.png) 4px 5px no-repeat; 
}
fieldset textarea{ 
    background:url(style/default/images/user.png) 4px 5px no-repeat;  width:310px; 
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#EEEEEE', endColorstr='#FFFFFF'); 
} 
fieldset input[type=password] { 
    background:url(style/default/images/password.png) 4px 5px no-repeat; 
} 
fieldset input[type=text], fieldset input[type=password] { 
    width:100%; 
    line-height:2em; 
    font-size:12px; 
    height:24px; 
    border:none; 
    padding:3px 4px 3px ; 
    width:200px; 
} 
fieldset input[type=submit], [type=button] { 
    font-size:30px;
    text-align:center; 
    padding:2px 20px; 
    line-height:2em; 
    border:1px solid #FF1500; 
    border-radius:3px; 
    background: -webkit-gradient(linear, left top, left 24, from(#FF6900), color-stop(0%, #FF9800), to(#FF6900)); 
    background: -moz-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px); 
    background:-o-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px); 
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800', endColorstr='#FF6900'); 
    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800', endColorstr='#FF6900')"; 
    height:60px; 
    cursor:pointer; 
    letter-spacing: 4px; 
    margin-left:10px; 
    color:#FFF; 
    font-weight:bold; 
} 
fieldset input[type=submit]:hover { 
    background: -webkit-gradient(linear, left top, left 24, from(#FF9800), color-stop(0%, #FF6900), to(#FF9800)); 
    background: -moz-linear-gradient(top, #FF9800, #FF6900 0, #FF9800 24px); 
    background:-o-linear-gradient(top, #FF6900, #FF6900 0, #FF9800 24px); 
filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF6900', endColorstr='#FF9800'); 
    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF6900', endColorstr='#FF9800')"; 
} 
.inputWrap { 
    background: -webkit-gradient(linear, left top, left 24, from(#FFFFFF), color-stop(4%, #EEEEEE), to(#FFFFFF)); 
    background: -moz-linear-gradient(top, #FFFFFF, #EEEEEE 1px, #FFFFFF 24px); 
    background: -o-linear-gradient(top, #FFFFFF, #EEEEEE 1px, #FFFFFF 24px); 
    border-radius:3px; 
    border:1px solid #CCC; 
    margin:20px 20px 0; 
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#EEEEEE', endColorstr='#FFFFFF'); 
    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#EEEEEE', endColorstr='#FFFFFF')"; 
} 
fieldset input[type=checkbox] { 
    margin-left:10px; 
    vertical-align:middle; 
} 
fieldset a { 
    color:blue; 
    font-size:12px; 
    margin:6px 0 0 10px; 
    text-decoration:none; 
} 
fieldset a:hover { 
    text-decoration:underline; 
} 
fieldset span { 
    font-size:12px; 
} 

.fieldspan { 
    margin-left:14px;
} 

.inputWrap_1 { 
    background: -webkit-gradient(linear, left top, left 24, from(#FFFFFF), color-stop(4%, #EEEEEE), to(#FFFFFF)); 
    background: -moz-linear-gradient(top, #FFFFFF, #EEEEEE 1px, #FFFFFF 24px); 
    background: -o-linear-gradient(top, #FFFFFF, #EEEEEE 1px, #FFFFFF 24px); 
    border-radius:3px; 
	 margin:20px 20px 0; 
}
.top { 
    margin:10px auto; 
    width:380px; 
    overflow:hidden; 
}
.table_content th {
	background:#f6f4eb;
	font-weight:bold;
	/**width:33%;**/
	height:24px;
	text-align:center;
	font-size:14px;
}
.table_content {
	margin:0 auto 6px;
	text-align:center;
}
.table_content th, .table_content td {
	border-right:1px solid #c4c4c4;
	border-bottom:1px solid #c4c4c4;
	font-size: 28px;
	height:40px;
	line-height:22px;
}
.table_content table {
	border-top:1px solid #c4c4c4;
	border-left:1px solid #c4c4c4;
	margin: 0 auto;
	width: 99%;
}
</style>

<script type="text/javascript">
		if(/Android (\d+\.\d+)/.test(navigator.userAgent)){
			var version = parseFloat(RegExp.$1);
			if(version>2.3){
				var phoneScale = parseInt(window.screen.width)/640;
				document.write('<meta name="viewport" content="width=640, minimum-scale = '+ phoneScale +', maximum-scale = '+ 2 +', target-densitydpi=device-dpi">');
			}else{
				document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
			}
		}else{
			document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
		}
	</script>

</head> 
 
<body><%-- 
 <div class="top"><img src="${ctx}/pages/index/image/logo_new.jpg" style="width:380px; max-width:100%;"/></div>
 
--%><div class="wrap"> 
  <form action="#" method="post" id="mainForm"> 
    <section class="loginForm"> 
      <header> 
        <h1>喜临门</h1> 
      </header> 
	  <fieldset style="text-align:center;"> 
          <input type="button" value="卡券录入" onclick="cardEdit();">
          <input type="button" value="注销" onclick="loginOut();" style="margin-left: 100px;">
       </fieldset> 
	   <fieldset style="text-align:center;" > 
          <div class="table_content" >
           <table  class="table_content" width="100%"  cellpadding="1" cellspacing="1">
		    <tr>
			 <th style="background-color:#F4CC87;border: 1 solid #DFDFDF;">卡券编号</th>
			 <th style="background-color:#F4CC87;border: 1 solid #DFDFDF;">顾客姓名</th>
			 <th style="background-color:#F4CC87;border: 1 solid #DFDFDF;">顾客手机</th>
			</tr>
			<c:forEach items="${page}" var="rst" varStatus="row">
			   <tr>
				  <td width="40%">${rst.MARKETING_CARD_NO}</td>
				  <td width="30%">${rst.CUST_NAME}</td>
				  <td width="30%">${rst.MOBILE_PHONE}</td>
				</tr>
			</c:forEach>
		    <tr>
			  <td >20150000100001</td>
			  <td>张三</td>
			  <td>15365137050</td>
			 </tr>
		    </table>
		   </div>
        </fieldset> 
      </div> 
    </section> 
  </form> 
</div> 

 <form action="#" method="post" id="listForm"> </form>
</body> 
</html> 

<script type="text/javascript">

function cardEdit(){
	$("#listForm").attr("action","${ctx}/wap.shtml?action=toCardEdit");
	$("#listForm").submit();
}

function loginOut(){
	$("#listForm").attr("action","${ctx}/wap.shtml?action=loginOut");
	$("#listForm").submit();
}

 $(function(){
		$("#userId").focus();
		$("#userId").blur(function(){
			var userId = $(this).val();
			var select = document.getElementById("S_ZTID");
			$("#S_ZTID").empty();
			if(null != userId && "" != userId){
				$.ajax({
					url: "login.shtml?action=getZtxx",
					type:"POST",
					dataType:"text",
					data:{"userId":userId}, 
					complete: function(xhr){
						eval("jsonResult = "+xhr.responseText);
						if(jsonResult.success===true){
							$("#S_ZTID").append(utf8Decode(jsonResult.messages));
						}else{
							parent.showErrorMsg(utf8Decode(jsonResult.messages));
						}
					}
				});
			}
		});
 });
 
</script>

 


