
/**
 *@module 渠道管理-装修管理
 *@func   装修申请单维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("channTermianl.shtml?action=edit","TERMINAL_QUOTA_ID");
	
	$("#commitT").click(function(){
	    var REIT_POLICY = $("#REIT_POLICY").val();
	    parent.topcontent.document.getElementById("POLICY").value=REIT_POLICY;
	    var CHANN_RNVTN_ID = $("#CHANN_RNVTN_ID").val();
	    var strscale  = "";
	    var strremark = "";
	    var scale  = document.getElementsByName("RNVTN_SCALE");
	    var remark = document.getElementsByName("REIT_REMARK");
	    for(var i=0;i<scale.length;i++){
	        if(scale[i].value!=""){
	           strscale += scale[i].value+",";
	        }
	    }
	    for(var j=0;j<remark.length;j++){
	        if(remark[j].value!=""){
	           strremark += remark[j].value+",";
	        }
	    }
	    strscale = strscale.substr(0,strscale.length-1);
	    strremark = strremark.substr(0,strremark.length-1);
	    var SelType = $("#SelType").val();
	    $.ajax({
		url: "decorationapp.shtml?action=commitT",
		type:"POST",
		dataType:"text",
		data:{"strscale":strscale,"strremark":strremark,"CHANN_RNVTN_ID":CHANN_RNVTN_ID,"REIT_POLICY":REIT_POLICY,"SelType":SelType},
		complete: function (xhr){
		    window.location.reload();
	 	}
	   });
	});
});

function  check(params){
   var value = params.value;
   var reg =/^0\.\d+$/;
   if(!reg.test(value)){
     parent.showErrorMsg("报销比例需要大于0小于1的小数");
     params.value="";
     return;
   }
}

function  checkT(params){
  var value = document.getElementById("RNVTN_SCALET"+params).value;
  if(value==""){
    parent.showErrorMsg("请输入报销比例");
    document.getElementById("RNVTN_SCALET"+params).value="";
  }
}

function formCheckedEx() {
	var storeType = $("#BUSS_SCOPE").val();
	if (storeType == "") {
		chkCanErrMsg("", "请选择'经营品牌'");
		return false;
	}
	
	var storeTypeT = $("#RNVTN_PROP").val();
	if (storeTypeT == "") {
		chkCanErrMsg("", "请选择'装修性质'");
		return false;
	}
	return true;
}

function  total(param){
    
   var BUSS_SCOPE = $("#BUSS_SCOPE").val();
   var TERM_WHICH_NUM = $("#TERM_WHICH_NUM").val();
   var LOCAL_TYPE = param.value;
   var USE_AREA   = $("#USE_AREA").val();
   var bxMoney = "";
   $.ajax({
		url: "decorationapp.shtml?action=query",
		type:"POST",
		dataType:"text",
		data:{"BUSS_SCOPE":BUSS_SCOPE,"TERM_WHICH_NUM":TERM_WHICH_NUM,"LOCAL_TYPE":LOCAL_TYPE},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			var str = utf8Decode(jsonResult.messages);
			var array = str.split(",");
			document.getElementById("minArea").value=array[0];
			document.getElementById("stdArea").value=array[1];
			document.getElementById("amount").value=array[2];
			document.getElementById("dtldes").value=array[3];
			var USE_AREA = $("#USE_AREA").val();
			if(eval(USE_AREA)<eval(array[0])){
			  chkCanErrMsg("", "小于最低面积的不给报销");
			}
			else if(eval(USE_AREA)>=eval(array[0]) && eval(USE_AREA)<eval(array[1])){
			  bxMoney = array[2]*0.8;
			} 
			else if(eval(USE_AREA) >= eval(array[1])){
			  bxMoney = array[2];
			}
			if(array[3]!=""){
			   array[3] = parseInt(1)+parseFloat(array[3]);
			}
			var REIT_AMOUNT_PS = bxMoney*array[3];
			document.getElementById("REIT_AMOUNT_PS").value=parseFloat(REIT_AMOUNT_PS);
			document.getElementById("REIT_AMOUNT").value=parseFloat(REIT_AMOUNT_PS*USE_AREA);
		}
	}); 
}

function chk(){
   var reg1 =  /^\d+$/; 
   var QUOTA_NUM = $("#QUOTA_NUM").val();
   if(QUOTA_NUM.match(reg1) == null){
       parent.showErrorMsg("数量必须为正整数");
       $("#QUOTA_NUM").val("");
       //$("#QUOTA_NUM").focus();
       return;
   }  
}

function  CompareDate(){
	//计划开业时间
	var PLAN_SBUSS_DATE = $("#PLAN_SBUSS_DATE").val();
	if(null == PLAN_SBUSS_DATE || "" == PLAN_SBUSS_DATE){
		return;
	}
	PLAN_SBUSS_DATE = PLAN_SBUSS_DATE.replace("-","/");//替换字符，变成标准格式
	var d1 = new Date(Date.parse(PLAN_SBUSS_DATE));
	var d2=new Date();    //取今天的日期
	if(d1<d2){
	    parent.showErrorMsg("计划开业时间应该不小于当前日期");
		$("#PLAN_SBUSS_DATE").val("");
		return ;
	} 
}

function  CompareDateT(){

    var PLAN_SBUSS_DATE = $("#PLAN_SBUSS_DATE").val();
	if(null == PLAN_SBUSS_DATE || "" == PLAN_SBUSS_DATE){
		return;
	}
	//要求出图时间
	var DMD_DRAW_DATE   = $("#DMD_DRAW_DATE").val();
	if(null == DMD_DRAW_DATE   || "" == DMD_DRAW_DATE){
		return;
	}
    PLAN_SBUSS_DATE = PLAN_SBUSS_DATE.replace("-","/"); 
    DMD_DRAW_DATE   = DMD_DRAW_DATE.replace("-","/");  
	if(PLAN_SBUSS_DATE<DMD_DRAW_DATE){
	    parent.showErrorMsg("要求出图时间应该小于计划开业时间");
		$("#DMD_DRAW_DATE").val("");
		return ;
	} 
}

function change(){
     var REIT_POLICYT = $("#REIT_POLICYT").val();
    var REIT_POLICY  = $("#REIT_POLICY").val();
    if(REIT_POLICY=="常规政策"){
       if(REIT_POLICYT==""){
            $("#SelType").val("5");
	        return;
       }
       if(REIT_POLICYT=="常规政策"){
	        $("#SelType").val("2");
	        return;
       }
       if(REIT_POLICYT=="特殊政策"){
           $("#SelType").val("4");
           return;
       }
    }
    
    if(REIT_POLICY=="特殊政策"){
       if(REIT_POLICYT==""){
           $("#SelType").val("6");
	       return;
       } 
       if(REIT_POLICYT=="常规政策"){
           $("#SelType").val("1");
           return;
       }
       if(REIT_POLICYT=="特殊政策"){
           $("#SelType").val("3");
           return;
       }
    }
 }
 
function  fun(){
 
    var REIT_POLICYT = $("#REIT_POLICYT").val();
    var REIT_POLICY  = $("#REIT_POLICY").val();
    if(REIT_POLICY=="常规政策"){
       if(REIT_POLICYT==""){
            $("#SelType").val("5");
	        $("#showDiv").show();
	        $("#showDivT").hide();
	        return;
       }
       if(REIT_POLICYT=="常规政策"){
	        $("#SelType").val("2");
	        $("#showDiv").show();
	        $("#showDivT").hide();
	        return;
       }
       if(REIT_POLICYT=="特殊政策"){
           $("#SelType").val("4");
           $("#showDiv").show();
	       $("#showDivT").hide();
           return;
       }
    }
    
    if(REIT_POLICY=="特殊政策"){
       if(REIT_POLICYT==""){
           $("#SelType").val("6");
	       $("#showDivT").show();
	       $("#showDiv").hide();
	       return;
       } 
       if(REIT_POLICYT=="常规政策"){
           $("#SelType").val("1");
           $("#showDivT").show();
           $("#showDiv").hide();
           return;
       }
       if(REIT_POLICYT=="特殊政策"){
           $("#SelType").val("3");
           $("#showDivT").show();
           $("#showDiv").hide();
           return;
       }
    }
    if(REIT_POLICY=="")
    {
      $("#showDivT").hide();
      $("#showDiv").hide();
      parent.showErrorMsg("请选择报销频次");
    }
 }
 
function  getMsg(){
    //主表form校验
	var parentCheckedRst = parent.topcontent.formChecked('mainForm');
	if(!parentCheckedRst){
		return;
	}
    var BUSS_SCOPE = $("#BUSS_SCOPE").val();
    var TERM_WHICH_NUM = $("#TERM_WHICH_NUM").val();
    var LOCAL_TYPE = $("#LOCAL_TYPE").val();
    var USE_AREA   = $("#USE_AREA").val();
    if(BUSS_SCOPE!="" && TERM_WHICH_NUM!="" && LOCAL_TYPE!="" && USE_AREA!=""){
      var minArea = document.getElementById("minArea").value;
      if(eval(USE_AREA)<eval(minArea)){
			  chkCanErrMsg("", "小于最低面积的不给报销");
	  }
	  var bxMoney = "";
      $.ajax({
		url: "decorationapp.shtml?action=query",
		type:"POST",
		dataType:"text",
		data:{"BUSS_SCOPE":BUSS_SCOPE,"TERM_WHICH_NUM":TERM_WHICH_NUM,"LOCAL_TYPE":LOCAL_TYPE},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			var str = utf8Decode(jsonResult.messages);
			var array = str.split(",");
			document.getElementById("minArea").value=array[0];
			document.getElementById("stdArea").value=array[1];
			document.getElementById("amount").value=array[2];
			document.getElementById("dtldes").value=array[3];
			var USE_AREA = $("#USE_AREA").val();
			if(eval(USE_AREA)<eval(array[0])){
			  chkCanErrMsg("", "小于最低面积的不给报销");
			}
			else if(eval(USE_AREA)>=eval(array[0]) && eval(USE_AREA)<eval(array[1])){
			  bxMoney = array[2]*0.8;
			} 
			else if(eval(USE_AREA) >= eval(array[1])){
			  bxMoney = array[2];
			}
			if(array[3]!=""){
			   array[3] = parseInt(1)+parseFloat(array[3]);
			}
			var REIT_AMOUNT_PS = bxMoney*array[3];
			document.getElementById("REIT_AMOUNT_PS").value=REIT_AMOUNT_PS;
			document.getElementById("REIT_AMOUNT").value=REIT_AMOUNT_PS*USE_AREA;
		}
	});
   }
}

function check(){
   var way =  document.getElementsByName("BEAR_WAY");
   for(var i=0;i<way.length;i++){
     if(way[i].checked){
     //alert(way[i].value);
     if(way[i].value=="1"){
         $("#showTr").show();
         $("#showTr1").hide();
         return;
     } if(way[i].value=="2"){
         $("#showTr").hide();
         $("#showTr1").show();
         return;
        }
     }
   }
}
