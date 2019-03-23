package com.hoperun.report.savereptshareview.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.report.savereptshareview.model.SaveReptShareViewModel;
import com.hoperun.report.savereptshareview.service.SaveReptShareViewService;
import com.hoperun.sys.model.UserBean;

public class SaveReptShareViewAction extends BaseAction {
	
	private SaveReptShareViewService saveReptShareViewService;
	
	
	/**
	 * @return the saveReptShareViewService
	 */
	public SaveReptShareViewService getSaveReptShareViewService() {
		return saveReptShareViewService;
	}


	/**
	 * @param saveReptShareViewService the saveReptShareViewService to set
	 */
	public void setSaveReptShareViewService(
			SaveReptShareViewService saveReptShareViewService) {
		this.saveReptShareViewService = saveReptShareViewService;
	}


	public void saveShare(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("SHAR_NAME", request.getParameter("SHAR_NAME"));
			map.put("rptModel", request.getParameter("rptModel"));
			map.put("params", request.getParameter("params"));
			map.put("RPT_NAME", request.getParameter("RPT_NAME"));
			map.put("url", request.getParameter("url"));
			//项目名称
			map.put("conName", request.getContextPath());
			String jsonDate = request.getParameter("childJsonData");
			if (!StringUtil.isEmpty(jsonDate)) {
				List<SaveReptShareViewModel> modelList = new Gson().fromJson(
						jsonDate, new TypeToken<List<SaveReptShareViewModel>>() {
						}.getType());
				saveReptShareViewService.txEdit(map, modelList, userBean);
				jsonResult = jsonResult(true, "");
			}
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
			s.printStackTrace();
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	public ActionForward toRept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession(true);
//		String USERID = request.getParameter("S_USER_ID");
//		String ZTXXID = request.getParameter("S_ZTXXID");
//		String jumpflag = request.getParameter("S_GOTO_FLG");
//		if("true".equals(jumpflag)){
//			UserBean aUserBean = new UserBean();
//			aUserBean.setXTYHID(USERID);
//			aUserBean.setLoginZTXXID(ZTXXID);
//			session.setAttribute("UserBean", aUserBean);	
//		}
		String  RPT_SCHE_SHAR_ID=request.getParameter("RPT_SCHE_SHAR_ID");
		Map<String,String> map=saveReptShareViewService.getRptInfo(RPT_SCHE_SHAR_ID);
		String SCHE_PARAMS=map.get("SCHE_PARAMS");
		String[] SCHE_URL=map.get("SCHE_URL").split(":");
		request.setAttribute("conDition", SCHE_PARAMS);
		request.setAttribute("rptModel", SCHE_URL[1]);
		request.setAttribute("printModel", SCHE_URL[1]);
		return mapping.findForward("pageResult");
	}
}
