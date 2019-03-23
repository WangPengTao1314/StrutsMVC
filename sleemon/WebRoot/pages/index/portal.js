/**
 * @prjName:喜临门营销平台
 * @fileName:分销首页
 * @author zzb
 * @time   2013-08-30 15:55:09 
 * @version 1.1
 */

$(function(){
	$("#ul_notice li").eq(0).css("background","#ffefd3");
	
	$("#news_c").hover(
		function(){
			
		},
		function(){
			//$("#ul_notice li").eq(0).css("background","#ffefd3");
			clearColor();
			var title = $.trim($("#h3_notice_title").text());
			$("#ul_notice li a").each(function(){
				var t = $.trim($(this).text());
				if(t==title){
					$(this).parent().parent().css("background","#ffefd3");
				}
			});
		}
	);
	
  $("#ul_notice li").hover(
    	function(){
    		clearColor();
    		$("#ul_notice li").eq(0).css("background","#F8F7F2");//灰色
    		$(this).css("background","#ffefd3");
    		
    	},
    	function(){
    		$(this).css("background","#F8F7F2");//灰色
    	}
    );
    
    $("a[name='a_title_']").click(function(){
    	showNoticeContent(this);
    });
  
    queryCount();
    
    $("#graph").hide();
    
    //查询 待发货预订单 待入库 待退货
    toShowPage();
    //add by zhuxw
    //总部出货
    $("#sale_report").click(function(){
    	 $("#report_frame").attr("src","scripts/flex/Graphic_b.html"); 
    });
    //总部订货
    $("#advc_report").click(function(){
    	 $("#report_frame").attr("src","scripts/flex/HeadquartersOrderAmount.html"); 
    });
    //直营办
    $("#dstr_report").click(function(){
    	 $("#report_frame").attr("src","scripts/flex/DirectSaleStore.html"); 
    });
    //直营办领导
    $("#dstr_report_main").click(function(){
    	 $("#report_frame").attr("src","scripts/flex/ChannelAndDirectSaleStore.html"); 
    });
    
    //渠道
     $("#chann_report").click(function(){
    	 $("#report_frame").attr("src","scripts/flex/EffectiveStoreChannelDepts.html"); 
    });
    if(typeof($("#report_menu span").get(0)) !="undefined"){
       $("#report_menu span").get(0).click();
    }
});

function showNoticeContent(obj){
	var NOTICE_ID = $(obj).parent().parent().find("input[name='NOTICE_ID']").val();
	window.showModalDialog('erpFirpage.shtml?action=toNotice&NOTICE_ID='+NOTICE_ID,'','dialogwidth=950px; dialogheight=600px; status=no');
	//window.open('erpFirpage.shtml?action=toNotice&NOTICE_ID='+NOTICE_ID,'','height=950,width=600');
}

function closeNoticeContent(){
	$("#notice_content").hide();
}
//清空背景色
function clearColor(){
	$("#ul_notice li").each(function(){
		var color = $(this).css("background-color");
		if("#ffefd3" == color ){
			$(this).css("background-color","#F8F7F2")
		}
	});
	
}

//
function doMore_Prmt(){
	document.getElementById("to_more").click();
}
/**
 * 待处理订单\待入库\待退货
 */
function queryCount(){
	$.ajax({
	url: "erpFirpage.shtml?action=queryErpCount",
	type:"POST",
	dataType:"json",
	data:{},
	complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			var data = jsonResult.data;
			 if(null !=data && ""!=data){
				$("#good_count").text(data.Goods);//未处理的订货订单
			    $("#turnoverplan_count").text(data.turnoverplan);//制定交付计划
			    $("#turnoverhd_count").text(data.turnoverhd);
			    $("#pdtdeliver_count").text(data.pdtdeliver);
			    $("#deliverconfm_count").text(data.deliverconfm);
			    $("#techAudit_count").text(data.techAudit);
			    $("#techPrice_count").text(data.techPrice);
			    
			 }
			 
		}
	}
});
	 
}


/**
 * 柱状图
 * @param {Object} obj
 */
function queryBar(obj){
  $("#table_content").hide();
  $("#graph").show();
  
  $("#span_bar").removeClass("last");
  $("#span_bar").addClass("current");
  
  $("#span_store").removeClass("current");
  $("#span_store").addClass("last");
  
  $("#graph").addClass("table_content");
  $.ajax({
	url: "drpFirpage.shtml?action=queryBar",
	type:"POST",
	dataType:"json",
	data:{},
	complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			 var data = jsonResult.data;
			 if(null !=data && ""!=data){
				 var array ;
				 var myData = [];
				 $.each(data,function(i,item){
					array = [];
					array.push(item.PRD_NAME);
					array.push(Number(item.ORDER_NUM));
					myData.push(array);
					
			    });
				 
				 if(myData.length==1){
					  array = [];
					 array.push("");
					 array.push(Number(0));
					 myData.push(array);
				 }
				bar(myData);
			 }
		}
	}
});
	
	
}

//柱状图 参数 是一个二维数组
function bar(data){
	var myChart = new JSChart('graph', 'bar');
	myChart.patchMbString();
//	var myData = new Array(['爱倍', 2], ['发思明', 1], ['2007', 3], ['2008', 6]);
	myChart.setDataArray(data);
	myChart.setBarColor('#E60012');//#42aBdB
	myChart.setBarOpacity(0.8);
	myChart.setBarBorderColor('#D9EDF7');
	myChart.setBarValues(false);
	myChart.setTitleColor('#8C8383');
	myChart.setAxisColor('#777E81');
	myChart.setAxisValuesColor('#777E81');
//	myChart.setAxisNameX("品牌");
//	myChart.setAxisNameY("销售量");
	
	myChart.setAxisNameX("");
	myChart.setAxisNameY("");
	myChart.setTitle("");
	myChart.draw();
}


function showStore(obj){
	$("#table_content").show();
	$("#graph").hide();
	
	$("#span_bar").removeClass("current");
    $("#span_bar").addClass("last");
  
    $("#span_store").removeClass("last");
    $("#span_store").addClass("current");
}

function toShowPage(){
	
	//var obj = parent.parent.leftFrame.getNode();
	var mainFrame = window.top.mainFrame;  
	$("a[name='target']").click(function(){
		 var label = $(this).attr("label");
		 var menu = $(this).attr("menu");
		 var url = $(this).attr("href");
		 var qx = $(this).attr("qx");
		 if(null == qx){
			 qx = "";
		 }
		 url = utf8(url);
		  $.ajax({
			url: "drpFirpage.shtml?action=checkHaveQx&QX="+qx,
			type:"POST",
			dataType:"json",
			data:{},
			complete: function(xhr){
				eval("jsonResult = "+xhr.responseText);
				if(jsonResult.success===true){
					showErrorMsg(utf8Decode(jsonResult.messages));
				}else{
					
				    mainFrame.addTab(menu,label,"../../"+url);
	                return false;
				}
			}
		});
		
		return false;

	});
	
	
}

function closePage(){
  $("#mycredit_show").hide();
}
 
function showFile(NOTICEID,ATT_PATH){
	if($("#fileDownTb")){
		$("#fileDownTb").remove();
	}
	$("#ATT_PATH").val("");
	$("#mycredit_show").hide(); 
	if(null == ATT_PATH || "" == ATT_PATH){
		$("#ATT_PATH_TXT").text("暂无附件");
		showErrorMsg("暂无附件");
		 
	}else{
		var html = "<table id='fileDownTb'> <tr> <td> <input type='hidden' name='ATT_PATH' id='ATT_PATH' value=''/> </td> </tr> </table>";
		$("#fileDown_title").after(html);
		$("#ATT_PATH").val(ATT_PATH);
		$("#mycredit_show").show();
	}
  	displayDownFile('ATT_PATH',"SAMPLE_DIR",false,false);
	
//	$.ajax({
//		url: "drpFirpage.shtml?action=toList?action=downFille",
//		type:"POST",
//		dataType:"text",
//		data:{"NOTICEID":NOTICEID},
//		complete: function(xhr){
//			eval("jsonResult = "+xhr.responseText);
//			if(jsonResult.success===true){
//				 
//			}else{
//				 
//			}
//		}
//	});	 
}


/*function toShowPage(){
	
	var mainFrame = window.top.mainFrame;  
	$("a[name='target']").click(function(){
		var url = $(this).attr("href");
		url = utf8(url);
	    mainFrame.addTab($(this).attr("label"),$(this).attr("label"),"../../"+url);
	    return false;
	});
}*/




