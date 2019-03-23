/**
 * @prjName:喜临门营销平台
 * @fileName:Storeout_Edit_Chld
 * @author chenj
 * @time   2013-09-15 14:59:50 
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
			if(!checkRealNum()){
				 parent.showErrorMsg(utf8Decode("实际出库数量应该大于0!"));
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

function checkRealNum(){
	var rownum = $("#jsontb tr").length;
	for(var i=1;i<rownum;i++){
		var realNum = $.trim($("#REAL_NUM"+i).val());
		if(realNum==0||realNum==''){
			 return false;
		}
	}
	return true;
}

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
		url: "storeout.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"STOREOUT_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","storeout.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){
	var billType=parent.window.document.getElementById("BILL_TYPE").value;
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "storeout.shtml?action=childEdit&BILL_TYPE="+billType,
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"STOREOUT_ID":selRowId},
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
		url: "storeout.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"STOREOUT_DTL_IDS":ids},
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
   newGoBack("storeout.shtml?action=toFrame");
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
	          '', '', '', '', '', '', '', '', '', '', '', '', '','','',''
              
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='STOREOUT_DTL_ID' name='STOREOUT_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  label="货品ID" type="hidden" value="'+arrs[2]+'"/>&nbsp;<input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'" label="货品编号" readonly type="text" value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'" label="货品名称"  type="text" readonly value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'" label="规格型号"  type="text" readonly value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'" label="花号"  type="text" readonly value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'" label="品牌"  type="text" readonly value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'" label="标准单位"  type="text" readonly value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NOTICE_NUM" id="NOTICE_NUM'+rownum+'" name="NOTICE_NUM'+rownum+'" label="通知出库数量"  type="text" readonly value="'+arrs[9]+'"/>&nbsp;</td>')
 			.append('<td nowrap align="left"><input class="REAL_NUM"  json="REAL_NUM" id="REAL_NUM'+rownum+'" name="REAL_NUM'+rownum+'" label="实际出库数量"  type="text" autocheck="true" inputtype="int" mustinput="true" maxlength="8" value="'+arrs[10]+'"/>&nbsp;')
            .append('<input  json="PRICE" id="PRICE'+rownum+'" name="PRICE'+rownum+'"  label="单价" type="hidden" readonly value="'+arrs[11]+'"/>&nbsp;')
            .append('<input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE'+rownum+'"  label="折扣" type="hidden" readonly value="'+arrs[12]+'"/>&nbsp;')
            .append('<input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE'+rownum+'"  label="折扣单价" type="hidden" readonly value="'+arrs[13]+'"/>&nbsp;')
            .append('<input  json="DECT_AMOUNT" id="DECT_AMOUNT'+rownum+'" name="DECT_AMOUNT'+rownum+'"  label="折后金额" type="hidden" readonly value="'+arrs[14]+'"/>&nbsp;</td>')
            //.append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  label="备注" type="text" readonly value="'+arrs[15]+'"/>&nbsp;</td>')
            ;
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	var STORAGE_FLAG = parent.document.getElementById("STORAGE_FLAG").value;
	if (1 == STORAGE_FLAG) {
		$(".REAL_NUM").attr('readonly','readonly'); 
		$(".REAL_NUM").css('color','blue'); 
		$(".REAL_NUM").css('text-decoration','underline'); 
		$(".REAL_NUM").css('text-decoration','underline'); 
		$(".REAL_NUM").css('text-align','right'); 
		$("#REAL_NUM"+rownum).click(function(){
			editGrendChild(arrs[0],arrs[1],rownum);
		});
	}
}

function editGrendChild(storeOutDtlId,storeOutId,rownum) {
	var storeId= parent.window.document.getElementById("STOREOUT_STORE_ID").value;
	var billType=parent.window.document.getElementById("BILL_TYPE").value;
	var strRealNum = window.showModalDialog('storeout.shtml?action=toStorgChildEdit&STOREOUT_DTL_ID='+ storeOutDtlId+"&STOREOUT_ID="+storeOutId+"&BILL_TYPE="+billType+"&STOREOUT_STORE_ID="+storeId,window,"dialogWidth=900px;dialogHeight=500px;depended=no");
	if(typeof strRealNum !="undefined"){
		setRealNum(rownum,strRealNum);
	}	
}

function setRealNum(i,realSum){
	document.getElementById("REAL_NUM"+i).value=realSum;
}
