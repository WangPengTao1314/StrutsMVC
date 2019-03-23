
/**
 *@module 基础数据
 *@func人员信息
 *@version 1.1
 *@author 吴亚林
 */
$(function(){
    
    if($("#RYBH").val()!=null && $("#RYBH").val() != ""){
	    $("#RYBH").attr("readonly","readonly");
	    
	}
	
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧，
	mtbSaveListener("ryxx.shtml?action=edit","RYXXID","ryxx.shtml?action=toList","mainForm");
	
	var selId = parent.document.getElementById("selRowId").value;
	if(null == selId || "" == selId){
		var treeNodes = parent.leftcontent.getSelNodes();
		if("" != treeNodes){
		    var flag = treeNodes.def1;//判断是机构还是部门
			if(1==flag){//部门
			   $("#BMXXID").val(treeNodes.id);
			   var jgbhname = treeNodes.name;
			   var index = jgbhname.indexOf("(");
			   $("#BMMC").val(jgbhname.substring(index+1,jgbhname.length-1));
			   $.ajax({
		             url: "bmxx.shtml?action=selectZTbyId&BMXXID="+treeNodes.id,
		             type:"POST",
		             dataType:"text",
		             complete: function(xhr){
		                eval("jsonResult = "+xhr.responseText);
		                   if(jsonResult.success===true){
		                       var arr = jsonResult.messages.split(',');
		                       $('#JGMC').val(arr[0]);
		                       $('#JGXXID').val(arr[1]);
		                   }
		             }
	            });
			}
		}
	}
	
	
});

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formChecked(){

   if($("#DZYJ").val()!=null && $("#DZYJ").val() != ""){
      var re1 = new RegExp(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/);//email匹配
      var email = re1.test($("#DZYJ").val());
      if(!email ){
         parent.showErrorMsg(utf8Decode("电子邮件格式输入不正确！"));
         return false;
      }
   }
    if($("#SJ").val()!=null && $("#SJ").val() != ""){
	        var re1 = new RegExp(/^1\d{10}$/);//手机匹配
	        var SJ = re1.test($("#SJ").val());
	        if(!SJ ){
	      	   parent.showErrorMsg(utf8Decode("手机格式输入不正确！"));
	           return false;
	   }
	        }
    if($("#GSDH").val() != null && $("#GSDH").val() != ""){
        var re2 = new RegExp(/^((0\d{2,3})-)|(\d{7,8})(-(\d{3,}))?$/);//国内电话号码
        var phone = re2.test($("#GSDH").val());
        if(!phone){
           parent.showErrorMsg(utf8Decode("电话号码格式输入不正确！"));
           return false;
        }
    }
    
    if($("#SFZH").val() != null && $("#SFZH").val() != ""){
        var re3 = new RegExp(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/);//身份证号
        var post = re3.test($("#SFZH").val());
        if(!post){
           parent.showErrorMsg(utf8Decode("身份证号格式输入不正确！"));
           return false;
        }
    }
    
    if(!newFormCheck('mainForm'))
    {
     return false;
    }
    return true;
}