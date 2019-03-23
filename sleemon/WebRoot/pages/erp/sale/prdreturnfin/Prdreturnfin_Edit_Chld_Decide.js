/**
 * @prjName:喜临门营销平台
 * @fileName:Prdreturn_Edit_Chld
 * @author wzg
 * @time   2013-08-19 15:33:31 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //页面初始化
    init();
	$("#save").click(function(){	
		//明细校验
		if(!formMutiTrChecked()){
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
//子表保存
function childSave(rowNum){
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "prdreturnfin.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

function getSelRowId(){
	return parent.document.getElementById("selRowId").value;
}
// 固定的一些共通的方法 end
// 下面这个方法要自己修改
//表格增加一行
function addRow(arrs){
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	var SPCL_TECH_FLAG;
	if(arrs[24]==null||arrs[24]==""||arrs[24]=="0"){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input class='btn' type='button' value='查看' onclick='url("+rownum+")' />"
	}
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='PRD_RETURN_DTL_ID' name='PRD_RETURN_DTL_ID"+rownum+"' value='"+arrs[0]+"' rowNum ='"+rownum+"'/></td>")
             .append('<td nowrap align="center">'+arrs[20]+'&nbsp;</td>')
            .append('<td nowrap align="center">'+arrs[2]+'&nbsp;</td>')
            .append('<td nowrap align="left">'+arrs[3]+'&nbsp;</td>')
            .append('<td nowrap align="left">'+arrs[4]+'&nbsp;</td>')
            .append('<td nowrap align="left">'+arrs[5]+'&nbsp;</td>')
            .append('<td nowrap align="left">'+arrs[6]+'&nbsp;</td>')
            .append('<td nowrap align="center">'+arrs[7]+'&nbsp;</td>')
 			.append('<td nowrap align="center">'+SPCL_TECH_FLAG+'<input id="spcl'+rownum+'" type="hidden" value="'+arrs[23]+'" /></td>')
            .append('<td nowrap align="right">'+arrs[8]+'&nbsp;</td>')
            .append('<td nowrap align="right">'+arrs[9]+'&nbsp;</td>')
//            .append('<td nowrap align="left"><input  json="RETURN_AMOUNT" id="RETURN_AMOUNT'+rownum+'" name="RETURN_AMOUNT'+rownum+'"  autocheck="true" label="退货金额"   type="text"  valueType="12,2"  inputtype="float"       value="'+arrs[10]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left">'+arrs[10]+'&nbsp;</td>')
            .append('<td nowrap align="center">'+arrs[11]+'&nbsp;</td>')
            .append('<td nowrap align="left">'+arrs[12]+'&nbsp;</td>')
            .append('<td nowrap align="left"><input type="hidden" id ="RETURN_ATT'+rownum+'" value="'+arrs[13]+'" />&nbsp;</td>')
            .append('<td nowrap align="left">'+arrs[14]+'&nbsp;</td>')
            .append('<td nowrap align="right">'+arrs[15]+'&nbsp;</td>')
            .append('<td nowrap align="right">'+arrs[16]+'&nbsp;</td>')
            .append('<td nowrap align="left">'+arrs[17]+'&nbsp;</td>')
            .append('<td nowrap align="left">'+arrs[19]+'&nbsp;</td>')
            .append('<td nowrap align="left">'+arrs[21]+'&nbsp;</td>')
            .append('<td nowrap align="center">'+arrs[22]+'&nbsp;</td>')
            ;
		displayDownFile('RETURN_ATT'+rownum,'SAMPLE_DIR',true,false);
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}
function url(id){
	var SPCL_TECH_ID=$("#spcl"+id).val();
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}