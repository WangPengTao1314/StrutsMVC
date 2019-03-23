/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time  2014-12-03 10:35:10 
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
 
	var selRowId = getSelRowId();
	//获取主表json数据
	var parentData = parent.topcontent.siglePackJsonData();
	 
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
 
	$.ajax({
		url: "marketact.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"MARKETING_ACT_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","marketact.shtml?action=toFrame");
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
		url: "marketact.shtml?action=marketCardEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"MARKETING_ACT_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_3");
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
		url: "marketact.shtml?action=marketCardDelete",
		type:"POST",
		dataType:"text",
		data:{"MARKETING_CARD_IDS":ids},
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
   newGoBack("marketact.shtml?action=toFrame");
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
              ''
	        ];
		}
 
 
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='valign:middle' json='MARKETING_CARD_ID' name='MARKETING_CARD_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="MARKETING_CARD_NO" id="MARKETING_CARD_NO'+rownum+'" name="MARKETING_CARD_NO'+rownum+'"  autocheck="true" label="卡券编号"  type="text" size="16"  inputtype="string"     mustinput="true"   maxlength="30"  value="'+arrs[1]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CARD_TYPE" id="CARD_TYPE'+rownum+'" name="CARD_TYPE'+rownum+'"  autocheck="true" label="卡券类型"  size="16" type="text"   inputtype="string"     mustinput="true"   maxlength="100" value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CARD_VALUE" id="CARD_VALUE'+rownum+'" name="CARD_VALUE'+rownum+'"  autocheck="true" label="卡券面值" size="16"  type="text"   inputtype="string"         maxlength="50"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CRE_TIME" id="CRE_TIME'+rownum+'" name="CRE_TIME'+rownum+'"    label="创建时间" size="16" type="text"   inputtype="string" readonly    mustinput="true"        value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CARD_SEQ_NO" id="CARD_SEQ_NO'+rownum+'" name="CARD_SEQ_NO'+rownum+'"  autocheck="true" label="卡券序号" size="16" type="text"   inputtype="string"      mustinput="true"     maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STATE" id="STATE'+rownum+'" name="STATE'+rownum+'"  autocheck="true" label="状态" size="16" type="text"   inputtype="string" readonly     mustinput="true"     maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')

            ;
	 
	 
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
}


 
 
