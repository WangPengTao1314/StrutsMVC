
package com.hoperun.drp.base.drpsaleprice.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.base.drpsaleprice.service.DrpsalepriceService;
import com.hoperun.drp.base.drpsaleprice.model.DrpsalepriceModel;
import com.hoperun.sys.model.UserBean;

public class DrpsalepriceAction extends BaseAction {
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0010901";
	private static String PVG_EDIT = "";
	private static String PVG_DELETE = "";
	private static String PVG_IMPORT="FX0010902";
	// 启用,停用
	private static String PVG_START_STOP = "";
	private static String AUD_BUSS_STATE="";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "";
	private static String PVG_TRACE = "";
	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_RETURN="";//退回
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "";// 表名
	private static String AUD_TAB_KEY = "";// 主键
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
	private DrpsalepriceService drpsalepriceService;
	


	public DrpsalepriceService getDrpsalepriceService() {
		return drpsalepriceService;
	}


	public void setDrpsalepriceService(DrpsalepriceService drpsalepriceService) {
		this.drpsalepriceService = drpsalepriceService;
	}


	/**
	 * 框架
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		UserBean userBean =  ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("frames");
		
	}
	
	
	/**
     * 查询结果列表.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
    	Map<String, String> params = new HashMap<String, String>();
    	ParamUtil.putStr2Map(request, "PRD_NO", params);
    	String PRD_NO=ParamUtil.utf8Decoder(request, "PRD_NO");
    	params.put("PRD_NOQuery", StringUtil.creCon("p.PRD_NO", PRD_NO, ","));
    	ParamUtil.putStr2Map(request, "PRD_NAME", params);
    	String PRD_NAME=ParamUtil.utf8Decoder(request, "PRD_NAME");
    	params.put("PRD_NAMEQuery", StringUtil.creCon("p.PRD_NAME", PRD_NAME, ","));
    	ParamUtil.putStr2Map(request, "PRD_SPEC", params);
    	String PRD_SPEC=ParamUtil.utf8Decoder(request, "PRD_SPEC");
    	params.put("PRD_SPECQuery", StringUtil.creCon("p.PRD_SPEC", PRD_SPEC, ","));
    	ParamUtil.putStr2Map(request, "BRAND", params);
    	String BRAND=ParamUtil.utf8Decoder(request, "BRAND");
    	params.put("BRANDQuery", StringUtil.creCon("p.BRAND", BRAND, ","));
    	ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "PAR_PRD_NAME", params);
    	String PAR_PRD_NAME=ParamUtil.utf8Decoder(request, "PAR_PRD_NAME");
    	params.put("PAR_PRD_NAMEQuery", StringUtil.creCon("p.PAR_PRD_NAME",PAR_PRD_NAME, ","));
    	int pageSize=ParamUtil.getInt(request, "pageSize",0);
    	if(0==pageSize){
    		pageSize=100;
    	}
    	params.put("pageSize", String.valueOf(pageSize));
    	int pageNo = ParamUtil.getInt(request, "pageNo", 1);
    	//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		// 字段排序
		ParamUtil.setOrderField(request, params, "p.PRD_NO", "ASC");
    	
    	ParamUtil.putStr2Map(request, "pageSize", params);
    	IListPage page = drpsalepriceService.pageQuery(params, pageNo);
    	request.setAttribute("params", params);
    	request.setAttribute("pvg",setPvg(userBean));
        request.setAttribute("page", page);
        return mapping.findForward("list");
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
    	UserBean userBean =  ParamUtil.getUserBean(request);
    	if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean,PVG_BWS))
    	{
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        PrintWriter writer = getWriter(response);
        String jsonResult = jsonResult();
        String jsonData = ParamUtil.get(request, "jsonData");
        List<DrpsalepriceModel> model = new ArrayList<DrpsalepriceModel>();
        if (StringUtils.isNotEmpty(jsonData)) {
            model = new Gson().fromJson(jsonData, new TypeToken <List<DrpsalepriceModel>>() {
            }.getType());
        }
    	try {
    		drpsalepriceService.txEdit(model, userBean);
        }catch(ServiceException x){
        	jsonResult = jsonResult(false, x.getMessage());
        } catch (RuntimeException e) {
        	e.printStackTrace();
            jsonResult = jsonResult(false, "保存失败");
        }
        if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
    }
    /**
	  * 导入
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  */
	 public void toImportData(ActionMapping mapping, ActionForm form, 
			 HttpServletRequest request, HttpServletResponse response) {
		 UserBean userBean =  ParamUtil.getUserBean(request);
		 if (Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_IMPORT)) {
				throw new ServiceException("对不起，您没有权限 !");
	    }
		 PrintWriter writer = getWriter(response);
	        String jsonResult = jsonResult();
	        try {
	        	String serverDir = Properties.getString("UPLOADFILE_DIR");
	        	String fileName = "sample"+ParamUtil.utf8Decoder(request, "fileName");
	        	String secPath = Properties.getString("SAMPLE_DIR");
	        	String path = serverDir+ File.separatorChar + secPath+ File.separatorChar + fileName;
	        	List<Map<String,String>> alist = new ArrayList<Map<String,String>>();
	        	Map<String,String> map = new HashMap<String,String>();
	        	map.put("PRD_NO", "0");
	        	map.put("PRD_NAME", "1");
	        	map.put("PRD_SPEC", "2");
	        	map.put("DECT_PRICE", "4");
	        	String[] a = new String[]{"0"};
	        	alist.add(map);
	            List list = ExcelUtil.readExcelbyModel(fileName, path, 1, alist, a);
	            drpsalepriceService.txImportExcel(list, userBean);
	            jsonResult = jsonResult(true, "上传成功");//
	        } catch (ServiceException e) {
	            jsonResult = jsonResult(false, e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	            jsonResult = jsonResult(false, "Execl解析失败." + e.getMessage());
	        }

	        if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	        }
	 }
	 
	 public void toExpData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		 UserBean userBean = ParamUtil.getUserBean(request);
		 if (Consts.FUN_CHEK_PVG&& !QXUtil.checkMKQX(userBean, PVG_IMPORT)) {
				throw new ServiceException("对不起，您没有权限 !");
	    }
		 Map<String, String> params = new HashMap<String, String>();
    	ParamUtil.putStr2Map(request, "PRD_NO", params);
    	String PRD_NO=ParamUtil.utf8Decoder(request, "PRD_NO");
    	params.put("PRD_NOQuery", StringUtil.creCon("p.PRD_NO", PRD_NO, ","));
    	ParamUtil.putStr2Map(request, "PRD_NAME", params);
    	String PRD_NAME=ParamUtil.utf8Decoder(request, "PRD_NAME");
    	params.put("PRD_NAMEQuery", StringUtil.creCon("p.PRD_NAME", PRD_NAME, ","));
    	ParamUtil.putStr2Map(request, "PRD_SPEC", params);
    	String PRD_SPEC=ParamUtil.utf8Decoder(request, "PRD_SPEC");
    	params.put("PRD_SPECQuery", StringUtil.creCon("p.PRD_SPEC", PRD_SPEC, ","));
    	ParamUtil.putStr2Map(request, "BRAND", params);
    	String BRAND=ParamUtil.utf8Decoder(request, "BRAND");
    	params.put("BRANDQuery", StringUtil.creCon("p.BRAND", BRAND, ","));
    	ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
    	ParamUtil.putStr2Map(request, "PAR_PRD_NAME", params);
    	String PAR_PRD_NAME=ParamUtil.utf8Decoder(request, "PAR_PRD_NAME");
    	params.put("PAR_PRD_NAMEQuery", StringUtil.creCon("p.PAR_PRD_NAME",PAR_PRD_NAME, ","));
		//只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		//只查询终结点标记为1的
		params.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		//字段排序
		ParamUtil.setOrderField(request, params, "CRE_TIME", "DESC");
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		List list = drpsalepriceService.queryPriceExp(params);
		 //excel数据上列显示的列明
		String tmpContentCn="货品编号(必填),货品名称,规格型号,出厂价,折扣价(必填),供货价";
		String tmpContent="PRD_NO,PRD_NAME,PRD_SPEC,FACT_PRICE,DECT_PRICE,PRVD_PRICE";
        try {
        	doExport(response, list, "渠道销售价格", tmpContent, tmpContentCn,"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 /**
	  * 导出模版
	  * @param mapping
	  * @param form
	  * @param request
	  * @param response
	  */
	 public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        	String tmpContentCn;
        	String tmpContent;
    		 //excel数据上列显示的列明
        	 tmpContentCn="货品编号(必填),货品名称,规格型号,出厂价,折扣价(必填),供货价";
    		 tmpContent="PRD_NO,PRD_NAME,PRD_SPEC,FACT_PRICE,DECT_PRICE,PRVD_PRICE";
        	try {
				doExport(response, new ArrayList(), "渠道销售价格模版", tmpContent, tmpContentCn,"");
			} catch (Exception e) {
				e.printStackTrace();
			}	
	    }
	 
	//Excel文件导出成文件流
		//dataList 需要导出的数据列表
		//execlName 导出后默认的文件名
		//tmpContent:数据库字段名，多字段以逗号分割
		//tmpContentCnexcel:文件名字段名，多字段以逗号分割
		/**
		 * Do export.
		 * 
		 * @param response the response
		 * @param dataList the data list
		 * @param execlName the execl name
		 * @param tmpContent the tmp content
		 * @param tmpContentCn the tmp content cn
		 * 
		 * @throws Exception the exception
		 */
		public  void  doExport(HttpServletResponse response,List dataList,String execlName,String tmpContent,String tmpContentCn,String type) throws Exception{
			//生成excel
			HSSFWorkbook workbook =new HSSFWorkbook();
			if("temp".equals(type)){
				 workbook = tempPrintExcel(tmpContentCn);
			}else{
				 workbook = printExcel(tmpContent,tmpContentCn,dataList);
			}
	        //导出excel
	        writeExecl(response,workbook,execlName,type);
		}
		/**
		 * Prints the excel.
		 * 
		 * @param tmpContent the tmp content
		 * @param tmpContentCn the tmp content cn
		 * @param dataList the data list
		 * 
		 * @return the hSSF workbook
		 */
		private  HSSFWorkbook printExcel(String tmpContent,String tmpContentCn,List dataList){

		     HSSFWorkbook workbook = null;
		     String[] titles_CN = tmpContentCn.split(",");
		     String[] titles_EN = tmpContent.split(",");
		     try{
		          //创建工作簿实例 
		           workbook = new HSSFWorkbook();
		          //创建工作表实例 
		         HSSFSheet sheet = workbook.createSheet("Sheet1"); 
		          //设置列宽 
		          setSheetColumnWidth(titles_CN,sheet);
		        //获取样式 
		          HSSFCellStyle style = createTitleStyle(workbook); 
		          if(dataList != null){
		               //创建第一行标题 
		                HSSFRow row = sheet.createRow((short)0);// 建立新行
		                row.setHeight((short) 0);
		                createCell(row, 0, style, HSSFCell.CELL_TYPE_STRING, "hoperun");
		                HSSFRow rows = sheet.createRow((short)1);// 建立新行
		                for(int i=0;i<titles_CN.length;i++){
		                createCell(rows, i, null, HSSFCell.CELL_TYPE_STRING, 
		                       titles_CN[i]);
		                }
		                //给excel填充数据 
		               for(int i=1;i<=dataList.size();i++){ 
		                        // 将dataList里面的数据取出来 
		                        Map<String,String> map = (HashMap)(dataList.get(i-1));
		                         HSSFRow row1 = sheet.createRow((short) (i + 1));// 建立新行
		                        boolean isOverTime = false;
		                         for(int j=0;j<titles_EN.length;j++){
		                            createCell(row1, j, style, HSSFCell.CELL_TYPE_STRING, map.get(titles_EN[j]));
		                                  }               
		                      }
		       }else{
		                createCell(sheet.createRow(0), 0, style,HSSFCell.CELL_TYPE_STRING, "查无资料");
		       }
		  }catch(Exception e){
		              e.printStackTrace();
		  }
		 return workbook;
		}
		//设置列宽
		/**
		 * Sets the sheet column width.
		 * 
		 * @param titles_CN the titles_ cn
		 * @param sheet the sheet
		 */
		private  void setSheetColumnWidth(String[] titles_CN,HSSFSheet sheet){ 
		   // 根据你数据里面的记录有多少列，就设置多少列
		  for(int i=0;i<titles_CN.length;i++){
			  sheet.setColumnWidth((short)i, (short) 6000);
		          
		  }

		}
		//创建Excel单元格  
		/**
		 * Creates the cell.
		 * 
		 * @param row the row
		 * @param column the column
		 * @param style the style
		 * @param cellType the cell type
		 * @param value the value
		 */
		private void createCell(HSSFRow row, int column, HSSFCellStyle style,int cellType,Object value) { 
		  HSSFCell cell = row.createCell( column);  
		  if (style != null) { 
		       cell.setCellStyle(style); 
		  }   
		  String res = (value==null?"":value).toString();
		  switch(cellType){ 
		       case HSSFCell.CELL_TYPE_BLANK: {} break; 
		       case HSSFCell.CELL_TYPE_STRING: {cell.setCellValue(res+"");} break; 
		       case HSSFCell.CELL_TYPE_NUMERIC: {
		       cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC); 
		           cell.setCellValue(Double.parseDouble(value.toString()));}break; 
		       default: break; 
			 }  
			
			} 
		/**
		 * Write execl.
		 * 
		 * @param response the response
		 * @param workbook the workbook
		 * @param execlName the execl name
		 */
		public  void writeExecl(HttpServletResponse response,HSSFWorkbook workbook, String execlName,String type) {
			if (null == workbook)
			{
				workbook = new HSSFWorkbook();
			}
			
			if (0 == workbook.getNumberOfSheets()) {
				HSSFSheet sheet = workbook.createSheet("无数据");
				sheet.createRow(3).createCell(3).setCellValue("未查询到数据!");
			}
			response.reset();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/msexcel;charset=UTF-8");
			ServletOutputStream outputStream = null;
			try {
				if("temp".equals(type)){
					response.setHeader("Content-disposition", "attachment; filename="
							+ new String(execlName.getBytes("gb2312"), "ISO8859-1") + ".xls");
				}else{
					response.setHeader("Content-disposition", "attachment; filename="
							+ new String(execlName.getBytes("gb2312"), "ISO8859-1") + "_" + DateFormatUtils.format(new Date(), "MM-dd") + ".xls");
				}
				outputStream = response.getOutputStream();
				workbook.write(outputStream);
				outputStream.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != outputStream) {
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		//设置excel的title样式  
		/**
		 * Creates the title style.
		 * 
		 * @param wb the wb
		 * 
		 * @return the hSSF cell style
		 */
		private HSSFCellStyle createTitleStyle(HSSFWorkbook wb) { 
		  HSSFFont boldFont = wb.createFont(); 
		  boldFont.setFontHeight((short) 200); 
		  HSSFCellStyle style = wb.createCellStyle(); 
		  style.setFont(boldFont); 
		  style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		  //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		  //style.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
		  return style;  
		}
		private  HSSFWorkbook tempPrintExcel(String tmpContentCn){
			HSSFWorkbook workbook = null;
		     String[] titles_CN = tmpContentCn.split(",");
		     try{
		          //创建工作簿实例 
		           workbook = new HSSFWorkbook();
		          //创建工作表实例 
		         HSSFSheet sheet = workbook.createSheet("Sheet1"); 
		          //设置列宽 
		          setSheetColumnWidth(titles_CN,sheet);
		        //获取样式 
		          HSSFCellStyle style = createTitleStyle(workbook); 
		               //创建第一行标题 
		                HSSFRow row = sheet.createRow((short)0);// 建立新行
		                row.setHeight((short) 0);
		                createCell(row, 0, style, HSSFCell.CELL_TYPE_STRING, "hoperun");
		                HSSFRow rows = sheet.createRow((short)1);// 建立新行
		                for(int i=0;i<titles_CN.length;i++){
		                createCell(rows, i, null, HSSFCell.CELL_TYPE_STRING, 
		                       titles_CN[i]);
		                }
		  }catch(Exception e){
		              e.printStackTrace();
		  }
		 return workbook;
		}
	 
	/**
	 * * 设置权限Map
	 * 
	 * @param UserBean
	 *            the userBean
	 * @return Map<String,String>
	 */
	private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,
				PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil
				.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("AUD_BUSS_STATE",AUD_BUSS_STATE);
		pvgMap.put("PVG_RETURN", QXUtil.checkPvg(userBean,PVG_RETURN));
		pvgMap.put("PVG_IMPORT", QXUtil.checkPvg(userBean,PVG_IMPORT));
		
		return pvgMap;
	}
}
