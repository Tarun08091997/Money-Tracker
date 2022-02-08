
/*
 * what to put in 3rd sheet
 * 
 *
 * username
 * password
 * url 
 *   
 */


import java.io.*;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;

public class FileHandle {
	File fc;
	XSSFWorkbook wb;
	XSSFSheet Category;
	XSSFSheet Data;
	XSSFSheet Expenses;
	
 public FileHandle() {
	 
	 try {
		 /*
		  * create files and workbook/sheet
		  */
		fc = new File("Files/Category.xlsx");
		fc.createNewFile();
		wb = new XSSFWorkbook();
		Category = wb.createSheet("Category");
		Expenses = wb.createSheet("Expenses"); //  Category, Date , Money
		Data = wb.createSheet("Data");   // Username,Password,URL,useJDBC
		
		
		
	} catch (Exception ex) {
		// TODO Auto-generated catch block
		System.out.println(ex);
	}
	 
	 
		////////////////////////CODE
 }
 
 
 
 public void addExpenses(String Category,java.util.Date Date, int money) {
	 
	 try {
		   int row = Expenses.getLastRowNum(); 
			XSSFRow r = Expenses.createRow(row+1);
			XSSFCell C0 =r.createCell(0);
			XSSFCell C1 =r.createCell(1);
			XSSFCell C2 =r.createCell(2);
			
			C0.setCellValue(Category);
			C1.setCellValue(Date); 
			C2.setCellValue(money);
			
			FileOutputStream fout = new FileOutputStream(fc);
			wb.write(fout);
			fout.close();
			
		}catch(Exception ex){
			System.out.println(ex);			
		}
	 
	 
	 
 }
 
 
 public String getCategory_E(int row) {
	 String b = null;
		try {
			FileInputStream fin = new FileInputStream(fc);
			XSSFWorkbook wb_in = new XSSFWorkbook(fin);
			XSSFSheet sheet_in = wb_in.getSheet("Expenses");
			
			XSSFRow r = sheet_in.getRow(row);
			
			XSSFCell c = r.getCell(0);
			 b = c.getStringCellValue();
			 
			 fin.close();

		}catch(Exception ex) {
			System.out.println(ex);
		}
		 return b; 
 }
 
 
 public void removeExpense(String cat, String Date, int money) {
	 
	 try {
		 XSSFRow r;
		int i =-1;
		 String a=null;
		 String d=null;
		 int m =0;
		 while(a != cat && d != Date && m != money  ) {
			 i++;
			 r = Expenses.getRow(i);
			 XSSFCell cell0 = r.getCell(0);
			 XSSFCell cell1 = r.getCell(1);
			 XSSFCell cell2 = r.getCell(2);
			 
			 
			 a = cell0.getStringCellValue();
			 d = cell1.getStringCellValue();
			 m = (int) cell0.getNumericCellValue();
		 }
		 
		 
		 String set =  null;
		     r = Expenses.getRow(i);
		    r.getCell(0).setCellValue(set);
		    r.getCell(1).setCellValue(set);
		    r.getCell(2).setCellValue(set);
		     FileOutputStream fout = new FileOutputStream(fc);
		     
			 wb.write(fout);
		     fout.close();
	} catch (Exception ex) {
		// TODO Auto-generated catch block
		System.out.println(ex);
	}
 }
 
 public String getdate_E(int row) {
	 String b = null;
		try {
			FileInputStream fin = new FileInputStream(fc);
			XSSFWorkbook wb_in = new XSSFWorkbook(fin);
			XSSFSheet sheet_in = wb_in.getSheet("Expenses");
			
			XSSFRow r = sheet_in.getRow(row);
			
			XSSFCell c = r.getCell(1);
			 b = c.getStringCellValue();
			 
			 fin.close();

		}catch(Exception ex) {
			System.out.println(ex);
		}
		 return b;
	 
	 
 }
 
 public Date getDate_E(int row) {
	 Date b = null;
		try {
			FileInputStream fin = new FileInputStream(fc);
			XSSFWorkbook wb_in = new XSSFWorkbook(fin);
			XSSFSheet sheet_in = wb_in.getSheet("Expenses");
			
			XSSFRow r = sheet_in.getRow(row);
			
			XSSFCell c = r.getCell(1);
			 b = c.getDateCellValue();
			 
			 fin.close();

		}catch(Exception ex) {
			System.out.println(ex);
		}
		 return b;
	 
	 
 }

 
 public int getmoney_E(int row) {
	 int b = 0;
		try {
			FileInputStream fin = new FileInputStream(fc);
			XSSFWorkbook wb_in = new XSSFWorkbook(fin);
			XSSFSheet sheet_in = wb_in.getSheet("Expenses");
			
			XSSFRow r = sheet_in.getRow(row);
			
			XSSFCell c = r.getCell(2);
			 b = (int) c.getNumericCellValue();
			 
			 fin.close();

		}catch(Exception ex) {
			System.out.println(ex);
		}
		 return b;
	 
	 
 }
 
 public int getEx_rows() {
	 int rows = Expenses.getLastRowNum();
	 return rows;
 }
 
 
 public int getcat_rows() {
	 int rows = Category.getLastRowNum();
	 return rows;
 }
 
 
 public int addCategory(String cat) {
	   try {
		   int row = Category.getLastRowNum(); 
			XSSFRow r = Category.createRow(row+1);
			XSSFCell c =r.createCell(0);
			c.setCellValue(cat);
			
			FileOutputStream fout = new FileOutputStream(fc);
			wb.write(fout);
			fout.close();
			
			System.out.println("Sucessfull");
			
			return 1;
		}catch(Exception ex){
			System.out.println(ex);
			return -1;			
		}
	   
 }
 /*
  * to get category at a specified row value
  * Starts from 0
  */
 
 public String getCategory(int rowValue) {
	
	String b = null;
	try {
		FileInputStream fin = new FileInputStream(fc);
		XSSFWorkbook wb_in = new XSSFWorkbook(fin);
		XSSFSheet sheet_in = wb_in.getSheet("Category");
		
		XSSFRow r = sheet_in.getRow(rowValue);
		
		XSSFCell c = r.getCell(0);
		 b = c.getStringCellValue();
		 
		 fin.close();

	}catch(Exception ex) {
		System.out.println(ex);
	}
	 return b;
 }
 
 
 public void removeCategory(String c) {
	 
	 try {
		 XSSFRow r;
		int i =-1;
		 String a=null;
		 
		 while(a != c) {
			 i++;
			 r = Category.getRow(i);
			 XSSFCell cell = r.getCell(0);
			 a = cell.getStringCellValue();
		 }
		     r = Category.getRow(i);
		     XSSFCell cell = r.getCell(0);
		     String set =  null;
		     cell.setCellValue(set);
		     FileOutputStream fout = new FileOutputStream(fc);
		     
			 wb.write(fout);
		     fout.close();
	} catch (Exception ex) {
		// TODO Auto-generated catch block
		System.out.println(ex);
	}
	     
	 
	 
 }


 
 public void addusername(String username) {
      try {
		XSSFRow r = Data.createRow(0);
		  XSSFCell c = r.createCell(0);
		  c.setCellValue(username);
		  FileOutputStream fout = new FileOutputStream(fc);
			wb.write(fout);
			fout.close();
	} catch (Exception ex) {
		// TODO Auto-generated catch block
		System.out.println(ex);
	}
	}

 
 public void addpassword(String password) {
     try {
		XSSFRow r = Data.createRow(1);
		  XSSFCell c = r.createCell(0);
		  c.setCellValue(password);
		  FileOutputStream fout = new FileOutputStream(fc);
			wb.write(fout);
			fout.close();
	} catch (Exception ex) {
		// TODO Auto-generated catch block
		System.out.println(ex);
	}
	}
 
 public void addurl(String url) {
     try {
		XSSFRow r = Data.createRow(2);
		  XSSFCell c = r.createCell(0);
		  c.setCellValue(url);
		  FileOutputStream fout = new FileOutputStream(fc);
			wb.write(fout);
			fout.close();
	} catch (Exception ex) {
		// TODO Auto-generated catch block
		System.out.println(ex);
	}
	}
 
 public String getusername() {
	 String b = null;
		try {
			FileInputStream fin = new FileInputStream(fc);
			XSSFWorkbook wb_in = new XSSFWorkbook(fin);
			XSSFSheet sheet_in = wb_in.getSheet("Data");
			
			XSSFRow r = sheet_in.getRow(0);
			
			XSSFCell c = r.getCell(0);
			 b = c.getStringCellValue();
			 
			 fin.close();

		}catch(Exception ex) {
			System.out.println(ex);
		}
		 return b;
 }
 
 
 public String getpassword() {
	 String b = null;
		try {
			FileInputStream fin = new FileInputStream(fc);
			XSSFWorkbook wb_in = new XSSFWorkbook(fin);
			XSSFSheet sheet_in = wb_in.getSheet("Data");
			
			XSSFRow r = sheet_in.getRow(1);
			
			XSSFCell c = r.getCell(0);
			 b = c.getStringCellValue();
			 
			 fin.close();

		}catch(Exception ex) {
			System.out.println(ex);
		}
		 return b;
 }
 
 public String geturl() {
	 String b = null;
		try {
			FileInputStream fin = new FileInputStream(fc);
			XSSFWorkbook wb_in = new XSSFWorkbook(fin);
			XSSFSheet sheet_in = wb_in.getSheet("Data");
			
			XSSFRow r = sheet_in.getRow(2);
			
			XSSFCell c = r.getCell(0);
			 b = c.getStringCellValue();
			 
			 fin.close();

		}catch(Exception ex) {
			System.out.println(ex);
		}
		 return b;
 }
 
 
 public void addJDBCStatus(boolean useJDBC) {        //in row 3
     try {
		XSSFRow r = Data.createRow(3);
		  XSSFCell c = r.createCell(0);
		  c.setCellValue(useJDBC);
		  FileOutputStream fout = new FileOutputStream(fc);
			wb.write(fout);
			fout.close();
	} catch (Exception ex) {
		// TODO Auto-generated catch block
		System.out.println(ex);
	}
	}
 
 public boolean getJDBCStatus() {
	 boolean b = false;
		try {
			FileInputStream fin = new FileInputStream(fc);
			XSSFWorkbook wb_in = new XSSFWorkbook(fin);
			XSSFSheet sheet_in = wb_in.getSheet("Data");
			
			XSSFRow r = sheet_in.getRow(3);
			
			XSSFCell c = r.getCell(0);
			 b = c.getBooleanCellValue();
			 System.out.println(b);
			 
			 fin.close();

		}catch(Exception ex) {
			System.out.println(ex);
		}
		 return b;
 }
 
 
 }
 






 

