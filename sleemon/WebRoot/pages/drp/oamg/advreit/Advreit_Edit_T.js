
/**
 *@module 营销管理-广告投放管理
 *@func   广告投放报销申请单维护
 *@version 1.1
 *@author zcx
 */
$(function(){
	//初始化校验
	InitFormValidator(0);
	//添加浮动按钮层的监听
	addFloatDivListener();
	//保存监听。参数1：请求Url，参数2：主键Id，参数3：上帧url.保存成功后需要刷新上帧
	mtbSaveListener("advreit.shtml?action=edit","ERP_ADV_REIT_ID");
	
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
	    $.ajax({
		url: "decorationapp.shtml?action=commitT",
		type:"POST",
		dataType:"text",
		data:{"strscale":strscale,"strremark":strremark,"CHANN_RNVTN_ID":CHANN_RNVTN_ID,"REIT_POLICY":REIT_POLICY},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
		    window.location.reload();
	 	}
	   });
	});
});

function selAdvcorderT(){
     var path = $("#ATT_PATH").val();
     var strs= new Array();   //定义一数组
     strs=path.split(",");    //字符分割      
     var str="";
     if(strs.length>1){
       for(var i=0;i<strs.length;i++){
          str+="SAMPLE_DIR,";
     }
     var str = str.substring(0, str.lastIndexOf(','));
     } else {
          str="SAMPLE_DIR";
     }
     displayDownFile('ATT_PATH',str,true,false);
     var ERP_ADV_REQ_ID = $("#ERP_ADV_REQ_ID").val();
     $.ajax({
		url: "advreit.shtml?action=getReitAmount",
		type:"POST",
		dataType:"text",
		data:{"ERP_ADV_REQ_ID":ERP_ADV_REQ_ID},
		complete: function (xhr){
			eval("jsonResult = "+xhr.responseText);
			var str = utf8Decode(jsonResult.messages);
			document.getElementById("HAS_REIM_AMOUNT").value = str;
	 	}
	   }); 
}