$(function(){	 //主从及主从从列表页面通用加载方法	 //listPageInits("act.shtml?action=toList");	 $("#act_Btn").click(function(){	    begAccount();	 });});function begAccount(){    //校验参数	var vCHANN_ID=$("#CHANN_ID").val();	var vYEAR=$("#YEAR").val();	var vMONTH=$("#MONTH").val();	//if(vCHANN_ID==""||vCHANN_ID==null){	//  parent.showErrorMsg("请选择渠道!");	//  return false;	//}	if(vYEAR==""||vYEAR==null){	  parent.showErrorMsg("请选择年份!");	  return false;	}	if(vMONTH==""||vMONTH==null){	  parent.showErrorMsg("请选择月份!");	  return false;	}	chkAccount(vCHANN_ID,vYEAR,vMONTH);}function chkAccount(vCHANN_ID,vYEAR,vMONTH){	//判断是否符合月结条件	$.ajax({	 	url: "acctengi.shtml?action=chkAccount&CHANN_ID="+vCHANN_ID+"&YEAR="+vYEAR+"&MONTH="+vMONTH,	 	dataType:'json',	 	async:false,		type:"POST",		complete:function(xhr){		    eval("jsonResult = "+xhr.responseText);			if(jsonResult.success===true){			 //checkWCL(vFINANCIAL_CYCLE,vCYCLE_START,vCYCLE_END,vCYCLE_NAME)			 doAccount(vCHANN_ID,vYEAR,vMONTH);			}else			{			  parent.showErrorMsg(utf8Decode(jsonResult.messages));			}		}	});}function doAccount(vCHANN_ID,vYEAR,vMONTH){       showWaitPanel();       $.ajax({		 	     url: "acctengi.shtml?action=doAccount&CHANN_ID="+vCHANN_ID+"&YEAR="+vYEAR+"&MONTH="+vMONTH,		 	     dataType:'json',		 	     async:false,			     type:"POST",			     complete:function(xhr){			     eval("jsonResult = "+xhr.responseText);			     if(jsonResult.success===true){			        parent.showMsgPanel(utf8Decode(jsonResult.messages));				    window.location="acctengi.shtml?action=toList" + window.reqParamsEx();			     }else			     {			        parent.showErrorMsg(utf8Decode(jsonResult.messages));			        window.location="acctengi.shtml?action=toList" + window.reqParamsEx();			      }			   }	    });}function cancleAccount(vCHANN_ID,vYEAR,vMONTH,vSTATE){		if(vSTATE!='已生成')	{	  parent.showErrorMsg("当前年份、月份没有生成，不能取消!");	  return false;	}	//判断是否符合月结条件	$.ajax({	 	url: "acctengi.shtml?action=cancleAccount&CHANN_ID="+vCHANN_ID+"&YEAR="+vYEAR+"&MONTH="+vMONTH+"&STATE="+vSTATE,	 	dataType:'json',	 	async:false,		type:"POST",		complete:function(xhr){		    eval("jsonResult = "+xhr.responseText);			if(jsonResult.success===true){			    parent.showMsgPanel(utf8Decode(jsonResult.messages));				window.location="acctengi.shtml?action=toList" + window.reqParamsEx();			}else			{			    parent.showErrorMsg(utf8Decode(jsonResult.messages));			    window.location="acctengi.shtml?action=toList" + window.reqParamsEx();			}		}	});}function setSelEids(obj,ztid){    $(obj).attr("checked",'true');	//设置选中的Id	parent.document.getElementById("selRowId").value = $(obj).val();	//重复设置是为了修改时方便调用公用方法 reqParamsEx()	$("#selRowId").attr("value",$(obj).val());}