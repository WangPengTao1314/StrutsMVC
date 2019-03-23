<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/commons/jslibs.jsp"%>
<%@ include file="/commons/qxcommon.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<title>查看审批流程</title>
	</head>
	<body >

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1">
						
						<tr>
							<td width="100%" align="center">
								<table width="100%" border="0" cellpadding="4" cellspacing="4"
									class="detail">
									<tr>
										<td class="detail2">
											<table width="100%" border="0" cellpadding="1"
												cellspacing="1" class="detail3">
												<tr>
													<td width="15%" height="20" align="right"
														class="detail_label" nowrap="nowrap">
														模板编号：
													</td>
													<td width="35%" height="20" align="left"
														class="detail_content">
														${rst.MODELNUMBER}
													</td>
													<td width="15%" height="20" align="right"
														class="detail_label" nowrap="nowrap">
														模板名称：
													</td>
													<td width="35%" height="20" align="left"
														class="detail_content">
														${rst.MODELNAME}
													</td>
												</tr>

												<tr>
													<td height="20" width="15%" align="right"
														class="detail_label">
														状态：
													</td>
													<td height="20" width="35%" align="left"
														class="detail_content">
														${rst.MODELSTATE}
													</td>
													<td height="20" width="15%" align="right"
														class="detail_label">
														机构：
													</td>
													<td height="20" width="35%" align="left"
														class="detail_content">
														${rst.JGMC }
													</td>
												</tr>

												<tr>
													<td height="20" align="right" width="15%" nowrap="nowrap" class="detail_label">
														业务类型：
													</td>
													<td height="20" align="left" width="35%"  class="detail_content">
														${rst.BUSINESSTYPE}
													</td>
													<td height="20" align="right" width="15%" nowrap="nowrap" class="detail_label">
														使用条件：
													</td>
													<td height="20" align="left" width="35%"  class="detail_content">
														${rst.MODELTYPE}
													</td>
												</tr>

												<tr>
													<td height="20" align="right" colspan='1'
														class="detail_label" nowrap="nowrap">
														提交跳签：
													</td>
													<td height="20" align="left" colspan='3'
														class="detail_content">
														${affirmJump }
													</td>
												</tr>

												<tr>
													<td height="20" align="right" colspan='1'
														class="detail_label">
														备注：
													</td>
													<td height="20" align="left" colspan='3'
														class="detail_content">
														${rst.REMARK }
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</td>
			</tr>
		</table>

		<form name="detailform" method="post" action="">
			<input name="flowModelId" type="hidden" value="${rst.FLOWMODELID}">
			<input name="modelNumber" type="hidden" value="${rst.MODELNUMBER}">
			<input name="modelName" type="hidden" value="${rst.MODELNAME}">
			<input name="modelType" type="hidden" value="${rst.MODELTYPE}">
			<input name="businessType" type="hidden" value="${rst.BUSINESSTYPE}">
			<input name="modelState" type="hidden" value="${rst.MODELSTATE}">
			<input name="remark" type="hidden" value="${rst.REMARK}">
			<input name="jgxxid" type="hidden" value="${rst.JGXXID}">
			<input name="event" type="hidden" value="newModel">
			<input name="affirmJump" type="hidden" value="0">
		</form>

	</body>
	<script language="javascript">
    function eventHandle(i)
    {
        var myObject = "";
        if (i == 1)
        {
           window.location="${ctx}/system/flow.shtml?action=toAdd";
        }
        else if (i == 2)
        { 
            var flowModelId = document.detailform.flowModelId.value;
            window.location="${ctx}/system/flow.shtml?action=toUpdate&flowModelId=" + flowModelId;
        }
        else if (i == 3)
        {
            if (confirm('确定要删除本条记录？'))
            {
                document.detailform.action = "${ctx}/system/flow.shtml?action=delFlow";
                document.detailform.submit();
                
            }
            else
            {
                return false;
            }
        }
        else if (i == 4)
        {
            document.detailform.action = "${ctx}/system/flow.shtml?action=validation";
            document.detailform.submit();
            return true;
        }
        else if (i == 5)
        {
            document.detailform.action = "${ctx}/system/flow.shtml?action=invalidation";
            document.detailform.submit();
            var flow
            //parent.refreshSelf();
            return true;
        }
        else if (i == 6)
        {
            //定制使用条件
            myObject = document.detailform.businessType.value;
            var resultArray = window.showModalDialog("condition.jsp?businessType=" + myObject, myObject, "dialogHeight:420px;dialogWidth:600px; dialogLeft:138px; dialogTop:72px; status=no; resizable=yes");
            if (resultArray == null) return false;
            document.detailform.modelType.value = resultArray[0];
            document.detailform.event.value = "updFlow";
            document.detailform.submit();
            return true;
        }

    }
    
  function checkOne()
     {
        <c:if test="${refreshFlag eq 'T'}">
           parent.document.main_cs.flowModelId.value='${flowModelId}';
           parent.topcontent.listForm.submit();   
        </c:if>
     }
     
  $(function()
    {  
       if('${msg}'!=null&&'${msg}'!='')
       {
          parent.showMsgPanel('${msg}');
       }
       if('${refreshFlag}'=='T')
       {
              parent.document.main_cs.flowModelId.value='${flowModelId}';
              parent.topcontent.listForm.submit();  
       }
    });

</script>
</html>
