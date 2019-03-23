
/**
 *@module 分销业务
 *@func人员信息
 *@version 1.1
 *@author lyg
 */
$(function(){
    if($("#RYBH").val()!=null && $("#RYBH").val() != ""){
	    $("#RYBH").attr("readonly","readonly");
	}
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("employee.shtml?action=edit","RYXXID");
	
});
function selBMXX(){
	var RYLB=$("#RYLB").val();
	if(""==RYLB||null==RYLB){
		alert("请选择人员类别");
		return;
	}else if("本部"==RYLB){
		selCommon('System_9', false, 'BMXXID', 'BMXXID', 'forms[0]','BMXXID,BMBH,BMMC,JGXXID,JGBH,JGMC', 'BMXXID,BMBH,BMMC,JGXXID,JGBH,JGMC', 'selectParams');
	}else if("门店"==RYLB){
		selCommon('BS_27', false, 'BMXXID', 'TERM_ID', 'forms[0]','BMXXID,BMBH,BMMC,JGXXID,JGBH,JGMC', 'TERM_ID,TERM_NO,TERM_NAME,CHANN_ID_P,CHANN_NO_P,CHANN_NAME_P', 'selectTerminal')
	}
}
function upType(RYLB_TEMP){
	if(""!=RYLB_TEMP&&null!=RYLB_TEMP){
		var RYLB=RYLB_TEMP;
	}else{
		var RYLB=$("#RYLB").val();
	}
	if("门店"!=RYLB){
		$("#RYJB").val("");
		$("#tdValueAddValue").hide();
		$("#tdNameAddValue").hide();
		$("#tdName").show();
		$("#tdValue").show();
		$("#TERM_DECT_FROM").prop("readonly",true);
		$("#TERM_DECT_FROM").val("0");
		$("#TERM_DECT_FROM").css("background","#EEEEFF");
	}else{
		$("#tdValueAddValue").show();
		$("#tdNameAddValue").show();
		$("#tdName").hide();
		$("#tdValue").hide();
		$("#TERM_DECT_FROM").prop("readonly",false);
		$("#TERM_DECT_FROM").css("background","white");
	}
	$("#BMXXID").val("");
	$("#BMBH").val("");
	$("#BMMC").val("");
	$("#JGXXID").val("");
	$("#JGBH").val("");
	$("#JGMC").val("");
	
}
//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formChecked(){
// var RYBH=$("#RYBH").val();
// var topTab=$("#topTab").val();
// //判断人员编号的长度 不等于渠道编号长度+3或者人员编号从0到渠道编号长度截取字符串不等于渠道编号
// if((RYBH.length!=(topTab.length+3)||RYBH.substring(0,topTab.length)!=topTab)&&RYBH!=topTab){
//	  parent.showErrorMsg(utf8Decode("人员编号输入格式不正确，请以"+topTab+"开头，后加3位数字"));
//	  	  return false;
// }
// var num=RYBH.substring(topTab.length,RYBH.length);//把人员编号后3位截取出来
//	 //如果后3位不是数字的话
// if(isNaN(num)){
//	  parent.showErrorMsg(utf8Decode("人员编号输入格式不正确，请以"+topTab+"开头，后加3位数字"));
//  	  return false;
// }
 if($("#SJ").val()!=null && $("#SJ").val() != ""){
        var re1 = new RegExp(/^1\d{10}$/);//手机匹配
        var SJ = re1.test($("#SJ").val());
        if(!SJ ){
      	   parent.showErrorMsg(utf8Decode("手机格式输入不正确！"));
           return false;
	   }
 }
 if(""==$("#RYLB").val()||null==$("#RYLB").val()){
	 parent.showErrorMsg(utf8Decode("请选择人员类别！"));
	 return false;
 }else{
	 if(""==$("#RYJB").val()||null==$("#RYJB").val()){
		 if("门店"==$("#RYLB").val()){
			 parent.showErrorMsg(utf8Decode("请选择人员岗位！"));
	 		return false;
		 }
	 }
 }
// if($("#GSDH").val() != null && $("#GSDH").val() != ""){
//        var re2 = new RegExp(/^((0\d{2,3})-)|(\d{7,8})(-(\d{3,}))?$/);//国内电话号码
//        var phone = re2.test($("#GSDH").val());
//        if(!phone){
//           parent.showErrorMsg(utf8Decode("公司电话号码格式输入不正确！"));
//           return false;
//        }
//    }
//   if($("#DZYJ").val()!=null && $("#DZYJ").val() != ""){
//      var re1 = new RegExp(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/);//email匹配
//      var email = re1.test($("#DZYJ").val());
//      if(!email ){
//         parent.showErrorMsg(utf8Decode("电子邮件格式输入不正确！"));
//         return false;
//      }
//   }
    if(!newFormCheck('mainForm'))
    {
     return false;
    }
    return true;
}