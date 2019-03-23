/**
 * @prjName:喜临门营销平台
 * @fileName:Advpayment_Edit_Chld
 * @author chenj
 * @time   2013-10-20 18:57:47 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
	$("#add").click(function(){
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
		
		var ttt = $("#jsontb");
       	var nTds = $('tr:gt(0)', ttt);
		var i = 1;
		var allPayAmont = 0;//加上所有付款金额的钱
		
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
			nTds.each(function (){
				if($(this).find("input[type='checkbox']").attr("checked")==="checked"){
					allPayAmont = parseFloat(allPayAmont) + parseFloat($(this).find("#PAY_AMONT"+i).val());
				}
				i++;
			})
			if(allPayAmont != parseFloat(parent.topcontent.document.getElementById("STATEMENTS_AMOUNT").value)){
				parent.showErrorMsg("'本次收款金额'和'付款金额'相加的总额不等,请重新填写！");
				return;
			}
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
		url: "advpayment.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"STATEMENTS_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","advpayment.shtml?action=toFrame");
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
	//0205324: 预订单收款方式，订金类型的客户收款单收款方式改过之后，要更新对应预订单上的收款方式
	var actionType = getActionType();
	var ADVC_ORDER_ID = "";
    var isUpdateAdvcPayDtl = "";
	if("list" == actionType){
		var STATE = parent.topcontent.$("#state"+selRowId).val();
		ADVC_ORDER_ID = parent.topcontent.$("#ADVC_ORDER_ID"+selRowId).val();
		var BILL_TYPE = parent.topcontent.$("#BILL_TYPE"+selRowId).val();
		if("订金" == BILL_TYPE && "已结算" == STATE){
			isUpdateAdvcPayDtl = "true";
		}
	}
	 
	$.ajax({
		url: "advpayment.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"STATEMENTS_ID":selRowId,"ADVC_ORDER_ID":ADVC_ORDER_ID,"isUpdateAdvcPayDtl":isUpdateAdvcPayDtl},
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
		url: "advpayment.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"STATEMENTS_PAYMENT_DTL_IDS":ids},
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
function gobacknew(){
	var actionType = getActionType();
	if("list" == actionType){
		parent.topcontent.$("#queryForm").submit();
	}else{
		newGoBack("advpayment.shtml?action=toFrame");
	}
    
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
	        ];
	}
	//add by zzb 2015-06-12 渠道财务在‘已结算’状态需要修改 付款方式
	var textReadonly = "";
	var actionType = getActionType();
	if("list" == actionType){
		var state = parent.topcontent.getBillState();
		if("已结算" == state){
			textReadonly = "readonly";
			btnDisable(["add","delete"]);
		}
	}
	
	 
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr id='tr"+rownum+"' class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input id='STATEMENTS_PAYMENT_DTL_ID"+rownum+"' type='checkbox' style='height:12px;valign:middle' json='STATEMENTS_PAYMENT_DTL_ID' name='STATEMENTS_PAYMENT_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><select json="PAY_TYPE" id="PAY_TYPE'+rownum+'" style="width:155px" name="PAY_TYPE'+rownum+'" autocheck="true" label="付款方式"  inputtype="string"     mustinput="true" ></select><input type="hidden" id="PAY_TYPE_OLD'+rownum+'" json="PAY_TYPE_OLD"    value="'+arrs[1]+'"/></td>')
            .append('<td nowrap align="left"><input  json="PAY_BILL_NO" id="PAY_BILL_NO'+rownum+'" name="PAY_BILL_NO'+rownum+'"  '+textReadonly+' autocheck="true" label="付款单据号"  type="text"   inputtype="string"        maxlength="100"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PAY_AMONT" id="PAY_AMONT'+rownum+'" name="PAY_AMONT'+rownum+'"   '+textReadonly+' autocheck="true" label="付款金额"  type="text"   inputtype="string"     mustinput="true"     maxlength="22"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PAY_INFO" id="PAY_INFO'+rownum+'" name="PAY_INFO'+rownum+'"      '+textReadonly+' autocheck="true" label="付款信息"  type="text"   inputtype="string"        maxlength="100"  value="'+arrs[4]+'"/>&nbsp;</td>')
              ;
           
	 var LEDGER_ID = $("#LEDGER_ID").val();
	 SelDictShow("PAY_TYPE"+rownum, "BS_102", arrs[1], " DEL_FLAG = 0 and DATA_TYPE = '付款方式' and LEDGER_ID='"+LEDGER_ID+"'");
	 
	 //多加此行 杜绝加载的时候 需要鼠标滑过 才会显示
	 $("#PAY_TYPE"+rownum).append("<option>"+arrs[1]+"</option");
	
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
	
	 
}
