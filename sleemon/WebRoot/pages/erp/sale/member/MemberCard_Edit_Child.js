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

 
 
 function btuMxRest(){
	 btnReset(["delete","save","add"]);
 }
 
 
 
//子表保存
function childSave(){
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "member.shtml?action=memberCardEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"MEMBER_ID":selRowId},
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
		url: "member.shtml?action=memberCardDelete",
		type:"POST",
		dataType:"text",
		data:{"MEMBER_ACT_DTL_IDS":ids},
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
		 parent.window.gotoBottomPage("label_3");
	}else{
		newGoBack("member.shtml?action=toFrame");
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
		    .append("<td nowrap align='center'><input type='checkbox' style='valign:middle' json='MEMBER_CARD_DTL_ID' name='MEMBER_CARD_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
		    .append("<td nowrap align='left'><input type='text'   json='MARKETING_ACT_NAME' name='MARKETING_ACT_NAME"+rownum+"' readonly  value='"+arrs[7]+"'/></td>")
            .append('<td nowrap align="left" ><input type="hidden" json="MEMBER_ACT_DTL_ID" id="MEMBER_ACT_DTL_ID'+rownum+'" name="MEMBER_ACT_DTL_ID'+rownum+'"  label="会员参加活动表ID" maxlength="32" value="'+arrs[1]+'"/>' +
            '<input type="hidden" json="MARKETING_CARD_ID" id="MARKETING_CARD_ID'+rownum+'" name="MARKETING_CARD_ID'+rownum+'"  label="卡券ID" maxlength="32" value="'+arrs[2]+'"/>'+
            '<input  json="MARKETING_CARD_NO" id="MARKETING_CARD_NO'+rownum+'" name="MARKETING_CARD_NO'+rownum+'"  autocheck="true" label="营销卡卷编号"  type="text" size="16"  inputtype="string"   readonly  mustinput="true"   maxlength="30"  value="'+arrs[3]+'"/>&nbsp;' +
            '<img id="img'+rownum+'" align="absmiddl" style="cursor: hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onClick="selcCard('+rownum+')"/></td>')
            .append('<td nowrap align="left"><input  json="CARD_TYPE" id="CARD_TYPE'+rownum+'" name="CARD_TYPE'+rownum+'"  autocheck="true" label="卡券类型"  size="16" type="text"   inputtype="string"  readonly   mustinput="true"   maxlength="100" value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CARD_VALUE" id="CARD_VALUE'+rownum+'" name="CARD_VALUE'+rownum+'"  autocheck="true" label="卡券值" size="16"  type="text"   inputtype="string"  readonly       maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CRE_TIME" id="CRE_TIME'+rownum+'" name="CRE_TIME'+rownum+'"  autocheck="true" label="发放时间" size="16" type="text"   inputtype="string" readonly     mustinput="true"     maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            //.append('<td nowrap align="left"><input  json="STATE" id="STATE'+rownum+'" name="STATE"    type="text"  value="'+arrs[6]+'"/></td>')
            ;
	 
	 
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
}


function selcCard(rownum){
	var checkBox = $("#jsontb tr:gt(0) input:checkbox");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		var dtlid = $(this).val();
		if(null == dtlid || "" == dtlid){
			var id = $(this).parent().parent().find("input[json='MARKETING_CARD_ID']").val();
			if(null !=id && "" != id){
			   ids = ids+"'"+id+"',";
			}
		}
		
	});
	ids = ids.substr(0,ids.length-1);
	var CARD_IDS = $("#CARD_IDS").val();
	if("" != ids){
		if(null != CARD_IDS && "" != CARD_IDS){
			CARD_IDS = CARD_IDS+","+ids;
		}else{
			CARD_IDS = ids;
		}
	}
	
	var MEMBER_ID = getSelRowId();
	var ACT_IDS = $("#ACT_IDS").val();
	var params = " MEMBER_ID='"+MEMBER_ID+"' ";
	if(null != ACT_IDS && "" != ACT_IDS){
		params = params+" and MARKETING_ACT_ID in("+ACT_IDS+")";
	}
	
	if(null != CARD_IDS && "" != CARD_IDS){
		params = params + " and MARKETING_CARD_ID not in("+CARD_IDS+")";
	}
	 
	$("#selectParams").val(params);
    var rtnArr =  selCommon("BS_150", false, "MARKETING_CARD_ID"+rownum, "MARKETING_CARD_ID", "forms[0]",
    	"MARKETING_CARD_NO"+rownum+",CARD_TYPE"+rownum+",CARD_VALUE"+rownum+",CRE_TIME"+rownum+",MEMBER_ACT_DTL_ID"+rownum+",MARKETING_ACT_NAME"+rownum, 
    	"MARKETING_CARD_NO,CARD_TYPE,CARD_VALUE,CRE_TIME,MEMBER_ACT_DTL_ID,MARKETING_ACT_NAME", "selectParams");
  //  multiSelCommonSet(rtnArr,"MARKETING_CARD_ID,MARKETING_CARD_NO,CARD_TYPE,CARD_VALUE,CRE_TIME",rownum);
 
}
 
 
