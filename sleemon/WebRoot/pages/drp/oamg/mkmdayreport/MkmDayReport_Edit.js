
/**
 *@module 渠道管理-上报管理
 *@func   营销经理日报维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	//addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("mkmdayreport.shtml?action=edit","MKM_DAY_RPT_ID");
	//下帧跳转
	//parent.window.gotoBottomPage();
 
});


//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedEx(){

   var VST_DATE   = $("#VST_DATE").val();
   var REPORT_DATE= $("#REPORT_DATE").val();
   if(DateDiff(REPORT_DATE,VST_DATE)>= 5) 
   { 
	parent.showErrorMsg("拜访日期不得小于当前日期5天"); 
	return false; 
   } 
   return true;
    /*
    var RNVTN_PROP= $("#RNVTN_PROP").val();
	//详细地址 
	if($("#ADDRESS").val()==null || $("#ADDRESS").val() == ""){
	      parent.showErrorMsg(utf8Decode("请填入'详细地址'！"));
	      return false;
	}
	if(RNVTN_PROP=="" || RNVTN_PROP == null){
	      parent.showErrorMsg(utf8Decode("请选择'装修性质'！"));
	      return false; 
	}
	if($("#TERM_TYPE").val()==null || $("#TERM_TYPE").val()==""){
	      parent.showErrorMsg(utf8Decode("请选择'终端类型'！"));
	      return false;
	}
	if($("#TERM_VERSION").val()==null || $("#TERM_VERSION").val()== ""){
	    parent.showErrorMsg(utf8Decode("请选择'终端版本'！"));
	    return false; 
	}
	if($("#CITY_TYPE").val()==null || $("#CITY_TYPE").val()==""){
	    parent.showErrorMsg(utf8Decode("请选择'城市类型'！"));
	    return false; 
	}
	if($("#BEG_BUSS_TYPE").val()==null || $("#BEG_BUSS_TYPE").val()==""){
	    parent.showErrorMsg(utf8Decode("请选择'开店类型'！"));
	    return false;
	}
	if($("#LOCAL_TYPE").val()==null || $("#LOCAL_TYPE").val()==""){
	    parent.showErrorMsg(utf8Decode("请选择'商城位置类别'！"));
	    return false;
	}*/
	return true;
}


function  getTotal(){
    
    var MATT_AMOUNT_STOCKT;
    var SOFT_BED_AMOUNT_STOCKT ;
    var OTHER_AMOUNT_STOCKT ;
    var TOTAL_AMOUNT_STOCKT   ;
    
    var MATT_AMOUNT_STOCKT = $("#MATT_AMOUNT").val();   //床垫
    if(MATT_AMOUNT_STOCKT==""){
       MATT_AMOUNT_STOCKT = 0; 
    } else{
       MATT_AMOUNT_STOCKT = parseInt(MATT_AMOUNT_STOCKT);
    }
    var SOFT_BED_AMOUNT_STOCK      = $("#SOFT_BED_AMOUNT").val();        //软床
    if(SOFT_BED_AMOUNT_STOCK==""){
       SOFT_BED_AMOUNT_STOCKT = 0;
    } else {
       SOFT_BED_AMOUNT_STOCKT      = parseInt(SOFT_BED_AMOUNT_STOCK);
    }
    var OTHER_AMOUNT_STOCK  = $("#OTHER_AMOUNT").val();    //床头柜
    if(OTHER_AMOUNT_STOCK==""){
       OTHER_AMOUNT_STOCKT = 0;
    } else{
       OTHER_AMOUNT_STOCKT  = parseInt(OTHER_AMOUNT_STOCK);
    }
    var TOTAL_AMOUNT_STOCKT = MATT_AMOUNT_STOCKT+SOFT_BED_AMOUNT_STOCKT+OTHER_AMOUNT_STOCKT;
    document.getElementById("TOTAL_AMOUNT").value = TOTAL_AMOUNT_STOCKT;
}


function getRate(){
   
   var ADVC_DEAL_AMOUNT = $("#ADVC_DEAL_AMOUNT").val();
   if(ADVC_DEAL_AMOUNT ==""){
     ADVC_DEAL_AMOUNT = 0;
   }
   var COST_AMOUNT = $("#COST_AMOUNT").val();
   if(COST_AMOUNT ==""){
     COST_AMOUNT  = 0;
   }
   if(ADVC_DEAL_AMOUNT !="" && COST_AMOUNT !=""){
     var RATE_AMOUNT = (ADVC_DEAL_AMOUNT/COST_AMOUNT).toFixed(2);
     $("#RATE_AMOUNT").val(RATE_AMOUNT);
   } 
}

function checkVstDate(param){
   var VST_DATE   = param.value;
   var REPORT_DATE= $("#REPORT_DATE").val();
   if(DateDiff(REPORT_DATE,VST_DATE)>= 5) 
   { 
	parent.showErrorMsg("拜访日期不得小于当前日期5天"); 
	return false; 
   } 
   return true;
}

//计算天数差的函数，通用 
function DateDiff(sDate1,sDate2) 
{  
	var aDate,oDate1,oDate2,iDays;
	aDate=sDate1.split("-"); 
	oDate1=new Date(aDate[1]+ '-'+aDate[2]+ '-'+aDate[0]);  
	aDate=sDate2.split("-"); 
	oDate2=new Date(aDate[1]+ '-'+aDate[2]+ '-'+aDate[0]);
	iDays = parseInt(Math.abs(oDate1 -oDate2)/1000/60/60/24);  
	if(oDate1 >oDate2) 
	{ 
	return iDays; 
	} 
	else 
	{ 
	return -iDays ; 
	} 
} 
