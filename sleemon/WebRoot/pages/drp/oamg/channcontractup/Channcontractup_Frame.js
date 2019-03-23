
/**
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author 刘曰刚
 */
$(function () {
	//框架页面初始化
	var module = document.getElementById("module").value;
	var paramUrl=document.getElementById("paramUrl");
	 if(paramUrl!=null&&paramUrl.value!="")
	    framePageInit("channcontractup.shtml?action=toList" +utf8((paramUrl.value.replaceAll("andflag","&")).replaceAll("equalsflag","=")));
	 else{
	    framePageInit("channcontractup.shtml?action=toList&module=" + module);
	 }
});

//bottomcontent页面跳转。
function gotoBottomPage(action) {
	//获取当前选中的记录
	var selRowId = $("#selRowId").val();
	//初始化时下帧页面的action
	if (null == action || "label_1" == action) {//"label_1"的判断是为了与主从界面通用
		action = "toDetail";
	}
	var url = "channcontractup.shtml?action=" + action + "&CHANN_CONTRACT_ID=" + selRowId;
	//下帧页面跳转
	$("#bottomcontent").attr("src", url);
}

