package com.hoperun.commons.job.service.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

import com.hoperun.commons.job.model.JobModel;
import com.hoperun.commons.job.service.JobService;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.util.TimeComm;
// TODO: Auto-generated Javadoc
//add by zhuxw
//job控制器
/**
 * The Class JobTimer.
 */
public class JobTimer extends BaseService {
	
	/** The job service. */
	private JobService jobService;
    
    /**
     * this is javaAutoDoc.
     * 
     * @throws Exception the exception
     * 
     * @author administrator
     */
    public void doTask() throws Exception {
		if (jobService == null) {
			System.err.println("jobService is null!");
			return;
		}
		//查看当前服务器是否具有执行job权限
		String thisIp = checkIp();
		if (thisIp.equals("")) {
			return;
		}
		System.err.println("begin do sys job ,time is :"+ TimeComm.getPreTimeStamp(""));
		List<JobModel> jobList = jobService.txGetJobList(thisIp);
		System.err.println("jobList===" + jobList);
		for (JobModel aJobModel : jobList) {
			// do each job
			if (StringUtil.isEmpty(aJobModel.getBILL_TYPE())
					|| StringUtil.isEmpty(aJobModel.getFUN_TYPE())
					|| StringUtil.isEmpty(aJobModel.getFUN_NAME())) {
				// 参数不完整，JOB执行失败，需要更新到JOB表
				jobService
						.txFinishJob(
								"false:没有设置该任务的处理方法!请检查BILL_TYPE、FUN_TYPE、FUN_NAME 参数！ ",
								"失败", aJobModel.getJOB_ID());
				continue;
			}
			if (aJobModel.getFUN_TYPE().equals("java")) {
				jobService.callJavaFun(aJobModel.getJOB_ID(), aJobModel.getFUN_NAME(), aJobModel.getFUN_PARAM(),aJobModel.getCALL_BACK(),aJobModel.getIS_RETRUNVAL());
			}
			//if (aJobModel.getFUN_TYPE().equals("procedure")) {
            //}
		}

	}

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

	/**
	 * this is javaAutoDoc.
	 * 
	 * @return return value
	 * 
	 * @author administrator
	 */
	public JobService getJobService() {
		return jobService;
	}

	/**
	 * this is javaAutoDoc.
	 * 
	 * @param jobService the job service
	 * 
	 * @author administrator
	 */
	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}
}
