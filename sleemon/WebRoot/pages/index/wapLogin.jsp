<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<c:set var="ctx"  value="${pageContext.request.contextPath}"/>
<c:set var="theme"  value="${sessionScope.UserCSS }"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0, width=device-width,target-densitydpi=device-dpi"/>
	<script type="text/javascript" src="${ctx}/scripts/core/jquery-1.6.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/core/Jscore.js"></script>
	<title>mobile login</title> 
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
    height:380px; 
    width:90%; /** **/
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
    font:normal 26px/1 Microsoft YaHei, sans-serif; 
} 
fieldset { 
    border:none; 
    padding:10px 10px 0; 
} 
fieldset input[type=text] { 
    background:url(style/default/images/user.png) 4px 5px no-repeat; 
} 
fieldset input[type=password] { 
    background:url(style/default/images/password.png) 4px 5px no-repeat; 
} 
fieldset input[type=text], fieldset input[type=password] { 
    width:100%; 
    line-height:2em; 
    font-size:16px; 
    height:30px; 
    border:none; 
    padding:2px 4px 3px 2.2em; 
    width:54%; 
} 
fieldset input[type=submit] { 
    font-size:20px;
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
    height:40px; 
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
    margin:10px 10px 0; 
    height:40px;
    font-size:20px;
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
    font-size:18px; 
} 

.fieldspan { 
    margin-left:14px;
} 

.top { 
    text-align:center; 
    margin:10px auto; 
    width:90%; 
    overflow:hidden; 
}

</style>
<script type="text/javascript">
        var phoneWidth = parseInt(window.screen.width);
        var phoneScale = phoneWidth / 640;
        
		if(/Android (\d+\.\d+)/.test(navigator.userAgent)){
			var version = parseFloat(RegExp.$1);
			 
			if(version>2.3){
				//var phoneScale = parseInt(window.screen.width)/640;
				document.write('<meta name="viewport" content="width=320, minimum-scale = '+ phoneScale +', maximum-scale = '+ 2 +', target-densitydpi=device-dpi">');
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
  <form action="${ctx}/wap.shtml?action=authLogin" method="post" > 
  <input type="hidden" id="card_no" name="card_no" value="${card_no}"/>
    <section class="loginForm">
      <header> 
        <h1>喜临门</h1> 
      </header> 
      <div class="loginForm_content"> 
        <fieldset> 
          <div class="inputWrap" style="text-overflow:ellipsis; white-space: nowrap;"> 
            <span style="" class="fieldspan">帐号：</span>
            <input type="text" id="S_NAME" name="S_NAME" placeholder="请输入帐号" autofocus required> 
          </div> 
          <div class="inputWrap" style="text-overflow:ellipsis; white-space: nowrap;"> 
             <span style="" class="fieldspan">密码：</span>
            <input type="password" name="S_PWD" id="S_PWD" placeholder="请输入密码" required> 
            <input type="hidden" name="S_ZTID" id="S_ZTID" value="" />
            <input type="hidden" name="ZTMC" id="ZTMC" value="" />
          </div><%-- 
		  <div class="inputWrap" style="text-overflow:ellipsis; white-space: nowrap;"> 
		    <span class="fieldspan">帐套：</span>
            <select     style="width:286px; "> 
			  <option value="volvo">Volvo</option>
			  <option value="saab">Saab</option>
			  <option value="opel">Opel</option>
			  <option value="audi">Audi</option>
			</select>
          </div> 
        --%>
        </fieldset> 
		<%--<fieldset> 
		 <div class="inputWrap"> 
		   <input type="text" list="S_ZTID" name="link" /> 
			<datalist id="S_ZTID">  
			 <option label="W3School" value="http://www.W3School.com.cn" /> 
			 <option label="Google" value="http://www.google.com" /> 
			 <option label="Microsoft" value="http://www.microsoft.com" />
			</datalist> 
         </div>
	   </fieldset> 
      

        --%><%--<fieldset> 
          <input type="checkbox" checked="checked"> 
          <span>下次自动登录</span> 
        </fieldset>  --%>
        <fieldset style="text-align: center;"> 
          <input type="submit" value="登录" style="width: 155px;">
           <%-- <a href="#">忘记密码？</a> <a href="#">注册</a> --%>
        </fieldset> 
      </div> 
    </section> 
  </form> 
</div> 
</body> 
</html> 

<script type="text/javascript">
 $(function(){
		$("#S_NAME").focus();
		$("#S_NAME").blur(function(){
			var userId = $(this).val();
			var select = document.getElementById("S_ZTID");
			$("#S_ZTID").empty();
			if(null != userId && "" != userId){
				$.ajax({
					url: "wap.shtml?action=getZtxx",
					type:"POST",
					dataType:"text",
					data:{"userId":userId},
					complete: function(xhr){
						eval("jsonResult = "+xhr.responseText);
						if(jsonResult.success===true){
							$("#S_ZTID").val(utf8Decode(jsonResult.messages));
						}else{
							parent.showErrorMsg(utf8Decode(jsonResult.messages));
						}
					}
				});
			}
		});
 });
 
</script>

 


