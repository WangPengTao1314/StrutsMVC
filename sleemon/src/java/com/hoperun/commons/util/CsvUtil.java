package com.hoperun.commons.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class CsvUtil.
 * 
 * @module 共通模块
 * @func Csv 文件操作类
 * @version 1.1
 * @author 朱晓文
 */
public class CsvUtil {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(CsvUtil.class);

	/**
	 * Write csv.
	 * 
	 * @param filePath the file path
	 * @param outVals the out vals
	 * @param cols the cols
	 * @param titls the titls
	 * @param charCode the char code
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeCsv(String filePath,
			ArrayList<HashMap<String, String>> outVals, String[] cols,
			String[] titls, String charCode) throws IOException {
		CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName(charCode));
		int lenCols = cols.length;
		csvWriter.writeRecord(titls);
		for (HashMap obj : outVals) {
			String[] insVal = new String[lenCols];
			for (int i = 0; i < lenCols; i++) {
				insVal[i] = StringUtil.nullToSring(obj.get(cols[i]));
			}
			csvWriter.writeRecord(insVal);
		}
		csvWriter.close();
	}
	

	/**
	 * Read csv by model.
	 * 
	 * @param filePath the file path
	 * @param colName the col name
	 * @param colNum the col num
	 * @param beaginNum the beagin num
	 * @param charCode the char code
	 * 
	 * @return the list
	 * 
	 * @throws Exception the exception
	 */
	public static List readCsvByModel(String filePath,String[]colName,String[]colNum,int beaginNum,String charCode) throws Exception {
		
		// 参数判断
		if (StringUtil.isEmpty(filePath)) {
			throw new Exception("对不起，您的 filePath 参数设置不完整");
		}
		if (colName == null || colName.length == 0) {
			throw new Exception("对不起，colName 参数设置不完整");
		}
		if (colNum == null ||colNum.length == 0) {
			throw new Exception("对不起，colNum 参数设置不完整");
		}
		if (StringUtil.isEmpty(charCode)) {
			throw new Exception("对不起，您的 charCode 参数设置不完整");
		}
		CsvReader csvReader =null;
		List<Map> resultList = new ArrayList<Map>();
		int j = 0;
		 try {
			   csvReader = new CsvReader(filePath, ',', Charset.forName(charCode));
			   // reader.readHeaders();   
		       // String[] headers = reader.getHeaders();   
               String [] nextLine;
			   while (csvReader.readRecord()) { 
				   nextLine= csvReader.getValues();
				   j = j + 1;
				   if (j < beaginNum)
				   continue;
				   if(nextLine!=null)
				   {
					  Map<String, String> rowMap = new HashMap<String, String>();
					  int i=0;
					  for(String col:colName)
					  {
						 rowMap.put(col,nextLine[Integer.parseInt(colNum[i])-1]);
						 i=i+1; 
					  }
					  resultList.add(rowMap);  
				   }
				   
				 }
			   return resultList;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}   
		return null;
	}
	
}
