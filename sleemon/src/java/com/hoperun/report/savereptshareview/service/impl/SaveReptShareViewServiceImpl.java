package com.hoperun.report.savereptshareview.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.report.BaseRptService;
import com.hoperun.report.savereptshareview.model.SaveReptShareViewModel;
import com.hoperun.report.savereptshareview.service.SaveReptShareViewService;
import com.hoperun.sys.model.UserBean;

public class SaveReptShareViewServiceImpl extends BaseRptService implements SaveReptShareViewService {

	@Override
	public void txEdit(Map<String, String> map,
			List<SaveReptShareViewModel> modelList, UserBean userBean) {
		List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
		//获取url
		String url=map.get("url");
		//项目名称
		String conName=map.get("conName");
		//按项目名截取action地址
		url=url.substring(url.indexOf(conName)+conName.length(), url.length()-1);
		//拼接req文件
		String SCHE_URL=url+":"+map.get("rptModel");
		String RPT_SCHE_SHAR_NO=LogicUtil.getBmgz("BASE_RPT_SCHE_SHAR_NO");
		Map<String,String> userMap=(Map<String, String>) this.load("SaveReptShareView.getUserInfo", userBean.getXTYHID());
		for (SaveReptShareViewModel model : modelList) {
			Map<String,String> params=new HashMap<String, String>();
			params.put("RPT_SCHE_SHAR_ID", StringUtil.uuid32len());
			params.put("RPT_SCHE_SHAR_NO", RPT_SCHE_SHAR_NO);
			params.put("SHAR_NAME", map.get("SHAR_NAME"));
			params.put("RPT_NAME", map.get("RPT_NAME"));
			params.put("SCHE_PARAMS", map.get("params"));
			params.put("SCHE_URL", SCHE_URL);
			params.put("SHAR_PSON_ID", userMap.get("RYXXID"));
			params.put("SHAR_PSON_NAME", userMap.get("XM"));
			params.put("SHAR_TIME", "sysdate");
			params.put("SHARED_PSON_ID", model.getSHARED_PSON_ID());
			params.put("SHARED_PSON_NAME", model.getSHARED_PSON_NAME());
			params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			addList.add(params);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("SaveReptShareView.insert", addList);
		}
	}
	public Map<String,String> getRptInfo(String RPT_SCHE_SHAR_ID){
		return (Map<String, String>) this.load("SaveReptShareView.getRptInfoById", RPT_SCHE_SHAR_ID);
	}
}
