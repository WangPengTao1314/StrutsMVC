/**
 * @prjName:系统管理_个人工作计划
 * @fileName:Grgzjh_List_Chld
 * @author wujun
 * @time   2014-12-14 09:42:25 
 * @version 1.1
 */
$(document).ready(function () {

    var operateFullCalendar = {

        objSswdId: $("#SSWDID"),
        objSswdMc: $("#SSWDMC"),
        xfjhId: $("#XFJHID"),
        currentNY: $("#currentNY"),
        currentNYR: $("#currentNYR"),
        year:null,
        month: null,
        date: null,

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
                return parseInt(this.currentNY.val().split('-')[1])-1;
            }
        },

        initEvents : function (){
            $('#calendar').fullCalendar('addEventSource', events);
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
                        $('#calendar').fullCalendar('addEventSource', events);
                        self.xfjhId.val(events[0].xfjhid);
                    }
                    self.objSswdId.val('');
                    self.objSswdMc.val('');
                });
        } ,

        addOneMonth: function (year, month, flag) {
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

        addEvents: function (start, allDay, view) {
            var sswdids = this.objSswdId.val().split(",");
            var sswdmcs = this.objSswdMc.val().split(",");
            var events = [];
            for (var i = 0; i < sswdids.length; i++) {
                if (this.getEvent(sswdids[i] + '-' + start.getDate()).length == 0) {
                    var event = {
                        id: sswdids[i] + "-" + start.getDate(),
                        title: sswdmcs[i],
                        start: start,
                        allDay: allDay,
                        className: 'event-size'
                    };
                    events.push(event);
                }
            }
            var self = this;
            if (events.length > 0) {
                this.sendRequest("addEvents",
                    {"events": this.eventToStr(events), "ryxxid": $("#RYXXID").val(),"jhnf":view.start.getFullYear(),"jhyf":view.start.getMonth()+1,"JHID": this.xfjhId.val()},
                    function (message) {
                        $('#calendar').fullCalendar('addEventSource', events);
                        self.objSswdId.val('');
                        self.objSswdMc.val('');
                        self.xfjhId.val(message);
                    });
            }
        },

        eventToStr : function(events){
            var eventStr = "[";
            var len = events.length;
            for (var i = 0; i < len; i++) {
                eventStr +="{\"XFWDID\":\""+ events[i].id.split('-')[0]+"\",\"JHNF\":\""+events[i].start.getFullYear()+"\",\"JHYF\":\""+ (parseInt(events[i].start.getMonth())+1)+"\",\"JHR\":\""+events[i].start.getDate()+"\"}" ;
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
                {"xfrid":$("#RYXXID").val(),"jhnf":event.start.getFullYear(),"jhyf":(event.start.getMonth()+1),"jhr":event.start.getDate(),"xfwdid":event.id.split('-')[0]},
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
        }
    };

    $("#RYXXID").change(function(){
        operateFullCalendar.reloadEvents(0);
    });

    var calendar = $('#calendar').fullCalendar({
        header: {
            left: '',
            center: '',
            right: ''
        },
        selectable: true,
        selectHelper: true,
        dayClick: function (dayDate, allDay, jsEvent, view) {

        },
        eventClick: function (event, jsEvent, view) {
//            parent._showMsgPanel("您确认要删除吗?", null, function(){operateFullCalendar.deleteEvent(event)},{"funcCancle" : "closeWindow();"},"N");
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
