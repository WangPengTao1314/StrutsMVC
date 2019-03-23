/**
 * @prjName:喜临门
 * @fileName:Grgzjhmx_List
 * @author wu_jun
 * @time   2014-12-15 14:30:01
 * @version 1.1
 */
$(document).ready(function () {

    var operateFullCalendar = {

        objSswdId: $("#SSWDID"),
        objSswdMc: $("#SSWDMC"),
        xfjhId: $("#JHID"),
        currentNY: $("#currentNY"),
        currentNYR: $("#currentNYR"),
        currentUser: $("#currentUser"),
        xfryId: $("#RYXXID"),
        year:null,
        month: null,
        date: null,
		
        initEvents : function (){
            $('#calendar').fullCalendar('addEventSource', events);
        },

        getCurrentUser: function(){
           return this.currentUser.val();
        },

        getXfryId: function(){
           return this.xfryId.val();
        },

        getYear: function(){
            if(this.currentNY.val()=='') {
                return new Date().getFullYear();
            }else{
                return this.currentNY.val().split('-')[0];
            }
        },

        getMonth: function(){
            if(this.currentNY.val()==''){
                return new Date().getMonth();
            }else{
                return new Number(this.currentNY.val().split('-')[1])-1;
            }
        },

        getDate: function(){
        	if(this.currentNY.val()==''){
                return new Date().getDate();
            }else{
                return this.currentNYR.val().split('-')[2];
            }
        },

        reloadEvents: function(inc){
            $("#calendar").fullCalendar("removeEvents");
            this.xfjhId.val('');

            var currentNY = this.currentNY.val();
            var NY =  currentNY.split("-");
            if(inc == 1 || inc == -1){
                currentNY = this.addOneMonth(NY[0],NY[1],inc);
            }
            if(inc == 2){
                currentNY = new Date().getFullYear()+"-"+(new Date().getMonth()+1);
            }
            this.currentNY.val(currentNY);

            var self = this;
            this.sendRequest("allEvents",
                {"xfrid": $("#RYXXID").val(), "currentNY": currentNY},
                function (message) {
                    var events = eval(message);
                    if(events.length == 0){
                        self.xfjhId.val('');
                    }else{
                        for(var i in events){
                            events[i].title = events[i].title.replaceAll("</br>","\n");
                        }
                        $('#calendar').fullCalendar('addEventSource', events);
                        self.xfjhId.val(events[0].xfjhId);
                    }
                    self.objSswdId.val('');
                    self.objSswdMc.val('');
                });
        } ,
        //点击上月下月的事件
        addOneMonth: function (year, month, flag) {
//        	alert("m="+operateFullCalendar.getMonth());
            month = parseInt(month);
            year = parseInt(year);
            if (month == 12) {
                if (flag > 0) {
                    year = year + 1;
                    month = 1;
                } else {
                    month = month - 1;
                }
            }else if (month == 1) {
                if (flag > 0) {
                    month = month + 1;
                } else {
                    month = 12;
                    year = year - 1;
                }
            }else{
                month = month + flag;
            }

            return year + '-' + month;
        },

        addEvents: function (start, allDay, view, eventObj) {
//            var sswdids = this.objSswdId.val().split(",");
//            var sswdmcs = this.objSswdMc.val().split(",");
            var events = [];
//            for (var i = 0; i < sswdids.length; i++) {
//                if (this.getEvent(sswdids[i] + '-' + start.getDate()).length == 0) {
                    var event = {
                        //id: eventObj.eventId,
                        id: '',
                        title: eventObj.eventText,
                        start: start,
                        allDay: allDay,
                        className: 'event-size'
                    };
            event.title = event.title.replace(/\'/g, " ");
            event.title = event.title.replace(/\"/g, " ");
            event.title = event.title.replace(/\·/g, " ");
            event.title = event.title.replace(/\\/g, " ");
                    events.push(event);
//                }
//            }
            var self = this;
            if (events.length > 0) {
                this.sendRequest("addEvents",
                    {"events": this.eventToStr(events),"REMARK":$("#REMARK").val(), "ryxxid": $("#RYXXID").val(),"PLAN_YEAR":view.start.getFullYear(),"PLAN_MONTH":start.getMonth()+1,"PER_WORK_PLAN_ID": this.xfjhId.val()},
                    function (message) {
                        $('#calendar').fullCalendar('addEventSource', events);
                        self.objSswdId.val('');
                        self.objSswdMc.val('');
                        self.xfjhId.val(message);
                    });
            }
        },

        eventToStr : function(events){
        	var impt = document.getElementById("IS_IMPT_FLAG");
            //alert(impt.value);
            var eventStr = "[";
            var len = events.length;
            for (var i = 0; i < len; i++) {
                var title = events[i].title;
                title = title.replace(/\r/g, "</br>");
                title = title.replace(/\n/g, "</br>");
                eventStr +="{\"PER_WORK_PLAN_DTL_ID\":\""+ events[i].id+"\",\"PLAN_CONTENT\":\""+title+"\",\"PLAN_YEAR\":\""+events[i].start.getFullYear()+"\",\"PLAN_MONTH\":\""+ (parseInt(events[i].start.getMonth())+1)+"\",\"PLAN_DAY\":\""+events[i].start.getDate()+"\",\"IS_IMPT_FLAG\":\""+impt.value+"\"}" ;
                if(i < len -1){
                    eventStr += ",";
                }
            }
            eventStr += "]";
            return eventStr;
        },

        getEvent: function (id) {
            return $('#calendar').fullCalendar("clientEvents", id);
        },

        deleteEvent: function (event) {
            this.sendRequest("deleteEvent",
                {"PER_WORK_PLAN_DTL_ID":event.id},
                function (message) {
                    $("#calendar").fullCalendar("removeEvents", event.id);
                });
        },

        sendRequest : function (url,sendData,callback){
            $.ajax({
                url: "grgzjh.shtml?action="+url,
                type:"POST",
                dataType:"text",
                data:sendData,
                complete: function (xhr){
                    eval("jsonResult = "+xhr.responseText);
                    if(jsonResult.success===true){
                        callback(jsonResult.messages);
                    }else{
                        parent.showErrorMsg(utf8Decode(jsonResult.messages));
                    }
                }
            });
        },

        _showMsgPanel: function (msg, title, func, cfg, isWait) {
            cfg = cfg ? cfg : {};
            var w = cfg.width ? cfg.width : 230;
            var h = cfg.height ? cfg.height : 130;
            w = w < 230 ? 230 : w;
            h = h < 130 ? 130 : h;
            var sb = [];
            sb[sb.length] = '<table width="' + w + '" height="' + h + '" border="0" cellpadding="0" cellspacing="0" background="/"+ctxPath+"/styles/"+theme+"/images/index/jsbg.png">';
            sb[sb.length] = '<tr><td height="32"><div align="left" class="mesWindowTop">';
            sb[sb.length] = title ? title : '系统提示';
            sb[sb.length] = '</div></td></tr>';
            sb[sb.length] = '<tr><td valign="middle" align="center"><b>' + msg + '</b></td></tr><tr><td height="15"></td></tr>';
//            sb[sb.length] = '<tr><td valign="middle" align="center"><b>是否重要:&nbsp;&nbsp;&nbsp;<input type="radio" id="imp" name="imp" value="0" onClick="changValue(this)"/>是&nbsp;&nbsp;&nbsp;<input type="radio" id="imp" name="imp"  value="1" onClick="changValue(this)"/>否</b></td></tr><tr><td height="15"></td></tr>';
            sb[sb.length] = '<tr><td height="30" valign="middle" align="center" >';
            sb[sb.length] = '<input id="YT_MSG_BTN_OK" type="button" class="btn1" value="确 定">';
            if (cfg.funcCancle) {
                sb[sb.length] = '&nbsp;&nbsp;&nbsp;<input id="YT_MSG_BTN_CANCLE" type="button" class="btn1" value="取 消">';
            }
            sb[sb.length] = '</td></tr></table>';
            var msgDiv = g("YT_MSG");
            if (msgDiv) {
                msgDiv.innerHTML = sb.join("");
            } else {
                var B = "<div id='YT_MSG'>" + sb.join("") + "</div>";
                showMessageBox(B, w, h + 160, cfg.fast);
            }
            var btnOk = g("YT_MSG_BTN_OK");
            if (btnOk) {
                btnOk.focus();
                btnOk.onkeydown = function () {
                    var event = window.event;
                    if (event.keyCode == 9) {
                        event.returnValue = false;
                        event.keyCode = 0;
                    }
                };
                btnOk.onclick = function () {
                    if(func($("#eventText").val())){
                        return;
                    }
                    showWaitPanel();
                    if (isWait != null && isWait != 'undefined' && isWait != 'Y') {
                        closeWindow();
                    }
                    var btnCancle = g("YT_MSG_BTN_CANCLE");
                    if (btnCancle) {
                        btnCancle.disabled = true;
                    }
                }
            }
            var btnCancle = g("YT_MSG_BTN_CANCLE");
            if (btnCancle) {
                btnCancle.onclick = function () {
                    closeWindow();
                    eval(cfg.funcCancle);
                }
            }
        }
    };

    $("#RYXXID").change(function(){
        operateFullCalendar.reloadEvents(0);
    });

    var calendar = $('#calendar').fullCalendar({
        header: {
            left: '',
            center: 'title',
            right: 'prev,today,next'
        },
        selectable: true,
        selectHelper: true,
        dayClick: function (dayDate, allDay, jsEvent, view) {
//        	alert(view.start.getMonth()); //系统当前时间
//        	alert(dayDate.getMonth());//点击单元格的时间
        	var plan_year_month = $("#plan_year_month").val();//工作计划的年月
        	if(null != plan_year_month && "" != plan_year_month){
        		var plan_month = plan_year_month.split('-')[1];
        		var head_month = operateFullCalendar.getMonth();//工作计划表头的月份
        		var click_month = dayDate.getMonth();//点击单元格的时间
        		click_month = parseInt(click_month)+1;
        		head_month = parseInt(head_month)+1;
        		plan_month = parseInt(plan_month);
        		//工作计划的时间与表头的时间不等 不可以编辑
//        		if(plan_month != head_month){
//        			return;
//        		} 
        		if(head_month != click_month){//表头的时间与点击单元格的的时间不等 不可以编辑
        			return;
        		}
        	}
         
            if(operateFullCalendar.getCurrentUser() != operateFullCalendar.getXfryId()){
                return;
            }
            var curDate = new Date();
            var curMon = curDate.getMonth()+1;
            //if ((new Date().getMonth()+1) == (dayDate.getMonth() + 1)) {
                var ryr = operateFullCalendar.currentNYR.val().split('-');
                var currentDate = new Date(ryr[0], parseInt(ryr[1]) - 1, ryr[2]);
                currentDate = new Date();
                var year = currentDate.getFullYear();    //获取完整的年份(4位,1970-????)
				var month = currentDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
				if(month<10) month = "0"+month;	
				var date = currentDate.getDate(); 
				if(date<10) date = "0"+date;
				currentDate = year+""+month+""+date;
                var clickDate = new Date(dayDate.getFullYear(), dayDate.getMonth(), dayDate.getDate());
               
                var clickYear = clickDate.getFullYear();
                var clickMonth = clickDate.getMonth()+1;
                if(clickMonth<10) clickMonth = "0"+clickMonth;	
                var clickR = clickDate.getDate();
                if(clickR<10) clickR = "0"+clickR;	
                clickDate = clickYear+""+clickMonth+""+clickR;
                //if((parseInt(currentDate) - parseInt(clickDate)) >0 ){
                //    parent.showErrorMsg("请操作当月并且是当前日期及其以后的计划！");
                //    return;
                //}
//                var obj = selCommon('BS_9', true, 'SSWDID', 'SSWDID', 'hiddenForm', 'SSWDID,SSWDMC', 'SSWDID,SSWDMC', 'selcondition');
                operateFullCalendar._showMsgPanel("<textarea type='text' rows='8' cols='45' style='overflow:hidden' id='eventText'></textarea>","个人工作计划",function(eventText){
                    if(eventText.length>200){
                        parent.showErrorMsg("寻访计划最大只能输入200个字！");
                        return true;
                    }
                    if(eventText.length==0){
                        parent.showErrorMsg("请填写个人工作计划！");
                        return true;
                    }
                    operateFullCalendar.sendRequest("getUUID",{},function(message){
                        var event = {"eventId":message, "eventText":eventText};
                        operateFullCalendar.addEvents(dayDate, allDay, view,event);
                    });
                },{"funcCancle" : "closeWindow();","width":400,"height":250},"N");
//                if (obj[1]) {
//                    operateFullCalendar.addEvents(dayDate, allDay, view);
//                }
            //} else {
           //     parent.showErrorMsg("请操作当月并且是当前日期及其以后的计划！");
            //}
        },
        eventClick: function (event, jsEvent, view) {
//        	var plan_year_month = $("#plan_year_month").val();//工作计划的年月
//        	var year_day = $.fullCalendar.formatDate(event.start, "yyyy-MM");//事件上的年月
//        	var head_month = operateFullCalendar.getYear()+"-"+(operateFullCalendar.getMonth()+1);//工作计划表头的月份
//        	 
//        	if(plan_year_month != year_day){
//        		return;
//        	}
        	
            if(operateFullCalendar.getCurrentUser() != operateFullCalendar.getXfryId()){
                return;
            }
            operateFullCalendar._showMsgPanel("您确认要删除吗?", null, function(){operateFullCalendar.deleteEvent(event)},{"funcCancle" : "closeWindow();"},"N");
        },
        editable: false,
        monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
        dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
        buttonText: {
            today: '今天',
            month: '月',
            week: '周',
            day: '日'
        },
        operator: operateFullCalendar,
        year: operateFullCalendar.getYear(),
        month: operateFullCalendar.getMonth()
    });
    
    operateFullCalendar.initEvents();
});

function changValue(obj){
	var imp = document.getElementsByName("IS_IMPT_FLAG");
	imp[0].value = obj.value;
}

//返回
function gobacknew() {
    newGoBack("grgzjh.shtml?action=toFrames");
}


function saveMainRemark(){
	var REMARK = $("#REMARK").val();
	var PER_WORK_PLAN_ID = parent.$("#selRowId").val();
	$.ajax({
        url: "grgzjh.shtml?action=saveRemark",
        type:"POST",
        dataType:"text",
        data:{"PER_WORK_PLAN_ID":PER_WORK_PLAN_ID,"REMARK":REMARK},
        complete: function (xhr){
            eval("jsonResult = "+xhr.responseText);
            if(jsonResult.success===true){
                 parent.showMsgPanel("保存成功")
            }else{
                parent.showErrorMsg(utf8Decode(jsonResult.messages));
            }
        }
    });
	
}


function changeTextAreaLength(obj) {
	var upper = obj.className;
	if("uppercase"==upper){
		obj.value=obj.value.toUpperCase();
	}
	var maxL = obj.maxlength || obj.maxLength;
	var L = stringLength(obj.value);
	if (L > maxL) {
		obj.value = obj.oVal;
	} else {
		obj.oVal = obj.value;
	}
}