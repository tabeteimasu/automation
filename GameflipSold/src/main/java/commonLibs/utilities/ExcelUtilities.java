package commonLibs.utilities;
import commonLibs.utilities.ConfigUtilities;
import commonLibs.implementationn.GameflipItemEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


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
    
    public static void outputItemPrice(List<GameflipItemEntry> itemPriceList) throws Exception
    {
		String currentWorkingDirectory=System.getProperty("user.dir");
		String configFileName=currentWorkingDirectory+"/config/config.properties";
		Properties configProperty=ConfigUtilities.readProperties(configFileName);
		String url = configProperty.getProperty("gameflipURL");
		
    	String excelFileName=currentWorkingDirectory+"/config/item&price.xlsx";
        FileInputStream fileIn = new FileInputStream(excelFileName);
        Workbook workbook = WorkbookFactory.create(fileIn);
		
		 // Find the index of "listings"
        int listingsIndex = url.indexOf("/listings");
        String storeName = "", result = "";
        // Check if "listings" is found
        if (listingsIndex != -1) {
            // Find the last slash before "listings"
            int lastSlashIndex = url.lastIndexOf('/', listingsIndex);
            // Find the second last slash before "listings"
            int secondLastSlashIndex = url.lastIndexOf('/', lastSlashIndex - 1);
            
            // Extract the text between the two slashes
            storeName = url.substring(secondLastSlashIndex + 1, lastSlashIndex);
            
            // Get the current timestamp
            LocalDateTime now = LocalDateTime.now();
            
            // Define the format for the timestamp
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-HH-mm");

            // Format the timestamp
            String timestamp = now.format(formatter);

            // Combine the original string with the timestamp
            result = storeName + "(" + timestamp + ")";
            //System.out.println("Result: " + result);
        } else {
            System.out.println("'listings' not found in the URL.");
        }
        XSSFSheet sheet = (XSSFSheet) workbook.createSheet(result);

        // Create header row
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Item");
        headerRow.createCell(1).setCellValue("Price");
        headerRow.createCell(2).setCellValue("Quantity");
        
     // Initialize the data dictionary for json output
        Map<String, List<Object>> data = new HashMap<>();
        data.put("Item", new ArrayList<>());
        data.put("Price", new ArrayList<>());
        data.put("Quantity", new ArrayList<>());

        // Populate the Excel sheet with items and prices
        int rowNum = 1;
        double totalPrice = 0,totalQuantity=0;
        for (GameflipItemEntry entry : itemPriceList) {
            XSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entry.getItemName());
            row.createCell(1).setCellValue(entry.getPrice());
            row.createCell(2).setCellValue(entry.getQuantity());
            totalPrice += entry.getTotalPrice();
            totalQuantity += entry.getQuantity();
            
            //populate data for json output
            data.get("Item").add(entry.getItemName());
            data.get("Price").add(entry.getPrice());
            data.get("Quantity").add(entry.getQuantity());
        }
        XSSFRow row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("Total");
        row.createCell(1).setCellValue(totalPrice);
        row.createCell(2).setCellValue(totalQuantity);
        
        // Convert the data dictionary to JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(data);
        
        //output the json data
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentWorkingDirectory+"/config/"+result+".json"))) {
            writer.write(jsonOutput);
            System.out.println("JSON output written to output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //update the python file and excute it
        String filePath = "outputChart.py";  // Path to your Python file
        String newJsonData = "data = "+jsonOutput; // New data

        try {
            // Read the Python file
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder fileContent = new StringBuilder();
            String line;
            boolean insideJsonBlock = false;
       

            while ((line = reader.readLine()) != null) {
                // Check if we're starting to read a JSON block
                if (line.trim().startsWith("data = {")) {
                    insideJsonBlock = true;  // We found the start of the block
                }

                // If we are inside the JSON block, skip reading lines until we find the closing brace
                if (insideJsonBlock) {
                    if (line.trim().endsWith("}")) {
                        // End of JSON block
                        insideJsonBlock = false;
                        // Replace the block with new JSON data
                        fileContent.append(newJsonData).append(System.lineSeparator());
                    }
                    // Continue to the next iteration without appending this line
                    continue;
                }
                
                if(line.trim().startsWith("title_text =")) {
                	line = "title_text = " + "\"Total sales of \"" + "\""+storeName+"\"";
                }
                
                // If not inside a JSON block, append the line as is
                fileContent.append(line).append(System.lineSeparator());
            }
            reader.close();

            // Write the modified content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(fileContent.toString());
            writer.close();

            System.out.println("JSON data replaced successfully in the Python file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Execute the Python script
        //ProcessBuilder processBuilder = new ProcessBuilder("python3", filePath);
        ProcessBuilder processBuilder = new ProcessBuilder("/Library/Frameworks/Python.framework/Versions/3.13/bin/python3", filePath);
        processBuilder.redirectErrorStream(true);  // Redirect error stream to output stream

        // Start the process
        Process process = processBuilder.start();

        // Read the output from the command
        try (BufferedReader processReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String outputLine;
            while ((outputLine = processReader.readLine()) != null) {
                System.out.println(outputLine);
            }
        }
        // Wait for the process to finish and check exit code
        int exitCode = process.waitFor();
        System.out.println("Python script executed with exit code: " + exitCode);
       

        
        
        // Write the output to the spreadsheet
        try (FileOutputStream fileOut = new FileOutputStream(excelFileName)) {
            workbook.write(fileOut);
        }

        // Close the workbook
        workbook.close();
    	
    }
}
