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
		url: "marketact.shtml?action=marketChannEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"MARKETING_ACT_ID":selRowId},
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
		url: "marketact.shtml?action=marketChannDelete",
		type:"POST",
		dataType:"text",
		data:{"MARKETING_CHANN_IDS":ids},
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
		 parent.window.gotoBottomPage("label_2");
	}else{
		newGoBack("marketact.shtml?action=toFrame");
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
		    .append("<td nowrap align='center'><input type='checkbox' style='valign:middle' json='MARKETING_CHANN_ID' name='MARKETING_CHANN_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left" ><input type="hidden" json="CHANN_ID" id="CHANN_ID'+rownum+'" name="CHANN_ID'+rownum+'"  label="渠道ID" maxlength="32" value="'+arrs[1]+'"/>'+
            '<input  json="CHANN_NO" id="CHANN_NO'+rownum+'" name="CHANN_NO'+rownum+'"  autocheck="true" label="渠道编号"  type="text" size="16"  inputtype="string"   readonly  mustinput="true"   maxlength="30"  value="'+arrs[2]+'"/>&nbsp;' +
            '<img id="img'+rownum+'" align="absmiddl" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="selcChann('+rownum+')"/></td>')
            .append('<td nowrap align="left"><input  json="CHANN_NAME" id="CHANN_NAME'+rownum+'" name="CHANN_NAME'+rownum+'"  autocheck="true" label="渠道名称"  size="16" type="text"   inputtype="string"  readonly   mustinput="true"   maxlength="100" value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CHANN_TYPE" id="CHANN_TYPE'+rownum+'" name="CHANN_TYPE'+rownum+'"  autocheck="true" label="渠道类型" size="16"  type="text"   inputtype="string"  readonly       maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left""><input json="WAREA_ID" id="WAREA_ID'+rownum+'" name="WAREA_ID'+rownum+'"   label=" " size="12"  type="hidden"   inputtype="string"    value="'+arrs[5]+'"/>'+
            '<input  json="WAREA_NO" id="WAREA_NO'+rownum+'" name="WAREA_NO'+rownum+'"    label="战区编号" size="16" type="text"   inputtype="string" readonly    mustinput="true"        value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="WAREA_NAME" id="WAREA_NAME'+rownum+'" name="WAREA_NAME'+rownum+'"  autocheck="true" label="战区名称" size="16" type="text"   inputtype="string" readonly     mustinput="true"     maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="AREA_MANAGE_ID" id="AREA_MANAGE_ID'+rownum+'" name="AREA_MANAGE_ID"    type="hidden"  value="'+arrs[8]+'"/> '+
            '<input  json="AREA_MANAGE_NAME" id="AREA_MANAGE_NAME'+rownum+'" name="AREA_MANAGE_NAME"  autocheck="true" label="区域经理名称" size="16"  type="text"   inputtype="string"   readonly     maxlength="11"  value="'+arrs[9]+'"/>&nbsp;</td>')
            ;
	 
	 
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
}


function selcChann(rownum){
	var CHANN_IDS = $("#CHANN_IDS").val();
	var checkBox = $("#jsontb tr:gt(0) input:checkbox");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		var dtlid = $(this).val();
		if(null == dtlid || "" == dtlid){
			var id = $(this).parent().parent().find("input[json='CHANN_ID']").val();
			if(null !=id && "" != id){
			   ids = ids+"'"+id+"',";
			}
		}
		
	});
	ids = ids.substr(0,ids.length-1);
	if("" != ids){
		if(null != CHANN_IDS && "" != CHANN_IDS){
			CHANN_IDS = CHANN_IDS+","+ids;
		}else{
			CHANN_IDS = ids;
		}
	}
	var params = "";
	if("" != CHANN_IDS){
		params = "  CHANN_ID not in("+CHANN_IDS+") ";
	}
	$("#selectParams").val(params);
    var rtnArr =  selCommon("BS_148", true, "CHANN_ID"+rownum, "CHANN_ID", "forms[0]","CHANN_NO"+rownum+",CHANN_NAME"+rownum+",CHANN_TYPE"+rownum+",WAREA_ID"+rownum
    	+",WAREA_NO"+rownum+",WAREA_NAME"+rownum+",AREA_MANAGE_ID"+rownum+",AREA_MANAGE_NAME"+rownum, "CHANN_NO,CHANN_NAME,CHANN_TYPE,WAREA_ID,WAREA_NO,WAREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME", "selectParams");
    multiSelCommonSet(rtnArr,"CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_TYPE,WAREA_ID,WAREA_NO,WAREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME",rownum);
 
}
 
 
