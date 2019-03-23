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

//子表保存
function childSave(rowNum){
	
	if(!formMutiTrChecked()){
		return;
	}
	
	var selRowId = getSelRowId();

	if(null == rowNum){
		var jsonData = multiPackJsonData();
	}else{
		var jsonData = getSingleJsonData('jsontb',rowNum);
	}
	$.ajax({
		url: "prdreturn.shtml?action=childEdit",
		type:"POST",
		dataType:"text",
		data:{"childJsonData":jsonData,"PRD_RETURN_ID":selRowId},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				saveSuccess("保存成功","prdreturn.shtml?action=toFrame&flag=decide");
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
}

//返回
function gobacknew()
{
   newGoBack("prdreturn.shtml?action=toFrame&flag=decide");
}

function init(){
	$("#jsontb tr").each(function(){
		$(this).find("td:gt(0)").click(function(){
			$(this).parent().find("input[type='checkbox']").attr("checked","checked");
		});
	});

    $("#jsontb tr:gt(0) :checkbox").attr("checked","true");
	//form校验设置
	InitFormValidator(0);
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
	if(arrs[22]==null||arrs[22]==""||arrs[22]=="0"){
		SPCL_TECH_FLAG='无';
	}else{
		SPCL_TECH_FLAG="<span style='color: red'>有</span><input class='btn'  value='查看' onclick='url("+rownum+")'  type='button' />";
	}
	$("#jsontb tr:last-child").after("<tr class='list_row"+classrow+"' onmouseover='mover(this)' onmouseout='mout(this)'></tr>");
	$("#jsontb tr:last-child")
		    .append("<td nowrap align='center'><input type='checkbox' style='height:12px;valign:middle' json='PRD_RETURN_DTL_ID' name='PRD_RETURN_DTL_ID"+rownum+"' value='"+arrs[0]+"' rowNum ='"+rownum+"'/></td>")
             .append('<td nowrap align="left" style="display:none;"><input type="text" json="PRD_ID" id="PRD_ID'+rownum+'" name="PRD_ID'+rownum+'"  label="货品ID" maxlength="32" value="'+arrs[1]+'"/></td>')
             .append('<td nowrap align="center"><input  json="SN" id="SN'+rownum+'" name="SN'+rownum+'"  autocheck="true" label="货品序列号"  type="text"   inputtype="string"  readonly       maxlength="100"  value="'+arrs[20]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NO" id="PRD_NO'+rownum+'" name="PRD_NO'+rownum+'"  autocheck="true" label="货品编号"  type="text"   inputtype="string"   readonly  mustinput="true"     maxlength="30"  value="'+arrs[2]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_NAME" id="PRD_NAME'+rownum+'" name="PRD_NAME'+rownum+'"  autocheck="true" label="货品名称"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="100"  value="'+arrs[3]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_SPEC" id="PRD_SPEC'+rownum+'" name="PRD_SPEC'+rownum+'"  autocheck="true" label="规格型号"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="50"  value="'+arrs[4]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRD_COLOR" id="PRD_COLOR'+rownum+'" name="PRD_COLOR'+rownum+'"  autocheck="true" label="花号"  type="text"   inputtype="string"  readonly       maxlength="50"  value="'+arrs[5]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="BRAND" id="BRAND'+rownum+'" name="BRAND'+rownum+'"  autocheck="true" label="品牌"  type="text"   inputtype="string"     mustinput="true"  readonly   maxlength="50"  value="'+arrs[6]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="STD_UNIT" id="STD_UNIT'+rownum+'" name="STD_UNIT'+rownum+'"  autocheck="true" label="标准单位"  type="text"   inputtype="string"    readonly    maxlength="50"  value="'+arrs[7]+'"/>&nbsp;</td>')
			.append('<td nowrap align="center"><span id="SPECIAL_FLAG_SCRN'+rownum+'">'+SPCL_TECH_FLAG+'</td>')
            .append('<td nowrap align="left"><input  json="RETURN_PRICE" id="RETURN_PRICE'+rownum+'" name="RETURN_PRICE'+rownum+'"  autocheck="true" label="退货单价"   type="text"   inputtype="string" readonly    mustinput="true"     maxlength="22"  value="'+arrs[8]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="ADVC_RETURN_PRICE" id="ADVC_RETURN_PRICE'+rownum+'" name="ADVC_RETURN_PRICE'+rownum+'"  autocheck="true" label="建议退货单价" readonly  type="text"   inputtype="float" valueType="8,2"    value="'+arrs[23]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RETURN_NUM" id="RETURN_NUM'+rownum+'" name="RETURN_NUM'+rownum+'"  autocheck="true" label="退货数量"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="22"  value="'+arrs[9]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RETURN_AMOUNT" id="RETURN_AMOUNT'+rownum+'" name="RETURN_AMOUNT'+rownum+'"  autocheck="true" label="退货金额"  type="text" inputtype="string"  readonly   mustinput="true"     maxlength="22"  value="'+arrs[10]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RETURN_RSON_TYPE" id="RETURN_RSON_TYPE'+rownum+'" name="RETURN_RSON_TYPE'+rownum+'"  autocheck="true" label="原因归类"  type="text"  readonly inputtype="string"       maxlength="30"  value="'+arrs[11]+'"/></input></td>')
            .append('<td nowrap align="left"><input  json="RETURN_RSON" id="RETURN_RSON'+rownum+'" name="RETURN_RSON'+rownum+'"  autocheck="true" label="退货原因"  type="text"   inputtype="string"  readonly   mustinput="true"     maxlength="250"  value="'+arrs[12]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="RETURN_ATT" id="RETURN_ATT'+rownum+'" name="RETURN_ATT'+rownum+'"  autocheck="true" label="退货附件"  type="hidden"   inputtype="string"  readonly      maxlength="100"  value="'+arrs[13]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="DUTY_DECIDE" id="DUTY_DECIDE'+rownum+'" name="DUTY_DECIDE'+rownum+'"  autocheck="true" label="责任认定"  type="text"   inputtype="string"     mustinput="true"     maxlength="20"  value="'+arrs[14]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="PRICE_DECIDE" id="PRICE_DECIDE'+rownum+'" name="PRICE_DECIDE'+rownum+'"  autocheck="true" label="核价价格"  onblur="checkPrice('+rownum+')" type="text"   inputtype="float"     mustinput="true"      valueType="8,2"  value="'+arrs[15]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REAL_RETURN_AMOUNT" id="REAL_RETURN_AMOUNT'+rownum+'" name="REAL_RETURN_AMOUNT'+rownum+'"  autocheck="true" label="实际退货金额"  type="text" readonly  inputtype="string"     mustinput="true"     maxlength="100"  value="'+arrs[16]+'"/>&nbsp;</td>')
            .append('<td nowrap align="left"><input  json="REMARK_DECIDE" id="REMARK_DECIDE'+rownum+'" name="REMARK_DECIDE'+rownum+'"  autocheck="true" label="核价说明"  type="text"   inputtype="string"     mustinput="true"     maxlength="100"  value="'+arrs[17]+'"/>&nbsp;</td>')
             .append('<td nowrap align="left" style="display:none;"><input type="text" json="DEAL_FACTORY_ID" id="DEAL_FACTORY_ID'+rownum+'" name="DEAL_FACTORY_ID'+rownum+'"  label="处理工厂ID" maxlength="32" value="'+arrs[18]+'"/></td>')
            .append('<td nowrap align="left"><input  json="DEAL_FACTORY_NAME" id="DEAL_FACTORY_NAME'+rownum+'" name="DEAL_FACTORY_NAME'+rownum+'"  autocheck="true" label="处理工厂"  type="text"  readonly inputtype="string"     mustinput="true"     maxlength="100"  value="'+arrs[19]+'"/>&nbsp;<img align="absmiddle" name="selcomm" style="cursor:hand" src="/sleemon/styles/newTheme/images/plus/select.gif" onclick="javascript:selFactory('+rownum+')"/>&nbsp;</td>')
            .append('<input  json="SPCL_TECH_ID" id="SPCL_TECH_ID'+rownum+'" name="SPCL_TECH_ID'+rownum+'"  label="特殊工艺单ID" type="hidden" value="'+arrs[21]+'"/>')
//            .append('<td nowrap align="left"><input  type="button" name="save" class="btn" value ="保存" onclick="childSave('+rownum+');"/>&nbsp;</td>')
            
            ;
		//SelDictShow("RETURN_RSON_TYPE"+rownum,"BS_12",arrs[11],"");
		//uploadFile('RETURN_ATT'+rownum,'SAMPLE_DIR',true);
		displayDownFile('RETURN_ATT'+rownum,'SAMPLE_DIR',true,false);
    $("#jsontb tr:last-child").find("td:gt(0)").click(function(){
		$(this).parent().find("input[type='checkbox']").attr("checked","checked");
	});
	//form校验设置
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") input"));
	trCheckInit($("#jsontb tr:gt("+(rownum-1)+") select"));
}


//生产工厂通用选取
function selFactory(rowNum){
	selCommon("BS_41", false, "DEAL_FACTORY_ID"+rowNum, "FACTORY_ID", "forms[0]",
			"DEAL_FACTORY_NAME"+rowNum, "FACTORY_NAME","selectCondition");
}

function checkPrice(rowNum)
{
	var price = parseFloat($("#PRICE_DECIDE"+rowNum).val());
	var num = parseFloat($("#RETURN_NUM"+rowNum).val()); 
	if("NaN" == price.toString()){
		$("#PRICE_DECIDE"+rowNum).val("");
		$("#REAL_RETURN_AMOUNT"+rowNum).val("");
		chkCanErrMsg("", "请选择输入正确的核价价格");
		return;
	}
	if("NaN" == num.toString()){
		return;
	}
	
	var amount  = price * num;
	$("#REAL_RETURN_AMOUNT"+rowNum).val(amount);
}

function getSingleJsonData(tableid,rowNum){
	var jsonData = "";
	var trs = $("#"+tableid+" tr:gt(0)");
	trs.each(function(){
		var num = $(this).find("input[type='checkbox']:eq(0)").attr("rowNum");
		if(num == rowNum){
			jsonData = jsonData+"{";
			var isFirst = true;
			var inputs = $(this).find(":input");
			inputs.each(function(){
				if(null != $(this).attr("json")){
					var key;
					var value;
					var type = $(this).attr("type");
					if(!isFirst && "checkbox" == type){
						key = $(this).attr("json");
						if($(this).attr("checked")){
							value= 1;
						}else{
							value= 0;
						}
					}else if("radio" == type){
						if($(this).attr("checked")){
							key = $(this).attr("json");
							value= $(this).attr("value");
						}
					}else{
						key = $(this).attr("json");
						value = $(this).attr("value");
						
						isFirst = false;
					}
					var inputtype = $(this).attr("inputtype");
					jsonData = jsonData+ "'" + key + "':'" + inputtypeFilter(inputtype,value) +"',";
				}
			});
			jsonData = jsonData.substr(0,jsonData.length-1)+"},"
		}
	});
	if(jsonData.length>1){
		return "["+jsonData.substr(0,jsonData.length-1)+"]";
	}
	return "[]";
}
function url(id){
	var SPCL_TECH_ID=$("#SPCL_TECH_ID"+id).val();
	window.open('techorderManager.shtml?action=viewTechWithOutPrice&SPCL_TECH_ID='+SPCL_TECH_ID,'特殊工艺单维护','height=600, width=800, top=100, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
}