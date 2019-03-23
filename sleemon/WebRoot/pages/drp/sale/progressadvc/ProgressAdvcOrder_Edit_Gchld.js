/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit_Gchld
 * @author lyg
 * @time   2013-08-11 17:17:29 
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
			gchildSave();
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
		url: "propressadvcorder.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"gchildJsonData":childData,"ADVC_ORDER_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_2");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function gchildSave(){
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "propressadvcorder.shtml?action=gchildEdit",
		type:"POST",
		dataType:"text",
		data:{"gchildJsonData":jsonData,"ADVC_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_2");
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
		url: "propressadvcorder.shtml?action=gchildDelete",
		type:"POST",
		dataType:"text",
		data:{"PAYMENT_DTL_IDS":ids},
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
   newGoBack("propressadvcorder.shtml?action=toFrame");
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
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='PAYMENT_DTL_ID' name='PAYMENT_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><select  json="PAY_TYPE" id="PAY_TYPE'+rownum+'" name="PAY_TYPE'+rownum+'"  style="width:155px" autocheck="true" label="付款方式"  mustinput="true"  inputtype="string"  maxlength="30"  ></select></td>')
            .append('<td nowrap align="left"><input  json="PAY_BILL_NO" id="PAY_BILL_NO'+rownum+'" name="PAY_BILL_NO'+rownum+'"  autocheck="true" label="付款单据号"  type="text"  inputtype="string"  maxlength="30" value="'+arrs[2]+'"  ></select></td>')
            .append('<td nowrap align="left"><input  json="PAY_AMONT" id="PAY_AMONT'+rownum+'" name="PAY_AMONT'+rownum+'"  autocheck="true" label="付款金额"  type="text"   inputtype="zffloat"  mustinput="true"     valueType="12,2"   value="'+arrs[3]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PAY_INFO" id="PAY_INFO'+rownum+'" name="PAY_INFO'+rownum+'"  autocheck="true" label="付款信息"  type="text"   inputtype="string"        maxlength="100"    value="'+arrs[4]+'"/>' +
            "<img  align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcBank("+rownum+")'/></td>")
            .append("<input type='hidden' id='PLAY_BANK_NAME"+rownum+"' name='PLAY_BANK_NAME' />")
            .append("<input type='hidden' id='PLAY_BANK_ACCT"+rownum+"' name='PLAY_BANK_ACCT' />")
        
            ;
	var LEDGER_ID = $("#LEDGER_ID").val();
	SelDictShow("PAY_TYPE"+rownum, "BS_102", arrs[1], " DEL_FLAG = 0 and LEDGER_ID='"+LEDGER_ID+"'");
	
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}
function formCheckedEx(){
	//明细提交时，可能会有多行，只有选中的才校验
	var chFlg=true;
	$("#jsontb").find("tr:gt(0)").each(function(){
		if($(this).find("input:checked").length>0){
			var selects = $(this).find("select");
			var selectChrgType = selects[0].value;
			if(selectChrgType==null||selectChrgType==""){
		       parent.showErrorMsg("请选择付款方式!");
		       chFlg= false;
	        }else{
	        	chFlg=true;
	        }
		}
	});
	return chFlg;
}
function selcBank(rownum){
	var TERM_ID=parent.topcontent.getTermId();
	$("#selectParams").val(" TERM_ID = '"+TERM_ID+"' ");
	selCommon("BS_27", false, "PLAY_BANK_NAME"+rownum, "PLAY_BANK_NAME", "forms[0]","PLAY_BANK_NAME"+rownum+",PLAY_BANK_ACCT"+rownum, "PLAY_BANK_NAME,PLAY_BANK_ACCT", "selectParams");
	var PLAY_BANK_NAME=$("#PLAY_BANK_NAME"+rownum).val();
	var PLAY_BANK_ACCT=$("#PLAY_BANK_ACCT"+rownum).val();
	var PAY_INFO="";
	if(null!=PLAY_BANK_NAME&&""!=PLAY_BANK_NAME){
		PAY_INFO=PAY_INFO+PLAY_BANK_NAME+":";
	}
	PAY_INFO=PAY_INFO+PLAY_BANK_ACCT;
	$("#PAY_INFO"+rownum).val(PAY_INFO);
}
