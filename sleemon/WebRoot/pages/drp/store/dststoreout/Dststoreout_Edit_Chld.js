/**
 * @prjName:供应链_贵人
 * @fileName:Dststoreout_Edit_Chld
 * @author zsl
 * @time   2016-01-11 15:05:08 
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
		url: "dststoreout.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"parentJsonData":parentData,"childJsonData":childData,"STOREOUT_ID":selRowId },
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","dststoreout.shtml?action=toFrame");
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
		url: "dststoreout.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"STOREOUT_ID":selRowId},
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
		url: "dststoreout.shtml?action=childDelete",
		type:"POST",
		dataType:"text",
		data:{"STOREOUT_DTL_IDS":ids},
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
   newGoBack("dststoreout.shtml?action=toFrame");
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
	rownum=_row_num;_row_num++;
	var SPCL_TECH_FLAG;
	if(null == arrs[13] || "" == arrs[13]){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input class='btn'  value='查看' onclick='url("+rownum+")'  type='button' />"
	}
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='STOREOUT_DTL_ID' name='STOREOUT_DTL_ID' value='"+arrs[0]+"'/></td>")
		    .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO" size="7"  autocheck="true" label="货品编号"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="30"  value="'+arrs[14]+'"/><input  json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID"  autocheck="true" label="货品ID"  type="hidden"  maxlength="32"  value="'+arrs[3]+'"/>' +
		    "<img align='absmiddle' style='cursor: hand' src='/sleemon/styles/newTheme/images/plus/select.gif' onClick='selcPrd("+rownum+")'/></td>")
		    .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME"  autocheck="true" label="货品名称"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="100"  value="'+arrs[9]+'"/>&nbsp;</td>')
		    .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC"  autocheck="true" label="规格型号"  type="text"   inputtype="string"   readonly        maxlength="50"  value="'+arrs[12]+'"/>&nbsp;</td>')
		    .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND" size="5" autocheck="true" label="品牌"  type="text"   inputtype="string"   readonly         maxlength="50"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT" size="5" autocheck="true" label="标准单位"  type="text"   inputtype="string"   readonly    maxlength="50"  value="'+arrs[1]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_TYPE" id="PRD_TYPE'+rownum+'" name="PRD_TYPE" size="5" autocheck="true" label="货品类型"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="30"  value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="center"><input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID"  autocheck="true" label="订单特殊工艺ID"  type="hidden" maxlength="32"  value="'+arrs[13]+'"/><font id="SPECIAL_FLAG'+rownum+'" name="SPECIAL_FLAG">'+SPCL_TECH_FLAG+'</font> &nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE" id="PRICE'+rownum+'" name="PRICE"  autocheck="true" label="单价" size="5" type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="22"  value="'+arrs[11]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_RATE" id="DECT_RATE'+rownum+'" name="DECT_RATE"  autocheck="true" size="3" label="折扣率"  type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="22"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_PRICE" id="DECT_PRICE'+rownum+'" name="DECT_PRICE"  autocheck="true" label="折扣价" size="5" type="text"   inputtype="string"   readonly     mustinput="true"     maxlength="22"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ORDER_NUM" id="ORDER_NUM'+rownum+'" name="ORDER_NUM"  autocheck="true" label="订货数量" readonly  type="text" size="5"  maxlength="22"  value="'+arrs[19]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="SEND_NUM" id="SEND_NUM'+rownum+'" name="SEND_NUM"  autocheck="true" label="已发数量" readonly  type="text" size="5"  maxlength="22"  value="'+arrs[20]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RECV_NUM" id="RECV_NUM'+rownum+'" name="RECV_NUM"  autocheck="true" label="出库数量"  onkeyup="countAmount('+rownum+')" type="text" size="5"      mustinput="true"     maxlength="22"  value="'+arrs[7]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DECT_AMOUNT" id="DECT_AMOUNT'+rownum+'" name="DECT_AMOUNT"  autocheck="true" label="折后金额"  type="text" size="5"  inputtype="string"   readonly     mustinput="true"     maxlength="22"  value="'+arrs[15]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK" id="REMARK'+rownum+'" name="REMARK"  autocheck="true" label="备注"  type="text"   inputtype="string"   readonly    maxlength="250"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left" style="display: none;"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR"   label="颜色"  type="hidden"  value="'+arrs[17]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left" style="display: none;"><input  json="FROM_BILL_DTL_ID" id="FROM_BILL_DTL_ID'+rownum+'" name="FROM_BILL_DTL_ID"   label="来源单据Id"  type="hidden"  value="'+arrs[18]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left" style="display: none;"><input  json="SPCL_TECH_FLAG" id="SPCL_TECH_FLAG'+rownum+'" name="SPCL_TECH_FLAG"   label="特殊工艺标记"  type="hidden"  value="'+arrs[16]+'"/>&nbsp;</td>')
              ;
	$("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}	
function url(rownum){
	var SPCL_TECH_ID=$("#SPCL_TECH_ID"+rownum).val();
	window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
}

//货品通用选取
    function selcPrd(rownum){
    	var FROM_BILL_ID=parent.topcontent.getFROM_BILL_ID();
    	var STOREOUT_STORE_ID=parent.topcontent.getSTOREOUT_STORE_ID();
    	if(FROM_BILL_ID==null || FROM_BILL_ID == ""){
    		alert("请选择来源单据编号");
    		return false;
    	}
    	if(STOREOUT_STORE_ID==null || STOREOUT_STORE_ID == ""){
    		alert("请选择出库库房编号");
    		return false;
    	}
		//获取编辑和列表里的来源单据id设置通用选取条件
    	 //0156501--start 刘曰刚 --2014-06-18//
        //修改过滤同一条明细只选一次
    	var dtlId=$("#FROM_BILL_DTL_IDS").val();
    	var ids;
    	if(""==dtlId||null==dtlId){
    		ids="";
    	}else{
    		ids=dtlId+",";
    	}
    	$("input[name='FROM_BILL_DTL_ID']").each(function(){
    		if(""!=$(this).val()&&null!=$(this).val()){
    			ids=ids+"'"+$(this).val()+"',";
    		}
    	});
    	ids = ids.substr(0,ids.length-1);
    	var sql;
    	if(""==ids||null==ids){
    		sql="1=1";
    	}else{
    		sql="ADVC_ORDER_DTL_ID not in ("+ids+")";
    	}
		$("#selectParams").val("DEL_FLAG=0 and NVL(ORDER_NUM,0)>NVL(SEND_NUM,0) and  ADVC_ORDER_ID='"+FROM_BILL_ID+"' and  NVL(IS_FREEZE_FLAG,0)='0' and "+sql);
		var obj=selCommon("BS_39", true, "PRD_ID"+rownum, "PRD_ID", "forms[0]",
			"PRD_ID"+rownum+",PRD_NO"+rownum+",PRD_NAME"+rownum+",PRD_SPEC"+rownum+",PRD_COLOR"+rownum+",BRAND"+rownum+",STD_UNIT"+rownum+",FROM_BILL_DTL_ID"+rownum+",SPCL_TECH_ID"+rownum+",SPCL_TECH_FLAG"+rownum+",PRICE"+rownum+",DECT_RATE"+rownum+",DECT_PRICE"+rownum
			+",PRD_TYPE"+rownum+",REMARK"+rownum+",RECV_NUM"+rownum+",ORDER_NUM"+rownum+",SEND_NUM"+rownum, 
			"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,ADVC_ORDER_DTL_ID,SPCL_TECH_ID,SPCL_TECH_FLAG,PRICE,DECT_RATE,DECT_PRICE,PRD_TYPE,REMARK,NUM,ORDER_NUM,SEND_NUM", "selectParams");
		rtnArr=multiSelCommonSet(obj,"PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,FROM_BILL_DTL_ID,SPCL_TECH_ID,SPCL_TECH_FLAG,PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,PRD_TYPE,REMARK,RECV_NUM,ORDER_NUM,SEND_NUM",rownum);
		var checkBox=$("input[type='checkbox']");
		checkBox.each(function(){
			var SPCL_TECH_FLAG=$(this).parent().parent().find("input[name='SPCL_TECH_FLAG']").val();
			var SPCL_TECH_ID=$(this).parent().parent().find("input[name='SPCL_TECH_ID']").val();
			if(SPCL_TECH_FLAG==null||SPCL_TECH_FLAG==""||SPCL_TECH_FLAG==0){
				$(this).parent().parent().find("font[name='SPECIAL_FLAG']").html("无");
			}else{
				var onclick="urlById('"+SPCL_TECH_ID+"')";
				$(this).parent().parent().find("font[name='SPECIAL_FLAG']").html("<span style='color: red'>有</span><input class='btn'  value='查看' onclick="+onclick+"  type='button' />");
			}
		})
		countAmount();
	}
    function delDtl(){
		$("#jsontb :checkbox").attr("checked","true");
		var checkBox = $("#jsontb tr:gt(0) input:checked");
		checkBox.parent().parent().remove();
	}
    function urlById(SPCL_TECH_ID){
		window.showModalDialog("techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID="+SPCL_TECH_ID,window,"dialogwidth=800px; dialogheight=600px; status=no");
	}
    function countAmount(i){
    	if(null==i||""==i){
    		$("#jsontb tr").each(function(){
	    		var DECT_PRICE=$(this).find("input[name='DECT_PRICE']").val();
	    		var RECV_NUM=$(this).find("input[name='RECV_NUM']").val();
	    		DECT_PRICE=isNaN(DECT_PRICE)?0:parseFloat(DECT_PRICE);
	    		RECV_NUM=isNaN(RECV_NUM)?0:parseFloat(RECV_NUM);
	    		var DECT_AMOUNT=Math.round((isNaN(DECT_PRICE*RECV_NUM)?0:DECT_PRICE*RECV_NUM)*100)/100;
	    		$(this).find("input[name='DECT_AMOUNT']").val(DECT_AMOUNT);
    		})
    	}else{
    		var DECT_PRICE=$("#DECT_PRICE"+i).val();
	    	var RECV_NUM=$("#RECV_NUM"+i).val();
	    	DECT_PRICE=isNaN(DECT_PRICE)?0:parseFloat(DECT_PRICE);
    		RECV_NUM=isNaN(RECV_NUM)?0:parseFloat(RECV_NUM);
    		var DECT_AMOUNT=Math.round((isNaN(DECT_PRICE*RECV_NUM)?0:DECT_PRICE*RECV_NUM)*100)/100;
    		$("#DECT_AMOUNT"+i).val(DECT_AMOUNT);
    	}
    	
    }
function formMutiTrChecked(){
		var checkBox = $("#jsontb tr:gt(0)  input[name='STOREOUT_DTL_ID']:checked");
		var flag=true;
		checkBox.each(function(){
			var RECV_NUM=$(this).parent().parent().find("input[name='RECV_NUM']").val();//订货数量
			var re2 = new RegExp(/^\d{1,8}(\.\d{1,2})?$/);
			if(""==RECV_NUM||null==RECV_NUM){
				parent.showErrorMsg("请输入出库数量");
	            flag=false;
				return false;
			}
	        if(!re2.test(RECV_NUM)){
	            parent.showErrorMsg("出库数量最多可输入8位正整数");
	            flag=false;
				return false;
	        }
			if(0==RECV_NUM&&""!=RECV_NUM&&null!=RECV_NUM){
				parent.showErrorMsg("出库数量不能为0");
				flag=false;
				return false;
			}
		});
		return flag;
	}
function queryDtl(id){
	window.location="dststoreout.shtml?action=toQueryAdvcDtl&ADVC_ORDER_ID="+id;
//	$("#bottomcontent").attr("src",url+"&STOREOUT_ID="+selRowId);
}