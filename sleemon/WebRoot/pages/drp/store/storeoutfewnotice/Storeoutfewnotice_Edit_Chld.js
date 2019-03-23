/**
 * @prjName:喜临门营销平台
 * @fileName:Storeinnotice_Edit_Chld
 * @author glw
 * @time   2013-08-17 17:07:03 
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
		url: "storeoutfewnotice.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"FEW_STOREOUT_REQ_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","storeoutfewnotice.shtml?action=toFrame");
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
		url: "storeoutfewnotice.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"FEW_STOREOUT_REQ_ID":selRowId},
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
		url: "storeoutfewnotice.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"FEW_STOREOUT_REQ_DTL_IDS":ids},
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
	  parent.window.gotoBottomPage("label_1"); 
   }else{
	   newGoBack("storeoutfewnotice.shtml?action=toFrame");
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
	var SPCL_TECH_FLAG;
	if(arrs[10]==null||arrs[10]==""){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<font style='color: red'>有</font><input class='btn'  value='查看' onclick='url("+rownum+")'  type='button' />";
	}
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='FEW_STOREOUT_REQ_DTL_ID' name='FEW_STOREOUT_REQ_DTL_ID"+rownum+"' id='FEW_STOREOUT_REQ_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="center"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  autocheck="true" label="货品ID"  type="hidden" value="'+arrs[1]+'"/><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'" size="12"  autocheck="true" label="货品编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="32"  value="'+arrs[2]+'"/>&nbsp;<img align="absmiddle" name="selJGXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="javascript:selProduct('+rownum+')"/>&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="100" size="12"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="50" size="12"   value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"   label="品牌"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="50" size="12"   value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="50" size="3"   value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><span id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</span>&nbsp;</td>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID"  label="订单特殊工艺ID"  type="hidden"   value="'+arrs[7]+'" />')
            .append('<input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG"  label="订单特殊工艺标记"  type="hidden"     value="'+arrs[10]+'" />')
            .append('<td nowrap align="center"><input  json="NOTICE_NUM"   id="NOTICE_NUM'+rownum+'"   name="NOTICE_NUM'+rownum+'"    autocheck="true" label="通知出库数量"  type="text"  size="8"  value="'+arrs[8]+'" autocheck="true"  inputtype="string"  mustinput="true" />&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="备注"  type="text"   inputtype="string"  maxlength="250" value="'+arrs[9]+'"/>&nbsp;</td>');
	
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
}


//
function selProduct(rownum){
	var STORE_ID = parent.topcontent.getStoreId();
	if(""==STORE_ID||null==STORE_ID){
		parent.showErrorMsg("请先选择出库库房");
		return;
	}
	var PRD_ID = $("#PRD_ID"+rownum).val();
	var SPCL_TECH_ID = "";
	var SPCL_SQL = "";
	if(null != PRD_ID && "" != PRD_ID){
		 SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
		 if(null != SPCL_TECH_ID && "" != SPCL_TECH_ID){
			 SPCL_SQL = " and SPCL_TECH_ID='"+SPCL_TECH_ID+"' ";
		 }
	}
	
	var selectPrdParams=" STORE_ID = '"+STORE_ID+"' and DEL_FLAG=0 "+SPCL_SQL;
	$("#selectPrdParams").val(selectPrdParams);
    selCommon("BS_71", false, "PRD_ID"+rownum, "PRD_ID", "forms[0]",
			 "PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",SPCL_TECH_ID"+rownum+",SPCL_TECH_FLAG"+rownum, 
			 "PRD_NO,PRD_NAME,PRD_SPEC,BRAND,STD_UNIT,SPCL_TECH_ID,SPCL_TECH_FLAG","selectPrdParams");
    var SPCL_TECH_FLAG=$("#SPCL_TECH_FLAG"+rownum).val();
	var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
	if(SPCL_TECH_FLAG==null||SPCL_TECH_FLAG==""||SPCL_TECH_FLAG==0){
		$("#SPECIAL_FLAG"+rownum).html("无");
	}else{
		var onclick="urlById('"+SPCL_TECH_ID+"')";
		$("#SPECIAL_FLAG"+rownum).html("<font style='color: red'>有</font><input class='btn'  value='查看' onclick='url("+rownum+")'  type='button' />");
	}
}
function url(rownum){
	var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}

