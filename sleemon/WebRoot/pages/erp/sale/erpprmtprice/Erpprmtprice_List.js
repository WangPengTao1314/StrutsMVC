
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
		var PRMT_PLAN_ID=parent.getPRMT_PLAN_ID();
		if(""==PRMT_PLAN_ID||null==PRMT_PLAN_ID){
			parent.showErrorMsg("请选择页面上方的促销活动编号");
			return;
		}
		var tab=true;
		var ids="";
		checkBox.each(function(){
			// 校验整型内容
			var id=$(this).parent().parent().find("input[name='PRMT_PRVD_PRICE']").attr("id");
			var PRMT_PRVD_PRICE=$(this).parent().parent().find("input[name='PRMT_PRVD_PRICE']").val();
			if (!CheckFloatInput("#"+id)) {
				tab=false;
				return false;
			}
			if(0==PRMT_PRVD_PRICE){
				parent.showErrorMsg("活动供货价不能输入0");
				tab=false;
				return false
			}
		});
		if(tab){
			childSave(PRMT_PLAN_ID);
		}
	})
	$("#start").click(function(){
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		updateState("启用");
	})
	$("#stop").click(function(){
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		updateState("停用");
	})
});
function childSave(PRMT_PLAN_ID){
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "erpprmtprice.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"jsonData":jsonData,"PRMT_PLAN_ID":PRMT_PLAN_ID},
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
				$(this).parent().parent().find("input[name='PRMT_PRVD_PRICE']").val(dect);
			})
		}
	}else{
		parent.showErrorMsg("输入价格不合法！");
	}
}
function setCheck(id){
	$("#"+id).prop("checked",true);
}
function updateState(state){
	parent.showWaitPanel();
	var ids="";
	var flag=true;
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.each(function(){
		var id=$(this).val();
		if(""==id||null==id||"undefined"==id){
			closeWindow();
			parent.showErrorMsg("所选货品有未设置活动供货价，请先保存再"+state+"！");
			flag=false;
			return false;
		}
		var oldState=$("#state"+id).val();
		if(state==oldState){
			closeWindow();
			parent.showErrorMsg("所选货品有状态为"+state+"的数据，不能选择已"+state+"的货品！");
			flag=false;
			return false;
		}
		ids = ids+"'"+id+"',";
	})
	if(!flag){
		return;
	}
	ids = ids.substr(0,ids.length-1);
	$.ajax({
		url: "erpprmtprice.shtml?action=updateState",
		type:"POST",
		dataType:"text",
		data:{"PRMT_PRICE_IDS":ids,"STATE":state},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel(state+"成功");
				closeWindow();
				parent.clickBut();
			}else{
				closeWindow();
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}



