package com.hoperun.report.savereptshareview.service;

import java.util.List;
import java.util.Map;

import com.hoperun.report.savereptshareview.model.SaveReptShareViewModel;
import com.hoperun.sys.model.UserBean;

public interface SaveReptShareViewService {
	public void txEdit(Map<String,String> map,List<SaveReptShareViewModel> modelList,UserBean userBean);
	public Map<String,String> getRptInfo(String RPT_SCHE_SHAR_ID);
}
