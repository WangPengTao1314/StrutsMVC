/**
 * @prjName:喜临门营销平台
 * @fileName:Goodsorderhd_Frame
 * @author zzb
 * @time   2013-09-15 10:35:10 
 * @version 1.1
 */
$(function(){
	 //框架页面初始化
	 var module = document.getElementById("module").value;
	 var flag = $("#flag").val();
	 var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!=""){
	    framePageInit("goodsorderhd.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }else{	 
	    framePageInit("goodsorderhd.shtml?action=toList&module=" + module+"&flag="+flag);
	 }	
	 
	 
	 $("#label_3").click(function(){
		  var selRowId = $("#selRowId").val();
		 //下帧页面跳转
	     $("#bottomcontent").attr("src","goodsorderhd.shtml?action=traceList&GOODS_ORDER_ID="+selRowId);
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
	if("label_1" == showLabelId){
		url = "goodsorderhd.shtml?action=childList"
	}else if("label_3" == showLabelId){
		url = "goodsorderhd.shtml?action=traceList";
	}else{
		url = "goodsorderhd.shtml?action=toDetail"
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&GOODS_ORDER_ID="+selRowId);
}

 function closePage(){
	 $("#midden").html("");
 }


 