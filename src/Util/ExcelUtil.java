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
				DataFormatter dataFormatter = new DataFormatter();  //���excel�������ݿ⣬������С������
				 
			    String cellFormatted = dataFormatter.formatCellValue(cell);
//			    System.out.println( cellFormatted );
				return cellFormatted;
			}else{
				return String.valueOf(cell.getStringCellValue());
			}
		}
	}
	/**
	 * �ж�Excel�汾�Ƿ�Ϊ2003
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
	 * ����һ��excel�ļ��Զ���ͷ���Ե�������
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
	 * ����ģ�嵼�����ݣ�ѧ����٣�
	 */
	public static Workbook fillExcelDataWithTemplate(ResultSet rs,String templateFileName)throws Exception{
		InputStream inp=ExcelUtil.class.getResourceAsStream("/template/"+templateFileName);
		Workbook wb=null;
//		if(POIXMLDocument.hasOOXMLHeader(inp)) {
//			//2007�汾������
//			wb = new XSSFWorkbook(inp); 
//		}else{
//			wb = new HSSFWorkbook(inp);
//		}
		if(POIFSFileSystem.hasPOIFSHeader(inp)) {
//			System.out.println("2003������");
			wb = new HSSFWorkbook(inp);
		}else {
//			System.out.println("2007������");
			wb = new XSSFWorkbook(inp); 
		}
		Sheet sheet=wb.getSheetAt(0); //��ȡ��һ��sheetҳ
		// ��ȡģ��һ���ж�����
		int cellNums=sheet.getRow(0).getLastCellNum();
		int rowIndex=1;      //�ӵڶ��п�ʼ��������
		while(rs.next()){
			Row row=sheet.createRow(rowIndex++);
			for(int i=0;i<cellNums;i++){
				row.createCell(i).setCellValue(rs.getObject(i+1).toString());//jdbc�������±��1��ʼ
			}
		}
		return wb;
	}
	
	/**
	 * ����ģ�嵼�����ݣ�ѧ����Ϣ��
	 */
	public static Workbook fillExcelDataWithTemplate1(ResultSet rs,String templateFileName)throws Exception{
		InputStream inp=ExcelUtil.class.getResourceAsStream("/template/"+templateFileName);
		Workbook wb=null;
		if(POIFSFileSystem.hasPOIFSHeader(inp)) {
			wb = new HSSFWorkbook(inp);
		}else {
			wb = new XSSFWorkbook(inp); 
		}
		Sheet sheet=wb.getSheetAt(0); //��ȡ��һ��sheetҳ
		// ��ȡģ��һ���ж�����
		int cellNums=sheet.getRow(0).getLastCellNum();
		int rowIndex=1;      //�ӵڶ��п�ʼ��������
		while(rs.next()){
			Row row=sheet.createRow(rowIndex++);
			for(int i=0;i<cellNums;i++){
				row.createCell(i).setCellValue(rs.getObject(i+1).toString());
			}
		}
		return wb;
	}
	
	/**
	 * ����ģ�嵼�����ݣ�ѧ��Υ����Ϣ��
	 */
	public static Workbook fillExcelDataWithTemplate2(ResultSet rs,String templateFileName)throws Exception{
		InputStream inp=ExcelUtil.class.getResourceAsStream("/template/"+templateFileName);
		Workbook wb=null;
		if(POIFSFileSystem.hasPOIFSHeader(inp)) {
			wb = new HSSFWorkbook(inp);
		}else {
			wb = new XSSFWorkbook(inp); 
		}
		Sheet sheet=wb.getSheetAt(0); //��ȡ��һ��sheetҳ
		// ��ȡģ��һ���ж�����
		int cellNums=sheet.getRow(0).getLastCellNum();
		int rowIndex=1;      //�ӵڶ��п�ʼ��������
		while(rs.next()){
			Row row=sheet.createRow(rowIndex++);
			for(int i=0;i<cellNums;i++){
				row.createCell(i).setCellValue(rs.getObject(i+1).toString());
			}
		}
		return wb;
	}
}
