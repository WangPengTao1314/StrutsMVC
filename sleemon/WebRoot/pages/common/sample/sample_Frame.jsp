<!-- 
  *@module 系统模块 
  *@func 系统模块 
  *@version 2.0
  *@author 朱晓文 
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<script type=text/javascript src="${ctx}/scripts/core/jquery-1.4.1.min.js"></script>
	<script type=text/javascript src="${ctx}/scripts/util/jquery.ztree-2.6.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/zTreeStyle.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css"/>
	<title>SAMPLE</title>
	<script type=text/javascript >
	$(function(){
	
	     //单一上传
	     uploadFile('uploadExcel','SAMPLE_DIR',true);
	     
	     //这里是加入系统job,原始水单转为标准的EXCEL
	     $("#btn_chg").click(function(){
	         //注意，可以多个EXCEL一起转换，这里只是一个例子
	    	 var chagExltext=$("#changeExcel").val();
	    	 //你解析exl的模板ID
	    	 var dataModelId="NT001";
	    	 var fileName=chagExltext;
	    	 var newFileName="StandFile";
	    	 var sedPath="SAMPLE_DIR";
	    	 var newSedPath="SAMPLE_DIR";
	    	 //这里放你用到的参数 ,具体格式自己定，根据你的处理函数所需要的参数决定。
	    	 var params='{"EXLMODELID":"'+dataModelId+'","SEDPATH":"'+sedPath+'","FILENAME":"'+fileName+'","NEWFILENAME":"'+newFileName+'","NEWSEDPATH":"'+newSedPath+'","DST_NO":"SYS0001","DST_NAME":"润和","BILL_TYPE":"水单类型","SUB_NAME":"提交人姓名"}';
	    	
	    	 //这里是任务处理的标准json格式，不能改动
	    	 //处理业务的业务方法对照
	    	 var bill_Type="原始水单转标准水单";
	    	 var callBack="com.hoperun.commons.job.service.impl.JobServiceImpl.callBackTest";
	    	 var jsonParam='[{"BILL_TYPE":"'+bill_Type+'","CALL_BACK":"'+callBack+'","PARAMS":'+params+'}]';
	    	 addTojob(jsonParam);
	    	
	     }); 
	    
	         	    
	    //上传加导入
	    //uploadFile('impExcel','SAMPLE_DIR',false,bussinessFun);
	    
	    
	    //下载
	    $("#TPdownload").click(function()
	     {
	       downloadFile('TPdownload','SAMPLE_DIR','2012/11-20/原始水单.xls');
	     }
	    );
	    
	      //多Exl下载
	     $("#mulExlDown").click(function()
	     {
	       var sedPath="SAMPLE_DIR";
	       var exl1Path=$("#Exl1").val();
	       var exl2Path=$("#Exl2").val();
	       var exl3Path=$("#Exl3").val();
	       var jsonParam='[{"SEDPATH":"'+sedPath+'","FILENAME":"'+exl1Path+'"},{"SEDPATH":"'+sedPath+'","FILENAME":"'+exl2Path+'"},{"SEDPATH":"'+sedPath+'","FILENAME":"'+exl3Path+'"}]'; 
	       mulFileDown(jsonParam);  
	      });
	     
	     
	     //多文件上传
	     var chkId="0";
	     uploadFile('uploadMulExl','SAMPLE_DIR',true,true,false,doyourBill,chkId);
	     
	     
	     //双排生成Exl
	     $("#chgDouExl").click(function()
	     {
	         var billId="1";
	         var fileName="双排水单001";
	         var sedPath="SAMPLE_DIR";
	         //douWriteExl(billId,sheetName,fileName,sedPath,doyourBill);
	         //这里是任务处理的标准json格式，不能改动
	    	 //处理业务的业务方法对照
	    	 //这里放你用到的参数 ,具体格式自己定，根据你的处理函数所需要的参数决定。
	    	 var params='{"BILLID":"'+billId+'","SEDPATH":"'+sedPath+'","FILENAME":"'+fileName+'"}';
	    	 var bill_Type="双排系统处理";
	    	 var callBack="com.hoperun.commons.job.service.impl.JobServiceImpl.callBackTest";
	    	 var jsonParam='[{"BILL_TYPE":"'+bill_Type+'","CALL_BACK":"'+callBack+'","PARAMS":'+params+'}]';
	    	 addTojob(jsonParam);
	        
	     });
	     
	     
	     //QC生成Exl
	     $("#chgQcExl").click(function()
	     {
	     
	         var billId1=$("#BILLID1").val();
	         var billId2=$("#BILLID2").val();
	         var billId3=$("#BILLID3").val();
	         var billIds=billId1+","+billId2+","+billId3;
	         var fileName="QC水单001";
	         var sedPath="SAMPLE_DIR";
	         var month_year="201212";
	         //这里是任务处理的标准json格式，不能改动
	    	 //处理业务的业务方法对照
	    	 //这里放你用到的参数 ,具体格式自己定，根据你的处理函数所需要的参数决定。
	    	 var params='{"BILLIDS":"'+billIds+'","SEDPATH":"'+sedPath+'","FILENAME":"'+fileName+'","YEAR_MONTH":"'+month_year+'"}';
	    	 var bill_Type="QC系统处理";
	    	 var callBack="com.hoperun.commons.job.service.impl.JobServiceImpl.callBackTest";
	    	 var jsonParam='[{"BILL_TYPE":"'+bill_Type+'","CALL_BACK":"'+callBack+'","PARAMS":'+params+'}]';
	    	 addTojob(jsonParam);
	        
	     });
	      //审核水单校验
	     $("#douAudCheck").click(function()
	     {
	         var chkModelId='002';
	         var fileName=$("#AUDBILL").val();
	         var sedPath="SAMPLE_DIR";
	         //这里是任务处理的标准json格式，不能改动
	    	 //处理业务的业务方法对照
	    	 //这里放你用到的参数 ,具体格式自己定，根据你的处理函数所需要的参数决定。
	    	 var params='{"CHKMODELID":"002","SEDPATH":"'+sedPath+'","FILENAME":"'+fileName+'"}';
	    	 var bill_Type="审核水单校验";
	    	 var callBack="com.hoperun.commons.job.service.impl.JobServiceImpl.callBackTest";
	    	 var jsonParam='[{"BILL_TYPE":"'+bill_Type+'","CALL_BACK":"'+callBack+'","PARAMS":'+params+'}]';
	    	 addTojob(jsonParam);
	        
	     });
	     
	     //DOU水单校验
	     $("#douCheck").click(function()
	     {
	         var chkModelId='100';
	         var fileName=$("#DOUBILL").val();
	         var sedPath="SAMPLE_DIR";
	         var cust_No="DST001";
	         var cust_Name="江苏润和";
	        // var prj_Id="0001";
	         var prj_Id="4d3f9a485d3e4cbd8a2ddff2c622c68f";
	         
	         var year_Month="201301";
	         
	         //这里是任务处理的标准json格式，不能改动
	    	 //处理业务的业务方法对照
	    	 //这里放你用到的参数 ,具体格式自己定，根据你的处理函数所需要的参数决定。
	    	 var params='{"CHKMODELID":"001","SEDPATH":"'+sedPath+'","FILENAME":"'
	    	            +fileName+'","PRJ_ID":"'+prj_Id+'","DST_NO":"'+cust_No+'","DST_NAME":"'+cust_Name+'","YEAR_MONTH":"'+year_Month+'"}';
	    	 var bill_Type="双排水单校验";
	    	 var callBack="com.hoperun.commons.job.service.impl.JobServiceImpl.callBackTest";
	    	 var jsonParam='[{"BILL_TYPE":"'+bill_Type+'","CALL_BACK":"'+callBack+'","PARAMS":'+params+'}]';
	    	 addTojob(jsonParam);
	        
	     });
	     
	     
	      //QC水单校验
	     $("#qcCheck").click(function()
	     {
	         var chkModelId='003';
	         var fileName=$("#QCBILL").val();
	         var sedPath="SAMPLE_DIR";
	        // var prj_Id="0001";
	         var prj_Id="4d3f9a485d3e4cbd8a2ddff2c622c68f";
	         var year_Month="201301";
	         
	         
	         //这里是任务处理的标准json格式，不能改动
	    	 //处理业务的业务方法对照
	    	 //这里放你用到的参数 ,具体格式自己定，根据你的处理函数所需要的参数决定。
	    	 var params='{"CHKMODELID":"001","SEDPATH":"'+sedPath+'","FILENAME":"'
	    	            +fileName+'","PRJ_ID":"'+prj_Id+'","YEAR_MONTH":"'+year_Month+'"}';
	    	 var bill_Type="QC水单校验";
	    	 var callBack="com.hoperun.commons.job.service.impl.JobServiceImpl.callBackTest";
	    	 var jsonParam='[{"BILL_TYPE":"'+bill_Type+'","CALL_BACK":"'+callBack+'","PARAMS":'+params+'}]';
	    	 addTojob(jsonParam);
	        
	     });
	     
	     
	     //QC入库处理
	     $("#qcStorage").click(function()
	     {
	         var fileName=$("#QCSTOCK").val();
	         var sedPath="SAMPLE_DIR";
	         var prj_Id="0001";
	         var year_month="201212";
	         var qcBat_Id="22222";
		     //这里是任务处理的标准json格式，不能改动
	    	 //处理业务的业务方法对照
	    	 //这里放你用到的参数 ,具体格式自己定，根据你的处理函数所需要的参数决定。
	    	 var params='{"QCBAT_ID":"'+qcBat_Id+'","YEAR_MONTH":"'+year_month+'","SEDPATH":"'+sedPath+'","FILENAME":"'+fileName+'","PRJ_ID":"'+prj_Id+'"}';
	    	 var bill_Type="QC入库处理";
	    	 var callBack="com.hoperun.commons.job.service.impl.JobServiceImpl.callBackTest";
	    	 var jsonParam='[{"BILL_TYPE":"'+bill_Type+'","CALL_BACK":"'+callBack+'","PARAMS":'+params+'}]';
	    	 addTojob(jsonParam);
	        
	     });
	     
	   });
	 
	 
	 function doyourBill(){
	   // alert();
	    //$("#DOUBILLEXL").val(filePath);
	    alert($("#uploadMulExl").val());
	 }
	 
	 
	 
	 

</script>
</head>

<body>
<form name="_sampleForm" method="post" target="_sampleForm" action="">
<table width="100%" height="20%" border="0" cellpadding="0" cellspacing="0">
	    <tr  height="10">
	        <td  align="left" >
				单个EXCEL上传例子：&nbsp;
			</td>
			
		</tr>
	    <tr >
	        <td  align="left">请选择EXECL：<input  type="text"  name="uploadExcel" id ="uploadExcel" />&nbsp;</td>
		</tr>
		
		 <tr  height="10">
	        <td  align="left" >
				转换EXCEL例子：&nbsp;
			</td>
			
		</tr>
		
	    <tr>
	        <td  align="left">EXECL路径:<input  type="text" value ="/2012/11-20/原始水单.xlsx"  id ="changeExcel" />&nbsp;<input  type="button"  name="btn_chg" id ="btn_chg" value="开始转换" /></td>
		</tr>
		
		
		<tr  height="10">
	        <td  align="left" >
				下载EXCEL例子：&nbsp;
			</td>
			 
		</tr>
		
		<tr  height="20">
		<td  align="left">下载EXECL：
	        
	         <input   type="button"   id ="TPdownload"  value='下载(*****)&nbsp;'  title="点击下载" />
	        </td>
		</tr>
		
		<tr  height="10">
	        <td  align="left" >
				多EXCEL下载例子：&nbsp;
			</td>
			
		</tr>
	    <tr >
	        <td  align="left">EXECL路径：
	           <input   type="text"   id ="Exl1"    value="2012/11-20/原始水单.xls" />
	           <input   type="text"   id ="Exl2"    value="2012/11-19/test_6f2e322ebb4a4604a5dc189fe089cc70.xlsx"  />
	           <input   type="text"   id ="Exl3"    value="2012/11-26/StandFile_f690fb835a144b92bf247d66f8d4ee2c.xls"  />
	           <input   type="button"   id ="mulExlDown"  value='下载&nbsp;'  title="点击下载" />
	        </td>
		</tr>
		<tr  height="10">
	        <td  align="left" >
				多文件上传例子：&nbsp;
			</td>
			
		</tr>
	    <tr>
	        <td align="left" nowrap >
	           请选择EXECL：<input type="text" name="uploadMulExl" id ="uploadMulExl"/>&nbsp;
	          <input  type="button" name="uploadMulExl_upl" value="开始上传" id ="uploadMulExl_upl"/>&nbsp;
	          <input  type="button" name="uploadMulExl_clear" value="取消传" id ="uploadMulExl_clear"/>&nbsp;
	        </td>
		</tr>
		
		
		
		
		<tr  height="10">
	        <td align="left" >
				 生成双排EXCEL：&nbsp;
			</td>
			
		</tr>
	    <tr >
	        <td  align="left" nowrap >双排EXL：
	           水单ID<input   type="text"   id ="BILLID" value="1"/>
	         <input  type="button" name="chgDouExl" value="开始生成" id ="chgDouExl"/>&nbsp;
	        </td>
		</tr>
		
		
		<tr  height="10">
	        <td align="left" >
				 生成QC批次EXCEL：&nbsp;
			</td>
			
		</tr>
	    <tr >
	        <td  align="left" nowrap >QC批次EXL：
	           水单ID<input   type="text"   id ="BILLID1" value="1"/>
	           水单ID<input   type="text"   id ="BILLID2" value="820ec66777ae4dd1b2b4cbdac8d835f0"/>
	           水单ID<input   type="text"   id ="BILLID3" value="c681ba3f51ee4dbfb5bed49e19b3bda1"/>
	         <input  type="button" name="chgQcExl" value="开始生成" id ="chgQcExl"/>&nbsp;
	        </td>
		</tr>
		
		<tr  height="10">
	        <td align="left" >
				审核提交水单校验：&nbsp;
			</td>
		</tr>
	    <tr >
	        <td  align="left" nowrap >审核提交水单校验：
	          水单路径:<input   type="text"   id ="AUDBILL" value="/2012/12-10/StandFile_70a01062a1014cceb20af0859d243f09.xls"/>
	         <input  type="button" name="douAudCheck" value="开始校验" id ="douAudCheck"/>&nbsp;
	        </td>
		</tr>
		&nbsp;
		<tr  height="10">
	        <td align="left" >
				双排提交水单校验：&nbsp;
			</td>
			
		</tr>
	    <tr >
	        <td  align="left" nowrap >双排水单校验：
	          水单路径:<input   type="text"   id ="DOUBILL" value="/2012/12-10/DOUEXL.xls"/>
	         <input  type="button" name="douCheck" value="开始校验" id ="douCheck"/>&nbsp;
	        </td>
		</tr>
		&nbsp;
		<tr  height="10">
	        <td align="left" >
				QC提交水单校验：&nbsp;
			</td>
			
		</tr>
	    <tr >
	        <td  align="left" nowrap >QC水单校验：
	          水单路径:<input   type="text"   id ="QCBILL" value="/2012/12-13/QC水单001_081ed379ca9a4736a56b8c60bdc8a89f.xls"/>
	         <input  type="button" name="qcCheck" value="开始校验" id ="qcCheck"/>&nbsp;
	        </td>
		</tr>
		<tr  height="10">
			
		
	        <td  align="left" nowrap >QC入库QC水单路径:<input type="text"   id ="QCSTOCK" value="/2012/12-13/QC水单001_081ed379ca9a4736a56b8c60bdc8a89f.xls"/>
	         <input  type="button" name="qcStorage" value="开始入库" id ="qcStorage"/>&nbsp;
	        </td>
		</tr>
		<tr  height="10">
			<td width="15%" align="right" class="detail_label">
							区域名称：
						</td>
						<td width="35%" class="detail_content">
							<input type="hidden" name="area_Con" id="area_Con" value=""/>
							<input  name="AREA_NAME" id="AREA_NAME" type="text" seltarget="sel_AREA" label="区域编号"/>
							<img align="absmiddle" name="sel_AREA" style="cursor: hand" src="${ctx}/styles/default/images/msg/select.gif"
							 onClick="selCommon('12', false, 'KFXXID', 'KFXXID', 'forms[0]','AREA_NAME,PRV_NAME,CITY_NAME', 'AREA_NAME,PRV_NAME,CITY_NAME','area_Con');"/>
						</td>
						<td width="15%" align="right" class="detail_label">
							省份名称：
						</td>
						<td  align="left" nowrap >
						     <input  type="text" name="PRV_NAME" size='10'  seltarget="sel_AREA" />&nbsp;
	                    </td>
	                    <td width="15%" align="right" class="detail_label">
							城市名称：
						</td>
						<td  align="left" nowrap >
						     <input  type="text" name="CITY_NAME" size='10'  seltarget="sel_AREA" />&nbsp;
	                    </td>
	                    
		</tr>
</table>
</form>
</body>
<%@ include file="/pages/common/uploadfile/uploadfilenew.jsp"%>
