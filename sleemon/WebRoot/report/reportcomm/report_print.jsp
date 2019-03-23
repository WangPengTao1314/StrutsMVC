<!-- 
 * @module 报表管理
 * @fuc 报表打印
 * @version 1.0
 * @author 邢克罚
 *  -->
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8" import="java.awt.*"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ taglib uri="/WEB-INF/runqian/runqianReport4.tld" prefix="report"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<title>
		</title>
		<style type="text/css"> 
			body {
			 font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
			 background: #E6EAE9;
			 width: 100%;
			}				
	　	</style> 
		<%
 			//报表路径名称 如【../reportFile/*.raq】
 			String reportFileName = request.getParameter("reportFileName").toString();
 			//要传递的宏参数！！！
 			String params = request.getParameter("params").toString();
 		%>
	</head>
	<body >
		<script>
			var width = '90px';
		</script>
		<div class="lst_area" style="width:100%;height:90%">
		<table align="center" width="100%" height="100%">
			<tr>
				<td width="100%" align="center">
					<report:html  name="report1"
						reportFileName='<%=reportFileName%>' 
						params='<%=params%>'
						funcBarLocation="" 
						separator=" " 
						generateParamForm="no"
						needSaveAsExcel="yes" 
						needSaveAsWord="yes" 
						needPrint="yes"
						printLabel="<span style='height:40px'></span><input type='button' class='btn' value='打　印'>"
						savePrintSetup="yes" printedRaq="<%=reportFileName%>"
						excelLabel="<input  type='button' class='btn' value='导出Excel'>"
						wordLabel="<input  type='button' class='btn' value='导出Word'>"
						exceptionPage="/report/reportcomm/reportError.jsp"
						needScroll="yes" scrollWidth="1024px" scrollHeight="90%"
						needPageMark="yes" displayNoLinkPageMark="yes" />
				</td>
			</tr>
		</table>
		</div>
		<!-- 
		<table>
				<tbody>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td>
							<div noWrap="nowrap" style="font-family: 宋体; font-size: 13px;">
							
							<a class="ICOhover" href="#" onClick="report1_saveAsPdf();return false;">
								<input  type='button' class='btn' value='导出pdf'></a>
							<a class="ICOhover" href="#" onClick="report1_saveAsWord();return false;">
								<input  type='button' class='btn' value='导出word'></a>
								
							<a class="ICOhover" href="#" onClick="report1_saveAsExcel();return false;">
								<input  type='button' class='btn' value='导出excel'></a>	
							<a class="" href="#" onClick="report1_directPrint();return false;">
								<input  type='button' class='btn' value='直接打印'></a>
							<a class="ICOhover" href="#" onClick="report1_print();return false;">
								<input  type='button' class='btn' value='打印预览'></a>		
							</div>
						</td>
					</tr>
				</tbody>
		</table>	
		 -->	
	</body>
	<script type="text/javascript">
		//设置滚动条的宽度
		var width=window.screen.width*0.65-10;//弹出窗口的宽度
		var height=window.screen.height*0.65-4;//弹出窗口的高度
		$("#report1_scrollArea").css({width: width,height: height});
	</script>
	<!-- 
	<script type="text/javascript" src="${ctx}/report/reportcomm/initReport.js"></script>
    -->
    <script type="text/javascript">
     $("#report1_scrollArea table").each(function(i){
			$(this).css({ color: "#ff0011",width: "100%" });
		}); 		
		$("table[id^='report1']").css({ color: "#ff0011",width: "100%" });
		alert("report1_getTotalPage()===="+report1_getTotalPage());
 		$('#t_page_span').text(report1_getTotalPage());
		$('#c_page_span').text(report1_getCurrPage());
		var modelName=$("#modelName").val();
		var theUrl;
		if("AnnualRebateDtlReport.raq"==modelName){
			theUrl="raq.shtml?action=toAnnualRebateDtl";
		}else{
			theUrl=parent.topcontent.$("#queryForm").attr("action");
		}
		$("form[name='report1_turnPageForm']").attr("action",theUrl);
		function report1_toPage(pageNo) {
			if( pageNo < 1 || pageNo > report1_getTotalPage() ) return;
			$("form[name='report1_turnPageForm']").attr("action",theUrl);
            $("form[name='report1_turnPageForm']").find("input[name='report1_currPage']").val(pageNo);
            showWaitPanel('data query!');			
			setTimeout("_frm.submit()",5);
		}
		$("#newtPage").click(function(){
			nextPage();
		});
		$("#firstPage").click(function(){
			report1_toPage(1);
		});
		$("#prevPage").click(function(){
			report1_toPage(report1_getCurrPage()-1);
		});
		$("#lastPage").click(function(){
			report1_toPage(report1_getTotalPage());
		});
     </script>
</html>
