/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：BrandAction.java
 */
package com.hoperun.base.brand.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.base.brand.model.BrandModel;
import com.hoperun.base.brand.service.BrandService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandAction.
 * 
 * @module 系统管理
 * @func 品牌信息
 * @version 1.1
 * @author 郭利伟
 */
public class BrandAction extends BaseAction {

	/** 权限对象维护**/
    //增删改查
    private static String PVG_BWS="XT0012201";
    private static String PVG_EDIT="XT0012202";
    private static String PVG_DELETE="XT0012203";
    //启用,停用
    private static String PVG_START_STOP="XT0012204";
    //确认，取消
    private static String PVG_FINISH_CANCLE="";
    private static String AUD_BUSS_STATE="STATE";
    /**权限 end*/
    
    /**审批维护必须维护字段**/
    //提交撤销
    private static String PVG_COMMIT_CANCLE="";
    private static String PVG_TRACE="";
    //审核模块
    private static String PVG_BWS_AUDIT="";
    private static String PVG_AUDIT_AUDIT="";
    private static String PVG_TRACE_AUDIT="";
    //审批流参数
    private static String AUD_TAB_NAME="";
    private static String AUD_TAB_KEY="";
    private static String AUD_BUSS_TYPE="";
    private static String AUD_FLOW_INS="";
    /**审批 end**/
   
    /** The brand service. */
    private BrandService brandService;
    
    /**
     * 品牌信息框架页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toFrames(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	//设置跳转时需要的一些公用属性
    	ParamUtil.setCommAttributeEx(request);
    	request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
        return mapping.findForward("frames");
    }


    /**
     * 品牌信息列表.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * s
     * @return the action forward
     */
    @SuppressWarnings("unchecked")
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map params = new HashMap();
    	//品牌ID
        params.put("BRAND_ID", ParamUtil.utf8Decoder(request, "BRAND_ID"));	
        //品牌
        params.put("BRAND", ParamUtil.utf8Decoder(request, "BRAND"));
        //英文名称
        params.put("BRAND_EN", ParamUtil.utf8Decoder(request, "BRAND_EN"));	
        //制单时间从
        params.put("CRE_TIME_FROM", ParamUtil.utf8Decoder(request, "CRE_TIME_FROM"));
        //制单时间到
        params.put("CRE_TIME_TO", ParamUtil.utf8Decoder(request, "CRE_TIME_TO"));
    	//状态
        params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));				
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		//权限级别和审批流的封装以及状态的封装 
		//增加权限则不能查询U9导入数据，待定
		//params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = brandService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
        return mapping.findForward("list");
    }


    /**
     * 查看人员详细信息.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    @SuppressWarnings("unchecked")
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG &&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String BRAND_ID = ParamUtil.get(request, "BRAND_ID");
        Map entry = brandService.load(BRAND_ID);
        request.setAttribute("rst", entry);

        return mapping.findForward("view");
    }


    /**
     * 点击新增或修改按钮跳转到编辑页面.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    @SuppressWarnings("unchecked")
    public ActionForward toEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        String BRAND_ID = ParamUtil.get(request, "BRAND_ID");
        if (StringUtils.isNotEmpty(BRAND_ID)) {
            Map entry = brandService.load(BRAND_ID);
            request.setAttribute("rst", entry);
        }
        return mapping.findForward("toedit");
    }


    /**
     * 编辑.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_EDIT))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
    	try {
    		String BRAND_ID = ParamUtil.get(request, "BRAND_ID");
            String jsonData = ParamUtil.get(request, "jsonData");
            BrandModel model = null;
            if (StringUtils.isNotEmpty(jsonData)) {
                model = new Gson().fromJson(jsonData, new TypeToken <BrandModel>() {
                }.getType());
                
                //新增的时候
                if(StringUtils.isEmpty(BRAND_ID)){
                	Map<String,Object> Map = brandService.load(model.getBRAND_ID().trim());
                    if(null != Map && !Map.isEmpty()){
                    	throw new ServiceException("品牌ID重复，请重新输入");
                    }
                }
                
    			brandService.txEdit(BRAND_ID, model, userBean); 
            }
    		
			
        }catch(ServiceException e){
        	jsonResult = jsonResult(false, e.getMessage());
        }catch (RuntimeException e) {
            jsonResult = jsonResult(false, "保存失败");
        }
                  
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }


    /**
     * 删除.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_DELETE))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String BRAND_ID = ParamUtil.get(request, "BRAND_ID");

        if (StringUtils.isNotEmpty(BRAND_ID)) {
            try {
                brandService.txDelete(BRAND_ID, userBean);
                brandService.clearCaches(BRAND_ID);
            } catch (RuntimeException e) {
                jsonResult = jsonResult(false, "删除失败");
            }
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }

    /**
     * 启用.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     */
    public void changeStateStart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean,PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
         	String BRAND_ID = request.getParameter("BRAND_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("BRAND_ID", BRAND_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
            brandService.txStopById(params);
            jsonResult = jsonResult(true, "状态修改成功!");
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败!");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    
    /**
	 * 停用
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 */
    public void changeStateStop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean = ParamUtil.getUserBean(request);
    	if(!QXUtil.checkMKQX(userBean, PVG_START_STOP))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        try {
        	String BRAND_ID = request.getParameter("BRAND_ID");
            Map <String, String> params = new HashMap <String, String>();
            params.put("BRAND_ID", BRAND_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
            brandService.txStopById(params);
            jsonResult = jsonResult(true, "状态修改成功!");
        } catch (Exception e) {
            jsonResult = jsonResult(false, "状态修改失败!");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    /**
     * Sets the brand service.
     * 
     * @param brandService the new brand service
     */
    public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
    }
    
    
    /**
	 * * 设置权限Map
	 * @param UserBean the userBean
	 * @return Map<String,String>
	 */
	 private Map<String,String> setPvg(UserBean userBean) {
    	Map<String,String>pvgMap=new HashMap<String,String>();
    	pvgMap.put("PVG_BWS",QXUtil.checkPvg(userBean, PVG_BWS));
    	pvgMap.put("PVG_EDIT",QXUtil.checkPvg(userBean, PVG_EDIT));
    	pvgMap.put("PVG_DELETE",QXUtil.checkPvg(userBean, PVG_DELETE)  );
    	pvgMap.put("PVG_START_STOP",QXUtil.checkPvg(userBean, PVG_START_STOP) );
    	pvgMap.put("PVG_COMMIT_CANCLE",QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE) );
    	pvgMap.put("PVG_TRACE",QXUtil.checkPvg(userBean, PVG_TRACE)  );
    	pvgMap.put("PVG_BWS_AUDIT",QXUtil.checkPvg(userBean, PVG_BWS_AUDIT)  );
    	pvgMap.put("PVG_AUDIT_AUDIT",QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT) );
    	pvgMap.put("PVG_FINISH_CANCLE",QXUtil.checkPvg(userBean, PVG_FINISH_CANCLE) );
    	pvgMap.put("PVG_TRACE_AUDIT",QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT) );
    	pvgMap.put("AUD_TAB_NAME",AUD_TAB_NAME);
    	pvgMap.put("AUD_TAB_KEY",AUD_TAB_KEY);
    	pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
    	pvgMap.put("AUD_BUSS_TYPE",AUD_BUSS_TYPE);
    	pvgMap.put("AUD_FLOW_INS",AUD_FLOW_INS);
    	return  pvgMap;
   }

}
