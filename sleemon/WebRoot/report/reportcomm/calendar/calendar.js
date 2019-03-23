var turnOffYearSpan = false;     // true = Only show This Year and Next, false = show +/- 5 years
var weekStartsOnSunday = false;  // true = Start the week on Sunday, false = start the week on Monday
var showWeekNumber = true;  // true = show week number,  false = do not show week number

var languageCode = 'en';	// Possible values: 	en,ge,no,nl,es,pt-br,fr
							// en = english, ge = german, no = norwegian,nl = dutch, es = spanish, pt-br = portuguese, fr = french, da = danish, hu = hungarian(Use UTF-8 doctype for hungarian)

var calendar_display_time = true;

// Format of current day at the bottom of the calendar
// [todayString] = the value of todayString
// [dayString] = day of week (examle: mon, tue, wed...)
// [UCFdayString] = day of week (examle: Mon, Tue, Wed...) ( First letter in uppercase)
// [day] = Day of month, 1..31
// [monthString] = Name of current month
// [year] = Current year
var todayStringFormat = '[todayString] [UCFdayString]. [day]. [monthString] [year]';
var pathToImages = ctxPath+'/report/reportcomm/calendar/image/';	// Relative to your HTML file

var speedOfSelectBoxSliding = 200;	// Milliseconds between changing year and hour when holding mouse over "-" and "+" - lower value = faster
var intervalSelectBox_minutes = 5;	// Minute select box - interval between each option (5 = default)

var calendar_offsetTop = 0;		// Offset - calendar placement - You probably have to modify this value if you're not using a strict doctype
var calendar_offsetLeft = 0;	// Offset - calendar placement - You probably have to modify this value if you're not using a strict doctype
var calendarDiv = false;
var calendarDiv1 = false;
var calendarDiv2 = false;
var calendarDiv3 = false;
var calendarDiv4 = false;
var calendarDiv5 = false;
var calendarDiv6 = false;
var calendarDiv7 = false;
var calendarDiv8 = false;
var calendarDiv9 = false;
var calendarDiv10 = false;
var calendarDiv11 = false;
var calendarDiv12 = false;
var calendarDiv13 = false;

var calendarDiv24 = false;
var calendarDiv36 = false;



var mkmName="营销经理名称：";
var MSIE = false;
var Opera = false;
var inputId="";
if(navigator.userAgent.indexOf('MSIE')>=0 && navigator.userAgent.indexOf('Opera')<0)MSIE=true;
if(navigator.userAgent.indexOf('Opera')>=0)Opera=true;


switch(languageCode){
	case "en":	/* English */
		var monthArray = ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
		var monthArrayShort = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
		var dayArray = ['Mon','Tue','Wed','Thu','Fri','Sat','Sun'];
		var weekString = 'Week';
		var todayString = '';
		break;
	case "ge":	/* German */
		var monthArray = ['Januar','Februar','M�rz','April','Mai','Juni','Juli','August','September','Oktober','November','Dezember'];
		var monthArrayShort = ['Jan','Feb','Mar','Apr','Mai','Jun','Jul','Aug','Sep','Okt','Nov','Dez'];
		var dayArray = ['Mon','Die','Mit','Don','Fre','Sam','Son'];
		var weekString = 'Woche';
		var todayString = 'Heute';
		break;
	case "no":	/* Norwegian */
		var monthArray = ['Januar','Februar','Mars','April','Mai','Juni','Juli','August','September','Oktober','November','Desember'];
		var monthArrayShort = ['Jan','Feb','Mar','Apr','Mai','Jun','Jul','Aug','Sep','Okt','Nov','Des'];
		var dayArray = ['Man','Tir','Ons','Tor','Fre','L&oslash;r','S&oslash;n'];
		var weekString = 'Uke';
		var todayString = 'Dagen i dag er';
		break;
	case "nl":	/* Dutch */
		var monthArray = ['Januari','Februari','Maart','April','Mei','Juni','Juli','Augustus','September','Oktober','November','December'];
		var monthArrayShort = ['Jan','Feb','Mar','Apr','Mei','Jun','Jul','Aug','Sep','Okt','Nov','Dec'];
		var dayArray = ['Ma','Di','Wo','Do','Vr','Za','Zo'];
		var weekString = 'Week';
		var todayString = 'Vandaag';
		break;
	case "es": /* Spanish */
		var monthArray = ['Enero','Febrero','Marzo','April','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'];
		var monthArrayShort =['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'];
		var dayArray = ['Lun','Mar','Mie','Jue','Vie','Sab','Dom'];
		var weekString = 'Semana';
		var todayString = 'Hoy es';
		break;
	case "pt-br":  /* Brazilian portuguese (pt-br) */
		var monthArray = ['Janeiro','Fevereiro','Mar&ccedil;o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'];
		var monthArrayShort = ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'];
		var dayArray = ['Seg','Ter','Qua','Qui','Sex','S&aacute;b','Dom'];
		var weekString = 'Sem.';
		var todayString = 'Hoje &eacute;';
		break;
	case "fr":      /* French */
		var monthArray = ['Janvier','F�vrier','Mars','Avril','Mai','Juin','Juillet','Ao�t','Septembre','Octobre','Novembre','D�cembre'];
		var monthArrayShort = ['Jan','Fev','Mar','Avr','Mai','Jun','Jul','Aou','Sep','Oct','Nov','Dec'];
		var dayArray = ['Lun','Mar','Mer','Jeu','Ven','Sam','Dim'];
		var weekString = 'Sem';
		var todayString = "Aujourd'hui";
		break;
	case "da": /*Danish*/
		var monthArray = ['januar','februar','marts','april','maj','juni','juli','august','september','oktober','november','december'];
		var monthArrayShort = ['jan','feb','mar','apr','maj','jun','jul','aug','sep','okt','nov','dec'];
		var dayArray = ['man','tirs','ons','tors','fre','l&oslash;r','s&oslash;n'];
		var weekString = 'Uge';
		var todayString = 'I dag er den';
		break;
	case "hu":	/* Hungarian  - Remember to use UTF-8 encoding, i.e. the <meta> tag */
		var monthArray = ['Január','Február','Március','�?prilis','Május','Június','Július','Augusztus','Szeptember','Október','November','December'];
		var monthArrayShort = ['Jan','Feb','Márc','�?pr','Máj','Jún','Júl','Aug','Szep','Okt','Nov','Dec'];
		var dayArray = ['Hé','Ke','Sze','Cs','Pé','Szo','Vas'];
		var weekString = 'Hét';
		var todayString = 'Mai nap';
		break;
	case "it":	/* Italian*/
		var monthArray = ['Gennaio','Febbraio','Marzo','Aprile','Maggio','Giugno','Luglio','Agosto','Settembre','Ottobre','Novembre','Dicembre'];
		var monthArrayShort = ['Gen','Feb','Mar','Apr','Mag','Giu','Lugl','Ago','Set','Ott','Nov','Dic'];
		var dayArray = ['Lun',';Mar','Mer','Gio','Ven','Sab','Dom'];
		var weekString = 'Settimana';
		var todayString = 'Oggi &egrave; il';
		break;
	case "sv":	/* Swedish */
		var monthArray = ['Januari','Februari','Mars','April','Maj','Juni','Juli','Augusti','September','Oktober','November','December'];
		var monthArrayShort = ['Jan','Feb','Mar','Apr','Maj','Jun','Jul','Aug','Sep','Okt','Nov','Dec'];
		var dayArray = ['M&aring;n','Tis','Ons','Tor','Fre','L&ouml;r','S&ouml;n'];
		var weekString = 'Vecka';
		var todayString = 'Idag &auml;r det den';
		break;
	case "cz":	/* Czech */
		var monthArray = ['leden','&#250;nor','b&#345;ezen','duben','kv&#283;ten','&#269;erven','&#269;ervenec','srpen','z&#225;&#345;&#237;','&#345;&#237;jen','listopad','prosinec'];
		var monthArrayShort = ['led','&#250;n','b&#345;','dub','kv&#283;','&#269;er','&#269;er-ec','srp','z&#225;&#345;','&#345;&#237;j','list','pros'];
		var dayArray = ['Pon','&#218;t','St','&#268;t','P&#225;','So','Ne'];
		var weekString = 't&#253;den';
		var todayString = '';
		break;	
}

if (weekStartsOnSunday) {
   var tempDayName = dayArray[6];
   for(var theIx = 6; theIx > 0; theIx--) {
      dayArray[theIx] = dayArray[theIx-1];
   }
   dayArray[0] = tempDayName;
}

var daysInMonthArray = [31,28,31,30,31,30,31,31,30,31,30,31];
var currentMonth;
var currentYear;
var currentHour;
var currentMinute;
var calendarContentDiv;
var calendarContentDiv1;
var calendarContentDiv2;
var calendarContentDiv3;
var calendarContentDiv4;
var calendarContentDiv5;
var calendarContentDiv6;
var calendarContentDiv7;
var calendarContentDiv8;
var calendarContentDiv9;
var calendarContentDiv10;
var calendarContentDiv11;
var calendarContentDiv12;
var calendarContentDiv13;

var calendarContentDiv24;
var calendarContentDiv36;

var returnDateTo;
var returnFormat;
var activeSelectBoxMonth;
var activeSelectBoxYear;
var activeSelectBoxHour;
var activeSelectBoxMinute;

var iframeObj = false;
var iframeObj2 =false;
var iframeObj3 =false;
var iframeObj4 =false;
var iframeObj5 =false;
var iframeObj6 =false;
var iframeObj7 =false;
var iframeObj8 =false;
var iframeObj9 =false;
var iframeObj10 =false;
var iframeObj11 =false;
var iframeObj12 =false;
var iframeObj13 =false;

var iframeObj24 =false;
var iframeObj36 =false;

var inputYear;
var inputMonth;
var inputDay;
var inputHour;
var inputMinute;
var calendarDisplayTime = false;
var mkmIds;

var selectBoxHighlightColor = '#D60808'; // Highlight color of select boxes
var selectBoxRolloverBgColor = '#E2EBED'; // Background color on drop down lists(rollover)

var selectBoxMovementInProgress = false;
var activeSelectBox = false;

function isLeapYear(inputYear)
{
	if(inputYear%400==0||(inputYear%4==0&&inputYear%100!=0)) return true;
	return false;

}
var activeSelectBoxMonth = false;
var activeSelectBoxDirection = false;

function calendarSortItems(a,b)
{
	return a/1 - b/1;
}

function getWeek(year,month,day){
   if (! weekStartsOnSunday) {
	   day = (day/1);
	} else {
	   day = (day/1)+1;
	}
	year = year /1;
    month = month/1 + 1; //use 1-12
    var a = Math.floor((14-(month))/12);
    var y = year+4800-a;
    var m = (month)+(12*a)-3;
    var jd = day + Math.floor(((153*m)+2)/5) +
                 (365*y) + Math.floor(y/4) - Math.floor(y/100) +
                 Math.floor(y/400) - 32045;      // (gregorian calendar)
    var d4 = (jd+31741-(jd%7))%146097%36524%1461;
    var L = Math.floor(d4/1460);
    var d1 = ((d4-L)%365)+L;
    NumberOfWeek = Math.floor(d1/7) + 1;
    return NumberOfWeek;
}

function initCalendar(mkmName,inputField)
{
	if(MSIE){
		iframeObj = document.createElement('IFRAME');
		iframeObj.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj);
	}

	calendarDiv = document.createElement('DIV');
	calendarDiv.id = 'calendarDiv';
	//inputId = mkmId;
	document.body.appendChild(calendarDiv);
	writeBottomBar(mkmName);
	writeTopBar();
	writeCalendarContent(inputField,mkmName);
}
 
function writeTopBar()
{

	var topBar = document.createElement('DIV');
	topBar.className = 'topBar';
	topBar.id = 'topBar';
	calendarDiv.appendChild(topBar);

	// Left arrow
	var leftDiv = document.createElement('DIV');
	leftDiv.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	 
	leftDiv.appendChild(img);
	if(Opera)leftDiv.style.width = '16px';

	// Right arrow
	var rightDiv = document.createElement('DIV');
	rightDiv.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv.appendChild(img);
	if(Opera)rightDiv.style.width = '16px';
	topBar.appendChild(rightDiv);

    
	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	topBar.appendChild(monthDiv);
	
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt';
	yearDiv.appendChild(span);
	topBar.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox'; 
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}


function writeBottomBar(name)
{
	var d = new Date();
	var bottomBar = document.createElement('DIV');
	bottomBar.id = 'bottomBar';
	bottomBar.style.cursor = 'pointer';
	bottomBar.className = 'todaysDate';
	var subDiv = document.createElement('DIV');
	//subDiv.onclick = pickTodaysDate;
	subDiv.id = 'todaysDateString';
	//subDiv.value=mkmId
	//subDiv.style.width = (calendarDiv.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
    var bottomString = mkmName+name
	subDiv.innerHTML = bottomString ;
	subDiv.style.left='20px';
	//subDiv.onclick = alert("1");  //queryById(mkmId);
	bottomBar.appendChild(subDiv);
	calendarDiv.appendChild(bottomBar);
	//calendarDiv.onclick =alert("1");
}


function writeCalendarContent(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv){
		calendarContentDiv = document.createElement('DIV');
		calendarDiv.appendChild(calendarContentDiv);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
	var existingTable = calendarContentDiv.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		cell.innerHTML = no;
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv.offsetHeight)
			document.getElementById('topBar').style.top = calendarContentDiv.offsetHeight + document.getElementById('timeBar').offsetHeight + document.getElementById('topBar').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar').style.top = '';
			document.getElementById('topBar').style.bottom = '0px';
		}
	}
	if(iframeObj){
		if(!calendarContentDivExists)setTimeout('resizeIframe()',350);else setTimeout('resizeIframe()',10);
	}
}

function displayCalendar1(inputField,format,mkmName)
{     
       var arr = new Array();
       arr = inputField.split(",");
       for(var i=0;i<arr.length;i++) {
       if(!arr[i].match(/^[0-9]*?$/gi)){
             
			var items = arr[i].split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var j=0;j<elements.length;j++) {
				if(positionArray[elements[j]]>=0) {
					window[properties[j]] = arr[i].substr(positionArray[elements[j]],propertyLength[j])/1;
			    }	
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = arr[i].substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = arr[i].substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = arr[i].substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = arr[i].substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = arr[i].substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}
     inputYear = currentYear;
	 inputMonth = currentMonth;

	if(!calendarDiv){
		initCalendar(mkmName,inputField);
	}else{
		//writeCalendarContent(inputField,mkmName);
	}
	returnFormat = format;
	returnDateTo = arr[i];
	calendarDiv.style.visibility = 'visible';
	calendarDiv.style.display = 'block';
	if(iframeObj){
		iframeObj.style.display = '';
		iframeObj.style.height = '140px';
		iframeObj.style.width = '195px';
	}
   }
}
 
function initCalendar1(mkmName,inputField)
{
	if(MSIE){
		iframeObj2 = document.createElement('IFRAME');
		iframeObj2.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj2);  // gfb move this down AFTER the .src is set
	}
	calendarDiv1 = document.createElement('DIV');
	calendarDiv1.id = 'calendarDiv1';
	document.body.appendChild(calendarDiv1);
	//inputId = mkmId,
	writeBottomBar1(mkmName);
	writeTopBar1();
	writeCalendarContent1(inputField,mkmName);
}

function writeTopBar1()
{
	var topBar1 = document.createElement('DIV');
	topBar1.className = 'topBar';
	topBar1.id = 'topBar1';
	calendarDiv1.appendChild(topBar1);

	// Left arrow
	var leftDiv1 = document.createElement('DIV');
	leftDiv1.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv1.appendChild(img);
	topBar1.appendChild(leftDiv1);
	if(Opera)leftDiv1.style.width = '16px';

	// Right arrow
	var rightDiv1 = document.createElement('DIV');
	rightDiv1.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv1.appendChild(img);
	if(Opera)rightDiv1.style.width = '16px';
	topBar1.appendChild(rightDiv1);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect1';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt1';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar1.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt1';
	yearDiv.appendChild(span);
	topBar1.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

 
function writeBottomBar1(name)
{
	var d = new Date();
	var bottomBar1 = document.createElement('DIV');
	bottomBar1.id = 'bottomBar1';
	bottomBar1.style.cursor = 'pointer';
	bottomBar1.className = 'todaysDate';

	var subDiv1 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv1.id = 'todaysDateString1';
	//subDiv1.value=mkmId
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = mkmName+name;
	subDiv1.innerHTML = bottomString ;
	subDiv1.style.left='20px';
	bottomBar1.appendChild(subDiv1);
	calendarDiv1.appendChild(bottomBar1);
}

function writeCalendarContent1(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv1){
		calendarContentDiv1 = document.createElement('DIV');
		calendarDiv1.appendChild(calendarContentDiv1);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv1.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv1.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv1.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		
		cell.innerHTML = no;
		/*
		inputId = "";
		//inputId= mkmId;
		cell.onclick = function(){
		     alert("this.no===="+no);
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		};//pickDate;*/
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv1.offsetHeight)
			document.getElementById('topBar1').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar1').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar1').style.top = '';
			document.getElementById('topBar1').style.bottom = '0px';
		}
	}
	if(iframeObj2){
		if(!calendarContentDivExists)setTimeout('resizeIframe1()',350);else setTimeout('resizeIframe1()',10);
	}
}

function resizeIframe()
{
	iframeObj.style.width = calendarDiv.offsetWidth + 'px';
	iframeObj.style.height = calendarDiv.offsetHeight + 'px' ;
}

function displayCalendar2(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv1){
		initCalendar1(mkmName,inputField);
	}else{
		writeCalendarContent1(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv1.style.visibility = 'visible';
	calendarDiv1.style.display = 'block';
	if(iframeObj2){
		iframeObj2.style.display = '';
		iframeObj2.style.height = '140px';
		iframeObj2.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe1()
{
	iframeObj2.style.width = calendarDiv1.offsetWidth + 'px';
	iframeObj2.style.height = calendarDiv1.offsetHeight + 'px' ;
}

function initCalendar2(mkmName,inputField)
{
	if(MSIE){
		iframeObj3 = document.createElement('IFRAME');
		iframeObj3.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj3);  // gfb move this down AFTER the .src is set
	}
	calendarDiv2 = document.createElement('DIV');
	calendarDiv2.id = 'calendarDiv2';
	document.body.appendChild(calendarDiv2);
	writeBottomBar2(mkmName);
	writeTopBar2();
	writeCalendarContent2(inputField,mkmName);
}

function writeTopBar2()
{
	var topBar2 = document.createElement('DIV');
	topBar2.className = 'topBar';
	topBar2.id = 'topBar2';
	calendarDiv2.appendChild(topBar2);

	// Left arrow
	var leftDiv2 = document.createElement('DIV');
	leftDiv2.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv2.appendChild(img);
	topBar2.appendChild(leftDiv2);
	if(Opera)leftDiv2.style.width = '16px';

	// Right arrow
	var rightDiv2 = document.createElement('DIV');
	rightDiv2.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv2.appendChild(img);
	if(Opera)rightDiv2.style.width = '16px';
	topBar2.appendChild(rightDiv2);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect2';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt2';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar2.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt2';
	yearDiv.appendChild(span);
	topBar2.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar2(name)
{
	var d = new Date();
	var bottomBar2 = document.createElement('DIV');
	bottomBar2.id = 'bottomBar2';
	bottomBar2.style.cursor = 'pointer';
	bottomBar2.className = 'todaysDate';

	var subDiv2 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv2.id = 'todaysDateString2';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
    var bottomString = mkmName+name;
	subDiv2.innerHTML = bottomString ;
	subDiv2.style.left='20px';
	bottomBar2.appendChild(subDiv2);
	calendarDiv2.appendChild(bottomBar2);
}


function writeCalendarContent2(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv2){
		calendarContentDiv2 = document.createElement('DIV');
		calendarDiv2.appendChild(calendarContentDiv2);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv2.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv2.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv2.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		cell.innerHTML = no;
		//window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
	 
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv2.offsetHeight)
			document.getElementById('topBar2').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar2').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar2').style.top = '';
			document.getElementById('topBar2').style.bottom = '0px';
		}
	}
	if(iframeObj3){
		if(!calendarContentDivExists)setTimeout('resizeIframe2()',350);else setTimeout('resizeIframe2()',10);
	}
}

function displayCalendar3(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv2){
		initCalendar2(mkmName,inputField);
	}else{
		writeCalendarContent2(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv2.style.visibility = 'visible';
	calendarDiv2.style.display = 'block';
	if(iframeObj3){
		iframeObj3.style.display = '';
		iframeObj3.style.height = '140px';
		iframeObj3.style.width = '195px';
	}
	//setTimeProperties1();
}


function resizeIframe2()
{
	iframeObj3.style.width = calendarDiv2.offsetWidth + 'px';
	iframeObj3.style.height = calendarDiv2.offsetHeight + 'px' ;
}

///////////////////////////////////
function initCalendar3(mkmName,inputField)
{
	if(MSIE){
		iframeObj4 = document.createElement('IFRAME');
		iframeObj4.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj4);  // gfb move this down AFTER the .src is set
	}
	calendarDiv3 = document.createElement('DIV');
	calendarDiv3.id = 'calendarDiv3';
	document.body.appendChild(calendarDiv3);
	writeBottomBar3(mkmName);
	writeTopBar3();
	writeCalendarContent3(inputField,mkmName);
}

function writeTopBar3()
{
	var topBar3 = document.createElement('DIV');
	topBar3.className = 'topBar';
	topBar3.id = 'topBar3';
	calendarDiv3.appendChild(topBar3);

	// Left arrow
	var leftDiv3 = document.createElement('DIV');
	leftDiv3.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv3.appendChild(img);
	topBar3.appendChild(leftDiv3);
	if(Opera)leftDiv3.style.width = '16px';

	// Right arrow
	var rightDiv3 = document.createElement('DIV');
	rightDiv3.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv3.appendChild(img);
	if(Opera)rightDiv3.style.width = '16px';
	topBar3.appendChild(rightDiv3);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect3';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt3';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar3.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt3';
	yearDiv.appendChild(span);
	topBar3.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar3(name)
{
	var d = new Date();
	var bottomBar3 = document.createElement('DIV');
	bottomBar3.id = 'bottomBar3';
	bottomBar3.style.cursor = 'pointer';
	bottomBar3.className = 'todaysDate';

	var subDiv3 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv3.id = 'todaysDateString3';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv3.innerHTML = bottomString ;
	subDiv3.style.left='20px';
	bottomBar3.appendChild(subDiv3);
	calendarDiv3.appendChild(bottomBar3);
}


function writeCalendarContent3(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv3){
		calendarContentDiv3 = document.createElement('DIV');
		calendarDiv3.appendChild(calendarContentDiv3);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv3.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv3.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv3.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		
		cell.innerHTML = no;
		//cell.onclick = function(){
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv3.offsetHeight)
			document.getElementById('topBar3').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar3').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar3').style.top = '';
			document.getElementById('topBar3').style.bottom = '0px';
		}
	}
	if(iframeObj4){
		if(!calendarContentDivExists)setTimeout('resizeIframe3()',350);else setTimeout('resizeIframe3()',10);
	}
}

function displayCalendar4(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv3){
		initCalendar3(mkmName,inputField);
	}else{
		writeCalendarContent3(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv3.style.visibility = 'visible';
	calendarDiv3.style.display = 'block';
	if(iframeObj4){
		iframeObj4.style.display = '';
		iframeObj4.style.height = '140px';
		iframeObj4.style.width = '195px';
	}
	//setTimeProperties1();
}


function resizeIframe3()
{
	iframeObj4.style.width = calendarDiv2.offsetWidth + 'px';
	iframeObj4.style.height = calendarDiv2.offsetHeight + 'px' ;
}



 ////////////////////////////////////////////////////
function initCalendar5(mkmName,inputField)
{
	if(MSIE){
		iframeObj5 = document.createElement('IFRAME');
		iframeObj5.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj5);  // gfb move this down AFTER the .src is set
	}
	calendarDiv5 = document.createElement('DIV');
	calendarDiv5.id = 'calendarDiv5';
	document.body.appendChild(calendarDiv5);
	writeBottomBar5(mkmName);
	writeTopBar5();
	writeCalendarContent5(inputField,mkmName);
}

function writeTopBar5()
{
	var topBar5 = document.createElement('DIV');
	topBar5.className = 'topBar';
	topBar5.id = 'topBar5';
	calendarDiv5.appendChild(topBar5);

	// Left arrow
	var leftDiv5 = document.createElement('DIV');
	leftDiv5.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv5.appendChild(img);
	topBar5.appendChild(leftDiv5);
	if(Opera)leftDiv5.style.width = '16px';

	// Right arrow
	var rightDiv5 = document.createElement('DIV');
	rightDiv5.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv5.appendChild(img);
	if(Opera)rightDiv5.style.width = '16px';
	topBar5.appendChild(rightDiv5);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect5';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt5';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar5.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt2';
	yearDiv.appendChild(span);
	topBar5.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar5(name)
{
	var d = new Date();
	var bottomBar5 = document.createElement('DIV');
	bottomBar5.id = 'bottomBar5';
	bottomBar5.style.cursor = 'pointer';
	bottomBar5.className = 'todaysDate';

	var subDiv5 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv5.id = 'todaysDateString5';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv5.innerHTML = bottomString ;
	subDiv5.style.left='20px';
	bottomBar5.appendChild(subDiv5);
	calendarDiv5.appendChild(bottomBar5);
}


function writeCalendarContent5(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv5){
		calendarContentDiv5 = document.createElement('DIV');
		calendarDiv5.appendChild(calendarContentDiv5);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv5.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv5.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv5.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		cell.innerHTML = no;
		//cell.onclick = function(){
		   // window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv5.offsetHeight)
			document.getElementById('topBar5').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar5').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar5').style.top = '';
			document.getElementById('topBar4').style.bottom = '0px';
		}
	}
	if(iframeObj5){
		if(!calendarContentDivExists)setTimeout('resizeIframe5()',350);else setTimeout('resizeIframe5()',10);
	}
}

function displayCalendar5(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv5){
		initCalendar5(mkmName,inputField);
	}else{
		writeCalendarContent5(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv5.style.visibility = 'visible';
	calendarDiv5.style.display = 'block';
	if(iframeObj5){
		iframeObj5.style.display = '';
		iframeObj5.style.height = '140px';
		iframeObj5.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe5()
{
	iframeObj5.style.width = calendarDiv5.offsetWidth + 'px';
	iframeObj5.style.height = calendarDiv5.offsetHeight + 'px' ;
}



function initCalendar6(mkmName,inputField)
{
	if(MSIE){
		iframeObj6 = document.createElement('IFRAME');
		iframeObj6.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj6);  // gfb move this down AFTER the .src is set
	}
	calendarDiv6 = document.createElement('DIV');
	calendarDiv6.id = 'calendarDiv6';
	document.body.appendChild(calendarDiv6);
	writeBottomBar6(mkmName);
	writeTopBar6();
	writeCalendarContent6(inputField,mkmName);
}

function writeTopBar6()
{
	var topBar6 = document.createElement('DIV');
	topBar6.className = 'topBar';
	topBar6.id = 'topBar6';
	calendarDiv6.appendChild(topBar6);

	// Left arrow
	var leftDiv6 = document.createElement('DIV');
	leftDiv6.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv6.appendChild(img);
	topBar6.appendChild(leftDiv6);
	if(Opera)leftDiv6.style.width = '16px';

	// Right arrow
	var rightDiv6 = document.createElement('DIV');
	rightDiv6.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv6.appendChild(img);
	if(Opera)rightDiv6.style.width = '16px';
	topBar6.appendChild(rightDiv6);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect6';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt6';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar6.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt6';
	yearDiv.appendChild(span);
	topBar6.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar6(name)
{
	var d = new Date();
	var bottomBar6 = document.createElement('DIV');
	bottomBar6.id = 'bottomBar6';
	bottomBar6.style.cursor = 'pointer';
	bottomBar6.className = 'todaysDate';

	var subDiv6 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv6.id = 'todaysDateString6';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv6.innerHTML = bottomString ;
	subDiv6.style.left='20px';
	bottomBar6.appendChild(subDiv6);
	calendarDiv6.appendChild(bottomBar6);
}


function writeCalendarContent6(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv6){
		calendarContentDiv6 = document.createElement('DIV');
		calendarDiv6.appendChild(calendarContentDiv6);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv6.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv6.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv6.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		cell.innerHTML = no;
		//cell.onclick = function(){
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv6.offsetHeight)
			document.getElementById('topBar6').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar6').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar6').style.top = '';
			document.getElementById('topBar6').style.bottom = '0px';
		}
	}
	if(iframeObj6){
		if(!calendarContentDivExists)setTimeout('resizeIframe6()',350);else setTimeout('resizeIframe6()',10);
	}
}

function displayCalendar6(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv6){
		initCalendar6(mkmName,inputField);
	}else{
		writeCalendarContent6(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv6.style.visibility = 'visible';
	calendarDiv6.style.display = 'block';
	if(iframeObj6){
		iframeObj6.style.display = '';
		iframeObj6.style.height = '140px';
		iframeObj6.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe6()
{
	iframeObj6.style.width = calendarDiv6.offsetWidth + 'px';
	iframeObj6.style.height = calendarDiv6.offsetHeight + 'px' ;
}




function initCalendar7(mkmName,inputField)
{
	if(MSIE){
		iframeObj7 = document.createElement('IFRAME');
		iframeObj7.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj7);  // gfb move this down AFTER the .src is set
	}
	calendarDiv7 = document.createElement('DIV');
	calendarDiv7.id = 'calendarDiv7';
	document.body.appendChild(calendarDiv7);
	writeBottomBar7(mkmName);
	writeTopBar7();
	writeCalendarContent7(inputField,mkmName);
}

function writeTopBar7()
{
	var topBar7 = document.createElement('DIV');
	topBar7.className = 'topBar';
	topBar7.id = 'topBar7';
	calendarDiv7.appendChild(topBar7);

	// Left arrow
	var leftDiv7 = document.createElement('DIV');
	leftDiv7.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv7.appendChild(img);
	topBar7.appendChild(leftDiv7);
	if(Opera)leftDiv7.style.width = '16px';

	// Right arrow
	var rightDiv7 = document.createElement('DIV');
	rightDiv7.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv7.appendChild(img);
	if(Opera)rightDiv7.style.width = '16px';
	topBar7.appendChild(rightDiv7);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect7';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt6';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar7.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt6';
	yearDiv.appendChild(span);
	topBar7.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar7(name)
{
	var d = new Date();
	var bottomBar7 = document.createElement('DIV');
	bottomBar7.id = 'bottomBar7';
	bottomBar7.style.cursor = 'pointer';
	bottomBar7.className = 'todaysDate';

	var subDiv7 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv7.id = 'todaysDateString7';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv7.innerHTML = bottomString ;
	subDiv7.style.left='20px';
	bottomBar7.appendChild(subDiv7);
	calendarDiv7.appendChild(bottomBar7);
}


function writeCalendarContent7(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv7){
		calendarContentDiv7 = document.createElement('DIV');
		calendarDiv7.appendChild(calendarContentDiv7);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv7.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv7.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv7.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		cell.innerHTML = no;
		//cell.onclick = function(){
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv7.offsetHeight)
			document.getElementById('topBar7').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar7').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar7').style.top = '';
			document.getElementById('topBar7').style.bottom = '0px';
		}
	}
	if(iframeObj7){
		if(!calendarContentDivExists)setTimeout('resizeIframe7()',350);else setTimeout('resizeIframe7()',10);
	}
}

function displayCalendar7(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv7){
		initCalendar7(mkmName,inputField);
	}else{
		writeCalendarContent7(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv7.style.visibility = 'visible';
	calendarDiv7.style.display = 'block';
	if(iframeObj7){
		iframeObj7.style.display = '';
		iframeObj7.style.height = '140px';
		iframeObj7.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe7()
{
	iframeObj7.style.width = calendarDiv7.offsetWidth + 'px';
	iframeObj7.style.height = calendarDiv7.offsetHeight + 'px' ;
}


/**************************/

function initCalendar8(mkmName,inputField)
{
	if(MSIE){
		iframeObj8 = document.createElement('IFRAME');
		iframeObj8.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj8);  // gfb move this down AFTER the .src is set
	}
	calendarDiv8 = document.createElement('DIV');
	calendarDiv8.id = 'calendarDiv8';
	document.body.appendChild(calendarDiv8);
	writeBottomBar8(mkmName);
	writeTopBar8();
	writeCalendarContent8(inputField,mkmName);
}

function writeTopBar8()
{
	var topBar8 = document.createElement('DIV');
	topBar8.className = 'topBar';
	topBar8.id = 'topBar8';
	calendarDiv8.appendChild(topBar8);

	// Left arrow
	var leftDiv8 = document.createElement('DIV');
	leftDiv8.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv8.appendChild(img);
	topBar8.appendChild(leftDiv8);
	if(Opera)leftDiv8.style.width = '16px';

	// Right arrow
	var rightDiv8 = document.createElement('DIV');
	rightDiv8.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv8.appendChild(img);
	if(Opera)rightDiv8.style.width = '16px';
	topBar8.appendChild(rightDiv8);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect8';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt8';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar8.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt8';
	yearDiv.appendChild(span);
	topBar8.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar8(name)
{
	var d = new Date();
	var bottomBar8 = document.createElement('DIV');
	bottomBar8.id = 'bottomBar8';
	bottomBar8.style.cursor = 'pointer';
	bottomBar8.className = 'todaysDate';

	var subDiv8 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv8.id = 'todaysDateString8';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv8.innerHTML = bottomString ;
	subDiv8.style.left='20px';
	bottomBar8.appendChild(subDiv8);
	calendarDiv8.appendChild(bottomBar8);
}


function writeCalendarContent8(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv8){
		calendarContentDiv8 = document.createElement('DIV');
		calendarDiv8.appendChild(calendarContentDiv8);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv8.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv8.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv8.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		cell.innerHTML = no;
		//cell.onclick = function(){
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv8.offsetHeight)
			document.getElementById('topBar8').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar8').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar8').style.top = '';
			document.getElementById('topBar8').style.bottom = '0px';
		}
	}
	if(iframeObj8){
		if(!calendarContentDivExists)setTimeout('resizeIframe8()',350);else setTimeout('resizeIframe8()',10);
	}
}

function displayCalendar8(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv8){
		initCalendar8(mkmName,inputField);
	}else{
		writeCalendarContent8(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv8.style.visibility = 'visible';
	calendarDiv8.style.display = 'block';
	if(iframeObj8){
		iframeObj8.style.display = '';
		iframeObj8.style.height = '140px';
		iframeObj8.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe8()
{
	iframeObj8.style.width = calendarDiv8.offsetWidth + 'px';
	iframeObj8.style.height = calendarDiv8.offsetHeight + 'px' ;
}

/**************************/
function initCalendar9(mkmName,inputField)
{
	if(MSIE){
		iframeObj9 = document.createElement('IFRAME');
		iframeObj9.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj9);  // gfb move this down AFTER the .src is set
	}
	calendarDiv9 = document.createElement('DIV');
	calendarDiv9.id = 'calendarDiv9';
	document.body.appendChild(calendarDiv9);
	writeBottomBar9(mkmName);
	writeTopBar9();
	writeCalendarContent9(inputField,mkmName);
}

function writeTopBar9(mkmName)
{
	var topBar9 = document.createElement('DIV');
	topBar9.className = 'topBar';
	topBar9.id = 'topBar9';
	calendarDiv9.appendChild(topBar9);

	// Left arrow
	var leftDiv9 = document.createElement('DIV');
	leftDiv9.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv9.appendChild(img);
	topBar9.appendChild(leftDiv9);
	if(Opera)leftDiv9.style.width = '16px';

	// Right arrow
	var rightDiv9 = document.createElement('DIV');
	rightDiv9.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv9.appendChild(img);
	if(Opera)rightDiv9.style.width = '16px';
	topBar9.appendChild(rightDiv9);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect9';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt9';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar9.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt9';
	yearDiv.appendChild(span);
	topBar9.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar9(name)
{
	var d = new Date();
	var bottomBar9 = document.createElement('DIV');
	bottomBar9.id = 'bottomBar9';
	bottomBar9.style.cursor = 'pointer';
	bottomBar9.className = 'todaysDate';

	var subDiv9 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv9.id = 'todaysDateString9';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv9.innerHTML = bottomString ;
	subDiv9.style.left='20px';
	bottomBar9.appendChild(subDiv9);
	calendarDiv9.appendChild(bottomBar9);
}


function writeCalendarContent9(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv9){
		calendarContentDiv9 = document.createElement('DIV');
		calendarDiv9.appendChild(calendarContentDiv9);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv9.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv9.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv9.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		cell.innerHTML = no;
		//cell.onclick = function(){
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv9.offsetHeight)
			document.getElementById('topBar9').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar9').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar9').style.top = '';
			document.getElementById('topBar9').style.bottom = '0px';
		}
	}
	if(iframeObj9){
		if(!calendarContentDivExists)setTimeout('resizeIframe9()',350);else setTimeout('resizeIframe9()',10);
	}
}

function displayCalendar9(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv9){
		initCalendar9(mkmName,inputField);
	}else{
		writeCalendarContent9(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv9.style.visibility = 'visible';
	calendarDiv9.style.display = 'block';
	if(iframeObj9){
		iframeObj9.style.display = '';
		iframeObj9.style.height = '140px';
		iframeObj9.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe9()
{
	iframeObj9.style.width = calendarDiv9.offsetWidth + 'px';
	iframeObj9.style.height = calendarDiv9.offsetHeight + 'px' ;
}


/****************************/
function initCalendar10(mkmName,inputField)
{
	if(MSIE){
		iframeObj10 = document.createElement('IFRAME');
		iframeObj10.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj10);  // gfb move this down AFTER the .src is set
	}
	calendarDiv10 = document.createElement('DIV');
	calendarDiv10.id = 'calendarDiv10';
	document.body.appendChild(calendarDiv10);
	writeBottomBar10(mkmName);
	writeTopBar10();
	writeCalendarContent10(inputField,mkmName);
}

function writeTopBar10()
{
	var topBar10 = document.createElement('DIV');
	topBar10.className = 'topBar';
	topBar10.id = 'topBar10';
	calendarDiv10.appendChild(topBar10);

	// Left arrow
	var leftDiv10 = document.createElement('DIV');
	leftDiv10.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv10.appendChild(img);
	topBar10.appendChild(leftDiv10);
	if(Opera)leftDiv10.style.width = '16px';

	// Right arrow
	var rightDiv10 = document.createElement('DIV');
	rightDiv10.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv10.appendChild(img);
	if(Opera)rightDiv10.style.width = '16px';
	topBar10.appendChild(rightDiv10);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect10';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt9';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar10.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt9';
	yearDiv.appendChild(span);
	topBar10.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar10(name)
{
	var d = new Date();
	var bottomBar10 = document.createElement('DIV');
	bottomBar10.id = 'bottomBar10';
	bottomBar10.style.cursor = 'pointer';
	bottomBar10.className = 'todaysDate';

	var subDiv10 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv10.id = 'todaysDateString10';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv10.innerHTML = bottomString ;
	subDiv10.style.left='20px';
	bottomBar10.appendChild(subDiv10);
	calendarDiv10.appendChild(bottomBar10);
}


function writeCalendarContent10(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv10){
		calendarContentDiv10 = document.createElement('DIV');
		calendarDiv10.appendChild(calendarContentDiv10);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv10.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv10.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv10.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		cell.innerHTML = no;
		//cell.onclick = function(){
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv10.offsetHeight)
			document.getElementById('topBar10').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar10').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar10').style.top = '';
			document.getElementById('topBar10').style.bottom = '0px';
		}
	}
	if(iframeObj10){
		if(!calendarContentDivExists)setTimeout('resizeIframe10()',350);else setTimeout('resizeIframe10()',10);
	}
}

function displayCalendar10(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv10){
		initCalendar10(mkmName,inputField);
	}else{
		writeCalendarContent10(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv10.style.visibility = 'visible';
	calendarDiv10.style.display = 'block';
	if(iframeObj10){
		iframeObj10.style.display = '';
		iframeObj10.style.height = '140px';
		iframeObj10.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe10()
{
	iframeObj10.style.width  = calendarDiv10.offsetWidth + 'px';
	iframeObj10.style.height = calendarDiv10.offsetHeight + 'px' ;
}

/*********************/

function initCalendar11(mkmName,inputField)
{
	if(MSIE){
		iframeObj11 = document.createElement('IFRAME');
		iframeObj11.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj11);  // gfb move this down AFTER the .src is set
	}
	calendarDiv11 = document.createElement('DIV');
	calendarDiv11.id = 'calendarDiv11';
	document.body.appendChild(calendarDiv11);
	writeBottomBar11(mkmName);
	writeTopBar11();
	writeCalendarContent11(inputField,mkmName);
}

function writeTopBar11()
{
	var topBar11 = document.createElement('DIV');
	topBar11.className = 'topBar';
	topBar11.id = 'topBar11';
	calendarDiv11.appendChild(topBar11);

	// Left arrow
	var leftDiv11 = document.createElement('DIV');
	leftDiv11.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv11.appendChild(img);
	topBar11.appendChild(leftDiv11);
	if(Opera)leftDiv11.style.width = '16px';

	// Right arrow
	var rightDiv11 = document.createElement('DIV');
	rightDiv11.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv11.appendChild(img);
	if(Opera)rightDiv11.style.width = '16px';
	topBar11.appendChild(rightDiv11);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect11';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt11';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar11.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt11';
	yearDiv.appendChild(span);
	topBar11.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar11(name)
{
	var d = new Date();
	var bottomBar11 = document.createElement('DIV');
	bottomBar11.id = 'bottomBar11';
	bottomBar11.style.cursor = 'pointer';
	bottomBar11.className = 'todaysDate';

	var subDiv11 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv11.id = 'todaysDateString11';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv11.innerHTML = bottomString ;
	subDiv11.style.left='20px';
	bottomBar11.appendChild(subDiv11);
	calendarDiv11.appendChild(bottomBar11);
}


function writeCalendarContent11(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv11){
		calendarContentDiv11 = document.createElement('DIV');
		calendarDiv11.appendChild(calendarContentDiv11);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv11.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv11.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv11.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		cell.innerHTML = no;
		//cell.onclick = function(){
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv11.offsetHeight)
			document.getElementById('topBar11').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar11').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar11').style.top = '';
			document.getElementById('topBar11').style.bottom = '0px';
		}
	}
	if(iframeObj11){
		if(!calendarContentDivExists)setTimeout('resizeIframe11()',350);else setTimeout('resizeIframe11()',10);
	}
}

function displayCalendar11(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv11){
		initCalendar11(mkmName,inputField);
	}else{
		writeCalendarContent11(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv11.style.visibility = 'visible';
	calendarDiv11.style.display = 'block';
	if(iframeObj11){
		iframeObj11.style.display = '';
		iframeObj11.style.height = '140px';
		iframeObj11.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe11()
{
	iframeObj11.style.width  = calendarDiv11.offsetWidth + 'px';
	iframeObj11.style.height = calendarDiv11.offsetHeight + 'px' ;
}

/*****************************/

function initCalendar12(mkmName,inputField)
{
	if(MSIE){
		iframeObj12 = document.createElement('IFRAME');
		iframeObj12.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj12);  // gfb move this down AFTER the .src is set
	}
	calendarDiv12 = document.createElement('DIV');
	calendarDiv12.id = 'calendarDiv12';
	document.body.appendChild(calendarDiv12);
	writeBottomBar12(mkmName);
	writeTopBar12();
	writeCalendarContent12(inputField,mkmName);
}

function writeTopBar12()
{
	var topBar12 = document.createElement('DIV');
	topBar12.className = 'topBar';
	topBar12.id = 'topBar12';
	calendarDiv12.appendChild(topBar12);

	// Left arrow
	var leftDiv12 = document.createElement('DIV');
	leftDiv12.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv12.appendChild(img);
	topBar12.appendChild(leftDiv12);
	if(Opera)leftDiv12.style.width = '16px';

	// Right arrow
	var rightDiv12 = document.createElement('DIV');
	rightDiv12.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv12.appendChild(img);
	if(Opera)rightDiv12.style.width = '16px';
	topBar12.appendChild(rightDiv12);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect12';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt12';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar12.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt12';
	yearDiv.appendChild(span);
	topBar12.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar12(name)
{
	var d = new Date();
	var bottomBar12 = document.createElement('DIV');
	bottomBar12.id = 'bottomBar12';
	bottomBar12.style.cursor = 'pointer';
	bottomBar12.className = 'todaysDate';

	var subDiv12 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv12.id = 'todaysDateString12';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv12.innerHTML = bottomString ;
	subDiv12.style.left='20px';
	bottomBar12.appendChild(subDiv12);
	calendarDiv12.appendChild(bottomBar12);
}


function writeCalendarContent12(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv12){
		calendarContentDiv12 = document.createElement('DIV');
		calendarDiv12.appendChild(calendarContentDiv12);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv12.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv12.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv12.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		cell.innerHTML = no;
		//cell.onclick = function(){
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv12.offsetHeight)
			document.getElementById('topBar12').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar12').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar12').style.top = '';
			document.getElementById('topBar12').style.bottom = '0px';
		}
	}
	if(iframeObj12){
		if(!calendarContentDivExists)setTimeout('resizeIframe12()',350);else setTimeout('resizeIframe12()',10);
	}
}

function displayCalendar12(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv12){
		initCalendar12(mkmName,inputField);
	}else{
		writeCalendarContent12(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv12.style.visibility = 'visible';
	calendarDiv12.style.display = 'block';
	if(iframeObj12){
		iframeObj12.style.display = '';
		iframeObj12.style.height = '140px';
		iframeObj12.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe12()
{
	iframeObj12.style.width  = calendarDiv12.offsetWidth + 'px';
	iframeObj12.style.height = calendarDiv12.offsetHeight + 'px' ;
}

/***************************/
function initCalendar13(mkmName,inputField)
{
	if(MSIE){
		iframeObj13 = document.createElement('IFRAME');
		iframeObj13.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj13);  // gfb move this down AFTER the .src is set
	}
	calendarDiv13 = document.createElement('DIV');
	calendarDiv13.id = 'calendarDiv13';
	document.body.appendChild(calendarDiv13);
	writeBottomBar13(mkmName);
	writeTopBar13();
	writeCalendarContent13(inputField,mkmName);
}

function writeTopBar13()
{
	var topBar13 = document.createElement('DIV');
	topBar13.className = 'topBar';
	topBar13.id = 'topBar13';
	calendarDiv13.appendChild(topBar13);

	// Left arrow
	var leftDiv13 = document.createElement('DIV');
	leftDiv13.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv13.appendChild(img);
	topBar13.appendChild(leftDiv13);
	if(Opera)leftDiv13.style.width = '16px';

	// Right arrow
	var rightDiv13 = document.createElement('DIV');
	rightDiv13.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv13.appendChild(img);
	if(Opera)rightDiv13.style.width = '16px';
	topBar13.appendChild(rightDiv13);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect13';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt13';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar13.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt12';
	yearDiv.appendChild(span);
	topBar13.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar13(name)
{
	var d = new Date();
	var bottomBar13 = document.createElement('DIV');
	bottomBar13.id = 'bottomBar13';
	bottomBar13.style.cursor = 'pointer';
	bottomBar13.className = 'todaysDate';

	var subDiv13 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv13.id = 'todaysDateString13';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv13.innerHTML = bottomString ;
	subDiv13.style.left='20px';
	bottomBar13.appendChild(subDiv13);
	calendarDiv13.appendChild(bottomBar13);
}


function writeCalendarContent13(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv13){
		calendarContentDiv13 = document.createElement('DIV');
		calendarDiv13.appendChild(calendarContentDiv13);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv13.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv13.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv13.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		cell.innerHTML = no;
		//cell.onclick = function(){
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv13.offsetHeight)
			document.getElementById('topBar13').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar13').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar13').style.top = '';
			document.getElementById('topBar13').style.bottom = '0px';
		}
	}
	if(iframeObj13){
		if(!calendarContentDivExists)setTimeout('resizeIframe13()',350);else setTimeout('resizeIframe13()',10);
	}
}

function displayCalendar13(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv13){
		initCalendar13(mkmName,inputField);
	}else{
		writeCalendarContent13(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv13.style.visibility = 'visible';
	calendarDiv13.style.display = 'block';
	if(iframeObj13){
		iframeObj13.style.display = '';
		iframeObj13.style.height = '140px';
		iframeObj13.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe13()
{
	iframeObj13.style.width  = calendarDiv13.offsetWidth + 'px';
	iframeObj13.style.height = calendarDiv13.offsetHeight + 'px' ;
}

/////////////////////////////////

function initCalendar24(mkmName,inputField)
{
	if(MSIE){
		iframeObj24 = document.createElement('IFRAME');
		iframeObj24.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj24);  // gfb move this down AFTER the .src is set
	}
	calendarDiv24 = document.createElement('DIV');
	calendarDiv24.id = 'calendarDiv24';
	document.body.appendChild(calendarDiv24);
	writeBottomBar24(mkmName);
	writeTopBar24();
	writeCalendarContent24(inputField,mkmName);
}

function writeTopBar24()
{
	var topBar24 = document.createElement('DIV');
	topBar24.className = 'topBar';
	topBar24.id = 'topBar24';
	calendarDiv24.appendChild(topBar24);

	// Left arrow
	var leftDiv24 = document.createElement('DIV');
	leftDiv24.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv24.appendChild(img);
	topBar24.appendChild(leftDiv24);
	if(Opera)leftDiv24.style.width = '16px';

	// Right arrow
	var rightDiv24 = document.createElement('DIV');
	rightDiv24.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv24.appendChild(img);
	if(Opera)rightDiv24.style.width = '16px';
	topBar24.appendChild(rightDiv24);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect24';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt24';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar24.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt24';
	yearDiv.appendChild(span);
	topBar24.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar24(name)
{
	var d = new Date();
	var bottomBar24 = document.createElement('DIV');
	bottomBar24.id = 'bottomBar24';
	bottomBar24.style.cursor = 'pointer';
	bottomBar24.className = 'todaysDate';

	var subDiv24 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv24.id = 'todaysDateString24';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv24.innerHTML = bottomString ;
	subDiv24.style.left='20px';
	bottomBar24.appendChild(subDiv24);
	calendarDiv24.appendChild(bottomBar24);
}


function writeCalendarContent24(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv24){
		calendarContentDiv24 = document.createElement('DIV');
		calendarDiv24.appendChild(calendarContentDiv24);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv24.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv24.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv24.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		cell.innerHTML = no;
		//cell.onclick = function(){
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv24.offsetHeight)
			document.getElementById('topBar24').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar24').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar24').style.top = '';
			document.getElementById('topBar24').style.bottom = '0px';
		}
	}
	if(iframeObj24){
		if(!calendarContentDivExists)setTimeout('resizeIframe24()',350);else setTimeout('resizeIframe24()',10);
	}
}

function displayCalendar24(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv24){
		initCalendar24(mkmName,inputField);
	}else{
		writeCalendarContent24(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv24.style.visibility = 'visible';
	calendarDiv24.style.display = 'block';
	if(iframeObj24){
		iframeObj24.style.display = '';
		iframeObj24.style.height = '140px';
		iframeObj24.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe24()
{
	iframeObj24.style.width  = calendarDiv24.offsetWidth + 'px';
	iframeObj24.style.height = calendarDiv24.offsetHeight + 'px' ;
}


/////////////////////////////////////////////////////



function initCalendar36(mkmName,inputField)
{
	if(MSIE){
		iframeObj36 = document.createElement('IFRAME');
		iframeObj36.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj36);  // gfb move this down AFTER the .src is set
	}
	calendarDiv36 = document.createElement('DIV');
	calendarDiv36.id = 'calendarDiv36';
	document.body.appendChild(calendarDiv36);
	writeBottomBar36(mkmName);
	writeTopBar36();
	writeCalendarContent36(inputField,mkmName);
}

function writeTopBar36()
{
	var topBar36 = document.createElement('DIV');
	topBar36.className = 'topBar';
	topBar36.id = 'topBar36';
	calendarDiv36.appendChild(topBar36);

	// Left arrow
	var leftDiv36 = document.createElement('DIV');
	leftDiv36.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv36.appendChild(img);
	topBar36.appendChild(leftDiv36);
	if(Opera)leftDiv36.style.width = '16px';

	// Right arrow
	var rightDiv36 = document.createElement('DIV');
	rightDiv36.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv36.appendChild(img);
	if(Opera)rightDiv36.style.width = '16px';
	topBar36.appendChild(rightDiv36);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect24';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt36';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar36.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt36';
	yearDiv.appendChild(span);
	topBar36.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar36(name)
{
	var d = new Date();
	var bottomBar36 = document.createElement('DIV');
	bottomBar36.id = 'bottomBar36';
	bottomBar36.style.cursor = 'pointer';
	bottomBar36.className = 'todaysDate';

	var subDiv36 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv36.id = 'todaysDateString36';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv36.innerHTML = bottomString ;
	subDiv36.style.left='20px';
	bottomBar36.appendChild(subDiv36);
	calendarDiv36.appendChild(bottomBar36);
}


function writeCalendarContent36(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv36){
		calendarContentDiv36 = document.createElement('DIV');
		calendarDiv36.appendChild(calendarContentDiv36);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv36.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv36.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv36.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		cell.innerHTML = no;
		//cell.onclick = function(){
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv36.offsetHeight)
			document.getElementById('topBar36').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar36').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar36').style.top = '';
			document.getElementById('topBar36').style.bottom = '0px';
		}
	}
	if(iframeObj36){
		if(!calendarContentDivExists)setTimeout('resizeIframe36()',350);else setTimeout('resizeIframe36()',10);
	}
}

function displayCalendar36(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv36){
		initCalendar36(mkmName,inputField);
	}else{
		writeCalendarContent36(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv36.style.visibility = 'visible';
	calendarDiv36.style.display = 'block';
	if(iframeObj36){
		iframeObj36.style.display = '';
		iframeObj36.style.height = '140px';
		iframeObj36.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe36()
{
	iframeObj36.style.width  = calendarDiv36.offsetWidth + 'px';
	iframeObj36.style.height = calendarDiv36.offsetHeight + 'px' ;
}

//////////////////////////////////////

function initCalendar48(mkmName,inputField)
{
	if(MSIE){
		iframeObj48 = document.createElement('IFRAME');
		iframeObj48.style.filter = 'alpha(opacity=0)';
		document.body.appendChild(iframeObj48);  // gfb move this down AFTER the .src is set
	}
	calendarDiv48 = document.createElement('DIV');
	calendarDiv48.id = 'calendarDiv36';
	document.body.appendChild(calendarDiv48);
	writeBottomBar48(mkmName);
	writeTopBar48();
	writeCalendarContent48(inputField,mkmName);
}

function writeTopBar48()
{
	var topBar48 = document.createElement('DIV');
	topBar48.className = 'topBar';
	topBar48.id = 'topBar48';
	calendarDiv48.appendChild(topBar48);

	// Left arrow
	var leftDiv48 = document.createElement('DIV');
	leftDiv48.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	leftDiv48.appendChild(img);
	topBar48.appendChild(leftDiv48);
	if(Opera)leftDiv48.style.width = '16px';

	// Right arrow
	var rightDiv48 = document.createElement('DIV');
	rightDiv48.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv48.appendChild(img);
	if(Opera)rightDiv48.style.width = '16px';
	topBar48.appendChild(rightDiv48);


	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect24';
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt36';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	if(Opera){
		img.style.cssText = 'float:right;position:relative';
		img.style.position = 'relative';
		img.style.styleFloat = 'right';
	}
	topBar36.appendChild(monthDiv);
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt36';
	yearDiv.appendChild(span);
	topBar36.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox';
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}

function writeBottomBar48(name)
{
	var d = new Date();
	var bottomBar48 = document.createElement('DIV');
	bottomBar48.id = 'bottomBar48';
	bottomBar48.style.cursor = 'pointer';
	bottomBar48.className = 'todaysDate';

	var subDiv48 = document.createElement('DIV');
	//subDiv1.onclick = pickTodaysDate;
	subDiv48.id = 'todaysDateString48';
	//subDiv1.style.width = (calendarDiv1.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
	var bottomString = todayStringFormat;
	bottomString = bottomString.replace('[monthString]',monthArrayShort[d.getMonth()]);
	bottomString = bottomString.replace('[day]',d.getDate());
	bottomString = bottomString.replace('[year]',d.getFullYear());
	bottomString = bottomString.replace('[dayString]',dayArray[day].toLowerCase());
	bottomString = bottomString.replace('[UCFdayString]',dayArray[day]);
	bottomString = bottomString.replace('[todayString]',todayString);
	
	var bottomString = mkmName+name;
	subDiv48.innerHTML = bottomString ;
	subDiv48.style.left='20px';
	bottomBar48.appendChild(subDiv48);
	calendarDiv48.appendChild(bottomBar48);
}


function writeCalendarContent48(inputField,name)
{
	var calendarContentDivExists = true;
	if(!calendarContentDiv48){
		calendarContentDiv48 = document.createElement('DIV');
		calendarDiv48.appendChild(calendarContentDiv48);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
 
	var existingTable = calendarContentDiv48.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		calendarContentDiv48.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	calendarContentDiv48.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		
		var arr = new Array();
		var arrt= new Array();
		arr = inputField.split(",")
		for(var i=0;i<arr.length;i++){
		  arrt= arr[i].split("-");
		  if(currentYear==inputYear && currentMonth == inputMonth && no==arrt[2]){
		        inputDay += arrt[2]+",";
				cell.className='activeDay';
				cell.id = arrt[2];
				cell.value = arrt[2];
				cell.onclick = function(){
				    var month ;
				    if((inputMonth+1)<10){
				       month = "0"+(inputMonth+1);
				    }else{
				       month = inputMonth+1;
				    }
				    var date = inputYear+"-"+month+"-"+this.value;
				    window.showModalDialog("raq.shtml?action=toDetail&VST_DATE="+encodeURI(date)+"&MKM_NAME="+encodeURI(name),window,"dialogwidth=1000px; dialogheight=800px; status=no");
				}
		    }
		}
		/*
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}*/
		cell.innerHTML = no;
		//cell.onclick = function(){
		    //window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		//};
		colCounter++;
	}
	if(!document.all){
		if(calendarContentDiv48.offsetHeight)
			document.getElementById('topBar48').style.top = calendarContentDiv1.offsetHeight +  document.getElementById('topBar48').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar48').style.top = '';
			document.getElementById('topBar48').style.bottom = '0px';
		}
	}
	if(iframeObj48){
		if(!calendarContentDivExists)setTimeout('resizeIframe48()',350);else setTimeout('resizeIframe48()',10);
	}
}

function displayCalendar48(inputField,format,mkmName)
{
	//if(displayTime)calendarDisplayTime=true; else calendarDisplayTime = false;
	 
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;

	if(!calendarDiv48){
		initCalendar48(mkmName,inputField);
	}else{
		writeCalendarContent48(inputField,mkmName);
	}
    
	returnFormat = format;
	returnDateTo = inputField;
	//positionCalendar(buttonObj);
	calendarDiv48.style.visibility = 'visible';
	calendarDiv48.style.display = 'block';
	if(iframeObj48){
		iframeObj48.style.display = '';
		iframeObj48.style.height = '140px';
		iframeObj48.style.width = '195px';
	}
	//setTimeProperties1();
}

function resizeIframe48()
{
	iframeObj48.style.width  = calendarDiv48.offsetWidth + 'px';
	iframeObj48.style.height = calendarDiv48.offsetHeight + 'px' ;
}

//////////////////////////////////////

/*
function displayCalendar()
{     
       alert("12121212");
       if(!inputField.match(/^[0-9]*?$/gi)){
			var items = inputField.split(/[^0-9]/gi);
			var positionArray = new Object();
			positionArray.m = format.indexOf('mm');
			if(positionArray.m==-1)positionArray.m = format.indexOf('m');
			positionArray.d = format.indexOf('dd');
			if(positionArray.d==-1)positionArray.d = format.indexOf('d');
			positionArray.y = format.indexOf('yyyy');
			positionArray.h = format.indexOf('hh');
			positionArray.i = format.indexOf('ii');
			
			this.initialHour = '00';
			this.initialMinute = '00';				
			var elements = ['y','m','d','h','i'];
			var properties = ['currentYear','currentMonth','inputDay','currentHour','currentMinute'];
			var propertyLength = [4,2,2,2,2];
			for(var i=0;i<elements.length;i++) {
				if(positionArray[elements[i]]>=0) {
					window[properties[i]] = inputField.substr(positionArray[elements[i]],propertyLength[i])/1;
				}					
			}			
			currentMonth--;
		}else{
			var monthPos = format.indexOf('mm');
			currentMonth = inputField.substr(monthPos,2)/1 -1;
			var yearPos = format.indexOf('yyyy');
			currentYear = inputField.substr(yearPos,4);
			var dayPos = format.indexOf('dd');
			tmpDay = inputField.substr(dayPos,2);

			var hourPos = format.indexOf('hh');
			if(hourPos>=0){
				tmpHour = inputField.substr(hourPos,2);
				currentHour = tmpHour;
				if(currentHour.length==1) currentHour = '0'
			}else{
				currentHour = '00';
			}
			var minutePos = format.indexOf('ii');
			if(minutePos>=0){
				tmpMinute = inputField.substr(minutePos,2);
				currentMinute = tmpMinute;
			}else{
				currentMinute = '00';
			}
		}

	inputYear = currentYear;
	inputMonth = currentMonth;
    initCalendar(mkmName,mkmId,param);
	returnFormat = format;
	returnDateTo = inputField;
	"calendarDiv"+param.style.visibility = 'visible';
	"calendarDiv"+param.style.display = 'block';
	if("iframeObj"+param){
		"iframeObj"+param.style.display = '';
		"iframeObj"+param.style.height = '140px';
		"iframeObj"+param.style.width = '195px';
	}
}


function initCalendar(mkmName,mkmId,param)
{
	if(MSIE){
		"iframeObj"+param = document.createElement('IFRAME');
		"iframeObj"+param.style.filter = 'alpha(opacity=0)';
		document.body.appendChild("iframeObj"+param);
	}
	"calendarDiv"+param = document.createElement('DIV');
	"calendarDiv"+param.id = 'calendarDiv'+param;
	inputId = mkmId;
	document.body.appendChild("calendarDiv"+param);
	writeBottomBar(mkmName,mkmId,param);
	writeTopBar(param);
	writeCalendarContent(mkmId,param);
}
 
function writeTopBar(param)
{

	var "topBar"+param = document.createElement('DIV');
	"topBar"+param.className = 'topBar';
	"topBar"+param.id = 'topBar'+param;
	"calendarDiv"+param.appendChild("topBar"+param);

	// Left arrow
	var leftDiv = document.createElement('DIV');
	leftDiv.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'left.gif';
	 
	leftDiv.appendChild(img);
	if(Opera)leftDiv.style.width = '16px';

	// Right arrow
	var rightDiv = document.createElement('DIV');
	rightDiv.style.marginRight = '1px';
	var img = document.createElement('IMG');
	img.src = pathToImages + 'right.gif';
	rightDiv.appendChild(img);
	if(Opera)rightDiv.style.width = '16px';
	"topBar"+param.appendChild(rightDiv);

    
	// Month selector
	var monthDiv = document.createElement('DIV');
	monthDiv.id = 'monthSelect'+param;
	var span = document.createElement('SPAN');
	span.innerHTML = monthArray[currentMonth];
	span.id = 'calendar_month_txt';
	monthDiv.appendChild(span);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	img.style.position = 'absolute';
	img.style.right = '0px';
	monthDiv.appendChild(img);
	monthDiv.className = 'selectBox';
	"topBar"+param.appendChild(monthDiv);
	
	// Year selector
	var yearDiv = document.createElement('DIV');
	var span = document.createElement('SPAN');
	span.innerHTML = currentYear;
	span.id = 'calendar_year_txt'+param;
	yearDiv.appendChild(span);
	topBar.appendChild(yearDiv);

	var img = document.createElement('IMG');
	img.src = pathToImages + 'down.gif';
	yearDiv.appendChild(img);
	yearDiv.className = 'selectBox'; 
	if(!document.all){
		img.style.position = 'absolute';
		img.style.right = '2px';
	}
}


function writeBottomBar(name,mkmId,param)
{
	var d = new Date();
	var "bottomBar"+param = document.createElement('DIV');
	"bottomBar"+param.id = 'bottomBar'+param;
	"bottomBar"+param.style.cursor = 'pointer';
	"bottomBar"+param.className = 'todaysDate';
	var subDiv = document.createElement('DIV');
	//subDiv.onclick = pickTodaysDate;
	subDiv.id = 'todaysDateString'+param;
	subDiv.value=mkmId
	//subDiv.style.width = (calendarDiv.offsetWidth - 95) + 'px';
	var day = d.getDay();
	if (! weekStartsOnSunday) {
      if(day==0)day = 7;
      day--;
   }
    var bottomString = mkmName+name
	subDiv.innerHTML = bottomString ;
	subDiv.style.left='20px';
	//subDiv.onclick = alert("1");  //queryById(mkmId);
	"bottomBar"+param.appendChild(subDiv);
	"calendarDiv"+param.appendChild("bottomBar"+param);
	//calendarDiv.onclick =alert("1");
}


function writeCalendarContent(mkmId,param)
{
	var calendarContentDivExists = true;
	if(!"calendarContentDiv"+param){
		"calendarContentDiv"+param = document.createElement('DIV');
		"calendarDiv"+param.appendChild("calendarContentDiv"+param);
		calendarContentDivExists = false;
	}
	currentMonth = currentMonth/1;
	var d = new Date();

	d.setFullYear(currentYear);
	d.setDate(1);
	d.setMonth(currentMonth);

	var dayStartOfMonth = d.getDay();
	if (! weekStartsOnSunday) {
      if(dayStartOfMonth==0)dayStartOfMonth=7;
      dayStartOfMonth--;
   }
	var existingTable = calendarContentDiv.getElementsByTagName('TABLE');
	if(existingTable.length>0){
		"calendarContentDiv"+param.removeChild(existingTable[0]);
	}

	var calTable = document.createElement('TABLE');
	calTable.width = '100%';
	calTable.cellSpacing = '0';
	"calendarContentDiv"+param.appendChild(calTable);

	var calTBody = document.createElement('TBODY');
	calTable.appendChild(calTBody);
	var row = calTBody.insertRow(-1);
	row.className = 'calendar_week_row';
   if (showWeekNumber) {
      var cell = row.insertCell(-1);
	   cell.innerHTML = weekString;
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	}

	for(var no=0;no<dayArray.length;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = dayArray[no];
	}

	var row = calTBody.insertRow(-1);

   if (showWeekNumber) {
	   var cell = row.insertCell(-1);
	   cell.className = 'calendar_week_column';
	   cell.style.backgroundColor = selectBoxRolloverBgColor;
	   var week = getWeek(currentYear,currentMonth,1);
	   cell.innerHTML = week;		// Week
	}
	for(var no=0;no<dayStartOfMonth;no++){
		var cell = row.insertCell(-1);
		cell.innerHTML = '&nbsp;';
	}

	var colCounter = dayStartOfMonth;
	var daysInMonth = daysInMonthArray[currentMonth];
	if(daysInMonth==28){
		if(isLeapYear(currentYear))daysInMonth=29;
	}

	for(var no=1;no<=daysInMonth;no++){
		d.setDate(no-1);
		if(colCounter>0 && colCounter%7==0){
			var row = calTBody.insertRow(-1);
         if (showWeekNumber) {
            var cell = row.insertCell(-1);
            cell.className = 'calendar_week_column';
            var week = getWeek(currentYear,currentMonth,no);
            cell.innerHTML = week;		// Week
            cell.style.backgroundColor = selectBoxRolloverBgColor;
         }
		}
		var cell = row.insertCell(-1);
		if(currentYear==inputYear && currentMonth == inputMonth && no==inputDay){
			cell.className='activeDay';
		}
		cell.innerHTML = no;
		inputId = "";
		inputId = mkmId;
		//cell.id = mkmId
		cell.onclick = function(){
		    window.showModalDialog("raq.shtml?action=toDetail&MKM_DAY_RPT_ID="+mkmId,window,"dialogwidth=1000px; dialogheight=800px; status=no");
		};//pickDate;
		colCounter++;
	}
	if(!document.all){
		if("calendarContentDiv"+param.offsetHeight)
			document.getElementById('topBar'+param).style.top =  document.getElementById('topBar').offsetHeight -1 + 'px';
		else{
			document.getElementById('topBar'+param).style.top = '';
			document.getElementById('topBar'+param).style.bottom = '0px';
		}
	}
	if("iframeObj"+param){
		if(!calendarContentDivExists)setTimeout('resizeIframe("'+param+'")',350);else setTimeout('resizeIframe("'+param+'")',10);
	}
}

function resizeIframe(param)
{
	"iframeObj"+param.style.width  = "calendarDiv"+param.offsetWidth + 'px';
	"iframeObj"+param.style.height = "calendarDiv"+param.offsetHeight + 'px' ;
}
*/
