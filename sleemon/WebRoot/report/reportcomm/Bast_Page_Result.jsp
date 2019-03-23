<!-- 
 * @module 报表管理
 * @fuc 报表通用显示页面
 * @version 1.0
 * @author 朱晓文
 *  -->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ taglib uri="/WEB-INF/runqian/runqianReport4.tld" prefix="report"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/report/reportcomm/css/style.css">
		<style type="text/css"> 
			#report1_scrollArea {width:100%;}
	　	</style> 
		<%
			//报表路径名称 如【../reportFile/*.raq】
			String rptModel = request.getAttribute("rptModel").toString();
			String printModel = request.getAttribute("printModel")==null?"":request.getAttribute("printModel").toString();
			if(printModel.equals("")){
				printModel = rptModel;
			}
			System.err.println("rptModel==="+rptModel);
			if(rptModel==null || "".equals(rptModel)){
				rptModel = "../reportFile/rptComm.raq";
			}
			//要传递的宏参数！！！
			String params = request.getAttribute("conDition").toString();
			System.out.println("rptModel=="+rptModel);
 		%>
    </head >
	<body>
	<div class="lst_area" style="width:100%;height:95%">
	<table align=center width="100%">
	<tr><td width="100%">
		<center>
		<report:html width="-1" name="report1" 
		        reportFileName="<%=rptModel %>"
				params="<%=params %>"
				funcBarLocation="" 
				separator=" "
				generateParamForm="no"
				needSaveAsExcel="yes"
				needSaveAsWord="yes" 
				needPrint="yes"
				savePrintSetup="yes" 
				printedRaq="<%=printModel %>"
				exceptionPage="/report/reportcomm/reportError.jsp"
				needScroll="no"
                scrollWidth="100%"
				displayNoLinkPageMark="yes"
		/>
		</center>
	</td></tr>
	<tr><td width="100%">&nbsp</td></tr>
</table>
</div>
<div style="width:100%;height:5%;position:absolute;bottom:0px">
<jsp:include page="toolbar.jsp" flush="false"/>
</div>
</body>
<script type="text/javascript">
	/*
	** wlbh：链接参数;
	** jsonData：查询页面条件参数！;
	** filepath：链接跳转地址（第二层）;
	*/
	  function show_product(wlbh,jsonData,filepath){
	  	var data = replaceAll(jsonData,'!','&');
        window.location = "${ctx}/"+filepath+"?param1="+wlbh+"&"+utf8(data);
	  }
	  var replaceAll = function(achar,charA,charB){   
	     var rep = new RegExp(charA,"g");   
	     return achar.replace(rep,charB);   
      } 
</script>
<script type="text/javascript">	  
$(function(){	

      //add by zhuxw 自定义页面
	  //initpersonal();
	  //add by zhuxw 表头排序
	  //setOrderCol();
	  var quieeMark=$("#report1").next();
	  var sysBar=quieeMark.next();
	  quieeMark.css("display","none");
	  sysBar.css("display","none");
});
/**
 function setOrderCol(){
   var tableId="report1";
   var tds = $("#"+tableId+" tr:eq(1) td");
  $.each(tds,function(i,k){
  
  if($(this).attr("onclick"))
  {
  $(this).hover(
  
   function () {
    $(this).css("cursor","hand");
    },
    function (){
      $(this).css("cursor","help");
    }

   );
  } 
   
   }
  );
}
**/

    /**
	  * 表头排序方法
	  * 2012-07-26
	  * edit by zhuxw
	  **/
	  /**
	  function resPx(pxColumnName,tdobj){
	  	//提交表单排序
	  	var obj = parent.topcontent.document.getElementById("conDition");
	  	var queryForm=$(parent.topcontent.document.getElementById("queryForm"));
	  	var pxColumnValue="";
	  	var pxObj=parent.topcontent.$("#pxColumnValue").val();
	  	if(pxObj==null||pxObj=="")
	  	{
	  	    $(queryForm).append("<input type='hidden' id='pxColumnValue' name='pxColumnValue' value='ASC'>");
	  	    $(queryForm).append("<input type='hidden' id='pxColumnName' name='pxColumnName' value='"+$(tdobj).text()+"'>");
	  	    pxColumnValue="ASC";
	  	    
	  	}
	  	else
	  	{
	  	  if(pxObj=="ASC")
	  	  {
	  	   pxColumnValue="DESC";
	  	   parent.topcontent.$("#pxColumnValue").val("DESC");
	  	   parent.topcontent.$("#pxColumnName").val($(tdobj).text());
	  	  }else
	  	  {
	  	   pxColumnValue="ASC";
	  	   parent.topcontent.$("#pxColumnValue").val("ASC");
	  	   parent.topcontent.$("#pxColumnName").val($(tdobj).text());
	  	  }
	  	 
	  	}
	  	var condition = $(obj).val();
	  	var rptSql = condition.split(";")[0];
	  	var sql = "select * from ("+rptSql.substring(rptSql.indexOf("=")+1)+") A order by "+pxColumnName+ " "+pxColumnValue;
	  	$(obj).val(rptSql.substring(0,rptSql.indexOf("="))+"="+sql+";"+condition.split(";")[1]+";pxColumnValue="+pxColumnValue+"")
	    queryForm.submit();
	  	$(obj).val(condition);
	  }	  
	  
	  **/
	  
function setpersonal(tableId){
      var pagediv = "<div id='pageConf' style='background:#e0edf6;width:100%;height:100%;z-index:999;position:absolute;top:0;margin-top:70px;filter:alpha(opacity=90);'></div>"; 
      $(document.body).append(pagediv);
      var table_conf='<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">';
      table_conf+='<tr><td colspan="10" align="center"  style="font-size:15" >';
      table_conf+=" 页面列自定义 ";
      table_conf+='</td></tr>';
      if(tableId==null||tableId=="")
      tableId="report1";
      var num=0;
      var tds = $("#"+tableId+" tr:eq(1) td");
     $.each(tds,function(i,k){
            var valstr=$(this).text();
            var disstr=$(this).css("display");
            var flag=$(this).attr("flag");
            var idstr="ID"+num;
            if(disstr=='block'||flag=='1')
		    { 
		       if(num%4==0)
		       {
		         table_conf+='<BR><tr>';
		       }
		        table_conf+='<td width="8%" nowrap align="right"  >'+valstr+'</td>';
                table_conf+='<td width="15%">';
                if(disstr=='block')
                   table_conf+='<input type="checkbox" style="height:12px;valign:middle"  checked name="'+idstr+'box" id='+idstr+'box   value="'+num+'"/>';
                else
                   table_conf+='<input type="checkbox" style="height:12px;valign:middle"  name="'+idstr+'box" id='+idstr+'box  value="'+num+'"/>';
                table_conf+='</td>';
		        num+=1; 
		    }
		     
	  }
	)
	table_conf+='<tr><td colspan="10" align="center"  valign="middle" >';
	table_conf+="<input id='btn_ok' type='button' class='btn' value='确认(O)' title='Alt+O' accesskey='O'>&nbsp;&nbsp;";
    table_conf+="<input id='btn_closed' type='button' class='btn' value='关闭(X)' title='Alt+X' accesskey='X'>&nbsp;&nbsp;";
    table_conf+='</td></tr></table>'; 
    $("#pageConf").append(table_conf);
    $("#btn_ok").click(function(){
		doPageRest(tableId); 
	 });
	 $("#btn_closed").click(function(){
		 $("#pageConf").remove();
	 });
}
function initpersonal(tableId){
      if(tableId==null||tableId=="")
      tableId="report1";
      var keyvalue="<%=rptModel %>";
      keyvalue=keyvalue.substring(keyvalue.lastIndexOf('/')+1,keyvalue.lastIndexOf('.'));
      var disStr=$.cookie(keyvalue+"_display");
      var hidStr=$.cookie(keyvalue+"_hidden");
      if(disStr==null)
        disStr="";
      if(hidStr==null)
        hidStr="";
     displayColumn(hidStr,disStr,"report1");
     var pxColumnValue=parent.topcontent.$("#pxColumnValue").val();
     var pxColumnName=parent.topcontent.$("#pxColumnName").val();
     if(pxColumnName!=null&&pxColumnName!=""&&pxColumnValue!=null&&pxColumnValue!="")
       {
        var tds = $("#"+tableId+" tr:eq(1) td");
	      $.each(tds,function(i,k){
	       if( pxColumnName.indexOf($(this).text())!=-1)
	       {
	         if(pxColumnValue=="ASC")
	          $(this).text($(this).text()+"︽");
	         else
	         {
	          $(this).text($(this).text()+"︾");
	         }
	         
	       }
    
    })
    parent.topcontent.$("#pxColumnName").val("");
  }
  
     
}

function displayColumn(hiddenNumStr,displayNumStr,tableId){
    if(hiddenNumStr==""&&displayNumStr=="")
       return;
    if(null == tableId){
		tableId = "report1";
	}
	var trs = $("#"+tableId+" tr");
	var num=0;
	 $.each(trs,function(i,k){
	  if(num>0)
	  {
	  var tds = $(this).find("td");
	  var hiddenNumArr=hiddenNumStr.split(",");  
	  var displayNumArr=displayNumStr.split(",");
	  
	   var tdnum=0; 
	   $.each(tds,function(z,x){
	     var cuttd=$(this);
         var tddisstr=cuttd.css("display");
         var flag=cuttd.attr("flag");
         
        
        if(tddisstr=='block'||flag=='1')
        {  
	      cuttd.attr("flag",'1');
	      $.each(hiddenNumArr,function(f,g){
		     if(g!=""&&g!=null&&tdnum==g){
		         cuttd.css("display","none");
		    }
		   }
	     )
	     $.each(displayNumArr,function(f,g){
		     if(g!=""&&g!=null&&tdnum==g){
		        cuttd.css("display","block");
		    }
		   }
	     )
	     tdnum=tdnum+1;
	     }
	    }
	    )
	    
	  }
	  num=num+1;
	  }
	)
	$("#report1_scrollArea table").each(function(i){
			$(this).css({ color: "#ff0011",width: "100%" });
	}); 		
	$("table[id^='report1']").css({ color: "#ff0011",width: "100%" });	
}
function doPageRest(tableId){
	var pageInp=$("#pageConf input:checkbox");
	var keyvalue="<%=rptModel %>";
	var disStr="";
    var hidStr="";
    keyvalue=keyvalue.substring(keyvalue.lastIndexOf('/')+1,keyvalue.lastIndexOf('.'));
    $.each(pageInp,function(i,k)
    {
      if(this.checked)
      {
        
        if(disStr!="")
         disStr=disStr+","+$(this).val();
        else
         disStr=$(this).val();
       }else
      {
       if(hidStr!="")
         hidStr=hidStr+","+$(this).val();
        else
         hidStr=$(this).val();
      }
    }
   )

$.cookie(keyvalue+"_display",disStr,{expires:2000});
   $.cookie(keyvalue+"_hidden",hidStr,{expires:2000});
   displayColumn(hidStr,disStr,"report1");
 }
</script>
<script type="text/javascript" src="${ctx}/report/reportcomm/initReport.js"></script>
</html>
