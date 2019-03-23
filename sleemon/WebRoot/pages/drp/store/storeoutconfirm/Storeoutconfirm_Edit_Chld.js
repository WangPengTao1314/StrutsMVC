

/**
 * @prjName:喜临门营销平台
 * @fileName:Advcorder_Edit_Chld
 * @author lyg
 * @time   2013-08-11 17:38:52 
 * @version 1.1
 */
 //固定的一些共通的方法 begin
$(function(){
    //form校验设置
	InitFormValidator(0);
	//点击关闭按钮关闭页面
	$("#close").click(function () {
		window.close();
	});
});

//子表保存
function childSave(){
	var jsonData = multiPackJsonData();
	var ADVC_ORDER_IDS=$("#ADVC_ORDER_IDS").val();
	$.ajax({
		url: "advctoorder.shtml?action=edit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"ADVC_ORDER_IDS":ADVC_ORDER_IDS},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert("加入成功");
				$("#pageForm").attr("action","advctoorder.shtml?action=toChild&ADVC_ORDER_IDS="+utf8(ADVC_ORDER_IDS))
				window.opener.parent.topcontent.listRef();
				window.close();
				//$("#pageForm").submit();
				
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
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
	        ];
		}
	//样式行
	var rownum = $("#jsontb tr").length;
	var classrow = rownum% 2;
	rownum=_row_num;_row_num++;
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center' style='display: none'><input type='checkbox' checked=checked'></td>")
		    .append('<td nowrap align="center">'+arrs[0]+'&nbsp;</td>')
            .append('<td nowrap align="center">'+arrs[1]+'&nbsp;</td>')
            .append('<td nowrap align="center">'+arrs[2]+'&nbsp;</td>')
            .append('<td nowrap align="center">'+arrs[3]+'&nbsp;</td>')
            .append('<td nowrap align="center">'+arrs[4]+'&nbsp;</td>')
            .append('<td nowrap align="center">'+arrs[5]+'&nbsp;</td>')
            .append('<td nowrap align="center">'+arrs[6]+'&nbsp;</td>')
            .append('<td nowrap align="center">'+arrs[7]+'&nbsp;</td>')
            .append('<td nowrap align="center"><input type="text" json="RECV_NUM" inputtype="float"  maxlength="8"  mustinput="true" style="width:70px;"  autocheck="true" label="收货数量"  name="RECV_NUM" value="'+arrs[7]+'" />&nbsp;</td>')
            .append('<td nowrap align="center" style="display: none"><input type="hidden" json="STOREOUT_DTL_ID" name="STOREOUT_DTL_ID" value="'+arrs[9]+'" />&nbsp;</td>')
            .append("<td nowrap='nowrap' align='center' style='display: none'><input type='hidden' json='REAL_NUM' name='REAL_NUM' value='"+arrs[10]+"' /></td>");
              ;
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
	});
    
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
	InitFormValidator(0);
}
function toVerify(){
	var STOREOUT_ID=$("#STOREOUT_ID").val();
	if(!formMutiTrChecked()){
		return;
	}
	var REMARK=$("#REMARK").val();
	if(""!=REMARK||null!=REMARK){
		if(REMARK.length>80){
  		    parent.showErrorMsg(utf8Decode("备注过长！"));
  		    return false;
  	   }
	}
	var RECV_ATT=$("#up").val();
	var jsonData = multiPackJsonData("jsontb");
	$.ajax({
		url: "storeoutconfirm.shtml?action=toVerify",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"STOREOUT_ID":STOREOUT_ID,"REMARK":REMARK,"RECV_ATT":RECV_ATT},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				alert("确认成功");
				window.close();
				window.opener.parent.topcontent.listRef();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}