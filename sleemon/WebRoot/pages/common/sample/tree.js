var zTree;
var setting = {
	asyncUrl : "sample.shtml?action=showTree",
	expandSpeed: "",
	callback : {
      click: zTreeOnClick
    }
};

function zTreeOnClick(event, treeId, treeNode) {
	//parent.document.getElementById("topcontent").src="jgxx.shtml?action=toList&JGXXID="+treeNode.id;
}

function reloadTree() {
	if (curLi) curLi.removeClass("focus");
	setting.showLine = false;
	var curLi = $("#noLineStyle");
	curLi.addClass("focus");
	zTree = $("#treeDemo").zTree(setting, zNodes);
}

$(document).ready(function(){
	reloadTree();
	$("#refresh").click(function(){
		reloadTree();
	});
	$("#open").click(function(){
		zTree.expandAll(true);
	});
	$("#close").click(function(){
		zTree.expandAll(false);	
	});
	$("#addChildNode").click(function(){
		alert("1234577555");
		var newNodes = [{id:"aa001", name:"ceshi2"}];
		var node = zTree.getNodeByTId("aa003");
     	zTree.addNodes(node, newNodes);
//		zTreeObj.reAsyncChildNodes(treeNode1, "refresh");
//		
//		var node = zTree.getNodeByTId("1");
//		zTree.reAsyncChildNodes(node, "refresh");
	});
	$("#addParentNode").click(function(){
		var newNodes = [{id:"aa003", name:"ceshi3",isParent : true}];
		zTree.addNodes(null, newNodes);
	});
	$("#removeChildNode").click(function(){
		zTree.expandAll(false);	
	});
	$("#removeParentNode").click(function(){
		zTree.expandAll(false);	
	});
});