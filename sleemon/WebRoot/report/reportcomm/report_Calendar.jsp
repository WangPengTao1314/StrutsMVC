<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="${ctx}/report/reportcomm/calendar/calendar.css?random=20121212" media="screen"></LINK>
	<SCRIPT type="text/javascript" src="${ctx}/report/reportcomm/calendar/calendar.js?random=20060118"></script>
</head>
<body onload="load()">
    <input type="hidden" value="${count}"    id="count" />
    <input type="hidden" value="${mkmIds}"   id="mkmIds" />
    <input type="hidden" value="${mkmNames}" id="mkmNames" />
    <input type="hidden" value="${mkmDates}" id="mkmDates" />
    <input type="hidden" value="${Names}"  id="Names" />
    <input type="hidden" value="${Dates}"  id="Dates" />
    
</body>
 <script type="text/javascript"> 
        function  load(){
           var Names = $("#Names").val();
           if(Names !="") {
	           var Dates = $("#Dates").val();
	           var arrNames = Names.split("|");
	           var arrDates = Dates.split("|");
	           for(var i=0;i<arrNames.length;i++){
	               if(i==0){
	                  arrDates[i] = arrDates[i].substr(0);
	                  //alert("arrDates[0]==="+arrDates[0]);
	                  displayCalendar1(arrDates[i],'yyyy-mm-dd',arrNames[i]);
	               }
	              
	               if(i==1){
	                  arrDates[i] = arrDates[i].substr(0);
	                  displayCalendar2(arrDates[i],'yyyy-mm-dd',arrNames[i],1);
	               }
	               if(i==2){
	                  arrDates[i] = arrDates[i].substr(0);
	                  displayCalendar3(arrDates[i],'yyyy-mm-dd',arrNames[i],1);
	               }
	                if(i==3){
	                  arrDates[i] = arrDates[i].substr(0);
	                  displayCalendar4(arrDates[i],'yyyy-mm-dd',arrNames[i],1);
	               }
	                if(i==4){
	                  arrDates[i] = arrDates[i].substr(0);
	                  displayCalendar5(arrDates[i],'yyyy-mm-dd',arrNames[i],1);
	               }
	                if(i==5){
	                  arrDates[i] = arrDates[i].substr(0);
	                  displayCalendar6(arrDates[i],'yyyy-mm-dd',arrNames[i],1);
	               }
	                if(i==6){
	                  arrDates[i] = arrDates[i].substr(0);
	                  displayCalendar7(arrDates[i],'yyyy-mm-dd',arrNames[i],1);
	               }
	                if(i==7){
	                  arrDates[i] = arrDates[i].substr(0);
	                  displayCalendar8(arrDates[i],'yyyy-mm-dd',arrNames[i],1);
	               }
	                if(i==8){
	                  arrDates[i] = arrDates[i].substr(0);
	                  displayCalendar9(arrDates[i],'yyyy-mm-dd',arrNames[i],1);
	               }
	                if(i==9){
	                  arrDates[i] = arrDates[i].substr(0);
	                  displayCalendar10(arrDates[i],'yyyy-mm-dd',arrNames[i],1);
	               }
	               if(i==10){
	                  arrDates[i] = arrDates[i].substr(0);
	                  displayCalendar11(arrDates[i],'yyyy-mm-dd',arrNames[i],1);
	               }
	               if(i==11){
	                  arrDates[i] = arrDates[i].substr(0);
	                  displayCalendar12(arrDates[i],'yyyy-mm-dd',arrNames[i],1);
	               } 
	           }
           }
        }
    </script>
</html>
