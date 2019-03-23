
/**
 * @prjName:喜临门营销平台
 * @fileName:Advctoorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
$(function(){
	headColumnSort();
	
	$("#yearClosing").click(function(){
	var YEAR=$("#YEAR").val();
	if(""==YEAR||null==YEAR){
		showErrorMsg("请选择年份");
		return;
	}
	checkInfo(YEAR);
})
});
function checkInfo(YEAR){
	$.ajax({
	 url: "annualClosing.shtml?action=toCheckClosing&YEAR="+utf8(YEAR),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			var data = jsonResult.data;
			if(data.flag==true){
				showConfirm("当前年份已存在年结，是否覆盖？如覆盖不可找回原有数据！","toClosing("+YEAR+");");
			}else{
				showConfirm("是否确定年结？年结后数据不可还原！","toClosing("+YEAR+");");
			}
		}else{
			showErrorMsg(utf8Decode(jsonResult.messages));
			return;
		}
	  }
    });
}
function toClosing(YEAR){
	$.ajax({
	 url: "annualClosing.shtml?action=toClosing&YEAR="+utf8(YEAR),
	 type:"POST",
 	 dataType:"text",
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			showMsgPanel("年结成功");
            $("#queryForm").submit();
		}else{
			showErrorMsg(utf8Decode(jsonResult.messages));
			return;
		}
	  }
    });
}
function selChange(){
	showWaitPanel();
	$('#queryForm').submit();
}