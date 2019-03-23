/**
 * @prjName:喜临门营销平台
 * @fileName: 
 * @author zzb
 * @time  2014-12-03 10:35:10 
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
	if(!checkchild()){
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
		url: "marketcardsale.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"CARD_SALE_ORDER_ID":selRowId},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","marketcardsale.shtml?action=toFrame");
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
	if(!checkchild()){
		btuMxRest();
		return;
	}
	var selRowId = getSelRowId();
	var jsonData = multiPackJsonData();
	$.ajax({
		url: "marketcardsale.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"CARD_SALE_ORDER_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
//				parent.window.gotoBottomPage("label_1");
				parent.topcontent.$("#pageForm").submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//删除操作
function multiRecDeletes(){
	var selRowId = getSelRowId();
    //查找当前是否有选中的记录
	var checkBox = $("#jsontb tr:gt(0) input:checked");
	//获取所有选中的记录
	var ids = "";
	checkBox.each(function(){
		ids = ids+"'"+$(this).val()+"',";
	});
	ids = ids.substr(0,ids.length-1);
	 
	$.ajax({
		url: "marketcardsale.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"CARD_SALE_ORDER_DTL_IDS":ids,"CARD_SALE_ORDER_ID":selRowId},
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
   newGoBack("marketcardsale.shtml?action=toFrame");
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
              ''
	        ];
		}
 
 
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='valign:middle' json='CARD_SALE_ORDER_DTL_ID'  name='CARD_SALE_ORDER_DTL_ID"+rownum+"' value='"+arrs[0]+"'/></td>")
            .append('<td nowrap align="left"><input type="hidden" id="MARKETING_CARD_ID'+rownum+'" json="MARKETING_CARD_ID"  value="'+arrs[1]+'" statevalue="'+arrs[15]+'"/><input  json="MARKETING_CARD_NO" id="MARKETING_CARD_NO'+rownum+'" name="MARKETING_CARD_NO'+rownum+'"  autocheck="true" label="卡券编号"  readonly type="text" size="16"  inputtype="string" mustinput="true"   maxlength="30"  value="'+arrs[2]+'"/>&nbsp;' +
             "<img name='imgTab' align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcCard("+rownum+")'/></td>")
            .append('<td nowrap align="left"><input  json="CARD_TYPE" id="CARD_TYPE'+rownum+'" name="CARD_TYPE'+rownum+'"  autocheck="true" label="卡券类型"  size="8" type="text"  readonly inputtype="string"     mustinput="true"   maxlength="30" value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CARD_VALUE" id="CARD_VALUE'+rownum+'" name="CARD_VALUE'+rownum+'"  autocheck="true" label="卡券面值" size="8"  type="text"  readonly inputtype="string"         maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CRE_TIME" id="CRE_TIME'+rownum+'" name="CRE_TIME'+rownum+'"    label="发放日期" size="8" type="text"   inputtype="string" readonly         value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STATE" id="STATE'+rownum+'" name="STATE'+rownum+'"  autocheck="true" label="状态" size="4" type="text"   inputtype="string" readonly          maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="CUST_NAME" id="CUST_NAME'+rownum+'" name="CUST_NAME'+rownum+'"  autocheck="true" label="客户名称" size="16" type="text"   inputtype="string"      mustinput="true"     maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="MOBILE_PHONE" id="MOBILE_PHONE'+rownum+'" name="MOBILE_PHONE"  autocheck="true" label="手机" size="16" type="text"   inputtype="s"      mustinput="true"    maxlength="50"  value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PAYABLE_AMOUNT" id="PAYABLE_AMOUNT'+rownum+'" name="PAYABLE_AMOUNT" mustinput="true" texttype="float" valueType="8,2" label="收款金额" size="5"  type="text" onkeyup="countAmount('+rownum+')"   value="'+arrs[8]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><select  json="SEX" id="SEX'+rownum+'" name="SEX'+rownum+'"   label="性别" style="width:55px;" ></select> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ADDRESS" id="ADDRESS'+rownum+'" name="ADDRESS'+rownum+'"  autocheck="true" label="住址" size="20" type="text"   inputtype="string"          maxlength="250"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="客户需求（意向购买）" size="30" type="text"   inputtype="string" mustinput="true"     maxlength="250"  value="'+arrs[14]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BIRTHDAY" id="BIRTHDAY'+rownum+'" name="BIRTHDAY'+rownum+'"  autocheck="true" label="生日" size="10" type="text"   inputtype="string"       onclick="SelectDate();"   maxlength="50"  value="'+arrs[11]+'"/>&nbsp;'+
            	'<img align="absmiddle" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/date_16x16.gif" onclick="SelectDate(BIRTHDAY'+rownum+');"></td>')
            .append('<td nowrap align="left"><input  json="HOBBY" id="HOBBY'+rownum+'" name="HOBBY'+rownum+'"  autocheck="true" label="爱好" size="16" type="text"   inputtype="string"         maxlength="50"  value="'+arrs[12]+'"/>&nbsp;</td>')
            

            ;
	 
	
	var SEX = arrs[10];
	if(null == SEX || "" == SEX){
		SEX = "男";
	}
	SelDictShow("SEX"+rownum, "BS_1", SEX, "");
	
	 
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
    trCheckFLoatInit($("#jsontb tr:gt(0) input"))
	trCheckInit($("#jsontb tr:gt(0) input"));
	trCheckInit($("#jsontb tr:gt(0) select"));
}

function selcCard(rownum){
	var CHANN_ID = parent.topcontent.getCHANN_ID();
	var MARKETING_ACT_ID = parent.topcontent.getMARKETING_ACT_ID();
	if(null == MARKETING_ACT_ID || "" == MARKETING_ACT_ID){
		parent.showErrorMsg("请先选择活动信息!");
		return;
	}
    var ids = "";
	var inputs = $("#jsontb tr:gt(0) input[json='MARKETING_CARD_ID']");
	inputs.each(function(){
		 var MARKETING_CARD_ID = $(this).val();
		 var statevalue = $(this).attr("statevalue");
		 if(null != MARKETING_CARD_ID && "" != MARKETING_CARD_ID && '关闭' != statevalue){
			  ids = ids+"'"+MARKETING_CARD_ID+"',";
		 }
	});
	ids = ids.substr(0,ids.length-1);
	var psql = "";
	if("" != ids){
		psql = " and c.MARKETING_CARD_ID not in("+ids+") " ;
	}
	
	var sql = "(select c.MARKETING_CARD_NO,"+
		       "c.CARD_TYPE,"+
		       "c.CARD_VALUE,"+
		       "a.MARKETING_ACT_NO,"+
		       "a.MARKETING_ACT_NAME,"+
		       "c.STATE,"+
		       "c.CARD_SEQ_NO,"+
		       "to_char(c.CRE_TIME, 'yyyy-MM-dd') CRE_TIME,"+
		       "c.MARKETING_CARD_ID,"+
		       "a.MARKETING_ACT_ID"+
		       " from ERP_MARKETING_CARD c"+
		       " left join ERP_MARKETING_ACT a"+
		       " on a.MARKETING_ACT_ID = c.MARKETING_ACT_ID"+
		       " where c.DEL_FLAG = 0"+
			   " and a.DEL_FLAG = 0"+
			   " and c.CHANN_ID='"+CHANN_ID+"'"+
			   " and c.MARKETING_ACT_ID = '"+MARKETING_ACT_ID+"'"+
			   " and c.STATE = '启用'"+
			   " and not exists"+
			   " (select 1  From ERP_CARD_SALE_ORDER_DTL d where d.DEL_FLAG = 0 and d.MARKETING_CARD_ID = c.MARKETING_CARD_ID and d.STATE !='关闭' ) "+
			   psql +
			   " order by c.MARKETING_CARD_NO ASC)";
	 
	 var obj = selCommon("BS_163", false, "MARKETING_CARD_ID"+rownum, "MARKETING_CARD_ID", "forms[0]","MARKETING_CARD_NO"+rownum+",CARD_TYPE"+rownum+",CARD_VALUE"+rownum+",CRE_TIME"+rownum+",STATE"+rownum, 
    	 				"MARKETING_CARD_NO,CARD_TYPE,CARD_VALUE,CRE_TIME,STATE", "","",sql);
}
 
 
function countAmount(){
	var actionType = getActionType();
	if("list" == actionType){
		return;
	} 
	var checkBox = $("#jsontb tr:gt(0) input[name='PAYABLE_AMOUNT']");
	var total = 0;
	var TOTAL_PAYABLE_AMOUNT = 0;
	var TOTAL_GIFT_AMOUNT = 0;
	checkBox.each(function(){
		var PAYABLE_AMOUNT = $(this).val();//收款金额
		total = total + Number(PAYABLE_AMOUNT); 
	});
	total = Math.round(total*100)/100;//计算订金金额，保留2位小数
	parent.topcontent.setSALE_CARD_AMOUNT(total); 
	
};


function getMemberInfo(rownum){
	var MOBILE_PHONE = $("#MOBILE_PHONE"+rownum).val();
	if(MOBILE_PHONE.legth <11){
		return;
	}
	 $.ajax({
		url: "marketcardsale.shtml?action=getMemberInfo",
		type:"POST",
		dataType:"text",
		data:{"MOBILE_PHONE":MOBILE_PHONE},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
			    var info = jsonResult.data;
			    if(null != info){
			    	$("#CUST_NAME"+rownum).val(info.MEMBER_NAME);
			    	$("#MOBILE_PHONE"+rownum).val(info.MOBILE_PHONE);
			    	$("#SEX"+rownum).val(info.SEX);
			    	$("#BIRTHDAY"+rownum).val(info.BIRTHDAY);
			    	$("#HOBBY"+rownum).val(info.HOBBY);
			    	$("#ADDRESS"+rownum).val(info.ADDRESS);
			    	$("#REMARK"+rownum).val(info.REMARK);
			    }
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
} 
 
 
function checkchild(){
	var trs = $("#jsontb tr:gt(0)");
	var flag = true;
	trs.each(function(){
		var isEdit = $(this).find("input[type='checkbox']:eq(0)").attr("checked");
		if(isEdit){
			var inputs = $(this).find("input[textType='float']");
			inputs.each(function(){
				var label = $(this).attr("label");
				var textType = $(this).attr("textType");
				if("float" == textType){
					// 校验浮点数内容
					if (!CheckFloatInput(this)) {
						flag = false;
						return false;
					}
				}
			});
		}
	});
	
	//页面查重
	if(flag){
		return dataColumnsValidation("jsontb",['MOBILE_PHONE'],"red");
	}else{
		return flag;
	}
	 
 
}

// add by zhxuw 对单行元素初始化
function trCheckFLoatInit(obj) {
	init_form = true;
	var el, name, val,tempMustinput,tempPttern;
	$(obj).each(function(i,k){
	    el = this;
		name = el.name;
		val = el.value;
		tempMustinput=$(el).attr("mustinput");
		tempPttern=$(el).attr("texttype");
		if (name) {
			if(el.readOnly){
				if("cmmx"!=el.className){
				   el.className = ' readonly '+el.className;
				}
			}
			if (tempPttern) {
				// 必输项
				if (tempMustinput && (tempMustinput == true||tempMustinput == 'true')) {
					if (el.mustinputSpan) {
					} else {
						el.insertAdjacentHTML("afterEnd", span_mustinput);
						el.mustinputSpan = true;
					}
				}
			}
		}
	
	});
	
	init_form = false;
}


