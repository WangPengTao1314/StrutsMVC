/**
  *@module 系统模块   
  *@func 系统Job   
  *@version 1.1
  *@author 朱晓文       
*/
package com.hoperun.commons.job.service;

import java.util.List;

import com.hoperun.commons.job.model.JobModel;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface JobService.
 */

public interface JobService extends IBaseService {
	
	
	//add by zhuxw
	/**
	 * this is javaAutoDoc.
	 * 
	 * @param jsonStr the json str
	 * @param aUserBean the a user bean
	 * 
	 * @return return value
	 * 
	 * @author administrator
	 */
	public boolean txSaveJob(String jsonStr,UserBean aUserBean);
	
	/**
	 * this is javaAutoDoc.
	 * 
	 * @param thisIp the this ip
	 * 
	 * @return return value
	 * 
	 * @author administrator
	 */
	public List<JobModel> txGetJobList(String thisIp);
	
	/**
	 * this is javaAutoDoc.
	 * 
	 * @param reMark the re mark
	 * @param state the state
	 * @param job_Id the job_ id
	 * 
	 * @return return value
	 * 
	 * @author administrator
	 */
	public boolean  txFinishJob (String reMark,String state,String job_Id);
	
	/**
	 * this is javaAutoDoc.
	 * 
	 * @param JOB_ID the jO b_ id
	 * @param fun_Name the fun_ name
	 * @param fun_Params the fun_ params
	 * @param call_Back the call_ back
	 * @param is_RetrunVal the is_ retrun val
	 * 
	 * @throws Exception the exception
	 * 
	 * @author administrator
	 */
	public void  callJavaFun (String JOB_ID,String fun_Name,String fun_Params,String call_Back,String is_RetrunVal)throws Exception;

	/**
	 * this is javaAutoDoc.
	 * 
	 * @param paramList the param list
	 * @param outParams the out params
	 * @param outParamType the out param type
	 * 
	 * @return return value
	 * 
	 * @author administrator
	 */
	public Object doCleanProduce(List<Object> paramList,
			List<String> outParams, List<Integer> outParamType);
}
