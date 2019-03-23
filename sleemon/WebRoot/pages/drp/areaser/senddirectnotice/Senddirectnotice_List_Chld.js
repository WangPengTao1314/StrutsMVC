/**
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_List_Chld
 * @author wzg
 * @time   2013-08-15 10:17:13 
 * @version 1.1
 */
$(function(){
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#ordertb :checkbox").attr("checked","true");
		}else{
			$("#ordertb :checkbox").removeAttr("checked");
		}
	});
	
	//防止 下帧间隙过大
	var module = parent.$("#module").val();
	if("sh" == module){
		$("#firstTr").hide();
	}
});

//显示特殊工艺单
function url(SPCL_TECH_ID,PRICE){
	var data = window.showModalDialog('techorderManager.shtml?action=viewTechWithPrice&SPCL_TECH_ID='+SPCL_TECH_ID+'&PRICE='+PRICE,window,"dialogwidth=800px; dialogheight=600px; status=no");
}


