/**
 * @prjName:喜临门营销平台
 * @fileName:Store_Edit_Chld
 * @author wdb
 * @time   2013-08-13 14:01:22 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
	$("#add").click(function(){
	    addRow();
	});
	$("#check").click(function(){
		window.open("advise.shtml?action=toFrame&CMPL_ADVS_TYPE=1","反馈查询","height=600, width=900, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
	});
	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		//页面新增的，数据库中无记录，可以直接remove
		checkBox.parent().parent().remove();
		//删除后scroll可能消失，因此需要重新调整浮动按钮的位置
		setFloatPostion();
	});
	
	$("#save").click(function(){
		allSave();
	});
	
	$("#allChecked").click(function(){
		var flag = document.getElementById("allChecked").checked;
		if(flag){
			$("#jsontb :checkbox").attr("checked","true");
		}else{
			$("#jsontb :checkbox").removeAttr("checked");
		}
	});
	
	//form校验设置
	InitFormValidator(0);
});

//主子表保存
function allSave(){
	//主表form校验
	var parentCheckedRst =formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
	var ADVS_SOURCE=$("#ADVS_SOURCE").val();
	if(""==ADVS_SOURCE||null==ADVS_SOURCE){
		parent.showErrorMsg("请选择投诉来源！")
		return;
	}
	if("消费者"==ADVS_SOURCE){
		var CUSTOMER_NAME=$("#CUSTOMER_NAME").val();
		if(""==CUSTOMER_NAME||null==CUSTOMER_NAME){
			parent.showErrorMsg("请输入消费者姓名！")
			return;
		}
		var CUSTOMER_TEL=$("#CUSTOMER_TEL").val();
		if(""==CUSTOMER_TEL||null==CUSTOMER_TEL){
			parent.showErrorMsg("请输入消费者电话！")
			return;
		}
	}
	//获取json数据
	var parentData = parent.bottomcontent.siglePackJsonData('maintable');
	var childData;
	
	if($("#jsontb tr:gt(0) input:checked").length>0){
		childData = multiPackJsonData();
	}
	if(childData == null){
		parent.showErrorMsg("货品明细不能为空！")
		return;
	}
	//对于选中的明细校验
	if(!formMutiTrChecked()){
		return;
	}
	
	if(!checkChildSelect()){
		return;
	}
	
	$.ajax({
		url: "adviseinit.shtml?action=saveProCmpl",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","adviseinit.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//货品通用选取
function sel(rownum){
	selCommon("BS_21", false, "PRD_ID"+rownum, "PRD_ID", "forms[1]","PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum, "PRD_NO,PRD_NAME,PRD_SPEC","selectPrdParams");
}

//表格增加一行
function addRow(){
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' id='"+rownum+"' style='height:12px;valign:middle'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  autocheck="true" label="货品ID"  type="hidden" inputtype="string"/>' +
            '<input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号" size="10"  type="text" mustinput="true" inputtype="string" READONLY maxlength="30"/><img align="absmiddle" name="selAREA" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="sel('+rownum+')">&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称" READONLY mustinput="true" type="text" inputtype="string" maxlength="100"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号" READONLY mustinput="true" type="text" inputtype="string" maxlength="50"/>&nbsp;</td>')
            .append('<td nowrap align="left"><select json="PRD_TYPE" id="PRD_TYPE'+rownum+'" name="PRD_TYPE" autocheck="true" label="产品类型" mustinput="true" inputtype="string"></td>')
            .append('<td nowrap align="left"><select  json="PRD_PROBLEM_TYPE" mustinput="true" autocheck="true" label="产品问题类型" inputtype="string" id="PRD_PROBLEM_TYPE'+rownum+'" name="PRD_PROBLEM_TYPE'+rownum+'"></select></td>')
            .append('<td nowrap align="left" ><input  json="USE_TIME" id="USE_TIME'+rownum+'" name="USE_TIME" size="5"  autocheck="true" label="消费者使用时间" mustinput="true" type="text" inputtype="float"  valueType="8,2"  maxlength="50"/>&nbsp;(天)</td>')
            .append('<td nowrap align="left"  style="display:none"><input  json="SHIP_POINT_ID" id="SHIP_POINT_ID'+rownum+'" name="SHIP_POINT_ID"  autocheck="true" label="生产基地ID"  type="hidden" />&nbsp;</td>')
            .append('<td nowrap align="left"  style="display:none"><input  json="SHIP_POINT_NO" id="SHIP_POINT_NO'+rownum+'" name="SHIP_POINT_NO"  autocheck="true" label="生产基地NO"  type="hidden" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="SHIP_POINT_NAME" id="SHIP_POINT_NAME'+rownum+'" name="SHIP_POINT_NAME"  autocheck="true" label="生产基地" READONLY mustinput="true" type="text" inputtype="string" />&nbsp;' +
            "<img  align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcShip("+rownum+")'/></td>")
            .append('<td nowrap align="left"><input  json="PRDC_DATE" id="PRDC_DATE'+rownum+'" name="PRDC_DATE"  autocheck="true" label="生产日期" size="10" type="text" inputtype="date" mustinput="true" onclick="SelectDate();" readonly maxlength="15"/>' +
            '<img align="absmiddle" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="SelectDate(PRDC_DATE'+rownum+');">&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BUY_DATE" id="BUY_DATE'+rownum+'" name="BUY_DATE"  autocheck="true" label="购买日期" size="10" type="text" inputtype="date" mustinput="true" onclick="SelectDate();" readonly maxlength="15"/>' +
            '<img align="absmiddle" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="SelectDate(BUY_DATE'+rownum+');">&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NUM" id="PRD_NUM'+rownum+'" name="PRD_NUM'+rownum+'"  autocheck="true" label="产品数量" mustinput="true"  inputtype="float"  valueType="8,2"   type="text" inputtype="string" maxlength="100"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_ATT" id="PRD_ATT'+rownum+'" name="PRD_ATT'+rownum+'"  autocheck="true" label="附件"  type="text" inputtype="string" maxlength="100"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="备注说明"  type="text" inputtype="string" maxlength="500"/>&nbsp;</td>')
            ;
	SelDictShow("PRD_PROBLEM_TYPE" + rownum, "BS_17", "", "");	
	SelDictShow("PRD_TYPE" + rownum, "BS_172", "", "");	
	uploadFile("PRD_ATT"+rownum, "SAMPLE_DIR", true);
	
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
}

//表单验证
function formCheckedEx() {
	var flag = true;
//	if($("#TEL").val()!=null && $("#TEL").val() != ""){
//	        var re1 = new RegExp(/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/);//电话匹配
//	        var re2 = new RegExp(/^(13[\d]{9}|15[\d]{9}|18[\d]{9})$/);//手机匹配
//	        var TEL1 = re1.test($("#TEL").val());
//	        var TEL2 = re2.test($("#TEL").val());
//	        if(!TEL1&&!TEL2 ){
//	      	   parent.showErrorMsg(utf8Decode("电话格式输入不正确！"));
//	      	   var flag = false;
//	           return false;
//	        }
//		}
	return flag;
}


function checkChildSelect(){
	var flag = true;
	var checkBox = $("#jsontb tr:gt(0) input:checked");
    checkBox.each(function(){
		var id = $(this).attr("id");
		var PRD_PROBLEM_TYPE = $("#PRD_PROBLEM_TYPE"+id).val();
		if (PRD_PROBLEM_TYPE == ""){
			chkCanErrMsg("", "请选择'产品问题类型'");
			flag = false;
			return false;
		}
		
		//附件上传 不能查看附件问题 是文件路径有问题
		var filename = $("#PRD_ATT"+id).val();
		var i = filename.indexOf("/");
		var newname = "";
		if(-1 == i ||"-1"==i){
			newname = filename.substring(0,4)+"/"+
			filename.substring(4,9)+"/"+filename.substring(9,filename.length);
			$("#PRD_ATT"+id).val(newname);
		}
//		if ($("#PRD_ATT"+id).val().length >50){
//			parent.showErrorMsg("附件名称过长！");
//			flag = false;
//		}
	});
    
    return flag;
}
function selcShip(rownum){
	selCommon("BS_22", false, "SHIP_POINT_ID"+rownum, "SHIP_POINT_ID", "forms[1]","SHIP_POINT_ID"+rownum+",SHIP_POINT_NO"+rownum+",SHIP_POINT_NAME"+rownum,
		"SHIP_POINT_ID,SHIP_POINT_NO,SHIP_POINT_NAME", "selectParams");
}
function checkSelTel(){
	var ADVS_SOURCE=$("#ADVS_SOURCE").val();
	if(""==ADVS_SOURCE||null==ADVS_SOURCE||"加盟商"==ADVS_SOURCE){
		$("#checkTr").find("span").hide();
	}else{
		$("#checkTr").find("span").show();
	}
}

