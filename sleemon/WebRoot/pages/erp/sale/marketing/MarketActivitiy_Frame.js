/**
 * @prjName:喜临门营销平台
 * @fileName:
 * @author zzb
 * @time  2014-12-03 10:35:10 
 * @version 1.1
 */
$(function(){
	 //框架页面初始化
	 var module = document.getElementById("module").value;
	 var flag = $("#flag").val();
	 var paramUrl = document.getElementById("paramUrl");
	 if(paramUrl != null && paramUrl.value != ""){
	    framePageInit("marketact.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }else{	 
	    framePageInit("marketact.shtml?action=toList&module=" + module+"&flag="+flag);
	 }	
	 
	 
	 $("#label_3").click(function(){
		  var selRowId = $("#selRowId").val();
		 //下帧页面跳转
	     $("#bottomcontent").attr("src","marketact.shtml?action=marketCardList&MARKETING_ACT_ID="+selRowId);
	 });
	 
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
    //获取当前选中的记录
	var selRowId = $("#selRowId").val();
	if(null == showLabelId){
		showLabelId = $("#showLabel").attr("value");
	}else{
		//设置当前显示的标签页
		$("#showLabel").attr("value",showLabelId);
	}
	var url;
	if("label_2" == showLabelId){
		url = "marketact.shtml?action=marketChannList"
	}else if("label_3" == showLabelId){
		url = "marketact.shtml?action=marketCardList";
	}else{
		url = "marketact.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&MARKETING_ACT_ID="+selRowId);
}

 function closePage(){
	 $("#midden").html("");
	 $("#midden").hide();
	 $("#zzb").remove();
	 
	 bottomcontent.$("#opendiv").hide();
	 closeWindow();
 }
function dosaveCard(){
    var CARD_TYPE = $("#CARD_TYPE").val();
    if(null == CARD_TYPE || "" == CARD_TYPE){
    	showErrorMsg("请选择'卡券类型'");
    	return;
    }
	if(!formChecked('selectForm')){
		return;
	}
	var selRowId = $("#selRowId").val();
	var jsonData = siglePackJsonData("selecttb");
	$.ajax({
		url: "marketact.shtml?action=marketCardEdit",
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData,"MARKETING_ACT_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				showMsgPanel("新增成功");
				closePage();
				gotoBottomPage("label_3");
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});

}

 