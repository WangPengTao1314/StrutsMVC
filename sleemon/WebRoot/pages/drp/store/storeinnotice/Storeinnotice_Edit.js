/**
 * @prjName:喜临门营销平台
 * @fileName:Storeinnotice_Edit
 * @author glw
 * @time   2013-08-17 17:07:03 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);
	$("#mainForm input[name='FROM_TYPE']").click(function(){
		var value = $(this).val();
		if("总部订货" == value){
			showform_good_span();
		}else{
			hidform_good_span();
		}
		parent.bottomcontent.multiRecDeleteChilds();
		$("#FROM_TYPE_VAL").val(value);
	});
	
	var FROM_TYPE = $("#FROM_TYPE_VAL").val();
	if("总部订货" == FROM_TYPE){
		  showform_good_span();
		  $("#FROM_TYPE_O").prop("checked",true);
	}else{
		  hidform_good_span();
		  $("#checkFromType").val("初始化");
		  $("#FROM_TYPE_").prop("checked",true);
	}
//  if("初始化" == FROM_TYPE){
//	  $("#FROM_TYPE_").prop("checked",true);
//  }
});

function formCheckedEx(){
	var FROM_TYPE_O = $("#FROM_TYPE_O").attr("checked");
	if("checked" == FROM_TYPE_O){
		var FROM_BILL_NO = $("#FROM_BILL_NO").val();
		if(null == FROM_BILL_NO || "" == FROM_BILL_NO){
			parent.showErrorMsg("请选择'来源单据编号'");
			return false;
		}
	}
	
	return true;
}
function getBIll_TYPE(){
	return $("#billtype").val();
}

function getFROM_BILL_ID(){
	return $("#FROM_BILL_ID").val();
}
function getFROM_BILL_NO(){
	return $("#FROM_BILL_NO").val();
}
function getFROM_TYPE(){
	return $("#FROM_TYPE_VAL").val();
}

//默认库房ID
function getDEF_STORE_ID(){
	return $("#DEF_STORE_ID").val();
}
//默认库房编号
function getDEF_STORE_NO(){
	return $("#DEF_STORE_NO").val();
}
//默认库房名称
function getDEF_STORE_NAME(){
	return $("#DEF_STORE_NAME").val();
}

function hidform_good_span(){
	$("#mainForm span[name='form_good_span']").hide();
}
function showform_good_span(){
	$("#mainForm span[name='form_good_span']").show();
}
function getRECEIVE_CHANN_ID(){
	return $("#RECV_CHANN_ID").val();
}

function changeChildStore(){
	var childTrs = parent.bottomcontent.$("#jsontb tr:gt(0)");
	childTrs.each(function(){
		$(this).parent().parent().find("input[json='STOREIN_STORE_ID']").val(getDEF_STORE_ID());
		$(this).parent().parent().find("input[json='STOREIN_STORE_NO']").val(getDEF_STORE_NO());
		$(this).parent().parent().find("input[json='STOREIN_STORE_NAME']").val(getDEF_STORE_NAME());
	});
}