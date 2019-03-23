
/**
 * @module 系统管理
 * @func 渠道分管
 * @version 1.1
 * @author 张忠斌
 */
$(function () {
     $("#querydiv").css("display","block");
     //初始化校验
	InitFormValidator("mainForm");
	
	
	$("#save").click(function(){
		var dimension = $("input[name='dimension']:checked").val();
		if(1 == dimension){
			saveXtyhxx();
		}else{
			saveChann();
		}
	});
	
	$("#query").click(function(){
		var dimension = $("input[name='dimension']:checked").val();
		if(1 == dimension){
			queryXtyhxx();
		}else{
			queryChann();
		}
	});
	
	$("input[name='dimension']").click(function(){
		dimensionSelect(null,this);
		parent.bottomcontent.$("#ordertb tr:gt(0)").remove();
	});

	//初始化查询页面，默认是按照人员维度分管渠道
	dimensionSelect(0,null);
});
 
  
// 以用户维度下帧调用查询
function queryChann(){
	var YHBH = $("#YHBH").val();
	if(null == YHBH || "" == $.trim(YHBH)){
		parent.showErrorMsg("请选择'"+$("#YHBH").attr("label")+"'");
		return;
	}
    
	parent.bottomcontent.$("#XTYHID").val($("#XTYHID").val());
	parent.bottomcontent.$("#YHBH").val($("#YHBH").val());
	parent.bottomcontent.$("#YHM").val($("#YHM").val());
	parent.bottomcontent.$("#AREA_ID").val($("#AREA_ID").val());
	parent.bottomcontent.$("#AREA_NO").val($("#AREA_NO").val());
	parent.bottomcontent.$("#AREA_NAME").val($("#AREA_NAME").val());
	parent.bottomcontent.$("#CHANN_TYPE").val($("#CHANN_TYPE").val());
	parent.bottomcontent.$("#CHAA_LVL").val($("#CHAA_LVL").val());
	parent.bottomcontent.$("#CHANN_ID").val($("#CHANN_ID").val());
	parent.bottomcontent.$("#CHANN_NO").val($("#CHANN_NO").val());
	parent.bottomcontent.$("#CHANN_NAME").val($("#CHANN_NAME").val());
	parent.bottomcontent.$("#PROV").val($("#PROV").val());
	parent.bottomcontent.$("#CITY").val($("#CITY").val());
	
	var notcharg = "0";
	var falg1 = $("#notcharg_1").attr("checked");//显示未分管
	var falg2 = $("#notcharg_2").attr("checked");//显示已分管
	if("checked" == falg1){
		notcharg = "1";
	}
	if("checked" == falg2){
		notcharg = "2";
	}
	parent.bottomcontent.$("#notcharg").val(notcharg);
	parent.bottomcontent.$("#queryForm").attr("action","channchrg.shtml?action=childList");
	parent.bottomcontent.$("#queryForm").submit();
 
}



function saveChann(){
	var YHBH = $("#YHBH").val();
	if(null == YHBH || "" == $.trim(YHBH)){
		parent.showErrorMsg("请选择'"+$("#YHBH").attr("label")+"'");
		return;
	}
	var XTYHID = $("#XTYHID").val();
	var CHANN_IDS =  parent.document.getElementById("selRowId").value;
//	if(null == CHANN_IDS || "" == CHANN_IDS){
//		parent.showErrorMsg("请至少选择一条渠道");
//		return;
//	}
	
	var deleteIds = parent.document.getElementById("deleteIds").value;
    $.ajax({
	 url: "channchrg.shtml?action=edit",
	 type:"POST",
 	 dataType:"text",
 	 data:{"XTYHID":XTYHID,"CHANN_IDS":CHANN_IDS,"deleteIds":deleteIds},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel(utf8Decode(jsonResult.messages));
			queryChann();
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}

//维度选择
function dimensionSelect(def,obj){
	var dimension = def;
	if(null != obj){
	    dimension = $(obj).val();
	}
	if(1 == dimension){
		$("#querytb tr[name='tr_ry']").hide();
		$("#querytb tr[name='tr_chann']").show();
	}else{
		$("#querytb tr[name='tr_ry']").show();
		$("#querytb tr[name='tr_chann']").hide();
	}
}


//以渠道维度
function queryXtyhxx(){
	var CHANN_ID_1 = $("#CHANN_ID_1").val();
	if(null == CHANN_ID_1 || "" == CHANN_ID_1){
		parent.showErrorMsg("请选择'渠道信息'");
		return;
	}
    
	parent.bottomcontent.$("#XTYHID").val($("#XTYHID_1").val());
	parent.bottomcontent.$("#YHBH").val($("#YHBH_1").val());
	parent.bottomcontent.$("#YHM").val($("#YHM_1").val());
 
	parent.bottomcontent.$("#CHANN_ID").val($("#CHANN_ID_1").val());
	parent.bottomcontent.$("#CHANN_NO").val($("#CHANN_NO_1").val());
	parent.bottomcontent.$("#CHANN_NAME").val($("#CHANN_NAME_1").val());
	
	parent.bottomcontent.$("#BMXXID").val($("#BMXXID").val());
	parent.bottomcontent.$("#BMMC").val($("#BMMC").val());
//	parent.bottomcontent.$("#JGMC").val($("#JGMC").val());
//	parent.bottomcontent.$("#CITY").val($("#CITY").val());
	
	var notcharg = "0";
	var falg1 = $("#notcharg_1").attr("checked");//显示未分管
	var falg2 = $("#notcharg_2").attr("checked");//显示已分管
	if("checked" == falg1){
		notcharg = "1";
	}
	if("checked" == falg2){
		notcharg = "2";
	}
	parent.bottomcontent.$("#notcharg").val(notcharg);
	parent.bottomcontent.$("#queryForm").attr("action","channchrg.shtml?action=childYhxxList");
	parent.bottomcontent.$("#queryForm").submit();
}

 


function saveXtyhxx(){
	var CHANN_ID = $("#CHANN_ID_1").val();
	if(null == CHANN_ID || "" == CHANN_ID){
		parent.showErrorMsg("请选择'渠道信息'");
		return;
	}
	var XTYHIDS =  parent.document.getElementById("selRowId").value;
	var deleteIds = parent.document.getElementById("deleteIds").value;
    $.ajax({
	 url: "channchrg.shtml?action=editRyxx",
	 type:"POST",
 	 dataType:"text",
 	 data:{"CHANN_ID":CHANN_ID,"XTYHIDS":XTYHIDS,"deleteIds":deleteIds},
	 complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			parent.showMsgPanel(utf8Decode(jsonResult.messages));
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	  }
    });
}
