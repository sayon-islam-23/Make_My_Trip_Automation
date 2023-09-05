package util;
import java.io.*;
import java.util.*;
import org.apache.poi.xssf.usermodel.*;
public class ExcelData {
	public static void saveData(List<String> arr) {
	//Accepts list as an argument
	//Creates an instance of file class by passing parameter as string which is the file that will be worked upon
		
		
		//Creates an instance of XSSFWorkbook which will create an excel workbook
		File file = new File("Make_My_Trip.xlsx");
		
		//create an instance of the Workbook
		XSSFWorkbook wb = new XSSFWorkbook();
		
		// creating a sheet in the workbook.
		XSSFSheet sh = wb.createSheet();
		
		//Creates a row and also a cell in that row and set a cell value with the given name
		sh.createRow(0).createCell(0).setCellValue("Adult Room Available");
		for(int i=0;i<arr.size();i++){
			//creates row and set value inside cells
			sh.createRow(i+1).createCell(0).setCellValue(arr.get(i));
		}
		try {
			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
			wb.close();
		}
		catch(Exception e) {
			System.out.println("Unable to store Data!");
			e.printStackTrace();
		}
	}
}
