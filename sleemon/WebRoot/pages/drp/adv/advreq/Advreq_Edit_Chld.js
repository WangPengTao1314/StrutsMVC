/**
 * @prjName:喜临门营销平台
 * @fileName:Advreq_Edit_Chld
 * @author ghx
 * @time   2014-07-15  
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
	$("#add").click(function(){
	    addRow();
	});
	
	$("body").dblclick(function(){
	    addRow();
	});
	
	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		//获取所有选中的记录
		var ids = "";
		checkBox.each(function(){
			if("" != $(this).val()){
				ids = ids+"'"+$(this).val()+"',";
			}
		});
		ids = ids.substr(0,ids.length-1);
		//没有ids说明都是页面新增的，数据库中无记录，可以直接remove
		if("" == ids){
			checkBox.parent().parent().remove();
		}else{
			parent.showConfirm("您确认要删除吗","bottomcontent.multiRecDeletes();");
		}
		//删除后scroll可能消失，因此需要重新调整浮动按钮的位置
		setFloatPostion();
	});
	
	$("#save").click(function(){
		//当前所在页面有2种可能，列表展示页面，编辑页面。
		var actionType = getActionType();
		if("list" == actionType){
			//查找当前是否有选中的记录
			var checkBox = $("#jsontb tr:gt(0) input:checked");
			if(checkBox.length<1){
				parent.showErrorMsg("请至少选择一条记录");
				return;
			}
			//对于选中的零星领料单明细校验
			if(!formMutiTrChecked()){
				return;
			}
			childSave();
		}else{
			//编辑页面，子表没有选中记录也可以提交，故不需要校验。
			allSave();
		}
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
});

//主子表保存
function allSave(){
	//主表form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	//明细校验
	if(!formMutiTrChecked()){
		return;
	}
	var selRowId = getSelRowId();
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	
	$.ajax({
		url: "advreq.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"ERP_ADV_REQ_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","advreq.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "advreq.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"ERP_ADV_REQ_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_1");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//删除操作
function multiRecDeletes(){
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "advreq.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"ERP_ADV_REQ_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//返回
function gobacknew()
{
   newGoBack("advreq.shtml?action=toFrame");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	var actionType = parent.document.getElementById("actionType").value;
    $("#jsontb tr:gt(0) :checkbox").attr("checked","true");
	//form校验设置
	InitFormValidator(0);
	
	getTotalAmount();
}

function getActionType(){
	return parent.document.getElementById("actionType").value;
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}
// 固定的一些共通的方法 end
// 下面这个方法要自己修改
//表格增加一行
function addRow(arrs){
	if(null == arrs){
     arrs = [
	          '',
	          '',
	          '',
	          '',
	          '',
	          '',
	          '',
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	if(arrs[6]==null||arrs[6]==""){
		arrs[6]='未付款';
	}
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append('<td nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" json="ERP_ADV_REQ_DTL_ID" id="ERP_ADV_REQ_DTL_ID'+rownum+'" name="ERP_ADV_REQ_DTL_ID" value="'+arrs[0]+'"/></td>')
		    .append('<td nowrap align="center"><select json="PAY_BATCH" id="PAY_BATCH'+rownum+'" name="PAY_BATCH" mustinput="true"  autocheck="true" label="付款批次" inputtype="string" value="'+arrs[1]+'"></select>&nbsp;</td>')
		    .append('<td nowrap align="center"><input type="text" json="PAY_PERCENT" id="PAY_PERCENT'+rownum+'" name="PAY_PERCENT" autocheck="true" label="付款比例"  onkeyup="getPAY_AMOUNT('+rownum+')" onblur="getPAY_AMOUNT('+rownum+')" mustinput="true" value="'+arrs[2]+'"/>&nbsp;</td>')
		    .append('<td nowrap align="center"><input type="text" json="PAY_AMOUNT" id="PAY_AMOUNT'+rownum+'" name="PAY_AMOUNT" label="付款金额" readonly value="'+arrs[3]+'"/>&nbsp;</td>')
		    .append('<td nowrap align="center"><input type="text" json="PAY_COND" id="PAY_COND'+rownum+'" name="PAY_COND" label="付款条件" value="'+arrs[4]+'"/>&nbsp;</td>')
		    .append('<td nowrap align="center"><select json="PAY_TYPE" id="PAY_TYPE'+rownum+'" name="PAY_TYPE" label="付款方式" value="'+arrs[5]+'"></select>&nbsp;</td>')
		    .append('<td nowrap align="center"><input type="text" json="STATE" id="STATE'+rownum+'" name="STATE" label="状态" readonly  value="'+arrs[6]+'"/>&nbsp;</td>')
              ;
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
    
    SelDictShow("PAY_BATCH"+rownum,"BS_113",arrs[1],"");
    SelDictShow("PAY_TYPE"+rownum,"BS_114",arrs[5],"");
	//form校验设置	
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}

function getTotalAmount(){
	var ADV_TOTAL_AMOUNT = 0;
	var actionType = getActionType();
	if("list" == actionType){
		var selRowId = getSelRowId();
		ADV_TOTAL_AMOUNT = parent.topcontent.document.getElementById("ADV_TOTAL_AMOUNT" + selRowId).value;
		
	}else{
		ADV_TOTAL_AMOUNT = parent.topcontent.document.getElementById("ADV_TOTAL_AMOUNT").value;
	}
	$("#ADV_TOTAL_AMOUNT").val(ADV_TOTAL_AMOUNT);
}

function getPAY_AMOUNT(i){
	var ADV_TOTAL_AMOUNT = $("#ADV_TOTAL_AMOUNT").val();//广告投放总预算金额 
	var PAY_PERCENT=$("#PAY_PERCENT"+i).val();//付款比例(%)
	if(ADV_TOTAL_AMOUNT == null || ADV_TOTAL_AMOUNT == ''){
		ADV_TOTAL_AMOUNT = '0';
	}
	if(PAY_PERCENT == null || PAY_PERCENT == ''){
		PAY_PERCENT = '0';
	}
	var re1 = new RegExp(/^(?:[1-9][0-9]?|100)$/);
	if(!re1.test(PAY_PERCENT)){
		parent.showErrorMsg("'付款比例'只能输入小于等于100的正整数!");
		$("#PAY_PERCENT"+i).val("");
		return false;
	}
	var PAY_AMOUNT = countPay(ADV_TOTAL_AMOUNT,PAY_PERCENT);
	$("#PAY_AMOUNT"+i).val(PAY_AMOUNT);
}

function countPay(ADV_TOTAL_AMOUNT,PAY_PERCENT){
	var total = isNaN(ADV_TOTAL_AMOUNT)?0:parseFloat(ADV_TOTAL_AMOUNT);
	var rate=isNaN(PAY_PERCENT)?0:parseFloat(PAY_PERCENT);
	return Math.round(total*rate/100);
}
