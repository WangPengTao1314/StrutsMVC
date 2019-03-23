/**
 * @prjName:喜临门营销平台
 * @fileName:Storein_Edit_Chld
 * @author glw
 * @time   2013-08-23 14:40:48 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
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
			//对于选中明细校验
			if(!formMutiTrChecked()){
				return;
			}
			childSave();
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


//子表保存
function childSave(){
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "storein.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"STOREIN_ID":selRowId},
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
//返回
function gobacknew()
{
   newGoBack("storein.shtml?action=toFrame");
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
		    .append('<td nowrap align="center"><input type="checkbox" style="height:12px;valign:middle" json="STOREIN_DTL_ID" id="STOREIN_DTL_ID'+rownum+'" name="STOREIN_DTL_ID'+rownum+'"  value="'+arrs[0]+'"/>&nbsp;<input json="STOREIN_ID" id="STOREIN_ID'+rownum+'" name="STOREIN_ID'+rownum+'" label="入库单ID" type="hidden" value="'+arrs[1]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  label="货品ID" type="hidden" value="'+arrs[2]+'"/>&nbsp;<input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'" label="货品编号" readonly type="text" value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'" label="货品名称"  type="text" readonly value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'" label="规格型号"  type="text" readonly value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'" label="花号"  type="text" readonly value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'" label="品牌"  type="text" readonly value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'" label="标准单位"  type="text" readonly value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NOTICE_NUM" id="NOTICE_NUM'+rownum+'" name="NOTICE_NUM'+rownum+'" label="通知入库数量"  type="text" readonly value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE'+rownum+'"  label="单价" type="hidden" value="'+arrs[11]+'"/>&nbsp;<input  class="REAL_NUM" json="REAL_NUM" id="REAL_NUM'+rownum+'" name="REAL_NUM'+rownum+'"  autocheck="true" label="实际入库数量"  type="text"   inputtype="int"   maxlength="10"   mustinput="true" value="'+arrs[10]+'"/>&nbsp;</td>')
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
			editGrendChild(arrs[0]);
		});
	}
}
function editGrendChild(temp) {
	window.showModalDialog('storein.shtml?action=modifyToGrandChildEdit&STOREIN_DTL_ID='+ temp,window,"dialogWidth=900px;dialogHeight=500px;depended=no");
	var STOREIN_ID = parent.document.getElementById("selRowId").value;
	window.location.href = "storein.shtml?action=modifyToChildEdit&STOREIN_ID="+STOREIN_ID;
}
