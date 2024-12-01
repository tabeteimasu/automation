package commonLibs.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//public class ExcelUtilities {
//	public static String readCell(String filename,int row,int column) throws Exception {
//		filename = filename.trim();
//		InputStream fileReader = new FileInputStream(filename);
//		XSSFWorkbook wb = new XSSFWorkbook(fileReader);
//		XSSFSheet sheet = wb.getSheetAt(0);
//		XSSFRow myrow = sheet.getRow(row);
//		XSSFCell cell = myrow.getCell(column);
//		String value = cell.getStringCellValue();
//		wb.close();
//		return value;
//	}
//}

public class ExcelUtilities {

    public static XSSFRow getRandomRow(String filename) throws IOException {
        filename = filename.trim();
        XSSFRow myrow = null;
        int rowCount = 0;
        Random random = new Random();

        try (InputStream fileReader = new FileInputStream(filename); 
             XSSFWorkbook wb = new XSSFWorkbook(fileReader)) {
        	
            XSSFSheet sheet = wb.getSheetAt(0);
            rowCount = sheet.getPhysicalNumberOfRows()-1;
            //System.out.println(rowCount);
            int randomRow = random.nextInt(rowCount)+1;
            //System.out.println(randomRow);
            
            myrow = sheet.getRow(randomRow);
            if (myrow == null) {
                throw new IllegalArgumentException("Row at specified index is null");
            }

        } catch (IOException e) {
            System.err.println("Error reading the Excel file: " + e.getMessage());
            throw e; // Rethrow the exception to inform the caller
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
            throw e; // Rethrow the exception to inform the caller
        }

        return myrow;
    }
    
    public static String[] countyNphone()
    {
    	String[] data = new String[2];
    	String currentWorkingDirectory=System.getProperty("user.dir");
    	String excelFileName=currentWorkingDirectory+"/config/county&phone.xlsx";
    	try {
			XSSFRow row = getRandomRow(excelFileName);
			XSSFCell cell1 = row.getCell(0);
			String county = cell1.getStringCellValue();
			XSSFCell cell2 = row.getCell(1);
			String phone = cell2.getStringCellValue().trim();
			data[0] = county;
			//System.out.println(county);
			data[1] = phone;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return data;
    }
}
