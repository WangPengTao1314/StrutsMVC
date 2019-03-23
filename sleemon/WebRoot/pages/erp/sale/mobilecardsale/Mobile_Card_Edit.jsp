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
	<script type="text/javascript" src="${ctx}/pages/erp/sale/mobilecardsale/Mobile_Card_Edit.js"></script>
	
	<title>卡券录入</title> 
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
    height:1200px; 
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
    font-size:30px; 
    height:60px; 
    border:none; 
    padding:3px 4px 3px ; 
    width:70%; 
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
    height:70px;
    font-size:20px;
filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#EEEEEE', endColorstr='#FFFFFF'); 
    -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#EEEEEE', endColorstr='#FFFFFF')"; 
} 
.inputWrap span{
font-size:25px;
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
	 height:240px;
    font-size:30px;
}

.inputWrap_1 span{
font-size:25px;
}

.top { 
    margin:10px auto; 
    width:90%;  
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
	font-size: 13px;
	height:22px;
	line-height:22px;
}
.table_content table {
	border-top:1px solid #c4c4c4;
	border-left:1px solid #c4c4c4;
	margin: 0 auto;
	width: 95%;
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
 
<body> 
 
<div class="wrap"> 
  <form action="#" method="post" id="mainForm"> 
    <section class="loginForm"> 
      <header> 
        <h1>喜临门</h1> 
      </header> 
	  <fieldset style="text-align:center;"> 
          <input type="button" value="查看列表" onclick="cardList();">
          <input type="button" value="注销" onclick="loginOut();" style="margin-left: 100px;">
        </fieldset> 
      <div class="loginForm_content"> 
        <fieldset> 
          <div class="inputWrap" style="text-overflow:ellipsis; white-space: nowrap;"> 
		    <span style="" class="fieldspan">卡券编号：</span>
            <input type="text"  name="MARKETING_CARD_NO" id="MARKETING_CARD_NO" json="MARKETING_CARD_NO" value="${rst.MARKETING_CARD_NO}" onblur="loadCard();"  placeholder="请输入卡券编号" autofocus required> 
          </div> 
          <div class="inputWrap" style="text-overflow:ellipsis; white-space: nowrap;"> 
		    <span class="fieldspan">卡券类型：</span>
            <input type="text" name="CARD_TYPE" id="CARD_TYPE" json="CARD_TYPE" value="${rst.CARD_TYPE}" placeholder="请输入卡券面值" required> 
          </div> 
		   <div class="inputWrap" style="text-overflow:ellipsis; white-space: nowrap;"> 
		   <span class="fieldspan">卡券面值：</span>
            <input type="text" name="CARD_VALUE" id="CARD_VALUE" json="CARD_VALUE" value="${rst.CARD_VALUE}"  placeholder="请输入卡券面值" required> 
          </div> 
		   <div class="inputWrap" style="text-overflow:ellipsis; white-space: nowrap;"> 
		   <span class="fieldspan">顾客姓名：</span>
            <input type="text" name="CUST_NAME" id="CUST_NAME" json="CUST_NAME" value="${rst.CUST_NAME}"  placeholder="请输入顾客姓名" required> 
          </div> 
		 <div class="inputWrap" style="text-overflow:ellipsis; white-space: nowrap;"> 
		   <span class="fieldspan">顾客手机：</span>
            <input type="text" name="MOBILE_PHONE" id="MOBILE_PHONE" json="MOBILE_PHONE" value="${rst.MOBILE_PHONE}"  placeholder="请输入顾客手机" required> 
          </div>
          <%--
		  <div class="inputWrap" style="text-overflow:ellipsis; white-space: nowrap;"> 
		   <span class="fieldspan">顾客生日：</span>
            <input type="text" name="BIRTHDAY" id="BIRTHDAY" json="BIRTHDAY" placeholder="请输入顾客生日" required> 
          </div>
		  <div class="inputWrap" style="text-overflow:ellipsis; white-space: nowrap;"> 
		   <span class="fieldspan">顾客爱好：</span>
            <input type="text" name="HOBBY" id="HOBBY" json="HOBBY" placeholder="请输入顾客爱好" required> 
          </div>
		   
		  --%>
		  <div class="inputWrap_1">
		    <span style="margin-left:14px;margin-top:5px;margin-bottom:5px;" >顾客住址：</span>
		    <p>
		    <textarea rows="4" cols="100%" style="margin-left:5px; margin-right:5px;margin-top:5px; border:1px solid #CCC; font-size: 30px;width: 500px;"
		              name="ADDRESS" id="ADDRESS" json="ADDRESS" >${rst.ADDRESS}
			</textarea>
			</p>
			<input type="hidden" id="CARD_SALE_ORDER_DTL_ID" name="CARD_SALE_ORDER_DTL_ID" json="CARD_SALE_ORDER_DTL_ID"/>
          </div>
          <div class="inputWrap_1">
		    <span style="margin-left:14px;margin-top:5px;margin-bottom:5px;" >购买意向：</span>
		    <p>
		    <textarea rows="4" cols="100%" style="margin-left:5px; margin-right:5px;margin-top:5px; border:1px solid #CCC; font-size: 30px;width: 500px;"
		              name="REMARK" id="REMARK" json="REMARK" >${rst.REMARK}
			</textarea>
			</p>
          </div>
		</fieldset> 
		<!--
       <fieldset> 
		 <input type="url" list="url_list" name="link" /> 
			<datalist id="url_list">  
			<option label="W3School" value="http://www.W3School.com.cn" /> 
			<option label="Google" value="http://www.google.com" /> 
			<option label="Microsoft" value="http://www.microsoft.com" />
			</datalist> 
			</fieldset> 
        <fieldset> 
          <input type="checkbox" checked="checked"> 
          <span>下次自动登录</span> 
        </fieldset> -->
        <fieldset style="text-align:center;" > 
          <input type="button" value=" 保 存 " onclick="cardEdit();" style="width: 500px;font-size: 30px;height: 60px;">
        </fieldset> 
      </div> 
    </section> 
  </form> 
</div> 

 <form action="#" method="post" id="listForm"> </form>
</body> 
</html> 
 

 


