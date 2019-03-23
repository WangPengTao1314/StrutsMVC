/**
 * @prjName:喜临门营销平台
 * @fileName:Termreturn_Edit_Chld
 * @author lyg
 * @time   2013-08-19 14:35:52 
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
			//对于选中的零星领料单明细校验
			if(!formMutiTrChecked()){
				return;
			}
				childSave();
			
		}else{
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
		url: "costadjust.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"COST_ADJUST_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","costadjust.shtml?action=toFrame");
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
		url: "costadjust.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"COST_ADJUST_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("保存成功");
				window.parent.topcontent.pageForm.submit();
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
	var selRowId = parent.document.getElementById("selRowId").value;
	$.ajax({
		url: "costadjust.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"COST_ADJUST_DTL_IDS":ids,"COST_ADJUST_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				parent.showMsgPanel("删除成功");
				checkBox.parent().parent().remove();
				//设置删除操作。有删除时返回后需要刷新页面。否则直接跳转上一页。
				$("#delFlag").val("true");
//				window.parent.topcontent.pageForm.submit();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}
//返回
function gobacknew()
{
   newGoBack("costadjust.shtml?action=toFrame");
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
              '',
              '',
              '',
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	var SPCL_TECH_FLAG;
	if(arrs[9]==null||arrs[9]==""){
		SPCL_TECH_FLAG='无';
	}else{
		var onclick="url('"+arrs[8]+"')";
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input class='btn'   value='查看' onclick="+onclick+"  type='button' />"
	}
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' value='"+arrs[0]+"'/></td>")
            .append('<input type="hidden" json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  label="货品ID" maxlength="32" value="'+arrs[1]+'"/>')
            .append('<td nowrap align="left" name="sss"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO"  inputtype="string"  autocheck="true" size="8" label="货品编号" size="10"  type="text"  mustinput="true"   READONLY  value="'+arrs[2]+'"/>&nbsp;' +
            "<img align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selPrdNotStore("+rownum+")'/></td>")
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly    mustinput="true"     maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" size="3" label="标准单位"  type="text"   inputtype="string"   readonly      maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DM_SPCL_TECH_NO" id="DM_SPCL_TECH_NO'+rownum+'" name="DM_SPCL_TECH_NO"  autocheck="true" size="9" label="DM特殊工艺编号"  type="text"   inputtype="string"   readonly      maxlength="50"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><font name="SPECIAL_FLAG">'+SPCL_TECH_FLAG+'</font>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="NEW_COST_PRICE" id="NEW_COST_PRICE'+rownum+'" name="NEW_COST_PRICE"  autocheck="true" inputtype="zffloat"  size="5" label="调整后成本单价"  type="text"  mustinput="true"  valueType="8,2" value="'+arrs[11]+'" />&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK'+rownum+'"  autocheck="true" label="备注"  type="text"   inputtype="string"        maxlength="250"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<input   type="hidden" json="COST_ADJUST_DTL_ID" id="COST_ADJUST_DTL_ID'+rownum+'" name="COST_ADJUST_DTL_ID"   value="'+arrs[0]+'"/>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID"  label="特殊工艺单ID" type="hidden" value="'+arrs[8]+'"/>')
            .append('<input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG"  label="特殊工艺单标记" type="hidden" value="'+arrs[9]+'"/>')
            .append('<input  json="CUR_COST_PRICE" id="CUR_COST_PRICE'+rownum+'" name="CUR_COST_PRICE"  label="当前成本单价" type="hidden" value="'+arrs[10]+'"/>')
            .append('<input  json="ADJUST_AMOUNT" id="ADJUST_AMOUNT'+rownum+'" name="ADJUST_AMOUNT"  label="调整金额" type="hidden" value="'+arrs[6]+'"/>')
            .append('<input  json="CHECKONLY" id="CHECKONLY'+rownum+'" name="CHECKONLY"  label="唯一校验" type="hidden" value="'+arrs[12]+'"/>')
              ;
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
//	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
//	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}
//货品通用选取（根据库房id查询货品）
//    function selcPrd(rownum){
//    	var YEAR=parent.topcontent.getYEAR();
//    	var MONTH=parent.topcontent.getMONTH();
//    	var STORE_ID=parent.topcontent.getSTORE_ID();
//    	var CHANN_ID=$("#CHANN_ID").val();
//    	if(""==YEAR||null==YEAR){
//    		parent.showErrorMsg(utf8Decode("请选择年份！"));
//    		return ;
//    	}
//    	if(""==MONTH||null==MONTH){
//    		parent.showErrorMsg(utf8Decode("请选择月份！"));
//    		return ;
//    	}
//    	if(""==STORE_ID||null==STORE_ID){
//    		parent.showErrorMsg(utf8Decode("请选择库房！"));
//    		return ;
//    	}
//    	var sql="(" +
//    				"select a.PRD_ID,"+
//    				       "a.PRD_NO,"+
//    				       "a.PRD_NAME,"+
//    				       "a.PRD_SPEC,"+
//    				       "a.STD_UNIT,"+
//    				       "a.BRAND,"+
//    				       "a.PRD_COLOR,"+
//    				       "NVL(b.COST_PRICE,0)COST_PRICE,"+
//    				       "c.SPCL_TECH_FLAG "+
//    				"from BASE_PRODUCT a "+
//    				"left join DRP_COST_PRICE b on a.PRD_ID=b.PRD_ID and b.MONTH='"+MONTH+"' and YEAR='"+YEAR+"' and b.LEDGER_ID ='"+CHANN_ID+"' "+
//    				"left join DRP_SPCL_TECH c on b.SPCL_TECH_ID=c.SPCL_TECH_ID and c.USE_FLAG=1 "+
//    				"left join DRP_STORE_ACCT d on d.PRD_ID=a.PRD_ID and d.LEDGER_ID='"+CHANN_ID+"'"+
//    				"where a.DEL_FLAG=0 and a.STATE='启用' and a.FINAL_NODE_FLAG=1 "+
//    				" and (a.COMM_FLAG=1 or (a.COMM_FLAG=0 and a.LEDGER_ID='"+CHANN_ID+"')) and d.STORE_ID='"+STORE_ID+"' "+
// 	   			")";
//    	var obj=selCommon("BS_135", true, "PRD_ID"+rownum, "PRD_ID", "forms[0]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",STD_UNIT"+rownum+",ADJUST_AMOUNT"+rownum, 
//	    	 				"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,STD_UNIT,COST_PRICE", "","",sql);
//    	rtnArr=multiSelCommonSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,STD_UNIT,ADJUST_AMOUNT",rownum);
//    	var checkBox=$("input[type='checkbox']");
//		checkBox.each(function(){
//			var SPCL_TECH_FLAG=$(this).parent().parent().find("input[name='SPCL_TECH_FLAG']").val();
//			var SPCL_TECH_ID=$(this).parent().parent().find("input[name='SPCL_TECH_ID']").val();
//			if(SPCL_TECH_FLAG==null||SPCL_TECH_FLAG==""||SPCL_TECH_FLAG==0){
//				$(this).parent().parent().find("font[name='SPECIAL_FLAG']").html("无");
//			}else{
//				var onclick="url('"+SPCL_TECH_ID+"')";
//				$(this).parent().parent().find("font[name='SPECIAL_FLAG']").html("<span style='color: red'>有</span><input class='btn'  value='查看' onclick="+onclick+"  type='button' />");
//			}
//		})
//	}
    //货品通用选取（根据成本帐表查询）
    function selPrdNotStore(rownum){
    	var YEAR=parent.topcontent.getYEAR();
    	var MONTH=parent.topcontent.getMONTH();
    	var CHANN_ID=$("#CHANN_ID").val();
    	if(""==YEAR||null==YEAR){
    		parent.showErrorMsg(utf8Decode("请选择年份！"));
    		return ;
    	}
    	if(""==MONTH||null==MONTH){
    		parent.showErrorMsg(utf8Decode("请选择月份！"));
    		return ;
    	}
    	$("#selectParams").val(" YEAR='"+YEAR+"' and MONTH='"+MONTH+"' and LEDGER_ID='"+CHANN_ID+"' ");
    	var obj=selCommon("BS_135", true, "CHECKONLY"+rownum, "CHECKONLY", "forms[0]","PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",STD_UNIT"+rownum+",NEW_COST_PRICE"+rownum+",SPCL_TECH_FLAG"+rownum+",SPCL_TECH_ID"+rownum+",CUR_COST_PRICE"+rownum+",ADJUST_AMOUNT"+rownum+",CHECKONLY"+rownum+",DM_SPCL_TECH_NO"+rownum, 
	    	 				"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,STD_UNIT,COST_PRICE,SPCL_TECH_FLAG,SPCL_TECH_ID,COST_PRICE,ADJUST_AMOUNT,CHECKONLY,DM_SPCL_TECH_NO", "selectParams");
    	rtnArr=multiSelCommonSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,STD_UNIT,NEW_COST_PRICE,SPCL_TECH_FLAG,SPCL_TECH_ID,CUR_COST_PRICE,ADJUST_AMOUNT,CHECKONLY,DM_SPCL_TECH_NO",rownum);
    	var checkBox=$("input[type='checkbox']");
		checkBox.each(function(){
			var SPCL_TECH_FLAG=$(this).parent().parent().find("input[name='SPCL_TECH_FLAG']").val();
			var SPCL_TECH_ID=$(this).parent().parent().find("input[name='SPCL_TECH_ID']").val();
			if(SPCL_TECH_FLAG==null||SPCL_TECH_FLAG==""||SPCL_TECH_FLAG==0){
				$(this).parent().parent().find("font[name='SPECIAL_FLAG']").html("无");
			}else{
				var onclick="url('"+SPCL_TECH_ID+"')";
				$(this).parent().parent().find("font[name='SPECIAL_FLAG']").html("<span style='color: red'>有</span><input class='btn'  value='查看' onclick="+onclick+"  type='button' />");
			}
		})
    }
	function url(SPCL_TECH_ID){
		window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
	}
	function delDtl(){
		$("#jsontb :checkbox").attr("checked","true");
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		checkBox.parent().parent().remove();
	}