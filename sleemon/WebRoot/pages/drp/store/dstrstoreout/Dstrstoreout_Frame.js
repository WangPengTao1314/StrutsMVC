/**
 * @prjName:喜临门营销平台
 * @fileName:Storeout_Frame
 * @author chenj
 * @time   2013-09-15 14:59:50 
 * @version 1.1
 */
$(function(){
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	 {
	    framePageInit("dstrstoreout.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 }	
	 else
     {	
		 framePageInit("dstrstoreout.shtml?action=toList&module=" + module);
	 }	
});

//bottomcontent页面跳转
function gotoBottomPage(showLabelId){
	var billType=document.getElementById("BILL_TYPE").value;
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
		url = "dstrstoreout.shtml?action=childList&BILL_TYPE="+billType
	}else if("label_2" == showLabelId){
		var ids = "";
		$("#bottomcontent").contents().find("#ordertb tr:gt(0) input:checked").each(function(){
				ids = ids+"'"+$(this).val()+"',";
		});

		var storeoutDtlIds = ids.substr(0,ids.length-1);
		url = "dstrstoreout.shtml?action=storgChildList&STOREOUT_DTL_IDS="+storeoutDtlIds+"&BILL_TYPE="+billType;

		
		
	}else{
		url = "dstrstoreout.shtml?action=toDetail&BILL_TYPE="+billType
	}
	//下帧页面跳转
	$("#bottomcontent").attr("src",url+"&STOREOUT_ID="+selRowId);
}