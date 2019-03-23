/**
 * @prjName:喜临门营销平台
 * @fileName:Store_Edit_Chld
 * @author wdb
 * @time   2013-08-13 14:01:22 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
	$("#check").click(function(){
		window.open("advise.shtml?action=toFrame&CMPL_ADVS_TYPE=2","反馈查询","height=600, width=900, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
	});
	$("#save").click(function(){
		allSave();
	});
	//form校验设置
	InitFormValidator(0);
});

//主子表保存
function allSave(){
	//主表form校验
	var parentCheckedRst =formChecked('mainform');
	if(!parentCheckedRst){
		return;
	}
	
	//获取json数据
	var parentData = parent.bottomcontent.siglePackJsonData('maintable');
	var CMPL_ADVS_CONTENT = $("#CMPL_ADVS_CONTENT").text();
	$.ajax({
		url: "adviseinit.shtml?action=saveSerCmpl",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"CMPL_ADVS_CONTENT":CMPL_ADVS_CONTENT},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","adviseinit.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//表单验证
function formCheckedEx() {
	if($("#TEL").val() != null && $("#TEL").val() != ""){
        var re1 = new RegExp(/^([\d]{3}-[\d]{8})|([\d]{4}-[\d]{7})$/);//国内电话号码匹配
        var tel1 = re1.test($("#TEL").val());
        var re2 = new RegExp(/^(13[\d]{9}|15[\d]{9}|18[\d]{9})$/);//手机匹配
        var tel2 = re2.test($("#TEL").val());
        if(!tel1&&!tel2){
           parent.showErrorMsg(utf8Decode("联系电话格式输入不正确！"));
           return false;
        }
    }
	if($("#CMPL_ADVS_CONTENT").val().trim() == "") {
		parent.showErrorMsg("请输入投诉内容！");
		return false;
	}
	return true;
}