/**
 *@module 系统模块   
 *@func   调度控制器  
 *@version 1.1
 *@author 朱晓文  
 */
package com.hoperun.commons.job.service.impl;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.job.model.ClientJobModel;
import com.hoperun.commons.job.model.JobModel;
import com.hoperun.commons.job.service.JobService;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.util.TimeComm;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class JobServiceImpl.
 */
public class JobServiceImpl extends BaseService implements JobService {

	/* (non-Javadoc)
	 * @see com.hoperun.commons.job.service.JobService#txSaveJob(java.lang.String, com.hoperun.sys.model.UserBean)
	 */
	public boolean txSaveJob(String jsonStr, UserBean aUserBean) {
		List<ClientJobModel> jobArray = new ArrayList<ClientJobModel>();
		if (StringUtils.isNotEmpty(jsonStr)) {
			jobArray = new Gson().fromJson(jsonStr,
					new TypeToken<ArrayList<ClientJobModel>>() {
					}.getType());
		}
		for(ClientJobModel obj:jobArray)
		{
			JobModel aJobModel = new JobModel();
			aJobModel.setJOB_ID(StringUtil.uuid32len());
			aJobModel.setBILL_TYPE(obj.getBILL_TYPE());
			aJobModel.setCALL_BACK(obj.getCALL_BACK());
			aJobModel.setFUN_PARAM(new Gson().toJson(obj.getPARAMS()));
			aJobModel.setCRENAME(aUserBean.getXM());
			aJobModel.setSTATE("待处理");
			//这边写死自己执行自己的Job,将来系统上线要删除
			//begin
			aJobModel.setSTATE("处理中");
			aJobModel.setAPP_ID(checkIp());
			//end 
			
			insert("Sample.insertJob", aJobModel);
		}
		return true;
	}
	
	//获得任务Num
	/* (non-Javadoc)
	 * @see com.hoperun.commons.job.service.JobService#txGetJobList(java.lang.String)
	 */
	public List<JobModel> txGetJobList(String thisIp){
	 Map<String,String> params=new HashMap<String,String>();
	 params.put("STATE", "处理中");
	 params.put("APP_ID", thisIp);
	 params.put("JOBS", Consts.PRE_SEVJOBS);
	 List<JobModel> jobList=this.findList("Sample.qryAppJobs", params);
	 int jobLen=jobList.size();
	 if(jobLen==0)
	  {
		this.update("Sample.lockJobsByIp", params);
		jobList=this.findList("Sample.qryAppJobs",params);
	  }
	 return jobList;
	}
	//调用java方法
	/* (non-Javadoc)
	 * @see com.hoperun.commons.job.service.JobService#callJavaFun(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void  callJavaFun (String JOB_ID,String fun_Name,String fun_Params,String call_Back,String is_RetrunVal)throws Exception {
		String className=fun_Name.substring(0,fun_Name.lastIndexOf("."));
		String methodName=fun_Name.substring(fun_Name.lastIndexOf(".")+1,fun_Name.length());
		Class<?>jobClass=null;
		Object reVal=null;
		Map<String,String> params=new HashMap<String,String>();
		try {
			System.err.println("start do job fun :"+TimeComm.getPreTimeStamp(""));
			if (StringUtils.isNotEmpty(fun_Params)) {
					params = new Gson().fromJson(fun_Params,
							new TypeToken<HashMap<String,String>>() {
							}.getType());
			}
			
			System.err.println("需要加载的className为"+className);
			System.err.println("需要加载的methodName为"+methodName);
			jobClass=Class.forName(className);
			Method javaFun=jobClass.getMethod(methodName, HashMap.class);
			reVal=javaFun.invoke(jobClass.newInstance(),params);
			System.err.println("end  do job fun :"+TimeComm.getPreTimeStamp(""));
			txFinishJob ("success!","完成",JOB_ID);
		} catch (Exception e) {
			reVal="false";
			Throwable exStr=e.getCause();
		    txFinishJob ("false:"+e.getMessage()+exStr.getMessage(),"失败",JOB_ID);
		    e.printStackTrace();
		}finally {
			//回调客户端方法
			if(call_Back!=null&&!call_Back.equals(""))
			{   
				params.put("RETURN_VAL", StringUtil.nullToSring(reVal));
				String backClassName=call_Back.substring(0,call_Back.lastIndexOf("."));
				String backMethodName=call_Back.substring(call_Back.lastIndexOf(".")+1,call_Back.length());
				Class<?>backClass=null;
				backClass=Class.forName(backClassName);
				Method callBackFun=backClass.getMethod(backMethodName, HashMap.class);
				callBackFun.invoke(backClass.newInstance(),params);
			}
		}
	}
	
	/**
	 * this is javaAutoDoc.
	 * 
	 * @param params the params
	 * 
	 * @author administrator
	 */
	public void  callBackTest(HashMap<String,String> params)
	{  
		System.err.println("Call Back OK !  params=="+params);
	}
	
	
	//完成或者失败，更新任务标识
	/* (non-Javadoc)
	 * @see com.hoperun.commons.job.service.JobService#txFinishJob(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean  txFinishJob (String reMark,String state,String job_Id) {
		Map<String,String> params=new HashMap<String,String>();
		params.put("STATE", state);
		params.put("REMARK", reMark);
		params.put("JOB_ID", job_Id);
		return  this.update("JOB.finishJobById", params);
	}
	
	
	//这边写死自己执行自己的Job,将来系统上线要删除
	//begin
	// add by zhuxw
	/**
	 * this is javaAutoDoc.
	 * 
	 * @return return value
	 * 
	 * @author administrator
	 */
	private String checkIp() {
		if (StringUtil.isEmpty(Consts.JOB_SERVER_IP)) {
			return "";
		}
		String[] Ips = StringUtil.toArr(Consts.JOB_SERVER_IP);
		String addr = getIp();
		for (String tempIp : Ips) {
			if (addr.indexOf(tempIp) > 0) {
				return tempIp;
			}
		}
		return "";
	}

	// add by zhuxw
	/**
	 * this is javaAutoDoc.
	 * 
	 * @return return value
	 * 
	 * @author administrator
	 */
	public String getIp() {
		Enumeration<NetworkInterface> netInterfaces = null;
		StringBuffer IPS = new StringBuffer();
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					IPS.append(ips.nextElement().getHostAddress());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return IPS.append("##").toString();
	}
	//end

	/* (non-Javadoc)
	 * @see com.hoperun.commons.job.service.JobService#doCleanProduce(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public Object doCleanProduce(List<Object> params, List<String> outParams,
			List<Integer> outParamType) {
		return this.callProcedure("{call P_CLEAN_JOB(?,?,?,?)}", params,null,null);
	}
}
