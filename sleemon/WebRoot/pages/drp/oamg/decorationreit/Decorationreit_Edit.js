
/**
 *@module 渠道管理-装修管理
 *@fuc    装修报销申请单维护
 *@version 1.0
 *@author zzb
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("decorationreit.shtml?action=edit","RNVTN_REIT_REQ_ID");
	//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
	
	//图纸设计完成时间 绑定事件
	/*
	$("#DRAW_FISH_DATE").bind("propertychange", function() { 
           setRNVTN_DAYS();
    }); 
	$("#RNVTN_FISH_DATE").bind("propertychange", function() { 
           setRNVTN_DAYS();
    }); 
	getReimbursement();
	*/
	checkMust(null);
});

function chkIsNorth(){
  var IsNorth = document.getElementsByName("IS_NORTHs");
  for(var i=0;i<IsNorth.length;i++){
     if(IsNorth[i].checked){
        $("#IS_NORTH").val(IsNorth[i].value);
     }
  }
}

function chkTotalNum(){
   var REIT_AMOUNT = $("#REIT_AMOUNT").val();
   var TOTAL_REITED_NUMs = document.getElementsByName("TOTAL_REITED_NUMs");
   var TOTAL_REITED_NUM = 0;
   var REITED_NUM = 0;
   for(var i=0;i<TOTAL_REITED_NUMs.length;i++){
      if(TOTAL_REITED_NUMs[i].checked){
          TOTAL_REITED_NUM = TOTAL_REITED_NUMs[i].value;
          $("#TOTAL_REITED_NUM").val(TOTAL_REITED_NUM);
      }
   }
 
   var REITED_NUMs = document.getElementsByName("REITED_NUMs");
   for(var i=0;i<REITED_NUMs.length;i++){
       if(REITED_NUMs[i].checked){
           REITED_NUM = REITED_NUMs[i].value;
           $("#REITED_NUM").val(REITED_NUMs[i].value);
       }
   }  
   /*
   if(TOTAL_REITED_NUM=="1"){
      $("#REAL_REIT_AMOUNT").val(REIT_AMOUNT*1);
   }
   if(TOTAL_REITED_NUM=="2"){
      $("#REAL_REIT_AMOUNT").val(REIT_AMOUNT*0.5);
   }
   if(TOTAL_REITED_NUM=="3"){
      if(REITED_NUM=="1"){
        $("#REAL_REIT_AMOUNT").val(REIT_AMOUNT*0.5);
      }
      if(REITED_NUM=="2"){
        $("#REAL_REIT_AMOUNT").val(REIT_AMOUNT*0.25);
      }
      if(REITED_NUM=="3"){
        $("#REAL_REIT_AMOUNT").val(REIT_AMOUNT*0.25);
      }
   }*/
}

function chkReitedNum(){
   var TOTAL_REITED_NUM = 0;
   var REITED_NUM = 0;
   var REITED_NUMs = document.getElementsByName("REITED_NUMs");
   for(var i=0;i<REITED_NUMs.length;i++){
       if(REITED_NUMs[i].checked){
           REITED_NUM = REITED_NUMs[i].value;
           $("#REITED_NUM").val(REITED_NUMs[i].value);
       }
   }  
   
   var REIT_AMOUNT = $("#REIT_AMOUNT").val();
   var TOTAL_REITED_NUMs = document.getElementsByName("TOTAL_REITED_NUMs");
   for(var i=0;i<TOTAL_REITED_NUMs.length;i++){
      if(TOTAL_REITED_NUMs[i].checked){
          TOTAL_REITED_NUM = TOTAL_REITED_NUMs[i].value;
          $("#TOTAL_REITED_NUM").val(TOTAL_REITED_NUM);
      }
   }
   /*
   if(TOTAL_REITED_NUM=="1"){
      $("#REAL_REIT_AMOUNT").val(REIT_AMOUNT*1);
   }
   if(TOTAL_REITED_NUM=="2"){
      $("#REAL_REIT_AMOUNT").val(REIT_AMOUNT*0.5);
   }
   if(TOTAL_REITED_NUM=="3"){
      if(REITED_NUM=="1"){
        $("#REAL_REIT_AMOUNT").val(REIT_AMOUNT*0.5);
      }
      if(REITED_NUM=="2"){
        $("#REAL_REIT_AMOUNT").val(REIT_AMOUNT*0.25);
      }
      if(REITED_NUM=="3"){
        $("#REAL_REIT_AMOUNT").val(REIT_AMOUNT*0.25);
      }
   }*/
}


function selectChannRnvtn(){
	$("#REIT_AMOUNT_PS").val("");
	selCommon('BS_85', false, 'CHANN_RNVTN_ID', 'CHANN_RNVTN_ID', 'forms[0]','CHANN_RNVTN_NO,TERM_ID,TERM_NO,TERM_NAME,CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_PERSON_CON,CHANN_TEL,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,REIT_AMOUNT_PS,REIT_POLICY,RNVTN_PROP,COMPACT_AMOUNT', 'CHANN_RNVTN_NO,TERM_ID,TERM_NO,TERM_NAME,CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_PERSON_CON,CHANN_TEL,AREA_ID,AREA_NO,AREA_NAME,AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,REIT_AMOUNT_PS,REIT_POLICY,RNVTN_PROP,COMPACT_AMOUNT', 'selectParams');
	$("#isHaveOver").val("");//是否已完结标记 置空 
	var CHANN_RNVTN_ID = $("#CHANN_RNVTN_ID").val();
	if(null == CHANN_RNVTN_ID || "" == CHANN_RNVTN_ID){
		return;
	}
    $.ajax({
		url: "decorationreit.shtml?action=loadModel",
		type:"POST",
		dataType:"text",
		data:{"CHANN_RNVTN_ID":CHANN_RNVTN_ID},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var result = jsonResult.data;
				if(null != result && "" != result){
					var TOTAL_REITED_NUM =result.TOTAL_REITED_NUM;
					var REITED_NUM = result.REITED_NUM;
					var CHANN_RNVTN_NO = result.CHANN_RNVTN_NO;
					if(TOTAL_REITED_NUM == REITED_NUM){
						$("#isHaveOver").val("1");//是否已完结标记设置值
						parent.showErrorMsg("单号是["+CHANN_RNVTN_NO+"]的装修申请单已经报销完毕");
						return ;
					}
					var inputs = $("#jsontb input[json]");
					inputs.each(function(){
						var id = $(this).attr("id");
						$(this).attr("readonly","readonly");
						//alert(eval("result."+id));
						$("#"+id).val(eval("result."+id));
					});
					REITED_NUM = parseInt(REITED_NUM)+1;
					$("#REITED_NUM").val(REITED_NUM);
					$("#ATT_ID").val("");//新增页面附件的ID置空
					var ATT_PATH = $("#ATT_PATH").val();
					if(null != ATT_PATH && "" != ATT_PATH){
						$("#ATT_PATH_TD").remove();
						var html = "<td width='35%' class='detail_content' colspan='3' id='ATT_PATH_TD'>";
						html = html + "<input type='hidden' id='ATT_PATH' json='ATT_PATH'  name='ATT_PATH' value='"+ATT_PATH+"'/></td>";
						$("#ATT_PATH_TR td:first-child").after(html);
						uploadFile('ATT_PATH',folder,true,true,true,callback);
					}
					InitFormValidator(0);
				}else{
					$("#REITED_NUM").val(1);
				}
				//获取 装修报销明细  
				getReimbursement();
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 }); 
}

//选择是否有DM装修申请单，没有申请的必须添加之前在老oa中审批的申请单为附件
function checkMust(obj){
	var v ;
	if(null == obj || "" == obj){
		v = $("#FROM_BILL_FLAG").val();
	}else{
		v = $(obj).val();
	}
	var chk ;
	var FROM_BILL_FLAGT = document.getElementsByName("FROM_BILL_FLAGT");
	for(var i=0;i<FROM_BILL_FLAGT.length;i++){
	    if(FROM_BILL_FLAGT[i].checked){
	       chk = FROM_BILL_FLAGT[i].value;
	    }
	}
    var v = $("#FROM_BILL_FLAGA").val();
    if(v==1){
        if(chk==0){
	        $("#jsontb td[name='OLD_OA_TD']").css("display","none");
			$("#jsontb td[name='OLD_OA_TD_L']").attr("colspan","6");
			$("#termSelcom").css("display","none");
			$("#showChannRnvtnNO").show();
			$("#showChannRnvtnNO1").show();
			$("#showChannName").hide();
			$("#showTermNo").hide();
			$("#CHANN_RNVTN_NO").val("");
			//$("#CHANN_NO").val("");
			document.getElementsByName("CHANN_RNVTN_NO").value="";
			//alert(document.getElementsByName("CHANN_RNVTN_NO").value);
			document.getElementById("TOTAL_REIT_AMOUNT").readOnly=true;
			document.getElementById("TOTAL_REIT_AMOUNT").className="textReadonly";
			document.getElementById("USE_AREA").readOnly=true;
			document.getElementById("USE_AREA").className="textReadonly";
			document.getElementById("SALE_STORE_NAME").readOnly=true;
			document.getElementById("SALE_STORE_NAME").className="textReadonly";
			document.getElementById("ADDRESS").readOnly=true;
			document.getElementById("ADDRESS").className="textReadonly";
			document.getElementById("OPEN_SALE_YEAR").readOnly=true;
			document.getElementById("OPEN_SALE_YEAR").className="textReadonly";
			$("#FORM_BILL_FLAG_T").val(0);
		}
		if(chk==1){
		    $("#jsontb td[name='OLD_OA_TD_L']").attr("colspan","1");
			$("#jsontb td[name='OLD_OA_TD']").css("display","block");
			$("#termSelcom").css("display","inline");
			$("#showChannRnvtnNO").hide();
			$("#showChannRnvtnNO1").hide();
			$("#showChannName").show();
			$("#showTermNo").show();
			document.getElementById("showTd").colSpan="3";
			document.getElementById("TOTAL_REIT_AMOUNT").readOnly=false;
			document.getElementById("TOTAL_REIT_AMOUNT").className="inputStyle";
			document.getElementById("USE_AREA").readOnly=false;
			document.getElementById("USE_AREA").className="inputStyle";
			document.getElementById("SALE_STORE_NAME").readOnly=false;
			document.getElementById("SALE_STORE_NAME").className="inputStyle";
			document.getElementById("ADDRESS").readOnly=false;
			document.getElementById("ADDRESS").className="inputStyle"
			document.getElementById("OPEN_SALE_YEAR").readOnly=false;
			document.getElementById("OPEN_SALE_YEAR").className="inputStyle";
			$("#FORM_BILL_FLAG_T").val(1);
		}
    }if(v==0){
        if(chk==1){
	        $("#jsontb td[name='OLD_OA_TD_L']").attr("colspan","1");
			$("#jsontb td[name='OLD_OA_TD']").css("display","block");
			$("#termSelcom").css("display","inline");
			$("#showChannRnvtnNO").hide();
			$("#showChannRnvtnNO1").hide();
			$("#showChannName").show();
			$("#showTermNo").show();
			document.getElementById("showTd").colSpan="3";
			document.getElementById("TOTAL_REIT_AMOUNT").readOnly=false;
			document.getElementById("TOTAL_REIT_AMOUNT").className="inputStyle";
			document.getElementById("USE_AREA").readOnly=false;
			document.getElementById("USE_AREA").className="inputStyle";
			document.getElementById("SALE_STORE_NAME").readOnly=false;
			document.getElementById("SALE_STORE_NAME").className="inputStyle";
			document.getElementById("ADDRESS").readOnly=false;
			document.getElementById("ADDRESS").className="inputStyle"
			document.getElementById("OPEN_SALE_YEAR").readOnly=false;
			document.getElementById("OPEN_SALE_YEAR").className="inputStyle";
			$("#FORM_BILL_FLAG_T").val(1);
	      } 
	     if(chk==0){
	        $("#jsontb td[name='OLD_OA_TD']").css("display","none");
			$("#jsontb td[name='OLD_OA_TD_L']").attr("colspan","6");
			$("#termSelcom").css("display","none");
			$("#showChannRnvtnNO").show();
			$("#showChannRnvtnNO1").show();
			$("#showChannName").hide();
			$("#showTermNo").hide();
			//$("#CHANN_RNVTN_NO").val("");
			//$("#CHANN_NO").val("");
			document.getElementsByName("CHANN_RNVTN_NO").value="";
			//alert(document.getElementsByName("CHANN_RNVTN_NO").value);
			document.getElementById("TOTAL_REIT_AMOUNT").readOnly=true;
			document.getElementById("TOTAL_REIT_AMOUNT").className="textReadonly";
			document.getElementById("USE_AREA").readOnly=true;
			document.getElementById("USE_AREA").className="textReadonly";
			document.getElementById("SALE_STORE_NAME").readOnly=true;
			document.getElementById("SALE_STORE_NAME").className="textReadonly";
			document.getElementById("ADDRESS").readOnly=true;
			document.getElementById("ADDRESS").className="textReadonly";
			document.getElementById("OPEN_SALE_YEAR").readOnly=true;
			document.getElementById("OPEN_SALE_YEAR").className="textReadonly";
			$("#FORM_BILL_FLAG_T").val(0);
	     }
    }
    
    
    /*
	if(0 == v){
		$("#jsontb td[name='OLD_OA_TD']").css("display","none");
		$("#jsontb td[name='OLD_OA_TD_L']").attr("colspan","6");
		$("#termSelcom").css("display","none");
		$("#showChannRnvtnNO").show();
		$("#showChannRnvtnNO1").show();
		$("#showChannName").hide();
		$("#showTermNo").hide();
		//$("#CHANN_RNVTN_NO").val("");
		//$("#CHANN_NO").val("");
		document.getElementsByName("CHANN_RNVTN_NO").value="";
		//alert(document.getElementsByName("CHANN_RNVTN_NO").value);
		document.getElementById("REIT_AMOUNT").readOnly=true;
		document.getElementById("REIT_AMOUNT").className="textReadonly";
		document.getElementById("USE_AREA").readOnly=true;
		document.getElementById("USE_AREA").className="textReadonly";
		document.getElementById("SALE_STORE_NAME").readOnly=true;
		document.getElementById("SALE_STORE_NAME").className="textReadonly";
		document.getElementById("ADDRESS").readOnly=true;
		document.getElementById("ADDRESS").className="textReadonly";
		document.getElementById("OPEN_SALE_YEAR").readOnly=true;
		document.getElementById("OPEN_SALE_YEAR").className="textReadonly";
		//
	}else{
	    
//		$("#jsontb td[name='OLD_OA_TD_L']").removeAttr("colspan");
		$("#jsontb td[name='OLD_OA_TD_L']").attr("colspan","1");
		$("#jsontb td[name='OLD_OA_TD']").css("display","block");
		$("#termSelcom").css("display","inline");
		$("#showChannRnvtnNO").hide();
		$("#showChannRnvtnNO1").hide();
		$("#showChannName").show();
		$("#showTermNo").show();
		document.getElementById("showTd").colSpan="3";
		document.getElementById("REIT_AMOUNT").readOnly=false;
		document.getElementById("REIT_AMOUNT").className="inputStyle";
		document.getElementById("USE_AREA").readOnly=false;
		document.getElementById("USE_AREA").className="inputStyle";
		document.getElementById("SALE_STORE_NAME").readOnly=false;
		document.getElementById("SALE_STORE_NAME").className="inputStyle";
		document.getElementById("ADDRESS").readOnly=false;
		document.getElementById("ADDRESS").className="inputStyle"
		document.getElementById("OPEN_SALE_YEAR").readOnly=false;
		document.getElementById("OPEN_SALE_YEAR").className="inputStyle";
	} */
}

function formChecked(){

    //判断原申请单是否为空
    /*
    var CHANN_RNVTN_NO = $("#CHANN_RNVTN_NO").val();
    if(CHANN_RNVTN_NO==""){
       chkCanErrMsg("", "请选择'原申请单号'");
	   return false;
    }*/
    
    var CHANN_NAME = $("#CHANN_NAME").val();
    if(CHANN_NAME==""){
       chkCanErrMsg("", "请选择'加盟商'");
	   return false;
    } 
    
    var DESIGN_PERSON = $("#DESIGN_PERSON").val();
    if(DESIGN_PERSON==""){
       chkCanErrMsg("", "请选择'设计师'");
	   return false;
    } 
    
    var TERM_NO = $("#TERM_NO").val();
    if(TERM_NO==""){
       chkCanErrMsg("", "请选择'终端编号'");
	   return false;
    } 
    
    var BUDGET_ITEM_NAME = $("#BUDGET_ITEM_NAME").val();
    if(BUDGET_ITEM_NAME==""){
       chkCanErrMsg("", "请选择'预算科目'");
	   return false;
    } 
    var module = $("#module").val();
    //alert("module==="+module);
    if(module !="sh"){
	    var SALE_COMPACT_AMOUNT = $("#SALE_COMPACT_AMOUNT").val();
	    if(SALE_COMPACT_AMOUNT==""){
	       chkCanErrMsg("", "请输入合同金额");
		   return false;
	    }
    }
    
    var LOCAL_TYPE ="";
	var RNVTN_PROP ="";
	var BUSS_SCOPE ="";
	var TOTAL_REITED_NUM = "";
	var REITED_NUM ="";
	
	var LOCAL_TYPEs = document.getElementsByName("LOCAL_TYPEs");
	for(var i=0;i<LOCAL_TYPEs.length;i++){
	   if(LOCAL_TYPEs[i].checked){
	       LOCAL_TYPE = LOCAL_TYPEs[i].value;
	   }
	}
	if(LOCAL_TYPE==""){
	   chkCanErrMsg("", "请选择'商场位置类别'");
	   return false;
	}
	var RNVTN_PROPs = document.getElementsByName("RNVTN_PROPs");
	for(var i=0;i<RNVTN_PROPs.length;i++){
	    if(RNVTN_PROPs[i].checked){
	       RNVTN_PROP = RNVTN_PROPs[i].value;
	    }
	}
	if(RNVTN_PROP==""){
	   chkCanErrMsg("", "请选择'装修性质'");
	   return false;
	}
	
    //var reg = new RegExp("^[0-9]*$");
    var reg = /^[0-9]*(\.[0-9]{1,2})?$/;
    var REIT_AMOUNT = $("#REIT_AMOUNT").val();
    if(!reg.test(REIT_AMOUNT)){
        parent.showErrorMsg("'本次报销金额'请输入数字!");
        return false;
    }
    
    var REAL_REIT_AMOUNT = $("#REAL_REIT_AMOUNT").val();
    if(!reg.test(REAL_REIT_AMOUNT)){
        parent.showErrorMsg("'实际报销金额'请输入数字!");
        return false;
    }
	
	var BUSS_SCOPEs = document.getElementsByName("BUSS_SCOPEs");
	for(var i=0;i<BUSS_SCOPEs.length;i++){
	    if(BUSS_SCOPEs[i].checked){
	       BUSS_SCOPE = BUSS_SCOPEs[i].value;
	    }
	}
	if(BUSS_SCOPE==""){
	   chkCanErrMsg("", "请选择'经营品牌'");
	   return false;
	}
	var TOTAL_REITED_NUMs = document.getElementsByName("TOTAL_REITED_NUMs");
	for(var i=0;i<TOTAL_REITED_NUMs.length;i++){
	    if(TOTAL_REITED_NUMs[i].checked){
	       TOTAL_REITED_NUM = TOTAL_REITED_NUMs[i].value;
	    }
	}
	if(TOTAL_REITED_NUM==""){
	   chkCanErrMsg("", "请选择'报销次数'");
	   return false;
	}
	
	var REITED_NUMs = document.getElementsByName("REITED_NUMs");
	for(var i=0;i<REITED_NUMs.length;i++){
	   if(REITED_NUMs[i].checked){
	       REITED_NUM = REITED_NUMs[i].value;
	   }
	}
	if(REITED_NUM == ""){
	   chkCanErrMsg("", "请选择'第几次报销'");
	   return false;
	}
	
	var REIT_AMOUNT = $("#REIT_AMOUNT").val();
	if(REIT_AMOUNT == ""){
	   chkCanErrMsg("", "请输入本次报销金额");
	   return false;
	}
	
   var REIT_AMOUNT = $("#REIT_AMOUNT").val();
   var REAL_REIT_AMOUNT  = $("#REAL_REIT_AMOUNT").val();
   if(eval(REAL_REIT_AMOUNT) > eval(REIT_AMOUNT)){
       parent.showErrorMsg("实际报销金额不能大于本次报销金额");
       return false;
   }
	return true;
}

/**
 * 浮点数的 乘法
 * @param {Object} arg1
 * @param {Object} arg2
 * @return {TypeName} 
 */
function FloatMul(arg1,arg2){   
  var m=0,s1=arg1.toString(),s2=arg2.toString();   
  try{m+=s1.split(".")[1].length}catch(e){}   
  try{m+=s2.split(".")[1].length}catch(e){}   
  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
}  

//计算按图纸面积标准参考报销金额
function countDrawReitAmountPs(obj){
	var DRAW_AREA = $(obj).val();
	DRAW_AREA = parseFloat(DRAW_AREA);
	var REIT_AMOUNT_PS = $("#REIT_AMOUNT_PS").val();
	REIT_AMOUNT_PS = parseFloat(REIT_AMOUNT_PS);
	if(!isNaN(DRAW_AREA) && !isNaN(REIT_AMOUNT_PS)){
		var resu =  FloatMul(DRAW_AREA,REIT_AMOUNT_PS);
	  $("#DRAW_REIT_AMOUNT_PS").val(resu);
	}
 
}

//计算装修天数
function  setRNVTN_DAYS(){
	//设计图纸完成时间
	var DRAW_FISH_DATE = $("#DRAW_FISH_DATE").val();
	//装修完成时间
	var RNVTN_FISH_DATE = $("#RNVTN_FISH_DATE").val();
	if(null == DRAW_FISH_DATE || "" == DRAW_FISH_DATE){
		return;
	}
	if(null == RNVTN_FISH_DATE || "" == RNVTN_FISH_DATE){
		return;
	}
	if(!compareDate(DRAW_FISH_DATE,RNVTN_FISH_DATE)){
		//装修完成时间应该大于图纸设计完成时间
		parent.showErrorMsg("'"+$("#RNVTN_FISH_DATE").attr("label")+"'应该大于'"+$("#DRAW_FISH_DATE").attr("label")+"'");
		$("#RNVTN_FISH_DATE").val("");
		return ;
	}
	var RNVTN_DAYS = DateDiff(DRAW_FISH_DATE,RNVTN_FISH_DATE);
	$("#RNVTN_DAYS").val(RNVTN_DAYS);
}

//计算（当前可报销金额=实际报销金额*当前报销次数报销比例）
//(剩余可报销金额=实际报销金额-累计已经报销金额)
function countCanReturnAmount(){
	//实际报销金额
	var REAL_REIT_AMOUNT = $("#REAL_REIT_AMOUNT").val();
	//当前报销次数报销比例
	var REITED_RATE = $("#REITED_RATE").val();
	REAL_REIT_AMOUNT = parseFloat(REAL_REIT_AMOUNT);
	if(null != REITED_RATE && "" != REITED_RATE){
		REITED_RATE = REITED_RATE.substring(0,REITED_RATE.length-1);
		REITED_RATE = REITED_RATE/100;
	}
	REITED_RATE = parseFloat(REITED_RATE);
	if(isNaN(REAL_REIT_AMOUNT)){
		REAL_REIT_AMOUNT = 0;
	}
	if(isNaN(REITED_RATE)){
		REITED_RATE = 1;
	}
	var CAN_RETURN_AMOUNT = FloatMul(REAL_REIT_AMOUNT,REITED_RATE);
	$("#CAN_RETURN_AMOUNT").val(CAN_RETURN_AMOUNT);
	//累计已经报销金额
	var TOTAL_RETURN_AMOUNT = $("#TOTAL_RETURN_AMOUNT").val();
	if(null == TOTAL_RETURN_AMOUNT || "" == TOTAL_RETURN_AMOUNT){
		TOTAL_RETURN_AMOUNT = 0;
	}
	TOTAL_RETURN_AMOUNT = parseFloat(TOTAL_RETURN_AMOUNT);
	if(!isNaN(TOTAL_RETURN_AMOUNT)){
		var LEFT_CAN_RETURN_AMOUNT = FloatSub(REAL_REIT_AMOUNT,TOTAL_RETURN_AMOUNT);
		$("#LEFT_CAN_RETURN_AMOUNT").val(LEFT_CAN_RETURN_AMOUNT);
	}
}


function  getReturnAmount(){
   params = $("#DEPOSIT").val();
   if(params !="") {
	   var RNVTN_DAYS  = $("#RNVTN_DAYS").val();
	   if(parseInt(RNVTN_DAYS)<40){
	       $("#DEPOSIT_RETURN_AMOUNT").val(params);
	   }
	   if(parseInt(RNVTN_DAYS)>40 && parseInt(RNVTN_DAYS)<60){
	       paramsT  = parseInt(params)-1000;
	       $("#DEPOSIT_RETURN_AMOUNT").val(paramsT);
	   }
	   if(parseInt(RNVTN_DAYS)>60){
	        $("#DEPOSIT_RETURN_AMOUNT").val(0);
	   }
   }
}
  //浮点数减法运算   
 function FloatSub(arg1,arg2){   
	  var r1,r2,m,n;   
	  try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}   
	  try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}   
	  m = Math.pow(10,Math.max(r1,r2));   
	  //动态控制精度长度   
	  n =(r1>=r2)?r1:r2;   
	  return ((arg1*m-arg2*m)/m).toFixed(n);   
 }   

//获取 装修报销明细  
function getReimbursement(){
    
	$("#reitedNumTd").attr("disabled","disabled");
	var CHANN_RNVTN_ID = $("#CHANN_RNVTN_ID").val();
	var REITED_NUM = $("#REITED_NUM").val();
	if(null == CHANN_RNVTN_ID || "" == CHANN_RNVTN_ID){
		return;
	}
	$.ajax({
		url: "decorationreit.shtml?action=getReimbursement",
		type:"POST",
		dataType:"text",
		data:{"CHANN_RNVTN_ID":CHANN_RNVTN_ID},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				//重新选择‘装修申请单编号’的时候 清空 ‘当前报销次数’和‘供需报销次数’
				 $("#REITED_NUM").val("");
				 $("#reitedNumTd").html("");
				 
				 var index = 0;
				 var scale = "";
				 var data = jsonResult.data;
				 $.each(data,function(i,item){
					 index = parseInt(index)+1;
					 var checked = "";
					 if(REITED_NUM == item.RNVTN_REIT_NO){
						 checked = "checked";
						 scale = item.RNVTN_SCALE;
						 $("#REITED_NUM").val(REITED_NUM);
					 }
					 $("#reitedNumTd").append("<input  type='radio' name='REITED_NUM_'  value='"+item.RNVTN_SCALE+"' label='当前报销次数' autocheck='true'  mustinput='true' inputtype='string' onclick='countReitedRate(this);' text='"+item.RNVTN_REIT_NO+"' "+checked+"/>"+item.RNVTN_REIT_NO+"&nbsp;");
				 });
				 $("#TOTAL_REITED_NUM").val(index);//‘供需报销次数’
				 $("#reitedNumTd").append("<SPAN class='validate'>&nbsp;*</SPAN>");
				 countReitedRate(scale);
				 //countCanReturnAmount();
				 var RNVTN_REIT_REQ_ID = $("#RNVTN_REIT_REQ_ID").val();
				 
				 if(null == RNVTN_REIT_REQ_ID || "" == RNVTN_REIT_REQ_ID){
					//计算已经报销的 报销金额
				   sumCanReturnAmout();
				 }
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
}

//当前报销次数报销比例
function countReitedRate(obj){
	var REITED_RATE = obj;
	REITED_RATE = parseFloat(REITED_RATE);
	var REITED_RATE_ = "";
	if(!isNaN(REITED_RATE)){
		REITED_RATE_ = REITED_RATE*100;
	}
	REITED_RATE_ = REITED_RATE_+"%";
	$("#REITED_RATE").val(REITED_RATE_);
	//$("#REITED_NUM").val($.trim($(obj).attr("text")));
	//实际报销金额
	var REAL_REIT_AMOUNT = $("#REAL_REIT_AMOUNT").val();
	if(null == REAL_REIT_AMOUNT || "" == REAL_REIT_AMOUNT){
		return;
	}
	//当前可报销金额
	var CAN_RETURN_AMOUNT = FloatMul(REITED_RATE,REAL_REIT_AMOUNT);
	$("#CAN_RETURN_AMOUNT").val(CAN_RETURN_AMOUNT);
}


//计算已经报销的 报销金额
function sumCanReturnAmout(){
	var CHANN_RNVTN_ID = $("#CHANN_RNVTN_ID").val();
	if(null == CHANN_RNVTN_ID || "" == CHANN_RNVTN_ID){
		return;
	}
	$.ajax({
		url: "decorationreit.shtml?action=sumCanReturnAmout",
		type:"POST",
		dataType:"text",
		data:{"CHANN_RNVTN_ID":CHANN_RNVTN_ID},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 var data = jsonResult.data;
				 $("#TOTAL_RETURN_AMOUNT").val(data.TOTAL_RETURN_AMOUNT);
				 var REAL_REIT_AMOUNT = $("#REAL_REIT_AMOUNT").val();
				 var TOTAL_RETURN_AMOUNT = $("#TOTAL_RETURN_AMOUNT").val();
				 if(null == TOTAL_RETURN_AMOUNT || "" == TOTAL_RETURN_AMOUNT){
				 	TOTAL_RETURN_AMOUNT = 0;
				 }
				 TOTAL_RETURN_AMOUNT = parseFloat(TOTAL_RETURN_AMOUNT);
				 if(!isNaN(TOTAL_RETURN_AMOUNT)){
					var LEFT_CAN_RETURN_AMOUNT = FloatSub(REAL_REIT_AMOUNT,TOTAL_RETURN_AMOUNT);
					$("#LEFT_CAN_RETURN_AMOUNT").val(LEFT_CAN_RETURN_AMOUNT);
				 }
				 
			}else{
			}
		}
	});
}

//日期比较  date1 = '2013-05-11', date2='2014-05-14' return true;
function compareDate(date1,date2){
	var arr = date1.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();
    var arr2 = date2.split("-");
    var endtime = new Date(arr2[0], arr2[1], arr2[2]);
    var endtimes = endtime.getTime();
    if(endtimes < starttimes){
    	return false;
    }
    return true;
}

//日期减法 
function DateDiff(date1,date2){
	var d = 24*60*60*1000;
	var arr = date1.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();
    var arr2 = date2.split("-");
    var endtime = new Date(arr2[0], arr2[1], arr2[2]);
    var endtimes = endtime.getTime();
    var diff = (endtimes-starttimes)/d;
    return diff;
}

function  getBussScope(){
  var strScope = "";
  var BUSS_SCOPE = document.getElementsByName("BUSS_SCOPEs");
  for(var i=0;i<BUSS_SCOPE.length;i++){
      if(BUSS_SCOPE[i].checked){
          strScope += BUSS_SCOPE[i].value+","
          //strScope = strScope.substr(0,strScope.length-1);
      }
  }
  
  var len=strScope.length;
  var strScope=strScope.substring(0,len-1);
  $("#BUSS_SCOPE").val(strScope);
}

function getRnvtnProp(){
   var RNVTN_PROP = document.getElementsByName("RNVTN_PROPs");
   for(var i=0;i<RNVTN_PROP.length;i++){
      if(RNVTN_PROP[i].checked){
         $("#RNVTN_PROP").val(RNVTN_PROP[i].value);
      }
   }
}

function getLocalType(){
   var strType = ""; 
   var LOCAL_TYPE = document.getElementsByName("LOCAL_TYPEs");
   for(var i=0;i<LOCAL_TYPE.length;i++){
       if(LOCAL_TYPE[i].checked){
          strType += LOCAL_TYPE[i].value+",";
       }
   }
  var len=strType.length;
  var strType=strType.substring(0,len-1);
  $("#LOCAL_TYPE").val(strType);
}

//选择终端信息
function selectTerm(){
	var CHANN_ID = $("#CHANN_ID").val();
	var CHANNS_CHARG = $("#CHANNS_CHARG").val();
	if(null == CHANN_ID || "" == CHANN_ID){
		parent.showErrorMsg("请先选择'渠道信息'！");
		return ;
	}
	$("#selTermParam").val(" STATE='启用' and DEL_FLAG=0 and  CHANN_ID_P IN "+CHANNS_CHARG+" and CHANN_ID_P='"+CHANN_ID+"'");
    selCommon('BS_174', false, 'TERM_ID', 'TERM_ID', 'forms[0]','TERM_NO,TERM_NAME,AREA_ID,AREA_NO,AREA_NAME,SALE_STORE_ID,SALE_STORE_NAME,ADDRESS,USE_AREA,BEG_SBUSS_DATE', 'TERM_NO,TERM_NAME,AREA_ID,AREA_NO,AREA_NAME,SALE_STORE_ID,SALE_STORE_NAME,ADDRESS,BUSS_AREA,BEG_SBUSS_DATE', 'selTermParam');
    var BEG_SBUSS_DATE = $("#BEG_SBUSS_DATE").val();
    var strPlanDate = BEG_SBUSS_DATE.split("-");
    $("#OPEN_SALE_YEAR").val(strPlanDate[0]);
}

function queryChannName(){

    selCommon('BS_136', false, 'CHANN_NAME', 'CHANN_NAME', 'forms[0]','AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,AREA_ID,AREA_NO,AREA_NAME,CHANN_ID,CHANN_NO,CHANN_NAME', 'AREA_MANAGE_ID,AREA_MANAGE_NAME,AREA_MANAGE_TEL,AREA_ID,AREA_NO,AREA_NAME,CHANN_ID,CHANN_NO,CHANN_NAME', 'selChannParam');
    var CHANN_ID = $("#CHANN_ID").val();
    var AREA_ID  = $("#AREA_ID").val();
    var AREA_NAME= $("#AREA_NAME").val();
    $.ajax({
	url: "decorationreit.shtml?action=loadModelT",
	type:"POST",
	dataType:"text",
	data:{"CHANN_ID":CHANN_ID,"AREA_ID":AREA_ID,"AREA_NAME":AREA_NAME},
	complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
		     var result = jsonResult.data;
		     $("#YEAR_GOODS_AMOUNT").val(result.DECT_AMOUNT);
		     $("#AREA_NAME").val(result.WAREA_NAME);
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	}
});
}

function  getRate(){
   var YEAR_GOODS_AMOUNT = $("#YEAR_GOODS_AMOUNT").val();
   var SALE_COMPACT_AMOUNT = $("#SALE_COMPACT_AMOUNT").val();
   var RATE = (YEAR_GOODS_AMOUNT/SALE_COMPACT_AMOUNT).toFixed(2);
   var QUARTER_RATE = RATE*100+"%";
   $("#QUARTER_RATE").val(QUARTER_RATE);
}


function getResiDue(){

   var BUDGET_QUOTA_ID = $("#BUDGET_QUOTA_ID").val();
   var CHANN_RNVTN_ID  = $("#CHANN_RNVTN_ID").val();
   $.ajax({
     url: "budgetquota.shtml?action=loadModelByQuota",
     type:  "POST",
     dataType:"text",
	 data:{"CHANN_RNVTN_ID":CHANN_RNVTN_ID,"BUDGET_QUOTA_ID":BUDGET_QUOTA_ID},
	 complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				var result = jsonResult.data;
				if(null != result && "" != result){
					var BUDGET_QUOTA =result.BUDGET_QUOTA;
					var USE_BUDGET_QUOTA = result.USE_BUDGET_QUOTA;
					var FREEZE_BUDGET_QUOTA = result.FREEZE_BUDGET_QUOTA;
					var RESIDUE = parseFloat(BUDGET_QUOTA)-parseFloat(USE_BUDGET_QUOTA)-parseFloat(FREEZE_BUDGET_QUOTA);
					var REIT_AMOUNT = $("#REIT_AMOUNT").val();
					if(eval(REIT_AMOUNT) > eval(RESIDUE)){
					   parent.showErrorMsg("当前最大可报销金额为：["+RESIDUE+"]");
					   $("#REIT_AMOUNT").val("");
					   return ;
					}
				} 
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	 });
}

function  getReitAmount(param){
   var REIT_AMOUNT = $("#REIT_AMOUNT").val();
   var REAL_REIT_AMOUNT  = $("#REAL_REIT_AMOUNT").val();
   if(eval(REAL_REIT_AMOUNT) > eval(REIT_AMOUNT)){
       parent.showErrorMsg("实际报销金额不能大于本次报销金额");
       return false;
   }
}

function checkAmount(param,label){
//定义正则表达式部分 
var reg = /^\d+$/; 
var val = param.value;
if( val.constructor == String ){ 
	var re = val.match( reg ); 
	if(!re){
	  parent.showErrorMsg(label+"请输入数字"); 
	  return false;
	}
	return true; 
} 
} 


/*
function  queryRate(){
    var CHANN_ID = $("#CHANN_ID").val();
    var AREA_ID  = $("#ARAE_ID").val();
    alert("AREA_ID====="+AREA_ID+"&AREA_NAME===="+AREA_NAME);
	var AREA_NAME= $("#AREA_NAME").val();
    $.ajax({
	url: "decorationreit.shtml?action=loadModelT",
	type:"POST",
	dataType:"text",
	data:{"CHANN_ID":CHANN_ID,"AREA_ID":AREA_ID,"AREA_NAME":AREA_NAME},
	complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
		     var result = jsonResult.data;
		     $("#YEAR_GOODS_AMOUNT").val(result.DECT_AMOUNT);
		     if(typeof(result.FNSH_RATE) !="undefined"){
		     $("#QUARTER_RATE").val(result.FNSH_RATE*100+'%');
		     }
		     //alert(result.YEAR_AMOUNT);
		     //$("#SALE_COMPACT_AMOUNT").val(result.YEAR_AMOUNT);
		}else{
			parent.showErrorMsg(utf8Decode(jsonResult.messages));
		}
	}
});
}*/
