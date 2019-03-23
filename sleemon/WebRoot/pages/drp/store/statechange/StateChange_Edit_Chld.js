/**
 * @prjName:喜临门营销平台
 * @fileName:形态转换
 * @author zzb
 * @time   2013-09-05 14:00:34 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
	$("#add").click(function(){
	    addRow();
	});
	
//	$("body").dblclick(function(){
//	    addRow();
//	});
	
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
			//保存之前 验证转储数量
			var f = saveCheckDumpNum();
			if(!f){
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
	//保存之前 验证转储数量
	var f = saveCheckDumpNum();
	if(!f){
		return;
	}
	//明细校验
	if(!formMutiTrChecked()){
		return;
	}
	//验证转储数量 是否为0
//	if(!checkDumpNumZero()){
//		return;
//	}
	
	var selRowId = getSelRowId();
	//获取json数据
	var parentData = parent.topcontent.siglePackJsonData();
	var childData;
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	
	$.ajax({
		url: "statechange.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"STATE_CHANGE_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","statechange.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){
    var f = saveCheckDumpNum();
	if(!f){
		return;
	}
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "statechange.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"STATE_CHANGE_ID":selRowId},
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


//验证 转储数量
function checkDumpNumZero(){
	var flag = true;
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.each(function(){
		var CHANGE_NUM = $(this).parent().parent().find("input[json='CHANGE_NUM']").val();
		if(0== CHANGE_NUM || "0" == CHANGE_NUM){
			parent.showErrorMsg("请输入'转换数量'");
			flag = false;
			return false;
		}
	});
	return flag;
	 
}
//删除操作
function multiRecDeletes(tag){
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "statechange.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"STATE_CHANGE_DTL_IDS":ids},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
				if(tag != 1){
					parent.showMsgPanel("删除成功");
				}
			}else{
				if(tag != 1){
				   parent.showErrorMsg(utf8Decode(jsonResult.messages));
				}
			}
		}
	});
}
//返回
function gobacknew()
{
   newGoBack("statechange.shtml?action=toFrame");
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
	var PAR_SPECIAL_FLAG;
	if(null == arrs[7] || "" == arrs[7]){
		PAR_SPECIAL_FLAG = '无';
	}else{
		var fn = "showTechPage('"+arrs[7]+"')";
		PAR_SPECIAL_FLAG = "<font style='color: red'>有</font> <input  class='btn'   value='查看' onclick="+fn+"  type='button' />"
	}
	
	var CHANGED_SPECIAL_FLAG;
	if(null == arrs[11] || "" == arrs[11]){
		CHANGED_SPECIAL_FLAG = "<font style='color: black'>无</font> <input class='btn'  value='编辑' onclick='showEditTechPage("+rownum+")'  type='button' />";
	}else{
		CHANGED_SPECIAL_FLAG = "<font style='color: red'>有</font> <input class='btn'  value='编辑' onclick='showEditTechPage("+rownum+")'  type='button' />";
	}
	
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='STATE_CHANGE_DTL_ID' name='STATE_CHANGE_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="STORE_ID" id="STORE_ID'+rownum+'" name="STORE_ID'+rownum+'"  label="库房ID" type="hidden"      value="'+arrs[1]+'"/>' +
            '<input  json="STORE_NO" id="STORE_NO'+rownum+'" name="STORE_NO'+rownum+'"  autocheck="true" label="库房编号" size="15" type="text"   inputtype="string"     mustinput="true" readonly    maxlength="30"  value="'+arrs[2]+'"/>&nbsp;' +
            '<img align="absmiddle" name="selStore" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="selectStore('+rownum+');"></td>')
            .append('<td nowrap align="left"><input  json="STORE_NAME" id="STORE_NAME'+rownum+'" name="STORE_NAME'+rownum+'"  autocheck="true" label="库房名称" size="15" type="text"   inputtype="string"     mustinput="true"  readonly   maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left">' +
            '<input  json="PAR_CHANGE_PRD_ID" id="PAR_CHANGE_PRD_ID'+rownum+'" name="PAR_CHANGE_PRD_ID'+rownum+'"  label="转换前货品ID" type="hidden"      value="'+arrs[4]+'"/>'+
            '<input  json="PAR_CHANGE_PRD_NO" id="PAR_CHANGE_PRD_NO'+rownum+'" name="PAR_CHANGE_PRD_NO'+rownum+'"  autocheck="true" label="转换前货品编号" size="12" type="text"   inputtype="string"     mustinput="true"  readonly   maxlength="50"  value="'+arrs[5]+'"/>' +
            '<img align="absmiddle" name="selPRD" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="selParPrd('+rownum+')">'+
            '&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PAR_CHANGE_PRD_NAME" id="PAR_CHANGE_PRD_NAME'+rownum+'" name="PAR_CHANGE_PRD_NAME'+rownum+'"   label="转换前货品名称"  size="8" type="text"   inputtype="string"        readonly  maxlength="100"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="PAR_DM_SPCL_TECH_NO" id="PAR_DM_SPCL_TECH_NO'+rownum+'" name="PAR_DM_SPCL_TECH_NO'+rownum+'"   label="转换前特殊工艺编号"  size="8" type="text"   inputtype="string"        readonly  maxlength="50"  value="'+arrs[16]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center" label="特殊工艺"><span id="PAR_SPECIAL_FLAG'+rownum+'">'+PAR_SPECIAL_FLAG+'</span> &nbsp;</td>')
            
            .append('<td nowrap align="left">' +
            '<input  json="CHANGED_PRD_ID" id="CHANGED_PRD_ID'+rownum+'" name="CHANGED_PRD_ID'+rownum+'"  label="转换后货品ID" type="hidden"      value="'+arrs[8]+'"/>'+
            '<input  json="CHANGED_PRD_NO" id="CHANGED_PRD_NO'+rownum+'" name="CHANGED_PRD_NO'+rownum+'"  autocheck="true" label="转换后货品编号" size="12" type="text"   inputtype="string"     mustinput="true"  readonly   maxlength="50"  value="'+arrs[9]+'"/>' +
            '<img align="absmiddle" name="selPRD" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="selchangePrd('+rownum+')">'+
            '&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CHANGED_PRD_NAME" id="CHANGED_PRD_NAME'+rownum+'" name="CHANGED_PRD_NAME'+rownum+'"   label="转换后货品名称"  size="8" type="text"   inputtype="string"  readonly  maxlength="100"  value="'+arrs[10]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="CHANGED_DM_SPCL_TECH_NO" id="CHANGED_DM_SPCL_TECH_NO'+rownum+'" name="CHANGED_DM_SPCL_TECH_NO'+rownum+'"   label="转换后特殊工艺编号"  size="8" type="text"   inputtype="string"        readonly  maxlength="50"  value="'+arrs[17]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center" label="特殊工艺"><span id="CHANGED_SPECIAL_FLAG'+rownum+'">'+CHANGED_SPECIAL_FLAG+'</span> &nbsp;</td>')
            
            
            .append('<td nowrap align="left"><input  json="CHANGE_NUM" id="CHANGE_NUM'+rownum+'" name="CHANGE_NUM'+rownum+'"  autocheck="true" label="转换数量" size="8" onkeyup="checkDumpNum('+rownum+')"  type="text"   inputtype="int"     mustinput="true"     maxlength="8"  value="'+arrs[12]+'"/></td>')
            .append('<td nowrap align="left"><input type="text"  label="转出库房的库存量" size="8" readonly name="SAFE_NUM" id="SAFE_NUM'+rownum+'" value="'+arrs[15]+'" /> </td>')
            .append('<input  json="PAR_CHANGE_SPCL_TECH_ID" id="PAR_CHANGE_SPCL_TECH_ID'+rownum+'" name="PAR_CHANGE_SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[7]+'"/>')
            .append('<input  json="PAR_SPCL_TECH_FLAG" id="PAR_SPCL_TECH_FLAG'+rownum+'" name="PAR_SPCL_TECH_FLAG'+rownum+'"  label="特殊工艺单标记" type="hidden" value="'+arrs[13]+'"/>')
            
            .append('<input  json="CHANGED_SPCL_TECH_ID" id="CHANGED_SPCL_TECH_ID'+rownum+'" name="CHANGED_SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[11]+'"/>')
            .append('<input  json="CHANGED_SPCL_TECH_FLAG" id="CHANGED_SPCL_TECH_FLAG'+rownum+'" name="CHANGED_SPCL_TECH_FLAG'+rownum+'"  label="特殊工艺单标记" type="hidden" value="'+arrs[14]+'"/>');
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	//trCheckInit($("#jsontb tr:gt(0) select"));
}


//选择转换前的货品
function selParPrd(rownum){
	var STORE_ID = $("#STORE_ID"+rownum).val();
	if(null == STORE_ID || "" == STORE_ID){
		parent.showErrorMsg("请选择'库房编号'");
		return false;
	}
	var ZTXXID = $("#ZTXXID").val();
	$("#selParam").val("  STORE_ID='"+STORE_ID+"' and  STOLEDGER='"+ZTXXID+"'  and NVL(SAFE_NUM,0)>0 ");
	selCommon("BS_130", false, "PAR_CHANGE_PRD_ID"+rownum, "PRD_ID", "forms[0]","PAR_CHANGE_PRD_NO"+rownum+",PAR_CHANGE_PRD_NAME"+rownum+",PAR_CHANGE_SPCL_TECH_ID"+rownum+",PAR_DM_SPCL_TECH_NO"+rownum+",PAR_SPCL_TECH_FLAG"+rownum+",SAFE_NUM"+rownum, "PRD_NO,PRD_NAME,SPCL_TECH_ID,DM_SPCL_TECH_NO,SPCL_TECH_FLAG,SAFE_NUM","selParam");
	var SPCL_TECH_FLAG = $("#PAR_SPCL_TECH_FLAG"+rownum).val();
	var PAR_CHANGE_SPCL_TECH_ID = $("#PAR_CHANGE_SPCL_TECH_ID"+rownum).val();
//	$("#DUMP_NUM"+rownum).val("");
    if(SPCL_TECH_FLAG > 0){
       var fn = "showTechPage('"+PAR_CHANGE_SPCL_TECH_ID+"')";
	   var SPCL_TECH = "<font style='color: red'>有</font><input class='btn'  value='查看' onclick="+fn+"  type='button' />"
	   $("#PAR_SPECIAL_FLAG"+rownum).html(SPCL_TECH);
    }else{
	   $("#PAR_SPECIAL_FLAG"+rownum).html("无");
    }
}


function selchangePrd(rownum){
	var STORE_ID = $("#STORE_ID"+rownum).val();
	if(null == STORE_ID || "" == STORE_ID){
		parent.showErrorMsg("请选择'库房编号'");
		return false;
	}
	var PAR_CHANGE_PRD_ID = $("#PAR_CHANGE_PRD_ID"+rownum).val();
	if(null == PAR_CHANGE_PRD_ID || "" == PAR_CHANGE_PRD_ID){
		parent.showErrorMsg("请选择'转换前货品编号'");
		return false;
	}
	var CHANGED_SPCL_TECH_ID = $("#CHANGED_SPCL_TECH_ID"+rownum).val();
	var CHANGED_PRD_NO = $("#CHANGED_PRD_NO"+rownum).val();
	var tempParam = "";
	if(null != CHANGED_PRD_NO && "" != CHANGED_PRD_NO){
		if(null != CHANGED_SPCL_TECH_ID && "" != CHANGED_SPCL_TECH_ID){
			tempParam = " and SPCL_TECH_ID='"+CHANGED_SPCL_TECH_ID+"' ";
		}else{
			tempParam = " and SPCL_TECH_ID is null  ";
		}
	}
	var ZTXXID = $("#ZTXXID").val();
//	var sql = " (select p1.PRD_ID,p1.PRD_NO,p1.PRD_NAME,p1.PRD_SPEC,p1.PRD_COLOR,p1.BRAND ,'' SPCL_TECH_ID,"+
//                               " '' DM_SPCL_TECH_NO,"+
//                               " '' SPCL_DTL_REMARK,"+
//                               " '' LEDGER_ID,"+
//                               " null SPCL_TECH_FLAG  " +
//                               "  from "+
//     " BASE_PRODUCT p1 where p1.STATE='启用' and  p1.PRD_NO not like '%-%' and  p1.DEL_FLAG=0  and p1.FINAL_NODE_FLAG=1 and p1.IS_NO_STAND_FLAG=0 and p1.COMM_FLAG=1 "+
//	 " union all "+
//       " select p.PRD_ID,p.PRD_NO,p.PRD_NAME,p.PRD_SPEC,p.PRD_COLOR,p.BRAND,"+
//	   " s.SPCL_TECH_ID,s.DM_SPCL_TECH_NO,s.SPCL_DTL_REMARK,s.LEDGER_ID,s.SPCL_TECH_FLAG"+
//	   " from BASE_PRODUCT p left join  DRP_SPCL_TECH s    "+
//	   " on p.PRD_NO=s.PRD_NO where  p.STATE='启用' and  s.PRD_NO not like '%-%' and p.DEL_FLAG=0"+
//	   " and FINAL_NODE_FLAG=1 and s.TECH_NO_EDIT_FLAG=1 and s.LEDGER_ID='"+ZTXXID+"')temp  ";
 
	 $("#selectChangePrdParams").val(" ZTXXID = '"+ZTXXID+"' "+tempParam);
	selCommon("BS_166", false, "CHANGED_PRD_ID"+rownum, "PRD_ID", "forms[0]","CHANGED_PRD_NO"+rownum+",CHANGED_PRD_NAME"+rownum+",CHANGED_SPCL_TECH_ID"+rownum+",CHANGED_DM_SPCL_TECH_NO"+rownum+",CHANGED_SPCL_TECH_FLAG"+rownum, "PRD_NO,PRD_NAME,SPCL_TECH_ID,DM_SPCL_TECH_NO,SPCL_TECH_FLAG","selectChangePrdParams");
	var SPCL_TECH_FLAG = $("#CHANGED_SPCL_TECH_FLAG"+rownum).val();
	var CHANGED_SPCL_TECH_ID = $("#CHANGED_SPCL_TECH_ID"+rownum).val();
	var SPCL_TECH = "";
	if(null == CHANGED_SPCL_TECH_ID || "" == CHANGED_SPCL_TECH_ID){
		SPCL_TECH = "<font style='color: black'>无</font><input class='btn'  value='编辑' onclick='showEditTechPage("+rownum+")'  type='button' />"
	}else{
        SPCL_TECH = "<font style='color: red'>有</font><input class='btn'  value='编辑' onclick='showEditTechPage("+rownum+")'  type='button' />"
     }
    $("#CHANGED_SPECIAL_FLAG"+rownum).html(SPCL_TECH);
    
    
}
//
//function selchangePrd(rownum){
//	var STORE_ID = $("#STORE_ID"+rownum).val();
//	if(null == STORE_ID || "" == STORE_ID){
//		parent.showErrorMsg("请选择'库房编号'");
//		return false;
//	}
//	var PAR_CHANGE_PRD_ID = $("#PAR_CHANGE_PRD_ID"+rownum).val();
//	if(null == PAR_CHANGE_PRD_ID || "" == PAR_CHANGE_PRD_ID){
//		parent.showErrorMsg("请选择'转换前货品编号'");
//		return false;
//	}
//	
//	var ZTXXID = $("#ZTXXID").val();
//	$("#selParam").val("  STORE_ID='"+STORE_ID+"' and  STOLEDGER='"+ZTXXID+"' and NVL(SAFE_NUM,0)>0 ");
//	selCommon("BS_130", false, "CHANGED_PRD_ID"+rownum, "PRD_ID", "forms[0]","CHANGED_PRD_NO"+rownum+",CHANGED_PRD_NAME"+rownum+",CHANGED_SPCL_TECH_ID"+rownum+",CHANGED_DM_SPCL_TECH_NO"+rownum+",CHANGED_SPCL_TECH_FLAG"+rownum, "PRD_NO,PRD_NAME,SPCL_TECH_ID,DM_SPCL_TECH_NO,SPCL_TECH_FLAG","selParam");
//	var SPCL_TECH_FLAG = $("#CHANGED_SPCL_TECH_FLAG"+rownum).val();
//	var CHANGED_SPCL_TECH_ID = $("#CHANGED_SPCL_TECH_ID"+rownum).val();
//	var SPCL_TECH = "";
//	if(null == CHANGED_SPCL_TECH_ID || "" == CHANGED_SPCL_TECH_ID){
//		SPCL_TECH = "<font style=''>无</font><input class='btn'  value='编辑' onclick='showEditTechPage("+rownum+")'  type='button' />"
//	}else{
//        SPCL_TECH = "<font style='color: red'>有</font><input class='btn'  value='编辑' onclick='showEditTechPage("+rownum+")'  type='button' />"
//     }
//    $("#CHANGED_SPECIAL_FLAG"+rownum).html(SPCL_TECH);
//    
//    
//}

//填写转储数量的 时候 验证
function checkDumpNum(rownum){
	var CHANGE_NUM = $("#CHANGE_NUM"+rownum).val();
	var SAFE_NUM = $("#SAFE_NUM"+rownum).val();
	// 校验整型内容
	if (!CheckIntegerInput($("#CHANGE_NUM"+rownum))) {
		return false;
	}
	SAFE_NUM = parseInt(SAFE_NUM);
	if(isNaN(SAFE_NUM)){
		SAFE_NUM = 0;
	}
	if(parseInt(CHANGE_NUM)>SAFE_NUM){
		parent.showErrorMsg("转换数量不能大于该库的库存量");
		return;
	}
	
	
}

//保存之前验证转储数量
function saveCheckDumpNum(){
	$("#jsontb tr:gt(0) td").css("background-color","#FFFFFF");
	var flag = true;
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var beforeV = "";
	checkBox.each(function(){
		var CHANGE_NUM = $(this).parent().parent().find("input[json='CHANGE_NUM']").val();
		var SAFE_NUM = $(this).parent().parent().find("input[name='SAFE_NUM']").val();
	    CHANGE_NUM = parseInt(CHANGE_NUM);
	    // 校验整型内容
		if (!CheckIntegerInput($(this).parent().parent().find("input[json='CHANGE_NUM']"))) {
			flag = false;
			return false;
		}
	    if(0 == CHANGE_NUM){
	    	parent.showErrorMsg("请输入'转换数量'");
			flag = false;
			return false;
	    }
	    SAFE_NUM = parseInt(SAFE_NUM);
		if(isNaN(SAFE_NUM)){
			SAFE_NUM = 0;
		}
		if(parseInt(CHANGE_NUM)>parseInt(SAFE_NUM)){
			parent.showErrorMsg("'转换数量'不能大于该库的库存量");
			flag = false;
			return false;
		}
		
		var CHANGED_SPCL_TECH_ID = $(this).parent().parent().find("input[json='CHANGED_SPCL_TECH_ID']").val();
//		if(null == CHANGED_SPCL_TECH_ID || "" == CHANGED_SPCL_TECH_ID){
//			parent.showErrorMsg("请选择转换后货品的特殊工艺");
//			flag = false;
//			return false;
//		}
		var PAR_CHANGE_PRD_ID =  $(this).parent().parent().find("input[json='PAR_CHANGE_PRD_ID']").val();
	    var PAR_CHANGE_SPCL_TECH_ID =  $(this).parent().parent().find("input[json='PAR_CHANGE_SPCL_TECH_ID']").val();
	    var CHANGED_PRD_ID =  $(this).parent().parent().find("input[json='CHANGED_PRD_ID']").val();
	    var PAR_CHANGE_PRD_NO = $(this).parent().parent().find("input[json='PAR_CHANGE_PRD_NO']").val();
	    if((PAR_CHANGE_PRD_ID == CHANGED_PRD_ID) &&(null != CHANGED_SPCL_TECH_ID && "" !=CHANGED_SPCL_TECH_ID)&& (PAR_CHANGE_SPCL_TECH_ID == CHANGED_SPCL_TECH_ID)){
	    	$(this).parent().parent().find("td:lt(2)").css("background-color","red");
	    	parent.showErrorMsg("货品["+PAR_CHANGE_PRD_NO+"]转换前后的特殊工艺一样，请重新选择");
			flag = false;
			return false;
	    }
	    //#FFFFFF
	});
 	return flag;
}

//选择库房
function selectStore(rownum){
	var TERM_ID = $("#TERM_ID").val();
	var ZTXXID = $("#ZTXXID").val();
	var TERM_CHARGE = $("#TERM_CHARGE").val();
	var params = "";
	if(null != TERM_ID  && "" != TERM_ID){
		//终端用户选择转出库房只能选自己的
		params = " STATE ='启用' and DEL_FLAG=0 and  BEL_CHANN_ID='"+TERM_ID+"' ";
	}else{
		params = " STATE ='启用' and DEL_FLAG=0 and  (BEL_CHANN_ID='"+ZTXXID+"' or BEL_CHANN_ID in "+TERM_CHARGE+")";
	}
	
	$("#selectStoreParams").val(params);
	selCommon('BS_35', false, 'STORE_ID'+rownum, 'STORE_ID', 'forms[0]','STORE_NO'+rownum+',STORE_NAME'+rownum, 'STORE_NO,STORE_NAME', 'selectStoreParams')
}
 




function showEditTechPage(rownum){
	var PRD_ID = $("#CHANGED_PRD_ID"+rownum).val();
	if(null == PRD_ID || "" == PRD_ID){
		parent.showErrorMsg("请选择转换后货品");
		return;
	}
	var busID = $("#STATE_CHANGE_DTL_ID"+rownum).val();
	var CHANN_ID = $("#ZTXXID").val();
	var SPCL_TECH_ID = $("#CHANGED_SPCL_TECH_ID"+rownum).val();
	//不验证渠道折扣
	//var data = window.showModalDialog("techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_STOREIN_NOTICE_DTL&BUSSID="+busID+"&citeUrl=viewTechWithPrice&check=N",window,"dialogwidth=800px; dialogheight=600px; status=no");
	var data=window.showModalDialog("techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&citeUrl=editTechWithOutPrice&optOldFlag=1",window,"dialogwidth=800px; dialogheight=600px; status=no");
	//验证渠道折扣
	//var data=window.showModalDialog("techorderManager.shtml?action=editTechWithOutPrice&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_ADVC_ORDER_DTL&BUSSID="+ADVC_ORDER_DTL_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
	if(null == data){
		return;
	}
	if(data.SPCL_TECH_FLAG>0){
		$("#CHANGED_SPECIAL_FLAG"+rownum).html("<font style='color: red'>有</font><input class='btn'  value='编辑' onclick='showEditTechPage("+rownum+")'  type='button' />");
	}else{
		$("#CHANGED_SPECIAL_FLAG"+rownum).html("无");
	}
	$("#CHANGED_SPCL_TECH_ID"+rownum).val(data.SPCL_TECH_ID);
	$("#CHANGED_SPCL_TECH_FLAG"+rownum).val(data.SPCL_TECH_FLAG);
	$("#CHANGED_DM_SPCL_TECH_NO"+rownum).val(data.DM_SPCL_TECH_NO);
}


 

  //特殊工艺
function showTechPage(SPCL_TECH_ID){
	var data = window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}

//清除字表的数据
function clrarAllChild(){
	$("#jsontb :checkbox").attr("checked","true");
//	multiRecDeletes(1);
//	$("#jsontb tr:gt(0)").remove();
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	checkBox.parent().parent().remove();
}



