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

//子表保存
function gchildSave(){
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "deliveryhd.shtml?action=freeChildEdit",
		type:"POST",
		dataType:"text",
		data:{"gchildJsonData":jsonData,"DELIVER_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				parent.window.gotoBottomPage("label_4");
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
		url: "deliveryhd.shtml?action=freeChildDelete",
		type:"POST",
		dataType:"text",
		data:{"DELIVER_FREEZE_DTL_IDS":ids},
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
   newGoBack("deliveryhd.shtml?action=toFrame");
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
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='DELIVER_FREEZE_DTL_ID' name='DELIVER_FREEZE_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="ORDER_CHANN_NO" id="ORDER_CHANN_NO'+rownum+'" name="ORDER_CHANN_NO'+rownum+'"  autocheck="true" label="订货方编号"  type="text"  inputtype="string"  readonly    mustinput="true" maxlength="30" value="'+arrs[2]+'"  >' +
            "<img name='imgTab' align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcChann("+rownum+")'/></td>")
            .append('<td nowrap align="left"><input  json="ORDER_CHANN_NAME" id="ORDER_CHANN_NAME'+rownum+'" name="ORDER_CHANN_NAME"  autocheck="true" label="订货方名称"  type="text"   inputtype="string"  mustinput="true" readonly   value="'+arrs[3]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="FREEZE_AMOUNT" id="FREEZE_AMOUNT'+rownum+'" name="FREEZE_AMOUNT"  autocheck="true" label="发货控制金额"  type="text"   inputtype="float"   valueType="8,2"   mustinput="true"  maxlength="100"    value="'+arrs[4]+'"/></td>')
            .append("<input type='hidden' id='ORDER_CHANN_ID"+rownum+"' name='ORDER_CHANN_ID' json='ORDER_CHANN_ID' value='"+arrs[1]+"' />")
        
            ;
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}
function selcChann(rownum){
	 var obj=selCommon("BS_19", true, "ORDER_CHANN_ID"+rownum, "CHANN_ID", "forms[0]","ORDER_CHANN_ID"+rownum+",ORDER_CHANN_NO"+rownum+",ORDER_CHANN_NAME"+rownum, 
	    	 				"CHANN_ID,CHANN_NO,CHANN_NAME", "selectParams");
	     rtnArr=multiSelCommonSet(obj,"ORDER_CHANN_ID,ORDER_CHANN_NO,ORDER_CHANN_NAME",rownum);
}