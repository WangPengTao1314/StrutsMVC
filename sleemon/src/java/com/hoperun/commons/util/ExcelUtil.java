package com.hoperun.commons.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelUtil.
 * 
 * @module 共通模块
 * @func Excel工具类
 * @version 1.1
 * @author 朱晓文
 */
public class ExcelUtil {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(ExcelUtil.class);
   
    /**
     * add by zhuxw 2012-09-20 Description : 根据传入的sheetModel，读取excel转化为List对象.
     * 
     * @param fileName the file name
     * @param secPath the sec path
     * @param sheetNum the sheet num
     * @param sheetModel the sheet model
     * @param beaginNum the beagin num
     * 
     * @return the list
     */
	@SuppressWarnings("unchecked")
	public static List readExcelbyModel(String fileName, String secPath,
			int sheetNum, List sheetModel, String beaginNum[]) {
		try {
			// 参数判断
			if (fileName == null || fileName.equals("")) {
				throw new Exception("对不起，您的 fileName 参数设置不完整");
			}
			// 参数判断
			if (secPath == null || secPath.equals("")) {
				throw new Exception("对不起，您的 secPath 参数设置不完整");
			}
			if (sheetModel == null || sheetModel.size() == 0) {
				throw new Exception("对不起，您的sheetModel 参数设置不完整");
			}
			String rootPath = Properties.getString("UPLOADFILE_DIR");
			String secondPath = Properties.getString(secPath);
			String filePath = rootPath + File.separator + secondPath
					+ File.separator + fileName;
			Workbook allBooks = getWorkbookByPath(filePath);
			if (allBooks == null) {
				throw new Exception("对不起，文件为空，没有内容!");
			}
			List<List> resultList = new ArrayList<List>();
			for (int i = 0; i < sheetNum; i++) {
				List<Map> sheetList = new ArrayList<Map>();
				Sheet sheet = allBooks.getSheetAt(i);
				int tempBeaginNum = Integer.parseInt(beaginNum[i]);
				Map<String, String> tempMap = (HashMap<String, String>) sheetModel
						.get(i);
				int j = 0;
				for (Iterator rit = sheet.rowIterator(); rit.hasNext();) {
					j = j + 1;
					Row row = (Row) rit.next();
					if (j < tempBeaginNum)
						continue;
					Map<String, String> rowMap = new HashMap<String, String>();
					for (Object obj : tempMap.keySet()) {
						if (obj == null) {
							throw new Exception("对不起，您的sheetModel 参数设置不完整");
						}
						String tempStr = obj.toString();
						Object tempConKey = tempMap.get(tempStr);
						rowMap.put(tempStr, getCellValue(row.getCell(Integer
								.parseInt(tempConKey.toString()))));
					}
					sheetList.add(rowMap);
				}
				resultList.add(sheetList);
			}
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * add by zhuxw 2012-09-20 Description : 读取excel转化为List对象 ,适用于数据量不是很大
	 * 
	 * @param response
	 * @param fileName,String secondPath
	 * @param rootPath  非必须传送，默认为系统设置的excel路径
	 * @param sheetColNum  存的每个sheet要读取的列数比如{10,12}等等
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static List readExcel(String fileName, String secPath,String [] sheetColNum) {
		try {

			// 参数判断
			if (StringUtil.isEmpty(fileName)) {
				throw new Exception("对不起，您的 fileName 参数设置不完整");
			}
			// 参数判断
			if (StringUtil.isEmpty(secPath)) {
				throw new Exception("对不起，您的 secondPath 参数设置不完整");
			}
			String rootPath = Properties.getString("UPLOADFILE_DIR");
			String secondPath = Properties.getString(secPath);
			String filePath = rootPath + "/" + secondPath + "/" + fileName;
			File tempFile = new File(filePath);
			if (tempFile == null) {
				throw new Exception("对不起，文件不存在");
            }
			Workbook allBooks = getWorkbookByPath(filePath);
			if (allBooks == null) {
				throw new Exception("对不起，文件为空，没有内容!");
			}
			List<List> resultList = new ArrayList<List>();
			int sheetNum = allBooks.getNumberOfSheets();
			for (int i = 0; i < sheetNum; i++) {
				List<Map> sheetList = new ArrayList<Map>();
				Sheet sheet = allBooks.getSheetAt(i);
				for (Iterator rit = sheet.rowIterator(); rit.hasNext();) {
					Map<String, String> rowMap = new HashMap<String, String>();
					StringBuffer rowAllVal = new StringBuffer();
					Row row = (Row) rit.next();
					int cellNum = Integer.parseInt(sheetColNum[i]);
					for (int k = 0; k < cellNum; k++) {
						String tempVal = getCellValue(row.getCell(k));
						rowMap.put(String.valueOf(k), tempVal);
						rowAllVal.append(StringUtil.nullToSring(tempVal));

					}
					if (!StringUtil.isEmpty(rowAllVal.toString())) {
						sheetList.add(rowMap);
					}

				}
				resultList.add(sheetList);
			}
			return resultList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * add by zhuxw 2012-09-20 Description : 写excel 单sheet
	 * 
	 * @param response
	 * @param fileName,
	 *            rootPath, sheetNum, sheetName, outModel, outValue
	 * @param rootPath
	 *            非必须传送，默认为系统设置的excel路径
	 * 
	 */
	public static String wirteExlJustOneSheet(String sheetName,
			String fileKeyName, String secPath, List<Map> outValue,
			String[] BILL_COL, String[] BILL_VAL, String[] BILL_TYPE) {
		FileOutputStream os = null;
		try {
			if (StringUtil.isEmpty(fileKeyName) ) {
				throw new Exception("wirteExcel:对不起，您的 fileKeyName 参数设置不完整");
			}
			if (StringUtil.isEmpty(secPath)  ) {
				throw new Exception("wirteExcel:对不起，您的 secPath 参数设置不完整");
			}
			String rootPath = Properties.getString("UPLOADFILE_DIR");
			String secondPath = Properties.getString(secPath);
			String uuId = StringUtil.uuid32len();
			String dateStr = DateFormatUtils.format(new Date(), "yyyy/MM-dd");
			String resPath = "/" + dateStr + "/" + fileKeyName + "_" + uuId
					+ ".xls";
			String path = rootPath + "/" + secondPath + "/" + dateStr;
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String filePath = rootPath + "/" + secondPath + resPath;
			os = new FileOutputStream(filePath);
			// 创建工作薄,一个参数
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFCellStyle dateCellStyle = wb.createCellStyle();
			short df = wb.createDataFormat().getFormat("yyyy-mm-dd");
			dateCellStyle.setDataFormat(df);

			// 创建工作表
			HSSFSheet sheet = wb.createSheet(sheetName);
			// 写表头
			HSSFRow firRow = sheet.createRow(0);
			int colLen = BILL_COL.length;
			for (int j = 0; j < colLen; j++) {
				HSSFCell cell = firRow.createCell(j);
				setCellValue(cell, StringUtil.nullToSring(BILL_COL[j]),
						"string", dateCellStyle);
			}
			int outValueNum = outValue.size();
			for (int i = 1; i <= outValueNum; i++) {
				HSSFRow row = sheet.createRow(i);
				Map rowVal = (HashMap) outValue.get(i - 1);
				for (int j = 0; j < colLen; j++) {
					String keyName = BILL_VAL[j];
					HSSFCell cell = row.createCell(j);
					if (keyName.equals("ISNULL")) {
						setCellValue(cell, "", BILL_TYPE[j], dateCellStyle);
					} else {
						setCellValue(cell, StringUtil.nullToSring(rowVal
								.get(BILL_VAL[j])), BILL_TYPE[j], dateCellStyle);

					}
				}
			}
			// 进行写操作
			wb.write(os);
			return resPath;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * add by zhuxw 2012-09-20 Description : 写excel 多sheet
	 * 
	 * @param response
	 * @param fileName,
	 *            rootPath, sheetNum, sheetName, outModel, outValue
	 * @param rootPath
	 *            非必须传送，默认为系统设置的excel路径
	 * 
	 */
	public static String wirteExlMulSheets(List<String> sheetName,
			String fileKeyName, String secPath, List<List<Map>> outValueS,
			List<String[]> BILL_COLS, List<String[]> BILL_VALS,
			List<String[]> BILL_TYPES) {
		FileOutputStream os = null;
		try {
			if (StringUtil.isEmpty(fileKeyName) ) {
				throw new Exception("wirteExcel:对不起，您的 fileKeyName 参数设置不完整");
			}
			if (StringUtil.isEmpty(secPath) ) {
				throw new Exception("wirteExcel:对不起，您的 secPath 参数设置不完整");
			}

			String rootPath = Properties.getString("UPLOADFILE_DIR");
			String secondPath = Properties.getString(secPath);
			String uuId = StringUtil.uuid32len();
			String dateStr = DateFormatUtils.format(new Date(), "yyyy/MM-dd");
			String resPath = "/" + dateStr + "/" + fileKeyName + "_" + uuId
					+ ".xls";
			String path = rootPath + "/" + secondPath + "/" + dateStr;
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String filePath = rootPath + "/" + secondPath + resPath;
			os = new FileOutputStream(filePath);
			// 创建工作薄,一个参数
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFCellStyle dateCellStyle = wb.createCellStyle();
			short df = wb.createDataFormat().getFormat("yyyy-mm-dd");
			dateCellStyle.setDataFormat(df);
			for (int k = 0; k < sheetName.size(); k++) {
				String tmpName = sheetName.get(k);
				// 创建工作表
				HSSFSheet sheet = wb.createSheet(tmpName);

				// 写表头
				HSSFRow firRow = sheet.createRow(0);
				String[] BILL_COL = BILL_COLS.get(k);
				String[] BILL_VAL = BILL_VALS.get(k);
				String[] BILL_TYPE = BILL_TYPES.get(k);
				int colLen = BILL_COL.length;
				for (int j = 0; j < colLen; j++) {
					HSSFCell cell = firRow.createCell(j);
					setCellValue(cell, StringUtil.nullToSring(BILL_COL[j]),
							"string", dateCellStyle);
				}
				List<Map> outValue = outValueS.get(k);
				int outValueNum = outValue.size();
				for (int i = 1; i <= outValueNum; i++) {
					HSSFRow row = sheet.createRow(i);
					Map rowVal = (HashMap) outValue.get(i - 1);
					for (int j = 0; j < colLen; j++) {

						String keyName = BILL_VAL[j];
						HSSFCell cell = row.createCell(j);
						if (keyName.equals("ISNULL")) {
							setCellValue(cell, "", BILL_TYPE[j], dateCellStyle);
						} else {
							setCellValue(cell, StringUtil.nullToSring(rowVal
									.get(BILL_VAL[j])), BILL_TYPE[j],
									dateCellStyle);
						}
					}
				}
			}
			// 进行写操作
			wb.write(os);
			return resPath;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * add by zhuxw 2012-09-20 Description :
	 * @param response
	 * @param fileName, rootPath, sheetNum, sheetName, outModel, outValue
	 * @param rootPath 非必须传送，默认为系统设置的excel路径
	 * @throws Exception
	 * 
	 */
	public static String wirteExlFromCopy(String fileKeyName, String secPath,
			List<Map> outValue, String[] BILL_COL, String[] BILL_VAL,
			String[] BILL_TYPE, String exlModelPath) throws Exception {
		FileOutputStream os = null;
		try {
			if (StringUtil.isEmpty(fileKeyName) ) {
				throw new Exception("wirteExcel:对不起，您的 fileKeyName 参数设置不完整");
			}
			if (StringUtil.isEmpty(fileKeyName)  ) {
				throw new Exception("wirteExcel:对不起，您的 secPath 参数设置不完整");
			}
			String rootPath = Properties.getString("UPLOADFILE_DIR");
			String secondPath = Properties.getString(secPath);
			String uuId = StringUtil.uuid32len();
			String dateStr = DateFormatUtils.format(new Date(), "yyyy/MM-dd");
			String resPath = "/" + dateStr + "/" + fileKeyName + "_" + uuId+ ".xls";
			String path = rootPath + "/" + secondPath + "/" + dateStr;
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			exlModelPath = rootPath + "/" + exlModelPath;
			File modelFile = new File(exlModelPath);
			if (!modelFile.exists()) {
				throw new Exception(" 模板文件不存在!");
			}

			String filePath = rootPath + "/" + secondPath + resPath;
			copyFile(exlModelPath, filePath);

			// 创建工作薄,一个参数
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					filePath));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFCellStyle dateCellStyle = wb.createCellStyle();
			short df = wb.createDataFormat().getFormat("yyyy-mm-dd");
			dateCellStyle.setDataFormat(df);

			// 获得工作簿
			HSSFSheet sheet = wb.getSheetAt(0);
			// 写表头
			HSSFRow firRow = sheet.createRow(0);
			int colLen = BILL_COL.length;
			for (int j = 0; j < colLen; j++) {
				HSSFCell cell = firRow.createCell(j);
				setCellValue(cell, StringUtil.nullToSring(BILL_COL[j]),
						"string", dateCellStyle);
			}
			int outValueNum = outValue.size();
			for (int i = 1; i <= outValueNum; i++) {
				HSSFRow row = sheet.createRow(i);
				Map rowVal = (HashMap) outValue.get(i - 1);
				for (int j = 0; j < colLen; j++) {
					String keyName = BILL_VAL[j];
					HSSFCell cell = row.createCell(j);
					if (keyName.equals("ISNULL")) {
						setCellValue(cell, "", BILL_TYPE[j], dateCellStyle);
					} else {
						setCellValue(cell, StringUtil.nullToSring(rowVal
								.get(BILL_VAL[j])), BILL_TYPE[j], dateCellStyle);
					}
				}
			}
			// 进行写操作
			os = new FileOutputStream(filePath);
			wb.write(os);
			return resPath;
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != os) {
				os.close();
			}
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
	public  static void  doExport(HttpServletResponse response,List dataList,String execlName,String tmpContent,String tmpContentCn) throws Exception{
				//生成excel
	            HSSFWorkbook workbook = printExcel(tmpContent,tmpContentCn,dataList);
	            //导出excel
	            writeExecl(response,workbook,execlName);
	}
	
	/**
	 * Do export.
	 * add by zhuxw 2014-08-18 增加字段内行
	 * @param response the response
	 * @param dataList the data list
	 * @param execlName the execl name
	 * @param tmpContent the tmp content
	 * @param tmpContentCn the tmp content cn
	 * 
	 * @throws Exception the exception
	 */
	public  static void  doExport(HttpServletResponse response,List dataList,String fileNameCn,String logicName,String physicName,String colType) throws Exception{
				//生成excel
	            HSSFWorkbook workbook = printExcel(logicName,physicName,dataList,colType);
	            //导出excel
	            writeExecl(response,workbook,fileNameCn);
	}
	
	
	/**
	 * Do export.
	 * add by zhuxw 2014-08-18 增加字段内行,颜色
	 * @param response the response
	 * @param dataList the data list
	 * @param execlName the execl name
	 * @param tmpContent the tmp content
	 * @param tmpContentCn the tmp content cn
	 * 
	 * @throws Exception the exception
	 */
	public  static void  doExport(HttpServletResponse response,List dataList,String fileNameCn,String logicName,String physicName,String colType,String color) throws Exception{
				//生成excel
	            HSSFWorkbook workbook = printExcel(logicName,physicName,dataList,colType,color);
	            //导出excel
	            writeExecl(response,workbook,fileNameCn);
	}
	/**
	 * Write execl.
	 * 
	 * @param response the response
	 * @param workbook the workbook
	 * @param execlName the execl name
	 */
	public static void writeExecl(HttpServletResponse response,HSSFWorkbook workbook, String execlName) {
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
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(execlName.getBytes("gb2312"), "ISO8859-1") + "_" + DateFormatUtils.format(new Date(), "MM-DD") + ".xls");
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
		}
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
	private  static HSSFWorkbook printExcel(String tmpContent,String tmpContentCn,List dataList){

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
	                for(int i=0;i<titles_CN.length;i++){
	                createCell(row, i, null, HSSFCell.CELL_TYPE_STRING, 
	                       titles_CN[i]);
	                }
	                //给excel填充数据 
	               for(int i=0;i<dataList.size();i++){ 
	                        // 将dataList里面的数据取出来 
	                        Map<String,String> map = (HashMap)(dataList.get(i));
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

	
	/** add by zhuxw 2014-08-18 增加字段类型
	 * Prints the excel.
	 * 
	 * @param tmpContent the tmp content
	 * @param tmpContentCn the tmp content cn
	 * @param dataList the data list
	 * 
	 * @return the hSSF workbook
	 */
	private  static HSSFWorkbook printExcel(String tmpContent,String tmpContentCn,List dataList,String colType){

	     HSSFWorkbook workbook = null;
	     String[] titles_CN = tmpContentCn.split(",");
	     String[] titles_EN = tmpContent.split(",");
	     String[] colTypeArr = colType.split(",");
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
	                for(int i=0;i<titles_CN.length;i++){
	                	  
	                	  
	                		   createCell(row, i, null, HSSFCell.CELL_TYPE_STRING, 
	        	                       titles_CN[i]);  
	                      
	                }
	                //给excel填充数据 
	               for(int i=0;i<dataList.size();i++){ 
	                        // 将dataList里面的数据取出来 
	                        Map<String,String> map = (HashMap)(dataList.get(i));
	                         HSSFRow row1 = sheet.createRow((short) (i + 1));// 建立新行
	               
	                        boolean isOverTime = false;
	                         for(int j=0;j<titles_EN.length;j++){
	                        	 if("number".equals(colTypeArr[j]))
	  	                	   {
	  	                		   createCell(row1, j, null, HSSFCell.CELL_TYPE_NUMERIC, map.get(titles_EN[j])); 
	  	                	   }else
	  	                	   {
	                            createCell(row1, j, null, HSSFCell.CELL_TYPE_STRING, map.get(titles_EN[j]));
	  	                	   }
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
	
	/** add by zhuxw 2014-08-18 增加字段类型,增加颜色
	 * Prints the excel.
	 * 
	 * @param tmpContent the tmp content
	 * @param tmpContentCn the tmp content cn
	 * @param dataList the data list
	 * 
	 * @return the hSSF workbook
	 */
	private  static HSSFWorkbook printExcel(String tmpContent,String tmpContentCn,List dataList,String colType,String color){

	     HSSFWorkbook workbook = null;
	     String[] titles_CN = tmpContentCn.split(",");
	     String[] titles_EN = tmpContent.split(",");
	     String[] colTypeArr = colType.split(",");
	     String[] colorArr = color.split(",");
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
	                for(int i=0;i<titles_CN.length;i++){
                          if(!StringUtil.isEmpty(colorArr[i])&&!colorArr[i].equals("none"))
                          {
                        	
                        	  String[] colNumber = colorArr[i].split("-");
                        	  if(colNumber.length==3)
                        	  {
                        		  HSSFPalette palette = workbook.getCustomPalette();  
                        		  palette.setColorAtIndex((short)9, (byte) (0xff & Integer.parseInt(colNumber[0])), (byte) (0xff & Integer.parseInt(colNumber[1])), (byte) (0xff & Integer.parseInt(colNumber[2])));
                        		  style.setFillBackgroundColor((short)9);
                        		  createCell(row, i, style, HSSFCell.CELL_TYPE_STRING,titles_CN[i]);  
                        	  }else
                        	  {
                        		  createCell(row, i, null, HSSFCell.CELL_TYPE_STRING,titles_CN[i]);   
                        	  }
                        	  
                        	  
                          }else
                          {
                        	  createCell(row, i, null, HSSFCell.CELL_TYPE_STRING,titles_CN[i]);   
                          }
	                	  
	                      
	                }
	                //给excel填充数据 
	               for(int i=0;i<dataList.size();i++){ 
	                        // 将dataList里面的数据取出来 
	                        Map<String,String> map = (HashMap)(dataList.get(i));
	                         HSSFRow row1 = sheet.createRow((short) (i + 1));// 建立新行
	               
	                        boolean isOverTime = false;
	                         for(int j=0;j<titles_EN.length;j++){
	                        	 
	                        	 if(!StringUtil.isEmpty(colorArr[j])&&!colorArr[j].equals("none"))
	                             {
	                           	
	                           	  String[] colNumber = colorArr[j].split("-");
	                           	  if(colNumber.length==3)
	                           	  {
	                           		  HSSFPalette palette = workbook.getCustomPalette();  
	                           		  palette.setColorAtIndex((short)9, (byte) (0xff & Integer.parseInt(colNumber[0])), (byte) (0xff & Integer.parseInt(colNumber[1])), (byte) (0xff & Integer.parseInt(colNumber[2])));
	                           		  style.setFillBackgroundColor((short)9);
	                           		 if("number".equals(colTypeArr[j]))
	  	  	                	   {
	  	  	                		   createCell(row1, j, style, HSSFCell.CELL_TYPE_NUMERIC,map.get(titles_EN[j])); 
	  	  	                	   }else
	  	  	                	   {
	  	                               createCell(row1, j, style, HSSFCell.CELL_TYPE_STRING, map.get(titles_EN[j]));
	  	  	                	   }
	                           	  }else
	                           	  {
	                           		 if("number".equals(colTypeArr[j]))
	  	  	                	   {
	  	  	                		   createCell(row1, j, null, HSSFCell.CELL_TYPE_NUMERIC,map.get(titles_EN[j])); 
	  	  	                	   }else
	  	  	                	   {
	  	                               createCell(row1, j, null, HSSFCell.CELL_TYPE_STRING, map.get(titles_EN[j]));
	  	  	                	   }  
	                           	  }
	                           	  
	                           	  
	                             }else
	                             {
	                            	 if("number".equals(colTypeArr[j]))
	  	  	                	   {
	  	  	                		   createCell(row1, j, null, HSSFCell.CELL_TYPE_NUMERIC,map.get(titles_EN[j])); 
	  	  	                	   }else
	  	  	                	   {
	  	                               createCell(row1, j, null, HSSFCell.CELL_TYPE_STRING, map.get(titles_EN[j]));
	  	  	                	   }
	                             }
	                        	 
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
	private static void setSheetColumnWidth(String[] titles_CN,HSSFSheet sheet){ 
	   // 根据你数据里面的记录有多少列，就设置多少列
	  for(int i=0;i<titles_CN.length;i++){
	          sheet.setColumnWidth((short)i, (short) 3000);
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
	private static HSSFCellStyle createTitleStyle(HSSFWorkbook wb) { 
	  HSSFFont boldFont = wb.createFont(); 
	  boldFont.setFontHeight((short) 200); 
	  HSSFCellStyle style = wb.createCellStyle(); 
	  style.setFont(boldFont); 
	  style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00")); 
	  //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	  //style.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
	  return style;  
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
	private static void createCell(HSSFRow row, int column, HSSFCellStyle style,int cellType,Object value) { 
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
	           cell.setCellValue(StringUtil.getDouble(res));}break; //modidy 2015-03-20
	       default: break; 
		 }  
		
		} 

	/**
	 * Gets the workbook by path.
	 * 
	 * @param filePath the file path
	 * 
	 * @return the workbook by path
	 * 
	 * @throws ServiceException the service exception
	 */
	public static Workbook getWorkbookByPath(String filePath)
			throws ServiceException {
		InputStream inp = null;
		Workbook wb = null;
		try {

			if (filePath.endsWith(".xls")) {
				inp = new FileInputStream(filePath);
				wb = (Workbook) new HSSFWorkbook(new POIFSFileSystem(inp));
			} else if (filePath.endsWith(".xlsx")) {
				inp = new FileInputStream(filePath);
				wb = (Workbook) new XSSFWorkbook(filePath);
			} else {
				throw new ServiceException("读取EXECL失败,您上传的文件非excel文件!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("读取EXECL失败");
		} finally {
			try {
				if (inp != null) {
					inp.close();
				}
			} catch (IOException ex) {
			}
		}
		return wb;
	}

	/**
	 * Gets the cell value.
	 * 
	 * @param cell the cell
	 * 
	 * @return the cell value
	 * 
	 * @throws ServiceException the service exception
	 */
	public static String getCellValue(Cell cell) throws ServiceException {
		if (cell == null)
			return "";
		String result = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_FORMULA:
			result = cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				// SimpleDateFormat datefomrat=new SimpleDateFormat("yyyy-MM-dd
				// HH:mm:ss");
				SimpleDateFormat datefomrat = new SimpleDateFormat("yyyy-MM-dd");
				if (date == null)
					result = "";
				else
					result = datefomrat.format(cell.getDateCellValue());
			} else {
				result = StringUtil.nullToSring(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_STRING:
			result = StringUtil.nullToSring(cell.getRichStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			result = "";
			break;
		default:
			result = "";
		}
		return result;
	}

	/**
	 * Sets the cell value.
	 * 
	 * @param cell the cell
	 * @param value the value
	 * @param strType the str type
	 * @param dateCellStyle the date cell style
	 * 
	 * @throws ServiceException the service exception
	 * @throws ParseException the parse exception
	 */
	public static void setCellValue(HSSFCell cell, String value,
			String strType, HSSFCellStyle dateCellStyle)
			throws ServiceException, ParseException {
		if (cell != null) {
			if ("string".equals(strType)) {
				cell.setCellValue(new HSSFRichTextString(StringUtil
						.nullToSring(value)));
			}
			if ("date".equals(strType)) {
				if (!StringUtil.nullToSring(value).equals("")) {
					SimpleDateFormat datetemp = new SimpleDateFormat(
							"yyyy-MM-dd");
					Date tempStr = datetemp.parse(value);
					cell.setCellValue(tempStr);
					cell.setCellStyle(dateCellStyle);
				}

			}
			if ("number".equals(strType)) {
				if (!StringUtil.nullToSring(value).equals("")) {
					cell.setCellValue(Double.parseDouble(StringUtil
							.nullToSring(value)));
				}
			}
		}

	}
	/**
	 * add by zhuxw 2012-12-06 Description : 使用二进制复制文件
	 * 
	 * @param String
	 *            oriFile,String copyFile
	 * 
	 * @throws IOException
	 * 
	 */
	public static void copyFile(String oriFile, String copyFile)
			throws IOException {
		FileInputStream fis =null;
		FileOutputStream fos = null;
		try {
			 fis = new FileInputStream(oriFile);
			 fos = new FileOutputStream(copyFile);
			byte[] bs = new byte[1024];
			int len = -1;
			while ((len = fis.read(bs)) != -1) {
				fos.write(bs, 0, len);
			}
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(null!=fos)
			{
				fos.close();
			}
			if(null!=fis)
			{
				fis.close();
			}
			
		}
		
	}

}	
