package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {

	Workbook wb;
	//Constructor for reading excel path.
	public ExcelFileUtil(String Excelpath) throws Throwable {
		if(Excelpath !=null) {
		FileInputStream fi = new FileInputStream(Excelpath);

		wb = new XSSFWorkbook(fi);
		}
		else {
			System.out.println("Path is not specified");
		}

	}
	//counting no of rows in a sheet

	public int rowCount(String sheetname) {
		Sheet sheet = wb.getSheet(sheetname);

		return sheet.getLastRowNum();

	}
	//method for reading cell data.
	public String getCellData(String sheetname, int row, int column) {
		
		String data = "";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC) {
			int celldata = (int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		
		}
		else {
			data =  wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
			
		}
		
		return data;
		
	}
	//Method for write results int new wb.
	public void setCellData(String sheetname, int row, int column, String status, String writeExcel) throws Throwable {
		
		//get sheet from wb
		XSSFSheet ws = (XSSFSheet) wb.getSheet(sheetname);
		//get row from sheet
		XSSFRow rowNum = ws.getRow(row);
		//create cell in a row
		XSSFCell cell = rowNum.createCell(column);
		//write status into cell
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
			XSSFFont font = (XSSFFont) wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
			XSSFFont font = (XSSFFont) wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Blocked"))
		{
			XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
			XSSFFont font = (XSSFFont) wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		
		FileOutputStream fo = new FileOutputStream(writeExcel);
		wb.write(fo);
		

	}
	public static void main(String[] args) throws Throwable {
		ExcelFileUtil xl = new ExcelFileUtil("D:/selenium.xlsx");
		int rc =xl.rowCount("Emp");
		System.out.println(rc);
		
		for(int i =1;i <=rc;i++) {
			String fname =xl.getCellData("Emp", i, 0);
			String mname = xl.getCellData("Emp", i, 2);
			String lname =xl.getCellData("Emp", i, 2);
			String eid = xl.getCellData("Emp", i, 3);
			System.out.println(fname +  " " + mname + " " + lname + " " + eid );
			xl.setCellData("Emp",i , 4, "pass", "D:/Resilts.xlsx");
		}
	}
	
	
	
	
	
	
	
}
