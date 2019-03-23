var trimRe = /^\s+|\s+$/g;
var decimalRe = /,/g;
var fastSpeed = 30;
var speed = 100;
String.prototype.trim = function() {
	return function() {
		return this.replace(trimRe, "");
	};
}(); 
String.prototype.decimal = function() {
	return function() {
		return this.replace(decimalRe, "");
	};
}();
function g(o) {
	return document.getElementById(o);
}

function gotoPage(form, pageNo) {
	form.pageNo.value = pageNo;
	showWaitPanel('',{fast:true});
	setTimeout(form.name + ".submit()", 20);
}

function loadPage(divId, url) {
	showWaitPanel('');
	_loadPage(divId, url, "closeWindow()");
}

function _loadPage(divId, url, func, errMsg) {
	var request = YAHOO.util.Connect.asyncRequest("POST", url, {
		success : function(o) {
			var divId = o.argument.divId;
			var pageDiv = g(divId);
			pageDiv.innerHTML = o.responseText;
			setTimeout(o.argument.func, 100);
		},
		failure : function(o) {
			var msg = o.argument.errMsg;
			msg = msg ? msg : "页面加载失败！";
			showMsgPanel(msg);
		},
		argument : {
			"divId" : divId,
			"errMsg" : errMsg,
			"func" : func
		}
	});
}

function getAbsXY(el) {
	var p = YAHOO.util.Dom.getXY(el);
	return {
		"x" : p[0],
		"y" : p[1]
	};
}

function showHiddenObject(objId, showStat) {
	var obj = g(objId);
	if (obj)
		obj.style.display = showStat ? "" : "none";
}

function checkAllStates(name, states) {
	var chks = document.getElementsByName(name);
	for (var i = 0, j = chks.length; i < j; i++) {
		if (chks[i].disabled == true)
			continue;
		chks[i].checked = states;
	}
}

function checkStates(name) {
	var chks = document.getElementsByName(name);
	var cnt = -1;
	if (chks && chks.length > 0) {
		cnt = 0;
		for (var i = 0, j = chks.length; i < j; i++) {
			if (chks[i].disabled == true)
				continue;
			if (chks[i].checked == true)
				cnt++;
		}
	}
	return cnt;
}

function getCheckElementsValue(name) {
	var cnt = [];
	var chks = document.getElementsByName(name);
	if (chks) {
		for (var i = 0, j = chks.length; i < j; i++) {
			if (chks[i].disabled == true)
				continue;
			if (chks[i].checked == true)
				cnt[cnt.length] = chks[i].value;
		}
	}
	return cnt.join() == "" ? null : cnt.join();
}

var isIe = (document.all) ? true : false;
function setSelectState(state) {
	var objs = document.getElementsByTagName('select');
	for (var i = 0, j = objs.length; i < j; i++) {
		objs[i].style.visibility = state;
	}
}

// 显示对话框
function showDialog(url, w, h, cfg) {
	w = w ? w : "580";
	h = h ? h : "530";
	cfg = cfg ? cfg : "help:no;scroll:no; status:no; center:yes";
	window.showModalDialog(url, window, "dialogWidth:" + w + "px;dialogHeight:"
			+ h + "px;" + cfg);
}

// 等待信息提示
function showWaitPanel(s, cfg) {
	cfg = cfg ? cfg : {fast:true};	
	var msg = s == null || s.trim() == '' ? '' : s + "，";
	var B = "<div id='YT_MSG'><div id='YT_LOAD_MSG' class='loading'>"
			+ msg + "正在处理……</div></div>";
	showMessageBox(B, 200, 300, cfg.fast);
}
// 更新系統等待信息
function updateWaitPanel(s) {
	var msgDiv = g("YT_LOAD_MSG");
	if (msgDiv) {
		msgDiv.innerHTML = s;
	} else {
		showWaitPanel(s);
	}
}
// 提示信息
function showMsgPanel(msg, title, cfg,isWait) {
	_showMsgPanel(msg, title, "closeWindow();", cfg,isWait);
}
// 提示错误信息
function showErrorMsg(msg, title, cfg,isWait) {
	msg = "<span class='FontRed'>" + msg + "</span>";
	_showMsgPanel(msg, title, "closeWindow();", cfg,isWait);
}
// 提示确定窗口
function showConfirm(msg, funcOk,isWait) { 
	_showMsgPanel(msg, null, funcOk, {"funcCancle" : "closeWindow();"},isWait);
}

// 提示是否窗口
function showYONConfirm(msg, funcOk,isWait) { 
	_showMsgPanel(msg, null, funcOk, {"funcCancle" : "closeWindow();"},isWait,"是","否");
}

// 保存确定窗口
function saveConfirm(msg, funcOk,cancel,isWait) {	 
	_showMsgPanel(msg, null, funcOk, {"funcCancle" : cancel},isWait);
}
//isWait == 'N' 表示 不需要等待窗口
function _showMsgPanel(msg, title, func, cfg,isWait,okTitle,cancleTitle) {
	cfg = cfg ? cfg : {};
	var w = cfg.width ? cfg.width : 230;
	var h = cfg.height ? cfg.height : 130;
	w = w < 230 ? 230 : w;
	h = h < 130 ? 130 : h;
	okTitle = okTitle?okTitle:"确 定";
	cancleTitle = cancleTitle?cancleTitle:"取 消";
	var sb = [];
	sb[sb.length] = '<table width="' + w + '" height="' + h + '" border="0" cellpadding="0" cellspacing="0" background="/"+ctxPath+"/styles/"+theme+"/images/index/jsbg.png">';
	sb[sb.length] = '<tr><td height="32"><div align="left" class="mesWindowTop">';
	sb[sb.length] = title ? title : '系统提示';
	sb[sb.length] = '</div></td></tr>';
	sb[sb.length] = '<tr><td valign="middle" align="center"><b>' + msg + '</b></td></tr><tr><td height="15"></td></tr>';
	sb[sb.length] = '<tr><td height="30" valign="middle" align="center" >';
	sb[sb.length] = '<input id="YT_MSG_BTN_OK" type="button" class="btn1" value="'+okTitle+'">';
//	sb[sb.length] = '<input id="YT_MSG_BTN_OK" type="button" class="btn1" value="确 定">';
	if(cfg.funcCancle){		
		sb[sb.length] = '<input id="YT_MSG_BTN_CANCLE" type="button" class="btn1" value="'+cancleTitle+'">';
//		sb[sb.length] = '<input id="YT_MSG_BTN_CANCLE" type="button" class="btn1" value="取 消">';
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
		btnOk.onkeydown = function() {
			var event = window.event;
			if (event.keyCode == 9) {
				event.returnValue = false;
				event.keyCode = 0;
			}
		};
		btnOk.onclick = function() {
			showWaitPanel();
			if(isWait!=null && isWait!='undefined' && isWait!='Y'){
				closeWindow();
			}
			eval(func);
			var btnCancle = g("YT_MSG_BTN_CANCLE");
			if (btnCancle) {
				btnCancle.disabled=true;
			}
		}
	}
	var btnCancle = g("YT_MSG_BTN_CANCLE");
	if (btnCancle) {
		btnCancle.onclick = function() {
			closeWindow();
			eval(cfg.funcCancle);
		}
	}
}

// 弹出消息框
function showMessageBox(content, wWidth, wHeight, fast) {
	closeWindow();

	var pos = createPosite();
	if (isIe) {
		setSelectState('hidden');
	}
	var bodyLeft = pos.bSLeft;
	var bodyTop = pos.bSTop;
	var bodyWidth = pos.bWidth;
	var bodyHeight = pos.bHeight; 
	// 生成操作屏蔽层
	createForbidLayer("backDivX", bodyWidth, bodyHeight, fast);
	// 定位
	var left = (bodyLeft + (bodyWidth - wWidth) / 2);
	var top = (bodyTop + (bodyHeight - wHeight) / 2);
	top = top < 30 ? 30 : top;
	// 生成消息框
	createMessageWindow(content, left, top, wWidth);
}
function getDocBody() {
	return isIe ? document.body : document.documentElement;
}
function createPosite() {
	var body = getDocBody();
	var bWidth = parseInt(body.scrollWidth);
	var bHeight = parseInt(body.scrollHeight);
	var cHeight = body.clientHeight;
	bHeight = bHeight < cHeight ? cHeight : bHeight;

	var bSWidth = body.scrollWidth;
	var bSHeight = body.scrollHeight;

	var bSTop = body.scrollTop;
	var bSLeft = body.scrollLeft;
	return {
		bWidth : bWidth,
		bHeight : bHeight,
		bSWidth : bSWidth,
		bSHeight : bSHeight,
		bSTop : bSTop,
		bSLeft : bSLeft
	};
}

function createForbidLayer(divId, bWidth, bHeight, fast) {
	var panel = document.createElement("div");
	panel.id = divId;

	var sb = [];
	sb[sb.length] = 'top:0px;left:0px;position:absolute;overflow:visible;background:#cbe9fd;';
	sb[sb.length] = 'width:' + bWidth + 'px;';
	sb[sb.length] = 'height:' + bHeight + 'px;';
	sb[sb.length] = (isIe) ? 'filter:alpha(opacity=15);' : 'opacity:15;';

	panel.style.cssText = sb.join("");
	document.body.appendChild(panel);
	if(!fast) showBackground(panel, 40);
	return panel;
}

function createMessageWindow(content, left, top, wWidth, divId) {
	divId = divId ? divId : "mesWindow";
	var mesW = document.createElement("div");
	mesW.id = divId;
	mesW.className = divId;

	var sb = [];
	sb[sb.length] = '<div id="mesWindowContent">';
	sb[sb.length] = content;
	sb[sb.length] = '</div>';
	mesW.innerHTML = sb.join("");

	sb = [];
	sb[sb.length] = 'left:' + left + 'px;';
	sb[sb.length] = 'top:' + top + 'px;';
	sb[sb.length] = 'position:absolute;width:' + wWidth + 'px;';
	mesW.style.cssText = sb.join("");
	document.body.appendChild(mesW);
}

// 让背景渐渐变暗
function showBackground(obj, endInt) {
	if (isIe) {
		obj.filters.alpha.opacity += 5;
		if (obj.filters.alpha.opacity < endInt) {
			setTimeout(function() {
				showBackground(obj, endInt)
			}, 5);
		}
	} else {
		var al = parseFloat(obj.style.opacity);
		al += 0.10;
		obj.style.opacity = al;
		if (al < (endInt / 100)) {
			setTimeout(function() {
				showBackground(obj, endInt)
			}, 5);
		}
	}
}
// 关闭窗口
function closeWindow(containId, panelId) {
	containId = containId ? containId : "backDivX";
	var backDivX = g(containId);
	if (backDivX != null) {
		backDivX.parentNode.removeChild(backDivX);
	}
	panelId = panelId ? panelId : "mesWindow";
	var mesWindow = g(panelId);
	if (mesWindow != null) {
		mesWindow.parentNode.removeChild(mesWindow);
	}
	if (isIe) {
		setSelectState('');
	}
}
function closeWindowPanel() {
	closeWindow("backDivX2", "msgWindowX2");
}

//add by zhuxw
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {   
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {   
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);   
    } else {   
        return this.replace(reallyDo, replaceWith);   
    }   
}  	

//add by zzb 2014-12-03
function showMessageBox_Self(content, wWidth, wHeight, divId) {
	closeWindow();

	var pos = createPosite();
	if (isIe) {
		setSelectState('hidden');
	}
	var bodyLeft = pos.bSLeft;
	var bodyTop = pos.bSTop;
	var bodyWidth = pos.bWidth;
	var bodyHeight = pos.bHeight; 
	// 生成操作屏蔽层
	createForbidLayer("backDivX", bodyWidth, bodyHeight, true);
	// 定位
	var left = (bodyLeft + (bodyWidth - wWidth) / 2);
	var top = (bodyTop + (bodyHeight - wHeight) / 2);
	top = top < 30 ? 30 : top;
	// 生成消息框
	createMessageWindow(content, left, top, wWidth,divId);
}