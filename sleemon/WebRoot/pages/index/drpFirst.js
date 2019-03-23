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
 
//    $("#ul_notice li").hover(
//    	function(){
//    		clearColor();
//    		var NOTICE_ID = $(this).find("input[name='NOTICE_ID']").val();
//    		if(null == NOTICE_ID || "" == NOTICE_ID){
//    			return;
//    		}
//    		$("#ul_notice li").eq(0).css("background","#F8F7F2");//灰色
//    		$(this).css("background","#ffefd3");
//    		var title = $(this).find("a[name='a_title_']").text();
//    		 
//    		$.ajax({
//				url: "drpFirpage.shtml?action=loadNotice",
//				type:"POST",
//				dataType:"json",
//				data:{"NOTICE_ID":NOTICE_ID},
//				complete: function(xhr){
//					eval("jsonResult = "+xhr.responseText);
//					if(jsonResult.success===true){
//						$("#h3_notice_title").html(title);
//						$("#p_notice").html(jsonResult.messages);
//					}
//				}
//			});
//    	},
//    	function(){
//    		$(this).css("background","#F8F7F2");//灰色
//    	}
//    );
    
    queryCount();
    
    $("#graph").hide();
    
    //查询 待发货预订单 待入库 待退货
    $("a[name='target']").click(function(){
    	toShowPage(this);
    });
    
    //默认第一条公告 add by zzb 2015-4-20 首页变形
    $("#ul_notice li a").eq(0).click();
});

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
	url: "drpFirpage.shtml?action=queryCount",
	type:"POST",
	dataType:"json",
	data:{},
	complete: function(xhr){
		eval("jsonResult = "+xhr.responseText);
		if(jsonResult.success===true){
			var data = jsonResult.data;
			 if(null !=data && ""!=data){
				$("#advcorder_count").text(data.DRP_ADVC_ORDER);
				$("#storeinnotice_count").text(data.DRP_STOREIN_NOTICE);
			    $("#prdreturnreq_count").text(data.DRP_PRD_RETURN_REQ);
			     
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
		showOrHide(obj);
//  $("#table_content").hide();
  $("#graph").show();
//  
//  $("#span_bar").removeClass("last");
//  $("#span_bar").addClass("current");
//  
//  $("#span_store").removeClass("current");
//  $("#span_store").addClass("last");
//  
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
	showOrHide(obj);
//	$("#table_content").show();
//	$("#graph").hide();
//	
//	$("#span_bar").removeClass("current");
//    $("#span_bar").addClass("last");
//  
//    $("#span_store").removeClass("last");
//    $("#span_store").addClass("current");
}

function toShowPage(obj){
	
	//var obj = parent.parent.leftFrame.getNode();
	var mainFrame = window.top.mainFrame;  
//	$("a[name='target']").click(function(){
		 var label = $(obj).attr("label");
		 var menu=$(obj).attr("menu");
		 var url  = $(obj).attr("url");
		 var qx = $(obj).attr("qx");
		 if(null == qx){
			 qx = "";
			 mainFrame.addTab(menu,label,"../../"+url);
			 return false;
		 }else{
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
		 }
		
		return false;

//	});
	
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
	
 
 
		 
}


function showDelive(obj){
	showOrHide(obj);
	 $.ajax({
		url: "drpFirpage.shtml?action=queryDeliver",
		type:"POST",
		dataType:"json",
		data:{},
		complete: function(xhr){
			eval("jsonResult = "+xhr.responseText);
			if(jsonResult.success===true){
				 var data = jsonResult.data;
				 $("#span_deliver_table tr:gt(0)").remove();
				 $.each(data, function(i, item) {  // i 指向键, val指定值
					 var JOIN_DELIVER_ORDER_NO = item.JOIN_DELIVER_ORDER_NO;
					 if(null == JOIN_DELIVER_ORDER_NO){
						 JOIN_DELIVER_ORDER_NO = "";
					 }
					 var ADVC_SEND_DATE = item.ADVC_SEND_DATE;
					 if(null == ADVC_SEND_DATE){
						 ADVC_SEND_DATE = "";
					 }
					 var html = '<tr>'+
                                '<td align="center"><a  label="发运单查看"  href="javascript:void(0);" url="delivershow.shtml?action=toFrame&module=" onclick="return toShowPage(this)">'+item.DELIVER_ORDER_NO+'</a></td>'+
                                '<td align="center">'+JOIN_DELIVER_ORDER_NO+'</td>'+
                                '<td align="center">'+ADVC_SEND_DATE+'</td>'+
                                '<td align="center">'+item.STATE+'</td>'+
                                '</tr>';
					 $("#span_deliver_table tr:first").after(html);
                 });
			}else{
			    
			}
		}
	});
}

function showOrHide(obj){
//	$("#table_content").show();
//	$("#graph").hide();
//	
//	$("#span_bar").removeClass("current");
//    $("#span_bar").addClass("last");
//  
//    $("#span_store").removeClass("last");
//    $("#span_store").addClass("current");
    
    var parentID = $(obj).attr("parent");
//    var parentObj = $("#"+parentID);
//	var id = parentObj.attr("id");
 
//	var clssname = parentObj.attr("class");
//	
//	parentObj.removeClass(clssname);
//    parentObj.addClass("current");
    
    $("span[name='span_scheme']").each(function(){
    	var spanID = $(this).attr("id");
    	var clssname = $(this).attr("class");
    	if(spanID == parentID){
    		 $(this).removeClass(clssname);
    		 $(this).addClass("current");
    	}else{
    		$(this).addClass("last");
    	}
    });
    
	$("div[name='scheme_div']").each(function(){
		var forname = $(this).attr("for");
		if(forname == parentID){
			$(this).show();
			//$(this).addClass("table_content");
		}else{
			$(this).hide();
		}
	});
	//var div = $("div[for='"+id+"']");
}
 function getNoticeInfo(NOTICE_ID){
    	clearColor();
//    		var NOTICE_ID = obj.parent().parent().find("input[name='NOTICE_ID']").val();
    		if(null == NOTICE_ID || "" == NOTICE_ID){
    			return;
    		}
    		$("#ul_notice li").eq(0).css("background","#F8F7F2");//灰色
    		$(this).css("background","#ffefd3");
    		var title = $(this).find("a[name='a_title_']").text();
    		 
    		$.ajax({
				url: "drpFirpage.shtml?action=loadNotice",
				type:"POST",
				dataType:"json",
				data:{"NOTICE_ID":NOTICE_ID},
				complete: function(xhr){
					eval("jsonResult = "+xhr.responseText);
					if(jsonResult.success===true){
						$("#h3_notice_title").html(title);
						$("#p_notice").html(jsonResult.messages);
					}
				}
			});
    }