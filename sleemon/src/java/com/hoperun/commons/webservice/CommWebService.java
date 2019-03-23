/**@describe 共通WebService接口
 * @author zhuxw
 * @date  2012-7-2
 */
package com.hoperun.commons.webservice;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.log4j.Logger;

import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.inter.AdvcOrderForWeChatModel;
import com.hoperun.commons.model.inter.InterBranchModel;
import com.hoperun.commons.model.inter.InterBrandModel;
import com.hoperun.commons.model.inter.InterChannModel;
import com.hoperun.commons.model.inter.InterEmployeeModel;
import com.hoperun.commons.model.inter.InterProductModel;
import com.hoperun.commons.model.inter.InterUserModel;
import com.hoperun.commons.model.inter.ReturnInterInfo;
import com.hoperun.commons.util.InterfaceInvokeUtil;
import com.hoperun.commons.util.LogicUtil;

/**
 * The Class CommWebService.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CommWebService {
	private Logger logger = Logger.getLogger(CommWebService.class);
	/**
	 * Instantiates a new comm web service.
	 */
	public CommWebService()
	{
	   System.out.println("WebServiceStart*******!");
	}
	
	@WebMethod(action="createLedger",operationName="createLedger",exclude=false)
	public String createLedger(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String passWord,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createLedger(userName,passWord,messageDate);
	}

	@WebMethod(action="updateLedger",operationName="updateLedger",exclude=false)
	public String updateLedger(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String passWord,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.updateLedger(userName,passWord,messageDate);
	}
	
	@WebMethod(action="deleteLedger",operationName="deleteLedger",exclude=false)
	public String deleteLedger(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String passWord,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.deleteLedger(userName,passWord,messageDate);
	}
	
	@WebMethod(action="createOrganization",operationName="createOrganization",exclude=false)
	public String createOrganization(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String passWord,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createOrganization(userName,passWord,messageDate);
	}
	
	@WebMethod(action="updateOrganization",operationName="updateOrganization",exclude=false)
	public String updateOrganization(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String passWord,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.updateOrganization(userName,passWord,messageDate);
	}
	
	@WebMethod(action="deleteOrganization",operationName="deleteOrganization",exclude=false)
	public String deleteOrganization(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String passWord,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.deleteOrganization(userName,passWord,messageDate);
	}
	
	
	@WebMethod(action="createDepartMent",operationName="createDepartMent",exclude=false)
	public String createDepartMent(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String passWord,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createDepartMent(userName,passWord,messageDate);
	}
	
	@WebMethod(action="updateDepartMent",operationName="updateDepartMent",exclude=false)
	public String updateDepartMent(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String passWord,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.updateDepartMent(userName,passWord,messageDate);
	}
	
	@WebMethod(action="deleteDepartMent",operationName="deleteDepartMent",exclude=false)
	public String deleteDepartMent(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String passWord,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.deleteDepartMent(userName,passWord,messageDate);
	}
	
	@WebMethod(action="createEmployee",operationName="createEmployee",exclude=false)
	public String createEmployee(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createEmployee(userName,password,messageDate);
	}
	
	@WebMethod(action="updateEmployee",operationName="updateEmployee",exclude=false)
	public String updateEmployee(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.updateEmployee(userName,password, messageDate);
	}
   
	@WebMethod(action="deleteEmployee",operationName="deleteEmployee",exclude=false)
	public String deleteEmployee(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.deleteEmployee(userName,password,messageDate);
	}
	
	@WebMethod(action="createUserInfo",operationName="createUserInfo",exclude=false)
	public String createUserInfo(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createUserInfo(userName,password,messageDate);
	}
	
	@WebMethod(action="updateUserInfo",operationName="updateUserInfo",exclude=false)
	public String updateUserInfo(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.updateUserInfo(userName,password,messageDate);
	}
	
	@WebMethod(action="deleteUserInfo",operationName="deleteUserInfo",exclude=false)
	public String deleteUserInfo(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.deleteUserInfo(userName,password,messageDate);
	}
	
	@WebMethod(action="createBrand",operationName="createBrand",exclude=false)
	public String createBrand(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createBrand(userName,password,messageDate);
	}
	
	@WebMethod(action="updateBrand",operationName="updateBrand",exclude=false)
	public String updateBrand(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.updateBrand(userName,password,messageDate);
	}
	
	@WebMethod(action="deleteBrand",operationName="deleteBrand",exclude=false)
	public String deleteBrand(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.deleteBrand(userName,password,messageDate);
	}
	
	@WebMethod(action="createPdtMenu",operationName="createPdtMenu",exclude=false)
	public String createPdtMenu(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createPdtMenu(userName,password,messageDate);
	}
	
	@WebMethod(action="updatePdtMenu",operationName="updatePdtMenu",exclude=false)
	public String updatePdtMenu(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.updatePdtMenu(userName,password,messageDate);
	}
	
	@WebMethod(action="deletePdtMenu",operationName="deletePdtMenu",exclude=false)
	public String deletePdtMenu(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.deletePdtMenu(userName,password,messageDate);
	}
	
	@WebMethod(action="createProduct",operationName="createProduct",exclude=false)
	public String createProduct(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createProduct(userName,password,messageDate);
	}
	
	@WebMethod(action="updateProduct",operationName="updateProduct",exclude=false)
	public String updateProduct(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.updateProduct(userName,password,messageDate);
	}
	
	@WebMethod(action="deleteProduct",operationName="deleteProduct",exclude=false)
	public String deleteProduct(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.deleteProduct(userName,password,messageDate);
	}
	
	@WebMethod(action="createUnitMenu",operationName="createUnitMenu",exclude=false)
	public String createUnitMenu(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createUnitMenu(userName,password,messageDate);
	}
	
	@WebMethod(action="updateUnitMenu",operationName="updateUnitMenu",exclude=false)
	public String updateUnitMenu(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.updateUnitMenu(userName,password,messageDate);
	}
	
	@WebMethod(action="deleteUnitMenu",operationName="deleteUnitMenu",exclude=false)
	public String deleteUnitMenu(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.deleteUnitMenu(userName,password,messageDate);
	}
	
	
	@WebMethod(action="createSaleArea",operationName="createSaleArea",exclude=false)
	public String createSaleArea(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createSaleArea(userName,password,messageDate);
	}
	
	@WebMethod(action="updateSaleArea",operationName="updateSaleArea",exclude=false)
	public String updateSaleArea(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.updateSaleArea(userName,password,messageDate);
	}
	
	@WebMethod(action="deleteSaleArea",operationName="deleteSaleArea",exclude=false)
	public String deleteSaleArea(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.deleteSaleArea(userName,password,messageDate);
	}
	
	@WebMethod(action="createBaseChann",operationName="createBaseChann",exclude=false)
	public String createBaseChann(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createBaseChann(userName,password,messageDate);
	}
	
	@WebMethod(action="updateBaseChann",operationName="updateBaseChann",exclude=false)
	public String updateBaseChann(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.updateBaseChann(userName,password,messageDate);
	}
	
	@WebMethod(action="deleteBaseChann",operationName="deleteBaseChann",exclude=false)
	public String deleteBaseChann(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.deleteBaseChann(userName,password,messageDate);
	}
	
	@WebMethod(action="createDeliverAddr",operationName="createDeliverAddr",exclude=false)
	public String createDeliverAddr(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createDeliverAddr(userName,password,messageDate);
	}
	
	@WebMethod(action="updateDeliverAddr",operationName="updateDeliverAddr",exclude=false)
	public String updateDeliverAddr(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.updateDeliverAddr(userName,password,messageDate);
	}
	
	@WebMethod(action="deleteDeliverAddr",operationName="deleteDeliverAddr",exclude=false)
	public String deleteDeliverAddr(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.deleteDeliverAddr(userName,password,messageDate);
	}
	
	@WebMethod(action="unAuditPayMent",operationName="unAuditPayMent",exclude=false)
	public String unAuditPayMent(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.unAuditPayMent(userName,password,messageDate);
	}
	
	@WebMethod(action="createPayMent",operationName="createPayMent",exclude=false)
	public String createPayMent(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		return LogicUtil.createPayMent(userName,password,messageDate);
	}
	                   

	
	@WebMethod(action="updateDeliverOrder",operationName="updateDeliverOrder",exclude=false)
	public String updateDeliverOrder(@WebParam(name = "userName")String userName,@WebParam(name = "passWord")String password,@WebParam(name = "messageData")String messageDate){
		if("1".equals(Consts.IS_NO_DELIVER_PLAN_FLAG)){
			return InterfaceInvokeUtil.updateDeliverOrder(userName,password,messageDate);
		}else{
			return LogicUtil.updateDeliverOrder(userName,password,messageDate);
		}
	}
	
	/** 
	 * @LA接口
	 * 查询导购员
	 * @param userName
	 * @param password
	 * @param messageDate
	 * @return
	 */
	@WebMethod(action="qrySales",operationName="qrySales",exclude=false)
	public String qrySales(@WebParam(name = "messageData")String messageDate){
		return LogicUtil.queryTermSales(messageDate);
	}
	
	/**
	 * @LA接口
	 * 订单接入
	 * @param messageDate
	 * @return
	 */
	@WebMethod(action="insAdvcOrder",operationName="insAdvcOrder",exclude=false)
	public String insAdvcOrder(@WebParam(name = "messageData")String messageDate){
		return LogicUtil.txInsAdvcOrder(messageDate);
	}
	
	
	/**  
	 * 
	 * 	MDM接口  
	 * 
	 */
	
	
	/**
	 * 新增品牌
	 * @param BRAND_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="insNewBrand",operationName="insNewBrand",exclude=false)
	public ReturnInterInfo insNewBrand(@WebParam(name = "BRAND_LIST")InterBrandModel[] BRAND_LIST){
		return LogicUtil.insNewBrand(BRAND_LIST);
	}
	
	/**
	 * 修改品牌
	 * @param BRAND_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="upNewBrand",operationName="upNewBrand",exclude=false)
	public ReturnInterInfo upNewBrand(@WebParam(name = "BRAND_LIST")InterBrandModel[] BRAND_LIST){
		return LogicUtil.upNewBrand(BRAND_LIST);
	}
	
	/**
	 * 新增客户
	 * @param CHANN_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="insNewChann",operationName="insNewChann",exclude=false)
	public ReturnInterInfo insNewChann(@WebParam(name = "CUST_LIST")InterChannModel[] CHANN_LIST){
		return LogicUtil.insNewChann(CHANN_LIST);
	}
	
	/**
	 * 修改客户
	 * @param CHANN_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="upNewChann",operationName="upNewChann",exclude=false)
	public ReturnInterInfo upNewChann(@WebParam(name = "CUST_LIST")InterChannModel[] CHANN_LIST){
		return LogicUtil.upNewChann(CHANN_LIST);
	}
	
	
	/**
	 * 新增用户
	 * @param USER_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="insNewUserInfo",operationName="insNewUserInfo",exclude=false)
	public ReturnInterInfo insNewUserInfo(@WebParam(name = "USER_LIST")InterUserModel[] USER_LIST){
		return LogicUtil.insNewUserInfo(USER_LIST);
	}
	
	
	/**
	 * 修改用户
	 * @param USER_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="upNewUserInfo",operationName="upNewUserInfo",exclude=false)
	public ReturnInterInfo upNewUserInfo(@WebParam(name = "USER_LIST")InterUserModel[] USER_LIST){
		return LogicUtil.upNewUserInfo(USER_LIST);
	}
	
	
	
	/**
	 * 新增物料
	 * @param PRODUCT_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="insNewProduct",operationName="insNewProduct",exclude=false)
	public ReturnInterInfo insNewProduct(@WebParam(name = "MATERIAL_LIST")InterProductModel[] PRODUCT_LIST){
		return LogicUtil.insNewProduct(PRODUCT_LIST);
	}
	
	
	/**
	 * 修改物料
	 * @param PRODUCT_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="upNewProduct",operationName="upNewProduct",exclude=false)
	public ReturnInterInfo upNewProduct(@WebParam(name = "MATERIAL_LIST")InterProductModel[] PRODUCT_LIST){
		return LogicUtil.upNewProduct(PRODUCT_LIST);
	}
	
	
	/**
	 * 新增组织
	 * @param BRANCH_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="insNewBranch",operationName="insNewBranch",exclude=false)
	public ReturnInterInfo insNewBranch(@WebParam(name = "ORG_LIST")InterBranchModel[] BRANCH_LIST){
		return LogicUtil.insNewBranch(BRANCH_LIST);
	}
	
	
	/**
	 * 修改组织
	 * @param BRANCH_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="upNewBranch",operationName="upNewBranch",exclude=false)
	public ReturnInterInfo upNewBranch(@WebParam(name = "ORG_LIST")InterBranchModel[] BRANCH_LIST){
		return LogicUtil.upNewBranch(BRANCH_LIST);
	}
	
	
	/**
	 * 新增员工
	 * @param EMPLOYEE_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="insNewEmployee",operationName="insNewEmployee",exclude=false)
	public ReturnInterInfo insNewEmployee(@WebParam(name = "EMPLOYEE_LIST")InterEmployeeModel[] EMPLOYEE_LIST){
		return LogicUtil.insNewEmployee(EMPLOYEE_LIST);
	}
	
	/**
	 * 修改员工
	 * @param EMPLOYEE_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="upNewEmployee",operationName="upNewEmployee",exclude=false)
	public ReturnInterInfo upNewEmployee(@WebParam(name = "EMPLOYEE_LIST")InterEmployeeModel[] EMPLOYEE_LIST){
		return LogicUtil.upNewEmployee(EMPLOYEE_LIST);
	}
	/**
	 * 微信预订单接入
	 * @param EMPLOYEE_LIST
	 * @return ReturnInterInfo
	 */
	@WebMethod(action="addAdvcOrderForWeChat",operationName="addAdvcOrderForWeChat",exclude=false)
	public ReturnInterInfo addAdvcOrderForWeChat(@WebParam(name = "AdvcOrderModel")AdvcOrderForWeChatModel AdvcOrderModel){
		return LogicUtil.addAdvcOrderForWeChat(AdvcOrderModel);
	}
 }
