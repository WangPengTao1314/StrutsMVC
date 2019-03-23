/**
 * @prjName:喜临门营销平台
 * @fileName:Storeingrand_Edit_Chld
 * @author glw
 * @time   2013-08-22 09:22:01 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
	$("#add").click(function(){
	    addRow(dtlArr);
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
			parent.showConfirm("您确认要删除吗","multiRecDeletes();");
		}
		//删除后scroll可能消失，因此需要重新调整浮动按钮的位置
		setFloatPostion();
	});
	
//	
//	$("body").dblclick(function(){
//		var jsonData = getRealNum();
//		var temp = noticeNum;
//		var rows = eval('('+jsonData+')');
//		for (var i=0; i<rows.length; i++) {
//			temp = temp - parseInt(rows[i]['REAL_NUM']);
//		}
//		if (0 > temp) {
//			temp = 0;
//		}
//		arrs = ['', '', '', '', temp];
//        var tempArr = arrs.concat(arrsTemp);  
//        
//	    addRow(tempArr);
//	});
	
	$("#save").click(function(){
		//对于选中的明细校验
		if(!formMutiTrChecked()){
			return;
		}
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请选择要保存的记录");
			return;
		}
		if(!checkRealNum()){
			parent.showErrorMsg(utf8Decode("实际出库数量应该大于0!"));
			return;
		}
		childSave();
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


//孙表保存
function childSave(){
	var jsonData = multiPackJsonData();
	var storeoutId = document.getElementById("STOREOUT_ID").value;
	var storeoutDtlId = document.getElementById("STOREOUT_DTL_ID").value;
	var rows = eval('('+jsonData+')');
	$.ajax({
		url: "dbstoreout.shtml?action=storgChildEdit&STOREOUT_ID="+storeoutId+"&STOREOUT_DTL_ID="+storeoutDtlId,
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var sumRealNum =returnRealNum(rows);
				window.returnValue =returnRealNum(rows);
				showMsgPanel("保存成功");
				window.close();
			}else{
				showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function returnRealNum(rows){
	var inRealNum=0;
	for(var i=0;i<rows.length;i++){
		inRealNum +=parseInt(rows[i].REAL_NUM);
	}
	return inRealNum;
}

//返回
function gobacknew(){
   window.close();
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});
	//var actionType = parent.document.getElementById("actionType").value;
    $("#jsontb tr:gt(0) :checkbox").attr("checked","true");
	//form校验设置
	InitFormValidator(0);
}

// 固定的一些共通的方法 end
//表格增加一行
function addRow(arrs){
	if(null == arrs){
     arrs = [
	          '', '', '', '', '', '', '', '', '', '', '', '', '',''
	        ];
	}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
			 .append("<td><input type='checkbox'  json='STOREOUT_STORG_DTL_ID' name='STOREOUT_STORG_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'" label="货品ID"  type="hidden" value="'+arrs[3]+'"/>&nbsp;<input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号"  type="text"   inputtype="string" readonly maxlength="30"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string" readonly maxlength="100"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string" readonly maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="text"   inputtype="string" readonly maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text"   inputtype="string" readonly maxlength="50"  value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位"  type="text"   inputtype="string" readonly maxlength="50"  value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STORG_ID" id="STORG_ID'+rownum+'" name="STORG_ID'+rownum+'" label="库位ID"  type="hidden" value="'+arrs[10]+'"/>' +
            '<input  json="STORG_NO" id="STORG_NO'+rownum+'" name="STORG_NO'+rownum+'"  autocheck="true" label="库位编号"  type="text" mustinput="true"  inputtype="string" readonly maxlength="30"  value="'+arrs[11]+'"/>&nbsp;' +
            '<img align="absmiddle" name="selKWXX" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="javascript:selStorg('+rownum+')"/></td>')
            .append('<td nowrap align="left"><input  json="STORG_NAME" id="STORG_NAME'+rownum+'" name="STORG_NAME'+rownum+'"  autocheck="true" label="库位名称"  type="text"  mustinput="true" inputtype="string" readonly maxlength="50"  value="'+arrs[12]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input class="REAL_NUM"  json="REAL_NUM" id="REAL_NUM'+rownum+'" name="REAL_NUM'+rownum+'"  autocheck="true" label="实际出库数量"  type="text" inputtype="int"  maxlength="8"  value="'+arrs[13]+'"/>&nbsp;' +
            '<input  json="STOREOUT_DTL_ID" id="STOREOUT_DTL_ID'+rownum+'" name="STOREOUT_DTL_ID'+rownum+'" label="出库单明细ID"  type="hidden" value="'+arrs[1]+'"/>&nbsp;' +
            '<input  json="STOREOUT_ID" id="STOREOUT_ID'+rownum+'" name="STOREOUT_ID'+rownum+'" label="出库单ID"  type="hidden" value="'+arrs[2]+'"/>&nbsp;</td>')
              ;
			  
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
}


//库位通用选取
function selStorg(rownum){
	selCommon("BS_42", false, "STORG_ID"+rownum, "STORG_ID", "grendChild", "STORG_NO"+rownum+",STORG_NAME"+rownum, "STORG_NO,STORG_NAME","selectParam");
}


function multiRecDeletes(){
	var billType = document.getElementById("BILL_TYPE").value;
//查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	
	$.ajax({
		url: "dbstoreout.shtml?action=storegChildDelete&BILL_TYPE="+billType,
		type:"POST",
		dataType:"text",
		data:{"STOREOUT_STORG_DTL_IDS":ids},
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
