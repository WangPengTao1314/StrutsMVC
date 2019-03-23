
/**
 *@module 分销业务
 *@func人员信息维护
 *@version 1.1
 *@author lyg
 */
$(function(){
	InitFormValidator(0);
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
	$("#add").click(function(){
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		var DECT_TYPE=parent.getDECT_TYPE();
		if(""==DECT_TYPE||null==DECT_TYPE||"--请选择--"==DECT_TYPE){
			alert("请选择页面上方的折扣类别");
			return;
		}
		var tab=true;
		var ids="";
		checkBox.each(function(){
			// 校验整型内容
			var id=$(this).parent().parent().find("input[name='DECT_RATE']").attr("id");
			var DECT_RATE=$(this).parent().parent().find("input[name='DECT_RATE']").val();
			if (!CheckFloatInput("#"+id)) {
				tab=false;
				return false;
			}
			if(0==DECT_RATE){
				alert("折扣率不能输入0");
				tab=false;
				return false
			}
			if("" != $(this).val()){
				ids = ids+"'"+$(this).val()+"',";
			}
		});
		ids = ids.substr(0,ids.length-1);
		var checkRateFlag =  checkRate();
		if(tab && checkRateFlag){
			childSave(ids,DECT_TYPE);
		}
	})
});
function childSave(ids,DECT_TYPE){
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "channdiscount.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData,"CHANN_DSCT_IDS":ids,"DECT_TYPE":DECT_TYPE},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.clickBut();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//输入折扣率批量修改时 选中所有渠道 修改所有渠道折扣率
function upDect(){
	var dect=$("#dect").val();
	if(!isNaN(dect)){
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length>0){
			checkBox.each(function(){
				$(this).parent().parent().find("input[name='DECT_RATE']").val(dect);
			})
		}
	}else{
		parent.showErrorMsg("输入折扣率不合法！");
	}
}
function setCheck(id){
	$("#"+id).prop("checked",true);
}



//返利折扣 不能大于1
function checkRate(){
	var DECT_TYPE = parent.$("#DECT_TYPE").val();
	var flag = true;
	if("返利折扣" == DECT_TYPE){
		var checkBoxs = $("#jsontb tr:gt(0) input:checked");
		checkBoxs.each(function(){
			var rate = $(this).parent().parent().find("input[json='DECT_RATE']").val();
			rate = parseFloat(rate);
			if(rate > 1){
				parent.showErrorMsg("折扣率不能大于1");
				flag = false;
				return false;
			}
				
		});
	}
	
	return flag;

}

