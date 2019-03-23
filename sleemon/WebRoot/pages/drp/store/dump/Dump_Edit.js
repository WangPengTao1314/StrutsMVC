/**
 * @prjName:喜临门营销平台
 * @fileName:Dump_Edit
 * @author zzb
 * @time   2013-09-05 14:00:34 
 * @version 1.1
 */
$(function(){
    //下帧跳转
	parent.window.gotoBottomPage();
	//初始化校验
	InitFormValidator(0);	
});

//如果需要额外的业务校验 只需在该页面增加方法 formCheckedEx();
function formCheckedType(){
	var BILL_TYPE = $("#BILL_TYPE").val();
	if (BILL_TYPE == "") {
		chkCanErrMsg("", "请选择'单据类型'");
		return false;
	}	
	return true;
}


   //单据类型     如果选择‘库内转储'　选择出库库房编号   带出转入库房编号 两者一致。
   function changeBillType(obj){
	   var v = $(obj).val();
	   if("库内转储" == v){
		   $("#selInStore").attr("title","不可编辑");
//		   $("#selectStoreParams").val("  STATE='启用' and DEL_FLAG=0 and STORAGE_FLAG=1 ");
		   $("#selInStore").attr("disabled","disabled");
	   }else{
		   $("#selInStore").removeAttr("title");
		   $("#selInStore").removeAttr("disabled");
		   $("#STORAGE_FLAG2").val(null);
		   $("#DUMP_IN_STORE_ID").val(null);
	       $("#DUMP_IN_STORE_NO").val(null);
	       $("#DUMP_IN_STORE_NAME").val(null);
	       
	   }
	   
	   setInstoreVal();
   }
   
   //
   //通用选取选择　转出库房编号，转出库房名称　时，把值赋给　转入库房编号，转入库房名称
   function setInstoreVal(){
	   var v = $("#BILL_TYPE").val();
	   if("库内转储" == v){
		  $("#STORAGE_FLAG2").val($("#DUMP_OUT_STORAGE_FLAG").val());
		  $("#DUMP_IN_STORE_ID").val($("#DUMP_OUT_STORE_ID").val());
	      $("#DUMP_IN_STORE_NO").val($("#DUMP_OUT_STORE_NO").val());
	      $("#DUMP_IN_STORE_NAME").val($("#DUMP_OUT_STORE_NAME").val());
	   }else{
		   
	   }
	   //转出库房通用选取时    改变下帧的 库位是否影藏
	   parent.bottomcontent.hideAllTd();
	   var DUMP_OUT_STORE_ID_OLD = $("#DUMP_OUT_STORE_ID_OLD").val();
	   var DUMP_OUT_STORE_ID = $("#DUMP_OUT_STORE_ID").val();
	   if(null != DUMP_OUT_STORE_ID_OLD && "" != DUMP_OUT_STORE_ID_OLD && DUMP_OUT_STORE_ID_OLD != DUMP_OUT_STORE_ID){
		     //清除子表的内容
	        clrarAllChild();
	   }
	   
   }
   
   function clearBottmOutKUWEIval(){
	   //清除 转出库位的 value
	   parent.bottomcontent.clearOutKUWEIval();
   }
    //转入库房通用选取时    改变下帧的 库位是否影藏
   function inStoreChange(){
	   parent.bottomcontent.hideAllTd();
	   //清除 转入库位的 value
	   parent.bottomcontent.clearInKUWEIval();
   }
   //转出库房通用选取时 改变  转出库位管理标记
   function checkOutFlag(obj){
	 var flag =  $(obj).val();
	 if(1==flag){
		$("#OUT_FLAG_YES").attr("checked","checked");
	 } 
	 if(0==flag){
		 $("#OUT_FLAG_NO").attr("checked","checked");
	 }
	 
   }
   
   //转入库房通用选取时 改变  转入库位管理标记
   function checkINFlag(obj){
	 var flag =  $(obj).val();
	 if(1==flag){
		 $("#IN_FLAG_YES").attr("checked","checked");
	 } 
	 if(0==flag){
		 $("#IN_FLAG_NO").attr("checked","checked");
	 }
	 
	 inStoreChange();
  }
  
   
//转出库房
function selTellOutStore(){
	var TERM_ID = $("#TERM_ID").val();
	var ZTXXID = $("#ZTXXID").val();
//	var TERM_CHARGE = $("#TERM_CHARGE").val();
	var tellOutparams = "";
//	var TERM_CHARGE="(select TERM_ID from BASE_TERMINAL  where TERM_NO = '"+ZTXXID+"' or CHANN_NO_P = '"+ZTXXID+"') ";
//	if(null != TERM_ID  && "" != TERM_ID){
//		//终端用户选择转出库房只能选自己的
//		tellOutparams = " STATE ='启用' and DEL_FLAG=0 and  BEL_CHANN_ID='"+TERM_ID+"' ";
//	}else{
		tellOutparams = " STATE ='启用' and DEL_FLAG=0 and  LEDGER_ID='"+ZTXXID+"' ";
//	}
	$("#tellOutparams").val(tellOutparams);
	
	selCommon('BS_35', false, 'DUMP_OUT_STORE_ID', 'STORE_ID', 'forms[0]','DUMP_OUT_STORE_NO,DUMP_OUT_STORE_NAME', 'STORE_NO,STORE_NAME', 'tellOutparams')
}



//转入库房
function selTellInStore(){
	var TERM_ID = $("#TERM_ID").val();
	var ZTXXID = $("#ZTXXID").val();
//	var TERM_CHARGE = $("#TERM_CHARGE").val();
	var TERM_CHARGE="(select TERM_ID from BASE_TERMINAL  where TERM_NO = '"+ZTXXID+"' or CHANN_NO_P = '"+ZTXXID+"') ";
	var tellInparams = "";
//	if(null != TERM_ID  && "" != TERM_ID){
//		//终端人员选择转入库房能选整个渠道的
//		tellInparams = " STATE ='启用' and DEL_FLAG=0 and  LEDGER_ID='"+ZTXXID+"' ";
//	}else{
		//渠道人员只能选本渠道和分管的终端的库房
		tellInparams = " STATE ='启用' and DEL_FLAG=0 and LEDGER_ID='"+ZTXXID+"' ";
//	}
	$("#tellInparams").val(tellInparams);
	
	selCommon('BS_35', false, 'DUMP_IN_STORE_ID', 'STORE_ID', 'forms[0]','DUMP_IN_STORE_NO,DUMP_IN_STORE_NAME', 'STORE_NO,STORE_NAME', 'tellInparams')
}

//选择关联单据自动带出明细
function addChild(){
	var RELT_BILL_NO_OLD = $("#RELT_BILL_NO_OLD").val();
	var RELT_BILL_NO = $("#RELT_BILL_NO").val();
	//验证是否选择转出，转入库房
	//验证转出，转入库房是否和关联单据转入转出库房对应
 	var DUMP_OUT_STORE_ID=$("#DUMP_OUT_STORE_ID").val();
 	if(""==DUMP_OUT_STORE_ID||null==DUMP_OUT_STORE_ID){
 		 parent.showErrorMsg(utf8Decode("请选择出库库房！"));
	     return ;
 	}
 	var DUMP_IN_STORE_ID=$("#DUMP_IN_STORE_ID").val();
 	if(""==DUMP_IN_STORE_ID||null==DUMP_IN_STORE_ID){
 		 parent.showErrorMsg(utf8Decode("请选择出库库房！"));
	     return ;
 	}
 	var OLD_IN_STORE_ID=$("#OLD_IN_STORE_ID").val();
 	var OLD_OUT_STORE_ID=$("#OLD_OUT_STORE_ID").val();
 	if(""!=OLD_IN_STORE_ID&&null!=OLD_IN_STORE_ID&&""!=OLD_OUT_STORE_ID&&null!=OLD_OUT_STORE_ID){
 		if(DUMP_OUT_STORE_ID!=OLD_IN_STORE_ID||DUMP_IN_STORE_ID!=OLD_OUT_STORE_ID){
 			$("#RELT_BILL_NO").val("");
 			$("#OLD_IN_STORE_ID").val("");
 			$("#OLD_OUT_STORE_ID").val("");
 			parent.showErrorMsg(utf8Decode("转出转入库房与关联单据库房不匹配，请重新选择！"));
	     	return ;
 		}
 	}
 	
//	if(null != RELT_BILL_NO_OLD && "" !=RELT_BILL_NO_OLD){
//		if(RELT_BILL_NO_OLD == RELT_BILL_NO){
//			return;
//		}
//		
//	}
	var MX_IDS = "";
	$.ajax({
		url: "dump.shtml?action=modifyReltToChildEdit",
		type:"POST",
		dataType:"text",
		data:{"RELT_BILL_NO":RELT_BILL_NO},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				  var MX_IDS_OLD = $("#MX_IDS").val();
				  if(null != MX_IDS_OLD && "" != MX_IDS_OLD){
					 parent.bottomcontent.reltChildDelete(MX_IDS_OLD);
				  }
				  
				 var data = jsonResult.data;
				 $.each(data,function(i,rst){
					 var RELT_BILL_DTL_ID = nullToString(rst.RELT_BILL_DTL_ID);
					 MX_IDS = MX_IDS+RELT_BILL_DTL_ID+","
					   	var arrs = [
				          '',
			              rst.PRD_ID,
			              rst.PRD_NO,
			              rst.PRD_NAME,
			              nullToString(rst.PRD_SPEC),
			              nullToString(rst.PRD_COLOR),
			              nullToString(rst.BRAND),
			              nullToString(rst.STD_UNIT),
			              rst.DUMP_OUT_STORG_ID,
			              rst.DUMP_OUT_STORG_NO,
			              rst.DUMP_OUT_STORG_NAME,
			              rst.DUMP_IN_STORG_ID,
			              rst.DUMP_IN_STORG_NO,
			              rst.DUMP_IN_STORG_NAME,
			              rst.DUMP_NUM,
			              nullToString(rst.REMARK),
			              nullToString(rst.SPCL_TECH_ID),
			              nullToString(rst.SPCL_TECH_FLAG),
			              rst.SAFE_NUM,// 18 的库存量（实时库存-冻结库存）
			              RELT_BILL_DTL_ID
			    
			            ];
				     parent.bottomcontent.addRow(arrs);
				 });
				  MX_IDS = MX_IDS.substr(0,MX_IDS.length-1);
				  $("#MX_IDS").val(MX_IDS);
			}else{
				parent.showErrorMsg(utf8Decode(jsonResult.messages));
			}
		}
	});
	
}

function nullToString(obj){
	if(null == obj){
		return "";
	}
	if("null" == obj){
		return "";
	}
	return obj
}

function writeObj(obj){ 
    var description = ""; 
    for(var i in obj){   
        var property=obj[i];   
        description+=i+" = "+property+"\n";  
    }   
    alert(description); 
} 
       
//转出库房ID
function getOutStoreId(){
	return $("#DUMP_OUT_STORE_ID").val();
}
//转出库房管理标记
function getOutFlag(){
	return $("#DUMP_OUT_STORAGE_FLAG").val();
}

//转入库房ID
function getInStoreId(){
	return $("#DUMP_IN_STORE_ID").val();
}
//转入库房管理标记
function getInFlag(){
	return $("#STORAGE_FLAG2").val();
}

//单据类型
function getBillType(){
	return $("#BILL_TYPE").val();
}

//清除字表的数据
function clrarAllChild(){
	parent.bottomcontent.clrarAllChild();
}

