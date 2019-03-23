
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
   dateDiffColor(); 
}); 




function dateDiffColor(){
	var inputs = $("#ordertb tr:gt(0)").find("input[name='SEND_DATE_DIFF']");
	inputs.each(function(){
		var date = $(this).val();
		date = parseInt(date);
	    //子表的交期有在三天之内的今天、明天、后天
		if(date<=2){
			$(this).parent().parent().find("td").css("background-color", "#B4EEB4");//花号还原
		}
		//子表的交期有在今天之前的
		if(date<0){
			$(this).parent().parent().find("td").css("background-color", "#E6B9B8");//花号还原
		}
		
	});
}


function url(SPCL_TECH_ID){
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}

function setSelOperateEx() {
	
}


