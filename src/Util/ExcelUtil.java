package Util;


import java.io.InputStream;
import java.sql.ResultSet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	@SuppressWarnings("deprecation")
	public static String formatCell(Cell cell){
		if(cell==null){
			return "";
		}else{
			if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
				return String.valueOf(cell.getBooleanCellValue());
			}else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
				DataFormatter dataFormatter = new DataFormatter();  //解决excel导入数据库，整数变小数问题
				 
			    String cellFormatted = dataFormatter.formatCellValue(cell);
//			    System.out.println( cellFormatted );
				return cellFormatted;
			}else{
				return String.valueOf(cell.getStringCellValue());
			}
		}
	}
	/**
	 * 判断Excel版本是否为2003
	 * @param is
	 * @return
	 */
	public static boolean isExcel2003(InputStream is) {
		try {
			new HSSFWorkbook(is);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 创建一个excel文件自定义头属性导出数据
	 */
	public static void fillExcelData(ResultSet rs,Workbook wb,String[] headers)throws Exception{
		int rowIndex=0;
		Sheet sheet=wb.createSheet();
		Row row=sheet.createRow(rowIndex++);
		for(int i=0;i<headers.length;i++){
			row.createCell(i).setCellValue(headers[i]);
		}
		while(rs.next()){
			row=sheet.createRow(rowIndex++);
			for(int i=0;i<headers.length;i++){
				row.createCell(i).setCellValue(rs.getObject(i+2).toString());
			}
		}
	}
	/**
	 * 利用模板导出数据（学生请假）
	 */
	public static Workbook fillExcelDataWithTemplate(ResultSet rs,String templateFileName)throws Exception{
		InputStream inp=ExcelUtil.class.getResourceAsStream("/template/"+templateFileName);
		Workbook wb=null;
//		if(POIXMLDocument.hasOOXMLHeader(inp)) {
//			//2007版本及以上
//			wb = new XSSFWorkbook(inp); 
//		}else{
//			wb = new HSSFWorkbook(inp);
//		}
		if(POIFSFileSystem.hasPOIFSHeader(inp)) {
//			System.out.println("2003及以下");
			wb = new HSSFWorkbook(inp);
		}else {
//			System.out.println("2007及以上");
			wb = new XSSFWorkbook(inp); 
		}
		Sheet sheet=wb.getSheetAt(0); //获取第一个sheet页
		// 获取模板一行有多少列
		int cellNums=sheet.getRow(0).getLastCellNum();
		int rowIndex=1;      //从第二行开始插入数据
		while(rs.next()){
			Row row=sheet.createRow(rowIndex++);
			for(int i=0;i<cellNums;i++){
				row.createCell(i).setCellValue(rs.getObject(i+1).toString());//jdbc的数据下标从1开始
			}
		}
		return wb;
	}
	
	/**
	 * 利用模板导出数据（学生信息）
	 */
	public static Workbook fillExcelDataWithTemplate1(ResultSet rs,String templateFileName)throws Exception{
		InputStream inp=ExcelUtil.class.getResourceAsStream("/template/"+templateFileName);
		Workbook wb=null;
		if(POIFSFileSystem.hasPOIFSHeader(inp)) {
			wb = new HSSFWorkbook(inp);
		}else {
			wb = new XSSFWorkbook(inp); 
		}
		Sheet sheet=wb.getSheetAt(0); //获取第一个sheet页
		// 获取模板一行有多少列
		int cellNums=sheet.getRow(0).getLastCellNum();
		int rowIndex=1;      //从第二行开始插入数据
		while(rs.next()){
			Row row=sheet.createRow(rowIndex++);
			for(int i=0;i<cellNums;i++){
				row.createCell(i).setCellValue(rs.getObject(i+1).toString());
			}
		}
		return wb;
	}
	
	/**
	 * 利用模板导出数据（学生违纪信息）
	 */
	public static Workbook fillExcelDataWithTemplate2(ResultSet rs,String templateFileName)throws Exception{
		InputStream inp=ExcelUtil.class.getResourceAsStream("/template/"+templateFileName);
		Workbook wb=null;
		if(POIFSFileSystem.hasPOIFSHeader(inp)) {
			wb = new HSSFWorkbook(inp);
		}else {
			wb = new XSSFWorkbook(inp); 
		}
		Sheet sheet=wb.getSheetAt(0); //获取第一个sheet页
		// 获取模板一行有多少列
		int cellNums=sheet.getRow(0).getLastCellNum();
		int rowIndex=1;      //从第二行开始插入数据
		while(rs.next()){
			Row row=sheet.createRow(rowIndex++);
			for(int i=0;i<cellNums;i++){
				row.createCell(i).setCellValue(rs.getObject(i+1).toString());
			}
		}
		return wb;
	}
}
