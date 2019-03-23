
/**
 *@module 渠道管理-装修管理
 *@func   装修申请单维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	
	//添加浮动按钮层的监听
     addFloatDivListener();
	 mtbSaveListener("decorationapp.shtml?action=edit&SelType="+$("#SelType").val(),"CHANN_RNVTN_ID");
	 
	 $("#commitTT").click(function(){
	    var SelType = $("#SelType").val();
	    var REIT_POLICY = $("#REIT_POLICY").val();
	    var COMPACT_AMOUNT = $("#COMPACT_AMOUNT").val();  //合同签订金额
	    var LOCAL_TYPE  = $("#LOCAL_TYPE").val();         //商场位置类别
	    var stadFlag = "";
	    var radio  = document.getElementsByName("radio"); //是否标准内
	    for(var i=0;i<radio.length;i++){
	       if(radio[i].checked){
	         stadFlag = radio[i].value;
	       }
	    }
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
	 
	    var REIT_AMOUNT_PS  = $("#REIT_AMOUNT_PS").val();  //每平米标准参考报销金额
	    var REIT_AMOUNT     = $("#REIT_AMOUNT").val();     //总标准参考报销金额
	    
	    $.ajax({
		url: "decorationapp.shtml?action=commitT",
		type:"POST",
		dataType:"text",
		data:{"strscale":strscale,"strremark":strremark,"CHANN_RNVTN_ID":CHANN_RNVTN_ID,"REIT_POLICY":REIT_POLICY,"SelType":SelType,"LOCAL_TYPE":LOCAL_TYPE,"COMPACT_AMOUNT":COMPACT_AMOUNT,"IS_STAD_FLAG":stadFlag,"REIT_AMOUNT_PS":REIT_AMOUNT_PS,"REIT_AMOUNT":REIT_AMOUNT},
		complete: function (xhr){
		    window.location.reload();
	 	}
	   });
	});
	
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
		    if(strscale != ""){
			    strscale = strscale.substr(0,strscale.length-1);
			    strremark = strremark.substr(0,strremark.length-1);
			    var SelType = $("#SelType").val();
			    
			    var COMPACT_AMOUNT = $("#COMPACT_AMOUNT").val();  //合同签订金额
			    var LOCAL_TYPE  = $("#LOCAL_TYPE").val();         //商场位置类别
			    var stadFlag = "";
			    var radio  = document.getElementsByName("radio"); //是否标准内
			    for(var i=0;i<radio.length;i++){
			       if(radio[i].checked){
			          stadFlag = radio[i].value;
			       }
			    }
			    var REIT_AMOUNT_PS  = $("#REIT_AMOUNT_PS").val();  //每平米标准参考报销金额
			    var REIT_AMOUNT     = $("#REIT_AMOUNT").val();     //总标准参考报销金额
			    
			    $.ajax({
				url: "decorationapp.shtml?action=commitT",
				type:"POST",
				dataType:"text",
				data:{"strscale":strscale,"strremark":strremark,"CHANN_RNVTN_ID":CHANN_RNVTN_ID,"REIT_POLICY":REIT_POLICY,"SelType":SelType,"LOCAL_TYPE":LOCAL_TYPE,"COMPACT_AMOUNT":COMPACT_AMOUNT,"IS_STAD_FLAG":stadFlag,"REIT_AMOUNT_PS":REIT_AMOUNT_PS,"REIT_AMOUNT":REIT_AMOUNT},
				complete: function (xhr){
				    window.location.reload();
			 	}
			   });
			} else {
				 parent.showErrorMsg("请输入报销比例");
				 return ;
			}
		//}  
	});
});

function getReitAmount(){
  var REIT_AMOUNT_PS = $("#REIT_AMOUNT_PS").val();
  var reg = new RegExp("^[0-9]*$");   
  if(!reg.test(REIT_AMOUNT_PS)){   
      parent.showErrorMsg("请输入数字!");
      $("#REIT_AMOUNT_PS").val("");  
      $("#REIT_AMOUNT").val("");  
      return; 
  }   
  if(!/^[0-9]*$/.test(REIT_AMOUNT_PS)){   
      parent.showErrorMsg("请输入数字!");  
      $("#REIT_AMOUNT_PS").val(""); 
      $("#REIT_AMOUNT").val("");
      return; 
  }   
  var USE_AREA = $("#USE_AREA").val();
  var total = parseFloat(REIT_AMOUNT_PS)*parseFloat(USE_AREA);
  $("#REIT_AMOUNT").val(total);
}

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

function  check1(params){
   var value = params.value;
   var reg =/^0\.\d+$/;
   if(!reg.test(value)){
     parent.showErrorMsg("报销比例需要大于0小于1的小数");
     params.value="";
     return;
   }
}

function  checkT1(params){
  var value = document.getElementById("RNVTN_SCALET"+params).value;
  if(value==""){
    parent.showErrorMsg("请输入报销比例");
    document.getElementById("RNVTN_SCALET"+params).value="";
  }
}

function formCheckedEx() {
   
   var DESIGN_PERSON = $("#DESIGN_PERSON").val();
   if(DESIGN_PERSON==""){
       chkCanErrMsg("", "请选择'设计师'");
	   return false;
   }
   
   	var LOCAL_TYPE ="";
	var RNVTN_PROP ="";
	var BUSS_SCOPE ="";
	
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
	return true;
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
           $("#showDivTT").show();
           $("#showDiv").hide();
           $("#showDivT").hidden();
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
/*
function  load(){
   var BEAR_WAY = $("#BEAR_WAYT").val();
   var way = document.getElementsByName("BEAR_WAY");
   for(var i=0;i<way.length;i++){
       if(BEAR_WAY==way[i].value){
           way[i].checked=true;
       }
   }
   var IS_STAD_FLAG  = document.getElementsByName("IS_STAD_FLAG");
   var IS_STAD_FLAGT = $("#IS_STAD_FLAGT").val();
   for(var j=0;j<IS_STAD_FLAG.length;j++){
       if(IS_STAD_FLAG[j].value = IS_STAD_FLAGT){
           IS_STAD_FLAG[j].checked=true;
       }
   }
   
   for(var i=0;i<way.length;i++){
     if(way[i].checked){
     if(way[i].value=="1"){
         $("#showTrt").show();
         $("#showTrt1").hide();
         return;
     } if(way[i].value=="2"){
         $("#showTrt").hide();
         $("#showTrt1").show();
         return;
        }
     }
   }
}*/

function chk(){
   var IS_STAD_FLAGs = document.getElementsByName("IS_STAD_FLAGs");
   for(var i=0;i<IS_STAD_FLAGs.length;i++){
       if(IS_STAD_FLAGs[i].checked){
          $("#IS_STAD_FLAG").val(IS_STAD_FLAGs[i].value);
       }
   }
}

function check(){
   var way =  document.getElementsByName("BEAR_WAYs");
   for(var i=0;i<way.length;i++){
     if(way[i].checked){
     //alert(way[i].value);
     if(way[i].value=="1"){
         $("#showTr").show();
         $("#showTr1").hide();
         $("#BEAR_WAY").val(1);
         return;
     } if(way[i].value=="2"){
         $("#showTr").hide();
         $("#showTr1").show();
         $("#BEAR_WAY").val(2);
         return;
        }
     }
   }
}

function  getBussScope(){
  var str = "";
  var BUSS_SCOPE = document.getElementsByName("BUSS_SCOPEs");
  for(var i=0;i<BUSS_SCOPE.length;i++){
      if(BUSS_SCOPE[i].checked){
          str += BUSS_SCOPE[i].value+",";
      }
  }
  str = str.substr(0,str.length-1);
  $("#BUSS_SCOPE").val(str);
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
   var LOCAL_TYPE = document.getElementsByName("LOCAL_TYPEs");
   for(var i=0;i<LOCAL_TYPE.length;i++){
       if(LOCAL_TYPE[i].checked){
          $("#LOCAL_TYPE").val(LOCAL_TYPE[i].value);
       }
   }
}

 
