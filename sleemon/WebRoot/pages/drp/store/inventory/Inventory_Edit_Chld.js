/**
 * @prjName:喜临门营销平台
 * @fileName:Inventory_Edit_Chld
 * @author lyg
 * @time   2013-09-07 13:51:04 
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
	addRow();
	$("#delete").click(function(){
		//查找当前是否有选中的记录
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		if(checkBox.length<1){
			parent.showErrorMsg("请至少选择一条记录");
			return;
		}
		//获取所有选中的记录
		var ids = "";
		var option=true;
		checkBox.each(function(){
			if("" != $(this).val()){
				ids = ids+"'"+$(this).val()+"',";
			}
			//获取INS_FLAG(手工新增标记)为0时可以删除，为1时不能删除
			var inputs = $(this).parent().parent().find(":input[name='INS_FLAG']");
			inputs.each(function(){
				var INS_FLAG = $(this).val();
				if(INS_FLAG=="1"){
					alert("该数据为非手工新增，不得删除");
					option=false;
					return;
				}
			});
			if(!option){
				return false;
			}
		});
		if(!option){
			return;
		}
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
			if(!checkNum()){
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
		url: "inventory.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"PRD_INV_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","inventory.shtml?action=toFrame");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//子表保存
function childSave(){
	parent.showWaitPanel();
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "inventory.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"PRD_INV_ID":selRowId},
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
	closeWindow();
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
		url: "inventory.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"PRD_INV_DTL_IDS":ids},
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
   newGoBack("inventory.shtml?action=toFrame");
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
	        ];
	}
	var INS_FLAG=0;//新增手工标记
	//arrs[16]='${INS_FLAG}' 从盘点表里查询的手工新增标记，如果为空，说明是新增的，再判断是页面新增还是库位数据库带出来的
	if(arrs[16]==null||arrs[16]==""){
		if(arrs[15]=="1"){
		INS_FLAG=arrs[15];
		}
	}else{
		INS_FLAG=arrs[16];
	}
	//样式行
	var rownum = $("#jsontb tr").length;
	var SPCL_TECH_FLAG;
	if(arrs[18]==null||arrs[18]==""){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<font style='color: red'>有</font>"
	}
	var selectSpectStyle = "";
	
	var ACCT_NUM=arrs[13];
	if(""==ACCT_NUM||null==ACCT_NUM){
		ACCT_NUM=0;
	}
	var INV_NUM;
	if(""==arrs[4]||null==arrs[4]){
		INV_NUM=ACCT_NUM;
	}else{
		INV_NUM=arrs[4];
	}
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='PRD_INV_DTL_ID' name='PRD_INV_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
             .append('<input type="hidden" json="PRD_INV_ID" id="PRD_INV_ID'+rownum+'" name="PRD_INV_ID'+rownum+'"  label="盘点单ID" maxlength="32" value="'+arrs[3]+'"/>')
//            .append('<input  json="STORG_ID" id="STORG_ID'+rownum+'" name="STORG_ID" json="STORG_ID" autocheck="true" label="库位信息ID" type="hidden"    inputtype="string"        maxlength="32"  value="'+arrs[1]+'"/>')
//            .append('<td nowrap align="left" name="STORG_NO_td"><input  json="STORG_NO" id="STORG_NO'+rownum+'" name="STORG_NO'+rownum+'"  autocheck="true" label="库位编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="'+arrs[5]+'"/>&nbsp;' +
//             "<img align='absmiddle' id='imgStorg"+rownum+"' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcStorg("+rownum+")'/></td>")
//            .append('<td nowrap align="left"  name="STORG_NAME_td"><input  json="STORG_NAME" id="STORG_NAME'+rownum+'" name="STORG_NAME'+rownum+'"  autocheck="true" label="库位名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'" json="PRD_ID"  autocheck="true" label="货品ID" type="hidden"    inputtype="string"        maxlength="32"  value="'+arrs[2]+'"/>')
            .append('<td nowrap align="center"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="30"  value="'+arrs[7]+'"/>&nbsp;' +
             "<img align='absmiddle' id='imgPrd"+rownum+"' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcPrd("+rownum+")'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left" style="display:none"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="text"   inputtype="string"   readonly       maxlength="50"  value="'+arrs[10]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text"   inputtype="string"   mustinput="true" readonly       maxlength="50"  value="'+arrs[11]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[12]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><span id="SPECIAL_FLAG'+rownum+'">'+SPCL_TECH_FLAG+'</span> <span id="" style="'+selectSpectStyle+'"><input class="btn"  value="编辑" onclick="url('+rownum+')"  type="button" /></span>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ACCT_NUM" id="ACCT_NUM'+rownum+'" name="ACCT_NUM'+rownum+'"  autocheck="true" label="账面数量"  type="text"   inputtype="string"  readonly   mustinput="true"      value="'+ACCT_NUM+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="INV_NUM" id="INV_NUM'+rownum+'" name="INV_NUM"  autocheck="true" onkeyup="countDIFF_NUM('+rownum+')" label="盘点数量"  type="text"       mustinput="true"    value="'+INV_NUM+'" /><input mustinput="true" inputtype="string" name="mustFlag"  style="width: 0px;"  type="text" >&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DIFF_NUM" id="DIFF_NUM'+rownum+'" name="DIFF_NUM'+rownum+'"  autocheck="true" label="差异数量"  type="text"  inputtype="string"  readonly   mustinput="true"    value="'+arrs[14]+'" />&nbsp;</td>')
            .append('<input type="hidden" json="INS_FLAG" id="INS_FLAG'+rownum+'" name="INS_FLAG"  label="手工新增标识列"  value="'+INS_FLAG+'"/>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[17]+'"/>')
              ;
	var imgStorg=$("#imgStorg"+rownum);
	var imgPrd=$("#imgPrd"+rownum);
	if("1"!=INS_FLAG){
		imgStorg.css("display","black");
		imgPrd.css("display","black");
	}else{
		imgStorg.css("display","none");
		imgPrd.css("display","none");
	}
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
//    hideStorg();
    countDIFF_NUM(rownum);
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}
function selcPrd(i){
	     var obj=selCommon("BS_21", true, "PRD_ID"+i, "PRD_ID", "forms[0]","PRD_ID"+i+",PRD_NO"+i+",PRD_NAME"+i+",PRD_SPEC"+i+",PRD_COLOR"+i+",BRAND"+i+",STD_UNIT"+i, "PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT", "selectParams");
	     rtnArr=multiSelCommonSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT",i);
	}
function selcStorg(i){
		 var selRow=getSelRowId();
		 var STORE_ID_edit=parent.topcontent.getSTORE_ID();
		 var STORE_ID_list=parent.topcontent.getSTORE_ID(selRow);
		 var STORG_ID_edit=parent.topcontent.getSTORG_ID();
		 var STORG_ID_list=parent.topcontent.getSTORG_ID(selRow);
		 var STORE_ID="";
		 var STORG_IDarr="";
		 //库房
		 if((STORE_ID_edit==""||STORE_ID_edit==null)&&(STORE_ID_list==""||STORE_ID_list==null)){
			 alert("请选择库房编号");
			 return;
		 }else if(STORE_ID_edit!=null&&STORE_ID_edit!=""){
			 STORE_ID=STORE_ID_edit;
		 }else if(STORE_ID_list!=null&&STORE_ID_list!=""){
			 STORE_ID=STORE_ID_list;
		 }
		 //库位
		 if((STORG_ID_edit==""||STORG_ID_edit==null)&&(STORG_ID_list==""||STORG_ID_list==null)){
			 alert("请选择库位编号");
			 return;
		 }else if(STORG_ID_edit!=null&&STORG_ID_edit!=""){
			 STORG_IDarr=STORG_ID_edit;
		 }else if(STORG_ID_list!=null&&STORG_ID_list!=""){
			 STORG_IDarr=STORG_ID_list;
		 }
		 var STORG_ID=STORG_IDarr.split(",");
		 var STORG_IDS="";
		 for(var i=0;i<STORG_ID.length;i++){
			 STORG_IDS=STORG_IDS+"'"+STORG_ID[i]+"',";
		 }
		 STORG_IDS = STORG_IDS.substr(0,STORG_IDS.length-1);
		 $("#selectParam").val("STATE in ('启用','停用') and DEL_FLAG='0' and STORE_ID='"+STORE_ID+"' and STORG_ID in ("+STORG_IDS+")");
	     selCommon("BS_42", false, "STORG_ID"+i, "STORG_ID", 'forms[0]',"STORG_ID"+i+",STORG_NO"+i+",STORG_NAME"+i,"STORG_ID,STORG_NO,STORG_NAME", "selectParam");
	}
//获取上贞的盘点范围，只有盘点范围为库房盘点的时候 才不隐藏明细的库位编号和名称
function hideStorg(){
	var INV_RANGE=parent.topcontent.getINV_RANGE();
	var STORG_NO=$("#STORG_NO");
	var STORG_NAME=$("#STORG_NAME");
	if(INV_RANGE!="库位盘点"){
		STORG_NO.hide();
		STORG_NAME.hide();
		$("#jsontb tr td[name='STORG_NO_td']").each(function(){
			$(this).hide().find(":input").attr("mustinput","false");
		})
		$("#jsontb tr td[name='STORG_NAME_td']").each(function(){
			$(this).hide().find(":input").attr("mustinput","false");
		})
		
	}else{
		STORG_NO.show();
		STORG_NAME.show();
		$("#jsontb tr td[name='STORG_NO_td']").each(function(){
			$(this).show().find(":input").attr("mustinput","true");
		})
		$("#jsontb tr td[name='STORG_NAME_td']").each(function(){
			$(this).show().find(":input").attr("mustinput","true");
		})
	}
}
//键盘按下修改盘点数量事件
function countDIFF_NUM(i){
	var temp_INV_NUM=$("#INV_NUM"+i).val();//盘点数量
	if(""==temp_INV_NUM||null==temp_INV_NUM){
		temp_INV_NUM=0;
	}
	var INV_NUM=isNaN(temp_INV_NUM)?0:parseFloat(temp_INV_NUM);
	var temp_ACCT_NUM=$("#ACCT_NUM"+i).val();//账面数量
	var ACCT_NUM=isNaN(temp_ACCT_NUM)?0:parseFloat(temp_ACCT_NUM);
	var DIFF_NUM = Math.round((isNaN(INV_NUM-ACCT_NUM)?0:INV_NUM-ACCT_NUM)*100)/100;
	$("#ACCT_NUM"+i).val(ACCT_NUM);
	$("#DIFF_NUM"+i).val(DIFF_NUM);
}

function url(rownum){
	var PRD_ID = $("#PRD_ID"+rownum).val();
	if(null == PRD_ID || "" == PRD_ID){
		parent.showErrorMsg("请先选择货品");
		return;
	}
	var CHANN_ID = $("#CHANN_ID").val();
	var SPCL_TECH_ID = $("#SPCL_TECH_ID"+rownum).val();
	var PRD_INV_DTL_ID = $("#PRD_INV_DTL_ID"+rownum).val();
	//不验证渠道折扣
	var data = window.showModalDialog("techorderManager.shtml?action=toFrame&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_PRD_INV_DTL&BUSSID="+PRD_INV_DTL_ID+"&citeUrl=editTechWithOutPrice&check=N",window,"dialogwidth=800px; dialogheight=600px; status=no");
	//验证渠道折扣
	//var data=window.showModalDialog("techorderManager.shtml?action=editTechWithOutPrice&PRD_ID="+PRD_ID+"&CHANN_ID="+CHANN_ID+"&SPCL_TECH_ID="+SPCL_TECH_ID+"&TABLE=DRP_ADVC_ORDER_DTL&BUSSID="+ADVC_ORDER_DTL_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
	if(null == data){
		return;
	}
	if(data.SPCL_TECH_FLAG>0){
		$("#SPECIAL_FLAG"+rownum).html("<span style='color: red'>有</span>");
	}else{
		$("#SPECIAL_FLAG"+rownum).html("无");
	}
	$("#SPCL_TECH_ID"+rownum).val(data.SPCL_TECH_ID);
	$("#SPCL_TECH_FLAG"+rownum).val(data.SPCL_TECH_FLAG);
}


//验证盘点数量为数字
function checkNum(){
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	var flag = true;
	checkBox.each(function(){
		var INV_NUM = $(this).parent().parent().find("input[name='INV_NUM']").val();//盘点数量
		if(""==INV_NUM||null==INV_NUM){
			parent.showErrorMsg("请输入盘点数量");
            flag=false;
			return false;
		}
		var re1 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
        if(!re1.test(INV_NUM)){
            parent.showErrorMsg("盘点数量最多可输入8位正整数");
            flag=false;
			return false;
        }
	});
	return flag;
}
