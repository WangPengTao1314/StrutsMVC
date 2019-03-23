/**

  *@module 系统管理

  *@fuc 系统用户编缉页面

  *@version 1.1

  *@author 唐赟
*/
var status;
$(document).ready(function(){
   
    status = $("#ctrType").val();   
    if('modify' == status){    
       $("#YHBH").attr("readonly","true");
       $("#RYBH").attr("readonly","true");
       $("#RYMC").attr("readonly","true");
       $("#BMMC").attr("readonly","true");
       $("#JGMC").attr("readonly","true");
       
       $("#YHKL").removeAttr("mustinput");
       $("#YHKL2").removeAttr("mustinput");
       
       $("#detail tr:eq(1)").hide();
    }
    
    $("#save").click(function(){
		if(!formChecked('mainForm')){
			return;
		}
		var selId = parent.document.getElementById("selRowId").value;
		var pwd = $("#YHKL").val();
		$("#YHKL").val(hex_md5(pwd));
		$("#YHKL2").val(hex_md5(pwd))
		var jsonData = siglePackJsonData();
		$.ajax({
			url: "xtyh.shtml?action=toSave",
			type:"POST",
			dataType:"text",
			data:{"jsonData":jsonData,"XTYHID":selId},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					parent.showMsgPanel("保存成功");
					window.parent.topcontent.queryForm.submit(); 
				}else{
					$("#YHKL").val(pwd);
					$("#YHKL2").val(pwd)
					parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		});
	});
  
    //mtbSaveListener("xtyh.shtml?action=toSave","XTYHID","xtyh.shtml?action=toList","mainForm");
   
});

function formCheckedEx(){
//   if($("#YHBH").val().length < 4){
//		      parent.showErrorMsg(utf8Decode("用户编号请输入四位！"));
//		      //flag = false;
//	           return false;
//		   } 
		   
		   status = $("#ctrType").val();   
    if('add' == status){   
        var re1 = new RegExp(/[0-9]/g);//只能是数字
	        var re2 = new RegExp(/[A-Za-z]/g);//只能是字母
	        var re3 = new RegExp(/[!@#$%^&*]/g);//只能是字符：[^%&',;=?$x22]+
	       
	        var s1= re1.test($("#YHKL").val());
			var s2 = re2.test($("#YHKL").val());
			var s3 = re3.test($("#YHKL").val());
			//alert(s1+","+s2+","+s3);
	        if($("#YHKL").val().length < 6){
	            parent.showErrorMsg(utf8Decode("密码至少输入六位！"));
	            //flag = false;
	           return false;
	        }else{
	            if(!((s1 && s2) || (s1 && s3)|| (s2 && s3)) ){
	                 parent.showErrorMsg(utf8Decode("请输入数字，字母，特殊字符中的至少两类,并输入六位！"));
	                 return false;
	            }
		    
		  }  

	       if($("#YHKL").val() != $("#YHKL2").val()){
	          parent.showErrorMsg(utf8Decode("两次输入密码不一样，请重新输入！"));
	           return false;
	       }  
    }
	var PORTAL_URL=$("#PORTAL_URL").val();
	if(""==PORTAL_URL||null==PORTAL_URL){
		parent.showErrorMsg(utf8Decode("请选择首页URL！"));
	           return false;
	}
	         
	       return true;
}




