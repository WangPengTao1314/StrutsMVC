
/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_List
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
var flag=1;
$(function(){
	var SCHEFLAG=$("#SCHEFLAG").val();
	if(""!==SCHEFLAG&&null!==SCHEFLAG){
		$("#myShare").prop("checked",true);
	}
	$("#eid1").attr("checked",true);
	$("#query").click(function(){
		if(flag%2==0){
			$("#querydiv").hide();
		}else{
			$("#querydiv").show();
		}
		flag++;
		$("#STATE option[text='制定']").remove();
	})
	$("#q_close").click(function(){
		$("#querydiv").hide();
		flag++;
	})
	$("#q_search").click(function(){
		$("#queryForm").submit();
	})
	$("#delete").click(function(){
		var RPT_SCHE_SHAR_ID=$('input:radio:checked').val();
		if(""==RPT_SCHE_SHAR_ID||null==RPT_SCHE_SHAR_ID||"undefined"==RPT_SCHE_SHAR_ID){
			showErrorMsg("请选择一条记录");
		    return;
		}
		showConfirm("将删除该条数据，是否继续?","todelete();");
		
	})
	$("#start").click(function(){
		var RPT_SCHE_SHAR_ID=$('input:radio:checked').val();
		if(""==RPT_SCHE_SHAR_ID||null==RPT_SCHE_SHAR_ID||"undefined"==RPT_SCHE_SHAR_ID){
			showErrorMsg("请选择一条记录");
		    return;
		}
		showConfirm("将启用该条数据，是否继续?","upState('启用');");
	})
	$("#stop").click(function(){
		var RPT_SCHE_SHAR_ID=$('input:radio:checked').val();
		if(""==RPT_SCHE_SHAR_ID||null==RPT_SCHE_SHAR_ID||"undefined"==RPT_SCHE_SHAR_ID){
			showErrorMsg("请选择一条记录");
		    return;
		}
		showConfirm("将停用该条数据，是否继续?","upState('停用');");
	})
	setSelOperateEx();
})
function checkRadio(id,rownum){
	$("#"+id).attr("checked",true);
	setSelOperateEx(rownum);
}
function selRPT_SCHE_SHAR_NO(){
	var myShare=$("#myShare").prop("checked");
	var psonId=$("#psonId").val();
	if(myShare){
		$("#selectShare").val(" DEL_FLAG=0 and SHAR_PSON_ID='"+psonId+"'");
	}else{
		$("#selectShare").val(" DEL_FLAG=0 and SHARED_PSON_ID='"+psonId+"'");
	}
	selCommon('BS_103', false, 'RPT_SCHE_SHAR_NO', 'RPT_SCHE_SHAR_NO', 'forms[1]','RPT_SCHE_SHAR_NO', 'RPT_SCHE_SHAR_NO', 'selectShare');
}
function setSelOperateEx(rownum){
	if(""==rownum||null==rownum){
		rownum=1;
	}
	var state=$("#state"+rownum).val();
	btnReset();
    if(state == "启用"){
		btnDisable(["start","delete"]);
	}else if(state == "停用"){
		btnDisable(["stop"]);
	}
 }
function upState(type){
	closeWindow();
	var RPT_SCHE_SHAR_ID=$('input:radio:checked').val();
	 $.ajax({
		url: "reptshareview.shtml?action=upState&RPT_SCHE_SHAR_ID="+utf8(RPT_SCHE_SHAR_ID)+"&STATE="+utf8(type),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert("状态修改成功");
				$("#pageForm").submit();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}
function todelete(){
	closeWindow();
	var RPT_SCHE_SHAR_ID=$('input:radio:checked').val();
	 $.ajax({
		url: "reptshareview.shtml?action=todelete&RPT_SCHE_SHAR_ID="+utf8(RPT_SCHE_SHAR_ID),
		type:"POST",
		dataType:"text",
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert("删除成功");
				$("#pageForm").submit();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
 	});
}
function openUrl(id,RPT_NAME){
	var SLEEMON_REPORT_URL=$("#SLEEMON_REPORT_URL").val();
	var RUNQIAN_REPORT_URL=$("#RUNQIAN_REPORT_URL").val();
	window.open(RUNQIAN_REPORT_URL+"/report/savereptshareview.shtml?action=toRept&RPT_SCHE_SHAR_ID="+id,RPT_NAME,' height=500,width=700,  top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=yes, status=yes');
}