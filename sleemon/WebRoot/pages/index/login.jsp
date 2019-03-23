<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.hoperun.commons.model.Consts"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>喜临门一体化营销管理平台</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type=text/javascript src="${ctx}/scripts/core/md5.js"></script>
<script type=text/javascript src="${ctx}/scripts/core/jsLoader.js"></script>
<script type="text/javascript">

        $(document).ready(
          function () {
              $("#divBig").width($(window).width());
              $("#divBig").height($(window).height());
              var hg = $(window).height() / 4;
              $("#divInput").css("top", hg);
          }
       );

          function resize() {
              
              $("#divBig").width($(window).width());
              $("#divBig").height($(window).height());
              var hg = $(window).height() / 4;
              $("#divInput").css("top", hg);             
          }
    </script>
    <style type="text/css">
        a img{border:none;}	  
        a{text-decoration:none; font-family:Arial, "微软雅黑"; } /* 链接无下划线,有为underline */ 
        a:link {color:#595656; text-decoration:none; font-family:Arial, "微软雅黑"; }           /* 未访问的链接 */
        a:visited {color:#900; text-decoration: none;}
        a:hover{ text-decoration:none; font-family:Arial, "微软雅黑"; }                         /* 鼠标在链接上 */ 
        a:active {color: #fff; text-decoration: none; font-family:Arial, "微软雅黑"; }          /* 点击激活链接 */
        
	
	    .logBtn 
	    {
	        width:380px;
            height:50px;       
	    }

        .logButton
        {
            width:160px;
            height:50px;
	        text-align:left;
	        line-height:30px;
	        float:left;
	    }

        .logButton a
        {
            height:50px;
            width:160px;
            background-image:url(${ctx}/pages/index/image/button01.png);
		    margin:0;
		    padding:0;
		    color:#fff;
		    line-height:50px;
		    text-align:center;
		    display:block;
		    float:left;
        }		
        .logButton a:hover 
        {
	        background-image:url(${ctx}/pages/index/image/button02.png);
	        font-weight: bold;
	    }
	
        .expansion 
        {
             width:60px; 
             height:50px;
	         float:left;
        }
	 
    
    
    /***************/
     body{
     	margin:0px;
     	/**background-image:url(${ctx}/pages/index/./image/sleemon_bg111.gif);**/
     	/**background-repeat:repeat;*/
     	background-color:#ed5603;
     }
    .intd{
    	width:170px; 
    	height:50px; 
    	font-family:Arial, "微软雅黑"; 
    	COLOR: #fff; 
    	font-size:16px;
    }  
    .sp{
    	margin-right:10px;
    }
    .inpt{
    	height:25px; 
    	width:220px;
    	vertical-align:middle;
    	line-height:25px; 
    	background-color:#fff; 
    	border:0px; 
    }      
</style>
</HEAD>
<body  scroll="no" onResize="resize();">
<%
 String targetName=com.hoperun.commons.util.TimeComm.getPreTimeStamp("");
 String account = Consts.ACCOUNT_DISPLAY;
 
%>
	<div id="divBig"><img id="imgbig" src="${ctx}/pages/index/image/bg.jpg" style="height:100%" alt=""/></div>   
	<div id="divInput" style=" float:right; z-index:100; position: absolute; right:5%;">
	<table cellpadding="0" cellspacing="0" border="0" style="width:430px;">
		<FORM name=form onkeyup="keyListenerEvent();" id="loginform" action="${ctx}/login.shtml?action=authLogin"     method=post onsubmit="return loginSubmit();" >
		<input id="invalidateFlag" name="invalidateFlag" type="hidden" value="">
		<input  id="userCss" name="userCss" type="hidden" value="<%=Consts.DEFAULT_CSS%>"/>
           <tr>
                <td align="right" colspan="2" style=" height:117px;"><img src="${ctx}/pages/index/image/slogan1.png" alt=""/></td>
           </tr>
           <tr>
                <td colspan="2" style="height:25px;">&nbsp;</td>
           </tr>
           <tr>
                 <td align="right" valign="middle"  class="intd"><span class="sp">用户:</span></td>
                 <td align="left" valign="middle"><input id="userId" name='S_NAME' type="text" autocheck="true" mustinput="true" label="用户名" class="inpt" /></td>
           </tr>
           <tr>
                 <td align="right" valign="middle" class="intd"><span class="sp">密码:</span></td>
                 <td align="left" valign="middle"><input id="KL" name="KL" minlength="6" maxlength="15"   autocheck="true" mustinput="true" label="密码" size="15" type="password" class="inpt" /></td>
                 <input id="S_PWD" name="S_PWD" type="hidden" value="">
                 <%
					if(!com.hoperun.commons.util.StringUtil.isEmpty(Consts.ACCOUNT_DISPLAY)&& "false".equals(Consts.ACCOUNT_DISPLAY)){
				%>
           </tr>
           	<%}else{ %>
             <tr>
                 <td align="right" valign="middle" style="height:50px;" class="intd"><span class="sp">帐套:</span></td>
                 <td align="left" valign="middle">
                 	<select   name="S_ZTID" autocheck="true" mustinput="true" label="帐套" id="S_ZTID"  class="inpt" style="height:20px;">
                 	</select>
                 	<input id="S_ZTMC" name="S_ZTMC" type="hidden" value="">
                 </td>
           </tr>
           <%}%>
           <tr>
                <td align="center" valign="bottom" colspan="2" style="height:60px;"  >
                  <div class="logBtn">
                    <div class="logButton"><a href='javascript:void(0);' onclick='loginSubmit()' >登录</a></div>
                    <div class="expansion"></div>
                    <div class="logButton"><a href='javascript:void(0);' onclick='reset()'>重置</a></div>
                  </div>
                </td>
           </tr>
           </FORM>
       </table>
    </div>
    <div id="divFootBg" style=" position:absolute; z-index:101px;width:100%; height:48px;  bottom:0px; text-align:center; background-image:url(${ctx}/pages/index/./image/white_bg.png); ">&nbsp;</div>
    <div id="divFoot" style=" position:absolute; z-index:102px;width:100%; bottom:0px; text-align:center; "><img src="${ctx}/pages/index/image/footer.png"  alt=""/></div>
</body>
<script type="text/javascript">
//点击回车提交登录
function keyListenerEvent(){
	if(event.keyCode==13){
		var userId=$("#userId").val();
		var KL=$("#KL").val();
		if(userId==""||userId==null){
			$("#userId").focus();
		}else if(KL==null||KL==""){
			$("#KL").focus();
		}else{
			loginSubmit();
		}
	}
}
function reset(){
	$("#loginform")[0].reset();
}
$(function(){
        browserinfo();
		$("#userId").focus();
		<%
			if(!(!com.hoperun.commons.util.StringUtil.isEmpty(Consts.ACCOUNT_DISPLAY)&& "false".equals(Consts.ACCOUNT_DISPLAY))){
		%>
		$("#userId").blur(function(){
			var userId = $(this).val();
			var select = document.getElementById("S_ZTID");
			$("#S_ZTID").empty();
			if(null != userId && "" != userId){
				$.ajax({
					url: "${ctx}/login.shtml?action=getZtxx",
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
		<%
			}
		%>	
 //for set user css
   setTheCss();	
 });

 //edit by zhuxw 2012.11.1
 function loginSubmit(){
		if(FormValidate(0)){
			var pwd = $("#KL").val();
			$("#S_PWD").val(hex_md5(pwd));
			var ztmc = $("#S_ZTID option:selected").text();
			$("#S_ZTMC").val(ztmc);
			$("#KL").val("");
//			var userId = $("#userId").val();
//			$.ajax({
//				url: "login.shtml?action=checkUserOnline&YHM="+userId,
//				type:"POST",
//				dataType:"text", 
//				complete: function(xhr){
//					 eval("jsonResult = "+xhr.responseText);
					formSubmit();
                    $("#KL").val(pwd);
					
//				}
//			});
		}
		return false;
	}
  //add by zhuxw 2012-11-1
  //for init user css
  function setTheCss(){	
  var theSytle=$.cookie('usersetcss');
  if(theSytle==null||theSytle=='')
  {
   theSytle='<%=Consts.DEFAULT_CSS%>';
   theme=theSytle;
  }
  include({
      cssFiles:["${ctx}/styles/"+theSytle+"/css/login.css"],       
      scripts:[]   
   }); 
  

  }
  //commit the page
  function formSubmit(){
        $("#invalidateFlag").val("true");
		<%if("true".equals(Consts.MAX_WINDOWS)){%>
		 $("#loginform").attr("target","<%=targetName%>")
		 var h = screen.height-50;
         var w = screen.width-10;
         var mainwin = window.open("about:blank", '<%=targetName%>', 'width='+w+',height='+h+',left=0,top=0,scrollbars=yes,resizable=no');
         mainwin.focus();
         form.submit(); 
		 if(navigator.Actual_Name!='Chrome')
	     {
	      window.opener=null;
	      window.close();
         }
        if(navigator.Actual_Name=='Chrome')
	     {
          top.window.opener = null;
          top.window.open('','_self',''); 
          top.window.close();
         }
	    <%}else{%>
	       form.submit(); 
	    <%}%>
       
  }
  
    </script>
</HTML>
    
