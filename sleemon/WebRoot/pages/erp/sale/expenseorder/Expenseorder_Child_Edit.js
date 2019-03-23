/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time   2014-12-03 10:35:10  
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
	  //  addRow();
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
		
		//设置主表的报销金额
        setMainEXPENSE_AMOUNT();

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
			//对于选中的明细校验
			if(!formMutiTrChecked()){
				return;
			}
			 //检查子表的报销金额
			if(!checkAmount()){
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
		btuMxRest();
		return;
	}
	//明细校验
	if(!formMutiTrChecked()){
		btuMxRest();
		return;
	}
    //检查子表的报销金额
	if(!checkAmount()){
		return;
	}
	var selRowId = getSelRowId();
	//获取主表json数据
	var parentData = parent.topcontent.siglePackJsonData();
	 
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
 
	$.ajax({
		url: "expenseorder.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"EXPENSE_ORDER_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","expenseorder.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
 
 
 
 function btuMxRest(){
	 btnReset(["delete","save","add"]);
 }
 
 
 
//子表保存
function childSave(){
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "expenseorder.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"EXPENSE_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
//				parent.window.gotoBottomPage("label_2");
				parent.topcontent.$("#pageForm").submit();
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
	var selRowId = getSelRowId();
	$.ajax({
		url: "expenseorder.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"EXPENSE_ORDER_DTL_IDS":ids,"EXPENSE_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
				//设置主表的报销金额
                setMainEXPENSE_AMOUNT();
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
		 parent.window.gotoBottomPage("label_2");
	}else{
		newGoBack("expenseorder.shtml?action=toFrame");
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
              '',
              ''
	        ];
		}
 
 
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='valign:middle' json='EXPENSE_ORDER_DTL_ID' name='EXPENSE_ORDER_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><select json="EXPENSE_TYPE" id="EXPENSE_TYPE'+rownum+'" name="EXPENSE_TYPE'+rownum+'"  autocheck="true" label="报销类型"    inputtype="string"   mustinput="true"  value="'+arrs[1]+'"></select>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="EXPENSE_REMARK" id="EXPENSE_REMARK'+rownum+'" name="EXPENSE_REMARK'+rownum+'"  autocheck="true" label="报销事由"  type="text"   inputtype="string"         maxlength="250"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left""><input json="BUSS_DATE" id="BUSS_DATE'+rownum+'" name="BUSS_DATE'+rownum+'"    label="发生日期" size="8" type="text"   inputtype="string" readonly onclick="SelectDate();"   mustinput="true"        value="'+arrs[3]+'"/>&nbsp;' +
            ' <img align="absmiddle" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="SelectDate(BUSS_DATE'+rownum+');"></td>')
            .append('<td nowrap align="left"><input  json="EXPENSE_AMOUNT" id="EXPENSE_AMOUNT'+rownum+'" name="EXPENSE_AMOUNT'+rownum+'"  autocheck="true" label="报销金额" size="11" type="text"   inputtype="xiaoshu"  valuetype="8,2"    mustinput="true" onkeyup="setMainEXPENSE_AMOUNT();"    maxlength="11"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="OTHER_REMARK" id="OTHER_REMARK'+rownum+'" name="OTHER_REMARK"  autocheck="true" label="其它说明"    type="text"   inputtype="string"  maxlength="250"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="EXPENSE_ATT" id="EXPENSE_ATT'+rownum+'" name="EXPENSE_ATT"  autocheck="true" label="附件"   type="text"   inputtype="string"  maxlength="250"  value="'+arrs[6]+'"/>&nbsp;</td>')

            ;
	 
	 
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
    
    uploadFile('EXPENSE_ATT'+rownum,'SAMPLE_DIR',true);
    SelDictShow("EXPENSE_TYPE"+rownum,"BS_151",arrs[1],"");
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
}

 
//设置主表的报销金额
function setMainEXPENSE_AMOUNT(){
	checkAmount();
};


function checkAmount(){
	var RELATE_AMOUNT_REQ = parent.topcontent.getRELATE_AMOUNT_REQ();  //申请金额
	var checkBox = $("#jsontb tr:gt(0) input[json='EXPENSE_AMOUNT']");
	var total = 0;
   
    checkBox.each(function(){
		var EXPENSE_AMOUNT = $(this).val();//报销金额
		total = total + Number(EXPENSE_AMOUNT); 
	});
	total = Math.round(total*100)/100;//计算报销金额，保留2位小数
	var actionType = getActionType();
	if("list" != actionType){
		parent.topcontent.setEXPENSE_AMOUNT(total);
	} 
    if(total > Number(RELATE_AMOUNT_REQ)){
      parent.showErrorMsg("报销金额不能大于申请金额");
      return false;
    }
 
    return true;
}
